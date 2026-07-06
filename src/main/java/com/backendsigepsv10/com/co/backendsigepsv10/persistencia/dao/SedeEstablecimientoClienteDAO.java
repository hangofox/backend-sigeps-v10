//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.EstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EstablecimientoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class SedeEstablecimientoClienteDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EstablecimientoClienteRepository establecimientoClienteRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EstablecimientoClienteDAO establecimientoClienteDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param sedeEstablecimientoClienteDTO
    * Recibe un DTO para crear un objeto sedeEstablecimientoCliente.
    * @return sedeEstablecimientoCliente
    */
    public SedeEstablecimientoCliente sedeEstablecimientoCliente(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO){
        SedeEstablecimientoCliente sedeEstablecimientoCliente = new SedeEstablecimientoCliente();
        sedeEstablecimientoCliente.setIdSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getIdSedeEstablecimientoCliente());
        sedeEstablecimientoCliente.setNombreSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getNombreSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setDireccionSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getDireccionSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setTelefonoSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getTelefonoSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setMovilSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getMovilSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setCorreoElectronicoInstitucionalSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getCorreoElectronicoInstitucionalSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setPaisOrigenSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getPaisOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setDepartamentooEstadoOrigenSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getDepartamentooEstadoOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setCiudadOrigenSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getCiudadOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoCliente.setFechaHMSIngresoSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getFechaHMSIngresoSedeEstablecimientoCliente());
        sedeEstablecimientoCliente.setFechaHMSModificacionSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getFechaHMSModificacionSedeEstablecimientoCliente());
        sedeEstablecimientoCliente.setEstadoSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getEstadoSedeEstablecimientoCliente().toUpperCase());
        
        //MAPEAR ESTABLECIMIENTO DEL CLIENTE RELACIONADO.
        if (sedeEstablecimientoClienteDTO.getEstablecimientoClienteDTO() != null && sedeEstablecimientoClienteDTO.getEstablecimientoClienteDTO().getIdEstablecimientoCliente() != null) {
           Optional<EstablecimientoCliente> establecimientoClienteFk = establecimientoClienteRepository.findByIdEstablecimientoCliente(sedeEstablecimientoClienteDTO.getEstablecimientoClienteDTO().getIdEstablecimientoCliente());
           establecimientoClienteFk.ifPresent(sedeEstablecimientoCliente::setEstablecimientoCliente);
        }
        
        return sedeEstablecimientoCliente;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param sedeEstablecimientoCliente
    * Recibe un objeto sedeEstablecimientoCliente para crear un DTO.
    * @return sedeEstablecimientoClienteDTO
    */
    public SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO(SedeEstablecimientoCliente sedeEstablecimientoCliente){
        SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO = new SedeEstablecimientoClienteDTO();
        sedeEstablecimientoClienteDTO.setIdSedeEstablecimientoCliente(sedeEstablecimientoCliente.getIdSedeEstablecimientoCliente());
        sedeEstablecimientoClienteDTO.setNombreSedeEstablecimientoCliente(sedeEstablecimientoCliente.getNombreSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setDireccionSedeEstablecimientoCliente(sedeEstablecimientoCliente.getDireccionSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setTelefonoSedeEstablecimientoCliente(sedeEstablecimientoCliente.getTelefonoSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setMovilSedeEstablecimientoCliente(sedeEstablecimientoCliente.getMovilSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setCorreoElectronicoInstitucionalSedeEstablecimientoCliente(sedeEstablecimientoCliente.getCorreoElectronicoInstitucionalSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setPaisOrigenSedeEstablecimientoCliente(sedeEstablecimientoCliente.getPaisOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setDepartamentooEstadoOrigenSedeEstablecimientoCliente(sedeEstablecimientoCliente.getDepartamentooEstadoOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setCiudadOrigenSedeEstablecimientoCliente(sedeEstablecimientoCliente.getCiudadOrigenSedeEstablecimientoCliente().toUpperCase());
        sedeEstablecimientoClienteDTO.setFechaHMSIngresoSedeEstablecimientoCliente(sedeEstablecimientoCliente.getFechaHMSIngresoSedeEstablecimientoCliente());
        sedeEstablecimientoClienteDTO.setFechaHMSModificacionSedeEstablecimientoCliente(sedeEstablecimientoCliente.getFechaHMSModificacionSedeEstablecimientoCliente());
        sedeEstablecimientoClienteDTO.setEstadoSedeEstablecimientoCliente(sedeEstablecimientoCliente.getEstadoSedeEstablecimientoCliente().toUpperCase());
        
        //MAPEAR ESTABLECIMIENTO DEL CLIENTE RELACIONADO.
        if (sedeEstablecimientoCliente.getEstablecimientoCliente() != null && sedeEstablecimientoCliente.getEstablecimientoCliente().getIdEstablecimientoCliente() != null) {
           Optional<EstablecimientoCliente> establecimientoClienteFk = establecimientoClienteRepository.findByIdEstablecimientoCliente(sedeEstablecimientoCliente.getEstablecimientoCliente().getIdEstablecimientoCliente());
           establecimientoClienteFk.ifPresent(establecimientoCliente -> sedeEstablecimientoClienteDTO.setEstablecimientoClienteDTO(establecimientoClienteDAO.establecimientoClienteDTO(establecimientoCliente)));
        }
        
        return sedeEstablecimientoClienteDTO;
    }
}
