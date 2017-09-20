/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import com.sun.glass.ui.Cursor;
import formuly.classe.formulyTools;
import formuly.model.frontend.mainModel;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class MainPrincipalController implements Initializable {
 
    //Injection For All Foods
    @FXML private BorderPane panelMilieu;
    @FXML private BorderPane fenetrePrincipal;
    @FXML private Button ok;
    @FXML  
  private TableView<mainModel>  aliment;
   @FXML 
  private TableColumn<mainModel, Integer> numero ;
     @FXML 
   private TableColumn<mainModel, String> qte ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide;
   @FXML
  private TableColumn<mainModel, Double>   Energie;
   @FXML
  private TableColumn<mainModel, Double>   fer;
   @FXML
  private TableColumn<mainModel, Double>   mg;
   @FXML
  private TableColumn<mainModel, Double>   na;
   @FXML
  private TableColumn<mainModel, Double>   ka;
   @FXML
  private TableColumn<mainModel, Double>   vitc;
   @FXML
  private TableColumn<mainModel, Double>   vite;
   @FXML
  private TableColumn<mainModel, Double>   vitb9;
  @FXML
  private TableColumn<mainModel, Double>   vita;
   
  //Injection For Burkina Faso
            @FXML  
  private TableView<mainModel>  aliment1;
   @FXML 
  private TableColumn<mainModel, Integer> numero1 ;
     @FXML 
   private TableColumn<mainModel, String> qte1 ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment1;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide1;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide1;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide1;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide1;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide1;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide1;
   @FXML
  private TableColumn<mainModel, Double>   Energie1;
   @FXML
  private TableColumn<mainModel, Double>   fer1;
   @FXML
  private TableColumn<mainModel, Double>   mg1;
   @FXML
  private TableColumn<mainModel, Double>   na1;
   @FXML
  private TableColumn<mainModel, Double>   ka1;
   @FXML
  private TableColumn<mainModel, Double>   vitc1;
   @FXML
  private TableColumn<mainModel, Double>   vite1;
   @FXML
  private TableColumn<mainModel, Double>   vitb91;
  @FXML
  private TableColumn<mainModel, Double>   vita1;
  
  //Injection For Nigeria
   @FXML  
  private TableView<mainModel>  aliment11;
   @FXML 
  private TableColumn<mainModel, Integer> numero11 ;
   @FXML 
   private TableColumn<mainModel, String> qte11 ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment11;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide11;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide11;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide11;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide11;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide11;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide11;
   @FXML
  private TableColumn<mainModel, Double>   Energie11;
   @FXML
  private TableColumn<mainModel, Double>   fer11;
   @FXML
  private TableColumn<mainModel, Double>   mg11;
   @FXML
  private TableColumn<mainModel, Double>   na11;
   @FXML
  private TableColumn<mainModel, Double>   ka11;
   @FXML
  private TableColumn<mainModel, Double>   vitc11;
   @FXML
  private TableColumn<mainModel, Double>   vite11;
   @FXML
  private TableColumn<mainModel, Double>   vitb911;
  @FXML
  private TableColumn<mainModel, Double>   vita11;
  //Injection For Benin
   @FXML  
  private TableView<mainModel>  aliment111;
   @FXML 
  private TableColumn<mainModel, Integer> numero111 ;
   @FXML 
   private TableColumn<mainModel, String> qte111 ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment111;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide111;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide111;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide111;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide111;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide111;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide111;
   @FXML
  private TableColumn<mainModel, Double>   Energie111;
   @FXML
  private TableColumn<mainModel, Double>   fer111;
   @FXML
  private TableColumn<mainModel, Double>   mg111;
   @FXML
  private TableColumn<mainModel, Double>   na111;
   @FXML
  private TableColumn<mainModel, Double>   ka111;
   @FXML
  private TableColumn<mainModel, Double>   vitc111;
   @FXML
  private TableColumn<mainModel, Double>   vite111;
   @FXML
  private TableColumn<mainModel, Double>   vitb9111;
  @FXML
  private TableColumn<mainModel, Double>   vita111;
  
  //Ghana 
   @FXML  
  private TableView<mainModel>  aliment1111;
   @FXML 
  private TableColumn<mainModel, Integer> numero1111 ;
   @FXML 
   private TableColumn<mainModel, String> qte1111 ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment1111;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide1111;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide1111;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide1111;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide1111;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide1111;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide1111;
   @FXML
  private TableColumn<mainModel, Double>   Energie1111;
   @FXML
  private TableColumn<mainModel, Double>   fer1111;
   @FXML
  private TableColumn<mainModel, Double>   mg1111;
   @FXML
  private TableColumn<mainModel, Double>   na1111;
   @FXML
  private TableColumn<mainModel, Double>   ka1111;
   @FXML
  private TableColumn<mainModel, Double>   vitc1111;
   @FXML
  private TableColumn<mainModel, Double>   vite1111;
   @FXML
  private TableColumn<mainModel, Double>   vitb91111;
  @FXML
  private TableColumn<mainModel, Double>   vita1111;
  
  //Mali
    @FXML  
  private TableView<mainModel>  aliment11111;
   @FXML 
  private TableColumn<mainModel, Integer> numero11111 ;
   @FXML 
   private TableColumn<mainModel, String> qte11111 ;
   @FXML
  private TableColumn<mainModel, String>  nom_aliment11111;
   @FXML
  private TableColumn<mainModel, Double>   cloumPcGlucide11111;
    @FXML
  private TableColumn<mainModel, Double>   cloumTtPcGlucide11111;
    @FXML
  private TableColumn<mainModel, Double>   cloumPclipide11111;
   @FXML
  private TableColumn<mainModel, Double>   cloumTtPclipide11111;
     @FXML
  private TableColumn<mainModel, Double>    Prprotide11111;
  @FXML
  private TableColumn<mainModel, Double>   TtPrprotide11111;
   @FXML
  private TableColumn<mainModel, Double>   Energie11111;
   @FXML
  private TableColumn<mainModel, Double>   fer11111;
   @FXML
  private TableColumn<mainModel, Double>   mg11111;
   @FXML
  private TableColumn<mainModel, Double>   na11111;
   @FXML
  private TableColumn<mainModel, Double>   ka11111;
   @FXML
  private TableColumn<mainModel, Double>   vitc11111;
   @FXML
  private TableColumn<mainModel, Double>   vite11111;
   @FXML
  private TableColumn<mainModel, Double>   vitb911111;
  @FXML
  private TableColumn<mainModel, Double>   vita11111;
  @FXML private Button faireRepas;
   @FXML private Button TousLesRepas;
   
    public MainPrincipalController() {
      
    }
  
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     System.out.println(fenetrePrincipal.getCenter());
    //initialisation des colonnes pour tous les aliments   
        Button[] btn={faireRepas,TousLesRepas};
        formulyTools.mettreEffetButton(btn);
         faireRepas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            try {
                // ImageCursor image = new ImageCursor(new javafx.scene.image.Image("/formuly/image/loading.gif"));  //pass in the image path
             faireRepas.getScene().setCursor(javafx.scene.Cursor.WAIT);
             Stage st=chargerPanelRepas();
             st.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
             faireRepas.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
            } catch (IOException ex) {
                Logger.getLogger(MainPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
});
      numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb9.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte.setCellFactory(TextFieldTableCell.forTableColumn());
         qte.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
         
      //pour la table Suivante
    
      
     aliment.setItems(formulyTools.getobservableListMainModel());
//     aliment.getColumns().addAll(numero,nom_aliment,qte,cloumPcGlucide,cloumTtPcGlucide,cloumPclipide,cloumTtPclipide,cloumPcprotide,cloumTtPcprotide
//      ,Energie,fer,mg,na,ka,vitc,vite,vitb9,vita);
   //initialisation des colonnes pour tous les aliments   Burkinabé
      numero1.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment1.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte1.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide1.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide1.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide1.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide1.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide1.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide1.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie1.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer1.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg1.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na1.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka1.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc1.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite1.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb91.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita1.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment1.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte1.setCellFactory(TextFieldTableCell.forTableColumn());
         qte1.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
          aliment1.setItems(formulyTools.getobservableListMainModel("Burkina Faso"));
          
          //Nigeria
           //initialisation des colonnes pour tous les aliments   Burkinabé
      numero11.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment11.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte11.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide11.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide11.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide11.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide11.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide11.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide11.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie11.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer11.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg11.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na11.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka11.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc11.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite11.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb911.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita11.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment11.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte11.setCellFactory(TextFieldTableCell.forTableColumn());
         qte11.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
          aliment11.setItems(formulyTools.getobservableListMainModel("Nigeria"));
         
          //for Benin
            //Nigeria
           //initialisation des colonnes pour tous les aliments   Burkinabé
      numero111.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment111.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte111.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide111.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide111.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide111.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie111.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer111.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg111.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na111.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka111.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc111.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite111.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb9111.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita111.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment111.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte111.setCellFactory(TextFieldTableCell.forTableColumn());
         qte111.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
          aliment111.setItems(formulyTools.getobservableListMainModel("Benin"));
          
          //Ghana
             //initialisation des colonnes pour tous les aliments   Burkinabé
      numero1111.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment1111.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte1111.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide1111.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide1111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide1111.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide1111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide1111.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide1111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie1111.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer1111.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg1111.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na1111.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka1111.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc1111.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite1111.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb91111.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita1111.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment1111.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte1111.setCellFactory(TextFieldTableCell.forTableColumn());
         qte1111.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
          aliment1111.setItems(formulyTools.getobservableListMainModel("Ghana"));
          
          //mali
             //initialisation des colonnes pour tous les aliments 
      numero11111.setCellValueFactory(new PropertyValueFactory<>("numero"));
      nom_aliment11111.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      qte11111.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      cloumPcGlucide11111.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
      cloumTtPcGlucide11111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcGlucide"));
      cloumPclipide11111.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
      cloumTtPclipide11111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPclipide"));
      Prprotide11111.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
     TtPrprotide11111.setCellValueFactory(new PropertyValueFactory<>("cloumTtPcprotide"));
      Energie11111.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       fer11111.setCellValueFactory(new PropertyValueFactory<>("fer"));
        mg11111.setCellValueFactory(new PropertyValueFactory<>("mg"));
        na11111.setCellValueFactory(new PropertyValueFactory<>("na"));
        ka11111.setCellValueFactory(new PropertyValueFactory<>("ka"));
        vitc11111.setCellValueFactory(new PropertyValueFactory<>("vitc"));
        vite11111.setCellValueFactory(new PropertyValueFactory<>("vite"));
        vitb911111.setCellValueFactory(new PropertyValueFactory<>("vitb9"));
        vita11111.setCellValueFactory(new PropertyValueFactory<>("vita"));   
        
        nom_aliment11111.setEditable(true);
        //mise a jour
        
        //
      //  nom_aliment.setCellFactory(TextFieldTableCell.forTableColumn());
         qte11111.setCellFactory(TextFieldTableCell.forTableColumn());
         qte11111.setOnEditCommit(
            new EventHandler<CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
         //ddjjdjdj
          aliment11111.setItems(formulyTools.getobservableListMainModel("Mali"));
       try {
                    
           ((BorderPane)(fenetrePrincipal.getCenter())).getChildren().clear();
                    panelMilieu.getChildren().clear();
                   ((BorderPane)(fenetrePrincipal.getCenter())).getChildren().add(FXMLLoader.load(getClass().getResource("/formuly/view/frontend/bilan_repas.fxml")));
                     System.out.println("bien cliquer");
                 } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }    
  
    public Stage chargerPanelRepas() throws IOException
    {
        
        
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/formuly/view/frontend/select_the_foods.fxml"));
         Parent root = loader.load();
         Stage st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("formuly Foods Selector");
          st.initModality(Modality.APPLICATION_MODAL);
          st.initOwner(faireRepas.getScene().getWindow());
          st.showAndWait();
         return st;
       
      }
   public Scene getThisScene() throws IOException
   {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/main.fxml"));
   Parent root = (Parent)loader.load();
   return root.getScene();
   }
}
