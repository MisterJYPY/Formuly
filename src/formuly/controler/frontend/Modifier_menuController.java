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
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Modifier_menuController implements Initializable {

    /**
     * Initializes the controller class.
     */
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

    @FXML 
    private TableColumn<alimentRepasModel, String> prendre;

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
    private TableColumn<repasModel, String> nom_repas;
    @FXML 
    private TextField recherche;
    @FXML
    private TableColumn<repasModel,Integer>    nbreAliment;
     @FXML
    private TableColumn<repasModel,String>    supMenu;
      @FXML
    private TableColumn<repasModel,String>    modifMenu;
    ObservableList<repasModel> bilanList;
    ObservableList<repasModel> bilanRecherche;
    ObservableList<alimentRepasModel> detailAliment;
       private repasModel repasCourant=null;
       private int indexCourant;

    public Modifier_menuController() {
          bilanList=FXCollections.observableArrayList();
          detailAliment=FXCollections.observableArrayList();
          bilanRecherche=FXCollections.observableArrayList();
    }
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
        initialiserTableauRepas();
        recherche.setOnKeyReleased(event->{
            tableAliment.getItems().clear();
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
  Callback<TableColumn<alimentRepasModel, String>, TableCell<alimentRepasModel, String>> cellFactory = new Callback<TableColumn<alimentRepasModel, String>, TableCell<alimentRepasModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<alimentRepasModel, String> param) {
                final TableCell<alimentRepasModel, String> cell = new TableCell<alimentRepasModel, String>() {

                    final Button btn = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            
                            btn.getStyleClass().add("dark-blue");
                              Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/del.png"));
                             btn.setGraphic(new ImageView(image));
                            btn.setOnAction(event -> {
                              alimentRepasModel person = getTableView().getItems().get(getIndex());
                               ControlSupprimerMenu(person,getIndex());
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
      public void placerBouton(TableColumn<repasModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<repasModel,String>, TableCell<repasModel,String>> cellFactory = new Callback<TableColumn<repasModel,String>, TableCell<repasModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<repasModel,String> param) {
                final TableCell<repasModel,String> cell = new TableCell<repasModel,String>() {

                    final Button btn = new Button();

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
                                    repasModel aliment= getTableView().getItems().get(getIndex());
                                   String urls="/formuly/view/frontend/make_foods_forMenu.fxml";
                                    chargerPanelRepas(btn,aliment,getIndex(),urls);
                                } catch (IOException ex) {
                                    Logger.getLogger(Modifier_menuController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/modif.jpg"));
                             btn.setGraphic(new ImageView(image));
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
         colonne.setCellFactory(cellFactory);
        }
      else{       
     colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<repasModel,String>, TableCell<repasModel,String>> cellFactory = new Callback<TableColumn<repasModel,String>, TableCell<repasModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<repasModel,String> param) {
                final TableCell<repasModel,String> cell = new TableCell<repasModel,String>() {

                    final Button btn = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                    repasModel rePm= getTableView().getItems().get(getIndex());      
                                //supprimerAliment(modelPath);
                              //  SupprimerPathologieExis(modelPath);
                    ControlSupprimerMenu(rePm,getIndex());
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/del.png"));
                             btn.setGraphic(new ImageView(image));
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }

                };
                return cell;
                
            }
        };
         colonne.setCellFactory(cellFactory);     
        
        }
    }
      
    public ObservableList<repasModel> chargerLists()
    {
           //transformer en caractere
       NumberFormat format=NumberFormat.getInstance();
            format.setMaximumFractionDigits(2); 
      ObservableList<repasModel> model=FXCollections.observableArrayList();
         FmRepasAlimentsJpaController repasAliment=new FmRepasAlimentsJpaController(formulyTools.getEm());
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
       List< FmRepasAliments> listeRepasAl=(  List<FmRepasAliments> ) repasAliment.findFmRepasAlimentsByRepas(repas.getId());  
           rpM.setNbreAliment(listeRepasAl.size());
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
      nbreAliment.setCellValueFactory(new PropertyValueFactory<>("nbreAliment"));
     
          placerBouton(modifMenu,1);
          placerBouton(supMenu,2);
        bilanList=chargerLists();
       // actionSurTable(tableRepas);
       tableRepas.setItems(bilanList);
       supprimerElementDeLaListeen2click(tableRepas);
       
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
             // System.out.println("list "+listAnalyse);
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
    public void actionSurTable(TableView table)
    {
   table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           repasModel rpm= (repasModel) table.getSelectionModel().getSelectedItem();
               repasCourant=rpm;
               indexCourant= table.getSelectionModel().getSelectedIndex();
              RemplirTableBas(rpm);
    }}
});
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
       FmRepas repas=Alrepas.getRepas();
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
       alrpm.setId_repas(repas.getId());
       alrpm.setAlrepas(Alrepas);
       list.add(alrpm);
       
      }}
    return list;
    }
     
    public void RemplirTableBas(repasModel repas)
    {
      numeroLocale.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      nom_aliment.setCellValueFactory(new PropertyValueFactory<>("libelle")); 
      glucideLocale.setCellValueFactory(new PropertyValueFactory<>("glucide")); 
      protideLocale.setCellValueFactory(new PropertyValueFactory<>("protide"));
      lipideLocale.setCellValueFactory(new PropertyValueFactory<>("lipide")); 
      energieLocale.setCellValueFactory(new PropertyValueFactory<>("energie"));
      quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        placerBouton();
       if(detailAliment.size()>0)
       {
             detailAliment.clear();
       }
  
       // 
        detailAliment=listDesAliment(repas);
        tableAliment.setItems(detailAliment);
    }
     public void chargerPanelRepas(Button faireRepas,repasModel modelRepas,int indexTableau,String url) throws IOException
    {      
           ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createUpdateFoodsForMenuWorker(faireRepas, modelRepas, indexTableau,url);
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
       public Task createUpdateFoodsForMenuWorker(Button faireRepas,repasModel modelRepas,int indexTableau,String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
              detailAliment.clear();
            updateMessage("Chargement des liste....");
            detailAliment=listDesAliment(modelRepas);
             updateProgress(2,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(3,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
             int taille=bilanList.size();
            ctrMakeFoods=new Make_foods_forMenuController(modelRepas,detailAliment,tableRepas,taille,modelRepas.getRepas(),indexTableau,bilanList);
            loader.setController( ctrMakeFoods);
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
                 Logger.getLogger(Modifier_menuController.class.getName()).log(
                Level.SEVERE, null, e
            );
             }
        return true;
      }
    };
    
  }
       public void ControlSupprimerMenu(repasModel model,int nbre)
     {
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
          String message="NOM DU MENU : "+model.getLibelle()+"\n"
                     + " Vous avez choisi de supprimer cet menu: \n"
                     + "Proccessus non bloquant ,Veuillez confirmer SVP \n ";
          alert.setTitle("Confirmer suppression");
             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/question.png"));
             alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                     alert.setContentText(message);
                     alert.showAndWait();
                 
                if(alert.getResult()==ButtonType.YES)
                {
          supprimerMenu(model,nbre);
                }
     }
     public void supprimerMenu(repasModel model,int nbre)
     {
               ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Suppression Menu : "+model.getLibelle());
               alert.show();
               Task copyWorker = ProccessusSupressionAliment(model);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
          
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setContentText("terminer mise a jour de votre espace...");
              //  initialisation();
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin Suppression");
               alert.setContentText("L'operation a ete un succes");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.showAndWait();
            
              }
              else{
                      if("erreur".equals(newValue))
              {
               
                 //   chargerDonne(listeSaisie,"aj");
                  //   System.out.println("taille liste enregister second : "+listeEnregistrer.size());
            //  formulyTools.initialiserLabelInfoAliment(derniereModif,nomFichier,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Erreur");
               alert.setContentText("Une erreur est Survenue lors de l'operation causant un arret du processus \n"
                       + " Veuillez reessayer SVP !!!!!!");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
              }
                      else{
             alert.setContentText(newValue); 
                      }
               }
         }
                });
        
      new Thread(copyWorker).start();
     }
      public Task ProccessusSupressionAliment(repasModel models) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
              EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la suppression......");
              updateProgress(15,100);
          try {
            em.getTransaction().begin(); 
           FmRepas repas=models.getRepas();
            updateMessage("en cour ......");
            updateProgress(50,100);
            FmRepas current=repas;
             if (!em.contains(repas)) {
             current = em.merge(repas);
             }
            em.remove(current);
              tableRepas.getItems().remove(models);
              bilanList.remove(models);
            updateMessage("suppression des aliments lié au menu.....");
            updateProgress(69,100);
            updateMessage("preparation pour affichage.....");
            updateProgress(85,100);
       
            em.getTransaction().commit();
            updateMessage("presque terminé");
            updateProgress(85,100);
          formulyTools.actualiserNumeroListe(bilanList,models.getNumero()-1);
          // formulyTools.actualisserNumeroTable(table1,models.getNumero()-1);
             if(tableAliment.getItems().size()>0)
              {
              if(tableAliment.getItems().get(0).getId_repas()==repas.getId())
              {
             updateProgress(90,100);
              tableAliment.getItems().clear();
              }
              }
               updateProgress(100,100);
            updateMessage("terminer");
          }catch (Exception e) {
              System.out.println(""+e.getLocalizedMessage());
              System.out.println(""+e.getMessage());
              System.out.println(""+e.getCause().toString());
               Logger.getLogger(Suppression_pathologieController.class.getName()).log(Level.SEVERE, null, e);
               updateMessage("erreur");
          }
      
         
          
      
        return true;
      }
    };
  }
      //proccessus suppression alimentRepas
        public void ControlSupprimerMenu(alimentRepasModel model,int nbre)
     {
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
         String verifieUnicite=(tableAliment.getItems().size()==1) ?"Attention cette Operation supprimera le menu tout entier \n"
                 + "Car il y a que 1 seul aliment present \n":"";
          String message="NOM DE L'ALIMENT : "+model.getLibelle()+"\n"
                     +verifieUnicite+" Vous avez choisi de supprimer cet aliment : \n"
                     + "Proccessus non bloquant ,Veuillez confirmer SVP \n ";
          alert.setTitle("Confirmer suppression");
             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/question.png"));
             alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                     alert.setContentText(message);
                     alert.showAndWait();           
                if(alert.getResult()==ButtonType.YES)
                 {
          supprimerMenu(model,nbre);
                  }
     }
     public void supprimerMenu(alimentRepasModel model,int nbre)
     {
               ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Suppression Aliment du menu : "+model.getLibelle());
               alert.show();
               Task copyWorker = ProccessusSupressionAliment(model);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
          
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setContentText("terminer mise a jour de votre espace...");
              //  initialisation();
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin Suppression");
               alert.setContentText("L'operation a ete un succes");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.showAndWait();
            
              }
              else{
                      if("erreur".equals(newValue))
              {
               
                 //   chargerDonne(listeSaisie,"aj");
                  //   System.out.println("taille liste enregister second : "+listeEnregistrer.size());
            //  formulyTools.initialiserLabelInfoAliment(derniereModif,nomFichier,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Erreur");
               alert.setContentText("Une erreur est Survenue lors de l'operation causant un arret du processus \n"
                       + " Veuillez reessayer SVP !!!!!!");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.showAndWait();
              }
                      else{
             alert.setContentText(newValue); 
                      }
               }
         }
                });
        
      new Thread(copyWorker).start();
     }
      public Task ProccessusSupressionAliment(alimentRepasModel models) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
              EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la suppression......");
              updateProgress(15,100);
          try {
            em.getTransaction().begin(); 
            FmRepasAliments Alrepas=models.getAlrepas();
            FmRepas repas=Alrepas.getRepas();
          FmRepasAlimentsJpaController repasAliment=new FmRepasAlimentsJpaController(formulyTools.getEm());
       List< FmRepasAliments> listeRepasAl=(  List<FmRepasAliments> ) repasAliment.findFmRepasAlimentsByRepas(repas.getId());
          //  System.out.println("nom repas "+repas.getLibelle());
            updateMessage("en cour ......");
            updateProgress(50,100);
            FmRepasAliments current=Alrepas;
         List< FmRepasAliments> listAliment=(List<FmRepasAliments>)repas.getFmRepasAlimentsCollection();
              System.out.println("nbre ALM : "+listAliment.size());
         List<FmRetentionNutriments> listR=(List<FmRetentionNutriments>)current.getAliment().getFmRetentionNutrimentsCollection();
             FmRetentionNutriments ret=listR.get(0);
           float quantite=current.getQuantite();
           float lpde=((quantite*ret.getLipide())/100);
           float glce=((quantite*ret.getGlucide())/100);
           float prtde=((quantite*ret.getProtein())/100);
           float energieAliment=9*lpde+4*glce+4*prtde;
         //les aliments du menu on reclacul la valeur en lipide et en glucide
           float lipideTotale=0;
           float glucideTotale=0;
           float protideTotale=0;
           float EnergieTotale=0;
           float quantites;
         //  float energieAliments=9*lpde+4*glce+4*prtde;
            for(FmRepasAliments rpasAli:listeRepasAl)
            {
              quantites=rpasAli.getQuantite();
              FmAliments al=rpasAli.getAliment();
              System.out.println("nom aliment : "+al.getNomFr()+" qte : "+quantites);
    List<FmRetentionNutriments> listRi=(List<FmRetentionNutriments>)al.getFmRetentionNutrimentsCollection();
         FmRetentionNutriments reti=listRi.get(0);
                //System.out.println("lipide : "+reti.getLipide());
         lipideTotale=lipideTotale+((quantites*reti.getLipide())/100);  
         glucideTotale=glucideTotale+((quantites*reti.getGlucide())/100); 
         protideTotale=protideTotale+((quantites*reti.getProtein())/100); 
           quantites=0;
            }
            float ener=(lipideTotale*9 )+(protideTotale*4) +(glucideTotale*4);
         lipideTotale=lipideTotale-lpde;
         glucideTotale=glucideTotale-glce;
         protideTotale=protideTotale-prtde;
         EnergieTotale= lipideTotale*9 +protideTotale*4 +glucideTotale*4;
        float nutrimentTotal=lipideTotale +protideTotale +glucideTotale;
         float pcenLp=(lipideTotale/nutrimentTotal)*100;
        float pcenPt=(protideTotale/nutrimentTotal)*100;
        float pcenGl=(glucideTotale/nutrimentTotal)*100;
       
             repas.setGlucide(pcenGl);
             repas.setLipide(pcenLp);
             repas.setProtide(pcenPt);
             repas.setEnergie(EnergieTotale);
             em.merge(repas);
              if (!em.contains(Alrepas)) {
             current = em.merge(Alrepas);
             }
             em.remove(current);
              tableAliment.getItems().remove(models);
              formulyTools.actualiserNumeroListes(tableAliment.getItems(),models.getNumero()-1);
              repasCourant.setNbreAliment(tableAliment.getItems().size());
              repasCourant.setEnergie(EnergieTotale);
              repasCourant.setLipide(pcenLp);
              repasCourant.setProtide(pcenPt);
              repasCourant.setGlucide(pcenGl);
              repasCourant.setNbreAliment( tableAliment.getItems().size()); 
              bilanList.set(repasCourant.getNumero()-1, repasCourant);
              tableRepas.getItems().set(indexCourant, repasCourant);
                int tailleAliment=tableAliment.getItems().size();
                 if(tailleAliment==0)
                //on supprime le repas en question 
                    {
                FmRepas currentRepas=repas;
             if (!em.contains(currentRepas)) {
             currentRepas= em.merge(repas);
             }
              em.remove(currentRepas);
              tableRepas.getItems().remove(repasCourant);
              bilanList.remove(repasCourant);
             formulyTools.actualiserNumeroListe(bilanList,repasCourant.getNumero()-1);
             formulyTools.actualiserNumeroListe(tableRepas.getItems(),indexCourant);
                      }
            updateMessage("suppression des aliments lié au menu.....");
            updateProgress(69,100);
            updateMessage("preparation pour affichage.....");
            updateProgress(85,100);
       
            em.getTransaction().commit();
            updateMessage("presque terminé");
            updateProgress(85,100);
       //   formulyTools.actualiserNumeroListe(bilanList,models.getNumero()-1);
               updateProgress(100,100);
            updateMessage("terminer");
          }catch (Exception e) {
              System.out.println(""+e.getLocalizedMessage());
              System.out.println(""+e.getMessage());
              System.out.println(""+e.getCause().toString());
               Logger.getLogger(Suppression_pathologieController.class.getName()).log(Level.SEVERE, null, e);
               updateMessage("erreur");
          }
      
         
          
      
        return true;
      }
    };
  }
   private Make_foods_forMenuController ctrMakeFoods;
   private Stage st;   
   private Parent root;
    
}
