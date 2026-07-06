//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ClasificacionEmpleadoPlanta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 11/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface ClasificacionEmpleadoPlantaRepository extends JpaRepository<ClasificacionEmpleadoPlanta,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_tipos_empleados_planta " +
    "WHERE " +
    "(tabla_clasificaciones_empleados_plantas.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:nombreTipoEmpleadoPlanta IS NULL OR UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) = UPPER(:nombreTipoEmpleadoPlanta)) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreTipoEmpleadoPlanta") String nombreTipoEmpleadoPlanta, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_clasificaciones_empleados_plantas.* " +
    "FROM " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_tipos_empleados_planta " +
    "WHERE " +
    "(tabla_clasificaciones_empleados_plantas.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:nombreTipoEmpleadoPlanta IS NULL OR UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) = UPPER(:nombreTipoEmpleadoPlanta)) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'idClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_tipos_empleados_planta.nombre_tipo_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_tipos_empleados_planta.nombre_tipo_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END DESC", nativeQuery = true)
    List<ClasificacionEmpleadoPlanta> findAllClasificacionesEmpleadosPlantas(@Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreTipoEmpleadoPlanta") String nombreTipoEmpleadoPlanta, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_clasificaciones_empleados_plantas.* " +
    "FROM " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_tipos_empleados_planta " +
    "WHERE " +
    "(tabla_clasificaciones_empleados_plantas.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:nombreTipoEmpleadoPlanta IS NULL OR UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) = UPPER(:nombreTipoEmpleadoPlanta)) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'idClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_clasificaciones_empleados_plantas, " +
    "tabla_tipos_empleados_planta " +
    "WHERE " +
    "(tabla_clasificaciones_empleados_plantas.id_tipo_empleado_planta=tabla_tipos_empleados_planta.id_tipo_empleado_planta) AND " +
    "(:idClasificacionEmpleadoPlanta IS NULL OR tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta = :idClasificacionEmpleadoPlanta) AND " +
    "(:nombreTipoEmpleadoPlanta IS NULL OR UPPER(tabla_tipos_empleados_planta.nombre_tipo_empleado_planta) = UPPER(:nombreTipoEmpleadoPlanta)) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Slice<ClasificacionEmpleadoPlanta> findAllClasificacionesEmpleadosPlantasPag(Pageable pageable, @Param("idClasificacionEmpleadoPlanta") Long idClasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreTipoEmpleadoPlanta") String nombreTipoEmpleadoPlanta, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<ClasificacionEmpleadoPlanta> findByIdClasificacionEmpleadoPlanta(Long idClasificacionEmpleadoPlanta);
    
    ClasificacionEmpleadoPlanta findByNombreClasificacionEmpleadoPlanta(String nombreClasificacionEmpleadoPlanta);
    
    @Query(value = "SELECT MAX(id_clasificacion_empleado_planta) FROM tabla_clasificaciones_empleados_plantas", nativeQuery = true)
    Long findMaxIdClasificacionEmpleadoPlanta();
}
