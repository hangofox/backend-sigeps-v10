//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.ProgramacionTurnoEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.ProgramacionTurnoEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.ProgramacionTurnoEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ProgramacionTurnoEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.ProgramacionTurnoEmpleadoRepository;
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
public class ProgramacionTurnoEmpleadoServiceImpl implements ProgramacionTurnoEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private ProgramacionTurnoEmpleadoDAO programacionTurnoEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private ProgramacionTurnoEmpleadoRepository programacionTurnoEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado) {
        return programacionTurnoEmpleadoRepository.findTotalRegistros(idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<ProgramacionTurnoEmpleadoDTO> listarProgramacionesTurnosEmpleados(Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado, String orderBy, String orderMode) {
        List<ProgramacionTurnoEmpleado> programacionesTurnosEmpleados = programacionTurnoEmpleadoRepository.findAllProgramacionesTurnosEmpleados(idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado, orderBy, orderMode);
        List<ProgramacionTurnoEmpleadoDTO> programacionTurnoEmpleadoDTOS = new ArrayList<>();
        
        for (ProgramacionTurnoEmpleado programacionTurnoEmpleado : programacionesTurnosEmpleados){
            programacionTurnoEmpleadoDTOS.add(programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(programacionTurnoEmpleado));
        }
        
        return programacionTurnoEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<ProgramacionTurnoEmpleadoDTO> listarProgramacionesTurnosEmpleadosPag(Pageable pageable, Long idProgramacionTurnoEmpleado, String keyword, String nombresEmpleado, String primerApellidoEmpleado, String nombrePuestoSedeEstablecimientoCliente, String nombreTurno, String estadoProgramacionTurnoEmpleado, String orderBy, String orderMode) {
        Slice<ProgramacionTurnoEmpleado> programacionesTurnosEmpleados = programacionTurnoEmpleadoRepository.findAllProgramacionesTurnosEmpleadosPag(pageable, idProgramacionTurnoEmpleado, keyword, nombresEmpleado, primerApellidoEmpleado, nombrePuestoSedeEstablecimientoCliente, nombreTurno, estadoProgramacionTurnoEmpleado, orderBy, orderMode);
        return programacionesTurnosEmpleados.map(programacionTurnoEmpleado -> programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(programacionTurnoEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearProgramacionTurnoEmpleado(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO) {
        Long maxIdProgramacionTurnoEmpleado=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        maxIdProgramacionTurnoEmpleado = programacionTurnoEmpleadoRepository.findMaxIdProgramacionTurnoEmpleado();
        if (maxIdProgramacionTurnoEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
           maxIdProgramacionTurnoEmpleado=Long.valueOf(0);
        }
        programacionTurnoEmpleadoDTO.setIdProgramacionTurnoEmpleado(maxIdProgramacionTurnoEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
        
        programacionTurnoEmpleadoRepository.save(programacionTurnoEmpleadoDAO.programacionTurnoEmpleado(programacionTurnoEmpleadoDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarProgramacionTurnoEmpleadoporId(Long idProgramacionTurnoEmpleado) {
        Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoId = programacionTurnoEmpleadoRepository.findByIdProgramacionTurnoEmpleado(Long.valueOf(idProgramacionTurnoEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (programacionTurnoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(programacionTurnoEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (programacionTurnoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE EMPLEADO Y NOMBRE DE TURNO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarProgramacionTurnoEmpleadoporIdEmpleadoyNombreTurno(Long idEmpleado, String nombreTurno) {
        Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoIdEmpleadoNombreTurno = Optional.ofNullable(programacionTurnoEmpleadoRepository.findByIdEmpleadoAndNombreTurno(idEmpleado, nombreTurno));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
        
        if (programacionTurnoEmpleadoIdEmpleadoNombreTurno.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID DE EMPLEADO Y EL NOMBRE DE TURNO PROPORCIONADOS.
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(programacionTurnoEmpleadoIdEmpleadoNombreTurno.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (programacionTurnoEmpleadoIdEmpleadoNombreTurno.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID DE EMPLEADO Y EL NOMBRE DE TURNO PROPORCIONADOS.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NOMBRE_NO_ENCONTRADO, false);
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarProgramacionTurnoEmpleado(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO) {
        Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoId = programacionTurnoEmpleadoRepository.findByIdProgramacionTurnoEmpleado(programacionTurnoEmpleadoDTO.getIdProgramacionTurnoEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (programacionTurnoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
           ProgramacionTurnoEmpleado programacionTurnoEmpleado = programacionTurnoEmpleadoDAO.programacionTurnoEmpleado(programacionTurnoEmpleadoDTO);
           programacionTurnoEmpleadoRepository.save(programacionTurnoEmpleado);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (programacionTurnoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarProgramacionTurnoEmpleado(Long idProgramacionTurnoEmpleado) {
        Optional<ProgramacionTurnoEmpleado> programacionTurnoEmpleadoId = programacionTurnoEmpleadoRepository.findById(idProgramacionTurnoEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (programacionTurnoEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(programacionTurnoEmpleadoDAO.programacionTurnoEmpleadoDTO(programacionTurnoEmpleadoId.get()));
           programacionTurnoEmpleadoRepository.delete(programacionTurnoEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (programacionTurnoEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setProgramacionTurnoEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
