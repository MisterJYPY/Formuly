/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

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

    public Bilan_repasController() {
        System.out.println("dhhhdhd dhd dhhd dd");
          
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("manger");
         pieCharts.setData(getChartData());
    pieCharts.setTitle("Composition en Macro Nutriment");
  
      
    }    
     private ObservableList<Data> getChartData() {
    ObservableList<Data> answer = FXCollections.observableArrayList();
    answer.addAll(new PieChart.Data("Glucide", 179),
            new PieChart.Data("Protide",341),
            new PieChart.Data("lipide",10)
           
            );
    return answer;
  }
  
}
