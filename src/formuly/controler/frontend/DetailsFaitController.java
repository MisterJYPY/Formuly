/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.entities.FmRegleFait;
import formuly.model.frontend.regleFaitModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class DetailsFaitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea listRegle;

    @FXML
    private Button fermer;

    @FXML
    private Label nombreRegleFait;

    @FXML
    private TextArea conclusionFait;

    @FXML
    private Label dateEnregistrementFait;

    @FXML
    private Label idFait;
    
    private final regleFaitModel infoRegle;
    private final int nombreRegle;
    private final String conclusionDuFait;
    private final String lettreFait;
    private final String dateREnregFait;
    private  String listFaitDeclenchable="";

    public DetailsFaitController(regleFaitModel infoRegles) {
        this.infoRegle = infoRegles;
        nombreRegle=infoRegle.getNombreRegleApplicable();
        conclusionDuFait=infoRegle.getFait().getLibelleFait();
        lettreFait=infoRegle.getFait().getLettreFait();
        dateREnregFait=infoRegle.getFait().getDerniereModif().toString();
        for(FmRegleFait regleFait:infoRegle.getListRegleFait())
        {
       listFaitDeclenchable=listFaitDeclenchable.concat(regleFait.getRegle().getLibelleRegleClair()+"\n\n");
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initElementVue();
        fermer.setOnAction(event->{
        ActionBoutonFermer();
        });  
    }    
    public void ActionBoutonFermer()
    {
       Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    public void initElementVue()
    {
     nombreRegleFait.setText(Integer.toString(nombreRegle));
     dateEnregistrementFait.setText(dateREnregFait);
     idFait.setText(lettreFait);
     conclusionFait.setText(conclusionDuFait);
     listRegle.setText(listFaitDeclenchable);
    }
}
