/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.Excel.ExcelTools;
import formuly.Excel.KnowBook;
import formuly.classe.formulyTools;
import formuly.entities.FmFait;
import formuly.entities.FmRegle;
import formuly.entities.FmRegleFait;
import formuly.model.frontend.regleFaitModel;
import formuly.model.frontend.regleModel;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class ImportFaitController implements Initializable {

     @FXML
    private TableColumn<regleModel,Date> date;

    @FXML
    private TableColumn<regleModel,Integer> nbFaitDe;

    @FXML
    private TableColumn<regleFaitModel,String> actionFait;

    @FXML
    private TableColumn<regleFaitModel,Integer> numFait;
    
     @FXML
    private TableView<regleFaitModel> tableFait;
      @FXML
    private TableView<regleModel> tableRegle;
    @FXML
    private TableColumn<regleFaitModel,String> fait;

    @FXML
    private TableColumn<regleModel,Integer> numRegle;

    @FXML
    private Button choisirFichier;
   @FXML
   private Button fermer;
   
   @FXML 
   private Button valider;
   
    @FXML
    private TableColumn<regleModel,String> actionR;

    @FXML
    private TableColumn<regleFaitModel,String> idFait;
     @FXML
    private TableColumn<regleModel,String> regle;

   private final FileChooser fileChooser ;
   private ObservableList<regleModel> listRel;
   private ObservableList<regleFaitModel> listFait;
   private List<FmRegleFait> lisReGleFait;

    public ImportFaitController() {
        System.out.println("création de fileChooser");
     fileChooser=new FileChooser();
     listFait=FXCollections.observableArrayList();
     listRel=FXCollections.observableArrayList();
     lisReGleFait=new ArrayList<>();
    }

   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choisirFichier.setOnAction(e->{
            System.out.println("seletion de fichier");
            takeFile(choisirFichier);
            System.out.println("fin selection fichier");
        });
        fermer.setOnAction(e->{
        formulyTools.fermerFenetre(fermer);
        });
      valider.setOnAction(e->{
          System.out.println("valierrr");
      traiterActionValider();
      });
    }   
    private void traiterActionValider()
    {
    
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
          Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/question.png"));
               alert.setGraphic(new ImageView(imageSucces));
               alert.setTitle("confirmation");
               alert.setHeaderText("Veuillez confirmez svp !!");
               alert.setContentText("Le traitement ne peut etre interrompu :\n"
                       + " Veuillez confirmer svp  \n");
               alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL); 
               alert.showAndWait();
               if(alert.getResult()==ButtonType.OK)
               {
               SuiviDeProcessusDeMiseAjour();   
               }
    }
     private void takeFile(Button btn)
  {
         Stage stage=(Stage) btn.getScene().getWindow();
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    ArrayList<String> info;
                     ArrayList<String> infoTrie;
                     //String extension=file.
                    if (file != null) {
                      ImporterBaseDepuisFichierExcel(file);
                    }
                    else
                    {
                        System.out.println("nulllll");
                    }
                   
  }
     public void initTableFait()
     {
       numFait.setCellValueFactory(new PropertyValueFactory<>("numero")); 
        idFait.setCellValueFactory(new PropertyValueFactory<>("identifiantFait"));
        fait.setCellValueFactory(new PropertyValueFactory<>("Conclusion"));
       // nbreRegleApplicable.setCellValueFactory(new PropertyValueFactory<>("nombreRegleApplicable"));
           placerBoutonFait();
      //   placerBouton(detailsFait,2);
        tableFait.setItems(listFait);
     }
      public void initTableRegle()
     {
       numRegle.setCellValueFactory(new PropertyValueFactory<>("numRegle")); 
        date.setCellValueFactory(new PropertyValueFactory<>("dateModif"));
        nbFaitDe.setCellValueFactory(new PropertyValueFactory<>("nombreFaitDeclencher"));
         regle.setCellValueFactory(new PropertyValueFactory<>("libelleExplicite"));
       // nbreRegleApplicable.setCellValueFactory(new PropertyValueFactory<>("nombreRegleApplicable"));
    placerBoutonRegle();
      //   placerBouton(detailsFait,2);
        tableRegle.setItems(listRel);
     }
     private void extraireElement(List<KnowBook> book)
     {
         regleFaitModel rgf;
         regleModel regM;
         int cmpteurRegle=1;
         int compteurFait=1;
         int compteurRFait=1;
//         int dernierIdRegle=formulyTools.TrouverDernierIdentifiant_Regle()+1;
//          int dernierIdFait=formulyTools.TrouverDernierIdentifiant_Fait()+1;
          int dernierIdRegle=1;
          int dernierIdFait=1;
           int dernierIdRFait=1;
      for(KnowBook list :book)
       {
        //on enregistre en tant que regle
           
          if(list.getNbreFaitDeclencherEntier()!=null && list.getQuatriemeColonne()==null && list.getNbreFaitDeclencherEntier().toString().length()<3)
          {
          FmRegle regle=new FmRegle( dernierIdRegle);
          regle.setLibelleRegle(list.getPremiereColonne());
          regle.setLibelleRegleClair(list.getSecondeColonne());
          regle.setNbreFaitDeclencher(list.getNbreFaitDeclencherEntier());
          regle.setDerniereModif(new Timestamp(new Date().getTime()));
          regM=new regleModel();
          regM.setNombreFaitDeclencher(list.getNbreFaitDeclencherEntier());
          regM.setLibelleExplicite(list.getSecondeColonne());
          regM.setLibelleImplicite(list.getPremiereColonne());
          regM.setNumRegle(cmpteurRegle);
          regM.setDateModif(new Timestamp(new Date().getTime()));
          regM.setRegle(regle);
          dernierIdRegle++;
           cmpteurRegle++;
           listRel.add(regM);
          }
       //on enregistre en tant que fait
        else
          {
          if(list.getNbreFaitDeclencherEntier()==null && list.getQuatriemeColonne()==null)
          {
          FmFait fai=new FmFait(dernierIdFait);
          fai.setLettreFait(list.getPremiereColonne());
          fai.setLibelleFait(list.getSecondeColonne());
          fai.setDerniereModif(new Timestamp(new Date().getTime()));
          rgf=new regleFaitModel();
          rgf.setConclusion(fai.getLibelleFait());
          rgf.setIdentifiantFait(fai.getLettreFait());
          rgf.setDateModif(fai.getDerniereModif());
          rgf.setFait(fai);
          rgf.setNumero(compteurFait);
          dernierIdFait++;
          compteurFait++;
          listFait.add(rgf);
          }
          else
          {
            //regle Fait recuperer
            if(!list.getSecondeColonne().isEmpty() && !list.getPremiereColonne().isEmpty())
            {
              FmRegleFait regleF=new FmRegleFait(dernierIdRFait);
              int id=Double.valueOf(list.getSecondeColonne()).intValue();
                System.out.println("id : "+id);
              FmRegle regl=new FmRegle(id);
              FmFait fai=new FmFait(Double.valueOf(list.getPremiereColonne()).intValue());
              regleF.setFait(fai);
              regleF.setRegle(regl);
              regleF.setDerniereModif(new Timestamp(new Date().getTime()));
              lisReGleFait.add(regleF);
              dernierIdRFait++;
            }
          }
          }
       }
         System.out.println("nombre de regle Fait : "+lisReGleFait.size());
     }
     public FmRegle retrouverFmRegle(FmRegleFait rgf,ObservableList<regleModel> list)
     {
     FmRegle fmR=null;
        for(regleModel rm:list)
         {
       FmRegle rg=rm.getRegle();
       if(Objects.equals(rg.getId(), rgf.getRegle().getId()))
       {
        return fmR=rg;  
        }
         }
     return fmR;
     }
      public FmFait retrouverFmFait(FmRegleFait rgf,ObservableList<regleFaitModel> list)
     {
      FmFait fmR=null;
        for(regleFaitModel rm:list)
         {
       FmFait rg=rm.getFait();
       if(Objects.equals(rg.getId(), rgf.getFait().getId()))
       {
        return fmR=rg;  
        }
         }
     return fmR;
     }
     private void lectureFichier(List<KnowBook> book)
     {
       for(KnowBook list :book)
       {
           System.out.println(list.getPremiereColonne()+"  "+list.getSecondeColonne()+"  "+list.getNbreFaitDeclencherEntier());
       }
     }
      private static void configureFileChooser(final FileChooser fileChooser) {      
             fileChooser.setTitle("fichier depuis Formuly V.0.1");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );    
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel ","*.xlsx")
            );
    }
        public void placerBoutonFait()
    {
     actionFait.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<regleFaitModel, String>, TableCell<regleFaitModel, String>> cellFactory = new Callback<TableColumn<regleFaitModel, String>, TableCell<regleFaitModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<regleFaitModel, String> param) {
            final TableCell<regleFaitModel, String> cell = new TableCell<regleFaitModel, String>() {

                    final Button btn = new Button("Back");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                          //  btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                      regleFaitModel model= getTableView().getItems().get(getIndex());
                       int id=getIndex();
                       //tableFait.getItems().remove(id);
                       listFait.remove(model);
                            });
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
        actionFait.setCellFactory(cellFactory);

    }  
         public void placerBoutonRegle()
    {
     actionR.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<regleModel, String>, TableCell<regleModel, String>> cellFactory = new Callback<TableColumn<regleModel, String>, TableCell<regleModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<regleModel, String> param) {
            final TableCell<regleModel, String> cell = new TableCell<regleModel, String>() {

                    final Button btn = new Button("Back");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                          //  btn.getStyleClass().add("dark-blue");
                            btn.setOnAction(event -> {
                      regleModel model= getTableView().getItems().get(getIndex());
                       int id=getIndex();
                     // tableRegle.getItems().remove(id);
                       listRel.remove(model);
                            });
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
        actionR.setCellFactory(cellFactory);

    }  
          public void ImporterBaseDepuisFichierExcel(File file)
         {
               ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Traitement du fichier");
                alert.show();
               Task copyWorker = createWorker(file);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
            //  System.out.println("new value: "+newValue);
              if("terminer".equals(newValue))
              { 
                // registerThread.
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                initTableFait();
                initTableRegle();
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
               alert.setGraphic(new ImageView(imageSucces));
               alert.setTitle("Traitement termine");
               alert.setContentText("Votre Fichier a ete  avec succes :\n"
                       + " vous Trouverez dans les tableaux suivants les informations de \n"
                       + " connaissances recceuillies depuis l'importation\n"
                       + " Merci de valider ");
               if(listFait.size()>0 || listRel.size()>0)
               {
               valider.setDisable(false);
               }
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();
               
              }
              else{
               if("terminer rien".equals(newValue))
              { 
                // registerThread.
              //  initialiserInfoFichier(file,nomFichier,derniereModif,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               alert.setGraphic(null);
               alert.setTitle("Traitement termine");
               alert.setContentText("Votre Fichier a ete traité et aucune Entree :\n"
                       + " Valide pouvant etre Inserée n'as ete enregistré \n"
                       + " Veuillez revoir vos  Entrees SVP ");
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();
               
              }
               else{
                   if("erreur".equals(newValue))
                   {
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               alert.setGraphic(null);
               alert.setTitle("Erreur rencontre");
               alert.setContentText("Une erreur innatendue s'est produite lors :\n"
                       + " de la lecture du fichier choisie  \n"
                       + " Veuillez revoir le format du fichier et reessayer SVP !!!! ");
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();     
                   }
                 else{
              alert.setContentText(newValue)  ;
                   }
               }
              }
             
         }
                });
        
        new Thread(copyWorker).start();
         }
    public Task TaskForUpDateKnowledgeBase() {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          try {
                EntityManager em=formulyTools.getEm().createEntityManager();
                em.getTransaction().begin();
                   updateMessage("Debut du processus ...");
                         updateProgress(5, 100);
                 Query q1=em.createNativeQuery("DELETE FROM fm_fait");
                 Query q2=em.createNativeQuery("DELETE FROM fm_regle");
                  updateMessage("Desactiation de la base de connaissance ...");
                   updateProgress(9, 100);
                  q1.executeUpdate();
                  q2.executeUpdate();
                   updateProgress(14, 100);
                 updateMessage("debut Mise a jour des faits  ...");
                  int tailleFait=listFait.size();
                   double debut=14;
                  double incrementFait=28/tailleFait; 
                   for(regleFaitModel fait : listFait)
                   {
                    updateMessage("enregistrement : "+fait.getlettreFait());
                    debut=debut+incrementFait;
                    FmFait faitEnr=fait.getFait();
                    em.persist(faitEnr);
                    updateProgress(debut, 100);
                   }
                 updateMessage("debut Mise a jour des Regles  ...");
                 int tailleRegle=listRel.size();
                incrementFait=28/tailleRegle; 
                   for(regleModel regle: listRel)
                   {
                    debut=debut+incrementFait;
                   FmRegle regleEnr=regle.getRegle();
                    em.persist(regleEnr);
                    updateProgress(debut, 100);
                   }
                    updateMessage("Mise a jour des liaisons ...");
                 int tailleRegleFait=lisReGleFait.size();
                incrementFait=28/tailleRegle; 
                   for(FmRegleFait regleF: lisReGleFait)
                   {
                    debut=debut+incrementFait;
                    FmRegle OldRight=retrouverFmRegle(regleF,listRel);
                    FmFait OldKnowledge=retrouverFmFait(regleF,listFait);
                    if(OldRight!=null && OldKnowledge!=null)
                    {
                     regleF.setRegle(OldRight);
                     regleF.setFait(OldKnowledge);
                      em.persist(regleF);
                    }
                    updateProgress(debut, 100);
                   }
                
                     updateMessage("activation de la base de connaissance ...");
                     Thread.sleep(15);
                     updateProgress(99.99, 100);
                  updateMessage("terminer");
                        //   formulyTools.viderCache();
                        
                           em.getTransaction().commit();
                             em.clear();
              }
          catch (Exception e) {
               updateMessage("erreur");  
              System.out.println("une erreur produite : "+e.getLocalizedMessage());
         Logger.getLogger(ImportFaitController.class.getName()).log(Level.SEVERE, null, e);
           
          }
        return true;
      }
    };
  }
      public void SuiviDeProcessusDeMiseAjour()
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Process For Update .....");
                alert.show();
               Task copyWorker = TaskForUpDateKnowledgeBase();
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
            //  System.out.println("new value: "+newValue);
              if("terminer".equals(newValue))
              { 
                // registerThread.
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               listFait.clear();
               listRel.clear();
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
               alert.setGraphic(new ImageView(imageSucces));
               alert.setTitle("Mise à jour terminée");
               alert.setContentText("Vous pouvez visualisez votre base de connaissance :\n"
                       + " dans la fenetre pour la mise à jour des faits depuis la page principale \n"
                       + " Merci \n"
                       + "");
               
//               formulyTools.viderClassEnCache(FmRegle.class);
//               formulyTools.viderClassEnCache(FmRegleFait.class);
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();
               
              }
              else{
               if("terminer rien".equals(newValue))
              { 
                // registerThread.
              //  initialiserInfoFichier(file,nomFichier,derniereModif,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               alert.setGraphic(null);
               alert.setTitle("Traitement termine");
               alert.setContentText("Votre Fichier a ete traité et aucune Entree :\n"
                       + " Valide pouvant etre Inserée n'as ete enregistré \n"
                       + " Veuillez revoir vos  Entrees SVP ");
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();
               
              }
               else{
                   if("erreur".equals(newValue))
                   {
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               alert.setGraphic(null);
               alert.setTitle("Erreur rencontre");
               alert.setContentText("Une erreur innatendue s'est produite lors :\n"
                       + " de la lecture du fichier choisie  \n"
                       + " Veuillez revoir le format du fichier et reessayer SVP !!!! ");
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();     
                   }
                 else{
              alert.setContentText(newValue)  ;
                   }
               }
              }
             
         }
                });
        
        new Thread(copyWorker).start();
         }
     public Task createWorker(File file) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
                     ArrayList<String> info=null;
                     ArrayList<String> infoTrie;
                     
   
          try {
                 List<KnowBook> listB;
                 ExcelTools lecteurFichirM=new ExcelTools();
                     updateProgress(5, 100);
                     updateMessage("Lecture du fichier ...");
                   //  info=RecupererElementFichier(file);
                     listB=lecteurFichirM.readBooksFromExcelFiles(file);
                     updateProgress(30, 100);
                     updateMessage("fin de la lecture ....");
                     Thread.sleep(15);
                  //   infoTrie=extraireInformationDsLeLot(info);
                      updateProgress(40, 100);
                     updateMessage("Traitement du contenu...");
                        Thread.sleep(10);
                      extraireElement(listB);
                      updateProgress(70, 100);  
                      if(listB.size()>0)
                      {
                    updateMessage("nous preparons l'affichage...");
                        // listeEnregistrer=listeSaisie;
                          updateProgress(80, 100); 
                          updateMessage("Presque Fini");
                          Thread.sleep(10);
                     //   initialiserTab1(listeSaisie);
                          updateProgress(100, 100);
                          updateMessage("terminer");
                      }
                      else{
                      updateProgress(100, 100);
                      updateMessage("terminer rien");
                      }
              }
          catch (Exception e) {
               updateMessage("erreur");  
             // System.out.println("une erreur produite : "+e.getLocalizedMessage());
         Logger.getLogger(ExcelTools.class.getName()).log(Level.SEVERE, null, e);
           
          }
        return true;
      }
    };
  }
}
