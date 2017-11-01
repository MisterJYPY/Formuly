/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mr_JYPY
 */
@Entity
@Table(name = "fm_fait")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmFait.findAll", query = "SELECT f FROM FmFait f ORDER BY f.lettreFait"),
    @NamedQuery(name = "FmFait.findById", query = "SELECT f FROM FmFait f WHERE f.id = :id"),
    @NamedQuery(name = "FmFait.findByLibelleFait", query = "SELECT f FROM FmFait f WHERE f.libelleFait = :libelleFait"),
    @NamedQuery(name = "FmFait.findByDerniereModif", query = "SELECT f FROM FmFait f WHERE f.derniereModif = :derniereModif"),
    @NamedQuery(name = "FmFait.findByLettreFait", query = "SELECT f FROM FmFait f WHERE f.lettreFait = :lettreFait")})
public class FmFait implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "libelle_fait")
    private String libelleFait;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereModif;
    @Column(name = "lettre_fait")
    private String lettreFait;
    @OneToMany(mappedBy = "fait")
    private Collection<FmRegleFait> fmRegleFaitCollection;

    public FmFait() {
    }

    public FmFait(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleFait() {
        return libelleFait;
    }

    public void setLibelleFait(String libelleFait) {
        this.libelleFait = libelleFait;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getLettreFait() {
        return lettreFait;
    }

    public void setLettreFait(String lettreFait) {
        this.lettreFait = lettreFait;
    }

    @XmlTransient
    public Collection<FmRegleFait> getFmRegleFaitCollection() {
        return fmRegleFaitCollection;
    }

    public void setFmRegleFaitCollection(Collection<FmRegleFait> fmRegleFaitCollection) {
        this.fmRegleFaitCollection = fmRegleFaitCollection;
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
        if (!(object instanceof FmFait)) {
            return false;
        }
        FmFait other = (FmFait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmFait[ id=" + id + " ]";
    }
    
}
