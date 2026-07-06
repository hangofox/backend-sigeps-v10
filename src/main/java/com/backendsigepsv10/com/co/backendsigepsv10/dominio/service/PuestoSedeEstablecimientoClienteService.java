//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PuestoSedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración de la interfaz del servicio.
*/
public interface PuestoSedeEstablecimientoClienteService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    Long contarTotalRegistros(Long idPuestoEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente);
    List<PuestoSedeEstablecimientoClienteDTO> listarPuestosSedesEstablecimientosClientes(Long idPuestoSedeEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente, String orderBy, String orderMode);
    Slice<PuestoSedeEstablecimientoClienteDTO> listarPuestosSedesEstablecimientosClientesPag(Pageable pageable, Long idPuestoSedeEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente, String orderBy, String orderMode);
    RespuestaDTO crearPuestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoClienteDTO puestoEstablecimientoClienteDTO);
    RespuestaDTO consultarPuestoSedeEstablecimientoClienteporId(Long idPuestoSedeEstablecimientoCliente);
    RespuestaDTO consultarPuestoSedeEstablecimientoClienteporNombre(String nombrePuestoEstablecimientoCliente);
    RespuestaDTO actualizarPuestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO);
    RespuestaDTO eliminarPuestoSedeEstablecimientoCliente(Long idPuestoSedeEstablecimientoCliente);
}
