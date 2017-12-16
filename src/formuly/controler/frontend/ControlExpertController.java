/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ControlExpertController implements Initializable {

      @FXML
    private Label infoError;

    @FXML
    private Button valider;

    @FXML
    private PasswordField password;

    @FXML
    private Button annuler;

    @FXML
    private CheckBox messageAffich;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  action();
        annuler.setOnAction(e->{
            formulyTools.fermerFenetre(annuler);
        });
    }    
    private void action()
    {
    messageAffich.selectedProperty().addListener(new ChangeListener<Boolean>(){

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue)   
        {
        formulyTools.accesExpert=1;
        }
        else
        {
         formulyTools.accesExpert=0;
        }
        }
    });
//    valider.setOnAction(e->{
//      String pass=password.getText();
//    });
    }

    public Label getInfoError() {
        return infoError;
    }

    public void setInfoError(Label infoError) {
        this.infoError = infoError;
    }

    public Button getValider() {
        return valider;
    }

    public void setValider(Button valider) {
        this.valider = valider;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Button getAnnuler() {
        return annuler;
    }

    public void setAnnuler(Button annuler) {
        this.annuler = annuler;
    }

    public CheckBox getMessageAffich() {
        return messageAffich;
    }

    public void setMessageAffich(CheckBox messageAffich) {
        this.messageAffich = messageAffich;
    }
    
}
