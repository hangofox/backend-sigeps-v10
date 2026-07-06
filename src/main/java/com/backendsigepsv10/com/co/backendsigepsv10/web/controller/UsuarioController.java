//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.UsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.UsuarioService;
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
* @Since 09/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class UsuarioController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private UsuarioService usuarioService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/usuarios/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionUsuario,
            @RequestParam(required = false) String nombresUsuario,
            @RequestParam(required = false) String primerApellidoUsuario,
            @RequestParam(required = false) String nombreTipoUsuario,
            @RequestParam(required = false) String estadoUsuario) {
        return new ResponseEntity<>(usuarioService.contarTotalRegistros(idUsuario, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionUsuario, nombresUsuario, primerApellidoUsuario, nombreTipoUsuario, estadoUsuario), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/usuarios/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosLista(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionUsuario,
            @RequestParam(required = false) String nombresUsuario,
            @RequestParam(required = false) String primerApellidoUsuario,
            @RequestParam(required = false) String nombreTipoUsuario,
            @RequestParam(required = false) String estadoUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(usuarioService.listarUsuarios(idUsuario, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionUsuario, nombresUsuario, primerApellidoUsuario, nombreTipoUsuario, estadoUsuario, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/usuarios/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<UsuarioDTO>> listarUsuarios(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionUsuario,
            @RequestParam(required = false) String nombresUsuario,
            @RequestParam(required = false) String primerApellidoUsuario,
            @RequestParam(required = false) String nombreTipoUsuario,
            @RequestParam(required = false) String estadoUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(usuarioService.listarUsuariosPag(pageable, idUsuario, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionUsuario, nombresUsuario, primerApellidoUsuario, nombreTipoUsuario, estadoUsuario, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/usuarios")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        System.out.println(usuarioDTO);
        return usuarioService.crearUsuario(usuarioDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/usuarios/{idUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarUsuarioporId(@PathVariable Long idUsuario){
        return usuarioService.consultarUsuarioporId(idUsuario);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @GetMapping("/usuarios/numeroDocumentoIdentificacion/{numeroDocumentoIdentificacionUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarUsuarioporNumeroDocumentoIdentificacion(@PathVariable String numeroDocumentoIdentificacionUsuario){
        return usuarioService.consultarUsuarioporNumeroDocumentoIdentificacion(numeroDocumentoIdentificacionUsuario);
    }
    
    //LEER CONSULTA DE REGISTRO PARA RECUPERACIÓN DE CONTRASEÑA POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @GetMapping("/usuarios/recuperacionContrasena/{numeroDocumentoIdentificacionUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarUsuarioRecuperacionContrasenaAccesoporNumeroDocumentoIdentificacion(@PathVariable String numeroDocumentoIdentificacionUsuario){
        return usuarioService.consultarUsuarioRecuperacionContrasenaAccesoporNumeroDocumentoIdentificacion(numeroDocumentoIdentificacionUsuario);
    }
    
    //LEER CONSULTA DE REGISTRO POR NICKNAME Y PASSWORD:
    @GetMapping("/usuarios/nicknameYPassword/{nicknameUsuario}/{passwordUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarUsuarioporNicknameYPassword(@PathVariable String nicknameUsuario, @PathVariable String passwordUsuario){
        return usuarioService.consultarUsuarioporNicknameYPassword(nicknameUsuario, passwordUsuario);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/usuarios")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.actualizarUsuario(usuarioDTO);
    }
    
    //ACTUALIZAR PASSWORD DE USUARIO:
    @PutMapping("/usuarios/actualizarPassword/{idUsuario}/{passwordUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD ACTUALIZAR PASSWORD.
    public RespuestaDTO actualizarPasswordUsuario(@PathVariable Long idUsuario, @PathVariable String passwordUsuario){
        return usuarioService.actualizarPasswordUsuario(idUsuario, passwordUsuario);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/usuarios/{idUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarUsuario(@PathVariable Long idUsuario){
        return usuarioService.eliminarUsuario(idUsuario);
    }
}
