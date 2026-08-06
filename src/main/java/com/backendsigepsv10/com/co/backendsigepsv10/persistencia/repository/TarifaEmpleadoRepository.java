//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TarifaEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 05/08/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface TarifaEmpleadoRepository extends JpaRepository<TarifaEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM tabla_tarifas_empleados tarifa " +
    "INNER JOIN tabla_tipos_empleados tipo_empleado ON tarifa.id_tipo_empleado=tipo_empleado.id_tipo_empleado " +
    "INNER JOIN tabla_tipos_empleados_planta tipo_empleado_planta ON tarifa.id_tipo_empleado_planta=tipo_empleado_planta.id_tipo_empleado_planta " +
    "INNER JOIN tabla_clasificaciones_empleados_plantas clasificacion ON tarifa.id_clasificacion_empleado_planta=clasificacion.id_clasificacion_empleado_planta " +
    "INNER JOIN tabla_subclasificaciones_empleados_plantas subclasificacion ON tarifa.id_subclasificacion_empleado_planta=subclasificacion.id_subclasificacion_empleado_planta " +
    "INNER JOIN tabla_tipos_tarifas_empleados tipo_tarifa ON tarifa.id_tipo_tarifa_empleado=tipo_tarifa.id_tipo_tarifa_empleado " +
    "WHERE " +
    "(:idTarifaEmpleado IS NULL OR tarifa.id_tarifa_empleado = :idTarifaEmpleado) AND " +
    "(:idTipoEmpleado IS NULL OR tipo_empleado.id_tipo_empleado = :idTipoEmpleado) AND " +
    "(:idTipoEmpleadoPlanta IS NULL OR tipo_empleado_planta.id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR clasificacion.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR subclasificacion.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:idTipoTarifaEmpleado IS NULL OR tipo_tarifa.id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
    "(:anioTarifaEmpleado IS NULL OR tarifa.anio_tarifa_empleado = :anioTarifaEmpleado) AND " +
    "(:estadoTarifaEmpleado IS NULL OR UPPER(tarifa.estado_tarifa_empleado) = UPPER(:estadoTarifaEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tipo_empleado.nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_empleado_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(clasificacion.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(subclasificacion.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_tarifa.nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tarifa.estado_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTarifaEmpleado") Long idTarifaEmpleado, @Param("keyword") String keyword, @Param("idTipoEmpleado") Long idTipoEmpleado, @Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("anioTarifaEmpleado") Long anioTarifaEmpleado, @Param("estadoTarifaEmpleado") String estadoTarifaEmpleado);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tarifa.* " +
    "FROM tabla_tarifas_empleados tarifa " +
    "INNER JOIN tabla_tipos_empleados tipo_empleado ON tarifa.id_tipo_empleado=tipo_empleado.id_tipo_empleado " +
    "INNER JOIN tabla_tipos_empleados_planta tipo_empleado_planta ON tarifa.id_tipo_empleado_planta=tipo_empleado_planta.id_tipo_empleado_planta " +
    "INNER JOIN tabla_clasificaciones_empleados_plantas clasificacion ON tarifa.id_clasificacion_empleado_planta=clasificacion.id_clasificacion_empleado_planta " +
    "INNER JOIN tabla_subclasificaciones_empleados_plantas subclasificacion ON tarifa.id_subclasificacion_empleado_planta=subclasificacion.id_subclasificacion_empleado_planta " +
    "INNER JOIN tabla_tipos_tarifas_empleados tipo_tarifa ON tarifa.id_tipo_tarifa_empleado=tipo_tarifa.id_tipo_tarifa_empleado " +
    "WHERE " +
    "(:idTarifaEmpleado IS NULL OR tarifa.id_tarifa_empleado = :idTarifaEmpleado) AND " +
    "(:idTipoEmpleado IS NULL OR tipo_empleado.id_tipo_empleado = :idTipoEmpleado) AND " +
    "(:idTipoEmpleadoPlanta IS NULL OR tipo_empleado_planta.id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR clasificacion.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR subclasificacion.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:idTipoTarifaEmpleado IS NULL OR tipo_tarifa.id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
    "(:anioTarifaEmpleado IS NULL OR tarifa.anio_tarifa_empleado = :anioTarifaEmpleado) AND " +
    "(:estadoTarifaEmpleado IS NULL OR UPPER(tarifa.estado_tarifa_empleado) = UPPER(:estadoTarifaEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tipo_empleado.nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_empleado_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(clasificacion.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(subclasificacion.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_tarifa.nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tarifa.estado_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.id_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.id_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'anioTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.anio_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'anioTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.anio_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'valorHoraTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.valor_hora_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'valorHoraTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.valor_hora_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.fecha_h_m_s_ingreso_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.fecha_h_m_s_ingreso_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.fecha_h_m_s_modificacion_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.fecha_h_m_s_modificacion_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'estadoTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.estado_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'estadoTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.estado_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'ASC' THEN tipo_empleado.nombre_tipo_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'DESC' THEN tipo_empleado.nombre_tipo_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN tipo_empleado_planta.nombre_tipo_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN tipo_empleado_planta.nombre_tipo_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN clasificacion.nombre_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN clasificacion.nombre_clasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN subclasificacion.nombre_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN subclasificacion.nombre_subclasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN tipo_tarifa.nombre_tipo_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN tipo_tarifa.nombre_tipo_tarifa_empleado END DESC", nativeQuery = true)
    List<TarifaEmpleado> findAllTarifasEmpleados(@Param("idTarifaEmpleado") Long idTarifaEmpleado, @Param("keyword") String keyword, @Param("idTipoEmpleado") Long idTipoEmpleado, @Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("anioTarifaEmpleado") Long anioTarifaEmpleado, @Param("estadoTarifaEmpleado") String estadoTarifaEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tarifa.* " +
    "FROM tabla_tarifas_empleados tarifa " +
    "INNER JOIN tabla_tipos_empleados tipo_empleado ON tarifa.id_tipo_empleado=tipo_empleado.id_tipo_empleado " +
    "INNER JOIN tabla_tipos_empleados_planta tipo_empleado_planta ON tarifa.id_tipo_empleado_planta=tipo_empleado_planta.id_tipo_empleado_planta " +
    "INNER JOIN tabla_clasificaciones_empleados_plantas clasificacion ON tarifa.id_clasificacion_empleado_planta=clasificacion.id_clasificacion_empleado_planta " +
    "INNER JOIN tabla_subclasificaciones_empleados_plantas subclasificacion ON tarifa.id_subclasificacion_empleado_planta=subclasificacion.id_subclasificacion_empleado_planta " +
    "INNER JOIN tabla_tipos_tarifas_empleados tipo_tarifa ON tarifa.id_tipo_tarifa_empleado=tipo_tarifa.id_tipo_tarifa_empleado " +
    "WHERE " +
    "(:idTarifaEmpleado IS NULL OR tarifa.id_tarifa_empleado = :idTarifaEmpleado) AND " +
    "(:idTipoEmpleado IS NULL OR tipo_empleado.id_tipo_empleado = :idTipoEmpleado) AND " +
    "(:idTipoEmpleadoPlanta IS NULL OR tipo_empleado_planta.id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR clasificacion.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR subclasificacion.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:idTipoTarifaEmpleado IS NULL OR tipo_tarifa.id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
    "(:anioTarifaEmpleado IS NULL OR tarifa.anio_tarifa_empleado = :anioTarifaEmpleado) AND " +
    "(:estadoTarifaEmpleado IS NULL OR UPPER(tarifa.estado_tarifa_empleado) = UPPER(:estadoTarifaEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tipo_empleado.nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_empleado_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(clasificacion.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(subclasificacion.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_tarifa.nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tarifa.estado_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.id_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.id_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'anioTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.anio_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'anioTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.anio_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'valorHoraTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.valor_hora_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'valorHoraTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.valor_hora_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.fecha_h_m_s_ingreso_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.fecha_h_m_s_ingreso_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.fecha_h_m_s_modificacion_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.fecha_h_m_s_modificacion_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'estadoTarifaEmpleado' AND :orderMode = 'ASC' THEN tarifa.estado_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'estadoTarifaEmpleado' AND :orderMode = 'DESC' THEN tarifa.estado_tarifa_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'ASC' THEN tipo_empleado.nombre_tipo_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'DESC' THEN tipo_empleado.nombre_tipo_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN tipo_empleado_planta.nombre_tipo_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN tipo_empleado_planta.nombre_tipo_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN clasificacion.nombre_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN clasificacion.nombre_clasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN subclasificacion.nombre_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN subclasificacion.nombre_subclasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN tipo_tarifa.nombre_tipo_tarifa_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN tipo_tarifa.nombre_tipo_tarifa_empleado END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM tabla_tarifas_empleados tarifa " +
    "INNER JOIN tabla_tipos_empleados tipo_empleado ON tarifa.id_tipo_empleado=tipo_empleado.id_tipo_empleado " +
    "INNER JOIN tabla_tipos_empleados_planta tipo_empleado_planta ON tarifa.id_tipo_empleado_planta=tipo_empleado_planta.id_tipo_empleado_planta " +
    "INNER JOIN tabla_clasificaciones_empleados_plantas clasificacion ON tarifa.id_clasificacion_empleado_planta=clasificacion.id_clasificacion_empleado_planta " +
    "INNER JOIN tabla_subclasificaciones_empleados_plantas subclasificacion ON tarifa.id_subclasificacion_empleado_planta=subclasificacion.id_subclasificacion_empleado_planta " +
    "INNER JOIN tabla_tipos_tarifas_empleados tipo_tarifa ON tarifa.id_tipo_tarifa_empleado=tipo_tarifa.id_tipo_tarifa_empleado " +
    "WHERE " +
    "(:idTarifaEmpleado IS NULL OR tarifa.id_tarifa_empleado = :idTarifaEmpleado) AND " +
    "(:idTipoEmpleado IS NULL OR tipo_empleado.id_tipo_empleado = :idTipoEmpleado) AND " +
    "(:idTipoEmpleadoPlanta IS NULL OR tipo_empleado_planta.id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR clasificacion.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR subclasificacion.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:idTipoTarifaEmpleado IS NULL OR tipo_tarifa.id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
    "(:anioTarifaEmpleado IS NULL OR tarifa.anio_tarifa_empleado = :anioTarifaEmpleado) AND " +
    "(:estadoTarifaEmpleado IS NULL OR UPPER(tarifa.estado_tarifa_empleado) = UPPER(:estadoTarifaEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tipo_empleado.nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_empleado_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(clasificacion.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(subclasificacion.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tipo_tarifa.nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tarifa.estado_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<TarifaEmpleado> findAllTarifasEmpleadosPag(Pageable pageable, @Param("idTarifaEmpleado") Long idTarifaEmpleado, @Param("keyword") String keyword, @Param("idTipoEmpleado") Long idTipoEmpleado, @Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("anioTarifaEmpleado") Long anioTarifaEmpleado, @Param("estadoTarifaEmpleado") String estadoTarifaEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TarifaEmpleado> findByIdTarifaEmpleado(Long idTarifaEmpleado);
    
    @Query(value = "SELECT tarifa.* FROM tabla_tarifas_empleados tarifa WHERE tarifa.id_tipo_empleado = :idTipoEmpleado AND tarifa.id_tipo_empleado_planta = :idTipoEmpleadoPlanta AND tarifa.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta AND tarifa.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta AND tarifa.id_tipo_tarifa_empleado = :idTipoTarifaEmpleado AND tarifa.anio_tarifa_empleado = :anioTarifaEmpleado LIMIT 1", nativeQuery = true)
    TarifaEmpleado findByIdTipoEmpleadoAndIdTipoEmpleadoPlantaAndIdClasificacionEmpleadoPlantaAndIdSubclasificacionEmpleadoPlantaAndIdTipoTarifaEmpleadoAndAnioTarifaEmpleado(@Param("idTipoEmpleado") Long idTipoEmpleado, @Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("anioTarifaEmpleado") Long anioTarifaEmpleado);
    
    @Query(value = "SELECT MAX(id_tarifa_empleado) FROM tabla_tarifas_empleados", nativeQuery = true)
    Long findMaxIdTarifaEmpleado();
}
