//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TurnoDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface TurnoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idTurno, String keyword, String nombreTurno, String estadoTurno);
    List<TurnoDTO> listarTurnos(Long idTurno, String keyword, String nombreTurno, String estadoTurno, String orderBy, String orderMode);
    Slice<TurnoDTO> listarTurnosPag(Pageable pageable, Long idTurno, String keyword, String nombreTurno, String estadoTurno, String orderBy, String orderMode);
    RespuestaDTO crearTurno(TurnoDTO turnoDTO);
    RespuestaDTO consultarTurnoporId(Long idTurno);
    RespuestaDTO consultarTurnoporNombre(String nombreTurno);
    RespuestaDTO actualizarTurno(TurnoDTO turnoDTO);
    RespuestaDTO eliminarTurno(Long idTurno);
}
