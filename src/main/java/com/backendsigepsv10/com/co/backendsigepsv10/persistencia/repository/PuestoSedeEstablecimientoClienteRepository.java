//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.PuestoSedeEstablecimientoCliente;
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
public interface PuestoSedeEstablecimientoClienteRepository extends JpaRepository<PuestoSedeEstablecimientoCliente,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_sedes_establecimientos_clientes " +
    "WHERE " +
    "(tabla_puestos_sedes_establecimientos_clientes.id_sede_establecimiento_cliente=tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente) AND " +
    "(:idPuestoSedeEstablecimientoCliente IS NULL OR tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente = :idPuestoSedeEstablecimientoCliente) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:estadoPuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) = UPPER(:estadoPuestoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idPuestoSedeEstablecimientoCliente") Long idPuestoSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("estadoPuestoSedeEstablecimientoCliente") String estadoPuestoSedeEstablecimientoCliente);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_puestos_sedes_establecimientos_clientes.* " +
    "FROM " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_sedes_establecimientos_clientes " +
    "WHERE " +
    "(tabla_puestos_sedes_establecimientos_clientes.id_sede_establecimiento_cliente=tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente) AND " +
    "(:idPuestoSedeEstablecimientoCliente IS NULL OR tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente = :idPuestoSedeEstablecimientoCliente) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:estadoPuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) = UPPER(:estadoPuestoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombreSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombrePuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombrePuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.fecha_h_m_s_ingreso_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIngresoPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.fecha_h_m_s_ingreso_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.fecha_h_m_s_modificacion_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSModificacionPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.fecha_h_m_s_modificacion_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente END DESC", nativeQuery = true)
    List<PuestoSedeEstablecimientoCliente> findAllPuestosSedesEstablecimientosClientes(@Param("idPuestoSedeEstablecimientoCliente") Long idPuestoSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("estadoPuestoSedeEstablecimientoCliente") String estadoPuestoSedeEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_puestos_sedes_establecimientos_clientes.* " +
    "FROM " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_sedes_establecimientos_clientes " +
    "WHERE " +
    "(tabla_puestos_sedes_establecimientos_clientes.id_sede_establecimiento_cliente=tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente) AND " +
    "(:idPuestoSedeEstablecimientoCliente IS NULL OR tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente = :idPuestoSedeEstablecimientoCliente) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:estadoPuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) = UPPER(:estadoPuestoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'idPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombrePuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombrePuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'estadoPuestoSedeEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'estadoPuestoSedeEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_sedes_establecimientos_clientes " +
    "WHERE " +
    "(tabla_puestos_sedes_establecimientos_clientes.id_sede_establecimiento_cliente=tabla_sedes_establecimientos_clientes.id_sede_establecimiento_cliente) AND " +
    "(:idPuestoSedeEstablecimientoCliente IS NULL OR tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente = :idPuestoSedeEstablecimientoCliente) AND " +
    "(:nombreSedeEstablecimientoCliente IS NULL OR UPPER(tabla_sedes_establecimientos_clientes.nombre_sede_establecimiento_cliente) = UPPER(:nombreSedeEstablecimientoCliente)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:estadoPuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) = UPPER(:estadoPuestoSedeEstablecimientoCliente)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.estado_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<PuestoSedeEstablecimientoCliente> findAllPuestosSedesEstablecimientosClientesPag(Pageable pageable, @Param("idPuestoSedeEstablecimientoCliente") Long idPuestoSedeEstablecimientoCliente, @Param("keyword") String keyword, @Param("nombreSedeEstablecimientoCliente") String nombreSedeEstablecimientoCliente, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("estadoPuestoSedeEstablecimientoCliente") String estadoPuestoSedeEstablecimientoCliente, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<PuestoSedeEstablecimientoCliente> findByIdPuestoSedeEstablecimientoCliente(Long idPuestoSedeEstablecimientoCliente);
    
    PuestoSedeEstablecimientoCliente findByNombrePuestoSedeEstablecimientoCliente(String nombrePuestoSedeEstablecimientoCliente);
    
    @Query(value = "SELECT MAX(id_puesto_sede_establecimiento_cliente) FROM tabla_puestos_sedes_establecimientos_clientes", nativeQuery = true)
    Long findMaxIdPuestoSedeEstablecimientoCliente();
}
