//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialMovimientoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.Date;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface HistorialMovimientoEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento);
    List<HistorialMovimientoEmpleadoDTO> listarHistorialesMovimientosEmpleados(Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento, String orderBy, String orderMode);
    Slice<HistorialMovimientoEmpleadoDTO> listarHistorialesMovimientosEmpleadosPag(Pageable pageable, Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento, String orderBy, String orderMode);
    RespuestaDTO crearHistorialMovimientoEmpleado(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO);
    RespuestaDTO consultarHistorialMovimientoEmpleadoporId(Long idHistorialMovimientoEmpleado);
    RespuestaDTO consultarHistorialMovimientoEmpleadoporIdEmpleadoyFechaHMS(Long idEmpleado, Date fechaHMS);
    RespuestaDTO actualizarHistorialMovimientoEmpleado(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO);
    RespuestaDTO eliminarHistorialMovimientoEmpleado(Long idHistorialMovimientoEmpleado);
}
