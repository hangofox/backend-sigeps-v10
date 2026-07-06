//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.security;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método autenticación de credenciales.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA AUTENTICACIÓN DE CREDENCIALES.
public class AuthCredentials {
    
    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE USUARIOS PARA LAS CREDENCIALES DE AUTENTICACIÓN:
    private String nicknameUsuario;
    private String passwordUsuario;
}
