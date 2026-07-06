//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.AuditoriaSistema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface AuditoriaSistemaRepository extends JpaRepository<AuditoriaSistema,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_auditorias_sistema, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_auditorias_sistema.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idAuditoriaSistema IS NULL OR tabla_auditorias_sistema.id_auditoria_sistema = :idAuditoriaSistema) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.accion_usuario_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.descripcion_auditoria_sistema) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idAuditoriaSistema") Long idAuditoriaSistema, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_auditorias_sistema.* " +
    "FROM " +
    "tabla_auditorias_sistema, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_auditorias_sistema.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idAuditoriaSistema IS NULL OR tabla_auditorias_sistema.id_auditoria_sistema = :idAuditoriaSistema) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.accion_usuario_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.descripcion_auditoria_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idAuditoriaSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.id_auditoria_sistema END ASC, " +
    "CASE WHEN :orderBy = 'idAuditoriaSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.id_auditoria_sistema END DESC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.nombres_usuario END ASC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.nombres_usuario END DESC, " +
    "CASE WHEN :orderBy = 'accionUsuarioSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.accion_usuario_sistema END ASC, " +
    "CASE WHEN :orderBy = 'accionUsuarioSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.accion_usuario_sistema END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSAuditoriaSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.fecha_h_m_s_auditoria_sistema END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSAuditoriaSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.fecha_h_m_s_auditoria_sistema END DESC", nativeQuery = true)
    List<AuditoriaSistema> findAllAuditoriasSistema(@Param("idAuditoriaSistema") Long idAuditoriaSistema, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_auditorias_sistema.* " +
    "FROM " +
    "tabla_auditorias_sistema, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_auditorias_sistema.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idAuditoriaSistema IS NULL OR tabla_auditorias_sistema.id_auditoria_sistema = :idAuditoriaSistema) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.accion_usuario_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.descripcion_auditoria_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idAuditoriaSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.id_auditoria_sistema END ASC, " +
    "CASE WHEN :orderBy = 'idAuditoriaSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.id_auditoria_sistema END DESC, " +
    "CASE WHEN :orderBy = 'accionUsuarioSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.accion_usuario_sistema END ASC, " +
    "CASE WHEN :orderBy = 'accionUsuarioSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.accion_usuario_sistema END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSAuditoriaSistema' AND :orderMode = 'ASC' THEN tabla_auditorias_sistema.fecha_h_m_s_auditoria_sistema END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSAuditoriaSistema' AND :orderMode = 'DESC' THEN tabla_auditorias_sistema.fecha_h_m_s_auditoria_sistema END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_auditorias_sistema, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_auditorias_sistema.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idAuditoriaSistema IS NULL OR tabla_auditorias_sistema.id_auditoria_sistema = :idAuditoriaSistema) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_auditorias_sistema.accion_usuario_sistema) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_auditorias_sistema.descripcion_auditoria_sistema) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<AuditoriaSistema> findAllAuditoriasSistemaPag(Pageable pageable, @Param("idAuditoriaSistema") Long idAuditoriaSistema, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<AuditoriaSistema> findByIdAuditoriaSistema(Long idAuditoriaSistema);
    
    @Query(value =
          "SELECT tabla_auditorias_sistema.* " +
          "FROM tabla_auditorias_sistema, tabla_usuarios " +
          "WHERE (tabla_auditorias_sistema.id_usuario = tabla_usuarios.id_usuario) AND " +
          "(tabla_auditorias_sistema.id_usuario = :idUsuario) AND " +
          "(tabla_auditorias_sistema.fecha_h_m_s_auditoria_sistema = :fechaHMSAuditoriaSistema) AND " +
          "(tabla_auditorias_sistema.accion_usuario_sistema = :accionUsuarioSistema)", nativeQuery = true)
    AuditoriaSistema findByIdUsuarioAndFechaHMSAuditoriaSistemaAndAccionUsuarioSistema(@Param("idUsuario") Long idUsuario, @Param("fechaHMSAuditoriaSistema") Date fechaHMSAuditoriaSistema, @Param("accionUsuarioSistema") String accionUsuarioSistema);

    @Query(value = "SELECT MAX(id_auditoria_sistema) FROM tabla_auditorias_sistema", nativeQuery = true)
    Long findMaxIdAuditoriaSistema();
}
