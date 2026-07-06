//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.CiudadMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.CiudadMundoService;
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
public class CiudadMundoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private CiudadMundoService ciudadMundoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/ciudadesMundo/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarCiudadesMundo(
            @RequestParam(required = false) Long idCiudadMundo,
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) Long idDepartamentooEstadoMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String nombreDepartamentooEstadoMundo,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(ciudadMundoService.contarTotalRegistros(idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS:
    @GetMapping("/ciudadesMundo/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<CiudadMundoDTO>> listarCiudadesMundo(
            @RequestParam(required = false) Long idCiudadMundo,
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) Long idDepartamentooEstadoMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String nombreDepartamentooEstadoMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(ciudadMundoService.listarCiudadesMundo(idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/ciudadesMundo/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<CiudadMundoDTO>> listarCiudadesMundoPag(
            @RequestParam(required = false) Long idCiudadMundo,
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) Long idDepartamentooEstadoMundo,
            @RequestParam(required = false) String nombrePaisMundo,
            @RequestParam(required = false) String nombreDepartamentooEstadoMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(ciudadMundoService.listarCiudadesMundoPag(pageable, idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/ciudadesMundo")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearCiudadMundo(@RequestBody CiudadMundoDTO ciudadMundoDTO) {
        System.out.println(ciudadMundoDTO);
        return ciudadMundoService.crearCiudadMundo(ciudadMundoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/ciudadesMundo/{idCiudadMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR ID.
    public RespuestaDTO consultarCiudadMundoporId(@PathVariable Long idCiudadMundo) {
        return ciudadMundoService.consultarCiudadMundoporId(idCiudadMundo);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID PAIS MUNDO, ID DEPTO O ESTADO MUNDO E ID CIUDAD MUNDO:
    @GetMapping("/ciudadesMundo/idPaisMundo/{idPaisMundo}/idDepartamentooEstadoMundo/{idDepartamentooEstadoMundo}/idCiudadMundo/{idCiudadMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR IDS.
    public RespuestaDTO consultarCiudadMundoporIdPaisMundoeIdDepartamentooEstadoMundoeIdCiudadMundo(
            @PathVariable Long idPaisMundo,
            @PathVariable Long idDepartamentooEstadoMundo,
            @PathVariable Long idCiudadMundo) {
        return ciudadMundoService.consultarCiudadMundoporIdPaisMundoeIdDepartamentooEstadoMundoeIdCiudadMundo(idPaisMundo, idDepartamentooEstadoMundo, idCiudadMundo);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE PAIS MUNDO, NOMBRE DEPTO O ESTADO MUNDO Y NOMBRE CIUDAD MUNDO:
    @GetMapping("/ciudadesMundo/nombrePaisMundo/{nombrePaisMundo}/nombreDepartamentooEstadoMundo/{nombreDepartamentooEstadoMundo}/nombreCiudadMundo/{nombreCiudadMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR NOMBRES.
    public RespuestaDTO consultarCiudadMundoporNombrePaisMundoyNombreDepartamentooEstadoMundoyNombreCiudadMundo(
            @PathVariable String nombrePaisMundo,
            @PathVariable String nombreDepartamentooEstadoMundo,
            @PathVariable String nombreCiudadMundo) {
        return ciudadMundoService.consultarCiudadMundoporNombrePaisMundoyNombreDepartamentooEstadoMundoyNombreCiudadMundo(nombrePaisMundo, nombreDepartamentooEstadoMundo, nombreCiudadMundo);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/ciudadesMundo")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarCiudadMundo(@RequestBody CiudadMundoDTO ciudadMundoDTO) {
        return ciudadMundoService.actualizarCiudadMundo(ciudadMundoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/ciudadesMundo/{idCiudadMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarCiudadMundo(@PathVariable Long idCiudadMundo) {
        return ciudadMundoService.eliminarCiudadMundo(idCiudadMundo);
    }
}
