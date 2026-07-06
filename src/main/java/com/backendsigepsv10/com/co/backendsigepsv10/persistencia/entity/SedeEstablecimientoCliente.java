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
@Table(name = "TABLA_SEDES_ESTABLECIMIENTOS_CLIENTES")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class SedeEstablecimientoCliente {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    private Long idSedeEstablecimientoCliente;
    
    @Column(name = "NOMBRE_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String nombreSedeEstablecimientoCliente;

    @Column(name = "DIRECCION_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String direccionSedeEstablecimientoCliente;

    @Column(name = "TELEFONO_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String telefonoSedeEstablecimientoCliente;

    @Column(name = "MOVIL_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String movilSedeEstablecimientoCliente;

    @Column(name = "CORREO_ELECTRONICO_INSTITUCIONAL_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String correoElectronicoInstitucionalSedeEstablecimientoCliente;

    @Column(name = "PAIS_ORIGEN_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String paisOrigenSedeEstablecimientoCliente;

    @Column(name = "DEPARTAMENTO_O_ESTADO_ORIGEN_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String departamentooEstadoOrigenSedeEstablecimientoCliente;

    @Column(name = "CIUDAD_ORIGEN_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(150) NOT NULL")
    private String ciudadOrigenSedeEstablecimientoCliente;
    
    @Column(name = "FECHA_H_M_S_INGRESO_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoSedeEstablecimientoCliente;
    
    @Column(name = "FECHA_H_M_S_MODIFICACION_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSModificacionSedeEstablecimientoCliente;
    
    @Column(name = "ESTADO_SEDE_ESTABLECIMIENTO_CLIENTE", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoSedeEstablecimientoCliente;
    
    //@Column(name = "ID_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    //private Long idEstablecimientoCliente;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_ESTABLECIMIENTO_CLIENTE", columnDefinition="BIGINT NOT NULL")
    private EstablecimientoCliente establecimientoCliente;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public String getCiudadOrigenSedeEstablecimientoCliente() {
        return ciudadOrigenSedeEstablecimientoCliente;
    }
    public void setCiudadOrigenSedeEstablecimientoCliente(String ciudadOrigenSedeEstablecimientoCliente) {
        this.ciudadOrigenSedeEstablecimientoCliente = ciudadOrigenSedeEstablecimientoCliente;
    }
    public String getCorreoElectronicoInstitucionalSedeEstablecimientoCliente() {
        return correoElectronicoInstitucionalSedeEstablecimientoCliente;
    }
    public void setCorreoElectronicoInstitucionalSedeEstablecimientoCliente(String correoElectronicoInstitucionalSedeEstablecimientoCliente) {
        this.correoElectronicoInstitucionalSedeEstablecimientoCliente = correoElectronicoInstitucionalSedeEstablecimientoCliente;
    }
    public String getDepartamentooEstadoOrigenSedeEstablecimientoCliente() {
        return departamentooEstadoOrigenSedeEstablecimientoCliente;
    }
    public void setDepartamentooEstadoOrigenSedeEstablecimientoCliente(String departamentooEstadoOrigenSedeEstablecimientoCliente) {
        this.departamentooEstadoOrigenSedeEstablecimientoCliente = departamentooEstadoOrigenSedeEstablecimientoCliente;
    }
    public String getDireccionSedeEstablecimientoCliente() {
        return direccionSedeEstablecimientoCliente;
    }
    public void setDireccionSedeEstablecimientoCliente(String direccionSedeEstablecimientoCliente) {
        this.direccionSedeEstablecimientoCliente = direccionSedeEstablecimientoCliente;
    }
    public EstablecimientoCliente getEstablecimientoCliente() {
        return establecimientoCliente;
    }
    public void setEstablecimientoCliente(EstablecimientoCliente establecimientoCliente) {
        this.establecimientoCliente = establecimientoCliente;
    }
    public String getEstadoSedeEstablecimientoCliente() {
        return estadoSedeEstablecimientoCliente;
    }
    public void setEstadoSedeEstablecimientoCliente(String estadoSedeEstablecimientoCliente) {
        this.estadoSedeEstablecimientoCliente = estadoSedeEstablecimientoCliente;
    }
    public Date getFechaHMSIngresoSedeEstablecimientoCliente() {
        return fechaHMSIngresoSedeEstablecimientoCliente;
    }
    public void setFechaHMSIngresoSedeEstablecimientoCliente(Date fechaHMSIngresoSedeEstablecimientoCliente) {
        this.fechaHMSIngresoSedeEstablecimientoCliente = fechaHMSIngresoSedeEstablecimientoCliente;
    }
    public Date getFechaHMSModificacionSedeEstablecimientoCliente() {
        return fechaHMSModificacionSedeEstablecimientoCliente;
    }
    public void setFechaHMSModificacionSedeEstablecimientoCliente(Date fechaHMSModificacionSedeEstablecimientoCliente) {
        this.fechaHMSModificacionSedeEstablecimientoCliente = fechaHMSModificacionSedeEstablecimientoCliente;
    }
    public Long getIdSedeEstablecimientoCliente() {
        return idSedeEstablecimientoCliente;
    }
    public void setIdSedeEstablecimientoCliente(Long idSedeEstablecimientoCliente) {
        this.idSedeEstablecimientoCliente = idSedeEstablecimientoCliente;
    }
    public String getMovilSedeEstablecimientoCliente() {
        return movilSedeEstablecimientoCliente;
    }
    public void setMovilSedeEstablecimientoCliente(String movilSedeEstablecimientoCliente) {
        this.movilSedeEstablecimientoCliente = movilSedeEstablecimientoCliente;
    }
    public String getNombreSedeEstablecimientoCliente() {
        return nombreSedeEstablecimientoCliente;
    }
    public void setNombreSedeEstablecimientoCliente(String nombreSedeEstablecimientoCliente) {
        this.nombreSedeEstablecimientoCliente = nombreSedeEstablecimientoCliente;
    }
    public String getPaisOrigenSedeEstablecimientoCliente() {
        return paisOrigenSedeEstablecimientoCliente;
    }
    public void setPaisOrigenSedeEstablecimientoCliente(String paisOrigenSedeEstablecimientoCliente) {
        this.paisOrigenSedeEstablecimientoCliente = paisOrigenSedeEstablecimientoCliente;
    }
    public String getTelefonoSedeEstablecimientoCliente() {
        return telefonoSedeEstablecimientoCliente;
    }
    public void setTelefonoSedeEstablecimientoCliente(String telefonoSedeEstablecimientoCliente) {
        this.telefonoSedeEstablecimientoCliente = telefonoSedeEstablecimientoCliente;
    }*/
}
