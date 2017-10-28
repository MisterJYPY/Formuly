/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Formuly_calculController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private BorderPane center;
    @FXML private BorderPane principal;
    @FXML private Button programmationLineaire;
    @FXML private Button matrice33;
    @FXML private Button carrePearson;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     programmationLineaire.setOnAction(event->{
       String urls="/formuly/view/frontend/prog_lineaire.fxml";
       placerVue(urls);
     });
     matrice33.setOnAction(event->{
       String urls="/formuly/view/frontend/matrix33Resolve.fxml";
         placerVue(urls);
     });
      carrePearson.setOnAction(event->{
       String urls="/formuly/view/frontend/pearsonResolve.fxml";
         placerVue(urls);
     });
    }    
     public void placerVue(String url)
    {
     try {
                    
      ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load(getClass().getResource(url)));
      // loader.setController(new Liste_AlimentsControllers());
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Formuly_calculController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
