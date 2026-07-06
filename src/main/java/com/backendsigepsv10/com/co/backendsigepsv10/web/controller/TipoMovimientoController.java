//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoMovimientoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoMovimientoService;
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
* @Since 11/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class TipoMovimientoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private TipoMovimientoService tipoMovimientoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/tiposMovimientos/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idTipoMovimiento,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(tipoMovimientoService.contarTotalRegistros(idTipoMovimiento, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/tiposMovimientos/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<TipoMovimientoDTO>> listarTiposMovimientosLista(
            @RequestParam(required = false) Long idTipoMovimiento,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(tipoMovimientoService.listarTiposMovimientos(idTipoMovimiento, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/tiposMovimientos/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<TipoMovimientoDTO>> listarTiposMovimientosPag(
            @RequestParam(required = false) Long idTipoMovimiento,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(tipoMovimientoService.listarTiposMovimientosPag(pageable, idTipoMovimiento, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/tiposMovimientos")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearTipoMovimiento(@RequestBody TipoMovimientoDTO tipoMovimientoDTO){
        System.out.println(tipoMovimientoDTO);
        return tipoMovimientoService.crearTipoMovimiento(tipoMovimientoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/tiposMovimientos/{idTipoMovimiento}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoMovimientoporId(@PathVariable Long idTipoMovimiento){
        return tipoMovimientoService.consultarTipoMovimientoporId(idTipoMovimiento);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/tiposMovimientos/nombre/{nombreTipoMovimiento}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoMovimientoporNombre(@PathVariable String nombreTipoMovimiento){
        return tipoMovimientoService.consultarTipoMovimientoporNombre(nombreTipoMovimiento);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/tiposMovimientos")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoMovimiento(@RequestBody TipoMovimientoDTO tipoMovimientoDTO){
        return tipoMovimientoService.actualizarTipoMovimiento(tipoMovimientoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/tiposMovimientos/{idTipoMovimiento}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoMovimiento(@PathVariable Long idTipoMovimiento){
        return tipoMovimientoService.eliminarTipoMovimiento(idTipoMovimiento);
    }
}
