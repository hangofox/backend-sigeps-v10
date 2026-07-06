//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan los repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private UsuarioRepository usuarioRepository;
    
    //CARGAR USUARIO POR NICKNAME:
    @Override//SOBREESCRIBIMOS EL METODO DE CARGAR USUARIO POR NICKNAME.
    public UserDetails loadUserByUsername(String nicknameUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNicknameUsuario(nicknameUsuario);
        
        if (usuario == null) {
           throw new UsernameNotFoundException("El usuario con nickname: " + nicknameUsuario + " no existe.");
        }
        
        return new UserDetailsImpl(usuario);
    }
}
