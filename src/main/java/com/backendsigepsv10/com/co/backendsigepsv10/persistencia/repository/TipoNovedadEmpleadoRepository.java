//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoNovedadEmpleado;
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
public interface TipoNovedadEmpleadoRepository extends JpaRepository<TipoNovedadEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_novedades_empleados " +
          "WHERE " +
          "(:idTipoNovedadEmpleado IS NULL OR id_tipo_novedad_empleado = :idTipoNovedadEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoNovedadEmpleado") Long idTipoNovedadEmpleado, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_novedades_empleados " +
          "WHERE " +
          "(:idTipoNovedadEmpleado IS NULL OR id_tipo_novedad_empleado = :idTipoNovedadEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN id_tipo_novedad_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN id_tipo_novedad_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_novedad_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_novedad_empleado END DESC", nativeQuery = true)
    List<TipoNovedadEmpleado> findAllTiposNovedadesEmpleados(@Param("idTipoNovedadEmpleado") Long idTipoNovedadEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_novedades_empleados " +
          "WHERE " +
          "(:idTipoNovedadEmpleado IS NULL OR id_tipo_novedad_empleado = :idTipoNovedadEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN id_tipo_novedad_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN id_tipo_novedad_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_novedad_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoNovedadEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_novedad_empleado END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_tipos_novedades_empleados " +
          "WHERE (:idTipoNovedadEmpleado IS NULL OR id_tipo_novedad_empleado = :idTipoNovedadEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_novedad_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Slice<TipoNovedadEmpleado> findAllTiposNovedadesEmpleadosPag(Pageable pageable, @Param("idTipoNovedadEmpleado") Long idTipoNovedadEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoNovedadEmpleado> findByIdTipoNovedadEmpleado(Long idTipoNovedadEmpleado);
    
    TipoNovedadEmpleado findByNombreTipoNovedadEmpleado(String nombreTipoNovedadEmpleado);
    
    @Query(value = "SELECT MAX(id_tipo_novedad_empleado) FROM tabla_tipos_novedades_empleados", nativeQuery = true)
    Long findMaxIdTipoNovedadEmpleado();
}
