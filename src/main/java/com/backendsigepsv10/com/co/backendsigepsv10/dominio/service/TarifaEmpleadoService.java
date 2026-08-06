//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TarifaEmpleadoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 05/08/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TarifaEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado);
    List<TarifaEmpleadoDTO> listarTarifasEmpleados(Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado, String orderBy, String orderMode);
    Slice<TarifaEmpleadoDTO> listarTarifasEmpleadosPag(Pageable pageable, Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado, String orderBy, String orderMode);
    RespuestaDTO crearTarifaEmpleado(TarifaEmpleadoDTO tarifaEmpleadoDTO);
    RespuestaDTO consultarTarifaEmpleadoporId(Long idTarifaEmpleado);
    RespuestaDTO consultarTarifaEmpleadoporIdTipoEmpleadoIdTipoEmpleadoPlantaIdClasificacionEmpleadoPlantaIdSubclasificacionEmpleadoPlantaIdTipoTarifaEmpleadoyAnio(Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado);
    RespuestaDTO actualizarTarifaEmpleado(TarifaEmpleadoDTO tarifaEmpleadoDTO);
    RespuestaDTO eliminarTarifaEmpleado(Long idTarifaEmpleado);
}
