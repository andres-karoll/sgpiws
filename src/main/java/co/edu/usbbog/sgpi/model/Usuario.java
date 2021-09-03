/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.sgpi.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.minidev.json.JSONObject;

/**
 *
 * @author 57310
 */
@Entity
@Table(catalog = "sgpi_db", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cod_universitario"})
    , @UniqueConstraint(columnNames = {"correo_est"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.cedula = :cedula")
    , @NamedQuery(name = "Usuario.findByCodUniversitario", query = "SELECT u FROM Usuario u WHERE u.codUniversitario = :codUniversitario")
    , @NamedQuery(name = "Usuario.findByCorreoEst", query = "SELECT u FROM Usuario u WHERE u.correoEst = :correoEst")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByVisiblidad", query = "SELECT u FROM Usuario u WHERE u.visiblidad = :visiblidad")
    , @NamedQuery(name = "Usuario.findByCorreoPersonal", query = "SELECT u FROM Usuario u WHERE u.correoPersonal = :correoPersonal")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String cedula;
    @Basic(optional = false)
    @Column(name = "cod_universitario", nullable = false)
    private int codUniversitario;
    @Basic(optional = false)
    @Column(name = "correo_est", nullable = false, length = 45)
    private String correoEst;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String contrasena;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nombres;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Column(length = 45)
    private String telefono;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String visiblidad;
    @Column(name = "correo_personal", length = 45)
    private String correoPersonal;
    @ManyToMany(mappedBy = "usuarios")
    private List<TipoUsuario> tiposUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directorGrupo")
    private List<GrupoInvestigacion> gruposInvestigacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "director")
    private List<Programa> programas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor")
    private List<Clase> clases;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liderSemillero")
    private List<Semillero> semilleros;
    @JoinColumn(name = "programa_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Programa programaId;
    @JoinColumn(name = "semillero_id", referencedColumnName = "id")
    @ManyToOne
    private Semillero semilleroId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coorInv")
    private List<Facultad> facultadList;//duda
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "decano")
    private List<Facultad> facultadList1;//duda
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private List<Participantes> participantes;

    public Usuario() {
    }

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    public Usuario(String cedula, int codUniversitario, String correoEst, String contrasena, String nombres, String apellidos, String visiblidad) {
        this.cedula = cedula;
        this.codUniversitario = codUniversitario;
        this.correoEst = correoEst;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.visiblidad = visiblidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCodUniversitario() {
        return codUniversitario;
    }

    public void setCodUniversitario(int codUniversitario) {
        this.codUniversitario = codUniversitario;
    }

    public String getCorreoEst() {
        return correoEst;
    }

    public void setCorreoEst(String correoEst) {
        this.correoEst = correoEst;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getVisiblidad() {
        return visiblidad;
    }

    public void setVisiblidad(String visiblidad) {
        this.visiblidad = visiblidad;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    @XmlTransient
    public List<TipoUsuario> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<TipoUsuario> tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }
    public TipoUsuario addTipoUsuario(TipoUsuario tipoUsuario) {
    	getTiposUsuario().add(tipoUsuario);
    	tipoUsuario.addUsuario(this);
    	return tipoUsuario;
    }
    public TipoUsuario removeTipoUsuario(TipoUsuario tipoUsuario) {
    	getTiposUsuario().remove(tipoUsuario);
    	tipoUsuario.removeUsuario(null);
    	return tipoUsuario;
    }
    @XmlTransient
    public List<GrupoInvestigacion> getGruposInvestigacion() {
        return gruposInvestigacion;
    }

    public void setGruposInvestigacion(List<GrupoInvestigacion> grupoInvestigacionList) {
        this.gruposInvestigacion = grupoInvestigacionList;
    }
    public GrupoInvestigacion addGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
    	getGruposInvestigacion().add(grupoInvestigacion);
    	grupoInvestigacion.setDirectorGrupo(this);
    	return grupoInvestigacion;
    }
    public GrupoInvestigacion removeGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
    	getGruposInvestigacion().remove(grupoInvestigacion);
    	grupoInvestigacion.setDirectorGrupo(null);
    	return grupoInvestigacion;
    }
    @XmlTransient
    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programaList) {
        this.programas = programaList;
    }
    public Programa addPrograma(Programa programa) {
    	getProgramas().add(programa);
    	programa.addUsuario(this);
    	return programa;
    } 
    public Programa removePrograma(Programa programa) {
    	getProgramas().remove(programa);
    	programa.removeUsuario(null);
    	return programa;
    } 

    @XmlTransient
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
    public Clase addClase(Clase clase) {
    	getClases().add(clase);
    	clase.setProfesor(this);
    	return clase;
    }
    public Clase removeClase(Clase clase) {
    	getClases().remove(clase);
    	clase.setProfesor(null);
    	return clase;
    }

    @XmlTransient
    public List<Semillero> getSemilleros() {
        return semilleros;
    }

    public void setSemilleros(List<Semillero> semilleroList) {
        this.semilleros = semilleroList;
    }
    public Semillero addsemillero(Semillero semillero) {
    	getSemilleros().add(semillero);
    	semillero.addUsuario(this);
    	return semillero;
    }
    public Semillero removesemillero(Semillero semillero) {
    	getSemilleros().remove(semillero);
    	semillero.removeUsuario(null);
    	return semillero;
    }
    public Programa getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Programa programaId) {
        this.programaId = programaId;
    }

    public Semillero getSemilleroId() {
        return semilleroId;
    }

    public void setSemilleroId(Semillero semilleroId) {
        this.semilleroId = semilleroId;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }
    public Facultad addFacultad(Facultad facultad) {
    	getFacultadList().add(facultad);
    	facultad.setCoorInv(this);
    	return facultad;
    }
    public Facultad removeFacultad(Facultad facultad) {
    	getFacultadList().remove(facultad);
    	facultad.setCoorInv(null);
    	return facultad;
    }
    @XmlTransient
    public List<Facultad> getFacultadList1() {
        return facultadList1;
    }

    public void setFacultadList1(List<Facultad> facultadList1) {
        this.facultadList1 = facultadList1;
    }
    public Facultad addFacultad1(Facultad facultad) {
    	getFacultadList1().add(facultad);
    	facultad.setDecano(this);
    	return facultad;
    }
    public Facultad removeFacultad1(Facultad facultad) {
    	getFacultadList1().remove(facultad);
    	facultad.setDecano(null);
    	return facultad;
    }
    @XmlTransient
    public List<Participantes> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participantes> participantes) {
        this.participantes = participantes;
    }
    public Participantes addParticipante(Participantes participante) {
    	getParticipantes().add(participante);
    	participante.setUsuario(this);
    	return participante;
    }
    public Participantes removeParticipante(Participantes participante) {
    	getParticipantes().add(participante);
    	participante.setUsuario(this);
    	return participante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }
    public JSONObject toJson() {
    	JSONObject usuarioJson=new JSONObject();
    	usuarioJson.put("cedula",this.getCedula());
    	usuarioJson.put("cod_Universitario",this.getCodUniversitario());
    	usuarioJson.put("correo_est",this.getCorreoEst());
    	usuarioJson.put("contrasena",this.getCedula());
    	usuarioJson.put("nombres",this.getNombres());
    	usuarioJson.put("apellidos",this.getApellidos());
    	usuarioJson.put("telefono",this.getTelefono());
    	usuarioJson.put("visbilidad",this.getVisiblidad());
    	usuarioJson.put("correo_personal",this.getCorreoPersonal());
    	return usuarioJson;
    	
    }
    @Override
    public String toString() {
        return "co.edu.usbbog.sgpi.model.Usuario[ cedula=" + cedula + " ]";
    }
    
}
