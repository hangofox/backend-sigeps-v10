//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TipoEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTipoEmpleado, String keyword);
    List<TipoEmpleadoDTO> listarTiposEmpleados(Long idTipoEmpleado, String keyword, String orderBy, String orderMode);
    Slice<TipoEmpleadoDTO> listarTiposEmpleadosPag(Pageable pageable, Long idTipoEmpleado, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearTipoEmpleado(TipoEmpleadoDTO tipoEmpleadoDTO);
    RespuestaDTO consultarTipoEmpleadoporId(Long idTipoEmpleado);
    RespuestaDTO consultarTipoEmpleadoporNombre(String nombreTipoEmpleado);
    RespuestaDTO actualizarTipoEmpleado(TipoEmpleadoDTO tipoEmpleadoDTO);
    RespuestaDTO eliminarTipoEmpleado(Long idTipoEmpleado);
}
