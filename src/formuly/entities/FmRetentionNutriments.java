/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mr_JYPY
 */
@Entity
@Table(name = "fm_retention_nutriments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRetentionNutriments.findAll", query = "SELECT f FROM FmRetentionNutriments f"),
    @NamedQuery(name = "FmRetentionNutriments.findById", query = "SELECT f FROM FmRetentionNutriments f WHERE f.id = :id"),
     @NamedQuery(name = "FmRetentionNutriments.findByAliment", query = "SELECT f FROM FmRetentionNutriments f WHERE f.aliment= :aliment"),
    @NamedQuery(name = "FmRetentionNutriments.findByProtein", query = "SELECT f FROM FmRetentionNutriments f WHERE f.protein = :protein"),
    @NamedQuery(name = "FmRetentionNutriments.findByGlucide", query = "SELECT f FROM FmRetentionNutriments f WHERE f.glucide = :glucide"),
    @NamedQuery(name = "FmRetentionNutriments.findByLipide", query = "SELECT f FROM FmRetentionNutriments f WHERE f.lipide = :lipide"),
    @NamedQuery(name = "FmRetentionNutriments.findByEnergieKcal", query = "SELECT f FROM FmRetentionNutriments f WHERE f.energieKcal = :energieKcal"),
    @NamedQuery(name = "FmRetentionNutriments.findByFibre", query = "SELECT f FROM FmRetentionNutriments f WHERE f.fibre = :fibre"),
    @NamedQuery(name = "FmRetentionNutriments.findByEnergieKj", query = "SELECT f FROM FmRetentionNutriments f WHERE f.energieKj = :energieKj"),
    @NamedQuery(name = "FmRetentionNutriments.findByEau", query = "SELECT f FROM FmRetentionNutriments f WHERE f.eau = :eau"),
    @NamedQuery(name = "FmRetentionNutriments.findByDescriptionvaleurs", query = "SELECT f FROM FmRetentionNutriments f WHERE f.descriptionvaleurs = :descriptionvaleurs"),
    @NamedQuery(name = "FmRetentionNutriments.findByAsh", query = "SELECT f FROM FmRetentionNutriments f WHERE f.ash = :ash")})
public class FmRetentionNutriments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "protein")
    private float protein;
    @Basic(optional = false)
    @Column(name = "glucide")
    private float glucide;
    @Basic(optional = false)
    @Column(name = "lipide")
    private float lipide;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "energie_kcal")
    private Float energieKcal;
    @Column(name = "fibre")
    private Float fibre;
    @Column(name = "energie_kj")
    private Float energieKj;
    @Column(name = "eau")
    private Float eau;
    @Column(name = "descriptionvaleurs")
    private String descriptionvaleurs;
    @Column(name = "ash")
    private Float ash;
    @JoinColumn(name = "aliment", referencedColumnName = "id")
    @ManyToOne
    private FmAliments aliment;

    public FmRetentionNutriments() {
    }

    public FmRetentionNutriments(Integer id) {
        this.id = id;
    }

    public FmRetentionNutriments(Integer id, float protein, float glucide, float lipide) {
        this.id = id;
        this.protein = protein;
        this.glucide = glucide;
        this.lipide = lipide;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getGlucide() {
        return glucide;
    }

    public void setGlucide(float glucide) {
        this.glucide = glucide;
    }

    public float getLipide() {
        return lipide;
    }

    public void setLipide(float lipide) {
        this.lipide = lipide;
    }

    public Float getEnergieKcal() {
        return energieKcal;
    }

    public void setEnergieKcal(Float energieKcal) {
        this.energieKcal = energieKcal;
    }

    public Float getFibre() {
        return fibre;
    }

    public void setFibre(Float fibre) {
        this.fibre = fibre;
    }

    public Float getEnergieKj() {
        return energieKj;
    }

    public void setEnergieKj(Float energieKj) {
        this.energieKj = energieKj;
    }

    public Float getEau() {
        return eau;
    }

    public void setEau(Float eau) {
        this.eau = eau;
    }

    public String getDescriptionvaleurs() {
        return descriptionvaleurs;
    }

    public void setDescriptionvaleurs(String descriptionvaleurs) {
        this.descriptionvaleurs = descriptionvaleurs;
    }

    public Float getAsh() {
        return ash;
    }

    public void setAsh(Float ash) {
        this.ash = ash;
    }

    public FmAliments getAliment() {
        return aliment;
    }

    public void setAliment(FmAliments aliment) {
        this.aliment = aliment;
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
        if (!(object instanceof FmRetentionNutriments)) {
            return false;
        }
        FmRetentionNutriments other = (FmRetentionNutriments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRetentionNutriments[ id=" + id + " ]";
    }
    
}
