/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.bilanMacroNut;
import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */

public class Bilan_repasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private PieChart pieCharts;
    @FXML private TableView<bilanMacroNut> tableBilan;
    @FXML private TableColumn<bilanMacroNut, String>   aliment;
    @FXML private TableColumn<bilanMacroNut, String>   quantite;
    @FXML private TableColumn<bilanMacroNut, Double>   glucide;
    @FXML private TableColumn<bilanMacroNut, Double>   protide;
    @FXML private TableColumn<bilanMacroNut, Double>   lipide;
    @FXML private TableColumn<bilanMacroNut, Double>  Energie;
      @FXML private TableColumn<bilanMacroNut, String>  pays;
      ObservableList<Data> piecharList;
   private ObservableList<mainModel> lesElements;
    private ObservableList<bilanMacroNut> bilanElements;
 
   public Bilan_repasController() {
      //lesElements=FXCollections.observableArrayList(); 
       lesElements=FXCollections.observableArrayList();
       bilanElements=FXCollections.observableArrayList();
       piecharList = FXCollections.observableArrayList();
      //  System.out.println("manger : "+lesElements);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        System.out.println("manger : "+lesElements);
//        pieCharts.setData(getChartData());
//        pieCharts.setTitle("Composition en Macro Nutriment");
     
  
      
    }    
     private ObservableList<Data> getChartData() {
    ObservableList<Data> answer = FXCollections.observableArrayList();
    answer.addAll(new PieChart.Data("Glucide", 17),
            new PieChart.Data("Protide",34),
            new PieChart.Data("lipide",10)
           
            );
    return answer;
  }

    public ObservableList<mainModel> getLesElementsTable() {
        return lesElements;
    }

    public void setLesElements(ObservableList<mainModel> lesElements) {
        this.lesElements = lesElements;
    }

    public PieChart getPieCharts() {
        return pieCharts;
    }

    public void setPieCharts(PieChart pieCharts) {
        this.pieCharts = pieCharts;
    }

    public TableView<bilanMacroNut> getTableBilan() {
        return tableBilan;
    }

    public void setTableBilan(TableView<bilanMacroNut> tableBilan) {
        this.tableBilan = tableBilan;
    }

    public TableColumn<bilanMacroNut, String> getAliment() {
        return aliment;
    }

    public void setAliment(TableColumn<bilanMacroNut, String> aliment) {
        this.aliment = aliment;
    }

    public TableColumn<bilanMacroNut, String> getQuantite() {
        return quantite;
    }

    public void setQuantite(TableColumn<bilanMacroNut, String> quantite) {
        this.quantite = quantite;
    }

    public TableColumn<bilanMacroNut, Double> getGluciide() {
        return glucide;
    }

    public TableColumn<bilanMacroNut, Double> getProtide() {
        return protide;
    }

    public void setProtide(TableColumn<bilanMacroNut, Double> protide) {
        this.protide = protide;
    }

    public TableColumn<bilanMacroNut, Double> getLipide() {
        return lipide;
    }

    public void setLipide(TableColumn<bilanMacroNut, Double> lipide) {
        this.lipide = lipide;
    }

    public TableColumn<bilanMacroNut, Double> getEnergie() {
        return Energie;
    }

    public void setEnergie(TableColumn<bilanMacroNut, Double> Energie) {
        this.Energie = Energie;
    }

    public ObservableList<bilanMacroNut> getBilanElements() {
        return bilanElements;
    }

    public void setBilanElements(ObservableList<bilanMacroNut> bilanElements) {
        this.bilanElements = bilanElements;
    }

    public TableColumn<bilanMacroNut, Double> getGlucide() {
        return glucide;
    }

    public void setGlucide(TableColumn<bilanMacroNut, Double> glucide) {
        this.glucide = glucide;
    }

    public TableColumn<bilanMacroNut, String> getPays() {
        return pays;
    }

    public void setPays(TableColumn<bilanMacroNut, String> pays) {
        this.pays = pays;
    }

    public ObservableList<Data> getPiecharList() {
        return piecharList;
    }

    public void setPiecharList(ObservableList<Data> piecharList) {
        this.piecharList = piecharList;
    }
    
  
}
