/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.expert;


/**
 *
 * @author Mr_JYPY
 */
public class Simplexe {  
    /**
     * stipule le cas .permet de preciser quel algorithme on veut lancer
     */
     public static String cas="MIN";
     /**
      * l'attribut qui stocke pour chaque iteration la ligne pivot
      * elle est tres utile pour le calcul dans le tableau du simplexe
      */
     protected int lignePivot;
     /**
      * la colonne du pivot
      */
     protected int colonnePivot;
     /**
      * le nombre de lignedu tableau stockant les variables de la forme standard
      */
     protected int nbreLigne;
     /**
      * le nombre de colonne du tableau stockant les variables de la forme standard
      */
     protected int nbreColonne;
     /**
      * variable qui stocke la variable du pivot courant
      */
     protected double Currentpivot;
     /**
      * la matrix qui represente notre matrice pour le calcul pour une nouvelle iteration
      */
     protected double [][] MatrixSuivant;
     /**
      * matrix de depart qui est initialiser a chaque fois qu'il y a un
      *nouveau calcul
      */
     protected double [][] MatrixNouveau;
     /**
      * le vecteur qui stok les variables en base en base du programme
      */
     protected String [] enBase;
     /**
      * variable non en base
      */
     protected String [] nonEnBase;
     /**
      * une variable qui pointe sur l'obdjet dual 
      * et qui est appeler lorsque nous constatons que le dual peut etre declencher
      */
     private dualSimplexe dual;
     /**
      * variable qui nous dira si au cour de la resolution le dual a ete lancer ou pas
      */
    protected boolean dualLncer=true;
/**
 * constructeur parametré du simplexe
 * @param nbreLignes le nombre de lignes du tableau
 * @param nbreColonnes le nombre de colonnes du tableau
 */
    public Simplexe(int nbreLignes,int nbreColonnes) {
        
      this.nbreColonne=nbreColonnes;
      this.nbreLigne=nbreLignes;
       enBase=new String[nbreColonne];
        nonEnBase=new String[nbreColonne];
      MatrixSuivant=new double[nbreLigne][nbreColonne];
      MatrixNouveau=new double[nbreLigne][nbreColonne];  
    }
    /**
     * le constructeur qui prends en parametre seulement la matrice de depart
     * @deprecated à eviter l'instance.il n'est pas utiliser pour ce cour de programme
     * @param MatrixNouveau 
     */
    public Simplexe(double[][] MatrixNouveau) {
        this.MatrixNouveau = MatrixNouveau;
    }
/**
 * constructeur du simplexe
 * @param nbreLigne le nombre de ligne
 * @param nbreColonne le nombre de colonne
 * @param MatrixNouveau la matrix d'origine sous forme de tableau a double dimension
 */
    public Simplexe(int nbreLigne, int nbreColonne, double[][] MatrixNouveau) {
        this.nbreLigne = nbreLigne;
        this.nbreColonne = nbreColonne;
        this.MatrixNouveau = MatrixNouveau;
        enBase=new String[nbreLigne];
        nonEnBase=new String[nbreColonne];
        MatrixSuivant=new double[nbreLigne][nbreColonne];
        dual=new dualSimplexe(nbreLigne, nbreColonne);
    }
    /**
     * initialisation des variables en base
     */
    public void intitTableEnBase()
    {
        int val=nbreColonne;
    for(int i=0;i<nbreColonne;i++)
     {
      enBase[i]="X"+val;
      val++;
     }
    }
    /**
     * initialisation des variables hors base
     */
    public void initHorsBase()
    {
        int val=nbreColonne;
    for(int i=0;i<nbreColonne-1;i++)
     {
      int cpt=i+1;
      nonEnBase[i]="X"+cpt;
     }
    }
    /**
     * l'algorithme du simplexe a proprement dit
     * @return 
     */
     public boolean MatrixSuivantNul()
     {
       boolean estNul=true;
       for(int i=0;i<nbreLigne;i++)
     {
      for(int j=0;j<nbreColonne;j++)
     { 
      if(MatrixSuivant[i][j]!=0.0)
      {
        estNul=false;
        break;
      }
     }
     }
       return estNul;
     }
     /**
      * initialisation a zero de la matrice acceuillant les resultats de calcul
      * la matrix nouveau
      */
    public void intialiserMatriceNvo()
    {
      //copie des valeurs
   if(!MatrixSuivantNul())
   {
      for(int i=0;i<nbreLigne;i++)
     {
      for(int j=0;j<nbreColonne;j++)
     {
      MatrixNouveau[i][j]=0.0;
  
     }
     }
     for(int i=0;i<nbreLigne;i++)
     {
      for(int j=0;j<nbreColonne;j++)
     {
      MatrixNouveau[i][j]=MatrixSuivant[i][j];
  
     }
     }
   }
     //mise a null des elements de la matrix suivante
      for(int i=0;i<nbreLigne;i++)
     {
      for(int j=0;j<nbreColonne;j++)
     {
      MatrixSuivant[i][j]=0.0;
     }
     }
    }
    /**
     * initioalisation de la matrice depuis le dual
     */
     public void intialiserMatriceNvoDepuisLeDual()
    {
     //mise a null des elements de la matrix suivante
      for(int i=0;i<nbreLigne;i++)
     {
      for(int j=0;j<nbreColonne;j++)
     {
      MatrixNouveau[i][j]=dual.MatrixNouveau[i][j];
     }
     }
    }
     /**
      * lance l'algorithme du simplexe a proprement dit
      * methode:
      * <p>au deroulement de l'algorithme une verification si nous sommes deja a l'optimum pour lancer le 
      * dual si possible . ensuite le pivot est determiné et les calculs internes sont determinés par la methode calculInterieus</p>
      */
    public void algorithmeSimplexe()
    {
      //determination de la colonne pivot
        intitTableEnBase();
        initHorsBase();
        boolean arret=false;
//     // double [][] mat=retournerDual(MatrixNouveau);
//        System.out.println("*********origine**********");
//        afficherContentMatrix(MatrixNouveau);
//        System.out.println("*********dual**********");
//      //     afficherContentMatrix(mat);
//         System.out.println("");
         System.out.println("********* Resultats du deroulement **********");
         System.out.println("");
   if(!verifierOptimalibilite())
       {
      while(!arret)
      {
       //   System.out.println("hd");
       colonnePivot=retourneColPivot();
     //  System.out.println("colonne pivo : "+colonnePivot);
       //
       if(colonnePivot!=-1111)
       {
       //determinaion du pivot de la 
        lignePivot=retournerLignPivot();
        //text si il a retrouver une ligne valide de pivot
        if(lignePivot!=-1111)
        {
          Currentpivot=MatrixNouveau[lignePivot][colonnePivot];
          int colEnbase=colonnePivot+1;
          enBase[lignePivot]="X"+colEnbase;
         calculateursInterne();
         arret=conditionArret();
         for(int i=0;i<nbreLigne;i++)
        {
         for(int j=0;j<nbreColonne;j++)
         {
             System.out.print(" "+MatrixSuivant[i][j]);
         }
            System.out.println("");
        }
       if(!arret)
       {
         intialiserMatriceNvo();
       } 
     }
        else{
         System.out.println("solution a +Infini "); 
       arret=true;
        }
       }
       else{
       System.out.println("solution a +Infini "); 
       arret=true;
       }
      }
       }
    boolean declencheDual= verifierSolutionPosiif();
         if(!declencheDual)
         {  
           dualLncer=true;
         dual.MatrixNouveau=MatrixNouveau;
         dual.enBase=enBase;
         dual.nonEnBase=nonEnBase;
         dual.algorithmeSimplexe();
         intialiserMatriceNvoDepuisLeDual();
         MatrixNouveau=dual.MatrixNouveau;
         enBase=dual.enBase;
         }
        
      for(int m=0;m<nbreColonne;m++)
       {
       System.out.print("en base  "+enBase[m]);
       }
       double[] res=null;
    
       if(!declencheDual)
       {
         res=extractionResult(dual.MatrixSuivant);
       }
       else{
       res=extractionResult(MatrixSuivant);
       }
      System.out.println("");
      for(int cpt=0;cpt<res.length;cpt++)
      {
       int nmro=cpt+1;
       System.out.println("X"+nmro+" : "+res[cpt]);
      }
    }
    /**
     * verifie si l'optimum est atteint
     * @return un boolean
     */
    public boolean conditionArret()
    {
     boolean arret=true;
     if(cas.equals("MIN"))
             {
     for(int i=0;i<nbreColonne-1;i++)
     {
       if(MatrixSuivant[nbreLigne-1][i]<0)
       {
        arret=false;
        break;
       }
     }
             }
      if(cas.equals("MAX"))
             {
     for(int i=0;i<nbreColonne;i++)
     {
       if(MatrixSuivant[nbreLigne-1][i]>0)
       {
        arret=false;
        break;
       }
     }
             }
     return arret;
    }
    /**
     * la methode qui permet de faire le calcul du simplexe 
     * pour determiner les resultats de calcul de la methode du simplexe
     */
      public void calculateursInterne()
      {
        //la ligne pivot recois son inverse 
        MatrixSuivant[lignePivot][colonnePivot]=1/MatrixNouveau[lignePivot][colonnePivot];
       //   System.out.println("valeur : "+MatrixSuivant[lignePivot][colonnePivot]);
        //remplissage de la colonne du pivot
          for(int i=0;i<nbreColonne;i++)
           {
          if(i!=colonnePivot) 
          {
            // System.out.println("opeation AA: ("+MatrixNouveau[lignePivot][i]+"/"+Currentpivot+")");
          double val = MatrixNouveau[lignePivot][i]/Currentpivot;
          MatrixSuivant[lignePivot][i] =val;
        //  System.out.println("opeation ss: ("+MatrixNouveau[lignePivot][i]+"/"+Currentpivot+")="+val);
          }
        //remplissage de la ligne du pivot
        for(int j=0;j<nbreLigne;j++)
           {
          if(j!=lignePivot) 
          {
          MatrixSuivant[j][colonnePivot] =-(MatrixNouveau[j][colonnePivot]/Currentpivot);
           //  System.out.println("opeation ss: ("+MatrixNouveau[lignePivot][i]+"/"+Currentpivot+")="+ MatrixSuivant[lignePivot][i]);
          }
           }
      }
         //remplissage des autres elements
          for(int lg=0;lg<nbreLigne;lg++)
          {
              for(int col=0;col<nbreColonne;col++)
              {
                if(col!=colonnePivot && lg!=lignePivot)
                {
                 //   System.out.println("operation effectuées :"+MatrixNouveau[lg][col]+" -(("+MatrixNouveau[lg][colonnePivot]+" * "+MatrixNouveau[lignePivot][col]+" )/"+Currentpivot+"))");
      //  MatrixSuivant[lg][col]=Currentpivot-(MatrixNouveau[lg][col]*(MatrixNouveau[lignePivot][col]/MatrixNouveau[lg][colonnePivot])); 
           MatrixSuivant[lg][col]=MatrixNouveau[lg][col]-((MatrixNouveau[lg][colonnePivot]*MatrixNouveau[lignePivot][col])/Currentpivot); 
                }
              }
          }
      }
      /**
       * permet de verifier si les resultats optenu apres le simplexe sont tous 
       * positif pour verifier si le dual peut etre lancé
       * @return un boolean
       */
      public boolean verifierSolutionPosiif()
      {
        boolean estOptimalPositivement=true;
        for(int i=0;i<nbreLigne-1;i++)
        {
          if(MatrixNouveau[i][nbreColonne-1]<0)
          {
          estOptimalPositivement=false;
          }
        }
        return estOptimalPositivement;
      }
      /**
       * methode qui retourne la colonne du 
       * @return l'entier
       */
      public int retourneColPivot()
      {
         double val=0;
         int colpivot=-1111;
     if(cas.equals("MIN"))
        {
    for(int i=0;i<nbreColonne;i++)
    {
     if(MatrixNouveau[nbreLigne-1][i]<val)  
     {
        colpivot=i;
        val=MatrixNouveau[nbreLigne-1][i];
     }
    }
    }
      if(cas.equals("MAX"))
        {
    for(int i=0;i<nbreColonne;i++)
    {
     if(MatrixNouveau[nbreLigne-1][i]>val)  
     {
        colpivot=i;
        val=MatrixNouveau[nbreLigne-1][i];
     }
    }
    }
         return colpivot;
    }
      /**
       * methode qui retourne la ligne du pivot dans le cas 
       * d'une resolution simplexe
       * @return un entier
       */
      public int retournerLignPivot()
      {
       int lignpvot=-1111;
       double [] tabResultat=new double[nbreColonne];
       int [] indiceResultat=new int[nbreColonne];
       int compteurCalcul=0;
//     if(cas.equals("MIN"))
//     {
      for(int i=0;i<nbreLigne-1;i++)
       {
          //      System.out.println("colonne "+MatrixNouveau[i][colonnePivot]); 
        if(MatrixNouveau[i][colonnePivot]>0)
        {     
        double resultPartielle=(MatrixNouveau[i][nbreColonne-1]/MatrixNouveau[i][colonnePivot]);
        tabResultat[compteurCalcul]=resultPartielle;
        indiceResultat[compteurCalcul]=i;
        compteurCalcul++;
        }
       }
       double plusPetit=tabResultat[0]; 
       int indicePetit=indiceResultat[0];
       for(int j=0;j<compteurCalcul;j++)
       {
        if(tabResultat[j]<plusPetit)
        {
        plusPetit=tabResultat[j];
        indicePetit=indiceResultat[j];
        }
       }
       lignpvot=indicePetit;
       return lignpvot;
      }
      /**
       * cette methode est appelé pour verifier si le probleme initial est deja optimale
       * avec les valeurs intial par defaut (0,0,0,...) 
       * cela permet de verifier si la methode de resolution dual doit etre utilisé
       * @return un boolean
       */
      public boolean verifierOptimalibilite()
      {
       boolean estoptimale=true;
       double val=0;
     if(cas.equals("MIN"))
        {
    for(int i=0;i<nbreColonne-1;i++)
    {
     if(MatrixNouveau[nbreLigne-1][i]<val)  
     {
         estoptimale=false;
     }
    }
        }
     if(cas.equals("MAX"))
        {
         //pour 
    for(int i=0;i<nbreColonne-1;i++)
    {
       //si il y a une valeur positive
     if(MatrixNouveau[nbreLigne-1][i]>val)  
      {
         estoptimale=false;
      }
    }
      }
     return estoptimale;
      }
      /**
       * permet d'extraire le resultat de la resolution
       * de l'algorithme du simplexe
       * @param matrix la matrix qui stock le resultat de l'execution
       * @return un vecteur
       */
      public double[] extractionResult(double [][] matrix)
      {
       double [] resultat=new double[nbreColonne];
         //System.out.println("");
        
         for(int i=0;i<nbreColonne-1;i++)
          {
            //recuperation de la variable i
          String variable=nonEnBase[i];
               int emplacement=Integer.valueOf(variable.substring(1));
          int pos=postionResult(variable);
             // System.out.println("var : "+variable);
            //  System.out.println("pos : "+pos);
            if(pos==-1)
            {
            resultat[emplacement-1]=0.0;
            }
            else{
               // System.out.println("element "+matrix[pos][nbreColonne-1]);
           resultat[emplacement-1]=matrix[pos][nbreColonne-1];
    
            }
          }
            //on enregistre la valeur de Z 
         resultat[nbreColonne-1]=matrix[nbreLigne-1][nbreColonne-1];
        return resultat;
      }
      /**
       * utile pour l'extraction du resultat 
       * cette methode est utilisé dans l'extraction d'un resultat
       * @param var
       * @return 
       */
      private int postionResult(String var)
      {
        int pos=-1;
         for(int i=0;i<nbreColonne;i++)
         {
            // System.out.println(enBase[i]);
           if(var.equals(enBase[i]))
           {
            pos=i;
           }
         }
        return pos;
      }
      /**
       * affiche le contenu d'une matrix
       * @param matrix la matrix
       */
      public void afficherContentMatrix(double [][] matrix)
      {
             for(int k=0;k<nbreLigne;k++)
     {
      for(int l=0;l<nbreColonne;l++)
     {
         System.out.print("  "+matrix[k][l]);
     }
         System.out.println(" ");
     }
      }
    /**
     * retourne le dual d'une matrix qui n'est autre que la transposée de la matrix
     * @param matrix la matrix en question
     * @return une matrix 
     */
      public double[][] retournerDual(double [][] matrix)
      {
        double [][] MatrixDual=new double[nbreColonne][nbreLigne];
      //enregistrement des elements de la ligne-1 et la ligne-2
        for(int i=0;i<nbreLigne;i++)
        {
        for(int j=0;j<nbreColonne;j++)
        {
            double element=matrix[i][j];
            MatrixDual[j][i]=element;
        }
        }
        
         return MatrixDual;
      }

    public double[][] getMatrixNouveau() {
        return MatrixNouveau;
    }

    public void setMatrixNouveau(double[][] MatrixNouveau) {
        this.MatrixNouveau = MatrixNouveau;
    }
      
}