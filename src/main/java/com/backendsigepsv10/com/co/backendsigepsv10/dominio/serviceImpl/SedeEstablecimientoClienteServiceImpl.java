//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.SedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.SedeEstablecimientoClienteService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.SedeEstablecimientoClienteDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.SedeEstablecimientoClienteRepository;
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
public class SedeEstablecimientoClienteServiceImpl implements SedeEstablecimientoClienteService {
    
    @Autowired//INYECTAMOS EL DAO.
    private SedeEstablecimientoClienteDAO sedeEstablecimientoClienteDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private SedeEstablecimientoClienteRepository sedeEstablecimientoClienteRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente) {
        return sedeEstablecimientoClienteRepository.findTotalRegistros(idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<SedeEstablecimientoClienteDTO> listarSedesEstablecimientosClientes(Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente, String orderBy, String orderMode) {
        List<SedeEstablecimientoCliente> sedesEstablecimientosClientes = sedeEstablecimientoClienteRepository.findAllSedesEstablecimientosClientes(idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente, orderBy, orderMode);
        List<SedeEstablecimientoClienteDTO> sedeEstablecimientoClienteDTOS = new ArrayList<>();
        
        for (SedeEstablecimientoCliente sedeEstablecimientoCliente : sedesEstablecimientosClientes){
            sedeEstablecimientoClienteDTOS.add(sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoCliente));
        }
        
        return sedeEstablecimientoClienteDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<SedeEstablecimientoClienteDTO> listarSedesEstablecimientosClientesPag(Pageable pageable, Long idSedeEstablecimientoCliente, String keyword, String nombreRazonSocialEstablecimientoCliente, String nombreSedeEstablecimientoCliente, String estadoSedeEstablecimientoCliente, String orderBy, String orderMode) {
        Slice<SedeEstablecimientoCliente> sedesEstablecimientosClientes = sedeEstablecimientoClienteRepository.findAllSedesEstablecimientosClientesPag(pageable, idSedeEstablecimientoCliente, keyword, nombreRazonSocialEstablecimientoCliente, nombreSedeEstablecimientoCliente, estadoSedeEstablecimientoCliente, orderBy, orderMode);
        return sedesEstablecimientosClientes.map(sedeEstablecimientoCliente -> sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoCliente));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearSedeEstablecimientoCliente(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO) {
        Long maxIdSedeEstablecimientoCliente=null;
        SedeEstablecimientoCliente sedeEstablecimientoClienteNombre = sedeEstablecimientoClienteRepository.findByNombreSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getNombreSedeEstablecimientoCliente().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(sedeEstablecimientoClienteNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setSedeEstablecimientoClienteDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdSedeEstablecimientoCliente = sedeEstablecimientoClienteRepository.findMaxIdSedeEstablecimientoCliente();
           if (maxIdSedeEstablecimientoCliente==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdSedeEstablecimientoCliente=Long.valueOf(0);
           }
           sedeEstablecimientoClienteDTO.setIdSedeEstablecimientoCliente(maxIdSedeEstablecimientoCliente+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           sedeEstablecimientoClienteRepository.save(sedeEstablecimientoClienteDAO.sedeEstablecimientoCliente(sedeEstablecimientoClienteDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarSedeEstablecimientoClienteporId(Long idSedeEstablecimientoCliente) {
        Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteId = sedeEstablecimientoClienteRepository.findByIdSedeEstablecimientoCliente(Long.valueOf(idSedeEstablecimientoCliente));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (sedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setSedeEstablecimientoClienteDTO(sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoClienteId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (sedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarSedeEstablecimientoClienteporNombre(String nombreSedeEstablecimientoCliente) {
        Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteNombre = Optional.ofNullable(sedeEstablecimientoClienteRepository.findByNombreSedeEstablecimientoCliente(String.valueOf(nombreSedeEstablecimientoCliente)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (sedeEstablecimientoClienteNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setSedeEstablecimientoClienteDTO(sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoClienteNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (sedeEstablecimientoClienteNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarSedeEstablecimientoCliente(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO) {
        Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteId = sedeEstablecimientoClienteRepository.findByIdSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getIdSedeEstablecimientoCliente());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (sedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (sedeEstablecimientoClienteDTO.getNombreSedeEstablecimientoCliente().equals(sedeEstablecimientoClienteId.get().getNombreSedeEstablecimientoCliente())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              SedeEstablecimientoCliente sedeEstablecimientoCliente = sedeEstablecimientoClienteDAO.sedeEstablecimientoCliente(sedeEstablecimientoClienteDTO);
              sedeEstablecimientoClienteRepository.save(sedeEstablecimientoCliente);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (sedeEstablecimientoClienteDTO.getNombreSedeEstablecimientoCliente().equals(sedeEstablecimientoClienteId.get().getNombreSedeEstablecimientoCliente())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              SedeEstablecimientoCliente sedeEstablecimientoClienteNombre = sedeEstablecimientoClienteRepository.findByNombreSedeEstablecimientoCliente(sedeEstablecimientoClienteDTO.getNombreSedeEstablecimientoCliente().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(sedeEstablecimientoClienteNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setSedeEstablecimientoClienteDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 SedeEstablecimientoCliente sedeEstablecimientoCliente = sedeEstablecimientoClienteDAO.sedeEstablecimientoCliente(sedeEstablecimientoClienteDTO);
                 sedeEstablecimientoClienteRepository.save(sedeEstablecimientoCliente);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (sedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarSedeEstablecimientoCliente(Long idSedeEstablecimientoCliente) {
        Optional<SedeEstablecimientoCliente> sedeEstablecimientoClienteId = sedeEstablecimientoClienteRepository.findById(idSedeEstablecimientoCliente);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (sedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setSedeEstablecimientoClienteDTO(sedeEstablecimientoClienteDAO.sedeEstablecimientoClienteDTO(sedeEstablecimientoClienteId.get()));
           sedeEstablecimientoClienteRepository.delete(sedeEstablecimientoClienteId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (sedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
}
