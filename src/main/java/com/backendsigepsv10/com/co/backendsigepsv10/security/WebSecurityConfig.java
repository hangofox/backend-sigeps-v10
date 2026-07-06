//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Clase de configuración principal para establecer las reglas de seguridad
* en la aplicación Spring Boot utilizando Spring Security 6.
*
* Esta clase:
* - Define los endpoints públicos y protegidos.
* - Habilita los filtros personalizados para autenticación y autorización con JWT.
* - Establece una política de sesión sin estado (stateless).
* - Define el manejador de autenticación y el codificador de contraseñas.
*/
@Configuration//DECLARACIÓN DE COMPONENT PARA INYECTAR LA CONFIGURACIÓN DE SEGURIDAD.
@EnableWebSecurity//HABILITA LA CONFIGURACIÓN DE SEGURIDAD WEB DE SPRING SECURITY.
@AllArgsConstructor//GENERA AUTOMÁTICAMENTE UN CONSTRUCTOR CON LOS ARGUMENTOS NECESARIOS.
public class WebSecurityConfig {
    
    //SERVICIO PERSONALIZADO PARA OBTENER LOS DETALLES DEL USUARIO DESDE LA BASE DE DATOS:
    private final UserDetailsService userDetailsService;
    
    //FILTRO PERSONALIZADO PARA VERIFICAR LA VALIDEZ DEL TOKEN JWT EN CADA SOLICITUD.
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    //PERMITE LA CONFIGURACIÓN DE SEGURIDAD HTTP.
    //NOTA: Se inyecta el manejador de autenticación.
    @Bean//DEVUELVE UN OBJETO QUE DEBE SER REGISTRADO EN EL CONTENEDOR DE SPRING.
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        
        //SE CREA EL FILTRO DE AUTENTICACIÓN Y SE CONFIGURA PARA INTERCEPTAR EL ENDPOINT "/login":
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        
        //ESTE BLOQUE CONSTRUYE Y DEVUELVE LA CADENA DE FILTROS DE SEGURIDAD (SecurityFilterChain) QUE DEFINE:
        //-Qué endpoints son públicos y cuáles requieren autenticación.
        //-Que no se usará protección CSRF (porque se usa JWT).
        //-Que se trabajará sin sesiones (stateless).
        //-Que se usarán filtros personalizados para autenticación y autorización con JWT.
        return http
                .csrf(AbstractHttpConfigurer::disable)//SE DESACTIVA LA PROTECCIÓN CSRF PORQUE SE TRABAJA CON JWT.
                .authorizeHttpRequests(auth -> auth
                        //ENDPOINTS PÚBLICOS AUTORIZADOS SIN AUTENTICACIÓN:
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/usuarios/recuperacionContrasena/**").permitAll()
                        .requestMatchers("/usuarios/numeroDocumento/**").permitAll()
                        //ENDPOINTS PÚBLICOS DEL FLUJO DE RECUPERACIÓN DE CONTRASEÑA (ANTES DE INICIAR SESIÓN):
                        .requestMatchers(HttpMethod.PUT, "/usuarios/actualizarPassword/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parametrosSistema/datosPublicosRecuperacionContrasena").permitAll()
                        .requestMatchers(HttpMethod.POST, "/recuperacionesContrasenasAccesosUsuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/recuperacionesContrasenasAccesosUsuarios/codigoActivacion/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/recuperacionesContrasenasAccesosUsuarios").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/recuperacionesContrasenasAccesosUsuarios/actualizarEstados/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/emails").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/recuperacionesContrasenasAccesosUsuarios/vaciar/**").permitAll()
                        //ENDPOINTS DE DOCUMENTACIÓN SWAGGER/OPENAPI:
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/v3/api-docs").permitAll()
                        //CUALQUIER OTRA PETICIÓN REQUIERE AUTENTICACIÓN:
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//SIN SESIONES EN EL SERVIDOR (STATELESS, IDEAL PARA JWT).
                )
                .addFilter(jwtAuthenticationFilter)//AGREGA EL FILTRO PERSONALIZADO DE AUTENTICACIÓN JWT (LOGIN).
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)//AGREGA EL FILTRO DE AUTORIZACIÓN JWT ANTES DEL FILTRO POR DEFECTO DE SPRING.
                .build();//CONSTRUYE Y RETORNA LA CADENA DE FILTROS CONFIGURADA.
    }
    
    /**
    * Reemplazo de BCryptPasswordEncoder por un PasswordEncoder personalizado.
    * Este encoder no encripta nada. Compara directamente lo que llega (desde Postman)
    * contra la contraseña almacenada en la base de datos.
    */
    @Bean//DEVUELVE UN OBJETO QUE DEBE SER REGISTRADO EN EL CONTENEDOR DE SPRING.
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override//SOBRESCRIBIMOS EL METODO DE CODIFICACIÓN DEL PASSWORD.
            public String encode(CharSequence rawPassword) {
                //NO SE CODIFICA, SE RETORNA TAL CUAL.
                return rawPassword.toString();
            }
            
            @Override//SOBRESCRIBIMOS EL METODO DE COMPARACIÓN DIRECTA SIN CODIFICACIÓN.
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                //COMPARACIÓN DIRECTA SIN CODIFICACIÓN ADICIONAL.
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
    
    //CONFIGURA Y EXPONE EL MANEJADOR DE AUTENTICACIÓN:
    //NOTA: Se enlaza el `UserDetailsService` y el `PasswordEncoder` definidos previamente.
    @Bean//DEVUELVE UN OBJETO QUE DEBE SER REGISTRADO EN EL CONTENEDOR DE SPRING.
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authManagerBuilder.build();
    }
}
