/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.Excel;

import formuly.classe.formulyTools;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mr_JYPY
 */
public class ExcelReader {
    
    private List<Book> listBooks;
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

    public ExcelReader() {
     listBooks=new ArrayList<>();
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
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("eeeeeeeeeeeeexxxxxeptionnnnnnnnnnnnn");
        }
        return listBooks;
}

    public List<Book> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Book> listBooks) {
        this.listBooks = listBooks;
    }
    
}
