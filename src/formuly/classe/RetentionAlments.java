/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.entities.FmAliments;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.modelFoodSelect;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Mr_JYPY
 */
public class RetentionAlments {
    
    
    
    private FmAliments aliments;
    private FmRetentionMineraux rm;
    private FmRetentionNutriments rn;
    private FmRetentionVitamines rvtm;
    private static RetentionAlments instance=null;

    private  RetentionAlments(FmAliments aliments) {
        this.aliments = aliments;
    }
    
    public static RetentionAlments getRetentionAliment(FmAliments aliments)
    {
          instance =new RetentionAlments(aliments);
           FmRetentionNutriments retentionNutriment=null;
            FmRetentionVitamines retentionVitamine=null;
            FmRetentionMineraux retentionMineraux=null;
//            instance.rm=retentionMineraux ;
//            instance.rn=retentionNutriment;
//            instance.rvtm=retentionVitamine;
//          EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU" );
//          EntityManager emq=emf.createEntityManager();      
//          Query reqs=emq.createNamedQuery("FmRetentionMineraux.findByAliment");
//          Query reqnut=emq.createNamedQuery("FmRetentionNutriments.findByAliment");
//          Query reqvit=emq.createNamedQuery("FmRetentionVitamines.findByAliment");
//          reqs.setParameter("aliment",aliments);
//          reqnut.setParameter("aliment",aliments);
//          reqvit.setParameter("aliment",aliments);
          
         //  if(reqs!=null){
             //  List<Object> min=reqs.getResultList();
              // System.out.println("mine"+min.size());
//               if(min.size()!=0)
//               {
//             instance.rm= retentionMineraux = (FmRetentionMineraux) reqs.getSingleResult();
//               }
//               min=null;
//               min=reqnut.getResultList();
//               // System.out.println("nutr"+min.size());
//            // }
//              if(min.size()!=0){
//           instance.rn=retentionNutriment= (FmRetentionNutriments) reqnut.getSingleResult();
//            }
//              min=null;
//              min=reqvit.getResultList();
//            //   System.out.println("vit"+min.size());
//             if(min.size()!=0){
//         instance.rvtm=retentionVitamine= (FmRetentionVitamines) reqvit.getSingleResult();        
//             }
            List<FmRetentionMineraux> lisM=(List<FmRetentionMineraux>) aliments.getFmRetentionMinerauxCollection();
            List<FmRetentionNutriments> lisN=(List<FmRetentionNutriments>) aliments.getFmRetentionNutrimentsCollection();
            List<FmRetentionVitamines> lisV=(List<FmRetentionVitamines>) aliments.getFmRetentionVitaminesCollection();
            instance.rm=(lisM.size()>0)?lisM.get(0):null;
            instance.rn=(lisN.size()>0)?lisN.get(0):null;
            instance.rvtm=(lisV.size()>0)?lisV.get(0):null;
           //emq.clear();
          
       return instance;
       
    }
   public static List<RetentionAlments> getAllAlimentRetention()
           
   {
         // instance=null;
          List<RetentionAlments> retentionAl = null;
         // EntityManagerFactory emf=formulyTools.getEm();
       EntityManager entityM=formulyTools.getEm().createEntityManager(); 
       //    entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery("FmAliments.findAll");//FmAliments.findByPays
          List<FmAliments> Aliments= reqAliment.getResultList();
          // List<FmAliments> Aliments=formulyTools.ListeAlimentUtilisable();
            int cpt=0;
            retentionAl=new ArrayList<>();
          for(FmAliments aliments: Aliments)
          {
            // System.out.println("aliments: "+aliments.getNomFr());
               RetentionAlments reta= getRetentionAliment(aliments);
                reta.aliments=aliments;
                 retentionAl.add(cpt,reta);
                
                  cpt++;
         //    }
          }   
//            entityM.clear();
//            entityM.close();
           //  entityM.getTransaction().commit();
          //   System.out.println("nbre element: "+retentionAl.size());
           
           Aliments=null;
         return retentionAl;
   }
      public static List<RetentionAlments> getAlimentRetentionForAliment(int idAliment)     
   {
          instance=null;
          List<RetentionAlments> retentionAl =null;
           retentionAl=new LinkedList();
          EntityManagerFactory emf=formulyTools.getEm();
          EntityManager entityM=emf.createEntityManager(); 
          
           entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery("FmAliments.findById");//FmAliments.findByPays
          reqAliment.setParameter("id",idAliment);
           FmAliments  aliments= (FmAliments) reqAliment.getSingleResult();
          // List<FmAliments> Aliments=formulyTools.ListeAlimentUtilisable();
             // System.out.println("aliments: "+aliments.getNomFr());
             RetentionAlments retention=null;
              retention= getRetentionAliment(aliments);
              retentionAl.add(retention);
             entityM.getTransaction().commit();
          //   System.out.println("nbre element: "+retentionAl.size());
          entityM.close();
         return retentionAl;
   }
   /**
    * methode pour recuperer les aliments et toutes leurs retentions en mineraus
    * en fonction d'une requete PLSQL 
    * @param NameQuery le nom de la requete
    * @param champ     le champ a indexer
    * @param parametre  la valeur du champ
    * @return  une list contenenant les aliments
    */
     public static List<RetentionAlments> getAllAlimentRetention(String NameQuery,String champ,Object parametre)
           
   {
          List<RetentionAlments> retentionAl = null;
          EntityManagerFactory emf=formulyTools.getEm();
          EntityManager entityM=emf.createEntityManager(); 
           entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery(NameQuery);//FmAliments.findByPays
                reqAliment.setParameter(champ,parametre);
          List<FmAliments> Aliments= reqAliment.getResultList();
            int cpt=0;
            retentionAl=new LinkedList();
          for(FmAliments aliments: Aliments)
          {
             // System.out.println("aliments: "+aliments.getNomFr());
               RetentionAlments reta= getRetentionAliment(aliments);
               
//             if(reta.rm!=null && reta.rn!=null)
//             {
//                 
                 retentionAl.add(cpt,reta);
                
                  cpt++;
         //    }
          }      
             entityM.getTransaction().commit();
          //   System.out.println("nbre element: "+retentionAl.size());
              entityM.clear();
              entityM.close();
           Aliments=null;
         return retentionAl;
   }
    
    public static List<RetentionAlments> getAllAlimentRetention(String requeteNative,modelFoodSelect model)
           
   {
          instance=null;
          List<RetentionAlments> retentionAl = null;
               // reqAliment.setParameter(champ,parametre);
          List<FmAliments> Aliments= model.getAllAlimentByFoods(requeteNative);
            int cpt=0;
            retentionAl=new LinkedList();
          for(FmAliments aliments: Aliments)
          {
             // System.out.println("aliments: "+aliments.getNomFr());
               RetentionAlments reta= getRetentionAliment(aliments);
               
//             if(reta.rm!=null && reta.rn!=null)
//             {
//                 
                 retentionAl.add(cpt,reta);
                
                  cpt++;
         //    }
          }      
  
           Aliments=null;
         return retentionAl;
   }
     public static List<RetentionAlments> getAllAlimentRetention(String Pays)
           
   {
          instance=null;
          List<RetentionAlments> retentionAl = null;
          EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU" );
          EntityManager entityM=emf.createEntityManager(); 
           entityM.getTransaction().begin();
          Query reqAliment =entityM.createNamedQuery("FmAliments.findByPays");//
          reqAliment.setParameter("pays", Pays);
          List<FmAliments> Aliments= reqAliment.getResultList();
            int cpt=0;
            retentionAl=new LinkedList();
         if(Aliments!=null)
         {
          for(FmAliments aliments: Aliments)
          {
             // System.out.println("aliments: "+aliments.getNomFr());
               RetentionAlments reta= getRetentionAliment(aliments);
               
//             if(reta.rm!=null && reta.rn!=null)
//             {
//                 
                 retentionAl.add(cpt,reta);
                  cpt++;
         //    }
          }   
         }
             entityM.getTransaction().commit();
          //   System.out.println("nbre element: "+retentionAl.size());
          entityM.close();
           Aliments=null;
         return retentionAl;
   }

    public FmRetentionVitamines getRvtm() {
        return rvtm;
    }

    public FmAliments getAliments() {
        return aliments;
    }

    public FmRetentionMineraux getRm() {
        return rm;
    }

    public FmRetentionNutriments getRn() {
        return rn;
    }

    public static RetentionAlments getInstance() {
        return instance;
    }
    
    
    
    
}
