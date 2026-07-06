//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface EstablecimientoClienteService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente);
    List<EstablecimientoClienteDTO> listarEstablecimientosClientes(Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente, String orderBy, String orderMode);
    Slice<EstablecimientoClienteDTO> listarEstablecimientosClientesPag(Pageable pageable, Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente, String orderBy, String orderMode);
    RespuestaDTO crearEstablecimientoCliente(EstablecimientoClienteDTO establecimientoClienteDTO);
    RespuestaDTO consultarEstablecimientoClienteporId(Long idEstablecimientoCliente);
    RespuestaDTO consultarEstablecimientoClienteporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionEstablecimientoCliente);
    RespuestaDTO actualizarEstablecimientoCliente(EstablecimientoClienteDTO establecimientoClienteDTO);
    RespuestaDTO eliminarEstablecimientoCliente(Long idEstablecimientoCliente);
}
