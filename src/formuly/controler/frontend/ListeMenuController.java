/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.alimentRepasModel;
import formuly.classe.formulyTools;
import formuly.model.frontend.repasModel;
import formuly.entities.FmAliments;
import formuly.entities.FmRepas;
import formuly.entities.FmRepasAliments;
import formuly.entities.FmRepasAnalyse;
import formuly.entities.FmRetentionNutriments;
import formuly.model.frontend.mainModel;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManagerFactory;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ListeMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
EntityManagerFactory     entityManagerFactory;
      @FXML
    private TableColumn<repasModel,Date> date;

    @FXML
    private TableColumn<repasModel,Float> protideTotale;

    @FXML
    private TableColumn<alimentRepasModel, Float> numeroLocale;

    @FXML
    private TableColumn<repasModel,Float> glucideTotale;

    @FXML
    private TableColumn<repasModel, Integer> numero;

    @FXML
    private TableColumn<alimentRepasModel, Float> lipideLocale;

    @FXML private TableColumn<repasModel, String> prendre;

    @FXML
    private TableColumn<alimentRepasModel, Float> protideLocale;

    @FXML
    private TableColumn<alimentRepasModel, Float> energieLocale;

    @FXML
    private TableColumn<repasModel,Float> energieTotale;

    @FXML
    private TableView<repasModel> tableRepas;

    @FXML
    private TableView<alimentRepasModel> tableAliment;

    @FXML
    private TableColumn<alimentRepasModel,String> nom_aliment;

    @FXML
    private TableColumn<alimentRepasModel, Float> glucideLocale;

    @FXML
    private TableColumn<repasModel,Float> lipideTotale;

    @FXML
    private TableColumn<alimentRepasModel, Float> quantite;

    @FXML
    private TableColumn<FmRepas, String> nom_repas;
    @FXML private TextField recherche;
    ObservableList<repasModel> bilanList;
    ObservableList<repasModel> bilanRecherche;
    ObservableList<alimentRepasModel> detailAliment;

    public ListeMenuController() {
          bilanList=FXCollections.observableArrayList();
          detailAliment=FXCollections.observableArrayList();
          bilanRecherche=FXCollections.observableArrayList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initialiserTableauRepas();
        supprimerElementDeLaListeen2click(tableRepas);
        recherche.setOnKeyReleased(event->{
         bilanRecherche=listElementsTrie(recherche.getText(),bilanList);
         if(bilanRecherche.size()>0)
         {
         tableRepas.setItems(bilanRecherche);
         }
        });
        
    }  
    public ObservableList<repasModel> listElementsTrie(String chaineRechercher, ObservableList<repasModel> liste)
    {
        ObservableList<repasModel> listTri=FXCollections.observableArrayList();
       
        for(repasModel ligne:liste)
      {
         Pattern p = Pattern.compile(chaineRechercher, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(ligne.getLibelle());
          
        if(m.find())
        {
            listTri.add(ligne);
        }
    }
        return listTri;
    }
    public void placerBouton()
    {
     prendre.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<repasModel, String>, TableCell<repasModel, String>> cellFactory = new Callback<TableColumn<repasModel, String>, TableCell<repasModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<repasModel, String> param) {
                final TableCell<repasModel, String> cell = new TableCell<repasModel, String>() {

                    final Button btn = new Button("Extraire");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                                try {
                                    repasModel person = getTableView().getItems().get(getIndex());
                                    
                                    chargerPanelRepas(btn,person,"/formuly/view/frontend/make_foods_forMenu.fxml") ;
                                } catch (IOException ex) {
                                    Logger.getLogger(ListeMenuController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
         prendre.setCellFactory(cellFactory);

    }
    public ObservableList<repasModel> chargerLists()
    {
           //transformer en caractere
       NumberFormat format=NumberFormat.getInstance();
            format.setMaximumFractionDigits(2); 
      ObservableList<repasModel> model=FXCollections.observableArrayList();
         List<FmRepas> lisRepas;
       // FmRepasJpaController repaC=new FmRepasJpaController(formulyTools.getEm());
        lisRepas=formulyTools.Liste_Repas();
            int i=0;
            repasModel rpM;
        for(FmRepas repas :lisRepas)
        {
           if(repas==null) continue;
             rpM=new repasModel();
              rpM.setNumero(i+1);
           //   Double db=Double.valueOf(format.format(Double.valueOf(repas.getGlucide().toString())));
           rpM.setEnergie(repas.getEnergie());
           rpM.setGlucide(repas.getGlucide());
           rpM.setProtide(repas.getProtide());
           rpM.setLipide(repas.getLipide());
           rpM.setLibelle(repas.getLibelle());
           rpM.setId_repas(repas.getId());
           rpM.setDate(repas.getDate());  
           rpM.setRepas(repas);
           rpM.setNbreAliment(repas.getFmRepasAlimentsCollection().size());
             model.add(rpM);
             i++;
        }
       return model;
    }
    public void initialiserTableauRepas()
    {
      numero.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      nom_repas.setCellValueFactory(new PropertyValueFactory<>("libelle")); 
      glucideTotale.setCellValueFactory(new PropertyValueFactory<>("glucide")); 
      protideTotale.setCellValueFactory(new PropertyValueFactory<>("protide"));
      lipideTotale.setCellValueFactory(new PropertyValueFactory<>("lipide")); 
     energieTotale.setCellValueFactory(new PropertyValueFactory<>("energie"));
     date.setCellValueFactory(new PropertyValueFactory<>("date"));
         placerBouton();
        bilanList=chargerLists();
      //  actionSurTable(tableRepas);
       tableRepas.setItems(bilanList);
    }
   
    public void actionSurTable(TableView table)
    {
   table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           repasModel rpm= (repasModel) table.getSelectionModel().getSelectedItem();
            RemplirTableBas(rpm);
    }}
});
//   table.setOnMouseClicked(e->{
//    if (e.isPrimaryButtonDown() && e.getClickCount() < 2) {
//           repasModel rpm= (repasModel) table.getSelectionModel().getSelectedItem();
//            RemplirTableBas(rpm);
//    }
//   });
  }
    public ObservableList<alimentRepasModel> listDesAliment(repasModel rpm)
    {
    ObservableList<alimentRepasModel>  list=FXCollections.observableArrayList();
    List<FmRepasAliments> listeRepasAl=null;
    FmRepasAlimentsJpaController repasAl=new FmRepasAlimentsJpaController(formulyTools.getEm());
     listeRepasAl=(  List<FmRepasAliments> ) repasAl.findFmRepasAlimentsByRepas(rpm.getId_repas());
    FmRepasAliments Alrepas=null;
    alimentRepasModel alrpm=null;
     FmRetentionNutriments retentionAliments=null;
     if( listeRepasAl.size()>0)
     {
           FmRetentionNutriments retentionAuCasOuNul=new FmRetentionNutriments();
      for(int i=0;i<listeRepasAl.size();i++)
      {
       Alrepas=( FmRepasAliments)listeRepasAl.get(i);
       alrpm=new alimentRepasModel();
      
       FmAliments aliment=Alrepas.getAliment();
       Float quantite=Alrepas.getQuantite();
       FmRetentionNutrimentsJpaController  ctr=new FmRetentionNutrimentsJpaController(formulyTools.getEm());
       List<FmRetentionNutriments> retF=ctr.findFmRetentionNutrimentsByAliment(aliment);     
                retentionAuCasOuNul.setLipide(0);
                retentionAuCasOuNul.setGlucide(0);
                retentionAuCasOuNul.setProtein(0);
       retentionAliments=(retF.size()>0)?retF.get(0):retentionAuCasOuNul;
        
       Float Lipide=retentionAliments.getLipide();
       Float glucide=retentionAliments.getGlucide();
       Float protide=retentionAliments.getProtein();
      
    // System.out.println("Glucide: "+retentionAliments.getGlucide());
       Float qtGl=(quantite*glucide)/100;
       Float qtPr=(quantite*protide)/100;
       Float qtLp=(quantite*Lipide)/100;
      
      Float energie=(qtGl*4)+(qtPr*4)+(qtLp*9);
       //initialisation
       alrpm.setNumero(i+1);
       alrpm.setEnergie(energie);
       alrpm.setGlucide(qtGl);
       alrpm.setLipide(qtLp);
       alrpm.setProtide(qtPr);
       alrpm.setLibelle(aliment.getNomFr());
       alrpm.setQuantite(quantite);
       alrpm.setId_aliment(aliment.getId());
       
       list.add(alrpm);
       
      }}
    return list;
    }
     
    public void RemplirTableBas(repasModel repas)
    {
        System.out.println("repas: "+repas.getLibelle());
      numeroLocale.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      nom_aliment.setCellValueFactory(new PropertyValueFactory<>("libelle")); 
      glucideLocale.setCellValueFactory(new PropertyValueFactory<>("glucide")); 
      protideLocale.setCellValueFactory(new PropertyValueFactory<>("protide"));
      lipideLocale.setCellValueFactory(new PropertyValueFactory<>("lipide")); 
      energieLocale.setCellValueFactory(new PropertyValueFactory<>("energie"));
      quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      
       if(detailAliment.size()>0)
       {
             detailAliment.clear();
       }
  
       // 
        detailAliment=listDesAliment(repas);
        tableAliment.setItems(detailAliment);
    }
     public void chargerPanelRepas(Button faireRepas,repasModel modelRepas,String url) throws IOException
    {
          ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createUpdateFoodsWorker(modelRepas, faireRepas,url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Update Foods");
            st.initOwner(faireRepas.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
            st.showAndWait();
              }
               else{
                  if(!"erreur".equals(newValue))
                  {
                   alert.setContentText(newValue);  
                  }
                  else
                  {
                 alert.setAlertType(Alert.AlertType.INFORMATION);
                 alert.close();
                 alert.setContentText("Une erreur inatendue s'est produit lors du chargement \n "
                         + "Cela peut etre due à une indisponibilité du serveur de base de donnée \n"
                         + " Fermer cette fenetre d'alerte et reessayer SVP !!!! merci \n");
                 alert.setTitle("erreur rencontre");
                     Image image = new Image(
            getClass().getResourceAsStream("/formuly/image/war.jpg")
        );
               alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.FINISH);
                 alert.showAndWait();
                  }
              }
         }
                });     
      new Thread(copyWorker).start();
       //  return st;
      }
       public Task createUpdateFoodsWorker(repasModel modelRepas,Button btn,String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
            detailAliment.clear();
            detailAliment=listDesAliment(modelRepas);
             updateProgress(1,10);
            updateMessage("debut du traitement....");
             updateProgress(2,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(3,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
           int taille=bilanList.size();
         ctrMakeFoods=new Make_foods_forMenuController(modelRepas,detailAliment,tableRepas,taille);
               loader.setController(ctrMakeFoods);
            updateProgress(6,10);
            updateMessage("chargement des modules supplémentaires...");
             root = loader.load();
             Thread.sleep(10);
             updateProgress(9,10);
            updateMessage("preparation pour l'affichage...");
                 Thread.sleep(15);
            updateProgress(10,10);
            updateMessage("terminer");
           } catch (Exception e) {
                updateMessage("erreur");
                 Logger.getLogger(ListeMenuController.class.getName()).log(
                Level.SEVERE, null, e
            );
             }
        return true;
      }
    };
    
  }
 public void supprimerElementDeLaListeen2click(TableView<repasModel> table_aliment_deja_choisi)
       {
            table_aliment_deja_choisi.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
          if(row.getItem() instanceof repasModel)
          {
           int index=table_aliment_deja_choisi.getSelectionModel().getSelectedIndex(); 
           List<FmRepasAnalyse> listAnalyse=(table_aliment_deja_choisi.getSelectionModel().getSelectedItem().getRepas().getFmRepasAnalyseCollection()!=null)?(List<FmRepasAnalyse>) table_aliment_deja_choisi.getSelectionModel().getSelectedItem().getRepas().getFmRepasAnalyseCollection():null;
            System.out.println("list "+listAnalyse);
           String conclusion =(listAnalyse!=null && listAnalyse.size()>0)?listAnalyse.get(0).getConclusion():"aucune analyse faite pour ce Menu";
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("analyse");
            TextArea tex=new TextArea();
              tex.setText(conclusion);
          //    tex.getStyleClass().add("analyse");
              tex.setStyle(" -fx-text-fill:#660000;\n" +
"    -fx-font-size: 14px;   \n" +
"     -fx-font-weight: bold;");
              tex.setEditable(false);
              tex.setWrapText(true);
            alert.setHeaderText("votre analyse : ");
            alert.setGraphic(tex);
          //  alert.setContentText(conclusion);
 
            alert.showAndWait();
          }
          else
          {
              System.out.println("non instance");
          }
        }
        else
        {
          repasModel rpm= (repasModel) table_aliment_deja_choisi.getSelectionModel().getSelectedItem();
            RemplirTableBas(rpm);
        }
    }
});
       }
   private Make_foods_forMenuController ctrMakeFoods;
   private Stage st;
   private Parent root;
}
