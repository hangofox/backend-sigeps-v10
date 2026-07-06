//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialMovimientoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialMovimientoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ProgramacionTurnoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoMovimiento;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EmpleadoRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ProgramacionTurnoEmpleadoRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class HistorialMovimientoEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ProgramacionTurnoEmpleadoRepository programacionTurnoEmpleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private ProgramacionTurnoEmpleadoDAO programacionTurnoEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoMovimientoRepository tipoMovimientoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoMovimientoDAO tipoMovimientoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 13/06/2026.
    * @param historialMovimientoEmpleadoDTO
    * Recibe un DTO para crear un objeto historialMovimientoEmpleado.
    * @return historialMovimientoEmpleado
    */
    public HistorialMovimientoEmpleado historialMovimientoEmpleado(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO){
        HistorialMovimientoEmpleado historialMovimientoEmpleado = new HistorialMovimientoEmpleado();
        historialMovimientoEmpleado.setIdHistorialMovimientoEmpleado(historialMovimientoEmpleadoDTO.getIdHistorialMovimientoEmpleado());
        historialMovimientoEmpleado.setFechaHMSHistorialMovimientoEmpleado(historialMovimientoEmpleadoDTO.getFechaHMSHistorialMovimientoEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (historialMovimientoEmpleadoDTO.getEmpleadoDTO() != null && historialMovimientoEmpleadoDTO.getEmpleadoDTO().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(historialMovimientoEmpleadoDTO.getEmpleadoDTO().getIdEmpleado());
           empleadoFk.ifPresent(historialMovimientoEmpleado::setEmpleado);
        }
        
        //MAPEAR PROGRAMACION TURNO EMPLEADO RELACIONADO.
        if (historialMovimientoEmpleadoDTO.getProgramacionTurnoEmpleadoDTO() != null && historialMovimientoEmpleadoDTO.getProgramacionTurnoEmpleadoDTO().getIdProgramacionTurnoEmpleado() != null) {
           Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoFk = programacionTurnoEmpleadoRepository.findByIdProgramacionTurnoEmpleado(historialMovimientoEmpleadoDTO.getProgramacionTurnoEmpleadoDTO().getIdProgramacionTurnoEmpleado());
           programacionTurnoEmpleadoFk.ifPresent(historialMovimientoEmpleado::setProgramacionTurnoEmpleado);
        }
        
        //MAPEAR TIPO MOVIMIENTO RELACIONADO.
        if (historialMovimientoEmpleadoDTO.getTipoMovimientoDTO() != null && historialMovimientoEmpleadoDTO.getTipoMovimientoDTO().getIdTipoMovimiento() != null) {
           Optional<TipoMovimiento> tipoMovimientoFk = tipoMovimientoRepository.findByIdTipoMovimiento(historialMovimientoEmpleadoDTO.getTipoMovimientoDTO().getIdTipoMovimiento());
           tipoMovimientoFk.ifPresent(historialMovimientoEmpleado::setTipoMovimiento);
        }
        
        return historialMovimientoEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 13/06/2026.
    * @param historialMovimientoEmpleado
    * Recibe un objeto historialMovimientoEmpleado para crear un DTO.
    * @return historialMovimientoEmpleadoDTO
    */
    public HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO(HistorialMovimientoEmpleado historialMovimientoEmpleado){
        HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO = new HistorialMovimientoEmpleadoDTO();
        historialMovimientoEmpleadoDTO.setIdHistorialMovimientoEmpleado(historialMovimientoEmpleado.getIdHistorialMovimientoEmpleado());
        historialMovimientoEmpleadoDTO.setFechaHMSHistorialMovimientoEmpleado(historialMovimientoEmpleado.getFechaHMSHistorialMovimientoEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (historialMovimientoEmpleado.getEmpleado() != null && historialMovimientoEmpleado.getEmpleado().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(historialMovimientoEmpleado.getEmpleado().getIdEmpleado());
           empleadoFk.ifPresent(empl -> historialMovimientoEmpleadoDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empl)));
        }
        
        //MAPEAR PROGRAMACION TURNO EMPLEADO RELACIONADO.
        if (historialMovimientoEmpleado.getProgramacionTurnoEmpleado() != null && historialMovimientoEmpleado.getProgramacionTurnoEmpleado().getIdProgramacionTurnoEmpleado() != null) {
           Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoFk = programacionTurnoEmpleadoRepository.findByIdProgramacionTurnoEmpleado(historialMovimientoEmpleado.getProgramacionTurnoEmpleado().getIdProgramacionTurnoEmpleado());
           programacionTurnoEmpleadoFk.ifPresent(prog -> historialMovimientoEmpleadoDTO.setProgramacionTurnoEmpleadoDTO(programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(prog)));
        }
        
        //MAPEAR TIPO MOVIMIENTO RELACIONADO.
        if (historialMovimientoEmpleado.getTipoMovimiento() != null && historialMovimientoEmpleado.getTipoMovimiento().getIdTipoMovimiento() != null) {
           Optional<TipoMovimiento> tipoMovimientoFk = tipoMovimientoRepository.findByIdTipoMovimiento(historialMovimientoEmpleado.getTipoMovimiento().getIdTipoMovimiento());
           tipoMovimientoFk.ifPresent(tipo -> historialMovimientoEmpleadoDTO.setTipoMovimientoDTO(tipoMovimientoDAO.tipoMovimientoDTO(tipo)));
        }
        
        return historialMovimientoEmpleadoDTO;
    }
}
