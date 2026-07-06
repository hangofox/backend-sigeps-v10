//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AltaEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface AltaEmpleadoRepository extends JpaRepository<AltaEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_altas_empleados, " +
    "tabla_empleados " +
    "WHERE " +
    "(tabla_altas_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(:idAltaEmpleado IS NULL OR tabla_altas_empleados.id_alta_empleado = :idAltaEmpleado) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idAltaEmpleado") Long idAltaEmpleado, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_altas_empleados.* " +
    "FROM " +
    "tabla_altas_empleados, " +
    "tabla_empleados " +
    "WHERE " +
    "(tabla_altas_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(:idAltaEmpleado IS NULL OR tabla_altas_empleados.id_alta_empleado = :idAltaEmpleado) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idAltaEmpleado' AND :orderMode = 'ASC' THEN tabla_altas_empleados.id_alta_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idAltaEmpleado' AND :orderMode = 'DESC' THEN tabla_altas_empleados.id_alta_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.primer_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.primer_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.numero_documento_identificacion_empleado END ASC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.numero_documento_identificacion_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSAltaEmpleado' AND :orderMode = 'ASC' THEN tabla_altas_empleados.fecha_h_m_s_alta_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSAltaEmpleado' AND :orderMode = 'DESC' THEN tabla_altas_empleados.fecha_h_m_s_alta_empleado END DESC, " +
    "CASE WHEN :orderBy = 'numeroContratoOActoAdmvoNombEmpleado' AND :orderMode = 'ASC' THEN tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado END ASC, " +
    "CASE WHEN :orderBy = 'numeroContratoOActoAdmvoNombEmpleado' AND :orderMode = 'DESC' THEN tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado END DESC", nativeQuery = true)
    List<AltaEmpleado> findAllAltasEmpleados(@Param("idAltaEmpleado") Long idAltaEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_altas_empleados.* " +
    "FROM " +
    "tabla_altas_empleados, " +
    "tabla_empleados " +
    "WHERE " +
    "(tabla_altas_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(:idAltaEmpleado IS NULL OR tabla_altas_empleados.id_alta_empleado = :idAltaEmpleado) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idAltaEmpleado' AND :orderMode = 'ASC' THEN tabla_altas_empleados.id_alta_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idAltaEmpleado' AND :orderMode = 'DESC' THEN tabla_altas_empleados.id_alta_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'numeroContratoOActoAdmvoNombEmpleado' AND :orderMode = 'ASC' THEN tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado END ASC, " +
    "CASE WHEN :orderBy = 'numeroContratoOActoAdmvoNombEmpleado' AND :orderMode = 'DESC' THEN tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_altas_empleados, " +
    "tabla_empleados " +
    "WHERE " +
    "(tabla_altas_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(:idAltaEmpleado IS NULL OR tabla_altas_empleados.id_alta_empleado = :idAltaEmpleado) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_altas_empleados.numero_contrato_o_acto_admvo_nomb_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<AltaEmpleado> findAllAltasEmpleadosPag(Pageable pageable, @Param("idAltaEmpleado") Long idAltaEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<AltaEmpleado> findByIdAltaEmpleado(Long idAltaEmpleado);
    
    AltaEmpleado findByNumeroContratoOActoAdmvoNombEmpleado(String numeroContratoOActoAdmvoNombEmpleado);
    
    @Query(value = "SELECT MAX(id_alta_empleado) FROM tabla_altas_empleados", nativeQuery = true)
    Long findMaxIdAltaEmpleado();
}
