//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ClasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ClasificacionEmpleadoPlantaService;
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
public class ClasificacionEmpleadoPlantaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private ClasificacionEmpleadoPlantaService clasificacionEmpleadoPlantaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/clasificacionesEmpleadosPlantas/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoEmpleadoPlanta,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta) {
        return new ResponseEntity<>(clasificacionEmpleadoPlantaService.contarTotalRegistros(idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/clasificacionesEmpleadosPlantas/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<ClasificacionEmpleadoPlantaDTO>> listarClasificacionesEmpleadosPlantasLista(
            @RequestParam(required = false) Long idClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoEmpleadoPlanta,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(clasificacionEmpleadoPlantaService.listarClasificacionesEmpleadosPlantas(idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/clasificacionesEmpleadosPlantas/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<ClasificacionEmpleadoPlantaDTO>> listarClasificacionesEmpleadosPlantasPag(
            @RequestParam(required = false) Long idClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoEmpleadoPlanta,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(clasificacionEmpleadoPlantaService.listarClasificacionesEmpleadosPlantasPag(pageable, idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/clasificacionesEmpleadosPlantas")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearClasificacionEmpleadoPlanta(@RequestBody ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO){
        System.out.println(clasificacionEmpleadoPlantaDTO);
        return clasificacionEmpleadoPlantaService.crearClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/clasificacionesEmpleadosPlantas/{idClasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarClasificacionEmpleadoPlantaporId(@PathVariable Long idClasificacionEmpleadoPlanta){
        return clasificacionEmpleadoPlantaService.consultarClasificacionEmpleadoPlantaporId(idClasificacionEmpleadoPlanta);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/clasificacionesEmpleadosPlantas/nombre/{nombreClasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarClasificacionEmpleadoPlantaporNombre(@PathVariable String nombreClasificacionEmpleadoPlanta){
        return clasificacionEmpleadoPlantaService.consultarClasificacionEmpleadoPlantaporNombre(nombreClasificacionEmpleadoPlanta);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/clasificacionesEmpleadosPlantas")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarClasificacionEmpleadoPlanta(@RequestBody ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO){
        return clasificacionEmpleadoPlantaService.actualizarClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/clasificacionesEmpleadosPlantas/{idClasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarClasificacionEmpleadoPlanta(@PathVariable Long idClasificacionEmpleadoPlanta){
        return clasificacionEmpleadoPlantaService.eliminarClasificacionEmpleadoPlanta(idClasificacionEmpleadoPlanta);
    }
}
