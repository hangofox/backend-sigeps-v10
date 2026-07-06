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
@Table(name = "TABLA_PAISES_MUNDO")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class PaisMundo {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_PAIS_MUNDO", columnDefinition="BIGINT NOT NULL")
    private Long idPaisMundo;
    
    @Column(name = "NOMBRE_PAIS_MUNDO", columnDefinition="VARCHAR(250) NOT NULL")
    private String nombrePaisMundo;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD:
    public Long getIdPaisMundo() {
        return idPaisMundo;
    }
    public void setIdPaisMundo(Long idPaisMundo) {
        this.idPaisMundo = idPaisMundo;
    }
    public String getNombrePaisMundo() {
        return nombrePaisMundo;
    }
    public void setNombrePaisMundo(String nombrePaisMundo) {
        this.nombrePaisMundo = nombrePaisMundo;
    }*/
}
