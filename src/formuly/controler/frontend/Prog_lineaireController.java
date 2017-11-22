/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.expert.Simplexe;
import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Prog_lineaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
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
    private TableColumn<mainModel, String> choix;

    @FXML
    private Button retirerAliment;

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
        public static int Nombre_MAX_ALIMENT=50;
         public static int Nombre_MAX_CONTRAINTE=5;
        private Simplexe simplexe;
        private  double resulatCalcul[]=null;
    public Prog_lineaireController() {
        listeAliments=formulyTools.getobservableListMainModel();
         listeRecherche=listeAliments;
           matrix=new double[Nombre_MAX_CONTRAINTE][Nombre_MAX_ALIMENT];   
             donneeMin=new double[4];
           
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
            formulyTools.textsConverter(LipideMax,glucideMax,protideMax);      
        
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
        public void deplacerAliment(mainModel model)
     {
      table2_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table2_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table2_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table2_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table2_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
         table2_prixUn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
      //  table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
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
                             deplacerAliment(aliment);
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
        int nombreAliment=tableChoix.getItems().size();
         Alert alert=new Alert(Alert.AlertType.ERROR);
             if(nombreAliment>1)
             {  
         //control des valeurs de non nul de lipide et glucide et protide
    if(Double.valueOf(LipideMax.getText())>0 || Double.valueOf(glucideMax.getText())>0 || Double.valueOf(protideMax.getText())>0)
    {
        ControlCalcul();
    }
     else
       {
       alert.setContentText("Les valeures minimales des nutriments ne peuvent pas etre nulle \n \n"
                   + " Revoir vos champ SVP \n");
           alert.setTitle("error");
           alert.showAndWait();
       }
        }
          else
             {
           alert.setContentText("Deux aliments minimum possible \n"
                   + " Revoir vos choix SVP \n");
           alert.setTitle("error");
           alert.showAndWait();
             }

    }
    private Task LancerCalculLineaire() {
    return new Task() {
      @Override
      protected Object call() throws Exception {
         try{
             updateMessage("recupération des aliments ....");
     ObservableList<mainModel> alimentsChoisi=tableChoix.getItems();
             updateProgress(2,100);
      int nombreAliment=tableChoix.getItems().size();
        int i=0;
         updateMessage("recupération des valeurs minimales des nutriments ....");
        double glucidMax=Double.parseDouble(glucideMax.getText());
        double lipideMax=Double.parseDouble(LipideMax.getText());
        double protidMax=Double.parseDouble(protideMax.getText());
    
        donneeMin[0]=lipideMax;
        donneeMin[1]=protidMax;
        donneeMin[2]=glucidMax;
        
         updateProgress(9,100);
    updateMessage("Extraction des valeurs en nutriments des aliments ...."); 
       for(mainModel element:alimentsChoisi)
       {
       //chargement des glucide
        matrix[0][i]=-element.getCloumPcGlucide();
        //chargement des Lipide
        matrix[1][i]=-element.getCloumPclipide();
        //chargement des Protides
        matrix[2][i]=-element.getCloumPcprotide();
                i++;
       }
         updateProgress(18,100);
        updateMessage("mise à jour des contraintes ...."); 
       //enregistrement des membres de droites 
        matrix[0][nombreAliment]=-glucidMax;
        matrix[1][nombreAliment]=-lipideMax;
        matrix[2][nombreAliment]=-protidMax;
         updateProgress(20,100);
//        for(int cpt=0;cpt<=nombreAliment;cpt++)
//      {
//       double val=matrix[1][cpt];
//       double valLip=matrix[1][cpt];
//       double valPro=matrix[2][cpt];
//       matrix[3][cpt]=-val;
//      //   matrix[4][cpt]=-valLip;
//       // matrix[5][cpt]=-valPro;
//      }

      //enregistrement des coefficient de la fonction obdjective
        i=0;
         updateMessage("réparation de la fonction obdjective...."); 
       for(mainModel element:alimentsChoisi)
       {
       //chargement des prix unitaires
        matrix[3][i]=Double.parseDouble(element.getPrixUnitaire());
                i++;
       } 
         updateProgress(30,100);
       updateMessage("Initialisation du cout total a Zéro...."); 
        matrix[3][i]=0.0;
         updateProgress(32,100);
       for(int j=0;j<=4;j++)
       {
           System.out.println("");
         for(int k=0;k<=nombreAliment;k++)
         {
          System.out.print(matrix[j][k]+"  ");
         }
           System.out.println("");
       }
        updateMessage("Création de l'obdjet Simplexe....");
       // System.out.println("taile tablà "+matrix.length);
       simplexe=new Simplexe(4, nombreAliment+1,matrix);
        updateProgress(35,100);
     //  double[][] matrixDual=simplexe.retournerDual(matrix);
       
      // simplexe.setMatrixNouveau(matrix);
       double[][] matrixSuivant=new double[nombreAliment+1][4];
    updateMessage("Cas de minimisation detecté ....");
       Simplexe.cas="MIN";
    //   Simplexe.appelExterieur=true;
          updateProgress(40,100);
      updateMessage("Lancement de l'algorithme du Simplexe Patientez SVP ....");
         Thread.sleep(15);
       simplexe.algorithmeSimplexe();
        updateProgress(65,100);
    updateMessage("Fin du calcul cupération des resultats....");
      resulatCalcul=simplexe.getResulltatCalcul();
              updateProgress(75,100);
      updateMessage("Préparation pour l'affichage....");
      String url="/formuly/view/frontend/resultatProgLineaire.fxml";
         loader=new FXMLLoader();
         loader.setLocation(getClass().getResource(url));
             for(int k=0;k<donneeMin.length;k++)
             {
                 System.out.println("n: "+donneeMin[k]);
             }
         ctr=new ResultatProgLineaireController(alimentsChoisi, resulatCalcul, donneeMin);
        updateMessage("chargement du controller....");
            updateProgress(80,100);
       loader.setController(ctr);
        updateMessage("Chargement des composants pour la vue....");
          updateProgress(85,100);
          root=loader.load();
          updateProgress(90,100);
          updateMessage("initialisation 3 secondes....");
          updateProgress(95,100);
          updateMessage("terminer");
           updateProgress(100,100);
          }
         catch(Exception ex)
      {
          updateMessage("erreur");
           Logger.getLogger(DemarrageAppController.class.getName()).log(
           Level.SEVERE, null, ex
            );
      }
        return true;
      
      }
    };
  }
    
       public void ControlCalcul() 
    {
             
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("calcul linéaire");
               alert.show();
             Task copyWorker =LancerCalculLineaire();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Resultat de la programmation linéaire");
            st.initOwner(lancerCalcul.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
              st.showAndWait();
             // alert.setContentText("Terminé...");  
              if(resulatCalcul!=null)
              {
               System.out.println("");
      for(int cpt=0;cpt<resulatCalcul.length;cpt++)
      {
       int nmro=cpt+1;
      // System.out.println("X"+nmro+" : "+resulatCalcul[cpt]);
      }
              }
//                st.setResizable(false);
//            st.showAndWait();
              }
              else{
                  if(!"erreur".equals(newValue))
                  {
             alert.setContentText(newValue);   
                  }
                  else
                  {
             alert.setTitle("erreur de calcul");
             alert.setContentText("erreur ....");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
//            st=new Stage();
//            st.setScene(new Scene(root));
//            st.setTitle("Votre Expert");
//            st.initOwner(lancerCalcul.getScene().getWindow());
//            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
            alert.setContentText("Une erreur à été rencontré pendant le calcul \n"
                     + " Veuillez SVP revoir les valeurs et recommencer \n"
                     + " merci ");   
          alert.setAlertType(Alert.AlertType.ERROR);
              alert.showAndWait();      
                  }
              }
         }
                });
        
      new Thread(copyWorker).start();
       
      }
      private Parent root;
      private FXMLLoader loader;
      private Stage st;
      private double[] donneeMin;
      private ResultatProgLineaireController ctr;
}
