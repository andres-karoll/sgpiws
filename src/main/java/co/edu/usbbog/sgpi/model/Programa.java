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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 57310
 */
@Entity
@Table(catalog = "sgpi_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findById", query = "SELECT p FROM Programa p WHERE p.id = :id")
    , @NamedQuery(name = "Programa.findByNombre", query = "SELECT p FROM Programa p WHERE p.nombre = :nombre")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @ManyToMany(mappedBy = "programas")
    private List<GrupoInvestigacion> gruposInvestigacion;
    @JoinTable(name = "programas_semilleros", joinColumns = {
        @JoinColumn(name = "programa", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "semillero", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<Semillero> semilleros;
    @JoinColumn(name = "facultad_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Facultad facultadId;
    @JoinColumn(name = "director", referencedColumnName = "cedula", nullable = false)
    @ManyToOne(optional = false)
    private Usuario director;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa")
    private List<Materia> materiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programaId")
    private List<Usuario> usuarioList;

    public Programa() {
    }

    public Programa(Integer id) {
        this.id = id;
    }

    public Programa(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<GrupoInvestigacion> getGruposInvestigacion() {
        return gruposInvestigacion;
    }

    public void setGrupoInvestigacionList(List<GrupoInvestigacion> gruposInvestigacion) {
        this.gruposInvestigacion = gruposInvestigacion;
    }
    public GrupoInvestigacion addGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
		getGruposInvestigacion().add(grupoInvestigacion);
		grupoInvestigacion.addPrograma(this);
		return grupoInvestigacion;
	}

	public GrupoInvestigacion removeGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
		getGruposInvestigacion().remove(grupoInvestigacion);
		grupoInvestigacion.removePrograma(this);
		return grupoInvestigacion;
	}

    @XmlTransient
    public List<Semillero> getSemilleros() {
        return semilleros;
    }

    public void setSemilleros(List<Semillero> semilleroList) {
        this.semilleros = semilleroList;
    }
    public Semillero addSemillero(Semillero semillero) {
    	getSemilleros().add(semillero);
    	semillero.addPrograma(this);
    	return semillero;
    }
    public Semillero removeSemillero(Semillero semillero) {
    	getSemilleros().remove(semillero);
    	semillero.removePrograma(this);
    	return semillero;
    }
    public Facultad getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Facultad facultadId) {
        this.facultadId = facultadId;
    }

    public Usuario getDirector() {
        return director;
    }

    public void setDirector(Usuario director) {
        this.director = director;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.usbbog.sgpi.model.Programa[ id=" + id + " ]";
    }

	

	
    
}
