//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AuditoriaSistemaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.AuditoriaSistemaService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.AuditoriaSistemaDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AuditoriaSistema;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.AuditoriaSistemaRepository;
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
* @Since 12/06/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class AuditoriaSistemaServiceImpl implements AuditoriaSistemaService {
    
    @Autowired//INYECTAMOS EL DAO.
    private AuditoriaSistemaDAO auditoriaSistemaDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private AuditoriaSistemaRepository auditoriaSistemaRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idAuditoriaSistema, String keyword) {
        return auditoriaSistemaRepository.findTotalRegistros(idAuditoriaSistema, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<AuditoriaSistemaDTO> listarAuditoriasSistema(Long idAuditoriaSistema, String keyword, String orderBy, String orderMode) {
        List<AuditoriaSistema> auditoriasSistema = auditoriaSistemaRepository.findAllAuditoriasSistema(idAuditoriaSistema, keyword, orderBy, orderMode);
        List<AuditoriaSistemaDTO> auditoriaSistemaDTOS = new ArrayList<>();
        
        for (AuditoriaSistema auditoriaSistema : auditoriasSistema){
            auditoriaSistemaDTOS.add(auditoriaSistemaDAO.auditoriaSistemaDTO(auditoriaSistema));
        }
        
        return auditoriaSistemaDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<AuditoriaSistemaDTO> listarAuditoriasSistemaPag(Pageable pageable, Long idAuditoriaSistema, String keyword, String orderBy, String orderMode) {
        Slice<AuditoriaSistema> auditoriasSistema = auditoriaSistemaRepository.findAllAuditoriasSistemaPag(pageable, idAuditoriaSistema, keyword, orderBy, orderMode);
        return auditoriasSistema.map(auditoriaSistema -> auditoriaSistemaDAO.auditoriaSistemaDTO(auditoriaSistema));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearAuditoriaSistema(AuditoriaSistemaDTO auditoriaSistemaDTO) {
        Long maxIdAuditoriaSistema=null;
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        maxIdAuditoriaSistema = auditoriaSistemaRepository.findMaxIdAuditoriaSistema();
        if (maxIdAuditoriaSistema==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
           maxIdAuditoriaSistema=Long.valueOf(0);
        }
        auditoriaSistemaDTO.setIdAuditoriaSistema(maxIdAuditoriaSistema+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
        
        auditoriaSistemaRepository.save(auditoriaSistemaDAO.auditoriaSistema(auditoriaSistemaDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarAuditoriaSistemabyId(Long idAuditoriaSistema) {
        Optional<AuditoriaSistema> auditoriaSistemaId = auditoriaSistemaRepository.findByIdAuditoriaSistema(Long.valueOf(idAuditoriaSistema));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (auditoriaSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setAuditoriaSistemaDTO(auditoriaSistemaDAO.auditoriaSistemaDTO(auditoriaSistemaId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (auditoriaSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAuditoriaSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID DE USUARIO, FECHA, HORA, MINUTO Y SEGUNDO DE LA AUDITORIA DEL SISTEMA Y ACCIÓN DE USUARIO EN SISTEMA:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarAuditoriaSistemaporIdUsuarioyFechayAccionUsuarioSistema(Long idUsuario, Date fechaHMSAuditoriaSistema, String accionUsuarioSistema) {
        Optional<AuditoriaSistema> auditoriaSistemaIdUsuarioFechaAccion = Optional.ofNullable(auditoriaSistemaRepository.findByIdUsuarioAndFechaHMSAuditoriaSistemaAndAccionUsuarioSistema(idUsuario, fechaHMSAuditoriaSistema, accionUsuarioSistema));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_AUDITORIA_SISTEMA_NO_ENCONTRADO, false);
        
        if (auditoriaSistemaIdUsuarioFechaAccion.isPresent()==true) {//SI ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA.
           respuestaDTO.setAuditoriaSistemaDTO(auditoriaSistemaDAO.auditoriaSistemaDTO(auditoriaSistemaIdUsuarioFechaAccion.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (auditoriaSistemaIdUsuarioFechaAccion.isPresent()==false) {//SI NO ENCONTRO EL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE CONSULTA NO EXITOSA.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_AUDITORIA_SISTEMA_NO_ENCONTRADO, false);
           respuestaDTO.setAuditoriaSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAuditoriaSistema(AuditoriaSistemaDTO auditoriaSistemaDTO) {
        Optional<AuditoriaSistema> auditoriaSistemaId = auditoriaSistemaRepository.findByIdAuditoriaSistema(auditoriaSistemaDTO.getIdAuditoriaSistema());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (auditoriaSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
           AuditoriaSistema auditoriaSistema = auditoriaSistemaDAO.auditoriaSistema(auditoriaSistemaDTO);
           auditoriaSistemaRepository.save(auditoriaSistema);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (auditoriaSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setAuditoriaSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAuditoriaSistema(Long idAuditoriaSistema) {
        Optional<AuditoriaSistema> auditoriaSistemaId = auditoriaSistemaRepository.findById(idAuditoriaSistema);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (auditoriaSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setAuditoriaSistemaDTO(auditoriaSistemaDAO.auditoriaSistemaDTO(auditoriaSistemaId.get()));
           auditoriaSistemaRepository.delete(auditoriaSistemaId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (auditoriaSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAuditoriaSistemaDTO(null);
        }
        
        return respuestaDTO;
    }
}
