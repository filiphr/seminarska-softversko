/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author Filip
 */
public class CreateDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> korisnici = new ArrayList<String>();
        for (int i=1; i<14; i++)
        {
           // InsertUserInDB(i);
            korisnici.add("fhrisafov"+i);
            korisnici.add("isudijovski"+i);
            korisnici.add("gmadzarov"+i);
            korisnici.add("dgjorgjevik"+i);
        }
        
        String [] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        
//        ArrayList<String> restorani = new ArrayList<String>();
//        
//        for (int i=0; i<restoraniHelp.length; i++)
//        {
//            for (int j=0; j<5; j++){
//                restorani.add(restoraniHelp[i] + Integer.toString(j + 1));
//            }
//        }
        
        String [] stavkiHelp = {"Капричиоза", "Маргарита", "Кватро Формаџи", "Кватро Стаџоне", "Рамстек", "Бифтек", "Бурек",
            "Симит погача", "Сарма", "Пастрмка", "Шопска салата", "Цезар салата", "Македонска салата",
            "Шеф салата", "Вешалица", "Кременадла", "Шарска", "Плескавица", "Колбаси", "Ражнич", "Пилешки стек", "Увијач", 
            "Пастрмајлија", "Пача", "Качамак", "Тавче гравче", "Мусака", "Гулаш", "Полнети пиперки", "Шкембе чорба", "Телешка чорба",};
        
        //Create restaurants with stavki, each restaurant has 15 stavki
        HashMap<String, Vector<String>> restoran_stavki = new  HashMap<String, Vector<String>>();
        for (int i=0; i<restoraniHelp.length; i++)
        {
            Vector<String> restoran_stavka = new Vector<String>();
            for (int j=0; j<15; j++)
            {
                Random r = new Random();
                String stavka = stavkiHelp[r.nextInt(stavkiHelp.length)];
                while (restoran_stavka.contains(stavka))
                {
                    stavka = stavkiHelp[r.nextInt(stavkiHelp.length)];
                }
                restoran_stavka.add(stavka);
            }
            restoran_stavki.put(restoraniHelp[i], restoran_stavka);
        }
        
        
//        ArrayList<String> stavki = new ArrayList<String>();
//        
//        for (int i=0; i<stavkiHelp.length; i++)
//        {
//            String ime=stavkiHelp[i];
//            for (int j=0; j<3; j++){
//                stavki.add(stavkiHelp[i] + Integer.toString(j+1));
//            }
//        }
        
        ArrayList<Integer> groupIDs = new ArrayList<Integer> ();
        //Create groups for eating 60 groups total
        
        
        long halfHourInMilis = 1800000;
        //1 Juni 2012 08:00
        GregorianCalendar startCalendar = new GregorianCalendar(2012, 5, 1, 8, 0);
        
        //09 Septemvri 2012 08:00
        GregorianCalendar endCalendar = new GregorianCalendar(2012,8,9,8,0);
        //100 dena za sekoj den po 2 do 5 grupi
        
        while (startCalendar.compareTo(endCalendar)<0)
        {
            //Get DAY OF WEKK
          int dow = startCalendar.get(Calendar.DAY_OF_WEEK);
          if (dow == Calendar.SUNDAY || dow == Calendar.SATURDAY)
          {
              startCalendar.add(Calendar.DATE, 1);
              continue;
          }
          Random r = new Random();
          int grupi = r.nextInt(4) + 2;
          Vector<String> usedKorisnici = new Vector<String>();
          
          for (int j=0 ; j<grupi; j++)
          {
              String kreator = korisnici.get(r.nextInt(korisnici.size()));
              while (usedKorisnici.contains(kreator))
              {
                  kreator = korisnici.get(r.nextInt(korisnici.size()));
              }
              usedKorisnici.add(kreator);
              String restoran = restoraniHelp[r.nextInt(restoraniHelp.length)];
              Timestamp date = new Timestamp(startCalendar.getTimeInMillis() + r.nextInt(18)*halfHourInMilis);
              Vector<String> stavkiVector = restoran_stavki.get(restoran);
              String stavka = stavkiVector.get(r.nextInt(stavkiVector.size()));
              int groupID=0;
              groupID = DataBaseHelper.insertGrupa(restoran, kreator, date);
              groupIDs.add(groupID);
              
              DataBaseHelper.insertGrupaKorisnik(kreator, groupID);
              DataBaseHelper.insertArhiviraniGrupi(kreator, stavka, groupID);
              
              //Za sekoja grupa vnesi dopolnitelno od 1 do 16 ucesnici
              int ucesnici = r.nextInt(16)+1;
              for (int i=0; i<ucesnici; i++)
              {
                  String ucesnik = korisnici.get(r.nextInt(korisnici.size()));
                  while (usedKorisnici.contains(ucesnik))
                  {
                      ucesnik = korisnici.get(r.nextInt(korisnici.size()));
                  }
                  usedKorisnici.add(ucesnik);
                  stavka = stavkiVector.get(r.nextInt(stavkiVector.size()));
                  
                DataBaseHelper.insertGrupaKorisnik(ucesnik, groupID);
                DataBaseHelper.insertArhiviraniGrupi(ucesnik, stavka, groupID);
              }
          }
          
          startCalendar.add(Calendar.DATE, 1);
        }
      
//        for(int i = 0; i < 5000; i++){
//            Random r = new Random();
//            int indexkorisnici = r.nextInt(korisnici.size());
//            int indexrestorani = r.nextInt(restoraniHelp.length);
//            int indexstavki = r.nextInt(stavkiHelp.length);
//            String date = RandomDate.getRandomDate();
//            //DataBaseHelper.insertArhiviraniGrupi(date, korisnici.get(indexkorisnici), restorani.get(indexrestorani), stavki.get(indexstavki));
//        }
    }

    private static void InsertUserInDB(int i) {
        DataBaseHelper.insertUser("Филип"+i, "Хрисафов"+i, "fhrisafov"+i, "filip.hrisfov@gmail.com", "fhrisafov"+i);
        DataBaseHelper.insertUser("Игор"+i, "Судијовски"+i, "isudijovski"+i, "igor.sudijovski@gmail.com", "isudijovski"+i);
        DataBaseHelper.insertUser("Ѓорѓи"+i, "Маџаров"+i, "gmadzarov"+i, "gjorgji.madzarov@gmail.com", "gmadzarov"+i);
        DataBaseHelper.insertUser("Дејан"+i, "Ѓорѓевиќ"+i, "dgjorgjevik"+i, "dejan.gjorgjevikj@gmail.com", "dgjorgjevikj"+i);
    }
    
}
