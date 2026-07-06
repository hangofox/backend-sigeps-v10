//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoDocumentoIdentificacionDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoDocumentoIdentificacionService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.TipoDocumentoIdentificacionDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoDocumentoIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class TipoDocumentoIdentificacionServiceImpl implements TipoDocumentoIdentificacionService {
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoDocumentoIdentificacionDAO tipoDocumentoIdentificacionDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoDocumentoIdentificacionRepository tipoDocumentoIdentificacionRepository;
    
    //CONTAR REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR REGISTROS.
    public Long contarTotalRegistros(Long idTipoDocumentoIdentificacion, String keyword) {
        return tipoDocumentoIdentificacionRepository.findTotalRegistros(idTipoDocumentoIdentificacion, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<TipoDocumentoIdentificacionDTO> listarTiposDocumentosIdentificacion(Long idTipoDocumentoIdentificacion, String keyword, String orderBy, String orderMode) {
        List<TipoDocumentoIdentificacion> tiposDocumentosIdentificacion = tipoDocumentoIdentificacionRepository.findAllTiposDocumentosIdentificacion(idTipoDocumentoIdentificacion, keyword, orderBy, orderMode);
        List<TipoDocumentoIdentificacionDTO> tipoDocumentoIdentificacionDTOS = new ArrayList<>();
        
        for (TipoDocumentoIdentificacion tipoDocumentoIdentificacion : tiposDocumentosIdentificacion){
            tipoDocumentoIdentificacionDTOS.add(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacion));
        }
        
        return tipoDocumentoIdentificacionDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<TipoDocumentoIdentificacionDTO> listarTiposDocumentosIdentificacionPag(Pageable pageable, Long idTipoDocumentoIdentificacion, String keyword, String orderBy, String orderMode) {
        Slice<TipoDocumentoIdentificacion> tiposDocumentosIdentificacion = tipoDocumentoIdentificacionRepository.findAllTiposDocumentosIdentificacionPag(pageable, idTipoDocumentoIdentificacion, keyword, orderBy, orderMode);
        return tiposDocumentosIdentificacion.map(tipoDocumentoIdentificacion -> tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacion));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearTipoDocumentoIdentificacion(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) {
        Long maxIdTipoDocumentoIdentificacion=null;
        TipoDocumentoIdentificacion tipoDocumentoIdentificacionNombre = tipoDocumentoIdentificacionRepository.findByNombreTipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO.getNombreTipoDocumentoIdentificacion().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(tipoDocumentoIdentificacionNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0)) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdTipoDocumentoIdentificacion = tipoDocumentoIdentificacionRepository.findMaxIdTipoDocumentoIdentificacion();
           if (maxIdTipoDocumentoIdentificacion==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdTipoDocumentoIdentificacion=Long.valueOf(0);
           }
           tipoDocumentoIdentificacionDTO.setIdTipoDocumentoIdentificacion(maxIdTipoDocumentoIdentificacion+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           tipoDocumentoIdentificacionRepository.save(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoDocumentoIdentificacionporId(Long idTipoDocumentoIdentificacion) {
        Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionId = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(Long.valueOf(idTipoDocumentoIdentificacion));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoDocumentoIdentificacionId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoDocumentoIdentificacionId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoDocumentoIdentificacionporNombre(String nombreTipoDocumentoIdentificacion) {
        Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionNombre = Optional.ofNullable(tipoDocumentoIdentificacionRepository.findByNombreTipoDocumentoIdentificacion(String.valueOf(nombreTipoDocumentoIdentificacion)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (tipoDocumentoIdentificacionNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoDocumentoIdentificacionNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoDocumentoIdentificacion(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) {
        Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionId = tipoDocumentoIdentificacionRepository.findByIdTipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO.getIdTipoDocumentoIdentificacion());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (tipoDocumentoIdentificacionId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ((tipoDocumentoIdentificacionDTO.getNombreTipoDocumentoIdentificacion().equals(tipoDocumentoIdentificacionId.get().getNombreTipoDocumentoIdentificacion())==true)) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              TipoDocumentoIdentificacion tipoDocumentoIdentificacion = tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO);
              tipoDocumentoIdentificacionRepository.save(tipoDocumentoIdentificacion);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (tipoDocumentoIdentificacionDTO.getNombreTipoDocumentoIdentificacion().equals(tipoDocumentoIdentificacionId.get().getNombreTipoDocumentoIdentificacion())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              TipoDocumentoIdentificacion tipoDocumentoIdentificacionNombre = tipoDocumentoIdentificacionRepository.findByNombreTipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO.getNombreTipoDocumentoIdentificacion().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(tipoDocumentoIdentificacionNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 TipoDocumentoIdentificacion tipoDocumentoIdentificacion = tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacion(tipoDocumentoIdentificacionDTO);
                 tipoDocumentoIdentificacionRepository.save(tipoDocumentoIdentificacion);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (tipoDocumentoIdentificacionId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoDocumentoIdentificacion(Long idTipoDocumentoIdentificacion) {
        Optional<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionId = tipoDocumentoIdentificacionRepository.findById(idTipoDocumentoIdentificacion);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoDocumentoIdentificacionId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionDAO.tipoDocumentoIdentificacionDTO(tipoDocumentoIdentificacionId.get()));
           tipoDocumentoIdentificacionRepository.delete(tipoDocumentoIdentificacionId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (tipoDocumentoIdentificacionId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoDocumentoIdentificacionDTO(null);
        }
        
        return respuestaDTO;
    }
}
