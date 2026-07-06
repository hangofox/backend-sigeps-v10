//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AltaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface AltaEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idAltaEmpleado, String keyword);
    List<AltaEmpleadoDTO> listarAltasEmpleados(Long idAltaEmpleado, String keyword, String orderBy, String orderMode);
    Slice<AltaEmpleadoDTO> listarAltasEmpleadosPag(Pageable pageable, Long idAltaEmpleado, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearAltaEmpleado(AltaEmpleadoDTO altaEmpleadoDTO);
    RespuestaDTO consultarAltaEmpleadoporId(Long idAltaEmpleado);
    RespuestaDTO consultarAltaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado);
    RespuestaDTO actualizarAltaEmpleado(AltaEmpleadoDTO altaEmpleadoDTO);
    RespuestaDTO eliminarAltaEmpleado(Long idAltaEmpleado);
}
