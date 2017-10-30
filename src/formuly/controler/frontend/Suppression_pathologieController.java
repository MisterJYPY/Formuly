/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.model.frontend.pathologieModel;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.model.frontend.mainModel;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public final class Suppression_pathologieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableColumn<mainModel,Double> aliment_numero;

    @FXML
    private TableColumn<pathologieModel,Integer> nbreAliments;

    @FXML
    private TableView<pathologieModel> listePathologie;

    @FXML
    private TableView<mainModel> listeAliment;

    @FXML
    private TableColumn<pathologieModel, Integer> Path_numero;

    @FXML
    private TableColumn<pathologieModel, String> description;

    @FXML
    private TableColumn<mainModel,String> supAliment;

    @FXML
    private TextField recherche;

    @FXML
    private TableColumn<pathologieModel, String> nomPathologie;

    @FXML
    private TableColumn<pathologieModel, String> modifPathologie;

    @FXML
    private TableColumn<pathologieModel, String> supPathologie;

    @FXML
    private TableColumn<mainModel,String> nom_aliment;

    @FXML
    private TableColumn<mainModel, String> cuisson;

    @FXML
    private TableColumn<mainModel,String> pays;
    
    private List<FmPathologie> TouteLalistePathologie;
    private ObservableList<pathologieModel> listeModelPathologie;
     private ObservableList<pathologieModel> listeModelRecherche;
    
    public Suppression_pathologieController() 
       {
     TouteLalistePathologie=formulyTools.touteListePathologie();
     listeModelPathologie=retournerListeModelPathologie();
     //  listeModelRecherche=listeModelRecherche;
       }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initiliserTablePathologie(listeModelPathologie);
        actionSurTable( listePathologie);
        recherche.setOnKeyReleased(event->{
            String recherch=recherche.getText();
      ObservableList<pathologieModel> ResultRecherche=retourneListParCritere(recherch,listePathologie.getItems());
//          if(recherch.isEmpty())
//          {
//           ResultRecherche=listeModelRecherche;
//          }
    initiliserTablePathologie(ResultRecherche); 
        });
        
    }    
     public ObservableList<pathologieModel> retourneListParCritere(String chaineArechercher,ObservableList<pathologieModel> liste)
     {
       ObservableList<pathologieModel> listTri=FXCollections.observableArrayList();
       
        for(pathologieModel ligne:liste)
      {
         Pattern p = Pattern.compile(chaineArechercher, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(ligne.getLibelle().concat(" "+ligne.getDescription()));
          
        if(m.find())
        {
            listTri.add(ligne);
        }
      }   
       return listTri;
     }
    public void initiliserTablePathologie(ObservableList<pathologieModel> liste)
    {
       Path_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
       nomPathologie.setCellValueFactory(new PropertyValueFactory<>("libelle")); 
       description.setCellValueFactory(new PropertyValueFactory<>("description"));
       nbreAliments.setCellValueFactory(new PropertyValueFactory<>("nbreAliment"));
        placerBouton(modifPathologie,1);
        placerBouton(supPathologie,2);
       listePathologie.setItems(liste);
      
    }
    public ObservableList<pathologieModel> retournerListeModelPathologie()
    {
        pathologieModel pat=null;
        ObservableList<pathologieModel> listPat=FXCollections.observableArrayList();
        int numero=1;
               mainModel model;
       for(FmPathologie pato : TouteLalistePathologie)
       {
       List<FmAlimentsPathologie> liste=formulyTools.listeAlimentPathologie(pato);
         pat=new pathologieModel();
         pat.setIdPathologie(pato.getId());
         pat.setLibelle(pato.getLibelle());
         pat.setDescription((!pato.getDescription().isEmpty())?pato.getDescription():"Aucune Description Trouvée");
         pat.setDate(pato.getDate());
         pat.setNumero(numero);
         pat.setIdPathologie(pato.getId());
         pat.setAlimentsPathologie(liste);
         pat.setPathologie(pato);
         
         pat.setNbreAliment(liste.size());
      
            listPat.add(pat);
            numero++;
       }
      return listPat;
    }
    public int retournerRangElementTableau(pathologieModel patmodel)
    {
        int id=-1;
        int cpt=0;
      for(pathologieModel ligne:listePathologie.getItems())
      {
       if(ligne.equals(patmodel))
       {
         return cpt;
       }
       else{
        cpt++;
       }
      }
      return id=cpt;
    }
     public  ObservableList<mainModel> ListeAliments(pathologieModel patmodel)
     {
      
         ObservableList<mainModel> listAl=FXCollections.observableArrayList();
         List<FmAlimentsPathologie> liste=patmodel.getAlimentsPathologie();
                    listAl.clear();
              int i=1;
               //  System.out.println("apt :"+alPat.getLibelle());
             
               mainModel model=null;
                   for(FmAlimentsPathologie element:liste)
                   {
                  model=new mainModel();
                  model.setIdAliment(element.getAliment().getId());
                  model.setNom_aliment(element.getAliment().getNomFr());
                 //  System.out.println("nom :"+element.getAliment().getNomFr());
                  model.setPays(element.getAliment().getPays());
                  model.setNumero(i);
                  model.setMode_cuisson(element.getAliment().getModeCuisson());
                  model.setAlimentPathologie(element);
                  model.setRangPathologieModelDansLaTable(i-1);
                  listAl.add(model);
                  model=null;
                  i++;
                   
                   }
           listAl.add(model);
       return listAl;
     }
     public  ObservableList<mainModel> ListeAliments(List<FmAlimentsPathologie> liste)
     {
      
         ObservableList<mainModel> listAl=FXCollections.observableArrayList();
         //List<FmAlimentsPathologie> liste=patmodel.getAlimentsPathologie();
                //    listAl.clear();
              int i=1;
               //  System.out.println("apt :"+alPat.getLibelle());
       
                   mainModel model ;
         
                   for(FmAlimentsPathologie element:liste)
                   {
                       model=new mainModel();
                  model.setIdAliment(element.getAliment().getId());
                  model.setNom_aliment(element.getAliment().getNomFr());
                 //  System.out.println("nom :"+element.getAliment().getNomFr());
                  model.setPays(element.getAliment().getPays());
                  model.setNumero(i);
                  model.setMode_cuisson(element.getAliment().getModeCuisson());
                  model.setAlimentPathologie(element);
                  model.setRangPathologieModelDansLaTable(i-1);
                  listAl.add(model);
                  model=null;
                  i++;
                   
                   }
           //listAl.add(model);
       return listAl;
     }
      public Task LoadingAliment(pathologieModel models,int index) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
          
          try {
        ObservableList<mainModel> listAl=FXCollections.observableArrayList();
         //List<FmAlimentsPathologie> liste=patmodel.getAlimentsPathologie();
                //    listAl.clear();
          patCourant=models;
              int i=1;
               //  System.out.println("apt :"+alPat.getLibelle());
              updateMessage("chargement des Info ..........");
         ObservableList<mainModel> listAlimentEnCour =listePathologie.getItems().get(index).getListeAliments();        
         if(listAlimentEnCour.size()<=0)
         {
             listBas.clear();
                   mainModel model ;
              List<FmAlimentsPathologie> liste=models.getAlimentsPathologie();
               updateMessage("Preparation affichage ..........");
               updateProgress(10, 100);
               double pas=90/liste.size();
               double debut =pas+10;
                   for(FmAlimentsPathologie element:liste)
                   {
                       model=new mainModel();
                  model.setIdAliment(element.getAliment().getId());
                  model.setNom_aliment(element.getAliment().getNomFr());
                 //  System.out.println("nom :"+element.getAliment().getNomFr());
                  model.setPays(element.getAliment().getPays());
                  model.setNumero(i);
                  model.setMode_cuisson(element.getAliment().getModeCuisson());
                  model.setAlimentPathologie(element);
                  model.setPathologieModel(models);
                  listBas.add(model);
                  listAl.add(model);
                  model=null;
                     updateMessage("aliment "+(i+1)+" :"+element.getAliment().getNomFr());
                  i++;
                    //   System.out.println("debut : "+debut);
                       updateProgress(debut,100);
                       debut=debut+pas;
                   }
                   
                   listePathologie.getItems().get(index).setListeAliments(listAl);
                   updateMessage("terminer");
               }
     else{
             listBas.clear();
             listBas.addAll(listAlimentEnCour);
             updateProgress(90, 10);
             updateMessage("terminer");
         }
          }catch (Exception e) {
               updateMessage("erreur");
          }
      
         
          
      
        return true;
      }
    };
  }
       public Task ProccessusSupressionPathologie(pathologieModel models) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
              EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la suppression......");
              updateProgress(15,100);
          try {
            em.getTransaction().begin(); 
           FmPathologie pat=models.getPathologie();
            updateMessage("suppression de la pathologie......");
            updateProgress(50,100);
            FmPathologie  current=pat;
             if (!em.contains(pat)) {
             current = em.merge(pat);
             }
            em.remove(current);
          listePathologie.getItems().remove(models);
           updateMessage("suppression des interdits lié.....");
            updateProgress(69,100);
           models.getListeAliments().clear();
           if(patCourant!=null && patCourant.equals(models))
           {
           listeAliment.getItems().clear();   
           }
            updateMessage("preparation pour affichage.....");
            updateProgress(89,100);
            formulyTools.actualisserNumeroListe(listeModelPathologie,models.getNumero()-1,1);
            em.getTransaction().commit();
            updateMessage("terminer");
            updateProgress(100,100);
            patCourant=null;
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
        public void SupprimerPathologie(pathologieModel model)
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Chargement Aliment Interdit");
               alert.show();
               Task copyWorker =ProccessusSupressionPathologie(model);
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
               alert.setContentText("pathologie supprimer avec succes ");
              //  initialisation();
              // initialiserTableauListeAliment(listBas);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin chargement");
               alert.setContentText("pathologie supprimer avec succes");
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
      public void placerBouton(TableColumn<pathologieModel,String> colonne,int option)
    {
        if(option==1)
        {
    colonne.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<pathologieModel, String>, TableCell<pathologieModel, String>> cellFactory = new Callback<TableColumn<pathologieModel, String>, TableCell<pathologieModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<pathologieModel, String> param) {
                final TableCell<pathologieModel, String> cell = new TableCell<pathologieModel, String>() {

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
                      pathologieModel aliment= getTableView().getItems().get(getIndex());      
                              //  deplacerAliment(modelPath);
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
  Callback<TableColumn<pathologieModel, String>, TableCell<pathologieModel, String>> cellFactory = new Callback<TableColumn<pathologieModel, String>, TableCell<pathologieModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<pathologieModel, String> param) {
                final TableCell<pathologieModel, String> cell = new TableCell<pathologieModel, String>() {

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
                      pathologieModel modelPath= getTableView().getItems().get(getIndex());      
                              // supprimerAliment(modelPath);
                                SupprimerPathologieExis(modelPath);
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
      public void SupprimerPathologieExis(pathologieModel pat)
      {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
          String message="NOM DE LA PATHOLOGIE : "+pat.getLibelle()+"\n"
                        + "NOMBRE ALIMENTS INTERDITS : "+pat.getAlimentsPathologie().size()+"\n"
                     + " Vous avez choisi de supprimer cette patholgie : \n"
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
             SupprimerPathologie(pat);
                }
      }
        public void enregistrerAlimentDeuxPathologie(pathologieModel model,int index)
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Chargement Aliment Interdit");
               alert.show();
               Task copyWorker = LoadingAliment(model, index);
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
               initialiserTableauListeAliment(listBas);
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
        public void actionSurTable(TableView table)
    {
   table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           pathologieModel pathModel= (pathologieModel) table.getSelectionModel().getSelectedItem();
          // chargerTableBas(pathModel);
         int index=  table.getSelectionModel().getSelectedIndex();
           enregistrerAlimentDeuxPathologie(pathModel,index);
    }}
});
  }
     public void chargerTableBas(pathologieModel pathologieMpdel)
     {
      patCourant=pathologieMpdel;
      List<FmAlimentsPathologie> l=formulyTools.listeAlimentPathologie(pathologieMpdel.getPathologie());
      ObservableList<mainModel> listAliment=ListeAliments(l);
      initialiserTableauListeAliment(listAliment);
     }
      public void initialiserTableauListeAliment(ObservableList<mainModel> liste)
     {
      nom_aliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      aliment_numero.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      pays.setCellValueFactory(new PropertyValueFactory<>("pays")); 
      cuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
       listeAliment.getItems().clear();
    //  placerBouton(table1_action,1);
       placerBoutonSuppresionAlimentPathologie();
       listeAliment.getItems().setAll(liste);
     }
      public void placerBoutonSuppresionAlimentPathologie()
      {  
      supAliment.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
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
                    mainModel model= getTableView().getItems().get(getIndex());      
                             supprimerAlimentPathologie(model,getIndex());
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
         supAliment.setCellFactory(cellFactory);
      }
         public Task ProccessusSupressionAlimentPathologie(mainModel models,int nbre) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
              EntityManager em=formulyTools.getEm().createEntityManager();
              updateMessage("debut de la suppression de l'aliment......");
              updateProgress(15,100);
              int index=retournerRangElementTableau(models.getPathologieModel());
              System.out.println("numero :"+nbre);
          try {
            em.getTransaction().begin(); 
           FmAlimentsPathologie alPat=models.getAlimentPathologie();
           // pathologieModel pa=models.
            updateMessage("suppression de la pathologie......");
            updateProgress(50,100);
          FmAlimentsPathologie current=alPat;
             if (!em.contains(alPat)) {
             current = em.merge(alPat);
             }
             em.remove(current);
           updateMessage("suppression des interdits lié.....");
            updateProgress(69,100);
            //suppression de l'element dans les liste
             listeAliment.getItems().remove(models);
            models.getPathologieModel().getListeAliments().remove(models);
            models.getPathologieModel().setNbreAliment(listeAliment.getItems().size());
            listePathologie.getItems().get(index).setNbreAliment(listeAliment.getItems().size());
            listePathologie.getItems().set(index, listePathologie.getItems().get(index));
                       //actualisation des index du bas
               actualisserNumeroTable(listeAliment,nbre);
               updateProgress(80,100);
            updateMessage("preparation pour affichage.....");
           
            updateProgress(89,100);
       
            em.getTransaction().commit();
            updateMessage("terminer");
            updateProgress(100,100);
            patCourant=null;
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
         /**
          * methode permettant d'actualiser les numero d'un table view 
          * en reorganisant les numero les identifainats dans la table
          * @param table la tableView concerné de type mainModel
          * @param nbre  l'indice de l'element qui ete supprimer
          * Cette methode est appelé uniquement apres une suppression d'une ligne dans un tableau de mainModels
          */
         public void actualisserNumeroTable(TableView<mainModel> table,int nbre)
         {
           for(int i=nbre;i<table.getItems().size();i++)
              { 
             table.getItems().get(i).setNumero(table.getItems().get(i).getNumero()-1);
             table.getItems().set(i, table.getItems().get(i));
              }
         }
      public void supprimerAlimentPathologie(mainModel models,int nbre)
      {
       Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
          String message="NOM DE LA PATHOLOGIE : "+models.getPathologieModel().getLibelle()+"\n"
                        + "NOMs ALIMENTS INTERDITS : "+models.getNom_aliment()+"\n"
                     + " Vous avez choisi de supprimer cet aliment : \n"
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
                 supprimerAlpa(models,nbre);
                }
      } 
      public void supprimerAlpa(mainModel model,int nbre)
      {
       ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Supression "+model.getNom_aliment()+" ....");
               alert.show();
               Task copyWorker =ProccessusSupressionAlimentPathologie(model,nbre);
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
             
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setContentText("Aliment Supprimer avec succes ...");
              //  initialisation();
              // initialiserTableauListeAliment(listBas);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin Suppression");
               alert.setContentText("Aliment supprimer avec succes");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
            
              }
              else{
                 if(!newValue.equals("erreur"))
                 {
                 alert.setContentText(newValue);   
                 }
                 else{
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
              }
         }
                });
        
      new Thread(copyWorker).start();
      }
        private ObservableList<mainModel> listBas=FXCollections.observableArrayList();
        private pathologieModel patCourant=null;
}
