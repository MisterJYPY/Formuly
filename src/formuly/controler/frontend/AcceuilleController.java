/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class AcceuilleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private AnchorPane cat;
    @FXML private Button faireRepas;
    @FXML private Button MenuAvecMenuExistant;
    @FXML private BorderPane panelMilieu;
    private Stage st;
   
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    actionFenetreSelectionFoods();
        Button[] btn={faireRepas};
        formulyTools.mettreEffetButton(btn);
      
   
       // cat.setClip(lb);
    }    
    public void chargerPanelRepas() throws IOException
    {
             
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/formuly/view/frontend/make_foods.fxml"));
         Parent root = loader.load();
         controllerSelectionFoods= loader.getController();
         st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("formuly Foods Selector");
         st.initOwner(faireRepas.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         
         st.showAndWait();
       //  return st;
       
      }
     public void actionFenetreSelectionFoods()
  {
   faireRepas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            try {
               
//                faireRepas.getScene().setCursor(javafx.scene.Cursor.WAIT);
//                st.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
//               
//                
//                st.showAndWait();
//                faireRepas.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
//              //  st=new Stage();
                chargerPanelRepas();
               // mettreAction();
            } catch (IOException ex) {
                Logger.getLogger(MainPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
});
        MenuAvecMenuExistant.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.out.println("bile");
                placerBilanChoixFoods();
             }
         }); 
  }
      public void placerBilanChoixFoods()
   {
          try {
                    
      ((BorderPane)(panelMilieu.getCenter())).getChildren().clear();
                   // Panelmilieu.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/formuly/view/frontend/listeMenu.fxml"));
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(panelMilieu.getCenter())).getChildren().add(loader.load());
     controllerListeMenu= (ListeMenuController)loader.getController(); 
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
    private ListeMenuController controllerListeMenu;
    private Select_the_foodsController controllerSelectionFoods;
}
