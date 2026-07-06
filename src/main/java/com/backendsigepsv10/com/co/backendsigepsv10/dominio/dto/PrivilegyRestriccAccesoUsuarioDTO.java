//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
public class PrivilegyRestriccAccesoUsuarioDTO {

    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idPrivilegyRestriccAccesoUsuario;
    private String numRegPrivilegyRestriccAccesoUsuario;
    private String urlAccesoUsuario;
    private String sioNoPrivilegyRestriccAccesoUsuario;
    private Date fechaHMSIngresoPrivilegyRestriccAccesoUsuario;
    private UsuarioDTO usuarioDTO;
    private FuncionalidadDTO funcionalidadDTO;
    private RolDTO rolDTO;
}
