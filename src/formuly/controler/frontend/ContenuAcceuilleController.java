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
     private int nbreAlr=0;
   private int nbreAlimentInterdit=0;
   private int nbrePatho=0;
  private int nbreRepas=0;
  private int nbreAlimentNonUtilisable=0;
 private int nbreAlimentMenuInterdit=0;
   private  formulyTools   formul;
   // String [] pays=new String["General","Cote Ivoire","Ghana","Benin","Mali","Burkina Faso","Nigeria","Niger","Senegal","Gambie"];
    public ContenuAcceuilleController() {
         seriesBarChat = new XYChart.Series();
         BarChartList=FXCollections.observableArrayList();
         pays=FXCollections.observableArrayList();
            LineChartList=FXCollections.observableArrayList();
            seriesLineChat = new XYChart.Series();
            nbreAlimentMenuInterdit=formulyTools.NbremenuAvecAlimentInterdit();
          nbreAlr=formulyTools.NbreAlimentEnregistrer(1);
            nbreAlimentInterdit=formulyTools.NbreAlimentInterdit();
            nbrePatho=formulyTools.NbrePathologie();
            nbreRepas=formulyTools.NbreRepasEffectuer();
            nbreAlimentNonUtilisable=formulyTools.nbreAlimentNonUtilisable();
           
      //    formul= new formulyTools();
         //chargerDonner();
    }
    public void IntialiserLabel()
    {
       // System.out.println("nbreAliment : "+formulyTools.NbreAlimentEnregistrer(1));
    nbreAlmtEnregistrer.setText(String.valueOf(nbreAlr));
    nbreAlmtInterdit.setText(String.valueOf(nbreAlimentInterdit));
   nbrePathologieEnregistrer.setText(String.valueOf( nbrePatho));
   nbreMenu.setText(String.valueOf( nbreRepas));
    nbreAlmtNonEntierementEnregistrer.setText(String.valueOf(nbreAlimentNonUtilisable));
   nbreMenuAvecAlimentInterdit.setText(String.valueOf(nbreAlimentMenuInterdit));
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
              
                nbre=formulyTools.AvoirNbreAlimentPays(nompay);
                BarChartList.add(new XYChart.Data(decoupe,nbre));
             }
             System.out.println("count :"+BarChartList.size());
             seriesBarChat.getData().addAll(BarChartList);
             bc.getData().addAll(seriesBarChat);
             LineChartList.add(new XYChart.Data(1000,formulyTools.NbrerepasFonctionRegime(1000.)));
             LineChartList.add(new XYChart.Data(1500,formulyTools.NbrerepasFonctionRegime(1500.)));
             LineChartList.add(new XYChart.Data(2000,formulyTools.NbrerepasFonctionRegime(2500.)));
             LineChartList.add(new XYChart.Data(2500,formulyTools.NbrerepasFonctionRegime(3000.)));
             LineChartList.add(new XYChart.Data(3000,formulyTools.NbrerepasFonctionRegime(3500.)));
             LineChartList.add(new XYChart.Data(3500,formulyTools.NbrerepasFonctionRegime(3500.)));
            LineChartList.add(new XYChart.Data(5000,formulyTools.NbrerepasFonctionRegime(5000.)));
               LineChartList.add(new XYChart.Data(10000,formulyTools.NbrerepasFonctionRegime(10000.)));
                //seriesLineChat.getNode().getStyleClass().add("series-line");
             seriesLineChat.getData().addAll(LineChartList);
                stick.getData().addAll(seriesLineChat);
                formulyTools.getEm().getCache().evictAll();
              //  formul.fermerConnection();
    }
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       IntialiserLabel();
        chargerDonner(); 
       
      
    }    
    
}
