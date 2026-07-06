//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoNovedadEmpleadoService;
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
public class TipoNovedadEmpleadoController {

    @Autowired//INYECTAMOS EL SERVICIO.
    private TipoNovedadEmpleadoService tipoNovedadEmpleadoService;

    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).

    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @GetMapping("/tiposNovedadesEmpleados/count")//DECLARACIÓN DEL MAPEO DEL CRUD CONTAR REGISTROS.
    public ResponseEntity<Long> contarTotalRegistros(
            @RequestParam(required = false) Long idTipoNovedadEmpleado,
            @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(tipoNovedadEmpleadoService.contarTotalRegistros(idTipoNovedadEmpleado, keyword), HttpStatus.OK);
    }

    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @GetMapping("/tiposNovedadesEmpleados/lista")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<TipoNovedadEmpleadoDTO>> listarTiposNovedadesEmpleadosLista(
            @RequestParam(required = false) Long idTipoNovedadEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode) {
        return new ResponseEntity<>(tipoNovedadEmpleadoService.listarTiposNovedadesEmpleados(idTipoNovedadEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }

    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @GetMapping("/tiposNovedadesEmpleados/listaPag")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS PAGINADOS.
    public ResponseEntity<Slice<TipoNovedadEmpleadoDTO>> listarTiposNovedadesEmpleadosPag(
            @RequestParam(required = false) Long idTipoNovedadEmpleado,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "ASC") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(tipoNovedadEmpleadoService.listarTiposNovedadesEmpleadosPag(pageable, idTipoNovedadEmpleado, keyword, orderBy, orderMode), HttpStatus.OK);
    }

    //CREAR REGISTRO:
    @PostMapping("/tiposNovedadesEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearTipoNovedadEmpleado(@RequestBody TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO){
        System.out.println(tipoNovedadEmpleadoDTO);
        return tipoNovedadEmpleadoService.crearTipoNovedadEmpleado(tipoNovedadEmpleadoDTO);
    }

    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/tiposNovedadesEmpleados/{idTipoNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoNovedadEmpleadoporId(@PathVariable Long idTipoNovedadEmpleado){
        return tipoNovedadEmpleadoService.consultarTipoNovedadEmpleadoporId(idTipoNovedadEmpleado);
    }

    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/tiposNovedadesEmpleados/nombre/{nombreTipoNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarTipoNovedadEmpleadoporNombre(@PathVariable String nombreTipoNovedadEmpleado){
        return tipoNovedadEmpleadoService.consultarTipoNovedadEmpleadoporNombre(nombreTipoNovedadEmpleado);
    }

    //MODIFICAR REGISTRO:
    @PutMapping("/tiposNovedadesEmpleados")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoNovedadEmpleado(@RequestBody TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO){
        return tipoNovedadEmpleadoService.actualizarTipoNovedadEmpleado(tipoNovedadEmpleadoDTO);
    }

    //ELIMINAR REGISTRO:
    @DeleteMapping("/tiposNovedadesEmpleados/{idTipoNovedadEmpleado}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoNovedadEmpleado(@PathVariable Long idTipoNovedadEmpleado){
        return tipoNovedadEmpleadoService.eliminarTipoNovedadEmpleado(idTipoNovedadEmpleado);
    }
}
