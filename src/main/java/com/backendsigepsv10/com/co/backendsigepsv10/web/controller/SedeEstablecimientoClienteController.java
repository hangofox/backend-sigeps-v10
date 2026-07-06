//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.SedeEstablecimientoClienteService;
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
public class SedeEstablecimientoClienteController {

    @Autowired//INYECTAMOS EL SERVICIO.
    private SedeEstablecimientoClienteService sedeEstablecimientoClienteService;

    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).

    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/sedesEstablecimientosClientes/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoSedeEstablecimientoCliente) {
        return new ResponseEntity<>(sedeEstablecimientoClienteService.contarTotalRegistros(idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente), HttpStatus.OK);
    }

    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/sedesEstablecimientosClientes/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<SedeEstablecimientoClienteDTO>> listarSedesEstablecimientosClientesLista(
            @RequestParam(required = false) Long idSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoSedeEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(sedeEstablecimientoClienteService.listarSedesEstablecimientosClientes(idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }

    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/sedesEstablecimientosClientes/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<SedeEstablecimientoClienteDTO>> listarSedesEstablecimientosClientesPag(
            @RequestParam(required = false) Long idSedeEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String nombreSedeEstablecimientoCliente,
            @RequestParam(required = false) String estadoSedeEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(sedeEstablecimientoClienteService.listarSedesEstablecimientosClientesPag(pageable, idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }

    //CREAR REGISTRO:
    @PostMapping("/sedesEstablecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearSedeEstablecimientoCliente(@RequestBody SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO){
        System.out.println(sedeEstablecimientoClienteDTO);
        return sedeEstablecimientoClienteService.crearSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO);
    }

    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/sedesEstablecimientosClientes/{idSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarSedeEstablecimientoClienteporId(@PathVariable Long idSedeEstablecimientoCliente){
        return sedeEstablecimientoClienteService.consultarSedeEstablecimientoClienteporId(idSedeEstablecimientoCliente);
    }

    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/sedesEstablecimientosClientes/nombre/{nombreSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarSedeEstablecimientoClienteporNombre(@PathVariable String nombreSedeEstablecimientoCliente){
        return sedeEstablecimientoClienteService.consultarSedeEstablecimientoClienteporNombre(nombreSedeEstablecimientoCliente);
    }

    //MODIFICAR REGISTRO:
    @PutMapping("/sedesEstablecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarSedeEstablecimientoCliente(@RequestBody SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO){
        return sedeEstablecimientoClienteService.actualizarSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO);
    }

    //ELIMINAR REGISTRO:
    @DeleteMapping("/sedesEstablecimientosClientes/{idSedeEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarSedeEstablecimientoCliente(@PathVariable Long idSedeEstablecimientoCliente){
        return sedeEstablecimientoClienteService.eliminarSedeEstablecimientoCliente(idSedeEstablecimientoCliente);
    }
}
