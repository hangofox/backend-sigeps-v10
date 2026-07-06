//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ProgramacionTurnoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ProgramacionTurnoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PuestoSedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Turno;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EmpleadoRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.PuestoSedeEstablecimientoClienteRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class ProgramacionTurnoEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private PuestoSedeEstablecimientoClienteRepository puestoSedeEstablecimientoClienteRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private PuestoSedeEstablecimientoClienteDAO puestoSedeEstablecimientoClienteDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TurnoRepository turnoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TurnoDAO turnoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param programacionTurnoEmpleadoDTO
    * Recibe un DTO para crear un objeto programacionTurnoEmpleado.
    * @return programacionTurnoEmpleado
    */
    public ProgramacionTurnoEmpleado programacionTurnoEmpleado(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO){
        ProgramacionTurnoEmpleado programacionTurnoEmpleado = new ProgramacionTurnoEmpleado();
        programacionTurnoEmpleado.setIdProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO.getIdProgramacionTurnoEmpleado());
        programacionTurnoEmpleado.setFechaHMSIniciacionProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO.getFechaHMSIniciacionProgramacionTurnoEmpleado());
        programacionTurnoEmpleado.setFechaHMSFinalizacionProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO.getFechaHMSFinalizacionProgramacionTurnoEmpleado());
        programacionTurnoEmpleado.setEstadoProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO.getEstadoProgramacionTurnoEmpleado() != null ? programacionTurnoEmpleadoDTO.getEstadoProgramacionTurnoEmpleado().toUpperCase() : null);
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (programacionTurnoEmpleadoDTO.getEmpleadoDTO() != null && programacionTurnoEmpleadoDTO.getEmpleadoDTO().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(programacionTurnoEmpleadoDTO.getEmpleadoDTO().getIdEmpleado());
           empleadoFk.ifPresent(programacionTurnoEmpleado::setEmpleado);
        }
        
        //MAPEAR PUESTO DE SEDE DE ESTABLECIMIENTO CLIENTE RELACIONADO.
        if (programacionTurnoEmpleadoDTO.getPuestoSedeEstablecimientoClienteDTO() != null && programacionTurnoEmpleadoDTO.getPuestoSedeEstablecimientoClienteDTO().getIdPuestoSedeEstablecimientoCliente() != null) {
           Optional<PuestoSedeEstablecimientoCliente> puestoSedeEstablecimientoClienteFk = puestoSedeEstablecimientoClienteRepository.findByIdPuestoSedeEstablecimientoCliente(programacionTurnoEmpleadoDTO.getPuestoSedeEstablecimientoClienteDTO().getIdPuestoSedeEstablecimientoCliente());
           puestoSedeEstablecimientoClienteFk.ifPresent(programacionTurnoEmpleado::setPuestoSedeEstablecimientoCliente);
        }
        
        //MAPEAR TURNO RELACIONADO.
        if (programacionTurnoEmpleadoDTO.getTurnoDTO() != null && programacionTurnoEmpleadoDTO.getTurnoDTO().getIdTurno() != null) {
           Optional<Turno> turnoFk = turnoRepository.findByIdTurno(programacionTurnoEmpleadoDTO.getTurnoDTO().getIdTurno());
           turnoFk.ifPresent(programacionTurnoEmpleado::setTurno);
        }
        
        return programacionTurnoEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param programacionTurnoEmpleado
    * Recibe un objeto programacionTurnoEmpleado para crear un DTO.
    * @return programacionTurnoEmpleadoDTO
    */
    public ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO(ProgramacionTurnoEmpleado programacionTurnoEmpleado){
        ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO = new ProgramacionTurnoEmpleadoDTO();
        programacionTurnoEmpleadoDTO.setIdProgramacionTurnoEmpleado(programacionTurnoEmpleado.getIdProgramacionTurnoEmpleado());
        programacionTurnoEmpleadoDTO.setFechaHMSIniciacionProgramacionTurnoEmpleado(programacionTurnoEmpleado.getFechaHMSIniciacionProgramacionTurnoEmpleado());
        programacionTurnoEmpleadoDTO.setFechaHMSFinalizacionProgramacionTurnoEmpleado(programacionTurnoEmpleado.getFechaHMSFinalizacionProgramacionTurnoEmpleado());
        programacionTurnoEmpleadoDTO.setEstadoProgramacionTurnoEmpleado(programacionTurnoEmpleado.getEstadoProgramacionTurnoEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (programacionTurnoEmpleado.getEmpleado() != null && programacionTurnoEmpleado.getEmpleado().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(programacionTurnoEmpleado.getEmpleado().getIdEmpleado());
           empleadoFk.ifPresent(empl -> programacionTurnoEmpleadoDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empl)));
        }
        
        //MAPEAR PUESTO DE SEDE DE ESTABLECIMIENTO CLIENTE RELACIONADO.
        if (programacionTurnoEmpleado.getPuestoSedeEstablecimientoCliente() != null && programacionTurnoEmpleado.getPuestoSedeEstablecimientoCliente().getIdPuestoSedeEstablecimientoCliente() != null) {
           Optional<PuestoSedeEstablecimientoCliente> puestoEstablecimientoClienteFk = puestoSedeEstablecimientoClienteRepository.findByIdPuestoSedeEstablecimientoCliente(programacionTurnoEmpleado.getPuestoSedeEstablecimientoCliente().getIdPuestoSedeEstablecimientoCliente());
           puestoEstablecimientoClienteFk.ifPresent(puesto -> programacionTurnoEmpleadoDTO.setPuestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puesto)));
        }
        
        //MAPEAR TURNO RELACIONADO.
        if (programacionTurnoEmpleado.getTurno() != null && programacionTurnoEmpleado.getTurno().getIdTurno() != null) {
           Optional<Turno> turnoFk = turnoRepository.findByIdTurno(programacionTurnoEmpleado.getTurno().getIdTurno());
           turnoFk.ifPresent(turno -> programacionTurnoEmpleadoDTO.setTurnoDTO(turnoDAO.turnoDTO(turno)));
        }
        
        return programacionTurnoEmpleadoDTO;
    }
}
