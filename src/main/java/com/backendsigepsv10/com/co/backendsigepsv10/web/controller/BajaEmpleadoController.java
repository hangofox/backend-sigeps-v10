//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.BajaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.BajaEmpleadoService;
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
public class BajaEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private BajaEmpleadoService bajaEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/bajasEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idBajaEmpleado,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(bajaEmpleadoService.contarTotalRegistros(idBajaEmpleado, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/bajasEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<BajaEmpleadoDTO>> listarBajasEmpleadosLista(
            @RequestParam(required = false) Long idBajaEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(bajaEmpleadoService.listarBajasEmpleados(idBajaEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/bajasEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<BajaEmpleadoDTO>> listarBajasEmpleadosPag(
            @RequestParam(required = false) Long idBajaEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(bajaEmpleadoService.listarBajasEmpleadosPag(pageable, idBajaEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/bajasEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearBajaEmpleado(@RequestBody BajaEmpleadoDTO bajaEmpleadoDTO){
        System.out.println(bajaEmpleadoDTO);
        return bajaEmpleadoService.crearBajaEmpleado(bajaEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/bajasEmpleados/{idBajaEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarBajaEmpleadoporId(@PathVariable Long idBajaEmpleado){
        return bajaEmpleadoService.consultarBajaEmpleadoporId(idBajaEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE CONTRATO O ACTO ADMINISTRATIVO:
    @GetMapping("/bajasEmpleados/numeroContrato/{numeroContratoOActoAdmvoNombEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarBajaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(@PathVariable String numeroContratoOActoAdmvoNombEmpleado){
        return bajaEmpleadoService.consultarBajaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(numeroContratoOActoAdmvoNombEmpleado);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/bajasEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarBajaEmpleado(@RequestBody BajaEmpleadoDTO bajaEmpleadoDTO){
        return bajaEmpleadoService.actualizarBajaEmpleado(bajaEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/bajasEmpleados/{idBajaEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarBajaEmpleado(@PathVariable Long idBajaEmpleado){
        return bajaEmpleadoService.eliminarBajaEmpleado(idBajaEmpleado);
    }
}
