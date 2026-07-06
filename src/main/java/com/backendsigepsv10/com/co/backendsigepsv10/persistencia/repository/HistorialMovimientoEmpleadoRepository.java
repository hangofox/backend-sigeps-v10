//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.HistorialMovimientoEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 13/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface HistorialMovimientoEmpleadoRepository extends JpaRepository<HistorialMovimientoEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_historial_movimientos_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_movimientos, " +
    "tabla_programacion_turnos_empleados " +
    "WHERE " +
    "(tabla_historial_movimientos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_movimientos_empleados.id_tipo_movimiento=tabla_tipos_movimientos.id_tipo_movimiento) AND " +
    "(tabla_historial_movimientos_empleados.id_programacion_turno_empleado=tabla_programacion_turnos_empleados.id_programacion_turno_empleado) AND " +
    "(:idHistorialMovimientoEmpleado IS NULL OR tabla_historial_movimientos_empleados.id_historial_movimiento_empleado = :idHistorialMovimientoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoMovimiento IS NULL OR UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) = UPPER(:nombreTipoMovimiento)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idHistorialMovimientoEmpleado") Long idHistorialMovimientoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoMovimiento") String nombreTipoMovimiento);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_historial_movimientos_empleados.* " +
    "FROM " +
    "tabla_historial_movimientos_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_movimientos, " +
    "tabla_programacion_turnos_empleados " +
    "WHERE " +
    "(tabla_historial_movimientos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_movimientos_empleados.id_tipo_movimiento=tabla_tipos_movimientos.id_tipo_movimiento) AND " +
    "(tabla_historial_movimientos_empleados.id_programacion_turno_empleado=tabla_programacion_turnos_empleados.id_programacion_turno_empleado) AND " +
    "(:idHistorialMovimientoEmpleado IS NULL OR tabla_historial_movimientos_empleados.id_historial_movimiento_empleado = :idHistorialMovimientoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoMovimiento IS NULL OR UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) = UPPER(:nombreTipoMovimiento)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idHistorialMovimientoEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_movimientos_empleados.id_historial_movimiento_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idHistorialMovimientoEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_movimientos_empleados.id_historial_movimiento_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialMovimientoEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_movimientos_empleados.fecha_h_m_s_historial_movimiento_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialMovimientoEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_movimientos_empleados.fecha_h_m_s_historial_movimiento_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.primer_apellido_empleado END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.primer_apellido_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'ASC' THEN tabla_tipos_movimientos.nombre_tipo_movimiento END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'DESC' THEN tabla_tipos_movimientos.nombre_tipo_movimiento END DESC", nativeQuery = true)
    List<HistorialMovimientoEmpleado> findAllHistorialesMovimientosEmpleados(@Param("idHistorialMovimientoEmpleado") Long idHistorialMovimientoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoMovimiento") String nombreTipoMovimiento, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_historial_movimientos_empleados.* " +
    "FROM " +
    "tabla_historial_movimientos_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_movimientos, " +
    "tabla_programacion_turnos_empleados " +
    "WHERE " +
    "(tabla_historial_movimientos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_movimientos_empleados.id_tipo_movimiento=tabla_tipos_movimientos.id_tipo_movimiento) AND " +
    "(tabla_historial_movimientos_empleados.id_programacion_turno_empleado=tabla_programacion_turnos_empleados.id_programacion_turno_empleado) AND " +
    "(:idHistorialMovimientoEmpleado IS NULL OR tabla_historial_movimientos_empleados.id_historial_movimiento_empleado = :idHistorialMovimientoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoMovimiento IS NULL OR UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) = UPPER(:nombreTipoMovimiento)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idHistorialMovimientoEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_movimientos_empleados.id_historial_movimiento_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idHistorialMovimientoEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_movimientos_empleados.id_historial_movimiento_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialMovimientoEmpleado' AND :orderMode = 'ASC' THEN tabla_historial_movimientos_empleados.fecha_h_m_s_historial_movimiento_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSHistorialMovimientoEmpleado' AND :orderMode = 'DESC' THEN tabla_historial_movimientos_empleados.fecha_h_m_s_historial_movimiento_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'ASC' THEN tabla_tipos_movimientos.nombre_tipo_movimiento END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'DESC' THEN tabla_tipos_movimientos.nombre_tipo_movimiento END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_historial_movimientos_empleados, " +
    "tabla_empleados, " +
    "tabla_tipos_movimientos, " +
    "tabla_programacion_turnos_empleados " +
    "WHERE " +
    "(tabla_historial_movimientos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_historial_movimientos_empleados.id_tipo_movimiento=tabla_tipos_movimientos.id_tipo_movimiento) AND " +
    "(tabla_historial_movimientos_empleados.id_programacion_turno_empleado=tabla_programacion_turnos_empleados.id_programacion_turno_empleado) AND " +
    "(:idHistorialMovimientoEmpleado IS NULL OR tabla_historial_movimientos_empleados.id_historial_movimiento_empleado = :idHistorialMovimientoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombreTipoMovimiento IS NULL OR UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) = UPPER(:nombreTipoMovimiento)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.numero_documento_identificacion_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_movimientos.nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<HistorialMovimientoEmpleado> findAllHistorialesMovimientosEmpleadosPag(Pageable pageable, @Param("idHistorialMovimientoEmpleado") Long idHistorialMovimientoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombreTipoMovimiento") String nombreTipoMovimiento, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<HistorialMovimientoEmpleado> findByIdHistorialMovimientoEmpleado(Long idHistorialMovimientoEmpleado);
    
    @Query(value = "SELECT tabla_historial_movimientos_empleados.* FROM tabla_historial_movimientos_empleados, tabla_empleados WHERE (tabla_historial_movimientos_empleados.id_empleado = tabla_empleados.id_empleado) AND (tabla_empleados.id_empleado = :idEmpleado) AND (tabla_historial_movimientos_empleados.fecha_h_m_s_historial_movimiento_empleado = :fechaHMS)", nativeQuery = true)
    HistorialMovimientoEmpleado findByIdEmpleadoAndFechaHMSHistorialMovimientoEmpleado(@Param("idEmpleado") Long idEmpleado, @Param("fechaHMS") Date fechaHMS);
    
    @Query(value = "SELECT MAX(id_historial_movimiento_empleado) FROM tabla_historial_movimientos_empleados", nativeQuery = true)
    Long findMaxIdHistorialMovimientoEmpleado();
}
