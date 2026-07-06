//DECLARACIÓN DE PAQUETES:
package com.backendsigepsv10.com.co.backendsigepsv10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ / DAVID GIOVANNI PAEZ OVALLE.
* @Since 09/06/2026.
* Declaración del método DTO.
*/

//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS RESPONSE DE LOS DTO.
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {
    
    //DECLARACIÓN DE LAS VARIABLES DE RESPUESTA DEL DTO:
    private AltaEmpleadoDTO altaEmpleadoDTO;
    private List<AltaEmpleadoDTO> altasEmpleadosDTO;
    private AuditoriaSistemaDTO auditoriaSistemaDTO;
    private List<AuditoriaSistemaDTO> auditoriasSistemasDTO;
    private BajaEmpleadoDTO bajaEmpleadoDTO;
    private List<BajaEmpleadoDTO> bajasEmpleadosDTO;
    private boolean banderaexito;
    private CiudadMundoDTO ciudadMundoDTO;
    private List<CiudadMundoDTO> ciudadesMundoDTO;
    private DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO;
    private List<DepartamentooEstadoMundoDTO> departamentosyEstadosMundoDTO;
    private ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO;
    private List<ClasificacionEmpleadoPlantaDTO> clasificacionesEmpleadosPlantasDTO;
    private EmpleadoDTO empleadoDTO;
    private List<EmpleadoDTO> empleadosDTO;
    private EstablecimientoClienteDTO establecimientoClienteDTO;
    private List<EstablecimientoClienteDTO> establecimientosClientesDTO;
    private FuncionalidadDTO funcionalidadDTO;
    private List<FuncionalidadDTO> funcionalidadesDTO;
    private HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO;
    private List<HistorialMovimientoEmpleadoDTO> historialesMovimientosEmpleadosDTO;
    private HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO;
    private List<HistorialNovedadEmpleadoDTO> historialesNovedadesEmpleadosDTO;
    private String mensaje;
    private PaisMundoDTO paisMundoDTO;
    private List<PaisMundoDTO> paisesMundoDTO;
    private ParametrosSistemaDTO parametrosSistemaDTO;
    private List<ParametrosSistemaDTO> parametrosSistemasDTO;
    private ParametrosSistemaRecuperacionContrasenaDTO parametrosSistemaRecuperacionContrasenaDTO;
    private PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO;
    private List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesosUsuariosDTO;
    private ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO;
    private List<ProgramacionTurnoEmpleadoDTO> programacionesTurnosEmpleadosDTO;
    private PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO;
    private List<PuestoSedeEstablecimientoClienteDTO> puestosSedesEstablecimientosClientesDTO;
    private RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO;
    private List<RecuperacionContrasenaAccesoUsuarioDTO> recuperacionesContrasenasAccesosUsuariosDTO;
    private RolDTO rolDTO;
    private List<RolDTO> rolesDTO;
    private SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO;
    private List<SedeEstablecimientoClienteDTO> sedesEstablecimientosClientesDTO;
    private SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO;
    private List<SubclasificacionEmpleadoPlantaDTO> subclasificacionesEmpleadosPlantasDTO;
    private TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO;
    private List<TipoDocumentoIdentificacionDTO> tiposDocumentosIdentificacionDTO;
    private TipoEmpleadoDTO tipoEmpleadoDTO;
    private List<TipoEmpleadoDTO> tiposEmpleadosDTO;
    private TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO;
    private List<TipoEmpleadoPlantaDTO> tiposEmpleadosPlantaDTO;
    private TipoMovimientoDTO tipoMovimientoDTO;
    private List<TipoMovimientoDTO> tiposMovimientosDTO;
    private TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO;
    private List<TipoNovedadEmpleadoDTO> tiposNovedadesEmpleadosDTO;
    private TipoUsuarioDTO tipoUsuarioDTO;
    private List<TipoUsuarioDTO> tiposUsuariosDTO;
    private TurnoDTO turnoDTO;
    private List<TurnoDTO> turnosDTO;
    private UsuarioDTO usuarioDTO;
    private List<UsuarioDTO> usuariosDTO;
    
    //DECLARACIÓN DE LOS MÉTODOS SETTERS Y GETTERS DE LAS VARIABLES DE RESPUESTA DECLARADAS DEL DTO Y LOS MENSAJES GENERADOS POR LOS CRUDS
    //QUE SON LOS METODOS PARA LA CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    public AltaEmpleadoDTO getAltaEmpleadoDTO() {
        return altaEmpleadoDTO;
    }
    public void setAltaEmpleadoDTO(AltaEmpleadoDTO altaEmpleadoDTO) {
        this.altaEmpleadoDTO = altaEmpleadoDTO;
    }
    public List<AltaEmpleadoDTO> getAltasEmpleadosDTO() {
        return altasEmpleadosDTO;
    }
    public void setAltasEmpleadosDTO(List<AltaEmpleadoDTO> altasEmpleadosDTO) {
        this.altasEmpleadosDTO = altasEmpleadosDTO;
    }
    public AuditoriaSistemaDTO getAuditoriaSistemaDTO() {
        return auditoriaSistemaDTO;
    }
    public void setAuditoriaSistemaDTO(AuditoriaSistemaDTO auditoriaSistemaDTO) {
        this.auditoriaSistemaDTO = auditoriaSistemaDTO;
    }
    public List<AuditoriaSistemaDTO> getAuditoriasSistemasDTO() {
        return auditoriasSistemasDTO;
    }
    public void setAuditoriasSistemasDTO(List<AuditoriaSistemaDTO> auditoriasSistemasDTO) {
        this.auditoriasSistemasDTO = auditoriasSistemasDTO;
    }
    public BajaEmpleadoDTO getBajaEmpleadoDTO() {
        return bajaEmpleadoDTO;
    }
    public void setBajaEmpleadoDTO(BajaEmpleadoDTO bajaEmpleadoDTO) {
        this.bajaEmpleadoDTO = bajaEmpleadoDTO;
    }
    public List<BajaEmpleadoDTO> getBajasEmpleadosDTO() {
        return bajasEmpleadosDTO;
    }
    public void setBajasEmpleadosDTO(List<BajaEmpleadoDTO> bajasEmpleadosDTO) {
        this.bajasEmpleadosDTO = bajasEmpleadosDTO;
    }
    public boolean isBanderaexito() {
        return banderaexito;
    }
    public void setBanderaexito(boolean banderaexito) {
        this.banderaexito = banderaexito;
    }
    public CiudadMundoDTO getCiudadMundoDTO() {
        return ciudadMundoDTO;
    }
    public void setCiudadMundoDTO(CiudadMundoDTO ciudadMundoDTO) {
        this.ciudadMundoDTO = ciudadMundoDTO;
    }
    public List<CiudadMundoDTO> getCiudadesMundoDTO() {
        return ciudadesMundoDTO;
    }
    public void setCiudadesMundoDTO(List<CiudadMundoDTO> ciudadesMundoDTO) {
        this.ciudadesMundoDTO = ciudadesMundoDTO;
    }
    public DepartamentooEstadoMundoDTO getDepartamentooEstadoMundoDTO() {
        return departamentooEstadoMundoDTO;
    }
    public void setDepartamentooEstadoMundoDTO(DepartamentooEstadoMundoDTO departamentooEstadoMundoDTO) {
        this.departamentooEstadoMundoDTO = departamentooEstadoMundoDTO;
    }
    public List<DepartamentooEstadoMundoDTO> getDepartamentosyEstadosMundoDTO() {
        return departamentosyEstadosMundoDTO;
    }
    public void setDepartamentosyEstadosMundoDTO(List<DepartamentooEstadoMundoDTO> departamentosyEstadosMundoDTO) {
        this.departamentosyEstadosMundoDTO = departamentosyEstadosMundoDTO;
    }
    public ClasificacionEmpleadoPlantaDTO getClasificacionEmpleadoPlantaDTO() {
        return clasificacionEmpleadoPlantaDTO;
    }
    public void setClasificacionEmpleadoPlantaDTO(ClasificacionEmpleadoPlantaDTO clasificacionEmpleadoPlantaDTO) {
        this.clasificacionEmpleadoPlantaDTO = clasificacionEmpleadoPlantaDTO;
    }
    public List<ClasificacionEmpleadoPlantaDTO> getClasificacionesEmpleadosPlantasDTO() {
        return clasificacionesEmpleadosPlantasDTO;
    }
    public void setClasificacionesEmpleadosPlantasDTO(List<ClasificacionEmpleadoPlantaDTO> clasificacionesEmpleadosPlantasDTO) {
        this.clasificacionesEmpleadosPlantasDTO = clasificacionesEmpleadosPlantasDTO;
    }
    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }
    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }
    public List<EmpleadoDTO> getEmpleadosDTO() {
        return empleadosDTO;
    }
    public void setEmpleadosDTO(List<EmpleadoDTO> empleadosDTO) {
        this.empleadosDTO = empleadosDTO;
    }
    public EstablecimientoClienteDTO getEstablecimientoClienteDTO() {
        return establecimientoClienteDTO;
    }
    public void setEstablecimientoClienteDTO(EstablecimientoClienteDTO establecimientoClienteDTO) {
        this.establecimientoClienteDTO = establecimientoClienteDTO;
    }
    public List<EstablecimientoClienteDTO> getEstablecimientosClientesDTO() {
        return establecimientosClientesDTO;
    }
    public void setEstablecimientosClientesDTO(List<EstablecimientoClienteDTO> establecimientosClientesDTO) {
        this.establecimientosClientesDTO = establecimientosClientesDTO;
    }
    public FuncionalidadDTO getFuncionalidadDTO() {
        return funcionalidadDTO;
    }
    public void setFuncionalidadDTO(FuncionalidadDTO funcionalidadDTO) {
        this.funcionalidadDTO = funcionalidadDTO;
    }
    public List<FuncionalidadDTO> getFuncionalidadesDTO() {
        return funcionalidadesDTO;
    }
    public void setFuncionalidadesDTO(List<FuncionalidadDTO> funcionalidadesDTO) {
        this.funcionalidadesDTO = funcionalidadesDTO;
    }
    public HistorialMovimientoEmpleadoDTO getHistorialMovimientoEmpleadoDTO() {
        return historialMovimientoEmpleadoDTO;
    }
    public void setHistorialMovimientoEmpleadoDTO(HistorialMovimientoEmpleadoDTO historialMovimientoEmpleadoDTO) {
        this.historialMovimientoEmpleadoDTO = historialMovimientoEmpleadoDTO;
    }
    public List<HistorialMovimientoEmpleadoDTO> getHistorialesMovimientosEmpleadosDTO() {
        return historialesMovimientosEmpleadosDTO;
    }
    public void setHistorialesMovimientosEmpleadosDTO(List<HistorialMovimientoEmpleadoDTO> historialesMovimientosEmpleadosDTO) {
        this.historialesMovimientosEmpleadosDTO = historialesMovimientosEmpleadosDTO;
    }
    public HistorialNovedadEmpleadoDTO getHistorialNovedadEmpleadoDTO() {
        return historialNovedadEmpleadoDTO;
    }
    public void setHistorialNovedadEmpleadoDTO(HistorialNovedadEmpleadoDTO historialNovedadEmpleadoDTO) {
        this.historialNovedadEmpleadoDTO = historialNovedadEmpleadoDTO;
    }
    public List<HistorialNovedadEmpleadoDTO> getHistorialesNovedadesEmpleadosDTO() {
        return historialesNovedadesEmpleadosDTO;
    }
    public void setHistorialesNovedadesEmpleadosDTO(List<HistorialNovedadEmpleadoDTO> historialesNovedadesEmpleadosDTO) {
        this.historialesNovedadesEmpleadosDTO = historialesNovedadesEmpleadosDTO;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public PaisMundoDTO getPaisMundoDTO() {
        return paisMundoDTO;
    }
    public void setPaisMundoDTO(PaisMundoDTO paisMundoDTO) {
        this.paisMundoDTO = paisMundoDTO;
    }
    public List<PaisMundoDTO> getPaisesMundoDTO() {
        return paisesMundoDTO;
    }
    public void setPaisesMundoDTO(List<PaisMundoDTO> paisesMundoDTO) {
        this.paisesMundoDTO = paisesMundoDTO;
    }
    public ParametrosSistemaDTO getParametrosSistemaDTO() {
        return parametrosSistemaDTO;
    }
    public void setParametrosSistemaDTO(ParametrosSistemaDTO parametrosSistemaDTO) {
        this.parametrosSistemaDTO = parametrosSistemaDTO;
    }
    public List<ParametrosSistemaDTO> getParametrosSistemasDTO() {
        return parametrosSistemasDTO;
    }
    public void setParametrosSistemasDTO(List<ParametrosSistemaDTO> parametrosSistemasDTO) {
        this.parametrosSistemasDTO = parametrosSistemasDTO;
    }
    public ParametrosSistemaRecuperacionContrasenaDTO getParametrosSistemaRecuperacionContrasenaDTO() {
        return parametrosSistemaRecuperacionContrasenaDTO;
    }
    public void setParametrosSistemaRecuperacionContrasenaDTO(ParametrosSistemaRecuperacionContrasenaDTO parametrosSistemaRecuperacionContrasenaDTO) {
        this.parametrosSistemaRecuperacionContrasenaDTO = parametrosSistemaRecuperacionContrasenaDTO;
    }
    public PrivilegyRestriccAccesoUsuarioDTO getPrivilegyRestriccAccesoUsuarioDTO() {
        return privilegyRestriccAccesoUsuarioDTO;
    }
    public void setPrivilegyRestriccAccesoUsuarioDTO(PrivilegyRestriccAccesoUsuarioDTO privilegyRestriccAccesoUsuarioDTO) {
        this.privilegyRestriccAccesoUsuarioDTO = privilegyRestriccAccesoUsuarioDTO;
    }
    public List<PrivilegyRestriccAccesoUsuarioDTO> getPrivilegyRestriccAccesosUsuariosDTO() {
        return privilegyRestriccAccesosUsuariosDTO;
    }
    public void setPrivilegyRestriccAccesosUsuariosDTO(List<PrivilegyRestriccAccesoUsuarioDTO> privilegyRestriccAccesosUsuariosDTO) {
        this.privilegyRestriccAccesosUsuariosDTO = privilegyRestriccAccesosUsuariosDTO;
    }
    public ProgramacionTurnoEmpleadoDTO getProgramacionTurnoEmpleadoDTO() {
        return programacionTurnoEmpleadoDTO;
    }
    public void setProgramacionTurnoEmpleadoDTO(ProgramacionTurnoEmpleadoDTO programacionTurnoEmpleadoDTO) {
        this.programacionTurnoEmpleadoDTO = programacionTurnoEmpleadoDTO;
    }
    public List<ProgramacionTurnoEmpleadoDTO> getProgramacionesTurnosEmpleadosDTO() {
        return programacionesTurnosEmpleadosDTO;
    }
    public void setProgramacionesTurnosEmpleadosDTO(List<ProgramacionTurnoEmpleadoDTO> programacionesTurnosEmpleadosDTO) {
        this.programacionesTurnosEmpleadosDTO = programacionesTurnosEmpleadosDTO;
    }
    public PuestoSedeEstablecimientoClienteDTO getPuestoSedeEstablecimientoClienteDTO() {
        return puestoSedeEstablecimientoClienteDTO;
    }
    public void setPuestoSedeEstablecimientoClienteDTO(PuestoSedeEstablecimientoClienteDTO puestoSedeEstablecimientoClienteDTO) {
        this.puestoSedeEstablecimientoClienteDTO = puestoSedeEstablecimientoClienteDTO;
    }
    public List<PuestoSedeEstablecimientoClienteDTO> getPuestosSedesEstablecimientosClientesDTO() {
        return puestosSedesEstablecimientosClientesDTO;
    }
    public void setPuestosSedesEstablecimientosClientesDTO(List<PuestoSedeEstablecimientoClienteDTO> puestosEstablecimientosClientesDTO) {
        this.puestosSedesEstablecimientosClientesDTO = puestosSedesEstablecimientosClientesDTO;
    }
    public RecuperacionContrasenaAccesoUsuarioDTO getRecuperacionContrasenaAccesoUsuarioDTO() {
        return recuperacionContrasenaAccesoUsuarioDTO;
    }
    public void setRecuperacionContrasenaAccesoUsuarioDTO(RecuperacionContrasenaAccesoUsuarioDTO recuperacionContrasenaAccesoUsuarioDTO) {
        this.recuperacionContrasenaAccesoUsuarioDTO = recuperacionContrasenaAccesoUsuarioDTO;
    }
    public List<RecuperacionContrasenaAccesoUsuarioDTO> getRecuperacionesContrasenasAccesosUsuariosDTO() {
        return recuperacionesContrasenasAccesosUsuariosDTO;
    }
    public void setRecuperacionesContrasenasAccesosUsuariosDTO(List<RecuperacionContrasenaAccesoUsuarioDTO> recuperacionesContrasenasAccesosUsuariosDTO) {
        this.recuperacionesContrasenasAccesosUsuariosDTO = recuperacionesContrasenasAccesosUsuariosDTO;
    }
    public RolDTO getRolDTO() {
        return rolDTO;
    }
    public void setRolDTO(RolDTO rolDTO) {
        this.rolDTO = rolDTO;
    }
    public List<RolDTO> getRolesDTO() {
        return rolesDTO;
    }
    public void setRolesDTO(List<RolDTO> rolesDTO) {
        this.rolesDTO = rolesDTO;
    }
    public SedeEstablecimientoClienteDTO getSedeEstablecimientoClienteDTO() {
        return sedeEstablecimientoClienteDTO;
    }
    public void setSedeEstablecimientoClienteDTO(SedeEstablecimientoClienteDTO sedeEstablecimientoClienteDTO) {
        this.sedeEstablecimientoClienteDTO = sedeEstablecimientoClienteDTO;
    }
    public List<SedeEstablecimientoClienteDTO> getSedesEstablecimientosClientesDTO() {
        return sedesEstablecimientosClientesDTO;
    }
    public void setSedesEstablecimientosClientesDTO(List<SedeEstablecimientoClienteDTO> sedesEstablecimientosClientesDTO) {
        this.sedesEstablecimientosClientesDTO = sedesEstablecimientosClientesDTO;
    }
    public SubclasificacionEmpleadoPlantaDTO getSubclasificacionEmpleadoPlantaDTO() {
        return subclasificacionEmpleadoPlantaDTO;
    }
    public void setSubclasificacionEmpleadoPlantaDTO(SubclasificacionEmpleadoPlantaDTO subclasificacionEmpleadoPlantaDTO) {
        this.subclasificacionEmpleadoPlantaDTO = subclasificacionEmpleadoPlantaDTO;
    }
    public List<SubclasificacionEmpleadoPlantaDTO> getSubclasificacionesEmpleadosPlantasDTO() {
        return subclasificacionesEmpleadosPlantasDTO;
    }
    public void setSubclasificacionesEmpleadosPlantasDTO(List<SubclasificacionEmpleadoPlantaDTO> subclasificacionesEmpleadosPlantasDTO) {
        this.subclasificacionesEmpleadosPlantasDTO = subclasificacionesEmpleadosPlantasDTO;
    }
    public TipoDocumentoIdentificacionDTO getTipoDocumentoIdentificacionDTO() {
        return tipoDocumentoIdentificacionDTO;
    }
    public void setTipoDocumentoIdentificacionDTO(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) {
        this.tipoDocumentoIdentificacionDTO = tipoDocumentoIdentificacionDTO;
    }
    public List<TipoDocumentoIdentificacionDTO> getTiposDocumentosIdentificacionDTO() {
        return tiposDocumentosIdentificacionDTO;
    }
    public void setTiposDocumentosIdentificacionDTO(List<TipoDocumentoIdentificacionDTO> tiposDocumentosIdentificacionDTO) {
        this.tiposDocumentosIdentificacionDTO = tiposDocumentosIdentificacionDTO;
    }
    public TipoEmpleadoDTO getTipoEmpleadoDTO() {
        return tipoEmpleadoDTO;
    }
    public void setTipoEmpleadoDTO(TipoEmpleadoDTO tipoEmpleadoDTO) {
        this.tipoEmpleadoDTO = tipoEmpleadoDTO;
    }
    public List<TipoEmpleadoDTO> getTiposEmpleadosDTO() {
        return tiposEmpleadosDTO;
    }
    public void setTiposEmpleadosDTO(List<TipoEmpleadoDTO> tiposEmpleadosDTO) {
        this.tiposEmpleadosDTO = tiposEmpleadosDTO;
    }
    public TipoEmpleadoPlantaDTO getTipoEmpleadoPlantaDTO() {
        return tipoEmpleadoPlantaDTO;
    }
    public void setTipoEmpleadoPlantaDTO(TipoEmpleadoPlantaDTO tipoEmpleadoPlantaDTO) {
        this.tipoEmpleadoPlantaDTO = tipoEmpleadoPlantaDTO;
    }
    public List<TipoEmpleadoPlantaDTO> getTiposEmpleadosPlantaDTO() {
        return tiposEmpleadosPlantaDTO;
    }
    public void setTiposEmpleadosPlantaDTO(List<TipoEmpleadoPlantaDTO> tiposEmpleadosPlantaDTO) {
        this.tiposEmpleadosPlantaDTO = tiposEmpleadosPlantaDTO;
    }
    public TipoMovimientoDTO getTipoMovimientoDTO() {
        return tipoMovimientoDTO;
    }
    public void setTipoMovimientoDTO(TipoMovimientoDTO tipoMovimientoDTO) {
        this.tipoMovimientoDTO = tipoMovimientoDTO;
    }
    public List<TipoMovimientoDTO> getTiposMovimientosDTO() {
        return tiposMovimientosDTO;
    }
    public void setTiposMovimientosDTO(List<TipoMovimientoDTO> tiposMovimientosDTO) {
        this.tiposMovimientosDTO = tiposMovimientosDTO;
    }
    public TipoNovedadEmpleadoDTO getTipoNovedadEmpleadoDTO() {
        return tipoNovedadEmpleadoDTO;
    }
    public void setTipoNovedadEmpleadoDTO(TipoNovedadEmpleadoDTO tipoNovedadEmpleadoDTO) {
        this.tipoNovedadEmpleadoDTO = tipoNovedadEmpleadoDTO;
    }
    public List<TipoNovedadEmpleadoDTO> getTiposNovedadesEmpleadosDTO() {
        return tiposNovedadesEmpleadosDTO;
    }
    public void setTiposNovedadesEmpleadosDTO(List<TipoNovedadEmpleadoDTO> tiposNovedadesEmpleadosDTO) {
        this.tiposNovedadesEmpleadosDTO = tiposNovedadesEmpleadosDTO;
    }
    public TipoUsuarioDTO getTipoUsuarioDTO() {
        return tipoUsuarioDTO;
    }
    public void setTipoUsuarioDTO(TipoUsuarioDTO tipoUsuarioDTO) {
        this.tipoUsuarioDTO = tipoUsuarioDTO;
    }
    public List<TipoUsuarioDTO> getTiposUsuariosDTO() {
        return tiposUsuariosDTO;
    }
    public void setTiposUsuariosDTO(List<TipoUsuarioDTO> tiposUsuariosDTO) {
        this.tiposUsuariosDTO = tiposUsuariosDTO;
    }
    public TurnoDTO getTurnoDTO() {
        return turnoDTO;
    }
    public void setTurnoDTO(TurnoDTO turnoDTO) {
        this.turnoDTO = turnoDTO;
    }
    public List<TurnoDTO> getTurnosDTO() {
        return turnosDTO;
    }
    public void setTurnosDTO(List<TurnoDTO> turnosDTO) {
        this.turnosDTO = turnosDTO;
    }
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
    public List<UsuarioDTO> getUsuariosDTO() {
        return usuariosDTO;
    }
    public void setUsuariosDTO(List<UsuarioDTO> usuariosDTO) {
        this.usuariosDTO = usuariosDTO;
    }
    public RespuestaDTO() {
    }
    public RespuestaDTO(String mensaje, boolean banderaexito) {
        this.mensaje = mensaje;
        this.banderaexito = banderaexito;
    }
}
