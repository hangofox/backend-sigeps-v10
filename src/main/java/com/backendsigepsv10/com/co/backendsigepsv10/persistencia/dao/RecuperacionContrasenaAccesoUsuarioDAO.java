//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RecuperacionContrasenaAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.RecuperacionContrasenaAccesoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class RecuperacionContrasenaAccesoUsuarioDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private UsuarioRepository usuarioRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private UsuarioDAO usuarioDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param recuperacionContrasenaAccesoUsuarioDTO
    * Recibe un DTO para crear un objeto recuperacionContrasenaAccesoUsuario.
    * @return recuperacionContrasenaAccesoUsuario
    */
    public RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario(RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO){
        RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario = new RecuperacionContrasenaAccesoUsuario();
        recuperacionContrasenaAccesoUsuario.setIdRecuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getIdRecuperacionContrasenaAccesoUsuario());
        
        //MAPEAR USUARIO RELACIONADO.
        if (recuperacionContrasenaAccesoUsuarioDTO.getUsuarioDTO() != null && recuperacionContrasenaAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(recuperacionContrasenaAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario());
           usuarioFk.ifPresent(recuperacionContrasenaAccesoUsuario::setUsuario);
        }
        
        recuperacionContrasenaAccesoUsuario.setCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
        recuperacionContrasenaAccesoUsuario.setFechaHMSExpCodActivContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getFechaHMSExpCodActivContrasenaAccesoUsuario());
        recuperacionContrasenaAccesoUsuario.setEstadoUsoCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getEstadoUsoCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
        
        return recuperacionContrasenaAccesoUsuario;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param recuperacionContrasenaAccesoUsuario
    * Recibe un objeto recuperacionContrasenaAccesoUsuario para crear un DTO.
    * @return recuperacionContrasenaAccesoUsuarioDTO
    */
    public RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO(RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario){
        RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO = new RecuperacionContrasenaAccesoUsuarioDTO();
        recuperacionContrasenaAccesoUsuarioDTO.setIdRecuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuario.getIdRecuperacionContrasenaAccesoUsuario());
        
        //MAPEAR USUARIO RELACIONADO.
        if (recuperacionContrasenaAccesoUsuario.getUsuario() != null && recuperacionContrasenaAccesoUsuario.getUsuario().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(recuperacionContrasenaAccesoUsuario.getUsuario().getIdUsuario());
           usuarioFk.ifPresent(usuario -> recuperacionContrasenaAccesoUsuarioDTO.setUsuarioDTO(usuarioDAO.usuarioDTO(usuario)));
        }
        
        recuperacionContrasenaAccesoUsuarioDTO.setCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuario.getCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
        recuperacionContrasenaAccesoUsuarioDTO.setFechaHMSExpCodActivContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuario.getFechaHMSExpCodActivContrasenaAccesoUsuario());
        recuperacionContrasenaAccesoUsuarioDTO.setEstadoUsoCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuario.getEstadoUsoCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
        
        return recuperacionContrasenaAccesoUsuarioDTO;
    }
}
