/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.formulyTools;
import formuly.expert.Simplexe;
import formuly.model.frontend.mainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Prog_lineaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableColumn<mainModel,Double> table1_numero;

    @FXML
    private TableColumn<mainModel, String> table1_categorie;

    @FXML
    private TableColumn<mainModel, String> table1_modeCuisson;

    @FXML
    private TableColumn<mainModel, Double> table1_glucide;

    @FXML
    private Button saisiePixUnitaire;

    @FXML
    private TextField protideMax;

    @FXML
    private TextField LipideMax;

    @FXML
    private TextField recherche;

    @FXML
    private TableColumn<mainModel, String> table1_pays;
    
    @FXML
    private TableColumn<mainModel, String> choix;

    @FXML
    private Button retirerAliment;

    @FXML
    private TableView<mainModel> table1;

    @FXML
    private TableColumn<mainModel, Double> table1_lipide;

    @FXML
    private TableColumn<mainModel, String> table1_nomFr;

    @FXML
    private TextField glucideMax;

    @FXML
    private Button lancerCalcul;

     @FXML
    private TableView<mainModel> tableChoix;

    @FXML
    private TableColumn<mainModel, Double> table1_protide;
    
    @FXML
    private TableColumn<mainModel, Double> table2_protide;
    
     @FXML
    private TableColumn<mainModel, Double> table2_lipide;
     
      @FXML
    private TableColumn<mainModel, String> table2_prixUn;
    
     @FXML
    private TableColumn<mainModel, String> table2_supp;
     
     @FXML
    private TableColumn<mainModel, Double> table2_glucide;
     
     @FXML
    private TableColumn<mainModel, Double> table2_numero;
     
       @FXML
    private TableColumn<mainModel, String> table2_nomAliment;

    
       ObservableList<mainModel> listeRecherche;
       ObservableList<mainModel> listeAliments;
        private ObservableList<mainModel> obsListTable1 ;
        private final double [][] matrix;
        public static int Nombre_MAX_ALIMENT=50;
        private Simplexe simplexe;
    public Prog_lineaireController() {
        listeAliments=formulyTools.getobservableListMainModel();
         listeRecherche=listeAliments;
           matrix=new double[4][Nombre_MAX_ALIMENT];    
    }
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           initialiserTab1(listeAliments);
           recherche.setOnKeyReleased(event->{
           listeRecherche=formulyTools.retourneListParCritere(recherche.getText(),listeAliments);
           initialiserTab1(listeRecherche);  
          });
         //rendons la cellule prix unitaire editable
                 formulyTools.rendreCelluleEditable(tableChoix,table2_prixUn);
                  lancerCalcul.setOnAction(event->{
                  chargerMatrix();
                  });
                  
        
    }    
      public void initialiserTab1(ObservableList<mainModel> model)
    {
        table1_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table1_nomFr.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table1_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table1_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table1_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
        table1_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        table1_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
        table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
     //  placerBouton();
       // placerBouton(table1_del,2);
      placerBouton(choix,1);
         System.out.println("nbre aliment : "+listeAliments.size());
        table1.setItems(model);
      
    }
        public void deplacerAliment(mainModel model)
     {
      table2_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        table2_nomAliment.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
        table2_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
        table2_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
        table2_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
         table2_prixUn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
      //  table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
      tableChoix.getItems().add(model);
      placerBouton(table2_supp,2);
      listeAliments.remove(model);
      table1.getItems().remove(model);
       if( table1.getItems().size()==0)
       {
          recherche.setText("");
         table1.setItems(obsListTable1);
       }
       
     }
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
       public void supprimerAliment(mainModel aliment)
                   {
            tableChoix.getItems().remove(aliment); 
            listeAliments.add(aliment);
             //traitementCasDejaPresent();
           // tableListe.getItems().add(aliment);
                    }
     public void chargerMatrix()
     {
     ObservableList<mainModel> alimentsChoisi=tableChoix.getItems();
      int nombreAliment=tableChoix.getItems().size();
        int i=0;
        double glucidMax=Double.parseDouble(glucideMax.getText());
        double lipideMax=Double.parseDouble(LipideMax.getText());
        double protidMax=Double.parseDouble(protideMax.getText());
         System.out.println("glucide totale : "+glucidMax);
         System.out.println("lipide  totale : "+lipideMax);
         System.out.println("protide totale : "+protidMax);
       for(mainModel element:alimentsChoisi)
       {
       //chargement des glucide
        matrix[0][i]=element.getCloumPcGlucide();
        //chargement des Lipide
        matrix[1][i]=element.getCloumPclipide();
        //chargement des Protides
        matrix[2][i]=element.getCloumPcprotide();
                i++;
       }
       //enregistrement des membres de droites 
        matrix[0][nombreAliment]=glucidMax;
        matrix[1][nombreAliment]=lipideMax;
        matrix[2][nombreAliment]=protidMax;
        
//        for(int cpt=0;cpt<=nombreAliment;i++)
//      {
//       double val=matrix[0][cpt];
//       matrix[i][cpt]=-val;
//      }
      //enregistrement des coefficient de la fonction obdjective
        i=0;
       for(mainModel element:alimentsChoisi)
       {
       //chargement des prix unitaires
        matrix[3][i]=Double.parseDouble(element.getPrixUnitaire());
                i++;
       } 
        matrix[3][i]=0.0;
       for(int j=0;j<=3;j++)
       {
           System.out.println("");
         for(int k=0;k<=nombreAliment;k++)
         {
          System.out.print(matrix[j][k]+"  ");
         }
           System.out.println("");
       }
       // System.out.println("taile tablà "+matrix.length);
       simplexe=new Simplexe(4, nombreAliment+1);
     //  double[][] matrixDual=simplexe.retournerDual(matrix);
       
       simplexe.setMatrixNouveau(matrix);
       double[][] matrixSuivant=new double[nombreAliment+1][4];
      // simplexe.setMatrixSuivant(matrixSuivant);
       //les valeurs en base et non en base
//       String [] enbase=new String[nombreAliment+1];
//       String [] Nonenbase=new String[4];
//       simplexe.setEnBase(enbase);
//       simplexe.setNonEnBase(Nonenbase);
       Simplexe.cas="MAX";
    //   Simplexe.appelExterieur=true;
       simplexe.algorithmeSimplexe();
//         System.out.println("*********afficahge du dual**************");
//        for(int j=0;j<nombreAliment+1;j++)
//       {
//           System.out.println("");
//         for(int k=0;k<=3;k++)
//         {
//          System.out.print(matrixDual[j][k]+"  ");
//         }
//           System.out.println("");
//       } 
      
    }
}
