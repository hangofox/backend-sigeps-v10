//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialNovedadEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoNovedadEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EmpleadoRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoNovedadEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class HistorialNovedadEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoNovedadEmpleadoRepository tipoNovedadEmpleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoNovedadEmpleadoDAO tipoNovedadEmpleadoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 13/06/2026.
    * @param historialNovedadEmpleadoDTO
    * Recibe un DTO para crear un objeto historialNovedadEmpleado.
    * @return historialNovedadEmpleado
    */
    public HistorialNovedadEmpleado historialNovedadEmpleado(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO){
        HistorialNovedadEmpleado historialNovedadEmpleado = new HistorialNovedadEmpleado();
        historialNovedadEmpleado.setIdHistorialNovedadEmpleado(historialNovedadEmpleadoDTO.getIdHistorialNovedadEmpleado());
        historialNovedadEmpleado.setFechaHMSHistorialNovedadEmpleado(historialNovedadEmpleadoDTO.getFechaHMSHistorialNovedadEmpleado());
        historialNovedadEmpleado.setDescripcionHistorialNovedadEmpleado(historialNovedadEmpleadoDTO.getDescripcionHistorialNovedadEmpleado() != null ? historialNovedadEmpleadoDTO.getDescripcionHistorialNovedadEmpleado().toUpperCase() : null);
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (historialNovedadEmpleadoDTO.getEmpleadoDTO() != null && historialNovedadEmpleadoDTO.getEmpleadoDTO().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(historialNovedadEmpleadoDTO.getEmpleadoDTO().getIdEmpleado());
           empleadoFk.ifPresent(historialNovedadEmpleado::setEmpleado);
        }
        
        //MAPEAR TIPO NOVEDAD EMPLEADO RELACIONADO.
        if (historialNovedadEmpleadoDTO.getTipoNovedadEmpleadoDTO() != null && historialNovedadEmpleadoDTO.getTipoNovedadEmpleadoDTO().getIdTipoNovedadEmpleado() != null) {
           Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoFk = tipoNovedadEmpleadoRepository.findByIdTipoNovedadEmpleado(historialNovedadEmpleadoDTO.getTipoNovedadEmpleadoDTO().getIdTipoNovedadEmpleado());
           tipoNovedadEmpleadoFk.ifPresent(historialNovedadEmpleado::setTipoNovedadEmpleado);
        }
        
        return historialNovedadEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 13/06/2026.
    * @param historialNovedadEmpleado
    * Recibe un objeto historialNovedadEmpleado para crear un DTO.
    * @return historialNovedadEmpleadoDTO
    */
    public HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO(HistorialNovedadEmpleado historialNovedadEmpleado){
        HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO = new HistorialNovedadEmpleadoDTO();
        historialNovedadEmpleadoDTO.setIdHistorialNovedadEmpleado(historialNovedadEmpleado.getIdHistorialNovedadEmpleado());
        historialNovedadEmpleadoDTO.setFechaHMSHistorialNovedadEmpleado(historialNovedadEmpleado.getFechaHMSHistorialNovedadEmpleado());
        historialNovedadEmpleadoDTO.setDescripcionHistorialNovedadEmpleado(historialNovedadEmpleado.getDescripcionHistorialNovedadEmpleado());
        
        //MAPEAR EMPLEADO RELACIONADO.
        if (historialNovedadEmpleado.getEmpleado() != null && historialNovedadEmpleado.getEmpleado().getIdEmpleado() != null) {
           Optional<Empleado> empleadoFk = empleadoRepository.findByIdEmpleado(historialNovedadEmpleado.getEmpleado().getIdEmpleado());
           empleadoFk.ifPresent(empl -> historialNovedadEmpleadoDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empl)));
        }
        
        //MAPEAR TIPO NOVEDAD EMPLEADO RELACIONADO.
        if (historialNovedadEmpleado.getTipoNovedadEmpleado() != null && historialNovedadEmpleado.getTipoNovedadEmpleado().getIdTipoNovedadEmpleado() != null) {
           Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoFk = tipoNovedadEmpleadoRepository.findByIdTipoNovedadEmpleado(historialNovedadEmpleado.getTipoNovedadEmpleado().getIdTipoNovedadEmpleado());
           tipoNovedadEmpleadoFk.ifPresent(tipo -> historialNovedadEmpleadoDTO.setTipoNovedadEmpleadoDTO(tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipo)));
        }
        
        return historialNovedadEmpleadoDTO;
    }
}
