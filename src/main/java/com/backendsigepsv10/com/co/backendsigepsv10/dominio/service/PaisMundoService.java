//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PaisMundoDTO;
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
public interface PaisMundoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idPaisMundo, String keyword);
    List<PaisMundoDTO> listarPaisesMundo(Long idPaisMundo, String keyword, String orderBy, String orderMode);
    Slice<PaisMundoDTO> listarPaisesMundoPag(Pageable pageable, Long idPaisMundo, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearPaisMundo(PaisMundoDTO paisMundoDTO);
    RespuestaDTO consultarPaisMundoporId(Long idPaisMundo);
    RespuestaDTO consultarPaisMundoporNombre(String nombrePaisMundo);
    RespuestaDTO actualizarPaisMundo(PaisMundoDTO paisMundoDTO);
    RespuestaDTO eliminarPaisMundo(Long idPaisMundo);
}
