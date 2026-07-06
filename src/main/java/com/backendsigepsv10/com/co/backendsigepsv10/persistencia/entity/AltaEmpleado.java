//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_ALTAS_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class AltaEmpleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_ALTA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idAltaEmpleado;
    
    @Column(name = "FECHA_H_M_S_ALTA_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSAltaEmpleado;
    
    @Column(name = "NUMERO_CONTRATO_O_ACTO_ADMVO_NOMB_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String numeroContratoOActoAdmvoNombEmpleado;
    
    //@Column(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Empleado empleado;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Date getFechaHMSAltaEmpleado() {
        return fechaHMSAltaEmpleado;
    }
    public void setFechaHMSAltaEmpleado(Date fechaHMSAltaEmpleado) {
        this.fechaHMSAltaEmpleado = fechaHMSAltaEmpleado;
    }
    public Long getIdAltaEmpleado() {
        return idAltaEmpleado;
    }
    public void setIdAltaEmpleado(Long idAltaEmpleado) {
        this.idAltaEmpleado = idAltaEmpleado;
    }
    public String getNumeroContratoOActoAdmvoNombEmpleado() {
        return numeroContratoOActoAdmvoNombEmpleado;
    }
    public void setNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado) {
        this.numeroContratoOActoAdmvoNombEmpleado = numeroContratoOActoAdmvoNombEmpleado;
    }*/
}
