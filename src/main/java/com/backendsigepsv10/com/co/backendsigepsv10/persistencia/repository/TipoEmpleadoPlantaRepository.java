//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoEmpleadoPlanta;
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
public interface TipoEmpleadoPlantaRepository extends JpaRepository<TipoEmpleadoPlanta,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_empleados_planta " +
          "WHERE " +
          "(:idTipoEmpleadoPlanta IS NULL OR id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_empleados_planta " +
          "WHERE " +
          "(:idTipoEmpleadoPlanta IS NULL OR id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN id_tipo_empleado_planta END ASC, " +
          "CASE WHEN :orderBy = 'idTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN id_tipo_empleado_planta END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN nombre_tipo_empleado_planta END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN nombre_tipo_empleado_planta END DESC", nativeQuery = true)
    List<TipoEmpleadoPlanta> findAllTiposEmpleadosPlanta(@Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_empleados_planta " +
          "WHERE " +
          "(:idTipoEmpleadoPlanta IS NULL OR id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN id_tipo_empleado_planta END ASC, " +
          "CASE WHEN :orderBy = 'idTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN id_tipo_empleado_planta END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'ASC' THEN nombre_tipo_empleado_planta END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoEmpleadoPlanta' AND :orderMode = 'DESC' THEN nombre_tipo_empleado_planta END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_tipos_empleados_planta " +
          "WHERE (:idTipoEmpleadoPlanta IS NULL OR id_tipo_empleado_planta = :idTipoEmpleadoPlanta) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_empleado_planta) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Slice<TipoEmpleadoPlanta> findAllTiposEmpleadosPlantaPag(Pageable pageable, @Param("idTipoEmpleadoPlanta") Long idTipoEmpleadoPlanta, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoEmpleadoPlanta> findByIdTipoEmpleadoPlanta(Long idTipoEmpleadoPlanta);
    
    TipoEmpleadoPlanta findByNombreTipoEmpleadoPlanta(String nombreTipoEmpleadoPlanta);
    
    @Query(value = "SELECT MAX(id_tipo_empleado_planta) FROM tabla_tipos_empleados_planta", nativeQuery = true)
    Long findMaxIdTipoEmpleadoPlanta();
}
