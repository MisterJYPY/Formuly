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
@Table(name = "fm_fait_conclusion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmFaitConclusion.findAll", query = "SELECT f FROM FmFaitConclusion f"),
    @NamedQuery(name = "FmFaitConclusion.findById", query = "SELECT f FROM FmFaitConclusion f WHERE f.id = :id"),
    @NamedQuery(name = "FmFaitConclusion.findByFait", query = "SELECT f FROM FmFaitConclusion f WHERE f.fait = :fait"),
    @NamedQuery(name = "FmFaitConclusion.findByRegle", query = "SELECT f FROM FmFaitConclusion f WHERE f.regle = :regle"),
    @NamedQuery(name = "FmFaitConclusion.findByConclusion", query = "SELECT f FROM FmFaitConclusion f WHERE f.conclusion = :conclusion"),
    @NamedQuery(name = "FmFaitConclusion.findByDerniereModif", query = "SELECT f FROM FmFaitConclusion f WHERE f.derniereModif = :derniereModif"),
    @NamedQuery(name = "FmFaitConclusion.findByPremisse", query = "SELECT f FROM FmFaitConclusion f WHERE f.premisse = :premisse")})
public class FmFaitConclusion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fait")
    private String fait;
    @Column(name = "regle")
    private Integer regle;
    @Column(name = "conclusion")
    private String conclusion;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIME)
    private Date derniereModif;
    @Column(name = "premisse")
    private String premisse;

    public FmFaitConclusion() {
    }

    public FmFaitConclusion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFait() {
        return fait;
    }

    public void setFait(String fait) {
        this.fait = fait;
    }

    public Integer getRegle() {
        return regle;
    }

    public void setRegle(Integer regle) {
        this.regle = regle;
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

    public String getPremisse() {
        return premisse;
    }

    public void setPremisse(String premisse) {
        this.premisse = premisse;
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
        if (!(object instanceof FmFaitConclusion)) {
            return false;
        }
        FmFaitConclusion other = (FmFaitConclusion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmFaitConclusion[ id=" + id + " ]";
    }
    
}
