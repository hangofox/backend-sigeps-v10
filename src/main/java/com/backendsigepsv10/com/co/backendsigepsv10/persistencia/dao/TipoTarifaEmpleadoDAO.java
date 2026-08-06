//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoTarifaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoTarifaEmpleado;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/08/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoTarifaEmpleadoDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 04/08/2026.
    * @param tipoTarifaEmpleadoDTO
    * Recibe un DTO para crear un objeto tipoTarifaEmpleado.
    * @return tipoTarifaEmpleado
    */
    public TipoTarifaEmpleado tipoTarifaEmpleado(TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO){
        TipoTarifaEmpleado tipoTarifaEmpleado = new TipoTarifaEmpleado();
        tipoTarifaEmpleado.setIdTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getIdTipoTarifaEmpleado());
        tipoTarifaEmpleado.setNombreTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getNombreTipoTarifaEmpleado().toUpperCase());
        tipoTarifaEmpleado.setDescripcionTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getDescripcionTipoTarifaEmpleado().toUpperCase());
        tipoTarifaEmpleado.setEstadoTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getEstadoTipoTarifaEmpleado().toUpperCase());
        
        return tipoTarifaEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 04/08/2026.
    * @param tipoTarifaEmpleado
    * Recibe un objeto tipoTarifaEmpleado para crear un DTO.
    * @return tipoTarifaEmpleadoDTO
    */
    public TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO(TipoTarifaEmpleado tipoTarifaEmpleado){
        TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO = new TipoTarifaEmpleadoDTO();
        tipoTarifaEmpleadoDTO.setIdTipoTarifaEmpleado(tipoTarifaEmpleado.getIdTipoTarifaEmpleado());
        tipoTarifaEmpleadoDTO.setNombreTipoTarifaEmpleado(tipoTarifaEmpleado.getNombreTipoTarifaEmpleado());
        tipoTarifaEmpleadoDTO.setDescripcionTipoTarifaEmpleado(tipoTarifaEmpleado.getDescripcionTipoTarifaEmpleado());
        tipoTarifaEmpleadoDTO.setEstadoTipoTarifaEmpleado(tipoTarifaEmpleado.getEstadoTipoTarifaEmpleado());
        
        return tipoTarifaEmpleadoDTO;
    }
}
