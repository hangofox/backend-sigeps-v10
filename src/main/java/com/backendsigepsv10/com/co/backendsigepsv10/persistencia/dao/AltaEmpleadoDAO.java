//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AltaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AltaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class AltaEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param altaEmpleadoDTO
    * Recibe un DTO para crear un objeto altaEmpleado.
    * @return altaEmpleado
    */
    public AltaEmpleado altaEmpleado(AltaEmpleadoDTO altaEmpleadoDTO){
        AltaEmpleado altaEmpleado = new AltaEmpleado();
        altaEmpleado.setIdAltaEmpleado(altaEmpleadoDTO.getIdAltaEmpleado());
        altaEmpleado.setFechaHMSAltaEmpleado(altaEmpleadoDTO.getFechaHMSAltaEmpleado());
        altaEmpleado.setNumeroContratoOActoAdmvoNombEmpleado(altaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado() != null ? altaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado().toUpperCase() : null);
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (altaEmpleadoDTO.getEmpleadoDTO() != null && altaEmpleadoDTO.getEmpleadoDTO().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(altaEmpleadoDTO.getEmpleadoDTO().getIdEmpleado());
           empleadoFk.ifPresent(altaEmpleado::setEmpleado);
        }
        
        return altaEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param altaEmpleado
    * Recibe un objeto altaEmpleado para crear un DTO.
    * @return altaEmpleadoDTO
    */
    public AltaEmpleadoDTO altaEmpleadoDTO(AltaEmpleado altaEmpleado){
        AltaEmpleadoDTO altaEmpleadoDTO = new AltaEmpleadoDTO();
        altaEmpleadoDTO.setIdAltaEmpleado(altaEmpleado.getIdAltaEmpleado());
        altaEmpleadoDTO.setFechaHMSAltaEmpleado(altaEmpleado.getFechaHMSAltaEmpleado());
        altaEmpleadoDTO.setNumeroContratoOActoAdmvoNombEmpleado(altaEmpleado.getNumeroContratoOActoAdmvoNombEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (altaEmpleado.getEmpleado() != null && altaEmpleado.getEmpleado().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(altaEmpleado.getEmpleado().getIdEmpleado());
           empleadoFk.ifPresent(empl -> altaEmpleadoDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empl)));
        }
        
        return altaEmpleadoDTO;
    }
}
