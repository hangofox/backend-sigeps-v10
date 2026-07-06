//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.BajaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.BajaEmpleado;
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
public class BajaEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param bajaEmpleadoDTO
    * Recibe un DTO para crear un objeto bajaEmpleado.
    * @return bajaEmpleado
    */
    public BajaEmpleado bajaEmpleado(BajaEmpleadoDTO bajaEmpleadoDTO){
        BajaEmpleado bajaEmpleado = new BajaEmpleado();
        bajaEmpleado.setIdBajaEmpleado(bajaEmpleadoDTO.getIdBajaEmpleado());
        bajaEmpleado.setFechaHMSBajaEmpleado(bajaEmpleadoDTO.getFechaHMSBajaEmpleado());
        bajaEmpleado.setNumeroContratoOActoAdmvoNombEmpleado(bajaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado() != null ? bajaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado().toUpperCase() : null);
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (bajaEmpleadoDTO.getEmpleadoDTO() != null && bajaEmpleadoDTO.getEmpleadoDTO().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(bajaEmpleadoDTO.getEmpleadoDTO().getIdEmpleado());
           empleadoFk.ifPresent(bajaEmpleado::setEmpleado);
        }
        
        return bajaEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param bajaEmpleado
    * Recibe un objeto bajaEmpleado para crear un DTO.
    * @return bajaEmpleadoDTO
    */
    public BajaEmpleadoDTO bajaEmpleadoDTO(BajaEmpleado bajaEmpleado){
        BajaEmpleadoDTO bajaEmpleadoDTO = new BajaEmpleadoDTO();
        bajaEmpleadoDTO.setIdBajaEmpleado(bajaEmpleado.getIdBajaEmpleado());
        bajaEmpleadoDTO.setFechaHMSBajaEmpleado(bajaEmpleado.getFechaHMSBajaEmpleado());
        bajaEmpleadoDTO.setNumeroContratoOActoAdmvoNombEmpleado(bajaEmpleado.getNumeroContratoOActoAdmvoNombEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (bajaEmpleado.getEmpleado() != null && bajaEmpleado.getEmpleado().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(bajaEmpleado.getEmpleado().getIdEmpleado());
           empleadoFk.ifPresent(empl -> bajaEmpleadoDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empl)));
        }
        
        return bajaEmpleadoDTO;
    }
}
