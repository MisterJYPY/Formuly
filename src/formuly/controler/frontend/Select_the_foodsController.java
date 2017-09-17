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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
     @FXML private TableView<mainModel> table_aliment_deja_choisi;
     @FXML private TableColumn<mainModel,String> nomAlimentsChoisi;
    @FXML private TableColumn<mainModel,String> nomAliment;
    @FXML private TableColumn<mainModel,String> quantite;
    @FXML private TableColumn<mainModel,String> quantiteChoisi;
    @FXML private TableColumn<mainModel,Integer> number;
    @FXML private ComboBox categorie_Foods;
    @FXML private ComboBox  pays_foods;
    @FXML private ComboBox  mode_cuisson;
    @FXML private TextField  nom_aliment;
    @FXML private TextField  code_aliment;
    @FXML private Button  envoi;
   
    private final modelFoodSelect model;

    public Select_the_foodsController() {
        model=new modelFoodSelect();
    }
    
    
      @Override
      public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rendreCelluleEditable(table_aliment_deja_choisi,quantiteChoisi);
       table_aliment_a_choisir.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       initialisationCombobox();
       initialiserLeTableauAchoisir();
       nom_aliment.setOnKeyReleased(
     event->{
      // if(!nom_aliment.getText().isEmpty())
      // {
          String sql="";
             String nom_ali=nom_aliment.getText();
           String sqlnomA= "select f.id,f.nom_fr ,f.code from fm_aliments f WHERE (f.nom_fr LIKE "+"'%"+nom_ali+"%' or f.nom_eng LIKE "+"'%"+nom_ali+"%' or f.surnom LIKE "+"'%"+nom_ali+"%')  "; 
        String sqlcate="";
        String sqlmodec="";
        String sqlpays="";
         String sqlcode="";
        String sql1="";
                    if(categorie_Foods.getValue()!=null)
                    {
                String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
                sqlcate="and f.groupe="+idGrouep+" ";   
                    }
                     if(!code_aliment.getText().isEmpty())
                    {      
                          String val=code_aliment.getText(); 
                sqlcode= "and f.code LIKE "+"'%"+val+"%' ";
                    }
                      if(pays_foods.getValue()!=null)
                    {
                   String pays=pays_foods.getValue().toString();
                sqlpays="and f.pays LIKE "+"'%"+pays+"%' ";   
                    }
                       if(mode_cuisson.getValue()!=null)
                    {
                   String mode_cuiss=mode_cuisson.getValue().toString();
                sqlmodec="and f.mode_cuisson="+"'"+mode_cuiss+"'";   
                    }
                   sql= sqlnomA.concat(sqlcate).concat(sqlcode).concat(sqlpays).concat(sqlmodec);
                   //System.out.println(sql1);
             
             if(!sql.isEmpty())
             {
                 //System.out.println(sql);
                initialiserLeTableauAchoisir(sql,"");   
             }
     //  }
     } );
       
       code_aliment.setOnKeyReleased(
        event->{
        //  if(!code_aliment.getText().isEmpty()){
            String sql="";
          String val=code_aliment.getText();
           String sqlcode= "select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.code LIKE "+"'%"+val+"%' "; 
        String sqlcate="";
        String sqlmodec="";
        String sqlpays="";
        String sqlnomA="";
        String sql1="";
                    if(categorie_Foods.getValue()!=null)
                    {
                String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
                sqlcate="and f.groupe="+idGrouep+" ";   
                    }
                     if(!nom_aliment.getText().isEmpty())
                    {
                        String nom_ali=nom_aliment.getText();
                sqlnomA="and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or f.nom_eng LIKE "+"'%"+nom_ali+"%' or f.surnom LIKE "+"'%"+nom_ali+"%') ";   
                    }
                      if(pays_foods.getValue()!=null)
                    {
                   String pays=pays_foods.getValue().toString();
                sqlpays="and f.pays LIKE "+"'%"+pays+"%' ";   
                    }
                       if(mode_cuisson.getValue()!=null)
                    {
                   String mode_cuiss=mode_cuisson.getValue().toString();
                sqlmodec="and f.mode_cuisson="+"'"+mode_cuiss+"'";   
                    }
                   sql= sqlcode.concat(sqlcate).concat(sqlnomA).concat(sqlpays).concat(sqlmodec);
                   System.out.println(sql1);
             
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
         // }
        });
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
         
    }
      public void initialiserLeTableauAchoisir()
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      number.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      
        table_aliment_a_choisir.setEditable(true);
      
       quantite.setCellFactory(TextFieldTableCell.forTableColumn());
       quantite.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<mainModel, String> t) {
                    
                    if(Double.parseDouble(t.getNewValue())>0.0)
                 {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
           // table_aliment_a_choisir.getSelectionModel().select( t.getTableView().getItems().get( t.getTablePosition().getRow()));         
                }   
                    else
                    {
                ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte("0");    
                    }
                    int i=0;
                     for (mainModel run :  table_aliment_a_choisir.getItems()) {
                         if(Double.parseDouble(run.getQte())>0.0)
                         {                           
                      table_aliment_a_choisir.getSelectionModel().select(i);  
                         }
                         i++;
                }
      }
            }
        );
     
        table_aliment_a_choisir.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
          if(row.getItem() instanceof mainModel)
          {
           int index=table_aliment_a_choisir.getSelectionModel().getSelectedIndex();  
         
            int i=0;
                     for (mainModel run :  table_aliment_a_choisir.getItems()) {
                         if(Double.parseDouble(run.getQte())>0.0)
                         {                           
                      table_aliment_a_choisir.getSelectionModel().select(i);  
                         }
                         i++;
                         //  table_aliment_a_choisir.getSelectionModel().clearSelection(index);
                         
                }
          }
        }
    }
});
         

       
        table_aliment_a_choisir.setItems(formulyTools.getobservableListMainModel(1));
      }
      public void rechercher(ActionEvent e)
    {
        Object obj=e.getSource();
        if(e.getSource().equals(categorie_Foods) && categorie_Foods.getValue()!=null)
        {   
             String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
          String sqlcate="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.groupe="+idGrouep+" ";
          String sql="";
         // String val=code_aliment.getText();
          String sqlcode= " ";     
          String sqlmodec="";
          String sqlpays="";
          String sqlnomA="";
          String sql1="";
                    if(!code_aliment.getText().isEmpty())
                    {
                  String code=code_aliment.getText();
         sqlcode= "and f.code LIKE "+"'%"+code+"%' "; 
                    }
                     if(!nom_aliment.getText().isEmpty())
                    {
                        String nom_ali=nom_aliment.getText();
                sqlnomA="and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or f.nom_eng LIKE "+"'%"+nom_ali+"%' or f.surnom LIKE "+"'%"+nom_ali+"%') ";   
                    }
                      if(pays_foods.getValue()!=null)
                    {
                   String pays=pays_foods.getValue().toString();
                sqlpays="and f.pays LIKE "+"'%"+pays+"%' ";   
                    }
                       if(mode_cuisson.getValue()!=null)
                    {
                   String mode_cuiss=mode_cuisson.getValue().toString();
                sqlmodec="and f.mode_cuisson="+"'"+mode_cuiss+"'";   
                    }
                   sql= sqlcate.concat(sqlcode).concat(sqlnomA).concat(sqlpays).concat(sqlmodec);
                   System.out.println(sql1);
             
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
        }
        
         if(e.getSource().equals(mode_cuisson) && mode_cuisson.getValue()!=null)
         {
           String mode_cuiss=mode_cuisson.getValue().toString();
          String   sqlmodec="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.mode_cuisson="+"'"+mode_cuiss+"' ";   
          String sqlcate="";
          String sql="";
         // String val=code_aliment.getText();
          String sqlcode= "";     
          String sqlpays="";
          String sqlnomA="";
          String sql1="";
                    if(!code_aliment.getText().isEmpty())
                    {
          String code=code_aliment.getText();
         sqlcode= "and f.code LIKE "+"'%"+code+"%' "; 
                    }
                     if(!nom_aliment.getText().isEmpty())
                    {
          String nom_ali=nom_aliment.getText();
                sqlnomA="and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or f.nom_eng LIKE "+"'%"+nom_ali+"%' or f.surnom LIKE "+"'%"+nom_ali+"%') ";   
                    }
                      if(pays_foods.getValue()!=null)
                    {
          String pays=pays_foods.getValue().toString();
                sqlpays="and f.pays LIKE "+"'%"+pays+"%' ";   
                    }
                       if(categorie_Foods.getValue()!=null)
                    {
          String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
          sqlcate="and f.groupe="+idGrouep+" "; 
                    }
                   sql=sqlmodec.concat(sqlcode).concat(sqlnomA).concat(sqlpays).concat(sqlcate);
                 
             
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
         }
           if(e.getSource().equals(pays_foods) && pays_foods.getValue()!=null)
         {
          String pays=pays_foods.getValue().toString();
          String sqlpays="select f.id,f.nom_fr ,f.code from fm_aliments f WHERE f.pays LIKE "+"'%"+pays+"%' ";         
          String   sqlmodec="";   
          String sqlcate="";
          String sql="";
          String sqlcode= "";     
          String sqlnomA="";
          String sql1="";
                    if(!code_aliment.getText().isEmpty())
                    {
                  String code=code_aliment.getText();
         sqlcode= "and f.code LIKE "+"'%"+code+"%' "; 
                    }
                     if(!nom_aliment.getText().isEmpty())
                    {
                 String nom_ali=nom_aliment.getText();
                sqlnomA="and (f.nom_fr LIKE "+"'%"+nom_ali+"%' or f.nom_eng LIKE "+"'%"+nom_ali+"%' or f.surnom LIKE "+"'%"+nom_ali+"%') ";   
                    }
                      if(mode_cuisson.getValue()!=null)
                    {
                 String mode_cuiss=mode_cuisson.getValue().toString();
              sqlmodec="and f.mode_cuisson="+"'"+mode_cuiss+"' ";  
                    }
                       if(categorie_Foods.getValue()!=null)
                    {
                 String groupe=categorie_Foods.getValue().toString();
             FmGroupeAlimentJpaController gp=new FmGroupeAlimentJpaController(model.emf);
             FmGroupeAliment grpe=gp.findFmGroupeAlimentByName(groupe);
             int idGrouep=grpe.getId();
          sqlcate="and f.groupe="+idGrouep+" "; 
                    }
                   sql=sqlpays.concat(sqlcode).concat(sqlnomA).concat(sqlmodec).concat(sqlcate);
             if(!sql.isEmpty())
             {
               initialiserLeTableauAchoisir(sql,"");   
             }
        }
    }
      public void initialiserLeTableauAchoisir(String NameQuery,String champ ,Object parametre)
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      number.setCellValueFactory(new PropertyValueFactory<>("numero")); 
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
        table_aliment_a_choisir.setItems(retournerObservableListNonDoublon(formulyTools.getobservableListMainModel(NameQuery, champ, parametre),table_aliment_deja_choisi.getItems(),""));
      }
      public void initialiserLeTableauAchoisir(String NameQuery,String champ)
      {
          
      nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
      quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
      number.setCellValueFactory(new PropertyValueFactory<>("numero")); 
      
       table_aliment_a_choisir.setEditable(true);
       quantite.setCellFactory(TextFieldTableCell.forTableColumn());
       quantite.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                     ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
        table_aliment_a_choisir.setItems(retournerObservableListNonDoublon(formulyTools.getobservableListMainModel(NameQuery,model,""),table_aliment_deja_choisi.getItems(),""));
      }

      public void envoi(ActionEvent event)
      {
             ObservableList<mainModel> obsL=FXCollections.observableArrayList();
            if(table_aliment_deja_choisi.getItems().size()==0)
            {
                     for (mainModel run :  table_aliment_a_choisir.getItems()) {
                         if(Double.parseDouble(run.getQte())>0.0)
                         {                           
                       obsL.add(run);
                         }
                     }
        nomAlimentsChoisi.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        quantiteChoisi.setCellValueFactory(new PropertyValueFactory<>("qte")); 
        nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
          rendreCelluleEditable(table_aliment_deja_choisi,quantiteChoisi);
          initialisationCombobox();
          initialiserJtextField();
           table_aliment_deja_choisi.setItems(obsL);
           table_aliment_a_choisir.setItems(retournerObservableListNonDoublon(table_aliment_a_choisir.getItems(),table_aliment_deja_choisi.getItems()));
            supprimerElementDeLaListeen2click(table_aliment_deja_choisi);
         }
            else{
         //  table_aliment_a_choisir 
                obsL.clear();
                 for (mainModel run :  table_aliment_a_choisir.getItems()) {
                         if(Double.parseDouble(run.getQte())>0.0)
                         {                           
                       obsL.add(run);
                         }
                     }
                 nomAlimentsChoisi.setCellValueFactory(
            new PropertyValueFactory<mainModel,String>("nom_aliment")
        );
                 quantiteChoisi.setCellValueFactory(
            new PropertyValueFactory<mainModel,String>("qte")
        );
            nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        quantite.setCellValueFactory(new PropertyValueFactory<>("qte")); 
        table_aliment_a_choisir.setEditable(true);
       rendreCelluleEditable(table_aliment_deja_choisi,quantiteChoisi);
        table_aliment_deja_choisi.getItems().addAll(retournerObservableListNonDoublon(obsL,table_aliment_deja_choisi.getItems(),""));    
       table_aliment_a_choisir.setItems(retournerObservableListNonDoublon(table_aliment_a_choisir.getItems(),table_aliment_deja_choisi.getItems()));
          supprimerElementDeLaListeen2click(table_aliment_deja_choisi);
            }
      }
      public ObservableList<mainModel>  retournerObservableListNonDoublon(ObservableList<mainModel> ob1,ObservableList<mainModel> ob2)
      {
          if(ob2.size()>0)
          {
             ob1.removeAll(ob2);
          }
            return ob1;
      }
       public ObservableList<mainModel>  retournerObservableListNonDoublon(ObservableList<mainModel> ob1,ObservableList<mainModel> ob2,String s)
      {
         ObservableList<mainModel> md=FXCollections.observableArrayList();
         
          if(ob2.size()>0)
          {
              int i=0;
               for(int k=0;k<ob1.size();k++)   
           {      
               mainModel md1=ob1.get(k);
               for(int l=0;l<ob2.size();l++)
           {
               mainModel md2=ob2.get(l);
             if(md1.getNom_aliment().equals(md2.getNom_aliment()) && md1.getMg().equals(md2.getMg()) && md1.getFer().equals(md2.getFer()))
             { 
              ob1.remove(md1);
             }
           } 
           }
             md=null;
          }
            return ob1;
      }
       public void supprimerElementDeLaListeen2click(TableView<mainModel> table_aliment_deja_choisi)
       {
            table_aliment_deja_choisi.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
   public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
          if(row.getItem() instanceof mainModel)
          {
           int index=table_aliment_deja_choisi.getSelectionModel().getSelectedIndex(); 
           String selection = table_aliment_deja_choisi.getSelectionModel().getSelectedItem().getNom_aliment();
           String quantite= table_aliment_deja_choisi.getSelectionModel().getSelectedItem().getQte();
               Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Dialogue de confirmation");
            alert.setHeaderText("Informations supplementaires relatif à la suppression");
            alert.setContentText("Nom aliment: "+selection+" Quantite :"+quantite+"\n"
                    + "VOULEZ VOUS VRAIMENT LE SUPPRIMER ?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
         table_aliment_deja_choisi.getItems().remove(index);
      }
          }
        }
    }
});
       }
      public void initialiserJtextField()
      {
           int i=0;
        for (mainModel run :  table_aliment_a_choisir.getSelectionModel().getSelectedItems()) {
                     // System.out.println(run);
                    
                      table_aliment_a_choisir.getSelectionModel().select(run);
                      i++;
               }
          code_aliment.setText("");
          nom_aliment.setText("");
          //initialiserLeTableauAchoisir();
      
      }
      public void rendreCelluleEditable(TableView<mainModel> table ,TableColumn<mainModel,String> colonne )
      {
      table.setEditable(true);
        colonne.setCellFactory(TextFieldTableCell.forTableColumn());
             colonne.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<mainModel, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<mainModel, String> t) {
                    ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                     ((mainModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setQte(t.getNewValue());
                }
            }
        );
      }
}
