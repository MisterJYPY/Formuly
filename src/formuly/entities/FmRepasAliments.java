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
@Table(name = "fm_repas_aliments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRepasAliments.findAll", query = "SELECT f FROM FmRepasAliments f"),
    @NamedQuery(name = "FmRepasAliments.findById", query = "SELECT f FROM FmRepasAliments f WHERE f.id = :id"),
    @NamedQuery(name = "FmRepasAliments.findByQuantite", query = "SELECT f FROM FmRepasAliments f WHERE f.quantite = :quantite"),
    @NamedQuery(name = "FmRepasAliments.findByDate", query = "SELECT f FROM FmRepasAliments f WHERE f.date = :date")})
public class FmRepasAliments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantite")
    private Float quantite;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "aliment", referencedColumnName = "id")
    @ManyToOne
    private FmAliments aliment;
    @JoinColumn(name = "repas", referencedColumnName = "id")
    @ManyToOne
    private FmRepas repas;

    public FmRepasAliments() {
    }

    public FmRepasAliments(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FmAliments getAliment() {
        return aliment;
    }

    public void setAliment(FmAliments aliment) {
        this.aliment = aliment;
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
        if (!(object instanceof FmRepasAliments)) {
            return false;
        }
        FmRepasAliments other = (FmRepasAliments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRepasAliments[ id=" + id + " ]";
    }
    
}
