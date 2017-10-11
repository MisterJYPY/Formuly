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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Inserer_pathologieAlimentsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private BorderPane principal;
    @FXML private BorderPane center;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        placerContenuAcceuille();
    }    
        public void placerContenuAcceuille()
   {
          try {
                    
        ((BorderPane)(principal.getCenter())).getChildren().clear();
            center.getChildren().clear();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/inserer_pathologie.fxml"));
                  loader.setController(new Inserer_pathologieController());
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load());
          } catch (IOException ex) {
                     Logger.getLogger(Inserer_pathologieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
}
