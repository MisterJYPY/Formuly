/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.model.frontend;

import formuly.classe.RetentionAlments;
import formuly.classe.formulyTools;
import formuly.controler.frontend.FmAlimentsJpaController;
import formuly.controler.frontend.FmGroupeAlimentJpaController;
import formuly.entities.FmAliments;
import formuly.entities.FmGroupeAliment;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
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
    
       EntityManagerFactory emf;
    /**
     * methode retournant la liste des modes de cuissons
     * @return 
     */
       
    public ObservableList<String> listeDesGroupesAliments()
    {
          ObservableList<String> grpe = FXCollections.observableArrayList();
          
          List<FmGroupeAliment> retentionAl = null;
          emf=formulyTools.getEm("fx_formulyPU" );
          EntityManager entityM=emf.createEntityManager(); 
          entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery("FmGroupeAliment.findAll");//FmAliments.findByPays
          List<FmGroupeAliment> Alimentsgrp= reqAliment.getResultList();
          Alimentsgrp.stream().forEach((alimentgrp) -> {
              grpe.add(alimentgrp.getNomFr());
        });
          entityM.close();
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
         emf=formulyTools.getEm("fx_formulyPU" );
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
            public List<FmAliments> getAllAlimentByFoods(String sql)
            {
                List<FmAliments> liste=null;
                emf=formulyTools.getEm("fx_formulyPU" );
             FmAlimentsJpaController ct=new FmAlimentsJpaController(emf);
               liste= ct.getAllAlimentByNativeRequete(sql);
                for(FmAliments list:liste )
                {
                    System.out.println("classe: "+list.getClass());
                    System.out.println("classe: "+list);
                }
             return liste;
            }
            public List<Object> getAllAlimentByFoods(String sql,int n)
            {
                List<Object> liste=null;
                emf=formulyTools.getEm("fx_formulyPU" );
             FmAlimentsJpaController ct=new FmAlimentsJpaController(emf);
               liste= ct.getAllAlimentByNativeRequete(sql,1);
                for(Object list:liste )
                {
                    System.out.println("classe: "+list.getClass());
                    System.out.println("classe: "+list);
                }
             return liste;
            }
//     
             /**
              * methode retournant une Instance de Groupe d'aliment
              * en mettant en parametre le nom du groupe(la categorie)
              * cette methode est presente dans la classe RetentionAliment mais avec 
              * les parametre d'envoi differents
              * @param nomGroupeFr nom de la categorie
              * @return une Instance de FmGroupeAiment
              */
             public FmGroupeAliment avoirGroupeAliment(String nomGroupeFr)
             {
               FmGroupeAliment gp=null;
                FmGroupeAliment grpeAl = null;
               emf=formulyTools.getEm("fx_formulyPU" );
              FmGroupeAlimentJpaController ctr=new FmGroupeAlimentJpaController(emf);
             grpeAl =ctr.findFmGroupeAlimentByName(nomGroupeFr);
              emf.close();
            return grpeAl;
             }
     public  ObservableList<mainModel> getobservableListMainModel(String requeteNative,String champ)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(requeteNative,this);
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),String.valueOf(100.0),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  } 
}
