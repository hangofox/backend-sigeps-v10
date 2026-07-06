//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA EL DTO.
public class BajaEmpleadoDTO {

    //DECLARACIÓN DE LAS VARIABLES DEL DTO:
    private Long idBajaEmpleado;
    private Date fechaHMSBajaEmpleado;
    private String numeroContratoOActoAdmvoNombEmpleado;
    private EmpleadoDTO empleadoDTO;
}
