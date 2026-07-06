//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoNovedadEmpleado;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoNovedadEmpleadoDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param tipoNovedadEmpleadoDTO
    * Recibe un DTO para crear un objeto tipoNovedadEmpleado.
    * @return tipoNovedadEmpleado
    */
    public TipoNovedadEmpleado tipoNovedadEmpleado(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO){
        TipoNovedadEmpleado tipoNovedadEmpleado = new TipoNovedadEmpleado();
        tipoNovedadEmpleado.setIdTipoNovedadEmpleado(tipoNovedadEmpleadoDTO.getIdTipoNovedadEmpleado());
        tipoNovedadEmpleado.setNombreTipoNovedadEmpleado(tipoNovedadEmpleadoDTO.getNombreTipoNovedadEmpleado().toUpperCase());
        
        return tipoNovedadEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param tipoNovedadEmpleado
    * Recibe un objeto tipoNovedadEmpleado para crear un DTO.
    * @return tipoNovedadEmpleadoDTO
    */
    public TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO(TipoNovedadEmpleado tipoNovedadEmpleado){
        TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO = new TipoNovedadEmpleadoDTO();
        tipoNovedadEmpleadoDTO.setIdTipoNovedadEmpleado(tipoNovedadEmpleado.getIdTipoNovedadEmpleado());
        tipoNovedadEmpleadoDTO.setNombreTipoNovedadEmpleado(tipoNovedadEmpleado.getNombreTipoNovedadEmpleado().toUpperCase());
        
        return tipoNovedadEmpleadoDTO;
    }
}
