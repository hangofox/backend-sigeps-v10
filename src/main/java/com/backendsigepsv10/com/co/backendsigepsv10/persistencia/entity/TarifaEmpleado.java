//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 05/08/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_TARIFAS_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class TarifaEmpleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_TARIFA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idTarifaEmpleado;
    
    @Column(name = "ANIO_TARIFA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long anioTarifaEmpleado;
    
    @Column(name = "VALOR_HORA_TARIFA_EMPLEADO", precision=12, scale=2, nullable=false)
    private BigDecimal valorHoraTarifaEmpleado;
    
    @Column(name = "FECHA_H_M_S_INGRESO_TARIFA_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoTarifaEmpleado;
    
    @Column(name = "FECHA_H_M_S_MODIFICACION_TARIFA_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSModificacionTarifaEmpleado;
    
    @Column(name = "ESTADO_TARIFA_EMPLEADO", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoTarifaEmpleado;
    
    //@Column(name = "ID_TIPO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private TipoEmpleado tipoEmpleado;
    
    //@Column(name = "ID_TIPO_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private TipoEmpleadoPlanta tipoEmpleadoPlanta;
    
    //@Column(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idClasificacionEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta;
    
    //@Column(name = "ID_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idSubclasificacionEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta;
    
    //@Column(name = "ID_TIPO_TARIFA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoTarifaEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_TARIFA_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private TipoTarifaEmpleado tipoTarifaEmpleado;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdTarifaEmpleado() {
        return idTarifaEmpleado;
    }
    public void setIdTarifaEmpleado(Long idTarifaEmpleado) {
        this.idTarifaEmpleado = idTarifaEmpleado;
    }
    public Long getAnioTarifaEmpleado() {
        return anioTarifaEmpleado;
    }
    public void setAnioTarifaEmpleado(Long anioTarifaEmpleado) {
        this.anioTarifaEmpleado = anioTarifaEmpleado;
    }
    public BigDecimal getValorHoraTarifaEmpleado() {
        return valorHoraTarifaEmpleado;
    }
    public void setValorHoraTarifaEmpleado(BigDecimal valorHoraTarifaEmpleado) {
        this.valorHoraTarifaEmpleado = valorHoraTarifaEmpleado;
    }
    public Date getFechaHMSIngresoTarifaEmpleado() {
        return fechaHMSIngresoTarifaEmpleado;
    }
    public void setFechaHMSIngresoTarifaEmpleado(Date fechaHMSIngresoTarifaEmpleado) {
        this.fechaHMSIngresoTarifaEmpleado = fechaHMSIngresoTarifaEmpleado;
    }
    public Date getFechaHMSModificacionTarifaEmpleado() {
        return fechaHMSModificacionTarifaEmpleado;
    }
    public void setFechaHMSModificacionTarifaEmpleado(Date fechaHMSModificacionTarifaEmpleado) {
        this.fechaHMSModificacionTarifaEmpleado = fechaHMSModificacionTarifaEmpleado;
    }
    public String getEstadoTarifaEmpleado() {
        return estadoTarifaEmpleado;
    }
    public void setEstadoTarifaEmpleado(String estadoTarifaEmpleado) {
        this.estadoTarifaEmpleado = estadoTarifaEmpleado;
    }
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }
    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    public TipoEmpleadoPlanta getTipoEmpleadoPlanta() {
        return tipoEmpleadoPlanta;
    }
    public void setTipoEmpleadoPlanta(TipoEmpleadoPlanta tipoEmpleadoPlanta) {
        this.tipoEmpleadoPlanta = tipoEmpleadoPlanta;
    }
    public ClasificacionEmpleadoPlanta getClasificacionEmpleadoPlanta() {
        return clasificacionEmpleadoPlanta;
    }
    public void setClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta) {
        this.clasificacionEmpleadoPlanta = clasificacionEmpleadoPlanta;
    }
    public SubclasificacionEmpleadoPlanta getSubclasificacionEmpleadoPlanta() {
        return subclasificacionEmpleadoPlanta;
    }
    public void setSubclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta) {
        this.subclasificacionEmpleadoPlanta = subclasificacionEmpleadoPlanta;
    }
    public TipoTarifaEmpleado getTipoTarifaEmpleado() {
        return tipoTarifaEmpleado;
    }
    public void setTipoTarifaEmpleado(TipoTarifaEmpleado tipoTarifaEmpleado) {
        this.tipoTarifaEmpleado = tipoTarifaEmpleado;
    }*/
}
