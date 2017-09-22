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
@Table(name = "fm_pathologie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmPathologie.findAll", query = "SELECT f FROM FmPathologie f"),
    @NamedQuery(name = "FmPathologie.findById", query = "SELECT f FROM FmPathologie f WHERE f.id = :id"),
    @NamedQuery(name = "FmPathologie.findByLibelle", query = "SELECT f FROM FmPathologie f WHERE f.libelle = :libelle"),
    @NamedQuery(name = "FmPathologie.findByDate", query = "SELECT f FROM FmPathologie f WHERE f.date = :date")})
public class FmPathologie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "pathologie")
    private Collection<FmAlimentsPathologie> fmAlimentsPathologieCollection;

    public FmPathologie() {
    }

    public FmPathologie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<FmAlimentsPathologie> getFmAlimentsPathologieCollection() {
        return fmAlimentsPathologieCollection;
    }

    public void setFmAlimentsPathologieCollection(Collection<FmAlimentsPathologie> fmAlimentsPathologieCollection) {
        this.fmAlimentsPathologieCollection = fmAlimentsPathologieCollection;
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
        if (!(object instanceof FmPathologie)) {
            return false;
        }
        FmPathologie other = (FmPathologie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmPathologie[ id=" + id + " ]";
    }
    
}
