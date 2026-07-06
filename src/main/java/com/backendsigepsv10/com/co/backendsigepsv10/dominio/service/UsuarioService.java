//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.UsuarioDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface UsuarioService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idUsuario, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionUsuario, String nombresUsuario, String primerApellidoUsuario, String nombreTipoUsuario, String estadoUsuario);
    List<UsuarioDTO> listarUsuarios(Long idUsuario, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionUsuario, String nombresUsuario, String primerApellidoUsuario, String nombreTipoUsuario, String estadoUsuario, String orderBy, String orderMode);
    Slice<UsuarioDTO> listarUsuariosPag(Pageable pageable, Long idUsuario, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionUsuario, String nombresUsuario, String primerApellidoUsuario, String nombreTipoUsuario, String estadoUsuario, String orderBy, String orderMode);
    RespuestaDTO crearUsuario(UsuarioDTO usuarioDTO);
    RespuestaDTO consultarUsuarioporId(Long idUsuario);
    RespuestaDTO consultarUsuarioporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionUsuario);
    RespuestaDTO consultarUsuarioRecuperacionContrasenaAccesoporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionUsuario);
    RespuestaDTO consultarUsuarioporNicknameYPassword(String nicknameUsuario, String passwordUsuario);
    RespuestaDTO actualizarUsuario(UsuarioDTO usuarioDTO);
    RespuestaDTO actualizarPasswordUsuario(Long idUsuario, String passwordUsuario);
    RespuestaDTO eliminarUsuario(Long idUsuario);
}
