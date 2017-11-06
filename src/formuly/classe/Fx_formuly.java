/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import formuly.controler.frontend.AcceuilleController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
       FXMLLoader loader=null;
    @Override
    public void start(Stage primaryStage) {
                  
        try {
            Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/logo1.jpg")
            );
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
             
            Parent root = loader.load();
            primaryStage.setTitle("formuly");
            primaryStage.getIcons().add(image);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException ex) {
//            
         Logger.getLogger(Fx_formuly.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("dans lexception");
//             for(int i=0;i<4;i++) 
//       {
//                 try {
//                     Image image = new Image(
//                             getClass().getResourceAsStream("/formuly/image/logo1.jpg")
//                     );
//                     loader = new FXMLLoader();
//                     loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
//                    // AcceuilleController ctrAcceuille=new AcceuilleController();
//                   //  loader.setController(ctrAcceuille);
//                     Parent root = loader.load();
//                     primaryStage.setTitle("formuly");
//                     primaryStage.getIcons().add(image);
//                     primaryStage.setScene(new Scene(root));
//                     if(loader!=null)
//                     {
//                         primaryStage.show();
//                         break;
//                     }     } catch (IOException ex1) {
//                     Logger.getLogger(Fx_formuly.class.getName()).log(Level.SEVERE, null, ex1);
//                 }}
        
        }
        
  
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
