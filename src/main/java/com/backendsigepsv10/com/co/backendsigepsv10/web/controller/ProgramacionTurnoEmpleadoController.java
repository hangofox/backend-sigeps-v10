//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ProgramacionTurnoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ProgramacionTurnoEmpleadoService;
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
public class ProgramacionTurnoEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private ProgramacionTurnoEmpleadoService programacionTurnoEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/programacionesTurnosEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idProgramacionTurnoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoProgramacionTurnoEmpleado) {
        return new ResponseEntity<>(programacionTurnoEmpleadoService.contarTotalRegistros(idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/programacionesTurnosEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<ProgramacionTurnoEmpleadoDTO>> listarProgramacionesTurnosEmpleadosLista(
            @RequestParam(required = false) Long idProgramacionTurnoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoProgramacionTurnoEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(programacionTurnoEmpleadoService.listarProgramacionesTurnosEmpleados(idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/programacionesTurnosEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<ProgramacionTurnoEmpleadoDTO>> listarProgramacionesTurnosEmpleadosPag(
            @RequestParam(required = false) Long idProgramacionTurnoEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombresEmpleado,
            @RequestParam(required = false) String primerApellidoEmpleado,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombreTurno,
            @RequestParam(required = false) String estadoProgramacionTurnoEmpleado,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(programacionTurnoEmpleadoService.listarProgramacionesTurnosEmpleadosPag(pageable, idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/programacionesTurnosEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearProgramacionTurnoEmpleado(@RequestBody ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO){
        System.out.println(programacionTurnoEmpleadoDTO);
        return programacionTurnoEmpleadoService.crearProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/programacionesTurnosEmpleados/{idProgramacionTurnoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarProgramacionTurnoEmpleadoporId(@PathVariable Long idProgramacionTurnoEmpleado){
        return programacionTurnoEmpleadoService.consultarProgramacionTurnoEmpleadoporId(idProgramacionTurnoEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE EMPLEADO Y NOMBRE DE TURNO:
    @GetMapping("/programacionesTurnosEmpleados/idEmpleado/{idEmpleado}/nombreTurno/{nombreTurno}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarProgramacionTurnoEmpleadoporIdEmpleadoyNombreTurno(@PathVariable Long idEmpleado, @PathVariable String nombreTurno){
        return programacionTurnoEmpleadoService.consultarProgramacionTurnoEmpleadoporIdEmpleadoyNombreTurno(idEmpleado, nombreTurno);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/programacionesTurnosEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarProgramacionTurnoEmpleado(@RequestBody ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO){
        return programacionTurnoEmpleadoService.actualizarProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/programacionesTurnosEmpleados/{idProgramacionTurnoEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarProgramacionTurnoEmpleado(@PathVariable Long idProgramacionTurnoEmpleado){
        return programacionTurnoEmpleadoService.eliminarProgramacionTurnoEmpleado(idProgramacionTurnoEmpleado);
    }
}
