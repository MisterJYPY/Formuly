/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.Excel;

/**
 *
 * @author Mr_JYPY
 */
public class FoodBook {
  
    /**
     * les attributs a recuperer dans le fichier excel
     */
    private String nomFr;
    private String nomEng;
    private String surnom;
    private String modeCuisson;
    private String categorie;
    private String pays;
    
    /**
     * les macro nutriments
     */
    private double lipide;
    private double protide;
    private double glucide ;
    private double energie;
    private double ash;
    private double eau;
    private double fibre;
    
    /**
     * les mineraux
     */
    private double ca;
    private double fer;
    private double mg;
    private double phos;
    private double pota;
    private double na;
    private double zn;
    private double cu;
    
    /**
     * les vitamines
     */
    
    private double vita;
    private double vitb1;
    private double vitb2;
    private double vitb6;
    private double vitb12;
    private double vitc;
    private double vitd;
    private double vite;
    private double niacine;
    private double folates;
    private double thiamin;
    private double riboflavin;
     
    public FoodBook() {
    }
 
     @Override
    public String toString() {
        return String.format("%s - %s - %f",nomFr,categorie,modeCuisson);
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

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getModeCuisson() {
        return modeCuisson;
    }

    public void setModeCuisson(String modeCuisson) {
        this.modeCuisson = modeCuisson;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getLipide() {
        return lipide;
    }

    public double getMg() {
        return mg;
    }

    public void setMg(double mg) {
        this.mg = mg;
    }

    public void setLipide(double lipide) {
        this.lipide = lipide;
    }

    public double getProtide() {
        return protide;
    }

    public void setProtide(double protide) {
        this.protide = protide;
    }

    public double getGlucide() {
        return glucide;
    }

    public void setGlucide(double glucide) {
        this.glucide = glucide;
    }

    public double getEnergie() {
        return energie;
    }

    public void setEnergie(double energie) {
        this.energie = energie;
    }

    public double getAsh() {
        return ash;
    }

    public void setAsh(double ash) {
        this.ash = ash;
    }

    public double getEau() {
        return eau;
    }

    public void setEau(double eau) {
        this.eau = eau;
    }

    public double getFibre() {
        return fibre;
    }

    public void setFibre(double fibre) {
        this.fibre = fibre;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getFer() {
        return fer;
    }

    public void setFer(double fer) {
        this.fer = fer;
    }

    public double getPhos() {
        return phos;
    }

    public void setPhos(double phos) {
        this.phos = phos;
    }

    public double getPota() {
        return pota;
    }

    public void setPota(double pota) {
        this.pota = pota;
    }

    public double getNa() {
        return na;
    }

    public void setNa(double na) {
        this.na = na;
    }

    public double getZn() {
        return zn;
    }

    public void setZn(double zn) {
        this.zn = zn;
    }

    public double getCu() {
        return cu;
    }

    public void setCu(double cu) {
        this.cu = cu;
    }

    public double getVita() {
        return vita;
    }

    public void setVita(double vita) {
        this.vita = vita;
    }

    public double getVitb1() {
        return vitb1;
    }

    public void setVitb1(double vitb1) {
        this.vitb1 = vitb1;
    }

    public double getVitb2() {
        return vitb2;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setVitb2(double vitb2) {
        this.vitb2 = vitb2;
    }

    public double getVitb6() {
        return vitb6;
    }

    public void setVitb6(double vitb6) {
        this.vitb6 = vitb6;
    }

    public double getVitb12() {
        return vitb12;
    }

    public void setVitb12(double vitb12) {
        this.vitb12 = vitb12;
    }

    public double getVitc() {
        return vitc;
    }

    public void setVitc(double vitc) {
        this.vitc = vitc;
    }

    public double getVitd() {
        return vitd;
    }

    public void setVitd(double vitd) {
        this.vitd = vitd;
    }

    public double getVite() {
        return vite;
    }

    public void setVite(double vite) {
        this.vite = vite;
    }

    public double getNiacine() {
        return niacine;
    }

    public void setNiacine(double niacine) {
        this.niacine = niacine;
    }

    public double getFolates() {
        return folates;
    }

    public void setFolates(double folates) {
        this.folates = folates;
    }

    public double getThiamin() {
        return thiamin;
    }

    public void setThiamin(double thiamin) {
        this.thiamin = thiamin;
    }

    public double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(double riboflavin) {
        this.riboflavin = riboflavin;
    }

  
 
}
