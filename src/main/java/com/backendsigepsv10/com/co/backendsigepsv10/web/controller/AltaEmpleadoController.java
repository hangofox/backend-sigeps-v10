//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AltaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.AltaEmpleadoService;
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
public class AltaEmpleadoController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private AltaEmpleadoService altaEmpleadoService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/altasEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idAltaEmpleado,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(altaEmpleadoService.contarTotalRegistros(idAltaEmpleado, keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/altasEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<AltaEmpleadoDTO>> listarAltasEmpleadosLista(
            @RequestParam(required = false) Long idAltaEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(altaEmpleadoService.listarAltasEmpleados(idAltaEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/altasEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<AltaEmpleadoDTO>> listarAltasEmpleadosPag(
            @RequestParam(required = false) Long idAltaEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(altaEmpleadoService.listarAltasEmpleadosPag(pageable, idAltaEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/altasEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearAltaEmpleado(@RequestBody AltaEmpleadoDTO altaEmpleadoDTO){
        System.out.println(altaEmpleadoDTO);
        return altaEmpleadoService.crearAltaEmpleado(altaEmpleadoDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/altasEmpleados/{idAltaEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarAltaEmpleadoporId(@PathVariable Long idAltaEmpleado){
        return altaEmpleadoService.consultarAltaEmpleadoporId(idAltaEmpleado);
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE CONTRATO O ACTO ADMINISTRATIVO:
    @GetMapping("/altasEmpleados/numeroContrato/{numeroContratoOActoAdmvoNombEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarAltaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(@PathVariable String numeroContratoOActoAdmvoNombEmpleado){
        return altaEmpleadoService.consultarAltaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(numeroContratoOActoAdmvoNombEmpleado);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/altasEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAltaEmpleado(@RequestBody AltaEmpleadoDTO altaEmpleadoDTO){
        return altaEmpleadoService.actualizarAltaEmpleado(altaEmpleadoDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/altasEmpleados/{idAltaEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAltaEmpleado(@PathVariable Long idAltaEmpleado){
        return altaEmpleadoService.eliminarAltaEmpleado(idAltaEmpleado);
    }
}
