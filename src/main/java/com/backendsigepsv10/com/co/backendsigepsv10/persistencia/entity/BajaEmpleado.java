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
@Table(name = "TABLA_BAJAS_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class BajaEmpleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_BAJA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idBajaEmpleado;
    
    @Column(name = "FECHA_H_M_S_BAJA_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSBajaEmpleado;
    
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
    public Date getFechaHMSBajaEmpleado() {
        return fechaHMSBajaEmpleado;
    }
    public void setFechaHMSBajaEmpleado(Date fechaHMSBajaEmpleado) {
        this.fechaHMSBajaEmpleado = fechaHMSBajaEmpleado;
    }
    public Long getIdBajaEmpleado() {
        return idBajaEmpleado;
    }
    public void setIdBajaEmpleado(Long idBajaEmpleado) {
        this.idBajaEmpleado = idBajaEmpleado;
    }
    public String getNumeroContratoOActoAdmvoNombEmpleado() {
        return numeroContratoOActoAdmvoNombEmpleado;
    }
    public void setNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado) {
        this.numeroContratoOActoAdmvoNombEmpleado = numeroContratoOActoAdmvoNombEmpleado;
    }*/
}
