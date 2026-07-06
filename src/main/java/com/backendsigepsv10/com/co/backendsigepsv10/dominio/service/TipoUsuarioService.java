//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoUsuarioDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TipoUsuarioService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTipoUsuario, String keyword);
    List<TipoUsuarioDTO> listarTiposUsuarios(Long idTipoUsuario, String keyword, String orderBy, String orderMode);
    Slice<TipoUsuarioDTO> listarTiposUsuariosPag(Pageable pageable, Long idTipoUsuario, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO);
    RespuestaDTO consultarTipoUsuarioporId(Long idTipoUsuario);
    RespuestaDTO consultarTipoUsuarioporNombre(String nombreTipoUsuario);
    RespuestaDTO actualizarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO);
    RespuestaDTO eliminarTipoUsuario(Long idTipoUsuario);
}
