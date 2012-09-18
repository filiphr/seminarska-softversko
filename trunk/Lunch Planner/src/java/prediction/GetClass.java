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
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        HashMap<String, ArrayList<String>> rest = new HashMap<String, ArrayList<String>>();
        for(int i = 0; i < restoraniHelp.length; i++){
            ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(stavkiHelp[i]));
            rest.put(restoraniHelp[i], tmp);
        }
        return rest;
    }
    
    public static ArrayList<String> getRestoratns(){
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(restoraniHelp));
        lst.add(0, "");
        return lst;
    }
    
    public static ArrayList<String> getStavki(){
         String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        ArrayList<String> stavki = new ArrayList<String>();
        for(int i = 0; i < stavkiHelp.length; i++){
            stavki.addAll(Arrays.asList(stavkiHelp[i]));
        }
        stavki.add(0, "");
        return stavki;
    }
    
    public static ArrayList<String> getStavki(String Restoran){
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        ArrayList<String> restorants = new ArrayList<String>(Arrays.asList(restoraniHelp));
        int index = restorants.indexOf(Restoran);
        return new ArrayList<String>(Arrays.asList(stavkiHelp[index]));
    }
    
}
