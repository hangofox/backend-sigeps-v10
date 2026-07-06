//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialNovedadEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.HistorialNovedadEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.HistorialNovedadEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialNovedadEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.HistorialNovedadEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class HistorialNovedadEmpleadoServiceImpl implements HistorialNovedadEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private HistorialNovedadEmpleadoDAO historialNovedadEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private HistorialNovedadEmpleadoRepository historialNovedadEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado) {
        return historialNovedadEmpleadoRepository.findTotalRegistros(idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<HistorialNovedadEmpleadoDTO> listarHistorialesNovedadesEmpleados(Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado, String orderBy, String orderMode) {
        List<HistorialNovedadEmpleado> historialesNovedadesEmpleados = historialNovedadEmpleadoRepository.findAllHistorialesNovedadesEmpleados(idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado, orderBy, orderMode);
        List<HistorialNovedadEmpleadoDTO> historialNovedadEmpleadoDTOS = new ArrayList<>();
        
        for (HistorialNovedadEmpleado historialNovedadEmpleado : historialesNovedadesEmpleados){
            historialNovedadEmpleadoDTOS.add(historialNovedadEmpleadoDAO.historialNovedadEmpleadoDTO(historialNovedadEmpleado));
        }
        
        return historialNovedadEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<HistorialNovedadEmpleadoDTO> listarHistorialesNovedadesEmpleadosPag(Pageable pageable, Long idHistorialNovedadEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoNovedadEmpleado, String descripcionHistorialNovedadEmpleado, String orderBy, String orderMode) {
        Slice<HistorialNovedadEmpleado> historialesNovedadesEmpleados = historialNovedadEmpleadoRepository.findAllHistorialesNovedadesEmpleadosPag(pageable, idHistorialNovedadEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoNovedadEmpleado, descripcionHistorialNovedadEmpleado, orderBy, orderMode);
        return historialesNovedadesEmpleados.map(historialNovedadEmpleado -> historialNovedadEmpleadoDAO.historialNovedadEmpleadoDTO(historialNovedadEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearHistorialNovedadEmpleado(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO) {
        Long maxIdHistorialNovedadEmpleado=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        maxIdHistorialNovedadEmpleado = historialNovedadEmpleadoRepository.findMaxIdHistorialNovedadEmpleado();
        if (maxIdHistorialNovedadEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
           maxIdHistorialNovedadEmpleado=Long.valueOf(0);
        }
        historialNovedadEmpleadoDTO.setIdHistorialNovedadEmpleado(maxIdHistorialNovedadEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
        
        historialNovedadEmpleadoRepository.save(historialNovedadEmpleadoDAO.historialNovedadEmpleado(historialNovedadEmpleadoDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarHistorialNovedadEmpleadoporId(Long idHistorialNovedadEmpleado) {
        Optional<HistorialNovedadEmpleado> historialNovedadEmpleadoId = historialNovedadEmpleadoRepository.findByIdHistorialNovedadEmpleado(Long.valueOf(idHistorialNovedadEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (historialNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setHistorialNovedadEmpleadoDTO(historialNovedadEmpleadoDAO.historialNovedadEmpleadoDTO(historialNovedadEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (historialNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN DEL EMPLEADO Y DESCRIPCIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarHistorialNovedadEmpleadoporNumeroDocumentoIdentificacionyDescripcion(String numeroDocumentoIdentificacionEmpleado, String descripcionHistorialNovedadEmpleado) {
        Optional<HistorialNovedadEmpleado> historialNovedadEmpleadoNumDocDesc = Optional.ofNullable(historialNovedadEmpleadoRepository.findByNumeroDocumentoIdentificacionEmpleadoAndDescripcionHistorialNovedadEmpleado(numeroDocumentoIdentificacionEmpleado, descripcionHistorialNovedadEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (historialNovedadEmpleadoNumDocDesc.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN Y LA DESCRIPCIÓN PROPORCIONADOS.
           respuestaDTO.setHistorialNovedadEmpleadoDTO(historialNovedadEmpleadoDAO.historialNovedadEmpleadoDTO(historialNovedadEmpleadoNumDocDesc.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (historialNovedadEmpleadoNumDocDesc.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA CON EL NÚMERO DE DOCUMENTO DE IDENTIFICACIÓN Y LA DESCRIPCIÓN PROPORCIONADOS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarHistorialNovedadEmpleado(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO) {
        Optional<HistorialNovedadEmpleado> historialNovedadEmpleadoId = historialNovedadEmpleadoRepository.findByIdHistorialNovedadEmpleado(historialNovedadEmpleadoDTO.getIdHistorialNovedadEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (historialNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
           HistorialNovedadEmpleado historialNovedadEmpleado = historialNovedadEmpleadoDAO.historialNovedadEmpleado(historialNovedadEmpleadoDTO);
           historialNovedadEmpleadoRepository.save(historialNovedadEmpleado);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (historialNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setHistorialNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarHistorialNovedadEmpleado(Long idHistorialNovedadEmpleado) {
        Optional<HistorialNovedadEmpleado> historialNovedadEmpleadoId = historialNovedadEmpleadoRepository.findById(idHistorialNovedadEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (historialNovedadEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setHistorialNovedadEmpleadoDTO(historialNovedadEmpleadoDAO.historialNovedadEmpleadoDTO(historialNovedadEmpleadoId.get()));
           historialNovedadEmpleadoRepository.delete(historialNovedadEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (historialNovedadEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialNovedadEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
