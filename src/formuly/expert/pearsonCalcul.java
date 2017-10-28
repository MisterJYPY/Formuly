/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.expert;

import formuly.model.frontend.mainModel;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Mr_JYPY
 */
public class pearsonCalcul {
    
 private List<mainModel> listAliments;
 private List<String> elementAequilibre;
 private List<Double> ValeurelementAequilibre;
 private double proportionAliment1;
 private double proportionAliment2;
 private String obdjectif;
 private double valeurObdjective;
 private ArrayList<Double> ListResultatEquilibre;
 private  int TailleList;
 /**
  * 
  * @param listAliments
  * @param valeurObj
  * @param obdjectif 
  */
    public pearsonCalcul(ObservableList<mainModel> listAliments,double valeurObj, String obdjectif) {
        this.listAliments = listAliments;
        this.obdjectif = obdjectif;
        this.valeurObdjective=valeurObj;
    }
   /**
    * 
    * @param listAliments
    * @param valeurObj
    * @param obdjectif
    * @param listElementCasEquilibre
    * @param ValeurListEquilibre 
    */
    public pearsonCalcul(ObservableList<mainModel> listAliments,double valeurObj, String obdjectif,List<String> listElementCasEquilibre,List<Double> ValeurListEquilibre) {
        this.listAliments = listAliments;
        this.obdjectif = obdjectif;
        this.valeurObdjective=valeurObj;
        this.elementAequilibre=listElementCasEquilibre;
        this.ValeurelementAequilibre=ValeurListEquilibre;
        ListResultatEquilibre=new ArrayList<>();
        
    }
    /**
     * constructeur de la classe qui execute la methode de Pearson
     * @param listAliments la liste des aliments
     * @param valeurObj    la valeur de l'element a determiner la valeur
     * @param obdjectif    l'obdjectif qui est soit (proteine,glucide ou protide)
     * @param listElementCasEquilibre 
     */
     public pearsonCalcul(List<mainModel> listAliments,double valeurObj, String obdjectif,List<String> listElementCasEquilibre) {
        this.listAliments = listAliments;
        this.obdjectif = obdjectif;
        this.valeurObdjective=valeurObj;
        this.elementAequilibre=listElementCasEquilibre;
        ListResultatEquilibre=new ArrayList<>();
        
    }
 /**
  * methode qui permet de retourner la valeur de l'obdjectif (lipide,Glucide,Protide)
  * @param aliment un obdjet de type mainModel
  * @return une valeur de type double
  */
    private double retourneLaValeurObdjectif(mainModel aliment)
    {
    double valeur=0;
    valeur=(obdjectif.equals("Proteine"))?aliment.getCloumPcprotide():(obdjectif.equals("Glucide"))?aliment.getCloumPcGlucide():(obdjectif.equals("Lipide"))?aliment.getCloumPclipide():-2;
       
    return valeur;
    }
    public double[] calculPearson()
    {
    double [] resultat=new double[2];
    double valeur1=retourneLaValeurObdjectif(listAliments.get(0));
    double valeur2=retourneLaValeurObdjectif(listAliments.get(1));
     System.out.println(obdjectif+ " 1 : "+valeur1);
      System.out.println(obdjectif+ " 2 : "+valeur2);
    double somme;
      
    double absValeur1=Math.abs(valeur1-valeurObdjective);
    double absValeur2=Math.abs(valeur2-valeurObdjective);
            System.out.println("valeur obdjectif : "+valeurObdjective);
      System.out.println("calcul 1 : "+absValeur1);
      System.out.println("calcul 2 : "+absValeur2);
       somme=absValeur1+absValeur2;
     proportionAliment1=(somme!=0)?(absValeur2/somme)*100:0.0;
     proportionAliment2=(somme!=0)?(absValeur1/somme)*100:0.0; 
        //mis a jour des resultats des calculs dans le model
     listAliments.get(0).setResultatCalcul( proportionAliment1);
     listAliments.get(1).setResultatCalcul( proportionAliment2);
        //enregistrement du resultat des calculs
     resultat[0]=proportionAliment1;
     resultat[1]=proportionAliment2;
       //affichages des resultats sur la console
    if(elementAequilibre!=null && elementAequilibre.size()>0)
    {
      for(String element :elementAequilibre)
      {
         CalculValeurAEquilibre(element);
      }
    }
    return resultat;
    }
 /**
  * methode qui permet de calculer l'equilibre d'un element(mineraux,vitamines,sel minearux)
  * @param elementAequilibre 
  */
 private void CalculValeurAEquilibre(String elementAequilibre)
   {
   double valeur=0.0;
   double valeur1;
   double valeur2;
    
       switch (elementAequilibre)
  {
  case "Ca":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getCa():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getCa():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
   case "Ka":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getPota():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getPota():0.0;
   valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
  case "Phos":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getPhos():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getPhos():0.0;
   valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
  case "Na":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getNa():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getNa():0.0;
   valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
  case "Cu":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getCu():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getCu():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
  case "Fer":
      valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getFe():0.0;
      valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getFe():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
  case "Mg":
     valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getMg():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getMg():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
    case "eau":
      valeur1=(listAliments.get(0).getRetNu()!=null)?listAliments.get(0).getRetNu().getEau():0.0;
     valeur2=(listAliments.get(1).getRetNu()!=null)?listAliments.get(1).getRetNu().getEau():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
  case "Ash":
     valeur1=(listAliments.get(0).getRetNu()!=null)?listAliments.get(0).getRetNu().getAsh():0.0;
     valeur2=(listAliments.get(1).getRetNu()!=null)?listAliments.get(1).getRetNu().getAsh():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
    case "Zn":
      valeur1=(listAliments.get(0).getRetMin()!=null)?listAliments.get(0).getRetMin().getZn():0.0;
     valeur2=(listAliments.get(1).getRetMin()!=null)?listAliments.get(1).getRetMin().getZn():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
  break;
    case "vitA":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVita():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVita():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
     case "vitE":
     valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVite():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVite():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
    case "vitC":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitc():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitc():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
     case "vitD":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitd():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitd():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
    case "vitB1":
     valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitb1():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitb1():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
    break;
    case "vitB2":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitb2():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitb2():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
    case "vitB6":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitb6():0.0;
      valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitb6():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
    case "vitB12":
     valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getVitb12():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getVitb12():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
     case "fibre":
     valeur1=(listAliments.get(0).getRetNu()!=null)?listAliments.get(0).getRetNu().getFibre():0.0;
     valeur2=(listAliments.get(1).getRetNu()!=null)?listAliments.get(1).getRetNu().getFibre():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
    case "folates":
     valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getFolates():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getFolates():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
    case "thiamin":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getThiamin():0.0;
     valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getThiamin():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
      case "riboflavin":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getRiboflavin():0.0;
      valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getRiboflavin():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
      case "niacine":
      valeur1=(listAliments.get(0).getRetVit()!=null)?listAliments.get(0).getRetVit().getNiacine():0.0;
      valeur2=(listAliments.get(1).getRetVit()!=null)?listAliments.get(1).getRetVit().getNiacine():0.0;
     valeur=(valeur1*(proportionAliment1/100))+(valeur2*(proportionAliment2/100));
     break;
  
       }
      ListResultatEquilibre.add(valeur);
   }

    public List<mainModel> getListAliments() {
        return listAliments;
    }

    public void setListAliments(List<mainModel> listAliments) {
        this.listAliments = listAliments;
    }

    public String getObdjectif() {
        return obdjectif;
    }
public void initialiserLesList()
{
 ListResultatEquilibre.clear();
}
    public void setObdjectif(String obdjectif) {
        this.obdjectif = obdjectif;
    }

    public double getValeurObdjective() {
        return valeurObdjective;
    }

    public void setValeurObdjective(double valeurObdjective) {
        this.valeurObdjective = valeurObdjective;
    }

    public ArrayList<Double> getListResultatEquilibre() {
        return ListResultatEquilibre;
    }

    public void setListResultatEquilibre(ArrayList<Double> ListResultatEquilibre) {
        this.ListResultatEquilibre = ListResultatEquilibre;
    }
  
}
