//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.*;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Esta clase se encarga de la gestión de tokens JWT en el sistema de seguridad.
* Sus responsabilidades principales son:
* - Crear tokens JWT firmados con una clave secreta.
* - Validar tokens entrantes y extraer información de autenticación.
* - Integrarse con el contexto de seguridad de Spring.
*
* Nota: Aunque este componente no implementa directamente un filtro, forma parte esencial
* de la infraestructura para autenticar solicitudes basadas en tokens.
*/
@Component//DECLARACIÓN DE COMPONENT PARA INYECTAR LA CONFIGURACIÓN DE SEGURIDAD.
public class TokenUtils {
    
    //DECLARAMOS Y ASIGNAMOS UN CÓDIGO DE ACCESO BASE PARA FIRMAR EL TOKEN JWT. ESTA CLAVE DE MANTENERSE SEGURA.
    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";
    
    //DECLARAMOS Y ASIGNAMOS EL TIEMPO DE VALIDEZ DEL TOKEN EN SEGUNDOS (30 DÍAS). EQUIVALE A 2,592,000 SEGUNDOS:
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
    
    //MÉTOD ESTÁTICO PARA GENERAR UN JWT CON BASE EN EL NOMBRE Y NICKNAME DEL USUARIO.
    public static String createToken(String nombre, String nicknameUsuario) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;//ASIGNAMOS UNA EXPIRACIÓN DEL TOKEN DE 30 DIAS.
        
        //TIEMPO DE VALIDEZ DEL TOKEN EN SEGUNDOS (30 DÍAS). EQUIVALE A 2,592,000 SEGUNDOS:
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);//ASIGNAMOS LA FECHA DE EXPIRACIÓN DEL TOKEN.
        
        //CLAVE SECRETA UTILIZADA PARA FIRMAR EL TOKEN JWT:
        SecretKey secretKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());
        
        //MAPEO CON DATOS EXTRA QUE SE INCLUIRÁN EN EL CUERPO DEL TOKEN (PAYLOAD):
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);//SE AÑADE EL NOMBRE COMO INFORMACIÓN ADICIONAL.
        
        //SE CONSTRUYE EL TOKEN UTILIZANDO EL BUILDER DE JJWT 0.12.x:
        return Jwts.builder()
                   .subject(nicknameUsuario)//SE ESTABLECE EL 'SUBJECT' COMO EL NOMBRE DE USUARIO.
                   .expiration(expirationDate)//SE DEFINE LA FECHA DE EXPIRACIÓN.
                   .claims(extra)//SE AÑADEN LOS DATOS ADICIONALES AL TOKEN.
                   .signWith(secretKey)//SE FIRMA EL TOKEN CON LA CLAVE SECRETA.
                   .compact();//SE GENERA EL TOKEN EN FORMATO STRING.
    }
    
    //MÉTODO QUE VALIDA UN TOKEN RECIBIDO Y EXTRAE EL NOMBRE DE USUARIO (SUBJECT) SI ES VALIDO.
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            //CLAVE SECRETA UTILIZADA PARA VERIFICAR LA FIRMA DEL TOKEN:
            SecretKey secretKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());
            
            //SE INTENTA PARSEAR Y VALIDAR EL TOKEN JWT CON LA CLAVE SECRETA (API jjwt 0.12.x):
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)//SE CONFIGURA LA CLAVE SECRETA PARA VERIFICAR LA FIRMA.
                    .build()
                    .parseSignedClaims(token)//SE PARSEA EL TOKEN.
                    .getPayload();//SE OBTIENE EL CUERPO DEL TOKEN (PAYLOAD).
            
            //SE OBTIENE EL NOMBRE DE USUARIO (SUBJECT) DEL TOKEN:
            String nicknameUsuario = claims.getSubject();
            
            //SE RETORNA UN OBJETO DE AUTENTICACIÓN SIN ROLES (VACÍO), YA QUE NO SE MANEJAN AQUÍ:
            return new UsernamePasswordAuthenticationToken(nicknameUsuario, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
