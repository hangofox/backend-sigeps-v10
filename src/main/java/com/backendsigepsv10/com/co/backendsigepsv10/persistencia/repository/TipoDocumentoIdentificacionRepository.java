//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.backendsigepsv10.com.co.backendsigepsv10.persistencia.entity.TipoDocumentoIdentificacion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface TipoDocumentoIdentificacionRepository extends JpaRepository<TipoDocumentoIdentificacion,Long> {
    
    //CONTADORES Y LISTADOS UNIFICADOS (KEYWORD + ORDERBY + ORDERMODE):
    
    //1. CONTADORES DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT COUNT(*) " +
          "FROM tabla_tipos_documentos_identificacion " +
          "WHERE " +
          "(:idTipoDocumentoIdentificacion IS NULL OR id_tipo_documento_identificacion = :idTipoDocumentoIdentificacion) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')))", nativeQuery = true)
    Long findTotalRegistros(@Param("idTipoDocumentoIdentificacion") Long idTipoDocumentoIdentificacion, @Param("keyword") String keyword);
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_documentos_identificacion " +
          "WHERE " +
          "(:idTipoDocumentoIdentificacion IS NULL OR id_tipo_documento_identificacion = :idTipoDocumentoIdentificacion) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN id_tipo_documento_identificacion END ASC, " +
          "CASE WHEN :orderBy = 'idTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN id_tipo_documento_identificacion END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN nombre_tipo_documento_identificacion END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN nombre_tipo_documento_identificacion END DESC", nativeQuery = true)
    List<TipoDocumentoIdentificacion> findAllTiposDocumentosIdentificacion(@Param("idTipoDocumentoIdentificacion") Long idTipoDocumentoIdentificacion, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    //2. LISTADO DE REGISTROS FILTRADOS PAGINADOS.
    @Query(value = "" +
          "SELECT * " +
          "FROM tabla_tipos_documentos_identificacion " +
          "WHERE " +
          "(:idTipoDocumentoIdentificacion IS NULL OR id_tipo_documento_identificacion = :idTipoDocumentoIdentificacion) AND " +
          "(:keyword IS NULL OR UPPER(nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'idTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN id_tipo_documento_identificacion END ASC, " +
          "CASE WHEN :orderBy = 'idTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN id_tipo_documento_identificacion END DESC, " +
          "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'ASC' THEN nombre_tipo_documento_identificacion END ASC, " +
          "CASE WHEN :orderBy = 'nombreTipoDocumentoIdentificacion' AND :orderMode = 'DESC' THEN nombre_tipo_documento_identificacion END DESC",
          countQuery = "SELECT COUNT(*) FROM tabla_tipos_documentos_identificacion WHERE (:idTipoDocumentoIdentificacion IS NULL OR id_tipo_documento_identificacion = :idTipoDocumentoIdentificacion) AND (:keyword IS NULL OR UPPER(nombre_tipo_documento_identificacion) LIKE UPPER(CONCAT('%', :keyword, '%')))",
          nativeQuery = true)
    Slice<TipoDocumentoIdentificacion> findAllTiposDocumentosIdentificacionPag(Pageable pageable, @Param("idTipoDocumentoIdentificacion") Long idTipoDocumentoIdentificacion, @Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode);
    
    Optional<TipoDocumentoIdentificacion> findByIdTipoDocumentoIdentificacion(Long idTipoDocumentoIdentificacion);
    
    TipoDocumentoIdentificacion findByNombreTipoDocumentoIdentificacion(String nombreTipoDocumentoIdentificacion);
    
    @Query(value = "SELECT MAX(id_tipo_documento_identificacion) FROM tabla_tipos_documentos_identificacion", nativeQuery = true)
    Long findMaxIdTipoDocumentoIdentificacion();
}
