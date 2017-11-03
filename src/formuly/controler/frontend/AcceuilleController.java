/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
       
     });
       // cat.setClip(lb);
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
    public void chargerPanelRepas() throws IOException
    {
             
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/formuly/view/frontend/make_foods.fxml"));
         Parent root = loader.load();
         controllerSelectionFoods= loader.getController();
         st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("formuly Foods Selector");
         st.initOwner(faireRepas.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         
         st.showAndWait();
       //  return st;
       
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
            st.setTitle("formuly Foods Selector");
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
             
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(url));
            fmexpert=new ExpertController();
            loader.setController(fmexpert);
            Parent root = loader.load();
            st=new Stage();
            st.setScene(new Scene(root));
            st.setTitle("Votre Expert");
            st.initOwner(expert.getScene().getWindow());
            st.initModality(Modality.APPLICATION_MODAL);
            
            st.showAndWait();
            //  return st;
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      }
     public void actionFenetreSelectionFoods()
  {
   faireRepas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            try {
               
//                faireRepas.getScene().setCursor(javafx.scene.Cursor.WAIT);
//                st.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
//               
//                
//                st.showAndWait();
//                faireRepas.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
//              //  st=new Stage();
                chargerPanelRepas();
               // mettreAction();
            } catch (IOException ex) {
                Logger.getLogger(MainPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
             InsertionAliment();
             }
         }); 
      enregistrer_aliment_fichier.setOnAction(event->{
        InsertionAliment_Fichier();
      });
    moteurCalcul.setOnAction(event->{
        LancerMoteur();
        //  miseAjourCouleurBtn(moteurCalcul, listBtn, NOMBRE_BUTTON_MAX);
      });
    interditAlimentaire.setOnAction(event->{
      lancerGestionInterditAlimentaire();
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
         public void InsertionAliment()
   {
          try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/inserer_aliment.fxml"));
               ctr_inserAliment=new Inserer_alimentController();
               loader.setController(ctr_inserAliment);
           Parent root = (Parent)loader.load(); 
                 st=null;
               st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("Insertion Aliment");
         st.initOwner(enregistrer_aliment.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(Select_the_foodsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
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
              public void InsertionAliment_Fichier()
   {
          try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/inserer_aliment_fichier.fxml"));
               ctr_inserAlimentFichier=new Inserer_aliment_fichierController();
               loader.setController(ctr_inserAlimentFichier);
           Parent root = (Parent)loader.load(); 
                 st=null;
               st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("Insertion Aliment");
         st.initOwner(enregistrer_aliment_fichier.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(Inserer_aliment_fichierController.class.getName()).log(Level.SEVERE, null, ex);
                 }
   }
    private void lancerGestionInterditAlimentaire() {
      try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/formuly/view/frontend/inserer_pathologieAliments.fxml"));
               ctr_patAliment=new Inserer_pathologieAlimentsController();
               loader.setController( ctr_patAliment);
           Parent root = (Parent)loader.load(); 
                 st=null;
               st=new Stage();
         st.setScene(new Scene(root));
         st.setTitle("Insertion Aliment");
         st.initOwner(interditAlimentaire.getScene().getWindow());
         st.initModality(Modality.APPLICATION_MODAL);
         st.showAndWait();
          } catch (IOException ex) {
                     Logger.getLogger(Inserer_pathologieAlimentsController.class.getName()).log(Level.SEVERE, null, ex);
                 }
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

    
}
