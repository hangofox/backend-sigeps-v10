//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_USUARIOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class Usuario {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_USUARIO", columnDefinition="BIGINT NOT NULL")
    private Long idUsuario;
    
    @Column(name = "NICKNAME_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String nicknameUsuario;
    
    @Column(name = "PASSWORD_USUARIO", columnDefinition="VARCHAR(4000) NOT NULL")
    private String passwordUsuario;
    
    //@Column(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoDocumentoIdentificacion;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_DOCUMENTO_IDENTIFICACION", columnDefinition="BIGINT NOT NULL")
    private TipoDocumentoIdentificacion tipoDocumentoIdentificacion;
    
    @Column(name = "NUMERO_DOCUMENTO_IDENTIFICACION_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String numeroDocumentoIdentificacionUsuario;
    
    @Column(name = "LUGAR_EXPEDICION_DOCUMENTO_IDENTIFICACION_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String lugarExpedicionDocumentoIdentificacionUsuario;
    
    @Column(name = "NOMBRES_USUARIO", columnDefinition="VARCHAR(255) NOT NULL")
    private String nombresUsuario;
    
    @Column(name = "PRIMER_APELLIDO_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String primerApellidoUsuario;
    
    @Column(name = "SEGUNDO_APELLIDO_USUARIO", columnDefinition="VARCHAR(150) NULL")
    private String segundoApellidoUsuario;
    
    @Column(name = "NOMBRE_ARCHIVO_FOTO_EXTENSION_O_FORMATO_USUARIO", columnDefinition="LONGTEXT NULL")
    private String nombreArchivoFotoExtensionoFormatoUsuario;
    
    @Column(name = "FECHA_H_M_S_NACIMIENTO_USUARIO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSNacimientoUsuario;
    
    @Column(name = "SEXO_USUARIO", columnDefinition="VARCHAR(50) NOT NULL")
    private String sexoUsuario;
    
    @Column(name = "DIRECCION_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String direccionUsuario;
    
    @Column(name = "TELEFONO_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String telefonoUsuario;
    
    @Column(name = "MOVIL_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String movilUsuario;
    
    @Column(name = "CORREO_ELECTRONICO_PERSONAL_USUARIO", columnDefinition="VARCHAR(150) NULL")
    private String correoElectronicoPersonalUsuario;
    
    @Column(name = "CORREO_ELECTRONICO_INSTITUCIONAL_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String correoElectronicoInstitucionalUsuario;
    
    @Column(name = "PAIS_ORIGEN_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String paisOrigenUsuario;
    
    @Column(name = "DEPARTAMENTO_O_ESTADO_ORIGEN_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String departamentooEstadoOrigenUsuario;
    
    @Column(name = "CIUDAD_ORIGEN_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String ciudadOrigenUsuario;
    
    //@Column(name = "ID_TIPO_USUARIO", columnDefinition="BIGINT NOT NULL")
    //private Long idTipoUsuario;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_TIPO_USUARIO", columnDefinition="BIGINT NOT NULL")
    private TipoUsuario tipoUsuario;
    
    @Column(name = "FECHA_H_M_S_INGRESO_USUARIO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoUsuario;
    
    @Column(name = "FECHA_H_M_S_MODIFICACION_USUARIO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSModificacionUsuario;
    
    @Column(name = "ESTADO_USUARIO", columnDefinition="VARCHAR(50) NOT NULL")
    private String estadoUsuario;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNicknameUsuario() {
        return nicknameUsuario;
    }
    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }
    public String getPasswordUsuario() {
        return passwordUsuario;
    }
    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
    public TipoDocumentoIdentificacion getTipoDocumentoIdentificacion() {
        return tipoDocumentoIdentificacion;
    }
    public void setTipoDocumentoIdentificacion(TipoDocumentoIdentificacion tipoDocumentoIdentificacion) {
        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
    }
    public String getNumeroDocumentoIdentificacionUsuario() {
        return numeroDocumentoIdentificacionUsuario;
    }
    public void setNumeroDocumentoIdentificacionUsuario(String numeroDocumentoIdentificacionUsuario) {
        this.numeroDocumentoIdentificacionUsuario = numeroDocumentoIdentificacionUsuario;
    }
    public String getLugarExpedicionDocumentoIdentificacionUsuario() {
        return lugarExpedicionDocumentoIdentificacionUsuario;
    }
    public void setLugarExpedicionDocumentoIdentificacionUsuario(String lugarExpedicionDocumentoIdentificacionUsuario) {
        this.lugarExpedicionDocumentoIdentificacionUsuario = lugarExpedicionDocumentoIdentificacionUsuario;
    }
    public String getNombresUsuario() {
        return nombresUsuario;
    }
    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }
    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }
    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }
    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }
    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }
    public String getNombreArchivoFotoExtensionoFormatoUsuario() {
        return nombreArchivoFotoExtensionoFormatoUsuario;
    }
    public void setNombreArchivoFotoExtensionoFormatoUsuario(String nombreArchivoFotoExtensionoFormatoUsuario) {
        this.nombreArchivoFotoExtensionoFormatoUsuario = nombreArchivoFotoExtensionoFormatoUsuario;
    }
    public Date getFechaHMSNacimientoUsuario() {
        return fechaHMSNacimientoUsuario;
    }
    public void setFechaHMSNacimientoUsuario(Date fechaHMSNacimientoUsuario) {
        this.fechaHMSNacimientoUsuario = fechaHMSNacimientoUsuario;
    }
    public String getSexoUsuario() {
        return sexoUsuario;
    }
    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }
    public String getDireccionUsuario() {
        return direccionUsuario;
    }
    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }
    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }
    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }
    public String getMovilUsuario() {
        return movilUsuario;
    }
    public void setMovilUsuario(String movilUsuario) {
        this.movilUsuario = movilUsuario;
    }
    public String getCorreoElectronicoPersonalUsuario() {
        return correoElectronicoPersonalUsuario;
    }
    public void setCorreoElectronicoPersonalUsuario(String correoElectronicoPersonalUsuario) {
        this.correoElectronicoPersonalUsuario = correoElectronicoPersonalUsuario;
    }
    public String getCorreoElectronicoInstitucionalUsuario() {
        return correoElectronicoInstitucionalUsuario;
    }
    public void setCorreoElectronicoInstitucionalUsuario(String correoElectronicoInstitucionalUsuario) {
        this.correoElectronicoInstitucionalUsuario = correoElectronicoInstitucionalUsuario;
    }
    public String getPaisOrigenUsuario() {
        return paisOrigenUsuario;
    }
    public void setPaisOrigenUsuario(String paisOrigenUsuario) {
        this.paisOrigenUsuario = paisOrigenUsuario;
    }
    public String getDepartamentooEstadoOrigenUsuario() {
        return departamentooEstadoOrigenUsuario;
    }
    public void setDepartamentooEstadoOrigenUsuario(String departamentooEstadoOrigenUsuario) {
        this.departamentooEstadoOrigenUsuario = departamentooEstadoOrigenUsuario;
    }
    public String getCiudadOrigenUsuario() {
        return ciudadOrigenUsuario;
    }
    public void setCiudadOrigenUsuario(String ciudadOrigenUsuario) {
        this.ciudadOrigenUsuario = ciudadOrigenUsuario;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public Date getFechaHMSIngresoUsuario() {
        return fechaHMSIngresoUsuario;
    }
    public void setFechaHMSIngresoUsuario(Date fechaHMSIngresoUsuario) {
        this.fechaHMSIngresoUsuario = fechaHMSIngresoUsuario;
    }
    public Date getFechaHMSModificacionUsuario() {
        return fechaHMSModificacionUsuario;
    }
    public void setFechaHMSModificacionUsuario(Date fechaHMSModificacionUsuario) {
        this.fechaHMSModificacionUsuario = fechaHMSModificacionUsuario;
    }
    public String getEstadoUsuario() {
        return estadoUsuario;
    }
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }*/
}
