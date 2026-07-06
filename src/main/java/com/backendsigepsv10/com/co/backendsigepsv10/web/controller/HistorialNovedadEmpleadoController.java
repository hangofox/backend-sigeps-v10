//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.HistorialNovedadEmpleadoService;
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
* @Since 13/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class HistorialNovedadEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private HistorialNovedadEmpleadoService historialNovedadEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/historialesNovedadesEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idHistorialNovedadEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoNovedadEmpleado,
            @RequestParam(required = false) String descripcionHistorialNovedadEmpleado) {
        return new ResponseEntity<>(historialNovedadEmpleadoService.contarTotalRegistros(idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/historialesNovedadesEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<HistorialNovedadEmpleadoDTO>> listarHistorialesNovedadesEmpleadosLista(
            @RequestParam(required = false) Long idHistorialNovedadEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoNovedadEmpleado,
            @RequestParam(required = false) String descripcionHistorialNovedadEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(historialNovedadEmpleadoService.listarHistorialesNovedadesEmpleados(idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/historialesNovedadesEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<HistorialNovedadEmpleadoDTO>> listarHistorialesNovedadesEmpleadosPag(
            @RequestParam(required = false) Long idHistorialNovedadEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombreTipoNovedadEmpleado,
            @RequestParam(required = false) String descripcionHistorialNovedadEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(historialNovedadEmpleadoService.listarHistorialesNovedadesEmpleadosPag(pageable, idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/historialesNovedadesEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearHistorialNovedadEmpleado(@RequestBody HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO){
        System.out.println(historialNovedadEmpleadoDTO);
        return historialNovedadEmpleadoService.crearHistorialNovedadEmpleado(historialNovedadEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/historialesNovedadesEmpleados/{idHistorialNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarHistorialNovedadEmpleadoporId(@PathVariable Long idHistorialNovedadEmpleado){
        return historialNovedadEmpleadoService.consultarHistorialNovedadEmpleadoporId(idHistorialNovedadEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL EMPLEADO Y DESCRIPCIÓN:
    @GetMapping("/historialesNovedadesEmpleados/numeroDocumentoIdentificacion/{numeroDocumentoIdentificacionEmpleado}/descripcion/{descripcionHistorialNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarHistorialNovedadEmpleadoporNumeroDocumentoIdentificacionyDescripcion(@PathVariable String numeroDocumentoIdentificacionEmpleado, @PathVariable String descripcionHistorialNovedadEmpleado){
        return historialNovedadEmpleadoService.consultarHistorialNovedadEmpleadoporNumeroDocumentoIdentificacionyDescripcion(numeroDocumentoIdentificacionEmpleado, descripcionHistorialNovedadEmpleado);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/historialesNovedadesEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarHistorialNovedadEmpleado(@RequestBody HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO){
        return historialNovedadEmpleadoService.actualizarHistorialNovedadEmpleado(historialNovedadEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/historialesNovedadesEmpleados/{idHistorialNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarHistorialNovedadEmpleado(@PathVariable Long idHistorialNovedadEmpleado){
        return historialNovedadEmpleadoService.eliminarHistorialNovedadEmpleado(idHistorialNovedadEmpleado);
    }
}
