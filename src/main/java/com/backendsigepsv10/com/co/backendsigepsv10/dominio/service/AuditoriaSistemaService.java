//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AuditoriaSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.Date;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface AuditoriaSistemaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idAuditoriaSistema, String keyword);
    List<AuditoriaSistemaDTO> listarAuditoriasSistema(Long idAuditoriaSistema, String keyword, String orderBy, String orderMode);
    Slice<AuditoriaSistemaDTO> listarAuditoriasSistemaPag(Pageable pageable, Long idAuditoriaSistema, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearAuditoriaSistema(AuditoriaSistemaDTO auditoriaSistemaDTO);
    RespuestaDTO consultarAuditoriaSistemabyId(Long idAuditoriaSistema);
    RespuestaDTO consultarAuditoriaSistemaporIdUsuarioyFechayAccionUsuarioSistema(Long idUsuario, Date fechaHMSAuditoriaSistema, String accionUsuarioSistema);
    RespuestaDTO actualizarAuditoriaSistema(AuditoriaSistemaDTO auditoriaSistemaDTO);
    RespuestaDTO eliminarAuditoriaSistema(Long idAuditoriaSistema);
}
