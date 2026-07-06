//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoPlantaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del servicio.
*/
public interface TipoEmpleadoPlantaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTipoEmpleadoPlanta, String keyword);
    List<TipoEmpleadoPlantaDTO> listarTiposEmpleadosPlanta(Long idTipoEmpleadoPlanta, String keyword, String orderBy, String orderMode);
    Slice<TipoEmpleadoPlantaDTO> listarTiposEmpleadosPlantaPag(Pageable pageable, Long idTipoEmpleadoPlanta, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearTipoEmpleadoPlanta(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO);
    RespuestaDTO consultarTipoEmpleadoPlantaporId(Long idTipoEmpleadoPlanta);
    RespuestaDTO consultarTipoEmpleadoPlantaporNombre(String nombreTipoEmpleadoPlanta);
    RespuestaDTO actualizarTipoEmpleadoPlanta(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO);
    RespuestaDTO eliminarTipoEmpleadoPlanta(Long idTipoEmpleadoPlanta);
}
