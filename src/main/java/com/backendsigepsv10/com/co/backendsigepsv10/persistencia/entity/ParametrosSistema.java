//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_PARAMETROS_SISTEMA")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class ParametrosSistema {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_PARAMETROS_SISTEMA", columnDefinition="BIGINT NOT NULL")
    private Long idParametrosSistema;
    
    @Column(name = "TIEMPO_MINUTOS_SESION_INACTIVA_SISTEMA", columnDefinition="BIGINT NOT NULL")
    private Long tiempoMinutosSesionInactivaSistema;
    
    @Column(name = "TIEMPO_MINUTOS_VALIDEZ_CODIGO_ACTIVACION_SISTEMA", columnDefinition="BIGINT NOT NULL")
    private Long tiempoMinutosValidezCodigoActivacionSistema;
    
    @Column(name = "RUTA_DESTINO_CARPETA_PRINCIPAL_SERVIDOR_APLICACIONES", columnDefinition="TEXT NOT NULL")
    private String rutaDestinoCarpetaPrincipalServidorAplicaciones;
    
    @Column(name = "RUTA_DESTINO_CARPETA_CARGUE_TEMPORAL_ARCHIVOS", columnDefinition="TEXT NOT NULL")
    private String rutaDestinoCarpetaCargueTemporalArchivos;
    
    @Column(name = "RUTA_DESTINO_ARCHIVOS_USUARIOS", columnDefinition="TEXT NOT NULL")
    private String rutaDestinoArchivosUsuarios;
    
    @Column(name = "RUTA_DESTINO_ARCHIVOS_EMPLEADOS", columnDefinition="TEXT NOT NULL")
    private String rutaDestinoArchivosEmpleados;
    
    @Column(name = "AUTH_ENABLE", columnDefinition="VARCHAR(150) NOT NULL")
    private String authEnable;
    
    @Column(name = "START_TTLS_ENABLE", columnDefinition="TEXT NOT NULL")
    private String startTtlsEnable;
    
    @Column(name = "SMTP_HOST", columnDefinition="VARCHAR(150) NOT NULL")
    private String smtpHost;
    
    @Column(name = "SMTP_PORT", columnDefinition="VARCHAR(20) NOT NULL")
    private String smtpPort;
    
    @Column(name = "SMTP_PROTOCOLS", columnDefinition="VARCHAR(150) NOT NULL")
    private String smtpProtocols;
    
    @Column(name = "USUARIO_REMITENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String usuarioRemitente;
    
    @Column(name = "PASSWORD_REMITENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String passwordRemitente;
    
    @Column(name = "CORREO_ELECTRONICO_REMITENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String correoElectronicoRemitente;
    
    @Column(name = "ASUNTO_DESTINATARIO_RECUPERACION_CONTRASENA", columnDefinition="VARCHAR(150) NOT NULL")
    private String asuntoDestinatarioRecuperacionContrasena;
    
    @Column(name = "CUERPO_MENSAJE_HTML_RECUPERACION_CONTRASENA", columnDefinition="TEXT NOT NULL")
    private String cuerpoMensajeHtmlRecuperacionContrasena;
}
