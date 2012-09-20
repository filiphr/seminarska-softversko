/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prediction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author igor
 */
public class GetClass {
    
    public static HashMap<String, ArrayList<String>> getResSoStavki(){
        /*String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        HashMap<String, ArrayList<String>> rest = new HashMap<String, ArrayList<String>>();
        for(int i = 0; i < restoraniHelp.length; i++){
            ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(stavkiHelp[i]));
            rest.put(restoraniHelp[i], tmp);
        }*/
        return DataBase.DataBaseHelper.getAllRestorantsAndStavki();
    }
    
    public static ArrayList<String> getRestoratns(){
        HashMap<String, ArrayList<String>> map = DataBase.DataBaseHelper.getAllRestorantsAndStavki();
//        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        ArrayList<String> lst = new ArrayList<String>(map.keySet());
        lst.add(0, "");
        return lst;
    }
    
    public static ArrayList<String> getStavki(){
        HashMap<String, ArrayList<String>> map = DataBase.DataBaseHelper.getAllRestorantsAndStavki();
        ArrayList<String> lst = new ArrayList<String>(map.keySet());
//         String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
//                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
//                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
//                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        ArrayList<String> stavki = new ArrayList<String>();
        for(int i = 0; i < lst.size(); i++){
            stavki.addAll(map.get(lst.get(i)));
        }
        stavki.add(0, "");
        return stavki;
    }
    
    public static ArrayList<String> getStavki(String Restoran){
//        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
//        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
//                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
//                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
//                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        HashMap<String, ArrayList<String>> map = DataBase.DataBaseHelper.getAllRestorantsAndStavki();
        return map.get(Restoran);
    }
    
}
