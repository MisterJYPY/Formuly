/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.classe.RetentionAlments;
import formuly.classe.pathologieModel;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import java.util.List;

/**
 *
 * @author Mr_JYPY
 */
 

public class mainModel {
   
  private int idAliment;  
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
  private String  pays;
  /********** ajout des element **/
  private String mode_cuisson;
  private String Surnom;
  private String nomEng;
  private String Categorie;
  /************* element a entre ******/
  private Double ca;
  private Double phos;
  private Double pota;
  private Double zn;
  private Double cu;
  private Double eau;
  private Double fibre;
  private Double ash;
  private Double vitd;
  private Double vitb1;
  private Double vitb2;
  private Double vitb6;
  private Double vitb12;
  private Double thiamin;
  private Double riboflavin;
  private String pathologie;
  private FmAliments aliment;
  private FmPathologie Fmpathologie;
  private FmAlimentsPathologie alimentPathologie;
  private FmRetentionMineraux retMin;
  private FmRetentionVitamines retVit;
  private FmRetentionNutriments retNu;
  private boolean PathologieAinsere;
  private pathologieModel pathologieModel;
  private int rangPathologieModelDansLaTable;

  

    private List<RetentionAlments> retentionAliments=null;
    
    public mainModel() {
         PathologieAinsere=false;
        retentionAliments=RetentionAlments.getAllAlimentRetention();
    }

    public int getRangPathologieModelDansLaTable() {
        return rangPathologieModelDansLaTable;
    }

    public void setRangPathologieModelDansLaTable(int rangPathologieModelDansLaTable) {
        this.rangPathologieModelDansLaTable = rangPathologieModelDansLaTable;
    }

    public pathologieModel getPathologieModel() {
        return pathologieModel;
    }

    public void setPathologieModel(pathologieModel pathologieModel) {
        this.pathologieModel = pathologieModel;
    }
    
      public boolean isPathologieAinsere() {
        return PathologieAinsere;
    }

    public void setPathologieAinsere(boolean PathologieAinsere) {
        this.PathologieAinsere = PathologieAinsere;
    }
  
  
/**
 * 
 * @param numero
 * @param clumAliment
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
        this.pays="non defini";
       
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
        this.pays="non defini";
     //  retentionAliments=RetentionAlments.getAllAlimentRetention();
    }

    public FmAliments getAliment() {
        return aliment;
    }

    public void setAliment(FmAliments aliment) {
        this.aliment = aliment;
    }

    public FmPathologie getFmpathologie() {
        return Fmpathologie;
    }

    public void setFmpathologie(FmPathologie Fmpathologie) {
        this.Fmpathologie = Fmpathologie;
    }

    public FmAlimentsPathologie getAlimentPathologie() {
        return alimentPathologie;
    }

    public void setAlimentPathologie(FmAlimentsPathologie alimentPathologie) {
        this.alimentPathologie = alimentPathologie;
    }

    public FmRetentionMineraux getRetMin() {
        return retMin;
    }

    public void setRetMin(FmRetentionMineraux retMin) {
        this.retMin = retMin;
    }

    public FmRetentionVitamines getRetVit() {
        return retVit;
    }

    public void setRetVit(FmRetentionVitamines retVit) {
        this.retVit = retVit;
    }

    public FmRetentionNutriments getRetNu() {
        return retNu;
    }

    public void setRetNu(FmRetentionNutriments retNu) {
        this.retNu = retNu;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(int idAliment) {
        this.idAliment = idAliment;
    }

    public String getMode_cuisson() {
        return mode_cuisson;
    }

    public void setMode_cuisson(String mode_cuisson) {
        this.mode_cuisson = mode_cuisson;
    }

    public String getSurnom() {
        return Surnom;
    }

    public void setSurnom(String Surnom) {
        this.Surnom = Surnom;
    }

    public String getNomEng() {
        return nomEng;
    }

    public void setNomEng(String nomEng) {
        this.nomEng = nomEng;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getPhos() {
        return phos;
    }

    public void setPhos(Double phos) {
        this.phos = phos;
    }

    public Double getPota() {
        return pota;
    }

    public void setPota(Double pota) {
        this.pota = pota;
    }

    public Double getZn() {
        return zn;
    }

    public void setZn(Double zn) {
        this.zn = zn;
    }

    public Double getCu() {
        return cu;
    }

    public void setCu(Double cu) {
        this.cu = cu;
    }

    public Double getEau() {
        return eau;
    }

    public void setEau(Double eau) {
        this.eau = eau;
    }

    public Double getFibre() {
        return fibre;
    }

    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    public Double getAsh() {
        return ash;
    }

    public void setAsh(Double ash) {
        this.ash = ash;
    }

    public Double getVitd() {
        return vitd;
    }

    public void setVitd(Double vitd) {
        this.vitd = vitd;
    }

    public Double getVitb1() {
        return vitb1;
    }

    public void setVitb1(Double vitb1) {
        this.vitb1 = vitb1;
    }

    public Double getVitb2() {
        return vitb2;
    }

    public void setVitb2(Double vitb2) {
        this.vitb2 = vitb2;
    }

    public Double getVitb6() {
        return vitb6;
    }

    public void setVitb6(Double vitb6) {
        this.vitb6 = vitb6;
    }

    public Double getVitb12() {
        return vitb12;
    }

    public void setVitb12(Double vitb12) {
        this.vitb12 = vitb12;
    }

    public Double getThiamin() {
        return thiamin;
    }

    public void setThiamin(Double thiamin) {
        this.thiamin = thiamin;
    }

    public Double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Double riboflavin) {
        this.riboflavin = riboflavin;
    }

    public String getPathologie() {
        return pathologie;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }
  
}
