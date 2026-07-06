//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
*
* Este filtro personalizado extiende de `UsernamePasswordAuthenticationFilter` y se encarga de:
* 1. Recibir las credenciales de usuario desde una petición de login (JSON).
* 2. Validar dichas credenciales con el `AuthenticationManager`.
* 3. Si las credenciales son válidas, generar y devolver un JWT como respuesta al cliente.
*/
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    //REALIZAR AUTENTICACIÓN DEL USUARIO.
    //NOTA: Se leen las credenciales (usuario y contraseña) del cuerpo de la petición.
    @Override//SOBREESCRIBIMOS EL METODO DE REALZIAR AUTENTICACIÓN DEL USUARIO.
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        
        try {
          //CONVIERTE EL CUERPO JSON DE LA PETICIÓN EN UN OBJETO AuthCredentials:
          authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        }
        catch (IOException e) {
          //EN CASO DE ERROR, LAS CREDENCIALES PERMANECERÁN VACÍAS (AUNQUE PODRÍA MANEJARSE MEJOR).
        }
        
        //CREA EL TOKEN DE AUTENTICACIÓN CON EL USUARIO Y CONTRASEÑA:
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getNicknameUsuario(),
                authCredentials.getPasswordUsuario(),
                Collections.emptyList()
        );
        
        //RETORNA EL RESULTADO DE AUTENTICAR LAS CREDENCIALES CON EL AuthenticationManager:
        return getAuthenticationManager().authenticate(usernamePAT);
    }
    
    //ENVIAR EL TOKEN DE AUTORIZACIÓN GENERADO CUANDO SE HA AUTENTICADO.
    @Override//SOBREESCRIBIMOS EL METODO DE ENVIAR EL TOKEN DE AUTORIZACIÓN GENERADO CUANDO SE HA AUTENTICADO.
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //SE OBTIENE EL USUARIO AUTENTICADO DESDE EL RESULTADO:
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        
        try {
            //SE GENERA EL TOKEN JWT CON LA INFORMACIÓN DEL USUARIO:
            String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
            //CONFIGURACIÓN DE LA RESPUESTA PARA DEVOLVER JSON:
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{");
            response.getWriter().write("\"tokenAutorizacion\":\"" + token + "\"" + ",");
            response.getWriter().write(" \"mensaje\":\"" + MensajesConstantes.MSG_TOKEN_AUTORIZACION_USUARIO_EXITO + "\"");
            response.getWriter().write("}");
            response.getWriter().flush();
        } catch (Exception e) {
            //EN CASO DE ERROR AL GENERAR EL TOKEN, SE RESPONDE CON UN MENSAJE DE ERROR EN JSON:
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{");
            response.getWriter().write("\"tokenAutorizacion\":\"" + null + "\",");
            response.getWriter().write("\"mensaje\":\"" + MensajesConstantes.MSG_ERROR_GENERACION_TOKEN_AUTORIZACION_USUARIO + "\"");
            response.getWriter().write("}");
        }
    }
}
