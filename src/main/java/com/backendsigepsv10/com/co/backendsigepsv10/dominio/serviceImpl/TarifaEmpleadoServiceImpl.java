//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.TarifaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.TarifaEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.TarifaEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TarifaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.TarifaEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 05/08/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class TarifaEmpleadoServiceImpl implements TarifaEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private TarifaEmpleadoDAO tarifaEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private TarifaEmpleadoRepository tarifaEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado) {
        return tarifaEmpleadoRepository.findTotalRegistros(idTarifaEmpleado, keyword, idTipoEmpleado, idTipoEmpleadoPlanta, idClasificacionEmpleadoPlanta, idSubclasificacionEmpleadoPlanta, idTipoTarifaEmpleado, anioTarifaEmpleado, estadoTarifaEmpleado);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<TarifaEmpleadoDTO> listarTarifasEmpleados(Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado, String orderBy, String orderMode) {
        List<TarifaEmpleado> tarifasEmpleados = tarifaEmpleadoRepository.findAllTarifasEmpleados(idTarifaEmpleado, keyword, idTipoEmpleado, idTipoEmpleadoPlanta, idClasificacionEmpleadoPlanta, idSubclasificacionEmpleadoPlanta, idTipoTarifaEmpleado, anioTarifaEmpleado, estadoTarifaEmpleado, orderBy, orderMode);
        List<TarifaEmpleadoDTO> tarifaEmpleadoDTOS = new ArrayList<>();
        
        for (TarifaEmpleado tarifaEmpleado : tarifasEmpleados){
            tarifaEmpleadoDTOS.add(tarifaEmpleadoDAO.tarifaEmpleadoDTO(tarifaEmpleado));
        }
        
        return tarifaEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<TarifaEmpleadoDTO> listarTarifasEmpleadosPag(Pageable pageable, Long idTarifaEmpleado, String keyword, Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado, String estadoTarifaEmpleado, String orderBy, String orderMode) {
        Slice<TarifaEmpleado> tarifasEmpleados = tarifaEmpleadoRepository.findAllTarifasEmpleadosPag(pageable, idTarifaEmpleado, keyword, idTipoEmpleado, idTipoEmpleadoPlanta, idClasificacionEmpleadoPlanta, idSubclasificacionEmpleadoPlanta, idTipoTarifaEmpleado, anioTarifaEmpleado, estadoTarifaEmpleado, orderBy, orderMode);
        return tarifasEmpleados.map(tarifaEmpleado -> tarifaEmpleadoDAO.tarifaEmpleadoDTO(tarifaEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearTarifaEmpleado(TarifaEmpleadoDTO tarifaEmpleadoDTO) {
        Long maxIdTarifaEmpleado=null;
        TarifaEmpleado tarifaEmpleadoDatos = tarifaEmpleadoRepository.findByIdTipoEmpleadoAndIdTipoEmpleadoPlantaAndIdClasificacionEmpleadoPlantaAndIdSubclasificacionEmpleadoPlantaAndIdTipoTarifaEmpleadoAndAnioTarifaEmpleado(tarifaEmpleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado(), tarifaEmpleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta(), tarifaEmpleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta(), tarifaEmpleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta(), tarifaEmpleadoDTO.getTipoTarifaEmpleadoDTO().getIdTipoTarifaEmpleado(), tarifaEmpleadoDTO.getAnioTarifaEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaTarifaEmpleadoTipoYAnioRegistroEncontrado=0;
        
        if (!(tarifaEmpleadoDatos==null)) {//SI ENCONTRO UNA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON LOS DATOS PROPORCIONADOS.
           banderaTarifaEmpleadoTipoYAnioRegistroEncontrado=1;
        }
        
        if (banderaTarifaEmpleadoTipoYAnioRegistroEncontrado==1) {//SI ENCONTRO UNA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON LOS DATOS PROPORCIONADOS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_TARIFA_EMPLEADO_TIPO_Y_ANIO_YA_EXISTE, false);
           respuestaDTO.setTarifaEmpleadoDTO(null);
        }
        if (banderaTarifaEmpleadoTipoYAnioRegistroEncontrado==0) {//SI NO ENCONTRO UNA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE.
           maxIdTarifaEmpleado = tarifaEmpleadoRepository.findMaxIdTarifaEmpleado();
           if (maxIdTarifaEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdTarifaEmpleado=Long.valueOf(0);
           }
           tarifaEmpleadoDTO.setIdTarifaEmpleado(maxIdTarifaEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           tarifaEmpleadoRepository.save(tarifaEmpleadoDAO.tarifaEmpleado(tarifaEmpleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTarifaEmpleadoporId(Long idTarifaEmpleado) {
        Optional<TarifaEmpleado> tarifaEmpleadoId = tarifaEmpleadoRepository.findByIdTarifaEmpleado(Long.valueOf(idTarifaEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setTarifaEmpleadoDTO(tarifaEmpleadoDAO.tarifaEmpleadoDTO(tarifaEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE TIPO DE EMPLEADO, TIPO DE EMPLEADO PLANTA, CLASIFICACION, SUBCLASIFICACION, TIPO DE TARIFA Y AÑO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarTarifaEmpleadoporIdTipoEmpleadoIdTipoEmpleadoPlantaIdClasificacionEmpleadoPlantaIdSubclasificacionEmpleadoPlantaIdTipoTarifaEmpleadoyAnio(Long idTipoEmpleado, Long idTipoEmpleadoPlanta, Long idClasificacionEmpleadoPlanta, Long idSubclasificacionEmpleadoPlanta, Long idTipoTarifaEmpleado, Long anioTarifaEmpleado) {
        Optional<TarifaEmpleado> tarifaEmpleadoDatos = Optional.ofNullable(tarifaEmpleadoRepository.findByIdTipoEmpleadoAndIdTipoEmpleadoPlantaAndIdClasificacionEmpleadoPlantaAndIdSubclasificacionEmpleadoPlantaAndIdTipoTarifaEmpleadoAndAnioTarifaEmpleado(idTipoEmpleado, idTipoEmpleadoPlanta, idClasificacionEmpleadoPlanta, idSubclasificacionEmpleadoPlanta, idTipoTarifaEmpleado, anioTarifaEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NO_ENCONTRADO, false);
        
        if (tarifaEmpleadoDatos.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON LOS DATOS PROPORCIONADOS.
           respuestaDTO.setTarifaEmpleadoDTO(tarifaEmpleadoDAO.tarifaEmpleadoDTO(tarifaEmpleadoDatos.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (tarifaEmpleadoDatos.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA CON LOS DATOS PROPORCIONADOS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NO_ENCONTRADO, false);
           respuestaDTO.setTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarTarifaEmpleado(TarifaEmpleadoDTO tarifaEmpleadoDTO) {
        Optional<TarifaEmpleado> tarifaEmpleadoId = tarifaEmpleadoRepository.findByIdTarifaEmpleado(tarifaEmpleadoDTO.getIdTarifaEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (tarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA SI EXISTE OTRA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO.
           //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
           long banderaTarifaEmpleadoTipoYAnioRegistroEncontrado=0;
           
           TarifaEmpleado tarifaEmpleadoDatos = tarifaEmpleadoRepository.findByIdTipoEmpleadoAndIdTipoEmpleadoPlantaAndIdClasificacionEmpleadoPlantaAndIdSubclasificacionEmpleadoPlantaAndIdTipoTarifaEmpleadoAndAnioTarifaEmpleado(tarifaEmpleadoDTO.getTipoEmpleadoDTO().getIdTipoEmpleado(), tarifaEmpleadoDTO.getTipoEmpleadoPlantaDTO().getIdTipoEmpleadoPlanta(), tarifaEmpleadoDTO.getClasificacionEmpleadoPlantaDTO().getIdClasificacionEmpleadoPlanta(), tarifaEmpleadoDTO.getSubclasificacionEmpleadoPlantaDTO().getIdSubclasificacionEmpleadoPlanta(), tarifaEmpleadoDTO.getTipoTarifaEmpleadoDTO().getIdTipoTarifaEmpleado(), tarifaEmpleadoDTO.getAnioTarifaEmpleado());
           
           if ( (!(tarifaEmpleadoDatos==null)) && (!(tarifaEmpleadoDatos.getIdTarifaEmpleado().equals(tarifaEmpleadoDTO.getIdTarifaEmpleado()))) ) {//SI ENCONTRO UNA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO Y PERTENECE A UN REGISTRO DIFERENTE AL QUE SE ESTA ACTUALIZANDO, SE CONSIDERA REPETIDO.
              banderaTarifaEmpleadoTipoYAnioRegistroEncontrado=1;
           }
           
           if (banderaTarifaEmpleadoTipoYAnioRegistroEncontrado==1) {//SI LA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO PERTENECE A OTRO REGISTRO MUESTRA UN MENSAJE DE REGISTRO REPETIDO CON LOS DATOS PROPORCIONADOS.
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_TARIFA_EMPLEADO_TIPO_Y_ANIO_YA_EXISTE, false);
              respuestaDTO.setTarifaEmpleadoDTO(null);
           }
           if (banderaTarifaEmpleadoTipoYAnioRegistroEncontrado==0) {//SI LA TARIFA PARA EL MISMO PERFIL DE EMPLEADO, TIPO DE TARIFA Y AÑO NO PERTENECE A OTRO REGISTRO (ES EL MISMO REGISTRO O NO EXISTE) SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              TarifaEmpleado tarifaEmpleado = tarifaEmpleadoDAO.tarifaEmpleado(tarifaEmpleadoDTO);
              tarifaEmpleadoRepository.save(tarifaEmpleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
        }
        if (tarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarTarifaEmpleado(Long idTarifaEmpleado) {
        Optional<TarifaEmpleado> tarifaEmpleadoId = tarifaEmpleadoRepository.findById(idTarifaEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (tarifaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           tarifaEmpleadoRepository.delete(tarifaEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (tarifaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setTarifaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
