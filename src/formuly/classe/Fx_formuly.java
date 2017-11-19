/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.controler.frontend.DemarrageAppController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mr_JYPY
 */
public class Fx_formuly extends Application {
    
       FXMLLoader loader=null;
      static DemarrageAppController dem;
    @Override
    public void start(Stage primaryStage) {
              
        try {
            Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            loader = new FXMLLoader();
           // loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
            loader.setLocation(getClass().getResource("/formuly/view/frontend/demarrageApp.fxml"));
            dem=new DemarrageAppController();
            loader.setController(dem);
            Parent root = loader.load();
            primaryStage.setTitle("démarrage");
            primaryStage.getIcons().add(image);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
           dem.positionnerAcceuille();
            
        } catch (IOException ex) {        
         Logger.getLogger(Fx_formuly.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    @Override
   public void stop(){
       formulyTools.getEm().getCache().evictAll();
       formulyTools.getEm().close();
       System.out.println("Application fermée avec succès");
    // Save file
}
  private void traiterFermerture()
  {
       Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("fermerture de Formuly");
       alert.setContentText("voulez vraiment fermer l'application ? \n"
               + " ");
       alert.getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
       alert.showAndWait();
       if(alert.getResult()==ButtonType.OK)
       {
        Platform.exit();
       }
  }
    
}
