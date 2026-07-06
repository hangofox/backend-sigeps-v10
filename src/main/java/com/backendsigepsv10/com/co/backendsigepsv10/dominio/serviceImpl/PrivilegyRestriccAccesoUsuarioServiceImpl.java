//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PrivilegyRestriccAccesoUsuarioDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.PrivilegyRestriccAccesoUsuarioService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.PrivilegyRestriccAccesoUsuarioDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PrivilegyRestriccAccesoUsuario;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.PrivilegyRestriccAccesoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class PrivilegyRestriccAccesoUsuarioServiceImpl implements PrivilegyRestriccAccesoUsuarioService {

    @Autowired//INYECTAMOS EL DAO.
    private PrivilegyRestriccAccesoUsuarioDAO privilegyRestriccAccesoUsuarioDAO;

    @Autowired//INYECTAMOS EL REPOSITORIO.
    private PrivilegyRestriccAccesoUsuarioRepository privilegyRestriccAccesoUsuarioRepository;

    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTEO.
    public Long contarTotalRegistros(Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario) {
        return privilegyRestriccAccesoUsuarioRepository.findTotalRegistros(idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario);
    }

    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<PrivilegyRestriccAccesoUsuarioDTO> listarPrivilegyRestriccAccesosUsuarios(Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario, String orderBy, String orderMode) {
        List<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesosUsuarios = privilegyRestriccAccesoUsuarioRepository.findAllPrivilegyRestriccAccesosUsuarios(idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario, orderBy, orderMode);
        List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesoUsuarioDTOS = new ArrayList<>();
        for (PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario : privilegyRestriccAccesosUsuarios) {
            privilegyRestriccAccesoUsuarioDTOS.add(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuario));
        }
        return privilegyRestriccAccesoUsuarioDTOS;
    }

    //LISTAR REGISTROS FILTRADOS CON PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public Slice<PrivilegyRestriccAccesoUsuarioDTO> listarPrivilegyRestriccAccesosUsuariosPag(Pageable pageable, Long idPrivilegyRestriccAccesoUsuario, String keyword, Long idFuncionalidad, String nombreFuncionalidad, Long idRol, String nombreRol, Long idUsuario, String orderBy, String orderMode) {
        Slice<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesosUsuarios = privilegyRestriccAccesoUsuarioRepository.findAllPrivilegyRestriccAccesosUsuariosPag(pageable, idPrivilegyRestriccAccesoUsuario, keyword, idFuncionalidad, nombreFuncionalidad, idRol, nombreRol, idUsuario, orderBy, orderMode);
        return privilegyRestriccAccesosUsuarios.map(privilegyRestriccAccesoUsuario -> privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuario));
    }

    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearPrivilegyRestriccAccesoUsuario(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO) {
        Long maxIdPrivilegyRestriccAccesoUsuario=null;
        PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol = privilegyRestriccAccesoUsuarioRepository.findByIdUsuarioAndIdFuncionalidadAndIdRol(privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario(), privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad(), privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);

        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaPrivilegyRestriccRegistroEncontrado=0;

        if (!(privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol==null)) {//SI ENCONTRO EL ID DE USUARIO, ID DE FUNCIONALIDAD E ID DE ROL DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON LOS IDS PROPORCIONADOS.
           banderaPrivilegyRestriccRegistroEncontrado=1;
        }

        if (banderaPrivilegyRestriccRegistroEncontrado==1) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE PRIVILEGIO Y RESTRICCIÓN DE REGISTRO REPETIDO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_YA_EXISTEN, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }
        if ((banderaPrivilegyRestriccRegistroEncontrado==0) ) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE.
           maxIdPrivilegyRestriccAccesoUsuario = privilegyRestriccAccesoUsuarioRepository.findMaxIdPrivilegyRestriccAccesoUsuario();
           if (maxIdPrivilegyRestriccAccesoUsuario==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdPrivilegyRestriccAccesoUsuario=Long.valueOf(0);
           }
           privilegyRestriccAccesoUsuarioDTO.setIdPrivilegyRestriccAccesoUsuario(maxIdPrivilegyRestriccAccesoUsuario+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.

           //OBTENCIÓN AUTOMÁTICA DEL NÚMERO DE REGISTRO COMBINANDO LA CADENA "PRIVYREST" CON LA ID DE LA LLAVE PRIMARIA, LA CADENA "_", ID DEL USUARIO Y LA FECHA Y HORA ACTUAL DEL SERVIDOR SIN GUIONES NI PUNTOS:
           SimpleDateFormat objetoFechaHMS = new SimpleDateFormat("yyyyMMddHHmmss");
           String fechaHMSPrivilegyRestriccAccesoUsuarioinIntermedios = objetoFechaHMS.format(privilegyRestriccAccesoUsuarioDTO.getFechaHMSIngresoPrivilegyRestriccAccesoUsuario());
           privilegyRestriccAccesoUsuarioDTO.setNumRegPrivilegyRestriccAccesoUsuario("PRIVYREST" + (maxIdPrivilegyRestriccAccesoUsuario+1) + "_" + privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario() + fechaHMSPrivilegyRestriccAccesoUsuarioinIntermedios);

           privilegyRestriccAccesoUsuarioRepository.save(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }

        return respuestaDTO;
    }

    //CREAR REGISTROS MÚLTIPLES:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTROS MÚLTIPLES.
    public RespuestaDTO crearPrivilegyRestriccAccesosUsuarios(List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesoUsuarioDTOS) {
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_CREADOS, false);

        if ((!(privilegyRestriccAccesoUsuarioDTOS==null))&&(!(privilegyRestriccAccesoUsuarioDTOS.isEmpty()==true))) {//SI LA LISTA TIENE DATOS SE CREAN LOS REGISTROS.
           Long maxIdPrivilegyRestriccAccesoUsuario = Long.valueOf(0);
           for (PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO : privilegyRestriccAccesoUsuarioDTOS) {
               maxIdPrivilegyRestriccAccesoUsuario = privilegyRestriccAccesoUsuarioRepository.findMaxIdPrivilegyRestriccAccesoUsuario();
               if (maxIdPrivilegyRestriccAccesoUsuario == null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
                  maxIdPrivilegyRestriccAccesoUsuario = Long.valueOf(0);
               }
               privilegyRestriccAccesoUsuarioDTO.setIdPrivilegyRestriccAccesoUsuario(maxIdPrivilegyRestriccAccesoUsuario+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.

               //OBTENCIÓN AUTOMÁTICA DEL NÚMERO DE REGISTRO:
               SimpleDateFormat objetoFechaHMS = new SimpleDateFormat("yyyyMMddHHmmss");
               String fechaHMSPrivilegyRestriccAccesoUsuarioinIntermedios = objetoFechaHMS.format(privilegyRestriccAccesoUsuarioDTO.getFechaHMSIngresoPrivilegyRestriccAccesoUsuario());
               privilegyRestriccAccesoUsuarioDTO.setNumRegPrivilegyRestriccAccesoUsuario("PRIVYREST" + (maxIdPrivilegyRestriccAccesoUsuario+1) + "_" + privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario() + fechaHMSPrivilegyRestriccAccesoUsuarioinIntermedios);

               privilegyRestriccAccesoUsuarioRepository.save(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO));
           }
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_CREADOS_EXITO, true);
        }
        if ((privilegyRestriccAccesoUsuarioDTOS==null)||(privilegyRestriccAccesoUsuarioDTOS.isEmpty()==true)) {//SI LA LISTA ESTA VACÍA O ES NULA NO SE CREAN REGISTROS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_CREADOS, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporId(Long idPrivilegyRestriccAccesoUsuario) {
        Optional<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesoUsuarioId = privilegyRestriccAccesoUsuarioRepository.findByIdPrivilegyRestriccAccesoUsuario(Long.valueOf(idPrivilegyRestriccAccesoUsuario));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);

        if (privilegyRestriccAccesoUsuarioId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (privilegyRestriccAccesoUsuarioId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, ID DE FUNCIONALIDAD E ID DE ROL:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioeIdFuncionalidadeIdRol(Long idUsuario, Long idFuncionalidad, Long idRol) {
        Optional<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol = Optional.ofNullable(privilegyRestriccAccesoUsuarioRepository.findByIdUsuarioAndIdFuncionalidadAndIdRol(idUsuario, idFuncionalidad, idRol));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_NO_ENCONTRADOS, false);

        if (privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA.
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_NO_ENCONTRADOS, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, NOMBRE DE FUNCIONALIDAD Y NOMBRE DE ROL:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarPrivilegyRestriccAccesoUsuarioporIdUsuarioyNombreFuncionalidadyNombreRol(Long idUsuario, String nombreFuncionalidad, String nombreRol) {
        Optional<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesoUsuarioIdUsuarioNombreFuncNombreRol = Optional.ofNullable(privilegyRestriccAccesoUsuarioRepository.findByIdUsuarioAndNombreFuncionalidadAndNombreRol(idUsuario, nombreFuncionalidad, nombreRol));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_NO_ENCONTRADOS, false);

        if (privilegyRestriccAccesoUsuarioIdUsuarioNombreFuncNombreRol.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA.
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioIdUsuarioNombreFuncNombreRol.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (privilegyRestriccAccesoUsuarioIdUsuarioNombreFuncNombreRol.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_NO_ENCONTRADOS, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarPrivilegyRestriccAccesoUsuario(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO) {
        Optional<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesoUsuarioId = privilegyRestriccAccesoUsuarioRepository.findByIdPrivilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO.getIdPrivilegyRestriccAccesoUsuario());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);

        if (privilegyRestriccAccesoUsuarioId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA LA COMBINACIÓN ÚNICA DE IDS.
           if ( (privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario().equals(privilegyRestriccAccesoUsuarioId.get().getUsuario().getIdUsuario())==true)&&(privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad().equals(privilegyRestriccAccesoUsuarioId.get().getFuncionalidad().getIdFuncionalidad())==true)&&(privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol().equals(privilegyRestriccAccesoUsuarioId.get().getRol().getIdRol())==true) ) {//SI LOS IDS SON IGUALES A LOS ALMACENADOS EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO.
              PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario = privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO);
              privilegyRestriccAccesoUsuarioRepository.save(privilegyRestriccAccesoUsuario);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if ( (privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario().equals(privilegyRestriccAccesoUsuarioId.get().getUsuario().getIdUsuario())==false)||(privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad().equals(privilegyRestriccAccesoUsuarioId.get().getFuncionalidad().getIdFuncionalidad())==false)||(privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol().equals(privilegyRestriccAccesoUsuarioId.get().getRol().getIdRol())==false) ) {//SI LOS IDS SON DIFERENTES A LOS ALMACENADOS EN LA TABLA DE LA BASE DE DATOS SE BUSCA SI LA COMBINACIÓN YA EXISTE EN OTRO REGISTRO.
              PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol = privilegyRestriccAccesoUsuarioRepository.findByIdUsuarioAndIdFuncionalidadAndIdRol(privilegyRestriccAccesoUsuarioDTO.getUsuarioDTO().getIdUsuario(), privilegyRestriccAccesoUsuarioDTO.getFuncionalidadDTO().getIdFuncionalidad(), privilegyRestriccAccesoUsuarioDTO.getRolDTO().getIdRol());

              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaIdUsuarioIdFuncionalidadIdRolRegistroEncontrado=0;

              if (!(privilegyRestriccAccesoUsuarioIdUsuarioIdFuncionalidadIdRol==null)) {//SI ENCONTRO LA COMBINACIÓN EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO.
                 banderaIdUsuarioIdFuncionalidadIdRolRegistroEncontrado=1;
              }

              if (banderaIdUsuarioIdFuncionalidadIdRolRegistroEncontrado==1) {//SI LA COMBINACIÓN YA EXISTE EN OTRO REGISTRO MUESTRA UN MENSAJE DE PRIVILEGIO Y RESTRICCIÓN REPETIDOS.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_PRIVILEGIO_Y_RESTRICCION_YA_EXISTEN, false);
                 respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
              }
              if (banderaIdUsuarioIdFuncionalidadIdRolRegistroEncontrado==0) {//SI LA COMBINACIÓN NO EXISTE EN OTRO REGISTRO SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 PrivilegyRestriccAccesoUsuario privilegyRestriccAccesoUsuario = privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuario(privilegyRestriccAccesoUsuarioDTO);
                 privilegyRestriccAccesoUsuarioRepository.save(privilegyRestriccAccesoUsuario);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (privilegyRestriccAccesoUsuarioId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarPrivilegyRestriccAccesoUsuario(Long idPrivilegyRestriccAccesoUsuario) {
        Optional<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesoUsuarioId = privilegyRestriccAccesoUsuarioRepository.findById(idPrivilegyRestriccAccesoUsuario);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);

        if (privilegyRestriccAccesoUsuarioId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioDAO.privilegyRestriccAccesoUsuarioDTO(privilegyRestriccAccesoUsuarioId.get()));
           privilegyRestriccAccesoUsuarioRepository.delete(privilegyRestriccAccesoUsuarioId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (privilegyRestriccAccesoUsuarioId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }

    //VACIAR REGISTROS POR ID DE USUARIO:
    @Override//SOBREESCRIBIMOS EL METODO DE VACIAR REGISTROS.
    public RespuestaDTO vaciarPrivilegyRestriccAccesosUsuariosporIdUsuario(Long idUsuario) {
        List<PrivilegyRestriccAccesoUsuario> privilegyRestriccAccesosUsuarios = privilegyRestriccAccesoUsuarioRepository.findAllPrivilegyRestriccAccesosUsuarios(null, null, null, null, null, null, idUsuario, null, null);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_VACIADOS, false);

        if ( (privilegyRestriccAccesosUsuarios==null)||(privilegyRestriccAccesosUsuarios.isEmpty()==false) ) {//EN CASO DE QUE LA LISTA CONTENGA DATOS SE VACÍAN LOS REGISTROS.
           privilegyRestriccAccesoUsuarioRepository.deletePrivilegyRestriccAccesosUsuariosByIdUsuario(idUsuario);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_VACIADOS_EXITO, true);
        }
        if ( (privilegyRestriccAccesosUsuarios==null)||(privilegyRestriccAccesosUsuarios.isEmpty()==true) ) {//EN CASO DE QUE LA LISTA ESTE VACIA NO SE VACÍAN REGISTROS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTROS_NO_VACIADOS, false);
           respuestaDTO.setPrivilegyRestriccAccesoUsuarioDTO(null);
        }

        return respuestaDTO;
    }
}
