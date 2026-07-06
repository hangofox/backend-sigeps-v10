//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
//Anotacion de lombok que me crea automaticamente los get, set constructor.
public class ParametrosSistemaDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idParametrosSistema;
    private Long tiempoMinutosSesionInactivaSistema;
    private Long tiempoMinutosValidezCodigoActivacionSistema;
    private String rutaDestinoCarpetaPrincipalServidorAplicaciones;
    private String rutaDestinoCarpetaCargueTemporalArchivos;
    private String rutaDestinoArchivosUsuarios;
    private String rutaDestinoArchivosEmpleados;
    private String authEnable;
    private String startTtlsEnable;
    private String smtpHost;
    private String smtpPort;
    private String smtpProtocols;
    private String usuarioRemitente;
    private String passwordRemitente;
    private String correoElectronicoRemitente;
    private String asuntoDestinatarioRecuperacionContrasena;
    private String cuerpoMensajeHtmlRecuperacionContrasena;
}
