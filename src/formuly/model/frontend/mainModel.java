/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.classe.RetentionAlments;
import java.util.List;

/**
 *
 * @author Mr_JYPY
 */
 

public class mainModel {
       
  private int numero ;
  private String nom_aliment;
  private String qte;
  private Double cloumPcGlucide;
  private Double cloumTtPcGlucide;
  private Double  cloumPclipide;
  private Double  cloumTtPclipide;
  private Double   cloumPcprotide;
  private Double  cloumTtPcprotide;
  private Double   Energie;
  private Double   fer;
  private Double  mg;
  private Double   na;
  private Double  ka;
  private Double vitc;
  private Double  vite;
  private Double  vitb9;
  private Double  vita;

    private List<RetentionAlments> retentionAliments=null;
    
    public mainModel() {
        retentionAliments=RetentionAlments.getAllAlimentRetention();
    }
/**
 * 
 * @param numero
 * @param clumAliment
 * @param qte
 * @param cloumPcGlucide
 * @param cloumTtPcGlucide
 * @param cloumPclipide
 * @param cloumTtPclipide
 * @param cloumPcprotide
 * @param cloumPcTtprotide
 * @param Energie
 * @param fer
 * @param mg
 * @param na
 * @param ka
 * @param vitc
 * @param vite
 * @param vitb9
 * @param vita 
 */
    public mainModel(int numero, String clumAliment, Double cloumPcGlucide, Double cloumTtPcGlucide, Double cloumPclipide, Double cloumTtPclipide, Double  cloumPcprotide, Double  cloumPcTtprotide, Double Energie, Double fer, Double mg, Double na, Double ka, Double vitc, Double vite, Double vitb9, Double vita) {
        this.numero = numero;
        this.nom_aliment = clumAliment;
        this.qte="0";
        this.cloumPcGlucide = cloumPcGlucide;
        this.cloumTtPcGlucide = cloumTtPcGlucide;
        this.cloumPclipide = cloumPclipide;
        this.cloumTtPclipide = cloumTtPclipide;
        this.cloumPcprotide=cloumPcprotide;
         this.cloumTtPcprotide=cloumPcTtprotide;
        this.Energie = Energie;
        this.fer = fer;
        this.mg = mg;
        this.na = na;
        this.ka = ka;
        this.vitc = vitc;
        this.vite = vite;
        this.vitb9 = vitb9;
        this.vita = vita;
       
     //  retentionAliments=RetentionAlments.getAllAlimentRetention();
    }
 public mainModel(int numero, String clumAliment,String qte, Double cloumPcGlucide, Double cloumTtPcGlucide, Double cloumPclipide, Double cloumTtPclipide, Double  cloumPcprotide, Double  cloumPcTtprotide, Double Energie, Double fer, Double mg, Double na, Double ka, Double vitc, Double vite, Double vitb9, Double vita) {
        this.numero = numero;
        this.nom_aliment = clumAliment;
        this.qte=qte;
        this.cloumPcGlucide = cloumPcGlucide;
        this.cloumTtPcGlucide = cloumTtPcGlucide;
        this.cloumPclipide = cloumPclipide;
        this.cloumTtPclipide = cloumTtPclipide;
        this.cloumPcprotide=cloumPcprotide;
         this.cloumTtPcprotide=cloumPcTtprotide;
        this.Energie = Energie;
        this.fer = fer;
        this.mg = mg;
        this.na = na;
        this.ka = ka;
        this.vitc = vitc;
        this.vite = vite;
        this.vitb9 = vitb9;
        this.vita = vita;
       
     //  retentionAliments=RetentionAlments.getAllAlimentRetention();
    }
    public int getNumero() {
        return numero;
    }

    public String getNom_aliment() {
        return nom_aliment;
    }

    public String getQte() {
        return qte;
    }

    public Double getCloumPcGlucide() {
        return cloumPcGlucide;
    }

    public Double getCloumTtPcGlucide() {
        return cloumTtPcGlucide;
    }

    public Double getCloumPclipide() {
        return cloumPclipide;
    }

    public Double getCloumTtPclipide() {
        return cloumTtPclipide;
    }

    public Double getCloumPcprotide() {
        return cloumPcprotide;
    }

    public Double getCloumTtPcprotide() {
        return cloumTtPcprotide;
    }

    public Double getEnergie() {
        return Energie;
    }

    public Double getFer() {
        return fer;
    }

    public Double getMg() {
        return mg;
    }

    public Double getNa() {
        return na;
    }

    public Double getKa() {
        return ka;
    }

    public Double getVitc() {
        return vitc;
    }

    public Double getVite() {
        return vite;
    }

    public Double getVitb9() {
        return vitb9;
    }

    public Double getVita() {
        return vita;
    }

    public List<RetentionAlments> getRetentionAliments() {
        return retentionAliments;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNom_aliment(String nom_aliment) {
        this.nom_aliment = nom_aliment;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public void setCloumPcGlucide(Double cloumPcGlucide) {
        this.cloumPcGlucide = cloumPcGlucide;
    }

    public void setCloumTtPcGlucide(Double cloumTtPcGlucide) {
        this.cloumTtPcGlucide = cloumTtPcGlucide;
    }

    public void setCloumPclipide(Double cloumPclipide) {
        this.cloumPclipide = cloumPclipide;
    }

    public void setCloumTtPclipide(Double cloumTtPclipide) {
        this.cloumTtPclipide = cloumTtPclipide;
    }

    public void setCloumPcprotide(Double cloumPcprotide) {
        this.cloumPcprotide = cloumPcprotide;
    }

    public void setCloumTtPcprotide(Double cloumTtPcprotide) {
        this.cloumTtPcprotide = cloumTtPcprotide;
    }

    public void setEnergie(Double Energie) {
        this.Energie = Energie;
    }

    public void setFer(Double fer) {
        this.fer = fer;
    }

    public void setMg(Double mg) {
        this.mg = mg;
    }

    public void setNa(Double na) {
        this.na = na;
    }

    public void setKa(Double ka) {
        this.ka = ka;
    }

    public void setVitc(Double vitc) {
        this.vitc = vitc;
    }

    public void setVite(Double vite) {
        this.vite = vite;
    }

    public void setVitb9(Double vitb9) {
        this.vitb9 = vitb9;
    }

    public void setVita(Double vita) {
        this.vita = vita;
    }

    public void setRetentionAliments(List<RetentionAlments> retentionAliments) {
        this.retentionAliments = retentionAliments;
    }
  
}
