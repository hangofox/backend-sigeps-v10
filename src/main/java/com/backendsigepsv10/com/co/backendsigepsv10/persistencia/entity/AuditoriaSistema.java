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
@Table(name = "TABLA_AUDITORIAS_SISTEMA")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class AuditoriaSistema {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_AUDITORIA_SISTEMA", columnDefinition="BIGINT NOT NULL")
    private Long idAuditoriaSistema;
    
    @Column(name = "FECHA_H_M_S_AUDITORIA_SISTEMA", columnDefinition="DATETIME NOT NULL")
    private Date fechaHMSAuditoriaSistema;
    
    @Column(name = "ACCION_USUARIO_SISTEMA", columnDefinition="VARCHAR(150) NOT NULL")
    private String accionUsuarioSistema;
    
    @Column(name = "DESCRIPCION_AUDITORIA_SISTEMA", columnDefinition="TEXT NOT NULL")
    private String descripcionAuditoriaSistema;
    
    //@Column(name = "ID_USUARIO", columnDefinition="BIGINT NOT NULL")
    //private Long idUsuario;
    //@JsonIgnore//OMITE DATO O CAMPO.
    @OneToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_USUARIO", columnDefinition="BIGINT NOT NULL")
    private Usuario usuario;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public String getAccionUsuarioSistema() {
        return accionUsuarioSistema;
    }
    public void setAccionUsuarioSistema(String accionUsuarioSistema) {
        this.accionUsuarioSistema = accionUsuarioSistema;
    }
    public String getDescripcionAuditoriaSistema() {
        return descripcionAuditoriaSistema;
    }
    public void setDescripcionAuditoriaSistema(String descripcionAuditoriaSistema) {
        this.descripcionAuditoriaSistema = descripcionAuditoriaSistema;
    }
    public Date getFechaHMSAuditoriaSistema() {
        return fechaHMSAuditoriaSistema;
    }
    public void setFechaHMSAuditoriaSistema(Date fechaHMSAuditoriaSistema) {
        this.fechaHMSAuditoriaSistema = fechaHMSAuditoriaSistema;
    }
    public Long getIdAuditoriaSistema() {
        return idAuditoriaSistema;
    }
    public void setIdAuditoriaSistema(Long idAuditoriaSistema) {
        this.idAuditoriaSistema = idAuditoriaSistema;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }*/
}
