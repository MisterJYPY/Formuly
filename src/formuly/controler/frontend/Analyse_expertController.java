/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *cette classe pour une meilleur experience devra etre appelée depuis le controller de creation de menu diethetique
 *qui va charger la liste des FaitConclusion et le passer en parametre de l'outils
 */
package formuly.controler.frontend;

import formuly.entities.FmFaitConclusion;
import formuly.expert.outilsExpert;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Mr_JYPY
 */
public class Analyse_expertController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private Button valider;

    @FXML
    private TextArea resultAnalyse;

    @FXML
    private Label aetLipide;

    @FXML
    private Label prcentGlucide;

    @FXML
    private Label aetGlucide;

    @FXML
    private Label poids;

    @FXML
    private Label regime3000;

    @FXML
    private Label regime2000;

    @FXML
    private Label sexe;

    @FXML
    private Label regime3500;

    @FXML
    private Label regime1000;

    @FXML
    private Label prcentProtide;

    @FXML
    private Label regime2500;

    @FXML
    private Label regime1500;

    @FXML
    private Label taille;

    @FXML
    private Label energieTotale;

    @FXML
    private Label prcentLipide;

    @FXML
    private Label aetProtide;

    @FXML
    private Label age;
    
   private outilsExpert expert;
   private List<FmFaitConclusion> listConclusion;
    public Analyse_expertController() {
        expert=new outilsExpert();
     //   listConclusion=formulyTools.Liste_FaitConclusion();
    }
/**
 * constructeur parametré du controller
 * @param aetLipide apport energetique en lipide
 * @param prcentGlucide prcentage Total en Glucide
 * @param aetGlucide    apport energetique en Glucide
 * @param regime3000    prcentage dans un regime de 3000 Kcal
 * @param regime2000    prcentage dans un regime de 2000 Kcal
 * @param regime3500    prcentage dans un reime de 3500 Kcal
 * @param regime1000    prcentage dans un regime de 1000 kcl
 * @param prcentProtide prcentage Total en Protide
 * @param regime2500    prcentage dans un regime de 2500 Kcal
 * @param regime1500    prcentage dans un regime de 1500 Kcal
 * @param energieTotale energie Totale en Kcal du menu
 * @param prcentLipide  prcentage Total en Lipide
 * @param aetProtide   apport energetique en Protide
 */
    public Analyse_expertController(Label aetLipide, Label prcentGlucide, Label aetGlucide, Label regime3000, Label regime2000, Label regime3500, Label regime1000, Label prcentProtide, Label regime2500, Label regime1500, Label energieTotale, Label prcentLipide, Label aetProtide) {
        this.aetLipide = aetLipide;
        this.prcentGlucide = prcentGlucide;
        this.aetGlucide = aetGlucide;
        this.regime3000 = regime3000;
        this.regime2000 = regime2000;
        this.regime3500 = regime3500;
        this.regime1000 = regime1000;
        this.prcentProtide = prcentProtide;
        this.regime2500 = regime2500;
        this.regime1500 = regime1500;
        this.energieTotale = energieTotale;
        this.prcentLipide = prcentLipide;
        this.aetProtide = aetProtide;
         //listConclusion=formulyTools.Liste_FaitConclusion();
    }

    /**
     * constructeur parametré avec des valeurs en parametre de type Double
     * @param expert
     * @param aetLipid 
     * @param aetProti
     * @param aetGlucid
     * @param energieTotal
     * @param prcentLipid
     * @param prcenProtid
     * @param prcenGlucid
     * @param reg1000
     * @param reg1500
     * @param reg2000
     * @param reg2500
     * @param reg3000
     * @param reg3500
     * @param sex
     * @param poid
     * @param taill
     * @param ages
     * @param analys 
     */
    public Analyse_expertController(outilsExpert expert,double aetLipid, double aetProti, double aetGlucid, double energieTotal, double prcentLipid, double prcenProtid, double prcenGlucid, double reg1000, double reg1500, double reg2000, double reg2500, double reg3000, double reg3500, int sex, double poid, double taill, double ages, String analys) {
        this.aetLipid = aetLipid;
        this.aetProti = aetProti;
        this.aetGlucid = aetGlucid;
        this.energieTotal = energieTotal;
        this.prcentLipid = prcentLipid;
        this.prcenProtid = prcenProtid;
        this.prcenGlucid = prcenGlucid;
        this.reg1000 = reg1000;
        this.reg1500 = reg1500;
        this.reg2000 = reg2000;
        this.reg2500 = reg2500;
        this.reg3000 = reg3000;
        this.reg3500 = reg3500;
        this.sex = sex;
        this.poid = poid;
        this.taill = taill;
        this.ages = ages;
        this.analys = analys;
        this.expert=expert;
//       listConclusion=formulyTools.Liste_FaitConclusion();
//       expert.setListConclusion(listConclusion);
       // expert=new outilsExpert(aetLipid, aetProti, aetGlucid, prcenGlucid, prcenProtid, prcentLipid,reg1000, reg1500, reg2000, reg2500, reg3000, reg3500,energieTotal);
        
    } 
    
      public Analyse_expertController(outilsExpert expert) {   
        this.expert=expert;
         this.aetLipid = expert.getAetLipide();
        this.aetProti = expert.getAetProide();
        this.aetGlucid = expert.getAetGlucide();
        this.energieTotal = expert.getEnergieTotale();
        this.prcentLipid = expert.getPrcenLipide();
        this.prcenProtid = expert.getPrcentProtide();
        this.prcenGlucid = expert.getPrcentGlucide();
        this.reg1000 = expert.getRegime1000();
        this.reg1500 = expert.getRegime1500();
        this.reg2000 = expert.getRegime2000();
        this.reg2500 = expert.getRegime2500();
        this.reg3000 = expert.getRegime3000();
        this.reg3500 = expert.getRegime3500();
//        this.sex = (int) expert.getSexeClient();
//        this.poid = expert.getPoidsClient();
//       // this.taill = expert.gettailleClient();
//        this.ages = expert.getAgeClient(); 
        this.analys = expert.getConclusion();
//       listConclusion=formulyTools.Liste_FaitConclusion();
//       expert.setListConclusion(listConclusion);
       // expert=new outilsExpert(aetLipid, aetProti, aetGlucid, prcenGlucid, prcenProtid, prcentLipid,reg1000, reg1500, reg2000, reg2500, reg3000, reg3500,energieTotal);
        
    } 
    
    public void intialiserLesLabels()
    {
         NumberFormat format=NumberFormat.getInstance();
            format.setMaximumFractionDigits(2); 
     energieTotale.setText(format.format(energieTotal)+" Kcal ");
     aetGlucide.setText(format.format(aetGlucid)+" % ");
     aetProtide.setText(format.format(aetProti)+" % ");
     aetLipide.setText(format.format(aetLipid)+" % ");
     prcentGlucide.setText(format.format(prcenGlucid)+" % ");
     prcentLipide.setText(format.format(prcentLipid)+" % ");
     prcentProtide.setText(format.format(prcenProtid)+" % ");
     regime1000.setText(format.format(reg1000)+" % ");
     regime1500.setText(format.format(reg1500)+" % ");
     regime2000.setText(format.format(reg2000)+" % ");
     regime2500.setText(format.format(reg2500)+" % ");
     regime3000.setText(format.format(reg3000)+" % ");
     regime3500.setText(format.format(reg3500)+" % ");
    poids.setText((poid>0)?format.format(poid)+" Kg ":"Non Def");
    age.setText((ages>0)?format.format(ages)+" Ans":"Non Def");
    taille.setText((taill>0)?format.format(taill)+" m ":"Non Def");
    sexe.setText((sex>0)?donnerSexe(sex):"ND");
    analys=expert.getConclusion();
    this.resultAnalyse.setText(!(analys.isEmpty())?analys:"Aucune Interpretation valide pour ce menu");
    }
    /**
     * methode qui retourne le sexe format string du sujet
     * les valeurs : 1 correspond a un homme 
     * 2 correspond a une femme
     * un chiffre negatif correspond a NON DEFINI
     * @param valeur la valeur passer en parametre
     * @return 
     */
    public String donnerSexe(int valeur)
    {
        String sexs;
      return  sexs=(valeur>0)?(valeur==1)?"M":(valeur==2)?"F":"Err":"ND";
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        intialiserLesLabels();
    }    

    public Button getValider() {
        return valider;
    }

    public void setValider(Button valider) {
        this.valider = valider;
    }

    public TextArea getResultAnalyse() {
        return resultAnalyse;
    }

    public void setResultAnalyse(TextArea resultAnalyse) {
        this.resultAnalyse = resultAnalyse;
    }

    public Label getAetLipide() {
        return aetLipide;
    }

    public void setAetLipide(Label aetLipide) {
        this.aetLipide = aetLipide;
    }

    public Label getPrcentGlucide() {
        return prcentGlucide;
    }

    public void setPrcentGlucide(Label prcentGlucide) {
        this.prcentGlucide = prcentGlucide;
    }

    public Label getAetGlucide() {
        return aetGlucide;
    }

    public void setAetGlucide(Label aetGlucide) {
        this.aetGlucide = aetGlucide;
    }

    public Label getPoids() {
        return poids;
    }

    public void setPoids(Label poids) {
        this.poids = poids;
    }

    public Label getRegime3000() {
        return regime3000;
    }

    public void setRegime3000(Label regime3000) {
        this.regime3000 = regime3000;
    }

    public Label getRegime2000() {
        return regime2000;
    }

    public void setRegime2000(Label regime2000) {
        this.regime2000 = regime2000;
    }

    public Label getSexe() {
        return sexe;
    }

    public void setSexe(Label sexe) {
        this.sexe = sexe;
    }

    public Label getRegime3500() {
        return regime3500;
    }

    public void setRegime3500(Label regime3500) {
        this.regime3500 = regime3500;
    }

    public Label getRegime1000() {
        return regime1000;
    }

    public void setRegime1000(Label regime1000) {
        this.regime1000 = regime1000;
    }

    public Label getPrcentProtide() {
        return prcentProtide;
    }

    public void setPrcentProtide(Label prcentProtide) {
        this.prcentProtide = prcentProtide;
    }

    public Label getRegime2500() {
        return regime2500;
    }

    public void setRegime2500(Label regime2500) {
        this.regime2500 = regime2500;
    }

    public Label getRegime1500() {
        return regime1500;
    }

    public void setRegime1500(Label regime1500) {
        this.regime1500 = regime1500;
    }

    public Label getTaille() {
        return taille;
    }

    public void setTaille(Label taille) {
        this.taille = taille;
    }

    public Label getEnergieTotale() {
        return energieTotale;
    }

    public void setEnergieTotale(Label energieTotale) {
        this.energieTotale = energieTotale;
    }

    public Label getPrcentLipide() {
        return prcentLipide;
    }

    public void setPrcentLipide(Label prcentLipide) {
        this.prcentLipide = prcentLipide;
    }

    public Label getAetProtide() {
        return aetProtide;
    }

    public void setAetProtide(Label aetProtide) {
        this.aetProtide = aetProtide;
    }

    public Label getAge() {
        return age;
    }

    public void setAge(Label age) {
        this.age = age;
    }

    public outilsExpert getExpert() {
        return expert;
    }

    public void setExpert(outilsExpert expert) {
        this.expert = expert;
    }

    public double getAetLipid() {
        return aetLipid;
    }

    public void setAetLipid(double aetLipid) {
        this.aetLipid = aetLipid;
    }

    public double getAetProti() {
        return aetProti;
    }

    public void setAetProti(double aetProti) {
        this.aetProti = aetProti;
    }

    public double getAetGlucid() {
        return aetGlucid;
    }

    public void setAetGlucid(double aetGlucid) {
        this.aetGlucid = aetGlucid;
    }

    public double getEnergieTotal() {
        return energieTotal;
    }

    public void setEnergieTotal(double energieTotal) {
        this.energieTotal = energieTotal;
    }

    public double getPrcentLipid() {
        return prcentLipid;
    }

    public void setPrcentLipid(double prcentLipid) {
        this.prcentLipid = prcentLipid;
    }

    public double getPrcenProtid() {
        return prcenProtid;
    }

    public void setPrcenProtid(double prcenProtid) {
        this.prcenProtid = prcenProtid;
    }

    public double getPrcenGlucid() {
        return prcenGlucid;
    }

    public void setPrcenGlucid(double prcenGlucid) {
        this.prcenGlucid = prcenGlucid;
    }

    public double getReg1000() {
        return reg1000;
    }

    public void setReg1000(double reg1000) {
        this.reg1000 = reg1000;
    }

    public double getReg1500() {
        return reg1500;
    }

    public void setReg1500(double reg1500) {
        this.reg1500 = reg1500;
    }

    public double getReg2000() {
        return reg2000;
    }

    public void setReg2000(double reg2000) {
        this.reg2000 = reg2000;
    }

    public double getReg2500() {
        return reg2500;
    }

    public void setReg2500(double reg2500) {
        this.reg2500 = reg2500;
    }

    public double getReg3000() {
        return reg3000;
    }

    public void setReg3000(double reg3000) {
        this.reg3000 = reg3000;
    }

    public double getReg3500() {
        return reg3500;
    }

    public void setReg3500(double reg3500) {
        this.reg3500 = reg3500;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getPoid() {
        return poid;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public double getTaill() {
        return taill;
    }

    public void setTaill(double taill) {
        this.taill = taill;
    }

    public double getAges() {
        return ages;
    }

    public void setAges(double ages) {
        this.ages = ages;
    }

    public String getAnalys() {
        return analys;
    }

    public void setAnalys(String analys) {
        this.analys = analys;
    }
    
    private double aetLipid;
    private double aetProti;
    private double aetGlucid;
    private double energieTotal;
    private double prcentLipid;
    private double prcenProtid;
    private double prcenGlucid;
    private double reg1000;
    private double reg1500;
    private double reg2000;
    private double reg2500;
    private double reg3000;
    private double reg3500;
    private int sex;
    private double poid;
    private double taill;
    private double ages;
    private String analys;
}
