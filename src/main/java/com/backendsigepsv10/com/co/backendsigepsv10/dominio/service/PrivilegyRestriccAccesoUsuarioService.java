//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PrivilegyRestriccAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface PrivilegyRestriccAccesoUsuarioService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario);
    List<PrivilegyRestriccAccesoUsuarioDTO> listarPrivilegyRestriccAccesosUsuarios(Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario, String orderBy, String orderMode);
    Slice<PrivilegyRestriccAccesoUsuarioDTO> listarPrivilegyRestriccAccesosUsuariosPag(Pageable pageable, Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario, String orderBy, String orderMode);
    RespuestaDTO crearPrivilegyRestriccAccesoUsuario(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO);
    RespuestaDTO crearPrivilegyRestriccAccesosUsuarios(List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesoUsuarioDTOS);
    RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporId(Long idPrivilegyRestriccAccesoUsuario);
    RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioeIdFuncionalidadeIdRol(Long idUsuario, Long idFuncionalidad, Long idRol);
    RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioyNombreFuncionalidadyNombreRol(Long idUsuario, String nombreFuncionalidad, String nombreRol);
    RespuestaDTO actualizarPrivilegyRestriccAccesoUsuario(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO);
    RespuestaDTO eliminarPrivilegyRestriccAccesoUsuario(Long idPrivilegyRestriccAccesoUsuario);
    RespuestaDTO vaciarPrivilegyRestriccAccesosUsuariosporIdUsuario(Long idUsuario);
}
