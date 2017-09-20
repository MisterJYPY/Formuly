/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.model.frontend.mainModel;

/**
 *
 * @author Mr_JYPY
 */
public class bilanMacroNut {
    
    
    private mainModel model ;
     private String aliment;
        private String pays;
    private double valeurEnergie;
    private String quantites;
    private double valeurGlucide;
    private double valeurProtide;
    private double valeurLipide;
    private double pourcentEnLipide;
    private double pourcentGlucide;
    private double pourcenProtide;
    private double EnergieTotale ;
    private double valeurProtideTotale ;
    private double valeurGlucideTotale ;
    private double valeurLipideTotale ;

    
    public bilanMacroNut(mainModel model) {
        this.model = model;
        quantites=model.getQte();
        pays=model.getPays();
        double quantite =Double.parseDouble(quantites);
        //recuperation des valeur en lipide normale
        double prGl=model.getCloumPcGlucide();
        double prLip=model.getCloumPclipide();
        double prPrt=model.getCloumPcprotide();
        //calcul des valeur 
       valeurGlucide= (quantite*prGl)/100;
       valeurProtide= (quantite*prPrt)/100;
       valeurLipide= (quantite*prLip)/100;
       valeurEnergie=(4*valeurGlucide) +(4*valeurProtide) +(4*valeurLipide) ;
       aliment=model.getNom_aliment();
        //calcul des prcentage
       
    }

    public mainModel getModel() {
        return model;
    }

    public void setModel(mainModel model) {
        this.model = model;
    }

    public double getValeurEnergie() {
        return valeurEnergie;
    }

    public void setValeurEnergie(double valeurEnergie) {
        this.valeurEnergie = valeurEnergie;
    }

    public double getValeurGlucide() {
        return valeurGlucide;
    }

    public void setValeurGlucide(double valeurGlucide) {
        this.valeurGlucide = valeurGlucide;
    }

    public double getValeurProtide() {
        return valeurProtide;
    }

    public void setValeurProtide(double valeurProtide) {
        this.valeurProtide = valeurProtide;
    }

    public double getValeurLipide() {
        return valeurLipide;
    }

    public void setValeurLipide(double valeurLipide) {
        this.valeurLipide = valeurLipide;
    }

    public double getPourcentEnLipide() {
        return pourcentEnLipide;
    }

    public void setPourcentEnLipide(double pourcentEnLipide) {
        this.pourcentEnLipide = pourcentEnLipide;
    }

    public double getPourcentGlucide() {
        return pourcentGlucide;
    }

    public void setPourcentGlucide(double pourcentGlucide) {
        this.pourcentGlucide = pourcentGlucide;
    }

    public double getPourcenProtide() {
        return pourcenProtide;
    }

    public void setPourcenProtide(double pourcenProtide) {
        this.pourcenProtide = pourcenProtide;
    }

    public String getAliment() {
        return aliment;
    }

    public void setAliment(String aliment) {
        this.aliment = aliment;
    }

    public String getQuantites() {
        return quantites;
    }

    public void setQuantites(String quantites) {
        this.quantites = quantites;
    }

    public double getEnergieTotale() {
        return EnergieTotale;
    }

    public void setEnergieTotale(double EnergieTotale) {
        this.EnergieTotale = EnergieTotale;
    }

    public double getValeurProtideTotale() {
        return valeurProtideTotale;
    }

    public void setValeurProtideTotale(double valeurProtideTotale) {
        this.valeurProtideTotale = valeurProtideTotale;
    }

    public double getValeurGlucideTotale() {
        return valeurGlucideTotale;
    }

    public void setValeurGlucideTotale(double valeurGlucideTotale) {
        this.valeurGlucideTotale = valeurGlucideTotale;
    }

    public double getValeurLipideTotale() {
        return valeurLipideTotale;
    }

    public void setValeurLipideTotale(double valeurLipideTotale) {
        this.valeurLipideTotale = valeurLipideTotale;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
    
    
}
