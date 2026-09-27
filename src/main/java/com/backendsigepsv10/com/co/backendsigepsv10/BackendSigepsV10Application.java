//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10;

//IMPORTACIÓN DE LIBRERIAS:
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración de la clase principal.
*/
//DECLARACIÓN DE LA CLASE PRINCIPAL:
@SpringBootApplication
public class BackendSigepsV10Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(BackendSigepsV10Application.class, args);
	}

	//NECESARIO PARA QUE EL WAR ARRANQUE DENTRO DE UN TOMCAT EXTERNO (SIN ESTO EL SERVIDOR NO SABE
	//COMO INICIALIZAR EL CONTEXTO DE SPRING AL DESPLEGAR EL .war, YA QUE NO SE EJECUTA EL main()).
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendSigepsV10Application.class);
	}
}
