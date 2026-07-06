//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.ProgramacionTurnoEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 12/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface ProgramacionTurnoEmpleadoRepository extends JpaRepository<ProgramacionTurnoEmpleado,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_programacion_turnos_empleados, " +
    "tabla_empleados, " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_turnos " +
    "WHERE " +
    "(tabla_programacion_turnos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_programacion_turnos_empleados.id_puesto_sede_establecimiento_cliente=tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente) AND " +
    "(tabla_programacion_turnos_empleados.id_turno=tabla_turnos.id_turno) AND " +
    "(:idProgramacionTurnoEmpleado IS NULL OR tabla_programacion_turnos_empleados.id_programacion_turno_empleado = :idProgramacionTurnoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:nombreTurno IS NULL OR UPPER(tabla_turnos.nombre_turno) = UPPER(:nombreTurno)) AND " +
    "(:estadoProgramacionTurnoEmpleado IS NULL OR UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) = UPPER(:estadoProgramacionTurnoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_turnos.nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idProgramacionTurnoEmpleado") Long idProgramacionTurnoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("nombreTurno") String nombreTurno, @Param("estadoProgramacionTurnoEmpleado") String estadoProgramacionTurnoEmpleado);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
    "SELECT tabla_programacion_turnos_empleados.* " +
    "FROM " +
    "tabla_programacion_turnos_empleados, " +
    "tabla_empleados, " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_turnos " +
    "WHERE " +
    "(tabla_programacion_turnos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_programacion_turnos_empleados.id_puesto_sede_establecimiento_cliente=tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente) AND " +
    "(tabla_programacion_turnos_empleados.id_turno=tabla_turnos.id_turno) AND " +
    "(:idProgramacionTurnoEmpleado IS NULL OR tabla_programacion_turnos_empleados.id_programacion_turno_empleado = :idProgramacionTurnoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:nombreTurno IS NULL OR UPPER(tabla_turnos.nombre_turno) = UPPER(:nombreTurno)) AND " +
    "(:estadoProgramacionTurnoEmpleado IS NULL OR UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) = UPPER(:estadoProgramacionTurnoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_empleados.primer_apellido_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_turnos.nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.id_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.id_programacion_turno_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombrePuestoEstablecimientoCliente' AND :orderMode = 'ASC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END ASC, " +
    "CASE WHEN :orderBy = 'nombrePuestoEstablecimientoCliente' AND :orderMode = 'DESC' THEN tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente END DESC, " +
    "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'ASC' THEN tabla_turnos.nombre_turno END ASC, " +
    "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'DESC' THEN tabla_turnos.nombre_turno END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSIniciacionProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.fecha_h_m_s_iniciacion_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSIniciacionProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.fecha_h_m_s_iniciacion_programacion_turno_empleado END DESC, " +
    "CASE WHEN :orderBy = 'fechaHMSFinalizacionProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.fecha_h_m_s_finalizacion_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'fechaHMSFinalizacionProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.fecha_h_m_s_finalizacion_programacion_turno_empleado END DESC, " +
    "CASE WHEN :orderBy = 'estadoProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.estado_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'estadoProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.estado_programacion_turno_empleado END DESC", nativeQuery = true)
    List<ProgramacionTurnoEmpleado> findAllProgramacionesTurnosEmpleados(@Param("idProgramacionTurnoEmpleado") Long idProgramacionTurnoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("nombreTurno") String nombreTurno, @Param("estadoProgramacionTurnoEmpleado") String estadoProgramacionTurnoEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
    "SELECT tabla_programacion_turnos_empleados.* " +
    "FROM " +
    "tabla_programacion_turnos_empleados, " +
    "tabla_empleados, " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_turnos " +
    "WHERE " +
    "(tabla_programacion_turnos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_programacion_turnos_empleados.id_puesto_sede_establecimiento_cliente=tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente) AND " +
    "(tabla_programacion_turnos_empleados.id_turno=tabla_turnos.id_turno) AND " +
    "(:idProgramacionTurnoEmpleado IS NULL OR tabla_programacion_turnos_empleados.id_programacion_turno_empleado = :idProgramacionTurnoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:nombreTurno IS NULL OR UPPER(tabla_turnos.nombre_turno) = UPPER(:nombreTurno)) AND " +
    "(:estadoProgramacionTurnoEmpleado IS NULL OR UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) = UPPER(:estadoProgramacionTurnoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_turnos.nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
    "ORDER BY " +
    "CASE WHEN :orderBy = 'idProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.id_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'idProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.id_programacion_turno_empleado END DESC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'ASC' THEN tabla_empleados.nombres_empleado END ASC, " +
    "CASE WHEN :orderBy = 'nombresEmpleado' AND :orderMode = 'DESC' THEN tabla_empleados.nombres_empleado END DESC, " +
    "CASE WHEN :orderBy = 'estadoProgramacionTurnoEmpleado' AND :orderMode = 'ASC' THEN tabla_programacion_turnos_empleados.estado_programacion_turno_empleado END ASC, " +
    "CASE WHEN :orderBy = 'estadoProgramacionTurnoEmpleado' AND :orderMode = 'DESC' THEN tabla_programacion_turnos_empleados.estado_programacion_turno_empleado END DESC",
    countQuery = "" +
    "SELECT COUNT(*) " +
    "FROM " +
    "tabla_programacion_turnos_empleados, " +
    "tabla_empleados, " +
    "tabla_puestos_sedes_establecimientos_clientes, " +
    "tabla_turnos " +
    "WHERE " +
    "(tabla_programacion_turnos_empleados.id_empleado=tabla_empleados.id_empleado) AND " +
    "(tabla_programacion_turnos_empleados.id_puesto_sede_establecimiento_cliente=tabla_puestos_sedes_establecimientos_clientes.id_puesto_sede_establecimiento_cliente) AND " +
    "(tabla_programacion_turnos_empleados.id_turno=tabla_turnos.id_turno) AND " +
    "(:idProgramacionTurnoEmpleado IS NULL OR tabla_programacion_turnos_empleados.id_programacion_turno_empleado = :idProgramacionTurnoEmpleado) AND " +
    "(:nombresEmpleado IS NULL OR UPPER(tabla_empleados.nombres_empleado) = UPPER(:nombresEmpleado)) AND " +
    "(:primerApellidoEmpleado IS NULL OR UPPER(tabla_empleados.primer_apellido_empleado) = UPPER(:primerApellidoEmpleado)) AND " +
    "(:nombrePuestoSedeEstablecimientoCliente IS NULL OR UPPER(tabla_puestos_sedes_establecimientos_clientes.nombre_puesto_sede_establecimiento_cliente) = UPPER(:nombrePuestoSedeEstablecimientoCliente)) AND " +
    "(:nombreTurno IS NULL OR UPPER(tabla_turnos.nombre_turno) = UPPER(:nombreTurno)) AND " +
    "(:estadoProgramacionTurnoEmpleado IS NULL OR UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) = UPPER(:estadoProgramacionTurnoEmpleado)) AND " +
    "(:keyword IS NULL OR (UPPER(tabla_empleados.nombres_empleado) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
    "UPPER(tabla_programacion_turnos_empleados.estado_programacion_turno_empleado) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<ProgramacionTurnoEmpleado> findAllProgramacionesTurnosEmpleadosPag(Pageable pageable, @Param("idProgramacionTurnoEmpleado") Long idProgramacionTurnoEmpleado, @Param("keyword") String keyword, @Param("nombresEmpleado") String nombresEmpleado, @Param("primerApellidoEmpleado") String primerApellidoEmpleado, @Param("nombrePuestoSedeEstablecimientoCliente") String nombrePuestoSedeEstablecimientoCliente, @Param("nombreTurno") String nombreTurno, @Param("estadoProgramacionTurnoEmpleado") String estadoProgramacionTurnoEmpleado, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<ProgramacionTurnoEmpleado> findByIdProgramacionTurnoEmpleado(Long idProgramacionTurnoEmpleado);
    
    @Query(value =
          "SELECT tabla_programacion_turnos_empleados.* " +
          "FROM tabla_programacion_turnos_empleados, tabla_empleados, tabla_turnos " +
          "WHERE (tabla_programacion_turnos_empleados.id_empleado = tabla_empleados.id_empleado) AND " +
          "(tabla_programacion_turnos_empleados.id_turno = tabla_turnos.id_turno) AND " +
          "(tabla_empleados.id_empleado = :idEmpleado) AND " +
          "(tabla_turnos.nombre_turno = :nombreTurno)", nativeQuery = true)
    ProgramacionTurnoEmpleado findByIdEmpleadoAndNombreTurno(@Param("idEmpleado") Long idEmpleado, @Param("nombreTurno") String nombreTurno);
    
    @Query(value = "SELECT MAX(id_programacion_turno_empleado) FROM tabla_programacion_turnos_empleados", nativeQuery = true)
    Long findMaxIdProgramacionTurnoEmpleado();
}
