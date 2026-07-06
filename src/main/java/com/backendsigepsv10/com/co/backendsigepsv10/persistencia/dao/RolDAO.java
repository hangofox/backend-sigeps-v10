//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RolDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Funcionalidad;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Rol;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.FuncionalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class RolDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private FuncionalidadRepository funcionalidadRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private FuncionalidadDAO funcionalidadDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param rolDTO
    * Recibe un DTO para crear un objeto rol.
    * @return rol
    */
    public Rol rol(RolDTO rolDTO){
        Rol rol = new Rol();
        rol.setIdRol(rolDTO.getIdRol());
        rol.setNombreRol(rolDTO.getNombreRol().toUpperCase());
        
        //MAPEAR FUNCIONALIDAD RELACIONADA.
        if (rolDTO.getFuncionalidadDTO() != null && rolDTO.getFuncionalidadDTO().getIdFuncionalidad() != null) {
           Optional<Funcionalidad> funcionalidadFk = funcionalidadRepository.findByIdFuncionalidad(rolDTO.getFuncionalidadDTO().getIdFuncionalidad());
           funcionalidadFk.ifPresent(rol::setFuncionalidad);
        }
        
        return rol;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param rol
    * Recibe un objeto rol para crear un DTO.
    * @return rolDTO
    */
    public RolDTO rolDTO(Rol rol){
        RolDTO rolDTO = new RolDTO();
        rolDTO.setIdRol(rol.getIdRol());
        rolDTO.setNombreRol(rol.getNombreRol().toUpperCase());
        
        //MAPEAR FUNCIONALIDAD RELACIONADA.
        if (rol.getFuncionalidad() != null && rol.getFuncionalidad().getIdFuncionalidad() != null) {
           Optional<Funcionalidad> funcionalidadFk = funcionalidadRepository.findByIdFuncionalidad(rol.getFuncionalidad().getIdFuncionalidad());
           funcionalidadFk.ifPresent(funcionalidad -> rolDTO.setFuncionalidadDTO(funcionalidadDAO.funcionalidadDTO(funcionalidad)));
        }
        
        return rolDTO;
    }
}
