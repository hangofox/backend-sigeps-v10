//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
//import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
//Anotacion de lombok que me crea automaticamente los get, set constructor.
public class RecuperacionContrasenaAccesoUsuarioDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idRecuperacionContrasenaAccesoUsuario;
    //private Long idUsuario;
    private String codigoActivacionContrasenaAccesoUsuario;
    private Date fechaHMSExpCodActivContrasenaAccesoUsuario;
    private String estadoUsoCodigoActivacionContrasenaAccesoUsuario;
    
    private UsuarioDTO usuarioDTO;
    
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}