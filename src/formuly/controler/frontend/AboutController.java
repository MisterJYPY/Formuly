/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import formuly.classe.Fx_formuly;
import formuly.classe.formulyTools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

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
      @FXML
    private Hyperlink link;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        okBtn.setOnAction(e->{
        formulyTools.fermerFenetre(okBtn);
        });
        link.setOnAction(e->{
         String urli="https://www.linkedin.com/in/kakou-nanou-893701122/";
        // String urlii="https://nanoukakoujeanpaul@gmail.com";
         HostServicesDelegate hostServices = HostServicesFactory.getInstance(Fx_formuly.returnApp());
         hostServices.showDocument(urli);
         // getHostServices().showDocument("http://www.yahoo.com");
        });
    }    
    
}
