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
@Table(name = "fm_retention_vitamines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRetentionVitamines.findAll", query = "SELECT f FROM FmRetentionVitamines f"),
    @NamedQuery(name = "FmRetentionVitamines.findById", query = "SELECT f FROM FmRetentionVitamines f WHERE f.id = :id"),
      @NamedQuery(name = "FmRetentionVitamines.findByAliment", query = "SELECT f FROM FmRetentionVitamines f WHERE f.aliment = :aliment"),
    @NamedQuery(name = "FmRetentionVitamines.findByVita", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vita = :vita"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitd", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitd = :vitd"),
    @NamedQuery(name = "FmRetentionVitamines.findByVite", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vite = :vite"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitc", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitc = :vitc"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitb1", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitb1 = :vitb1"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitb2", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitb2 = :vitb2"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitb6", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitb6 = :vitb6"),
    @NamedQuery(name = "FmRetentionVitamines.findByVitb12", query = "SELECT f FROM FmRetentionVitamines f WHERE f.vitb12 = :vitb12"),
    @NamedQuery(name = "FmRetentionVitamines.findByNiacine", query = "SELECT f FROM FmRetentionVitamines f WHERE f.niacine = :niacine"),
    @NamedQuery(name = "FmRetentionVitamines.findByFolates", query = "SELECT f FROM FmRetentionVitamines f WHERE f.folates = :folates"),
    @NamedQuery(name = "FmRetentionVitamines.findByThiamin", query = "SELECT f FROM FmRetentionVitamines f WHERE f.thiamin = :thiamin"),
    @NamedQuery(name = "FmRetentionVitamines.findByRiboflavin", query = "SELECT f FROM FmRetentionVitamines f WHERE f.riboflavin = :riboflavin"),
    @NamedQuery(name = "FmRetentionVitamines.findByDescriptionvaleurs", query = "SELECT f FROM FmRetentionVitamines f WHERE f.descriptionvaleurs = :descriptionvaleurs")})
public class FmRetentionVitamines implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vita")
    private Float vita;
    @Column(name = "vitd")
    private Float vitd;
    @Column(name = "vite")
    private Float vite;
    @Column(name = "vitc")
    private Float vitc;
    @Column(name = "vitb1")
    private Float vitb1;
    @Column(name = "vitb2")
    private Float vitb2;
    @Column(name = "vitb6")
    private Float vitb6;
    @Column(name = "vitb12")
    private Float vitb12;
    @Column(name = "niacine")
    private Float niacine;
    @Column(name = "folates")
    private Float folates;
    @Column(name = "thiamin")
    private Float thiamin;
    @Column(name = "riboflavin")
    private Float riboflavin;
    @Column(name = "descriptionvaleurs")
    private String descriptionvaleurs;
    @JoinColumn(name = "aliment", referencedColumnName = "id")
    @ManyToOne
    private FmAliments aliment;

    public FmRetentionVitamines() {
    }

    public FmRetentionVitamines(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVita() {
        return vita;
    }

    public void setVita(Float vita) {
        this.vita = vita;
    }

    public Float getVitd() {
        return vitd;
    }

    public void setVitd(Float vitd) {
        this.vitd = vitd;
    }

    public Float getVite() {
        return vite;
    }

    public void setVite(Float vite) {
        this.vite = vite;
    }

    public Float getVitc() {
        return vitc;
    }

    public void setVitc(Float vitc) {
        this.vitc = vitc;
    }

    public Float getVitb1() {
        return vitb1;
    }

    public void setVitb1(Float vitb1) {
        this.vitb1 = vitb1;
    }

    public Float getVitb2() {
        return vitb2;
    }

    public void setVitb2(Float vitb2) {
        this.vitb2 = vitb2;
    }

    public Float getVitb6() {
        return vitb6;
    }

    public void setVitb6(Float vitb6) {
        this.vitb6 = vitb6;
    }

    public Float getVitb12() {
        return vitb12;
    }

    public void setVitb12(Float vitb12) {
        this.vitb12 = vitb12;
    }

    public Float getNiacine() {
        return niacine;
    }

    public void setNiacine(Float niacine) {
        this.niacine = niacine;
    }

    public Float getFolates() {
        return folates;
    }

    public void setFolates(Float folates) {
        this.folates = folates;
    }

    public Float getThiamin() {
        return thiamin;
    }

    public void setThiamin(Float thiamin) {
        this.thiamin = thiamin;
    }

    public Float getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Float riboflavin) {
        this.riboflavin = riboflavin;
    }

    public String getDescriptionvaleurs() {
        return descriptionvaleurs;
    }

    public void setDescriptionvaleurs(String descriptionvaleurs) {
        this.descriptionvaleurs = descriptionvaleurs;
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
        if (!(object instanceof FmRetentionVitamines)) {
            return false;
        }
        FmRetentionVitamines other = (FmRetentionVitamines) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRetentionVitamines[ id=" + id + " ]";
    }
    
}
