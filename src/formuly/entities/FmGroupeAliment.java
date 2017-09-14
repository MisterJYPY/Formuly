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
@Table(name = "fm_groupe_aliment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmGroupeAliment.findAll", query = "SELECT f FROM FmGroupeAliment f"),
    @NamedQuery(name = "FmGroupeAliment.findById", query = "SELECT f FROM FmGroupeAliment f WHERE f.id = :id"),
    @NamedQuery(name = "FmGroupeAliment.findByNomFr", query = "SELECT f FROM FmGroupeAliment f WHERE f.nomFr = :nomFr"),
    @NamedQuery(name = "FmGroupeAliment.findByNomEng", query = "SELECT f FROM FmGroupeAliment f WHERE f.nomEng = :nomEng"),
    @NamedQuery(name = "FmGroupeAliment.findByDerniereModif", query = "SELECT f FROM FmGroupeAliment f WHERE f.derniereModif = :derniereModif"),
    @NamedQuery(name = "FmGroupeAliment.findByCode", query = "SELECT f FROM FmGroupeAliment f WHERE f.code = :code")})
public class FmGroupeAliment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nom_fr")
    private String nomFr;
    @Column(name = "nom_eng")
    private String nomEng;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereModif;
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "groupe")
    private Collection<FmAliments> fmAlimentsCollection;

    public FmGroupeAliment() {
    }

    public FmGroupeAliment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public String getNomEng() {
        return nomEng;
    }

    public void setNomEng(String nomEng) {
        this.nomEng = nomEng;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<FmAliments> getFmAlimentsCollection() {
        return fmAlimentsCollection;
    }

    public void setFmAlimentsCollection(Collection<FmAliments> fmAlimentsCollection) {
        this.fmAlimentsCollection = fmAlimentsCollection;
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
        if (!(object instanceof FmGroupeAliment)) {
            return false;
        }
        FmGroupeAliment other = (FmGroupeAliment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmGroupeAliment[ id=" + id + " ]";
    }
    
}
