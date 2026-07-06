//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.EstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoDocumentoIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class EstablecimientoClienteDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoDocumentoIdentificacionRepository tipoDocumentoIdentificacionRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoDocumentoIdentificacionDAO tipoDocumentoIdentificacionDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param establecimientoClienteDTO
    * Recibe un DTO para crear un objeto establecimientoCliente.
    * @return establecimientoCliente
    */
    public EstablecimientoCliente establecimientoCliente(EstablecimientoClienteDTO establecimientoClienteDTO){
        EstablecimientoCliente establecimientoCliente = new EstablecimientoCliente();
        establecimientoCliente.setIdEstablecimientoCliente(establecimientoClienteDTO.getIdEstablecimientoCliente());
        establecimientoCliente.setNumeroDocumentoIdentificacionEstablecimientoCliente(establecimientoClienteDTO.getNumeroDocumentoIdentificacionEstablecimientoCliente());
        establecimientoCliente.setNombreRazonSocialEstablecimientoCliente(establecimientoClienteDTO.getNombreRazonSocialEstablecimientoCliente().toUpperCase());
        establecimientoCliente.setFechaHMSIngresoEstablecimientoCliente(establecimientoClienteDTO.getFechaHMSIngresoEstablecimientoCliente());
        establecimientoCliente.setFechaHMSModificacionEstablecimientoCliente(establecimientoClienteDTO.getFechaHMSModificacionEstablecimientoCliente());
        establecimientoCliente.setEstadoEstablecimientoCliente(establecimientoClienteDTO.getEstadoEstablecimientoCliente().toUpperCase());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (establecimientoClienteDTO.getTipoDocumentoIdentificacionDTO() != null && establecimientoClienteDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(establecimientoClienteDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(establecimientoCliente::setTipoDocumentoIdentificacion);
        }
        
        return establecimientoCliente;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param establecimientoCliente
    * Recibe un objeto establecimientoCliente para crear un DTO.
    * @return establecimientoClienteDTO
    */
    public EstablecimientoClienteDTO establecimientoClienteDTO(EstablecimientoCliente establecimientoCliente){
        EstablecimientoClienteDTO establecimientoClienteDTO = new EstablecimientoClienteDTO();
        establecimientoClienteDTO.setIdEstablecimientoCliente(establecimientoCliente.getIdEstablecimientoCliente());
        establecimientoClienteDTO.setNumeroDocumentoIdentificacionEstablecimientoCliente(establecimientoCliente.getNumeroDocumentoIdentificacionEstablecimientoCliente());
        establecimientoClienteDTO.setNombreRazonSocialEstablecimientoCliente(establecimientoCliente.getNombreRazonSocialEstablecimientoCliente().toUpperCase());
        establecimientoClienteDTO.setFechaHMSIngresoEstablecimientoCliente(establecimientoCliente.getFechaHMSIngresoEstablecimientoCliente());
        establecimientoClienteDTO.setFechaHMSModificacionEstablecimientoCliente(establecimientoCliente.getFechaHMSModificacionEstablecimientoCliente());
        establecimientoClienteDTO.setEstadoEstablecimientoCliente(establecimientoCliente.getEstadoEstablecimientoCliente().toUpperCase());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (establecimientoCliente.getTipoDocumentoIdentificacion() != null && establecimientoCliente.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(establecimientoCliente.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(tipoDocumentoIdentificacion -> establecimientoClienteDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacion)));
        }
        
        return establecimientoClienteDTO;
    }
}
