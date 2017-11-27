/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.expert.Simplexe;
import formuly.expert.pearsonCalcul;
import formuly.model.frontend.mainModel;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class PearsonResolveController implements Initializable {

    @FXML
    private TableColumn<mainModel,Double> table1_numero;

    @FXML
    private TableColumn<mainModel, String> table1_categorie;

    @FXML
    private TableColumn<mainModel, String> table1_modeCuisson;

    @FXML
    private TableColumn<mainModel, Double> table1_glucide;

    @FXML
    private Button saisiePixUnitaire;


    @FXML
    private TextField recherche;

    @FXML
    private TableColumn<mainModel, String> table1_pays;
    
     @FXML
    private TableColumn<mainModel, String> table2_pays;
    
    @FXML
    private TableColumn<mainModel, String> choix;

    @FXML
    private TableView<mainModel> table1;

    @FXML
    private TableColumn<mainModel, Double> table1_lipide;

    @FXML
    private TableColumn<mainModel, String> table1_nomFr;

    @FXML
    private TextField valeurMax;
    
   @FXML
    private ComboBox<String> listeNutriment;

    @FXML
    private Button lancerCalcul;

     @FXML
    private TableView<mainModel> tableChoix;

    @FXML
    private TableColumn<mainModel, Double> table1_protide;
    
    @FXML
    private TableColumn<mainModel, Double> table2_protide;
    
     @FXML
    private TableColumn<mainModel, Double> table2_lipide;
     
      @FXML
    private TableColumn<mainModel, String> table2_prixUn;
    
     @FXML
    private TableColumn<mainModel, String> table2_supp;
     
     @FXML
    private TableColumn<mainModel, Double> table2_glucide;
     
     @FXML
    private TableColumn<mainModel, Double> table2_numero;
     
       @FXML
    private TableColumn<mainModel, String> table2_nomAliment;

    
       ObservableList<mainModel> listeRecherche;
       ObservableList<mainModel> listeAliments;
        private ObservableList<mainModel> obsListTable1 ;
        private final double [][] matrix;
        public static int Nombre_MAX_ALIMENT=2;
        public int compteurSelection=0;
        private Simplexe simplexe;
        private final List<String> ListElementsAequilibrer;
        pearsonCalcul pearsonCal;
    public PearsonResolveController() {
         listeAliments=formulyTools.getobservableListMainModel();
         listeRecherche=listeAliments;
           matrix=new double[Nombre_MAX_ALIMENT][Nombre_MAX_ALIMENT];  
           colonneDroite=new double[Nombre_MAX_ALIMENT];
           resultat=new double[Nombre_MAX_ALIMENT];
           ListElementsAequilibrer=new ArrayList<>();
            initialiserElementAEquilibrer();
    }
     private void initialiserElementAEquilibrer()  
     {
        ListElementsAequilibrer.add("Na");
        ListElementsAequilibrer.add("Ka");
        ListElementsAequilibrer.add("Cu");
        ListElementsAequilibrer.add("Ca");
        ListElementsAequilibrer.add("Mg");
        ListElementsAequilibrer.add("Phos");
        ListElementsAequilibrer.add("Fer");
        ListElementsAequilibrer.add("Zn");
        ListElementsAequilibrer.add("Ash");
        ListElementsAequilibrer.add("fibre");
        ListElementsAequilibrer.add("eau");
        ListElementsAequilibrer.add("niacine");
        ListElementsAequilibrer.add("vitA");
        ListElementsAequilibrer.add("vitE");
        ListElementsAequilibrer.add("vitC");
        ListElementsAequilibrer.add("vitD");
        ListElementsAequilibrer.add("vitB1");
        ListElementsAequilibrer.add("vitB2");
        ListElementsAequilibrer.add("vitB6");
        ListElementsAequilibrer.add("vitB12");
        ListElementsAequilibrer.add("folates");
        ListElementsAequilibrer.add("thiamin");
        ListElementsAequilibrer.add("riboflavin");
        
     }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           initialiserTab1(listeAliments);
           recherche.setOnKeyReleased(event->{
           listeRecherche=formulyTools.retourneListParCritere(recherche.getText(),listeAliments);
           initialiserTab1(listeRecherche);  
          });
         //rendons la cellule prix unitaire editable
                 formulyTools.rendreCelluleEditable(tableChoix,table2_prixUn);
                  lancerCalcul.setOnAction(event->{
                  LancerCalculPearson();
                  });
            textConverter(valeurMax);
          initialisationCombobox();
    }    
      public void initialiserTab1(ObservableList<mainModel> model)
     {
        table1_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table1_nomFr.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table1_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table1_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table1_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        table1_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        table1_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
        table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
       //  placerBouton();
       // placerBouton(table1_del,2);
        placerBouton(choix,1);
       //  System.out.println("nbre aliment : "+listeAliments.size());
        table1.setItems(model);
      
    }
      public void initialiserNutriment()
      {
       
      }
        public void deplacerAliment(mainModel model)
     {
      table2_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table2_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table2_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table2_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table2_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
         table2_prixUn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        table2_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
      tableChoix.getItems().add(model);
      placerBouton(table2_supp,2);
      listeAliments.remove(model);
      table1.getItems().remove(model);
       if( table1.getItems().size()==0)
       {
          recherche.setText("");
         table1.setItems(obsListTable1);
       }
       
     }
        public void lancerControl(mainModel aliment)
        {
         if(tableChoix.getItems().size()<Nombre_MAX_ALIMENT)
           {
          //ivi pour lancer le deplacement
               deplacerAliment(aliment);
           }
       else{
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.close();
                 Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                 alert.setGraphic(new ImageView(image));
               alert.setTitle("Max Atteint");
               alert.setContentText("Le Nombre d'aliment Maximal est atteint :\n"
                       + " Veuillez saisir les autres valeurs et lancer le calcul \n");
               //alert.getButtonTypes().setAll(ButtonType.OK);  
               alert.show();
           }
        }
       public void placerBouton(TableColumn<mainModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>> cellFactory = new Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel, String> param) {
                final TableCell<mainModel, String> cell = new TableCell<mainModel, String>() {

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
                       mainModel aliment= getTableView().getItems().get(getIndex());  
                          lancerControl(aliment);
                             
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
  Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>> cellFactory = new Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel, String> param) {
                final TableCell<mainModel, String> cell = new TableCell<mainModel, String>() {

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
                       mainModel aliment= getTableView().getItems().get(getIndex());      
                              supprimerAliment(aliment);
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/del.png"));
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
    }
        public void initialisationCombobox()
    {
       //initialisation des pays
       
     List<String> list = new ArrayList<>();
         list.add("aucun choix");
        list.add("Glucide");
        list.add("Lipide");
        list.add("Proteine");
        ObservableList obList = FXCollections.observableList(list);
        listeNutriment.getItems().clear();
        listeNutriment.setItems(obList );

         
    }
       public void supprimerAliment(mainModel aliment)
                   {
            tableChoix.getItems().remove(aliment); 
            listeAliments.add(aliment);
             //traitementCasDejaPresent();
           // tableListe.getItems().add(aliment);
                    }
     public void LancerCalculPearson()
     {
      ObservableList<mainModel> alimentsChoisi=tableChoix.getItems();
      List<mainModel> listAliment=alimentsChoisi.subList(0,alimentsChoisi.size());
      int nombreAliment=tableChoix.getItems().size();
      int i=0;
     double valeurMaxx=Double.parseDouble(valeurMax.getText());
     String Objdectif=listeNutriment.getValue();
   if(valeurMaxx>0 && !Objdectif.equals("aucun choix"))
   {
      if(pearsonCal==null)
       {
      pearsonCal=new pearsonCalcul( listAliment, valeurMaxx,Objdectif,ListElementsAequilibrer);
      pearsonCal.calculPearson();
      List<Double> resultAutres=pearsonCal.getListResultatEquilibre();
          // System.out.println("affichage des resultats : ");
           for(mainModel elmt:alimentsChoisi)
           {
          //System.out.println(elmt.getNom_aliment()+" : "+elmt.getResultatCalcul()); 
           }
           affichageResult(alimentsChoisi, Objdectif, valeurMaxx,resultAutres);
       }
   else{
        pearsonCal.initialiserLesList();
        pearsonCal.setObdjectif(Objdectif);
        pearsonCal.setValeurObdjective(valeurMaxx);
        pearsonCal.setListAliments( listAliment);
        pearsonCal.calculPearson();
      List<Double> resultAutres=pearsonCal.getListResultatEquilibre();
          // System.out.println("affichage des resultats une fois : ");
//           for(mainModel elmt:alimentsChoisi)
//           {
//        //  System.out.println(elmt.getNom_aliment()+" : "+elmt.getResultatCalcul()); 
//           }
         affichageResult(alimentsChoisi, Objdectif, valeurMaxx,resultAutres);
       }
    }
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
         public void affichageResult(ObservableList<mainModel> listChoix,String objectif,double valeurObj,List<Double> valeurAutresElementCalculer)
   {
          try {
     //  if(loader==null){
              loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("/formuly/view/frontend/resultatPearson.fxml"));
               ctr_Result=new ResultatPearsonController(listChoix,objectif,valeurObj, ListElementsAequilibrer, valeurAutresElementCalculer);
               loader.setController(ctr_Result);
               Parent root = (Parent)loader.load(); 
                 st=null;
              Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
               st=new Stage();
         st.setScene(new Scene(root));
         st.getIcons().add(image);
         st.setTitle("Resultat du calcul");
         st.initOwner(lancerCalcul.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.setResizable(false);
         st.showAndWait();
        //  }
//       else{
//           System.out.println("dans le else");
//      ctr_Result.setListResult(listChoix);
//      ctr_Result.setResiduCalcul(residu);
//     // st.getScene().getRoot().get
//      st.showAndWait();
//         }
          } catch (IOException ex) {
                     Logger.getLogger(Inserer_aliment_fichierController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
        private  FXMLLoader loader=null;
        private Stage st;
        private ResultatPearsonController ctr_Result=null;
        private double[] colonneDroite;
        private double[] resultat;
        private double residu;
}
