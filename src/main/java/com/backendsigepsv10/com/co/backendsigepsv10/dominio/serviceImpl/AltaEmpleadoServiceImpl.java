//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.AltaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.AltaEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.AltaEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AltaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.AltaEmpleadoRepository;
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
public class AltaEmpleadoServiceImpl implements AltaEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private AltaEmpleadoDAO altaEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private AltaEmpleadoRepository altaEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idAltaEmpleado, String keyword) {
        return altaEmpleadoRepository.findTotalRegistros(idAltaEmpleado, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<AltaEmpleadoDTO> listarAltasEmpleados(Long idAltaEmpleado, String keyword, String orderBy, String orderMode) {
        List<AltaEmpleado> altasEmpleados = altaEmpleadoRepository.findAllAltasEmpleados(idAltaEmpleado, keyword, orderBy, orderMode);
        List<AltaEmpleadoDTO> altaEmpleadoDTOS = new ArrayList<>();
        
        for (AltaEmpleado altaEmpleado : altasEmpleados){
            altaEmpleadoDTOS.add(altaEmpleadoDAO.altaEmpleadoDTO(altaEmpleado));
        }
        
        return altaEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<AltaEmpleadoDTO> listarAltasEmpleadosPag(Pageable pageable, Long idAltaEmpleado, String keyword, String orderBy, String orderMode) {
        Slice<AltaEmpleado> altasEmpleados = altaEmpleadoRepository.findAllAltasEmpleadosPag(pageable, idAltaEmpleado, keyword, orderBy, orderMode);
        return altasEmpleados.map(altaEmpleado -> altaEmpleadoDAO.altaEmpleadoDTO(altaEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearAltaEmpleado(AltaEmpleadoDTO altaEmpleadoDTO) {
        Long maxIdAltaEmpleado=null;
        AltaEmpleado altaEmpleadoNumeroContrato = altaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(altaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNumeroContratoRegistroEncontrado=0;
        
        if (!(altaEmpleadoNumeroContrato==null)) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           banderaNumeroContratoRegistroEncontrado=1;
        }
        
        if (banderaNumeroContratoRegistroEncontrado==1) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NUMERO_CONTRATO_YA_EXISTE, false);
           respuestaDTO.setAltaEmpleadoDTO(null);
        }
        if ((banderaNumeroContratoRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           maxIdAltaEmpleado = altaEmpleadoRepository.findMaxIdAltaEmpleado();
           if (maxIdAltaEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdAltaEmpleado=Long.valueOf(0);
           }
           altaEmpleadoDTO.setIdAltaEmpleado(maxIdAltaEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           altaEmpleadoRepository.save(altaEmpleadoDAO.altaEmpleado(altaEmpleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarAltaEmpleadoporId(Long idAltaEmpleado) {
        Optional<AltaEmpleado> altaEmpleadoId = altaEmpleadoRepository.findByIdAltaEmpleado(Long.valueOf(idAltaEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (altaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setAltaEmpleadoDTO(altaEmpleadoDAO.altaEmpleadoDTO(altaEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (altaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAltaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE CONTRATO O ACTO ADMINISTRATIVO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarAltaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado) {
        Optional<AltaEmpleado> altaEmpleadoNumeroContrato = Optional.ofNullable(altaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(numeroContratoOActoAdmvoNombEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_REGISTRO_NO_ENCONTRADO, false);
        
        if (altaEmpleadoNumeroContrato.isPresent()==true) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO.setAltaEmpleadoDTO(altaEmpleadoDAO.altaEmpleadoDTO(altaEmpleadoNumeroContrato.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (altaEmpleadoNumeroContrato.isPresent()==false) {//SI NO ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_REGISTRO_NO_ENCONTRADO, false);
           respuestaDTO.setAltaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAltaEmpleado(AltaEmpleadoDTO altaEmpleadoDTO) {
        Optional<AltaEmpleado> altaEmpleadoId = altaEmpleadoRepository.findByIdAltaEmpleado(altaEmpleadoDTO.getIdAltaEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (altaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NÚMERO DE CONTRATO DEL REGISTRO CON EL ID PROPORCIONADO.
           //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
           long banderaNumeroContratoRegistroEncontrado=0;
           
           AltaEmpleado altaEmpleadoNumeroContrato = altaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(altaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado());
           
           if ( (!(altaEmpleadoNumeroContrato==null)) && (!(altaEmpleadoNumeroContrato.getIdAltaEmpleado().equals(altaEmpleadoDTO.getIdAltaEmpleado()))) ) {//SI ENCONTRO EL NÚMERO DE CONTRATO EN LA TABLA DE LA BASE DE DATOS Y PERTENECE A UN REGISTRO DIFERENTE AL QUE SE ESTA ACTUALIZANDO, SE CONSIDERA REPETIDO.
              banderaNumeroContratoRegistroEncontrado=1;
           }
           
           if (banderaNumeroContratoRegistroEncontrado==1) {//SI EL NÚMERO DE CONTRATO PERTENECE A OTRO REGISTRO MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NUMERO_CONTRATO_YA_EXISTE, false);
              respuestaDTO.setAltaEmpleadoDTO(null);
           }
           if (banderaNumeroContratoRegistroEncontrado==0) {//SI EL NÚMERO DE CONTRATO NO PERTENECE A OTRO REGISTRO (ES EL MISMO REGISTRO O NO EXISTE) SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              AltaEmpleado altaEmpleado = altaEmpleadoDAO.altaEmpleado(altaEmpleadoDTO);
              altaEmpleadoRepository.save(altaEmpleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
        }
        if (altaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setAltaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAltaEmpleado(Long idAltaEmpleado) {
        Optional<AltaEmpleado> altaEmpleadoId = altaEmpleadoRepository.findById(idAltaEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (altaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setAltaEmpleadoDTO(altaEmpleadoDAO.altaEmpleadoDTO(altaEmpleadoId.get()));
           altaEmpleadoRepository.delete(altaEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (altaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setAltaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
