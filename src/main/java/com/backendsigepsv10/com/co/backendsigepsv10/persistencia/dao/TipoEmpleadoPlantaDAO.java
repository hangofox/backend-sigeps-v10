//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoEmpleadoPlantaDAO {

    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param tipoEmpleadoPlantaDTO
    * Recibe un DTO para crear un objeto tipoEmpleadoPlanta.
    * @return tipoEmpleadoPlanta
    */
    public TipoEmpleadoPlanta tipoEmpleadoPlanta(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO){
        TipoEmpleadoPlanta tipoEmpleadoPlanta = new TipoEmpleadoPlanta();
        tipoEmpleadoPlanta.setIdTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO.getIdTipoEmpleadoPlanta());
        tipoEmpleadoPlanta.setNombreTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO.getNombreTipoEmpleadoPlanta().toUpperCase());
        
        return tipoEmpleadoPlanta;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param tipoEmpleadoPlanta
    * Recibe un objeto tipoEmpleadoPlanta para crear un DTO.
    * @return tipoEmpleadoPlantaDTO
    */
    public TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO(TipoEmpleadoPlanta tipoEmpleadoPlanta){
        TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO = new TipoEmpleadoPlantaDTO();
        tipoEmpleadoPlantaDTO.setIdTipoEmpleadoPlanta(tipoEmpleadoPlanta.getIdTipoEmpleadoPlanta());
        tipoEmpleadoPlantaDTO.setNombreTipoEmpleadoPlanta(tipoEmpleadoPlanta.getNombreTipoEmpleadoPlanta().toUpperCase());
        
        return tipoEmpleadoPlantaDTO;
    }
}
