//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RolDTO;
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
public interface RolService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idRol, String keyword, Long idFuncionalidad);
    List<RolDTO> listarRoles(Long idRol, String keyword, Long idFuncionalidad, String orderBy, String orderMode);
    Slice<RolDTO> listarRolesPag(Pageable pageable, Long idRol, String keyword, Long idFuncionalidad, String orderBy, String orderMode);
    RespuestaDTO crearRol(RolDTO rolDTO);
    RespuestaDTO consultarRolporId(Long idRol);
    RespuestaDTO consultarRolporIdeIdFuncionalidad(Long idFuncionalidad, Long idRol);
    RespuestaDTO consultarRolporNombreyNombreFuncionalidad(String nombreFuncionalidad, String nombreRol);
    RespuestaDTO actualizarRol(RolDTO rolDTO);
    RespuestaDTO eliminarRol(Long idRol);
}
