//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ClasificacionEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ClasificacionEmpleadoPlantaService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.ClasificacionEmpleadoPlantaDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ClasificacionEmpleadoPlantaRepository;
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
public class ClasificacionEmpleadoPlantaServiceImpl implements ClasificacionEmpleadoPlantaService {
    
    @Autowired//INYECTAMOS EL DAO.
    private ClasificacionEmpleadoPlantaDAO clasificacionEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ClasificacionEmpleadoPlantaRepository clasificacionEmpleadoPlantaRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta) {
        return clasificacionEmpleadoPlantaRepository.findTotalRegistros(idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<ClasificacionEmpleadoPlantaDTO> listarClasificacionesEmpleadosPlantas(Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta, String orderBy, String orderMode) {
        List<ClasificacionEmpleadoPlanta> clasificacionesEmpleadosPlantas = clasificacionEmpleadoPlantaRepository.findAllClasificacionesEmpleadosPlantas(idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta, orderBy, orderMode);
        List<ClasificacionEmpleadoPlantaDTO> clasificacionEmpleadoPlantaDTOS = new ArrayList<>();
        
        for (ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta : clasificacionesEmpleadosPlantas){
            clasificacionEmpleadoPlantaDTOS.add(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlanta));
        }
        
        return clasificacionEmpleadoPlantaDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<ClasificacionEmpleadoPlantaDTO> listarClasificacionesEmpleadosPlantasPag(Pageable pageable, Long idClasificacionEmpleadoPlanta, String keyword, String nombreTipoEmpleadoPlanta, String nombreClasificacionEmpleadoPlanta, String orderBy, String orderMode) {
        Slice<ClasificacionEmpleadoPlanta> clasificacionesEmpleadosPlantas = clasificacionEmpleadoPlantaRepository.findAllClasificacionesEmpleadosPlantasPag(pageable, idClasificacionEmpleadoPlanta, keyword, nombreTipoEmpleadoPlanta, nombreClasificacionEmpleadoPlanta, orderBy, orderMode);
        return clasificacionesEmpleadosPlantas.map(clasificacionEmpleadoPlanta -> clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlanta));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO) {
        Long maxIdClasificacionEmpleadoPlanta=null;
        ClasificacionEmpleadoPlanta clasificacionEmpleadoPlantaNombre = clasificacionEmpleadoPlantaRepository.findByNombreClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getNombreClasificacionEmpleadoPlanta().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(clasificacionEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdClasificacionEmpleadoPlanta = clasificacionEmpleadoPlantaRepository.findMaxIdClasificacionEmpleadoPlanta();
           if (maxIdClasificacionEmpleadoPlanta==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdClasificacionEmpleadoPlanta=Long.valueOf(0);
           }
           clasificacionEmpleadoPlantaDTO.setIdClasificacionEmpleadoPlanta(maxIdClasificacionEmpleadoPlanta+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           clasificacionEmpleadoPlantaRepository.save(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarClasificacionEmpleadoPlantaporId(Long idClasificacionEmpleadoPlanta) {
        Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaId = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(Long.valueOf(idClasificacionEmpleadoPlanta));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (clasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (clasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarClasificacionEmpleadoPlantaporNombre(String nombreClasificacionEmpleadoPlanta) {
        Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaNombre = Optional.ofNullable(clasificacionEmpleadoPlantaRepository.findByNombreClasificacionEmpleadoPlanta(String.valueOf(nombreClasificacionEmpleadoPlanta)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (clasificacionEmpleadoPlantaNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (clasificacionEmpleadoPlantaNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarClasificacionEmpleadoPlanta(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO) {
        Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaId = clasificacionEmpleadoPlantaRepository.findByIdClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getIdClasificacionEmpleadoPlanta());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (clasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (clasificacionEmpleadoPlantaDTO.getNombreClasificacionEmpleadoPlanta().equals(clasificacionEmpleadoPlantaId.get().getNombreClasificacionEmpleadoPlanta())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta = clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO);
              clasificacionEmpleadoPlantaRepository.save(clasificacionEmpleadoPlanta);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (clasificacionEmpleadoPlantaDTO.getNombreClasificacionEmpleadoPlanta().equals(clasificacionEmpleadoPlantaId.get().getNombreClasificacionEmpleadoPlanta())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              ClasificacionEmpleadoPlanta clasificacionEmpleadoPlantaNombre = clasificacionEmpleadoPlantaRepository.findByNombreClasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO.getNombreClasificacionEmpleadoPlanta().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(clasificacionEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 ClasificacionEmpleadoPlanta clasificacionEmpleadoPlanta = clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlanta(clasificacionEmpleadoPlantaDTO);
                 clasificacionEmpleadoPlantaRepository.save(clasificacionEmpleadoPlanta);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (clasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarClasificacionEmpleadoPlanta(Long idClasificacionEmpleadoPlanta) {
        Optional<ClasificacionEmpleadoPlanta> clasificacionEmpleadoPlantaId = clasificacionEmpleadoPlantaRepository.findById(idClasificacionEmpleadoPlanta);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (clasificacionEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaDAO.clasificacionEmpleadoPlantaDTO(clasificacionEmpleadoPlantaId.get()));
           clasificacionEmpleadoPlantaRepository.delete(clasificacionEmpleadoPlantaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (clasificacionEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setClasificacionEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
}
