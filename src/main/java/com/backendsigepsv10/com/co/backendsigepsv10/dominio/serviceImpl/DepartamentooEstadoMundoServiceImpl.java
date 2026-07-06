//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.DepartamentooEstadoMundoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.DepartamentooEstadoMundoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.DepartamentooEstadoMundoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.DepartamentooEstadoMundo;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.DepartamentooEstadoMundoRepository;
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
public class DepartamentooEstadoMundoServiceImpl implements DepartamentooEstadoMundoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private DepartamentooEstadoMundoDAO departamentooEstadoMundoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private DepartamentooEstadoMundoRepository departamentooEstadoMundoRepository;
    
    //CONTAR REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR REGISTROS.
    public Long contarTotalRegistros(Long idPaisMundo, String nombrePaisMundo, String keyword) {
        return departamentooEstadoMundoRepository.findTotalRegistros(idPaisMundo, nombrePaisMundo, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<DepartamentooEstadoMundoDTO> listarDepartamentosoEstadosMundo(Long idPaisMundo, String nombrePaisMundo, String keyword, String orderBy, String orderMode) {
        List<DepartamentooEstadoMundo> departamentosoEstadosMundo = departamentooEstadoMundoRepository.findAllDepartamentosoEstadosMundo(idPaisMundo, nombrePaisMundo, keyword, orderBy, orderMode);
        List<DepartamentooEstadoMundoDTO> departamentooEstadoMundoDTOS = new ArrayList<>();
        
        for (DepartamentooEstadoMundo departamentooEstadoMundo : departamentosoEstadosMundo){
            departamentooEstadoMundoDTOS.add(departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundo));
        }
        
        return departamentooEstadoMundoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<DepartamentooEstadoMundoDTO> listarDepartamentosoEstadosMundoPag(Pageable pageable, Long idPaisMundo, String nombrePaisMundo, String keyword, String orderBy, String orderMode) {
        Slice<DepartamentooEstadoMundo> departamentosoEstadosMundo = departamentooEstadoMundoRepository.findAllDepartamentosoEstadosMundoPag(pageable, idPaisMundo, nombrePaisMundo, keyword, orderBy, orderMode);
        return departamentosoEstadosMundo.map(departamentooEstadoMundo -> departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundo));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearDepartamentooEstadoMundo(DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO) {
        Long maxIdDepartamentooEstadoMundo=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        //VERIFICAR SI YA EXISTE EL NOMBRE EN EL MISMO PAIS.
        if (departamentooEstadoMundoDTO.getPaisMundoDTO() != null && departamentooEstadoMundoDTO.getPaisMundoDTO().getNombrePaisMundo() != null) {
           DepartamentooEstadoMundo departamentooEstadoMundoNombre = departamentooEstadoMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundo(
               departamentooEstadoMundoDTO.getPaisMundoDTO().getNombrePaisMundo().toUpperCase(),
               departamentooEstadoMundoDTO.getNombreDepartamentooEstadoMundo().toUpperCase());

           if (!(departamentooEstadoMundoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
              banderaNombreRegistroEncontrado=1;
           }
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0)) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdDepartamentooEstadoMundo = departamentooEstadoMundoRepository.findMaxIdDepartamentooEstadoMundo();
           if (maxIdDepartamentooEstadoMundo==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdDepartamentooEstadoMundo=Long.valueOf(0);
           }
           departamentooEstadoMundoDTO.setIdDepartamentooEstadoMundo(maxIdDepartamentooEstadoMundo+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           departamentooEstadoMundoRepository.save(departamentooEstadoMundoDAO.departamentooEstadoMundo(departamentooEstadoMundoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR ID.
    public RespuestaDTO consultarDepartamentooEstadoMundoporId(Long idDepartamentooEstadoMundo) {
        Optional<DepartamentooEstadoMundo> departamentooEstadoMundoId = departamentooEstadoMundoRepository.findByIdDepartamentooEstadoMundo(Long.valueOf(idDepartamentooEstadoMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (departamentooEstadoMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setDepartamentooEstadoMundoDTO(departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (departamentooEstadoMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID Y ID PAIS MUNDO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR ID Y ID PAIS MUNDO.
    public RespuestaDTO consultarDepartamentooEstadoMundoporIdeIdPaisMundo(Long idPaisMundo, Long idDepartamentooEstadoMundo) {
        Optional<DepartamentooEstadoMundo> departamentooEstadoMundoIdPais = Optional.ofNullable(departamentooEstadoMundoRepository.findByIdPaisMundoAndIdDepartamentooEstadoMundo(idPaisMundo, idDepartamentooEstadoMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (departamentooEstadoMundoIdPais.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA.
           respuestaDTO.setDepartamentooEstadoMundoDTO(departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundoIdPais.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (departamentooEstadoMundoIdPais.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE Y NOMBRE PAIS MUNDO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO POR NOMBRE Y NOMBRE PAIS MUNDO.
    public RespuestaDTO consultarDepartamentooEstadoMundoporNombreyNombrePaisMundo(String nombrePaisMundo, String nombreDepartamentooEstadoMundo) {
        Optional<DepartamentooEstadoMundo> departamentooEstadoMundoNombre = Optional.ofNullable(departamentooEstadoMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundo(nombrePaisMundo, nombreDepartamentooEstadoMundo));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (departamentooEstadoMundoNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setDepartamentooEstadoMundoDTO(departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundoNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (departamentooEstadoMundoNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarDepartamentooEstadoMundo(DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO) {
        Optional<DepartamentooEstadoMundo> departamentooEstadoMundoId = departamentooEstadoMundoRepository.findByIdDepartamentooEstadoMundo(departamentooEstadoMundoDTO.getIdDepartamentooEstadoMundo());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (departamentooEstadoMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ((departamentooEstadoMundoDTO.getNombreDepartamentooEstadoMundo().equals(departamentooEstadoMundoId.get().getNombreDepartamentooEstadoMundo())==true)) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              DepartamentooEstadoMundo departamentooEstadoMundo = departamentooEstadoMundoDAO.departamentooEstadoMundo(departamentooEstadoMundoDTO);
              departamentooEstadoMundoRepository.save(departamentooEstadoMundo);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (departamentooEstadoMundoDTO.getNombreDepartamentooEstadoMundo().equals(departamentooEstadoMundoId.get().getNombreDepartamentooEstadoMundo())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              String nombrePaisBusqueda = (departamentooEstadoMundoDTO.getPaisMundoDTO() != null && departamentooEstadoMundoDTO.getPaisMundoDTO().getNombrePaisMundo() != null)
                  ? departamentooEstadoMundoDTO.getPaisMundoDTO().getNombrePaisMundo().toUpperCase() : "";
              DepartamentooEstadoMundo departamentooEstadoMundoNombre = departamentooEstadoMundoRepository.findByNombrePaisMundoAndNombreDepartamentooEstadoMundo(
                  nombrePaisBusqueda, departamentooEstadoMundoDTO.getNombreDepartamentooEstadoMundo().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(departamentooEstadoMundoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setDepartamentooEstadoMundoDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 DepartamentooEstadoMundo departamentooEstadoMundo = departamentooEstadoMundoDAO.departamentooEstadoMundo(departamentooEstadoMundoDTO);
                 departamentooEstadoMundoRepository.save(departamentooEstadoMundo);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (departamentooEstadoMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarDepartamentooEstadoMundo(Long idDepartamentooEstadoMundo) {
        Optional<DepartamentooEstadoMundo> departamentooEstadoMundoId = departamentooEstadoMundoRepository.findById(idDepartamentooEstadoMundo);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (departamentooEstadoMundoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setDepartamentooEstadoMundoDTO(departamentooEstadoMundoDAO.departamentooEstadoMundoDTO(departamentooEstadoMundoId.get()));
           departamentooEstadoMundoRepository.delete(departamentooEstadoMundoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (departamentooEstadoMundoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setDepartamentooEstadoMundoDTO(null);
        }
        
        return respuestaDTO;
    }
}
