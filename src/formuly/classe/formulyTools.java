/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.model.frontend.repasModel;
import formuly.model.frontend.pathologieModel;
import formuly.controler.frontend.FmAlimentsJpaController;
import formuly.model.frontend.mainModel;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmFait;
import formuly.entities.FmFaitConclusion;
import formuly.entities.FmPathologie;
import formuly.entities.FmRegle;
import formuly.entities.FmRegleFait;
import formuly.entities.FmRepas;
import formuly.entities.FmRepasAliments;
import formuly.entities.FmRepasAnalyse;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.modelFoodSelect;
import formuly.model.frontend.regleFaitModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
        entityManger=Persistence.createEntityManagerFactory("fx_formulyPU").createEntityManager();
    }
    /**
     * methode static qui retourne le manager de connection au serveur de donnee
     * @param persistenceName le nom de la persistence unit contenu dans le fichier xml et pouvant etre appelé de depuis la variable static pesisence unit de cette classe
     * @return un obdjet factory de connection
     */
    public static EntityManagerFactory getEm(String persistenceName)
        {
           if( entityManagerFactory ==null)
           {
               entityManagerFactory = Persistence.createEntityManagerFactory(persistenceName);
           }
       else {
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
             * methode qui retourne la derniere entree d'un faitConcl
             * @return un entier qui est en fait l'id du dernier enregistrement
             */
    public static int TrouverDernierIdentifiant_Repas() 
    {
      int id=0;
      
    EntityManagerFactory emf=getEm(persistenceUnit);
      EntityManager em=emf.createEntityManager();
      String sql="SELECT f.id,f.libelle,f.energie,f.lipide,f.glucide,f.protide FROM fm_repas f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas s)";
      Query eqr=em.createNativeQuery(sql,FmRepas.class);
      //  System.out.println("eqr: "+eqr);
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
     * methode static qui retourne la liste de tous les faitConcl etabli 
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
      public static List<FmFaitConclusion> Liste_FaitConclusion() 
    {
      List<FmFaitConclusion> faitConcl;
      EntityManager em=getEm().createEntityManager();
   //   String sql="SELECT f.id FROM fm_repas f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas s)";
      Query eqr=em.createNamedQuery("FmFaitConclusion.findAll");
         faitConcl=eqr.getResultList();
         
        return faitConcl;
    }
        public static List<FmFait> Liste_Fait() 
    {
      List<FmFait> faitConcl;
      EntityManager em=getEm().createEntityManager();
      Query eqr=em.createNamedQuery("FmFait.findAlls");
         faitConcl=eqr.getResultList();
      //   em.getTransaction().commit();
          em.clear();
        return faitConcl;
    }
          public static List<FmRegle> Liste_Regle() 
    {
      List<FmRegle> faitConcl;
      EntityManager em=getEm().createEntityManager();
      Query eqr=em.createNamedQuery("FmRegle.findAlls");
         faitConcl=eqr.getResultList();
      //   em.getTransaction().commit();
          em.clear();
        return faitConcl;
    }
              public static List<FmRegleFait> Liste_RegleFait() 
    {
      List<FmRegleFait> faitConcl;
      EntityManager em=getEm().createEntityManager();
      Query eqr=em.createNamedQuery("FmRegleFait.findAll");
         faitConcl=eqr.getResultList();
      //   em.getTransaction().commit();
          em.clear();
        return faitConcl;
    }
        public static ArrayList<String> RecupererElementFichierFt(File files)
    {
       ArrayList<String> list=new ArrayList();
    try{
       String ligne;
    //chemins=this.takeTheFile();
       BufferedReader buffer=new BufferedReader(new FileReader(files));
       LineNumberReader numeroLigne = new LineNumberReader(buffer);
                 int i =0;
       while((ligne=buffer.readLine())!=null )
       {     
         //System.out.println(ligne);
       list.add(ligne);
       numeroLigne.getLineNumber();
            //}
        }
       buffer.close();
        }catch(IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
     return list;
    }
        public static void rendreCelluleEditable(TableView<mainModel> table ,TableColumn<mainModel,String> colonne )
      {
      table.setEditable(true);
        colonne.setCellFactory(TextFieldTableCell.forTableColumn());
             colonne.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<mainModel, String> t) {
                    String qte=formulyTools.preformaterChaine(t.getNewValue());
                     int ligne= t.getTablePosition().getRow();
                     double qt=Double.valueOf(qte);
                            mainModel md= table.getItems().get(ligne);
                            
               if(qt>0){
        
               table.getItems().set(ligne, md);
                   //  t.getTableView().getItems().get(ligne).setQte(qte);
                     ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setPrixUnitaire(qte);
                }
                else{
       //alert pour dire la valeur est nulle nous allons la supprimer ?  
                   Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error prix ");
            alert.setHeaderText("la valeur saisie n'est pas valide: "+t.getNewValue()+" \n");
            alert.setContentText("Nom aliment: "+md.getNom_aliment()+" encienne valeur :"+qte+"\n"
                    + "Nous avons ignor2 votre saisie et concervé la precedente \n"
                    + " Veuillez reessayer avec un nombre valide de prix SVP \n"
                    + " NB: Tous les Prix entrés doivent etre strictement superieurs a 0 FCfa");
            alert.show();
              table.getItems().set(ligne, md);
                    // t.getTableView().getItems().get(ligne).setQte(qte);
                     ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setPrixUnitaire(md.getPrixUnitaire());
               }
      }
            });
      }
           public static ObservableList<mainModel> retourneListParCritere(String chaineArechercher,ObservableList<mainModel> liste)
     {
       ObservableList<mainModel> listTri=FXCollections.observableArrayList();
       String info="";
        for(mainModel ligne:liste)
      {
         Pattern p = Pattern.compile(chaineArechercher, Pattern.CASE_INSENSITIVE);
         info=info.concat(ligne.getNom_aliment()).concat(" "+ligne.getCategorie()).concat(" "+ligne.getPays()).concat(" "+ligne.getSurnom()).concat(" "+ligne.getMode_cuisson());
          Matcher m = p.matcher(info);
          
        if(m.find())
        {
            listTri.add(ligne);
        }
        info="";
      }   
       return listTri;
     }
      public static List<String> listRegles()
      {
        // List<String> myList = Files.lines(Paths.get("/formuly/expert/regle.txt")).collect(Collectors.toList());
          List<String> list=new ArrayList<>();
      try{
         EntityManager em=getEm().createEntityManager();
          em.getTransaction().begin();
          String nameQuery="FmRegle.findAll";
          Query query =em.createNamedQuery(nameQuery);
          List<FmRegle> lists = query.getResultList();
          for(FmRegle elmt :lists)
          {
          list.add(elmt.getLibelleRegle());
          }
//         String parent=System.getProperty("user.dir");
//          File fichier=new File(parent+"/regle.txt");
//          list= RecupererElementFichierFt(fichier);
          em.getTransaction().commit();
      }
      catch(Exception e)
      {
          System.out.println("execption rencontre : "+e.getLocalizedMessage());
      }
          return list;
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
  
      String sql="SELECT f.id,f.nom_eng,f.nom_fr,f.code,f.pays,f.surnom FROM fm_aliments f";
      Query eqr=entityManger.createNativeQuery(sql,FmAliments.class);
      ls=eqr.getResultList();
       // entityManger.clear();
       // entityManger.close();
    
    return nbre=ls.size();
    }
          public static int NbreFaitsEnregistrer()
    {
        int nbre=0;
     List<FmAliments> ls=null;
  
      String sql="SELECT f.id,f.libelle_fait,f.lettre_fait FROM fm_fait f";
       EntityManager em=getEm().createEntityManager();
      Query eqr=em.createNativeQuery(sql,FmFait.class);
      ls=eqr.getResultList();
       // entityManger.clear();
       // entityManger.close();
        em.clear();
    
    return nbre=ls.size();
    }
        public static int NbreAlimentEnregistrer(int n)
    {
        int nbre=0;
     List<FmAliments> ls=null;
  
      String sql="FmAliments.findAll";
      EntityManager em=getEm().createEntityManager();
      Query eqr=em.createNamedQuery(sql);
      ls=eqr.getResultList();
      em.close();
    return nbre=ls.size();
    }
       /**
        * methode qui retourne le nombre de faitConcl effectuer 
        * @return un entier
        */
         public static int NbreRepasEffectuer()
    {
        int nbre=0;
      List<FmRepas> ls=null;
       EntityManager em=getEm().createEntityManager();
         em.getTransaction().begin();
        String sql="FmRepas.findAll";
//       String sql="SELECT f.id,f.libelle,f.energie,f.lipide,f.glucide,f.protide FROM fm_repas f ";
//      Query eqr=em.createNativeQuery(sql,FmRepas.class);
      Query eqr=em.createNamedQuery(sql);
      ls=eqr.getResultList();
      em.clear();
         em.getTransaction().commit();
    return nbre=ls.size();
    }
    /**
     * methode qui retourne le nombre d'aliment Interdits
     * @return un entier
     */   
         public  static int NbreAlimentInterdit()
    {
        int nbre=0;
      EntityManager em=getEm().createEntityManager();
      em.getTransaction().begin();
     List<FmAlimentsPathologie> ls=null;
      String sql="SELECT f.id FROM fm_aliments_pathologie f";
      Query eqr=em.createNativeQuery(sql,FmAlimentsPathologie.class);
      
      ls=eqr.getResultList();
         em.getTransaction().commit();
    return nbre=ls.size();
    }
       /**
        * methode qui retourne le nombre de pathologie enregistrée
        * @return un entier
        */  
         public static int NbrePathologie()
    {
        int nbre=0;
     List<FmPathologie> ls=null;
       EntityManager em=getEm().createEntityManager();
      String sql="FmPathologie.findAll";
      Query eqr=em.createNamedQuery(sql);
      ls=eqr.getResultList();
       
    return nbre=ls.size();
    }
         /**
          * methode qui retourne le nombre de menu avec aliment Interdit
          * @return 
          */
         public static int NbremenuAvecAlimentInterdit()
    {
        int nbre=0;
         EntityManager em=getEm().createEntityManager();
      em.getTransaction().begin();
     List<FmAliments> ls=null;
      String sql="SELECT f.id FROM fm_repas f WHERE f.id IN (SELECT a.repas FROM fm_repas_aliments a WHERE a.aliment IN (SELECT p.aliment FROM fm_aliments_pathologie p))";
      Query eqr=em.createNativeQuery(sql,FmRepas.class);
      ls=eqr.getResultList();
      em.getTransaction().commit();
    return nbre=ls.size();
    }
         /**
          * methode qui return le nombre d'aliment ou les valeurs en glucides ,lipide,protide sont toutes nulles ou non enregistré
          * @return un entier
          */
     public static int nbreAlimentNonUtilisable()
    {
        int nbre=0;
     List<FmAliments> ls=null;
     EntityManager em=getEm().createEntityManager();
      String sql="SELECT f.id FROM fm_aliments f Where f.id NOT IN (SELECT v.aliment From fm_retention_nutriments v)";
      Query eqr=em.createNativeQuery(sql,FmAliments.class);
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
    public static int NbrerepasFonctionRegime(Double regime)
        {
       int nbre=0;
        List<FmRepas> ls=null;
          EntityManager em=getEm().createEntityManager();
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
          
      Query eqr=em.createNativeQuery(sql,FmRepas.class);
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
    public  static int AvoirNbreAlimentPays(String pays)
    {
       int nbre=0;
       String sql="SELECT f.id FROM fm_aliments f WHERE f.pays='"+pays+"'";
       EntityManager em=getEm().createEntityManager();
       Query eqr=em.createNativeQuery(sql,FmRepasAliments.class);
       List< FmAliments >aliment=eqr.getResultList();
       nbre=aliment.size();
       
         return nbre;
    }
    /**
     * methode qui retourne le dernier identifiant des aliments faitConcl (tous)
 utilile pour l'insertion et pour la generaration de code aliment
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
      public static int TrouverDernierIdentifiant_Repas_analyse() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_repas_analyse f WHERE f.id=(SELECT MAX(s.id) FROM fm_repas_analyse s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRepasAnalyse.class);
       FmRepasAnalyse aliment=(eqr.getResultList().size()>0)?(FmRepasAnalyse) eqr.getSingleResult():null;
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
      * methode qui retourne le dernier identiant des entree des fait
      * @return un entier 
      */
      public static int TrouverDernierIdentifiant_Fait() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_fait f WHERE f.id=(SELECT MAX(s.id) FROM fm_fait s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmFait.class);
     FmFait fait=(eqr.getResultList().size()>0)?(FmFait) eqr.getSingleResult():null;
      if(fait!=null)
      {
        id=fait.getId();
      }
       
       return id;
    }
       /**
      * methode qui retourne le dernier identiant des entree des regle
      * @return un entier 
      */
      public static int TrouverDernierIdentifiant_Regle() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_regle f WHERE f.id=(SELECT MAX(s.id) FROM fm_regle s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRegle.class);
     FmRegle fait=(eqr.getResultList().size()>0)?(FmRegle) eqr.getSingleResult():null;
      if(fait!=null)
      {
        id=fait.getId();
      }
       return id;
    }
       /**
      * methode qui retourne le dernier identiant des entree des regle
      * @return un entier 
      */
      public static int TrouverDernierIdentifiant_RegleFait() 
    {
      int id=0;
     
      String sql="SELECT f.id FROM fm_regle_fait f WHERE f.id=(SELECT MAX(s.id) FROM fm_regle_fait s)";
      Query eqr=getEm().createEntityManager().createNativeQuery(sql,FmRegleFait.class);
     FmRegleFait fait=(eqr.getResultList().size()>0)?(FmRegleFait) eqr.getSingleResult():null;
      if(fait!=null)
      {
        id=fait.getId();
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
      public static String formatageInterdi(ObservableList<FmAlimentsPathologie> list)
      {
        String content="";
        if(list.size()>0)
        {
       content=content.concat(" aliments Interdits: \n");
          for(FmAlimentsPathologie liste :list)
          {
           String nom=(!"aucun".equals(liste.getAliment().getSurnom()))?liste.getAliment().getSurnom():liste.getAliment().getNomFr();
           String pathologie=liste.getPathologie().getLibelle();
           String ligne=nom.concat(" :pat: "+pathologie);
          content=content.concat(ligne+"\n");
          }
        }
        return content;
      }
      public static List<FmPathologie> touteListePathologie()
      {
         List<FmPathologie> list=null;
        EntityManager em=getEm().createEntityManager();
        Query req=em.createNamedQuery("FmPathologie.findAll");
        list=req.getResultList();
        em.clear();
        em.close();
        return list;
      }
      public static List<FmAlimentsPathologie> listeAlimentPathologie(FmPathologie pathologie)
      {
        //FmAlimentsPathologie.findByPathologie
          List<FmAlimentsPathologie> list=null;
        EntityManager em=getEm().createEntityManager();
        Query req=em.createNamedQuery("FmAlimentsPathologie.findByPathologie");
        req.setParameter("pathologie", pathologie);
        list=req.getResultList();
        em.clear();
        em.close();
        return list;
        
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
       // System.out.println("id pathologie: "+id);   
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
            mainM.setCategorie(aliment.getGroupe().getNomFr());
            mainM.setMode_cuisson(aliment.getModeCuisson());
            mainM.setAliment(aliment);
            mainM.setRetMin(mine);
            mainM.setRetNu(nutr);
            mainM.setRetVit(vit);
            mainM.setPrixUnitaire("0.0");
            inf.add(mainM);
            cpt++;
        }
        
      }
     return inf;
  }  
   public static ObservableList<regleFaitModel> getobservableListRegleFaitModel()
  {
      ObservableList<regleFaitModel> inf = FXCollections.observableArrayList();
       List<FmFait> listeFait=new ArrayList<>();
         EntityManagerFactory emf=getEm();
        EntityManager em=emf.createEntityManager();
      Query eqr=em.createNamedQuery("FmFait.findAll");
      listeFait=eqr.getResultList();
       regleFaitModel regleModel=null;
       int numero=1;
        for(FmFait fait : listeFait)
        {
          //  System.out.println("fait : "+fait);
        List<FmRegleFait> listRegleAssociees=(List<FmRegleFait>) fait.getFmRegleFaitCollection();
         int nbreRegleApplicable=listRegleAssociees.size();
        String conclusion=fait.getLibelleFait();
        String lettre=fait.getLettreFait();
        int id=fait.getId();
        Date dateModif=fait.getDerniereModif();
        
        regleModel=new regleFaitModel(numero,lettre,conclusion, nbreRegleApplicable);
        regleModel.setFait(fait);
        regleModel.setListRegleFait(listRegleAssociees);
        regleModel.setDateModif(dateModif);
        inf.add(regleModel);
        numero++;
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
         //   mainM.setSurnom((aliment.getSurnom().equals("aucun") || aliment.getSurnom().isEmpty())?aliment.getSurnom():"aucun");
            mainM.setIdAliment(aliment.getId());
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            mainM.setMode_cuisson(aliment.getModeCuisson());
            mainM.setCategorie(aliment.getGroupe().getNomFr());
            mainM.setNomEng(aliment.getNomEng());
            mainM.setPathologie(searchAlimentPathologie(aliment));
            mainM.setEau(Double.parseDouble((nutr!=null && nutr.getEau()!=null)?preformaterChaine(nutr.getEau().toString()):"0.0"));
            mainM.setAsh(Double.parseDouble((nutr!=null && nutr.getAsh()!=null)?preformaterChaine(nutr.getAsh().toString()):"0.0"));
            mainM.setCa(Double.parseDouble((mine!=null && mine.getCa()!=null)?preformaterChaine(mine.getCa().toString()):"0.0"));
            mainM.setFer(Double.parseDouble((mine!=null && mine.getFe()!=null)?preformaterChaine(mine.getFe().toString()):"0.0"));
            mainM.setMg(Double.parseDouble((mine!=null && mine.getMg()!=null)?preformaterChaine(mine.getMg().toString()):"0.0"));
            mainM.setPhos(Double.parseDouble((mine!=null && mine.getPhos()!=null)?preformaterChaine(mine.getPhos().toString()):"0.0"));
            mainM.setPota(Double.parseDouble((mine!=null && mine.getPota()!=null)?preformaterChaine(mine.getPota().toString()):"0.0"));
            mainM.setNa(Double.parseDouble((mine!=null && mine.getNa()!=null)?preformaterChaine(mine.getNa().toString()):"0.0"));
            mainM.setVita(Double.parseDouble((vit!=null && vit.getVita()!=null)?preformaterChaine(vit.getVita().toString()):"0.0"));
            mainM.setVitb1(Double.parseDouble((vit!=null && vit.getVitb1()!=null)?preformaterChaine(vit.getVitb1().toString()):"0.0"));
            mainM.setVitb12(Double.parseDouble((vit!=null && vit.getVitb12()!=null)?preformaterChaine(vit.getVitb12().toString()):"0.0"));
            mainM.setVitb6(Double.parseDouble((vit!=null && vit.getVitb6()!=null)?preformaterChaine(vit.getVitb6().toString()):"0.0"));
            mainM.setVitb9(Double.parseDouble((vit!=null && vit.getFolates()!=null)?preformaterChaine(vit.getFolates().toString()):"0.0"));
            mainM.setCu(Double.parseDouble((mine!=null && mine.getCu()!=null)?preformaterChaine(mine.getCu().toString()):"0.0"));
            mainM.setFibre(Double.parseDouble((nutr!=null && nutr.getFibre()!=null)?preformaterChaine(nutr.getFibre().toString()):"0.0"));  
            mainM.setRiboflavin(Double.parseDouble((vit!=null && vit.getRiboflavin()!=null)?preformaterChaine(vit.getRiboflavin().toString()):"0.0"));  
            mainM.setVitc(Double.parseDouble((vit!=null && vit.getVitc()!=null)?preformaterChaine(vit.getVitc().toString()):"0.0"));  
            mainM.setVitd(Double.parseDouble((vit!=null && vit.getVitd()!=null)?preformaterChaine(vit.getVitd().toString()):"0.0"));   
            mainM.setVite(Double.parseDouble((vit!=null && vit.getVite()!=null)?preformaterChaine(vit.getVite().toString()):"0.0"));   
            mainM.setThiamin(Double.parseDouble((vit!=null && vit.getThiamin()!=null)?preformaterChaine(vit.getThiamin().toString()):"0.0"));  
            mainM.setZn(Double.parseDouble((mine!=null && mine.getZn()!=null)?preformaterChaine(mine.getZn().toString()):"0.0"));  
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
     public static String getUserName() {
        String userName = null;
        Properties infoSysteme = System.getProperties();
        userName = infoSysteme.getProperty("user.name");
        return userName;
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
          pat=pat.concat((alp!=null && alp.getPathologie()!=null )?alp.getPathologie().getLibelle()+" ,\n":" \n");
          }
     return pat;
   }
   public static List<FmAliments> listeDesAlimentsBase()
   {
        List<FmAliments> Aliments;
      EntityManager em=formulyTools.getEm().createEntityManager();
      Query reqAliment =em.createNamedQuery("FmAliments.findAll");//FmAliments.findByPays
       Aliments= reqAliment.getResultList();
       em.clear();
       em.close();
       return Aliments;
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
      EntityManager em=formulyTools.getEm().createEntityManager();
      Query reqAliment =em.createNamedQuery("FmAliments.findAll");//FmAliments.findByPays
          List<FmAliments> Aliments= reqAliment.getResultList();
      //List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
      if(true)
      {
           int cpt=1;
        for(FmAliments aliment: Aliments)
        {
         //   FmAliments aliment=ret.getAliments();
       List<FmRetentionMineraux> lisM=(List<FmRetentionMineraux>) aliment.getFmRetentionMinerauxCollection();
       List<FmRetentionNutriments> lisN=(List<FmRetentionNutriments>) aliment.getFmRetentionNutrimentsCollection();
       List<FmRetentionVitamines> lisV=(List<FmRetentionVitamines>) aliment.getFmRetentionVitaminesCollection();
       FmRetentionMineraux mine=(lisM.size()>0)?lisM.get(0):null;
       FmRetentionNutriments nutr=(lisN.size()>0)?lisN.get(0):null;
        FmRetentionVitamines vit=(lisV.size()>0)?lisV.get(0):null;
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setIdAliment(aliment.getId());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            mainM.setRetMin(mine);
            mainM.setRetNu(nutr);
            mainM.setRetVit(vit);
            mainM.setMode_cuisson(aliment.getModeCuisson());
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
     // System.out.println(requeteNative);
     //System.out.println("nbre pri: "+retentionAliment.size());
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
   //   System.out.println(requeteNative);
   //  System.out.println("nbre pri: "+retentionAliment.size());
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
    //  System.out.println(chaine);
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
     // System.out.println(pal);
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
       // System.out.println(m);
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
     // System.out.println(pal);
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
           public static void quitterFenetre(Button quitte)
     {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("fermer fenetre alerte");
            alert.setHeaderText("Confirmer la fermerture \n");
            alert.setContentText(""
                    + "VOULEZ-VOUS VRAIMENT QUITTEZ ?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
           Stage stage = (Stage) quitte.getScene().getWindow();
    // do what you have to do
               stage.close();
        }                
     }
         /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
          * @param table la tableView concerné de type mainModel
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
          */
         public static void actualisserNumeroTable(TableView<mainModel> table,int nbre)
         {
           for(int i=nbre;i<table.getItems().size();i++)
              { 
             table.getItems().get(i).setNumero(table.getItems().get(i).getNumero()-1);
             table.getItems().set(i, table.getItems().get(i));
              }
         }
           /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
           * @param liste la liste des elements 
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
          */
         public static void actualisserNumeroListe(ObservableList<mainModel> liste,int nbre)
         {
           for(int i=nbre;i<liste.size();i++)
              { 
             liste.get(i).setNumero(liste.get(i).getNumero()-1);
              }
         }
          public static void actualisserNumeroListeRepas(ObservableList<regleFaitModel> liste,int nbre)
         {
           for(int i=nbre;i<liste.size();i++)
              { 
             liste.get(i).setNumero(liste.get(i).getNumero()-1);
              }
         }
          /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
           * @param liste la liste des elements 
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
          */
         public static void actualiserNumeroListe(ObservableList<repasModel> liste,int nbre)
         {
           for(int i=nbre;i<liste.size();i++)
              { 
             liste.get(i).setNumero(liste.get(i).getNumero()-1);
              }
         }
         /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
           * @param liste la liste des elements 
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
          */
         public static void actualiserNumeroListes(ObservableList<alimentRepasModel> liste,int nbre)
         {
           for(int i=nbre;i<liste.size();i++)
              { 
             liste.get(i).setNumero(liste.get(i).getNumero()-1);
              }
         }
           /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
           * @param liste la liste des elements 
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
     * @param parametreFictif
          */
         public static void actualisserNumeroListe(ObservableList<pathologieModel> liste,int nbre,int ... parametreFictif)
         {
           for(int i=nbre;i<liste.size();i++)
              { 
             liste.get(i).setNumero(liste.get(i).getNumero()-1);
              }
         }
      public static void textsConverter(TextField...textField)
    {
    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

UnaryOperator<TextFormatter.Change> filter = c -> {
    String text = c.getControlNewText();
    if (validEditingState.matcher(text).matches()) {
        return c ;
    } else {
        return null ;
    }
};

StringConverter<Double> converter = new StringConverter<Double>() {

    @Override
    public Double fromString(String s) {
        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
            return 0.0 ;
        } else {
            return Double.valueOf(s);
        }
    }


    @Override
    public String toString(Double d) {
        return d.toString();
    }
};


  for(TextField tf: textField)
  {
  TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
  tf.setTextFormatter(textFormatter);
  }
    
    }
        public static String DonnerEquivalenceAge(String element)
     {
      String elmt="";
      switch(element)
      {
          case "Jeune":
          //age compri entre
           elmt="3";
          break;         
         case "Enfant":
          // age compri   entre 0 et 16 ans
            elmt="1";
              break;
         case "Adolescent":
          // age compri
             elmt="2";
              break;
         case "Adulte":
               elmt="4";
              break;
         case "Age avancé":
              elmt="5";
              break;
      
      }
      return elmt;
     }
          public static String DonnerEquivalenceSexe(String sexeParticulier)
     {
        String chaineSexeClient="";
         String op="";
        switch(sexeParticulier){
            case "Masculin":
               chaineSexeClient="0";
                break;
            case "Feminin":
               chaineSexeClient="1";
            break;
        }
        return chaineSexeClient;
     }
          public static void fermerFenetre(Button btn)
          {
      Stage stage = (Stage) btn.getScene().getWindow();    
        stage.close();
          }
           public static void RendreNonDimensionnable(Button btn)
          {
      Stage stage = (Stage) btn.getScene().getWindow();    
        stage.close();
          }
       public static void openWebpage(String url) {
    try {
        new ProcessBuilder("x-www-browser", url).start();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
       /**
        * contenu vide pour l'instant
        * @param btn
        * @param urlImage 
        */
       public static void mettreImageBtn(Button btn ,String urlImage)
       {
      
       }
       public static void ouvrirDossier(String cheminDossier,Class clas)
       {
        Runtime rutine = Runtime.getRuntime();
        try {
            rutine.exec(new String[]{"explorer", cheminDossier});

        } catch (IOException ex) {
            Logger.getLogger(clas.getName()).log(Level.SEVERE, null, ex);
        }
       }
       public static void viderClassEnCache(Class clas)
       {
        getEm().getCache().evict(clas);
       }
        public static void viderCache()
       {
        getEm().getCache().evictAll();
       }
}
