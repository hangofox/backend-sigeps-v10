//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SubclasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.SubclasificacionEmpleadoPlantaService;
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
public class SubclasificacionEmpleadoPlantaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private SubclasificacionEmpleadoPlantaService subclasificacionEmpleadoPlantaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/subclasificacionesEmpleadosPlantas/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idSubclasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String nombreSubclasificacionEmpleadoPlanta) {
        return new ResponseEntity<>(subclasificacionEmpleadoPlantaService.contarTotalRegistros(idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/subclasificacionesEmpleadosPlantas/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<SubclasificacionEmpleadoPlantaDTO>> listarSubclasificacionesEmpleadosPlantasLista(
            @RequestParam(required = false) Long idSubclasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String nombreSubclasificacionEmpleadoPlanta,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(subclasificacionEmpleadoPlantaService.listarSubclasificacionesEmpleadosPlantas(idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/subclasificacionesEmpleadosPlantas/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<SubclasificacionEmpleadoPlantaDTO>> listarSubclasificacionesEmpleadosPlantasPag(
            @RequestParam(required = false) Long idSubclasificacionEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreClasificacionEmpleadoPlanta,
            @RequestParam(required = false) String nombreSubclasificacionEmpleadoPlanta,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(subclasificacionEmpleadoPlantaService.listarSubclasificacionesEmpleadosPlantasPag(pageable, idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/subclasificacionesEmpleadosPlantas")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearSubclasificacionEmpleadoPlanta(@RequestBody SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO){
        System.out.println(subclasificacionEmpleadoPlantaDTO);
        return subclasificacionEmpleadoPlantaService.crearSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/subclasificacionesEmpleadosPlantas/{idSubclasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarSubclasificacionEmpleadoPlantaporId(@PathVariable Long idSubclasificacionEmpleadoPlanta){
        return subclasificacionEmpleadoPlantaService.consultarSubclasificacionEmpleadoPlantaporId(idSubclasificacionEmpleadoPlanta);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/subclasificacionesEmpleadosPlantas/nombre/{nombreSubclasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarSubclasificacionEmpleadoPlantaporNombre(@PathVariable String nombreSubclasificacionEmpleadoPlanta){
        return subclasificacionEmpleadoPlantaService.consultarSubclasificacionEmpleadoPlantaporNombre(nombreSubclasificacionEmpleadoPlanta);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/subclasificacionesEmpleadosPlantas")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarSubclasificacionEmpleadoPlanta(@RequestBody SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO){
        return subclasificacionEmpleadoPlantaService.actualizarSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/subclasificacionesEmpleadosPlantas/{idSubclasificacionEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarSubclasificacionEmpleadoPlanta(@PathVariable Long idSubclasificacionEmpleadoPlanta){
        return subclasificacionEmpleadoPlantaService.eliminarSubclasificacionEmpleadoPlanta(idSubclasificacionEmpleadoPlanta);
    }
}
