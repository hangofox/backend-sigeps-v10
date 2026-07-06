//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.SedeEstablecimientoCliente;
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
public interface SedeEstablecimientoClienteRepository extends JpaRepository<SedeEstablecimientoCliente,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_sedes_establecimientos_clientes, " +
    "tabla_establecimientos_clientes " +
    "WHERE " +
    "(tabla_sedes_establecimientos_clientes.id_establecimiento_cliente=tabla_establecimientos_clientes.id_establecimiento_cliente) AND " +
    "(:idSedeEstablecimientoCliente IS NULL OR tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente = :idSedeEstablecimientoCliente) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:estadoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) = UPPER(:estadoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idSedeEstablecimientoCliente") Long idSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("estadoSedeEstablecimientoCliente") String estadoSedeEstablecimientoCliente);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_sedes_establecimientos_clientes.* " +
    "FROM " +
    "tabla_sedes_establecimientos_clientes, " +
    "tabla_establecimientos_clientes " +
    "WHERE " +
    "(tabla_sedes_establecimientos_clientes.id_establecimiento_cliente=tabla_establecimientos_clientes.id_establecimiento_cliente) AND " +
    "(:idSedeEstablecimientoCliente IS NULL OR tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente = :idSedeEstablecimientoCliente) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:estadoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) = UPPER(:estadoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreRazonSocialEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.fecha_h_m_s_ingreso_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.fecha_h_m_s_ingreso_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.fecha_h_m_s_modificacion_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.fecha_h_m_s_modificacion_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente END DESC", nativeQuery = true)
    List<SedeEstablecimientoCliente> findAllSedesEstablecimientosClientes(@Param("idSedeEstablecimientoCliente") Long idSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("estadoSedeEstablecimientoCliente") String estadoSedeEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_sedes_establecimientos_clientes.* " +
    "FROM " +
    "tabla_sedes_establecimientos_clientes, " +
    "tabla_establecimientos_clientes " +
    "WHERE " +
    "(tabla_sedes_establecimientos_clientes.id_establecimiento_cliente=tabla_establecimientos_clientes.id_establecimiento_cliente) AND " +
    "(:idSedeEstablecimientoCliente IS NULL OR tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente = :idSedeEstablecimientoCliente) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:estadoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) = UPPER(:estadoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_sedes_establecimientos_clientes, " +
    "tabla_establecimientos_clientes " +
    "WHERE " +
    "(tabla_sedes_establecimientos_clientes.id_establecimiento_cliente=tabla_establecimientos_clientes.id_establecimiento_cliente) AND " +
    "(:idSedeEstablecimientoCliente IS NULL OR tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente = :idSedeEstablecimientoCliente) AND " +
    "(:nombreRazonSocialEstablecimientoCliente IS NULL OR UPPER(tabla_establecimientos_clientes.nombre_razon_social_establecimiento_cliente) = UPPER(:nombreRazonSocialEstablecimientoCliente)) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:estadoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) = UPPER(:estadoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_sedes_establecimientos_clientes.estado_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<SedeEstablecimientoCliente> findAllSedesEstablecimientosClientesPag(Pageable pageable, @Param("idSedeEstablecimientoCliente") Long idSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreRazonSocialEstablecimientoCliente") String nombreRazonSocialEstablecimientoCliente, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("estadoSedeEstablecimientoCliente") String estadoSedeEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<SedeEstablecimientoCliente> findByIdSedeEstablecimientoCliente(Long idSedeEstablecimientoCliente);
    
    SedeEstablecimientoCliente findByNombreSedeEstablecimientoCliente(String nombreSedeEstablecimientoCliente);
    
    @Query(value = "SELECT MAX(id_sede_establecimiento_cliente) FROM tabla_sedes_establecimientos_clientes", nativeQuery = true)
    Long findMaxIdSedeEstablecimientoCliente();
}
