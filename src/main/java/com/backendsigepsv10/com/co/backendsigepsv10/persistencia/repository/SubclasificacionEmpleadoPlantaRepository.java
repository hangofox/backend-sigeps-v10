//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SubclasificacionEmpleadoPlanta;
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
public interface SubclasificacionEmpleadoPlantaRepository extends JpaRepository<SubclasificacionEmpleadoPlanta,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_subclasificaciones_empleados_plantas, " +
    "tabla_clasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_subclasificaciones_empleados_plantas.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:nombreSubclasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) = UPPER(:nombreSubclasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta, @Param("nombreSubclasificacionEmpleadoPlanta") String nombreSubclasificacionEmpleadoPlanta);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_subclasificaciones_empleados_plantas.* " +
    "FROM " +
    "tabla_subclasificaciones_empleados_plantas, " +
    "tabla_clasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_subclasificaciones_empleados_plantas.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:nombreSubclasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) = UPPER(:nombreSubclasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'idSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreClasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta END DESC", nativeQuery = true)
    List<SubclasificacionEmpleadoPlanta> findAllSubclasificacionesEmpleadosPlantas(@Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta, @Param("nombreSubclasificacionEmpleadoPlanta") String nombreSubclasificacionEmpleadoPlanta, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_subclasificaciones_empleados_plantas.* " +
    "FROM " +
    "tabla_subclasificaciones_empleados_plantas, " +
    "tabla_clasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_subclasificaciones_empleados_plantas.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:nombreSubclasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) = UPPER(:nombreSubclasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'idSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta END DESC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'ASC' THEN tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta END ASC, " +
    "CASE WHEN :orderBy = 'nombreSubclasificacionEmpleadoPlanta' AND :orderMode = 'DESC' THEN tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_subclasificaciones_empleados_plantas, " +
    "tabla_clasificaciones_empleados_plantas " +
    "WHERE " +
    "(tabla_subclasificaciones_empleados_plantas.id_clasificacion_empleado_planta=tabla_clasificaciones_empleados_plantas.id_clasificacion_empleado_planta) AND " +
    "(:idSubclasificacionEmpleadoPlanta IS NULL OR tabla_subclasificaciones_empleados_plantas.id_subclasificacion_empleado_planta = :idSubclasificacionEmpleadoPlanta) AND " +
    "(:nombreClasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_clasificaciones_empleados_plantas.nombre_clasificacion_empleado_planta) = UPPER(:nombreClasificacionEmpleadoPlanta)) AND " +
    "(:nombreSubclasificacionEmpleadoPlanta IS NULL OR UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) = UPPER(:nombreSubclasificacionEmpleadoPlanta)) AND " +
    "(:keyword IS NULL OR UPPER(tabla_subclasificaciones_empleados_plantas.nombre_subclasificacion_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Slice<SubclasificacionEmpleadoPlanta> findAllSubclasificacionesEmpleadosPlantasPag(Pageable pageable, @Param("idSubclasificacionEmpleadoPlanta") Long idSubclasificacionEmpleadoPlanta, @Param("keyword") String keyword, @Param("nombreClasificacionEmpleadoPlanta") String nombreClasificacionEmpleadoPlanta, @Param("nombreSubclasificacionEmpleadoPlanta") String nombreSubclasificacionEmpleadoPlanta, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<SubclasificacionEmpleadoPlanta> findByIdSubclasificacionEmpleadoPlanta(Long idSubclasificacionEmpleadoPlanta);
    
    SubclasificacionEmpleadoPlanta findByNombreSubclasificacionEmpleadoPlanta(String nombreSubclasificacionEmpleadoPlanta);
    
    @Query(value = "SELECT MAX(id_subclasificacion_empleado_planta) FROM tabla_subclasificaciones_empleados_plantas", nativeQuery = true)
    Long findMaxIdSubclasificacionEmpleadoPlanta();
}
