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
@Table(name = "TABLA_PROGRAMACION_TURNOS_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class ProgramacionTurnoEmpleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idProgramacionTurnoEmpleado;
    
    @Column(name = "FECHA_H_M_S_INICIACION_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIniciacionProgramacionTurnoEmpleado;
    
    @Column(name = "FECHA_H_M_S_FINALIZACION_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSFinalizacionProgramacionTurnoEmpleado;
    
    @Column(name = "ESTADO_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoProgramacionTurnoEmpleado;
    
    //@Column(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Empleado empleado;
    
    //@Column(name = "ID_PUESTO_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    //private Long idPuestoSedeEstablecimientoCliente;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_PUESTO_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    private PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente;
    
    //@Column(name = "ID_TURNO", columnDefinition="BIGINT NOT NULL")
    //private Long idTurno;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TURNO", columnDefinition="BIGINT NOT NULL")
    private Turno turno;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public String getEstadoProgramacionTurnoEmpleado() {
        return estadoProgramacionTurnoEmpleado;
    }
    public void setEstadoProgramacionTurnoEmpleado(String estadoProgramacionTurnoEmpleado) {
        this.estadoProgramacionTurnoEmpleado = estadoProgramacionTurnoEmpleado;
    }
    public Date getFechaHMSFinalizacionProgramacionTurnoEmpleado() {
        return fechaHMSFinalizacionProgramacionTurnoEmpleado;
    }
    public void setFechaHMSFinalizacionProgramacionTurnoEmpleado(Date fechaHMSFinalizacionProgramacionTurnoEmpleado) {
        this.fechaHMSFinalizacionProgramacionTurnoEmpleado = fechaHMSFinalizacionProgramacionTurnoEmpleado;
    }
    public Date getFechaHMSIniciacionProgramacionTurnoEmpleado() {
        return fechaHMSIniciacionProgramacionTurnoEmpleado;
    }
    public void setFechaHMSIniciacionProgramacionTurnoEmpleado(Date fechaHMSIniciacionProgramacionTurnoEmpleado) {
        this.fechaHMSIniciacionProgramacionTurnoEmpleado = fechaHMSIniciacionProgramacionTurnoEmpleado;
    }
    public Long getIdProgramacionTurnoEmpleado() {
        return idProgramacionTurnoEmpleado;
    }
    public void setIdProgramacionTurnoEmpleado(Long idProgramacionTurnoEmpleado) {
        this.idProgramacionTurnoEmpleado = idProgramacionTurnoEmpleado;
    }
    public PuestoSedeEstablecimientoCliente getPuestoSedeEstablecimientoCliente() {
        return puestoSedeEstablecimientoCliente;
    }
    public void setPuestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente) {
        this.puestoSedeEstablecimientoCliente = puestoSedeEstablecimientoCliente;
    }
    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }*/
}
