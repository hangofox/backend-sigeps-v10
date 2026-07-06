//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoUsuario;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoUsuarioDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param tipoUsuarioDTO
    * Recibe un DTO para crear un objeto tipoUsuario.
    * @return tipoUsuario
    */
    public TipoUsuario tipoUsuario(TipoUsuarioDTO tipoUsuarioDTO){
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(tipoUsuarioDTO.getIdTipoUsuario());
        tipoUsuario.setNombreTipoUsuario(tipoUsuarioDTO.getNombreTipoUsuario().toUpperCase());
        
        return tipoUsuario;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param tipoUsuario
    * Recibe un objeto tipoUsuario para crear un DTO.
    * @return tipoUsuarioDTO
    */
    public TipoUsuarioDTO tipoUsuarioDTO(TipoUsuario tipoUsuario){
        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
        tipoUsuarioDTO.setIdTipoUsuario(tipoUsuario.getIdTipoUsuario());
        tipoUsuarioDTO.setNombreTipoUsuario(tipoUsuario.getNombreTipoUsuario().toUpperCase());
        
        return tipoUsuarioDTO;
    }
}
