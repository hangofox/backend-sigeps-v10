//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_ESTABLECIMIENTOS_CLIENTES")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class EstablecimientoCliente {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    private Long idEstablecimientoCliente;
    
    @Column(name = "NUMERO_DOCUMENTO_IDENTIFICACION_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String numeroDocumentoIdentificacionEstablecimientoCliente;
    
    @Column(name = "NOMBRE_RAZON_SOCIAL_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(255) NOT NULL")
    private String nombreRazonSocialEstablecimientoCliente;
    
    @Column(name = "FECHA_H_M_S_INGRESO_ESTABLECIMIENTO_CLIENTE", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoEstablecimientoCliente;
    
    @Column(name = "FECHA_H_M_S_MODIFICACION_ESTABLECIMIENTO_CLIENTE", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSModificacionEstablecimientoCliente;
    
    @Column(name = "ESTADO_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoEstablecimientoCliente;
    
    //@Column(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoDocumentoIdentificacion;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    private TipoDocumentoIdentificacion tipoDocumentoIdentificacion;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public String getEstadoEstablecimientoCliente() {
        return estadoEstablecimientoCliente;
    }
    public void setEstadoEstablecimientoCliente(String estadoEstablecimientoCliente) {
        this.estadoEstablecimientoCliente = estadoEstablecimientoCliente;
    }
    public Date getFechaHMSIngresoEstablecimientoCliente() {
        return fechaHMSIngresoEstablecimientoCliente;
    }
    public void setFechaHMSIngresoEstablecimientoCliente(Date fechaHMSIngresoEstablecimientoCliente) {
        this.fechaHMSIngresoEstablecimientoCliente = fechaHMSIngresoEstablecimientoCliente;
    }
    public Date getFechaHMSModificacionEstablecimientoCliente() {
        return fechaHMSModificacionEstablecimientoCliente;
    }
    public void setFechaHMSModificacionEstablecimientoCliente(Date fechaHMSModificacionEstablecimientoCliente) {
        this.fechaHMSModificacionEstablecimientoCliente = fechaHMSModificacionEstablecimientoCliente;
    }
    public Long getIdEstablecimientoCliente() {
        return idEstablecimientoCliente;
    }
    public void setIdEstablecimientoCliente(Long idEstablecimientoCliente) {
        this.idEstablecimientoCliente = idEstablecimientoCliente;
    }
    public String getNombreRazonSocialEstablecimientoCliente() {
        return nombreRazonSocialEstablecimientoCliente;
    }
    public void setNombreRazonSocialEstablecimientoCliente(String nombreRazonSocialEstablecimientoCliente) {
        this.nombreRazonSocialEstablecimientoCliente = nombreRazonSocialEstablecimientoCliente;
    }
    public String getNumeroDocumentoIdentificacionEstablecimientoCliente() {
        return numeroDocumentoIdentificacionEstablecimientoCliente;
    }
    public void setNumeroDocumentoIdentificacionEstablecimientoCliente(String numeroDocumentoIdentificacionEstablecimientoCliente) {
        this.numeroDocumentoIdentificacionEstablecimientoCliente = numeroDocumentoIdentificacionEstablecimientoCliente;
    }
    public TipoDocumentoIdentificacion getTipoDocumentoIdentificacion() {
        return tipoDocumentoIdentificacion;
    }
    public void setTipoDocumentoIdentificacion(TipoDocumentoIdentificacion tipoDocumentoIdentificacion) {
        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
    }*/
}
