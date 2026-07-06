//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AuditoriaSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AuditoriaSistema;
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
public class AuditoriaSistemaDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private UsuarioRepository usuarioRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private UsuarioDAO usuarioDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param auditoriaSistemaDTO
    * Recibe un DTO para crear un objeto auditoriaSistema.
    * @return auditoriaSistema
    */
    public AuditoriaSistema auditoriaSistema(AuditoriaSistemaDTO auditoriaSistemaDTO){
        AuditoriaSistema auditoriaSistema = new AuditoriaSistema();
        auditoriaSistema.setIdAuditoriaSistema(auditoriaSistemaDTO.getIdAuditoriaSistema());
        auditoriaSistema.setFechaHMSAuditoriaSistema(auditoriaSistemaDTO.getFechaHMSAuditoriaSistema());
        auditoriaSistema.setAccionUsuarioSistema(auditoriaSistemaDTO.getAccionUsuarioSistema() != null ? auditoriaSistemaDTO.getAccionUsuarioSistema().toUpperCase() : null);
        auditoriaSistema.setDescripcionAuditoriaSistema(auditoriaSistemaDTO.getDescripcionAuditoriaSistema() != null ? auditoriaSistemaDTO.getDescripcionAuditoriaSistema().toUpperCase() : null);
        
        //MAPEAR USUARIO RELACIONADO.
        if (auditoriaSistemaDTO.getUsuarioDTO() != null && auditoriaSistemaDTO.getUsuarioDTO().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(auditoriaSistemaDTO.getUsuarioDTO().getIdUsuario());
           usuarioFk.ifPresent(auditoriaSistema::setUsuario);
        }
        
        return auditoriaSistema;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param auditoriaSistema
    * Recibe un objeto auditoriaSistema para crear un DTO.
    * @return auditoriaSistemaDTO
    */
    public AuditoriaSistemaDTO auditoriaSistemaDTO(AuditoriaSistema auditoriaSistema){
        AuditoriaSistemaDTO auditoriaSistemaDTO = new AuditoriaSistemaDTO();
        auditoriaSistemaDTO.setIdAuditoriaSistema(auditoriaSistema.getIdAuditoriaSistema());
        auditoriaSistemaDTO.setFechaHMSAuditoriaSistema(auditoriaSistema.getFechaHMSAuditoriaSistema());
        auditoriaSistemaDTO.setAccionUsuarioSistema(auditoriaSistema.getAccionUsuarioSistema());
        auditoriaSistemaDTO.setDescripcionAuditoriaSistema(auditoriaSistema.getDescripcionAuditoriaSistema());
        
        //MAPEAR USUARIO RELACIONADO.
        if (auditoriaSistema.getUsuario() != null && auditoriaSistema.getUsuario().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(auditoriaSistema.getUsuario().getIdUsuario());
           usuarioFk.ifPresent(usuario -> auditoriaSistemaDTO.setUsuarioDTO(usuarioDAO.usuarioDTO(usuario)));
        }
        
        return auditoriaSistemaDTO;
    }
}
