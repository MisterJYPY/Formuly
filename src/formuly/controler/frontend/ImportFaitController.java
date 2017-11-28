/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.Excel.ExcelTools;
import formuly.Excel.KnowBook;
import formuly.classe.formulyTools;
import formuly.model.frontend.regleFaitModel;
import formuly.model.frontend.regleModel;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ImportFaitController implements Initializable {

     @FXML
    private TableColumn<regleModel,Date> date;

    @FXML
    private TableColumn<regleModel,Integer> nbFait;

    @FXML
    private TableColumn<regleFaitModel,String> actionFait;

    @FXML
    private TableColumn<regleFaitModel,Integer> numFait;
    
     @FXML
    private TableView<regleFaitModel> tableFait;
      @FXML
    private TableView<regleModel> tableRegle;
    @FXML
    private TableColumn<regleFaitModel,String> fait;

    @FXML
    private TableColumn<regleModel,Integer> numRegle;

    @FXML
    private Button choisirFichier;
   @FXML
   private Button fermer;
    @FXML
    private TableColumn<?, ?> actionR;

    @FXML
    private TableColumn<regleFaitModel,String> idFait;
   private final FileChooser fileChooser ;

    public ImportFaitController() {
     fileChooser=new FileChooser();
    }

   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choisirFichier.setOnAction(e->{
            System.out.println("seletion de fichier");
            takeFile(choisirFichier);
            System.out.println("fin selection fichier");
        });
        fermer.setOnAction(e->{
        formulyTools.fermerFenetre(fermer);
        });
    }    
     private void takeFile(Button btn)
  {
         Stage stage=(Stage) btn.getScene().getWindow();
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    ArrayList<String> info;
                     ArrayList<String> infoTrie;
                     //String extension=file.
                    if (file != null) {
                       // openFile(file);
             String   nomFichiers=file.getName();
             List<KnowBook> listB;
              ExcelTools lecteurFichirM=new ExcelTools();
              listB=lecteurFichirM.readBooksFromExcelFiles(file);
                        System.out.println(listB);
             String extension =nomFichiers.substring(nomFichiers.indexOf(".")).toLowerCase();
                      
                    }
                    else
                    {
                        System.out.println("nulllll");
                    }
                   
  }
      private static void configureFileChooser(final FileChooser fileChooser) {      
             fileChooser.setTitle("fichier depuis Formuly V.0.1");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );    
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel ","*.xlsx")
            );
    }
}
