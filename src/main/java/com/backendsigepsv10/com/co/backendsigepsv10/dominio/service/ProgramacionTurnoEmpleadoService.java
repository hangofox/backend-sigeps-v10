//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ProgramacionTurnoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface ProgramacionTurnoEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado);
    List<ProgramacionTurnoEmpleadoDTO> listarProgramacionesTurnosEmpleados(Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado, String orderBy, String orderMode);
    Slice<ProgramacionTurnoEmpleadoDTO> listarProgramacionesTurnosEmpleadosPag(Pageable pageable, Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado, String orderBy, String orderMode);
    RespuestaDTO crearProgramacionTurnoEmpleado(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO);
    RespuestaDTO consultarProgramacionTurnoEmpleadoporId(Long idProgramacionTurnoEmpleado);
    RespuestaDTO consultarProgramacionTurnoEmpleadoporIdEmpleadoyNombreTurno(Long idEmpleado, String nombreTurno);
    RespuestaDTO actualizarProgramacionTurnoEmpleado(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO);
    RespuestaDTO eliminarProgramacionTurnoEmpleado(Long idProgramacionTurnoEmpleado);
}
