/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mr_JYPY
 */
public class Fx_formuly extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    
       
  
        try {
//                     List<RetentionAlments> liste=RetentionAlments.getAllAlimentRetention();
//             System.out.println("taille: "+liste.size());
//            EntityManagerFactory emf=formulyTools.getEm("fx_formulyPU");
//            FmAlimentsJpaController fal= new FmAlimentsJpaController(emf);
//            FmPathologieJpaController pat=new FmPathologieJpaController(emf);
//            FmAlimentsPathologieJpaController falPat=new FmAlimentsPathologieJpaController(emf);
//            FmAliments al=fal.findFmAliments(8);
//            FmPathologie pato=new FmPathologie(5);
//            pato.setLibelle("Palu");
//            pat.create(pato);
//            FmAlimentsPathologie alP=new FmAlimentsPathologie();
//            alP.setAliment(al);
//            alP.setPathologie(pato);
//         falPat.create(alP);
            
            
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/formuly/view/frontend/acceuille.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("formuly");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Fx_formuly.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
