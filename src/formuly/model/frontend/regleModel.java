/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

/**
 *
 * @author Mr_JYPY
 */
public class regleModel {
    
    private int numRegle;
    private String libelleImplicite;
    private String libelleExplicite;
    private int nombreFaitDeclencher;
    private String action;

    public int getNumRegle() {
        return numRegle;
    }

    public void setNumRegle(int numRegle) {
        this.numRegle = numRegle;
    }

    public String getLibelleImplicite() {
        return libelleImplicite;
    }

    public void setLibelleImplicite(String libelleImplicite) {
        this.libelleImplicite = libelleImplicite;
    }

    public String getLibelleExplicite() {
        return libelleExplicite;
    }

    public void setLibelleExplicite(String libelleExplicite) {
        this.libelleExplicite = libelleExplicite;
    }

    public int getNombreFaitDeclencher() {
        return nombreFaitDeclencher;
    }

    public void setNombreFaitDeclencher(int nombreFaitDeclencher) {
        this.nombreFaitDeclencher = nombreFaitDeclencher;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
}
