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
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
    @FXML private Button enregistrer_aliment;
    @FXML private Button MenuAvecMenuExistant;
    @FXML private BorderPane panelMilieu;
    @FXML private BorderPane center;
    @FXML private BorderPane principal;
    @FXML private Button modifierMenu;
    @FXML private Button listMenu;
    @FXML private Button listAliment;
     @FXML private Button modifierAliment;
    @FXML private TitledPane paneGauche;
   @FXML private TitledPane paneDroite;
    @FXML private Accordion accordGauche;
    @FXML private Accordion accordDroite;
    private Stage st;
   
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         placerContenuAcceuille();
       actionFenetreSelectionFoods();
        Button[] btn={faireRepas,modifierMenu,listMenu,listAliment,enregistrer_aliment,MenuAvecMenuExistant,modifierAliment};
        formulyTools.mettreEffetButton(btn,Color.ROYALBLUE);
        accordGauche.setExpandedPane(paneGauche);
        accordDroite.setExpandedPane(paneDroite);
   
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
                placerBilanChoixFoods();
             }
         }); 
       enregistrer_aliment.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
             InsertionAliment();
             }
         }); 
  }
      public void placerBilanChoixFoods()
   {
          try {
                    
      ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load(getClass().getResource("/formuly/view/frontend/listeMenu.fxml")));
     controllerListeMenu= (ListeMenuController)loader.getController(); 
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
         public void InsertionAliment()
   {
          try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/inserer_aliment.fxml"));
               ctr_inserAliment=new Inserer_alimentController();
               loader.setController(ctr_inserAliment);
           Parent root = (Parent)loader.load(); 
                 st=null;
               st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("Insertion Aliment");
         st.initOwner(enregistrer_aliment.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
       public void placerContenuAcceuille()
   {
          try {
                    
        ((BorderPane)(principal.getCenter())).getChildren().clear();
            center.getChildren().clear();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/contenuAcceuille.fxml"));
                  loader.setController(new ContenuAcceuilleController());
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load());
          } catch (IOException ex) {
                     Logger.getLogger(ContenuAcceuilleController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
    private ListeMenuController controllerListeMenu;
    private Select_the_foodsController controllerSelectionFoods;
    private Inserer_alimentController ctr_inserAliment;
}
