/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.entities.FmFait;
import formuly.entities.FmRegleFait;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mr_JYPY
 */
public class regleFaitModel {
    
    private int numero;
    private String identifiantFait;
    private int idFait;
    private String Conclusion;
    private int nombreRegleApplicable;
    private List<FmRegleFait> listRegleFait;
    private FmFait Fait;
    private Date dateModif;

    public regleFaitModel() {
    }

    
    public regleFaitModel(int numero, String fait, String Conclusion, int nombreRegleApplicable) {
        this.numero = numero;
        this.identifiantFait = fait;
        this.Conclusion = Conclusion;
        this.nombreRegleApplicable = nombreRegleApplicable;
    }

    public regleFaitModel(int numero, String fait, String Conclusion, int nombreRegleApplicable, List<FmRegleFait> listRegleFait) {
        this.numero = numero;
        this.identifiantFait = fait;
        this.Conclusion = Conclusion;
        this.nombreRegleApplicable = nombreRegleApplicable;
        this.listRegleFait = listRegleFait;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public String getIdentifiantFait() {
        return identifiantFait;
    }

    public void setIdentifiantFait(String identifiantFait) {
        this.identifiantFait = identifiantFait;
    }

    public int getIdFait() {
        return idFait;
    }

    public void setIdFait(int idFait) {
        this.idFait = idFait;
    }

    public FmFait getFait() {
        return Fait;
    }

    public void setFait(FmFait Fait) {
        this.Fait = Fait;
    }

    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getlettreFait() {
        return identifiantFait;
    }

    public void setFait(String fait) {
        this.identifiantFait = fait;
    }

    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String Conclusion) {
        this.Conclusion = Conclusion;
    }

    public int getNombreRegleApplicable() {
        return nombreRegleApplicable;
    }

    public void setNombreRegleApplicable(int nombreRegleApplicable) {
        this.nombreRegleApplicable = nombreRegleApplicable;
    }

    public List<FmRegleFait> getListRegleFait() {
        return listRegleFait;
    }

    public void setListRegleFait(List<FmRegleFait> listRegleFait) {
        this.listRegleFait = listRegleFait;
    }
    
    
}
