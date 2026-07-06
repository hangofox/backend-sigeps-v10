//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_HISTORIAL_MOVIMIENTOS_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class HistorialMovimientoEmpleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_HISTORIAL_MOVIMIENTO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idHistorialMovimientoEmpleado;
    
    @Column(name = "FECHA_H_M_S_HISTORIAL_MOVIMIENTO_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSHistorialMovimientoEmpleado;
    
    //@Column(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Empleado empleado;
    
    //@Column(name = "ID_TIPO_MOVIMIENTO", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoMovimiento;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_MOVIMIENTO", columnDefinition="BIGINT NOT NULL")
    private TipoMovimiento tipoMovimiento;
    
    //@Column(name = "ID_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idProgramacionTurnoEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_PROGRAMACION_TURNO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private ProgramacionTurnoEmpleado programacionTurnoEmpleado;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Date getFechaHMSHistorialMovimientoEmpleado() {
        return fechaHMSHistorialMovimientoEmpleado;
    }
    public void setFechaHMSHistorialMovimientoEmpleado(Date fechaHMSHistorialMovimientoEmpleado) {
        this.fechaHMSHistorialMovimientoEmpleado = fechaHMSHistorialMovimientoEmpleado;
    }
    public Long getIdHistorialMovimientoEmpleado() {
        return idHistorialMovimientoEmpleado;
    }
    public void setIdHistorialMovimientoEmpleado(Long idHistorialMovimientoEmpleado) {
        this.idHistorialMovimientoEmpleado = idHistorialMovimientoEmpleado;
    }
    public ProgramacionTurnoEmpleado getProgramacionTurnoEmpleado() {
        return programacionTurnoEmpleado;
    }
    public void setProgramacionTurnoEmpleado(ProgramacionTurnoEmpleado programacionTurnoEmpleado) {
        this.programacionTurnoEmpleado = programacionTurnoEmpleado;
    }
    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }*/
}
