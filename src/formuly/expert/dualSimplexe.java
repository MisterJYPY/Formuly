/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.expert;


/**
 *classe permettant d'executer la methode du dual simplexe
 * @author Mr_JYPY
 */
public class dualSimplexe extends Simplexe{

    public dualSimplexe(int nbreLignes, int nbreColonnes) {
        super(nbreLignes, nbreColonnes);
    }

    public dualSimplexe(int nbreLigne, int nbreColonne, double[][] MatrixNouveau) {
        super(nbreLigne, nbreColonne, MatrixNouveau);
    }

    public dualSimplexe(double[][] MatrixNouveau) {
        super(MatrixNouveau);
    }
    /**
     * methode de resolution de l'algorithme du dual
     * polymorphisme de la methode algorithmeSimplex del la classe simplexe pour le dual
     */
    @Override
 public void algorithmeSimplexe()
    {
      //determination de la colonne pivot
        intitTableEnBase();
        boolean arret=false;
        int cmptIteration=1;
         for(int k=0;k<nbreLigne;k++)
        {
         for(int j=0;j<nbreColonne;j++)
         {
             System.out.print(" "+MatrixNouveau[k][j]);
         }
            System.out.println("");
        }
     while(!arret)
      {
//          System.out.println("hd");
//          System.out.println("ligne pivo : "+lignePivot);
         lignePivot=retournerLignPivot();
       System.out.println("ligne pivo : "+lignePivot);
       //
       if(lignePivot>=0)
       {
       //determinaion du pivot de la 
       colonnePivot=retourneColPivot();
        //text si il a retrouver une ligne valide de pivot
        if(colonnePivot>=0)
        {
          Currentpivot=MatrixNouveau[lignePivot][colonnePivot];
          int colEnbase=colonnePivot+1;
          enBase[lignePivot]="X"+colEnbase;
       System.out.println("ligne pivot :"+lignePivot);
     System.out.println("colonne pivot :"+colonnePivot);
        System.out.println("pivot : "+Currentpivot);
              calculateursInterne();
            System.out.println("****************** iteration "+cmptIteration+"**************");
          for(int k=0;k<nbreLigne;k++)
        {
         for(int j=0;j<nbreColonne;j++)
         {
             System.out.print(" "+MatrixSuivant[k][j]);
         }
            System.out.println("");
        }
          System.out.println("****************** fin it "+cmptIteration+"**************");
         arret=conditionArret();
       if(!arret)
       {
        MatrixNouveau=new double[nbreLigne][nbreColonne];
         intialiserMatriceNvo();
       } 
       //remplissage des elements matrixSuivante
     }
        else{
         System.out.println("solution a +Infini dela colonne "); 
       arret=true;
        }
       }
       else{
       System.out.println("solution a +Infini ligne pivot"); 
       arret=true;
       }
       cmptIteration++;
      }
      for(int m=0;m<nbreColonne;m++)
       {
       System.out.print("en base  "+enBase[m]);
       }
    }
 /**
  * la condition d'arret
  * @return 
  */
    @Override
  public boolean conditionArret()
    {
     boolean arret=true;
     for(int i=0;i<nbreLigne;i++)
     {
       if(MatrixSuivant[i][nbreColonne-1]<0)
       {
        arret=false;
        break;
       }
     }
     return arret;
    }
    @Override
    /**
     * retourne la ligne d'un pivot dans le cas d'un dual
     * polymorphisme utilisé
     */
     public int retournerLignPivot()
      {
           double val=0;
         int LignePivot=-1111;
    for(int i=0;i<nbreLigne-1;i++)
    {
     if(MatrixNouveau[i][nbreColonne-1]<val)  
     {
        LignePivot=i;
     val=MatrixNouveau[i][nbreColonne-1];
     }
  
    }
    return LignePivot; 
      }
     /**
      * return la ligne
      * @return un entier
      */
    @Override
      public int retourneColPivot()
    {
         int colPivot=-1111;
          // System.out.println("coll coll");
       double [] tabResultat=new double[nbreColonne];
       int [] indiceResultat=new int[nbreColonne];
       int compteurCalcul=0;
     if(cas.equals("MIN"))
     {
      for(int i=0;i<nbreColonne-1;i++)
       {
       //    System.out.println("coll coll : "+MatrixNouveau[lignePivot][i]);
        if(MatrixNouveau[lignePivot][i]<0)
        {
        //System.out.println("valeur sdds : "+MatrixNouveau[lignePivot][i]);
        double resultPartielle=(MatrixNouveau[nbreLigne-1][i]/MatrixNouveau[lignePivot][i]);
        tabResultat[compteurCalcul]=resultPartielle;
        indiceResultat[compteurCalcul]=i;
        compteurCalcul++;
        }
       }
      //determination du plus petit parmis 
       double plusgrandNegativement=tabResultat[0]; 
     //  indiceResultat[0]=-1111;
       int indicePetit=indiceResultat[0];
     //   System.out.println("compteur calcul : "+compteurCalcul);
       for(int j=0;j<compteurCalcul;j++)
       {
     //  System.out.println("valer table : "+tabResultat[j]);
           //interchangement
        if(tabResultat[j]>plusgrandNegativement)
        {
        plusgrandNegativement=tabResultat[j];
        indicePetit=indiceResultat[j];
        }
       }
     
       colPivot=indicePetit;
     } 
      if(cas.equals("MAX"))
     {
      for(int i=0;i<nbreColonne-1;i++)
       {
       //    System.out.println("coll coll : "+MatrixNouveau[lignePivot][i]);
        if(MatrixNouveau[lignePivot][i]<0)
        {
        //System.out.println("valeur sdds : "+MatrixNouveau[lignePivot][i]);
        double resultPartielle=(MatrixNouveau[nbreLigne-1][i]/MatrixNouveau[lignePivot][i]);
        tabResultat[compteurCalcul]=resultPartielle;
        indiceResultat[compteurCalcul]=i;
        compteurCalcul++;
        }
       }
      //determination du plus petit parmis 
       double pluspetit=tabResultat[0]; 
     //  indiceResultat[0]=-1111;
       int indicePetit=indiceResultat[0];
     //   System.out.println("compteur calcul : "+compteurCalcul);
       for(int j=0;j<compteurCalcul;j++)
       {
     //  System.out.println("valer table : "+tabResultat[j]);
           //interchangement
        if(tabResultat[j]<pluspetit)
        {
        pluspetit=tabResultat[j];
        indicePetit=indiceResultat[j];
        }
       }
     
       colPivot=indicePetit;
     } 
       
       //   System.out.println("piv : "+Currentpivot);
       return colPivot;
    }
   
  
}
