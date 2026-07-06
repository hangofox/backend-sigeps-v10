//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_usuarios, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_usuarios " +
    "WHERE " +
    "(tabla_usuarios.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_usuarios.id_tipo_usuario=tabla_tipos_usuarios.id_tipo_usuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionUsuario IS NULL OR UPPER(tabla_usuarios.numero_documento_identificacion_usuario) = UPPER(:numeroDocumentoIdentificacionUsuario)) AND " +
    "(:nombresUsuario IS NULL OR UPPER(tabla_usuarios.nombres_usuario) = UPPER(:nombresUsuario)) AND " +
    "(:primerApellidoUsuario IS NULL OR UPPER(tabla_usuarios.primer_apellido_usuario) = UPPER(:primerApellidoUsuario)) AND " +
    "(:nombreTipoUsuario IS NULL OR UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) = UPPER(:nombreTipoUsuario)) AND " +
    "(:estadoUsuario IS NULL OR UPPER(tabla_usuarios.estado_usuario) = UPPER(:estadoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.numero_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.lugar_expedicion_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.primer_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.segundo_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.sexo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.direccion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.telefono_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.movil_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_personal_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_institucional_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.pais_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.departamento_o_estado_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.ciudad_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.estado_usuario) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idUsuario") Long idUsuario, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionUsuario") String numeroDocumentoIdentificacionUsuario, @Param("nombresUsuario") String nombresUsuario, @Param("primerApellidoUsuario") String primerApellidoUsuario, @Param("nombreTipoUsuario") String nombreTipoUsuario, @Param("estadoUsuario") String estadoUsuario);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_usuarios.* " +
    "FROM " +
    "tabla_usuarios, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_usuarios " +
    "WHERE " +
    "(tabla_usuarios.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_usuarios.id_tipo_usuario=tabla_tipos_usuarios.id_tipo_usuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionUsuario IS NULL OR UPPER(tabla_usuarios.numero_documento_identificacion_usuario) = UPPER(:numeroDocumentoIdentificacionUsuario)) AND " +
    "(:nombresUsuario IS NULL OR UPPER(tabla_usuarios.nombres_usuario) = UPPER(:nombresUsuario)) AND " +
    "(:primerApellidoUsuario IS NULL OR UPPER(tabla_usuarios.primer_apellido_usuario) = UPPER(:primerApellidoUsuario)) AND " +
    "(:nombreTipoUsuario IS NULL OR UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) = UPPER(:nombreTipoUsuario)) AND " +
    "(:estadoUsuario IS NULL OR UPPER(tabla_usuarios.estado_usuario) = UPPER(:estadoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.numero_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.lugar_expedicion_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.primer_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.segundo_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.sexo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.direccion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.telefono_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.movil_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_personal_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_institucional_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.pais_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.departamento_o_estado_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.ciudad_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.estado_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.id_usuario END ASC, " +
    "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.id_usuario END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoUsuario' AND :orderMode = 'ASC' THEN tabla_tipos_usuarios.nombre_tipo_usuario END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoUsuario' AND :orderMode = 'DESC' THEN tabla_tipos_usuarios.nombre_tipo_usuario END DESC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.numero_documento_identificacion_usuario END ASC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.numero_documento_identificacion_usuario END DESC, " +
    "CASE WHEN :orderBy = 'lugarExpedicionDocumentoIdentificacionUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.lugar_expedicion_documento_identificacion_usuario END ASC, " +
    "CASE WHEN :orderBy = 'lugarExpedicionDocumentoIdentificacionUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.lugar_expedicion_documento_identificacion_usuario END DESC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.nombres_usuario END ASC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.nombres_usuario END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.primer_apellido_usuario END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.primer_apellido_usuario END DESC, " +
    "CASE WHEN :orderBy = 'segundoApellidoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.segundo_apellido_usuario END ASC, " +
    "CASE WHEN :orderBy = 'segundoApellidoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.segundo_apellido_usuario END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSNacimientoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.fecha_h_m_s_nacimiento_usuario END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSNacimientoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.fecha_h_m_s_nacimiento_usuario END DESC, " +
    "CASE WHEN :orderBy = 'sexoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.sexo_usuario END ASC, " +
    "CASE WHEN :orderBy = 'sexoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.sexo_usuario END DESC, " +
    "CASE WHEN :orderBy = 'direccionUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.direccion_usuario END ASC, " +
    "CASE WHEN :orderBy = 'direccionUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.direccion_usuario END DESC, " +
    "CASE WHEN :orderBy = 'telefonoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.telefono_usuario END ASC, " +
    "CASE WHEN :orderBy = 'telefonoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.telefono_usuario END DESC, " +
    "CASE WHEN :orderBy = 'movilUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.movil_usuario END ASC, " +
    "CASE WHEN :orderBy = 'movilUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.movil_usuario END DESC, " +
    "CASE WHEN :orderBy = 'correoElectronicoPersonalUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.correo_electronico_personal_usuario END ASC, " +
    "CASE WHEN :orderBy = 'correoElectronicoPersonalUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.correo_electronico_personal_usuario END DESC, " +
    "CASE WHEN :orderBy = 'correoElectronicoInstitucionalUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.correo_electronico_institucional_usuario END ASC, " +
    "CASE WHEN :orderBy = 'correoElectronicoInstitucionalUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.correo_electronico_institucional_usuario END DESC, " +
    "CASE WHEN :orderBy = 'paisOrigenUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.pais_origen_usuario END ASC, " +
    "CASE WHEN :orderBy = 'paisOrigenUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.pais_origen_usuario END DESC, " +
    "CASE WHEN :orderBy = 'departamentooEstadoOrigenUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.departamento_o_estado_origen_usuario END ASC, " +
    "CASE WHEN :orderBy = 'departamentooEstadoOrigenUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.departamento_o_estado_origen_usuario END DESC, " +
    "CASE WHEN :orderBy = 'ciudadOrigenUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.ciudad_origen_usuario END ASC, " +
    "CASE WHEN :orderBy = 'ciudadOrigenUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.ciudad_origen_usuario END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.fecha_h_m_s_ingreso_usuario END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.fecha_h_m_s_ingreso_usuario END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.fecha_h_m_s_modificacion_usuario END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.fecha_h_m_s_modificacion_usuario END DESC, " +
    "CASE WHEN :orderBy = 'estadoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.estado_usuario END ASC, " +
    "CASE WHEN :orderBy = 'estadoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.estado_usuario END DESC", nativeQuery = true)
    List<Usuario> findAllUsuarios(@Param("idUsuario") Long idUsuario, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionUsuario") String numeroDocumentoIdentificacionUsuario, @Param("nombresUsuario") String nombresUsuario, @Param("primerApellidoUsuario") String primerApellidoUsuario, @Param("nombreTipoUsuario") String nombreTipoUsuario, @Param("estadoUsuario") String estadoUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_usuarios.* " +
    "FROM " +
    "tabla_usuarios, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_usuarios " +
    "WHERE " +
    "(tabla_usuarios.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_usuarios.id_tipo_usuario=tabla_tipos_usuarios.id_tipo_usuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionUsuario IS NULL OR UPPER(tabla_usuarios.numero_documento_identificacion_usuario) = UPPER(:numeroDocumentoIdentificacionUsuario)) AND " +
    "(:nombresUsuario IS NULL OR UPPER(tabla_usuarios.nombres_usuario) = UPPER(:nombresUsuario)) AND " +
    "(:primerApellidoUsuario IS NULL OR UPPER(tabla_usuarios.primer_apellido_usuario) = UPPER(:primerApellidoUsuario)) AND " +
    "(:nombreTipoUsuario IS NULL OR UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) = UPPER(:nombreTipoUsuario)) AND " +
    "(:estadoUsuario IS NULL OR UPPER(tabla_usuarios.estado_usuario) = UPPER(:estadoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.numero_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.lugar_expedicion_documento_identificacion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.primer_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.segundo_apellido_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.sexo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.direccion_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.telefono_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.movil_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_personal_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.correo_electronico_institucional_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.pais_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.departamento_o_estado_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.ciudad_origen_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.estado_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.id_usuario END ASC, " +
    "CASE WHEN :orderBy = 'idUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.id_usuario END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoUsuario' AND :orderMode = 'ASC' THEN tabla_tipos_usuarios.nombre_tipo_usuario END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoUsuario' AND :orderMode = 'DESC' THEN tabla_tipos_usuarios.nombre_tipo_usuario END DESC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.numero_documento_identificacion_usuario END ASC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.numero_documento_identificacion_usuario END DESC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.nombres_usuario END ASC, " +
    "CASE WHEN :orderBy = 'nombresUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.nombres_usuario END DESC, " +
    "CASE WHEN :orderBy = 'primerApellidoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.primer_apellido_usuario END ASC, " +
    "CASE WHEN :orderBy = 'primerApellidoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.primer_apellido_usuario END DESC, " +
    "CASE WHEN :orderBy = 'estadoUsuario' AND :orderMode = 'ASC' THEN tabla_usuarios.estado_usuario END ASC, " +
    "CASE WHEN :orderBy = 'estadoUsuario' AND :orderMode = 'DESC' THEN tabla_usuarios.estado_usuario END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_usuarios, " +
    "tabla_tipos_documentos_identificacion, " +
    "tabla_tipos_usuarios " +
    "WHERE " +
    "(tabla_usuarios.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(tabla_usuarios.id_tipo_usuario=tabla_tipos_usuarios.id_tipo_usuario) AND " +
    "(:idUsuario IS NULL OR tabla_usuarios.id_usuario = :idUsuario) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionUsuario IS NULL OR UPPER(tabla_usuarios.numero_documento_identificacion_usuario) = UPPER(:numeroDocumentoIdentificacionUsuario)) AND " +
    "(:nombresUsuario IS NULL OR UPPER(tabla_usuarios.nombres_usuario) = UPPER(:nombresUsuario)) AND " +
    "(:primerApellidoUsuario IS NULL OR UPPER(tabla_usuarios.primer_apellido_usuario) = UPPER(:primerApellidoUsuario)) AND " +
    "(:nombreTipoUsuario IS NULL OR UPPER(tabla_tipos_usuarios.nombre_tipo_usuario) = UPPER(:nombreTipoUsuario)) AND " +
    "(:estadoUsuario IS NULL OR UPPER(tabla_usuarios.estado_usuario) = UPPER(:estadoUsuario)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.nombres_usuario) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_usuarios.estado_usuario) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<Usuario> findAllUsuariosPag(Pageable pageable, @Param("idUsuario") Long idUsuario, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionUsuario") String numeroDocumentoIdentificacionUsuario, @Param("nombresUsuario") String nombresUsuario, @Param("primerApellidoUsuario") String primerApellidoUsuario, @Param("nombreTipoUsuario") String nombreTipoUsuario, @Param("estadoUsuario") String estadoUsuario, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<Usuario> findByIdUsuario(Long idUsuario);
    
    Usuario findByNumeroDocumentoIdentificacionUsuario(String numeroDocumentoIdentificacionUsuario);
    
    Usuario findByNicknameUsuario(String nicknameUsuario);
    
    Usuario findByNicknameUsuarioAndPasswordUsuario(String nicknameUsuario, String passwordUsuario);
    
    @Query(value = "SELECT MAX(id_usuario) FROM tabla_usuarios", nativeQuery = true)
    Long findMaxIdUsuario();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE tabla_usuarios SET password_usuario=:passwordUsuario WHERE (id_usuario=:idUsuario)", nativeQuery = true)
    void updatePasswordUsuario(@Param("idUsuario") Long idUsuario, @Param("passwordUsuario") String passwordUsuario);
}
