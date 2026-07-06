//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoEmpleadoService;
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
public class TipoEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private TipoEmpleadoService tipoEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/tiposEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idTipoEmpleado,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(tipoEmpleadoService.contarTotalRegistros(idTipoEmpleado, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/tiposEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<TipoEmpleadoDTO>> listarTiposEmpleadosLista(
            @RequestParam(required = false) Long idTipoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(tipoEmpleadoService.listarTiposEmpleados(idTipoEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/tiposEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<TipoEmpleadoDTO>> listarTiposEmpleadosPag(
            @RequestParam(required = false) Long idTipoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(tipoEmpleadoService.listarTiposEmpleadosPag(pageable, idTipoEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/tiposEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearTipoEmpleado(@RequestBody TipoEmpleadoDTO tipoEmpleadoDTO){
        System.out.println(tipoEmpleadoDTO);
        return tipoEmpleadoService.crearTipoEmpleado(tipoEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/tiposEmpleados/{idTipoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoporId(@PathVariable Long idTipoEmpleado){
        return tipoEmpleadoService.consultarTipoEmpleadoporId(idTipoEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/tiposEmpleados/nombre/{nombreTipoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoporNombre(@PathVariable String nombreTipoEmpleado){
        return tipoEmpleadoService.consultarTipoEmpleadoporNombre(nombreTipoEmpleado);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/tiposEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoEmpleado(@RequestBody TipoEmpleadoDTO tipoEmpleadoDTO){
        return tipoEmpleadoService.actualizarTipoEmpleado(tipoEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/tiposEmpleados/{idTipoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoEmpleado(@PathVariable Long idTipoEmpleado){
        return tipoEmpleadoService.eliminarTipoEmpleado(idTipoEmpleado);
    }
}
