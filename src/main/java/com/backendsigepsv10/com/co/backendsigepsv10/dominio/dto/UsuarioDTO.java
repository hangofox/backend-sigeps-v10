//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.Date;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
//Anotacion de lombok que me crea automaticamente los get, set constructor.
public class UsuarioDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idUsuario;
    private String nicknameUsuario;
    private String passwordUsuario;
    private TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO;
    private String numeroDocumentoIdentificacionUsuario;
    private String lugarExpedicionDocumentoIdentificacionUsuario;
    private String nombresUsuario;
    private String primerApellidoUsuario;
    private String segundoApellidoUsuario;
    private String nombreArchivoFotoExtensionoFormatoUsuario;
    private Date fechaHMSNacimientoUsuario;
    private String sexoUsuario;
    private String direccionUsuario;
    private String telefonoUsuario;
    private String movilUsuario;
    private String correoElectronicoPersonalUsuario;
    private String correoElectronicoInstitucionalUsuario;
    private String paisOrigenUsuario;
    private String departamentooEstadoOrigenUsuario;
    private String ciudadOrigenUsuario;
    private TipoUsuarioDTO tipoUsuarioDTO;
    private Date fechaHMSIngresoUsuario;
    private Date fechaHMSModificacionUsuario;
    private String estadoUsuario;
    
    //DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DE RELACIONES DECLARADAS DEL DTO:
    public TipoDocumentoIdentificacionDTO getTipoDocumentoIdentificacionDTO() {
        return tipoDocumentoIdentificacionDTO;
    }
    public void setTipoDocumentoIdentificacionDTO(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) {
        this.tipoDocumentoIdentificacionDTO = tipoDocumentoIdentificacionDTO;
    }
    public TipoUsuarioDTO getTipoUsuarioDTO() {
        return tipoUsuarioDTO;
    }
    public void setTipoUsuarioDTO(TipoUsuarioDTO tipoUsuarioDTO) {
        this.tipoUsuarioDTO = tipoUsuarioDTO;
    }
}
