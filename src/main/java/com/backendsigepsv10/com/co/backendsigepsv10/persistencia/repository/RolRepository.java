//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Rol;
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
public interface RolRepository extends JpaRepository<Rol,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (ID ROL + KEYWORD + ID FUNCIONALIDAD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT COUNT(*) FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad)", nativeQuery = true)
    Long findTotalRegistros(@Param("idRol") Long idRol, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT tabla_roles.* FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) " +
          "ORDER BY CASE WHEN :orderBy = 'idRol' AND :orderMode = 'ASC' THEN tabla_roles.id_rol END ASC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'DESC' THEN tabla_roles.id_rol END DESC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'ASC' THEN tabla_roles.nombre_rol END ASC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'DESC' THEN tabla_roles.nombre_rol END DESC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_roles.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_roles.id_funcionalidad END DESC", nativeQuery = true)
    List<Rol> findAllRoles(@Param("idRol") Long idRol, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value =
          "SELECT tabla_roles.* FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) " +
          "ORDER BY CASE WHEN :orderBy = 'idRol' AND :orderMode = 'ASC' THEN tabla_roles.id_rol END ASC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'DESC' THEN tabla_roles.id_rol END DESC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'ASC' THEN tabla_roles.nombre_rol END ASC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'DESC' THEN tabla_roles.nombre_rol END DESC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_roles.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_roles.id_funcionalidad END DESC",
          countQuery =
          "SELECT COUNT(*) FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad)", nativeQuery = true)
    Slice<Rol> findAllRolesPag(Pageable pageable, @Param("idRol") Long idRol, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<Rol> findByIdRol(Long idRol);
    
    @Query(value =
          "SELECT tabla_roles.* " +
          "FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad=tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_roles.id_rol = :idRol) AND " +
          "(tabla_funcionalidades.id_funcionalidad = :idFuncionalidad)", nativeQuery = true)
    Rol findByIdRolAndIdFuncionalidad(@Param("idRol") Long idRol, @Param("idFuncionalidad") Long idFuncionalidad);
    
    @Query(value =
          "SELECT tabla_roles.* " +
          "FROM tabla_roles, tabla_funcionalidades " +
          "WHERE (tabla_roles.id_funcionalidad=tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_roles.nombre_rol = :nombreRol) AND " +
          "(tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad)", nativeQuery = true)
    Rol findByNombreRolAndNombreFuncionalidad(@Param("nombreRol") String nombreRol, @Param("nombreFuncionalidad") String nombreFuncionalidad);
    
    @Query(value = "SELECT MAX(id_rol) FROM tabla_roles", nativeQuery = true)
    Long findMaxIdRol();
}
