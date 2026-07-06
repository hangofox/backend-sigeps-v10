//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoEmpleadoPlantaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoEmpleadoPlantaService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.TipoEmpleadoPlantaDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoEmpleadoPlantaRepository;
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
public class TipoEmpleadoPlantaServiceImpl implements TipoEmpleadoPlantaService {
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoEmpleadoPlantaDAO tipoEmpleadoPlantaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoEmpleadoPlantaRepository tipoEmpleadoPlantaRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idTipoEmpleadoPlanta, String keyword) {
        return tipoEmpleadoPlantaRepository.findTotalRegistros(idTipoEmpleadoPlanta, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<TipoEmpleadoPlantaDTO> listarTiposEmpleadosPlanta(Long idTipoEmpleadoPlanta, String keyword, String orderBy, String orderMode) {
        List<TipoEmpleadoPlanta> tiposEmpleadosPlanta = tipoEmpleadoPlantaRepository.findAllTiposEmpleadosPlanta(idTipoEmpleadoPlanta, keyword, orderBy, orderMode);
        List<TipoEmpleadoPlantaDTO> tipoEmpleadoPlantaDTOS = new ArrayList<>();
        
        for (TipoEmpleadoPlanta tipoEmpleadoPlanta : tiposEmpleadosPlanta){
            tipoEmpleadoPlantaDTOS.add(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlanta));
        }
        
        return tipoEmpleadoPlantaDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<TipoEmpleadoPlantaDTO> listarTiposEmpleadosPlantaPag(Pageable pageable, Long idTipoEmpleadoPlanta, String keyword, String orderBy, String orderMode) {
        Slice<TipoEmpleadoPlanta> tiposEmpleadosPlanta = tipoEmpleadoPlantaRepository.findAllTiposEmpleadosPlantaPag(pageable, idTipoEmpleadoPlanta, keyword, orderBy, orderMode);
        return tiposEmpleadosPlanta.map(tipoEmpleadoPlanta -> tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlanta));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearTipoEmpleadoPlanta(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO) {
        Long maxIdTipoEmpleadoPlanta=null;
        TipoEmpleadoPlanta tipoEmpleadoPlantaNombre = tipoEmpleadoPlantaRepository.findByNombreTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO.getNombreTipoEmpleadoPlanta().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(tipoEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setTipoEmpleadoPlantaDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdTipoEmpleadoPlanta = tipoEmpleadoPlantaRepository.findMaxIdTipoEmpleadoPlanta();
           if (maxIdTipoEmpleadoPlanta==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdTipoEmpleadoPlanta=Long.valueOf(0);
           }
           tipoEmpleadoPlantaDTO.setIdTipoEmpleadoPlanta(maxIdTipoEmpleadoPlanta+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           tipoEmpleadoPlantaRepository.save(tipoEmpleadoPlantaDAO.tipoEmpleadoPlanta(tipoEmpleadoPlantaDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoPlantaporId(Long idTipoEmpleadoPlanta) {
        Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaId = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(Long.valueOf(idTipoEmpleadoPlanta));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlantaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoEmpleadoPlantaporNombre(String nombreTipoEmpleadoPlanta) {
        Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaNombre = Optional.ofNullable(tipoEmpleadoPlantaRepository.findByNombreTipoEmpleadoPlanta(String.valueOf(nombreTipoEmpleadoPlanta)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (tipoEmpleadoPlantaNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlantaNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoEmpleadoPlantaNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setTipoEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoEmpleadoPlanta(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO) {
        Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaId = tipoEmpleadoPlantaRepository.findByIdTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO.getIdTipoEmpleadoPlanta());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (tipoEmpleadoPlantaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (tipoEmpleadoPlantaDTO.getNombreTipoEmpleadoPlanta().equals(tipoEmpleadoPlantaId.get().getNombreTipoEmpleadoPlanta())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              TipoEmpleadoPlanta tipoEmpleadoPlanta = tipoEmpleadoPlantaDAO.tipoEmpleadoPlanta(tipoEmpleadoPlantaDTO);
              tipoEmpleadoPlantaRepository.save(tipoEmpleadoPlanta);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (tipoEmpleadoPlantaDTO.getNombreTipoEmpleadoPlanta().equals(tipoEmpleadoPlantaId.get().getNombreTipoEmpleadoPlanta())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              TipoEmpleadoPlanta tipoEmpleadoPlantaNombre = tipoEmpleadoPlantaRepository.findByNombreTipoEmpleadoPlanta(tipoEmpleadoPlantaDTO.getNombreTipoEmpleadoPlanta().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(tipoEmpleadoPlantaNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }

              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setTipoEmpleadoPlantaDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 TipoEmpleadoPlanta tipoEmpleadoPlanta = tipoEmpleadoPlantaDAO.tipoEmpleadoPlanta(tipoEmpleadoPlantaDTO);
                 tipoEmpleadoPlantaRepository.save(tipoEmpleadoPlanta);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (tipoEmpleadoPlantaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setTipoEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoEmpleadoPlanta(Long idTipoEmpleadoPlanta) {
        Optional<TipoEmpleadoPlanta> tipoEmpleadoPlantaId  = tipoEmpleadoPlantaRepository.findById(idTipoEmpleadoPlanta);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoEmpleadoPlantaId .isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoEmpleadoPlantaDTO(tipoEmpleadoPlantaDAO.tipoEmpleadoPlantaDTO(tipoEmpleadoPlantaId.get()));
           tipoEmpleadoPlantaRepository.delete(tipoEmpleadoPlantaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (tipoEmpleadoPlantaId .isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoEmpleadoPlantaDTO(null);
        }
        
        return respuestaDTO;
    }
}
