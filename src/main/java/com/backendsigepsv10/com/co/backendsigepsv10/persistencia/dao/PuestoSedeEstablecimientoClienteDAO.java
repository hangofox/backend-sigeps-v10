//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PuestoSedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PuestoSedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.SedeEstablecimientoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class PuestoSedeEstablecimientoClienteDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private SedeEstablecimientoClienteRepository sedeEstablecimientoClienteRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private SedeEstablecimientoClienteDAO sedeEstablecimientoClienteDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param puestoSedeEstablecimientoClienteDTO
    * Recibe un DTO para crear un objeto puestoSedeEstablecimientoCliente.
    * @return puestoSedeEstablecimientoCliente
    */
    public PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO){
        PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente = new PuestoSedeEstablecimientoCliente();
        puestoSedeEstablecimientoCliente.setIdPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getIdPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoCliente.setNombrePuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getNombrePuestoSedeEstablecimientoCliente().toUpperCase());
        puestoSedeEstablecimientoCliente.setDescripcionPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getDescripcionPuestoSedeEstablecimientoCliente().toUpperCase());
        puestoSedeEstablecimientoCliente.setFechaHMSIngresoPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getFechaHMSIngresoPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoCliente.setFechaHMSModificacionPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getFechaHMSModificacionPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoCliente.setEstadoPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getEstadoPuestoSedeEstablecimientoCliente().toUpperCase());
        
        //MAPEAR SEDE DE ESTABLECIMIENTO DEL CLIENTE RELACIONADO.
        if (puestoSedeEstablecimientoClienteDTO.getSedeEstablecimientoClienteDTO() != null && puestoSedeEstablecimientoClienteDTO.getSedeEstablecimientoClienteDTO().getIdSedeEstablecimientoCliente() != null) {
           Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteFk = sedeEstablecimientoClienteRepository.findByIdSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getSedeEstablecimientoClienteDTO().getIdSedeEstablecimientoCliente());
           sedeEstablecimientoClienteFk.ifPresent(puestoSedeEstablecimientoCliente::setSedeEstablecimientoCliente);
        }
        
        return puestoSedeEstablecimientoCliente;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param puestoSedeEstablecimientoCliente
    * Recibe un objeto puestoSedeEstablecimientoCliente para crear un DTO.
    * @return puestoSedeEstablecimientoClienteDTO
    */
    public PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO(PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente){
        PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO = new PuestoSedeEstablecimientoClienteDTO();
        puestoSedeEstablecimientoClienteDTO.setIdPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getIdPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoClienteDTO.setNombrePuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getNombrePuestoSedeEstablecimientoCliente().toUpperCase());
        puestoSedeEstablecimientoClienteDTO.setDescripcionPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getDescripcionPuestoSedeEstablecimientoCliente().toUpperCase());
        puestoSedeEstablecimientoClienteDTO.setFechaHMSIngresoPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getFechaHMSIngresoPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoClienteDTO.setFechaHMSModificacionPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getFechaHMSModificacionPuestoSedeEstablecimientoCliente());
        puestoSedeEstablecimientoClienteDTO.setEstadoPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getEstadoPuestoSedeEstablecimientoCliente().toUpperCase());
        
        //MAPEAR SEDE DE ESTABLECIMIENTO DEL CLIENTE RELACIONADO.
        if (puestoSedeEstablecimientoCliente.getSedeEstablecimientoCliente() != null && puestoSedeEstablecimientoCliente.getSedeEstablecimientoCliente().getIdSedeEstablecimientoCliente() != null) {
           Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteFk = sedeEstablecimientoClienteRepository.findByIdSedeEstablecimientoCliente(puestoSedeEstablecimientoCliente.getSedeEstablecimientoCliente().getIdSedeEstablecimientoCliente());
           sedeEstablecimientoClienteFk.ifPresent(sedeEstablecimientoCliente -> puestoSedeEstablecimientoClienteDTO.setSedeEstablecimientoClienteDTO(sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoCliente)));
        }
        
        return puestoSedeEstablecimientoClienteDTO;
    }
}
