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
* @Since 09/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_TIPOS_DOCUMENTOS_IDENTIFICACION")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class TipoDocumentoIdentificacion {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    private Long idTipoDocumentoIdentificacion;
    
    @Column(name = "NOMBRE_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="VARCHAR(150) NOT NULL")
    private String nombreTipoDocumentoIdentificacion;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdTipoDocumentoIdentificacion() {
        return idTipoDocumentoIdentificacion;
    }
    public void setIdTipoDocumentoIdentificacion(Long idTipoDocumentoIdentificacion) {
        this.idTipoDocumentoIdentificacion = idTipoDocumentoIdentificacion;
    }
    public String getNombreTipoDocumentoIdentificacion() {
        return nombreTipoDocumentoIdentificacion;
    }
    public void setNombreTipoDocumentoIdentificacion(String nombreTipoDocumentoIdentificacion) {
        this.nombreTipoDocumentoIdentificacion = nombreTipoDocumentoIdentificacion;
    }*/
}
