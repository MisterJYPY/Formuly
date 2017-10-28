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
     private Button[] listBtn;
     private final int NOMBRE_MAX_BUTTON=3;

    public Formuly_calculController() {
           listBtn=new Button[NOMBRE_MAX_BUTTON];
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         listBtn[0]=programmationLineaire;
         listBtn[1]=matrice33;
         listBtn[2]=carrePearson;
     programmationLineaire.setOnAction(event->{
       String urls="/formuly/view/frontend/prog_lineaire.fxml";
       placerVue(urls);
         miseAjourCouleurBtn( programmationLineaire,listBtn,NOMBRE_MAX_BUTTON);
     });
     matrice33.setOnAction(event->{
       String urls="/formuly/view/frontend/matrix33Resolve.fxml";
         placerVue(urls);
         miseAjourCouleurBtn(matrice33,listBtn,NOMBRE_MAX_BUTTON);
     });
      carrePearson.setOnAction(event->{
       String urls="/formuly/view/frontend/pearsonResolve.fxml";
         placerVue(urls);
//          carrePearson.getStyleClass().clear();
//           carrePearson.getStyleClass().add("navi");
//         programmationLineaire.getStyleClass().clear();
//          programmationLineaire.getStyleClass().add("nav");
//         matrice33.getStyleClass().clear();
//        matrice33.getStyleClass().add("nav");
        miseAjourCouleurBtn(carrePearson,listBtn,NOMBRE_MAX_BUTTON);
     });
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
