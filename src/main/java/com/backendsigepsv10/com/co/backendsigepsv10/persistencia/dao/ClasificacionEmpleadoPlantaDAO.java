//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ClasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoPlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class ClasificacionEmpleadoPlantaDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoEmpleadoPlantaRepository tipoEmpleadoPlantaRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoEmpleadoPlantaDAO tipoEmpleadoPlantaDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param clasificacionEmpleadoPlantaDTO
    * Recibe un DTO para crear un objeto clasificacionEmpleadoPlanta.
    * @return clasificacionEmpleadoPlanta
    */
    public ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO){
        ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta = new ClasificacionEmpleadoPlanta();
        clasificacionEmpleadoPlanta.setIdClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getIdClasificacionEmpleadoPlanta());
        clasificacionEmpleadoPlanta.setNombreClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getNombreClasificacionEmpleadoPlanta().toUpperCase());
        
        //MAPEAR TIPO DE EMPLEADO DE PLANTA RELACIONADO.
        if (clasificacionEmpleadoPlantaDTO.getTipoEmpleadoPlantaDTO() != null && clasificacionEmpleadoPlantaDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(clasificacionEmpleadoPlanta::setTipoEmpleadoPlanta);
        }
        
        return clasificacionEmpleadoPlanta;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param clasificacionEmpleadoPlanta
    * Recibe un objeto clasificacionEmpleadoPlanta para crear un DTO.
    * @return clasificacionEmpleadoPlantaDTO
    */
    public ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO(ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta){
        ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO = new ClasificacionEmpleadoPlantaDTO();
        clasificacionEmpleadoPlantaDTO.setIdClasificacionEmpleadoPlanta(clasificacionEmpleadoPlanta.getIdClasificacionEmpleadoPlanta());
        clasificacionEmpleadoPlantaDTO.setNombreClasificacionEmpleadoPlanta(clasificacionEmpleadoPlanta.getNombreClasificacionEmpleadoPlanta().toUpperCase());
        
        //MAPEAR TIPO DE EMPLEADO DE PLANTA RELACIONADO.
        if (clasificacionEmpleadoPlanta.getTipoEmpleadoPlanta() != null && clasificacionEmpleadoPlanta.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(clasificacionEmpleadoPlanta.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(tipoEmpleadoPlanta -> clasificacionEmpleadoPlantaDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlanta)));
        }
        
        return clasificacionEmpleadoPlantaDTO;
    }
}
