//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EmailDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.EmailService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ParametrosSistema;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.RecuperacionContrasenaAccesoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ParametrosSistemaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.RecuperacionContrasenaAccesoUsuarioRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.UsuarioRepository;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
* * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/07/2026.
* Esta es la declaración de la implementación del servicio.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class EmailServiceImpl implements EmailService {
    
    //ENVIAR CORREO ELECTRÓNICO:
    
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);//DECLARACIÓN DE VARIABLES DE LOG.
    
    //ÚNICO REGISTRO DE PARÁMETROS DEL SISTEMA (SMTP, PLANTILLAS, ETC.):
    private static final Long ID_PARAMETROS_SISTEMA = 1L;
    
    //MARCADORES DE LA PLANTILLA DE CORREO ALMACENADA EN parametrosSistema.cuerpoMensajeHtmlRecuperacionContrasena:
    private static final String MARCADOR_NUMERO_DOCUMENTO = "*[NUMDOCIDSADEC]*";
    private static final String MARCADOR_CODIGO_ACTIVACION = "*[CODACTIVAUSIGEPS]*";
    
    @Autowired//INYECTAMOS EL REPOSITORIO PARA CONSULTAR LA CONFIGURACIÓN SMTP Y LA PLANTILLA INTERNAMENTE (NUNCA DESDE EL CLIENTE).
    private ParametrosSistemaRepository parametrosSistemaRepository;
    
    @Autowired//INYECTAMOS EL REPOSITORIO DE USUARIOS (PARA RESOLVER EL CORREO DE DESTINO Y EL NÚMERO DE DOCUMENTO INTERNAMENTE, NUNCA DESDE EL CLIENTE).
    private UsuarioRepository usuarioRepository;
    
    @Autowired//INYECTAMOS EL REPOSITORIO DE RECUPERACIONES (PARA RESOLVER EL CÓDIGO DE ACTIVACIÓN VIGENTE INTERNAMENTE, NUNCA DESDE EL CLIENTE).
    private RecuperacionContrasenaAccesoUsuarioRepository recuperacionContrasenaAccesoUsuarioRepository;
    
    @Override//SOBREESCRIBIMOS EL METODO DE ENVIAR CORREO ELECTRÓNICO.
    public RespuestaDTO enviarCorreoElectronico(EmailDTO emailDTO) {
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_CORREO_ELECTRONICO_NO_ENVIADO, false);
        
        //SE RESUELVE EL USUARIO Y SU CORREO DE DESTINO (INSTITUCIONAL O PERSONAL) DESDE SU PROPIO REGISTRO, NUNCA
        //DESDE UNA DIRECCIÓN QUE ENVÍE EL CLIENTE:
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdUsuario(emailDTO.getIdUsuario());
        if (usuarioOpt.isEmpty()) {
            return new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        }
        Usuario usuario = usuarioOpt.get();
        String correoElectronicoDestinatario = "INSTITUCIONAL".equalsIgnoreCase(emailDTO.getMedioEnvio())
            ? usuario.getCorreoElectronicoInstitucionalUsuario()
            : usuario.getCorreoElectronicoPersonalUsuario();
        if (correoElectronicoDestinatario == null || correoElectronicoDestinatario.isBlank()) {
            return new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        }
        
        //SE BUSCA EL CÓDIGO DE ACTIVACIÓN VIGENTE DE ESTE USUARIO (EL MÁS RECIENTE, YA GUARDADO PREVIAMENTE POR
        //crearRecuperacionContrasenaAccesoUsuario):
        List<RecuperacionContrasenaAccesoUsuario> recuperaciones = recuperacionContrasenaAccesoUsuarioRepository.searchRecuperacionesContrasenasAccesosUsuariosByIdUsuarioOrderedByIdAsc(emailDTO.getIdUsuario());
        if (recuperaciones == null || recuperaciones.isEmpty()) {
            return new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        }
        String codigoActivacionContrasenaAccesoUsuario = recuperaciones.get(recuperaciones.size() - 1).getCodigoActivacionContrasenaAccesoUsuario();
        
        //SE CONSULTA LA CONFIGURACIÓN SMTP Y LA PLANTILLA DEL CORREO DIRECTAMENTE EN LA BASE DE DATOS (NUNCA SE
        //RECIBEN DESDE EL FRONTEND, PARA QUE LA CONTRASEÑA Y DEMÁS DATOS DEL REMITENTE NUNCA VIAJEN POR EL NAVEGADOR):
        Optional<ParametrosSistema> parametrosSistemaOpt = parametrosSistemaRepository.findByIdParametrosSistema(ID_PARAMETROS_SISTEMA);
        if (parametrosSistemaOpt.isEmpty()) {
            return new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        }
        ParametrosSistema parametrosSistema = parametrosSistemaOpt.get();
        boolean authEnable = Boolean.parseBoolean(parametrosSistema.getAuthEnable());
        boolean startTTLSEnable = Boolean.parseBoolean(parametrosSistema.getStartTtlsEnable());
        int smtpPort = Integer.parseInt(parametrosSistema.getSmtpPort());
        
        //SE ARMA EL ASUNTO Y EL CUERPO HTML DEL CORREO REEMPLAZANDO LOS MARCADORES DE LA PLANTILLA CON EL NÚMERO
        //DE DOCUMENTO Y EL CÓDIGO DE ACTIVACIÓN RESUELTOS INTERNAMENTE (NUNCA VIENEN DEL CLIENTE):
        String asuntoDestinatario = parametrosSistema.getAsuntoDestinatarioRecuperacionContrasena();
        String cuerpoMensajeHtml = parametrosSistema.getCuerpoMensajeHtmlRecuperacionContrasena()
            .replace(MARCADOR_NUMERO_DOCUMENTO, usuario.getNumeroDocumentoIdentificacionUsuario())
            .replace(MARCADOR_CODIGO_ACTIVACION, codigoActivacionContrasenaAccesoUsuario);
            
        try {
            //CONFIGURACIÓN DEL SERVIDOR SMTP:
            Properties props = new Properties();
            props.put("mail.smtp.auth", authEnable);//PERMITE SI O NO ACTIVAR EN VERDADERO O FALSO (TRUE OR FALSE) LA AUTENTICACIÓN SMTP CON USUARIO Y PASSWORD.
            props.put("mail.smtp.starttls.enable", startTTLSEnable);//PERMITE SI O NO ACTIVAR EN VERDADERO O FALSO (TRUE OR FALSE) EL CIFRADO DE LA CONEXIÓN.
            props.put("mail.smtp.host", parametrosSistema.getSmtpHost());//ESPECÍFICA LA DIRECCIÓN DEL SERVIDOR SMTP AL QUE SE CONECTARÁ PARA ENVIAR LOS CORREOS ELECTRÓNICOS.
            props.put("mail.smtp.ssl.trust", parametrosSistema.getSmtpHost());//ESPECÍFICA QUE SERVIDORES SE CONSIDERAN CONFIABLES PARA CONEXIONES SSL (DIRECCIÓN DEL SERVIDOR SMTP).
            props.put("mail.smtp.port", String.valueOf(smtpPort));//ESPECÍFICA EL PUERTO DEL SERVIDOR SMTP AL QUE SE CONECTARÁ PARA ENVIAR LOS CORREOS ELECTRÓNICOS.
            props.put("mail.smtp.ssl.protocols", parametrosSistema.getSmtpProtocols());//ESPECÍFICA LOS PROTOCOLOS SSL/TLS CON SU VERSIÓN QUE SE DEBEN DE USAR PARA ENVIAR LOS CORREOS ELECTRÓNICOS.
            
            //SI SE USA SSL (PUERTO 465):
            if (smtpPort==465) {
               props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort));
               props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
               props.put("mail.smtp.ssl.enable", "true");
            }
            
            //AUTENTICACIÓN DEL REMITENTE:
            Session session = null;
            if (authEnable==true) {
               final String usuarioRemitente = parametrosSistema.getUsuarioRemitente();
               final String passwordRemitente = parametrosSistema.getPasswordRemitente();
               session = Session.getInstance(props, new Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(usuarioRemitente, passwordRemitente);
                   }
               });
            }
            if (authEnable==false) {
               session = Session.getInstance(props);
            }
            
            //COMPOSICIÓN DEL MENSAJE:
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(parametrosSistema.getCorreoElectronicoRemitente()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoElectronicoDestinatario));
            message.setSubject(asuntoDestinatario);
            message.setContent(cuerpoMensajeHtml, "text/html; charset=UTF-8");
            
            //ENVIO DEL MENSAJE:
            Transport.send(message);
            
            respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_CORREO_ELECTRONICO_ENVIADO_EXITO, true);
        } catch (Exception e) {
            //CAPTURA EL STACKTRACE COMO TEXTO:
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();
            //e.printStackTrace();//MUESTRA STACKTRACE EN CONSOLA.
            logger.error("Error al enviar correo electrónico", e);//MUESTRA TRAZA COMPLETA.
            respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_ERROR_ENVIO_CORREO_ELECTRONICO + " " + stackTrace, false);
        }
        
        return respuestaDTO;
    }
}
