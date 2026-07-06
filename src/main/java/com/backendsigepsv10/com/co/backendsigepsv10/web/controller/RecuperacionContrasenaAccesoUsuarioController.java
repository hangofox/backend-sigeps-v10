//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RecuperacionContrasenaAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.RecuperacionContrasenaAccesoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
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
public class RecuperacionContrasenaAccesoUsuarioController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private RecuperacionContrasenaAccesoUsuarioService recuperacionContrasenaAccesoUsuarioService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/recuperacionesContrasenasAccesosUsuarios/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idRecuperacionContrasenaAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String estadoUsoCodigoActivacionContrasenaAccesoUsuario) {
        return new ResponseEntity<>(recuperacionContrasenaAccesoUsuarioService.contarTotalRegistros(idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/recuperacionesContrasenasAccesosUsuarios/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<RecuperacionContrasenaAccesoUsuarioDTO>> listarRecuperacionesContrasenasAccesosUsuariosLista(
            @RequestParam(required = false) Long idRecuperacionContrasenaAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String estadoUsoCodigoActivacionContrasenaAccesoUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(recuperacionContrasenaAccesoUsuarioService.listarRecuperacionesContrasenasAccesosUsuarios(idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/recuperacionesContrasenasAccesosUsuarios/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<RecuperacionContrasenaAccesoUsuarioDTO>> listarRecuperacionesContrasenasAccesosUsuarios(
            @RequestParam(required = false) Long idRecuperacionContrasenaAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String estadoUsoCodigoActivacionContrasenaAccesoUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(recuperacionContrasenaAccesoUsuarioService.listarRecuperacionesContrasenasAccesosUsuariosPag(pageable, idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/recuperacionesContrasenasAccesosUsuarios")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearRecuperacionContrasenaAccesoUsuario(@RequestBody RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO) {
        return recuperacionContrasenaAccesoUsuarioService.crearRecuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/recuperacionesContrasenasAccesosUsuarios/{idRecuperacionContrasenaAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarRecuperacionContrasenaAccesoUsuarioporId(@PathVariable Long idRecuperacionContrasenaAccesoUsuario) {
        return recuperacionContrasenaAccesoUsuarioService.consultarRecuperacionContrasenaAccesoUsuarioporId(idRecuperacionContrasenaAccesoUsuario);
    }
    
    //LEER CONSULTA DE REGISTRO POR CÓDIGO DE ACTIVACIÓN:
    @GetMapping("/recuperacionesContrasenasAccesosUsuarios/codigoActivacion/{codigoActivacionRecuperacionContrasenaAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarRecuperacionContrasenaAccesoUsuarioporCodigoActivacion(@PathVariable String codigoActivacionRecuperacionContrasenaAccesoUsuario) {
        return recuperacionContrasenaAccesoUsuarioService.consultarRecuperacionContrasenaAccesoUsuarioporCodigoActivacion(codigoActivacionRecuperacionContrasenaAccesoUsuario);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/recuperacionesContrasenasAccesosUsuarios")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarRecuperacionContrasenaAccesoUsuario(@RequestBody RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO) {
        return recuperacionContrasenaAccesoUsuarioService.actualizarRecuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO);
    }
    
    //ACTUALIZAR ESTADOS DE USOS DE CÓDIGOS DE ACTIVACIONES DE CONTRASEÑAS DE ACCESOS DE USUARIOS:
    @PutMapping("/recuperacionesContrasenasAccesosUsuarios/actualizarEstados/{fechaHMSExpCodActivContrasenaAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD ACTUALIZAR ESTADOS.
    public RespuestaDTO actualizarEstadosUsosCodigosActivacionesContrasenasAccesosUsuarios(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") Date fechaHMSExpCodActivContrasenaAccesoUsuario) {
        return recuperacionContrasenaAccesoUsuarioService.actualizarEstadosUsosCodigosActivacionesContrasenasAccesosUsuarios(fechaHMSExpCodActivContrasenaAccesoUsuario);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/recuperacionesContrasenasAccesosUsuarios/{idRecuperacionContrasenaAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarRecuperacionContrasenaAccesoUsuario(@PathVariable Long idRecuperacionContrasenaAccesoUsuario) {
        return recuperacionContrasenaAccesoUsuarioService.eliminarRecuperacionContrasenaAccesoUsuario(idRecuperacionContrasenaAccesoUsuario);
    }
    
    //VACIAR REGISTROS POR ID DE USUARIO:
    @DeleteMapping("/recuperacionesContrasenasAccesosUsuarios/vaciar/{idUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD VACIAR REGISTROS POR USUARIO.
    public RespuestaDTO vaciarRecuperacionesContrasenasAccesosUsuariosporIdUsuario(@PathVariable Long idUsuario) {
        return recuperacionContrasenaAccesoUsuarioService.vaciarRecuperacionesContrasenasAccesosUsuariosporIdUsuario(idUsuario);
    }
}
