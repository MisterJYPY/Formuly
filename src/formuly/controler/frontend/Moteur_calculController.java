/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.controler.frontend;

import Jama.Matrix;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textConverter(q11,q12,q13,q21,q22,q23,q31,q32,q33,b1,b2,b3);
        
        lancer.setOnAction(event->{
        faireCalcul();
        });
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

    private void faireCalcul() {
        //valeur pour la matrice a
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
        //  double[][] array = {{1.,2.,3},{4.,5.,6.},{7.,8,10.}};
         Matrix A = new Matrix(array);
            double detA=  A.det();
         if(detA!=0.0)
          {
       double[] col={v1,v2,v3};
  //  Matrix b = Matrix.random(3,1);
       Matrix b =new Matrix(col, 3);
       Matrix x = A.solve(b);
       Matrix Residual = A.times(x).minus(b);
       double rnorm = Residual.normInf();
       double res1=x.get(0, 0);
       double res2=x.get(1, 0);
       double res3=x.get(2, 0);
        resx1.setText(String.valueOf(res1));
        resx2.setText(String.valueOf(res2));
        resx3.setText(String.valueOf(res3));
        residu.setText(String.valueOf(rnorm));
         }
    }
}
