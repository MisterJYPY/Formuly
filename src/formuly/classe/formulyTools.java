/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formuly.classe;


import formuly.model.frontend.mainModel;
import formuly.entities.FmAliments;
import formuly.entities.FmRetentionMineraux;
import formuly.entities.FmRetentionNutriments;
import formuly.entities.FmRetentionVitamines;
import formuly.model.frontend.modelFoodSelect;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mr_JYPY
 */
public class formulyTools {
    
    
    public static EntityManagerFactory getEm(String persistenceName)
    {
           
      EntityManagerFactory   entityManagerFactory = Persistence.createEntityManagerFactory( persistenceName);
        return  entityManagerFactory ;
       
    }
         
  public static ObservableList<mainModel> getobservableListMainModel()
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),String.valueOf(100.0),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
    public static ObservableList<mainModel> getobservableListMainModel(int parametreFictif)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention();
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
   public static ObservableList<mainModel> getobservableListMainModel(String NomNameQuery,String champ,Object parametre)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(NomNameQuery, champ, parametre);
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),String.valueOf(100.0),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
   
   public static ObservableList<mainModel> getobservableListMainModel(String requeteNative,modelFoodSelect model)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(requeteNative, model);
      System.out.println(requeteNative);
     System.out.println("nbre pri: "+retentionAliment.size());
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),String.valueOf(100.0),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
    public static ObservableList<mainModel> getobservableListMainModel(String requeteNative,modelFoodSelect model,String parametreFictif)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
      mainModel mainM=null;
     List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(requeteNative, model);
      System.out.println(requeteNative);
     System.out.println("nbre pri: "+retentionAliment.size());
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }  
  public static ObservableList<mainModel> getobservableListMainModel(String Pays)
  {
      ObservableList<mainModel> inf = FXCollections.observableArrayList();
       mainModel mainM=null;
      List<RetentionAlments> retentionAliment= RetentionAlments.getAllAlimentRetention(Pays);
      if(retentionAliment!=null)
      {
           int cpt=1;
        for(RetentionAlments ret: retentionAliment)
        {
            FmAliments aliment=ret.getAliments();
            FmRetentionMineraux mine=ret.getRm();
            FmRetentionNutriments nutr=ret.getRn();
            FmRetentionVitamines vit=ret.getRvtm();
//             mainM=new mainModel(cpt,aliment.getNomFr(),null,(double)nutr.getGlucide(),null,(double)nutr.getLipide(),null, (double)nutr.getProtein(),null,(double)nutr.getEnergieKcal(),(double)mine.getFe(),(double)mine.getMg(),(double)mine.getNa(),(double)mine.getPota(), (double)vit.getVitc(),(double)vit.getVite(),null,(double)vit.getVita());
           // System.out.println(aliment.getSurnom());
            mainM=new mainModel(cpt,("aucun".equals(aliment.getSurnom()))?aliment.getNomFr():aliment.getSurnom(),String.valueOf(100.0),(nutr==null)?0.0:nutr.getGlucide(),0.0,(nutr==null)?0.0:nutr.getLipide(),0.0,(nutr==null)?0.0:nutr.getProtein(),0.0,(nutr==null)?0.0:nutr.getEnergieKcal(),(mine==null)?0.0:mine.getFe(),(mine==null)?0.0:mine.getMg(),(mine==null)?0.0:mine.getNa(),(mine==null)?0.0:mine.getPota(),(vit==null)?0.0:vit.getVitc(),(vit==null)?0.0:vit.getVite(),(vit==null)?0.0:vit.getFolates(),(vit==null)?0.0:vit.getVita());
            mainM.setNumero(cpt);
            mainM.setNom_aliment(aliment.getNomFr());
            mainM.setPays(aliment.getPays());
            
            inf.add(mainM);
            cpt++;
        }
      }
     return inf;
  }   
  public static String preformaterChaine(String m)
  {
  String lesCaracteresNonVoulu = "[(,, ,=,/,),ฐ,+,*,',:, ,้,&,่,--,_,๙,$,^^,\\,\",?,!,็,ฒ,ฃ,จจ,%,ต,{,},#,ง,;,<,>,เ,[a-z,A-Z]]";   
     //char a=evt.getKeyChar();
     //notre classe paterne pour compiler la plage de caracterenon esires
     boolean t=false;
      m=m.replace(',','.');
     while(!t){
         if(!m.isEmpty()){
    Pattern regPat = Pattern.compile(lesCaracteresNonVoulu );
 //ici c'est pour voir si  notre pattern comiler peut contenir les caracteres non desires
    Matcher matcher = regPat.matcher(m);
    if (matcher.find()){
       m="0"; 
      }else{
       t=true;
    }
    
     }
  } 
     return m;
  }
   public static void mettreEffetButton(Button[] TabloButton)
     {
          DropShadow shadow = new DropShadow();
//Adding the shadow when the mouse cursor is on
          for(int i=0;i<TabloButton.length;i++)
          {
              Button bt=TabloButton[i];
       bt.addEventHandler(MouseEvent.MOUSE_ENTERED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
           bt.setEffect(shadow);
        }
});
//Removing the shadow when the mouse cursor is off
     bt.addEventHandler(MouseEvent.MOUSE_EXITED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            bt.setEffect(null);
        }
});
          }
     }
}
