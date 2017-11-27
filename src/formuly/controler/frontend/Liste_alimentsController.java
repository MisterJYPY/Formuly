/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmRepasAnalyse;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.repasModel;
import java.io.IOException;
import java.net.URL;
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
public class Liste_alimentsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableColumn<mainModel, String> table1_modif;
    @FXML
    private TableColumn<mainModel, String> table1_del;
    @FXML
    private TableView<mainModel> table1;
    @FXML
    private TableColumn<mainModel, Double> table1_lipide;
    @FXML
    private TableColumn<mainModel, Double> table1_protide;
    @FXML
    private TableColumn<mainModel, Double> table1_numero;

    @FXML
    private TableColumn<mainModel, String> table1_modeCuisson;
     @FXML
    private TableColumn<mainModel, String> table1_categorie;

    @FXML
    private TableColumn<mainModel, Double> table1_glucide;

    @FXML
    private TableColumn<mainModel, String> table1_pays;
     @FXML
    private TableColumn<mainModel, String> table1_payss;
    @FXML
    private TableColumn<mainModel, String> table1_nomFr;
    @FXML private TextField recherche;

    ObservableList<mainModel> listeRecherche;
    ObservableList<mainModel> listeAliments;

    public Liste_alimentsController() {
         listeAliments=formulyTools.getobservableListMainModel();
         listeRecherche=listeAliments;
    }
     public void initialiserTab1(ObservableList<mainModel> model)
    {
        table1_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table1_nomFr.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table1_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table1_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table1_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        table1_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        table1_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
        table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
     //  placerBouton();
        placerBouton(table1_del,2);
        placerBouton(table1_modif,1);
        // System.out.println("nbre aliment : "+listeAliments.size());
        table1.setItems(model);
      
    }
      public void placerBouton(TableColumn<mainModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<mainModel,String>, TableCell<mainModel,String>> cellFactory = new Callback<TableColumn<mainModel,String>, TableCell<mainModel,String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel,String> param) {
                final TableCell<mainModel,String> cell = new TableCell<mainModel,String>() {

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
                     mainModel aliment= getTableView().getItems().get(getIndex());  
                     String urls="/formuly/view/frontend/modifier_info_aliment.fxml";
                             lancerFentreModif(aliment,btn,getIndex(),urls);
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
  Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>> cellFactory = new Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel, String> param) {
                final TableCell<mainModel, String> cell = new TableCell<mainModel, String>() {
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
                      mainModel modelPath= getTableView().getItems().get(getIndex());      
                              ControlSupprimerAliment(modelPath,getIndex());
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
    public void lancerFentreModif(mainModel model,Button btn,int index,String url)
          {
           ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createUpdateFoodsWorker(model, btn, index,url);
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
            st.initOwner(btn.getScene().getWindow());
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
          }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          initialiserTab1(listeAliments);
          recherche.setOnKeyReleased(event->{
           listeRecherche=retourneListParCritere(recherche.getText(),listeAliments);
           initialiserTab1(listeRecherche);  
          });
       
    }    
     public ObservableList<mainModel> retourneListParCritere(String chaineArechercher,ObservableList<mainModel> liste)
     {
       ObservableList<mainModel> listTri=FXCollections.observableArrayList();
       String info="";
        for(mainModel ligne:liste)
      {
         Pattern p = Pattern.compile(chaineArechercher, Pattern.CASE_INSENSITIVE);
         info=info.concat(ligne.getNom_aliment()).concat(" "+ligne.getCategorie()).concat(" "+ligne.getPays()).concat(" "+ligne.getSurnom()).concat(" "+ligne.getMode_cuisson());
          Matcher m = p.matcher(info);
          
        if(m.find())
        {
            listTri.add(ligne);
        }
        info="";
      }   
       return listTri;
     }
     public void ControlSupprimerAliment(mainModel model,int nbre)
     {
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
          String message="NOM ALIMENT : "+model.getNom_aliment()+"\n"
                     + " Vous avez choisi de supprimer cet aliment: \n"
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
          supprimerAliment(model);
                }
     }
     public void supprimerAliment(mainModel model)
     {
               ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Chargement Aliment Interdit");
               alert.show();
               Task copyWorker = ProccessusSupressionAliment(model);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
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
                    alert.setTitle("Fin chargement");
               alert.setContentText("L'operation a ete un succes");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
            
              }
              else{
             alert.setContentText(newValue);   
              }
         }
                });
        
      new Thread(copyWorker).start();
     }
      public Task ProccessusSupressionAliment(mainModel models) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
              EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la suppression......");
              updateProgress(15,100);
          try {
            em.getTransaction().begin(); 
          FmAliments aliments=models.getAliment();
            updateMessage("en cour ......");
            updateProgress(50,100);
            FmAliments current=aliments;
             if (!em.contains(aliments)) {
             current = em.merge(aliments);
             }
            em.remove(current);
            table1.getItems().remove(models);
            listeAliments.remove(models);
           updateMessage("suppression des interdits lié.....");
            updateProgress(69,100);
            updateMessage("preparation pour affichage.....");
            updateProgress(85,100);
       
            em.getTransaction().commit();
            updateMessage("presque terminé");
            updateProgress(85,100);
           formulyTools.actualisserNumeroListe(listeAliments,models.getNumero()-1);
          // formulyTools.actualisserNumeroTable(table1,models.getNumero()-1);
               updateProgress(100,100);
            updateMessage("terminer");
          }catch (Exception e) {
//              System.out.println(""+e.getLocalizedMessage());
//              System.out.println(""+e.getMessage());
//              System.out.println(""+e.getCause().toString());
               Logger.getLogger(Suppression_pathologieController.class.getName()).log(Level.SEVERE, null, e);
               updateMessage("erreur");
          }
        return true;
      }
    };
  }
      

        public Task createUpdateFoodsWorker(mainModel model,Button btn,int index,String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            updateMessage("debut du traitement....");
             updateProgress(2,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(3,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
          ctr_inserAliment=new Modifier_info_alimentController(model,table1,index);
               loader.setController(ctr_inserAliment);
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
        private   Modifier_info_alimentController   ctr_inserAliment;
        private   Parent root;
        private Stage st;
}
