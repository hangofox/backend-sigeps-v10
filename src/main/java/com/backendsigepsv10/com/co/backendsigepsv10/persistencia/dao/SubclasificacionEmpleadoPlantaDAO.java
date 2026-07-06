//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SubclasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SubclasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ClasificacionEmpleadoPlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class SubclasificacionEmpleadoPlantaDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ClasificacionEmpleadoPlantaRepository clasificacionEmpleadoPlantaRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private ClasificacionEmpleadoPlantaDAO clasificacionEmpleadoPlantaDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param subclasificacionEmpleadoPlantaDTO
    * Recibe un DTO para crear un objeto subclasificacionEmpleadoPlanta.
    * @return subclasificacionEmpleadoPlanta
    */
    public SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO){
        SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta = new SubclasificacionEmpleadoPlanta();
        subclasificacionEmpleadoPlanta.setIdSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getIdSubclasificacionEmpleadoPlanta());
        subclasificacionEmpleadoPlanta.setNombreSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getNombreSubclasificacionEmpleadoPlanta().toUpperCase());
        
        //MAPEAR CLASIFICACION DEL EMPLEADO DE PLANTA RELACIONADO.
        if (subclasificacionEmpleadoPlantaDTO.getClasificacionEmpleadoPlantaDTO() != null && subclasificacionEmpleadoPlantaDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(subclasificacionEmpleadoPlanta::setClasificacionEmpleadoPlanta);
        }
        
        return subclasificacionEmpleadoPlanta;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param subclasificacionEmpleadoPlanta
    * Recibe un objeto subclasificacionEmpleadoPlanta para crear un DTO.
    * @return subclasificacionEmpleadoPlantaDTO
    */
    public SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO(SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta){
        SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO = new SubclasificacionEmpleadoPlantaDTO();
        subclasificacionEmpleadoPlantaDTO.setIdSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlanta.getIdSubclasificacionEmpleadoPlanta());
        subclasificacionEmpleadoPlantaDTO.setNombreSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlanta.getNombreSubclasificacionEmpleadoPlanta().toUpperCase());
        
        //MAPEAR CLASIFICACION DEL EMPLEADO DE PLANTA RELACIONADO.
        if (subclasificacionEmpleadoPlanta.getClasificacionEmpleadoPlanta() != null && subclasificacionEmpleadoPlanta.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(subclasificacionEmpleadoPlanta.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(clasificacionEmpleadoPlanta -> subclasificacionEmpleadoPlantaDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlanta)));
        }
        
        return subclasificacionEmpleadoPlantaDTO;
    }
}
