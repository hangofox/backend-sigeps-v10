//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PaisMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PaisMundo;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class PaisMundoDAO {
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param paisMundoDTO
    * Recibe un DTO para crear un objeto paisMundo.
    * @return paisMundo
    */
    public PaisMundo paisMundo(PaisMundoDTO paisMundoDTO){
        PaisMundo paisMundo = new PaisMundo();
        paisMundo.setIdPaisMundo(paisMundoDTO.getIdPaisMundo());
        paisMundo.setNombrePaisMundo(paisMundoDTO.getNombrePaisMundo().toUpperCase());
        
        return paisMundo;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 10/06/2026.
    * @param paisMundo
    * Recibe un objeto paisMundo para crear un DTO.
    * @return paisMundoDTO
    */
    public PaisMundoDTO paisMundoDTO(PaisMundo paisMundo){
        PaisMundoDTO paisMundoDTO = new PaisMundoDTO();
        paisMundoDTO.setIdPaisMundo(paisMundo.getIdPaisMundo());
        paisMundoDTO.setNombrePaisMundo(paisMundo.getNombrePaisMundo().toUpperCase());
        
        return paisMundoDTO;
    }
}
