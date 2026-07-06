//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TurnoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TurnoService;
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
public class TurnoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private TurnoService turnoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/turnos/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idTurno,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoTurno) {
        return new ResponseEntity<>(turnoService.contarTotalRegistros(idTurno, keyword, nombreTurno, estadoTurno), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/turnos/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<TurnoDTO>> listarTurnosLista(
            @RequestParam(required = false) Long idTurno,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoTurno,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(turnoService.listarTurnos(idTurno, keyword, nombreTurno, estadoTurno, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/turnos/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<TurnoDTO>> listarTurnosPag(
            @RequestParam(required = false) Long idTurno,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoTurno,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(turnoService.listarTurnosPag(pageable, idTurno, keyword, nombreTurno, estadoTurno, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/turnos")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearTurno(@RequestBody TurnoDTO turnoDTO){
        System.out.println(turnoDTO);
        return turnoService.crearTurno(turnoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/turnos/{idTurno}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTurnoporId(@PathVariable Long idTurno){
        return turnoService.consultarTurnoporId(idTurno);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/turnos/nombre/{nombreTurno}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTurnoporNombre(@PathVariable String nombreTurno){
        return turnoService.consultarTurnoporNombre(nombreTurno);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/turnos")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTurno(@RequestBody TurnoDTO turnoDTO){
        return turnoService.actualizarTurno(turnoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/turnos/{idTurno}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTurno(@PathVariable Long idTurno){
        return turnoService.eliminarTurno(idTurno);
    }
}
