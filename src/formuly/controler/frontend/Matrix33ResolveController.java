/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import Jama.Matrix;
import formuly.classe.formulyTools;
import formuly.expert.Simplexe;
import formuly.model.frontend.mainModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class Matrix33ResolveController implements Initializable {

     
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
    private TextField protideMax;

    @FXML
    private TextField LipideMax;

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
    private TextField glucideMax;

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
        public static int Nombre_MAX_ALIMENT=3;
        public int compteurSelection=0;
        private Simplexe simplexe;
    public Matrix33ResolveController() {
        listeAliments=formulyTools.getobservableListMainModel();
         listeRecherche=listeAliments;
           matrix=new double[Nombre_MAX_ALIMENT][Nombre_MAX_ALIMENT];  
           colonneDroite=new double[Nombre_MAX_ALIMENT];
           resultat=new double[Nombre_MAX_ALIMENT];
           
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
                  chargerMatrix();
                  });
            textConverter(glucideMax,protideMax,LipideMax);
        
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
         System.out.println("nbre aliment : "+listeAliments.size());
        table1.setItems(model);
      
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
       public void supprimerAliment(mainModel aliment)
                   {
            tableChoix.getItems().remove(aliment); 
            listeAliments.add(aliment);
             //traitementCasDejaPresent();
           // tableListe.getItems().add(aliment);
                    }
     public void chargerMatrix()
     {
     ObservableList<mainModel> alimentsChoisi=tableChoix.getItems();
      int nombreAliment=tableChoix.getItems().size();
        int i=0;
        double glucidMax=Double.parseDouble(glucideMax.getText());
        double lipideMax=Double.parseDouble(LipideMax.getText());
        double protidMax=Double.parseDouble(protideMax.getText());
         System.out.println("glucide totale : "+glucidMax);
         System.out.println("lipide  totale : "+lipideMax);
         System.out.println("protide totale : "+protidMax);
         colonneDroite[0]=glucidMax;
         colonneDroite[1]=lipideMax;
         colonneDroite[2]=protidMax;
       for(mainModel element:alimentsChoisi)
       {
       //chargement des glucide
        matrix[0][i]=element.getCloumPcGlucide();
        //chargement des Lipide
        matrix[1][i]=element.getCloumPclipide();
        //chargement des Protides
        matrix[2][i]=element.getCloumPcprotide();
                i++;
       }
         Matrix A = new Matrix(matrix);
         Matrix b =new Matrix(colonneDroite, 3);
          Matrix x=null;
         double det=A.det();
         double rnorm=0 ;
       // si le determinant est null nous lancons le calcul
        if(det!=0)
        {
         x = A.solve(b);
         Matrix Residual = A.times(x).minus(b);
         rnorm = Residual.normInf();
         
         //affichage des resulttas
         
          for(int l=0;l<x.getRowDimension();l++)
       {
         for(int m=0;m<x.getColumnDimension();m++)
         {
             double res=x.get(l, m);
             System.out.print(" "+res);
             resultat[l]=res;
         }
          
       }
          int cpt=0;
         for(mainModel element:alimentsChoisi)
       {
           
         element.setResultatCalcul(resultat[cpt]*100);
         cpt++;
           System.out.println("eleent "+element.getNom_aliment()+" res : "+element.getResultatCalcul());
       }
        // tableChoix.setItems(alimentsChoisi);
          residu=rnorm;
        System.out.println("residu"+residu);
        affichageResult(alimentsChoisi);
        }
       
//       } 
      
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
         public void affichageResult(ObservableList<mainModel> listChoix)
   {
          try {
     //  if(loader==null){
                Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
              loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("/formuly/view/frontend/resultatCalcul.fxml"));
               ctr_Result=new ResultatCalculController(listChoix, residu);
               loader.setController(ctr_Result);
               Parent root = (Parent)loader.load(); 
                 st=null;
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
        private ResultatCalculController ctr_Result=null;
        private double[] colonneDroite;
        private double[] resultat;
        private double residu;
}
