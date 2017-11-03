/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.model.frontend.regleFaitModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ExpertController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextArea affichageProgressif;

    @FXML
    private TableColumn<regleFaitModel,Integer> numero;

    @FXML
    private TableColumn<regleFaitModel ,String> identifiant;

    
    @FXML
    private TableColumn<regleFaitModel ,String> action;
     @FXML
    private TableColumn<regleFaitModel ,String>  detailsFait;

    @FXML
    private ComboBox<String> parenthese;

    @FXML
    private ComboBox<String> comparateur;

    @FXML
    private TableView<regleFaitModel> listFaitConclusion;
    @FXML
    private TextArea conclusion;
    @FXML
    private TableColumn<regleFaitModel, Integer> nbreRegleApplicable;
    @FXML
    private TableColumn<regleFaitModel, Integer> fait;

    @FXML
    private Button enregistrer;

    @FXML
    private ComboBox<String> entites;

    @FXML
    private ComboBox<String> connecteur;
    @FXML
    private Button effacerDernierElement;
     @FXML
    private Button effacerDernierElement2;
    @FXML
    private Button envoiValeur;
    @FXML
     private ComboBox<String>  listeIntelligente;
    @FXML
    private Label mesure;
    @FXML 
    private ImageView alert;
    
    private ObservableList<regleFaitModel> list;
    private int nombreCaractere;
    private String uniteMesure="";
    private String derniereChaineCaractere="";
    private ArrayList<String> listSectionEnregistre;
    private ArrayList<String> listSectionEnregistreClair;
    private String message="\n";
    private  ComboBox<String> dernierComboBoxModifier;
    private  List<ComboBox<String>> ListComboBoxModifier=new ArrayList<>();
    
    
    public ExpertController() {
      list=formulyTools.getobservableListRegleFaitModel();
      listSectionEnregistre=new ArrayList<>();
      listSectionEnregistreClair=new ArrayList<>();
    }
    public void lancerEnregistrement()
    {
    enregistrer.setOnAction(event->{
      boolean b=  validiteCrochet(listSectionEnregistre);
        System.out.println(" res : "+b);
          System.out.println("nbreElement : "+listSectionEnregistre.size());
       String regle=(b)?retournerValeurFormater(listSectionEnregistreClair):"Mal Formatage cause d'un crochet malveillant";
        System.out.println("regle : "+regle);
    });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       IntitLesElementsDeLaVue();
       traiterAction();
       lancerEnregistrement();
       mesure.setVisible(false);
    }    
     private void traiterAction()
     {
      entites.setOnAction(event->{
         String entite=(entites.getValue()!=null)?entites.getValue():"";
          if(!entite.isEmpty() && !entite.equals("----Aucun Choix-----"))
         {
         String elementClair=entite;
         String textVolu=affichageProgressif.getText();
         String nvoText=textVolu.concat(entite);
         affichageProgressif.setText(nvoText+" ");
          nombreCaractere=textVolu.length();
          derniereChaineCaractere=nvoText;
          listSectionEnregistre.add(entite);
          listSectionEnregistreClair.add(elementClair);
         List<String> operateurAuth=donnerComparateurManiereIntelligente(entite);
         List<String> optionValeur=valeurAutorise(entite);
          comparateur.getItems().clear();
          comparateur.getItems().addAll(operateurAuth);    
          listeIntelligente.getItems().clear();
          connecteur.getItems().clear();
          parenthese.getItems().clear();
          connecteur.getItems().addAll(listConnecteur());
          parenthese.getItems().addAll(listParenthese());
          listeIntelligente.getItems().addAll(FXCollections.observableArrayList(optionValeur));
          mesure.setText(uniteMesure);
          mesure.setVisible(true);
         // comparateur.setDisable(true);
          parenthese.setDisable(true);
          connecteur.setDisable(true);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
          comparateur.setDisable(false);
          ListComboBoxModifier.add(entites);
         }
          else{
          comparateur.getItems().clear();  
          listeIntelligente.getItems().clear();
          connecteur.getItems().clear();
          parenthese.getItems().clear();
          
          mesure.setVisible(false); 
          }
      });
      connecteur.setOnAction(event->{
         String entite=(connecteur.getValue()!=null)?connecteur.getValue():"";
         if(!entite.isEmpty() && !entite.equals("----Aucun Choix-----"))
         {
         String elementClair=entite;
         String textVolu=affichageProgressif.getText();
         String nvoText=textVolu.concat(" "+entite+" ");
         affichageProgressif.setText(nvoText+" ");
          nombreCaractere=textVolu.length();
          derniereChaineCaractere=nvoText;
           listSectionEnregistre.add(entite);
           listSectionEnregistreClair.add(elementClair);
           parenthese.setDisable(false);
          connecteur.setDisable(true);
          comparateur.setDisable(true);
          entites.setDisable(false);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
         ListComboBoxModifier.add(connecteur);
         }
      });
      comparateur.setOnAction(event->{
         String entite=(comparateur.getValue()!=null)?comparateur.getValue():"";
          if(!entite.isEmpty() && !entite.equals("----Aucun Choix-----"))
         {
             String elementClair=entite;
         entite=retournerCorrespondanceComparateur(entite, true);
         String textVolu=affichageProgressif.getText();
         String nvoText=textVolu.concat(entite);
         affichageProgressif.setText(nvoText+" ");
         nombreCaractere=textVolu.length();
         derniereChaineCaractere=nvoText;
          listSectionEnregistre.add(entite);
          listSectionEnregistreClair.add(elementClair);
          parenthese.setDisable(true);
          connecteur.setDisable(true);
          entites.setDisable(true);
          listeIntelligente.setDisable(false);
          envoiValeur.setDisable(false);
          ListComboBoxModifier.add(comparateur);
         }
      });
      
       envoiValeur.setOnAction(event->{
         String text=listeIntelligente.getValue();
        String textVolu=derniereChaineCaractere;
         String nvoText=textVolu.concat(text);
         affichageProgressif.setText(nvoText+" ");
         nombreCaractere=textVolu.length();
          listSectionEnregistre.add(text);
         //  System.out.println(" text "+text);
          listSectionEnregistreClair.add(text);
          //nous allos verifier si il y a un crochet deja
          boolean b=validiteCrochet(listSectionEnregistre);
        // derniereChaineCaractere=nvoText;
        
          parenthese.setDisable(b);
          connecteur.setDisable(false);
          comparateur.setDisable(true);
          entites.setDisable(true);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
         ListComboBoxModifier.add(listeIntelligente);
      });
        listeIntelligente.setOnAction(event->{
         String text=listeIntelligente.getValue();
            System.out.println("valer : "+text);
      });
      parenthese.setOnAction(event->{
         String entite=(parenthese.getValue()!=null)?parenthese.getValue():"";
          if(!entite.isEmpty() && !entite.equals("----Aucun Choix-----"))
         {
             System.out.println("parenthese:  "+entite);
         String textVolu=affichageProgressif.getText();
         String nvoText=textVolu.concat(entite);
           affichageProgressif.setText(nvoText+" ");
           nombreCaractere=textVolu.length();
           derniereChaineCaractere=nvoText;
           listSectionEnregistre.add(entite);
          listSectionEnregistreClair.add(entite);
           parenthese.setDisable(true);
          connecteur.setDisable(false);
          comparateur.setDisable(true);
          entites.setDisable(false);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
         ListComboBoxModifier.add(parenthese);
         }
      });
      
      effacerDernierElement.setOnAction(event->{
          System.out.println("text : "+affichageProgressif.getText().length());
          if(affichageProgressif.getText().length()>=1)
          {
               nombreCaractere--;
              int nbre= listSectionEnregistre.size();
         nombreCaractere=affichageProgressif.getText().length();
         listSectionEnregistre.remove(nbre-1);
         listSectionEnregistreClair.remove(nbre-1);
         affichageProgressif.setText(chaineOrdre());
         int tailleElment=ListComboBoxModifier.size();
          if(tailleElment>0)
          {
           parenthese.setDisable(true);
          connecteur.setDisable(true);
          comparateur.setDisable(true);
          entites.setDisable(true);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
          ListComboBoxModifier.get(tailleElment-1).setDisable(false);
          //desactiver tous le reste
          ListComboBoxModifier.get(tailleElment-1).setDisable(false);
          ListComboBoxModifier.remove(tailleElment-1);
          }
          }
      });
       effacerDernierElement2.setOnAction(event->{
          System.out.println("text : "+affichageProgressif.getText().length());
          if(affichageProgressif.getText().length()>=1)
          {
               nombreCaractere--;
              int nbre= listSectionEnregistre.size();
         nombreCaractere=affichageProgressif.getText().length();
         listSectionEnregistre.remove(nbre-1);
         affichageProgressif.setText(chaineOrdre());
          }
      });
     }
     private boolean ExistenceDelimiteur(String element)
     {
       boolean b;
       ArrayList<String> listElement=new ArrayList<>();
        listElement.add("[");
        listElement.add("]");
        listElement.add("(");
        listElement.add(")");
       b=listElement.contains(element);
       return b;
     }
     private boolean ExistenceConnecteur(String element)
     {
       boolean b;
       ArrayList<String> listElement=new ArrayList<>();
        listElement.add("OU");
        listElement.add("ET");
       b=listElement.contains(element);
       return b;
     }
     private boolean ExistanceEntite(String element)
     {
       List<String> entite=listEntitite();
     boolean b=entite.contains(element);
      return b;
     }
     private boolean ExistanceComparateur(String element)
     {
       List<String> entite=listComparateur();
     boolean b=entite.contains(element);
      return b;
     }
     private boolean ExistanceNiveauAge(String element)
      {
       boolean b;
       List<String> nvage=retournerListeNiveauAge();
       b=nvage.contains(element);
       return b;
      }
    private String retournerValeurFormater(ArrayList<String> listElementsPris)
     {
      String regle="";
       int i=0;
       for(String element:listElementsPris)
       {
           System.out.println("elemnt : "+element);
           boolean b=ExistenceDelimiteur(element);
         if(b)
         {
            if(element.contains("]") && listElementsPris.size()>(i+1))
            {
           regle=regle.concat("//");
             
            }
         }
          else
         {
            boolean br=ExistenceConnecteur(element);
         if(br)
          {
         if(element.contains("OU"))
         {
         int vPrec=i-1;
         int vSuiv=i+1; 
         String precedent=listElementsPris.get(vPrec);
         String suivant=listElementsPris.get(vSuiv);
         boolean ctCrochetP=precedent.contains("]");
         boolean ctCrochetS=suivant.contains("[");
        if(ctCrochetP && ctCrochetS)
        {
        regle=regle.concat("");
        }
         }
          if(element.contains("ET"))
         {
         regle=regle.concat("ET");
         }
          }
         else
           {
         //nous avons des operateurs a gerer
               boolean cmparateur=ExistanceComparateur(element);
               boolean entite=ExistanceEntite(element);
              if(cmparateur)
              {
                  System.out.println("op "+element);
             String operateur=retournerCorrespondanceComparateur(element,false);
                   System.out.println("op ap "+operateur);
             regle=regle.concat(operateur);
              }
              else
              {
                if(entite)
                {
            String entitess=retournerCorrespondanceEntites(element);
             regle=regle.concat(entitess);    
                }
                else
                {
             boolean exAge=ExistanceNiveauAge(element);
             String valeurAge="";
               if(exAge)
               {
         valeurAge=retournerValeurJeune(element);
          regle=regle.concat(valeurAge);
               }
               else{
               boolean exSexe=ExistenceDeValeurSexe(element);
               if(exSexe)
               {
               String valeurSexe=valeurEquivalentSexe(element);
               regle=regle.concat(valeurSexe);
               }
               else
               {
              regle=regle.concat(element);
               }
                 }
                }
              }
           }
         }
         i++;
       }
      return regle;
     }
    private String retournerValeurJeune(String element)
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
     private List<String> listSexe()
     {
      List<String> list=new ArrayList<>();
      list.add("Masculin");
      list.add("Feminin");
       return list;
     }
     private boolean ExistenceDeValeurSexe(String element)
     {
      boolean b;
      List<String> listSexe=listSexe();
      b=listSexe.contains(element);
      return b;
     }
     private List<String> retournerListeNiveauAge()
     {
         ArrayList<String> list=new ArrayList<>();
       list.add("Enfant");
       list.add("Adolescent");
       list.add("Jeune");
       list.add("Adulte");
       list.add("Age avancé");
       return list;
     }
     private List<String> valeurAutorise(String event)
     {
         List<String> list=new ArrayList<>();
    switch(event)
    {
        case "Sexe":
       list.add("Masculin");
       list.add("Feminin");
       uniteMesure="";
        break;
            
        case "Age":  
       list.add("Enfant");
       list.add("Adolescent");
       list.add("Jeune");
       list.add("Adulte");
       list.add("Age avancé");
       uniteMesure="";
        break;
            
          case "Taille":            
       list.add("1.00");
       list.add("1.20");
       list.add("1.56");
       list.add("1.61");
       list.add("1.82");
       list.add("2.00");
       uniteMesure="M";
        break;
        
          case "Poids":            
       list.add("1");
       list.add("2");
       list.add("5");
       list.add("10");
       list.add("30");
       list.add("35");
       list.add("40");
       list.add("45");
       list.add("50");
       list.add("60");
       list.add("80");
       list.add("95");
       list.add("100");
       uniteMesure="KG";
        break;
        
          case "Energie Totale":           
       list.add("250");
       list.add("500");
       list.add("900");
       list.add("1000");
       list.add("1500");
       list.add("2000");
       list.add("2500");
       list.add("2700");
       list.add("3000");
       list.add("3500");
       list.add("4000");
       list.add("4500");
       list.add("5000");
       list.add("7000");
       list.add("9000");
       list.add("9500");
       uniteMesure="Kcal";
        break;
       default:
          list.clear();
       list.add("10");
       list.add("5");
       list.add("9");
       list.add("10");
       list.add("30");
       list.add("35");
       list.add("40");
       list.add("45");
       list.add("50");
       list.add("60");
       list.add("80");
       list.add("95");
       list.add("100");   
       uniteMesure="%";
     }
       return list;
     }
     private void controllePaenthese(String valeurParenthese)
     {
       if(!valeurParenthese.equals("----Aucun Choix-----"))
       {
         if(valeurParenthese.equals("("));
         {
        //lancement du control d'ouverture si non  
         }
       }
     }
      private boolean validiteCrochet(ArrayList<String> listChaine)
     {
         boolean b=true;
         int i=0;
         int cptOuvr=0;
         int cptFer=0;
         for(String element:listChaine)
         {
         if(i==0)
         {
     if(element.equals("]") || element.equals("["))
        {
         cptOuvr=(element.equals("["))?cptOuvr++:cptOuvr; 
        // cptFer=(element.equals("]"))?cptFer++:cptFer;
         if(element.equals("]"))
          {
          b=false;
          break;
          }
         else{
         cptOuvr++;
         }
         }
         }
       else{
        String chainePreccedent=listChaine.get(i-1);
        int taillePresumesup=i+1;
        String chaineSuivant=(listChaine.size()>taillePresumesup)?listChaine.get(taillePresumesup):null;
        boolean estOu=chainePreccedent.contains("OU");
        boolean estOuSuivant=(chaineSuivant==null || chaineSuivant.contains("OU"));
       //ici on verifie si derriere un crochet se trouve un ou
      if(element.equals("]") || element.equals("["))
        {
         if(element.equals("[") && estOu)
         {
         cptOuvr++;
         }
       else{
       //probleme de crochet
          if(element.equals("]") && estOuSuivant)
           {
           cptFer++;
           }
          else{
               b=false;
         message=message.concat("Probleme au niveau des crochets  \n");
             System.out.println(message);
           break;
              }
          }
         }
          }
         i++;
         }
          System.out.println("nbre [ "+cptOuvr);
          System.out.println("nbre ] "+cptFer);
        if(cptOuvr!=cptFer)
        {
        b=false;
        }
       return b;
     }
     private List<String> donnerComparateurManiereIntelligente(String event)
     {
       List<String> list=new ArrayList<>();
         switch(event)
    {
        case "Sexe":
         list.add("----Aucun Choix-----");
        list.add("Egale");
        list.add("Different");
        break;
        default:
               list.add("----Aucun Choix-----");
        list.add("Superieur");
        list.add("Superieur ou Egale");
        list.add("Inferieur");
        list.add("Inferieur ou Egale");
        list.add("Egale");
        list.add("Different");
     }
         return list;
     }
     private String valeurEquivalentSexe(String sexeParticulier)
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
     private void remplirLaListeIntelligente(String event)
     {
       String correspondance="";
             switch (event)
{
  case "AET Lipide":
    correspondance="aLp";
    break;
  case "AET Proteine":
    correspondance="aPt";
    break;
  case "AET Glucide":
   correspondance="aGl";
    break;
  case "Taux Glucide":
    correspondance="pGl";
   break;
       case "Taux Lipide":
   correspondance="pLp";
    break;
     case "Taux Proteine":
   correspondance="pPt";
    break;       
     case "Energie Totale":
    correspondance="vEn";
     break;    
        case "Regime 1000":
       correspondance="r10";
     break;
     case "Regime 1500":
      correspondance="r15";
     break;
     case "Regime 2000":
       correspondance="r20";
     break;
        
     case "Regime 2500":
    correspondance="r25";
     break;
        
     case "Regime 3000":
    correspondance="r30";
     break;
      
     case "Regime 3500":
      correspondance="r35";
     break;
        
      case "Regime 4500":
       correspondance="r45";
     break;
     case "Age":
         correspondance="age";
     break;
  
     case "Poids": //le poids 
        correspondance="pds";
      break;
      
     case "Sexe": //le poids 
        correspondance="sex";
      break;
       case "Taille": //le poids 
        correspondance="tai";
      break;

  default:
    System.out.println("pas de correspondance");
             }
     }
     private String chaineOrdre()
     {
         String chaine="";
      for(String element:listSectionEnregistre)
        {
      chaine=chaine.concat(element+" ");
        }
      return chaine;
     }
    private void IntitLesElementsDeLaVue()
      {
          initTable();
    entites.setItems(FXCollections.observableList(listEntitite()));
    parenthese.setItems(FXCollections.observableList(listParenthese()));
    connecteur.setItems(FXCollections.observableList(listConnecteur()));
    comparateur.setItems(FXCollections.observableList(listComparateur()));
    
      }
    private void initTable()
    {
        numero.setCellValueFactory(new PropertyValueFactory<>("numero")); 
        identifiant.setCellValueFactory(new PropertyValueFactory<>("identifiantFait"));
        fait.setCellValueFactory(new PropertyValueFactory<>("Conclusion"));
        nbreRegleApplicable.setCellValueFactory(new PropertyValueFactory<>("nombreRegleApplicable"));
        placerBouton(action,1);
         placerBouton(detailsFait,2);
        listFaitConclusion.setItems(list);
    }
    private List<String> listEntitite()
    {
    List<String> list=new ArrayList<>();
    
        list.add("----Aucun Choix-----");
        list.add("Energie Totale");
        list.add("Taux Proteine");
        list.add("Taux Glucide");
        list.add("Taux Lipide");
        list.add("AET Proteine");
        list.add("AET Glucide");
        list.add("AET Lipide");
        list.add("Regime 1500");
        list.add("Regime 2000");
        list.add("Regime 2500");
        list.add("Regime 3000");
        list.add("Regime 3500");
        list.add("Age");
        list.add("Sexe");
        list.add("Poids");
        list.add("Taille");
     return list;
    }
    private List<String> listConnecteur()
    {
    List<String> list=new ArrayList<>();
       
       list.add("----Aucun Choix-----");
        list.add("ET");
        list.add("OU");
        list.add("ALORS");
     return list;
    }
     private List<String> listComparateur()
    {
    List<String> list=new ArrayList<>();
      
         list.add("----Aucun Choix-----");
        list.add("Superieur");
        list.add("Superieur ou Egale");
        list.add("Inferieur");
        list.add("Inferieur ou Egale");
        list.add("Egale");
        list.add("Different");
        
     return list;
    }
     private String retournerCorrespondanceEntites(String entites)
     {
         String correspondance="";
             switch (entites)
{
  case "AET Lipide":
    correspondance="aLp";
    break;
  case "AET Proteine":
    correspondance="aPt";
    break;
  case "AET Glucide":
   correspondance="aGl";
    break;
  case "Taux Glucide":
    correspondance="pGl";
   break;
       case "Taux Lipide":
   correspondance="pLp";
    break;
     case "Taux Proteine":
   correspondance="pPt";
    break;       
     case "Energie Totale":
    correspondance="vEn";
     break;    
        case "Regime 1000":
       correspondance="r10";
     break;
     case "Regime 1500":
      correspondance="r15";
     break;
     case "Regime 2000":
       correspondance="r20";
     break;
        
     case "Regime 2500":
    correspondance="r25";
     break;
        
     case "Regime 3000":
    correspondance="r30";
     break;
      
     case "Regime 3500":
      correspondance="r35";
     break;
        
      case "Regime 4500":
       correspondance="r45";
     break;
     case "Age":
         correspondance="age";
     break;
  
     case "Poids": //le poids 
        correspondance="pds";
      break;
      
     case "Sexe": //le poids 
        correspondance="sex";
      break;
       case "Taille": //le poids 
        correspondance="tai";
      break;

  default:
    System.out.println("pas de correspondance ");
             }
         return correspondance;
     }
      private String retournerCorrespondanceComparateur(String entites,boolean pourAffichage)
     {
         String correspondance="";
             switch (entites)
{
  case "Superieur":
    correspondance=(pourAffichage)?">":">>";
    break;
  case "Superieur ou Egale":
     correspondance=">=";
    break;
  case "Inferieur":
   correspondance=(pourAffichage)?"<":"<<";
    break;
  case "Inferieur ou Egale":
    correspondance="<=";
   break;
       case "Egale":
  correspondance=(pourAffichage)?"=":"==";
    break;
     case "Different":
   correspondance="!=";
    break;       
     
  default:
    System.out.println("Mrs JYPY");
             }
         return correspondance;
     }
      private List<String> listParenthese()
    {
    List<String> list=new ArrayList<>();
    
        list.add("----Aucun Choix-----");
        list.add("(");
        list.add(")");
        list.add("[");
        list.add("]");

     return list;
    }
      public void textConverter(TextField...textField)
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
      public void placerBouton(TableColumn<regleFaitModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<regleFaitModel,String>, TableCell<regleFaitModel,String>> cellFactory = new Callback<TableColumn<regleFaitModel,String>, TableCell<regleFaitModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<regleFaitModel,String> param) {
                final TableCell<regleFaitModel,String> cell = new TableCell<regleFaitModel,String>() {

                    final Button btn = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                      regleFaitModel regleFModel= getTableView().getItems().get(getIndex());
                      
                             insererFaitExistant(regleFModel);
                             
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/bass.png"));
                             btn.setGraphic(new ImageView(image));
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
         colonne.setCellFactory(cellFactory);
        }
      else{
        
     colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<regleFaitModel,String>, TableCell<regleFaitModel,String>> cellFactory = new Callback<TableColumn<regleFaitModel,String>, TableCell<regleFaitModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<regleFaitModel,String> param) {
                final TableCell<regleFaitModel,String> cell = new TableCell<regleFaitModel,String>() {

                    final Button btn = new Button("details ici");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                     regleFaitModel rgfait= getTableView().getItems().get(getIndex());    
                                System.out.println("regFait : "+rgfait);
                                lancerDetailsFait(rgfait,btn);
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
//                             Image image= new Image(
//     getClass().getResourceAsStream("/formuly/image/del.png"));
//                             btn.setGraphic(new ImageView(image));
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }

                };
                return cell;
                
            }
        };
         colonne.setCellFactory(cellFactory);     
        
        }
    }
      public void lancerDetailsFait(regleFaitModel rgfait,Button btn)
      {
       
          try {
     //  if(loader==null){
               loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("/formuly/view/frontend/detailsFait.fxml"));
              ctr_details=new DetailsFaitController(rgfait);
               loader.setController(ctr_details);
               Parent root = (Parent)loader.load(); 
                 st=null;
               st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("Details fait");
         st.initOwner(btn.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
    
//         }
          } catch (IOException ex) {
                     Logger.getLogger(DetailsFaitController.class.getName()).log(Level.SEVERE, null, ex);
                 }
      }
      public void insererFaitExistant(regleFaitModel rgM)
      {
         //nous sommes a la fin nous l'inserons dans la conclusion et dans la progression
            if(fin)
            {
        String lettreFait=rgM.getlettreFait();
        String conclusionPartielle=rgM.getConclusion();
         String concl=conclusion.getText();
                 if(concl.isEmpty())
                 {
              conclusion.setText("["+lettreFait+"]");
                 }
                 else
                 {
               conclusion.setText(concl.concat("\n"));
                 }
            }
            //
       else
       // nous l'inserons dans la progression
            {
       String lettreFait=rgM.getlettreFait();
       String conclusionPartielle=rgM.getConclusion();  
       String text=affichageProgressif.getText();
       affichageProgressif.setText(text.concat(lettreFait));
       listSectionEnregistre.add(lettreFait);
            }
      }
      private DetailsFaitController ctr_details;
      private FXMLLoader loader;
      private Stage st;
      private boolean fin=false;
}
