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
@Table(name = "TABLA_FUNCIONALIDADES")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class Funcionalidad {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_FUNCIONALIDAD", columnDefinition="BIGINT NOT NULL")
    private Long idFuncionalidad;
    
    @Column(name = "LABEL_MENU_PRINCIPAL_FUNCIONALIDAD", columnDefinition="VARCHAR(150) NOT NULL")
    private String labelMenuPrincipalFuncionalidad;
    
    @Column(name = "NOMBRE_FUNCIONALIDAD", columnDefinition="VARCHAR(150) NOT NULL")
    private String nombreFuncionalidad;
    
    @Column(name = "NOMBRE_ICONO_MENU_PRINCIPAL_FUNCIONALIDAD", columnDefinition="VARCHAR(150) NOT NULL")
    private String nombreIconoMenuPrincipalFuncionalidad;

    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdFuncionalidad() {
        return idFuncionalidad;
    }
    public void setIdFuncionalidad(Long idFuncionalidad) {
        this.idFuncionalidad = idFuncionalidad;
    }
    public String getLabelMenuPrincipalFuncionalidad() {
        return labelMenuPrincipalFuncionalidad;
    }
    public void setLabelMenuPrincipalFuncionalidad(String labelMenuPrincipalFuncionalidad) {
        this.labelMenuPrincipalFuncionalidad = labelMenuPrincipalFuncionalidad;
    }
    public String getNombreFuncionalidad() {
        return nombreFuncionalidad;
    }
    public void setNombreFuncionalidad(String nombreFuncionalidad) {
        this.nombreFuncionalidad = nombreFuncionalidad;
    }
    public String getNombreIconoMenuPrincipalFuncionalidad() {
        return nombreIconoMenuPrincipalFuncionalidad;
    }
    public void setNombreIconoMenuPrincipalFuncionalidad(String nombreIconoMenuPrincipalFuncionalidad) {
        this.nombreIconoMenuPrincipalFuncionalidad = nombreIconoMenuPrincipalFuncionalidad;
    }*/
}
