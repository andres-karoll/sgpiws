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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.minidev.json.JSONObject;

/**
 *
 * @author 57310
 */
@Entity
@Table(catalog = "sgpi_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f")
    , @NamedQuery(name = "Facultad.findById", query = "SELECT f FROM Facultad f WHERE f.id = :id")
    , @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadId")
    private List<Programa> programas;
    @JoinColumn(name = "coor_inv", referencedColumnName = "cedula", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Usuario coorInv;
    @JoinColumn(name = "decano", referencedColumnName = "cedula", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Usuario decano;

    public Facultad() {
    }

    public Facultad(Integer id) {
        this.id = id;
    }

    public Facultad(Integer id, String nombre) {
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
    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programaList) {
        this.programas = programaList;
    }
    public Programa addProgramas(Programa programa) {
    	getProgramas().add(programa);
    	programa.setFacultadId(this);
    	return programa;
    }
    public Programa removeProgramas(Programa programa) {
    	getProgramas().add(programa);
    	programa.setFacultadId(null);
    	return programa;
    }

    public Usuario getCoorInv() {
        return coorInv;
    }

    public void setCoorInv(Usuario coorInv) {
        this.coorInv = coorInv;
    }

    public Usuario getDecano() {
        return decano;
    }

    public void setDecano(Usuario decano) {
        this.decano = decano;
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
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
    public JSONObject toJson() {
    	JSONObject facultadJson=new JSONObject();
    	facultadJson.put("id",this.getId());
    	facultadJson.put("nombre",this.getNombre());
    	facultadJson.put("coor_inv",this.getCoorInv());
    	facultadJson.put("decano",this.getDecano());
    	
    	return facultadJson;
    	
    }
    
}
