/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmFait;
import formuly.entities.FmRegle;
import formuly.entities.FmRegleFait;
import formuly.model.frontend.regleFaitModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
import javax.persistence.EntityManager;

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
    @FXML
    private Button enleverImportConclusion;
    private ObservableList<regleFaitModel> list;
    private int nombreCaractere;
    private String uniteMesure="";
    private String derniereChaineCaractere="";
    private ArrayList<String> listSectionEnregistre;
    private ArrayList<String> listSectionEnregistreClair;
    private String message="\n";
    private  ComboBox<String> dernierComboBoxModifier;
    private  List<ComboBox<String>> ListComboBoxModifier=new ArrayList<>();
    private final int NOMBRE_MAX_DEDUCTION_TABLEAU=1;
    private String conclusionAjouter;
    private String regle="";
    private int dernierIdFait;
    private int dernierIdRegle;
    private int dernierIdRegleFait;
    
    public ExpertController() {
      list=formulyTools.getobservableListRegleFaitModel();
      listSectionEnregistre=new ArrayList<>();
      listSectionEnregistreClair=new ArrayList<>();
      alphabet=new ArrayList<>();
      remplirListAlphabet();
       dernierIdFait=formulyTools.TrouverDernierIdentifiant_Fait()+1;
       dernierIdRegle=formulyTools.TrouverDernierIdentifiant_Regle()+1;
       dernierIdRegleFait=formulyTools.TrouverDernierIdentifiant_RegleFait()+1;
//      retournerIdentifiant("B");
    }
    public void initialiserLesElementsDeConception()
    {
      entites.getItems().clear();
      parenthese.getItems().clear();
      connecteur.getItems().clear();
      comparateur.getItems().clear();
       listeIntelligente.getItems().clear();
       conclusion.setText("");
       affichageProgressif.setText("");
       mesure.setText("");
       mesure.setVisible(false);
     entites.setItems(FXCollections.observableList(listEntitite()));
    parenthese.setItems(FXCollections.observableList(listParenthese()));
    connecteur.setItems(FXCollections.observableList(listConnecteur()));
    comparateur.setItems(FXCollections.observableList(listComparateur()));
    }
    public final void remplirListAlphabet()
     {
       alphabet.add(0,"A");
       alphabet.add(1,"B");
       alphabet.add(2,"C");
       alphabet.add(3,"D");
       alphabet.add(4,"E");
       alphabet.add(5,"F");
       alphabet.add(6,"G");
       alphabet.add(7,"H");
       alphabet.add(8,"I");
       alphabet.add(9,"J");
       alphabet.add(10,"K");
       alphabet.add(11,"L");
       alphabet.add(12,"M");
       alphabet.add(13,"N");
       alphabet.add(14,"O");
       alphabet.add(15,"P");
       alphabet.add(16,"Q");
       alphabet.add(17,"R");
       alphabet.add(18,"S");
       alphabet.add(19,"T");
       alphabet.add(20,"U");
       alphabet.add(21,"V");
       alphabet.add(22,"W");
       alphabet.add(23,"X");
       alphabet.add(24,"Y");
       alphabet.add(25,"Z");
     }
    public int returnerIndiceCaractere(String caractere)
     {
       int indice=-1;
       int cpt=0;
       for(String element:alphabet)
       {
        if(element.equals(caractere))
        {
         indice=cpt;
         break;
        }
        else{
         cpt++;
           }
       }
       return indice;
     }
    public String retournerCaractereSuivant(int indiceActuelle)
     {
      String caractereSuivant="";
      if(indiceActuelle<25)
      {
      caractereSuivant=alphabet.get(indiceActuelle+1);
      }
    else{
       caractereSuivant=alphabet.get(0);
      }
      return caractereSuivant;
     }
    public String retournerIdentifiant(String dernierIdentifiant)
     {
      String id="";
      int nbreElement=dernierIdentifiant.length();
//      if(nbreElement>1)
//       {
      //recuperation du dernierCaractere
          String dernierCaractere=dernierIdentifiant.substring(nbreElement-1);
           int indiceElement=returnerIndiceCaractere(dernierCaractere);
           String caractereSuivant=retournerCaractereSuivant(indiceElement);
           /*dans le ou le caractere suivant le dernier caractere est different de A alors
           *nous sommes dans une chaine nous devons donc remplacer le dernier par le caractere suivant
           */
      if(!caractereSuivant.equals("A"))
       {
    CharSequence elementPrec=dernierIdentifiant.subSequence(0,nbreElement-1);
            String element=elementPrec.toString();
            element=element.concat(caractereSuivant);
            id=element;
           // System.out.println("l'id est : "+id);
       }
      //si egale a A nous faisons une concatenanion cela permet de garder l'encoien
     else{
         id=dernierIdentifiant.concat(caractereSuivant);
          // System.out.println("l'id est : "+id);
        }
    //   }
//      else
//      {
//     int indiceElement=returnerIndiceCaractere(dernierIdentifiant);
//     String caractereSuivant=retournerCaractereSuivant(indiceElement);
//     if(!caractereSuivant.equals("A"))
//       {
//      id=caractereSuivant;
//       }
//     else{
//       id=dernierIdentifiant.concat(caractereSuivant);
//        }
//      }
      return id;
     }
    public void lancerEnregistrement()
    {
    enregistrer.setOnAction(event->{
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
      boolean b=  validiteCrochet(listSectionEnregistre);
        if(b)
         {
          String messages="Veuillez confirmer la mise a jour \n"
                        + "SVP \n"
                     + "";
          alert.setTitle("Confirmation");
             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/question.png"));
             alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
                     alert.setContentText(messages);
                     alert.showAndWait();
                if(alert.getResult()==ButtonType.YES)
                {
             //SupprimerPathologie(pat);
      regle=(b)?retournerValeurFormater(listSectionEnregistreClair):"Mal Formatage cause d'un crochet malveillant";
                creationregle();
                }
         }
     else{
      
          String messages="Une erreur rencontré dans la formulation de la regle\n"
                        + "details : "+message+"\n"
                     + " veuillez revoir vos saisies : \n"
                     + "";
          alert.setTitle("Erreur rencontre");
             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
             alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.OK);
                     alert.setContentText(messages);
                     alert.showAndWait();
                 
         }
    });
    }
    public String retournerRegleComplet(String regle,String identifiantFaitDeclencher)
    {
    String regleComplet=regle.concat(" alors ").concat(identifiantFaitDeclencher);
    return regleComplet;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       IntitLesElementsDeLaVue();
       traiterAction();
       lancerEnregistrement();
       mesure.setVisible(false);
       actionEnleverImportConclusion();
          Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/basExpert.png"));
                            envoiValeur.setGraphic(new ImageView(image));
    }  
    private void actionEnleverImportConclusion()
     {
     enleverImportConclusion.setOnAction(event->
     {
      initialiserAjoutConclusion();
     });
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
           if(!elementClair.contains("ALORS"))
           {
           parenthese.setDisable(false);
          connecteur.setDisable(true);
          comparateur.setDisable(true);
          entites.setDisable(false);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
           }
           else{
           parenthese.setDisable(true);
          connecteur.setDisable(true);
          comparateur.setDisable(true);
          entites.setDisable(true);
          listeIntelligente.setDisable(true);
          envoiValeur.setDisable(true);
          fin=true;
           }
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
          //  System.out.println("valer : "+text);
      });
      parenthese.setOnAction(event->{
         String entite=(parenthese.getValue()!=null)?parenthese.getValue():"";
          if(!entite.isEmpty() && !entite.equals("----Aucun Choix-----"))
         {
           //  System.out.println("parenthese:  "+entite);
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
         // System.out.println("text : "+affichageProgressif.getText().length());
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
         // System.out.println("text : "+affichageProgressif.getText().length());
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
         if(!element.contains("ALORS"))
           {
          // System.out.println("elemnt : "+element);
      
           boolean b=ExistenceDelimiteur(element);
         if(b)
         {
          // String chaineSuivant=
          
         int vSuiv=i+1; 
         String suivant=(listElementsPris.size()>(i+1))?listElementsPris.get(vSuiv):"";
         boolean contientAlors=suivant.contains("ALORS");
            if(element.contains("]") && listElementsPris.size()>(i+1) && !contientAlors)
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
        if(ctCrochetP || ctCrochetS)
        {
        regle=regle.concat("");
        }
        else{
            regle=regle.concat(element.toLowerCase());
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
               //   System.out.println("op "+element);
             String operateur=retournerCorrespondanceComparateur(element,false);
                //   System.out.println("op ap "+operateur);
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
      List<String> lists=new ArrayList<>();
      lists.add("Masculin");
      lists.add("Feminin");
       return lists;
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
         ArrayList<String> lists=new ArrayList<>();
       lists.add("Enfant");
       lists.add("Adolescent");
       lists.add("Jeune");
       lists.add("Adulte");
       lists.add("Age avancé");
       return lists;
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
      boolean estOuSuivant=(chaineSuivant==null || chaineSuivant.contains("OU") || chaineSuivant.contains("ALORS"));
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
            // System.out.println(message);
           break;
              }
          }
         }
          }
         i++;
         }
          //System.out.println("nbre [ "+cptOuvr);
          //System.out.println("nbre ] "+cptFer);
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
                             //   System.out.println("regFait : "+rgfait);
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
                   Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
               st=new Stage();
         st.setScene(new Scene(root));
         st.getIcons().add(image);
         st.setTitle("Details fait");
         st.initOwner(btn.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
    
//         }
          } catch (IOException ex) {
                     Logger.getLogger(DetailsFaitController.class.getName()).log(Level.SEVERE, null, ex);
                 }
      }
    
     public void initialiserAjoutConclusion()
     {
             String text=conclusion.getText();
           if(conclusionAjouter!=null)
           {
         if(text.contains(conclusionAjouter))
         {
              text=text.replaceAll(conclusionAjouter,"");
              conclusion.setText(text);
              fin=true;
              compteurMax=0;
         }
         else
         {
        fin=true;
        compteurMax=0;
         }
           }
     }
     public void insererFaitExistant(regleFaitModel rgM)
      {
         //nous sommes a la fin nous l'inserons dans la conclusion et dans la progression
        if(compteurMax<NOMBRE_MAX_DEDUCTION_TABLEAU)
        {
            if(fin)
            {
         String lettreFait=rgM.getlettreFait();
         String conclusionPartielle=rgM.getConclusion();
         conclusionAjouter="<"+conclusionPartielle+">";
         String concl=conclusion.getText();
         
                 if(concl.isEmpty())
                 {
              conclusion.setText("<"+conclusionPartielle+">");
                 }
                 else
                 {
                     concl=concl.concat("\n");
               String nouveauText=concl.concat("<"+conclusionPartielle+">");
               conclusion.setText(nouveauText);
                 }
                  compteurMax++;
            }  
        }
        else
        {
   //une alerte pour signifier que une seule regle existante est accordee   
        }
      }
     private Task ProccessusCreationRegle() {
    return new Task() {
      @Override
      protected Object call() throws Exception {
            EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la mise a jour ......");
              updateProgress(15,100);
          try {
            em.getTransaction().begin(); 
            regleFaitModel rgF=list.get(list.size()-1);
            String id=retournerIdentifiant(rgF.getlettreFait());
           String regleCmpletCmp=retournerRegleComplet(regle,id);
           //creation de la regle
           updateMessage("mise a jour des info de la regle...");
              updateProgress(35,100);
           FmRegle regle=new FmRegle();
           regle.setId(dernierIdRegle);
           regle.setNbreFaitDeclencher(1);
           regle.setLibelleRegle(regleCmpletCmp);
           regle.setDerniereModif(new Timestamp(new Date().getTime()));
           String textRegle=affichageProgressif.getText();
           textRegle=textRegle.replaceAll("ALORS","");
           regle.setLibelleRegleClair(textRegle);
          //fin de la mise a jour des info de la regle et debut creation du fait
            FmFait fait=new FmFait();
            updateMessage("mise a jour des info du fait engendré...");
            updateProgress(35,100);
            fait.setId(dernierIdFait);
            fait.setDerniereModif(new Timestamp(new Date().getTime()));
            fait.setLettreFait(id);
            fait.setLibelleFait(conclusion.getText());
         //fin de la mise a jour du fait nous allons creer FmRegleFait
            updateMessage("mise a jour ...");
            updateProgress(55,100);
            FmRegleFait regleFait=new FmRegleFait();
             regleFait.setId(dernierIdRegleFait);
             regleFait.setFait(fait);
             regleFait.setRegle(regle);
            regleFait.setDerniereModif(new Timestamp(new Date().getTime()));
         //fin mise a jour des elements actualistion des  informations
              List<FmRegleFait> listFmRegle=new ArrayList<>();
              listFmRegle.add(regleFait);
              fait.setFmRegleFaitCollection(listFmRegle);
            updateMessage("Debut de la persistence ...");
            updateProgress(65,100);
            //creation des elements
              em.persist(fait);
            updateMessage("persistence du fait ...");
            updateProgress(75,100);
            updateMessage("persistence de la regle ...");
              em.persist(regle);
            updateProgress(75,100);
              em.persist(regleFait);
            updateMessage("persistence des infos  ...");
              updateProgress(90,100);
              dernierIdFait++;
              dernierIdRegle++;
              dernierIdRegleFait++;
              updateProgress(95,100);
              //mise a jour des elements pour la table
              regleFaitModelAjouter=new regleFaitModel(rgF.getNumero()+1,fait.getLettreFait(),fait.getLibelleFait(),1);
              regleFaitModelAjouter.setFait(fait);
              regleFaitModelAjouter.setListRegleFait(listFmRegle);
              regleFaitModelAjouter.setIdentifiantFait(fait.getLettreFait());
              list.add(regleFaitModelAjouter);
              //listFaitConclusion.getItems().
               updateProgress(100,100);
           updateMessage("terminer");
            
           em.getTransaction().commit();
          }catch (Exception e) {
             // System.out.println(""+e.getLocalizedMessage());
             // System.out.println(""+e.getMessage());
              //System.out.println(""+e.getCause().toString());
              Logger.getLogger(ExpertController.class.getName()).log(Level.SEVERE, null, e);
              updateMessage("erreur");
          }
        return true;
      }
    };
  }
     private void creationregle()
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Mise a jour de votre Base de connaissance");
               alert.show();
               Task copyWorker =ProccessusCreationRegle();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("erreur".equals(newValue))
              {
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Erreur");
               alert.setContentText("Une erreur est Survenue lors de l'operation causant un arret du processus \n"
                       + " Veuillez reessayer SVP !!!!!!");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
              }
              if("terminer".equals(newValue))
              {
               alert.setContentText("operation Reussie");
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                initialiserLesElementsDeConception();
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin Mise a jour ");
               alert.setContentText("Connaissance acquise avec succes");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
              }
              else{
             alert.setContentText(newValue);   
              }
         }
                });
      new Thread(copyWorker).start();
         }
     private List<String> alphabet;
     private DetailsFaitController ctr_details;
     private FXMLLoader loader;
     private Stage st;
     private boolean fin=false;
     private int compteurMax=0;
     private regleFaitModel regleFaitModelAjouter;
}
