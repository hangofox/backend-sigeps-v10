//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AuditoriaSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.AuditoriaSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class AuditoriaSistemaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private AuditoriaSistemaService auditoriaSistemaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/auditoriasSistema/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idAuditoriaSistema,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(auditoriaSistemaService.contarTotalRegistros(idAuditoriaSistema, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/auditoriasSistema/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<AuditoriaSistemaDTO>> listarAuditoriasSistemaLista(
            @RequestParam(required = false) Long idAuditoriaSistema,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(auditoriaSistemaService.listarAuditoriasSistema(idAuditoriaSistema, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/auditoriasSistema/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<AuditoriaSistemaDTO>> listarAuditoriasSistemaPag(
            @RequestParam(required = false) Long idAuditoriaSistema,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(auditoriaSistemaService.listarAuditoriasSistemaPag(pageable, idAuditoriaSistema, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/auditoriasSistema")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearAuditoriaSistema(@RequestBody AuditoriaSistemaDTO auditoriaSistemaDTO){
        System.out.println(auditoriaSistemaDTO);
        return auditoriaSistemaService.crearAuditoriaSistema(auditoriaSistemaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/auditoriasSistema/{idAuditoriaSistema}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarAuditoriaSistemabyId(@PathVariable Long idAuditoriaSistema){
        return auditoriaSistemaService.consultarAuditoriaSistemabyId(idAuditoriaSistema);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, FECHA, HORA, MINUTO Y SEGUNDO DE LA AUDITORIA DEL SISTEMA Y ACCIÓN DE USUARIO EN SISTEMA:
    @GetMapping("/auditoriasSistema/idUsuario/{idUsuario}/fechaHMSAuditoriaSistema/{fechaHMSAuditoriaSistema}/accionUsuario/{accionUsuarioSistema}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarAuditoriaSistemaporIdUsuarioyFechayAccionUsuarioSistema(@PathVariable Long idUsuario, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") Date fechaHMSAuditoriaSistema, @PathVariable String accionUsuarioSistema){
        return auditoriaSistemaService.consultarAuditoriaSistemaporIdUsuarioyFechayAccionUsuarioSistema(idUsuario, fechaHMSAuditoriaSistema, accionUsuarioSistema);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/auditoriasSistema")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAuditoriaSistema(@RequestBody AuditoriaSistemaDTO auditoriaSistemaDTO){
        return auditoriaSistemaService.actualizarAuditoriaSistema(auditoriaSistemaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/auditoriasSistema/{idAuditoriaSistema}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAuditoriaSistema(@PathVariable Long idAuditoriaSistema){
        return auditoriaSistemaService.eliminarAuditoriaSistema(idAuditoriaSistema);
    }
}
