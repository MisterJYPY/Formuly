/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ResultatPearsonController implements Initializable {
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
    private Label valeurObdjectif;
    @FXML
    private Label obdjectif;

    @FXML
    private TableColumn<mainModel, Double> protide;

    @FXML
    private TableColumn<mainModel, Double> glucide;

    @FXML
    private TableColumn<mainModel, Double> resultat;

      @FXML
    private ListView<String> liste1;
      @FXML
    private ListView<String> liste2;
      @FXML
    private ListView<String> liste3;
      
    private ObservableList<mainModel> listResult;
    private  double valeurObjectif;
    private   String Objectif;
    private  List<String> autresComposantes;
    private  List<Double> valeurAutresComposantes;
    private  ObservableList<String> elementList1;
    private  ObservableList<String> elementList2;
    private  ObservableList<String> elementList3;
    
    /**
     * constructeur du controller qui gere la vue de l'algo de pearson
     * @param listResult la liste des resultat
     * @param Obdjectif  l'obdjectif sous forme de chaine de caractere pouvant etre (proteine,lipide,Glucide)
     * @param valeurObdjectif la valeur de l'Obdjectif a chercher
     * @param listAutresNut  la list des autres nutriments mis a jour depuis lalgo de pearson
     * @param valeurAutre    la list des valeurs des autres nutriments mis a jour depuis lalgo de pearson
     */
    public ResultatPearsonController(ObservableList<mainModel> listResult,String Obdjectif,double valeurObdjectif,List<String> listAutresNut,List<Double> valeurAutre) {
        this.listResult = listResult;
        this.Objectif=Obdjectif;
        this.valeurObjectif=valeurObdjectif;
        this.valeurAutresComposantes=valeurAutre;
        this.autresComposantes=listAutresNut;
         elementList1=FXCollections.observableArrayList();
         elementList2=FXCollections.observableArrayList();
         elementList3=FXCollections.observableArrayList();
    }
    private void initListView()
    {
     for(int i=0;i<=7;i++)
     {
      String element=autresComposantes.get(i);
      double valeur=valeurAutresComposantes.get(i);
      String ligne=element.concat(" : "+valeur);
      elementList1.add(ligne);
     }
     for(int j=8;j<=15;j++)
     {
      String element=autresComposantes.get(j);
      double valeur=valeurAutresComposantes.get(j);
      String ligne=element.concat(" : "+valeur);
      elementList2.add(ligne);
     }
     for(int k=16;k<=22;k++)
     {
      String element=autresComposantes.get(k);
      double valeur=valeurAutresComposantes.get(k);
      String ligne=element.concat(" : "+valeur);
      elementList3.add(ligne);
     }
     liste1.getItems().clear();
     liste2.getItems().clear();
     liste3.getItems().clear();
     liste1.getItems().addAll(elementList1);
     liste2.getItems().addAll(elementList2);
     liste3.getItems().addAll(elementList3);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        intialiserLaVue();
        initListView();
        fermer.setOnAction(event->{
          Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
        });
    }  
    public void intialiserLaVue()
        {
       //insertion de la valeur du residu
        obdjectif.setText(Objectif);
        valeurObdjectif.setText(String.valueOf(valeurObjectif));
        nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        resultat.setCellValueFactory(new PropertyValueFactory<>("resultatCalcul"));
       TableResultat.setItems(listResult);
        }

    public Label getValeurObdjectif() {
        return valeurObdjectif;
    }

    public void setValeurObdjectif(Label valeurObdjectif) {
        this.valeurObdjectif = valeurObdjectif;
    }

    public Label getObdjectif() {
        return obdjectif;
    }

    public void setObdjectif(Label obdjectif) {
        this.obdjectif = obdjectif;
    }

    public ObservableList<mainModel> getListResult() {
        return listResult;
    }

    public void setListResult(ObservableList<mainModel> listResult) {
        this.listResult = listResult;
    }

    public double getValeurObjectif() {
        return valeurObjectif;
    }

    public void setValeurObjectif(double valeurObjectif) {
        this.valeurObjectif = valeurObjectif;
    }

    public String getObjectif() {
        return Objectif;
    }

    public void setObjectif(String Objectif) {
        this.Objectif = Objectif;
    }

    public List<String> getAutresComposantes() {
        return autresComposantes;
    }

    public void setAutresComposantes(List<String> autresComposantes) {
        this.autresComposantes = autresComposantes;
    }

    public List<Double> getValeurAutresComposantes() {
        return valeurAutresComposantes;
    }

    public void setValeurAutresComposantes(List<Double> valeurAutresComposantes) {
        this.valeurAutresComposantes = valeurAutresComposantes;
    }

 
    
    
}
