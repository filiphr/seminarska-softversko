/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;
import java.util.Calendar;
import java.util.Random;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

/**
 *
 * @author igor
 */
public class CreateNotRandomDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String user = "isudijov1";
        DataBaseHelper.insertUser("Petar", "Filev", user, "filev@asdfa.asd", "filev");
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        for (int i = 0; i < 220; i++) {
            String datatmp = RandomDate.getRandomDate();
            String data[] = datatmp.split("-");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(data[0]));
            c.add(Calendar.MONTH, Integer.parseInt(data[1]));
            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            dayOfWeek--;
            Random r = new Random();
//            dayOfWeek = dayOfWeek * 2 + r.nextInt(2);
            if(dayOfWeek == 6){
                DataBaseHelper.insertArhiviraniGrupi(datatmp, user, restoraniHelp[6], stavkiHelp[6][r.nextInt(stavkiHelp[dayOfWeek].length)]);
            }            
        }
    }
}
