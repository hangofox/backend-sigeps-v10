//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Esta es la declaración de la interface del servicio.
*/
public interface EmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado);
    List<EmpleadoDTO> listarEmpleados(Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado, String orderBy, String orderMode);
    Slice<EmpleadoDTO> listarEmpleadosPag(Pageable pageable, Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado, String orderBy, String orderMode);
    RespuestaDTO crearEmpleado(EmpleadoDTO empleadoDTO);
    RespuestaDTO consultarEmpleadoporId(Long idEmpleado);
    RespuestaDTO consultarEmpleadoporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionEmpleado);
    RespuestaDTO actualizarEmpleado(EmpleadoDTO empleadoDTO);
    RespuestaDTO eliminarEmpleado(Long idEmpleado);
}
