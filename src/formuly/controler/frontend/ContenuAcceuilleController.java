/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ContenuAcceuilleController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Label nbreAlmtEnregistrer;
    @FXML 
    private Label nbreMenu;
     @FXML
    private Label nbrePathologieEnregistrer;
       @FXML
    private Label nbreMenuAlmtInterdit;

     @FXML
    private Label nbreAlmtNonEntierementEnregistrer;

    @FXML
    private Label nbreAlmtInterdit;

    @FXML
    private Label nbreMenuAvecAlimentInterdit;
    @FXML
    private BarChart<?, ?> bc;

    @FXML
    private CategoryAxis YRegime;
    @FXML
    private NumberAxis YqteMenu;
     @FXML
    private NumberAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private LineChart<Number, Number> stick;
     ObservableList<XYChart.Data> BarChartList;
       ObservableList<XYChart.Data> LineChartList;
     List<String> pays ;
    List<FmAliments> list=null;
      XYChart.Series seriesBarChat ;
      XYChart.Series seriesLineChat ;
       formulyTools   formul;
   // String [] pays=new String["General","Cote Ivoire","Ghana","Benin","Mali","Burkina Faso","Nigeria","Niger","Senegal","Gambie"];
    public ContenuAcceuilleController() {
      seriesBarChat = new XYChart.Series();
        BarChartList=FXCollections.observableArrayList();
        pays=FXCollections.observableArrayList();
          LineChartList=FXCollections.observableArrayList();
            seriesLineChat = new XYChart.Series();
          formul= new formulyTools();
         //chargerDonner();
    }
    public void IntialiserLabel()
    {
    nbreAlmtEnregistrer.setText(String.valueOf(formul.NbreAlimentEnregistrer()));
    nbreAlmtInterdit.setText(String.valueOf(formul.NbreAlimentInterdit()));
   nbrePathologieEnregistrer.setText(String.valueOf(formul.NbrePathologie()));
     nbreMenu.setText(String.valueOf(formul.NbreRepasEffectuer()));
    nbreAlmtNonEntierementEnregistrer.setText(String.valueOf(formul.nbreAlimentNonUtilisable()));
   nbreMenuAvecAlimentInterdit.setText(String.valueOf(formul.NbremenuAvecAlimentInterdit()));
    }
    public void chargerDonner()
    {
 
            pays.add("General");
            pays.add("Cote Ivoire");
                pays.add("Ghana");
                  pays.add("Benin");
                    pays.add("Mali");
                     pays.add("Burkina Faso");
                       pays.add("Nigeria");
                         pays.add("Niger");
                           pays.add("Senegal");
                             pays.add("Gambie");
             for(int i=0;i<pays.size();i++)
             {
                 int nbre=0;
              String nompay=pays.get(i);
              String decoupe=(nompay.equals("Cote Ivoire"))?"CIV":nompay.substring(0, 4);
                   if(nompay.equals("Nigeria"))
                   {
             decoupe="Ngria";      
                   }
              
                nbre=formul.AvoirNbreAlimentPays(nompay);
                BarChartList.add(new XYChart.Data(decoupe,nbre));
             }
             System.out.println("count :"+BarChartList.size());
             seriesBarChat.getData().addAll(BarChartList);
             bc.getData().addAll(seriesBarChat);
             LineChartList.add(new XYChart.Data(1000,formul.NbrerepasFonctionRegime(1000.)));
             LineChartList.add(new XYChart.Data(1500,formul.NbrerepasFonctionRegime(1500.)));
             LineChartList.add(new XYChart.Data(2000,formul.NbrerepasFonctionRegime(2500.)));
             LineChartList.add(new XYChart.Data(2500,formul.NbrerepasFonctionRegime(3000.)));
             LineChartList.add(new XYChart.Data(3000,formul.NbrerepasFonctionRegime(3500.)));
              LineChartList.add(new XYChart.Data(3500,formul.NbrerepasFonctionRegime(3500.)));
                //seriesLineChat.getNode().getStyleClass().add("series-line");
             seriesLineChat.getData().addAll(LineChartList);
                stick.getData().addAll(seriesLineChat);
                formul.fermerConnection();
    }
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      IntialiserLabel();
        chargerDonner(); 
       
      
    }    
    
}
