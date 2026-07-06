//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RecuperacionContrasenaAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.RecuperacionContrasenaAccesoUsuarioService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.RecuperacionContrasenaAccesoUsuarioDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.RecuperacionContrasenaAccesoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.RecuperacionContrasenaAccesoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
//import java.text.SimpleDateFormat;

/**
* * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class RecuperacionContrasenaAccesoUsuarioServiceImpl implements RecuperacionContrasenaAccesoUsuarioService {
    
    @Autowired//INYECTAMOS EL DAO.
    private RecuperacionContrasenaAccesoUsuarioDAO recuperacionContrasenaAccesoUsuarioDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private RecuperacionContrasenaAccesoUsuarioRepository recuperacionContrasenaAccesoUsuarioRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR REGISTROS.
    public Long contarTotalRegistros(Long idRecuperacionContrasenaAccesoUsuario, String keyword, Long idUsuario, String estadoUsoCodigoActivacionContrasenaAccesoUsuario) {
        return recuperacionContrasenaAccesoUsuarioRepository.findTotalRegistros(idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<RecuperacionContrasenaAccesoUsuarioDTO> listarRecuperacionesContrasenasAccesosUsuarios(Long idRecuperacionContrasenaAccesoUsuario, String keyword, Long idUsuario, String estadoUsoCodigoActivacionContrasenaAccesoUsuario, String orderBy, String orderMode) {
        List<RecuperacionContrasenaAccesoUsuario> recuperacionesContrasenasAccesosUsuarios = recuperacionContrasenaAccesoUsuarioRepository.findAllRecuperacionesContrasenasAccesosUsuarios(idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario, orderBy, orderMode);
        List<RecuperacionContrasenaAccesoUsuarioDTO> recuperacionContrasenaAccesoUsuarioDTOS = new ArrayList<>();
        
        for (RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario : recuperacionesContrasenasAccesosUsuarios){
            recuperacionContrasenaAccesoUsuarioDTOS.add(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuario));
        }
        
        return recuperacionContrasenaAccesoUsuarioDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<RecuperacionContrasenaAccesoUsuarioDTO> listarRecuperacionesContrasenasAccesosUsuariosPag(Pageable pageable, Long idRecuperacionContrasenaAccesoUsuario, String keyword, Long idUsuario, String estadoUsoCodigoActivacionContrasenaAccesoUsuario, String orderBy, String orderMode) {
        Slice<RecuperacionContrasenaAccesoUsuario> recuperacionesContrasenasAccesosUsuarios = recuperacionContrasenaAccesoUsuarioRepository.findAllRecuperacionesContrasenasAccesosUsuariosPag(pageable, idRecuperacionContrasenaAccesoUsuario, keyword, idUsuario, estadoUsoCodigoActivacionContrasenaAccesoUsuario, orderBy, orderMode);
        List<RecuperacionContrasenaAccesoUsuarioDTO> recuperacionContrasenaAccesoUsuarioDTOS = new ArrayList<>();
        
        for (RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario : recuperacionesContrasenasAccesosUsuarios){
            recuperacionContrasenaAccesoUsuarioDTOS.add(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuario));
        }
        
        return new SliceImpl<>(recuperacionContrasenaAccesoUsuarioDTOS, pageable, recuperacionesContrasenasAccesosUsuarios.hasNext());
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearRecuperacionContrasenaAccesoUsuario(RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO) {
        Long maxIdRecuperacionContrasenaAccesoUsuario=null;
        RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuarioCodigoActivacion = recuperacionContrasenaAccesoUsuarioRepository.findByCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaCodigoActivacionRegistroEncontrado=0;
        
        if (!(recuperacionContrasenaAccesoUsuarioCodigoActivacion==null)) {//SI ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CÓDIGO DE ACTIVACIÓN DE REGISTRO REPETIDO CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
           banderaCodigoActivacionRegistroEncontrado=1;
        }
        
        //System.out.println("INGRESA METODO CREAR.");
        if (banderaCodigoActivacionRegistroEncontrado==1) {//SI ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CÓDIGO DE ACTIVACIÓN DE REGISTRO REPETIDO CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CODIGO_ACTIVACION_YA_EXISTE, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        if ((banderaCodigoActivacionRegistroEncontrado==0) ) {//SI NO ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
           maxIdRecuperacionContrasenaAccesoUsuario = recuperacionContrasenaAccesoUsuarioRepository.findMaxIdRecuperacionContrasenaAccesoUsuario();
           if (maxIdRecuperacionContrasenaAccesoUsuario==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdRecuperacionContrasenaAccesoUsuario=Long.valueOf(0);
           }
           recuperacionContrasenaAccesoUsuarioDTO.setIdRecuperacionContrasenaAccesoUsuario(maxIdRecuperacionContrasenaAccesoUsuario+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           recuperacionContrasenaAccesoUsuarioRepository.save(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarRecuperacionContrasenaAccesoUsuarioporId(Long idRecuperacionContrasenaAccesoUsuario) {
        Optional<RecuperacionContrasenaAccesoUsuario> recuperacionContrasenaAccesoUsuarioId = recuperacionContrasenaAccesoUsuarioRepository.findByIdRecuperacionContrasenaAccesoUsuario(Long.valueOf(idRecuperacionContrasenaAccesoUsuario));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (recuperacionContrasenaAccesoUsuarioId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (recuperacionContrasenaAccesoUsuarioId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR CÓDIGO DE ACTIVACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarRecuperacionContrasenaAccesoUsuarioporCodigoActivacion(String codigoActivacionContrasenaAccesoUsuario) {
        Optional<RecuperacionContrasenaAccesoUsuario> recuperacionContrasenaAccesoUsuarioCodigoActivacion = Optional.ofNullable(recuperacionContrasenaAccesoUsuarioRepository.findByCodigoActivacionContrasenaAccesoUsuario(String.valueOf(codigoActivacionContrasenaAccesoUsuario)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (recuperacionContrasenaAccesoUsuarioCodigoActivacion.isPresent()==true) {//SI ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioCodigoActivacion.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (recuperacionContrasenaAccesoUsuarioCodigoActivacion.isPresent()==false) {//SI NO ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarRecuperacionContrasenaAccesoUsuario(RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO) {
        Optional<RecuperacionContrasenaAccesoUsuario> recuperacionContrasenaAccesoUsuarioId = recuperacionContrasenaAccesoUsuarioRepository.findByIdRecuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getIdRecuperacionContrasenaAccesoUsuario());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (recuperacionContrasenaAccesoUsuarioId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (recuperacionContrasenaAccesoUsuarioDTO.getCodigoActivacionContrasenaAccesoUsuario().equals(recuperacionContrasenaAccesoUsuarioId.get().getCodigoActivacionContrasenaAccesoUsuario())==true) ) {//SI EL CÓDIGO DE ACTIVACIÓN DIGITADO ES IGUAL AL CÓDIGO DE ACTIVACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario = recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO);
              recuperacionContrasenaAccesoUsuarioRepository.save(recuperacionContrasenaAccesoUsuario);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (recuperacionContrasenaAccesoUsuarioDTO.getCodigoActivacionContrasenaAccesoUsuario().equals(recuperacionContrasenaAccesoUsuarioId.get().getCodigoActivacionContrasenaAccesoUsuario())==false) {//SI EL CÓDIGO DE ACTIVACIÓN DIGITADO ES DIFERENTE AL CÓDIGO DE ACTIVACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE CÓDIGO DE ACTIVACIÓN DIGITADO EXISTE EN OTROS REGISTROS.
              //consulte por nombre e imprima si existe y si no existe
              RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuarioCodigoActivacion = recuperacionContrasenaAccesoUsuarioRepository.findByCodigoActivacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO.getCodigoActivacionContrasenaAccesoUsuario().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaCodigoActivacionRegistroEncontrado=0;
              
              if (!(recuperacionContrasenaAccesoUsuarioCodigoActivacion==null)) {//SI ENCONTRO EL CÓDIGO DE ACTIVACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CÓDIGO DE ACTIVACIÓN DE REGISTRO REPETIDO CON EL CÓDIGO DE ACTIVACIÓN PROPORCIONADO.
                 banderaCodigoActivacionRegistroEncontrado=1;
              }
              
              if (banderaCodigoActivacionRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL CÓDIGO DE ACTIVACIÓN DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO CÓDIGO DE ACTIVACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE CÓDIGO DE ACTIVACIÓN DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CODIGO_ACTIVACION_YA_EXISTE, false);
                 respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
              }
              if (banderaCodigoActivacionRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL CÓDIGO DE ACTIVACIÓN DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 RecuperacionContrasenaAccesoUsuario recuperacionContrasenaAccesoUsuario = recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuario(recuperacionContrasenaAccesoUsuarioDTO);
                 recuperacionContrasenaAccesoUsuarioRepository.save(recuperacionContrasenaAccesoUsuario);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (recuperacionContrasenaAccesoUsuarioId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarRecuperacionContrasenaAccesoUsuario(Long idRecuperacionContrasenaAccesoUsuario) {
        Optional<RecuperacionContrasenaAccesoUsuario> recuperacionContrasenaAccesoUsuarioId  = recuperacionContrasenaAccesoUsuarioRepository.findById(idRecuperacionContrasenaAccesoUsuario);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (recuperacionContrasenaAccesoUsuarioId .isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioDAO.recuperacionContrasenaAccesoUsuarioDTO(recuperacionContrasenaAccesoUsuarioId .get()));
           recuperacionContrasenaAccesoUsuarioRepository.delete(recuperacionContrasenaAccesoUsuarioId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (recuperacionContrasenaAccesoUsuarioId .isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ACTUALIZAR ESTADOS DE USOS DE CÓDIGOS DE ACTIVACIONES DE CONTRASEÑAS DE ACCESOS DE USUARIOS:
    @Override//SOBREESCRIBIMOS EL METODO DE PREDETERMINAR REGISTRO.
    public RespuestaDTO actualizarEstadosUsosCodigosActivacionesContrasenasAccesosUsuarios(Date fechaHMSExpCodActivContrasenaAccesoUsuario) {
        List<RecuperacionContrasenaAccesoUsuario> recuperacionesContrasenasAccesosUsuarios = recuperacionContrasenaAccesoUsuarioRepository.findAllRecuperacionesContrasenasAccesosUsuariosOrderedByIdAsc();
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_ACTUALIZADOS, false);
        
        if ( (recuperacionesContrasenasAccesosUsuarios==null)||(recuperacionesContrasenasAccesosUsuarios.isEmpty()==false) ) {//EN CASO DE QUE LA LISTA CONTENGA DATOS.
           //RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO = new RecuperacionContrasenaAccesoUsuarioDTO();
           recuperacionContrasenaAccesoUsuarioRepository.updateEstadoUsoCodActivContrasenaAccesoUsuarioRecuperacionesContrasenasAccesosUsuarios(fechaHMSExpCodActivContrasenaAccesoUsuario);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_ACTUALIZADOS_EXITO, true);
        }
        if ( (recuperacionesContrasenasAccesosUsuarios==null)||(recuperacionesContrasenasAccesosUsuarios.isEmpty()==true) ) {//EN CASO DE QUE LA LISTA ESTE VACIA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_ACTUALIZADOS, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //VACIAR REGISTROS POR ID DE USUARIO:
    @Override//SOBREESCRIBIMOS EL METODO DE VACIAR REGISTROS.
    public RespuestaDTO vaciarRecuperacionesContrasenasAccesosUsuariosporIdUsuario(Long idUsuario) {
        List<RecuperacionContrasenaAccesoUsuario> recuperacionesContrasenasAccesosUsuarios = recuperacionContrasenaAccesoUsuarioRepository.searchRecuperacionesContrasenasAccesosUsuariosByIdUsuarioOrderedByIdAsc(idUsuario);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_VACIADOS, false);
        
        if ( (recuperacionesContrasenasAccesosUsuarios==null)||(recuperacionesContrasenasAccesosUsuarios.isEmpty()==false) ) {//EN CASO DE QUE LA LISTA CONTENGA DATOS.
           recuperacionContrasenaAccesoUsuarioRepository.deleteRecuperacionesContrasenasAccesosUsuariosByIdUsuario(idUsuario);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_VACIADOS_EXITO, true);
        }
        if ( (recuperacionesContrasenasAccesosUsuarios==null)||(recuperacionesContrasenasAccesosUsuarios.isEmpty()==true) ) {//EN CASO DE QUE LA LISTA ESTE VACIA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_VACIADOS, false);
           respuestaDTO.setRecuperacionContrasenaAccesoUsuarioDTO(null);
        }
        
        return respuestaDTO;
    }
}
