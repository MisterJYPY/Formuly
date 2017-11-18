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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    @FXML private Button enregistrerPathologie;
    @FXML private Button supprimerPathologie;
    @FXML private Button quitter;
    private Button [] listBtn;
    private final int NOMBRE_MAX_BUTTON=2;
     public Button[] retournerListBtn()
     {
        Button[] btns={supprimerPathologie,enregistrerPathologie};
       return btns;
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listBtn=retournerListBtn();
        placerContenuAcceuille();
        enregistrerPathologie.setOnAction(event->{
        // placerContenuAcceuille();
          String urls="/formuly/view/frontend/inserer_pathologie.fxml";
            placeInStageMiddle(urls,Inserer_pathologieController.class);
       miseAjourCouleurBtn(enregistrerPathologie, listBtn, NOMBRE_MAX_BUTTON);
        });
        supprimerPathologie.setOnAction(event->{
        // placerSuppprimerPathologie();
         String urls="/formuly/view/frontend/suppression_pathologie.fxml";
            placeInStageMiddle(urls,Suppression_pathologieController.class);
        miseAjourCouleurBtn( supprimerPathologie, listBtn, NOMBRE_MAX_BUTTON);
        });
        quitter.setOnAction(event->{
        quitterFenetre(quitter);
        });
        Button [] btn={quitter};
        formulyTools.mettreEffetButton(btn);
    }  
     public void miseAjourCouleurBtn(Button btn,Button[] listBntn,int nbreBtnEnregistrer)
    {
         btn.getStyleClass().clear();
         btn.getStyleClass().add("navi");
        String idbtn=btn.getId();
       for(int i=0;i<nbreBtnEnregistrer;i++)
         {
             if(!(listBntn[i].getId()).equals(idbtn)) 
             {
             listBntn[i].getStyleClass().clear();  
             listBntn[i].getStyleClass().add("nav");
             } 
         }
    }
     public static void quitterFenetre(Button quitte)
     {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("fermer fenetre alerte");
            alert.setHeaderText("Confirmer la fermerture \n");
            alert.setContentText(""
                    + "VOULEZ-VOUS VRAIMENT QUITTEZ ?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
           Stage stage = (Stage) quitte.getScene().getWindow();
    // do what you have to do
               stage.close();
        }                
     }
     public void placerSuppprimerPathologie()
     {
      try {
                    
        ((BorderPane)(principal.getCenter())).getChildren().clear();
            center.getChildren().clear();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/suppression_pathologie.fxml"));
                  loader.setController(new Suppression_pathologieController());
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load());
          } catch (IOException ex) {
                     Logger.getLogger(Suppression_pathologieController.class.getName()).log(Level.SEVERE, null, ex);
                 }
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
          private Task createPutInMidlleWorker(String url,Class cls) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            loader.setController(cls.newInstance());
            updateProgress(4,10);
            updateProgress(8,10);
            updateMessage("chargement des modules supplémentaires...");
         
            root = loader.load();
            Thread.sleep(10);
            updateProgress(9,10);
            updateMessage("preparation pour l'affichage...");
                 Thread.sleep(15);
            updateProgress(10,10);
            updateMessage("terminer");
           } catch (Exception e) {
                updateMessage("erreur");
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
             }
        return true;
      }
    };
    
  }
          private void placeInStageMiddle(String url,Class cls)
    {
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createPutInMidlleWorker(url,cls);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
           ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
          ((BorderPane)(principal.getCenter())).getChildren().add(root);
              alert.close();
              }
              else{
                  if(!"erreur".equals(newValue))
                  {
                   alert.setContentText(newValue);  
                  }
                  else
                  {
                 alert.setAlertType(Alert.AlertType.INFORMATION);
                 alert.close();
                 alert.setContentText("Une erreur inatendue s'est produit lors du chargement \n "
                         + "Cela peut etre due à une indisponibilité du serveur de base de donnée \n"
                         + " Fermer cette fenetre d'alerte et reessayer SVP !!!! merci \n");
                 alert.setTitle("erreur ");
                     Image image = new Image(
            getClass().getResourceAsStream("/formuly/image/war.jpg")
        );
               alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.FINISH);
                 alert.showAndWait();
                  }
              }
         }
                });
        
      new Thread(copyWorker).start();
    }
         private Parent root;
}
