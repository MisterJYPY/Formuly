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
@Table(name = "fm_aliments_pathologie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FmAlimentsPathologie.findAll", query = "SELECT f FROM FmAlimentsPathologie f"),
    @NamedQuery(name = "FmAlimentsPathologie.findById", query = "SELECT f FROM FmAlimentsPathologie f WHERE f.id = :id"),
    @NamedQuery(name = "FmAlimentsPathologie.findByType", query = "SELECT f FROM FmAlimentsPathologie f WHERE f.type = :type"),
    @NamedQuery(name = "FmAlimentsPathologie.findByDate", query = "SELECT f FROM FmAlimentsPathologie f WHERE f.date = :date"),
   @NamedQuery(name = "FmAlimentsPathologie.findByAliment", query = "SELECT f FROM FmAlimentsPathologie f WHERE f.aliment = :aliment")})
public class FmAlimentsPathologie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "aliment", referencedColumnName = "id")
    @ManyToOne
    private FmAliments aliment;
    @JoinColumn(name = "pathologie", referencedColumnName = "id")
    @ManyToOne
    private FmPathologie pathologie;

    public FmAlimentsPathologie() {
    }

    public FmAlimentsPathologie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public FmPathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(FmPathologie pathologie) {
        this.pathologie = pathologie;
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
        if (!(object instanceof FmAlimentsPathologie)) {
            return false;
        }
        FmAlimentsPathologie other = (FmAlimentsPathologie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formuly.entities.FmAlimentsPathologie[ id=" + id + " ]";
    }
    
}
