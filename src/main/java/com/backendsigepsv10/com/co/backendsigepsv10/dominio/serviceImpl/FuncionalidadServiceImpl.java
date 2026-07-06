//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.FuncionalidadDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.FuncionalidadService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.FuncionalidadDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Funcionalidad;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.FuncionalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
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
public class FuncionalidadServiceImpl implements FuncionalidadService {
    
    @Autowired//INYECTAMOS EL DAO.
    private FuncionalidadDAO funcionalidadDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private FuncionalidadRepository funcionalidadRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTEO.
    public Long contarTotalRegistros(Long idFuncionalidad, String keyword) {
        return funcionalidadRepository.findTotalRegistros(idFuncionalidad, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<FuncionalidadDTO> listarFuncionalidades(Long idFuncionalidad, String keyword, String orderBy, String orderMode) {
        List<Funcionalidad> funcionalidades = funcionalidadRepository.findAllFuncionalidades(idFuncionalidad, keyword, orderBy, orderMode);
        List<FuncionalidadDTO> funcionalidadDTOS = new ArrayList<>();
        
        for (Funcionalidad funcionalidad : funcionalidades){
            funcionalidadDTOS.add(funcionalidadDAO.funcionalidadDTO(funcionalidad));
        }
        
        return funcionalidadDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS CON PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public Slice<FuncionalidadDTO> listarFuncionalidadesPag(Pageable pageable, Long idFuncionalidad, String keyword, String orderBy, String orderMode) {
        Slice<Funcionalidad> funcionalidades = funcionalidadRepository.findAllFuncionalidadesPag(pageable, idFuncionalidad, keyword, orderBy, orderMode);
        return funcionalidades.map(funcionalidad -> funcionalidadDAO.funcionalidadDTO(funcionalidad));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearFuncionalidad(FuncionalidadDTO funcionalidadDTO) {
        Long maxIdFuncionalidad=null;
        Funcionalidad funcionalidadNombre = funcionalidadRepository.findByNombreFuncionalidad(funcionalidadDTO.getNombreFuncionalidad().toUpperCase());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNombreRegistroEncontrado=0;
        
        if (!(funcionalidadNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           banderaNombreRegistroEncontrado=1;
        }
        
        //System.out.println("INGRESA METODO CREAR.");
        if (banderaNombreRegistroEncontrado==1) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
           respuestaDTO.setFuncionalidadDTO(null);
        }
        if ((banderaNombreRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NOMBRE PROPORCIONADO.
           maxIdFuncionalidad = funcionalidadRepository.findMaxIdFuncionalidad();
           if (maxIdFuncionalidad==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdFuncionalidad=Long.valueOf(0);
           }
           funcionalidadDTO.setIdFuncionalidad(maxIdFuncionalidad+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           funcionalidadRepository.save(funcionalidadDAO.funcionalidad(funcionalidadDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarFuncionalidadporId(Long idFuncionalidad) {
        Optional<Funcionalidad> funcionalidadId = funcionalidadRepository.findByIdFuncionalidad(Long.valueOf(idFuncionalidad));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (funcionalidadId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setFuncionalidadDTO(funcionalidadDAO.funcionalidadDTO(funcionalidadId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (funcionalidadId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setFuncionalidadDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarFuncionalidadporNombre(String nombreFuncionalidad) {
        Optional<Funcionalidad> funcionalidadNombre = Optional.ofNullable(funcionalidadRepository.findByNombreFuncionalidad(String.valueOf(nombreFuncionalidad)));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (funcionalidadNombre.isPresent()==true) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO.setFuncionalidadDTO(funcionalidadDAO.funcionalidadDTO(funcionalidadNombre.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (funcionalidadNombre.isPresent()==false) {//SI NO ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NOMBRE PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setFuncionalidadDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarFuncionalidad(FuncionalidadDTO funcionalidadDTO) {
        Optional<Funcionalidad> funcionalidadId = funcionalidadRepository.findByIdFuncionalidad(funcionalidadDTO.getIdFuncionalidad());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (funcionalidadId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NOMBRE DEL REGISTRO CON EL ID PROPORCIONADO.
           if ( (funcionalidadDTO.getNombreFuncionalidad().equals(funcionalidadId.get().getNombreFuncionalidad())==true) ) {//SI EL NOMBRE DIGITADO ES IGUAL AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              Funcionalidad funcionalidad = funcionalidadDAO.funcionalidad(funcionalidadDTO);
              funcionalidadRepository.save(funcionalidad);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
           if (funcionalidadDTO.getNombreFuncionalidad().equals(funcionalidadId.get().getNombreFuncionalidad())==false) {//SI EL NOMBRE DIGITADO ES DIFERENTE AL NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS SE REALIZA BUSQUEDA PARA VERIFICAR SI ESTE NOMBRE DIGITADO EXISTE EN OTROS REGISTROS.
              //consulte por nombre e imprima si existe y si no existe
              Funcionalidad funcionalidadNombre = funcionalidadRepository.findByNombreFuncionalidad(funcionalidadDTO.getNombreFuncionalidad().toUpperCase());
              
              //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
              long banderaNombreRegistroEncontrado=0;
              
              if (!(funcionalidadNombre==null)) {//SI ENCONTRO EL NOMBRE DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NOMBRE DE REGISTRO REPETIDO CON EL NOMBRE PROPORCIONADO.
                 banderaNombreRegistroEncontrado=1;
              }
              
              if (banderaNombreRegistroEncontrado==1) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES DIFERENTE DE NULO SIGNIFICA QUE ENCONTRO EL MISMO NOMBRE ALMACENADO EN LA TABLA DE LA BASE DE DATOS Y MUESTRA UN MENSAJE DE NOMBRE DEL REGISTRO REPETIDO.
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE, false);
                 respuestaDTO.setFuncionalidadDTO(null);
              }
              if (banderaNombreRegistroEncontrado==0) {//SI LA BUSQUEDA OBTIENE QUE EL NOMBRE DIGITADO Y BUSCADO ES NULO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
                 Funcionalidad funcionalidad = funcionalidadDAO.funcionalidad(funcionalidadDTO);
                 funcionalidadRepository.save(funcionalidad);
                 respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
              }
           }
        }
        if (funcionalidadId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setFuncionalidadDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarFuncionalidad(Long idFuncionalidad) {
        Optional<Funcionalidad> funcionalidadId  = funcionalidadRepository.findById(idFuncionalidad);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (funcionalidadId .isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setFuncionalidadDTO(funcionalidadDAO.funcionalidadDTO(funcionalidadId.get()));
           funcionalidadRepository.delete(funcionalidadId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (funcionalidadId .isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setFuncionalidadDTO(null);
        }
        
        return respuestaDTO;
    }
}
