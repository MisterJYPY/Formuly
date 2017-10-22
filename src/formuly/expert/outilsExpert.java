/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.expert;

import formuly.entities.FmFaitConclusion;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mr_JYPY
 */
public class outilsExpert {
    
   ArrayList<String> lesFaitsTrouver;
    private double ageClient;
    private double poidsClient;
    private double sexeClient;
    private double regime1000;
    private String conclusion;
/**
 * constructeur par defaut de la classe outil expert
 */
    public outilsExpert() {
        lesFaitsTrouver=new ArrayList<>();
        init_valeur();
    }
    public void init_valeur()
    {
     this.poidsClient=-2;
     this.ageClient=-2;
     this.sexeClient=-2;
     this.tailleclient=-2;
    }
/**
 * constructeur parametre
 * @param AetLipide apport energetique en Lipide
 * @param AetProide apport energetique en Protide
 * @param AetGlucide apport energetique en Glucide
 * @param prcentGlucide pourcentage en Glucide
 * @param prcentProtide pourcentage en Protide
 * @param prcenLipide   pourcentage en Lipide
     * @param regime1000
 * @param regime1500    pourcentage regime de 1500
 * @param regime2000    pourcentage regime de 2000
 * @param regime2500    pourcentage regime de 2500
 * @param regime3000    pourcentage regime de 3000
 * @param regime3500    pourcentage regime de 3500
 * @param regime4500    pourcentage regime de 4500
 * @param EnergieTotale valeur en energie Totale
 */
    public outilsExpert(double AetLipide, double AetProide, double AetGlucide, double prcentGlucide, double prcentProtide, double prcenLipide, double regime1000,double regime1500, double regime2000, double regime2500, double regime3000, double regime3500, double regime4500, double EnergieTotale) {
        this.AetLipide = AetLipide;
        this.AetProide = AetProide;
        this.AetGlucide = AetGlucide;
        this.prcentGlucide = prcentGlucide;
        this.prcentProtide = prcentProtide;
        this.prcenLipide = prcenLipide;
     this.regime1000 = regime1000;
        this.regime1500 = regime1500;
        this.regime2000 = regime2000;
        this.regime2500 = regime2500;
        this.regime3000 = regime3000;
        this.regime3500 = regime3500;
        this.regime4500 = regime4500;
        this.EnergieTotale = EnergieTotale;
    }
public outilsExpert(double AetLipide, double AetProide, double AetGlucide, double prcentGlucide, double prcentProtide, double prcenLipide, double regime1000,double regime1500, double regime2000, double regime2500, double regime3000, double regime3500, double EnergieTotale) {
        this.AetLipide = AetLipide;
        this.AetProide = AetProide;
        this.AetGlucide = AetGlucide;
        this.prcentGlucide = prcentGlucide;
        this.prcentProtide = prcentProtide;
        this.prcenLipide = prcenLipide;
     this.regime1000 = regime1000;
        this.regime1500 = regime1500;
        this.regime2000 = regime2000;
        this.regime2500 = regime2500;
        this.regime3000 = regime3000;
        this.regime3500 = regime3500;
        this.EnergieTotale = EnergieTotale;
    }
    /**
 * constructeur parametre
* @param lesFaitsTrouver liste des faits enregistré apres le chainage avant
* @param ageClient      l'age du client
* @param poidsClient    le poids du client
* @param sexeClient     le sexe du client
 * @param AetLipide apport energetique en Lipide
 * @param AetProide apport energetique en Protide
 * @param AetGlucide apport energetique en Glucide
 * @param prcentGlucide pourcentage en Glucide
 * @param prcentProtide pourcentage en Protide
 * @param prcenLipide   pourcentage en Lipide
 * @param regime1500    pourcentage regime de 1500
 * @param regime2000    pourcentage regime de 2000
 * @param regime2500    pourcentage regime de 2500
 * @param regime3000    pourcentage regime de 3000
 * @param regime3500    pourcentage regime de 3500
 * @param regime4500    pourcentage regime de 4500
 * @param EnergieTotale valeur en energie Totale
 */
    public outilsExpert(ArrayList<String> lesFaitsTrouver, double ageClient, double poidsClient, double sexeClient, double AetLipide, double AetProide, double AetGlucide, double prcentGlucide, double prcentProtide, double prcenLipide, double regime1500, double regime2000, double regime2500, double regime3000, double regime3500, double regime4500, double EnergieTotale) {
        this.lesFaitsTrouver = lesFaitsTrouver;
        this.ageClient = ageClient;
        this.poidsClient = poidsClient;
        this.sexeClient = sexeClient;
        this.AetLipide = AetLipide;
        this.AetProide = AetProide;
        this.AetGlucide = AetGlucide;
        this.prcentGlucide = prcentGlucide;
        this.prcentProtide = prcentProtide;
        this.prcenLipide = prcenLipide;
        this.regime1500 = regime1500;
        this.regime2000 = regime2000;
        this.regime2500 = regime2500;
        this.regime3000 = regime3000;
        this.regime3500 = regime3500;
        this.regime4500 = regime4500;
        this.EnergieTotale = EnergieTotale;
    }
     /**
      * methode permettant de traiter une regle 
      * en verifiant si ces premisses sont verifiés
      * @param regle  la regle
      * @return  un boolean qui est true si la regle est declenchable et non ds la cas contraire
      */
      public boolean decouperVal(String regle)
    {
        lesFaitsTrouver.clear();
   String [] donnee=regle.split("ET");
   ArrayList<Integer> list=new ArrayList<>();
   boolean regleApplicable=false;
      
       String element;
       String cond;
       String val;
       String [] alors;
       String conclusion="";
   if(donnee.length>1)
   {
       for(int i=0;i<donnee.length;i++)
       {
       String expr=donnee[i];
       String [] exprOu=expr.split("ou");
       int tailleOu =exprOu.length;
       if(tailleOu<=1)  
       {
          element="";
          cond="";
          val="";
         conclusion="";
        
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5);
          
         if(i==donnee.length-1)
         {
           System.out.println("");
           alors=val.split("alors");
           val=alors[0];
          conclusion=alors[1];
         }
  
       list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
      else{
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
            expr=exprOu[j]; 
           // System.out.println("expr *******************: "+expr);
            String[] snsAlors=expr.split("alors");
          //  System.out.println("expr tailel*******************: "+snsAlors.length);
          //   conclusions=snsAlors[snsAlors.length-1];
          String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
         conclusion=snsAlors[snsAlors.length-1];
    
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
          
      listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
       }
       }
   //ici dans le cas ou il n'y a pas de et 
   else{
        String expr=donnee[0];
        String [] exprOu=expr.split("ou");
       int tailleOu =exprOu.length;
         //nous allons traiter le cas ou il n'y pas de ou
       // System.out.println("regle :"+regle);
     if(tailleOu<=1)
             {
         alors=regle.split("alors");
         String nv=alors[0];
         element=nv.substring(0, 3);
         cond=nv.substring(3,5);
         val=nv.substring(5);
         conclusion=alors[1];
    
      list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
             }
    else{
      
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
 
               expr=exprOu[j]; 
         String[] snsAlors=expr.split("alors");
       //    System.out.println(""+snsAlors[snsAlors.length-1]);
        String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
        conclusion=snsAlors[snsAlors.length-1];
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
       listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
   }
      int tailleListe=list.size();
      int cpt=0;
       for(Integer ent:list)
       {
         cpt=cpt+ent; 
        //   System.out.println(ent+" ");
       }
       if(cpt==tailleListe)
       {
      lesFaitsTrouver.add(conclusion);
      regleApplicable=true;
       }
      
     //  for(String eleme)
       
       return regleApplicable;
    }
       /**
      * methode permettant de traiter une regle 
      * particuliarite : permet d'inclure les connecteur OU
      * en verifiant si ces premisses sont verifiés
      * @param regle  la regle
     * @param parametreFictif un parametre qui sert de surcharge 
      * @return  un boolean qui est true si la regle est declenchable et non ds la cas contraire
      */
           public boolean decouperVal(String regle,String parametreFictif)
    {
   String [] donnee=regle.split("ET");
 //  String [] TableConjonction=regle.split("/");//pour dire ou
   String [] TableDisjonction=regle.split("//");//pour dire Et
   ArrayList<Integer> list=new ArrayList<>();
  int tailleElementOu=TableDisjonction.length;
  
   boolean regleApplicable=false;
      
       String element;
       String cond;
       String val;
       String [] alors;
       String conclusion="";
     if(tailleElementOu<=1)
       {
     System.out.println("**********presence de ou**************");
   if(donnee.length>1)
   {
       System.out.println("**********nous avons des e**************");
       for(int i=0;i<donnee.length;i++)
       {
       String expr=donnee[i];
       String [] exprOu=expr.split("ou");
       int tailleOu =exprOu.length;
       if(tailleOu<=1)  
       {
          element="";
          cond="";
          val="";
         conclusion="";
        
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5);
          
         if(expr.contains("alors"))
         {
           System.out.println("");
           alors=val.split("alors");
           val=alors[0];      
          conclusion=alors[1];
         }
   System.out.println(val);
       list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
      else{
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
            expr=exprOu[j]; 
           // System.out.println("expr *******************: "+expr);
            String[] snsAlors=expr.split("alors");
          //  System.out.println("expr tailel*******************: "+snsAlors.length);
          //   conclusions=snsAlors[snsAlors.length-1];
          String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
         conclusion=snsAlors[snsAlors.length-1];
    
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
          
      listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
       }
       }
   //ici dans le cas ou il n'y a pas de et 
   else{
        String expr=donnee[0];
        String [] exprOu=expr.split("ou");
       int tailleOu =exprOu.length;
         //nous allons traiter le cas ou il n'y pas de ou
       // System.out.println("regle :"+regle);
     if(tailleOu<=1)
             {
         alors=regle.split("alors");
         String nv=alors[0];
         element=nv.substring(0, 3);
         cond=nv.substring(3,5);
         val=nv.substring(5);
         conclusion=alors[1];
    
      list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
             }
    else{
      
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
 
               expr=exprOu[j]; 
         String[] snsAlors=expr.split("alors");
       //    System.out.println(""+snsAlors[snsAlors.length-1]);
        String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
        conclusion=snsAlors[snsAlors.length-1];
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
       listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
   }
      int tailleListe=list.size();
      int cpt=0;
       for(Integer ent:list)
       {
         cpt=cpt+ent; 
        //   System.out.println(ent+" ");
       }
       if(cpt==tailleListe)
       {
      lesFaitsTrouver.add(conclusion);
      regleApplicable=true;
       }
    }  
     else{
         System.out.println("************bcp de ou ***********");
       ArrayList<Integer> solutionPrimaire=new ArrayList<>();
     for(int k=0;k<TableDisjonction.length;k++)
         {
           String  expression=TableDisjonction[k];
        System.out.println("ex "+expression);
        donnee=expression.split("ET");   
        
        if(donnee.length>1)
   {
            System.out.println("************bcp de donnee***********");
       for(int i=0;i<donnee.length;i++)
       {
       String expr=donnee[i];
       String [] exprOu=expr.split("ou");
           System.out.println("expr :"+expr);
       if(expr.contains("alors")){
       String[] exprConcl=expr.split("alors");
        conclusion=exprConcl[exprConcl.length-1];
        //   System.out.println("Conclusion "+conclusions);
           }
       int tailleOu =exprOu.length;
       if(tailleOu<=1)  
       {
          element="";
          cond="";
          val="";
        // conclusions="";
        
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5);
          
         if(i==donnee.length-1)
         {
           System.out.println("VAL: "+val);
           alors=val.split("alors");
           val=alors[0];
         //  conclusions=alors[1];
         }
  
       list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
      else{
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
            expr=exprOu[j]; 
           // System.out.println("expr *******************: "+expr);
            String[] snsAlors=expr.split("alors");
          //  System.out.println("expr tailel*******************: "+snsAlors.length);
          //   conclusions=snsAlors[snsAlors.length-1];
          String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
         conclusion=snsAlors[snsAlors.length-1];
    
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
          
      listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
       }
       }
   //ici dans le cas ou il n'y a pas de et 
   else{
            System.out.println("EXXX"+expression);
        String expr=expression;
        String [] exprOu=expr.split("ou");
       int tailleOu =exprOu.length;
          
         //nous allons traiter le cas ou il n'y pas de ou
       // System.out.println("regle :"+regle);
     if(tailleOu<=1 && expression.contains("alors"))
             {
         alors=expression.split("alors");
         String nv=alors[0];
         element=nv.substring(0, 3);
         cond=nv.substring(3,5);
         val=nv.substring(5);
         conclusion=alors[1];
                 System.out.println("conclu "+conclusion);
//      list.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
         int result=(premisseRespecter(Double.parseDouble(val),element, cond))?1:0;
                     System.out.println("resulllt alors " +result);
            list.add(result);
             }
     
     if(tailleOu<=1 && !expression.contains("alors"))
     {
         System.out.println("esees"+expression);
         element=expression.substring(0, 3);
         cond=expression.substring(3,5);
         val=expression.substring(5);
             
              int result=(premisseRespecter(Double.parseDouble(val),element, cond))?1:0;
                     System.out.println("resulllt " +result);
            list.add(result);
     }
    else{
      
        ArrayList<Integer> listResultOu=new ArrayList<>();
        for(int j=0;j<tailleOu;j++)  
       {
 
               expr=exprOu[j]; 
         String[] snsAlors=expr.split("alors");
       //    System.out.println(""+snsAlors[snsAlors.length-1]);
        String  elementDroiteAvecAlors=snsAlors[snsAlors.length-1];
       if(expr.contains("alors"))
       {
          expr=snsAlors[0];
       }
             //       System.out.println("entrer dans la condition");
         element="";
         cond="";
         val="";
        conclusion=snsAlors[snsAlors.length-1];
         element=expr.substring(0, 3);
         cond=expr.substring(3,5);
         val=expr.substring(5); 
       listResultOu.add((premisseRespecter(Double.parseDouble(val),element, cond))?1:0);
       }
        int cptOu=0;
        for(Integer res:listResultOu)
        {
        cptOu=cptOu+res;
        }
        list.add((cptOu>=1)?1:0);
       }
   }
      int tailleListe=list.size();
    
      int cpt=0;
       for(Integer ent:list)
       {
         cpt=cpt+ent;
       }
       solutionPrimaire.add((cpt==tailleListe)?1:0);
        list.clear();
      
         }
     int cptvaleur=0;
         System.out.println("solution pmaire: "+solutionPrimaire.size());
     for(Integer valeurSoluPrimaire:solutionPrimaire)
         {
        cptvaleur=cptvaleur+valeurSoluPrimaire;
           System.out.println("solution pmaire: "+valeurSoluPrimaire);
         }
      if(cptvaleur>=1)
       {
      lesFaitsTrouver.add(conclusion);   
      regleApplicable=true;
       }
     }
     //  for(String eleme)
       
       return regleApplicable;
    }
      public void ChainageAvant(ArrayList<String> ensemble_des_regles)
      {  
          //parcours de l'ensemble des regles pour etudier les cas
               for(String regle:ensemble_des_regles)
               {
                 decouperVal(regle,"");
               }
      }
      /**
       * methode qui retourne une chaine concaenée qui donne 
       * le resultat de l'analyse fait
       * @return la chaine qui est en fait l'analyse
       */
      public String donnerResultatConclusion()
      {
       String resultat="";
       String conclusions="";
          System.out.println("nombre fait "+lesFaitsTrouver.size());
         if(lesFaitsTrouver.size()>0)
         {
        for(String element:lesFaitsTrouver)
        {
         String [] elementOu=element.split("OR");
         String [] elementEt=element.split("AND");
 
         if(elementOu.length==1 && elementEt.length==1)
         {
           conclusions=conclusions.concat(retournerConclusion(element));
           conclusions=conclusions.concat("\n");
         }
         else{
             //nous sommes dans le cas ou s'est un cas qui est traité
              String separateur="";
              String concluspartielle="";
              if(elementOu.length>1)
              {
                
                separateur="\n OU  \n";
                for(int i= 0;i<elementOu.length;i++)
                {
                concluspartielle=concluspartielle.concat(retournerConclusion(elementOu[i]));
                if(i<elementOu.length-1)
                {
                concluspartielle=concluspartielle.concat(separateur);
                }
                }
                conclusions=conclusions.concat(concluspartielle).concat("\n\n");
              }
              //nous somme dans le cas ou s'et et qui est traité
              else
              {
               separateur="\n ET  \n";
                String conclusEt="";
                for(int i= 0;i<elementEt.length;i++)
                {
                concluspartielle=concluspartielle.concat(retournerConclusion(elementEt[i]));
                if(i<elementEt.length-1)
                {
                concluspartielle=concluspartielle.concat(separateur);
                }
                }
                  conclusions=conclusions.concat(concluspartielle).concat("\n");
              }
             }
        }
         }
          System.out.println(conclusions);
         this.conclusion=conclusions;
       return conclusions;
      }
      /**
       * methode appeler dans la methode traitant une regle apres separation
       * des elements permettant de le traiter comme les signe >=,<= ...
       * @param valeur la valeur apres l'operateur
       * @param Donnee la donnee qui est l'element ex: vEn pour energie Totale
       * @param cond   la condition qui peut etre > ,< ,<< ,>>,<=,>=,!=,==,
       * @return 
       */
    public boolean premisseRespecter(double valeur,String Donnee,String cond)
       {
           boolean respecte=false;
           double valeurTraiter=-1.0;
      switch (Donnee)
{
  case "aLp":
    valeurTraiter=this.AetLipide;
    break;
  case "aPt":
     valeurTraiter=this.AetProide;
    break;
  case "aGl":
   valeurTraiter=this.AetGlucide;
    break;
  case "pGl":
   valeurTraiter=this.prcentGlucide;
   break;
       case "pLp":
   valeurTraiter=this.prcenLipide;
    break;
     case "pPt":
   valeurTraiter=this.prcentProtide;
    break;       
     case "vEn":
     valeurTraiter=this.EnergieTotale;
     break;    
         case "r10":
      valeurTraiter=this.regime1000;
     break;
     case "r15":
      valeurTraiter=this.regime1500;
     break;
     case "r20":
     valeurTraiter=this.regime2000;
     break;
        
     case "r25":
     valeurTraiter=this.regime2500;
     break;
        
     case "r30":
     valeurTraiter=this.regime3000;
     break;
      
     case "r35":
      valeurTraiter=this.regime3500;
     break;
        
      case "r45":
         valeurTraiter=this.regime4500;
     break;
     case "age":
         valeurTraiter=this.ageClient;
     break;
  
     case "pds": //le poids 
         valeurTraiter=this.poidsClient;
      break;
      
     case "sex": //le poids 
         valeurTraiter=this.sexeClient;
      break;

  default:
    System.out.println("Mrs JYPY");
} 
      if(valeurTraiter>=0)
      {
   respecte=(cond.equals("<<"))?valeurTraiter<valeur:((cond.equals(">>")))?valeurTraiter>valeur:((cond.equals(">=")))?valeurTraiter>=valeur:((cond.equals("<=")))?valeurTraiter<=valeur:((cond.equals("!=")))?valeurTraiter!=valeur:((cond.equals("==")))?valeurTraiter==valeur:false; 
      }
        return respecte;
       }

    public double getAetLipide() {
        return AetLipide;
    }

    public void setAetLipide(double AetLipide) {
        this.AetLipide = AetLipide;
    }

    public double getAetProide() {
        return AetProide;
    }

    public void setAetProide(double AetProide) {
        this.AetProide = AetProide;
    }

    public double getAetGlucide() {
        return AetGlucide;
    }

    public void setAetGlucide(double AetGlucide) {
        this.AetGlucide = AetGlucide;
    }

    public double getPrcentGlucide() {
        return prcentGlucide;
    }

    public void setPrcentGlucide(double prcentGlucide) {
        this.prcentGlucide = prcentGlucide;
    }

    public double getPrcentProtide() {
        return prcentProtide;
    }

    public void setPrcentProtide(double prcentProtide) {
        this.prcentProtide = prcentProtide;
    }

    public double getPrcenLipide() {
        return prcenLipide;
    }

    public void setPrcenLipide(double prcenLipide) {
        this.prcenLipide = prcenLipide;
    }

    public double getRegime1000() {
        return regime1000;
    }

    public void setRegime1000(double regime1000) {
        this.regime1000 = regime1000;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public double getRegime1500() {
        return regime1500;
    }

    public void setRegime1500(double regime1500) {
        this.regime1500 = regime1500;
    }

    public double getRegime2500() {
        return regime2500;
    }

    public ArrayList<String> getLesFaitsTrouver() {
        return lesFaitsTrouver;
    }

    public void setLesFaitsTrouver(ArrayList<String> lesFaitsTrouver) {
        this.lesFaitsTrouver = lesFaitsTrouver;
    }

    public double getAgeClient() {
        return ageClient;
    }

    public void setAgeClient(double ageClient) {
        this.ageClient = ageClient;
    }

    public double getPoidsClient() {
        return poidsClient;
    }

    public void setPoidsClient(double poidsClient) {
        this.poidsClient = poidsClient;
    }

    public double getSexeClient() {
        return sexeClient;
    }

    public void setSexeClient(double sexeClient) {
        this.sexeClient = sexeClient;
    }

    public double getRegime2000() {
        return regime2000;
    }

    public void setRegime2000(double regime2000) {
        this.regime2000 = regime2000;
    }

    public void setRegime2500(double regime2500) {
        this.regime2500 = regime2500;
    }

    public double getRegime3000() {
        return regime3000;
    }

    public void setRegime3000(double regime3000) {
        this.regime3000 = regime3000;
    }

    public double getRegime3500() {
        return regime3500;
    }

    public double getTailleclient() {
        return tailleclient;
    }

    public void setTailleclient(double tailleclient) {
        this.tailleclient = tailleclient;
    }

    public void setRegime3500(double regime3500) {
        this.regime3500 = regime3500;
    }

    public double getRegime4500() {
        return regime4500;
    }

    public void setRegime4500(double regime4500) {
        this.regime4500 = regime4500;
    }

    public double getEnergieTotale() {
        return EnergieTotale;
    }

    public void setEnergieTotale(double EnergieTotale) {
        this.EnergieTotale = EnergieTotale;
    }

    public List<FmFaitConclusion> getListConclusion() {
        return listConclusion;
    }

    public void setListConclusion(List<FmFaitConclusion> listConclusion) {
        this.listConclusion = listConclusion;
    }
    
    /**
     * methode permettant de retourner la conclusions d'une regle
     * @param element l'element qui est la chaine apres "alors"
     * @return une chaine
     */
    private String retournerConclusion(String element) {
        String conclus="";
      for(FmFaitConclusion fait :listConclusion)
      {
        String ligne=fait.getFait();
        Pattern p = Pattern.compile(ligne);
        Matcher m = p.matcher(element);
        if(m.find())
        {
         conclus=fait.getConclusion();
         break;
        }
      }
        
       return conclus;
    }
    
  //public 
  private double AetLipide;
  private double AetProide;
  private double AetGlucide;
  private double prcentGlucide;
  private double prcentProtide;
  private double prcenLipide;
  private double regime1500;
  private double regime2000;
  private double regime2500;
  private double regime3000;
  private double regime3500;
  private double regime4500;
  private double EnergieTotale;
  private double tailleclient;
  List<FmFaitConclusion> listConclusion;
  

}
