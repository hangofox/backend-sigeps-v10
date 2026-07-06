//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.Turno;
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
public interface TurnoRepository extends JpaRepository<Turno,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_turnos " +
          "WHERE " +
          "(:idTurno IS NULL OR id_turno = :idTurno) AND " +
          "(:nombreTurno IS NULL OR UPPER(nombre_turno) = UPPER(:nombreTurno)) AND " +
          "(:estadoTurno IS NULL OR UPPER(estado_turno) = UPPER(:estadoTurno)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_turno) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTurno") Long idTurno, @Param("keyword") String keyword, @Param("nombreTurno") String nombreTurno, @Param("estadoTurno") String estadoTurno);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_turnos " +
          "WHERE " +
          "(:idTurno IS NULL OR id_turno = :idTurno) AND " +
          "(:nombreTurno IS NULL OR UPPER(nombre_turno) = UPPER(:nombreTurno)) AND " +
          "(:estadoTurno IS NULL OR UPPER(estado_turno) = UPPER(:estadoTurno)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_turno) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTurno' AND :orderMode = 'ASC' THEN id_turno END ASC, " +
          "CASE WHEN :orderBy = 'idTurno' AND :orderMode = 'DESC' THEN id_turno END DESC, " +
          "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'ASC' THEN nombre_turno END ASC, " +
          "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'DESC' THEN nombre_turno END DESC, " +
          "CASE WHEN :orderBy = 'hMSIniciacionTurno' AND :orderMode = 'ASC' THEN h_m_s_iniciacion_turno END ASC, " +
          "CASE WHEN :orderBy = 'hMSIniciacionTurno' AND :orderMode = 'DESC' THEN h_m_s_iniciacion_turno END DESC, " +
          "CASE WHEN :orderBy = 'hMSFinalizacionTurno' AND :orderMode = 'ASC' THEN h_m_s_finalizacion_turno END ASC, " +
          "CASE WHEN :orderBy = 'hMSFinalizacionTurno' AND :orderMode = 'DESC' THEN h_m_s_finalizacion_turno END DESC, " +
          "CASE WHEN :orderBy = 'estadoTurno' AND :orderMode = 'ASC' THEN estado_turno END ASC, " +
          "CASE WHEN :orderBy = 'estadoTurno' AND :orderMode = 'DESC' THEN estado_turno END DESC", nativeQuery = true)
    List<Turno> findAllTurnos(@Param("idTurno") Long idTurno, @Param("keyword") String keyword, @Param("nombreTurno") String nombreTurno, @Param("estadoTurno") String estadoTurno, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_turnos " +
          "WHERE " +
          "(:idTurno IS NULL OR id_turno = :idTurno) AND " +
          "(:nombreTurno IS NULL OR UPPER(nombre_turno) = UPPER(:nombreTurno)) AND " +
          "(:estadoTurno IS NULL OR UPPER(estado_turno) = UPPER(:estadoTurno)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_turno) LIKE UPPER(CONCAT('%', :keyword, '%')))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTurno' AND :orderMode = 'ASC' THEN id_turno END ASC, " +
          "CASE WHEN :orderBy = 'idTurno' AND :orderMode = 'DESC' THEN id_turno END DESC, " +
          "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'ASC' THEN nombre_turno END ASC, " +
          "CASE WHEN :orderBy = 'nombreTurno' AND :orderMode = 'DESC' THEN nombre_turno END DESC, " +
          "CASE WHEN :orderBy = 'hMSIniciacionTurno' AND :orderMode = 'ASC' THEN h_m_s_iniciacion_turno END ASC, " +
          "CASE WHEN :orderBy = 'hMSIniciacionTurno' AND :orderMode = 'DESC' THEN h_m_s_iniciacion_turno END DESC, " +
          "CASE WHEN :orderBy = 'hMSFinalizacionTurno' AND :orderMode = 'ASC' THEN h_m_s_finalizacion_turno END ASC, " +
          "CASE WHEN :orderBy = 'hMSFinalizacionTurno' AND :orderMode = 'DESC' THEN h_m_s_finalizacion_turno END DESC, " +
          "CASE WHEN :orderBy = 'estadoTurno' AND :orderMode = 'ASC' THEN estado_turno END ASC, " +
          "CASE WHEN :orderBy = 'estadoTurno' AND :orderMode = 'DESC' THEN estado_turno END DESC",
          countQuery = "" +
          "SELECT COUNT(*) FROM tabla_turnos " +
          "WHERE (:idTurno IS NULL OR id_turno = :idTurno) AND " +
          "(:nombreTurno IS NULL OR UPPER(nombre_turno) = UPPER(:nombreTurno)) AND " +
          "(:estadoTurno IS NULL OR UPPER(estado_turno) = UPPER(:estadoTurno)) AND " +
          "(:keyword IS NULL OR (UPPER(nombre_turno) LIKE UPPER(CONCAT('%', :keyword, '%')) OR " +
          "UPPER(estado_turno) LIKE UPPER(CONCAT('%', :keyword, '%'))))", nativeQuery = true)
    Slice<Turno> findAllTurnosPag(Pageable pageable, @Param("idTurno") Long idTurno, @Param("keyword") String keyword, @Param("nombreTurno") String nombreTurno, @Param("estadoTurno") String estadoTurno, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<Turno> findByIdTurno(Long idTurno);
    
    Turno findByNombreTurno(String nombreTurno);
    
    @Query(value = "SELECT MAX(id_turno) FROM tabla_turnos", nativeQuery = true)
    Long findMaxIdTurno();
}
