//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ParametrosSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface ParametrosSistemaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idParametrosSistema, String keyword);
    List<ParametrosSistemaDTO> listarParametrosSistemas(Long idParametrosSistema, String keyword, String orderBy, String orderMode);
    Slice<ParametrosSistemaDTO> listarParametrosSistemasPag(Pageable pageable, Long idParametrosSistema, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearParametrosSistema(ParametrosSistemaDTO parametrosSistemaDTO);
    RespuestaDTO consultarParametrosSistemaporId(Long idParametrosSistema);
    RespuestaDTO consultarParametrosSistemaporSmtpHost(String smtpHost);
    RespuestaDTO actualizarParametrosSistema(ParametrosSistemaDTO parametrosSistemaDTO);
    RespuestaDTO eliminarParametrosSistema(Long idParametrosSistema);
    //ENDPOINT PÚBLICO (SIN AUTENTICACIÓN) PARA EL FLUJO DE RECUPERACIÓN DE CONTRASEÑA: DEVUELVE SOLO EL TIEMPO
    //DE EXPIRACIÓN DEL CÓDIGO, NUNCA LOS DATOS SMTP NI LA PLANTILLA DEL CORREO.
    RespuestaDTO consultarDatosPublicosRecuperacionContrasena();
}
