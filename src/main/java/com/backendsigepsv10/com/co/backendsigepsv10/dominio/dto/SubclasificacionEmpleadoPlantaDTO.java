//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA EL DTO.
public class SubclasificacionEmpleadoPlantaDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DEL DTO:
    private Long idSubclasificacionEmpleadoPlanta;
    private String nombreSubclasificacionEmpleadoPlanta;
    private ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO;
}
