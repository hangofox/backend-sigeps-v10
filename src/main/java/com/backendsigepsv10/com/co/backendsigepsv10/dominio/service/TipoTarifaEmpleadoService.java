//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoTarifaEmpleadoDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/08/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TipoTarifaEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado);
    List<TipoTarifaEmpleadoDTO> listarTiposTarifasEmpleados(Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado, String orderBy, String orderMode);
    Slice<TipoTarifaEmpleadoDTO> listarTiposTarifasEmpleadosPag(Pageable pageable, Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado, String orderBy, String orderMode);
    RespuestaDTO crearTipoTarifaEmpleado(TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO);
    RespuestaDTO consultarTipoTarifaEmpleadoporId(Long idTipoTarifaEmpleado);
    RespuestaDTO consultarTipoTarifaEmpleadoporNombre(String nombreTipoTarifaEmpleado);
    RespuestaDTO actualizarTipoTarifaEmpleado(TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO);
    RespuestaDTO eliminarTipoTarifaEmpleado(Long idTipoTarifaEmpleado);
}
