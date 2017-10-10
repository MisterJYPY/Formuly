/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;


import formuly.controler.frontend.FmAlimentsJpaController;
import formuly.model.frontend.mainModel;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.entities.FmRepas;
import formuly.entities.FmRepasAliments;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.modelFoodSelect;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *cette classe instanciable regorge plusieurs methodes statiques
 * utilisable depuis n'importe package.elle est aussi le gestionnaire de connection (le factory)
 * Toutes les connections au serveur postgresql devrait passer par elle pour eviter des bugs au niveau du systeme
 * de fermerture du manager de connection
 * @author Mr_JYPY
 */
public class formulyTools {
    /**
     * cette variable static contient le nom de la persistence present dans le fichier xml persistence unit
     */
    public static String persistenceUnit="fx_formulyPU";
      static EntityManagerFactory   entityManagerFactory=null ;
        static EntityManagerFactory   entityManagerFactoryss =null;
      static   EntityManager entityManger;

  
/**
 * constructeur non parametrer qui cree automtiquement une instance du manager factory au demarage
 */
    public formulyTools() {     
        entityManagerFactory=Persistence.createEntityManagerFactory("fx_formulyPU");
        entityManger=entityManagerFactory.createEntityManager();
    }
     
    /**
     * methode static qui retourne le manager de connection au serveur de donnee
     * @param persistenceName le nom de la persistence unit contenu dans le fichier xml et pouvant etre appelé de depuis la variable static pesisence unit de cette classe
     * @return un obdjet factory de connection
     */
    public static EntityManagerFactory getEm(String persistenceName)
    {
         EntityManagerFactory   entityManagerFactoys ;
           if( entityManagerFactory ==null )
           {
          entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
           }
            else{
           if(!entityManagerFactory .isOpen())
           {
           entityManagerFactory=Persistence.createEntityManagerFactory("fx_formulyPU");
           }
           }
      
        return  entityManagerFactory ;
       
    }
    /**
     * methode renvoyant un obdet factory qui a la particuliarite de ne qu'etre utilisé pour notre base de données
     * @return un EntityManagerFactory
     */
     public static EntityManagerFactory getEm()
    {
           
         if(entityManagerFactory ==null)
         {  
           entityManagerFactory = Persistence.createEntityManagerFactory("fx_formulyPU");          
         }
         else{
           if(!entityManagerFactory .isOpen())
           {
           entityManagerFactory=Persistence.createEntityManagerFactory("fx_formulyPU");
           }
         }
        return entityManagerFactory ;
       
    }
            /**
             * methode qui retourne la derniere entree d'un repas
             * @return un entier qui est en fait l'id du dernier enregistrement
             */
    public static int TrouverDernierIdentifiant_Repas() 
    {
      int id=0;
      
    EntityManagerFactory emf=getEm(persistenceUnit);
      EntityManager em=emf.createEntityManager();
      String sql="SELECT f.id,f.libelle,f.energie,f.lipide,f.glucide,f.protide FROM fm_repas f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas s)";
      Query eqr=em.createNativeQuery(sql,FmRepas.class);
        System.out.println("eqr: "+eqr);
      FmRepas repas=(eqr.getResultList().size()>0)?(FmRepas) eqr.getSingleResult():null;
      if(repas!=null)
         {
        id=repas.getId();
         }
        em.clear();
        emf.close();
        return id;
        
    }
    /**
     * methode static qui retourne la liste de tous les repas etabli 
     * @return une List d'obdjet FmRepas
     */
    public static List<FmRepas> Liste_Repas() 
    {
      List<FmRepas> repas;
      EntityManager em=getEm().createEntityManager();
   //   String sql="SELECT f.id FROM fm_repas f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas s)";
      Query eqr=em.createNamedQuery("FmRepas.findAll");
         repas=eqr.getResultList();
         
        return repas;
    }
    /**
     * methode static qui retourne la liste de tous les pays enregistré
     * @deprecated à limiter l'utilisation
     * @return une List d'obdjet FmAliments qui sont concerné par ces pays
     */
      public static List<FmAliments> listePays()
    {
     List<FmAliments> ls=null;
      EntityManagerFactory emf=getEm(persistenceUnit);
      EntityManager em=emf.createEntityManager();
      String sql="SELECT Distinct(pays) FROM fm_aliments ";
      Query eqr=em.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
       em.clear();
       em.close();
       emf.close();
    return ls;
    }
    /**
     * methode qui permet d'avoir le nombre d'aliment enregistrer dans la base de donnée
     * @return un entier qui est le nombre d'aliment enregistré
     */
       public int NbreAlimentEnregistrer()
    {
        int nbre=0;
     List<FmAliments> ls=null;
  
      String sql="SELECT f.id FROM fm_aliments f";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
    return nbre=ls.size();
    }
       /**
        * methode qui retourne le nombre de repas effectuer 
        * @return un entier
        */
         public int NbreRepasEffectuer()
    {
        int nbre=0;
     List<FmRepas> ls=null;
      String sql="SELECT f.id FROM fm_repas f";
      Query eqr=entityManger.createNativeQuery(sql,FmRepas.class);
      ls=eqr.getResultList();
     
    return nbre=ls.size();
    }
    /**
     * methode qui retourne le nombre d'aliment Interdits
     * @return un entier
     */   
         public  int NbreAlimentInterdit()
    {
        int nbre=0;
     List<FmAlimentsPathologie> ls=null;
      String sql="SELECT f.id FROM fm_aliments_pathologie f";
      Query eqr=entityManger.createNativeQuery(sql,FmAlimentsPathologie.class);
      
      ls=eqr.getResultList();
        
    return nbre=ls.size();
    }
       /**
        * methode qui retourne le nombre de pathologie enregistrée
        * @return un entier
        */  
         public  int NbrePathologie()
    {
        int nbre=0;
     List<FmPathologie> ls=null;
      
      String sql="SELECT f.id FROM fm_pathologie f";
      Query eqr=entityManger.createNativeQuery(sql,FmPathologie.class);
      ls=eqr.getResultList();
       
    return nbre=ls.size();
    }
         /**
          * methode qui retourne le nombre de menu avec aliment Interdit
          * @return 
          */
         public int NbremenuAvecAlimentInterdit()
    {
        int nbre=0;
     List<FmAliments> ls=null;
      String sql="SELECT f.id FROM fm_repas f WHERE f.id IN (SELECT a.repas FROM fm_repas_aliments a WHERE a.aliment IN (SELECT p.aliment FROM fm_aliments_pathologie p))";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
    return nbre=ls.size();
    }
         /**
          * methode qui return le nombre d'aliment ou les valeurs en glucides ,lipide,protide sont toutes nulles ou non enregistré
          * @return un entier
          */
     public int nbreAlimentNonUtilisable()
    {
        int nbre=0;
     List<FmAliments> ls=null;
      String sql="SELECT f.id FROM fm_aliments f Where f.id NOT IN (SELECT v.aliment From fm_retention_nutriments v)";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
        
    return nbre=ls.size();
    }
       /**
        * methode permettant de determiner 
        * le nombre de foods enregistrer donc les quantites en macro nutriment on deja ete enregistre
        * @return 
        */
      public int nbreAlimentUtilisable()
    {
        int nbre=0;
     List<FmAliments> ls=null;
      String sql="SELECT f.id FROM fm_aliments f Where f.id IN (SELECT v.aliment From fm_retention_nutriments v)";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
      
    return nbre=ls.size();
    }
      /**
       * methode qui retourne la liste de tous les aliments
       * @return une liste d'instance FmAliments
       */
      public  List<FmAliments> ListeAlimentUtilisable()
    {
        int nbre=0;
     List<FmAliments> ls=null;
      String sql="SELECT f.id FROM fm_aliments f Where f.id IN (SELECT v.aliment From fm_retention_nutriments v)";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
      
    return ls;
    }
      /**
       * methode qui retourne la liste de tous les menu effectuer jusqu'a ce jour
       * @return un entier
       */
        public  int listeMenuEffecter()
    {
        int nbre=0;
     List<FmAliments> ls=null;
   
      String sql="SELECT f.id FROM fm_repas f";
      Query eqr=entityManger.createNativeQuery(sql,FmRepas.class);
      ls=eqr.getResultList();
     
    return nbre=ls.size();
    }
        /**
         * methode qui retourne le nombre de menu en fonction d'une plage de regime
         * ex: 1500 signifie entre 1000 et 15000 
         *  1000 signifie tous les menus inferieur ou egale à ce regime
         * 2500 signifie entre 1500 et 2500 inclu
         * 3500 signifie entre 2500 et 3500 inclu ...
         * @param regime
         * @return 
         */
    public int NbrerepasFonctionRegime(Double regime)
        {
       int nbre=0;
        List<FmRepas> ls=null;
         
           String sql="";
          if(regime<=1500){
           sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime;
            }
          if(regime<=2000 && regime>1500){
       sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime+" and f.energie>1500";     
            }
           if(regime<=2500 && regime>2000){
       sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime+" and f.energie>2000";     
            }
           if(regime<=3500 && regime>2500){
       sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime+" and f.energie>2500";     
            }
            if(regime>3500 && regime<=5000){
       sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime+" and f.energie>3500";     
            }
            if(regime>5000 && regime<=10000){
       sql="SELECT f.id FROM fm_repas f WHERE f.energie<="+regime+" and f.energie>5000";     
            }
          
      Query eqr=entityManger.createNativeQuery(sql,FmRepas.class);
      ls=eqr.getResultList();
       nbre=ls.size();
        
       return nbre;
       }
    /**
     * methode qui returne la liste des pays
     * @deprecated methode a eviter d'utiliser .une methode encore plus robuste devrait etre utilisé
     * @return un entier
     */
       public static int listePayss()
    {
     List<FmAliments> ls=null;
     int nbre=0;
      EntityManager em=getEm(persistenceUnit).createEntityManager();
      String sql="SELECT Distinct(f.pays) as f.pays,f.id FROM fm_aliments f ";
      Query eqr=em.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
      nbre=ls.size();
      em.close();
    return nbre;
    }
       /**
        * methode static qui retourne la liste des FmAliments
        * @return ue liste d'obdjet FmAliments
        */
    public static List<FmAliments> listeAliment()
    {
    List<FmAliments> ls=null;
    FmAlimentsJpaController fmc=new FmAlimentsJpaController(getEm(persistenceUnit));
    ls=fmc.findFmAlimentsEntities();
    return ls;
    }
    /**
     * methode qui retourne le nombre d'aliment enregistré pour unpays donné
     * @param pays le pays conerné
     * @return 
     */
    public  int AvoirNbreAlimentPays(String pays)
    {
       int nbre=0;
       String sql="SELECT f.id FROM fm_aliments f WHERE f.pays='"+pays+"'";
       
       Query eqr=entityManger.createNativeQuery(sql,FmRepasAliments.class);
       List< FmAliments >aliment=eqr.getResultList();
       nbre=aliment.size();
       
         return nbre;
    }
    /**
     * methode qui retourne le dernier identifiant des aliments repas (tous)
     * utilile pour l'insertion et pour la generaration de code aliment
     * @return 
     */
     public static int TrouverDernierIdentifiant_Repas_aliment() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_repas_aliments f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas_aliments s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRepasAliments.class);
     FmRepasAliments aliment=(eqr.getResultList().size()>0)?(FmRepasAliments) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
       
       return id;
    }
     /**
      * methode qui retourne le dernier identiant des entree des retention en nutriment
      * @return un entier 
      */
      public static int TrouverDernierIdentifiant_RetentionNutriment() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_retention_nutriments f WHERE f.id=(SELECT MAX(s.id) FROM fm_retention_nutriments s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRetentionNutriments.class);
     FmRetentionNutriments aliment=(eqr.getResultList().size()>0)?(FmRetentionNutriments) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
       
       return id;
    }
       /**
      * methode qui retourne le dernier identiant des entree des retention en vitamines
      * @return un entier 
      */
         public static int TrouverDernierIdentifiant_RetentionVitamines() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_retention_vitamines f WHERE f.id=(SELECT MAX(s.id) FROM fm_retention_vitamines s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRetentionVitamines.class);
     FmRetentionVitamines aliment=(eqr.getResultList().size()>0)?(FmRetentionVitamines) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
       
       return id;
    }
     /**
      * methode qui retourne le dernier identiant des entree des retention en mineraux
      * @return un entier 
      */
           public static int TrouverDernierIdentifiant_RetentionMineraux() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_retention_mineraux f WHERE f.id=(SELECT MAX(s.id) FROM fm_retention_mineraux s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRetentionMineraux.class);
     FmRetentionMineraux aliment=(eqr.getResultList().size()>0)?(FmRetentionMineraux) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
       
       return id;
    }
    /**
     * methode qui retourne l'id du derbnier enregistrement de la table fm_aliments
     * @return un identifiant
     */ 
     public static int TrouverDernierIdentifiant_Aliment() 
    {
      int id=0;
      EntityManagerFactory emf=getEm(persistenceUnit);
        EntityManager em=emf.createEntityManager();
     List<FmAliments> listR=FXCollections.observableArrayList();
      String sql="SELECT f.id,f.pays FROM fm_aliments f WHERE f.id=(SELECT MAX(s.id) FROM fm_aliments s)";
      Query eqr=em.createNativeQuery(sql,FmAliments.class);
      FmAliments aliment=(eqr.getResultList().size()>0)?(FmAliments) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
         
       return id;
    }
     /**
     * methode qui retourne l'id du derbnier enregistrement de la table fm_aliments_pathologie
     * @return un entier
     */ 
      public static int TrouverDernierIdentifiant_Aliment_Pathologie() 
    {
      int id=0;
      EntityManagerFactory emf=getEm(persistenceUnit);
        EntityManager em=emf.createEntityManager();
     List<FmAliments> listR=FXCollections.observableArrayList();
      String sql="SELECT f.id,f.aliment FROM fm_aliments_pathologie f WHERE f.id=(SELECT MAX(s.id) FROM fm_aliments_pathologie s)";
      Query eqr=em.createNativeQuery(sql,FmAlimentsPathologie.class);
      FmAlimentsPathologie aliment=(eqr.getResultList().size()>0)?(FmAlimentsPathologie) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        id=aliment.getId();
      }
         
       return id;
    }
      /**
       * methode qui retourne le dernier identifaint des pathologies enregistrées
       * @return un entier
       */
        public static int TrouverDernierIdentifiant_Pathologie() {
       int id=0;
        EntityManagerFactory emf=getEm(persistenceUnit);
        EntityManager em=emf.createEntityManager();
     List<FmPathologie> listR=FXCollections.observableArrayList();
      String sql="SELECT f.id FROM fm_pathologie f WHERE f.id=(SELECT MAX(s.id) FROM fm_pathologie s)";
      Query eqr=em.createNativeQuery(sql,FmPathologie.class);
      FmPathologie aliment=(eqr.getResultList().size()>0)?(FmPathologie) eqr.getSingleResult():null;
      if(aliment!=null)
      {
        
        id=aliment.getId();
      }
        System.out.println("id pathologie: "+id);   
       return id;
    }
        /**
         * methode qui retourne une liste observable de mainModel qui est une classe qui a servit pour 
         * stocker des données dans les TableView
         * @return une liste observable
         */
  public static ObservableList<mainModel> getobservableListMainModel()
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
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
            mainM.setIdAliment(aliment.getId());
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
   /**
         * methode qui retourne une liste observable de mainModel qui est une classe qui a servit pour 
         * stocker des données dans les TableView.cette methode a la particuliarité de donner la liste de tous les 
         * aliments avec leurs differentes retentions 
         * @return une liste observable de mainModel
         */
   public static ObservableList<mainModel> TouteLaListeDesAliments()
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
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
            mainM.setIdAliment(aliment.getId());
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            mainM.setMode_cuisson(aliment.getModeCuisson());
            mainM.setCategorie(aliment.getGroupe().getNomFr());
            mainM.setNomEng(aliment.getNomEng());
            mainM.setPathologie(searchAlimentPathologie(aliment));
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
   /**
    * donne sous forme de chaine de caractere (avec des concatenations) la liste de toutes les 
    * pathologies concernée par ce aliment (interdit alimentaire)
    * @param aliment un obdet FmAliment
    * @return  une chaine de caractere
    */
   public static String searchAlimentPathologie(FmAliments aliment)
   {
     String pat="";
      Query qer=getEm().createEntityManager().createNamedQuery("FmAlimentsPathologie.findByAliment");
        qer.setParameter("aliment",aliment);
       List<FmAlimentsPathologie> list=qer.getResultList();
          for(FmAlimentsPathologie alp:list)
          {
          pat=pat.concat(alp.getPathologie().getLibelle()+" ,\n");
          }
     return pat;
   }
   /**
    * cette methode est en fait une surchage mqui prends un parametre qui en realité n'est pas 
    * utilisé dans le deroulement de l'algorithme utilisé par cette methode
    * @param parametreFictif prennt nimporte quelle valeur
    * @return une liste Observable
    */
    public static ObservableList<mainModel> getobservableListMainModel(int parametreFictif)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
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
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
    /**
     * methode permetant de donner liste d'instance mainModel particuliere
     * @param NomNameQuery le nom de la requete contenu de preference dans sa classe d'origine
     * @param champ le champ de la liste de resulat voulu
     * @param parametre l'obdjet qui servira de parametre pour la requete
     * @deprecated a eviter mais preferé plus la methode getobservableListMainModel non surchargé 
     * @return 
     */
   public static ObservableList<mainModel> getobservableListMainModel(String NomNameQuery,String champ,Object parametre)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(NomNameQuery, champ, parametre);
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
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
   /**
    * methode donnant une liste de mainModel permettant de donner sa propre requete sql 
    * @param requeteNative la requete native
    * @param model l'objet qui manipule les aiments(model)
    * @return  une liste Observable
    */
   public static ObservableList<mainModel> getobservableListMainModel(String requeteNative,modelFoodSelect model)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(requeteNative, model);
      System.out.println(requeteNative);
     System.out.println("nbre pri: "+retentionAliment.size());
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
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
    public static ObservableList<mainModel> getobservableListMainModel(String requeteNative,modelFoodSelect model,String parametreFictif)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(requeteNative, model);
      System.out.println(requeteNative);
     System.out.println("nbre pri: "+retentionAliment.size());
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
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
    /**
     * donne la liste des aliments sous forme d'obdjet (mainModel)
     * @param Pays le pays concerné
     * @return une liste de mainModel
     */
  public static ObservableList<mainModel> getobservableListMainModel(String Pays)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
       mainModel mainM=null;
      List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(Pays);
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
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }
  /**
   * methode qui donne le nombre d'occurence
   * @param chaine
   * @param caractere
   * @return 
   */
  public static  int donnerNombreOccurence(String chaine,char caractere)
  {
      
    int n=0;
    String m=chaine.replace('.','_');
    System.out.println(chaine);
     for(int i=0;i<chaine.length();i++)
     {
     if(chaine.charAt(i)=='_')
     {
     n++;
     }
         
     }
    return n;
  }
  
  public static String preformaterChaine(String m)
  {
  String lesCaracteresNonVoulu = "[(,, ,=,/,),ฐ,+,*,',:, ,้,&,่,--,_,๙,$,^^,\\,\",?,!,็,ฒ,ฃ,จจ,%,ต,{,},#,ง,;,<,>,เ,[a-z,A-Z],à,^,$,é,¨,°,&,ç,è,ù,*,-, ,%,§,<,>,+,?,/,(,),=,²,_,€,|,`,^,},{,[,¤,$,]]";   
     //char a=evt.getKeyChar();
     //notre classe paterne pour compiler la plage de caracterenon esires
  donnerNombreOccurence(m,'.');
     boolean t=false;
      String pal=m;
      pal=pal.replace(',','.');
      pal=pal.replace('.','_');
      System.out.println(pal);
      m=m.replace(',','.');
      String mi=m;
     while(!t){
         if(!m.isEmpty()){
    Pattern regPat = Pattern.compile(lesCaracteresNonVoulu );
 //ici c'est pour voir si  notre pattern comiler peut contenir les caracteres non desires
    Matcher matcher = regPat.matcher(m);
    if (matcher.find()){
       m="0.0"; 
      }else{
       if(pal.split("_").length>2) 
       {
       m="0.0";
       }
       t=true;
    }
    
    
     }
  } 
     return m;
  }
  /**
   * fdait le traitrement d'une chaine de caractere passé en parametre
   * verifie si des caracteres indesirés sont presents dans la chaine
   * @param m
   * @return 
   */
   public static String preformaterChaineAvecEspace(String m)
  {
  String lesCaracteresNonVoulu = "[(,=,/,),ฐ,+,*,',:,้,&,่,--,_,๙,$,^^,\\,\",?,!,็,ฒ,ฃ,จจ,%,ต,{,},#,ง,;,<,>,เ,[a-z,A-Z],à,^,$,é,¨,°,&,ç,è,ù,*,-,%,§,<,>,+,?,/,(,),=,²,_,€,|,`,^,},{,[,¤,$,]]";   
     //char a=evt.getKeyChar();
     //notre classe paterne pour compiler la plage de caracterenon esires
  donnerNombreOccurence(m,'.');
     boolean t=false;
      String pal=m;
      pal=pal.replace(',','.');
      pal=pal.replace('.','_');
      System.out.println(pal);
      m=m.replace(',','.');
      String mi=m;
     while(!t){
         if(!m.isEmpty()){
    Pattern regPat = Pattern.compile(lesCaracteresNonVoulu );
 //ici c'est pour voir si  notre pattern comiler peut contenir les caracteres non desires
    Matcher matcher = regPat.matcher(m);
    if (matcher.find()){
       m="0.0"; 
      }else{
       if(pal.split("_").length>2) 
       {
       m="0.0";
       }
       t=true;
    }
    
    
     }
  } 
     return m;
  }
   /**
    * methode qui permet de mettre les effets sur un tableau de bouton mis en parametre
    * l'effet qu'il met c'est le box shadow
    * @param TabloButton 
    */
   public static void mettreEffetButton(Button[] TabloButton)
     {
          DropShadow shadow = new DropShadow();
          shadow.setColor(Color.MAROON);
//Adding the shadow when the mouse cursor is on
          for(int i=0;i<TabloButton.length;i++)
          {
              Button bt=TabloButton[i];
       bt.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
           bt.setEffect(shadow);
        }
});
      
//Removing the shadow when the mouse cursor is off
     bt.addEventHandler(MouseEvent.MOUSE_EXITED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            bt.setEffect(null);
        }
});
          }
     }
   /**
    * surchage de la methode mettreEffetButton mais cette fois avec precision de la couleurs d'effet
    * @param TabloButton le tableau de bouton
    * @param color la couleur de type Color
    */
    public static void mettreEffetButton(Button[] TabloButton,Color color)
     {
          DropShadow shadow = new DropShadow();
          shadow.setColor(color);
//Adding the shadow when the mouse cursor is on
          for(int i=0;i<TabloButton.length;i++)
          {
              Button bt=TabloButton[i];
       bt.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
           bt.setEffect(shadow);
        }
});
      
//Removing the shadow when the mouse cursor is off
     bt.addEventHandler(MouseEvent.MOUSE_EXITED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            bt.setEffect(null);
        }
});
          }
     }
    /**
     * methode servant a ferme la connection au serveur de base de donnee ici postgresql 9.0
     */
   public void fermerConnection()
   {
     entityManagerFactory.close();
   }
   /**
    * methode permettant d'initialialiser les champ de texte à 0.0
    * @param texfield un tableau de TextFiels
    */
     public static void initialiserLesTextFieldValeur(TextField...texfield)
      {
        for(TextField txf:texfield)
        {
         txf.setText("0.0");
        }
      }
     /**
    * methode permettant d'initialialiser les champ de texte à vide ("")
    * @param texfield un tableau de TextFiels
    */
       public static void initialiserLesTextFieldInfoAliment(TextField...texfield)
      {
        for(TextField txf:texfield)
        {
         txf.setText("");
        }
      }
       /**
        * methode permettant d'initialiser les labels a vide
        * @param label un tableau de taille variable de label
        */
         public static void initialiserLabelInfoAliment(Label...label)
      {
        for(Label lbl:label)
        {
         lbl.setText("");
        }
      }
}
