//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PaisMundo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 10/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface PaisMundoRepository extends JpaRepository<PaisMundo,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_paises_mundo " +
          "WHERE " +
          "(:idPaisMundo IS NULL OR id_pais_mundo = :idPaisMundo) AND " +
          "(:keyword IS NULL OR UPPER(nombre_pais_mundo) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idPaisMundo") Long idPaisMundo, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_paises_mundo " +
          "WHERE " +
          "(:idPaisMundo IS NULL OR id_pais_mundo = :idPaisMundo) AND " +
          "(:keyword IS NULL OR UPPER(nombre_pais_mundo) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'ASC' THEN id_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'DESC' THEN id_pais_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'ASC' THEN nombre_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'DESC' THEN nombre_pais_mundo END DESC", nativeQuery = true)
    List<PaisMundo> findAllPaisesMundo(@Param("idPaisMundo") Long idPaisMundo, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_paises_mundo " +
          "WHERE " +
          "(:idPaisMundo IS NULL OR id_pais_mundo = :idPaisMundo) AND " +
          "(:keyword IS NULL OR UPPER(nombre_pais_mundo) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'ASC' THEN id_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'DESC' THEN id_pais_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'ASC' THEN nombre_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'DESC' THEN nombre_pais_mundo END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_paises_mundo " +
          "WHERE (:idPaisMundo IS NULL OR id_pais_mundo = :idPaisMundo) AND " +
          "(:keyword IS NULL OR UPPER(nombre_pais_mundo) LIKE UPPER(CONCAT('%', :keyword, '%')))",
          nativeQuery = true)
    Slice<PaisMundo> findAllPaisesMundoPag(Pageable pageable, @Param("idPaisMundo") Long idPaisMundo, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<PaisMundo> findByIdPaisMundo(Long idPaisMundo);
    
    PaisMundo findByNombrePaisMundo(String nombrePaisMundo);
    
    @Query(value = "SELECT MAX(id_pais_mundo) FROM tabla_paises_mundo", nativeQuery = true)
    Long findMaxIdPaisMundo();
}
