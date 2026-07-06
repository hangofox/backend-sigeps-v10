//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface TipoEmpleadoRepository extends JpaRepository<TipoEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_empleados " +
          "WHERE " +
          "(:idTipoEmpleado IS NULL OR id_tipo_empleado = :idTipoEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoEmpleado") Long idTipoEmpleado, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_empleados " +
          "WHERE " +
          "(:idTipoEmpleado IS NULL OR id_tipo_empleado = :idTipoEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoEmpleado' AND :orderMode = 'ASC' THEN id_tipo_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoEmpleado' AND :orderMode = 'DESC' THEN id_tipo_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_empleado END DESC", nativeQuery = true)
    List<TipoEmpleado> findAllTiposEmpleados(@Param("idTipoEmpleado") Long idTipoEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_empleados " +
          "WHERE " +
          "(:idTipoEmpleado IS NULL OR id_tipo_empleado = :idTipoEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoEmpleado' AND :orderMode = 'ASC' THEN id_tipo_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoEmpleado' AND :orderMode = 'DESC' THEN id_tipo_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_empleado END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_tipos_empleados " +
          "WHERE (:idTipoEmpleado IS NULL OR id_tipo_empleado = :idTipoEmpleado) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))",
          nativeQuery = true)
    Slice<TipoEmpleado> findAllTiposEmpleadosPag(Pageable pageable, @Param("idTipoEmpleado") Long idTipoEmpleado, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoEmpleado> findByIdTipoEmpleado(Long idTipoEmpleado);
    
    TipoEmpleado findByNombreTipoEmpleado(String nombreTipoEmpleado);
    
    @Query(value = "SELECT MAX(id_tipo_empleado) FROM tabla_tipos_empleados", nativeQuery = true)
    Long findMaxIdTipoEmpleado();
}
