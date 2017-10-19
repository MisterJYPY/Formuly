/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.expert;

import java.util.ArrayList;

/**
 *
 * @author Mr_JYPY
 */
public class outilsExpert {
    
       ArrayList<String> lesFaitsTrouver;
    private double ageClient;
    private double poidsClient;
    private double sexeClient;

    public outilsExpert() {
        lesFaitsTrouver=new ArrayList<>();
    }
       
      public boolean decouperVal(String regle)
    {
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
          //   conclusion=snsAlors[snsAlors.length-1];
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
      public void ChainageAvant(ArrayList<String> ensemble_des_regles)
      {  
          //parcours de l'ensemble des regles pour etudier les cas
               for(String regle:ensemble_des_regles)
               {
                 decouperVal(regle);
               }
      }
      public String donnerResultatConclusion()
      {
       String resultat="";
       String conclusion="";
         if(lesFaitsTrouver.size()>0)
         {
        for(String element:lesFaitsTrouver)
        {
         String [] elementOu=element.split("OR");
         String [] elementEt=element.split("AND");
 
         if(elementOu.length==1 && elementEt.length==1)
         {
           conclusion=conclusion.concat(retournerConclusion(element));
           conclusion=conclusion.concat("\n");
         }
         else{
             //nous sommes dans le cas ou s'est un cas qui est traité
             System.out.println("li li ");
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
                conclusion=conclusion.concat(concluspartielle);
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
                  conclusion=conclusion.concat(concluspartielle);
              }
             }
        }
         }
       return conclusion;
      }
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

    private String retournerConclusion(String element) {
       String conclusion="YALA AUCUN";
       return conclusion;
    }
    
}
