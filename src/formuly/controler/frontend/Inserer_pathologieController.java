/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.classe.repasModel;
import formuly.entities.FmPathologie;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.modelFoodSelect;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    private TableColumn<mainModel, String> table1_pays;

    @FXML
    private TextField nomPathologie;

    @FXML
    private TableView<mainModel> tableListe;

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
    private ObservableList<mainModel> obsListTable1 ;
    private ObservableList<mainModel> listRecherche;
    private List<FmPathologie> listPathologie;

    public Inserer_pathologieController() {
    obsListTable1=formulyTools.getobservableListMainModel(1);
    listRecherche=obsListTable1;
    listPathologie=modelFoodSelect.listePathologie();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialiserTableauListeAliment(obsListTable1);
        recherche.setOnKeyReleased(event->{
          String contenu=recherche.getText();
             if(!contenu.isEmpty())
             {
             initialiserTableauListeAliment(retourneListParCritere(contenu,listRecherche));
             }
        });
        ActionSurPathologie(listePathologie,listPathologie);
        valider.setOnAction(event->{
        traiterEnregistrement();
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
      placerBouton(table1_action,1);
      tableListe.setItems(liste);
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
     getClass().getResourceAsStream("/formuly/image/corbeille.jpg"));
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
       tableListe.getItems().remove(model);
      
     }
       public void supprimerAliment(mainModel aliment)
                   {
            tableSelection.getItems().remove(aliment);     
            tableListe.getItems().add(aliment);
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
                  System.out.println(" insertion");
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
                  System.out.println(" insertion");
                }
                }
               else{
          if(pat!=null && !pat.getLibelle().equals("aucun choix"))
                     {
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
                  System.out.println(" insertion");
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
       
}
