/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmGroupeAliment;
import formuly.entities.FmPathologie;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.modelFoodSelect;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Modifier_info_alimentController implements Initializable {

    /**
     * Initializes the controller class.
     */
           @FXML
    private TextField nutr_eau;

    @FXML
    private TextField min_cu;

    @FXML
    private Button enreistrer;


    @FXML
    private TextField min_fer;

    @FXML
    private TextField nutr_energie;


    @FXML
    private TextField min_ca;
   
    @FXML
    private TextField vit_riboflavin;

    @FXML
    private TextField info_nomEng;


    @FXML
    private TextField info_nomFr;

    @FXML
    private TextField nutr_protide;
    
    @FXML private Label codes;


    @FXML
    private TextField min_ka;

    @FXML
    private ComboBox<String> info_pays;

    @FXML
    private TextField min_phos;
    @FXML
    private TextField min_mg;

    @FXML
    private TextField nutr_ash;

    @FXML
    private ComboBox<String> info_modeCuisson;

    @FXML
    private TextField nutr_lipide;

    @FXML
    private TextField vit_folates;

    @FXML
    private TextField nutr_glucide;

    @FXML
    private ComboBox<FmGroupeAliment> info_categorie;
    @FXML
    private TextField vit_a;

    @FXML
    private TextField vit_thiamin;

    @FXML
    private TextField vit_b1;

    @FXML
    private TextField nutr_fibre;

    @FXML
    private TextField vit_b2;

    @FXML
    private TextField vit_niacines;

    @FXML
    private TextField min_zn;

    @FXML
    private TextField info_surnom;

    @FXML
    private TextField min_na;
     @FXML
    private TextField vit_c;

    
    @FXML private ComboBox<FmPathologie> info_path;

    @FXML
    private TextField vit_b12;

    @FXML private TextField vit_e;
    @FXML private TextField vit_d;
    @FXML TextField vit_b6;
    @FXML private Button btncancel; 
    List<FmGroupeAliment> listeCategorie;
    List<FmPathologie> listePathologie;
    private boolean minNew;
    private boolean vitNew;
    private boolean nuNew;
    mainModel main;
    TableView<mainModel> Tablemodel;
    private int position=-1;
    public Modifier_info_alimentController(mainModel model) {
           listeCategorie=modelFoodSelect.listeCategories(); 
        //   listePathologie=modelFoodSelect.listePathologie();
           this.main=model;
             minNew=false;
             vitNew=false;
             nuNew=false;
    }
    public Modifier_info_alimentController(mainModel model,TableView<mainModel> table) {
           listeCategorie=modelFoodSelect.listeCategories(); 
        //   listePathologie=modelFoodSelect.listePathologie();
           this.main=model;
           this.Tablemodel=table;
            minNew=false;
           vitNew=false;
            nuNew=false;
            
    }
      public Modifier_info_alimentController(mainModel model,TableView<mainModel> table,int indexCliquer) {
           listeCategorie=modelFoodSelect.listeCategories(); 
        //   listePathologie=modelFoodSelect.listePathologie();
           this.main=model;
           this.Tablemodel=table;
            minNew=false;
           vitNew=false;
            nuNew=false;
            position=indexCliquer;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ActionSurLeBoutonCategorie();
      // ActionSurPathologie();
       initialiserPays();
       intialiserModeCuisson();
       textConverter(nutr_ash,nutr_eau,nutr_energie,nutr_fibre,nutr_glucide,nutr_lipide,nutr_protide,vit_a,vit_b1,vit_b12,vit_b2,vit_folates,vit_niacines,vit_riboflavin,vit_thiamin,min_ca,min_cu,min_fer,min_ka,min_mg,min_na,min_phos,min_zn,vit_b6,vit_e,vit_d,vit_c);
       enreistrer.setOnAction(
            event->{
            traiterValeur();
            });
       btncancel.setOnAction(event->{
           fermerFenetre(btncancel);
          });
       initialiserValeurLancement(main);
      
       
    }
    public void initialiserValeurLancement(mainModel main)
    {
        //initialisation des info aliments 
        info_categorie.setValue(main.getAliment().getGroupe());
        info_modeCuisson.setValue(main.getAliment().getModeCuisson());
        info_nomEng.setText(main.getAliment().getNomEng());
        info_nomFr.setText(main.getAliment().getNomFr());
        info_pays.setValue(main.getAliment().getPays());
        info_surnom.setText(main.getAliment().getSurnom());
        //initialisation des valeurs en vitamie
        vit_niacines.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getNiacine():0.0));
        vit_folates.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getFolates():0.0));
        vit_riboflavin.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getRiboflavin():0.0));
        vit_thiamin.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getThiamin():0.0));
        vit_a.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVita():0.0));
        vit_b1.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitb1():0.0));
        vit_b2.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitb2():0.0));
        vit_b12.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitb12():0.0));
        vit_b6.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitb6():0.0));
        vit_c.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitc():0.0));
        vit_d.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVitd():0.0));
        vit_e.setText(String.valueOf((main.getRetVit()!=null)?main.getRetVit().getVite():0.0));
        
        //initialisation en nutriments
          nutr_ash.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getAsh():0.0));
          nutr_eau.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getEau():0.0));
          nutr_energie.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getEnergieKcal():0.0));
          nutr_fibre.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getFibre():0.0));
          nutr_glucide.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getGlucide():0.0));
          nutr_lipide.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getLipide():0.0));
          nutr_protide.setText(String.valueOf((main.getRetNu()!=null)?main.getRetNu().getProtein():0.0));
       //initialisation mineraux
         min_ca.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getCa():0.0));  
         min_cu.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getCu():0.0));  
         min_fer.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getFe():0.0));
         min_mg.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getMg():0.0));  
         min_na.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getNa():0.0));
        min_phos.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getPhos():0.0));  
        min_ka.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getPota():0.0));  
        min_zn.setText(String.valueOf((main.getRetMin()!=null)?main.getRetMin().getZn():0.0));  
                
    }
       /**
     * methode permettant la fermerture du bouton qui suit le
     * meme algorithme que celui pour l'enregistrement daliment (message davertissement)
     * @param btn 
     */
    public void fermerFenetre(Button btn)
    {
     Stage stage = (Stage) btn.getScene().getWindow();
               List<ComboBox> cmbo=null;
          List<TextField> txtFNul=null;
          List<TextField> txtFVide=null;
          txtFNul=ControlTextFieldObligatoireNul(nutr_glucide,nutr_lipide,nutr_protide);
          txtFVide=ControlTextFieldObligatoireVide(nutr_glucide,nutr_lipide,nutr_protide);
          cmbo=ControlComboBoxObligatoire(info_pays,info_modeCuisson,info_categorie);
          Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg")
      );
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Avertissement");
            alert.setHeaderText("Interruption de Travail\n");
            alert.setGraphic(new ImageView(image));
              String Info="";
            alert.setContentText(Info);
          
  if(!info_nomFr.getText().isEmpty() && cmbo.isEmpty())  
   {
    //verification si il ny a pas de valeur vide
       if(txtFVide.isEmpty())
       {
      //verification si des valeurs ont ete entré
               if(txtFNul.isEmpty())
                {
            //message pour prevenir avant de fermer 
                    String message="Vous allez Interrompre votre travail en cour \n"
             + "Souhaitez Vous Vraiment Fermer cette Fenetre ? \n"
             + " merci de confirmer ";
                String titre="Avertissement";
                String enetete="Interruption de Travail";
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText(message);
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
        
             if(alert.getResult()==ButtonType.YES)
                {
                  stage.close();
                   // System.out.println(" insertion");
                }
                }
      //si non on verifie si toutes les valeurs saisie ne sont pas nulles
            else{
             //verification de toutes les valeurs ne sont pas nulles
             //dans ce cas on peut inserer 
                  if(txtFNul.size()<3)
                   {
            // inserAliment();
               //demander de confirmer les valeurs
                String message="Vous allez Interrompre votre travail en cour \n"
             + "Souhaitez Vous Vraiment Fermer cette Fenetre ? \n"
             + " merci de confirmer ";
                String titre="Avertissement";
                String enetete="Interruption de Travail";
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText(message);
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
        
             if(alert.getResult()==ButtonType.YES)
                {
                  stage.close();
                   // System.out.println(" insertion");
                }
                   }
         //si non on affiche que les valeurs sont toutes nulles
           //et on laisse la mainTemp a l'utilisateur pour entrer les valauers
               else{
           stage.close();
                   }
                }
        } 
       //dans ce cas on affiche que les valeurs sont vides
    else{
   stage.close();
        }
    }
  //on affiche le message que les informations liea
  //l'aliment non pas ete correctement entre
else{
    stage.close();
  }
    }
    public void initialiserPays()
    {
        
     List<String> listPays = new ArrayList<String>();
         listPays.add("General");
        listPays.add("Cote Ivoire");
          listPays.add("Burkina Faso");
        listPays.add("Mali");
        listPays.add("Nigeria");
        listPays.add("Ghana");
        listPays.add("Gambie");
        listPays.add("Nigeria");
        listPays.add("Niger");
        listPays.add("Senegal");
        listPays.add("Guinee");
        listPays.add("Ghana");
        listPays.add("Benin");
        info_pays.getItems().clear();
        info_pays.setItems(FXCollections.observableList(listPays));
    }
    public void intialiserModeCuisson()
    {
     info_modeCuisson.getItems().clear();
     info_modeCuisson.setItems(modelFoodSelect.listeDesMode_cuissons());
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
    public void initialiserCategorie()
    {
        info_categorie.getItems().clear();
         info_categorie.setItems(FXCollections.observableList(listeCategorie));
    }
    
    public void ActionSurLeBoutonCategorie()
    {
      info_categorie.setItems(FXCollections.observableList(listeCategorie));
          info_categorie.getSelectionModel().selectFirst();
          info_categorie.setCellFactory(new Callback<ListView<FmGroupeAliment>,ListCell<FmGroupeAliment>>(){
            @Override
            public ListCell<FmGroupeAliment> call(ListView<FmGroupeAliment> l){
                return new ListCell<FmGroupeAliment>(){
                    @Override
                    protected void updateItem(FmGroupeAliment item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                         setText(item.getNomFr());
                        }
                    }
                } ;
            }
        });
           info_categorie.setConverter(new StringConverter<FmGroupeAliment>() {
              @Override
              public String toString(FmGroupeAliment user) {
                if (user == null){
                  return null;
                } else {
                  return user.getNomFr();
                }
              }

            @Override
            public FmGroupeAliment fromString(String userId) {
                return null;
            }
        });
        
    }
     public void ActionSurPathologie()
    {
      info_path.setItems(FXCollections.observableList(listePathologie));
           info_path.getSelectionModel().selectFirst();
          info_path.setCellFactory(new Callback<ListView<FmPathologie>,ListCell<FmPathologie>>(){
            @Override
            public ListCell<FmPathologie> call(ListView<FmPathologie> l){
                return new ListCell<FmPathologie>(){
                    @Override
                    protected void updateItem(FmPathologie item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                         setText(item.getLibelle());
                        }
                    }
                } ;
            }
        });
           info_path.setConverter(new StringConverter<FmPathologie>() {
              @Override
              public String toString(FmPathologie user) {
                if (user == null){
                  return null;
                } else {
                  return user.getLibelle();
                }
              }

            @Override
            public FmPathologie fromString(String userId) {
                return null;
            }
        });
        
    }
    
      public void traiterValeur()
      {
         //info sur aliment
          List<ComboBox> cmbo=null;
          List<TextField> txtFNul=null;
          List<TextField> txtFVide=null;
          txtFNul=ControlTextFieldObligatoireNul(nutr_glucide,nutr_lipide,nutr_protide);
          txtFVide=ControlTextFieldObligatoireVide(nutr_glucide,nutr_lipide,nutr_protide);
          cmbo=ControlComboBoxObligatoire(info_pays,info_modeCuisson,info_categorie);
          Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg")
      );
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Avertissement");
            alert.setHeaderText("Erreur Rencontrer \n");
            alert.setGraphic(new ImageView(image));
              String Info="";
            alert.setContentText(Info);
          
  if(!info_nomFr.getText().isEmpty() && cmbo.isEmpty())  
   {
    //verification si il ny a pas de valeur vide
       if(txtFVide.isEmpty())
       {
      //verification si des valeurs ont ete entré
               if(txtFNul.isEmpty())
                {
            //on peut inserer sans crainte
             mofificationAliment();
                }
      //si non on verifie si toutes les valeurs saisie ne sont pas nulles
            else{
             //verification de toutes les valeurs ne sont pas nulles
             //dans ce cas on peut inserer 
                  if(txtFNul.size()<3)
                   {
            // inserAliment();
               //demander de confirmer les valeurs
                String message="Certaines Valueurs Requises en Nutriment sont nulles \n"
             + "  Lipide,Glucide,Protide . Les macro Nutriments Nul "
                     + " seront enregistrer comme telles avec des valeurs nulles\n"
             + " Veuillez confirmer SVP ";
                String titre="Avertissement Avant Insertion";
                String enetete="Insertion avec valeur Nulle";
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText(message);
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
        
             if(alert.getResult()==ButtonType.YES)
                {
                  mofificationAliment();
                   // System.out.println(" insertion");
                }
                   }
         //si non on affiche que les valeurs sont toutes nulles
           //et on laisse la mainTemp a l'utilisateur pour entrer les valauers
               else{
             String message="Les Valeurs Requises en Nutriment sont nulles \n"
             + "  Lipide,Glucide,Protide .ces compositions sont obligatoires. et "
                     + " Elles ne peuvent pas etre Toutes Nulles \n"
             + " Veuillez ressayez SVP ";
             alert.setContentText(message);
             alert.setAlertType(Alert.AlertType.INFORMATION);
             alert.showAndWait();
                   }
                }
        } 
       //dans ce cas on affiche que les valeurs sont vides
    else{
      String message="Certaines Valeurs Requises en Nutriment sont Vides \n"
             + "  Lipide ,Glucide,Protide .ces compositions sont obligatoires. et "
                     + "Ne peuvent pas etre Toutes Nulles ou vide \n"
             + " Veuillez ressayez SVP ";
      alert.setAlertType(Alert.AlertType.INFORMATION);
     alert.setContentText(message);
     alert.showAndWait();
        }
    }
  //on affiche le message que les informations liea
  //l'aliment non pas ete correctement entre
else{
    String message="Les Informations liées a l'aliment n'ont pas correctement\n"
             + " ete saisie. Les champs (NomFr,pays,categorie et mode cuisson obligatoire\n"
             + " Veuillez ressayez SVP ";
     alert.setAlertType(Alert.AlertType.INFORMATION);
     alert.setContentText(message);
     alert.showAndWait();
    }
  
          
      }
     
      public void miseAJourMain()
      {
      mainModel  mainTemp = new mainModel();
     String nomFr=info_nomFr.getText();
   String nomng=info_nomEng.getText();
   String surnom=info_surnom.getText();
   String modeCuisson=(info_modeCuisson.getValue()!=null)?info_modeCuisson.getValue():main.getAliment().getModeCuisson();
   FmGroupeAliment categorie=(info_categorie.getValue().getNomFr().equals("aucun choix"))?info_categorie.getValue():main.getAliment().getGroupe();
   String pays =(info_pays.getValue()!=null)?info_pays.getValue():main.getAliment().getPays();
   int idAl=0;
   String code=(categorie.getId()>9)?String.valueOf(categorie.getId())+"_"+idAl:"0"+categorie.getId()+"_"+idAl;
      //creation de l'aliment
   FmAliments aliments=new FmAliments(idAl);

  // aliments.setId(idAl);
   aliments.setNomFr(nomFr);
   aliments.setNomEng(nomng);
   aliments.setSurnom(surnom);
   aliments.setModeCuisson(modeCuisson);
   aliments.setPays(pays);
   aliments.setGroupe(categorie);
   aliments.setCode(code);
        //  System.out.println("code: "+code);
       //info sur nutriment
   double glucide=Double.parseDouble(nutr_glucide.getText());
   double lipide=Double.parseDouble(nutr_lipide.getText());
   double protide=Double.parseDouble(nutr_protide.getText());
   double energie=(!nutr_energie.getText().isEmpty())?Double.parseDouble(nutr_energie.getText()):0.0;
   double fibre=(!nutr_fibre.getText().isEmpty())?Double.parseDouble(nutr_fibre.getText()):0.0;
   double ash=(!nutr_ash.getText().isEmpty())?Double.parseDouble(nutr_ash.getText()):0.0;
   double eau=(!nutr_eau.getText().isEmpty())?Double.parseDouble(nutr_eau.getText()):0.0;
     //creation de l'obdjet retentionNutriment
   FmRetentionNutriments retNu=new FmRetentionNutriments();
  int  idNutr=formulyTools.TrouverDernierIdentifiant_RetentionNutriment()+1;
   retNu.setAliment(aliments);
   retNu.setId(idNutr);
   retNu.setGlucide((float)glucide);
   retNu.setProtein((float)protide);
   retNu.setLipide((float)lipide);
   retNu.setEau((float)eau);
   retNu.setEnergieKcal((float)energie);
   retNu.setAsh((float)ash);
   retNu.setFibre((float)fibre);   
   //info sur vitamine
   double vitA=(!vit_a.getText().isEmpty())?Double.parseDouble(vit_a.getText()):0.0;
  double vitB1=(!vit_b1.getText().isEmpty())?Double.parseDouble(vit_b1.getText()):0.0;
  double vitB2=(!vit_b2.getText().isEmpty())?Double.parseDouble(vit_b2.getText()):0.0;
  double vitB6=(!vit_b6.getText().isEmpty())?Double.parseDouble(vit_b6.getText()):0.0;
  double vitB12=(!vit_b12.getText().isEmpty())?Double.parseDouble(vit_b12.getText()):0.0;
  double vitNiacine=(!vit_niacines.getText().isEmpty())?Double.parseDouble(vit_niacines.getText()):0.0;
  double vitRiboflavin=(!vit_riboflavin.getText().isEmpty())?Double.parseDouble(vit_riboflavin.getText()):0.0;
  double vitFolate=(!vit_folates.getText().isEmpty())?Double.parseDouble(vit_folates.getText()):0.0;
  double vitThiamin=(!vit_thiamin.getText().isEmpty())?Double.parseDouble(vit_thiamin.getText()):0.0;
  double vitE=(!vit_e.getText().isEmpty())?Double.parseDouble(vit_e.getText()):0.0;
  double vitD=(!vit_d.getText().isEmpty())?Double.parseDouble(vit_d.getText()):0.0;
   double vitC=(!vit_c.getText().isEmpty())?Double.parseDouble(vit_c.getText()):0.0;
    //creation de l'obdjet retentionVitamines
   FmRetentionVitamines retVit=new FmRetentionVitamines();
   int  idVit=formulyTools.TrouverDernierIdentifiant_RetentionVitamines()+1;
   retVit.setId(idVit);
   retVit.setAliment(aliments);
   retVit.setFolates((float)vitFolate);
   retVit.setNiacine((float)vitNiacine);
   retVit.setRiboflavin((float)vitRiboflavin);
   retVit.setThiamin((float)vitThiamin);
   retVit.setVita((float)vitA);
   retVit.setVitb1((float)vitB1);
   retVit.setVitb2((float)vitB2);
   retVit.setVitb12((float)vitB12);
  retVit.setVitb6((float)vitB6);
   retVit.setVitc((float)vitC);
  retVit.setVitd((float)vitD);
  retVit.setVite((float)vitE);
 
  //info sur sel mineraux
   double minCa=(!min_ca.getText().isEmpty())?Double.parseDouble(min_ca.getText()):0.0;
   double minCu=(!min_cu.getText().isEmpty())?Double.parseDouble(min_cu.getText()):0.0;
   double minKa=(!min_ka.getText().isEmpty())?Double.parseDouble(min_ka.getText()):0.0;
   double minNa=(!min_na.getText().isEmpty())?Double.parseDouble(min_na.getText()):0.0;
   double minPho=(!min_phos.getText().isEmpty())?Double.parseDouble(min_phos.getText()):0.0;
   double minMg=(!min_mg.getText().isEmpty())?Double.parseDouble(min_mg.getText()):0.0;
   double minFer=(!min_fer.getText().isEmpty())?Double.parseDouble(min_fer.getText()):0.0;
   double minZn=(!min_zn.getText().isEmpty())?Double.parseDouble(min_zn.getText()):0.0;
   //creation de l'obdjet retentionMineraux
   FmRetentionMineraux retMin=new FmRetentionMineraux();  
 int  idMin=formulyTools.TrouverDernierIdentifiant_RetentionMineraux()+1;
  retMin.setAliment(aliments);
  retMin.setId(idMin);
  retMin.setCa((float)minCa);
  retMin.setCu((float)minCu);
  retMin.setPota((float)minKa);
  retMin.setNa((float)minNa);
  retMin.setPhos((float)minPho);
  retMin.setMg((float)minMg);
  retMin.setFe((float)minFer);
  retMin.setZn((float)minZn);
   Date date =new Date();
  aliments.setDerniereModif(new Timestamp(date.getTime()));
        //proccedure d'insertion
  mainTemp.setCloumPcGlucide(glucide);
  mainTemp.setCloumPclipide(lipide);
  mainTemp.setCloumPcprotide(protide);
  mainTemp.setNom_aliment(nomFr);
  mainTemp.setEnergie(energie);
  mainTemp.setFer(minFer);
  mainTemp.setIdAliment(aliments.getId());
  mainTemp.setPays(pays);
  mainTemp.setKa(minKa);
  mainTemp.setMg(minMg);
  mainTemp.setNa(minNa);
  mainTemp.setVita(vitA);
  mainTemp.setVitb9(vitFolate);
  mainTemp.setVite(vitE);
  mainTemp.setVitc(vitC);
  mainTemp.setNumero(1);
  mainTemp.setAliment(aliments);
  mainTemp.setRetMin(retMin);
  mainTemp.setRetNu(retNu);
  mainTemp.setRetVit(retVit);
          
      main.getAliment().setModeCuisson(mainTemp.getAliment().getModeCuisson());
      main.getAliment().setNomEng(mainTemp.getAliment().getNomEng());
      main.getAliment().setNomFr(mainTemp.getAliment().getNomFr());
      main.getAliment().setPays(mainTemp.getAliment().getPays());
      main.getAliment().setGroupe(mainTemp.getAliment().getGroupe());
      main.getAliment().setSurnom(mainTemp.getAliment().getSurnom());
          System.out.println("nom :"+   main.getAliment().getNomFr());
           System.out.println("pays :"+   main.getAliment().getPays());
             System.out.println("groupe :"+   main.getAliment().getGroupe().getNomFr());
      int idRetvit=(main.getRetVit()!=null)?main.getRetVit().getId():0;
      int idRetNut=(main.getRetNu()!=null)?main.getRetNu().getId():0;
      int idRetMin=(main.getRetMin()!=null)?main.getRetMin().getId():0;
      FmAliments aliment=main.getAliment();
      
      //les autres retention 
          FmRetentionMineraux retMins=mainTemp.getRetMin();
          FmRetentionVitamines retVits=mainTemp.getRetVit();
         FmRetentionNutriments retNus=mainTemp.getRetNu();
         
         //mis a jour 
        if(idRetMin>0)
        {
        retMins.setId(idRetMin);
        }
        else{
        minNew=true;
        }
        retMins.setAliment(aliment);
        if(idRetvit>0)
        {
        retVits.setId(idRetvit);
        }
        else
        {
        vitNew=true;
        }
        retVit.setAliment(aliment);
        if(idRetNut>0)
        {
        retNus.setId(idRetNut);
        }
      else{
        nuNew=true;
        }
        retNus.setAliment(aliment);
        
        //mise a jour pour les aliment
         main.setRetMin(retMins);
         main.setRetNu(retNus);
         main.setRetVit(retVits);
         
         main.setCloumPcGlucide(glucide);
  main.setCloumPclipide(lipide);
  main.setCloumPcprotide(protide);
  main.setNom_aliment(nomFr);
  main.setEnergie(energie);
  main.setFer(minFer);
  main.setIdAliment(aliments.getId());
  main.setPays(pays);
  main.setKa(minKa);
  main.setMg(minMg);
  main.setNa(minNa);
  main.setVita(vitA);
  main.setVitb9(vitFolate);
  main.setVite(vitE);
  main.setVitc(vitC);
      }
      public mainModel creerInstanceMainModel()
      {
            mainModel mainTemp;
            mainTemp = new mainModel();
     String nomFr=info_nomFr.getText();
   String nomng=info_nomEng.getText();
   String surnom=info_surnom.getText();
   String modeCuisson=info_modeCuisson.getValue();
   FmGroupeAliment categorie=(info_categorie.getValue().getNomFr().equals("aucun choix"))?info_categorie.getValue():mainTemp.getAliment().getGroupe();
   String pays =info_pays.getValue();
   int idAl=0;
   String code=(categorie.getId()>9)?String.valueOf(categorie.getId())+"_"+idAl:"0"+categorie.getId()+"_"+idAl;
      //creation de l'aliment
   FmAliments aliments=new FmAliments(idAl);

  // aliments.setId(idAl);
   aliments.setNomFr(nomFr);
   aliments.setNomEng(nomng);
   aliments.setSurnom(surnom);
   aliments.setModeCuisson(modeCuisson);
   aliments.setPays(pays);
   aliments.setGroupe(categorie);
   aliments.setCode(code);
        //  System.out.println("code: "+code);
   codes.setText(code);
       //info sur nutriment
   double glucide=Double.parseDouble(nutr_glucide.getText());
   double lipide=Double.parseDouble(nutr_lipide.getText());
   double protide=Double.parseDouble(nutr_protide.getText());
   double energie=(!nutr_energie.getText().isEmpty())?Double.parseDouble(nutr_energie.getText()):0.0;
   double fibre=(!nutr_fibre.getText().isEmpty())?Double.parseDouble(nutr_fibre.getText()):0.0;
   double ash=(!nutr_ash.getText().isEmpty())?Double.parseDouble(nutr_ash.getText()):0.0;
   double eau=(!nutr_eau.getText().isEmpty())?Double.parseDouble(nutr_eau.getText()):0.0;
     //creation de l'obdjet retentionNutriment
   FmRetentionNutriments retNu=new FmRetentionNutriments();
  int  idNutr=formulyTools.TrouverDernierIdentifiant_RetentionNutriment()+1;
   retNu.setAliment(aliments);
   retNu.setId(idNutr);
   retNu.setGlucide((float)glucide);
   retNu.setProtein((float)protide);
   retNu.setLipide((float)lipide);
   retNu.setEau((float)eau);
   retNu.setEnergieKcal((float)energie);
   retNu.setAsh((float)ash);
   retNu.setFibre((float)fibre);   
   //info sur vitamine
   double vitA=(!vit_a.getText().isEmpty())?Double.parseDouble(vit_a.getText()):0.0;
  double vitB1=(!vit_b1.getText().isEmpty())?Double.parseDouble(vit_b1.getText()):0.0;
  double vitB2=(!vit_b2.getText().isEmpty())?Double.parseDouble(vit_b2.getText()):0.0;
  double vitB6=(!vit_b6.getText().isEmpty())?Double.parseDouble(vit_b6.getText()):0.0;
  double vitB12=(!vit_b12.getText().isEmpty())?Double.parseDouble(vit_b12.getText()):0.0;
  double vitNiacine=(!vit_niacines.getText().isEmpty())?Double.parseDouble(vit_niacines.getText()):0.0;
  double vitRiboflavin=(!vit_riboflavin.getText().isEmpty())?Double.parseDouble(vit_riboflavin.getText()):0.0;
  double vitFolate=(!vit_folates.getText().isEmpty())?Double.parseDouble(vit_folates.getText()):0.0;
  double vitThiamin=(!vit_thiamin.getText().isEmpty())?Double.parseDouble(vit_thiamin.getText()):0.0;
  double vitE=(!vit_e.getText().isEmpty())?Double.parseDouble(vit_e.getText()):0.0;
  double vitD=(!vit_d.getText().isEmpty())?Double.parseDouble(vit_d.getText()):0.0;
   double vitC=(!vit_c.getText().isEmpty())?Double.parseDouble(vit_c.getText()):0.0;
    //creation de l'obdjet retentionVitamines
   FmRetentionVitamines retVit=new FmRetentionVitamines();
   int  idVit=formulyTools.TrouverDernierIdentifiant_RetentionVitamines()+1;
   retVit.setId(idVit);
   retVit.setAliment(aliments);
   retVit.setFolates((float)vitFolate);
   retVit.setNiacine((float)vitNiacine);
   retVit.setRiboflavin((float)vitRiboflavin);
   retVit.setThiamin((float)vitThiamin);
   retVit.setVita((float)vitA);
   retVit.setVitb1((float)vitB1);
   retVit.setVitb2((float)vitB2);
   retVit.setVitb12((float)vitB12);
  retVit.setVitb6((float)vitB6);
   retVit.setVitc((float)vitC);
  retVit.setVitd((float)vitD);
  retVit.setVite((float)vitE);
 
  //info sur sel mineraux
   double minCa=(!min_ca.getText().isEmpty())?Double.parseDouble(min_ca.getText()):0.0;
   double minCu=(!min_cu.getText().isEmpty())?Double.parseDouble(min_cu.getText()):0.0;
   double minKa=(!min_ka.getText().isEmpty())?Double.parseDouble(min_ka.getText()):0.0;
   double minNa=(!min_na.getText().isEmpty())?Double.parseDouble(min_na.getText()):0.0;
   double minPho=(!min_phos.getText().isEmpty())?Double.parseDouble(min_phos.getText()):0.0;
   double minMg=(!min_mg.getText().isEmpty())?Double.parseDouble(min_mg.getText()):0.0;
   double minFer=(!min_fer.getText().isEmpty())?Double.parseDouble(min_fer.getText()):0.0;
   double minZn=(!min_zn.getText().isEmpty())?Double.parseDouble(min_zn.getText()):0.0;
   //creation de l'obdjet retentionMineraux
   FmRetentionMineraux retMin=new FmRetentionMineraux();  
 int  idMin=formulyTools.TrouverDernierIdentifiant_RetentionMineraux()+1;
  retMin.setAliment(aliments);
  retMin.setId(idMin);
  retMin.setCa((float)minCa);
  retMin.setCu((float)minCu);
  retMin.setPota((float)minKa);
  retMin.setNa((float)minNa);
  retMin.setPhos((float)minPho);
  retMin.setMg((float)minMg);
  retMin.setFe((float)minFer);
  retMin.setZn((float)minZn);
   Date date =new Date();
  aliments.setDerniereModif(new Timestamp(date.getTime()));
        //proccedure d'insertion
  mainTemp.setCloumPcGlucide(glucide);
  mainTemp.setCloumPclipide(lipide);
  mainTemp.setCloumPcprotide(protide);
  mainTemp.setNom_aliment(nomFr);
  mainTemp.setEnergie(energie);
  mainTemp.setFer(minFer);
  mainTemp.setIdAliment(aliments.getId());
  mainTemp.setPays(pays);
  mainTemp.setKa(minKa);
  mainTemp.setMg(minMg);
  mainTemp.setNa(minNa);
  mainTemp.setVita(vitA);
  mainTemp.setVitb9(vitFolate);
  mainTemp.setVite(vitE);
  mainTemp.setVitc(vitC);
  mainTemp.setNumero(1);
        return mainTemp;
      }
      public void viderCombobox(ComboBox...cmbo)
      {
         for(ComboBox cmb:cmbo)
         {
         cmb.getItems().clear();
         }
      }
         public Task createWorker() {
      return new Task() {
      @Override
      protected Object call() throws Exception {
       EntityManager em=formulyTools.getEm().createEntityManager();
      //  System.out.println());
           //appel du controller de Jpa 
              
  try {
      em.getTransaction().begin(); 
           updateProgress(5, 100);
            miseAJourMain();
           updateProgress(15, 100);
           updateMessage("mise a jour de l'aliment");
           em.merge(main.getAliment()); 
           updateProgress(25, 100);
            updateMessage("mise a jour des ret en utriments");
           Thread.sleep(30);
           if(!nuNew)
           {
            em.merge(main.getRetNu());
           }
          else{
           em.persist(main.getRetNu());
            }
            updateProgress(50, 100);
            updateMessage("mise a jour des ret en Mineraux");
             Thread.sleep(30);
           if(!minNew)
           {
            em.merge(main.getRetMin());
           }
          else{
             em.persist(main.getRetMin());
           }
            updateProgress(75, 100);
            updateMessage("mise a jour des ret en Vitamine");
             Thread.sleep(30);
           if(!vitNew)
           {
            em.merge(main.getRetVit());   
           }
         else{
           em.persist(main.getRetVit());  
           }
           updateProgress(95, 100);
           updateMessage("actualisation de votre interface");
           updateProgress(100, 100);
           updateMessage("terminer");
           em.getTransaction().commit();
            
          } catch (Exception e) {
               updateMessage("erreur");
            Logger.getLogger(Modifier_info_alimentController.class.getName()).log(Level.SEVERE, null, e);
          }
        return true;
      }
    };
  }
         public void mofificationAliment()
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("modification d'aliment");
               alert.show();
               Task copyWorker = createWorker();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                   alert.setGraphic(null);
                    alert.setTitle("Aliment enregistré");
               alert.setContentText("Votre Aliment a ete mis a jour avec succes:");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
           initialiserLesTextFieldValeur(nutr_ash,nutr_eau,nutr_energie,nutr_fibre,nutr_glucide,nutr_lipide,nutr_protide,vit_a,vit_b1,vit_b12,vit_b2,vit_b6,vit_c,vit_d,vit_e,vit_folates,vit_niacines,vit_riboflavin,vit_thiamin);
           initialiserLesTextFieldInfoAliment(info_nomEng,info_nomFr,info_surnom);
             initialiserPays();
            intialiserModeCuisson();
            initialiserCategorie();
            Tablemodel.getItems().set(position,main);
            alert.showAndWait();
              }
              else{
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
              }
         }
                });
        
      new Thread(copyWorker).start();
         }
      public void initialiserLesTextFieldValeur(TextField...texfield)
      {
        for(TextField txf:texfield)
        {
         txf.setText("0.0");
        }
      }
       public void initialiserLesTextFieldInfoAliment(TextField...texfield)
      {
        for(TextField txf:texfield)
        {
         txf.setText("");
        }
      }
       /**
        * methode qui verifie si il ya une valeur de macroNutriment valide entrer
        * cad valeur superieur A 0.0 , une liste de TexField est passe en parametre
        * qui sont en fait  :LIPIDE ,GLUCIDE ,PROTIDE a priori .cette methode est utile pour 
        * la notification a l'utilisateur si il ya des valeurs 0.0 dans la liste
        * @param texfield
        * @return 
        */
        public List<TextField> ControlTextFieldObligatoireNul(TextField...texfield)
      {
          List<TextField> txt=new LinkedList<>();
        for(TextField txf:texfield)
        {
          if(Double.parseDouble(txf.getText())==0.0)
          {
           txt.add(txf);
          }
        }
        return txt;
      }
        /**
         * methode qui prend en parametre une liste de TexField et verifie si il ya resence de contenu vide
         * 
         * @param texfield une Liste de TexField concerne
         * @return une liste 
         */
         public List<TextField> ControlTextFieldObligatoireVide(TextField...texfield)
      {
          List<TextField> txt=new LinkedList<>();
        for(TextField txf:texfield)
        {
          if(txf.getText().isEmpty())
          {
           txt.add(txf);
          }
        }
        return txt;
      }
         public List<ComboBox> ControlComboBoxObligatoire(ComboBox...cmbo)
      {
          List<ComboBox> cmbobox=new LinkedList<>();
        for(ComboBox liste:cmbo)
        {
          if(liste.getValue() == null)
          {
          cmbobox.add(liste);
          }
        }
        return cmbobox;
      }
        private Thread registerThread;
        private mainModel modelTableau;
    }    
    
