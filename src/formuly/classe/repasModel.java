/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import java.util.Date;

/**
 *
 * @author Mr_JYPY
 */
public class repasModel{
    
    
   private int numero;
   private int id_repas;
   private String libelle;
   private Float glucide;
   private Float lipide;
   private Float protide;
   private Float Energie;
   private Date date;
    public int getNumero() {
        return numero;
    }

    public int getId_repas() {
        return id_repas;
    }

    public void setId_repas(int id_repas) {
        this.id_repas = id_repas;
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
        return Energie;
    }

    public void setEnergie(Float Energie) {
        this.Energie = Energie;
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
    
    
}
