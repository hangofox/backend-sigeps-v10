//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.Constantes.MensajesConstantes;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.BajaEmpleadoDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto.RespuestaDTO;
import com.backendsigepsv10.com.co.backendsigepsv10.dominio.service.BajaEmpleadoService;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.dao.BajaEmpleadoDAO;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.BajaEmpleado;
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository.BajaEmpleadoRepository;
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
public class BajaEmpleadoServiceImpl implements BajaEmpleadoService {
    
    @Autowired//INYECTAMOS EL DAO.
    private BajaEmpleadoDAO bajaEmpleadoDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private BajaEmpleadoRepository bajaEmpleadoRepository;
    
    //CONTAR TOTAL DE REGISTROS FILTRADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE CONTAR TOTAL DE REGISTROS.
    public Long contarTotalRegistros(Long idBajaEmpleado, String keyword) {
        return bajaEmpleadoRepository.findTotalRegistros(idBajaEmpleado, keyword);
    }
    
    //LISTAR REGISTROS FILTRADOS SIN PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<BajaEmpleadoDTO> listarBajasEmpleados(Long idBajaEmpleado, String keyword, String orderBy, String orderMode) {
        List<BajaEmpleado> bajasEmpleados = bajaEmpleadoRepository.findAllBajasEmpleados(idBajaEmpleado, keyword, orderBy, orderMode);
        List<BajaEmpleadoDTO> bajaEmpleadoDTOS = new ArrayList<>();
        
        for (BajaEmpleado bajaEmpleado : bajasEmpleados){
            bajaEmpleadoDTOS.add(bajaEmpleadoDAO.bajaEmpleadoDTO(bajaEmpleado));
        }
        
        return bajaEmpleadoDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS PAGINADOS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS PAGINADOS.
    public Slice<BajaEmpleadoDTO> listarBajasEmpleadosPag(Pageable pageable, Long idBajaEmpleado, String keyword, String orderBy, String orderMode) {
        Slice<BajaEmpleado> bajasEmpleados = bajaEmpleadoRepository.findAllBajasEmpleadosPag(pageable, idBajaEmpleado, keyword, orderBy, orderMode);
        return bajasEmpleados.map(bajaEmpleado -> bajaEmpleadoDAO.bajaEmpleadoDTO(bajaEmpleado));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearBajaEmpleado(BajaEmpleadoDTO bajaEmpleadoDTO) {
        Long maxIdBajaEmpleado=null;
        BajaEmpleado bajaEmpleadoNumeroContrato = bajaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(bajaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
        long banderaNumeroContratoRegistroEncontrado=0;
        
        if (!(bajaEmpleadoNumeroContrato==null)) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           banderaNumeroContratoRegistroEncontrado=1;
        }
        
        if (banderaNumeroContratoRegistroEncontrado==1) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NUMERO_CONTRATO_YA_EXISTE, false);
           respuestaDTO.setBajaEmpleadoDTO(null);
        }
        if ((banderaNumeroContratoRegistroEncontrado==0) ) {//SI NO ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CREA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO CREADO EXITOSAMENTE CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           maxIdBajaEmpleado = bajaEmpleadoRepository.findMaxIdBajaEmpleado();
           if (maxIdBajaEmpleado==null) {//ESTO SE HACE EN CASO DE QUE SI LA TABLA DE LA BASE DE DATOS ESTA EN BLANCO Y VA SER EL PRIMER REGISTRO AL OBTENER UN VALOR NULO, SE ASIGNE CERO (0) AUTOMÁTICAMENTE PORQUE SI NO ARROJARIA UN ERROR DE CONVERSIÓN DE CARACTER NULO AL SUMAR CON NÚMERO ENTERO.
              maxIdBajaEmpleado=Long.valueOf(0);
           }
           bajaEmpleadoDTO.setIdBajaEmpleado(maxIdBajaEmpleado+1);//OBTENGO EL ID MAXIMO AUTOMATICO, SUMO (1) ENTERO PARA OBTENER EL NUEVO ID.
           
           bajaEmpleadoRepository.save(bajaEmpleadoDAO.bajaEmpleado(bajaEmpleadoDTO));
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarBajaEmpleadoporId(Long idBajaEmpleado) {
        Optional<BajaEmpleado> bajaEmpleadoId = bajaEmpleadoRepository.findByIdBajaEmpleado(Long.valueOf(idBajaEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (bajaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO.setBajaEmpleadoDTO(bajaEmpleadoDAO.bajaEmpleadoDTO(bajaEmpleadoId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (bajaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setBajaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR NÚMERO DE CONTRATO O ACTO ADMINISTRATIVO:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarBajaEmpleadoporNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado) {
        Optional<BajaEmpleado> bajaEmpleadoNumeroContrato = Optional.ofNullable(bajaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(numeroContratoOActoAdmvoNombEmpleado));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_REGISTRO_NO_ENCONTRADO, false);
        
        if (bajaEmpleadoNumeroContrato.isPresent()==true) {//SI ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO.setBajaEmpleadoDTO(bajaEmpleadoDAO.bajaEmpleadoDTO(bajaEmpleadoNumeroContrato.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (bajaEmpleadoNumeroContrato.isPresent()==false) {//SI NO ENCONTRO EL NÚMERO DE CONTRATO DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL NÚMERO DE CONTRATO PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NUMERO_REGISTRO_NO_ENCONTRADO, false);
           respuestaDTO.setBajaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarBajaEmpleado(BajaEmpleadoDTO bajaEmpleadoDTO) {
        Optional<BajaEmpleado> bajaEmpleadoId = bajaEmpleadoRepository.findByIdBajaEmpleado(bajaEmpleadoDTO.getIdBajaEmpleado());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (bajaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL NÚMERO DE CONTRATO DEL REGISTRO CON EL ID PROPORCIONADO.
           //DECLARACIÓN E INICIALIZACIÓN DE LAS BANDERAS EN CERO (0):
           long banderaNumeroContratoRegistroEncontrado=0;
           
           BajaEmpleado bajaEmpleadoNumeroContrato = bajaEmpleadoRepository.findByNumeroContratoOActoAdmvoNombEmpleado(bajaEmpleadoDTO.getNumeroContratoOActoAdmvoNombEmpleado());
           
           if ( (!(bajaEmpleadoNumeroContrato==null)) && (!(bajaEmpleadoNumeroContrato.getIdBajaEmpleado().equals(bajaEmpleadoDTO.getIdBajaEmpleado()))) ) {//SI ENCONTRO EL NÚMERO DE CONTRATO EN LA TABLA DE LA BASE DE DATOS Y PERTENECE A UN REGISTRO DIFERENTE AL QUE SE ESTA ACTUALIZANDO, SE CONSIDERA REPETIDO.
              banderaNumeroContratoRegistroEncontrado=1;
           }
           
           if (banderaNumeroContratoRegistroEncontrado==1) {//SI EL NÚMERO DE CONTRATO PERTENECE A OTRO REGISTRO MUESTRA UN MENSAJE DE NÚMERO DE CONTRATO DE REGISTRO REPETIDO CON EL NÚMERO DE CONTRATO PROPORCIONADO.
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_NUMERO_CONTRATO_YA_EXISTE, false);
              respuestaDTO.setBajaEmpleadoDTO(null);
           }
           if (banderaNumeroContratoRegistroEncontrado==0) {//SI EL NÚMERO DE CONTRATO NO PERTENECE A OTRO REGISTRO (ES EL MISMO REGISTRO O NO EXISTE) SE MODIFICA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO MODIFICADO EXITOSAMENTE.
              BajaEmpleado bajaEmpleado = bajaEmpleadoDAO.bajaEmpleado(bajaEmpleadoDTO);
              bajaEmpleadoRepository.save(bajaEmpleado);
              respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
           }
        }
        if (bajaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setBajaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarBajaEmpleado(Long idBajaEmpleado) {
        Optional<BajaEmpleado> bajaEmpleadoId = bajaEmpleadoRepository.findById(idBajaEmpleado);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (bajaEmpleadoId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setBajaEmpleadoDTO(bajaEmpleadoDAO.bajaEmpleadoDTO(bajaEmpleadoId.get()));
           bajaEmpleadoRepository.delete(bajaEmpleadoId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (bajaEmpleadoId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setBajaEmpleadoDTO(null);
        }
        
        return respuestaDTO;
    }
}
