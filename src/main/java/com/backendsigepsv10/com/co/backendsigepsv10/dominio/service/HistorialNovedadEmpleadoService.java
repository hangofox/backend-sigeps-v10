//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface HistorialNovedadEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado);
    List<HistorialNovedadEmpleadoDTO> listarHistorialesNovedadesEmpleados(Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado, String orderBy, String orderMode);
    Slice<HistorialNovedadEmpleadoDTO> listarHistorialesNovedadesEmpleadosPag(Pageable pageable, Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado, String orderBy, String orderMode);
    RespuestaDTO crearHistorialNovedadEmpleado(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO);
    RespuestaDTO consultarHistorialNovedadEmpleadoporId(Long idHistorialNovedadEmpleado);
    RespuestaDTO consultarHistorialNovedadEmpleadoporNumeroDocumentoIdentificacionyDescripcion(String numeroDocumentoIdentificacionEmpleado, String descripcionHistorialNovedadEmpleado);
    RespuestaDTO actualizarHistorialNovedadEmpleado(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO);
    RespuestaDTO eliminarHistorialNovedadEmpleado(Long idHistorialNovedadEmpleado);
}
