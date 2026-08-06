//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TipoTarifaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TipoTarifaEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.TipoTarifaEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoTarifaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TipoTarifaEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/08/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class TipoTarifaEmpleadoServiceImpl implements TipoTarifaEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private TipoTarifaEmpleadoDAO tipoTarifaEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TipoTarifaEmpleadoRepository tipoTarifaEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado) {
        return tipoTarifaEmpleadoRepository.findTotalRegistros(idTipoTarifaEmpleado, keyword, estadoTipoTarifaEmpleado);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<TipoTarifaEmpleadoDTO> listarTiposTarifasEmpleados(Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado, String orderBy, String orderMode) {
        List<TipoTarifaEmpleado> tiposTarifasEmpleados = tipoTarifaEmpleadoRepository.findAllTiposTarifasEmpleados(idTipoTarifaEmpleado, keyword, estadoTipoTarifaEmpleado, orderBy, orderMode);
        List<TipoTarifaEmpleadoDTO> tipoTarifaEmpleadoDTOS = new ArrayList<>();
        
        for (TipoTarifaEmpleado tipoTarifaEmpleado : tiposTarifasEmpleados){
            tipoTarifaEmpleadoDTOS.add(tipoTarifaEmpleadoDAO.tipoTarifaEmpleadoDTO(tipoTarifaEmpleado));
        }
        
        return tipoTarifaEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<TipoTarifaEmpleadoDTO> listarTiposTarifasEmpleadosPag(Pageable pageable, Long idTipoTarifaEmpleado, String keyword, String estadoTipoTarifaEmpleado, String orderBy, String orderMode) {
        Slice<TipoTarifaEmpleado> tiposTarifasEmpleados = tipoTarifaEmpleadoRepository.findAllTiposTarifasEmpleadosPag(pageable, idTipoTarifaEmpleado, keyword, estadoTipoTarifaEmpleado, orderBy, orderMode);
        return tiposTarifasEmpleados.map(tipoTarifaEmpleado -> tipoTarifaEmpleadoDAO.tipoTarifaEmpleadoDTO(tipoTarifaEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearTipoTarifaEmpleado(TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO) {
        Long maxIdTipoTarifaEmpleado=null;
        TipoTarifaEmpleado tipoTarifaEmpleadoNombre = tipoTarifaEmpleadoRepository.findByNombreTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getNombreTipoTarifaEmpleado().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(tipoTarifaEmpleadoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setTipoTarifaEmpleadoDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdTipoTarifaEmpleado = tipoTarifaEmpleadoRepository.findMaxIdTipoTarifaEmpleado();
           if (maxIdTipoTarifaEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdTipoTarifaEmpleado=Long.valueOf(0);
           }
           tipoTarifaEmpleadoDTO.setIdTipoTarifaEmpleado(maxIdTipoTarifaEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           tipoTarifaEmpleadoRepository.save(tipoTarifaEmpleadoDAO.tipoTarifaEmpleado(tipoTarifaEmpleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoTarifaEmpleadoporId(Long idTipoTarifaEmpleado) {
        Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoId = tipoTarifaEmpleadoRepository.findByIdTipoTarifaEmpleado(Long.valueOf(idTipoTarifaEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoTarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setTipoTarifaEmpleadoDTO(tipoTarifaEmpleadoDAO.tipoTarifaEmpleadoDTO(tipoTarifaEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoTarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTipoTarifaEmpleadoporNombre(String nombreTipoTarifaEmpleado) {
        Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoNombre = Optional.ofNullable(tipoTarifaEmpleadoRepository.findByNombreTipoTarifaEmpleado(String.valueOf(nombreTipoTarifaEmpleado).toUpperCase()));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (tipoTarifaEmpleadoNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setTipoTarifaEmpleadoDTO(tipoTarifaEmpleadoDAO.tipoTarifaEmpleadoDTO(tipoTarifaEmpleadoNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tipoTarifaEmpleadoNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setTipoTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTipoTarifaEmpleado(TipoTarifaEmpleadoDTO tipoTarifaEmpleadoDTO) {
        Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoId = tipoTarifaEmpleadoRepository.findByIdTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getIdTipoTarifaEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (tipoTarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if (tipoTarifaEmpleadoDTO.getNombreTipoTarifaEmpleado().equalsIgnoreCase(tipoTarifaEmpleadoId.get().getNombreTipoTarifaEmpleado())==true) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              TipoTarifaEmpleado tipoTarifaEmpleado = tipoTarifaEmpleadoDAO.tipoTarifaEmpleado(tipoTarifaEmpleadoDTO);
              tipoTarifaEmpleadoRepository.save(tipoTarifaEmpleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (tipoTarifaEmpleadoDTO.getNombreTipoTarifaEmpleado().equalsIgnoreCase(tipoTarifaEmpleadoId.get().getNombreTipoTarifaEmpleado())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              TipoTarifaEmpleado tipoTarifaEmpleadoNombre = tipoTarifaEmpleadoRepository.findByNombreTipoTarifaEmpleado(tipoTarifaEmpleadoDTO.getNombreTipoTarifaEmpleado().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(tipoTarifaEmpleadoNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setTipoTarifaEmpleadoDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 TipoTarifaEmpleado tipoTarifaEmpleado = tipoTarifaEmpleadoDAO.tipoTarifaEmpleado(tipoTarifaEmpleadoDTO);
                 tipoTarifaEmpleadoRepository.save(tipoTarifaEmpleado);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (tipoTarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setTipoTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTipoTarifaEmpleado(Long idTipoTarifaEmpleado) {
        Optional<TipoTarifaEmpleado> tipoTarifaEmpleadoId = tipoTarifaEmpleadoRepository.findById(idTipoTarifaEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tipoTarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           tipoTarifaEmpleadoRepository.delete(tipoTarifaEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (tipoTarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTipoTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
