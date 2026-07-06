//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PrivilegyRestriccAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Funcionalidad;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PrivilegyRestriccAccesoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Rol;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.FuncionalidadRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.RolRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class PrivilegyRestriccAccesoUsuarioDAO {

    @Autowired//INYECTAMOS EL REPOSITORIO.
    private UsuarioRepository usuarioRepository;

    @Autowired//INYECTAMOS EL REPOSITORIO.
    private FuncionalidadRepository funcionalidadRepository;

    @Autowired//INYECTAMOS EL REPOSITORIO.
    private RolRepository rolRepository;

    @Autowired//INYECTAMOS EL DAO.
    private UsuarioDAO usuarioDAO;

    @Autowired//INYECTAMOS EL DAO.
    private FuncionalidadDAO funcionalidadDAO;

    @Autowired//INYECTAMOS EL DAO.
    private RolDAO rolDAO;

    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param privilegyRestriccAccesoUsuarioDTO
    * Recibe un DTO para crear un objeto privilegyRestriccAccesoUsuario.
    * @return privilegyRestriccAccesoUsuario
    */
    public PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO){
        PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario = new PrivilegyRestriccAccesoUsuario();
        privilegyRestriccAccesoUsuario.setIdPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getIdPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuario.setNumRegPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getNumRegPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuario.setUrlAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getUrlAccesoUsuario());
        privilegyRestriccAccesoUsuario.setSioNoPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getSioNoPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuario.setFechaHMSIngresoPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getFechaHMSIngresoPrivilegyRestriccAccesoUsuario());

        //MAPEAR USUARIO RELACIONADO.
        if (privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO() != null && privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario());
           usuarioFk.ifPresent(privilegyRestriccAccesoUsuario::setUsuario);
        }

        //MAPEAR FUNCIONALIDAD RELACIONADA.
        if (privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO() != null && privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad() != null) {
           Optional<Funcionalidad> funcionalidadFk = funcionalidadRepository.findByIdFuncionalidad(privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad());
           funcionalidadFk.ifPresent(privilegyRestriccAccesoUsuario::setFuncionalidad);
        }

        //MAPEAR ROL RELACIONADO.
        if (privilegyRestriccAccesoUsuarioDTO.getRolDTO() != null && privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol() != null) {
           Optional<Rol> rolFk = rolRepository.findByIdRol(privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol());
           rolFk.ifPresent(privilegyRestriccAccesoUsuario::setRol);
        }

        return privilegyRestriccAccesoUsuario;
    }

    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param privilegyRestriccAccesoUsuario
    * Recibe un objeto privilegyRestriccAccesoUsuario para crear un DTO.
    * @return privilegyRestriccAccesoUsuarioDTO
    */
    public PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO(PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario){
        PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO = new PrivilegyRestriccAccesoUsuarioDTO();
        privilegyRestriccAccesoUsuarioDTO.setIdPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuario.getIdPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuarioDTO.setNumRegPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuario.getNumRegPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuarioDTO.setUrlAccesoUsuario(privilegyRestriccAccesoUsuario.getUrlAccesoUsuario());
        privilegyRestriccAccesoUsuarioDTO.setSioNoPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuario.getSioNoPrivilegyRestriccAccesoUsuario());
        privilegyRestriccAccesoUsuarioDTO.setFechaHMSIngresoPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuario.getFechaHMSIngresoPrivilegyRestriccAccesoUsuario());

        //MAPEAR USUARIO RELACIONADO.
        if (privilegyRestriccAccesoUsuario.getUsuario() != null && privilegyRestriccAccesoUsuario.getUsuario().getIdUsuario() != null) {
           Optional<Usuario> usuarioFk = usuarioRepository.findByIdUsuario(privilegyRestriccAccesoUsuario.getUsuario().getIdUsuario());
           usuarioFk.ifPresent(usuario -> privilegyRestriccAccesoUsuarioDTO.setUsuarioDTO(usuarioDAO.usuarioDTO(usuario)));
        }

        //MAPEAR FUNCIONALIDAD RELACIONADA.
        if (privilegyRestriccAccesoUsuario.getFuncionalidad() != null && privilegyRestriccAccesoUsuario.getFuncionalidad().getIdFuncionalidad() != null) {
           Optional<Funcionalidad> funcionalidadFk = funcionalidadRepository.findByIdFuncionalidad(privilegyRestriccAccesoUsuario.getFuncionalidad().getIdFuncionalidad());
           funcionalidadFk.ifPresent(funcionalidad -> privilegyRestriccAccesoUsuarioDTO.setFuncionalidadDTO(funcionalidadDAO.funcionalidadDTO(funcionalidad)));
        }

        //MAPEAR ROL RELACIONADO.
        if (privilegyRestriccAccesoUsuario.getRol() != null && privilegyRestriccAccesoUsuario.getRol().getIdRol() != null) {
           Optional<Rol> rolFk = rolRepository.findByIdRol(privilegyRestriccAccesoUsuario.getRol().getIdRol());
           rolFk.ifPresent(rol -> privilegyRestriccAccesoUsuarioDTO.setRolDTO(rolDAO.rolDTO(rol)));
        }

        return privilegyRestriccAccesoUsuarioDTO;
    }
}
