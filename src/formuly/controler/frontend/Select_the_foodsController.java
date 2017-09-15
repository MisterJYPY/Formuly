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
import java.awt.event.KeyEvent;
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
import jdk.nashorn.internal.objects.NativeRegExp;

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
//        String val="cru";
//       // String sql="select f.id,f.nom from fm_aliments f ";
//        String sql="select f.id,f.nom_fr from fm_aliments f WHERE f.mode_cuisson="+"'"+val+"'";
//        
//         model.getAllAlimentByFoods(sql);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       initialisationCombobox();
       initialiserLeTableauAchoisir();
       nom_aliment.setOnKeyReleased(
     event->{
         String sql="";
           String val="";
             String nom_ali=nom_aliment.getText();
             String pays=pays_foods.getValue().toString();
             String mode_cuiss=mode_cuisson.getValue().toString();
             String groupe=categorie_Foods.getValue().toString();
            String code=code_aliment.getText();
             //val=nom_aliment.getText();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);                  
             int idGrouep=grpe.getId();
            if(categorie_Foods.getValue()==null && pays_foods.getValue()==null && mode_cuisson.getValue()==null && !nom_aliment.getText().isEmpty() && code_aliment.getText().isEmpty())
            {
     sql="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE (f.nom_fr LIKE "+"'%"+nom_ali+"%') OR (f.nom_eng LIKE "+"'%"+nom_ali+"%')";
       // initialiserLeTableauAchoisir(sql,"");
        }
          else{
               if(categorie_Foods.getValue()!=null && pays_foods.getValue()!=null && mode_cuisson.getValue()!=null && !nom_aliment.getText().isEmpty() && !code_aliment.getText().isEmpty()){
      sql="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.code LIKE "+"'%"+ code+"%' and f.pays LIKE "+"'%"+pays+"%' and f.mode_cuisson="+"'"+mode_cuiss+"'"
              + " and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or nom_eng LIKE "+"'%"+nom_ali+"%') and f.groupe="+idGrouep+"";        
                   System.out.println(sql);
               }
              }
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
        }
        );
       
       code_aliment.setOnKeyReleased(
        event->{
            String sql="";
             String val=code_aliment.getText();
              if(categorie_Foods.getValue()==null && pays_foods.getValue()==null && mode_cuisson.getValue()==null && nom_aliment.getText().isEmpty() && !code_aliment.getText().isEmpty())
            {    
     sql="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.code LIKE "+"'%"+val+"%'";
   
        }
          else{
             String nom_ali=nom_aliment.getText();
             String pays=pays_foods.getValue().toString();
             String mode_cuiss=mode_cuisson.getValue().toString();
              String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
               if(categorie_Foods.getValue()!=null && pays_foods.getValue()!=null && mode_cuisson.getValue()!=null && !nom_aliment.getText().isEmpty() && !code_aliment.getText().isEmpty()){
      sql="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.code LIKE "+"'%"+val+"%' and f.pays LIKE "+"'%"+pays+"%' and f.mode_cuisson="+"'"+mode_cuiss+"'"
              + " and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or nom_eng LIKE "+"'%"+nom_ali+"%') and f.groupe="+idGrouep+"";        
              }
              }
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
        }
        );
    }    
    public void initialisationCombobox()
    {
       //initialisation des pays
       
     List<String> list = new ArrayList<String>();
         list.add("nd");
        list.add("Cote Ivoire");
        list.add("Mali");
        list.add("Nigeria");
        list.add("Ghana");
        list.add("Gambie");
        list.add("Nigeria");
        list.add("Niger");
        list.add("Senegal");
        list.add("Guinee");
        list.add("Ghana");
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
        
       code_aliment.setOnKeyReleased(
        event->{
            System.out.println("prieregdgdggd: "+code_aliment.getText());
        }
        );
         
    }
    public void initialiserLeTableauAchoisir()
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      
        table_aliment_a_choisir.setEditable(true);
      
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
        FmGroupeAliment groupe=model.avoirGroupeAliment(categorie_Foods.getValue().toString());
            initialiserLeTableauAchoisir("FmAliments.findAllByGroupe","groupe",groupe);
        }
        
         if(e.getSource().equals(mode_cuisson))
         {
           
             String val=mode_cuisson.getValue().toString();
       String sql="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.mode_cuisson="+"'"+val+"'";
        initialiserLeTableauAchoisir(sql,"");
         }
           if(e.getSource().equals(pays_foods))
         {
             initialiserLeTableauAchoisir("FmAliments.findByPays","pays",pays_foods.getValue());
        }
    }
      public void initialiserLeTableauAchoisir(String NameQuery,String champ ,Object parametre)
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      
       table_aliment_a_choisir.setEditable(true);
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
        table_aliment_a_choisir.setItems(formulyTools.getobservableListMainModel(NameQuery, champ, parametre));
      }
        public void initialiserLeTableauAchoisir(String NameQuery,String champ)
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      
       table_aliment_a_choisir.setEditable(true);
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
        table_aliment_a_choisir.setItems(formulyTools.getobservableListMainModel(NameQuery,model));
      }

       public void invoqueKeyReleased(String valueTape)
       {
           System.out.println("action entendu");
       }
       
}
