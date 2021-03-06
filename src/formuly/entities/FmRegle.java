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
@Table(name = "fm_regle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRegle.findAll", query = "SELECT f FROM FmRegle f"),
     @NamedQuery(name = "FmRegle.findAlls", query = "SELECT f FROM FmRegle f ORDER BY f.id"),
    @NamedQuery(name = "FmRegle.findById", query = "SELECT f FROM FmRegle f WHERE f.id = :id"),
    @NamedQuery(name = "FmRegle.findByLibelleRegle", query = "SELECT f FROM FmRegle f WHERE f.libelleRegle = :libelleRegle"),
    @NamedQuery(name = "FmRegle.findByDerniereModif", query = "SELECT f FROM FmRegle f WHERE f.derniereModif = :derniereModif"),
    @NamedQuery(name = "FmRegle.findByLibelleRegleClair", query = "SELECT f FROM FmRegle f WHERE f.libelleRegleClair = :libelleRegleClair"),
    @NamedQuery(name = "FmRegle.findByNbreFaitDeclencher", query = "SELECT f FROM FmRegle f WHERE f.nbreFaitDeclencher = :nbreFaitDeclencher"),
    @NamedQuery(name = "FmRegle.findByConnecteur", query = "SELECT f FROM FmRegle f WHERE f.connecteur = :connecteur")})
public class FmRegle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "libelle_regle")
    private String libelleRegle;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIME)
    private Date derniereModif;
    @Column(name = "libelle_regle_clair")
    private String libelleRegleClair;
    @Column(name = "nbre_fait_declencher")
    private Integer nbreFaitDeclencher;
    @Column(name = "connecteur")
    private String connecteur;

    public FmRegle() {
    }

    public FmRegle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleRegle() {
        return libelleRegle;
    }

    public void setLibelleRegle(String libelleRegle) {
        this.libelleRegle = libelleRegle;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getLibelleRegleClair() {
        return libelleRegleClair;
    }

    public void setLibelleRegleClair(String libelleRegleClair) {
        this.libelleRegleClair = libelleRegleClair;
    }

    public Integer getNbreFaitDeclencher() {
        return nbreFaitDeclencher;
    }

    public void setNbreFaitDeclencher(Integer nbreFaitDeclencher) {
        this.nbreFaitDeclencher = nbreFaitDeclencher;
    }

    public String getConnecteur() {
        return connecteur;
    }

    public void setConnecteur(String connecteur) {
        this.connecteur = connecteur;
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
        if (!(object instanceof FmRegle)) {
            return false;
        }
        FmRegle other = (FmRegle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRegle[ id=" + id + " ]";
    }
    
}
