/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mr_JYPY
 */
@Entity
@Table(name = "fm_regle_fait")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRegleFait.findAll", query = "SELECT f FROM FmRegleFait f"),
    @NamedQuery(name = "FmRegleFait.findById", query = "SELECT f FROM FmRegleFait f WHERE f.id = :id"),
    @NamedQuery(name = "FmRegleFait.findByDerniereModif", query = "SELECT f FROM FmRegleFait f WHERE f.derniereModif = :derniereModif")})
public class FmRegleFait implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereModif;
    @JoinColumn(name = "fait", referencedColumnName = "id")
    @ManyToOne
    private FmFait fait;
    @JoinColumn(name = "regle", referencedColumnName = "id")
    @ManyToOne
    private FmRegle regle;

    public FmRegleFait() {
    }

    public FmRegleFait(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public FmFait getFait() {
        return fait;
    }

    public void setFait(FmFait fait) {
        this.fait = fait;
    }

    public FmRegle getRegle() {
        return regle;
    }

    public void setRegle(FmRegle regle) {
        this.regle = regle;
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
        if (!(object instanceof FmRegleFait)) {
            return false;
        }
        FmRegleFait other = (FmRegleFait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRegleFait[ id=" + id + " ]";
    }
    
}
