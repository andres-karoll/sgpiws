/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.sgpi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 57310
 */
@Entity
@Table(name = "macro_proyecto", catalog = "sgpi_db", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MacroProyecto.findAll", query = "SELECT m FROM MacroProyecto m")
    , @NamedQuery(name = "MacroProyecto.findById", query = "SELECT m FROM MacroProyecto m WHERE m.id = :id")
    , @NamedQuery(name = "MacroProyecto.findByNombre", query = "SELECT m FROM MacroProyecto m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "MacroProyecto.findByFechaInicio", query = "SELECT m FROM MacroProyecto m WHERE m.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "MacroProyecto.findByFechaFin", query = "SELECT m FROM MacroProyecto m WHERE m.fechaFin = :fechaFin")
    , @NamedQuery(name = "MacroProyecto.findByEstado", query = "SELECT m FROM MacroProyecto m WHERE m.estado = :estado")})
public class MacroProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 2147483647)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "macroProyecto")
    private List<Proyecto> proyectos;

    public MacroProyecto() {
    }

    public MacroProyecto(Integer id) {
        this.id = id;
    }

    public MacroProyecto(Integer id, String nombre, String descripcion, Date fechaInicio, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    public Proyecto addProyecto(Proyecto proyecto) {
    	getProyectos().add(proyecto);
    	proyecto.addProyecto(null);
    	return proyecto;
    }
    public Proyecto removeProyecto(Proyecto proyecto) {
    	getProyectos().remove(proyecto);
    	proyecto.removeProyecto(null);
    	return proyecto;
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
        if (!(object instanceof MacroProyecto)) {
            return false;
        }
        MacroProyecto other = (MacroProyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.usbbog.sgpi.model.MacroProyecto[ id=" + id + " ]";
    }
    
}
