//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PrivilegyRestriccAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.PrivilegyRestriccAccesoUsuarioService;
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
public class PrivilegyRestriccAccesoUsuarioController {

    @Autowired//INYECTAMOS EL SERVICIO.
    private PrivilegyRestriccAccesoUsuarioService privilegyRestriccAccesoUsuarioService;

    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).

    //CONTADORES DE REGISTROS FILTRADOS.
    //ENDPOINT ÚNICO PARA CONTAR REGISTROS CON QUERY PARAMS:
    @GetMapping("/privilegyRestriccAccesoUsuario/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idPrivilegyRestriccAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idFuncionalidad,
            @RequestParam(required = false) String nombreFuncionalidad,
            @RequestParam(required = false) Long idRol,
            @RequestParam(required = false) String nombreRol,
            @RequestParam(required = false) Long idUsuario) {
        return new ResponseEntity<>(privilegyRestriccAccesoUsuarioService.contarTotalRegistros(idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario), HttpStatus.OK);
    }

    //LISTADO DE REGISTROS FILTRADOS SIN PAGINACIÓN.
    //ENDPOINT PARA LISTAR TODOS LOS REGISTROS SIN PAGINACIÓN (PARA SELECTS DEL FRONTEND):
    @GetMapping("/privilegyRestriccAccesoUsuario/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<PrivilegyRestriccAccesoUsuarioDTO>> listarPrivilegyRestriccAccesosUsuariosLista(
            @RequestParam(required = false) Long idPrivilegyRestriccAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idFuncionalidad,
            @RequestParam(required = false) String nombreFuncionalidad,
            @RequestParam(required = false) Long idRol,
            @RequestParam(required = false) String nombreRol,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(privilegyRestriccAccesoUsuarioService.listarPrivilegyRestriccAccesosUsuarios(idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario, orderBy, orderMode), HttpStatus.OK);
    }

    //LISTADO DE REGISTROS FILTRADOS CON PAGINACIÓN.
    //ENDPOINT ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR REGISTROS CON QUERY PARAMS:
    @GetMapping("/privilegyRestriccAccesoUsuario/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<PrivilegyRestriccAccesoUsuarioDTO>> listarPrivilegyRestriccAccesosUsuariosListaPag(
            @RequestParam(required = false) Long idPrivilegyRestriccAccesoUsuario,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idFuncionalidad,
            @RequestParam(required = false) String nombreFuncionalidad,
            @RequestParam(required = false) Long idRol,
            @RequestParam(required = false) String nombreRol,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(privilegyRestriccAccesoUsuarioService.listarPrivilegyRestriccAccesosUsuariosPag(pageable, idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario, orderBy, orderMode), HttpStatus.OK);
    }

    //CREAR REGISTRO:
    @PostMapping("/privilegyRestriccAccesoUsuario")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearPrivilegyRestriccAccesoUsuario(@RequestBody PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO){
        System.out.println(privilegyRestriccAccesoUsuarioDTO);
        return privilegyRestriccAccesoUsuarioService.crearPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO);
    }

    //CREAR REGISTROS MÚLTIPLES:
    @PostMapping("/privilegyRestriccAccesosUsuarios")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTROS MÚLTIPLES.
    public RespuestaDTO crearPrivilegyRestriccAccesosUsuarios(@RequestBody List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesoUsuarioDTOS){
        System.out.println(privilegyRestriccAccesoUsuarioDTOS);
        return privilegyRestriccAccesoUsuarioService.crearPrivilegyRestriccAccesosUsuarios(privilegyRestriccAccesoUsuarioDTOS);
    }

    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/privilegyRestriccAccesoUsuario/{idPrivilegyRestriccAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuariobyId(@PathVariable Long idPrivilegyRestriccAccesoUsuario){
        return privilegyRestriccAccesoUsuarioService.consultarPrivilegyRestriccAccesoUsuarioporId(idPrivilegyRestriccAccesoUsuario);
    }

    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, ID DE FUNCIONALIDAD E ID DE ROL:
    @GetMapping("/privilegyRestriccAccesoUsuario/idUsuario/{idUsuario}/idFuncionalidad/{idFuncionalidad}/idRol/{idRol}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuariobyIdUsuarioIdFuncionalidadIdRol(@PathVariable Long idUsuario, @PathVariable Long idFuncionalidad, @PathVariable Long idRol){
        return privilegyRestriccAccesoUsuarioService.consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioeIdFuncionalidadeIdRol(idUsuario, idFuncionalidad, idRol);
    }

    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, NOMBRE DE FUNCIONALIDAD Y NOMBRE DE ROL:
    @GetMapping("/privilegyRestriccAccesoUsuario/idUsuario/{idUsuario}/nombreFuncionalidad/{nombreFuncionalidad}/nombreRol/{nombreRol}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuariobyIdUsuarioNombreFuncNombreRol(@PathVariable Long idUsuario, @PathVariable String nombreFuncionalidad, @PathVariable String nombreRol){
        return privilegyRestriccAccesoUsuarioService.consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioyNombreFuncionalidadyNombreRol(idUsuario, nombreFuncionalidad, nombreRol);
    }

    //MODIFICAR REGISTRO:
    @PutMapping("/privilegyRestriccAccesoUsuario")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarPrivilegyRestriccAccesoUsuario(@RequestBody PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO){
        return privilegyRestriccAccesoUsuarioService.actualizarPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO);
    }

    //ELIMINAR REGISTRO:
    @DeleteMapping("/privilegyRestriccAccesoUsuario/{idPrivilegyRestriccAccesoUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarPrivilegyRestriccAccesoUsuario(@PathVariable Long idPrivilegyRestriccAccesoUsuario){
        return privilegyRestriccAccesoUsuarioService.eliminarPrivilegyRestriccAccesoUsuario(idPrivilegyRestriccAccesoUsuario);
    }

    //VACIAR REGISTROS POR ID DE USUARIO:
    @DeleteMapping("/privilegyRestriccAccesoUsuario/idUsuario/{idUsuario}")//DECLARACIÓN DEL MAPEO DEL CRUD VACIAR REGISTROS.
    public RespuestaDTO vaciarPrivilegyRestriccAccesosUsuariosporIdUsuario(@PathVariable Long idUsuario){
        return privilegyRestriccAccesoUsuarioService.vaciarPrivilegyRestriccAccesosUsuariosporIdUsuario(idUsuario);
    }
}
