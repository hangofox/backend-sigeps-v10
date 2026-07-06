//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ClasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la interfaz del servicio.
*/
public interface ClasificacionEmpleadoPlantaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta);
    List<ClasificacionEmpleadoPlantaDTO> listarClasificacionesEmpleadosPlantas(Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta, String orderBy, String orderMode);
    Slice<ClasificacionEmpleadoPlantaDTO> listarClasificacionesEmpleadosPlantasPag(Pageable pageable, Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta, String orderBy, String orderMode);
    RespuestaDTO crearClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO);
    RespuestaDTO consultarClasificacionEmpleadoPlantaporId(Long idClasificacionEmpleadoPlanta);
    RespuestaDTO consultarClasificacionEmpleadoPlantaporNombre(String nombreClasificacionEmpleadoPlanta);
    RespuestaDTO actualizarClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO);
    RespuestaDTO eliminarClasificacionEmpleadoPlanta(Long idClasificacionEmpleadoPlanta);
}
