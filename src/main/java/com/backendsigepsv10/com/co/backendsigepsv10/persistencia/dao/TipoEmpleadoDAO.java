//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleado;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoEmpleadoDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param tipoEmpleadoDTO
    * Recibe un DTO para crear un objeto tipoEmpleado.
    * @return tipoEmpleado
    */
    public TipoEmpleado tipoEmpleado(TipoEmpleadoDTO tipoEmpleadoDTO){
        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        tipoEmpleado.setIdTipoEmpleado(tipoEmpleadoDTO.getIdTipoEmpleado());
        tipoEmpleado.setNombreTipoEmpleado(tipoEmpleadoDTO.getNombreTipoEmpleado().toUpperCase());
        
        return tipoEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param tipoEmpleado
    * Recibe un objeto tipoEmpleado para crear un DTO.
    * @return tipoEmpleadoDTO
    */
    public TipoEmpleadoDTO tipoEmpleadoDTO(TipoEmpleado tipoEmpleado){
        TipoEmpleadoDTO tipoEmpleadoDTO = new TipoEmpleadoDTO();
        tipoEmpleadoDTO.setIdTipoEmpleado(tipoEmpleado.getIdTipoEmpleado());
        tipoEmpleadoDTO.setNombreTipoEmpleado(tipoEmpleado.getNombreTipoEmpleado().toUpperCase());
        
        return tipoEmpleadoDTO;
    }
}
