//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoDocumentoIdentificacionDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TipoDocumentoIdentificacionDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param tipoDocumentoIdentificacionDTO
    * Recibe un DTO para crear un objeto tipoDocumentoIdentificacion.
    * @return tipoDocumentoIdentificacion
    */
    public TipoDocumentoIdentificacion tipoDocumentoIdentificacion(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO){
        TipoDocumentoIdentificacion tipoDocumentoIdentificacion = new TipoDocumentoIdentificacion();
        tipoDocumentoIdentificacion.setIdTipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO.getIdTipoDocumentoIdentificacion());
        tipoDocumentoIdentificacion.setNombreTipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO.getNombreTipoDocumentoIdentificacion().toUpperCase());
        
        return tipoDocumentoIdentificacion;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 09/06/2026.
    * @param tipoDocumentoIdentificacion
    * Recibe un objeto tipoDocumentoIdentificacion para crear un DTO.
    * @return tipoDocumentoIdentificacionDTO
    */
    public TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO(TipoDocumentoIdentificacion tipoDocumentoIdentificacion){
        TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO = new TipoDocumentoIdentificacionDTO();
        tipoDocumentoIdentificacionDTO.setIdTipoDocumentoIdentificacion(tipoDocumentoIdentificacion.getIdTipoDocumentoIdentificacion());
        tipoDocumentoIdentificacionDTO.setNombreTipoDocumentoIdentificacion(tipoDocumentoIdentificacion.getNombreTipoDocumentoIdentificacion().toUpperCase());
        
        return tipoDocumentoIdentificacionDTO;
    }
}
