/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import formuly.classe.TooltipTableRow;
import formuly.classe.bilanMacroNut;
import formuly.classe.formulyTools;
import formuly.classe.repasModel;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TableColumn<mainModel, Double> table1_categorie;

    @FXML
    private TableColumn<mainModel, Double> table1_vite;

    @FXML
    private TableColumn<mainModel, Double> table2_nomFr;

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
    private TableColumn<mainModel, Double> table2_categorie;

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
    private TableColumn<mainModel, Double> table2_modeCuisson;

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
    private TableColumn<mainModel, Double> table2_pays;

    @FXML
    private TableColumn<mainModel, Double> table2_mg;
    ObservableList<mainModel> listeSaisie;

     private static Desktop desktop ;
      final FileChooser fileChooser ;

        List<FmGroupeAliment> listeCategorie;
    List<FmPathologie> listePathologie;
    List<String> listPays;
    ObservableList<String> ListmodeCuisson;
   
    
    public Inserer_aliment_fichierController() {
         desktop = Desktop.getDesktop();
         fileChooser = new FileChooser();
        listeCategorie=modelFoodSelect.listeCategories(); 
        listePathologie=modelFoodSelect.listePathologie();
        ListmodeCuisson=modelFoodSelect.listeDesMode_cuissonss();
        listPays=initialiserPays();
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
                                System.out.println("nanou");
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
                      if((nomFr.isEmpty() || categorie.isEmpty()) || (nomFr.isEmpty() && categorie.isEmpty()))continue;
                     if(!nomFr.isEmpty() && !categorie.isEmpty())
                     {
           //on peut proccesser a a la creation mais en verifiant si la categorie existe
                  grpe= recupererGroupeParNomFichier(listeCategorie,categorie);
                      if(grpe!=null)
                      {
                     int idAliment=formulyTools.TrouverDernierIdentifiant_Aliment()+1;
                       aliments=new FmAliments(idAliment);
                       aliments.setGroupe(grpe);
                       String code=grpe.getCode()+idAliment;
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
                      FmAlimentsPathologie fmp;
                      //verifiaction si la pathologie existe ou pas nous traitons le cas ou elle nexiste pas
                      if(pat==null)
                      {
                     //creation de la pathologie
                    int idPa=formulyTools.TrouverDernierIdentifiant_Pathologie();
                      pat=new FmPathologie(idAliment);
                      pat.setLibelle(pathologie);
                      pat.setDate(new Timestamp(new Date().getTime()));
                       fmp=new FmAlimentsPathologie(formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1);
                       fmp.setAliment(aliments);
                       fmp.setPathologie(pat);
                       fmp.setDate(new Timestamp(new Date().getTime()));
                      }
                      //on insere slmt laliment pour la pathologie
                      else{
                      fmp=new FmAlimentsPathologie(formulyTools.TrouverDernierIdentifiant_Aliment_Pathologie()+1);
                      fmp.setPathologie(pat);
                      fmp.setAliment(aliments);
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
                    FmRn=new FmRetentionNutriments(formulyTools.TrouverDernierIdentifiant_RetentionNutriment()+1);
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
               FmRv=new FmRetentionVitamines(formulyTools.TrouverDernierIdentifiant_RetentionVitamines()+1);
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
              
       FmRetentionMineraux FmRs=new FmRetentionMineraux(formulyTools.TrouverDernierIdentifiant_RetentionMineraux()+1);
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
                numero++;
                     }
                     }
                     //recuperation des valeurs pour nutriment 
               
             }
               
           return list;
    }
   
            private static void configureFileChooser(
        final FileChooser fileChooser) {      
             fileChooser.setTitle("Select de Fichier Aliment");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
    }
  private void takeFile(Button btn)
  {
         Stage stage=(Stage) btn.getScene().getWindow();
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    ArrayList<String> info;
                     ArrayList<String> infoTrie;
                    if (file != null) {
                       // openFile(file);
                     initialiserInfoFichier(file,nomFichier,derniereModif,tailleFichier);
                     info=RecupererElementFichier(file);
                     infoTrie=extraireInformationDsLeLot(info);
                     listeSaisie= remetreLalistesousFormeDecouper(infoTrie);
                        initialiserTab1(listeSaisie);
                
                    }
                   
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
}