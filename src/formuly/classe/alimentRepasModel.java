/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

/**
 *classe permettant de servir de model de TableView 
 * elle est principalement utilisée dans la confection d'un menu avec menu existant
 * le tableau affichant la liste des menu l'utilise
 * elle doit absolument respecter la structure d'un bean java
 * @author Mr_JYPY
 */
public class alimentRepasModel {
    
    private int numero;
    private Float glucide ;
    private Float lipide;
    private Float protide;
    private Float energie;
    private String libelle;
    private Float quantite;
    private int id_aliment;

    public int getId_aliment() {
        return id_aliment;
    }

    public void setId_aliment(int id_aliment) {
        this.id_aliment = id_aliment;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Float getGlucide() {
        return glucide;
    }

    public void setGlucide(Float glucide) {
        this.glucide = glucide;
    }

    public Float getLipide() {
        return lipide;
    }

    public void setLipide(Float lipide) {
        this.lipide = lipide;
    }

    public Float getProtide() {
        return protide;
    }

    public void setProtide(Float protide) {
        this.protide = protide;
    }

    public Float getEnergie() {
        return energie;
    }

    public void setEnergie(Float energie) {
        this.energie = energie;
    }
    
    
}
