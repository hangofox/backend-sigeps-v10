//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ParametrosSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ParametrosSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class ParametrosSistemaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private ParametrosSistemaService parametrosSistemaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTADORES DE REGISTROS FILTRADOS.
    //ENDPOINT ÚNICO PARA CONTAR REGISTROS CON QUERY PARAMS:
    @GetMapping("/parametrosSistema/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idParametrosSistema,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(parametrosSistemaService.contarTotalRegistros(idParametrosSistema, keyword), HttpStatus.OK);
    }
    
    //LISTADO DE REGISTROS FILTRADOS SIN PAGINACIÓN.
    //ENDPOINT PARA LISTAR TODOS LOS PARAMETROS DEL SISTEMA SIN PAGINACIÓN (PARA SELECTS DEL FRONTEND):
    @GetMapping("/parametrosSistema/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<ParametrosSistemaDTO>> listarParametrosSistemasLista(
            @RequestParam(required = false) Long idParametrosSistema,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(parametrosSistemaService.listarParametrosSistemas(idParametrosSistema, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTADO DE REGISTROS FILTRADOS CON PAGINACIÓN.
    //ENDPOINT ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR PARAMETROS DEL SISTEMA CON QUERY PARAMS:
    @GetMapping("/parametrosSistema/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<ParametrosSistemaDTO>> listarParametrosSistemasListaPag(
            @RequestParam(required = false) Long idParametrosSistema,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(parametrosSistemaService.listarParametrosSistemasPag(pageable, idParametrosSistema, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/parametrosSistema")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearParametrosSistema(@RequestBody ParametrosSistemaDTO parametrosSistemaDTO){
        System.out.println(parametrosSistemaDTO);
        return parametrosSistemaService.crearParametrosSistema(parametrosSistemaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/parametrosSistema/{idParametrosSistema}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarParametrosSistemabyId(@PathVariable Long idParametrosSistema){
        return parametrosSistemaService.consultarParametrosSistemaporId(idParametrosSistema);
    }
    
    //LEER CONSULTA DE REGISTRO POR SMTP HOST:
    @GetMapping("/parametrosSistema/smtpHost/{smtpHost}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarParametrosSistemabySmtpHost(@PathVariable String smtpHost){
        return parametrosSistemaService.consultarParametrosSistemaporSmtpHost(smtpHost);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/parametrosSistema")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarParametrosSistema(@RequestBody ParametrosSistemaDTO parametrosSistemaDTO){
        return parametrosSistemaService.actualizarParametrosSistema(parametrosSistemaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/parametrosSistema/{idParametrosSistema}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarParametrosSistema(@PathVariable Long idParametrosSistema){
        return parametrosSistemaService.eliminarParametrosSistema(idParametrosSistema);
    }

    //ENDPOINT PÚBLICO (VER WebSecurityConfig, SE AGREGA A permitAll) PARA EL FLUJO DE RECUPERACIÓN DE CONTRASEÑA
    //ANTES DE INICIAR SESIÓN: DEVUELVE SOLO EL TIEMPO DE EXPIRACIÓN DEL CÓDIGO, NUNCA LOS DATOS SMTP NI LA
    //PLANTILLA DEL CORREO (ESOS LOS ARMA EmailServiceImpl INTERNAMENTE):
    @GetMapping("/parametrosSistema/datosPublicosRecuperacionContrasena")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarDatosPublicosRecuperacionContrasena(){
        return parametrosSistemaService.consultarDatosPublicosRecuperacionContrasena();
    }
}
