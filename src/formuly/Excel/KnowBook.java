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
public class KnowBook {
    
    private String premiereColonne;
    private String secondeColonne;
    private double troisiemeColonne;
    
    private String quatriemeColonne;
    private String fait;
    private Integer nbreFaitDeclencherEntier;

    public KnowBook() {
        this.premiereColonne=null;
        this.secondeColonne=null;
        this.quatriemeColonne=null;
        this.nbreFaitDeclencherEntier=null;
    }
    
    
    
    
      @Override
    public String toString() {
        return String.format("%s - %s - %f",premiereColonne,premiereColonne,premiereColonne);
    }

    public String getPremiereColonne() {
        return premiereColonne;
    }

    public void setPremiereColonne(String premiereColonne) {
        this.premiereColonne = premiereColonne;
    }

    public String getSecondeColonne() {
        return secondeColonne;
    }

    public void setSecondeColonne(String secondeColonne) {
        this.secondeColonne = secondeColonne;
    }

    public double getTroisiemeColonne() {
        return troisiemeColonne;
    }
     public Integer getNbreFaitDeclencherEntier() {
        return nbreFaitDeclencherEntier;
    }

    public void setNbreFaitDeclencherEntier(Integer nbreFaitDeclencherEntier) {
        this.nbreFaitDeclencherEntier = nbreFaitDeclencherEntier;
    }

    public void setTroisiemeColonne(double troisiemeColonne) {
        this.troisiemeColonne = troisiemeColonne;
    }

    public String getQuatriemeColonne() {
        return quatriemeColonne;
    }

    public void setQuatriemeColonne(String quatriemeColonne) {
        this.quatriemeColonne = quatriemeColonne;
    }

    public String getFait() {
        return fait;
    }

    public void setFait(String fait) {
        this.fait = fait;
    }
    
    
}
