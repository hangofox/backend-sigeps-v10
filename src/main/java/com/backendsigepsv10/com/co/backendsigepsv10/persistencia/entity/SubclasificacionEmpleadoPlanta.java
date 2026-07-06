//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_SUBCLASIFICACIONES_EMPLEADOS_PLANTAS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class SubclasificacionEmpleadoPlanta {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private Long idSubclasificacionEmpleadoPlanta;
    
    @Column(name = "NOMBRE_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="VARCHAR(255) NOT NULL")
    private String nombreSubclasificacionEmpleadoPlanta;
    
    //@Column(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idClasificacionEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public ClasificacionEmpleadoPlanta getClasificacionEmpleadoPlanta() {
        return clasificacionEmpleadoPlanta;
    }
    public void setClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta) {
        this.clasificacionEmpleadoPlanta = clasificacionEmpleadoPlanta;
    }
    public Long getIdSubclasificacionEmpleadoPlanta() {
        return idSubclasificacionEmpleadoPlanta;
    }
    public void setIdSubclasificacionEmpleadoPlanta(Long idSubclasificacionEmpleadoPlanta) {
        this.idSubclasificacionEmpleadoPlanta = idSubclasificacionEmpleadoPlanta;
    }
    public String getNombreSubclasificacionEmpleadoPlanta() {
        return nombreSubclasificacionEmpleadoPlanta;
    }
    public void setNombreSubclasificacionEmpleadoPlanta(String nombreSubclasificacionEmpleadoPlanta) {
        this.nombreSubclasificacionEmpleadoPlanta = nombreSubclasificacionEmpleadoPlanta;
    }*/
}
