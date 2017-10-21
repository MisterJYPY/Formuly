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
@Table(name = "fm_repas_analyse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRepasAnalyse.findAll", query = "SELECT f FROM FmRepasAnalyse f"),
    @NamedQuery(name = "FmRepasAnalyse.findById", query = "SELECT f FROM FmRepasAnalyse f WHERE f.id = :id"),
    @NamedQuery(name = "FmRepasAnalyse.findByConclusion", query = "SELECT f FROM FmRepasAnalyse f WHERE f.conclusion = :conclusion"),
    @NamedQuery(name = "FmRepasAnalyse.findByDerniereModif", query = "SELECT f FROM FmRepasAnalyse f WHERE f.derniereModif = :derniereModif")})
public class FmRepasAnalyse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "conclusion")
    private String conclusion;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.DATE)
    private Date derniereModif;
    @JoinColumn(name = "repas", referencedColumnName = "id")
    @ManyToOne
    private FmRepas repas;

    public FmRepasAnalyse() {
    }

    public FmRepasAnalyse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public FmRepas getRepas() {
        return repas;
    }

    public void setRepas(FmRepas repas) {
        this.repas = repas;
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
        if (!(object instanceof FmRepasAnalyse)) {
            return false;
        }
        FmRepasAnalyse other = (FmRepasAnalyse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRepasAnalyse[ id=" + id + " ]";
    }
    
}
