//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.CiudadMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.CiudadMundoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.CiudadMundoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.CiudadMundo;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.CiudadMundoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class CiudadMundoServiceImpl implements CiudadMundoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private CiudadMundoDAO ciudadMundoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private CiudadMundoRepository ciudadMundoRepository;
    
    //CONTAR REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR REGISTROS.
    public Long contarTotalRegistros(Long idCiudadMundo, Long idPaisMundo, Long idDepartamentooEstadoMundo, String nombrePaisMundo, String nombreDepartamentooEstadoMundo, String keyword) {
        return ciudadMundoRepository.findTotalRegistros(idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<CiudadMundoDTO> listarCiudadesMundo(Long idCiudadMundo, Long idPaisMundo, Long idDepartamentooEstadoMundo, String nombrePaisMundo, String nombreDepartamentooEstadoMundo, String keyword, String orderBy, String orderMode) {
        List<CiudadMundo> ciudadesMundo = ciudadMundoRepository.findAllCiudadesMundo(idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword, orderBy, orderMode);
        List<CiudadMundoDTO> ciudadesMundoDTO = new ArrayList<>();
        
        for (CiudadMundo ciudadMundo : ciudadesMundo){
            ciudadesMundoDTO.add(ciudadMundoDAO.ciudadMundoDTO(ciudadMundo));
        }
        
        return ciudadesMundoDTO;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<CiudadMundoDTO> listarCiudadesMundoPag(Pageable pageable, Long idCiudadMundo, Long idPaisMundo, Long idDepartamentooEstadoMundo, String nombrePaisMundo, String nombreDepartamentooEstadoMundo, String keyword, String orderBy, String orderMode) {
        Slice<CiudadMundo> ciudadesMundo = ciudadMundoRepository.findAllCiudadesMundoPag(pageable, idCiudadMundo, idPaisMundo, idDepartamentooEstadoMundo, nombrePaisMundo, nombreDepartamentooEstadoMundo, keyword, orderBy, orderMode);
        return ciudadesMundo.map(ciudadMundo -> ciudadMundoDAO.ciudadMundoDTO(ciudadMundo));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearCiudadMundo(CiudadMundoDTO ciudadMundoDTO) {
        Long maxIdCiudadMundo=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        //VERIFICAR SI YA EXISTE EL NOMBRE EN EL MISMO PAIS Y DEPARTAMENTO O ESTADO.
        if (ciudadMundoDTO.getPaisMundoDTO() != null && ciudadMundoDTO.getPaisMundoDTO().getNombrePaisMundo() != null &&
            ciudadMundoDTO.getDepartamentooEstadoMundoDTO() != null && ciudadMundoDTO.getDepartamentooEstadoMundoDTO().getNombreDepartamentooEstadoMundo() != null) {
           CiudadMundo ciudadMundoNombre = ciudadMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundoAndNombreCiudadMundo(
               ciudadMundoDTO.getPaisMundoDTO().getNombrePaisMundo().toUpperCase(),
               ciudadMundoDTO.getDepartamentooEstadoMundoDTO().getNombreDepartamentooEstadoMundo().toUpperCase(),
               ciudadMundoDTO.getNombreCiudadMundo().toUpperCase());
           
           if (!(ciudadMundoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
              banderaNombreRegistroEncontrado=1;
           }
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0)) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdCiudadMundo = ciudadMundoRepository.findMaxIdCiudadMundo();
           if (maxIdCiudadMundo==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdCiudadMundo=Long.valueOf(0);
           }
           ciudadMundoDTO.setIdCiudadMundo(maxIdCiudadMundo+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           ciudadMundoRepository.save(ciudadMundoDAO.ciudadMundo(ciudadMundoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR ID.
    public RespuestaDTO consultarCiudadMundoporId(Long idCiudadMundo) {
        Optional<CiudadMundo> ciudadMundoId = ciudadMundoRepository.findByIdCiudadMundo(Long.valueOf(idCiudadMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (ciudadMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setCiudadMundoDTO(ciudadMundoDAO.ciudadMundoDTO(ciudadMundoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (ciudadMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID PAIS MUNDO, ID DEPTO O ESTADO MUNDO E ID CIUDAD MUNDO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR ID PAIS MUNDO, ID DEPTO O ESTADO MUNDO E ID CIUDAD MUNDO.
    public RespuestaDTO consultarCiudadMundoporIdPaisMundoeIdDepartamentooEstadoMundoeIdCiudadMundo(Long idPaisMundo, Long idDepartamentooEstadoMundo, Long idCiudadMundo) {
        Optional<CiudadMundo> ciudadMundoIdsPais = Optional.ofNullable(ciudadMundoRepository.findByIdPaisMundoAndIdDepartamentooEstadoMundoAndIdCiudadMundo(idPaisMundo, idDepartamentooEstadoMundo, idCiudadMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (ciudadMundoIdsPais.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA.
           respuestaDTO.setCiudadMundoDTO(ciudadMundoDAO.ciudadMundoDTO(ciudadMundoIdsPais.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (ciudadMundoIdsPais.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE PAIS MUNDO, NOMBRE DEPTO O ESTADO MUNDO Y NOMBRE CIUDAD MUNDO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR NOMBRE PAIS MUNDO, NOMBRE DEPTO O ESTADO MUNDO Y NOMBRE CIUDAD MUNDO.
    public RespuestaDTO consultarCiudadMundoporNombrePaisMundoyNombreDepartamentooEstadoMundoyNombreCiudadMundo(String nombrePaisMundo, String nombreDepartamentooEstadoMundo, String nombreCiudadMundo) {
        Optional<CiudadMundo> ciudadMundoNombres = Optional.ofNullable(ciudadMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundoAndNombreCiudadMundo(nombrePaisMundo, nombreDepartamentooEstadoMundo, nombreCiudadMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (ciudadMundoNombres.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setCiudadMundoDTO(ciudadMundoDAO.ciudadMundoDTO(ciudadMundoNombres.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (ciudadMundoNombres.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarCiudadMundo(CiudadMundoDTO ciudadMundoDTO) {
        Optional<CiudadMundo> ciudadMundoId = ciudadMundoRepository.findByIdCiudadMundo(ciudadMundoDTO.getIdCiudadMundo());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (ciudadMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ((ciudadMundoDTO.getNombreCiudadMundo().equals(ciudadMundoId.get().getNombreCiudadMundo())==true)) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              CiudadMundo ciudadMundo = ciudadMundoDAO.ciudadMundo(ciudadMundoDTO);
              ciudadMundoRepository.save(ciudadMundo);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (ciudadMundoDTO.getNombreCiudadMundo().equals(ciudadMundoId.get().getNombreCiudadMundo())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              String nombrePaisBusqueda = (ciudadMundoDTO.getPaisMundoDTO() != null && ciudadMundoDTO.getPaisMundoDTO().getNombrePaisMundo() != null)
                  ? ciudadMundoDTO.getPaisMundoDTO().getNombrePaisMundo().toUpperCase() : "";
              String nombreDeptoBusqueda = (ciudadMundoDTO.getDepartamentooEstadoMundoDTO() != null && ciudadMundoDTO.getDepartamentooEstadoMundoDTO().getNombreDepartamentooEstadoMundo() != null)
                  ? ciudadMundoDTO.getDepartamentooEstadoMundoDTO().getNombreDepartamentooEstadoMundo().toUpperCase() : "";
              CiudadMundo ciudadMundoNombre = ciudadMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundoAndNombreCiudadMundo(
                  nombrePaisBusqueda, nombreDeptoBusqueda, ciudadMundoDTO.getNombreCiudadMundo().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(ciudadMundoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setCiudadMundoDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 CiudadMundo ciudadMundo = ciudadMundoDAO.ciudadMundo(ciudadMundoDTO);
                 ciudadMundoRepository.save(ciudadMundo);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (ciudadMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarCiudadMundo(Long idCiudadMundo) {
        Optional<CiudadMundo> ciudadMundoId = ciudadMundoRepository.findById(idCiudadMundo);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (ciudadMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setCiudadMundoDTO(ciudadMundoDAO.ciudadMundoDTO(ciudadMundoId.get()));
           ciudadMundoRepository.delete(ciudadMundoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (ciudadMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setCiudadMundoDTO(null);
        }
        
        return respuestaDTO;
    }
}
