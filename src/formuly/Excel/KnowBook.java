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
    
    private String libelleRegle;
    private String libelleClairRegle;
    private int nbreFaitDeclencher;
    
    private String identifiantFait;
    private String fait;
    
    
    
    
      @Override
    public String toString() {
        return String.format("%s - %s - %f",libelleRegle,libelleRegle,libelleRegle);
    }

    public String getLibelleRegle() {
        return libelleRegle;
    }

    public void setLibelleRegle(String libelleRegle) {
        this.libelleRegle = libelleRegle;
    }

    public String getLibelleClairRegle() {
        return libelleClairRegle;
    }

    public void setLibelleClairRegle(String libelleClairRegle) {
        this.libelleClairRegle = libelleClairRegle;
    }

    public int getNbreFaitDeclencher() {
        return nbreFaitDeclencher;
    }

    public void setNbreFaitDeclencher(int nbreFaitDeclencher) {
        this.nbreFaitDeclencher = nbreFaitDeclencher;
    }

    public String getIdentifiantFait() {
        return identifiantFait;
    }

    public void setIdentifiantFait(String identifiantFait) {
        this.identifiantFait = identifiantFait;
    }

    public String getFait() {
        return fait;
    }

    public void setFait(String fait) {
        this.fait = fait;
    }
    
    
}
