/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Select_the_foodsController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML  private ComboBox categorie_Foods;
    @FXML  private ComboBox  pays_foods;
    @FXML  private ComboBox  mode_cuisson;
    @FXML private TextField  nom_aliment;
    @FXML private TextField  code_aliment;
    @FXML private TableView table_aliment_a_choisir;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
