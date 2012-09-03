/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestingWithj48;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author igor
 */
public class GettingClassForNotRandom {
    
    public static String getDayByCalendar(int i){
        String days[] = {"","nedela","ponedelnik", "vtornik", "sreda", "cetvrtok", "petok", "sabota", "nedela"};
        return days[i];
    }
    
    public static ArrayList<String> getRestorants(){
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        return new ArrayList<String>(Arrays.asList(restoraniHelp));
    }
    
    public static ArrayList<String> getStavki(){
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        ArrayList<String> lst = new ArrayList<String>();
        for(int  i = 0; i < stavkiHelp.length; i++)
            for(int j = 0; j < stavkiHelp[i].length; j++)
                lst.add(stavkiHelp[i][j]);
        return lst;
    }
    
    public static ArrayList<String> getStavki(int i){
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        return new ArrayList<String>(Arrays.asList(stavkiHelp[i]));
    }
    
}
