//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Empleado;
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
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_empleados, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_empleados, " +
    "tabla_tipos_empleados_planta, " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_subclasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_empleados.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_empleados.id_tipo_empleado=tabla_tipos_empleados.id_tipo_empleado) AND " +
    "(tabla_empleados.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(tabla_empleados.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(tabla_empleados.id_subclasificacion_empleado_planta=tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta) AND " +
    "(:idEmpleado IS NULL OR tabla_empleados.id_empleado = :idEmpleado) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEmpleado IS NULL OR UPPER(tabla_empleados.numero_documento_identificacion_empleado) = UPPER(:numeroDocumentoIdentificacionEmpleado)) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.segundo_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idEmpleado") Long idEmpleado, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEmpleado") String numeroDocumentoIdentificacionEmpleado, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_empleados.* " +
    "FROM " +
    "tabla_empleados, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_empleados, " +
    "tabla_tipos_empleados_planta, " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_subclasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_empleados.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_empleados.id_tipo_empleado=tabla_tipos_empleados.id_tipo_empleado) AND " +
    "(tabla_empleados.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(tabla_empleados.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(tabla_empleados.id_subclasificacion_empleado_planta=tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta) AND " +
    "(:idEmpleado IS NULL OR tabla_empleados.id_empleado = :idEmpleado) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEmpleado IS NULL OR UPPER(tabla_empleados.numero_documento_identificacion_empleado) = UPPER(:numeroDocumentoIdentificacionEmpleado)) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.segundo_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.id_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.id_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion END DESC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.numero_documento_identificacion_empleado END ASC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.numero_documento_identificacion_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.primer_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.primer_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'segundoApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.segundo_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'segundoApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.segundo_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.fecha_h_m_s_ingreso_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.fecha_h_m_s_ingreso_empleado END DESC", nativeQuery = true)
    List<Empleado> findAllEmpleados(@Param("idEmpleado") Long idEmpleado, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEmpleado") String numeroDocumentoIdentificacionEmpleado, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_empleados.* " +
    "FROM " +
    "tabla_empleados, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_empleados, " +
    "tabla_tipos_empleados_planta, " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_subclasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_empleados.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_empleados.id_tipo_empleado=tabla_tipos_empleados.id_tipo_empleado) AND " +
    "(tabla_empleados.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(tabla_empleados.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(tabla_empleados.id_subclasificacion_empleado_planta=tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta) AND " +
    "(:idEmpleado IS NULL OR tabla_empleados.id_empleado = :idEmpleado) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEmpleado IS NULL OR UPPER(tabla_empleados.numero_documento_identificacion_empleado) = UPPER(:numeroDocumentoIdentificacionEmpleado)) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.segundo_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.id_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.id_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.primer_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.primer_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'segundoApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.segundo_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'segundoApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.segundo_apellido_empleado END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_empleados, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_empleados, " +
    "tabla_tipos_empleados_planta, " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_subclasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_empleados.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_empleados.id_tipo_empleado=tabla_tipos_empleados.id_tipo_empleado) AND " +
    "(tabla_empleados.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(tabla_empleados.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(tabla_empleados.id_subclasificacion_empleado_planta=tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta) AND " +
    "(:idEmpleado IS NULL OR tabla_empleados.id_empleado = :idEmpleado) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEmpleado IS NULL OR UPPER(tabla_empleados.numero_documento_identificacion_empleado) = UPPER(:numeroDocumentoIdentificacionEmpleado)) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.segundo_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<Empleado> findAllEmpleadosPag(Pageable pageable, @Param("idEmpleado") Long idEmpleado, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEmpleado") String numeroDocumentoIdentificacionEmpleado, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<Empleado> findByIdEmpleado(Long idEmpleado);
    
    Empleado findByNumeroDocumentoIdentificacionEmpleado(String numeroDocumentoIdentificacionEmpleado);
    
    @Query(value = "SELECT MAX(id_empleado) FROM tabla_empleados", nativeQuery = true)
    Long findMaxIdEmpleado();
}
