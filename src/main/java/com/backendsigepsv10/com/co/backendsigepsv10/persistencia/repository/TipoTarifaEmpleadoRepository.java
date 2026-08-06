//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoTarifaEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 04/08/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface TipoTarifaEmpleadoRepository extends JpaRepository<TipoTarifaEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_tarifas_empleados " +
          "WHERE " +
          "(:idTipoTarifaEmpleado IS NULL OR id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
          "(:estadoTipoTarifaEmpleado IS NULL OR UPPER(estado_tipo_tarifa_empleado) = UPPER(:estadoTipoTarifaEmpleado)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(descripcion_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("keyword") String keyword, @Param("estadoTipoTarifaEmpleado") String estadoTipoTarifaEmpleado);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_tarifas_empleados " +
          "WHERE " +
          "(:idTipoTarifaEmpleado IS NULL OR id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
          "(:estadoTipoTarifaEmpleado IS NULL OR UPPER(estado_tipo_tarifa_empleado) = UPPER(:estadoTipoTarifaEmpleado)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(descripcion_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN id_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN id_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'descripcionTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN descripcion_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'descripcionTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN descripcion_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'estadoTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN estado_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'estadoTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN estado_tipo_tarifa_empleado END DESC", nativeQuery = true)
    List<TipoTarifaEmpleado> findAllTiposTarifasEmpleados(@Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("keyword") String keyword, @Param("estadoTipoTarifaEmpleado") String estadoTipoTarifaEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_tarifas_empleados " +
          "WHERE " +
          "(:idTipoTarifaEmpleado IS NULL OR id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
          "(:estadoTipoTarifaEmpleado IS NULL OR UPPER(estado_tipo_tarifa_empleado) = UPPER(:estadoTipoTarifaEmpleado)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(descripcion_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN id_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'idTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN id_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN nombre_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN nombre_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'descripcionTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN descripcion_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'descripcionTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN descripcion_tipo_tarifa_empleado END DESC, " +
          "CASE WHEN :orderBy = 'estadoTipoTarifaEmpleado' AND :orderMode = 'ASC' THEN estado_tipo_tarifa_empleado END ASC, " +
          "CASE WHEN :orderBy = 'estadoTipoTarifaEmpleado' AND :orderMode = 'DESC' THEN estado_tipo_tarifa_empleado END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_tipos_tarifas_empleados " +
          "WHERE (:idTipoTarifaEmpleado IS NULL OR id_tipo_tarifa_empleado = :idTipoTarifaEmpleado) AND " +
          "(:estadoTipoTarifaEmpleado IS NULL OR UPPER(estado_tipo_tarifa_empleado) = UPPER(:estadoTipoTarifaEmpleado)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(descripcion_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_tipo_tarifa_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<TipoTarifaEmpleado> findAllTiposTarifasEmpleadosPag(Pageable pageable, @Param("idTipoTarifaEmpleado") Long idTipoTarifaEmpleado, @Param("keyword") String keyword, @Param("estadoTipoTarifaEmpleado") String estadoTipoTarifaEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoTarifaEmpleado> findByIdTipoTarifaEmpleado(Long idTipoTarifaEmpleado);
    
    TipoTarifaEmpleado findByNombreTipoTarifaEmpleado(String nombreTipoTarifaEmpleado);
    
    @Query(value = "SELECT MAX(id_tipo_tarifa_empleado) FROM tabla_tipos_tarifas_empleados", nativeQuery = true)
    Long findMaxIdTipoTarifaEmpleado();
}
