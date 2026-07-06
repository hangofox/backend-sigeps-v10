//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialNovedadEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface HistorialNovedadEmpleadoRepository extends JpaRepository<HistorialNovedadEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_historial_novedades_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_novedades_empleados " +
    "WHERE " +
    "(tabla_historial_novedades_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_novedades_empleados.id_tipo_novedad_empleado=tabla_tipos_novedades_empleados.id_tipo_novedad_empleado) AND " +
    "(:idHistorialNovedadEmpleado IS NULL OR tabla_historial_novedades_empleados.id_historial_novedad_empleado = :idHistorialNovedadEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoNovedadEmpleado IS NULL OR UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) = UPPER(:nombreTipoNovedadEmpleado)) AND " +
    "(:descripcionHistorialNovedadEmpleado IS NULL OR UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) = UPPER(:descripcionHistorialNovedadEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idHistorialNovedadEmpleado") Long idHistorialNovedadEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoNovedadEmpleado") String nombreTipoNovedadEmpleado, @Param("descripcionHistorialNovedadEmpleado") String descripcionHistorialNovedadEmpleado);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_historial_novedades_empleados.* " +
    "FROM " +
    "tabla_historial_novedades_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_novedades_empleados " +
    "WHERE " +
    "(tabla_historial_novedades_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_novedades_empleados.id_tipo_novedad_empleado=tabla_tipos_novedades_empleados.id_tipo_novedad_empleado) AND " +
    "(:idHistorialNovedadEmpleado IS NULL OR tabla_historial_novedades_empleados.id_historial_novedad_empleado = :idHistorialNovedadEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoNovedadEmpleado IS NULL OR UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) = UPPER(:nombreTipoNovedadEmpleado)) AND " +
    "(:descripcionHistorialNovedadEmpleado IS NULL OR UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) = UPPER(:descripcionHistorialNovedadEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.id_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.id_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.fecha_h_m_s_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.fecha_h_m_s_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'descripcionHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'descripcionHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.primer_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.primer_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado END DESC", nativeQuery = true)
    List<HistorialNovedadEmpleado> findAllHistorialesNovedadesEmpleados(@Param("idHistorialNovedadEmpleado") Long idHistorialNovedadEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoNovedadEmpleado") String nombreTipoNovedadEmpleado, @Param("descripcionHistorialNovedadEmpleado") String descripcionHistorialNovedadEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_historial_novedades_empleados.* " +
    "FROM " +
    "tabla_historial_novedades_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_novedades_empleados " +
    "WHERE " +
    "(tabla_historial_novedades_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_novedades_empleados.id_tipo_novedad_empleado=tabla_tipos_novedades_empleados.id_tipo_novedad_empleado) AND " +
    "(:idHistorialNovedadEmpleado IS NULL OR tabla_historial_novedades_empleados.id_historial_novedad_empleado = :idHistorialNovedadEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoNovedadEmpleado IS NULL OR UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) = UPPER(:nombreTipoNovedadEmpleado)) AND " +
    "(:descripcionHistorialNovedadEmpleado IS NULL OR UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) = UPPER(:descripcionHistorialNovedadEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.id_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.id_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.fecha_h_m_s_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.fecha_h_m_s_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'descripcionHistorialNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'descripcionHistorialNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_historial_novedades_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_novedades_empleados " +
    "WHERE " +
    "(tabla_historial_novedades_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_novedades_empleados.id_tipo_novedad_empleado=tabla_tipos_novedades_empleados.id_tipo_novedad_empleado) AND " +
    "(:idHistorialNovedadEmpleado IS NULL OR tabla_historial_novedades_empleados.id_historial_novedad_empleado = :idHistorialNovedadEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoNovedadEmpleado IS NULL OR UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) = UPPER(:nombreTipoNovedadEmpleado)) AND " +
    "(:descripcionHistorialNovedadEmpleado IS NULL OR UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) = UPPER(:descripcionHistorialNovedadEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_novedades_empleados.nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<HistorialNovedadEmpleado> findAllHistorialesNovedadesEmpleadosPag(Pageable pageable, @Param("idHistorialNovedadEmpleado") Long idHistorialNovedadEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoNovedadEmpleado") String nombreTipoNovedadEmpleado, @Param("descripcionHistorialNovedadEmpleado") String descripcionHistorialNovedadEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<HistorialNovedadEmpleado> findByIdHistorialNovedadEmpleado(Long idHistorialNovedadEmpleado);
    
    @Query(value =
          "SELECT tabla_historial_novedades_empleados.* " +
          "FROM tabla_historial_novedades_empleados, tabla_empleados " +
          "WHERE (tabla_historial_novedades_empleados.id_empleado = tabla_empleados.id_empleado) AND " +
          "(tabla_empleados.numero_documento_identificacion_empleado = :numeroDocumentoIdentificacionEmpleado) AND " +
          "(tabla_historial_novedades_empleados.descripcion_historial_novedad_empleado = :descripcionHistorialNovedadEmpleado)", nativeQuery = true)
    HistorialNovedadEmpleado findByNumeroDocumentoIdentificacionEmpleadoAndDescripcionHistorialNovedadEmpleado(@Param("numeroDocumentoIdentificacionEmpleado") String numeroDocumentoIdentificacionEmpleado, @Param("descripcionHistorialNovedadEmpleado") String descripcionHistorialNovedadEmpleado);
    
    @Query(value = "SELECT MAX(id_historial_novedad_empleado) FROM tabla_historial_novedades_empleados", nativeQuery = true)
    Long findMaxIdHistorialNovedadEmpleado();
}
