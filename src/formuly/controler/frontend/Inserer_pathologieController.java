/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.classe.repasModel;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmPathologie;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.modelFoodSelect;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Inserer_pathologieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button valider;

    @FXML
    private TableColumn<mainModel, Double> table1_numero;

    @FXML
    private TableColumn<mainModel,String> table2_action;

    @FXML
    private ComboBox<FmPathologie> listePathologie;

    @FXML
    private TableColumn<?, ?> table1_modeCuisson;

    @FXML
    private Tooltip ToolTipRecherche;

    @FXML
    private TableColumn<mainModel, String> table2_modeCuisson;

    @FXML
    private TableColumn<mainModel, Double> table2_numero;

    @FXML
    private TextField recherche;

    @FXML
    private TableColumn<mainModel,String> table2_nomAliment;
    
    @FXML 
       private TableColumn <mainModel,String> table3_nomAliment;
    @FXML 
     private TableColumn <mainModel,String> table3_cuisson;
    @FXML 
     private TableColumn <mainModel,String> table3_pays;
    @FXML
    private TableColumn<mainModel, String> table1_pays;

    @FXML
    private TextField nomPathologie;

    @FXML
    private TableView<mainModel> tableListe;
    @FXML 
    private TableView<mainModel> tableAlimentNonEnregistre;

    @FXML
    private TableView<mainModel> tableSelection;

    @FXML
    private TableColumn<mainModel, String> table2_pays;

    @FXML
    private TextArea descriptionPathologie;

    @FXML
    private TableColumn<mainModel, String> table1_action;
    
    @FXML
    private TableColumn<mainModel, String> table1_nomAliment;
    @FXML private Label labelAttention;
    @FXML private Tooltip infoLabelHalt;
    @FXML private Tooltip TtypeNomPathologie;
    private ObservableList<mainModel> obsListTable1 ;
    private ObservableList<mainModel> listRecherche;
    private List<FmPathologie> listPathologie;
    private List<FmAlimentsPathologie> listeAlimentPathologieNvo;
       private List<FmAlimentsPathologie> listeAlimentPathologieEncien;
       private FmPathologie nouvellePathologie;
       private FmPathologie enciennePathologie;
    private static int dernierIdAlimentPathologie;
    public Inserer_pathologieController() {
    obsListTable1=formulyTools.getobservableListMainModel(1);
    listRecherche=obsListTable1;
    listPathologie=modelFoodSelect.listePathologie();
    listeAlimentPathologieEncien=new ArrayList<>();
    listeAlimentPathologieNvo=new ArrayList<>();
    nouvellePathologie=null;
    enciennePathologie=null;
    dernierIdAlimentPathologie=formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelAttention.setVisible(false);
        tableAlimentNonEnregistre.setVisible(false);
        initialiserTableauListeAliment(obsListTable1);
        Button[] lisbtn={valider};
        formulyTools.mettreEffetButton(lisbtn);
        recherche.setOnKeyReleased(event->{
          String contenu=recherche.getText();
             // TtypeNomPathologie.set
             if(!contenu.isEmpty())
             {
             initialiserTableauListeAliment(retourneListParCritere(contenu,listRecherche));
            
             }
        });
        ActionSurPathologie(listePathologie,listPathologie);
        valider.setOnAction(event->{
        traiterEnregistrement();
        });
        listePathologie.setOnAction(event->{
          traitementCasDejaPresent();
        });
        
    }   
      public void ActionSurPathologie(ComboBox<FmPathologie> info_path,List<FmPathologie> liste)
    {
      info_path.setItems(FXCollections.observableList(liste));
           info_path.getSelectionModel().selectFirst();
          info_path.setCellFactory(new Callback<ListView<FmPathologie>,ListCell<FmPathologie>>(){
            @Override
            public ListCell<FmPathologie> call(ListView<FmPathologie> l){
                return new ListCell<FmPathologie>(){
                    @Override
                    protected void updateItem(FmPathologie item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                         setText(item.getLibelle());
                        }
                    }
                } ;
            }
        });
           info_path.setConverter(new StringConverter<FmPathologie>() {
              @Override
              public String toString(FmPathologie user) {
                if (user == null){
                  return null;
                } else {
                  return user.getLibelle();
                }
              }

            @Override
            public FmPathologie fromString(String userId) {
                return null;
            }
        });
        
    }
    public void initialiserTableauListeAliment(ObservableList<mainModel> liste)
     {
      table1_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      table1_numero.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays")); 
      table1_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson")); 
       tableListe.getItems().clear();
      placerBouton(table1_action,1);
      tableListe.getItems().addAll(liste);
     }
      public void initialiserTableauAlimentNonEnregistre(ObservableList<mainModel> list)
      {
      
        infoLabelHalt.setText("Aliment(s) Non Pris en compte pour la pathologie \n"
                + " "+listePathologie.getValue().getLibelle()+" Lors de l'enregistrement \n");
          Image image = new Image(
    getClass().getResourceAsStream("/formuly/image/war.jpg")
     );
         infoLabelHalt.setGraphic(new ImageView(image));  
         labelAttention.setText("Attention aliment(s) deja Interdit pour "+listePathologie.getValue().getLibelle());
         labelAttention.setGraphic(new ImageView(image));
         labelAttention.setStyle(" -fx-background-color:linear-gradient(to top right,white,red,red,white);");
    
       table3_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
       table3_pays.setCellValueFactory(new PropertyValueFactory<>("pays")); 
       table3_cuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson")); 
      
       tableAlimentNonEnregistre.setItems(list);
          labelAttention.setVisible(true);
       tableAlimentNonEnregistre.setVisible(true);
        
      }
    /**
     * methode permettant de positionner des boutons sur
     * des tableView pour des actions particulieres
     * @param colonne la colonne
     * @param option  une variable utile dans le cas de deux table
     */
     public void placerBouton(TableColumn<mainModel,String> colonne,int option)
    {
        if(option==1)
        {
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
                       mainModel aliment= getTableView().getItems().get(getIndex());      
                                deplacerAliment(aliment);
                            });
                        
//                            btn.setOnMouseDragOver(event->{
//                             btn.getStyleClass().add("dark-blue-hover"); 
//                            });
                            //btn.setGraphic(valider);
                             Image image= new Image(
     getClass().getResourceAsStream("/formuly/image/bass.png"));
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
                       mainModel aliment= getTableView().getItems().get(getIndex());      
                               supprimerAliment(aliment);
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
     public ObservableList<mainModel> retourneListParCritere(String chaineArechercher,ObservableList<mainModel> liste)
     {
       ObservableList<mainModel> listTri=FXCollections.observableArrayList();
       
        for(mainModel ligne:liste)
      {
         Pattern p = Pattern.compile(chaineArechercher, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(ligne.getNom_aliment());
          
        if(m.find())
        {
            listTri.add(ligne);
        }
      }   
       return listTri;
     }
     public void deplacerAliment(mainModel model)
     {
     table2_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      table2_numero.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      table2_pays.setCellValueFactory(new PropertyValueFactory<>("pays")); 
      table2_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson")); 
      tableSelection.getItems().add(model);
      placerBouton(table2_action,2);
        obsListTable1.remove(model);
      tableListe.getItems().remove(model);
       if( tableListe.getItems().size()==0)
       {
          recherche.setText("");
         tableListe.setItems(obsListTable1);
       }
       traitementCasDejaPresent();
       
     }
     public void traitementCasDejaPresent()
     {
     if(listePathologie.getValue()!=null && !listePathologie.getValue().getLibelle().equals("aucun choix"))
       {
          if(tableSelection.getItems().size()>0)
          {
      ObservableList<mainModel> listInterdi=verificationPathologie(tableSelection.getItems(),listePathologie.getValue(),"");
      if(listInterdi.size()>0)
             {
         initialiserTableauAlimentNonEnregistre(listInterdi);     
             }
        else{
          tableAlimentNonEnregistre.getItems().clear();
         labelAttention.setText("");
         labelAttention.setStyle(" -fx-background-color:white");
         labelAttention.setVisible(false);
         tableAlimentNonEnregistre.setVisible(false);
             }
         
          }
           else{
          tableAlimentNonEnregistre.getItems().clear();
         labelAttention.setText("");
         labelAttention.setStyle(" -fx-background-color:white");
         labelAttention.setVisible(false);
         tableAlimentNonEnregistre.setVisible(false);
             }
          
       }
          else{
          tableAlimentNonEnregistre.getItems().clear();
         labelAttention.setText("");
         labelAttention.setStyle(" -fx-background-color:white");
         labelAttention.setVisible(false);
         tableAlimentNonEnregistre.setVisible(false);
             }
     }
       public void supprimerAliment(mainModel aliment)
                   {
            tableSelection.getItems().remove(aliment); 
            obsListTable1.add(aliment);
             traitementCasDejaPresent();
           // tableListe.getItems().add(aliment);
                    }
       public void traiterEnregistrement()
              {
          String nomPathologi=nomPathologie.getText();
          String descriptio =descriptionPathologie.getText();
          FmPathologie pat = listePathologie.getValue();
          ObservableList <mainModel> list=tableSelection.getItems();
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            
                String titre="Avertissement Avant Insertion";
                String enetete="Insertion avec valeur Nulle";
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
               
            if(!nomPathologi.isEmpty() && (pat!=null && !pat.getLibelle().equals("aucun choix")))
                    {
                String completion=(list.size()>0)?"Les Deux seront enregistrées avec les memes aliments interdits \n "
                        + "NOMBRE ALIMENTS INTERDITS DETECTES : "+list.size()+" \n":"Aucun Aliments Interdit pour ces pathologies entrées \n "
                        + " SEULE la Nouvelle pathologie saisie sera enregistrée dans ce cas \n";
           String message="2 PATHOLOGIES SAISIES detectées dont une existane et une nouvelle \n"
                       + completion
                     + " Veuillez Confirmez l'opperation SVP \n";
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
             alert.setContentText(message);
                    alert.showAndWait();
                   
                if(alert.getResult()==ButtonType.YES)
                {
                   //recuperation de la pathologie
                    enciennePathologie=pat;
              ObservableList<mainModel> listEncien=ListDesAlimentsPourPathologieExistante(tableAlimentNonEnregistre.getItems(),tableSelection.getItems());
              listeAlimentPathologieEncien=listAlimentPathologie(listEncien,enciennePathologie);
              
                   //recuperation des info pour nouveau
               nouvellePathologie=getNewInstancePathologie(nomPathologi, descriptio);
               listeAlimentPathologieNvo=listAlimentPathologie(list, nouvellePathologie);
               
               enregistrerAlimentDeuxPathologie();
                }
                //nous affichons que seul une patholie peut etre enregistré a la fois
                    }
            else{
               if(!nomPathologi.isEmpty())
                {
             String completion=(list.size()>0)?"NOM DE LA PATHOLOGIE : "+nomPathologi+" \n"
                        + "NOMBRE ALIMENTS INTERDITS DETECTES : "+list.size()+" \n":"NOM DE LA PATHOLOGIE : "+nomPathologi+" \n"
                     + "Aucun Aliments Interdit pour cette pathologie detectée \n "
                        + " SEULE la Nouvelle pathologie saisie sera enregistrée dans ce cas \n";
           String message=""
                       + completion
                     + " Veuillez Confirmez l'opperation SVP \n";
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                  alert.setContentText(message);
                    alert.showAndWait();
              
                if(alert.getResult()==ButtonType.YES)
                {
                     //recuperation des info pour nouveau
               nouvellePathologie=getNewInstancePathologie(nomPathologi, descriptio);
               listeAlimentPathologieNvo=listAlimentPathologie(list,  nouvellePathologie);
               enregistrerAlimentDeuxPathologie();
                }
                }
               else{
          if(pat!=null && !pat.getLibelle().equals("aucun choix"))
                     {
                 FmPathologie  pathologi= listePathologie.getValue();
                 nomPathologi =pathologi.getLibelle();
             String completion=(list.size()>0)?"NOM DE LA PATHOLOGIE : "+nomPathologi+" \n"
                        + "NOMBRE ALIMENTS INTERDITS DETECTES : "+list.size()+" \n"
                     + " Pathologie existante nous allons mettre a jour ses donees":"NOM DE LA PATHOLOGIE : "+nomPathologi+" \n"
                     + "Aucun Aliments Interdit pour cette pathologie detectée \n "
                        + " Verifier Vos saisies SVP .merci \n";
           String message=""
                       + completion
                     + " Veuillez Confirmez l'opperation SVP \n";
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            if(list.size()>0)
                 {
                     alert.setContentText(message);
                     alert.showAndWait();
                 
                if(alert.getResult()==ButtonType.YES)
                {
                //recuperation de la pathologie
               enciennePathologie=pat;
              ObservableList<mainModel> listEncien=ListDesAlimentsPourPathologieExistante(tableAlimentNonEnregistre.getItems(),tableSelection.getItems());
              listeAlimentPathologieEncien=listAlimentPathologie(listEncien,enciennePathologie);
               enregistrerAlimentDeuxPathologie();
                }
                   
                 }
            else{
             alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText(message);
                    alert.showAndWait();
                 
                }
                     }
              else{
          //on affiche que rien a ete selectionner
              String message="Erreur !!!! Veuillez verifier vos champs SVP \n "
                      + " ";
                 
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText(message);
                    alert.showAndWait();
                 
          
                }
                   }
            }
              }
      public   ObservableList<FmAlimentsPathologie>   verificationPathologie(ObservableList<mainModel> main,FmPathologie pathologie)
   {
       ObservableList<FmAlimentsPathologie> listInterdit=FXCollections.observableArrayList();
       List<FmAlimentsPathologie> listeAlPa=formulyTools.listeAlimentPathologie(pathologie);
       for(FmAlimentsPathologie listP:listeAlPa)
       {
       int idA1=listP.getAliment().getId();
             for(int i=0;i<main.size();i++)
             {
           int idA2=main.get(i).getIdAliment();
            if(idA2==idA1)
            {
             listInterdit.add(listP);
            }
             }
       }
       return listInterdit;
   }
        public   ObservableList<mainModel>   verificationPathologie(ObservableList<mainModel> main,FmPathologie pathologie,String ...listeFictif)
   {
       ObservableList<mainModel> listInterdit=FXCollections.observableArrayList();
       List<FmAlimentsPathologie> listeAlPa=formulyTools.listeAlimentPathologie(pathologie);
           System.out.println("taille de la liste aliment pathologie : "+listeAlPa.size());
       for(FmAlimentsPathologie listP:listeAlPa)
       {
       int idA1=listP.getAliment().getId();
             for(int i=0;i<main.size();i++)
             {
           int idA2=main.get(i).getIdAliment();
            if(idA2==idA1)
            {
                if( !listInterdit.contains(main.get(i)))
                {
             listInterdit.add(main.get(i));
                }
            }
             }
       }
       return listInterdit;
   }
         public void TraiterInterdi(FmPathologie pathologie)
         {
   ObservableList<FmAlimentsPathologie>  list=verificationPathologie(tableSelection.getItems(),pathologie);
             System.out.println("taille: "+list.size());
             String info="";
        info = formulyTools.formatageInterdi(list);
    String lesElement="";
        if(list.size()>0)
         {      
              Image image = new Image(
    getClass().getResourceAsStream("/formuly/image/war.jpg")
     );
         labelAttention.setText("Attention aliment(s) deja Interdit "+pathologie.getLibelle());
         labelAttention.setGraphic(new ImageView(image));
         labelAttention.setStyle(" -fx-background-color:linear-gradient(to top right,white,red,red,white);");
         infoLabelHalt.setText(info );
         infoLabelHalt.setGraphic(new ImageView(image));  
        labelAttention.setVisible(true);
         }
       else
              {
         labelAttention.setText("");
         labelAttention.setStyle(" -fx-background-color:white");
         labelAttention.setVisible(false);
              }
         }
         public ObservableList<mainModel> ListDesAlimentsPourPathologieExistante(ObservableList<mainModel> listRepete,ObservableList<mainModel> listGlobale)
         {
             System.out.println("list reparete size : "+listRepete.size());
             System.out.println("listGlobale: "+listGlobale.size());
          ObservableList<mainModel>  list=FXCollections.observableArrayList();
          ArrayList<Integer> listI=new ArrayList<>();
            if(listRepete.size()>0)
            {
             if(listRepete.size()!=listGlobale.size())
          {
             for(int i=0;i<listGlobale.size();i++)
             {
                 System.out.println("id 1: "+listGlobale.get(i).getIdAliment());
                 boolean estInterdit=false;
                for(int j=0;j<listRepete.size();j++)
                { 
                if(listGlobale.get(i).getIdAliment()==listRepete.get(j).getIdAliment())
                 {   
                 estInterdit=true;
                 }
                }
                if(!estInterdit)
                {
                list.add(listGlobale.get(i));
                }
             }
          }
               System.out.println("list tri: "+list.size());

            }
            else{
            list=listGlobale;
            }
     
          return list;
         }
         /**
          * methode qui permet de caster les mainModel en objets FmAlimentsPathologie
          * @param liste la liste de mainModel
          * @param pato  la pathologie concernée
          * @return une liste d'instances de FmAlumentsPathologie
          */
         public List<FmAlimentsPathologie> listAlimentPathologie(ObservableList<mainModel> liste,FmPathologie pato)
         {
         List<FmAlimentsPathologie> list=new ArrayList<>();
         FmAlimentsPathologie alpa=null;
         FmAliments aliments=null;
           for(mainModel model:liste)
           {
            aliments =new FmAliments(model.getIdAliment());
            aliments.setNomFr(model.getNom_aliment());
            alpa =new FmAlimentsPathologie( dernierIdAlimentPathologie);
            alpa.setAliment(aliments);
            alpa.setPathologie(pato);
            alpa.setDate(new Timestamp(new Date().getTime()));
            list.add(alpa);
             dernierIdAlimentPathologie++;
           }
         return list;
         }
         public FmPathologie getNewInstancePathologie(String libelle ,String Description)
         {
           int idPathologie=formulyTools.TrouverDernierIdentifiant_Pathologie()+1;
           FmPathologie patho=new FmPathologie(idPathologie);
           patho.setLibelle(libelle);
           patho.setDescription(Description);
           patho.setDate(new Timestamp(new Date().getTime()));
           return patho;
         }
         public Task traitementTacheAvecDeuxPathologies(List<FmAlimentsPathologie> listNvo,List<FmAlimentsPathologie> listEncien) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
             EntityManager em=formulyTools.getEm().createEntityManager();
          try {
              
         em.getTransaction().begin(); 
         double debut=0;
         if(nouvellePathologie!=null)
         {
             updateMessage("traitement de la Pathologie :"+nouvellePathologie.getLibelle());
             Thread.sleep(50);
             em.persist(nouvellePathologie);
              updateProgress(10, 100);
              double partie=(listNvo.size()/50);
               debut=partie+10;
              if(listNvo.size()>0)
              {
             for(FmAlimentsPathologie alpa:listNvo)
             {
                 updateMessage("Insertion de :"+alpa.getAliment().getNomFr());
                 Thread.sleep(30);
                 em.persist(alpa);
                 updateProgress(debut, 100);
                 debut=debut+partie;
             }
              }
         }
              else{
               updateProgress(60, 100);
               debut=60;
              }
             if(listEncien.size()>0)
             {
              updateMessage("mise à jour des Info de :"+enciennePathologie.getLibelle());
                Thread.sleep(40);
               double  partie2=(listEncien.size()/35);
               double  debut2=partie2+10+debut;
            for(FmAlimentsPathologie alpaEncien:listEncien)
             {
               updateMessage("mise a jour de :"+alpaEncien.getAliment().getNomFr());
               Thread.sleep(30);
               em.persist(alpaEncien);
               updateProgress(debut2,100);
               debut2=partie2+debut2;
             }
             }
             else{
             updateProgress(95, 100);
             }
            em.getTransaction().commit();
              updateMessage("terminer");
               updateProgress(100, 100);
           } catch (Exception e) {
               updateMessage("erreur");
          }
         
         
          
      
        return true;
      }
    };
  }
          public void enregistrerAlimentDeuxPathologie()
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Enregistrement et mise a jour de pathologie");
               alert.show();
               Task copyWorker = traitementTacheAvecDeuxPathologies(listeAlimentPathologieNvo,listeAlimentPathologieEncien);
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
                initialisation();
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  //viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin insertion");
               alert.setContentText("Pathologies Mis a jour avec succes :");
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
          public void initialisation()
          {
         
           obsListTable1.clear();
           listRecherche.clear();
           listeAlimentPathologieEncien.clear();
           listeAlimentPathologieNvo.clear();
           tableSelection.getItems().clear();
           tableListe.getItems().clear();
           nouvellePathologie=null;
          enciennePathologie=null;
           obsListTable1=formulyTools.getobservableListMainModel(1);
          listRecherche=obsListTable1;
          initialiserTableauListeAliment(obsListTable1);
         
          listPathologie=modelFoodSelect.listePathologie();
           recherche.setText("");
          descriptionPathologie.setText("");
          nomPathologie.setText("");
          listePathologie.getItems().clear();
          listePathologie.setItems(FXCollections.observableArrayList(listPathologie));
          
          labelAttention.setVisible(false);
          tableAlimentNonEnregistre.getItems().clear();
          tableAlimentNonEnregistre.setVisible(false);
          }
}
