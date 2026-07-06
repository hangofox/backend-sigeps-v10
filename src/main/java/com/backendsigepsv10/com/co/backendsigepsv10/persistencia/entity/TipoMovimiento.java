//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la entidad.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LA ENTIDAD.
@Entity//DECLARACIÓN DE LA ENTIDAD QUE ES LA MISMA TABLA DE LA BASE DE DATOS.
@Table(name = "TABLA_TIPOS_MOVIMIENTOS")//REFERENCIA A LA TABLA DE LA BASE DE DATOS.
public class TipoMovimiento {
    
    //CAMPOS DE LA TABLA DE LA BASE DE DATOS:
    @Id//DECLARACIÓN DEL ID PRINCIPAL DE LA TABLA DE BASE DE DATOS.
    
    //AQUI ES DONDE SE CREA EL ENLACE ENTRE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS Y LAS VARIABLES DECLARADAS
    //QUE RECIBIRAN O ENVIARAS LOS DATOS A LA BASE DE DATOS PARA LA ENTIDAD.
    //NOTA: EN LOS NAME SE PONEN EL NOMBRE DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS EXACTAMENTE IGUAL A COMO SE CREARÓN.
    @Column(name = "ID_TIPO_MOVIMIENTO", columnDefinition="BIGINT NOT NULL")
    private Long idTipoMovimiento;
    
    @Column(name = "NOMBRE_TIPO_MOVIMIENTO", columnDefinition="VARCHAR(150) NOT NULL")
    private String nombreTipoMovimiento;
    
    /*//DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DECLARADAS DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS DE LA ENTIDAD:
    public Long getIdTipoMovimiento() {
        return idTipoMovimiento;
    }
    public void setIdTipoMovimiento(Long idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }
    public String getNombreTipoMovimiento() {
        return nombreTipoMovimiento;
    }
    public void setNombreTipoMovimiento(String nombreTipoMovimiento) {
        this.nombreTipoMovimiento = nombreTipoMovimiento;
    }*/
}
