//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoNovedadEmpleadoDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TipoNovedadEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTipoNovedadEmpleado, String keyword);
    List<TipoNovedadEmpleadoDTO> listarTiposNovedadesEmpleados(Long idTipoNovedadEmpleado, String keyword, String orderBy, String orderMode);
    Slice<TipoNovedadEmpleadoDTO> listarTiposNovedadesEmpleadosPag(Pageable pageable, Long idTipoNovedadEmpleado, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearTipoNovedadEmpleado(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO);
    RespuestaDTO consultarTipoNovedadEmpleadoporId(Long idTipoNovedadEmpleado);
    RespuestaDTO consultarTipoNovedadEmpleadoporNombre(String nombreTipoNovedadEmpleado);
    RespuestaDTO actualizarTipoNovedadEmpleado(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO);
    RespuestaDTO eliminarTipoNovedadEmpleado(Long idTipoNovedadEmpleado);
}
