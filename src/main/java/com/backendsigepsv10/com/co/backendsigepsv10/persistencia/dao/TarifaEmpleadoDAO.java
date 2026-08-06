//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TarifaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SubclasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TarifaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoTarifaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ClasificacionEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.SubclasificacionEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoTarifaEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 05/08/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class TarifaEmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoEmpleadoDAO tipoEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoEmpleadoPlantaRepository tipoEmpleadoPlantaRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoEmpleadoPlantaDAO tipoEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ClasificacionEmpleadoPlantaRepository clasificacionEmpleadoPlantaRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private ClasificacionEmpleadoPlantaDAO clasificacionEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private SubclasificacionEmpleadoPlantaRepository subclasificacionEmpleadoPlantaRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private SubclasificacionEmpleadoPlantaDAO subclasificacionEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoTarifaEmpleadoRepository tipoTarifaEmpleadoRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoTarifaEmpleadoDAO tipoTarifaEmpleadoDAO;
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 05/08/2026.
    * @param tarifaEmpleadoDTO
    * Recibe un DTO para crear un objeto tarifaEmpleado.
    * @return tarifaEmpleado
    */
    public TarifaEmpleado tarifaEmpleado(TarifaEmpleadoDTO tarifaEmpleadoDTO){
        TarifaEmpleado tarifaEmpleado = new TarifaEmpleado();
        tarifaEmpleado.setIdTarifaEmpleado(tarifaEmpleadoDTO.getIdTarifaEmpleado());
        tarifaEmpleado.setAnioTarifaEmpleado(tarifaEmpleadoDTO.getAnioTarifaEmpleado());
        tarifaEmpleado.setValorHoraTarifaEmpleado(tarifaEmpleadoDTO.getValorHoraTarifaEmpleado());
        tarifaEmpleado.setFechaHMSIngresoTarifaEmpleado(tarifaEmpleadoDTO.getFechaHMSIngresoTarifaEmpleado());
        tarifaEmpleado.setFechaHMSModificacionTarifaEmpleado(tarifaEmpleadoDTO.getFechaHMSModificacionTarifaEmpleado());
        tarifaEmpleado.setEstadoTarifaEmpleado(tarifaEmpleadoDTO.getEstadoTarifaEmpleado() != null ? tarifaEmpleadoDTO.getEstadoTarifaEmpleado().toUpperCase() : null);
        
        //MAPEAR TIPO DE EMPLEADO RELACIONADO.
        if (tarifaEmpleadoDTO.getTipoEmpleadoDTO() != null && tarifaEmpleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado() != null) {
           Optional<TipoEmpleado> tipoEmpleadoFk = tipoEmpleadoRepository.findByIdTipoEmpleado(tarifaEmpleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado());
           tipoEmpleadoFk.ifPresent(tarifaEmpleado::setTipoEmpleado);
        }
        
        //MAPEAR TIPO DE EMPLEADO PLANTA RELACIONADO.
        if (tarifaEmpleadoDTO.getTipoEmpleadoPlantaDTO() != null && tarifaEmpleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(tarifaEmpleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(tarifaEmpleado::setTipoEmpleadoPlanta);
        }
        
        //MAPEAR CLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (tarifaEmpleadoDTO.getClasificacionEmpleadoPlantaDTO() != null && tarifaEmpleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(tarifaEmpleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(tarifaEmpleado::setClasificacionEmpleadoPlanta);
        }
        
        //MAPEAR SUBCLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (tarifaEmpleadoDTO.getSubclasificacionEmpleadoPlantaDTO() != null && tarifaEmpleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta() != null) {
           Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaFk = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(tarifaEmpleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta());
           subclasificacionEmpleadoPlantaFk.ifPresent(tarifaEmpleado::setSubclasificacionEmpleadoPlanta);
        }
        
        //MAPEAR TIPO DE TARIFA DE EMPLEADO RELACIONADO.
        if (tarifaEmpleadoDTO.getTipoTarifaEmpleadoDTO() != null && tarifaEmpleadoDTO.getTipoTarifaEmpleadoDTO().getIdTipoTarifaEmpleado() != null) {
           Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoFk = tipoTarifaEmpleadoRepository.findByIdTipoTarifaEmpleado(tarifaEmpleadoDTO.getTipoTarifaEmpleadoDTO().getIdTipoTarifaEmpleado());
           tipoTarifaEmpleadoFk.ifPresent(tarifaEmpleado::setTipoTarifaEmpleado);
        }
        
        return tarifaEmpleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 05/08/2026.
    * @param tarifaEmpleado
    * Recibe un objeto tarifaEmpleado para crear un DTO.
    * @return tarifaEmpleadoDTO
    */
    public TarifaEmpleadoDTO tarifaEmpleadoDTO(TarifaEmpleado tarifaEmpleado){
        TarifaEmpleadoDTO tarifaEmpleadoDTO = new TarifaEmpleadoDTO();
        tarifaEmpleadoDTO.setIdTarifaEmpleado(tarifaEmpleado.getIdTarifaEmpleado());
        tarifaEmpleadoDTO.setAnioTarifaEmpleado(tarifaEmpleado.getAnioTarifaEmpleado());
        tarifaEmpleadoDTO.setValorHoraTarifaEmpleado(tarifaEmpleado.getValorHoraTarifaEmpleado());
        tarifaEmpleadoDTO.setFechaHMSIngresoTarifaEmpleado(tarifaEmpleado.getFechaHMSIngresoTarifaEmpleado());
        tarifaEmpleadoDTO.setFechaHMSModificacionTarifaEmpleado(tarifaEmpleado.getFechaHMSModificacionTarifaEmpleado());
        tarifaEmpleadoDTO.setEstadoTarifaEmpleado(tarifaEmpleado.getEstadoTarifaEmpleado());
        
        //MAPEAR TIPO DE EMPLEADO RELACIONADO.
        if (tarifaEmpleado.getTipoEmpleado() != null && tarifaEmpleado.getTipoEmpleado().getIdTipoEmpleado() != null) {
           Optional<TipoEmpleado> tipoEmpleadoFk = tipoEmpleadoRepository.findByIdTipoEmpleado(tarifaEmpleado.getTipoEmpleado().getIdTipoEmpleado());
           tipoEmpleadoFk.ifPresent(tipoEmpleado -> tarifaEmpleadoDTO.setTipoEmpleadoDTO(tipoEmpleadoDAO.tipoEmpleadoDTO(tipoEmpleado)));
        }
        
        //MAPEAR TIPO DE EMPLEADO PLANTA RELACIONADO.
        if (tarifaEmpleado.getTipoEmpleadoPlanta() != null && tarifaEmpleado.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(tarifaEmpleado.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(tipoEmpleadoPlanta -> tarifaEmpleadoDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlanta)));
        }
        
        //MAPEAR CLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (tarifaEmpleado.getClasificacionEmpleadoPlanta() != null && tarifaEmpleado.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(tarifaEmpleado.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(clasificacionEmpleadoPlanta -> tarifaEmpleadoDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlanta)));
        }
        
        //MAPEAR SUBCLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (tarifaEmpleado.getSubclasificacionEmpleadoPlanta() != null && tarifaEmpleado.getSubclasificacionEmpleadoPlanta().getIdSubclasificacionEmpleadoPlanta() != null) {
           Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaFk = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(tarifaEmpleado.getSubclasificacionEmpleadoPlanta().getIdSubclasificacionEmpleadoPlanta());
           subclasificacionEmpleadoPlantaFk.ifPresent(subclasificacionEmpleadoPlanta -> tarifaEmpleadoDTO.setSubclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlanta)));
        }
        
        //MAPEAR TIPO DE TARIFA DE EMPLEADO RELACIONADO.
        if (tarifaEmpleado.getTipoTarifaEmpleado() != null && tarifaEmpleado.getTipoTarifaEmpleado().getIdTipoTarifaEmpleado() != null) {
           Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoFk = tipoTarifaEmpleadoRepository.findByIdTipoTarifaEmpleado(tarifaEmpleado.getTipoTarifaEmpleado().getIdTipoTarifaEmpleado());
           tipoTarifaEmpleadoFk.ifPresent(tipoTarifaEmpleado -> tarifaEmpleadoDTO.setTipoTarifaEmpleadoDTO(tipoTarifaEmpleadoDAO.tipoTarifaEmpleadoDTO(tipoTarifaEmpleado)));
        }
        
        return tarifaEmpleadoDTO;
    }
}
