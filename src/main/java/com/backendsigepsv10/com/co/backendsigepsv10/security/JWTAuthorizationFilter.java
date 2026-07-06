//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Este método se ejecuta automáticamente para cada solicitud HTTP que llega al servidor.
* Se encarga de:
* - Verificar si el encabezado "Authorization" contiene un token válido.
* - Extraer el token JWT si está presente.
* - Validar y autenticar al usuario a partir del token.
* - Establecer la autenticación en el contexto de seguridad de Spring.
* - Permitir que la solicitud continúe su curso (pase a otros filtros o al controlador).
*
* @param request       Objeto que representa la solicitud HTTP entrante.
* @param response      Objeto que representa la respuesta HTTP que se enviará.
* @param filterChain   Cadena de filtros de seguridad de Spring. Se usa para continuar el flujo.
*/
@Component//DECLARACIÓN DE COMPONENT PARA INYECTAR LA CONFIGURACIÓN DE SEGURIDAD.
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    
    //VERIFICAR EL ENCABEZADO DE LA SOLICITUD.
    @Override//SOBREESCRIBIMOS EL METODO DE VERIFICAR EL ENCABEZADO DE LA SOLICITUD.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //EXTRAE EL VALOR DEL ENCABEZADO "Authorization" DE LA SOLICITUD HTTP:
        String bearerToken = request.getHeader("Authorization");
        
        if ((!(bearerToken==null))&&(bearerToken.startsWith("Bearer ")==true)) {
           String token = bearerToken.replace("Bearer ", "").trim();//ELIMINAMOS LOS ESPACIOS EN AMBOS EXTREMOS.
           UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
           SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        
        //ESTE PASO ES OBLIGATORIO. CONTINÚA CON EL RESTO DE FILTROS Y EVENTUALMENTE CON EL CONTROLADOR:
        filterChain.doFilter(request, response);
    }
}
