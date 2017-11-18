/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.Excel.Book;
import formuly.Excel.ExcelTools;
import formuly.classe.TooltipTableRow;
import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmAlimentsPathologie;
import formuly.entities.FmGroupeAliment;
import formuly.entities.FmPathologie;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.mainModel;
import formuly.model.frontend.modelFoodSelect;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Label;
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

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public final class Inserer_aliment_fichierController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TableColumn<mainModel, Double> table1_energie;

    @FXML
    private TableColumn<mainModel, Double> table2_energie;

    @FXML
    private TableColumn<mainModel, Double> table2_na;

    @FXML
    private TableColumn<mainModel, String> table1_categorie;

    @FXML
    private TableColumn<mainModel, Double> table1_vite;

    @FXML
    private TableColumn<mainModel, String> table2_nomFr;

    @FXML
    private TableView<mainModel> table2;

    @FXML
    private TableView<mainModel> table1;

    @FXML
    private TableColumn<mainModel, Double> table1_lipide;

    @FXML
    private TableColumn<mainModel, Double> table2_protide;

    @FXML
    private Label tailleFichier;

    @FXML
    private Label derniereModif;

    @FXML
    private TableColumn<mainModel, Double> table2_lipide;

    @FXML
    private TableColumn<mainModel, Double> table1_ka;

    @FXML
    private TableColumn<mainModel, String> table2_categorie;

    @FXML
    private Label nomFichier;

    @FXML
    private TableColumn<mainModel, Double> table1_protide;

    @FXML
    private TableColumn<mainModel, Double> table1_mg;

    @FXML
    private TableColumn<mainModel, Double> table2_vite;

    @FXML
    private TableColumn<mainModel, Double> table1_numero;

    @FXML
    private TableColumn<mainModel, Double> table2_vitc;

    @FXML
    private TableColumn<mainModel, Double> table2_vita;

    @FXML
    private TableColumn<mainModel, Double> table1_na;

    @FXML
    private TableColumn<mainModel, String> table1_modeCuisson;

    @FXML
    private TableColumn<mainModel, Double> table2_fer;

    @FXML
    private TableColumn<mainModel, Double> table2_glucide;

    @FXML
    private TableColumn<mainModel, String> table2_modeCuisson;

    @FXML
    private TableColumn<mainModel, Double> table1_glucide;

    @FXML
    private TableColumn<mainModel, Double> table2_numero;

    @FXML
    private Button boutonSelection;

    @FXML
    private TableColumn<mainModel, String> table1_pays;

    @FXML
    private TableColumn<mainModel, String> table1_nomFr;

    @FXML
    private TableColumn<mainModel, Double> table1_fer;

    @FXML
    private TableColumn<mainModel, Double> table1_vitc;

    @FXML
    private TableColumn<mainModel, String> table1_action;

    @FXML
    private TableColumn<mainModel, Double> table2_ka;

    @FXML
    private TableColumn<mainModel, String> table2_pays;
  
    @FXML
    private TableColumn<mainModel, Double> table2_mg;
   
    @FXML 
    private Button lancer;
    
    @FXML 
    private Button quitter;
    
    ObservableList<mainModel> listeSaisie;
    ObservableList<mainModel> listeEnregistrer;

     private static Desktop desktop ;
      final FileChooser fileChooser ;

        List<FmGroupeAliment> listeCategorie;
    List<FmPathologie> listePathologie;
    List<String> listPays;
    ObservableList<String> ListmodeCuisson;
   private boolean EstfichierExcel;
    public Inserer_aliment_fichierController() {
         desktop = Desktop.getDesktop();
         fileChooser = new FileChooser();
        listeCategorie=modelFoodSelect.listeCategories(); 
        listePathologie=modelFoodSelect.listePathologie();
        ListmodeCuisson=modelFoodSelect.listeDesMode_cuissonss();
        listPays=initialiserPays();
        listeEnregistrer=formulyTools.TouteLaListeDesAliments();
        //EstfichierExcel=false;
      //  System.out.println("groupe: "+recupererGroupeParNomFichier(listeCategorie,"céréale"));
    }
    public void initialiserTab1(ObservableList<mainModel> model)
    {
       table1_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
       table1_nomFr.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
       table1_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
       table1_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
       table1_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
       table1_energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       table1_fer.setCellValueFactory(new PropertyValueFactory<>("fer"));
       table1_mg.setCellValueFactory(new PropertyValueFactory<>("mg"));
       table1_na.setCellValueFactory(new PropertyValueFactory<>("na"));
       table1_ka.setCellValueFactory(new PropertyValueFactory<>("ka"));
       table1_vitc.setCellValueFactory(new PropertyValueFactory<>("vitc"));
       table1_vite.setCellValueFactory(new PropertyValueFactory<>("vite"));
        table1_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
       table1_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
        table1_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
       placerBouton();
       table1.setItems(model);
      
    }
    public void placerBouton()
    {
     table1_action.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
  Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>> cellFactory = new Callback<TableColumn<mainModel, String>, TableCell<mainModel, String>>() {      
                    @Override
            public TableCell call(final TableColumn<mainModel, String> param) {
            final TableCell<mainModel, String> cell = new TableCell<mainModel, String>() {

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
                       mainModel model= getTableView().getItems().get(getIndex());
                       int id=getIndex();
                        retirerAliment(model,id);
                            });
                            setGraphic(btn);
                            
                            setText(null);
                        }
                    }
                };
                return cell;
                
            }
        };
         table1_action.setCellFactory(cellFactory);

    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        boutonSelection.setOnAction(event->{
            takeFile(boutonSelection);
        });
  
        mettreLesToolTip(table1,table2);
        
        quitter.setOnAction(event->{
          fermerFenetre();
        });
        lancer.setOnAction(event->{
              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("alert confirmation");
              alert.setContentText("Confirmer l'enregistrement SVP ");
            if(table1.getItems().size()>0)
            {
               Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/question.png")
      );
           alert.setGraphic(new ImageView(image));
              alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
              alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
       enregistrerAliment();
        }
            }
          else{
        alert.setTitle("avertissement");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg")
      );
        alert.setGraphic(new ImageView(image));
   alert.setContentText("Vous N'avez aucun aliment valide \n"
           + "Succeptible d'etre enregistrer \n"
           + " Veuiller choisir un fichier valide SVP");     
              alert.show();
            }
        });
        chargerDonne(listeEnregistrer,"");
    }    
    
    
     public void retirerAliment(mainModel model,int id) {
               table1.getItems().remove(model);
//               int i=id;
//               int cpt=0;
//               for(mainModel md:table1.getItems())
//               {
//              if(i>0) 
//              {
//                md.setNumero(md.getNumero()-1);
//              }
//               }
     }
     
    public ArrayList<String> RecupererElementFichier(File files)
    {
    ArrayList<String> list=new ArrayList();
     try{
       String ligne;
    //chemins=this.takeTheFile();
BufferedReader buffer=new BufferedReader(new FileReader(files));
 LineNumberReader numeroLigne = new LineNumberReader(buffer);
                 int i =0;
     while((ligne=buffer.readLine())!=null ){     
         //System.out.println(ligne);
             list.add(ligne);
             numeroLigne.getLineNumber();
            //}
        }
     buffer.close();
        }catch(IOException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
          }
   
     return list;
    }
    /**
     * methode permettant d'extraire les informations recuperer du fichier
     * en fonction du caractere # de fin
     * @param list la liste des info du fichier
     * @return une Liste de chaine de caractere 
     */
    public ArrayList<String> extraireInformationDsLeLot(ArrayList<String> list)
    {
          ArrayList<String> listExtr=new ArrayList<>();
          String premierElement="";
          String deuxiemeElement;
          for(String ligne:list)
          { 
           if (ligne.isEmpty()) continue ;
            if(!ligne.contains("#"))
            {
           premierElement= premierElement.concat(ligne);
           premierElement=(!premierElement.equals(""))?premierElement.concat("/"):"";
            }
            else{
             deuxiemeElement=ligne;
       String concat = premierElement.concat(deuxiemeElement);        
               listExtr.add(concat);
                   System.out.println(concat);
                   premierElement="";
                   deuxiemeElement="";
              }
         
          }
          return listExtr;
    }
    /**
     * methode permettant d'initialiser les labels
     * qui donnent des Infos sur le Fichier notement: 
     * le nom du fichier ,sa derniere modification,son chemin abolue
     * les paraemtre doivent etre entrer de la sorte: 
     * Label nom fichier
     * Label derniere modif
     * Label absolute path
     * @param file
     * @param label 
     */
    public void initialiserInfoFichier(File file,Label...label)
    {
      if(label != null)
      {
          label[0].setText(file.getName());
          label[1].setText(new Timestamp(file.lastModified()).toString());
          label[2].setText(file.getAbsolutePath());
      } 
    }
    public List<String> recupererlaListeEnChaine(List<FmGroupeAliment> liste)
    {
       ArrayList<String> lis=new ArrayList<>();
         for(FmGroupeAliment ligne:liste)
         {
           lis.add(ligne.getNomFr());
         }
         return lis;
    }
    /**
     * methoed qui permet de recuperer l'instance de la categorie 
     * d'un aliment .elle est recupeéré en mettant une chaine identique 
     * a une categorie
     * @param listeOb
     * @param chaine
     * @return 
     */
   public FmGroupeAliment recupererGroupeParNomFichier(List<FmGroupeAliment> listeOb,String chaine)
   {
      String n="";
      boolean estPresent;
      FmGroupeAliment groupe=null;
      List<String> liste=recupererlaListeEnChaine(listeOb);
      String ligne;
   
     String nvelleChaine=chaine.replace("e", "é");
      for(FmGroupeAliment grpe:listeOb)
      {
         Pattern p = Pattern.compile(chaine.concat("|"+nvelleChaine), Pattern.CASE_INSENSITIVE);
// création d'un moteur de recherche
                ligne=grpe.getNomFr();
          Matcher m = p.matcher(ligne);

        if(m.find() && groupe==null)
        {
        groupe= grpe;
       
      }
        }
      return groupe;
   }
    public String recupererModecuissonParNomFichier(ObservableList<String> listeOb,String chaine)
   {
      String n="";
      boolean estPresent;
      FmGroupeAliment groupe=null;
     String modeCuisson="";
      for(String ligne:listeOb)
      {
         Pattern p = Pattern.compile(chaine, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(ligne);

        if(m.find() && !ligne.isEmpty())
        {
       modeCuisson=ligne;  
      }
        }
      return modeCuisson;
   }
    public FmPathologie recupererPathologieParNomFichier(List<FmPathologie> listeOb,String chaine)
   {
      String n="";
      boolean estPresent;
      FmPathologie groupe=null;
      String ligne;

      for(FmPathologie grpe:listeOb)
      {
         Pattern p = Pattern.compile(chaine, Pattern.CASE_INSENSITIVE);
// création d'un moteur de recherche
                ligne=grpe.getLibelle();
          Matcher m = p.matcher(ligne);

        if(m.find() && groupe==null)
        {
        groupe= grpe;
       
      }
        }
      return groupe;
   }
   public List<String> initialiserPays()
    {
        
     List<String> listPays = new ArrayList<String>();
         listPays.add("General");
        listPays.add("Cote Ivoire");
          listPays.add("Burkina Faso");
        listPays.add("Mali");
        listPays.add("Nigeria");
        listPays.add("Ghana");
        listPays.add("Gambie");
        listPays.add("Nigeria");
        listPays.add("Niger");
        listPays.add("Senegal");
        listPays.add("Guinee");
        listPays.add("Ghana");
        listPays.add("Benin");
       return listPays;
    }
   public String donnerPays(List<String> listPays,String pays)
   {
     String payss="";
    
      for(String ligne:listPays)
      {
      Pattern p = Pattern.compile(pays, Pattern.CASE_INSENSITIVE);
// création d'un moteur de recherche
          Matcher m = p.matcher(ligne);
       if(m.find() && !ligne.isEmpty())
       {
         payss=ligne;
       }
      
     }
     return payss;
   }
   public String returnConcatenantionValeur(mainModel model)
   {
   String info="*******Infos Sup:********\n";
     info=info.concat(!(model.getNomEng().isEmpty())?"Nom Eng: "+model.getNomEng()+"\n":"Nom Eng: aucun \n").concat(!(model.getPathologie().isEmpty())?"Pathologie: "+model.getPathologie()+"\n":"Pathologie: aucun\n");
     info=info.concat("surnom :"+model.getSurnom()+"\n");
     info=info.concat("Ash : "+model.getAsh()+"\n").concat("Ca :"+model.getCa()+"\n").concat("Cu :"+model.getCu()+"\n");
     info=info.concat("Aau(H2O) :"+model.getEau()+"\n").concat("Fibre :"+model.getFibre()+"\n").concat("Phos :"+model.getPhos()+"\n");
     info=info.concat("Riboflavin :"+model.getRiboflavin()+"\n").concat("Thiamin :"+model.getThiamin()+"\n").concat("vit A:"+model.getVita()+"\n");
     info=info.concat("vit b1 :"+model.getVitb1()+" \n").concat("vit b2 :"+model.getVitb2()+"\n").concat("vit b6 :"+model.getVitb6()+"\n");
     info=info.concat("folates :"+model.getVitb9()+"\n").concat("vit D :"+model.getVitd()+"\n").concat("Zn :"+model.getZn()+"\n");
     info=info.concat("*******Infos Sup:********");
     return info;
   }
    public void mettreLesToolTip(TableView<mainModel> table1,TableView<mainModel> table2)
      {
          String Chaine;
         table1.setRowFactory((tableView) -> {
      return new  TooltipTableRow<mainModel>((mainModel model) -> {
        return returnConcatenantionValeur(model);
      });
});
          table2.setRowFactory((tableView) -> {
      return new  TooltipTableRow<mainModel>((mainModel model) -> {
        return returnConcatenantionValeur(model);
      });
});
      }
    public ObservableList<mainModel> remetreLalistesousFormeDecouper(ArrayList<String> donneeExtraiteTable)
    {
           ObservableList<mainModel> list=FXCollections.observableArrayList();
           String[] tabInfo={"",""};
          mainModel mainModels = null;
          int numero=1;
          int derNierIdAliment=formulyTools.TrouverDernierIdentifiant_Aliment()+1;
          int derNierIdentifiantNut=formulyTools.TrouverDernierIdentifiant_RetentionNutriment()+1; 
          int dernierIdentifiantVit=formulyTools.TrouverDernierIdentifiant_RetentionVitamines()+1; 
          int dernierIdentifianMin=formulyTools.TrouverDernierIdentifiant_RetentionMineraux()+1; 
          int dernierIdentifiantPathologie=formulyTools.TrouverDernierIdentifiant_Pathologie()+1; 
          int dernierIdentifiantAlimentPathologie=formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1;
               for(String ligne :donneeExtraiteTable)
               {
                tabInfo=ligne.split("/");
                   //recuperation des info aliment
                  String infoA=tabInfo[0];
                 
                  String  [] donneeAliment;
                  donneeAliment=infoA.split("!");
                  System.out.println("taille info :"+donneeAliment.length);
                  String nomFr;
                  String nomEng;
                  String surnom;
                  String pays;
                  String modeCuisson;
                  String categorie;
                  String pathologie;
                  
                //info sur l'aliment
                     nomFr=(donneeAliment.length>0)?donneeAliment[0].toLowerCase():"";
                      categorie=(donneeAliment.length>=2 && !donneeAliment[1].isEmpty())?donneeAliment[1]:"";
                    modeCuisson=(donneeAliment.length>=3)?donneeAliment[2]:"cuit";
                     nomEng=(donneeAliment.length>=4)?donneeAliment[3].toLowerCase():"";
                     surnom=(donneeAliment.length>=5)?donneeAliment[4]:"";
                    pathologie=(donneeAliment.length>=6)?donneeAliment[5]:"";
                     pays=(donneeAliment.length>=7 && !donneeAliment[6].isEmpty())?donneeAliment[6]:"General";
                
                 //verification pour creer laliment
                     FmAliments aliments=null;
                     FmGroupeAliment grpe=null;
                     FmPathologie pat=null;
                     FmAlimentsPathologie fmp=null;
                     boolean pathAinserer=false;
                      if((nomFr.isEmpty() || categorie.isEmpty()) || (nomFr.isEmpty() && categorie.isEmpty()))continue;
                     if(!nomFr.isEmpty() && !categorie.isEmpty())
                     {
           //on peut proccesser a a la creation mais en verifiant si la categorie existe
                  grpe= recupererGroupeParNomFichier(listeCategorie,categorie);
                      if(grpe!=null)
                      {
                       aliments=new FmAliments(derNierIdAliment);
                       aliments.setGroupe(grpe);
                       String code=grpe.getCode()+derNierIdAliment;
                       aliments.setCode(code);
                       aliments.setNomEng(nomEng);
                       aliments.setSurnom(surnom);
                       aliments.setNomFr(nomFr);
                       String pa=donnerPays(listPays, pays);
                       String pay=(!pa.isEmpty())?pa:pays;
                       aliments.setPays(pays);
                       String modeCuissons=recupererModecuissonParNomFichier(ListmodeCuisson,modeCuisson);
                       aliments.setModeCuisson(modeCuissons);
                       aliments.setDerniereModif(new Timestamp(new Date().getTime()));
                        pat=recupererPathologieParNomFichier(listePathologie,pathologie);
                      //verifiaction si la pathologie existe ou pas nous traitons le cas ou elle nexiste pas
                      if(pat==null)
                      {
                     //creation de la pathologie
                      
                      pat=new FmPathologie(dernierIdentifiantPathologie);
                      pat.setLibelle(pathologie);
                      pat.setDate(new Timestamp(new Date().getTime()));
                       fmp=new FmAlimentsPathologie(dernierIdentifiantAlimentPathologie);
                       fmp.setAliment(aliments);
                       fmp.setPathologie(pat);
                       fmp.setDate(new Timestamp(new Date().getTime()));
                       pathAinserer=true;
                       
                      }
                      //on insere slmt laliment pour la pathologie
                      else{
                      fmp=new FmAlimentsPathologie(dernierIdentifiantAlimentPathologie);
                      fmp.setPathologie(pat);
                      fmp.setAliment(aliments);
                      fmp.setDate(new Timestamp(new Date().getTime()));
                      }
                      }
                   if(aliments!=null)
                   {
                       String lipide;
                String protide;
                String glucide;
                String energie;
                String ash;
                String eau;
                String fibre;
                
                String infoNut=(tabInfo.length>=2)?tabInfo[1]:"";
                String[] donneNutr=infoNut.split("!");
                  System.out.println("taille nutr :"+donneNutr.length);
                     
                 lipide=(donneNutr.length>=1)?formulyTools.preformaterChaineAvecEspace(donneNutr[0]):"0.0";
                 protide=(donneNutr.length>=2)?formulyTools.preformaterChaineAvecEspace(donneNutr[1]):"0.0";
                 glucide=(donneNutr.length>=3)?formulyTools.preformaterChaineAvecEspace(donneNutr[2]):"0.0";
                 energie=(donneNutr.length>=4)?formulyTools.preformaterChaineAvecEspace(donneNutr[3]):"0.0"; 
                 ash=(donneNutr.length>=5)?formulyTools.preformaterChaineAvecEspace(donneNutr[4]):"0.0"; 
                 eau=(donneNutr.length>=6)?formulyTools.preformaterChaineAvecEspace(donneNutr[5]):"0.0";
                 fibre=(donneNutr.length>=7)?formulyTools.preformaterChaineAvecEspace(donneNutr[6]):"0.0"; 
                 
                double lipides=Double.valueOf(lipide);
                double protides=Double.valueOf(protide);
                double glucides=Double.valueOf(glucide);
                double energies=Double.valueOf(energie);
                double ashs=Double.valueOf(ash);
                double eaus=Double.valueOf(eau);
                double fibres=Double.valueOf(fibre);
                FmRetentionNutriments FmRn=null;
         if(lipides>0.0 || glucides>0.0 || protides>0.0)
                {
             //a quel moment on cree l'entree de l'aliment  
                    FmRn=new FmRetentionNutriments(derNierIdentifiantNut);
                    FmRn.setAliment(aliments);
                    FmRn.setAsh((float)ashs);
                    FmRn.setEau((float)eaus);
                    FmRn.setEnergieKcal((float)energies);
                    FmRn.setFibre((float)fibres);
                    FmRn.setLipide((float)lipides);
                    FmRn.setProtein((float)protides);
                    FmRn.setGlucide((float)glucides);
                }
                    //recuperation des info relatif a aux nutriments
           if(FmRn!=null)
           {
          String infoVit=(tabInfo.length>=3)?tabInfo[2]:"";
          String[] donneVit=infoVit.split("!");
                String vita;
                String vitb1;
                String vitb2;
                String vitb6;
                String vitb12;
                String vitc;
                String vitd;
                String vite;
                String niacine;
                String folates;
                String thiamin;
                String riboflavin;
                
               vita= (donneVit.length>0)?formulyTools.preformaterChaineAvecEspace(donneVit[0]):"0.0";
               vitb1= (donneVit.length>=2)?formulyTools.preformaterChaineAvecEspace(donneVit[1]):"0.0";
               vitb2= (donneVit.length>=3)?formulyTools.preformaterChaineAvecEspace(donneVit[2]):"0.0";
               vitb6= (donneVit.length>=4)?formulyTools.preformaterChaineAvecEspace(donneVit[3]):"0.0";
              vitb12= (donneVit.length>=5)?formulyTools.preformaterChaineAvecEspace(donneVit[4]):"0.0";
              vitc= (donneVit.length>=6)?formulyTools.preformaterChaineAvecEspace(donneVit[5]):"0.0";
              vitd= (donneVit.length>=7)?formulyTools.preformaterChaineAvecEspace(donneVit[6]):"0.0";
               vite= (donneVit.length>=8)?formulyTools.preformaterChaineAvecEspace(donneVit[7]):"0.0";
              niacine= (donneVit.length>=9)?formulyTools.preformaterChaineAvecEspace(donneVit[8]):"0.0";
              folates= (donneVit.length>=10)?formulyTools.preformaterChaineAvecEspace(donneVit[9]):"0.0";
              thiamin= (donneVit.length>=11)?formulyTools.preformaterChaineAvecEspace(donneVit[10]):"0.0";
               riboflavin= (donneVit.length>=12)?formulyTools.preformaterChaineAvecEspace(donneVit[11]):"0.0";
               
               double vitaa=Double.valueOf(vita);
               double vitb11=Double.valueOf(vitb1);
               double vitb22=Double.valueOf(vitb2);
               double vitb66=Double.valueOf(vitb6);
               double vitb122=Double.valueOf(vitb12);
               double vitcc=Double.valueOf(vitc);
               double  vitdd=Double.valueOf(vitd);
               double vitee=Double.valueOf(vite);
               double niacinee=Double.valueOf(niacine);
               double folatess=Double.valueOf(folates);
               double thiaminn=Double.valueOf(thiamin);
               double riboflavinn=Double.valueOf(riboflavin);
               //creation de vitamines
               FmRetentionVitamines FmRv=null;
               FmRv=new FmRetentionVitamines(dernierIdentifiantVit);
               FmRv.setAliment(aliments);
               FmRv.setFolates((float)folatess);
               FmRv.setNiacine((float)niacinee);
               FmRv.setRiboflavin((float)riboflavinn);
               FmRv.setThiamin((float)thiaminn);
               FmRv.setVita((float)vitaa);
               FmRv.setVitb1((float)vitb11);
               FmRv.setVitb12((float)vitb122);
               FmRv.setVitb2((float)vitb22);
               FmRv.setVitb6((float)vitb66);
               FmRv.setVitc((float)vitcc);
               FmRv.setVitd((float)vitdd);
               FmRv.setVite((float)vitee);
               
               //recuperation des informations pour les sells mineraux
                String infoSel=(tabInfo.length>=4)?tabInfo[3]:"";
                String[] donneSel=infoVit.split("!");
                
              String  ca= (donneSel.length>0)?formulyTools.preformaterChaineAvecEspace(donneSel[0]):"0.0";
              String fe= (donneSel.length>=2)?formulyTools.preformaterChaineAvecEspace(donneSel[1]):"0.0";
              String mg= (donneSel.length>=3)?formulyTools.preformaterChaineAvecEspace(donneSel[2]):"0.0";
              String phos= (donneSel.length>=4)?formulyTools.preformaterChaineAvecEspace(donneSel[3]):"0.0";
             String pota= (donneSel.length>=5)?formulyTools.preformaterChaineAvecEspace(donneSel[4]):"0.0";
              String na= (donneSel.length>=6)?formulyTools.preformaterChaineAvecEspace(donneSel[5]):"0.0";
             String zn= (donneSel.length>=7)?formulyTools.preformaterChaineAvecEspace(donneSel[6]):"0.0";
              String cu= (donneSel.length>=8)?formulyTools.preformaterChaineAvecEspace(donneSel[7]):"0.0";
              
              //recuperation des info pour les sels mineraux
              double caa=Double.valueOf(ca);
              double fee=Double.valueOf(fe);
              double mgg=Double.valueOf(mg);
              double phoss=Double.valueOf(phos);
              double potas=Double.valueOf(pota);
              double naa=Double.valueOf(na);
              double znn=Double.valueOf(zn);
              double cuu=Double.valueOf(cu);
              
       FmRetentionMineraux FmRs=new FmRetentionMineraux(dernierIdentifianMin);
              FmRs.setAliment(aliments);
              FmRs.setCa((float)caa);
              FmRs.setCu((float)cuu);
              FmRs.setFe((float)fee);
              FmRs.setMg((float)mgg);
              FmRs.setNa((float)naa);
              FmRs.setPhos((float)phoss);
              FmRs.setPota((float)potas);
              FmRs.setZn((float)znn);
              
                mainModels=new mainModel();
                //enregistrement des objets
                mainModels.setAliment(aliments);
                mainModels.setFmpathologie(pat);
                mainModels.setAlimentPathologie(fmp);
                mainModels.setRetMin(FmRs);
                mainModels.setRetNu(FmRn);
                mainModels.setRetVit(FmRv);
                //fin enregistrement des objets
                mainModels.setPathologieAinsere(pathAinserer);
                mainModels.setNumero(numero);
                mainModels.setNa(naa);
                mainModels.setNom_aliment(aliments.getNomFr());
                mainModels.setNomEng(nomEng);
                mainModels.setSurnom(aliments.getSurnom());
                mainModels.setAsh(ashs);
                mainModels.setCa(caa);
                mainModels.setCategorie(grpe.getNomFr());
                mainModels.setCloumPcGlucide(glucides);
                mainModels.setCloumPclipide(lipides);
                mainModels.setCloumPcprotide(protides);
                mainModels.setCu(cuu);
                mainModels.setEau(eaus);
                mainModels.setEnergie(energies);
                mainModels.setFer(fee);
                mainModels.setFibre(fibres);
                mainModels.setIdAliment(aliments.getId());
                mainModels.setKa(potas);
                mainModels.setMg(mgg);
                mainModels.setMode_cuisson(aliments.getModeCuisson());
                mainModels.setPathologie(pat.getLibelle());
                mainModels.setPays(aliments.getPays());
                mainModels.setPota(potas);
                mainModels.setPhos(phoss);
                mainModels.setZn(znn);
                mainModels.setRiboflavin(riboflavinn);
                mainModels.setThiamin(thiaminn);
                mainModels.setVita(vitaa);
                mainModels.setVitb1(vitb11);
                mainModels.setVitb12(vitb122);
                mainModels.setVitb2(vitb22);
                mainModels.setVitb6(vitb66);
                mainModels.setVitb9(folatess);
                mainModels.setVitc(vitcc);
                mainModels.setVitd(vitdd);
                mainModels.setVite(vitee);
                list.add(mainModels);
                derNierIdAliment++;
                derNierIdentifiantNut++;
                dernierIdentifiantVit++;
                dernierIdentifianMin++;
                 if(fmp!=null)
                 {
                 dernierIdentifiantAlimentPathologie++;
                 }
                 if(pathAinserer)
                 {
                 dernierIdentifiantPathologie++;
                 }
                numero++;
                     }
                     }
                     //recuperation des valeurs pour nutriment 
                     }
             }
               
           return list;
    }
      public ObservableList<mainModel> remetreLalistesousFormeDecouper(List<Book> donneeExtraiteTable)
    {
           ObservableList<mainModel> list=FXCollections.observableArrayList();  
          mainModel mainModels = null;
          int numero=1;
          int derNierIdAliment=formulyTools.TrouverDernierIdentifiant_Aliment()+1;
          int derNierIdentifiantNut=formulyTools.TrouverDernierIdentifiant_RetentionNutriment()+1; 
          int dernierIdentifiantVit=formulyTools.TrouverDernierIdentifiant_RetentionVitamines()+1; 
          int dernierIdentifianMin=formulyTools.TrouverDernierIdentifiant_RetentionMineraux()+1; 
          int dernierIdentifiantPathologie=formulyTools.TrouverDernierIdentifiant_Pathologie()+1; 
          int dernierIdentifiantAlimentPathologie=formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1;
               for(Book ligne :donneeExtraiteTable)
               {
                  String nomFr;
                  String nomEng;
                  String surnom;
                  String pays;
                  String modeCuisson;
                  String categorie;
                  String pathologie;
                  
                //info sur l'aliment
                    nomFr=ligne.getNomFr();
                    categorie=ligne.getCategorie();
                    modeCuisson=ligne.getModeCuisson();
                    nomEng=ligne.getNomEng();
                     surnom=ligne.getSurnom();
                    pathologie="";
                     pays=ligne.getPays();
                
                 //verification pour creer laliment
                     FmAliments aliments=null;
                     FmGroupeAliment grpe=null;
                     FmPathologie pat=null;
                     FmAlimentsPathologie fmp=null;
                     boolean pathAinserer=false;
                      if((nomFr.isEmpty() || categorie.isEmpty()) || (nomFr.isEmpty() && categorie.isEmpty()))continue;
                     if(!nomFr.isEmpty() && !categorie.isEmpty())
                     {
           //on peut proccesser a a la creation mais en verifiant si la categorie existe
                  grpe= recupererGroupeParNomFichier(listeCategorie,categorie);
                      if(grpe!=null)
                      {
                       aliments=new FmAliments(derNierIdAliment);
                       aliments.setGroupe(grpe);
                       String code=grpe.getCode()+derNierIdAliment;
                       aliments.setCode(code);
                       aliments.setNomEng(nomEng);
                       aliments.setSurnom(surnom);
                       aliments.setNomFr(nomFr);
                       String pa=donnerPays(listPays, pays);
                       String pay=(!pa.isEmpty())?pa:pays;
                       aliments.setPays(pays);
                       String modeCuissons=recupererModecuissonParNomFichier(ListmodeCuisson,modeCuisson);
                       aliments.setModeCuisson(modeCuissons);
                       aliments.setDerniereModif(new Timestamp(new Date().getTime()));
                        pat=recupererPathologieParNomFichier(listePathologie,pathologie);
                      //verifiaction si la pathologie existe ou pas nous traitons le cas ou elle nexiste pas
                      }
                   if(aliments!=null)
                   {
                       String lipide;
                String protide;
                String glucide;
                String energie;
                String ash;
                String eau;
                String fibre;
                
                double lipides=ligne.getLipide();
                double protides=ligne.getProtide();
                double glucides=ligne.getGlucide();
                double energies=ligne.getGlucide();
                double ashs=ligne.getAsh();
                double eaus=ligne.getEau();
                double fibres=ligne.getFibre();
                FmRetentionNutriments FmRn=null;
         if(lipides>0.0 || glucides>0.0 || protides>0.0)
                {
             //a quel moment on cree l'entree de l'aliment  
                    FmRn=new FmRetentionNutriments(derNierIdentifiantNut);
                    FmRn.setAliment(aliments);
                    FmRn.setAsh((float)ashs);
                    FmRn.setEau((float)eaus);
                    FmRn.setEnergieKcal((float)energies);
                    FmRn.setFibre((float)fibres);
                    FmRn.setLipide((float)lipides);
                    FmRn.setProtein((float)protides);
                    FmRn.setGlucide((float)glucides);
                }
                    //recuperation des info relatif a aux nutriments
           if(FmRn!=null)
           {
     
               double vitaa=ligne.getVita();
               double vitb11=ligne.getVitb1();
               double vitb22=ligne.getVitb2();
               double vitb66=ligne.getVitb6();
               double vitb122=ligne.getVitb12();
               double vitcc=ligne.getVitc();
               double  vitdd=ligne.getVitd();
               double vitee=ligne.getVite();
               double niacinee=ligne.getNiacine();
               double folatess=ligne.getFolates();
               double thiaminn=ligne.getThiamin();
               double riboflavinn=ligne.getRiboflavin();
               //creation de vitamines
               FmRetentionVitamines FmRv=null;
               FmRv=new FmRetentionVitamines(dernierIdentifiantVit);
               FmRv.setAliment(aliments);
               FmRv.setFolates((float)folatess);
               FmRv.setNiacine((float)niacinee);
               FmRv.setRiboflavin((float)riboflavinn);
               FmRv.setThiamin((float)thiaminn);
               FmRv.setVita((float)vitaa);
               FmRv.setVitb1((float)vitb11);
               FmRv.setVitb12((float)vitb122);
               FmRv.setVitb2((float)vitb22);
               FmRv.setVitb6((float)vitb66);
               FmRv.setVitc((float)vitcc);
               FmRv.setVitd((float)vitdd);
               FmRv.setVite((float)vitee);
               
               //recuperation des informations pour les sells mineraux
              //recuperation des info pour les sels mineraux
              double caa=ligne.getCa();
              double fee=ligne.getFer();
              double mgg=ligne.getMg();
              double phoss=ligne.getPhos();
              double potas=ligne.getPota();
              double naa=ligne.getNa();
              double znn=ligne.getZn();
              double cuu=ligne.getCu();
              
       FmRetentionMineraux FmRs=new FmRetentionMineraux(dernierIdentifianMin);
              FmRs.setAliment(aliments);
              FmRs.setCa((float)caa);
              FmRs.setCu((float)cuu);
              FmRs.setFe((float)fee);
              FmRs.setMg((float)mgg);
              FmRs.setNa((float)naa);
              FmRs.setPhos((float)phoss);
              FmRs.setPota((float)potas);
              FmRs.setZn((float)znn);
              
                mainModels=new mainModel();
                //enregistrement des objets
                mainModels.setAliment(aliments);
                mainModels.setFmpathologie(pat);
                mainModels.setAlimentPathologie(fmp);
                mainModels.setRetMin(FmRs);
                mainModels.setRetNu(FmRn);
                mainModels.setRetVit(FmRv);
                //fin enregistrement des objets
                mainModels.setPathologieAinsere(pathAinserer);
                mainModels.setNumero(numero);
                mainModels.setNa(naa);
                mainModels.setNom_aliment(aliments.getNomFr());
                mainModels.setNomEng(nomEng);
                mainModels.setSurnom(aliments.getSurnom());
                mainModels.setAsh(ashs);
                mainModels.setCa(caa);
                mainModels.setCategorie(grpe.getNomFr());
                mainModels.setCloumPcGlucide(glucides);
                mainModels.setCloumPclipide(lipides);
                mainModels.setCloumPcprotide(protides);
                mainModels.setCu(cuu);
                mainModels.setEau(eaus);
                mainModels.setEnergie(energies);
                mainModels.setFer(fee);
                mainModels.setFibre(fibres);
                mainModels.setIdAliment(aliments.getId());
                mainModels.setKa(potas);
                mainModels.setMg(mgg);
                mainModels.setMode_cuisson(aliments.getModeCuisson());
                mainModels.setPathologie(pat.getLibelle());
                mainModels.setPays(aliments.getPays());
                mainModels.setPota(potas);
                mainModels.setPhos(phoss);
                mainModels.setZn(znn);
                mainModels.setRiboflavin(riboflavinn);
                mainModels.setThiamin(thiaminn);
                mainModels.setVita(vitaa);
                mainModels.setVitb1(vitb11);
                mainModels.setVitb12(vitb122);
                mainModels.setVitb2(vitb22);
                mainModels.setVitb6(vitb66);
                mainModels.setVitb9(folatess);
                mainModels.setVitc(vitcc);
                mainModels.setVitd(vitdd);
                mainModels.setVite(vitee);
                list.add(mainModels);
                derNierIdAliment++;
                derNierIdentifiantNut++;
                dernierIdentifiantVit++;
                dernierIdentifianMin++;
                 if(fmp!=null)
                 {
                 dernierIdentifiantAlimentPathologie++;
                 }
                 if(pathAinserer)
                 {
                 dernierIdentifiantPathologie++;
                 }
                numero++;
                     }
                     }
                     //recuperation des valeurs pour nutriment 
                     }
             }
           return list;
    }
      private static void configureFileChooser(final FileChooser fileChooser) {      
             fileChooser.setTitle("fichier depuis Formuly V.0.1");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );    
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT ou Excel ", "*.txt","*.xlsx")
            );
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
                       // openFile(file);
                        String   nomFichiers=file.getName();
                        String extension =nomFichiers.substring(nomFichiers.indexOf(".")).toLowerCase();
                       if(extension.equals(".txt"))
                       {
                       EstfichierExcel=false;
                         System.out.println("fichier txt");
                       }
                       else
                       {
                       if(extension.equals(".xlsx"))
                       {
                         EstfichierExcel=true;
                           System.out.println("exel recuperer");
                       }
                       }
                        enregistrerAliment(file);
                    }
                   
  }
    public void enregistrerAliment(File file)
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
              System.out.println("new value: "+newValue);
              if("terminer".equals(newValue))
              { 
                // registerThread.
               initialiserInfoFichier(file,nomFichier,derniereModif,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
               alert.setGraphic(null);
               alert.setTitle("Traitement termine");
               alert.setContentText("Votre Fichier a ete  avec succes :\n"
                       + " vous Trouverez dans le tableau les aliments");
               alert.getButtonTypes().setAll(ButtonType.FINISH);  
               alert.showAndWait();
               
              }
              else{
               if("terminer rien".equals(newValue))
              { 
                // registerThread.
                initialiserInfoFichier(file,nomFichier,derniereModif,tailleFichier);
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
                     ArrayList<String> info;
                     ArrayList<String> infoTrie;
   
          try {
              if(!EstfichierExcel)
              {
                     updateProgress(5, 100);
                     updateMessage("Debut de la lecture du fichier ...");
                     Thread.sleep(90);
                     info=RecupererElementFichier(file);
                     updateProgress(30, 100);
                     updateMessage("fin de la lecture");
                     Thread.sleep(50);
                     infoTrie=extraireInformationDsLeLot(info);
                      updateProgress(40, 100);
                     updateMessage("recuperation des informations non erronnées...");
                     Thread.sleep(50);
                     listeSaisie= remetreLalistesousFormeDecouper(infoTrie);
                      updateProgress(70, 100);  
                      if(listeSaisie.size()>0)
                      {
                    updateMessage("nous preparons l'affichage...");
                         listeEnregistrer=listeSaisie;
                          updateProgress(80, 100); 
                          updateMessage("Presque Fini");
                          Thread.sleep(30);
                        initialiserTab1(listeSaisie);
                          updateProgress(100, 100);
                          updateMessage("terminer");
                      }
                      else{
                      updateProgress(100, 100);
                      updateMessage("terminer rien");
                      }
              }
            else
              {
                   List<Book> listElement;
                  updateProgress(5, 100);
              updateMessage("Debut de la lecture du fichier....");
                ExcelTools lecteurFichirM=new ExcelTools();
                 updateProgress(9, 100);
                 updateMessage("Recuperation des Information contenu dans le fichier...");       
                listElement=lecteurFichirM.readBooksFromExcelFile(file);
                 updateProgress(30, 100);
                updateMessage("Extraction des Information Valides contenu dans le fichier...");
                     Thread.sleep(20);
               listeSaisie= remetreLalistesousFormeDecouper(listElement);
             updateMessage("Preparation de certains elements pour l'affichage ...");
                      updateProgress(50, 100);
                     updateMessage("Re");
                     Thread.sleep(10);
                      updateProgress(70, 100);  
                      if(listeSaisie.size()>0)
                      {
                         listeEnregistrer=listeSaisie;
                          updateProgress(80, 100); 
                          updateMessage("presque terminé .... ");
                          Thread.sleep(30);
                        initialiserTab1(listeSaisie);
                          updateProgress(100, 100);
                          updateMessage("terminer");
                      }
                      else{
                      updateProgress(100, 10);
                      updateMessage("terminer rien");
                      }
              }
          } catch (Exception e) {
               updateMessage("erreur");  
              System.out.println("une erreur produite : "+e.getLocalizedMessage());
         Logger.getLogger(ExcelTools.class.getName()).log(Level.SEVERE, null, e);
           
          }
        return true;
      }
    };
  }
    /**
     * methode permettant de rendre dans la base de donnée les aliments 
     * valides selectionner dans le fichier
     */
    public void enregistrerAliment()
         {
              ProgressBar  progressBar =new ProgressBar(0);
               progressBar.prefWidth(100.0);
                 Alert alert = new Alert(Alert.AlertType.NONE);
               alert.setGraphic( progressBar);
                alert.setTitle("Enregistrement d'aliment");
               alert.show();
               Task copyWorker = createWorker(table1.getItems());
          progressBar.progressProperty().unbind();
          progressBar.progressProperty().bind(copyWorker.progressProperty());
        
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                
                // registerThread.
               alert.setContentText("preparation pour l'afficahe...");
                  System.out.println("taille liste enregister: "+listeEnregistrer.size());
                 
                    chargerDonne(listeSaisie,"aj");
                     System.out.println("taille liste enregister second : "+listeEnregistrer.size());
              formulyTools.initialiserLabelInfoAliment(derniereModif,nomFichier,tailleFichier);
               alert.setAlertType(Alert.AlertType.INFORMATION); 
               alert.close();
                  viderTableau(table1);
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                    alert.setTitle("Fin insertion");
               alert.setContentText("Aliment(s) enregistré(s) avec succes:");
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
    public void viderTableau(TableView<mainModel> model)
    {
     model.getItems().clear();
    }
    public void chargerDonne(ObservableList<mainModel> list,String ...actu)
    {
       table2_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
       table2_nomFr.setCellValueFactory(new PropertyValueFactory<>("nom_aliment")); 
       table2_glucide.setCellValueFactory(new PropertyValueFactory<>("cloumPcGlucide"));
       table2_lipide.setCellValueFactory(new PropertyValueFactory<>("cloumPclipide"));
       table2_protide.setCellValueFactory(new PropertyValueFactory<>("cloumPcprotide"));
       table2_energie.setCellValueFactory(new PropertyValueFactory<>("Energie"));
       table2_fer.setCellValueFactory(new PropertyValueFactory<>("fer"));
       table2_mg.setCellValueFactory(new PropertyValueFactory<>("mg"));
       table2_na.setCellValueFactory(new PropertyValueFactory<>("na"));
       table2_ka.setCellValueFactory(new PropertyValueFactory<>("ka"));
       table2_vitc.setCellValueFactory(new PropertyValueFactory<>("vitc"));
       table2_vita.setCellValueFactory(new PropertyValueFactory<>("vita"));
       table2_vite.setCellValueFactory(new PropertyValueFactory<>("vite"));
       table2_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
       table2_modeCuisson.setCellValueFactory(new PropertyValueFactory<>("mode_cuisson"));
       table2_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        if(actu[0].equals("aj"))
        {
           table2.getItems().addAll(list);
        }
      else{
         table2.getItems().setAll(list);
        }
      
    }
    public Task createWorker(ObservableList<mainModel> Listmodel) {
    return new Task() {
      @Override
      protected Object call() throws Exception {
             EntityManager em=formulyTools.getEm().createEntityManager();
             int tailleDonnee=Listmodel.size();
             int i=1,j=4;
             if(tailleDonnee==1)
             {
             j=99;
             }
          try {
               for(mainModel model:Listmodel)    
               {
         em.getTransaction().begin(); 
               FmAliments aliments=model.getAliment();
               FmPathologie pat=model.getFmpathologie();
               FmAlimentsPathologie alPath=model.getAlimentPathologie();
               FmRetentionMineraux retMin=  model.getRetMin();
               FmRetentionNutriments retNu=model.getRetNu();
               FmRetentionVitamines retVit=model.getRetVit();
               updateMessage("Insertion de :"+aliments.getNomFr());
               Thread.sleep(30);
               em.persist(aliments); 
               Thread.sleep(30);
               em.persist(retNu);
               Thread.sleep(30);
               em.persist(retMin);
               Thread.sleep(30);
               em.persist(retVit);  
            
              if(model.isPathologieAinsere())
          {
         updateMessage("Finalisation de :"+aliments.getNomFr());
               Thread.sleep(200);
               em.persist(pat);
               Thread.sleep(30);
               em.persist(alPath);
          }
         else{
         updateMessage("Finalisation de :"+aliments.getNomFr());
              Thread.sleep(200);
              em.persist(alPath);
             }
          updateProgress(j + 1, 10);
          j=(100/tailleDonnee);
          if(i!=tailleDonnee)
          {
          updateMessage("fin de : "+aliments.getNomFr());
          }
          else{
         updateMessage("terminer");
          }
         i++;
          em.getTransaction().commit();
              
               }
           } catch (Exception e) {
          }
         
         
          
      
        return true;
      }
    };
  }
    private  static void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(Inserer_aliment_fichierController.class.getName()).log(
                Level.SEVERE, null, ex
            );
        }
    }

    private void fermerFenetre() {
       int nbre=table1.getItems().size();
            if(nbre==0)
            {
                Stage stage = (Stage) quitter.getScene().getWindow();
               stage.close();
            }
            else
            {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Travail non enregistrer");
            alert.setHeaderText("Aliments Non enregistrés \n");
            alert.setContentText("Le Travail n'as pas ete Enregistrer \n"
                    + "VOULEZ-VOUS VRAIMENT QUITTEZ ?");
               Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg")
      );
               alert.setGraphic(new ImageView(image));
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
           Stage stage = (Stage) quitter.getScene().getWindow();
               stage.close();
        }                
            }
    }
    
}