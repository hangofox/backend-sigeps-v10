//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoMovimiento;
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
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_movimientos " +
          "WHERE " +
          "(:idTipoMovimiento IS NULL OR id_tipo_movimiento = :idTipoMovimiento) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoMovimiento") Long idTipoMovimiento, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_movimientos " +
          "WHERE " +
          "(:idTipoMovimiento IS NULL OR id_tipo_movimiento = :idTipoMovimiento) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoMovimiento' AND :orderMode = 'ASC' THEN id_tipo_movimiento END ASC, " +
          "CASE WHEN :orderBy = 'idTipoMovimiento' AND :orderMode = 'DESC' THEN id_tipo_movimiento END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'ASC' THEN nombre_tipo_movimiento END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'DESC' THEN nombre_tipo_movimiento END DESC", nativeQuery = true)
    List<TipoMovimiento> findAllTiposMovimientos(@Param("idTipoMovimiento") Long idTipoMovimiento, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_movimientos " +
          "WHERE " +
          "(:idTipoMovimiento IS NULL OR id_tipo_movimiento = :idTipoMovimiento) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoMovimiento' AND :orderMode = 'ASC' THEN id_tipo_movimiento END ASC, " +
          "CASE WHEN :orderBy = 'idTipoMovimiento' AND :orderMode = 'DESC' THEN id_tipo_movimiento END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'ASC' THEN nombre_tipo_movimiento END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoMovimiento' AND :orderMode = 'DESC' THEN nombre_tipo_movimiento END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_tipos_movimientos " +
          "WHERE (:idTipoMovimiento IS NULL OR id_tipo_movimiento = :idTipoMovimiento) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_movimiento) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Slice<TipoMovimiento> findAllTiposMovimientosPag(Pageable pageable, @Param("idTipoMovimiento") Long idTipoMovimiento, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoMovimiento> findByIdTipoMovimiento(Long idTipoMovimiento);
    
    TipoMovimiento findByNombreTipoMovimiento(String nombreTipoMovimiento);
    
    @Query(value = "SELECT MAX(id_tipo_movimiento) FROM tabla_tipos_movimientos", nativeQuery = true)
    Long findMaxIdTipoMovimiento();
}
