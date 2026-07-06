//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TurnoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Turno;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TurnoDAO {
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param turnoDTO
    * Recibe un DTO para crear un objeto turno.
    * @return turno
    */
    public Turno turno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        turno.setIdTurno(turnoDTO.getIdTurno());
        turno.setNombreTurno(turnoDTO.getNombreTurno().toUpperCase());
        turno.setHMSIniciacionTurno(turnoDTO.getHMSIniciacionTurno());
        turno.setHMSFinalizacionTurno(turnoDTO.getHMSFinalizacionTurno());
        turno.setEstadoTurno(turnoDTO.getEstadoTurno().toUpperCase());
        
        return turno;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 11/06/2026.
    * @param turno
    * Recibe un objeto turno para crear un DTO.
    * @return turnoDTO
    */
    public TurnoDTO turnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setIdTurno(turno.getIdTurno());
        turnoDTO.setNombreTurno(turno.getNombreTurno().toUpperCase());
        turnoDTO.setHMSIniciacionTurno(turno.getHMSIniciacionTurno());
        turnoDTO.setHMSFinalizacionTurno(turno.getHMSFinalizacionTurno());
        turnoDTO.setEstadoTurno(turno.getEstadoTurno().toUpperCase());
        
        return turnoDTO;
    }
}
