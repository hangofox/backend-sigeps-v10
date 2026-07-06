//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ParametrosSistema;
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
public interface ParametrosSistemaRepository extends JpaRepository<ParametrosSistema,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (ID + KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT COUNT(*) FROM tabla_parametros_sistema " +
          "WHERE (:idParametrosSistema IS NULL OR tabla_parametros_sistema.id_parametros_sistema = :idParametrosSistema) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_parametros_sistema.smtp_host) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.correo_electronico_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.usuario_remitente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idParametrosSistema") Long idParametrosSistema, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT tabla_parametros_sistema.* FROM tabla_parametros_sistema " +
          "WHERE (:idParametrosSistema IS NULL OR tabla_parametros_sistema.id_parametros_sistema = :idParametrosSistema) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_parametros_sistema.smtp_host) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.correo_electronico_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.usuario_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY CASE WHEN :orderBy = 'idParametrosSistema' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.id_parametros_sistema END ASC, " +
          "CASE WHEN :orderBy = 'idParametrosSistema' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.id_parametros_sistema END DESC, " +
          "CASE WHEN :orderBy = 'smtpHost' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.smtp_host END ASC, " +
          "CASE WHEN :orderBy = 'smtpHost' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.smtp_host END DESC, " +
          "CASE WHEN :orderBy = 'correoElectronicoRemitente' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.correo_electronico_remitente END ASC, " +
          "CASE WHEN :orderBy = 'correoElectronicoRemitente' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.correo_electronico_remitente END DESC", nativeQuery = true)
    List<ParametrosSistema> findAllParametrosSistemas(@Param("idParametrosSistema") Long idParametrosSistema, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value =
          "SELECT tabla_parametros_sistema.* FROM tabla_parametros_sistema " +
          "WHERE (:idParametrosSistema IS NULL OR tabla_parametros_sistema.id_parametros_sistema = :idParametrosSistema) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_parametros_sistema.smtp_host) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.correo_electronico_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.usuario_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY CASE WHEN :orderBy = 'idParametrosSistema' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.id_parametros_sistema END ASC, " +
          "CASE WHEN :orderBy = 'idParametrosSistema' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.id_parametros_sistema END DESC, " +
          "CASE WHEN :orderBy = 'smtpHost' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.smtp_host END ASC, " +
          "CASE WHEN :orderBy = 'smtpHost' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.smtp_host END DESC, " +
          "CASE WHEN :orderBy = 'correoElectronicoRemitente' AND :orderMode = 'ASC' THEN tabla_parametros_sistema.correo_electronico_remitente END ASC, " +
          "CASE WHEN :orderBy = 'correoElectronicoRemitente' AND :orderMode = 'DESC' THEN tabla_parametros_sistema.correo_electronico_remitente END DESC",
          countQuery =
          "SELECT COUNT(*) FROM tabla_parametros_sistema " +
          "WHERE (:idParametrosSistema IS NULL OR tabla_parametros_sistema.id_parametros_sistema = :idParametrosSistema) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_parametros_sistema.smtp_host) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.correo_electronico_remitente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_parametros_sistema.usuario_remitente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<ParametrosSistema> findAllParametrosSistemasPag(Pageable pageable, @Param("idParametrosSistema") Long idParametrosSistema, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<ParametrosSistema> findByIdParametrosSistema(Long idParametrosSistema);
    
    ParametrosSistema findBySmtpHost(String smtpHost);
    
    @Query(value = "SELECT MAX(id_parametros_sistema) FROM tabla_parametros_sistema", nativeQuery = true)
    Long findMaxIdParametrosSistema();
}
