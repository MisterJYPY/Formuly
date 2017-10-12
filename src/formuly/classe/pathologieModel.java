/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.model.frontend.mainModel;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mr_JYPY
 */

public class pathologieModel {
    
    private int numero ;
    private String libelle;
    private String description;
    private Date date;
    private int idPathologie;
    private int nbreAliment;
    
    private FmPathologie pathologie;
    private FmAlimentsPathologie alpathologie;
    private ObservableList<mainModel> listeAliments;
   private List<FmAlimentsPathologie> alimentsPathologie;

    public pathologieModel() {
        listeAliments=FXCollections.observableArrayList();
    }

    public int getNbreAliment() {
        return nbreAliment;
    }

    public void setNbreAliment(int nbreAliment) {
        this.nbreAliment = nbreAliment;
    }
    
    public int getNumero() {
        return numero;
    }

    public List<FmAlimentsPathologie> getAlimentsPathologie() {
        return alimentsPathologie;
    }

    public void setAlimentsPathologie(List<FmAlimentsPathologie> alimentsPathologie) {
        this.alimentsPathologie = alimentsPathologie;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdPathologie() {
        return idPathologie;
    }

    public void setIdPathologie(int idPathologie) {
        this.idPathologie = idPathologie;
    }

    public FmPathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(FmPathologie pathologie) {
        this.pathologie = pathologie;
    }

    public FmAlimentsPathologie getAlpathologie() {
        return alpathologie;
    }

    public void setAlpathologie(FmAlimentsPathologie alpathologie) {
        this.alpathologie = alpathologie;
    }

    public ObservableList<mainModel> getListeAliments() {
        return listeAliments;
    }

    public void setListeAliments(ObservableList<mainModel> listeAliments) {
        this.listeAliments = listeAliments;
    }
    
    
}
