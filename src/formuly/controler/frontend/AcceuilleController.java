/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.Excel.ExcelTools;
import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmFait;
import formuly.entities.FmRegle;
import formuly.entities.FmRegleFait;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    @FXML private Button accueille;
    @FXML private Label dumpLabelIndicator;
    @FXML private ProgressBar dumpProgressIndicator;
    @FXML private MenuItem telecharerAlimentItem;
    @FXML private MenuItem  about;
    @FXML private MenuItem baseConnaissanceDump;
     @FXML private MenuItem administrator;
     @FXML private MenuItem updateFoods;
     @FXML private MenuItem  Menufermer;
      @FXML private MenuItem  secureExpert;

    private Stage st;
    private Formuly_calculController  fmCalcul;
     private ExpertController  fmexpert;
    private final int NOMBRE_BUTTON_MAX=8;
    private Button[] listBtn;
    private ControlExpertController ctr;
    
    public AcceuilleController() {
        windowMteurCacul=null;
       
        //lisBtn=new Button[NOMBRE_BUTTON_MAX];
    }
    private void securiserExpert()
      {
           secureExpert.setOnAction(e->{
     Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        
     String content1="Confirmer l'opération SVP !!! "
             + " Celà vous permettrera de mettre un mot de passe avant d'avoir "
             + " Acces à votre espace espace expert";
     String content2="Espace déjà sécurisé"
             + " Merci!!!!!! ";
     String title1="confirmation";
     String title2="information";
       Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png")
      );
               alert.setGraphic(new ImageView(image));
     if(formulyTools.accesExpert==0)
     {
     alert.setAlertType(Alert.AlertType.INFORMATION);
     alert.setContentText(content2);
     alert.setTitle(title2);
     alert.showAndWait();
     }
     else
     {
     formulyTools.accesExpert=0;
     alert.setAlertType(Alert.AlertType.INFORMATION);
     alert.setContentText("Espace sécurisé");
     alert.setTitle("succes");
    alert.show();
     }
           });
      }
      public void lancerControl(String url,String urlsExpert)
    {
     try {
          if(formulyTools.accesExpert==0)
           {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            
        root = (Parent)loader.load(); 
     ctr=loader.getController();
     ctr.getValider().setOnAction(e->{
         if(ctr.getPassword().getText().equalsIgnoreCase(formulyTools.passwordExpert))
         {
        
          if(ctr.getMessageAffich().isSelected())
          {
          formulyTools.accesExpert=1;
          }
          else
          {
          formulyTools.accesExpert=0;
          }
           formulyTools.fermerFenetre(ctr.getAnnuler());
           LancerExpert(urlsExpert);
         }
         else
         {
         ctr.getInfoError().setText("Mot de pass Incorrect, veuilez reessayer SVP");
         }
        });
        st=new Stage();
        st.setScene(new Scene(root));
       st.setTitle("sécurité");
        st.initOwner(moteurCalcul.getScene().getWindow());
       st.initModality(Modality.APPLICATION_MODAL);
       st.setResizable(false);
        st.showAndWait();
          } 
     else
          {
            LancerExpert(urlsExpert);
          }
     }catch (IOException ex) {
                     Logger.getLogger(ControlExpertController.class.getName()).log(Level.SEVERE, null, ex);
                 }
  
    }
   private void actionButtonAccueille()
    {
        accueille.setOnAction(e->{
        String url="/formuly/view/frontend/contenuAcceuille.fxml";
        placeInStageMiddle(url,ContenuAcceuilleController.class);
         miseAjourCouleurBtn(accueille, listBtn, NOMBRE_BUTTON_MAX,"");
        });
    }
     public Button[] retournerListBtn()
     {
        Button[] btns={faireRepas,modifierMenu,listMenu,listAliment,enregistrer_aliment,MenuAvecMenuExistant,modifierAliment,accueille};
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
    public void ActionAdminstration()
    {
    administrator.setOnAction(e->{
          String urls="/formuly/view/frontend/importFait.fxml";
        lancerAbout(urls,ImportFaitController.class,"Mis à j our de la base de connaissance");
    });
     updateFoods.setOnAction(e->{
          String urls="/formuly/view/frontend/updateFoods.fxml";
        LancerToutProcessus(urls,UpdateFoodsController.class,"Mise à jour de la base des aliments",expert) ;
    });
    }
    
      public void ActionDumpageKnowLedgeBase()
    {
    baseConnaissanceDump.setOnAction(e->{
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

         List<FmFait> listeF=formulyTools.Liste_Fait();
          List<FmRegle> listeR=formulyTools.Liste_Regle();
          List<FmRegleFait> listRF=formulyTools.Liste_RegleFait();
         ExcelTools.cas=2;
         ExcelTools exlT=new ExcelTools();
      exlT.DumpKnowLedgeDataBase(listeR,listeF,listRF, dumpProgressIndicator,dumpLabelIndicator);
        }      
    });
    }
    public void actionAbout()
    {
    about.setOnAction(e->{
      String urls="/formuly/view/frontend/about.fxml";
        lancerAbout(urls,Moteur_calculController.class,"A propos de Formuly");
    });
    }
    public void lancerAbout(String url,Class clas,String title)
    {
     try {
           
    FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
      root = (Parent)loader.load(); 
        st=new Stage();
        st.setScene(new Scene(root));
       st.setTitle(title);
        st.initOwner(moteurCalcul.getScene().getWindow());
       st.initModality(Modality.APPLICATION_MODAL);
       st.setResizable(false);
        st.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(clas.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/dec.jpg")
            );
       fermer.setGraphic(new ImageView(image));
        listBtn=retournerListBtn();
         placerContenuAcceuille();
        actionFenetreSelectionFoods();
        actionAbout();
       // Button[] btn={faireRepas,modifierMenu,listMenu,listAliment,enregistrer_aliment,MenuAvecMenuExistant,modifierAliment};
        formulyTools.mettreEffetButton(listBtn,Color.ROYALBLUE);
        accordGauche.setExpandedPane(paneGauche);
        accordDroite.setExpandedPane(paneDroite);
        listAliment.setOnAction(event->{
        String urlLi="/formuly/view/frontend/liste_aliments.fxml";
         //  placerListAliment(urlLi);
           placeInStageMiddle(urlLi);
            miseAjourCouleurBtn(listAliment, listBtn, NOMBRE_BUTTON_MAX);
        });
         modifierAliment.setOnAction(event->{
        String urlLi="/formuly/view/frontend/liste_aliments.fxml";
         //  placerListAliment(urlLi);
           placeInStageMiddle(urlLi);
            miseAjourCouleurBtn(modifierAliment, listBtn, NOMBRE_BUTTON_MAX);
        });
     modifierMenu.setOnAction(event->{
//      String urls="/formuly/view/frontend/modifier_menu.fxml";
//         placerVue(urls);
        String urlLi="/formuly/view/frontend/modifier_menu.fxml";
           placeInStageMiddle(urlLi);
     miseAjourCouleurBtn(modifierMenu, listBtn, NOMBRE_BUTTON_MAX);
     });
       listMenu.setOnAction(event->{
//      String urls="/formuly/view/frontend/modifier_menu.fxml";
//         placerVue(urls);
        String urlLi="/formuly/view/frontend/modifier_menu.fxml";
           placeInStageMiddle(urlLi);
     miseAjourCouleurBtn(listMenu, listBtn, NOMBRE_BUTTON_MAX);
     });
     formulation.setOnAction(event->{
      String urls="/formuly/view/frontend/formuly_calcul.fxml";
       afficherFentre(urls) ;
       
     });
     expert.setOnMouseClicked(event->{
      String urls="/formuly/view/frontend/expert.fxml";
      String urlss="/formuly/view/frontend/controlExpert.fxml";
         lancerControl(urlss,urls);
//      boolean b=true;
//      if(formulyTools.accesExpert==0)
//      {
//          Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
//        String content="Espace sécurisé, veuillez entrer le mot de passe\n "
//                + " Merci!!! \n";
//        String title="Vérification";
//        alert.setContentText(content);
//        alert.setTitle(title);
//       b=faireControl(alert);
//      }
//      if(b)
//      {
//     LancerExpert(urls) ;
//      }
//      else
//      {
//      
//      }
     //  lancerExpert(urls);
     });
       // cat.setClip(lb);
     fermer.setOnAction(event->{
    // formulyTools.getEm().close();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        String content=" Assurez vous que tous vos opérations ont été enregistré\n "
                + " Veuillez confirmer la fermerture de Formuly SVP!!! \n";
        String title="confirmation";
        alert.setContentText(content);
        alert.setTitle(title);
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK)
        {
         formulyTools.fermerFenetre(fermer);
        }
     });
      Menufermer.setOnAction(event->{
    // formulyTools.getEm().close();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        String content=" Assurez vous que tous vos opérations ont été enregistré\n "
                + " Veuillez confirmer la fermerture de Formuly SVP!!! \n";
        String title="confirmation";
        alert.setContentText(content);
        alert.setTitle(title);
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK)
        {
         formulyTools.fermerFenetre(fermer);
        }
     });
     dumpLabelIndicator.setVisible(false);
     dumpProgressIndicator.setVisible(false);
     ActionDumpageFoods();
    ActionDumpageKnowLedgeBase();
    ActionAdminstration();
    actionButtonAccueille();
   
     securiserExpert();
    
   
    }
      public void shutdown() {
        // cleanup code here...
       Alert al= new Alert(Alert.AlertType.CONFIRMATION);
       al.showAndWait();
       al.getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
          if(al.getResult()==ButtonType.OK)
          {
          traiterFermerture();
          }
    }
      private Boolean faireControl(Alert alert)
      {
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
       
        GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
String styleCss="-fx-text-fill: red;\n" +
"       -fx-font-weight:bold;\n" +
"       -fx-background-color:#BBD2E1;\n" +
"       -fx-cursor:hand;\n" +
"        -fx-alignment: CENTER;";
String styleCssGrid=" -fx-background-color:linear-gradient(to top right,greenyellow,white,greenyellow 20%,white);";
grid.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
 
  grid.setStyle(styleCssGrid);
  
 final PasswordField tailleP = new PasswordField();
 final CheckBox choix = new CheckBox();
 
grid.add(new Label("passWord:"), 0, 0);
grid.add(tailleP, 1, 0);
grid.add(new Label("ne plus afficher :"), 0, 1);
grid.add(choix, 1, 1);

Callback myCallback = new Callback() {
     @Override
   public Object call(Object param) {
//              usernameResult = username.getText();
//              passwordResult = password.getText();
       return null;
              }
          }; 
 
choix.selectedProperty().addListener(new ChangeListener<Boolean>(){

          @Override
   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
       if(newValue==true)
       {
       formulyTools.accesExpert=1;    
       }
          }
      
      });
    alert.setGraphic(grid);
    boolean estOk=true;
      alert.showAndWait();
             if (alert.getResult() == ButtonType.OK) {
          if(!tailleP.getText().equalsIgnoreCase(formulyTools.passwordExpert))
          {
          estOk=false;
          }
      }
       return estOk;
      }
       private void traiterFermerture()
  {
//       Alert alerts =new Alert(Alert.AlertType.CONFIRMATION);
//       alerts.setHeaderText("fermerture de Formuly");
//       alerts.setContentText("voulez vraiment fermer l'application ? \n"
//               + " ");
//       alerts.getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
//       alerts.showAndWait();
//       if(alerts.getResult()==ButtonType.OK)
//       {
        Platform.exit();
      // }
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
        public Task createAllTaskWorker(String url,Class clas) {
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
            //fmexpert=new ExpertController();
            loader.setController(clas.newInstance());
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
           public Task createCalculBaseWorker(String url) {
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
            fmCalcul=new Formuly_calculController();
            loader.setController(fmCalcul);
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
           public Task createPutInMidlleWorker(String url) {
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
        // loader.setLocation(getClass().getResource("/formuly/view/frontend/liste_aliments.fxml"));
       //  Parent roots =loader.load();
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
            public Task createPutInMidlleWorker(String url,Class clas) {
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
        // loader.setLocation(getClass().getResource("/formuly/view/frontend/liste_aliments.fxml"));
       //  Parent roots =loader.load();
            loader.setController(clas.newInstance());
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
             if(!(listBntn[i].getId()).equals(idbtn) && !(listBntn[i].getId()).equals(accueille.getId())) 
             {
             listBntn[i].getStyleClass().clear();  
             listBntn[i].getStyleClass().add("nav");
             } 
         }
    }
      /**
       * utilisé pour mettre a jour le background des images lorsqu'on clique sur l'accueill btn
       * @param btn
       * @param listBntn
       * @param nbreBtnEnregistrer
       * @param option 
       */
        public void miseAjourCouleurBtn(Button btn,Button[] listBntn,int nbreBtnEnregistrer,String option)
    {
        String idbtn=btn.getId();
       for(int i=0;i<nbreBtnEnregistrer;i++)
         {
              if(!(listBntn[i].getId()).equals(idbtn) ) 
             {
             listBntn[i].getStyleClass().clear();  
             listBntn[i].getStyleClass().add("nav");
             }
              
         }
    }
    public void placeInStageMiddle(String url)
    {
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createPutInMidlleWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
           ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
          ((BorderPane)(principal.getCenter())).getChildren().add(root);
              alert.close();
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
      public void placeInStageMiddle(String url,Class clas)
    {
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createPutInMidlleWorker(url,clas);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
           ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
          ((BorderPane)(principal.getCenter())).getChildren().add(root);
              alert.close();
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
    public void placerListAliment(String url)
    {
         ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createPutInMidlleWorker(url);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
            alert.setContentText("preparation pour l'afficahe...");   
            alert.setAlertType(Alert.AlertType.INFORMATION);
           ((BorderPane)(principal.getCenter())).getChildren().clear();
                  center.getChildren().clear();
          ((BorderPane)(principal.getCenter())).getChildren().add(root);
              alert.close();
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
              Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Easy Foods Créator");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            st.setResizable(false);
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
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Lancement de votre espace");
               alert.show();
               Task copyWorker =createCalculBaseWorker(url);
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
               Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Base de calcul");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
            st.setResizable(false);
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
             Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Votre Expert");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
                st.setResizable(false);
            st.showAndWait();
              }
              else{
             alert.setContentText(newValue);   
              }
         }
                });
        
      new Thread(copyWorker).start();
       
      }
        private void LancerToutProcessus(String url,Class clas,String title,Button btn) 
    {
             
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle(title);
               alert.show();
               Task copyWorker =createAllTaskWorker(url, clas);
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
             Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle(title);
            st.initOwner(btn.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
                st.setResizable(false);
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
              //  placerBilanChoixFoods();
           String urlLi="/formuly/view/frontend/listeMenu.fxml";
           placeInStageMiddle(urlLi);
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
             Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Enter Foods");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
                st.setResizable(false);
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
             Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Enter Foods For File");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
                st.setResizable(false);
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
             Image image = new Image(
                    getClass().getResourceAsStream("/formuly/image/iconeAc.png")
            );
            st=new Stage();
            st.setScene(new Scene(root));
            st.getIcons().add(image);
            st.setTitle("Gerer vos pathologie");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
              alert.close();
                st.setResizable(false);
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
