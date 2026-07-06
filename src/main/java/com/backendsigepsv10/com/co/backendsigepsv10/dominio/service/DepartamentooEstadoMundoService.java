//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.DepartamentooEstadoMundoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface DepartamentooEstadoMundoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    //CONTADOR DE REGISTROS FILTRADOS.
    Long contarTotalRegistros(Long idPaisMundo, String nombrePaisMundo, String keyword);
    //LISTADO DE REGISTROS FILTRADOS.
    List<DepartamentooEstadoMundoDTO> listarDepartamentosoEstadosMundo(Long idPaisMundo, String nombrePaisMundo, String keyword, String orderBy, String orderMode);
    Slice<DepartamentooEstadoMundoDTO> listarDepartamentosoEstadosMundoPag(Pageable pageable, Long idPaisMundo, String nombrePaisMundo, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearDepartamentooEstadoMundo(DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO);
    RespuestaDTO consultarDepartamentooEstadoMundoporId(Long idDepartamentooEstadoMundo);
    RespuestaDTO consultarDepartamentooEstadoMundoporIdeIdPaisMundo(Long idPaisMundo, Long idDepartamentooEstadoMundo);
    RespuestaDTO consultarDepartamentooEstadoMundoporNombreyNombrePaisMundo(String nombrePaisMundo, String nombreDepartamentooEstadoMundo);
    RespuestaDTO actualizarDepartamentooEstadoMundo(DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO);
    RespuestaDTO eliminarDepartamentooEstadoMundo(Long idDepartamentooEstadoMundo);
}
