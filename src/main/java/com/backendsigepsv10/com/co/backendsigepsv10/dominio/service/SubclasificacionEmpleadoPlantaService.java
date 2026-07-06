//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SubclasificacionEmpleadoPlantaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la interfaz del servicio.
*/
public interface SubclasificacionEmpleadoPlantaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta);
    List<SubclasificacionEmpleadoPlantaDTO> listarSubclasificacionesEmpleadosPlantas(Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta, String orderBy, String orderMode);
    Slice<SubclasificacionEmpleadoPlantaDTO> listarSubclasificacionesEmpleadosPlantasPag(Pageable pageable, Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta, String orderBy, String orderMode);
    RespuestaDTO crearSubclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO);
    RespuestaDTO consultarSubclasificacionEmpleadoPlantaporId(Long idSubclasificacionEmpleadoPlanta);
    RespuestaDTO consultarSubclasificacionEmpleadoPlantaporNombre(String nombreSubclasificacionEmpleadoPlanta);
    RespuestaDTO actualizarSubclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO);
    RespuestaDTO eliminarSubclasificacionEmpleadoPlanta(Long idSubclasificacionEmpleadoPlanta);
}
