/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.classe.pathologieModel;
import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Liste_alimentsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableColumn<mainModel, String> table1_modif;
    @FXML
    private TableColumn<mainModel, String> table1_del;
    @FXML
    private TableView<mainModel> table1;
    @FXML
    private TableColumn<mainModel, Double> table1_lipide;
    @FXML
    private TableColumn<mainModel, Double> table1_protide;
    @FXML
    private TableColumn<mainModel, Double> table1_numero;

    @FXML
    private TableColumn<mainModel, String> table1_modeCuisson;
     @FXML
    private TableColumn<mainModel, String> table1_categorie;

    @FXML
    private TableColumn<mainModel, Double> table1_glucide;

    @FXML
    private TableColumn<mainModel, String> table1_pays;
     @FXML
    private TableColumn<mainModel, String> table1_payss;
    @FXML
    private TableColumn<mainModel, String> table1_nomFr;

   
    ObservableList<mainModel> listeSaisie;
    ObservableList<mainModel> listeAliments;

    public Liste_alimentsController() {
         listeAliments=formulyTools.getobservableListMainModel();
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
        //placerBouton(table1_modif,1);
         System.out.println("nbre aliment : "+listeAliments.size());
        table1.setItems(model);
      
    }
      public void placerBouton(TableColumn<mainModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<mainModel,String>, TableCell<mainModel,String>> cellFactory = new Callback<TableColumn<mainModel,String>, TableCell<mainModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel,String> param) {
                final TableCell<mainModel,String> cell = new TableCell<mainModel,String>() {

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
                              //  deplacerAliment(modelPath);
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/modif.jpg"));
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
                      mainModel modelPath= getTableView().getItems().get(getIndex());      
                              // supprimerAliment(modelPath);
                                //SupprimerPathologieExis(modelPath);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       //  initialiserTab1(listeAliments);
         System.out.println("nbre aliment : "+listeAliments.size());
    }    
    
}
