//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.BajaEmpleadoDTO;
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
public interface BajaEmpleadoService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idBajaEmpleado, String keyword);
    List<BajaEmpleadoDTO> listarBajasEmpleados(Long idBajaEmpleado, String keyword, String orderBy, String orderMode);
    Slice<BajaEmpleadoDTO> listarBajasEmpleadosPag(Pageable pageable, Long idBajaEmpleado, String keyword, String orderBy, String orderMode);
    RespuestaDTO crearBajaEmpleado(BajaEmpleadoDTO bajaEmpleadoDTO);
    RespuestaDTO consultarBajaEmpleadoporId(Long idBajaEmpleado);
    RespuestaDTO consultarBajaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado);
    RespuestaDTO actualizarBajaEmpleado(BajaEmpleadoDTO bajaEmpleadoDTO);
    RespuestaDTO eliminarBajaEmpleado(Long idBajaEmpleado);
}
