//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.PuestoSedeEstablecimientoClienteDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.PuestoSedeEstablecimientoClienteService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.PuestoSedeEstablecimientoClienteDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PuestoSedeEstablecimientoCliente;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.PuestoSedeEstablecimientoClienteRepository;
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
public class PuestoSedeEstablecimientoClienteServiceImpl implements PuestoSedeEstablecimientoClienteService {
    
    @Autowired//INYECTAMOS EL DAO.
    private PuestoSedeEstablecimientoClienteDAO puestoSedeEstablecimientoClienteDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private PuestoSedeEstablecimientoClienteRepository puestoSedeEstablecimientoClienteRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idPuestoSedeEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente) {
        return puestoSedeEstablecimientoClienteRepository.findTotalRegistros(idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<PuestoSedeEstablecimientoClienteDTO> listarPuestosSedesEstablecimientosClientes(Long idPuestoSedeEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente, String orderBy, String orderMode) {
        List<PuestoSedeEstablecimientoCliente> puestosSedesEstablecimientosClientes = puestoSedeEstablecimientoClienteRepository.findAllPuestosSedesEstablecimientosClientes(idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente, orderBy, orderMode);
        List<PuestoSedeEstablecimientoClienteDTO> puestoSedeEstablecimientoClienteDTOS = new ArrayList<>();
        
        for (PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente : puestosSedesEstablecimientosClientes){
            puestoSedeEstablecimientoClienteDTOS.add(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoCliente));
        }
        
        return puestoSedeEstablecimientoClienteDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<PuestoSedeEstablecimientoClienteDTO> listarPuestosSedesEstablecimientosClientesPag(Pageable pageable, Long idPuestoSedeEstablecimientoCliente, String keyword, String nombreSedeEstablecimientoCliente, String nombrePuestoSedeEstablecimientoCliente, String estadoPuestoSedeEstablecimientoCliente, String orderBy, String orderMode) {
        Slice<PuestoSedeEstablecimientoCliente> puestosSedesEstablecimientosClientes = puestoSedeEstablecimientoClienteRepository.findAllPuestosSedesEstablecimientosClientesPag(pageable, idPuestoSedeEstablecimientoCliente, keyword, nombreSedeEstablecimientoCliente, nombrePuestoSedeEstablecimientoCliente, estadoPuestoSedeEstablecimientoCliente, orderBy, orderMode);
        return puestosSedesEstablecimientosClientes.map(puestoSedeEstablecimientoCliente -> puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoCliente));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearPuestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO) {
        Long maxIdPuestoSedeEstablecimientoCliente=null;
        PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoClienteNombre = puestoSedeEstablecimientoClienteRepository.findByNombrePuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getNombrePuestoSedeEstablecimientoCliente().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(puestoSedeEstablecimientoClienteNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdPuestoSedeEstablecimientoCliente = puestoSedeEstablecimientoClienteRepository.findMaxIdPuestoSedeEstablecimientoCliente();
           if (maxIdPuestoSedeEstablecimientoCliente==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdPuestoSedeEstablecimientoCliente=Long.valueOf(0);
           }
           puestoSedeEstablecimientoClienteDTO.setIdPuestoSedeEstablecimientoCliente(maxIdPuestoSedeEstablecimientoCliente+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.

           puestoSedeEstablecimientoClienteRepository.save(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarPuestoSedeEstablecimientoClienteporId(Long idPuestoSedeEstablecimientoCliente) {
        Optional<PuestoSedeEstablecimientoCliente> puestoSedeEstablecimientoClienteId = puestoSedeEstablecimientoClienteRepository.findByIdPuestoSedeEstablecimientoCliente(Long.valueOf(idPuestoSedeEstablecimientoCliente));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (puestoSedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (puestoSedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarPuestoSedeEstablecimientoClienteporNombre(String nombrePuestoSedeEstablecimientoCliente) {
        Optional<PuestoSedeEstablecimientoCliente> puestoSedeEstablecimientoClienteNombre = Optional.ofNullable(puestoSedeEstablecimientoClienteRepository.findByNombrePuestoSedeEstablecimientoCliente(String.valueOf(nombrePuestoSedeEstablecimientoCliente)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (puestoSedeEstablecimientoClienteNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (puestoSedeEstablecimientoClienteNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarPuestoSedeEstablecimientoCliente(PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO) {
        Optional<PuestoSedeEstablecimientoCliente> puestoSedeEstablecimientoClienteId = puestoSedeEstablecimientoClienteRepository.findByIdPuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getIdPuestoSedeEstablecimientoCliente());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (puestoSedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (puestoSedeEstablecimientoClienteDTO.getNombrePuestoSedeEstablecimientoCliente().equals(puestoSedeEstablecimientoClienteId.get().getNombrePuestoSedeEstablecimientoCliente())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente = puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO);
              puestoSedeEstablecimientoClienteRepository.save(puestoSedeEstablecimientoCliente);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (puestoSedeEstablecimientoClienteDTO.getNombrePuestoSedeEstablecimientoCliente().equals(puestoSedeEstablecimientoClienteId.get().getNombrePuestoSedeEstablecimientoCliente())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoClienteNombre = puestoSedeEstablecimientoClienteRepository.findByNombrePuestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO.getNombrePuestoSedeEstablecimientoCliente().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(puestoSedeEstablecimientoClienteNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 PuestoSedeEstablecimientoCliente puestoSedeEstablecimientoCliente = puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoCliente(puestoSedeEstablecimientoClienteDTO);
                 puestoSedeEstablecimientoClienteRepository.save(puestoSedeEstablecimientoCliente);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (puestoSedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarPuestoSedeEstablecimientoCliente(Long idPuestoSedeEstablecimientoCliente) {
        Optional<PuestoSedeEstablecimientoCliente> puestoSedeEstablecimientoClienteId = puestoSedeEstablecimientoClienteRepository.findById(idPuestoSedeEstablecimientoCliente);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (puestoSedeEstablecimientoClienteId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteDAO.puestoSedeEstablecimientoClienteDTO(puestoSedeEstablecimientoClienteId.get()));
           puestoSedeEstablecimientoClienteRepository.delete(puestoSedeEstablecimientoClienteId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (puestoSedeEstablecimientoClienteId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setPuestoSedeEstablecimientoClienteDTO(null);
        }
        
        return respuestaDTO;
    }
}
