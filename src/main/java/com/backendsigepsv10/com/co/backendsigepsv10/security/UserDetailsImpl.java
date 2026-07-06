//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Implementación personalizada de la interfaz UserDetails de Spring Security.
*
* Esta clase adapta la entidad `Usuario` a un formato compatible con el sistema
* de autenticación de Spring Security. Se utiliza en el proceso de autenticación
* para extraer datos del usuario, como nombre de usuario, contraseña y estado.
*
* - Implementa todos los métodos requeridos por la interfaz UserDetails.
* - No asigna roles o permisos explícitos, retorna una lista vacía de autoridades.
*/
@AllArgsConstructor//GENERA AUTOMÁTICAMENTE UN CONSTRUCTOR CON EL PARÁMETRO FINAL 'usuario'.
public class UserDetailsImpl implements UserDetails {
    
    //DECLARACIÓN DE LA ENTIDAD DE USUARIO QUE REPRESENTA AL USUARIO AUTENTICADO:
    private final Usuario usuario;
    
    /**
    * Devuelve las autoridades o roles asignados al usuario.
    * En este caso, se retorna una lista vacía ya que no se manejan roles aquí.
    *
    * @return Colección vacía de GrantedAuthority.
    */
    @Override//SOBREESCRIBIMOS Y EXTENDEMOS EL METODO GET DE AUTORIZACIONES.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    
    /**
    * Devuelve la contraseña del usuario.
    *
    * @return Contraseña extraída del objeto Usuario.
    */
    @Override//SOBREESCRIBIMOS EL METODO GET.
    public String getPassword() {
        return usuario.getPasswordUsuario();
    }
    
    /**
    * Devuelve el nombre de usuario (nickname) utilizado para autenticación.
    *
    * @return Nickname del usuario.
    */
    @Override//SOBREESCRIBIMOS EL METODO GET.
    public String getUsername() {
        return usuario.getNicknameUsuario();
    }
    
    /**
    * Indica si la cuenta del usuario no ha expirado.
    * Se retorna true, indicando que siempre está activa en este sistema.
    *
    * @return true.
    */
    @Override//SOBREESCRIBIMOS EL METODO IS.
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
    * Indica si la cuenta del usuario no está bloqueada.
    * Se retorna true, indicando que las cuentas no se bloquean por lógica propia.
    *
    * @return true.
    */
    @Override//SOBREESCRIBIMOS EL METODO IS.
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
    * Indica si las credenciales del usuario (contraseña) no han expirado.
    * Se retorna true, indicando que no se manejan expiraciones de contraseñas.
    *
    * @return true.
    */
    @Override//SOBREESCRIBIMOS EL METODO IS.
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
    * Indica si el usuario está habilitado.
    * Se valida si el estado del usuario es "ACTIVO".
    *
    * @return true si el estado es "ACTIVO", sin importar mayúsculas/minúsculas.
    */
    @Override//SOBREESCRIBIMOS EL METODO IS.
    public boolean isEnabled() {
        return "ACTIVO".equalsIgnoreCase(usuario.getEstadoUsuario());
    }
    
    /**
    * Método adicional que no forma parte de UserDetails.
    * Devuelve el nombre completo del usuario para ser utilizado en otras partes del sistema.
    *
    * @return Nombres del usuario.
    */
    public String getNombre() {//ESTE METODO NO SE SOBREESCRIBE.
        return usuario.getNombresUsuario();
    }
}
