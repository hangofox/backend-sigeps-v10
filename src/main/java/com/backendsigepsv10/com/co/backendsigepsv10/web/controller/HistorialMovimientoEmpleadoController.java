//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialMovimientoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.HistorialMovimientoEmpleadoService;
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
* @Since 13/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class HistorialMovimientoEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private HistorialMovimientoEmpleadoService historialMovimientoEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/historialesMovimientosEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idHistorialMovimientoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoMovimiento) {
        return new ResponseEntity<>(historialMovimientoEmpleadoService.contarTotalRegistros(idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/historialesMovimientosEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<HistorialMovimientoEmpleadoDTO>> listarHistorialesMovimientosEmpleadosLista(
            @RequestParam(required = false) Long idHistorialMovimientoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoMovimiento,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(historialMovimientoEmpleadoService.listarHistorialesMovimientosEmpleados(idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/historialesMovimientosEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<HistorialMovimientoEmpleadoDTO>> listarHistorialesMovimientosEmpleadosPag(
            @RequestParam(required = false) Long idHistorialMovimientoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoMovimiento,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(historialMovimientoEmpleadoService.listarHistorialesMovimientosEmpleadosPag(pageable, idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/historialesMovimientosEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearHistorialMovimientoEmpleado(@RequestBody HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO){
        System.out.println(historialMovimientoEmpleadoDTO);
        return historialMovimientoEmpleadoService.crearHistorialMovimientoEmpleado(historialMovimientoEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/historialesMovimientosEmpleados/{idHistorialMovimientoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarHistorialMovimientoEmpleadoporId(@PathVariable Long idHistorialMovimientoEmpleado){
        return historialMovimientoEmpleadoService.consultarHistorialMovimientoEmpleadoporId(idHistorialMovimientoEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE EMPLEADO Y FECHA HMS:
    @GetMapping("/historialesMovimientosEmpleados/idEmpleado/{idEmpleado}/fechaHMS/{fechaHMS}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarHistorialMovimientoEmpleadoporIdEmpleadoyFechaHMS(@PathVariable Long idEmpleado, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") Date fechaHMS){
        return historialMovimientoEmpleadoService.consultarHistorialMovimientoEmpleadoporIdEmpleadoyFechaHMS(idEmpleado, fechaHMS);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/historialesMovimientosEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarHistorialMovimientoEmpleado(@RequestBody HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO){
        return historialMovimientoEmpleadoService.actualizarHistorialMovimientoEmpleado(historialMovimientoEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/historialesMovimientosEmpleados/{idHistorialMovimientoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarHistorialMovimientoEmpleado(@PathVariable Long idHistorialMovimientoEmpleado){
        return historialMovimientoEmpleadoService.eliminarHistorialMovimientoEmpleado(idHistorialMovimientoEmpleado);
    }
}
