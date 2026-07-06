//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Funcionalidad;
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
public interface FuncionalidadRepository extends JpaRepository<Funcionalidad,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (ID + KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT COUNT(*) FROM tabla_funcionalidades " +
          "WHERE (:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idFuncionalidad") Long idFuncionalidad, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT tabla_funcionalidades.* FROM tabla_funcionalidades " +
          "WHERE (:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.id_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreIconoMenuPrincipalFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreIconoMenuPrincipalFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'labelMenuPrincipalFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.label_menu_principal_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'labelMenuPrincipalFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.label_menu_principal_funcionalidad END DESC", nativeQuery = true)
    List<Funcionalidad> findAllFuncionalidades(@Param("idFuncionalidad") Long idFuncionalidad, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value =
          "SELECT tabla_funcionalidades.* FROM tabla_funcionalidades " +
          "WHERE (:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.id_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreIconoMenuPrincipalFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreIconoMenuPrincipalFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'labelMenuPrincipalFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.label_menu_principal_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'labelMenuPrincipalFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.label_menu_principal_funcionalidad END DESC",
          countQuery =
          "SELECT COUNT(*) FROM tabla_funcionalidades " +
          "WHERE (:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_icono_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<Funcionalidad> findAllFuncionalidadesPag(Pageable pageable, @Param("idFuncionalidad") Long idFuncionalidad, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<Funcionalidad> findByIdFuncionalidad(Long idFuncionalidad);
    
    Funcionalidad findByNombreFuncionalidad(String nombreFuncionalidad);
    
    @Query(value = "SELECT MAX(id_funcionalidad) FROM tabla_funcionalidades", nativeQuery = true)
    Long findMaxIdFuncionalidad();
}
