//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.DepartamentooEstadoMundo;
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
public interface DepartamentooEstadoMundoRepository extends JpaRepository<DepartamentooEstadoMundo,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (ID + ID PAIS MUNDO + ID DEPTO O ESTADO MUNDO + NOMBRE PAIS MUNDO + KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE (tabla_departamentos_o_estados_mundo.id_pais_mundo = tabla_paises_mundo.id_pais_mundo) AND " +
          "(:idPaisMundo IS NULL OR tabla_paises_mundo.id_pais_mundo = :idPaisMundo) AND " +
          "(:nombrePaisMundo IS NULL OR UPPER(tabla_paises_mundo.nombre_pais_mundo) = UPPER(:nombrePaisMundo)) AND " +
          "(:keyword IS NULL OR UPPER(tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idPaisMundo") Long idPaisMundo, @Param("nombrePaisMundo") String nombrePaisMundo, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT tabla_departamentos_o_estados_mundo.* " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE (tabla_departamentos_o_estados_mundo.id_pais_mundo = tabla_paises_mundo.id_pais_mundo) AND " +
          "(:idPaisMundo IS NULL OR tabla_paises_mundo.id_pais_mundo = :idPaisMundo) AND " +
          "(:nombrePaisMundo IS NULL OR UPPER(tabla_paises_mundo.nombre_pais_mundo) = UPPER(:nombrePaisMundo)) AND " +
          "(:keyword IS NULL OR UPPER(tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idDepartamentooEstadoMundo' AND :orderMode = 'ASC' THEN tabla_departamentos_o_estados_mundo.id_departamento_o_estado_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idDepartamentooEstadoMundo' AND :orderMode = 'DESC' THEN tabla_departamentos_o_estados_mundo.id_departamento_o_estado_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombreDepartamentooEstadoMundo' AND :orderMode = 'ASC' THEN tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombreDepartamentooEstadoMundo' AND :orderMode = 'DESC' THEN tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo END DESC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'ASC' THEN tabla_paises_mundo.id_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'DESC' THEN tabla_paises_mundo.id_pais_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'ASC' THEN tabla_paises_mundo.nombre_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'DESC' THEN tabla_paises_mundo.nombre_pais_mundo END DESC", nativeQuery = true)
    List<DepartamentooEstadoMundo> findAllDepartamentosoEstadosMundo(@Param("idPaisMundo") Long idPaisMundo, @Param("nombrePaisMundo") String nombrePaisMundo, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT tabla_departamentos_o_estados_mundo.* " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE (tabla_departamentos_o_estados_mundo.id_pais_mundo = tabla_paises_mundo.id_pais_mundo) AND " +
          "(:idPaisMundo IS NULL OR tabla_paises_mundo.id_pais_mundo = :idPaisMundo) AND " +
          "(:nombrePaisMundo IS NULL OR UPPER(tabla_paises_mundo.nombre_pais_mundo) = UPPER(:nombrePaisMundo)) AND " +
          "(:keyword IS NULL OR UPPER(tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idDepartamentooEstadoMundo' AND :orderMode = 'ASC' THEN tabla_departamentos_o_estados_mundo.id_departamento_o_estado_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idDepartamentooEstadoMundo' AND :orderMode = 'DESC' THEN tabla_departamentos_o_estados_mundo.id_departamento_o_estado_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombreDepartamentooEstadoMundo' AND :orderMode = 'ASC' THEN tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombreDepartamentooEstadoMundo' AND :orderMode = 'DESC' THEN tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo END DESC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'ASC' THEN tabla_paises_mundo.id_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'idPaisMundo' AND :orderMode = 'DESC' THEN tabla_paises_mundo.id_pais_mundo END DESC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'ASC' THEN tabla_paises_mundo.nombre_pais_mundo END ASC, " +
          "CASE WHEN :orderBy = 'nombrePaisMundo' AND :orderMode = 'DESC' THEN tabla_paises_mundo.nombre_pais_mundo END DESC",
          countQuery = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE (tabla_departamentos_o_estados_mundo.id_pais_mundo = tabla_paises_mundo.id_pais_mundo) AND " +
          "(:idPaisMundo IS NULL OR tabla_paises_mundo.id_pais_mundo = :idPaisMundo) AND " +
          "(:nombrePaisMundo IS NULL OR UPPER(tabla_paises_mundo.nombre_pais_mundo) = UPPER(:nombrePaisMundo)) AND " +
          "(:keyword IS NULL OR UPPER(tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo) LIKE UPPER(CONCAT('%', :keyword, '%')))",
          nativeQuery = true)
    Slice<DepartamentooEstadoMundo> findAllDepartamentosoEstadosMundoPag(Pageable pageable, @Param("idPaisMundo") Long idPaisMundo, @Param("nombrePaisMundo") String nombrePaisMundo, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<DepartamentooEstadoMundo> findByIdDepartamentooEstadoMundo(Long idDepartamentooEstadoMundo);
    
    @Query(value = "" +
          "SELECT tabla_departamentos_o_estados_mundo.* " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE " +
          "(tabla_departamentos_o_estados_mundo.id_pais_mundo=tabla_paises_mundo.id_pais_mundo) AND " +
          "(tabla_paises_mundo.id_pais_mundo = :idPaisMundo) AND " +
          "(tabla_departamentos_o_estados_mundo.id_departamento_o_estado_mundo = :idDepartamentooEstadoMundo)", nativeQuery = true)
    DepartamentooEstadoMundo findByIdPaisMundoAndIdDepartamentooEstadoMundo(@Param("idPaisMundo") Long idPaisMundo, @Param("idDepartamentooEstadoMundo") Long idDepartamentooEstadoMundo);
    
    @Query(value = "" +
          "SELECT tabla_departamentos_o_estados_mundo.* " +
          "FROM tabla_departamentos_o_estados_mundo, tabla_paises_mundo " +
          "WHERE " +
          "(tabla_departamentos_o_estados_mundo.id_pais_mundo=tabla_paises_mundo.id_pais_mundo) AND " +
          "(UPPER(tabla_paises_mundo.nombre_pais_mundo) = UPPER(:nombrePaisMundo)) AND " +
          "(UPPER(tabla_departamentos_o_estados_mundo.nombre_departamento_o_estado_mundo) = UPPER(:nombreDepartamentooEstadoMundo))", nativeQuery = true)
    DepartamentooEstadoMundo findByNombrePaisMundoAndNombreDepartamentooEstadoMundo(@Param("nombrePaisMundo") String nombrePaisMundo, @Param("nombreDepartamentooEstadoMundo") String nombreDepartamentooEstadoMundo);
    
    @Query(value = "SELECT MAX(id_departamento_o_estado_mundo) FROM tabla_departamentos_o_estados_mundo", nativeQuery = true)
    Long findMaxIdDepartamentooEstadoMundo();
}
