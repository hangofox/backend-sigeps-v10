//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/07/2026.
* DTO liviano y público (sin autenticación) con el ÚNICO dato de ParametrosSistema que el frontend necesita
* para calcular la fecha de expiración del código de recuperación de contraseña. Deliberadamente NO incluye
* ninguno de los campos SMTP (host, puerto, protocolos, usuario, password, auth) ni la plantilla del correo
* (asunto/cuerpo), que EmailServiceImpl consulta y arma internamente y nunca deben salir hacia el navegador.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
public class ParametrosSistemaRecuperacionContrasenaDTO {

    private Long tiempoMinutosValidezCodigoActivacionSistema;
}
