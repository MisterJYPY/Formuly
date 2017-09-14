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
@Table(name = "fm_retention_mineraux")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmRetentionMineraux.findAll", query = "SELECT f FROM FmRetentionMineraux f"),
    @NamedQuery(name = "FmRetentionMineraux.findById", query = "SELECT f FROM FmRetentionMineraux f WHERE f.id = :id"),
     @NamedQuery(name = "FmRetentionMineraux.findByAliment", query = "SELECT f FROM FmRetentionMineraux f WHERE f.aliment = :aliment"),
    @NamedQuery(name = "FmRetentionMineraux.findByCa", query = "SELECT f FROM FmRetentionMineraux f WHERE f.ca = :ca"),
    @NamedQuery(name = "FmRetentionMineraux.findByFe", query = "SELECT f FROM FmRetentionMineraux f WHERE f.fe = :fe"),
    @NamedQuery(name = "FmRetentionMineraux.findByMg", query = "SELECT f FROM FmRetentionMineraux f WHERE f.mg = :mg"),
    @NamedQuery(name = "FmRetentionMineraux.findByPhos", query = "SELECT f FROM FmRetentionMineraux f WHERE f.phos = :phos"),
    @NamedQuery(name = "FmRetentionMineraux.findByPota", query = "SELECT f FROM FmRetentionMineraux f WHERE f.pota = :pota"),
    @NamedQuery(name = "FmRetentionMineraux.findByNa", query = "SELECT f FROM FmRetentionMineraux f WHERE f.na = :na"),
    @NamedQuery(name = "FmRetentionMineraux.findByZn", query = "SELECT f FROM FmRetentionMineraux f WHERE f.zn = :zn"),
    @NamedQuery(name = "FmRetentionMineraux.findByCu", query = "SELECT f FROM FmRetentionMineraux f WHERE f.cu = :cu"),
    @NamedQuery(name = "FmRetentionMineraux.findByDescriptionvaleurs", query = "SELECT f FROM FmRetentionMineraux f WHERE f.descriptionvaleurs = :descriptionvaleurs")})
public class FmRetentionMineraux implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ca")
    private Float ca;
    @Column(name = "fe")
    private Float fe;
    @Column(name = "mg")
    private Float mg;
    @Column(name = "phos")
    private Float phos;
    @Column(name = "pota")
    private Float pota;
    @Column(name = "na")
    private Float na;
    @Column(name = "zn")
    private Float zn;
    @Column(name = "cu")
    private Float cu;
    @Column(name = "descriptionvaleurs")
    private String descriptionvaleurs;
    @JoinColumn(name = "aliment", referencedColumnName = "id")
    @ManyToOne
    private FmAliments aliment;

    public FmRetentionMineraux() {
    }

    public FmRetentionMineraux(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCa() {
        return ca;
    }

    public void setCa(Float ca) {
        this.ca = ca;
    }

    public Float getFe() {
        return fe;
    }

    public void setFe(Float fe) {
        this.fe = fe;
    }

    public Float getMg() {
        return mg;
    }

    public void setMg(Float mg) {
        this.mg = mg;
    }

    public Float getPhos() {
        return phos;
    }

    public void setPhos(Float phos) {
        this.phos = phos;
    }

    public Float getPota() {
        return pota;
    }

    public void setPota(Float pota) {
        this.pota = pota;
    }

    public Float getNa() {
        return na;
    }

    public void setNa(Float na) {
        this.na = na;
    }

    public Float getZn() {
        return zn;
    }

    public void setZn(Float zn) {
        this.zn = zn;
    }

    public Float getCu() {
        return cu;
    }

    public void setCu(Float cu) {
        this.cu = cu;
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
        if (!(object instanceof FmRetentionMineraux)) {
            return false;
        }
        FmRetentionMineraux other = (FmRetentionMineraux) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmRetentionMineraux[ id=" + id + " ]";
    }
    
}
