//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ParametrosSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ParametrosSistemaRecuperacionContrasenaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ParametrosSistemaService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.ParametrosSistemaDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ParametrosSistema;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ParametrosSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class ParametrosSistemaServiceImpl implements ParametrosSistemaService {

    @Autowired//INYECTAMOS EL DAO.
    private ParametrosSistemaDAO parametrosSistemaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ParametrosSistemaRepository parametrosSistemaRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTEO.
    public Long contarTotalRegistros(Long idParametrosSistema, String keyword) {
        return parametrosSistemaRepository.findTotalRegistros(idParametrosSistema, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<ParametrosSistemaDTO> listarParametrosSistemas(Long idParametrosSistema, String keyword, String orderBy, String orderMode) {
        List<ParametrosSistema> parametrosSistemas = parametrosSistemaRepository.findAllParametrosSistemas(idParametrosSistema, keyword, orderBy, orderMode);
        List<ParametrosSistemaDTO> parametrosSistemaDTOS = new ArrayList<>();
        
        for (ParametrosSistema parametrosSistema : parametrosSistemas){
            parametrosSistemaDTOS.add(parametrosSistemaDAO.parametrosSistemaDTO(parametrosSistema));
        }
        
        return parametrosSistemaDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS CON PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public Slice<ParametrosSistemaDTO> listarParametrosSistemasPag(Pageable pageable, Long idParametrosSistema, String keyword, String orderBy, String orderMode) {
        Slice<ParametrosSistema> parametrosSistemas = parametrosSistemaRepository.findAllParametrosSistemasPag(pageable, idParametrosSistema, keyword, orderBy, orderMode);
        return parametrosSistemas.map(parametrosSistema -> parametrosSistemaDAO.parametrosSistemaDTO(parametrosSistema));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearParametrosSistema(ParametrosSistemaDTO parametrosSistemaDTO) {
        Long maxIdParametrosSistema=null;
        ParametrosSistema parametrosSistemaSmtpHost = parametrosSistemaRepository.findBySmtpHost(parametrosSistemaDTO.getSmtpHost());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaSmtpHostRegistroEncontrado=0;
        
        if (!(parametrosSistemaSmtpHost==null)) {//SI ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON EL SMTP HOST PROPORCIONADO.
           banderaSmtpHostRegistroEncontrado=1;
        }
        
        if (banderaSmtpHostRegistroEncontrado==1) {//SI ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON EL SMTP HOST PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setParametrosSistemaDTO(null);
        }
        if ((banderaSmtpHostRegistroEncontrado==0) ) {//SI NO ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL SMTP HOST PROPORCIONADO.
           maxIdParametrosSistema = parametrosSistemaRepository.findMaxIdParametrosSistema();
           if (maxIdParametrosSistema==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdParametrosSistema=Long.valueOf(0);
           }
           parametrosSistemaDTO.setIdParametrosSistema(maxIdParametrosSistema+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           parametrosSistemaRepository.save(parametrosSistemaDAO.parametrosSistema(parametrosSistemaDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarParametrosSistemaporId(Long idParametrosSistema) {
        Optional<ParametrosSistema> parametrosSistemaId = parametrosSistemaRepository.findByIdParametrosSistema(Long.valueOf(idParametrosSistema));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (parametrosSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setParametrosSistemaDTO(parametrosSistemaDAO.parametrosSistemaDTO(parametrosSistemaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (parametrosSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setParametrosSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR SMTP HOST:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarParametrosSistemaporSmtpHost(String smtpHost) {
        Optional<ParametrosSistema> parametrosSistemaSmtpHost = Optional.ofNullable(parametrosSistemaRepository.findBySmtpHost(String.valueOf(smtpHost)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (parametrosSistemaSmtpHost.isPresent()==true) {//SI ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL SMTP HOST PROPORCIONADO.
           respuestaDTO.setParametrosSistemaDTO(parametrosSistemaDAO.parametrosSistemaDTO(parametrosSistemaSmtpHost.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (parametrosSistemaSmtpHost.isPresent()==false) {//SI NO ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL SMTP HOST PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setParametrosSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarParametrosSistema(ParametrosSistemaDTO parametrosSistemaDTO) {
        Optional<ParametrosSistema> parametrosSistemaId = parametrosSistemaRepository.findByIdParametrosSistema(parametrosSistemaDTO.getIdParametrosSistema());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (parametrosSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL SMTP HOST DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (parametrosSistemaDTO.getSmtpHost().equals(parametrosSistemaId.get().getSmtpHost())==true) ) {//SI EL SMTP HOST DIGITADO ES IGUAL AL SMTP HOST ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              ParametrosSistema parametrosSistema = parametrosSistemaDAO.parametrosSistema(parametrosSistemaDTO);
              parametrosSistemaRepository.save(parametrosSistema);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (parametrosSistemaDTO.getSmtpHost().equals(parametrosSistemaId.get().getSmtpHost())==false) {//SI EL SMTP HOST DIGITADO ES DIFERENTE AL SMTP HOST ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE SMTP HOST DIGITADO EXISTE EN OTROS REGISTROS.
              ParametrosSistema parametrosSistemaSmtpHost = parametrosSistemaRepository.findBySmtpHost(parametrosSistemaDTO.getSmtpHost());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaSmtpHostRegistroEncontrado=0;
              
              if (!(parametrosSistemaSmtpHost==null)) {//SI ENCONTRO EL SMTP HOST DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON EL SMTP HOST PROPORCIONADO.
                 banderaSmtpHostRegistroEncontrado=1;
              }
              
              if (banderaSmtpHostRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL SMTP HOST DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO SMTP HOST ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setParametrosSistemaDTO(null);
              }
              if (banderaSmtpHostRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL SMTP HOST DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 ParametrosSistema parametrosSistema = parametrosSistemaDAO.parametrosSistema(parametrosSistemaDTO);
                 parametrosSistemaRepository.save(parametrosSistema);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (parametrosSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setParametrosSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarParametrosSistema(Long idParametrosSistema) {
        Optional<ParametrosSistema> parametrosSistemaId = parametrosSistemaRepository.findById(idParametrosSistema);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (parametrosSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setParametrosSistemaDTO(parametrosSistemaDAO.parametrosSistemaDTO(parametrosSistemaId.get()));
           parametrosSistemaRepository.delete(parametrosSistemaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (parametrosSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setParametrosSistemaDTO(null);
        }

        return respuestaDTO;
    }

    //CONSULTA PÚBLICA (SIN AUTENTICACIÓN) DEL TIEMPO DE EXPIRACIÓN DEL CÓDIGO DE ACTIVACIÓN, ÚNICO DATO QUE EL
    //FLUJO DE RECUPERACIÓN DE CONTRASEÑA NECESITA ANTES DE INICIAR SESIÓN. NUNCA DEVUELVE LOS CAMPOS SMTP NI
    //LA PLANTILLA DEL CORREO (ESOS LOS ARMA EmailServiceImpl INTERNAMENTE):
    @Override//SOBREESCRIBIMOS EL METODO DE CONSULTA PÚBLICA.
    public RespuestaDTO consultarDatosPublicosRecuperacionContrasena() {
        Optional<ParametrosSistema> parametrosSistemaId = parametrosSistemaRepository.findByIdParametrosSistema(1L);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);

        if (parametrosSistemaId.isPresent()==true) {//SI ENCONTRO EL REGISTRO ÚNICO DE PARÁMETROS DEL SISTEMA, ARMA EL DTO LIVIANO SOLO CON EL TIEMPO DE EXPIRACIÓN.
           ParametrosSistemaRecuperacionContrasenaDTO datosPublicos = new ParametrosSistemaRecuperacionContrasenaDTO();
           datosPublicos.setTiempoMinutosValidezCodigoActivacionSistema(parametrosSistemaId.get().getTiempoMinutosValidezCodigoActivacionSistema());

           respuestaDTO.setParametrosSistemaRecuperacionContrasenaDTO(datosPublicos);
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }

        return respuestaDTO;
    }
}
