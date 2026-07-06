//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.EmpleadoService;
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
* @Since 12/06/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class EmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private EmpleadoService empleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/empleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEmpleado,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado) {
        return new ResponseEntity<>(empleadoService.contarTotalRegistros(idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/empleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<EmpleadoDTO>> listarEmpleadosLista(
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEmpleado,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(empleadoService.listarEmpleados(idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/empleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<EmpleadoDTO>> listarEmpleadosPag(
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEmpleado,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(empleadoService.listarEmpleadosPag(pageable, idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/empleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        System.out.println(empleadoDTO);
        return empleadoService.crearEmpleado(empleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/empleados/{idEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarEmpleadoporId(@PathVariable Long idEmpleado){
        return empleadoService.consultarEmpleadoporId(idEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @GetMapping("/empleados/numeroDocumentoIdentificacion/{numeroDocumentoIdentificacionEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarEmpleadoporNumeroDocumentoIdentificacion(@PathVariable String numeroDocumentoIdentificacionEmpleado){
        return empleadoService.consultarEmpleadoporNumeroDocumentoIdentificacion(numeroDocumentoIdentificacionEmpleado);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/empleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.actualizarEmpleado(empleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/empleados/{idEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarEmpleado(@PathVariable Long idEmpleado){
        return empleadoService.eliminarEmpleado(idEmpleado);
    }
}
