//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.EstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.EstablecimientoClienteService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.EstablecimientoClienteDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.EstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.EstablecimientoClienteRepository;
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
public class EstablecimientoClienteServiceImpl implements EstablecimientoClienteService {
    
    @Autowired//INYECTAMOS EL DAO.
    private EstablecimientoClienteDAO establecimientoClienteDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private EstablecimientoClienteRepository establecimientoClienteRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente) {
        return establecimientoClienteRepository.findTotalRegistros(idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<EstablecimientoClienteDTO> listarEstablecimientosClientes(Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente, String orderBy, String orderMode) {
        List<EstablecimientoCliente> establecimientosClientes = establecimientoClienteRepository.findAllEstablecimientosClientes(idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente, orderBy, orderMode);
        List<EstablecimientoClienteDTO> establecimientoClienteDTOS = new ArrayList<>();
        
        for (EstablecimientoCliente establecimientoCliente : establecimientosClientes){
            establecimientoClienteDTOS.add(establecimientoClienteDAO.establecimientoClienteDTO(establecimientoCliente));
        }
        
        return establecimientoClienteDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<EstablecimientoClienteDTO> listarEstablecimientosClientesPag(Pageable pageable, Long idEstablecimientoCliente, String keyword, String nombreTipoDocumentoIdentificacion, String numeroDocumentoIdentificacionEstablecimientoCliente, String nombreRazonSocialEstablecimientoCliente, String estadoEstablecimientoCliente, String orderBy, String orderMode) {
        Slice<EstablecimientoCliente> establecimientosClientes = establecimientoClienteRepository.findAllEstablecimientosClientesPag(pageable, idEstablecimientoCliente, keyword, nombreTipoDocumentoIdentificacion, numeroDocumentoIdentificacionEstablecimientoCliente, nombreRazonSocialEstablecimientoCliente, estadoEstablecimientoCliente, orderBy, orderMode);
        return establecimientosClientes.map(establecimientoCliente -> establecimientoClienteDAO.establecimientoClienteDTO(establecimientoCliente));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearEstablecimientoCliente(EstablecimientoClienteDTO establecimientoClienteDTO) {
        Long maxIdEstablecimientoCliente=null;
        EstablecimientoCliente establecimientoClienteNumeroDocumento = establecimientoClienteRepository.findByNumeroDocumentoIdentificacionEstablecimientoCliente(establecimientoClienteDTO.getNumeroDocumentoIdentificacionEstablecimientoCliente());
        EstablecimientoCliente establecimientoClienteNombre = establecimientoClienteRepository.findFirstByNombreRazonSocialEstablecimientoCliente(establecimientoClienteDTO.getNombreRazonSocialEstablecimientoCliente().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNumeroDocumentoIdentificacionRegistroEncontrado=0;
        long banderaNombreRegistroEncontrado=0;
        
        if (!(establecimientoClienteNumeroDocumento==null)) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           banderaNumeroDocumentoIdentificacionRegistroEncontrado=1;
        }
        
        if (!(establecimientoClienteNombre==null)) {//SI ENCONTRO EL NOMBRE DE LA RAZÓN SOCIAL DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNumeroDocumentoIdentificacionRegistroEncontrado==1) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_YA_EXISTE, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        if ((banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) && (banderaNombreRegistroEncontrado==1)) {//SI NO ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PERO SI ENCONTRO EL NOMBRE DE LA RAZÓN SOCIAL DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        if ((banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) && (banderaNombreRegistroEncontrado==0)) {//SI NO ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN NI EL NOMBRE DE LA RAZÓN SOCIAL DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE.
           maxIdEstablecimientoCliente = establecimientoClienteRepository.findMaxIdEstablecimientoCliente();
           if (maxIdEstablecimientoCliente==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdEstablecimientoCliente=Long.valueOf(0);
           }
           establecimientoClienteDTO.setIdEstablecimientoCliente(maxIdEstablecimientoCliente+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           establecimientoClienteRepository.save(establecimientoClienteDAO.establecimientoCliente(establecimientoClienteDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarEstablecimientoClienteporId(Long idEstablecimientoCliente) {
        Optional<EstablecimientoCliente> establecimientoClienteId = establecimientoClienteRepository.findByIdEstablecimientoCliente(Long.valueOf(idEstablecimientoCliente));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (establecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setEstablecimientoClienteDTO(establecimientoClienteDAO.establecimientoClienteDTO(establecimientoClienteId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (establecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarEstablecimientoClienteporNumeroDocumentoIdentificacion(String numeroDocumentoIdentificacionEstablecimientoCliente) {
        Optional<EstablecimientoCliente> establecimientoClienteNumeroDocumento = Optional.ofNullable(establecimientoClienteRepository.findByNumeroDocumentoIdentificacionEstablecimientoCliente(numeroDocumentoIdentificacionEstablecimientoCliente));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_NO_ENCONTRADO, false);
        
        if (establecimientoClienteNumeroDocumento.isPresent()==true) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO.setEstablecimientoClienteDTO(establecimientoClienteDAO.establecimientoClienteDTO(establecimientoClienteNumeroDocumento.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (establecimientoClienteNumeroDocumento.isPresent()==false) {//SI NO ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_NO_ENCONTRADO, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarEstablecimientoCliente(EstablecimientoClienteDTO establecimientoClienteDTO) {
        Optional<EstablecimientoCliente> establecimientoClienteId = establecimientoClienteRepository.findByIdEstablecimientoCliente(establecimientoClienteDTO.getIdEstablecimientoCliente());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (establecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN Y EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
           long banderaNumeroDocumentoIdentificacionRegistroEncontrado=0;
           long banderaNombreRegistroEncontrado=0;
           
           EstablecimientoCliente establecimientoClienteNumeroDocumento = establecimientoClienteRepository.findByNumeroDocumentoIdentificacionEstablecimientoCliente(establecimientoClienteDTO.getNumeroDocumentoIdentificacionEstablecimientoCliente());
           EstablecimientoCliente establecimientoClienteNombre = establecimientoClienteRepository.findFirstByNombreRazonSocialEstablecimientoCliente(establecimientoClienteDTO.getNombreRazonSocialEstablecimientoCliente().toUpperCase());
           
           if ( (!(establecimientoClienteNumeroDocumento==null)) && (!(establecimientoClienteNumeroDocumento.getIdEstablecimientoCliente().equals(establecimientoClienteDTO.getIdEstablecimientoCliente()))) ) {//SI ENCONTRO EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN EN LA TABLA DE LA BASE DE DATOS Y PERTENECE A UN REGISTRO DIFERENTE AL QUE SE ESTA ACTUALIZANDO, SE CONSIDERA REPETIDO.
              banderaNumeroDocumentoIdentificacionRegistroEncontrado=1;
           }
           
           if ( (!(establecimientoClienteNombre==null)) && (!(establecimientoClienteNombre.getIdEstablecimientoCliente().equals(establecimientoClienteDTO.getIdEstablecimientoCliente()))) ) {//SI ENCONTRO EL NOMBRE DE LA RAZÓN SOCIAL EN LA TABLA DE LA BASE DE DATOS Y PERTENECE A UN REGISTRO DIFERENTE AL QUE SE ESTA ACTUALIZANDO, SE CONSIDERA REPETIDO.
              banderaNombreRegistroEncontrado=1;
           }
           
           if (banderaNumeroDocumentoIdentificacionRegistroEncontrado==1) {//SI EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PERTENECE A OTRO REGISTRO MUESTRA UN MENSAJE DE NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DE REGISTRO REPETIDO CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN PROPORCIONADO.
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_DOCUMENTO_IDENTIFICACION_YA_EXISTE, false);
              respuestaDTO.setEstablecimientoClienteDTO(null);
           }
           if ((banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) && (banderaNombreRegistroEncontrado==1)) {//SI EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN NO ESTA REPETIDO PERO EL NOMBRE DE LA RAZÓN SOCIAL PERTENECE A OTRO REGISTRO MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
              respuestaDTO.setEstablecimientoClienteDTO(null);
           }
           if ((banderaNumeroDocumentoIdentificacionRegistroEncontrado==0) && (banderaNombreRegistroEncontrado==0)) {//SI EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN NI EL NOMBRE DE LA RAZÓN SOCIAL PERTENECEN A OTRO REGISTRO SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              EstablecimientoCliente establecimientoCliente = establecimientoClienteDAO.establecimientoCliente(establecimientoClienteDTO);
              establecimientoClienteRepository.save(establecimientoCliente);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
        }
        if (establecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarEstablecimientoCliente(Long idEstablecimientoCliente) {
        Optional<EstablecimientoCliente> establecimientoClienteId = establecimientoClienteRepository.findById(idEstablecimientoCliente);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (establecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setEstablecimientoClienteDTO(establecimientoClienteDAO.establecimientoClienteDTO(establecimientoClienteId.get()));
           establecimientoClienteRepository.delete(establecimientoClienteId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (establecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
}
