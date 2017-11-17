/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.Excel.ExcelTools;
import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.mainModel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class AcceuilleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private AnchorPane cat;
    @FXML private Button faireRepas;
    @FXML private Button enregistrer_aliment;
    @FXML private Button MenuAvecMenuExistant;
    @FXML private BorderPane panelMilieu;
    @FXML private BorderPane center;
    @FXML private BorderPane principal;
    @FXML private Button modifierMenu;
    @FXML private Button listMenu;
    @FXML private Button listAliment;
    @FXML private Button modifierAliment;
    @FXML private TitledPane paneGauche;
    @FXML private TitledPane paneDroite;
    @FXML private Accordion accordGauche;
    @FXML private Accordion accordDroite;
    @FXML private Button enregistrer_aliment_fichier;
    @FXML private Button moteurCalcul;
    @FXML private Button interditAlimentaire;
    @FXML private Button formulation;
    @FXML private Button expert;
    @FXML  private Button fermer;
    @FXML private Label dumpLabelIndicator;
    @FXML private ProgressBar dumpProgressIndicator;
    @FXML private MenuItem telecharerAlimentItem;

    private Stage st;
    private Formuly_calculController  fmCalcul;
     private ExpertController  fmexpert;
    private final int NOMBRE_BUTTON_MAX=7;
    private Button[] listBtn;
    
    public AcceuilleController() {
        windowMteurCacul=null;
        //lisBtn=new Button[NOMBRE_BUTTON_MAX];
    }
   
     public Button[] retournerListBtn()
     {
        Button[] btns={faireRepas,modifierMenu,listMenu,listAliment,enregistrer_aliment,MenuAvecMenuExistant,modifierAliment};
       return btns;
     }
    
    public void ActionDumpageFoods()
    {
    telecharerAlimentItem.setOnAction(e->{
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Telechargement");
            alert.setHeaderText("Voulez vous vraiment lancer le téléchargment \n");
            alert.setContentText("NB: Le processus est irreversible  \n"
                    + "CONFIRMER L'OPERATION SVP ?");
               Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/question.png")
      );
               alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
//           Stage stage = (Stage) quitter.getScene().getWindow();
              // stage.close();
          List<FmAliments> list=formulyTools.listeAliment();
         ExcelTools.cas=1;
         ExcelTools exlT=new ExcelTools();
         exlT.DumpFoodsDataBase(list, dumpProgressIndicator,dumpLabelIndicator);
        }      
    });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listBtn=retournerListBtn();
         placerContenuAcceuille();
        actionFenetreSelectionFoods();
       // Button[] btn={faireRepas,modifierMenu,listMenu,listAliment,enregistrer_aliment,MenuAvecMenuExistant,modifierAliment};
        formulyTools.mettreEffetButton(listBtn,Color.ROYALBLUE);
        accordGauche.setExpandedPane(paneGauche);
        accordDroite.setExpandedPane(paneDroite);
        listAliment.setOnAction(event->{
           placerListAliment();
            miseAjourCouleurBtn(listAliment, listBtn, NOMBRE_BUTTON_MAX);
        });
     modifierMenu.setOnAction(event->{
      String urls="/formuly/view/frontend/modifier_menu.fxml";
         placerVue(urls);
     miseAjourCouleurBtn(modifierMenu, listBtn, NOMBRE_BUTTON_MAX);
     });
     formulation.setOnAction(event->{
      String urls="/formuly/view/frontend/formuly_calcul.fxml";
       afficherFentre(urls) ;
       
     });
     expert.setOnMouseClicked(event->{
      String urls="/formuly/view/frontend/expert.fxml";
     LancerExpert(urls) ;
     //  lancerExpert(urls);
     });
       // cat.setClip(lb);
     fermer.setOnAction(event->{
    // formulyTools.getEm().close();
     });
     dumpLabelIndicator.setVisible(false);
     dumpProgressIndicator.setVisible(false);
     ActionDumpageFoods();
    } 
      public Task createExpertWorker(String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
            
          try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
            fmexpert=new ExpertController();
            loader.setController(fmexpert);
            updateProgress(6,10);
            updateMessage("chargement des modules supplémentaires...");
             root = loader.load();
             updateProgress(9,10);
            updateMessage("presque terminé patientez juste un peu SVP...");
            updateProgress(10,10);
            updateMessage("terminer");
           } catch (Exception e) {
               updateMessage("erreur");
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
            
             }

        return true;
      }
    };
  }
      public Task createNewFoodsWorker(String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          try {
            updateMessage("debut du traitement....");
            updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
            updateMessage("chargement des modules supplémentaires...");
            updateProgress(8,10);
             root = loader.load();
             Thread.sleep(10);
             updateProgress(9,10);
            updateMessage("presque terminé patientez juste un peu SVP...");
             Thread.sleep(15);
            updateProgress(10,10);
            updateMessage("terminer");
           } catch (Exception e) {
               updateMessage("erreur");
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
            
             }

        return true;
      }
    };
    
  }
        public Task createPathologieWorker(String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
            ctr_patAliment=new Inserer_pathologieAlimentsController();
            loader.setController(ctr_patAliment);
            updateProgress(6,10);
            updateMessage("chargement des modules supplémentaires...");
             root = loader.load();
             Thread.sleep(10);
             updateProgress(9,10);
            updateMessage("presque terminé patientez juste un peu SVP...");
             Thread.sleep(15);
            updateProgress(10,10);
            updateMessage("terminer");
           } catch (Exception e) {
                updateMessage("erreur");
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
            
             }

        return true;
      }
    };
    
  }
         public Task createInserFoodsForFileWorker(String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
            ctr_inserAlimentFichier=new Inserer_aliment_fichierController();
            loader.setController(ctr_inserAlimentFichier);
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
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
             }
        return true;
      }
    };
    
  }
           public Task createInserFoodsWorker(String url) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
            try {
            updateMessage("debut du traitement....");
             updateProgress(1,10);
            FXMLLoader loader = new FXMLLoader();
            updateMessage("mise à jour ....");
            updateProgress(2,10);
            loader.setLocation(getClass().getResource(url));
            updateMessage("création du controleur de traitement ....");
            updateProgress(4,10);
            ctr_inserAliment=new Inserer_alimentController();
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
                 Logger.getLogger(AcceuilleController.class.getName()).log(
                Level.SEVERE, null, e
            );
             }
        return true;
      }
    };
    
  }
      public void miseAjourCouleurBtn(Button btn,Button[] listBntn,int nbreBtnEnregistrer)
    {
         btn.getStyleClass().clear();
         btn.getStyleClass().add("navi");
        String idbtn=btn.getId();
       for(int i=0;i<nbreBtnEnregistrer;i++)
         {
             if(!(listBntn[i].getId()).equals(idbtn)) 
             {
             listBntn[i].getStyleClass().clear();  
             listBntn[i].getStyleClass().add("nav");
             } 
         }
    }
    public void placerListAliment()
    {
     try {
                    
      ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load(getClass().getResource("/formuly/view/frontend/liste_aliments.fxml")));
      // loader.setController(new Liste_AlimentsControllers());
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Liste_alimentsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
     public void placerVue(String url)
    {
     try {
                    
      ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load(getClass().getResource(url)));
      // loader.setController(new Liste_AlimentsControllers());
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Liste_alimentsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    public void chargerPanelRepas(String url) throws IOException
    {
          ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createNewFoodsWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Easy Foods Créator");
            st.initOwner(expert.getScene().getWindow());
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
      public void afficherFentre(String url) 
    {
             
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(url));
            fmCalcul=new Formuly_calculController();
            loader.setController(fmCalcul);
            Parent root = loader.load();
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("base de calcul");
            st.initOwner(formulation.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.showAndWait();
            //  return st;
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      }
       public void LancerExpert(String url) 
    {
             
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de l'expert");
               alert.show();
               Task copyWorker =createExpertWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Votre Expert");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
            st.showAndWait();
              }
              else{
             alert.setContentText(newValue);   
              }
         }
                });
        
      new Thread(copyWorker).start();
       
      }
     public void actionFenetreSelectionFoods()
  {
     faireRepas.setOnMouseClicked(e->{
         try {
             String url="/formuly/view/frontend/make_foods.fxml";
             chargerPanelRepas(url);
         } catch (IOException ex) {
             Logger.getLogger(AcceuilleController.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
        MenuAvecMenuExistant.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                placerBilanChoixFoods();
             miseAjourCouleurBtn( MenuAvecMenuExistant, listBtn, NOMBRE_BUTTON_MAX);
             }
         }); 
       enregistrer_aliment.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
             String url="/formuly/view/frontend/inserer_aliment.fxml";
             InsertionAliment(url);
             }
         }); 
      enregistrer_aliment_fichier.setOnAction(event->{
          String url="/formuly/view/frontend/inserer_aliment_fichier.fxml";
        InsertionAliment_Fichier(url);
      });
    moteurCalcul.setOnAction(event->{
        LancerMoteur();
        //  miseAjourCouleurBtn(moteurCalcul, listBtn, NOMBRE_BUTTON_MAX);
      });
    interditAlimentaire.setOnAction(event->{
        String url="/formuly/view/frontend/inserer_pathologieAliments.fxml";
      lancerGestionInterditAlimentaire(url);
     //  miseAjourCouleurBtn(moteurCalcul, listBtn, NOMBRE_BUTTON_MAX);
    });
  }
      public void placerBilanChoixFoods()
   {
          try {
                    
      ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
              FXMLLoader loader = new FXMLLoader();
        // Parent root = (Parent)loader.load(); 
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load(getClass().getResource("/formuly/view/frontend/listeMenu.fxml")));
     controllerListeMenu= (ListeMenuController)loader.getController(); 
            //initialiserTableBilan();
          } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
         public void InsertionAliment(String url)
   {
           ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createInserFoodsWorker(url);
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
            st.setTitle("Enter Foods");
            st.initOwner(expert.getScene().getWindow());
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
                 alert.setTitle("erreur ");
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
                public void LancerMoteur()
   {
       if(windowMteurCacul==null)
           {
          try {
           
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/moteur_calcul.fxml"));
               ctr_moteur=new Moteur_calculController();
               loader.setController(ctr_moteur);
           Parent root = (Parent)loader.load(); 
        windowMteurCacul=new Stage();
        windowMteurCacul.setScene(new Scene(root));
        windowMteurCacul.setTitle("Insertion Aliment");
        windowMteurCacul.initOwner(moteurCalcul.getScene().getWindow());
        windowMteurCacul.initModality(Modality.APPLICATION_MODAL);
        windowMteurCacul.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(Moteur_calculController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
     else{
       windowMteurCacul.showAndWait();
       }
   }
              public void InsertionAliment_Fichier(String url)
   {
           ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createInserFoodsForFileWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Enter Foods For File");
            st.initOwner(expert.getScene().getWindow());
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
    private void lancerGestionInterditAlimentaire(String url) {
          ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createPathologieWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Gerer vos pathologie");
            st.initOwner(expert.getScene().getWindow());
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
       public void placerContenuAcceuille()
   {
          try {
                    
        ((BorderPane)(principal.getCenter())).getChildren().clear();
            center.getChildren().clear();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/contenuAcceuille.fxml"));
                  loader.setController(new ContenuAcceuilleController());
      ((BorderPane)(principal.getCenter())).getChildren().add(loader.load());
          } catch (IOException ex) {
                     Logger.getLogger(ContenuAcceuilleController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
    private ListeMenuController controllerListeMenu;
    private Select_the_foodsController controllerSelectionFoods;
    private Inserer_alimentController ctr_inserAliment;
    private Inserer_aliment_fichierController ctr_inserAlimentFichier;
    private Moteur_calculController ctr_moteur;
    private Inserer_pathologieAlimentsController ctr_patAliment;
    private Stage windowMteurCacul;
    private Parent root;

    
}
