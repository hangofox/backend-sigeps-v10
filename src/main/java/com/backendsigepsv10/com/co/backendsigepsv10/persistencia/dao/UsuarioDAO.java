//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.UsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoDocumentoIdentificacionRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class UsuarioDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoDocumentoIdentificacionRepository tipoDocumentoIdentificacionRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoDocumentoIdentificacionDAO tipoDocumentoIdentificacionDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoUsuarioRepository tipoUsuarioRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoUsuarioDAO tipoUsuarioDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param usuarioDTO
    * Recibe un DTO para crear un objeto usuario.
    * @return usuario
    */
    public Usuario usuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setNicknameUsuario(usuarioDTO.getNicknameUsuario());
        usuario.setPasswordUsuario(usuarioDTO.getPasswordUsuario());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (usuarioDTO.getTipoDocumentoIdentificacionDTO() != null && usuarioDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(usuarioDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(usuario::setTipoDocumentoIdentificacion);
        }
        
        usuario.setNumeroDocumentoIdentificacionUsuario(usuarioDTO.getNumeroDocumentoIdentificacionUsuario());
        usuario.setLugarExpedicionDocumentoIdentificacionUsuario(usuarioDTO.getLugarExpedicionDocumentoIdentificacionUsuario().toUpperCase());
        usuario.setNombresUsuario(usuarioDTO.getNombresUsuario().toUpperCase());
        usuario.setPrimerApellidoUsuario(usuarioDTO.getPrimerApellidoUsuario().toUpperCase());
        usuario.setSegundoApellidoUsuario(usuarioDTO.getSegundoApellidoUsuario());
        usuario.setNombreArchivoFotoExtensionoFormatoUsuario(usuarioDTO.getNombreArchivoFotoExtensionoFormatoUsuario());
        usuario.setFechaHMSNacimientoUsuario(usuarioDTO.getFechaHMSNacimientoUsuario());
        usuario.setSexoUsuario(usuarioDTO.getSexoUsuario().toUpperCase());
        usuario.setDireccionUsuario(usuarioDTO.getDireccionUsuario().toUpperCase());
        usuario.setTelefonoUsuario(usuarioDTO.getTelefonoUsuario());
        usuario.setMovilUsuario(usuarioDTO.getMovilUsuario());
        usuario.setCorreoElectronicoPersonalUsuario(usuarioDTO.getCorreoElectronicoPersonalUsuario());
        usuario.setCorreoElectronicoInstitucionalUsuario(usuarioDTO.getCorreoElectronicoInstitucionalUsuario());
        usuario.setPaisOrigenUsuario(usuarioDTO.getPaisOrigenUsuario().toUpperCase());
        usuario.setDepartamentooEstadoOrigenUsuario(usuarioDTO.getDepartamentooEstadoOrigenUsuario().toUpperCase());
        usuario.setCiudadOrigenUsuario(usuarioDTO.getCiudadOrigenUsuario().toUpperCase());
        
        //MAPEAR TIPO DE USUARIO RELACIONADO.
        if (usuarioDTO.getTipoUsuarioDTO() != null && usuarioDTO.getTipoUsuarioDTO().getIdTipoUsuario() != null) {
           Optional<TipoUsuario> tipoUsuarioFk = tipoUsuarioRepository.findByIdTipoUsuario(usuarioDTO.getTipoUsuarioDTO().getIdTipoUsuario());
           tipoUsuarioFk.ifPresent(usuario::setTipoUsuario);
        }
        
        usuario.setFechaHMSIngresoUsuario(usuarioDTO.getFechaHMSIngresoUsuario());
        usuario.setFechaHMSModificacionUsuario(usuarioDTO.getFechaHMSModificacionUsuario());
        usuario.setEstadoUsuario(usuarioDTO.getEstadoUsuario().toUpperCase());
        
        return usuario;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param usuario
    * Recibe un objeto usuario para crear un DTO.
    * @return usuarioDTO
    */
    public UsuarioDTO usuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNicknameUsuario(usuario.getNicknameUsuario());
        usuarioDTO.setPasswordUsuario(usuario.getPasswordUsuario());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (usuario.getTipoDocumentoIdentificacion() != null && usuario.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(usuario.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(tipoDocumentoIdentificacion -> usuarioDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacion)));
        }
        
        usuarioDTO.setNumeroDocumentoIdentificacionUsuario(usuario.getNumeroDocumentoIdentificacionUsuario());
        usuarioDTO.setLugarExpedicionDocumentoIdentificacionUsuario(usuario.getLugarExpedicionDocumentoIdentificacionUsuario().toUpperCase());
        usuarioDTO.setNombresUsuario(usuario.getNombresUsuario().toUpperCase());
        usuarioDTO.setPrimerApellidoUsuario(usuario.getPrimerApellidoUsuario().toUpperCase());
        usuarioDTO.setSegundoApellidoUsuario(usuario.getSegundoApellidoUsuario());
        usuarioDTO.setNombreArchivoFotoExtensionoFormatoUsuario(usuario.getNombreArchivoFotoExtensionoFormatoUsuario());
        usuarioDTO.setFechaHMSNacimientoUsuario(usuario.getFechaHMSNacimientoUsuario());
        usuarioDTO.setSexoUsuario(usuario.getSexoUsuario().toUpperCase());
        usuarioDTO.setDireccionUsuario(usuario.getDireccionUsuario().toUpperCase());
        usuarioDTO.setTelefonoUsuario(usuario.getTelefonoUsuario());
        usuarioDTO.setMovilUsuario(usuario.getMovilUsuario());
        usuarioDTO.setCorreoElectronicoPersonalUsuario(usuario.getCorreoElectronicoPersonalUsuario());
        usuarioDTO.setCorreoElectronicoInstitucionalUsuario(usuario.getCorreoElectronicoInstitucionalUsuario());
        usuarioDTO.setPaisOrigenUsuario(usuario.getPaisOrigenUsuario().toUpperCase());
        usuarioDTO.setDepartamentooEstadoOrigenUsuario(usuario.getDepartamentooEstadoOrigenUsuario().toUpperCase());
        usuarioDTO.setCiudadOrigenUsuario(usuario.getCiudadOrigenUsuario().toUpperCase());
        
        //MAPEAR TIPO USUARIO RELACIONADO.
        if (usuario.getTipoUsuario() != null && usuario.getTipoUsuario().getIdTipoUsuario() != null) {
           Optional<TipoUsuario> tipoUsuarioFk = tipoUsuarioRepository.findByIdTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario());
           tipoUsuarioFk.ifPresent(tipoUsuario -> usuarioDTO.setTipoUsuarioDTO(tipoUsuarioDAO.tipoUsuarioDTO(tipoUsuario)));
        }
        
        usuarioDTO.setFechaHMSIngresoUsuario(usuario.getFechaHMSIngresoUsuario());
        usuarioDTO.setFechaHMSModificacionUsuario(usuario.getFechaHMSModificacionUsuario());
        usuarioDTO.setEstadoUsuario(usuario.getEstadoUsuario().toUpperCase());
        
        return usuarioDTO;
    }
}
