//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA EL DTO.
public class SedeEstablecimientoClienteDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DEL DTO:
    private Long idSedeEstablecimientoCliente;
    private String nombreSedeEstablecimientoCliente;
    private String direccionSedeEstablecimientoCliente;
    private String telefonoSedeEstablecimientoCliente;
    private String movilSedeEstablecimientoCliente;
    private String correoElectronicoInstitucionalSedeEstablecimientoCliente;
    private String paisOrigenSedeEstablecimientoCliente;
    private String departamentooEstadoOrigenSedeEstablecimientoCliente;
    private String ciudadOrigenSedeEstablecimientoCliente;
    private Date fechaHMSIngresoSedeEstablecimientoCliente;
    private Date fechaHMSModificacionSedeEstablecimientoCliente;
    private String estadoSedeEstablecimientoCliente;
    private EstablecimientoClienteDTO establecimientoClienteDTO;
}
