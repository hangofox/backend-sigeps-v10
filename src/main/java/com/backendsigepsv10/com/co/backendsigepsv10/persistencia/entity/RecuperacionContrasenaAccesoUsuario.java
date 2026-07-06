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
@Table(name = "TABLA_RECUPERACIONES_CONTRASENAS_ACCESOS_USUARIOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class RecuperacionContrasenaAccesoUsuario {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_RECUPERACION_CONTRASENA_ACCESO_USUARIO", columnDefinition="BIGINT NOT NULL")
    private Long idRecuperacionContrasenaAccesoUsuario;
    
    //@Column(name = "ID_USUARIO", columnDefinition="BIGINT NOT NULL")
    //private Long idUsuario;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_USUARIO", columnDefinition = "BIGINT NOT NULL")
    private Usuario usuario;
    
    @Column(name = "CODIGO_ACTIVACION_CONTRASENA_ACCESO_USUARIO", columnDefinition="VARCHAR(150) NULL")
    private String codigoActivacionContrasenaAccesoUsuario;
    
    @Column(name = "FECHA_H_M_S_EXP_COD_ACTIV_CONTRASENA_ACCESO_USUARIO", columnDefinition = "DATETIME NOT NULL")
    private Date fechaHMSExpCodActivContrasenaAccesoUsuario;
    
    @Column(name = "ESTADO_USO_CODIGO_ACTIVACION_CONTRASENA_ACCESO_USUARIO", columnDefinition = "VARCHAR(50) NOT NULL")
    private String estadoUsoCodigoActivacionContrasenaAccesoUsuario;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdRecuperacionContrasenaAccesoUsuario() {
        return idRecuperacionContrasenaAccesoUsuario;
    }
    public void setIdRecuperacionContrasenaAccesoUsuario(Long idRecuperacionContrasenaAccesoUsuario) {
        this.idRecuperacionContrasenaAccesoUsuario = idRecuperacionContrasenaAccesoUsuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getCodigoActivacionContrasenaAccesoUsuario() {
        return codigoActivacionContrasenaAccesoUsuario;
    }
    public void setCodigoActivacionContrasenaAccesoUsuario(String codigoActivacionContrasenaAccesoUsuario) {
        this.codigoActivacionContrasenaAccesoUsuario = codigoActivacionContrasenaAccesoUsuario;
    }
    public Date getFechaHMSExpCodActivContrasenaAccesoUsuario() {
        return fechaHMSExpCodActivContrasenaAccesoUsuario;
    }
    public void setFechaHMSExpCodActivContrasenaAccesoUsuario(Date fechaHMSExpCodActivContrasenaAccesoUsuario) {
        this.fechaHMSExpCodActivContrasenaAccesoUsuario = fechaHMSExpCodActivContrasenaAccesoUsuario;
    }
    public String getEstadoUsoCodigoActivacionContrasenaAccesoUsuario() {
        return estadoUsoCodigoActivacionContrasenaAccesoUsuario;
    }
    public void setEstadoUsoCodigoActivacionContrasenaAccesoUsuario(String estadoUsoCodigoActivacionContrasenaAccesoUsuario) {
        this.estadoUsoCodigoActivacionContrasenaAccesoUsuario = estadoUsoCodigoActivacionContrasenaAccesoUsuario;
    }*/
}
