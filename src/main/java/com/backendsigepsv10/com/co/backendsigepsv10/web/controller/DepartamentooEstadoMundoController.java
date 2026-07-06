//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.DepartamentooEstadoMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.DepartamentooEstadoMundoService;
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
public class DepartamentooEstadoMundoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private DepartamentooEstadoMundoService departamentooEstadoMundoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/departamentosoEstadosMundo/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarDepartamentosoEstadosMundo(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(departamentooEstadoMundoService.contarTotalRegistros(idPaisMundo, nombrePaisMundo, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS:
    @GetMapping("/departamentosoEstadosMundo/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<DepartamentooEstadoMundoDTO>> listarDepartamentosoEstadosMundo(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(departamentooEstadoMundoService.listarDepartamentosoEstadosMundo(idPaisMundo, nombrePaisMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/departamentosoEstadosMundo/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<DepartamentooEstadoMundoDTO>> listarDepartamentosoEstadosMundoPag(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(departamentooEstadoMundoService.listarDepartamentosoEstadosMundoPag(pageable, idPaisMundo, nombrePaisMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/departamentosoEstadosMundo")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearDepartamentooEstadoMundo(@RequestBody DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO) {
        System.out.println(departamentooEstadoMundoDTO);
        return departamentooEstadoMundoService.crearDepartamentooEstadoMundo(departamentooEstadoMundoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/departamentosoEstadosMundo/{idDepartamentooEstadoMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR ID.
    public RespuestaDTO consultarDepartamentooEstadoMundoporId(@PathVariable Long idDepartamentooEstadoMundo) {
        return departamentooEstadoMundoService.consultarDepartamentooEstadoMundoporId(idDepartamentooEstadoMundo);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID PAIS MUNDO E ID DEPARTAMENTO O ESTADO MUNDO:
    @GetMapping("/departamentosoEstadosMundo/idPaisMundo/{idPaisMundo}/idDepartamentooEstadoMundo/{idDepartamentooEstadoMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR ID PAIS MUNDO E ID.
    public RespuestaDTO consultarDepartamentooEstadoMundoporIdeIdPaisMundo(
            @PathVariable Long idPaisMundo,
            @PathVariable Long idDepartamentooEstadoMundo) {
        return departamentooEstadoMundoService.consultarDepartamentooEstadoMundoporIdeIdPaisMundo(idPaisMundo, idDepartamentooEstadoMundo);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE PAIS MUNDO Y NOMBRE DEPARTAMENTO O ESTADO MUNDO:
    @GetMapping("/departamentosoEstadosMundo/nombrePaisMundo/{nombrePaisMundo}/nombreDepartamentooEstadoMundo/{nombreDepartamentooEstadoMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR NOMBRE PAIS MUNDO Y NOMBRE.
    public RespuestaDTO consultarDepartamentooEstadoMundoporNombreyNombrePaisMundo(
            @PathVariable String nombrePaisMundo,
            @PathVariable String nombreDepartamentooEstadoMundo) {
        return departamentooEstadoMundoService.consultarDepartamentooEstadoMundoporNombreyNombrePaisMundo(nombrePaisMundo, nombreDepartamentooEstadoMundo);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/departamentosoEstadosMundo")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarDepartamentooEstadoMundo(@RequestBody DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO) {
        return departamentooEstadoMundoService.actualizarDepartamentooEstadoMundo(departamentooEstadoMundoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/departamentosoEstadosMundo/{idDepartamentooEstadoMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarDepartamentooEstadoMundo(@PathVariable Long idDepartamentooEstadoMundo) {
        return departamentooEstadoMundoService.eliminarDepartamentooEstadoMundo(idDepartamentooEstadoMundo);
    }
}
