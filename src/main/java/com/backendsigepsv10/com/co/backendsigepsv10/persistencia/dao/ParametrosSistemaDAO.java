//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ParametrosSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ParametrosSistema;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class ParametrosSistemaDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param parametrosSistemaDTO
    * Recibe un DTO para crear un objeto parametrosSistema.
    * @return parametrosSistema
    */
    public ParametrosSistema parametrosSistema(ParametrosSistemaDTO parametrosSistemaDTO){
        ParametrosSistema parametrosSistema = new ParametrosSistema();
        parametrosSistema.setIdParametrosSistema(parametrosSistemaDTO.getIdParametrosSistema());
        parametrosSistema.setTiempoMinutosSesionInactivaSistema(parametrosSistemaDTO.getTiempoMinutosSesionInactivaSistema());
        parametrosSistema.setTiempoMinutosValidezCodigoActivacionSistema(parametrosSistemaDTO.getTiempoMinutosValidezCodigoActivacionSistema());
        parametrosSistema.setRutaDestinoCarpetaPrincipalServidorAplicaciones(parametrosSistemaDTO.getRutaDestinoCarpetaPrincipalServidorAplicaciones());
        parametrosSistema.setRutaDestinoCarpetaCargueTemporalArchivos(parametrosSistemaDTO.getRutaDestinoCarpetaCargueTemporalArchivos());
        parametrosSistema.setRutaDestinoArchivosUsuarios(parametrosSistemaDTO.getRutaDestinoArchivosUsuarios());
        parametrosSistema.setRutaDestinoArchivosEmpleados(parametrosSistemaDTO.getRutaDestinoArchivosEmpleados());
        parametrosSistema.setAuthEnable(parametrosSistemaDTO.getAuthEnable());
        parametrosSistema.setStartTtlsEnable(parametrosSistemaDTO.getStartTtlsEnable());
        parametrosSistema.setSmtpHost(parametrosSistemaDTO.getSmtpHost());
        parametrosSistema.setSmtpPort(parametrosSistemaDTO.getSmtpPort());
        parametrosSistema.setSmtpProtocols(parametrosSistemaDTO.getSmtpProtocols());
        parametrosSistema.setUsuarioRemitente(parametrosSistemaDTO.getUsuarioRemitente());
        parametrosSistema.setPasswordRemitente(parametrosSistemaDTO.getPasswordRemitente());
        parametrosSistema.setCorreoElectronicoRemitente(parametrosSistemaDTO.getCorreoElectronicoRemitente());
        parametrosSistema.setAsuntoDestinatarioRecuperacionContrasena(parametrosSistemaDTO.getAsuntoDestinatarioRecuperacionContrasena());
        parametrosSistema.setCuerpoMensajeHtmlRecuperacionContrasena(parametrosSistemaDTO.getCuerpoMensajeHtmlRecuperacionContrasena());
        
        return parametrosSistema;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param parametrosSistema
    * Recibe un objeto parametrosSistema para crear un DTO.
    * @return parametrosSistemaDTO
    */
    public ParametrosSistemaDTO parametrosSistemaDTO(ParametrosSistema parametrosSistema){
        ParametrosSistemaDTO parametrosSistemaDTO = new ParametrosSistemaDTO();
        parametrosSistemaDTO.setIdParametrosSistema(parametrosSistema.getIdParametrosSistema());
        parametrosSistemaDTO.setTiempoMinutosSesionInactivaSistema(parametrosSistema.getTiempoMinutosSesionInactivaSistema());
        parametrosSistemaDTO.setTiempoMinutosValidezCodigoActivacionSistema(parametrosSistema.getTiempoMinutosValidezCodigoActivacionSistema());
        parametrosSistemaDTO.setRutaDestinoCarpetaPrincipalServidorAplicaciones(parametrosSistema.getRutaDestinoCarpetaPrincipalServidorAplicaciones());
        parametrosSistemaDTO.setRutaDestinoCarpetaCargueTemporalArchivos(parametrosSistema.getRutaDestinoCarpetaCargueTemporalArchivos());
        parametrosSistemaDTO.setRutaDestinoArchivosUsuarios(parametrosSistema.getRutaDestinoArchivosUsuarios());
        parametrosSistemaDTO.setRutaDestinoArchivosEmpleados(parametrosSistema.getRutaDestinoArchivosEmpleados());
        parametrosSistemaDTO.setAuthEnable(parametrosSistema.getAuthEnable());
        parametrosSistemaDTO.setStartTtlsEnable(parametrosSistema.getStartTtlsEnable());
        parametrosSistemaDTO.setSmtpHost(parametrosSistema.getSmtpHost());
        parametrosSistemaDTO.setSmtpPort(parametrosSistema.getSmtpPort());
        parametrosSistemaDTO.setSmtpProtocols(parametrosSistema.getSmtpProtocols());
        parametrosSistemaDTO.setUsuarioRemitente(parametrosSistema.getUsuarioRemitente());
        parametrosSistemaDTO.setPasswordRemitente(parametrosSistema.getPasswordRemitente());
        parametrosSistemaDTO.setCorreoElectronicoRemitente(parametrosSistema.getCorreoElectronicoRemitente());
        parametrosSistemaDTO.setAsuntoDestinatarioRecuperacionContrasena(parametrosSistema.getAsuntoDestinatarioRecuperacionContrasena());
        parametrosSistemaDTO.setCuerpoMensajeHtmlRecuperacionContrasena(parametrosSistema.getCuerpoMensajeHtmlRecuperacionContrasena());
        
        return parametrosSistemaDTO;
    }
}
