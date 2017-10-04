/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Inserer_alimentController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField nutr_eau;

    @FXML
    private TableColumn<mainModel, Double> numero;

    @FXML
    private TextField min_cu;

    @FXML
    private Button enreistrer;

    @FXML
    private TableColumn<mainModel, Double> vitb9;

    @FXML
    private TextField min_fer;

    @FXML
    private TextField nutr_energie;

    @FXML
    private TableColumn<mainModel, Double> Energie;

    @FXML
    private TextField min_ca;

    @FXML
    private TableColumn<mainModel, Double> fer;

    @FXML
    private TextField vit_riboflavin;

    @FXML
    private TableColumn<mainModel, Double> vite;

    @FXML
    private TableColumn<mainModel, Double> vitc;

    @FXML
    private TextField info_nomEng;

    @FXML
    private TableColumn<mainModel, Double> ka;

    @FXML
    private TextField info_nomFr;

    @FXML
    private TextField nutr_protide;
    
    @FXML private Label codes;

    @FXML
    private TableColumn<mainModel, Double> vita;

    @FXML
    private TextField min_ka;

    @FXML
    private TableColumn<mainModel, Double> mg;

    @FXML
    private TableColumn<mainModel, Double> nom_aliment;
    @FXML
    private TableColumn<mainModel, String> pays;

    @FXML
    private ComboBox<String> info_pays;

    @FXML
    private TextField min_phos;
    @FXML
    private TextField min_mg;

    @FXML
    private TableColumn<mainModel, Double> cloumPcGlucide;

    @FXML
    private TableColumn<mainModel, Double> cloumPclipide;

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
    private TableColumn<mainModel, Double> na;

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

    @FXML
    private TableColumn<mainModel, Double> Prprotide;

    @FXML
    private TableView<mainModel> aliment;
    
    @FXML private ComboBox<FmPathologie> info_path;

    @FXML
    private TextField vit_b12;

    @FXML private TextField vit_e;
    @FXML private TextField vit_d;
    @FXML TextField vit_b6;
    @FXML private Button btncancel; 
    List<FmGroupeAliment> listeCategorie;
    List<FmPathologie> listePathologie;
   
    public Inserer_alimentController() {
        listeCategorie=modelFoodSelect.listeCategories(); 
        listePathologie=modelFoodSelect.listePathologie();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialiserableauAliment();
       ActionSurLeBoutonCategorie();
       ActionSurPathologie();
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
           //et on laisse la main a l'utilisateur pour entrer les valauers
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
     public void initialiserPathologie()
    {
        info_path.getItems().clear();
         info_path.setItems(FXCollections.observableList(listePathologie));
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
      public void initialiserableauAliment()
    {
       numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
       nom_aliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
       cloumPcGlucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
       cloumPclipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
       Prprotide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
       Energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer.setCellValueFactory(new PropertyValueFactory<>("fer"));
       mg.setCellValueFactory(new PropertyValueFactory<>("mg"));
       na.setCellValueFactory(new PropertyValueFactory<>("na"));
       ka.setCellValueFactory(new PropertyValueFactory<>("ka"));
       vitc.setCellValueFactory(new PropertyValueFactory<>("vitc"));
       vite.setCellValueFactory(new PropertyValueFactory<>("vite"));
       vitb9.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
       vita.setCellValueFactory(new PropertyValueFactory<>("vita"));   
         pays.setCellValueFactory(new PropertyValueFactory<>("pays"));  
        
       nom_aliment.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
      
         
      //pour la table Suivante
    
      
     aliment.setItems(formulyTools.getobservableListMainModel());
//     aliment.getColumns().addAll(numero,nom_aliment,qte,cloumPcGlucide,cloumTtPcGlucide,cloumPclipide,cloumTtPclipide,cloumPcprotide,cloumTtPcprotide
//      ,Energie,fer,mg,na,ka,vitc,vite,vitb9,vita);
   //initialisation des colonnes pour tous les aliments   Burkinabé
     
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
             enregistrerAliment();
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
                  enregistrerAliment();
                   // System.out.println(" insertion");
                }
                   }
         //si non on affiche que les valeurs sont toutes nulles
           //et on laisse la main a l'utilisateur pour entrer les valauers
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
      public void insererAlimentTableau()
      {
          mainModel nouvelleEntre=creerInstanceMainModel();
          mainModel first=aliment.getItems().get(0);
          int taille=aliment.getItems().size();
          aliment.getItems().set(0, nouvelleEntre);
           first.setNumero(taille+1);
          aliment.getItems().add(first);
          nouvelleEntre=null;
          first=null;
          
      }
      public mainModel creerInstanceMainModel()
      {
            mainModel main;
            main = new mainModel();
       String nomFr=info_nomFr.getText();
   String nomng=info_nomEng.getText();
   String surnom=info_surnom.getText();
   String modeCuisson=info_modeCuisson.getValue();
   FmGroupeAliment categorie=info_categorie.getValue();
   String pays =info_pays.getValue();
   int idAl=formulyTools.TrouverDernierIdentifiant_Aliment()+1;
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
  main.setNumero(1);
        
      return main;
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
   
   String nomFr=info_nomFr.getText().toLowerCase();
   String nomng=info_nomEng.getText().toLowerCase();
   String surnom=info_surnom.getText().toLowerCase();
   String modeCuisson=info_modeCuisson.getValue();
   FmGroupeAliment categorie=info_categorie.getValue();
   String pays =info_pays.getValue();
   int idAl=formulyTools.TrouverDernierIdentifiant_Aliment()+1;
    int idAlPath=formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1;
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
     FmPathologie path=info_path.getValue();
     FmAlimentsPathologie fmp=(path!=null || !path.getLibelle().equals("aucun choix"))?new FmAlimentsPathologie(idAlPath):null;
       if(fmp != null)
       {
         fmp.setAliment(aliments);
         fmp.setPathologie(path);
         fmp.setDate(new Timestamp(new Date().getTime()));
       }
   //   codes.setText(code);
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
  try {
            
           em.getTransaction().begin(); 
           em.persist(aliments); 
           updateProgress(25, 10);
            updateMessage("aliment");
           Thread.sleep(100);
            Thread.sleep(100);
           em.persist(retNu);
           updateProgress(50, 10);
            updateMessage("nutriments");
             Thread.sleep(100);
            em.persist(retMin);
           updateProgress(75, 10);
          updateMessage("mineraux");
             Thread.sleep(100);
             if(fmp!=null)
         {
           em.persist(fmp);
           updateProgress(80, 10);
            Thread.sleep(90);
         }
           em.persist(retVit);    
           updateProgress(100, 10);
           updateMessage("terminer");
           em.getTransaction().commit();
            
          } catch (Exception e) {
          }
         
         
          
      
        return true;
      }
    };
  }
         public void enregistrerAliment()
         {
            ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Enregistrement d'aliment");
               alert.show();
               Task copyWorker = createWorker();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              System.out.println("new value: "+newValue);
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                   alert.setGraphic(null);
                    alert.setTitle("Aliment enregistré");
               alert.setContentText("Votre Aliment a ete enregistré avec succes:");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
                insererAlimentTableau();
           initialiserLesTextFieldValeur(nutr_ash,nutr_eau,nutr_energie,nutr_fibre,nutr_glucide,nutr_lipide,nutr_protide,vit_a,vit_b1,vit_b12,vit_b2,vit_b6,vit_c,vit_d,vit_e,vit_folates,vit_niacines,vit_riboflavin,vit_thiamin);
           initialiserLesTextFieldInfoAliment(info_nomEng,info_nomFr,info_surnom);
            alert.showAndWait();
            initialiserPays();
            intialiserModeCuisson();
            initialiserCategorie();
          initialiserPathologie();
              }
              else{
               
              }
         }
                });
        
         registerThread= new Thread(copyWorker);
         registerThread.start();
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
