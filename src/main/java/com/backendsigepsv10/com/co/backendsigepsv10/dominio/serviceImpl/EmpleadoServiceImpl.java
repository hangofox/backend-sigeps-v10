//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.EmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.EmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private EmpleadoDAO empleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EmpleadoRepository empleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado) {
        return empleadoRepository.findTotalRegistros(idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<EmpleadoDTO> listarEmpleados(Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado, String orderBy, String orderMode) {
        List<Empleado> empleados = empleadoRepository.findAllEmpleados(idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado, orderBy, orderMode);
        List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
        
        for (Empleado empleado : empleados){
            empleadoDTOS.add(empleadoDAO.empleadoDTO(empleado));
        }
        
        return empleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<EmpleadoDTO> listarEmpleadosPag(Pageable pageable, Long idEmpleado, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEmpleado, String nombresEmpleado, String primerApellidoEmpleado, String orderBy, String orderMode) {
        Slice<Empleado> empleados = empleadoRepository.findAllEmpleadosPag(pageable, idEmpleado, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEmpleado, nombresEmpleado, primerApellidoEmpleado, orderBy, orderMode);
        return empleados.map(empleado -> empleadoDAO.empleadoDTO(empleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        Long maxIdEmpleado=null;
        Empleado empleadoNumeroDocumentoIdentificacion = empleadoRepository.findByNumeroDocumentoIdentificacionEmpleado(empleadoDTO.getNumeroDocumentoIdentificacionEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNumeroDocumentoIdentificacionRegistroEncontrado=0;
        
        if (!(empleadoNumeroDocumentoIdentificacion==null)) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           banderaNumeroDocumentoIdentificacionRegistroEncontrado=1;
        }
        
        if (banderaNumeroDocumentoIdentificacionRegistroEncontrado==1) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_YA_EXISTE, false);
           respuestaDTO.setEmpleadoDTO(null);
        }
        if ((banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           maxIdEmpleado = empleadoRepository.findMaxIdEmpleado();
           if (maxIdEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdEmpleado=Long.valueOf(0);
           }
           empleadoDTO.setIdEmpleado(maxIdEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           empleadoRepository.save(empleadoDAO.empleado(empleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarEmpleadoporId(Long idEmpleado) {
        Optional<Empleado> empleadoId = empleadoRepository.findByIdEmpleado(Long.valueOf(idEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (empleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (empleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarEmpleadoporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionEmpleado) {
        Optional<Empleado> empleadoNumeroDocumento = Optional.ofNullable(empleadoRepository.findByNumeroDocumentoIdentificacionEmpleado(numeroDocumentoIdentificacionEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_NO_ENCONTRADO, false);
        
        if (empleadoNumeroDocumento.isPresent()==true) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empleadoNumeroDocumento.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (empleadoNumeroDocumento.isPresent()==false) {//SI NO ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_NO_ENCONTRADO, false);
           respuestaDTO.setEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        Optional<Empleado> empleadoId = empleadoRepository.findByIdEmpleado(empleadoDTO.getIdEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (empleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO CON EL ID PROPORCIONADO.
           //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
           long banderaNumeroDocumentoIdentificacionRegistroEncontrado=0;
           
           if ( (empleadoDTO.getNumeroDocumentoIdentificacionEmpleado().equals(empleadoId.get().getNumeroDocumentoIdentificacionEmpleado())==true) ) {//SI EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DIGITADO ES IGUAL AL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              Empleado empleado = empleadoDAO.empleado(empleadoDTO);
              empleadoRepository.save(empleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (empleadoDTO.getNumeroDocumentoIdentificacionEmpleado().equals(empleadoId.get().getNumeroDocumentoIdentificacionEmpleado())==false) {//SI EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DIGITADO ES DIFERENTE AL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NÚMERO DIGITADO EXISTE EN OTROS REGISTROS.
              Empleado empleadoNumeroDocumento = empleadoRepository.findByNumeroDocumentoIdentificacionEmpleado(empleadoDTO.getNumeroDocumentoIdentificacionEmpleado());
              
              if ((!(empleadoNumeroDocumento==null))) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
                 banderaNumeroDocumentoIdentificacionRegistroEncontrado=1;
              }
              
              if (banderaNumeroDocumentoIdentificacionRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_YA_EXISTE, false);
                 respuestaDTO.setEmpleadoDTO(null);
              }
              if (banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 Empleado empleado = empleadoDAO.empleado(empleadoDTO);
                 empleadoRepository.save(empleado);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (empleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarEmpleado(Long idEmpleado) {
        Optional<Empleado> empleadoId = empleadoRepository.findById(idEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (empleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setEmpleadoDTO(empleadoDAO.empleadoDTO(empleadoId.get()));
           empleadoRepository.delete(empleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (empleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
