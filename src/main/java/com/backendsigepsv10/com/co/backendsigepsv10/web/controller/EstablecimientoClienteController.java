//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.EstablecimientoClienteService;
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
public class EstablecimientoClienteController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private EstablecimientoClienteService establecimientoClienteService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/establecimientosClientes/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEstablecimientoCliente,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String estadoEstablecimientoCliente) {
        return new ResponseEntity<>(establecimientoClienteService.contarTotalRegistros(idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/establecimientosClientes/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<EstablecimientoClienteDTO>> listarEstablecimientosClientesLista(
            @RequestParam(required = false) Long idEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEstablecimientoCliente,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String estadoEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(establecimientoClienteService.listarEstablecimientosClientes(idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/establecimientosClientes/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<EstablecimientoClienteDTO>> listarEstablecimientosClientesPag(
            @RequestParam(required = false) Long idEstablecimientoCliente,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String nombreTipoDocumentoIdentificacion,
            @RequestParam(required = false) String numeroDocumentoIdentificacionEstablecimientoCliente,
            @RequestParam(required = false) String nombreRazonSocialEstablecimientoCliente,
            @RequestParam(required = false) String estadoEstablecimientoCliente,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(establecimientoClienteService.listarEstablecimientosClientesPag(pageable, idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/establecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearEstablecimientoCliente(@RequestBody EstablecimientoClienteDTO establecimientoClienteDTO){
        System.out.println(establecimientoClienteDTO);
        return establecimientoClienteService.crearEstablecimientoCliente(establecimientoClienteDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/establecimientosClientes/{idEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarEstablecimientoClienteporId(@PathVariable Long idEstablecimientoCliente){
        return establecimientoClienteService.consultarEstablecimientoClienteporId(idEstablecimientoCliente);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @GetMapping("/establecimientosClientes/numeroDocumento/{numeroDocumentoIdentificacionEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarEstablecimientoClienteporNumeroDocumentoIdentificacion(@PathVariable String numeroDocumentoIdentificacionEstablecimientoCliente){
        return establecimientoClienteService.consultarEstablecimientoClienteporNumeroDocumentoIdentificacion(numeroDocumentoIdentificacionEstablecimientoCliente);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/establecimientosClientes")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarEstablecimientoCliente(@RequestBody EstablecimientoClienteDTO establecimientoClienteDTO){
        return establecimientoClienteService.actualizarEstablecimientoCliente(establecimientoClienteDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/establecimientosClientes/{idEstablecimientoCliente}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarEstablecimientoCliente(@PathVariable Long idEstablecimientoCliente){
        return establecimientoClienteService.eliminarEstablecimientoCliente(idEstablecimientoCliente);
    }
}
