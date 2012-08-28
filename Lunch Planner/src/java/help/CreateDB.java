/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;

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
        String korisnici [] = new String[500];
        for (int i=1; i<51; i++)
        {
            DataBaseHelper.insertUser("Филип"+i, "Хрисафов"+i, "fhrisafov"+i, "filip.hrisfov@gmail.com", "fhrisafov"+i);
            DataBaseHelper.insertUser("Игор"+i, "Судијовски"+i, "isudijovski"+i, "igor.sudijovski@gmail.com", "isudijovski"+i);
            DataBaseHelper.insertUser("Ѓорѓи"+i, "Маџаров"+i, "gmadzarov"+i, "gjorgji.madzarov@gmail.com", "gmadzarov"+i);
            DataBaseHelper.insertUser("Дејан"+i, "Ѓорѓевиќ"+i, "dgjorgjevik"+i, "dejan.gjorgjevikj@gmail.com", "dgjorgjevikj"+i);
        }
        
        String [] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица"};
        
        String restorani [] = new String[60];
        
        for (int i=0; i<restoraniHelp.length; i++)
        {
            String ime=restoraniHelp[i];
            for (int j=0; j<5; j++){
                restorani[i*5+j] = ime+""+(j+1);
            }
        }
        
        String [] stavkiHelp = {"Капричиоза", "Маргарита", "Кватро Формаџи", "Кватро Стаџоне", "Рамстек", "Бифтек", "Бурек",
            "Симит погача", "Сарма", "Пастрмка", "Шопска салата", "Цезар салата", "Македонска салата",
            "Шеф салата", "Вешалица", "Кременадла", "Шарска", "Плескавица", "Колбаси", "Ражнич", "Пилешки стек", "Увијач", 
            "Пастрмајлија", "Пача", "Качамак", "Тавче гравче", "Мусака", "Гулаш", "Полнети пиперки", "Шкембе чорба", "Телешка чорба",};
        
        String stavki [] = new String[1024];
        
        for (int i=0; i<stavkiHelp.length; i++)
        {
            String ime=stavkiHelp[i];
            for (int j=0; j<3; j++){
                restorani[i*3+j] = ime+""+(j+1);
            }
        }
        
    }
}
