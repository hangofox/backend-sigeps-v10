//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.FuncionalidadDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Funcionalidad;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class FuncionalidadDAO {
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param funcionalidadDTO
    * Recibe un DTO para crear un objeto funcionalidad.
    * @return funcionalidad
    */
    public Funcionalidad funcionalidad(FuncionalidadDTO funcionalidadDTO){
        Funcionalidad funcionalidad = new Funcionalidad();
        funcionalidad.setIdFuncionalidad(funcionalidadDTO.getIdFuncionalidad());
        funcionalidad.setNombreFuncionalidad(funcionalidadDTO.getNombreFuncionalidad().toUpperCase());
        funcionalidad.setNombreIconoMenuPrincipalFuncionalidad(funcionalidadDTO.getNombreIconoMenuPrincipalFuncionalidad());
        funcionalidad.setLabelMenuPrincipalFuncionalidad(funcionalidadDTO.getLabelMenuPrincipalFuncionalidad());
        
        return funcionalidad;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param funcionalidad
    * Recibe un DTO para un objeto funcionalidad para crear un DTO.
    * @return funcionalidadDTO
    */
    public FuncionalidadDTO funcionalidadDTO(Funcionalidad funcionalidad){
        FuncionalidadDTO funcionalidadDTO = new FuncionalidadDTO();
        funcionalidadDTO.setIdFuncionalidad(funcionalidad.getIdFuncionalidad());
        funcionalidadDTO.setNombreFuncionalidad(funcionalidad.getNombreFuncionalidad().toUpperCase());
        funcionalidadDTO.setNombreIconoMenuPrincipalFuncionalidad(funcionalidad.getNombreIconoMenuPrincipalFuncionalidad());
        funcionalidadDTO.setLabelMenuPrincipalFuncionalidad(funcionalidad.getLabelMenuPrincipalFuncionalidad());
        
        return funcionalidadDTO;
    }
}
