//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.HistorialMovimientoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.HistorialMovimientoEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.HistorialMovimientoEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialMovimientoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.HistorialMovimientoEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
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
public class HistorialMovimientoEmpleadoServiceImpl implements HistorialMovimientoEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private HistorialMovimientoEmpleadoDAO historialMovimientoEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private HistorialMovimientoEmpleadoRepository historialMovimientoEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento) {
        return historialMovimientoEmpleadoRepository.findTotalRegistros(idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<HistorialMovimientoEmpleadoDTO> listarHistorialesMovimientosEmpleados(Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento, String orderBy, String orderMode) {
        List<HistorialMovimientoEmpleado> historialesMovimientosEmpleados = historialMovimientoEmpleadoRepository.findAllHistorialesMovimientosEmpleados(idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento, orderBy, orderMode);
        List<HistorialMovimientoEmpleadoDTO> historialMovimientoEmpleadoDTOS = new ArrayList<>();
        
        for (HistorialMovimientoEmpleado historialMovimientoEmpleado : historialesMovimientosEmpleados){
            historialMovimientoEmpleadoDTOS.add(historialMovimientoEmpleadoDAO.historialMovimientoEmpleadoDTO(historialMovimientoEmpleado));
        }
        
        return historialMovimientoEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<HistorialMovimientoEmpleadoDTO> listarHistorialesMovimientosEmpleadosPag(Pageable pageable, Long idHistorialMovimientoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombreTipoMovimiento, String orderBy, String orderMode) {
        Slice<HistorialMovimientoEmpleado> historialesMovimientosEmpleados = historialMovimientoEmpleadoRepository.findAllHistorialesMovimientosEmpleadosPag(pageable, idHistorialMovimientoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombreTipoMovimiento, orderBy, orderMode);
        return historialesMovimientosEmpleados.map(historialMovimientoEmpleado -> historialMovimientoEmpleadoDAO.historialMovimientoEmpleadoDTO(historialMovimientoEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearHistorialMovimientoEmpleado(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO) {
        Long maxIdHistorialMovimientoEmpleado=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        maxIdHistorialMovimientoEmpleado = historialMovimientoEmpleadoRepository.findMaxIdHistorialMovimientoEmpleado();
        if (maxIdHistorialMovimientoEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
           maxIdHistorialMovimientoEmpleado=Long.valueOf(0);
        }
        historialMovimientoEmpleadoDTO.setIdHistorialMovimientoEmpleado(maxIdHistorialMovimientoEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
        
        historialMovimientoEmpleadoRepository.save(historialMovimientoEmpleadoDAO.historialMovimientoEmpleado(historialMovimientoEmpleadoDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarHistorialMovimientoEmpleadoporId(Long idHistorialMovimientoEmpleado) {
        Optional<HistorialMovimientoEmpleado> historialMovimientoEmpleadoId = historialMovimientoEmpleadoRepository.findByIdHistorialMovimientoEmpleado(Long.valueOf(idHistorialMovimientoEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (historialMovimientoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(historialMovimientoEmpleadoDAO.historialMovimientoEmpleadoDTO(historialMovimientoEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (historialMovimientoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE EMPLEADO Y FECHA, HORA, MINUTO Y SEGUNDO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarHistorialMovimientoEmpleadoporIdEmpleadoyFechaHMS(Long idEmpleado, Date fechaHMS) {
        Optional<HistorialMovimientoEmpleado> historialMovimientoEmpleadoFecha = Optional.ofNullable(historialMovimientoEmpleadoRepository.findByIdEmpleadoAndFechaHMSHistorialMovimientoEmpleado(idEmpleado, fechaHMS));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (historialMovimientoEmpleadoFecha.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID DE EMPLEADO Y LA FECHA PROPORCIONADOS.
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(historialMovimientoEmpleadoDAO.historialMovimientoEmpleadoDTO(historialMovimientoEmpleadoFecha.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (historialMovimientoEmpleadoFecha.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID DE EMPLEADO Y LA FECHA PROPORCIONADOS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarHistorialMovimientoEmpleado(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO) {
        Optional<HistorialMovimientoEmpleado> historialMovimientoEmpleadoId = historialMovimientoEmpleadoRepository.findByIdHistorialMovimientoEmpleado(historialMovimientoEmpleadoDTO.getIdHistorialMovimientoEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (historialMovimientoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
           HistorialMovimientoEmpleado historialMovimientoEmpleado = historialMovimientoEmpleadoDAO.historialMovimientoEmpleado(historialMovimientoEmpleadoDTO);
           historialMovimientoEmpleadoRepository.save(historialMovimientoEmpleado);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (historialMovimientoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarHistorialMovimientoEmpleado(Long idHistorialMovimientoEmpleado) {
        Optional<HistorialMovimientoEmpleado> historialMovimientoEmpleadoId = historialMovimientoEmpleadoRepository.findById(idHistorialMovimientoEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (historialMovimientoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(historialMovimientoEmpleadoDAO.historialMovimientoEmpleadoDTO(historialMovimientoEmpleadoId.get()));
           historialMovimientoEmpleadoRepository.delete(historialMovimientoEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (historialMovimientoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setHistorialMovimientoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
