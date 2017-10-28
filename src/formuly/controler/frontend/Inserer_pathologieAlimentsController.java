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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private final int NOMBRE_MAX_BUTTON=3;
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
         placerContenuAcceuille();
       miseAjourCouleurBtn(enregistrerPathologie, listBtn, NOMBRE_MAX_BUTTON);
        });
        supprimerPathologie.setOnAction(event->{
         placerSuppprimerPathologie();
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
}
