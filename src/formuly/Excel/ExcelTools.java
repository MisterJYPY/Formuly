/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.Excel;

import formuly.classe.formulyTools;
import formuly.entities.FmAliments;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mr_JYPY
 */
public class ExcelTools {
    
    private List<Book> listBooks;
    public static int cas=0;
    private boolean dossierCreer;
   private String nomFichier;
    private final String cheminDossierAlimentBc;
    private Object getCellValue(Cell cell) {
    switch (cell.getCellType()) {
        
    case Cell.CELL_TYPE_STRING:
        return cell.getStringCellValue();
 
    case Cell.CELL_TYPE_BOOLEAN:
        return cell.getBooleanCellValue();
 
    case Cell.CELL_TYPE_NUMERIC:
        return cell.getNumericCellValue();
    }
 
    return null;
}

    public ExcelTools() {
     listBooks=new ArrayList<>();
     cheminDossier = "C:\\Users\\" + formulyTools.getUserName() + "\\Documents\\Formuly";
     cheminDossierAlimentBc = "C:\\Users\\" + formulyTools.getUserName() + "\\Documents\\Formuly\\FoodsBc";
     
     //si on veut faire un dump  alors on cree le dossier si le dossier n'est pas creer
     
      if(cas==1)
      {
       File dir = new File(cheminDossierAlimentBc);
       dossierCreer = dir.mkdirs();
      }
    }
    
    public List<Book> readBooksFromExcelFile(File file) {
            listBooks.clear();
        try {
           
            FileInputStream inputStream = new FileInputStream(file);
            System.out.println("nom fichier : "+file.getName());
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                int ligne=nextRow.getRowNum();
                if(ligne!=0)
                {
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    Book aBook = new Book();
                    
                    while (cellIterator.hasNext()) {
                        Cell nextCell = cellIterator.next();
                        int columnIndex = nextCell.getColumnIndex();
                        
                        switch (columnIndex) {
                            case 0:
                                // System.out.println("");
                                 System.out.println("nomFr");
                                aBook.setNomFr((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"aucun");
                               
                                break;
                            case 1:
                                System.out.println("nomEng");
                                aBook.setNomEng((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"");
                                break;
                            case 2:
                              
                                aBook.setSurnom((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"");
                                 System.out.println("Surnom");
                                break;
                            case 3:
                                
                                aBook.setModeCuisson((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"cuit");
                               System.out.println("Mode cuisson");
                                break;
                            case 4:
                               
                                aBook.setCategorie((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"aucun");
                                System.out.println("Categorie : "+aBook.getCategorie());
                                break;
                            case 5:
                               
                                aBook.setPays((getCellValue(nextCell))!=null?String.valueOf(getCellValue(nextCell)):"General");
                                System.out.println("pays");
                                break;
                            case 6:
                                aBook.setLipide((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                 System.out.println("lipide : "+aBook.getLipide());
                                break;
                                
                            case 7:
                                aBook.setProtide((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("protide :"+aBook.getProtide());
                                break;
                                
                            case 8:
                                aBook.setGlucide((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("glucide : "+aBook.getGlucide());
                                break;
                                
                            case 9:
                                aBook.setEnergie((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("energie");
                                break;
                                
                            case 10:
                                aBook.setAsh((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                 System.out.println("ash");
                                break;
                                
                            case 11:
                                aBook.setEau((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                               System.out.println("eau");
                                break;
                                
                            case 12:
                                aBook.setFibre((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("fibre");
                                break;
                                
                            case 13:
                                aBook.setCa((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("ca");
                                break;
                                
                            case 14:
                                aBook.setFer((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                 System.out.println("fer");
                                break;
                            case 15:
                                aBook.setMg((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                               System.out.println("mg");
                                break;
                                
                            case 16:
                                aBook.setPhos((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("PHOS");
                                break;
                                
                            case 17:
                                aBook.setPota((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                               System.out.println("pota");
                                break;
                                
                            case 18:
                                aBook.setNa((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                              System.out.println("na");
                                break;
                                
                            case 19:
                                aBook.setZn((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                             System.out.println("zn");
                                break;
                                
                            case 20:
                                aBook.setCu((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                               System.out.println("cu");
                                break;
                            case 21:
                                aBook.setVita((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("vita");
                                break;
                                
                            case 22:
                                aBook.setVitb1((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                System.out.println("vitb1");
                                break;
                                
                            case 23:
                                aBook.setVitb2((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                   System.out.println("vitb2");
                                break;
                                
                            case 24:
                                aBook.setVitb6((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                    System.out.println("vitb6");
                                break;
                                
                            case 25:
                                aBook.setVitb12((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                    System.out.println("vitb12");
                                break;
                                
                            case 26:
                                aBook.setVitc((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                     System.out.println("vitc");
                                break;
                                
                            case 27:
                                aBook.setVitd((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                   System.out.println("vitd");
                                break;
                            case 28:
                                aBook.setVite((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                 System.out.println("vite");
                                break;
                            case 29:
                                aBook.setNiacine((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                     System.out.println("niacine");
                                break;
                            case 30:
                                aBook.setFolates((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                   System.out.println("folate");
                                break;
                            case 31:
                                aBook.setThiamin((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                    System.out.println("thiamin");
                                break;
                            case 32:
                                aBook.setRiboflavin((getCellValue(nextCell))!=null?Double.parseDouble(formulyTools.preformaterChaine(String.valueOf(getCellValue(nextCell)))):0.0);
                                    System.out.println("ribo");
                                break;
                                
                        }
                    }
                    if(aBook.getNomFr()!=null)
                    {
 
                    if(!(aBook.getNomFr()).equals("aucun") && (aBook.getLipide()>0 || aBook.getGlucide()>0 || aBook.getProtide()>0) && !aBook.getCategorie().equals("aucun"))
                    {
                        listBooks.add(aBook); 
                    }
                    }
                }
                        System.out.println("enregisterr");
                        System.out.println("taille :"+listBooks.size());
                       
            }
           
           inputStream.close();
           workbook.close();
           
        } catch (IOException ex) {
            Logger.getLogger(ExcelTools.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("eeeeeeeeeeeeexxxxxeptionnnnnnnnnnnnn");
        }
        return listBooks;
}
    public void DumpFoodsDataBase(List<?> ListAliment,ProgressBar progress,Label indication)
    {
      if(ListAliment.size()>0)
      {
      if(ListAliment.get(0).getClass().equals(FmAliments.class))
      {
          indication.setVisible(true);
          progress.setVisible(true);
          LaunchDumpProcess((List<FmAliments>) ListAliment, progress,indication);
        //  System.out.println("liste des aliments nbre : "+ListAliment.size());
      }
      else
       {
    //appel de la tache   
       }
      }
    }
    private void LaunchDumpProcess(List<FmAliments> list,ProgressBar progress,Label indication)
    {
    
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrement d'aliment");
                // alert.show();
               Task copyWorker = createWorker(list);
          progress.progressProperty().unbind();
          progress.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener(new ChangeListener<String>() {        
          public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue) {
              if("terminer".equals(newValue))
              {
                Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/correct.png"));
                   alert.setGraphic(new ImageView(imageSucces));
                 
                    alert.setTitle("Opération terminée");
               alert.setContentText("Votre Téléchargement a été un succès...\n"
                       + "Vous trouverez le fichier au nom de : "+nomFichier+" \n"
                       + " Dans le dossier à L'adresse  : "+cheminDossierAlimentBc);
               indication.setVisible(false);
               progress.setVisible(false);
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();
              }
              else{
                if(!"erreur".equals(newValue))
                {
              indication.setText(newValue);
                }
                else
                {
              Image imageSucces = new Image(
     getClass().getResourceAsStream("/formuly/image/war.jpg"));
                   alert.setGraphic(new ImageView(imageSucces));
                     alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Opération Echoué");
               alert.setContentText("Erreur rencontré lors du téléchargement de vos aliments\n"
                       + "Reesayer cela à nouveau SVP \n");
              alert.getButtonTypes().setAll(ButtonType.FINISH);  
              alert.show();       
                }
              }
         }
                });
        
      new Thread(copyWorker).start();
    }
     public Task createWorker(List<FmAliments> list) {
    return new Task() {
      @Override
      protected Object call() throws Exception {

          try {
               Date dat=new Date();
               nomFichier="FoodsBc"+dat.getTime();
                updateMessage("lancement du BackUp....");
              updateProgress(1,list.size());
     FileOutputStream out = new FileOutputStream(cheminDossierAlimentBc+"\\" +nomFichier+".xlsx");    
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet mySheet = wb.createSheet();
                XSSFRow myRow = null;
                
            myRow = mySheet.createRow(0);
         //   myRow.setRowStyle(new XSSFCellStyl);
           
      for(int k=0;k<33;k++)
                   {
                myRow.createCell(0).setCellValue("NomFr");
                myRow.createCell(1).setCellValue("NomEng");
                myRow.createCell(2).setCellValue("SurNom");
                myRow.createCell(3).setCellValue("Cuisson");
                myRow.createCell(4).setCellValue("Catégorie");
                myRow.createCell(5).setCellValue("Pays");
                myRow.createCell(6).setCellValue("Lipide");
                myRow.createCell(7).setCellValue("Protide");
                myRow.createCell(8).setCellValue("Glucide");
                myRow.createCell(9).setCellValue("Energie");
                myRow.createCell(10).setCellValue("Ash");
                myRow.createCell(11).setCellValue("Eau");
                myRow.createCell(12).setCellValue("Fibre");
                myRow.createCell(13).setCellValue("Ca");
                myRow.createCell(14).setCellValue("Fer");
                myRow.createCell(15).setCellValue("Mg");
                myRow.createCell(16).setCellValue("Phosphore");
                myRow.createCell(17).setCellValue("Ka");
                myRow.createCell(18).setCellValue("Na");
                myRow.createCell(19).setCellValue("Zn");
                myRow.createCell(20).setCellValue("Cu");
                myRow.createCell(21).setCellValue("vit A");
                myRow.createCell(22).setCellValue("vit B1");
                myRow.createCell(23).setCellValue("vit B2");
                myRow.createCell(24).setCellValue("vit B6");
                myRow.createCell(25).setCellValue("vit B12");
                myRow.createCell(26).setCellValue("vit C");
                myRow.createCell(27).setCellValue("vit D");
                myRow.createCell(28).setCellValue("vit E");
                myRow.createCell(29).setCellValue("Niacine");
                myRow.createCell(30).setCellValue("Folates");
                myRow.createCell(31).setCellValue("Thiamin");
                myRow.createCell(32).setCellValue("Riboflavin");
                   }
       CellStyle style = wb.createCellStyle();
           style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
          style.setFillPattern(CellStyle.BIG_SPOTS);
           myRow.setRowStyle(style);
                 int cmpteur=0;
              for (FmAliments list1 : list) {
   List<FmRetentionMineraux> mineL=(list1.getFmRetentionMinerauxCollection()!=null && list1.getFmRetentionMinerauxCollection().size()>0)?(List<FmRetentionMineraux>) list1.getFmRetentionMinerauxCollection():null;
   List<FmRetentionVitamines> vitL=(list1.getFmRetentionVitaminesCollection()!=null && list1.getFmRetentionVitaminesCollection().size()>0)?(List<FmRetentionVitamines>) list1.getFmRetentionVitaminesCollection():null; 
   List<FmRetentionNutriments> nutrL=(list1.getFmRetentionNutrimentsCollection()!=null && list1.getFmRetentionNutrimentsCollection().size()>0)?(List<FmRetentionNutriments>) list1.getFmRetentionNutrimentsCollection():null; 
   FmRetentionMineraux mine=(mineL!=null)?mineL.get(0):null;
   FmRetentionVitamines vit=(vitL!=null)?vitL.get(0):null;
   FmRetentionNutriments nutr=(nutrL!=null)?nutrL.get(0):null;
       int nbre=cmpteur+1;
           updateMessage("Chargement..."+nbre+"/"+list.size());
               myRow = mySheet.createRow(nbre);
   for(int i=0;i<33;i++)
                   {
                          myRow.createCell(0).setCellValue(list1.getNomFr());
                          myRow.createCell(1).setCellValue(list1.getNomEng());
                          myRow.createCell(2).setCellValue(list1.getSurnom());
                            myRow.createCell(3).setCellValue(list1.getModeCuisson());
                            myRow.createCell(4).setCellValue(list1.getGroupe().getNomFr());
                            myRow.createCell(5).setCellValue(list1.getPays());
                            myRow.createCell(6).setCellValue((nutr!=null)?nutr.getLipide():0.0);
                            myRow.createCell(7).setCellValue((nutr!=null)?nutr.getProtein():0.0);
                            myRow.createCell(8).setCellValue((nutr!=null)?nutr.getGlucide():0.0);
                            myRow.createCell(9).setCellValue((nutr!=null)?nutr.getEnergieKcal():0.0);
                            myRow.createCell(10).setCellValue((nutr!=null)?nutr.getAsh():0.0);
                            myRow.createCell(11).setCellValue((nutr!=null)?nutr.getEau():0.0);
                            myRow.createCell(12).setCellValue((nutr!=null)?nutr.getFibre():0.0);
                            myRow.createCell(13).setCellValue((mine!=null)?mine.getCa():0.0);
                            myRow.createCell(14).setCellValue((mine!=null)?mine.getFe():0.0);
                            myRow.createCell(15).setCellValue((mine!=null)?mine.getMg():0.0);
                            myRow.createCell(16).setCellValue((mine!=null)?mine.getPhos():0.0);
                            myRow.createCell(17).setCellValue((mine!=null)?mine.getPota():0.0);
                            myRow.createCell(18).setCellValue((mine!=null)?mine.getNa():0.0);
                            //myRow.createCell(19).setCellValue(montant);
                            myRow.createCell(19).setCellValue((mine!=null)?mine.getZn():0.0);
                            myRow.createCell(20).setCellValue((mine!=null)?mine.getCu():0.0);
                            myRow.createCell(21).setCellValue((vit!=null)?vit.getVita():0.0);
                            myRow.createCell(22).setCellValue((vit!=null)?vit.getVitb1():0.0);
                            myRow.createCell(23).setCellValue((vit!=null)?vit.getVitb2():0.0);
                            myRow.createCell(24).setCellValue((vit!=null)?vit.getVitb6():0.0);
                            myRow.createCell(25).setCellValue((vit!=null)?vit.getVitb12():0.0);
                            myRow.createCell(26).setCellValue((vit!=null)?vit.getVitc():0.0);
                            myRow.createCell(27).setCellValue((vit!=null)?vit.getVitd():0.0);
                            myRow.createCell(28).setCellValue((vit!=null)?vit.getVite():0.0);
                            myRow.createCell(29).setCellValue((vit!=null)?vit.getNiacine():0.0);
                            myRow.createCell(30).setCellValue((vit!=null)?vit.getFolates():0.0);
                            myRow.createCell(31).setCellValue((vit!=null)?vit.getThiamin():0.0);
                            myRow.createCell(32).setCellValue((vit!=null)?vit.getRiboflavin():0.0);
                         //   myRow.createCell(33).setCellValue(code_operation);
                   }
                  cmpteur++;
                  updateProgress(cmpteur,list.size());
                  
                
              }
               wb.write(out);
                out.close();
              updateMessage("terminer");
              }

       catch (Exception e) {
               updateMessage("erreur");  
              System.out.println("une erreur produite : "+e.getLocalizedMessage());
         Logger.getLogger(ExcelTools.class.getName()).log(Level.SEVERE, null, e);
           
          }
        return true;
         }
            };
            }
    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }
   public String cheminDossier ;
}
