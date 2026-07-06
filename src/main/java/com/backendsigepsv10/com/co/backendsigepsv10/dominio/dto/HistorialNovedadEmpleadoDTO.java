//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA EL DTO.
public class HistorialNovedadEmpleadoDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DEL DTO:
    private Long idHistorialNovedadEmpleado;
    private Date fechaHMSHistorialNovedadEmpleado;
    private String descripcionHistorialNovedadEmpleado;
    private EmpleadoDTO empleadoDTO;
    private TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO;
}
