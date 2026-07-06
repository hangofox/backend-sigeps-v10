//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PuestoSedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.PuestoSedeEstablecimientoClienteService;
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
public class PuestoSedeEstablecimientoClienteController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private PuestoSedeEstablecimientoClienteService puestoSedeEstablecimientoClienteService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/puestosSedesEstablecimientosClientes/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idPuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoPuestoSedeEstablecimientoCliente) {
        return new ResponseEntity<>(puestoSedeEstablecimientoClienteService.contarTotalRegistros(idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/puestosSedesEstablecimientosClientes/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<PuestoSedeEstablecimientoClienteDTO>> listarPuestosEstablecimientosClientesLista(
            @RequestParam(required = false) Long idPuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoPuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(puestoSedeEstablecimientoClienteService.listarPuestosSedesEstablecimientosClientes(idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/puestosSedesEstablecimientosClientes/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<PuestoSedeEstablecimientoClienteDTO>> listarPuestosEstablecimientosClientesPag(
            @RequestParam(required = false) Long idPuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String nombrePuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoPuestoSedeEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(puestoSedeEstablecimientoClienteService.listarPuestosSedesEstablecimientosClientesPag(pageable, idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/puestosSedesEstablecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearPuestoSedeEstablecimientoCliente(@RequestBody PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO){
        System.out.println(puestoSedeEstablecimientoClienteDTO);
        return puestoSedeEstablecimientoClienteService.crearPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/puestosSedesEstablecimientosClientes/{idPuestoSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarPuestoSedeEstablecimientoClienteporId(@PathVariable Long idPuestoSedeEstablecimientoCliente){
        return puestoSedeEstablecimientoClienteService.consultarPuestoSedeEstablecimientoClienteporId(idPuestoSedeEstablecimientoCliente);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/puestosSedesEstablecimientosClientes/nombre/{nombrePuestoSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarPuestoSedeEstablecimientoClienteporNombre(@PathVariable String nombrePuestoSedeEstablecimientoCliente){
        return puestoSedeEstablecimientoClienteService.consultarPuestoSedeEstablecimientoClienteporNombre(nombrePuestoSedeEstablecimientoCliente);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/puestosSedesEstablecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarPuestoSedeEstablecimientoCliente(@RequestBody PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO){
        return puestoSedeEstablecimientoClienteService.actualizarPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/puestosSedesEstablecimientosClientes/{idPuestoSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarPuestoSedeEstablecimientoCliente(@PathVariable Long idPuestoSedeEstablecimientoCliente){
        return puestoSedeEstablecimientoClienteService.eliminarPuestoSedeEstablecimientoCliente(idPuestoSedeEstablecimientoCliente);
    }
}
