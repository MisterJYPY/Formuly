/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mr_JYPY
 */
public class Fx_formuly extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
                  
               Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/logo1.jpg")
      );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("formuly");
            primaryStage.getIcons().add(image);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
