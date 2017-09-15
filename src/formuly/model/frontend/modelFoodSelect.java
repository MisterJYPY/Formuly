/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.classe.formulyTools;
import formuly.controler.frontend.FmGroupeAlimentJpaController;
import formuly.entities.FmGroupeAliment;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Mr_JYPY
 */
public class modelFoodSelect {
    
    
    /**
     * methode retournant la liste des modes de cuissons
     * @return 
     */
    public ObservableList<String> listeDesGroupesAliments()
    {
          ObservableList<String> grpe = FXCollections.observableArrayList();
          
          List<FmGroupeAliment> retentionAl = null;
          EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU" );
          EntityManager entityM=emf.createEntityManager(); 
          entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery("FmGroupeAliment.findAll");//FmAliments.findByPays
          List<FmGroupeAliment> Alimentsgrp= reqAliment.getResultList();
          Alimentsgrp.stream().forEach((alimentgrp) -> {
              grpe.add(alimentgrp.getNomFr());
        });
          return grpe;
    }
    //
     /**
      * methode qui retiurne la liste des modes de cuisson
      * qui est en fait une variable de type enumeration
      * @return 
      */
             public ObservableList<String> listeDesMode_cuisson()
    {
          ObservableList<String> grpe = FXCollections.observableArrayList();
          
          List<FmGroupeAliment> retentionAl = null;
          EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU" );
          EntityManager entityM=emf.createEntityManager(); 
           entityM.getTransaction().begin();
          Query reqAliment =entityM.createNativeQuery("SELECT unnest(enum_range(NULL::fm_md_cuisson))::text as mode_cuisson");
          List<String> modesc= reqAliment.getResultList();
          for(String element :modesc)
          {
              System.out.println("elemnt:"+element);
          if(element!="nd")
               {
              grpe.add(element);
              }
          }
          grpe.removeAll("nd");
          return grpe;
    }
                  
//      
             /**
              * methode retournant une Instance de Groupe d'aliment
              * en mettant en parametre le nom du groupe(la categorie)
              * @param nomGroupeFr nom de la categorie
              * @return une Instance de FmGroupeAiment
              */
             public FmGroupeAliment avoirGroupeAliment(String nomGroupeFr)
             {
               FmGroupeAliment gp=null;
                FmGroupeAliment grpeAl = null;
          EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU" );
             FmGroupeAlimentJpaController ctr=new FmGroupeAlimentJpaController(emf);
             grpeAl =ctr.findFmGroupeAlimentByName(nomGroupeFr);
              emf.close();
            return grpeAl;
             }
    
}
