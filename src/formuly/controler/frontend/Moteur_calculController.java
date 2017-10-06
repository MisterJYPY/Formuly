/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import Jama.Matrix;
import formuly.classe.formulyTools;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */

public class Moteur_calculController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label resProtide;
    @FXML
    private Label resx2;
    @FXML
    private Label resx3;
    @FXML
    private TextField q31;
    @FXML
    private Label resx1;
    @FXML
    private TextField b1;
    @FXML
    private TextField q11;
    @FXML
    private TextField q22;
    @FXML
    private TextField q33;
    @FXML
    private TextField b2;
    @FXML
    private TextField q21;
    @FXML
    private TextField q32;
    @FXML
    private TextField b3;
    @FXML
    private TextField q13;
    @FXML
    private TextField q12;
    @FXML
    private TextField q23;
    @FXML
    private Label resGlucide;
    @FXML
    private Label resLipide;
    @FXML private Button lancer;
    @FXML private Label residu;
    @FXML private ImageView logoProtide;
    @FXML private ImageView logoLipide;
    @FXML private ImageView logoGlucide;
    @FXML private Label labelInfoResidu;
    
    @FXML private Button reload;
    @FXML
    private TextField valeurFixerLipide;
    @FXML
    private TextField valeurFixerProtide;
    @FXML
    private TextField valeurFixerGlucide;
    @FXML
    private TextField valeurFixerEnergie;
    @FXML private Label infoResult;
     
    private double lipideFixe;
    private double protideFixe;
    private double GlucideFixe;

    public Moteur_calculController() {
          Resultas=new ArrayList<>();
          Resultass=new ArrayList<>();
          lipideFixe=0.0;
          GlucideFixe=0.0;
          protideFixe=0.0;
          fixLip=false;
          fixPr=false;
          fixGl=false;
          fixEnergie=false;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textConverter(q11,q12,q13,q21,q22,q23,q31,q32,q33,b1,b2,b3,valeurFixerGlucide,valeurFixerLipide,valeurFixerProtide,valeurFixerEnergie);
        
        lancer.setOnAction(event->{
        faireCalcul();
        });
         valeurFixerEnergie.setOnKeyReleased(event->{
          actionRapideEnergie();
          });
         valeurFixerLipide.setOnKeyReleased(event->{
          actionRapideLipide();
          });
            valeurFixerGlucide.setOnKeyReleased(event->{
          actionRapideGlucide();
          });
             valeurFixerProtide.setOnKeyReleased(event->{
        actionProtide();
          });
       reload.setOnAction(event->{
            ToutReinitialiser();
            });
         Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/actualiser.jpg"));
         reload.setGraphic(new ImageView(imageSucces));
            
    }    
   public void  ToutReinitialiser()
    {
    formulyTools.initialiserLesTextFieldInfoAliment(b1,b2,b3,valeurFixerEnergie,valeurFixerGlucide,valeurFixerLipide,valeurFixerProtide,q11,q12,q13,q21,q22,q23,q31,q32,q33);
    formulyTools.initialiserLabelInfoAliment(labelInfoResidu,resGlucide,resLipide,resProtide,residu,resx1,resx2,resx3,infoResult);
    fixEnergie=false;
    fixGl=false;
    fixPr=false;
    fixLip=false;
    logoGlucide.setImage(null);
    logoProtide.setImage(null);
    logoLipide.setImage(null);
    }
    public void textConverter(TextField...textField)
    {
    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

UnaryOperator<TextFormatter.Change> filter = c -> {
    String text = c.getControlNewText();
    if (validEditingState.matcher(text).matches()) {
        return c ;
    } else {
        return null ;
    }
};

StringConverter<Double> converter = new StringConverter<Double>() {

    @Override
    public Double fromString(String s) {
        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
            return 0.0 ;
        } else {
            return Double.valueOf(s);
        }
    }


    @Override
    public String toString(Double d) {
        return d.toString();
    }
};


  for(TextField tf: textField)
  {
  TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
  tf.setTextFormatter(textFormatter);
  }
    
    }
    private Matrix dessinnerMatrice()
  {
        double x11=Double.valueOf(q11.getText());
        double x12=Double.valueOf(q12.getText());
        double x13=Double.valueOf(q13.getText());
        double x21=Double.valueOf(q21.getText());
        double x22=Double.valueOf(q22.getText());
        double x23=Double.valueOf(q23.getText());
        double x31=Double.valueOf(q31.getText());
        double x32=Double.valueOf(q32.getText());
        double x33=Double.valueOf(q33.getText());
        //valeur pour le b
        double v1=Double.valueOf(b1.getText());
        double v2=Double.valueOf(b2.getText());
        double v3=Double.valueOf(b3.getText());
        
        //constitution de la matrice
        
        
     Matrix x=null;
     if(lipideFixe==0.0 && GlucideFixe==0.0 && protideFixe==0.0)
     {
       double[][] array= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33}};
        x = new Matrix(array);
          double[] col={v1,v2,v3};
     }
     if(lipideFixe==0 && GlucideFixe==0.0 && protideFixe>0.0) 
         {
      double[][] array1= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33},{0,0,1}};
         x = new Matrix(array1);
        double[] col1={v1,v2,v3,protideFixe};
         }
          if(lipideFixe==0 && GlucideFixe>0.0 && protideFixe==0.0) 
         {
      double[][] array2= {{x11,x12,x13,0},{x21,x22,x23,0},{x31,x32,x33,0},{0,1,0,0}};
         x = new Matrix(array2);
         double[] col2={v1,v2,v3,GlucideFixe};
         }
           if(lipideFixe==0 && GlucideFixe>0.0 && protideFixe>0.0) 
         {
      double[][] array3= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{0,1,0,0,0},{0,0,1,0,0}};
         x = new Matrix(array3);
           double[] col3={v1,v2,v3,GlucideFixe,protideFixe};
         }
            if(lipideFixe>0 && GlucideFixe==0.0 && protideFixe==0.0) 
         {
     double[][] array4= {{x11,x12,x13,0},{x21,x22,x23,0},{x31,x32,x33,0},{1,0,0,0}};
        x = new Matrix(array4);
         double[] col4={v1,v2,v3,lipideFixe};
         }
          if(lipideFixe>0 && GlucideFixe==0.0 && protideFixe>0.0) 
         {
   double[][] array5= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{1,0,0,0,0},{0,0,1,0,0}};
    x = new Matrix(array5);
       double[] col5={v1,v2,v3,lipideFixe,protideFixe};
         }
            if(lipideFixe>0 && GlucideFixe>0.0 && protideFixe==0.0) 
         {
   double[][] array6= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{1,0,0,0,0},{0,1,0,0,0}};
    x = new Matrix(array6);
       double[] col6={v1,v2,v3,lipideFixe,GlucideFixe};
         }
                if(lipideFixe>0 && GlucideFixe>0.0 && protideFixe>0.0) 
         {
   double[][] array7= {{x11,x12,x13,0,0,0},{x21,x22,x23,0,0,0},{x31,x32,x33,0,0,0},{1,0,0,0,0,0},{0,1,0,0,0,0},{0,0,1,0,0,0}};
       x = new Matrix(array7);
   double[] col7={v1,v2,v3,lipideFixe,GlucideFixe,protideFixe};
         }
     return x;
  }
    private Matrix dessinnerColonneDroite()
  {
        double x11=Double.valueOf(q11.getText());
        double x12=Double.valueOf(q12.getText());
        double x13=Double.valueOf(q13.getText());
        double x21=Double.valueOf(q21.getText());
        double x22=Double.valueOf(q22.getText());
        double x23=Double.valueOf(q23.getText());
        double x31=Double.valueOf(q31.getText());
        double x32=Double.valueOf(q32.getText());
        double x33=Double.valueOf(q33.getText());
        //valeur pour le b
        double v1=Double.valueOf(b1.getText());
        double v2=Double.valueOf(b2.getText());
        double v3=Double.valueOf(b3.getText());
        
        //constitution de la matrice
        
        
     Matrix x=null;
     if(lipideFixe==0.0 && GlucideFixe==0.0 && protideFixe==0.0)
     {
       double[][] array= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33}};
          double[] col={v1,v2,v3};
          x =new Matrix(col, 3);
     }
     if(lipideFixe==0 && GlucideFixe==0.0 && protideFixe>0.0) 
         {
      double[][] array1= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33},{0,0,1}};
        double[] col1={v1,v2,v3,protideFixe};
         x =new Matrix(col1, 4);
         }
          if(lipideFixe==0 && GlucideFixe>0.0 && protideFixe==0.0) 
         {
      double[][] array2= {{x11,x12,x13,0},{x21,x22,x23,0},{x31,x32,x33,0},{0,1,0,0}};
         double[] col2={v1,v2,v3,GlucideFixe};
             x =new Matrix(col2, 4);
         }
           if(lipideFixe==0 && GlucideFixe>0.0 && protideFixe>0.0) 
         {
      double[][] array3= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{0,1,0,0,0},{0,0,1,0,0}};
           double[] col3={v1,v2,v3,GlucideFixe,protideFixe};
               x =new Matrix(col3, 5);
         }
            if(lipideFixe>0 && GlucideFixe==0.0 && protideFixe==0.0) 
         {
     double[][] array4= {{x11,x12,x13,0},{x21,x22,x23,0},{x31,x32,x33,0},{1,0,0,0}};
         double[] col4={v1,v2,v3,lipideFixe};
             x =new Matrix(col4, 4);
         }
          if(lipideFixe>0 && GlucideFixe==0.0 && protideFixe>0.0) 
         {
   double[][] array5= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{1,0,0,0,0},{0,0,1,0,0}};
       double[] col5={v1,v2,v3,lipideFixe,protideFixe};
           x =new Matrix(col5, 5);
         }
            if(lipideFixe>0 && GlucideFixe>0.0 && protideFixe==0.0) 
         {
   double[][] array6= {{x11,x12,x13,0,0},{x21,x22,x23,0,0},{x31,x32,x33,0,0},{1,0,0,0,0},{0,1,0,0,0}};
       double[] col6={v1,v2,v3,lipideFixe,GlucideFixe};
           x =new Matrix(col6, 5);
         }
                if(lipideFixe>0 && GlucideFixe>0.0 && protideFixe>0.0) 
         {
   double[][] array7= {{x11,x12,x13,0,0,0},{x21,x22,x23,0,0,0},{x31,x32,x33,0,0,0},{1,0,0,0,0,0},{0,1,0,0,0,0},{0,0,1,0,0,0}};
   double[] col7={v1,v2,v3,lipideFixe,GlucideFixe,protideFixe};
       x =new Matrix(col7, 6);
         }
     return x;
  }
    
    private void faireCalcul() {
        //valeur pour la matrice a
        Resultas.clear();
         Resultas.clear();
        double x11=Double.valueOf(q11.getText());
        double x12=Double.valueOf(q12.getText());
        double x13=Double.valueOf(q13.getText());
        double x21=Double.valueOf(q21.getText());
        double x22=Double.valueOf(q22.getText());
        double x23=Double.valueOf(q23.getText());
        double x31=Double.valueOf(q31.getText());
        double x32=Double.valueOf(q32.getText());
        double x33=Double.valueOf(q33.getText());
        //valeur pour le b
        double v1=Double.valueOf(b1.getText());
        double v2=Double.valueOf(b2.getText());
        double v3=Double.valueOf(b3.getText());
        
        //constitution de la matrice
         double[][] array= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33}};
           double[][] arrays= {{x11,x12,x13},{x21,x22,x23},{x31,x32,x33}};
        //  double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8,10.}};
         Matrix A = dessinnerMatrice();
         Matrix AA = new Matrix(array);
         double[] col={v1,v2,v3};
       Matrix b =dessinnerColonneDroite();
            double detA=  A.det();
         if(detA!=0.0)
          {
        for(int i=0;i<4;i++)
        {   
        Matrix x = A.solve(b);
        Matrix Residual = A.times(x).minus(b);
       double rnorm = Residual.normInf();
       double res1=x.get(0, 0);
       double res2=x.get(1, 0);
       double res3=x.get(2, 0);
       ArrayList<Double> result=new ArrayList<>();
          result.add(res1);
          result.add(res2);
          result.add(res3);
          result.add(rnorm);
          Resultas.add(result);
        }    
        resx1.setText(String.valueOf(Resultas.get(0).get(0)));
        resx2.setText(String.valueOf(Resultas.get(0).get(1)));
        resx3.setText(String.valueOf(Resultas.get(0).get(2)));
         resLipide.setText(String.valueOf(Resultas.get(0).get(0)*9));
        resGlucide.setText(String.valueOf(Resultas.get(0).get(1)*4));
        resProtide.setText(String.valueOf(Resultas.get(0).get(2)*4));
        residu.setText(String.valueOf(Resultas.get(0).get(3)));
        labelInfoResidu.setText("Résidu :");
     double resltLipide=(Resultas.get(0).get(0)>=0)?Resultas.get(0).get(0)*9:0;
     double resltGlucide=(Resultas.get(0).get(1)>=0)?Resultas.get(0).get(1)*4:0;
     double resltProtide=(Resultas.get(0).get(2)>=0)?Resultas.get(0).get(2)*4:0;
     double somme=resltGlucide+resltLipide+resltProtide;
         if(fixEnergie)
         {
        infoResult.setText("Resultats Energetiques :"+somme);
         }
           Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
            Image imageWarning = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
        if(Resultas.get(0).get(0)>=0)
         {
        logoLipide.setImage(imageSucces);
         }
       else{
         logoLipide.setImage(imageWarning);
         }
         if(Resultas.get(0).get(1)>=0)
         {
        logoGlucide.setImage(imageSucces);
         }
       else{
         logoGlucide.setImage(imageWarning);
         }
          if(Resultas.get(0).get(2)>=0)
         {
        logoProtide.setImage(imageSucces);
         }
       else{
         logoProtide.setImage(imageWarning);
         }
        //afficah
//              System.out.println("*****resultat 1*******");
//        for(int i=0;i<9;i++)
//        {
//            System.out.println(Resultas.get(i).get(0)+" "+Resultas.get(i).get(1)+" "+Resultas.get(i).get(2)+" "+Resultas.get(i).get(3));
//        
//        }
//          System.out.println("*****resultat 2*******");
//        for(int i=0;i<9;i++)
//        {
//            System.out.println(Resultass.get(i).get(0)+" "+Resultass.get(i).get(1)+" "+Resultass.get(i).get(2)+" "+Resultass.get(i).get(3));
//        
//        }
         }
         else{
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.close();
                 Image image = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                 alert.setGraphic(new ImageView(image));
               alert.setTitle("Erreur rencontré");
               alert.setContentText("Votre Matrice est Singuliere :\n"
                       + " Son determinant est nulle Veuillez revoir vos coefficient SVP \n");
               alert.getButtonTypes().setAll(ButtonType.OK);  
               alert.showAndWait();
         }
    }
    private  ArrayList<ArrayList<Double>> Resultas;
    private  ArrayList<ArrayList<Double>> Resultass;
    
    private void actionRapideEnergie() {
      String energie=(!valeurFixerEnergie.getText().isEmpty())?valeurFixerEnergie.getText():"0.0";
      double energieDouble=Double.valueOf(energie);
      if(energieDouble>0)
      {
       //copier dans les text
         q11.setText("9.0");
         q12.setText("4.0");
         q13.setText("4.0");
         b1.setText(energie);
         fixEnergie=true;
      }
      else{
       fixEnergie=false;
      }
    }
    private void actionRapideLipide() {
      String lipide=(!valeurFixerLipide.getText().isEmpty())?valeurFixerLipide.getText():"0.0";
      double glucide=(!valeurFixerGlucide.getText().isEmpty())?Double.valueOf(valeurFixerGlucide.getText()):0.0;
      double Protide=(!valeurFixerProtide.getText().isEmpty())?Double.valueOf(valeurFixerProtide.getText()):0.0;
      double lipideDouble=Double.valueOf(lipide);
      if(lipideDouble>0)
      {
        double res=lipideDouble/9;
       //copier dans les text
          if(!fixGl && !fixPr)
     {
         q31.setText("1");
         q32.setText("0.0");
         q33.setText("0.0");
         b3.setText(String.valueOf(res));
         // lipideFixe=protideDouble;
         fixLip=true;
     }  
          else{
         q21.setText("1");
         q22.setText("0.0");
         q23.setText("0.0");
         b2.setText(String.valueOf(res));
         fixLip=true;
         }
      }
      else
      {
      fixLip=false;
      }
   
          
    }
    private void actionProtide() {
      String protide=(!valeurFixerProtide.getText().isEmpty())?valeurFixerProtide.getText():"0.0";
      double glucide=(!valeurFixerGlucide.getText().isEmpty())?Double.valueOf(valeurFixerGlucide.getText()):0.0;
      double Lipide=(!valeurFixerLipide.getText().isEmpty())?Double.valueOf(valeurFixerLipide.getText()):0.0;
      double protideDouble=Double.valueOf(protide);
      if(protideDouble!=0)
      {
        double valeurDerniereLigne=Double.valueOf(q33.getText());
        double valeurSecondeLigne=Double.valueOf(q23.getText());
        double valeurPremiereLigne=Double.valueOf(q13.getText());
     if(!fixGl && !fixLip)
     {
         q31.setText("0");
         q32.setText("0.0");
         q33.setText("1");
         b3.setText(String.valueOf(protideDouble/4));
         fixPr=true;
     }
     else{
         q21.setText("0");
         q22.setText("0.0");
         q23.setText("1");
         b2.setText(String.valueOf(protideDouble/4));
         fixPr=true;
         }
         }
        
        
        
         // protideFixe=protideDouble;
       
      }
  
    private void actionRapideGlucide(){
      String glucide=(!valeurFixerGlucide.getText().isEmpty())?valeurFixerGlucide.getText():"0.0";
      double lipide=Double.valueOf(valeurFixerLipide.getText());
      double Protide=Double.valueOf(valeurFixerProtide.getText());
      double glucideDouble=Double.valueOf(glucide);
      if(glucideDouble>0)
      {
        double res=glucideDouble/4;
       //copier dans les text
         if(!fixPr && !fixLip)
     {
         q31.setText("0.0");
         q32.setText("1.0");
         q33.setText("0.0");
         b3.setText(String.valueOf(res));
         fixGl=true;
     }
      else{
          q21.setText("0.0");
         q22.setText("1.0");
         q23.setText("0.0");
         b2.setText(String.valueOf(res));
         fixGl=true;
         }
      }
     else{
      
      }
    }
    private boolean fixLip;
    private boolean fixPr;
    private boolean fixGl;
    private boolean fixEnergie;
}
