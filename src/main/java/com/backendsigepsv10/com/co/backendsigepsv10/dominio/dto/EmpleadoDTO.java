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
public class EmpleadoDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DEL DTO:
    private Long idEmpleado;
    private String numeroDocumentoIdentificacionEmpleado;
    private String nombresEmpleado;
    private String primerApellidoEmpleado;
    private String segundoApellidoEmpleado;
    private String nombreArchivoFotoExtensionOFormatoEmpleado;
    private String direccionEmpleado;
    private String telefonoEmpleado;
    private String movilEmpleado;
    private String correoElectronicoPersonalEmpleado;
    private String correoElectronicoInstitucionalEmpleado;
    private String paisOrigenEmpleado;
    private String departamentooEstadoOrigenEmpleado;
    private String ciudadOrigenEmpleado;
    private Date fechaHMSIngresoEmpleado;
    private Date fechaHMSModificacionEmpleado;
    private String estadoEmpleado;
    private TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO;
    private TipoEmpleadoDTO tipoEmpleadoDTO;
    private TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO;
    private ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO;
    private SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO;
}
