//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PaisMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.PaisMundoService;
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
public class PaisMundoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private PaisMundoService paisMundoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/paisesMundo/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarPaisesMundo(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(paisMundoService.contarTotalRegistros(idPaisMundo, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/paisesMundo/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<PaisMundoDTO>> listarPaisesMundo(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(paisMundoService.listarPaisesMundo(idPaisMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS PAGINADOS:
    @GetMapping("/paisesMundo/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<PaisMundoDTO>> listarPaisesMundoPag(
            @RequestParam(required = false) Long idPaisMundo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(paisMundoService.listarPaisesMundoPag(pageable, idPaisMundo, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/paisesMundo")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearPaisMundo(@RequestBody PaisMundoDTO paisMundoDTO) {
        System.out.println(paisMundoDTO);
        return paisMundoService.crearPaisMundo(paisMundoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/paisesMundo/{idPaisMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR ID.
    public RespuestaDTO consultarPaisMundoporId(@PathVariable Long idPaisMundo) {
        return paisMundoService.consultarPaisMundoporId(idPaisMundo);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/paisesMundo/nombre/{nombrePaisMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO POR NOMBRE.
    public RespuestaDTO consultarPaisMundoporNombre(@PathVariable String nombrePaisMundo) {
        return paisMundoService.consultarPaisMundoporNombre(nombrePaisMundo);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/paisesMundo")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarPaisMundo(@RequestBody PaisMundoDTO paisMundoDTO) {
        return paisMundoService.actualizarPaisMundo(paisMundoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/paisesMundo/{idPaisMundo}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarPaisMundo(@PathVariable Long idPaisMundo) {
        return paisMundoService.eliminarPaisMundo(idPaisMundo);
    }
}
