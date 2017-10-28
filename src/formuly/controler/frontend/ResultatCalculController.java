/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ResultatCalculController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableView<mainModel> TableResultat;
     
     @FXML
    private TableColumn<mainModel, Double> lipide;

    @FXML
    private Button fermer;

    @FXML
    private TableColumn<mainModel, String> nomAliment;

    @FXML
    private Label residu;

    @FXML
    private TableColumn<mainModel, Double> protide;

    @FXML
    private TableColumn<mainModel, Double> glucide;

    @FXML
    private TableColumn<mainModel, Double> resultat;

    private ObservableList<mainModel> listResult;
    private  double residuCalcul;
    public ResultatCalculController(ObservableList<mainModel> listResult, double residuCalcul) {
        this.listResult = listResult;
        this.residuCalcul = residuCalcul;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        intialiserLaVue();
        
        fermer.setOnAction(event->{
          Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
        });
    }  
    public void intialiserLaVue()
        {
       //insertion de la valeur du residu
      residu.setText(String.valueOf(residuCalcul));
        nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        resultat.setCellValueFactory(new PropertyValueFactory<>("resultatCalcul"));
       TableResultat.setItems(listResult);
        }

    public double getResiduCalcul() {
        return residuCalcul;
    }

    public void setResiduCalcul(double residuCalcul) {
        this.residuCalcul = residuCalcul;
    }

    public ObservableList<mainModel> getListResult() {
        return listResult;
    }

    public void setListResult(ObservableList<mainModel> listResults) {
        this.listResult.clear();
        System.out.println("nbre element "+listResults);
        this.listResult = listResults;
        TableResultat.setItems(listResult);
    }
    
    
}
