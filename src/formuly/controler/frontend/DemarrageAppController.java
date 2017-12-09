/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class DemarrageAppController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private ProgressBar barProgression;
   
     @FXML
    private Label infop;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    public ProgressBar getBarProgression() {
        return barProgression;
    }

    public void setBarProgression(ProgressBar barProgression) {
        this.barProgression = barProgression;
    }
   public void ecrire() 
   {
       System.out.println("chef d'etat ");
   }
   public void positionnerAcceuille()
   {
          Task copyWorker = lancerAcceuille();
          barProgression.progressProperty().unbind();
          barProgression.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if(newValue.equals("terminer"))
              {    
                    Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
                  st=new Stage();
                  st.setTitle("Votre accueil");
                  st.setScene(new Scene(root));
                  st.getIcons().add(image);
                  Stage stage = (Stage) barProgression.getScene().getWindow();
                  st.centerOnScreen();
                 // st.setResizable(false);
                  stage.close();
//                AcceuilleController controller = loader.getController();
//                  st.setOnHidden(e -> controller.shutdown());
                  st.showAndWait();
              }
              else{
                if(!newValue.equals("erreur"))
                {
             infop.setText(newValue);
                }
                else
                {
            infop.setText("Une erreur s'est produit lors du chargement , Veuillez ressayer...");  
           Alert  alert=new Alert(Alert.AlertType.ERROR);
           String message="!!!!!Une Erreur Inattendue s'est produite \n "
                   + " Cela peut etre due à l'indisponibilité du serveur de base de données \n"
                   + " Veuillez fermer l'application et la relancé à nouveau SVP \n";
           String title="erreur Chargement";
           alert.setTitle(title);
           alert.setContentText(message);
           alert.show();
                }
                 }
          }
          });
        new Thread(copyWorker).start();
        
   }
       public Task lancerAcceuille() {
    return new Task() {
      @Override
      protected Object call() throws Exception {
         try{
            updateProgress(5, 100);
            updateMessage(" debut du Chargement des composants utiles ...... ");
             Thread.sleep(10);
             loader = new FXMLLoader();   
            updateProgress(15, 100);
              Thread.sleep(15);
           updateProgress(25, 100);
           updateMessage("Création du Loader et initialisation des graphiques ...........");
               Thread.sleep(30);
           loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
             updateProgress(50, 100);
             updateProgress(70, 100);
           updateMessage("Presque terminé nous préparons l'affichage......");
               Thread.sleep(10);
                updateProgress(73, 100);
            updateMessage("Chargement des modules de l'interface......");
             root = loader.load();
            updateMessage("Juste Une derniere étape......");
            updateProgress(75, 100);
             updateProgress(80, 100);     
          updateMessage("La Création de la scène est deja terminée patientez......");   
             updateProgress(90, 100);
          updateMessage("Pres pour le démarrage dans 2 sécondes Max...");
             Thread.sleep(30);
           updateProgress(95, 100);
           updateProgress(100, 100);
            updateMessage("Nous fermons cette fenetre pour afficher votre acceuille...");
           Thread.sleep(15);
          updateMessage("terminer");
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

       private boolean estVisible;
       private AcceuilleController acceuille;
       private Stage st;
       private FXMLLoader loader;
       private Parent root;
}
