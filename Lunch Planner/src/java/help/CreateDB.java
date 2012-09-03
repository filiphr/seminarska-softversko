/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;
import java.util.ArrayList;
import java.util.Random;

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
        for (int i=1; i<51; i++)
        {
            DataBaseHelper.insertUser("Филип"+i, "Хрисафов"+i, "fhrisafov"+i, "filip.hrisfov@gmail.com", "fhrisafov"+i);
            DataBaseHelper.insertUser("Игор"+i, "Судијовски"+i, "isudijovski"+i, "igor.sudijovski@gmail.com", "isudijovski"+i);
            DataBaseHelper.insertUser("Ѓорѓи"+i, "Маџаров"+i, "gmadzarov"+i, "gjorgji.madzarov@gmail.com", "gmadzarov"+i);
            DataBaseHelper.insertUser("Дејан"+i, "Ѓорѓевиќ"+i, "dgjorgjevik"+i, "dejan.gjorgjevikj@gmail.com", "dgjorgjevikj"+i);
            korisnici.add("fhrisafov"+i);
            korisnici.add("isudijovski"+i);
            korisnici.add("gmadzarov"+i);
            korisnici.add("dgjorgjevik"+i);
        }
        
        String [] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        
        ArrayList<String> restorani = new ArrayList<String>();
        
        for (int i=0; i<restoraniHelp.length; i++)
        {
            for (int j=0; j<5; j++){
                restorani.add(restoraniHelp[i] + Integer.toString(j + 1));
            }
        }
        
        String [] stavkiHelp = {"Капричиоза", "Маргарита", "Кватро Формаџи", "Кватро Стаџоне", "Рамстек", "Бифтек", "Бурек",
            "Симит погача", "Сарма", "Пастрмка", "Шопска салата", "Цезар салата", "Македонска салата",
            "Шеф салата", "Вешалица", "Кременадла", "Шарска", "Плескавица", "Колбаси", "Ражнич", "Пилешки стек", "Увијач", 
            "Пастрмајлија", "Пача", "Качамак", "Тавче гравче", "Мусака", "Гулаш", "Полнети пиперки", "Шкембе чорба", "Телешка чорба",};
        
        ArrayList<String> stavki = new ArrayList<String>();
        
        for (int i=0; i<stavkiHelp.length; i++)
        {
            String ime=stavkiHelp[i];
            for (int j=0; j<3; j++){
                stavki.add(stavkiHelp[i] + Integer.toString(j+1));
            }
        }
        for(int i = 0; i < 5000; i++){
            Random r = new Random();
            int indexkorisnici = r.nextInt(korisnici.size());
            int indexrestorani = r.nextInt(restorani.size());
            int indexstavki = r.nextInt(stavki.size());
            String date = RandomDate.getRandomDate();
            DataBaseHelper.insertArhiviraniGrupi(date, korisnici.get(indexkorisnici), restorani.get(indexrestorani), stavki.get(indexstavki));
        }
    }
    
}
