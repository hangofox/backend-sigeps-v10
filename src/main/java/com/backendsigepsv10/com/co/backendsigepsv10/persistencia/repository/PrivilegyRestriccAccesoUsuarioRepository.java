//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PrivilegyRestriccAccesoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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
public interface PrivilegyRestriccAccesoUsuarioRepository extends JpaRepository<PrivilegyRestriccAccesoUsuario,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (ID + KEYWORD + IDFUNCIONALIDAD + NOMBREFUNCIONALIDAD + IDROL + NOMBREROL + IDUSUARIO + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT COUNT(*) FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (:idPrivilegyRestriccAccesoUsuario IS NULL OR tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario = :idPrivilegyRestriccAccesoUsuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = tabla_usuarios.id_usuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:nombreFuncionalidad IS NULL OR tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:nombreRol IS NULL OR tabla_roles.nombre_rol = :nombreRol) AND " +
          "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario)", nativeQuery = true)
    Long findTotalRegistros(@Param("idPrivilegyRestriccAccesoUsuario") Long idPrivilegyRestriccAccesoUsuario, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad, @Param("nombreFuncionalidad") String nombreFuncionalidad, @Param("idRol") Long idRol, @Param("nombreRol") String nombreRol, @Param("idUsuario") Long idUsuario);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value =
          "SELECT tabla_privileg_y_restricc_accesos_usuarios.* FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (:idPrivilegyRestriccAccesoUsuario IS NULL OR tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario = :idPrivilegyRestriccAccesoUsuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = tabla_usuarios.id_usuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:nombreFuncionalidad IS NULL OR tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:nombreRol IS NULL OR tabla_roles.nombre_rol = :nombreRol) AND " +
          "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) " +
          "ORDER BY CASE WHEN :orderBy = 'idPrivilegioYRestriccionAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'idPrivilegioYRestriccionAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'numRegPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.numero_registro_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'numRegPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.numero_registro_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_usuario END ASC, " +
          "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_usuario END DESC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_rol END ASC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_rol END DESC, " +
          "CASE WHEN :orderBy = 'urlAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'urlAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'sioNoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.si_o_no_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'sioNoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.si_o_no_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'fechaHMSIngresoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.fecha_h_m_s_ingreso_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'fechaHMSIngresoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.fecha_h_m_s_ingreso_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'ASC' THEN tabla_roles.nombre_rol END ASC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'DESC' THEN tabla_roles.nombre_rol END DESC", nativeQuery = true)
    List<PrivilegyRestriccAccesoUsuario> findAllPrivilegyRestriccAccesosUsuarios(@Param("idPrivilegyRestriccAccesoUsuario") Long idPrivilegyRestriccAccesoUsuario, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad, @Param("nombreFuncionalidad") String nombreFuncionalidad, @Param("idRol") Long idRol, @Param("nombreRol") String nombreRol, @Param("idUsuario") Long idUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value =
          "SELECT tabla_privileg_y_restricc_accesos_usuarios.* FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (:idPrivilegyRestriccAccesoUsuario IS NULL OR tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario = :idPrivilegyRestriccAccesoUsuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = tabla_usuarios.id_usuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:nombreFuncionalidad IS NULL OR tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:nombreRol IS NULL OR tabla_roles.nombre_rol = :nombreRol) AND " +
          "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) " +
          "ORDER BY CASE WHEN :orderBy = 'idPrivilegioYRestriccionAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'idPrivilegioYRestriccionAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'numRegPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.numero_registro_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'numRegPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.numero_registro_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_usuario END ASC, " +
          "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_usuario END DESC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'idFuncionalidad' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_rol END ASC, " +
          "CASE WHEN :orderBy = 'idRol' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.id_rol END DESC, " +
          "CASE WHEN :orderBy = 'urlAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'urlAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'sioNoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.si_o_no_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'sioNoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.si_o_no_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'fechaHMSIngresoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'ASC' THEN tabla_privileg_y_restricc_accesos_usuarios.fecha_h_m_s_ingreso_privilegio_y_restriccion_acceso_usuario END ASC, " +
          "CASE WHEN :orderBy = 'fechaHMSIngresoPrivilegyRestriccAccesoUsuario' AND :orderMode = 'DESC' THEN tabla_privileg_y_restricc_accesos_usuarios.fecha_h_m_s_ingreso_privilegio_y_restriccion_acceso_usuario END DESC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'ASC' THEN tabla_funcionalidades.nombre_funcionalidad END ASC, " +
          "CASE WHEN :orderBy = 'nombreFuncionalidad' AND :orderMode = 'DESC' THEN tabla_funcionalidades.nombre_funcionalidad END DESC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'ASC' THEN tabla_roles.nombre_rol END ASC, " +
          "CASE WHEN :orderBy = 'nombreRol' AND :orderMode = 'DESC' THEN tabla_roles.nombre_rol END DESC",
          countQuery =
          "SELECT COUNT(*) FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (:idPrivilegyRestriccAccesoUsuario IS NULL OR tabla_privileg_y_restricc_accesos_usuarios.id_privilegio_y_restriccion_acceso_usuario = :idPrivilegyRestriccAccesoUsuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = tabla_usuarios.id_usuario) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(:keyword IS NULL OR (UPPER(tabla_funcionalidades.nombre_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_funcionalidades.label_menu_principal_funcionalidad) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_roles.nombre_rol) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(tabla_privileg_y_restricc_accesos_usuarios.url_acceso_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) AND " +
          "(:idFuncionalidad IS NULL OR tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(:nombreFuncionalidad IS NULL OR tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad) AND " +
          "(:idRol IS NULL OR tabla_roles.id_rol = :idRol) AND " +
          "(:nombreRol IS NULL OR tabla_roles.nombre_rol = :nombreRol) AND " +
          "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario)", nativeQuery = true)
    Slice<PrivilegyRestriccAccesoUsuario> findAllPrivilegyRestriccAccesosUsuariosPag(Pageable pageable, @Param("idPrivilegyRestriccAccesoUsuario") Long idPrivilegyRestriccAccesoUsuario, @Param("keyword") String keyword, @Param("idFuncionalidad") Long idFuncionalidad, @Param("nombreFuncionalidad") String nombreFuncionalidad, @Param("idRol") Long idRol, @Param("nombreRol") String nombreRol, @Param("idUsuario") Long idUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<PrivilegyRestriccAccesoUsuario> findByIdPrivilegyRestriccAccesoUsuario(Long idPrivilegyRestriccAccesoUsuario);
    
    @Query(value =
          "SELECT tabla_privileg_y_restricc_accesos_usuarios.* " +
          "FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = :idUsuario) AND " +
          "(tabla_funcionalidades.id_funcionalidad = :idFuncionalidad) AND " +
          "(tabla_roles.id_rol = :idRol)", nativeQuery = true)
    PrivilegyRestriccAccesoUsuario findByIdUsuarioAndIdFuncionalidadAndIdRol(@Param("idUsuario") Long idUsuario, @Param("idFuncionalidad") Long idFuncionalidad, @Param("idRol") Long idRol);
    
    @Query(value =
          "SELECT tabla_privileg_y_restricc_accesos_usuarios.* " +
          "FROM tabla_privileg_y_restricc_accesos_usuarios, tabla_funcionalidades, tabla_roles " +
          "WHERE (tabla_privileg_y_restricc_accesos_usuarios.id_funcionalidad = tabla_funcionalidades.id_funcionalidad) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_rol = tabla_roles.id_rol) AND " +
          "(tabla_privileg_y_restricc_accesos_usuarios.id_usuario = :idUsuario) AND " +
          "(tabla_funcionalidades.nombre_funcionalidad = :nombreFuncionalidad) AND " +
          "(tabla_roles.nombre_rol = :nombreRol)", nativeQuery = true)
    PrivilegyRestriccAccesoUsuario findByIdUsuarioAndNombreFuncionalidadAndNombreRol(@Param("idUsuario") Long idUsuario, @Param("nombreFuncionalidad") String nombreFuncionalidad, @Param("nombreRol") String nombreRol);
    
    @Query(value = "SELECT MAX(id_privilegio_y_restriccion_acceso_usuario) FROM tabla_privileg_y_restricc_accesos_usuarios", nativeQuery = true)
    Long findMaxIdPrivilegyRestriccAccesoUsuario();
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tabla_privileg_y_restricc_accesos_usuarios WHERE id_usuario = :idUsuario", nativeQuery = true)
    void deletePrivilegyRestriccAccesosUsuariosByIdUsuario(@Param("idUsuario") Long idUsuario);
}
