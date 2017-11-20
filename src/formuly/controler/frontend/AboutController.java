/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class AboutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Button okBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        okBtn.setOnAction(e->{
        formulyTools.fermerFenetre(okBtn);
        });
    }    
    
}
