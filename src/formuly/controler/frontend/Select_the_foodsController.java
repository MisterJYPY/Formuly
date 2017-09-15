/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.entities.FmGroupeAliment;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.modelFoodSelect;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Select_the_foodsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<mainModel> table_aliment_a_choisir;
    @FXML private TableColumn<mainModel,String> nomAliment;
    @FXML private TableColumn<mainModel,String> quantite;
    @FXML  private ComboBox categorie_Foods;
    @FXML  private ComboBox  pays_foods;
    @FXML  private ComboBox  mode_cuisson;
    @FXML private TextField  nom_aliment;
    @FXML private TextField  code_aliment;
   
    
    private modelFoodSelect model;

    public Select_the_foodsController() {
        model=new modelFoodSelect();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       initialisationCombobox();
       initialiserLeTableauAchoisir();
    }    
    public void initialisationCombobox()
    {
       //initialisation des pays
     List<String> list = new ArrayList<String>();
        list.add("Cote Ivoire");
        list.add("Mali");
        list.add("Nigeria");
        list.add("Ghana");
        list.add("Gambie");
        list.add("Nigeria");
        list.add("Niger");
        list.add("Senegal");
        list.add("Guinee");
        list.add("Benin");
        ObservableList obList = FXCollections.observableList(list);
        pays_foods.getItems().clear();
        pays_foods.setItems(obList);
        
        //initialisation du mode de cuisson
        ObservableList<String> groupe=model.listeDesGroupesAliments();
        ObservableList<String> ModeCuisson=model.listeDesMode_cuisson();
        
        categorie_Foods.getItems().clear();
        mode_cuisson.getItems().clear();
        categorie_Foods.setItems(groupe);
        mode_cuisson.setItems(ModeCuisson);
         
    }
    public void initialiserLeTableauAchoisir()
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      
       quantite.setCellFactory(TextFieldTableCell.forTableColumn());
       quantite.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
        table_aliment_a_choisir.setItems(formulyTools.getobservableListMainModel());
      }
    public void rechercher(ActionEvent e)
    {
        Object obj=e.getSource();
        if(e.getSource().equals(categorie_Foods))
        {
            String mc;
             if(mode_cuisson.getValue()!=null)
             {
         mc=mode_cuisson.getValue().toString();
            System.out.println("mc: "+mc);
             }
        }
         if(e.getSource().equals(mode_cuisson))
        {
           
        }
           if(e.getSource().equals(pays_foods))
        {
            
        }
    }
}
