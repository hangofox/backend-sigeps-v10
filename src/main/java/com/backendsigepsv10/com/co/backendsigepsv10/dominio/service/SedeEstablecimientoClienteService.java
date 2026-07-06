//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SedeEstablecimientoClienteDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la interfaz del servicio.
*/
public interface SedeEstablecimientoClienteService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente);
    List<SedeEstablecimientoClienteDTO> listarSedesEstablecimientosClientes(Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente, String orderBy, String orderMode);
    Slice<SedeEstablecimientoClienteDTO> listarSedesEstablecimientosClientesPag(Pageable pageable, Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente, String orderBy, String orderMode);
    RespuestaDTO crearSedeEstablecimientoCliente(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO);
    RespuestaDTO consultarSedeEstablecimientoClienteporId(Long idSedeEstablecimientoCliente);
    RespuestaDTO consultarSedeEstablecimientoClienteporNombre(String nombreSedeEstablecimientoCliente);
    RespuestaDTO actualizarSedeEstablecimientoCliente(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO);
    RespuestaDTO eliminarSedeEstablecimientoCliente(Long idSedeEstablecimientoCliente);
}
