//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SubclasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ClasificacionEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.SubclasificacionEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoDocumentoIdentificacionRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoPlantaRepository;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class EmpleadoDAO {
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoDocumentoIdentificacionRepository tipoDocumentoIdentificacionRepository;
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoDocumentoIdentificacionDAO tipoDocumentoIdentificacionDAO;
    
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
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param empleadoDTO
    * Recibe un DTO para crear un objeto empleado.
    * @return empleado
    */
    public Empleado empleado(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(empleadoDTO.getIdEmpleado());
        empleado.setNumeroDocumentoIdentificacionEmpleado(empleadoDTO.getNumeroDocumentoIdentificacionEmpleado());
        empleado.setNombresEmpleado(empleadoDTO.getNombresEmpleado().toUpperCase());
        empleado.setPrimerApellidoEmpleado(empleadoDTO.getPrimerApellidoEmpleado().toUpperCase());
        empleado.setSegundoApellidoEmpleado(empleadoDTO.getSegundoApellidoEmpleado().toUpperCase());
        empleado.setNombreArchivoFotoExtensionOFormatoEmpleado(empleadoDTO.getNombreArchivoFotoExtensionOFormatoEmpleado());
        empleado.setDireccionEmpleado(empleadoDTO.getDireccionEmpleado().toUpperCase());
        empleado.setTelefonoEmpleado(empleadoDTO.getTelefonoEmpleado().toUpperCase());
        empleado.setMovilEmpleado(empleadoDTO.getMovilEmpleado().toUpperCase());
        empleado.setCorreoElectronicoPersonalEmpleado(empleadoDTO.getCorreoElectronicoPersonalEmpleado().toUpperCase());
        empleado.setCorreoElectronicoInstitucionalEmpleado(empleadoDTO.getCorreoElectronicoInstitucionalEmpleado().toUpperCase());
        empleado.setPaisOrigenEmpleado(empleadoDTO.getPaisOrigenEmpleado().toUpperCase());
        empleado.setDepartamentooEstadoOrigenEmpleado(empleadoDTO.getDepartamentooEstadoOrigenEmpleado().toUpperCase());
        empleado.setCiudadOrigenEmpleado(empleadoDTO.getCiudadOrigenEmpleado().toUpperCase());
        empleado.setFechaHMSIngresoEmpleado(empleadoDTO.getFechaHMSIngresoEmpleado());
        empleado.setFechaHMSModificacionEmpleado(empleadoDTO.getFechaHMSModificacionEmpleado());
        empleado.setEstadoEmpleado(empleadoDTO.getEstadoEmpleado().toUpperCase());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (empleadoDTO.getTipoDocumentoIdentificacionDTO() != null && empleadoDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(empleadoDTO.getTipoDocumentoIdentificacionDTO().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(empleado::setTipoDocumentoIdentificacion);
        }
        
        //MAPEAR TIPO DE EMPLEADO RELACIONADO.
        if (empleadoDTO.getTipoEmpleadoDTO() != null && empleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado() != null) {
           Optional<TipoEmpleado> tipoEmpleadoFk = tipoEmpleadoRepository.findByIdTipoEmpleado(empleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado());
           tipoEmpleadoFk.ifPresent(empleado::setTipoEmpleado);
        }
        
        //MAPEAR TIPO DE EMPLEADO PLANTA RELACIONADO.
        if (empleadoDTO.getTipoEmpleadoPlantaDTO() != null && empleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(empleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(empleado::setTipoEmpleadoPlanta);
        }
        
        //MAPEAR CLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (empleadoDTO.getClasificacionEmpleadoPlantaDTO() != null && empleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(empleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(empleado::setClasificacionEmpleadoPlanta);
        }
        
        //MAPEAR SUBCLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (empleadoDTO.getSubclasificacionEmpleadoPlantaDTO() != null && empleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta() != null) {
           Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaFk = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(empleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta());
           subclasificacionEmpleadoPlantaFk.ifPresent(empleado::setSubclasificacionEmpleadoPlanta);
        }
        
        return empleado;
    }
    
    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
    * @Since 12/06/2026.
    * @param empleado
    * Recibe un objeto empleado para crear un DTO.
    * @return empleadoDTO
    */
    public EmpleadoDTO empleadoDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setIdEmpleado(empleado.getIdEmpleado());
        empleadoDTO.setNumeroDocumentoIdentificacionEmpleado(empleado.getNumeroDocumentoIdentificacionEmpleado());
        empleadoDTO.setNombresEmpleado(empleado.getNombresEmpleado().toUpperCase());
        empleadoDTO.setPrimerApellidoEmpleado(empleado.getPrimerApellidoEmpleado().toUpperCase());
        empleadoDTO.setSegundoApellidoEmpleado(empleado.getSegundoApellidoEmpleado().toUpperCase());
        empleadoDTO.setNombreArchivoFotoExtensionOFormatoEmpleado(empleado.getNombreArchivoFotoExtensionOFormatoEmpleado());
        empleadoDTO.setDireccionEmpleado(empleado.getDireccionEmpleado().toUpperCase());
        empleadoDTO.setTelefonoEmpleado(empleado.getTelefonoEmpleado().toUpperCase());
        empleadoDTO.setMovilEmpleado(empleado.getMovilEmpleado().toUpperCase());
        empleadoDTO.setCorreoElectronicoPersonalEmpleado(empleado.getCorreoElectronicoPersonalEmpleado().toUpperCase());
        empleadoDTO.setCorreoElectronicoInstitucionalEmpleado(empleado.getCorreoElectronicoInstitucionalEmpleado().toUpperCase());
        empleadoDTO.setPaisOrigenEmpleado(empleado.getPaisOrigenEmpleado().toUpperCase());
        empleadoDTO.setDepartamentooEstadoOrigenEmpleado(empleado.getDepartamentooEstadoOrigenEmpleado().toUpperCase());
        empleadoDTO.setCiudadOrigenEmpleado(empleado.getCiudadOrigenEmpleado().toUpperCase());
        empleadoDTO.setFechaHMSIngresoEmpleado(empleado.getFechaHMSIngresoEmpleado());
        empleadoDTO.setFechaHMSModificacionEmpleado(empleado.getFechaHMSModificacionEmpleado());
        empleadoDTO.setEstadoEmpleado(empleado.getEstadoEmpleado().toUpperCase());
        
        //MAPEAR TIPO DE DOCUMENTO DE IDENTIFICACION RELACIONADO.
        if (empleado.getTipoDocumentoIdentificacion() != null && empleado.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion() != null) {
           Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionFk = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(empleado.getTipoDocumentoIdentificacion().getIdTipoDocumentoIdentificacion());
           tipoDocumentoIdentificacionFk.ifPresent(tipoDocumentoIdentificacion -> empleadoDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacion)));
        }
        
        //MAPEAR TIPO DE EMPLEADO RELACIONADO.
        if (empleado.getTipoEmpleado() != null && empleado.getTipoEmpleado().getIdTipoEmpleado() != null) {
           Optional<TipoEmpleado> tipoEmpleadoFk = tipoEmpleadoRepository.findByIdTipoEmpleado(empleado.getTipoEmpleado().getIdTipoEmpleado());
           tipoEmpleadoFk.ifPresent(tipoEmpleado -> empleadoDTO.setTipoEmpleadoDTO(tipoEmpleadoDAO.tipoEmpleadoDTO(tipoEmpleado)));
        }
        
        //MAPEAR TIPO DE EMPLEADO PLANTA RELACIONADO.
        if (empleado.getTipoEmpleadoPlanta() != null && empleado.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta() != null) {
           Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaFk = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(empleado.getTipoEmpleadoPlanta().getIdTipoEmpleadoPlanta());
           tipoEmpleadoPlantaFk.ifPresent(tipoEmpleadoPlanta -> empleadoDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlanta)));
        }
        
        //MAPEAR CLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (empleado.getClasificacionEmpleadoPlanta() != null && empleado.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta() != null) {
           Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaFk = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(empleado.getClasificacionEmpleadoPlanta().getIdClasificacionEmpleadoPlanta());
           clasificacionEmpleadoPlantaFk.ifPresent(clasificacionEmpleadoPlanta -> empleadoDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlanta)));
        }
        
        //MAPEAR SUBCLASIFICACION DE EMPLEADO PLANTA RELACIONADA.
        if (empleado.getSubclasificacionEmpleadoPlanta() != null && empleado.getSubclasificacionEmpleadoPlanta().getIdSubclasificacionEmpleadoPlanta() != null) {
           Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaFk = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(empleado.getSubclasificacionEmpleadoPlanta().getIdSubclasificacionEmpleadoPlanta());
           subclasificacionEmpleadoPlantaFk.ifPresent(subclasificacionEmpleadoPlanta -> empleadoDTO.setSubclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlanta)));
        }
        
        return empleadoDTO;
    }
}
