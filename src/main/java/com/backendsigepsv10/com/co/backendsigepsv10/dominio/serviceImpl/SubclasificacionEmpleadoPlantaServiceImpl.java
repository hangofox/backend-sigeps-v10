//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SubclasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.SubclasificacionEmpleadoPlantaService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.SubclasificacionEmpleadoPlantaDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SubclasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.SubclasificacionEmpleadoPlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class SubclasificacionEmpleadoPlantaServiceImpl implements SubclasificacionEmpleadoPlantaService {
    
    @Autowired//INYECTAMOS EL DAO.
    private SubclasificacionEmpleadoPlantaDAO subclasificacionEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private SubclasificacionEmpleadoPlantaRepository subclasificacionEmpleadoPlantaRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta) {
        return subclasificacionEmpleadoPlantaRepository.findTotalRegistros(idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<SubclasificacionEmpleadoPlantaDTO> listarSubclasificacionesEmpleadosPlantas(Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta, String orderBy, String orderMode) {
        List<SubclasificacionEmpleadoPlanta> subclasificacionesEmpleadosPlantas = subclasificacionEmpleadoPlantaRepository.findAllSubclasificacionesEmpleadosPlantas(idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta, orderBy, orderMode);
        List<SubclasificacionEmpleadoPlantaDTO> subclasificacionEmpleadoPlantaDTOS = new ArrayList<>();
        
        for (SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta : subclasificacionesEmpleadosPlantas){
            subclasificacionEmpleadoPlantaDTOS.add(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlanta));
        }
        
        return subclasificacionEmpleadoPlantaDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<SubclasificacionEmpleadoPlantaDTO> listarSubclasificacionesEmpleadosPlantasPag(Pageable pageable, Long idSubclasificacionEmpleadoPlanta, String keyword, String nombreClasificacionEmpleadoPlanta, String nombreSubclasificacionEmpleadoPlanta, String orderBy, String orderMode) {
        Slice<SubclasificacionEmpleadoPlanta> subclasificacionesEmpleadosPlantas = subclasificacionEmpleadoPlantaRepository.findAllSubclasificacionesEmpleadosPlantasPag(pageable, idSubclasificacionEmpleadoPlanta, keyword, nombreClasificacionEmpleadoPlanta, nombreSubclasificacionEmpleadoPlanta, orderBy, orderMode);
        return subclasificacionesEmpleadosPlantas.map(subclasificacionEmpleadoPlanta -> subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlanta));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearSubclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO) {
        Long maxIdSubclasificacionEmpleadoPlanta=null;
        SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlantaNombre = subclasificacionEmpleadoPlantaRepository.findByNombreSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getNombreSubclasificacionEmpleadoPlanta().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(subclasificacionEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdSubclasificacionEmpleadoPlanta = subclasificacionEmpleadoPlantaRepository.findMaxIdSubclasificacionEmpleadoPlanta();
           if (maxIdSubclasificacionEmpleadoPlanta==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdSubclasificacionEmpleadoPlanta=Long.valueOf(0);
           }
           subclasificacionEmpleadoPlantaDTO.setIdSubclasificacionEmpleadoPlanta(maxIdSubclasificacionEmpleadoPlanta+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           subclasificacionEmpleadoPlantaRepository.save(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarSubclasificacionEmpleadoPlantaporId(Long idSubclasificacionEmpleadoPlanta) {
        Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaId = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(Long.valueOf(idSubclasificacionEmpleadoPlanta));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (subclasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (subclasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarSubclasificacionEmpleadoPlantaporNombre(String nombreSubclasificacionEmpleadoPlanta) {
        Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaNombre = Optional.ofNullable(subclasificacionEmpleadoPlantaRepository.findByNombreSubclasificacionEmpleadoPlanta(String.valueOf(nombreSubclasificacionEmpleadoPlanta)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (subclasificacionEmpleadoPlantaNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (subclasificacionEmpleadoPlantaNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarSubclasificacionEmpleadoPlanta(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO) {
        Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaId = subclasificacionEmpleadoPlantaRepository.findByIdSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getIdSubclasificacionEmpleadoPlanta());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (subclasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (subclasificacionEmpleadoPlantaDTO.getNombreSubclasificacionEmpleadoPlanta().equals(subclasificacionEmpleadoPlantaId.get().getNombreSubclasificacionEmpleadoPlanta())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta = subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO);
              subclasificacionEmpleadoPlantaRepository.save(subclasificacionEmpleadoPlanta);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (subclasificacionEmpleadoPlantaDTO.getNombreSubclasificacionEmpleadoPlanta().equals(subclasificacionEmpleadoPlantaId.get().getNombreSubclasificacionEmpleadoPlanta())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlantaNombre = subclasificacionEmpleadoPlantaRepository.findByNombreSubclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO.getNombreSubclasificacionEmpleadoPlanta().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(subclasificacionEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 SubclasificacionEmpleadoPlanta subclasificacionEmpleadoPlanta = subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlanta(subclasificacionEmpleadoPlantaDTO);
                 subclasificacionEmpleadoPlantaRepository.save(subclasificacionEmpleadoPlanta);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (subclasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarSubclasificacionEmpleadoPlanta(Long idSubclasificacionEmpleadoPlanta) {
        Optional<SubclasificacionEmpleadoPlanta> subclasificacionEmpleadoPlantaId = subclasificacionEmpleadoPlantaRepository.findById(idSubclasificacionEmpleadoPlanta);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (subclasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaDAO.subclasificacionEmpleadoPlantaDTO(subclasificacionEmpleadoPlantaId.get()));
           subclasificacionEmpleadoPlantaRepository.delete(subclasificacionEmpleadoPlantaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (subclasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setSubclasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
}
