//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_PRIVILEG_Y_RESTRICC_ACCESOS_USUARIOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class PrivilegyRestriccAccesoUsuario {

    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.

    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_PRIVILEGIO_Y_RESTRICCION_ACCESO_USUARIO", columnDefinition="BIGINT NOT NULL")
    private Long idPrivilegyRestriccAccesoUsuario;

    @Column(name = "NUMERO_REGISTRO_PRIVILEGIO_Y_RESTRICCION_ACCESO_USUARIO", columnDefinition="VARCHAR(150) NOT NULL")
    private String numRegPrivilegyRestriccAccesoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_USUARIO", columnDefinition="BIGINT NOT NULL")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_FUNCIONALIDAD", columnDefinition="BIGINT NOT NULL")
    private Funcionalidad funcionalidad;

    @ManyToOne(fetch = FetchType.LAZY)//MAPEA RELACIÓN DE FORMA PEREZOSA.
    @JoinColumn(name = "ID_ROL", columnDefinition="BIGINT NOT NULL")
    private Rol rol;

    @Column(name = "URL_ACCESO_USUARIO", columnDefinition="VARCHAR(400) NOT NULL")
    private String urlAccesoUsuario;

    @Column(name = "SI_O_NO_PRIVILEGIO_Y_RESTRICCION_ACCESO_USUARIO", columnDefinition="VARCHAR(50) NOT NULL")
    private String sioNoPrivilegyRestriccAccesoUsuario;

    @Column(name = "FECHA_H_M_S_INGRESO_PRIVILEGIO_Y_RESTRICCION_ACCESO_USUARIO", columnDefinition="DATETIME NOT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHMSIngresoPrivilegyRestriccAccesoUsuario;
}
