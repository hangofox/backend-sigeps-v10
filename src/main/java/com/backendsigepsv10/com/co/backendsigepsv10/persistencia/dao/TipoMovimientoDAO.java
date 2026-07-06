//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoMovimientoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoMovimiento;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoMovimientoDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param tipoMovimientoDTO
    * Recibe un DTO para crear un objeto tipoMovimiento.
    * @return tipoMovimiento
    */
    public TipoMovimiento tipoMovimiento(TipoMovimientoDTO tipoMovimientoDTO){
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setIdTipoMovimiento(tipoMovimientoDTO.getIdTipoMovimiento());
        tipoMovimiento.setNombreTipoMovimiento(tipoMovimientoDTO.getNombreTipoMovimiento().toUpperCase());
        
        return tipoMovimiento;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param tipoMovimiento
    * Recibe un objeto tipoMovimiento para crear un DTO.
    * @return tipoMovimientoDTO
    */
    public TipoMovimientoDTO tipoMovimientoDTO(TipoMovimiento tipoMovimiento){
        TipoMovimientoDTO tipoMovimientoDTO = new TipoMovimientoDTO();
        tipoMovimientoDTO.setIdTipoMovimiento(tipoMovimiento.getIdTipoMovimiento());
        tipoMovimientoDTO.setNombreTipoMovimiento(tipoMovimiento.getNombreTipoMovimiento().toUpperCase());
        
        return tipoMovimientoDTO;
    }
}
