/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.entities.FmRegleFait;
import java.util.List;

/**
 *
 * @author Mr_JYPY
 */
public class regleFaitModel {
    
    private int numero;
    private String fait;
    private String Conclusion;
    private int nombreRegleApplicable;
    private List<FmRegleFait> listRegleFait;

    public regleFaitModel(int numero, String fait, String Conclusion, int nombreRegleApplicable) {
        this.numero = numero;
        this.fait = fait;
        this.Conclusion = Conclusion;
        this.nombreRegleApplicable = nombreRegleApplicable;
    }

    public regleFaitModel(int numero, String fait, String Conclusion, int nombreRegleApplicable, List<FmRegleFait> listRegleFait) {
        this.numero = numero;
        this.fait = fait;
        this.Conclusion = Conclusion;
        this.nombreRegleApplicable = nombreRegleApplicable;
        this.listRegleFait = listRegleFait;
    }

    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFait() {
        return fait;
    }

    public void setFait(String fait) {
        this.fait = fait;
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
