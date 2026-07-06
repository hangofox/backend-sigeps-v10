//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.EstablecimientoCliente;
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
public interface EstablecimientoClienteRepository extends JpaRepository<EstablecimientoCliente,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_establecimientos_clientes, " +
    "tabla_tipos_documentos_identificacion " +
    "WHERE " +
    "(tabla_establecimientos_clientes.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(:idEstablecimientoCliente IS NULL OR tabla_establecimientos_clientes.id_establecimiento_cliente = :idEstablecimientoCliente) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) = UPPER(:numeroDocumentoIdentificacionEstablecimientoCliente)) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:estadoEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) = UPPER(:estadoEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idEstablecimientoCliente") Long idEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEstablecimientoCliente") String numeroDocumentoIdentificacionEstablecimientoCliente, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("estadoEstablecimientoCliente") String estadoEstablecimientoCliente);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_establecimientos_clientes.* " +
    "FROM " +
    "tabla_establecimientos_clientes, " +
    "tabla_tipos_documentos_identificacion " +
    "WHERE " +
    "(tabla_establecimientos_clientes.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(:idEstablecimientoCliente IS NULL OR tabla_establecimientos_clientes.id_establecimiento_cliente = :idEstablecimientoCliente) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) = UPPER(:numeroDocumentoIdentificacionEstablecimientoCliente)) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:estadoEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) = UPPER(:estadoEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.id_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.id_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion END ASC, " +
    "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion END DESC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'numeroDocumentoIdentificacionEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.fecha_h_m_s_ingreso_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.fecha_h_m_s_ingreso_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.fecha_h_m_s_modificacion_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.fecha_h_m_s_modificacion_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.estado_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.estado_establecimiento_cliente END DESC", nativeQuery = true)
    List<EstablecimientoCliente> findAllEstablecimientosClientes(@Param("idEstablecimientoCliente") Long idEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEstablecimientoCliente") String numeroDocumentoIdentificacionEstablecimientoCliente, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("estadoEstablecimientoCliente") String estadoEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_establecimientos_clientes.* " +
    "FROM " +
    "tabla_establecimientos_clientes, " +
    "tabla_tipos_documentos_identificacion " +
    "WHERE " +
    "(tabla_establecimientos_clientes.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(:idEstablecimientoCliente IS NULL OR tabla_establecimientos_clientes.id_establecimiento_cliente = :idEstablecimientoCliente) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) = UPPER(:numeroDocumentoIdentificacionEstablecimientoCliente)) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:estadoEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) = UPPER(:estadoEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.id_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.id_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.estado_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.estado_establecimiento_cliente END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_establecimientos_clientes, " +
    "tabla_tipos_documentos_identificacion " +
    "WHERE " +
    "(tabla_establecimientos_clientes.id_tipo_documento_identificacion=tabla_tipos_documentos_identificacion.id_tipo_documento_identificacion) AND " +
    "(:idEstablecimientoCliente IS NULL OR tabla_establecimientos_clientes.id_establecimiento_cliente = :idEstablecimientoCliente) AND " +
    "(:nombreTipoDocumentoIdentificacion IS NULL OR UPPER(tabla_tipos_documentos_identificacion.nombre_tipo_documento_identificacion) = UPPER(:nombreTipoDocumentoIdentificacion)) AND " +
    "(:numeroDocumentoIdentificacionEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.numero_documento_identificacion_establecimiento_cliente) = UPPER(:numeroDocumentoIdentificacionEstablecimientoCliente)) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:estadoEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) = UPPER(:estadoEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_establecimientos_clientes.estado_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<EstablecimientoCliente> findAllEstablecimientosClientesPag(Pageable pageable, @Param("idEstablecimientoCliente") Long idEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreTipoDocumentoIdentificacion") String nombreTipoDocumentoIdentificacion, @Param("numeroDocumentoIdentificacionEstablecimientoCliente") String numeroDocumentoIdentificacionEstablecimientoCliente, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("estadoEstablecimientoCliente") String estadoEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<EstablecimientoCliente> findByIdEstablecimientoCliente(Long idEstablecimientoCliente);
    
    EstablecimientoCliente findByNumeroDocumentoIdentificacionEstablecimientoCliente(String numeroDocumentoIdentificacionEstablecimientoCliente);

    EstablecimientoCliente findFirstByNombreRazonSocialEstablecimientoCliente(String nombreRazonSocialEstablecimientoCliente);
    
    @Query(value = "SELECT MAX(id_establecimiento_cliente) FROM tabla_establecimientos_clientes", nativeQuery = true)
    Long findMaxIdEstablecimientoCliente();
}
