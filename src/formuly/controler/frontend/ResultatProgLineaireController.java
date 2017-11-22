/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.model.frontend.mainModel;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ResultatProgLineaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Label glucideMin;

    @FXML
    private Label coutTotal;

    @FXML
    private Label protideMin;

    @FXML
    private TableColumn<mainModel, String> nomAliment;

    @FXML
    private TableColumn<mainModel, Double> Pu;

    @FXML
    private Label lipideMin;

    @FXML
    private TableColumn<mainModel, Double> resultat;

    @FXML
    private TableColumn<mainModel, Double> lipide;

    @FXML
    private Button fermer;

    @FXML
    private TableColumn<mainModel, Double> protide;

    @FXML
    private TableColumn<mainModel, String> cuisson;

    @FXML
    private TableColumn<mainModel, Double> glucide;

    @FXML
    private TableView<mainModel> table;

    @FXML
    private TableColumn<mainModel, String> pays;
    
    private final ObservableList<mainModel> lists;
    private final double[] result;
    private final double [] donneeMin;
      NumberFormat format;
      
/**
 * 
 * @param list
 * @param resultat
 * @param donneMin 
 */
    public ResultatProgLineaireController(List<mainModel> list ,double[] resultat,double [] donneMin) {
     this.lists=FXCollections.observableArrayList(list);
     this.result=resultat;
     this.donneeMin=donneMin;
     format=NumberFormat.getInstance();
     format.setMaximumFractionDigits(2);
    }
    /**
 * 
 * @param list
 * @param resultat
 * @param donneMin 
 */
    public ResultatProgLineaireController(ObservableList<mainModel> list ,double[] resultat,double [] donneMin) {
     this.lists=list;
     this.result=resultat;
     this.donneeMin=donneMin;
     format=NumberFormat.getInstance();
     format.setMaximumFractionDigits(2);
     miseAjourResultCalcul();
  
    }
     public ResultatProgLineaireController(ObservableList<mainModel> list ,double[] resultat,List<Double> LdonneMin) {
     this.lists=list;
     this.result=resultat;
     format=NumberFormat.getInstance();
     format.setMaximumFractionDigits(2);
     miseAjourResultCalcul();
      donneeMin=new double[4];
      actualiserdonneeMin(LdonneMin);
  
    }
       public final void actualiserdonneeMin(List<Double> list)
       {
         int i=0;
        for(Double element :list)
        {
         donneeMin[i]=element;
         System.out.println("el : "+element);
         i++;
        }
       }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        fermer.setOnAction(e->{
        formulyTools.fermerFenetre(fermer);
        });
        
    }    
    public void init()
      {
      //obdjectif.setText(Objectif);
          lipideMin.setText(String.valueOf(donneeMin[0]));
          protideMin.setText(String.valueOf(donneeMin[1]));
          glucideMin.setText(String.valueOf(donneeMin[2]));
         
        //valeurObdjectif.setText(String.valueOf(valeurObjectif));
        nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        resultat.setCellValueFactory(new PropertyValueFactory<>("resultatCalcul"));
        cuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        Pu.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        resultat.setCellValueFactory(new PropertyValueFactory<>("resultatCalcul"));
        int taille=result.length;
        coutTotal.setText(String.valueOf(format.format((-result[taille-1])*100)));
        table.setItems(lists);
      }
    private void miseAjourResultCalcul()
    {
      for(int cpt=0;cpt<result.length;cpt++)
      {
       int nmro=cpt+1;
      // System.out.println("X"+nmro+" : "+resulatCalcul[cpt]);
       if(cpt<(result.length-1))
              {
       lists.get(cpt).setResultatCalcul(Double.valueOf(formulyTools.preformaterChaine(format.format(result[cpt]*100))));
               }
      }
    }
    private void afficherDonneeMin()
    {
     for(int i=0;i<donneeMin.length;i++)
       {
           System.out.println(donneeMin[0]);
       }
    }
}
