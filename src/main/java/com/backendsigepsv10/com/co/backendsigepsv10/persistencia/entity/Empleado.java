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
@Table(name = "TABLA_EMPLEADOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class Empleado {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private Long idEmpleado;
    
    @Column(name = "NUMERO_DOCUMENTO_IDENTIFICACION_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String numeroDocumentoIdentificacionEmpleado;
    
    @Column(name = "NOMBRES_EMPLEADO", columnDefinition="VARCHAR(255) NOT NULL")
    private String nombresEmpleado;
    
    @Column(name = "PRIMER_APELLIDO_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String primerApellidoEmpleado;
    
    @Column(name = "SEGUNDO_APELLIDO_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String segundoApellidoEmpleado;
    
    @Column(name = "NOMBRE_ARCHIVO_FOTO_EXTENSION_O_FORMATO_EMPLEADO", columnDefinition="TEXT NOT NULL")
    private String nombreArchivoFotoExtensionOFormatoEmpleado;
    
    @Column(name = "DIRECCION_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String direccionEmpleado;
    
    @Column(name = "TELEFONO_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String telefonoEmpleado;
    
    @Column(name = "MOVIL_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String movilEmpleado;
    
    @Column(name = "CORREO_ELECTRONICO_PERSONAL_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String correoElectronicoPersonalEmpleado;
    
    @Column(name = "CORREO_ELECTRONICO_INSTITUCIONAL_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String correoElectronicoInstitucionalEmpleado;
    
    @Column(name = "PAIS_ORIGEN_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String paisOrigenEmpleado;
    
    @Column(name = "DEPARTAMENTO_O_ESTADO_ORIGEN_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String departamentooEstadoOrigenEmpleado;
    
    @Column(name = "CIUDAD_ORIGEN_EMPLEADO", columnDefinition="VARCHAR(150) NOT NULL")
    private String ciudadOrigenEmpleado;
    
    @Column(name = "FECHA_H_M_S_INGRESO_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoEmpleado;
    
    @Column(name = "FECHA_H_M_S_MODFICACION_EMPLEADO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSModificacionEmpleado;
    
    @Column(name = "ESTADO_EMPLEADO", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoEmpleado;
    
    //@Column(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoDocumentoIdentificacion;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    private TipoDocumentoIdentificacion tipoDocumentoIdentificacion;
    
    //@Column(name = "ID_TIPO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoEmpleado;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_EMPLEADO", columnDefinition="BIGINT NOT NULL")
    private TipoEmpleado tipoEmpleado;
    
    //@Column(name = "ID_TIPO_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private TipoEmpleadoPlanta tipoEmpleadoPlanta;
    
    //@Column(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idClasificacionEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_CLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta;
    
    //@Column(name = "ID_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    //private Long idSubclasificacionEmpleadoPlanta;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_SUBCLASIFICACION_EMPLEADO_PLANTA", columnDefinition="BIGINT NOT NULL")
    private SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getNumeroDocumentoIdentificacionEmpleado() {
        return numeroDocumentoIdentificacionEmpleado;
    }
    public void setNumeroDocumentoIdentificacionEmpleado(String numeroDocumentoIdentificacionEmpleado) {
        this.numeroDocumentoIdentificacionEmpleado = numeroDocumentoIdentificacionEmpleado;
    }
    public String getNombresEmpleado() {
        return nombresEmpleado;
    }
    public void setNombresEmpleado(String nombresEmpleado) {
        this.nombresEmpleado = nombresEmpleado;
    }
    public String getPrimerApellidoEmpleado() {
        return primerApellidoEmpleado;
    }
    public void setPrimerApellidoEmpleado(String primerApellidoEmpleado) {
        this.primerApellidoEmpleado = primerApellidoEmpleado;
    }
    public String getSegundoApellidoEmpleado() {
        return segundoApellidoEmpleado;
    }
    public void setSegundoApellidoEmpleado(String segundoApellidoEmpleado) {
        this.segundoApellidoEmpleado = segundoApellidoEmpleado;
    }
    public String getNombreArchivoFotoExtensionOFormatoEmpleado() {
        return nombreArchivoFotoExtensionOFormatoEmpleado;
    }
    public void setNombreArchivoFotoExtensionOFormatoEmpleado(String nombreArchivoFotoExtensionOFormatoEmpleado) {
        this.nombreArchivoFotoExtensionOFormatoEmpleado = nombreArchivoFotoExtensionOFormatoEmpleado;
    }
    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }
    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }
    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }
    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }
    public String getMovilEmpleado() {
        return movilEmpleado;
    }
    public void setMovilEmpleado(String movilEmpleado) {
        this.movilEmpleado = movilEmpleado;
    }
    public String getCorreoElectronicoPersonalEmpleado() {
        return correoElectronicoPersonalEmpleado;
    }
    public void setCorreoElectronicoPersonalEmpleado(String correoElectronicoPersonalEmpleado) {
        this.correoElectronicoPersonalEmpleado = correoElectronicoPersonalEmpleado;
    }
    public String getCorreoElectronicoInstitucionalEmpleado() {
        return correoElectronicoInstitucionalEmpleado;
    }
    public void setCorreoElectronicoInstitucionalEmpleado(String correoElectronicoInstitucionalEmpleado) {
        this.correoElectronicoInstitucionalEmpleado = correoElectronicoInstitucionalEmpleado;
    }
    public String getPaisOrigenEmpleado() {
        return paisOrigenEmpleado;
    }
    public void setPaisOrigenEmpleado(String paisOrigenEmpleado) {
        this.paisOrigenEmpleado = paisOrigenEmpleado;
    }
    public String getDepartamentooEstadoOrigenEmpleado() {
        return departamentooEstadoOrigenEmpleado;
    }
    public void setDepartamentooEstadoOrigenEmpleado(String departamentooEstadoOrigenEmpleado) {
        this.departamentooEstadoOrigenEmpleado = departamentooEstadoOrigenEmpleado;
    }
    public String getCiudadOrigenEmpleado() {
        return ciudadOrigenEmpleado;
    }
    public void setCiudadOrigenEmpleado(String ciudadOrigenEmpleado) {
        this.ciudadOrigenEmpleado = ciudadOrigenEmpleado;
    }
    public Date getFechaHMSIngresoEmpleado() {
        return fechaHMSIngresoEmpleado;
    }
    public void setFechaHMSIngresoEmpleado(Date fechaHMSIngresoEmpleado) {
        this.fechaHMSIngresoEmpleado = fechaHMSIngresoEmpleado;
    }
    public Date getFechaHMSModificacionEmpleado() {
        return fechaHMSModificacionEmpleado;
    }
    public void setFechaHMSModificacionEmpleado(Date fechaHMSModificacionEmpleado) {
        this.fechaHMSModificacionEmpleado = fechaHMSModificacionEmpleado;
    }
    public String getEstadoEmpleado() {
        return estadoEmpleado;
    }
    public void setEstadoEmpleado(String estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }
    public TipoDocumentoIdentificacion getTipoDocumentoIdentificacion() {
        return tipoDocumentoIdentificacion;
    }
    public void setTipoDocumentoIdentificacion(TipoDocumentoIdentificacion tipoDocumentoIdentificacion) {
        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
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
    }*/
}
