//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.RecuperacionContrasenaAccesoUsuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface RecuperacionContrasenaAccesoUsuarioRepository extends JpaRepository<RecuperacionContrasenaAccesoUsuario,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_recuperaciones_contrasenas_accesos_usuarios, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idRecuperacionContrasenaAccesoUsuario IS NULL OR tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario = :idRecuperacionContrasenaAccesoUsuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:estadoUsoCodigoActivacionContrasenaAccesoUsuario IS NULL OR UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) = UPPER(:estadoUsoCodigoActivacionContrasenaAccesoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idRecuperacionContrasenaAccesoUsuario") Long idRecuperacionContrasenaAccesoUsuario, @Param("keyword") String keyword, @Param("idUsuario") Long idUsuario, @Param("estadoUsoCodigoActivacionContrasenaAccesoUsuario") String estadoUsoCodigoActivacionContrasenaAccesoUsuario);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_recuperaciones_contrasenas_accesos_usuarios.* " +
    "FROM " +
    "tabla_recuperaciones_contrasenas_accesos_usuarios, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idRecuperacionContrasenaAccesoUsuario IS NULL OR tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario = :idRecuperacionContrasenaAccesoUsuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:estadoUsoCodigoActivacionContrasenaAccesoUsuario IS NULL OR UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) = UPPER(:estadoUsoCodigoActivacionContrasenaAccesoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idRecuperacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'idRecuperacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSExpCodActivContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.fecha_h_m_s_exp_cod_activ_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSExpCodActivContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.fecha_h_m_s_exp_cod_activ_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'estadoUsoCodigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'estadoUsoCodigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'codigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'codigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario END DESC", nativeQuery = true)
    List<RecuperacionContrasenaAccesoUsuario> findAllRecuperacionesContrasenasAccesosUsuarios(@Param("idRecuperacionContrasenaAccesoUsuario") Long idRecuperacionContrasenaAccesoUsuario, @Param("keyword") String keyword, @Param("idUsuario") Long idUsuario, @Param("estadoUsoCodigoActivacionContrasenaAccesoUsuario") String estadoUsoCodigoActivacionContrasenaAccesoUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_recuperaciones_contrasenas_accesos_usuarios.* " +
    "FROM " +
    "tabla_recuperaciones_contrasenas_accesos_usuarios, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idRecuperacionContrasenaAccesoUsuario IS NULL OR tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario = :idRecuperacionContrasenaAccesoUsuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:estadoUsoCodigoActivacionContrasenaAccesoUsuario IS NULL OR UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) = UPPER(:estadoUsoCodigoActivacionContrasenaAccesoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idRecuperacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'idRecuperacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSExpCodActivContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.fecha_h_m_s_exp_cod_activ_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSExpCodActivContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.fecha_h_m_s_exp_cod_activ_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'estadoUsoCodigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'estadoUsoCodigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'codigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'codigoActivacionContrasenaAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_recuperaciones_contrasenas_accesos_usuarios, " +
    "tabla_usuarios " +
    "WHERE " +
    "(tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario=tabla_usuarios.id_usuario) AND " +
    "(:idRecuperacionContrasenaAccesoUsuario IS NULL OR tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario = :idRecuperacionContrasenaAccesoUsuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:estadoUsoCodigoActivacionContrasenaAccesoUsuario IS NULL OR UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) = UPPER(:estadoUsoCodigoActivacionContrasenaAccesoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_recuperaciones_contrasenas_accesos_usuarios.estado_uso_codigo_activacion_contrasena_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<RecuperacionContrasenaAccesoUsuario> findAllRecuperacionesContrasenasAccesosUsuariosPag(Pageable pageable, @Param("idRecuperacionContrasenaAccesoUsuario") Long idRecuperacionContrasenaAccesoUsuario, @Param("keyword") String keyword, @Param("idUsuario") Long idUsuario, @Param("estadoUsoCodigoActivacionContrasenaAccesoUsuario") String estadoUsoCodigoActivacionContrasenaAccesoUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<RecuperacionContrasenaAccesoUsuario> findByIdRecuperacionContrasenaAccesoUsuario(Long idRecuperacionContrasenaAccesoUsuario);
    
    RecuperacionContrasenaAccesoUsuario findByCodigoActivacionContrasenaAccesoUsuario(String codigoActivacionContrasenaAccesoUsuario);
    
    @Query(value = "SELECT * FROM tabla_recuperaciones_contrasenas_accesos_usuarios ORDER BY id_recuperacion_contrasena_acceso_usuario ASC", nativeQuery = true)
    List<RecuperacionContrasenaAccesoUsuario> findAllRecuperacionesContrasenasAccesosUsuariosOrderedByIdAsc();
    
    @Query(value = "" +
          "SELECT " +
          "tabla_recuperaciones_contrasenas_accesos_usuarios.* " +
          "FROM " +
          "tabla_recuperaciones_contrasenas_accesos_usuarios, " +
          "tabla_usuarios " +
          "WHERE " +
          "(tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario=tabla_usuarios.id_usuario) AND " +
          "(tabla_usuarios.id_usuario = :idUsuario) " +
          "ORDER BY tabla_recuperaciones_contrasenas_accesos_usuarios.id_recuperacion_contrasena_acceso_usuario ASC", nativeQuery = true)
    List<RecuperacionContrasenaAccesoUsuario> searchRecuperacionesContrasenasAccesosUsuariosByIdUsuarioOrderedByIdAsc(@Param("idUsuario") Long idUsuario);
    
    @Query(value = "SELECT MAX(id_recuperacion_contrasena_acceso_usuario) FROM tabla_recuperaciones_contrasenas_accesos_usuarios", nativeQuery = true)
    Long findMaxIdRecuperacionContrasenaAccesoUsuario();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE tabla_recuperaciones_contrasenas_accesos_usuarios SET estado_uso_codigo_activacion_contrasena_acceso_usuario='EXPIRADO' WHERE (fecha_h_m_s_exp_cod_activ_contrasena_acceso_usuario < :fechaHMSExpCodActivContrasenaAccesoUsuario) AND (estado_uso_codigo_activacion_contrasena_acceso_usuario='PENDIENTE DE USO')", nativeQuery = true)
    void updateEstadoUsoCodActivContrasenaAccesoUsuarioRecuperacionesContrasenasAccesosUsuarios(@Param("fechaHMSExpCodActivContrasenaAccesoUsuario") Date fechaHMSExpCodActivContrasenaAccesoUsuario);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tabla_recuperaciones_contrasenas_accesos_usuarios WHERE tabla_recuperaciones_contrasenas_accesos_usuarios.id_usuario = :idUsuario", nativeQuery = true)
    void deleteRecuperacionesContrasenasAccesosUsuariosByIdUsuario(@Param("idUsuario") Long idUsuario);
}
