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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "fm_aliments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmAliments.findAll", query = "SELECT f FROM FmAliments f"), 
    @NamedQuery(name = "FmAliments.findMaxId", query = "SELECT f FROM FmAliments f WHERE f.id=(SELECT MAX(s.id) FROM FmAliments s)"),
    @NamedQuery(name = "FmAliments.findById", query = "SELECT f FROM FmAliments f WHERE f.id = :id"),
    @NamedQuery(name = "FmAliments.findByModeCuisson", query = "SELECT f FROM FmAliments f WHERE f.modeCuisson = :mode_cuisson"),
    @NamedQuery(name = "FmAliments.findAllByGroupe", query = "SELECT f FROM FmAliments f WHERE f.groupe= :groupe"),
    @NamedQuery(name = "FmAliments.findByNomEng", query = "SELECT f FROM FmAliments f WHERE f.nomEng = :nomEng"),
    @NamedQuery(name = "FmAliments.findByNomFr", query = "SELECT f FROM FmAliments f WHERE f.nomFr = :nomFr"),
    @NamedQuery(name = "FmAliments.findByDerniereModif", query = "SELECT f FROM FmAliments f WHERE f.derniereModif = :derniereModif"),
    @NamedQuery(name = "FmAliments.findBySurnom", query = "SELECT f FROM FmAliments f WHERE f.surnom = :surnom"),
    @NamedQuery(name = "FmAliments.findByCode", query = "SELECT f FROM FmAliments f WHERE f.code = :code"),
    @NamedQuery(name = "FmAliments.findByPays", query = "SELECT f FROM FmAliments f WHERE f.pays = :pays")})
public class FmAliments implements Serializable {
    @Lob
    @Column(name = "mode_cuisson")
    private String modeCuisson;
    @OneToMany(mappedBy = "aliment")
    private Collection<FmRepasAliments> fmRepasAlimentsCollection;
    @OneToMany(mappedBy = "aliment")
    private Collection<FmAlimentsPathologie> fmAlimentsPathologieCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_eng")
    private String nomEng;
    @Basic(optional = false)
    @Column(name = "nom_fr")
    private String nomFr;
    @Column(name = "derniere_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereModif;
    @Column(name = "surnom")
    private String surnom;
    @Column(name = "code")
    private String code;
    @Column(name = "pays")
    private String pays;
    @JoinColumn(name = "groupe", referencedColumnName = "id")
    @ManyToOne
    private FmGroupeAliment groupe;
    @OneToMany(mappedBy = "aliment")
    private Collection<FmRetentionMineraux> fmRetentionMinerauxCollection;
    @OneToMany(mappedBy = "aliment")
    private Collection<FmRetentionVitamines> fmRetentionVitaminesCollection;
    @OneToMany(mappedBy = "aliment")
    private Collection<FmRetentionNutriments> fmRetentionNutrimentsCollection;

    public FmAliments() {
    }

    public FmAliments(Integer id) {
        this.id = id;
    }

    public FmAliments(Integer id, String nomEng, String nomFr) {
        this.id = id;
        this.nomEng = nomEng;
        this.nomFr = nomFr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEng() {
        return nomEng;
    }

    public void setNomEng(String nomEng) {
        this.nomEng = nomEng;
    }

    public String getNomFr() {
        return nomFr;
    }

    public void setNomFr(String nomFr) {
        this.nomFr = nomFr;
    }

    public Object getModeCuisson() {
        return modeCuisson;
    }

    public void setModeCuisson(String modeCuisson) {
        this.modeCuisson = modeCuisson;
    }

    public Date getDerniereModif() {
        return derniereModif;
    }

    public void setDerniereModif(Date derniereModif) {
        this.derniereModif = derniereModif;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public FmGroupeAliment getGroupe() {
        return groupe;
    }

    public void setGroupe(FmGroupeAliment groupe) {
        this.groupe = groupe;
    }

    @XmlTransient
    public Collection<FmRetentionMineraux> getFmRetentionMinerauxCollection() {
        return fmRetentionMinerauxCollection;
    }

    public void setFmRetentionMinerauxCollection(Collection<FmRetentionMineraux> fmRetentionMinerauxCollection) {
        this.fmRetentionMinerauxCollection = fmRetentionMinerauxCollection;
    }

    @XmlTransient
    public Collection<FmRetentionVitamines> getFmRetentionVitaminesCollection() {
        return fmRetentionVitaminesCollection;
    }

    public void setFmRetentionVitaminesCollection(Collection<FmRetentionVitamines> fmRetentionVitaminesCollection) {
        this.fmRetentionVitaminesCollection = fmRetentionVitaminesCollection;
    }

    @XmlTransient
    public Collection<FmRetentionNutriments> getFmRetentionNutrimentsCollection() {
        return fmRetentionNutrimentsCollection;
    }

    public void setFmRetentionNutrimentsCollection(Collection<FmRetentionNutriments> fmRetentionNutrimentsCollection) {
        this.fmRetentionNutrimentsCollection = fmRetentionNutrimentsCollection;
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
        if (!(object instanceof FmAliments)) {
            return false;
        }
        FmAliments other = (FmAliments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmAliments[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<FmRepasAliments> getFmRepasAlimentsCollection() {
        return fmRepasAlimentsCollection;
    }

    public void setFmRepasAlimentsCollection(Collection<FmRepasAliments> fmRepasAlimentsCollection) {
        this.fmRepasAlimentsCollection = fmRepasAlimentsCollection;
    }

    @XmlTransient
    public Collection<FmAlimentsPathologie> getFmAlimentsPathologieCollection() {
        return fmAlimentsPathologieCollection;
    }

    public void setFmAlimentsPathologieCollection(Collection<FmAlimentsPathologie> fmAlimentsPathologieCollection) {
        this.fmAlimentsPathologieCollection = fmAlimentsPathologieCollection;
    }
    
}
