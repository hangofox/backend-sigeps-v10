//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoEmpleadoPlantaService;
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
public class TipoEmpleadoPlantaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private TipoEmpleadoPlantaService tipoEmpleadoPlantaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/tiposEmpleadosPlanta/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idTipoEmpleadoPlanta,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(tipoEmpleadoPlantaService.contarTotalRegistros(idTipoEmpleadoPlanta, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/tiposEmpleadosPlanta/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<TipoEmpleadoPlantaDTO>> listarTiposEmpleadosPlantaLista(
            @RequestParam(required = false) Long idTipoEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(tipoEmpleadoPlantaService.listarTiposEmpleadosPlanta(idTipoEmpleadoPlanta, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/tiposEmpleadosPlanta/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<TipoEmpleadoPlantaDTO>> listarTiposEmpleadosPlantaPag(
            @RequestParam(required = false) Long idTipoEmpleadoPlanta,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(tipoEmpleadoPlantaService.listarTiposEmpleadosPlantaPag(pageable, idTipoEmpleadoPlanta, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/tiposEmpleadosPlanta")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearTipoEmpleadoPlanta(@RequestBody TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO){
        System.out.println(tipoEmpleadoPlantaDTO);
        return tipoEmpleadoPlantaService.crearTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/tiposEmpleadosPlanta/{idTipoEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoPlantaporId(@PathVariable Long idTipoEmpleadoPlanta){
        return tipoEmpleadoPlantaService.consultarTipoEmpleadoPlantaporId(idTipoEmpleadoPlanta);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/tiposEmpleadosPlanta/nombre/{nombreTipoEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoPlantaporNombre(@PathVariable String nombreTipoEmpleadoPlanta){
        return tipoEmpleadoPlantaService.consultarTipoEmpleadoPlantaporNombre(nombreTipoEmpleadoPlanta);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/tiposEmpleadosPlanta")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoEmpleadoPlanta(@RequestBody TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO){
        return tipoEmpleadoPlantaService.actualizarTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/tiposEmpleadosPlanta/{idTipoEmpleadoPlanta}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoEmpleadoPlanta(@PathVariable Long idTipoEmpleadoPlanta){
        return tipoEmpleadoPlantaService.eliminarTipoEmpleadoPlanta(idTipoEmpleadoPlanta);
    }
}
