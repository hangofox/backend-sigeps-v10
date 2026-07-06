//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoNovedadEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.TipoNovedadEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoNovedadEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoNovedadEmpleadoRepository;
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
public class TipoNovedadEmpleadoServiceImpl implements TipoNovedadEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoNovedadEmpleadoDAO tipoNovedadEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoNovedadEmpleadoRepository tipoNovedadEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idTipoNovedadEmpleado, String keyword) {
        return tipoNovedadEmpleadoRepository.findTotalRegistros(idTipoNovedadEmpleado, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<TipoNovedadEmpleadoDTO> listarTiposNovedadesEmpleados(Long idTipoNovedadEmpleado, String keyword, String orderBy, String orderMode) {
        List<TipoNovedadEmpleado> tiposNovedadesEmpleados = tipoNovedadEmpleadoRepository.findAllTiposNovedadesEmpleados(idTipoNovedadEmpleado, keyword, orderBy, orderMode);
        List<TipoNovedadEmpleadoDTO> tipoNovedadEmpleadoDTOS = new ArrayList<>();
        
        for (TipoNovedadEmpleado tipoNovedadEmpleado : tiposNovedadesEmpleados){
            tipoNovedadEmpleadoDTOS.add(tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipoNovedadEmpleado));
        }
        
        return tipoNovedadEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<TipoNovedadEmpleadoDTO> listarTiposNovedadesEmpleadosPag(Pageable pageable, Long idTipoNovedadEmpleado, String keyword, String orderBy, String orderMode) {
        Slice<TipoNovedadEmpleado> tiposNovedadesEmpleados = tipoNovedadEmpleadoRepository.findAllTiposNovedadesEmpleadosPag(pageable, idTipoNovedadEmpleado, keyword, orderBy, orderMode);
        return tiposNovedadesEmpleados.map(tipoNovedadEmpleado -> tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipoNovedadEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearTipoNovedadEmpleado(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO) {
        Long maxIdTipoNovedadEmpleado=null;
        TipoNovedadEmpleado tipoNovedadEmpleadoNombre = tipoNovedadEmpleadoRepository.findByNombreTipoNovedadEmpleado(tipoNovedadEmpleadoDTO.getNombreTipoNovedadEmpleado().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(tipoNovedadEmpleadoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setTipoNovedadEmpleadoDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdTipoNovedadEmpleado = tipoNovedadEmpleadoRepository.findMaxIdTipoNovedadEmpleado();
           if (maxIdTipoNovedadEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdTipoNovedadEmpleado=Long.valueOf(0);
           }
           tipoNovedadEmpleadoDTO.setIdTipoNovedadEmpleado(maxIdTipoNovedadEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           tipoNovedadEmpleadoRepository.save(tipoNovedadEmpleadoDAO.tipoNovedadEmpleado(tipoNovedadEmpleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoNovedadEmpleadoporId(Long idTipoNovedadEmpleado) {
        Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoId = tipoNovedadEmpleadoRepository.findByIdTipoNovedadEmpleado(Long.valueOf(idTipoNovedadEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoNovedadEmpleadoDTO(tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipoNovedadEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoNovedadEmpleadoporNombre(String nombreTipoNovedadEmpleado) {
        Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoNombre = Optional.ofNullable(tipoNovedadEmpleadoRepository.findByNombreTipoNovedadEmpleado(String.valueOf(nombreTipoNovedadEmpleado)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (tipoNovedadEmpleadoNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setTipoNovedadEmpleadoDTO(tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipoNovedadEmpleadoNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoNovedadEmpleadoNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setTipoNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoNovedadEmpleado(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO) {
        Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoId = tipoNovedadEmpleadoRepository.findByIdTipoNovedadEmpleado(tipoNovedadEmpleadoDTO.getIdTipoNovedadEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (tipoNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (tipoNovedadEmpleadoDTO.getNombreTipoNovedadEmpleado().equals(tipoNovedadEmpleadoId.get().getNombreTipoNovedadEmpleado())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              TipoNovedadEmpleado tipoNovedadEmpleado = tipoNovedadEmpleadoDAO.tipoNovedadEmpleado(tipoNovedadEmpleadoDTO);
              tipoNovedadEmpleadoRepository.save(tipoNovedadEmpleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (tipoNovedadEmpleadoDTO.getNombreTipoNovedadEmpleado().equals(tipoNovedadEmpleadoId.get().getNombreTipoNovedadEmpleado())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              TipoNovedadEmpleado tipoNovedadEmpleadoNombre = tipoNovedadEmpleadoRepository.findByNombreTipoNovedadEmpleado(tipoNovedadEmpleadoDTO.getNombreTipoNovedadEmpleado().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(tipoNovedadEmpleadoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setTipoNovedadEmpleadoDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 TipoNovedadEmpleado tipoNovedadEmpleado = tipoNovedadEmpleadoDAO.tipoNovedadEmpleado(tipoNovedadEmpleadoDTO);
                 tipoNovedadEmpleadoRepository.save(tipoNovedadEmpleado);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (tipoNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setTipoNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoNovedadEmpleado(Long idTipoNovedadEmpleado) {
        Optional<TipoNovedadEmpleado> tipoNovedadEmpleadoId = tipoNovedadEmpleadoRepository.findById(idTipoNovedadEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoNovedadEmpleadoDTO(tipoNovedadEmpleadoDAO.tipoNovedadEmpleadoDTO(tipoNovedadEmpleadoId.get()));
           tipoNovedadEmpleadoRepository.delete(tipoNovedadEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (tipoNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
