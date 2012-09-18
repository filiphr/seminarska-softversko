/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;
import java.util.Calendar;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import org.omg.PortableInterceptor.USER_EXCEPTION;

/**
 *
 * @author igor
 */
public class CreateNotRandomDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String user = "isudijov1";
//        DataBaseHelper.insertUser("Petar", "Filev", "filev", "filev@asdfa.asd", "filev");
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};
        String[][] stavkiHelp = {{"Капричиоза", "Маргарита", "Кватро Формаџи"}, {"Кватро Стаџоне", "Рамстек"}, {"Бифтек", "Бурек",
                "Симит погача"}, {"Сарма", "Пастрмка"}, {"Шопска салата", "Цезар салата"}, {"Македонска салата",
                "Шеф салата"}, {"Вешалица", "Кременадла"}, {"Шарска", "Плескавица", "Колбаси"}, {"Ражнич", "Пилешки стек"}, {"Увијач",
                "Пастрмајлија"}, {"Пача", "Качамак"}, {"Тавче гравче", "Мусака", "Гулаш"}, {"Полнети пиперки", "Шкембе чорба", "Телешка чорба"}, {"Топено сирење", "Пржен сладолед"}};
        int minutes[] = {0, 15, 30, 45};
        String[][] users = new String[4][15];
        for (int i = 1; i < 16; i++) {
            InsertUserInDB(i, users);
        }
        Random r = new Random();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 6);
        c.set(Calendar.YEAR, 2012);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, RandomDate.randBetween(9, 17));
        c.set(Calendar.MINUTE, minutes[r.nextInt(minutes.length)]);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        DataBaseHelper.insertGrupa("asdfa", "filev", sdf.format(c.getTime()), true);
        int count = 500;
        while (count-- != 0) {
            int groups = RandomDate.randBetween(6, 8);
            ArrayList<ArrayList<String>> userstemp = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < users.length; i++) {
                userstemp.add(new ArrayList(Arrays.asList(users[i])));
            }
            for (int i = 0; i < groups; i++) {
                String resStav = getResStavka(i % 4, restoraniHelp);
                int indexRestoran = IndexOf(restoraniHelp, resStav);
                if(userstemp.get(i % 4).isEmpty()) {
                    continue;
                }
                int participants = r.nextInt(userstemp.get(i % 4).size());
                c.set(Calendar.HOUR_OF_DAY, RandomDate.randBetween(9, 17));
                c.set(Calendar.MINUTE, minutes[r.nextInt(minutes.length)]);
                System.out.println(resStav + " " + userstemp.get(i%4).get(participants)+ " " + sdf.format(c.getTime()) + " " + Boolean.toString((participants > 3) ? true : false));
//                int groupID = DataBaseHelper.insertGrupa(resStav, userstemp.get(i%4).get(participants), sdf.format(c.getTime()), (participants > 3) ? true : false);
//                DataBaseHelper.insertArhiviraniGrupi(userstemp.get(i%4).get(participants), stavkiHelp[indexRestoran][r.nextInt(stavkiHelp[indexRestoran].length)], groupID);
//                DataBaseHelper.insertGrupaKorisnik(userstemp.get(i%4).get(participants), groupID);
                System.out.println(userstemp.get(i%4).get(participants) + " " + stavkiHelp[indexRestoran][r.nextInt(stavkiHelp[indexRestoran].length)]);
                userstemp.get(i % 4).remove(participants);
                for (int participan = 1; participan < participants; participan++) {
                    int temp = r.nextInt(userstemp.get(i % 4).size());
                    System.out.println(userstemp.get(i%4).get(participants) + " " + stavkiHelp[indexRestoran][r.nextInt(stavkiHelp[indexRestoran].length)]);
//                    DataBaseHelper.insertArhiviraniGrupi(userstemp.get(i%4).get(temp), stavkiHelp[indexRestoran][r.nextInt(stavkiHelp[indexRestoran].length)], groupID);
//                    DataBaseHelper.insertGrupaKorisnik(userstemp.get(i%4).get(temp), groupID);
                    userstemp.get(i % 4).remove(temp);
                }
            }
            c.add(Calendar.DATE, 1);
            c.set(Calendar.HOUR_OF_DAY, RandomDate.randBetween(9, 17));
            c.set(Calendar.MINUTE, minutes[r.nextInt(minutes.length)]);
        }
//        for (int i = 0; i < 220; i++) {
//            String datatmp = RandomDate.getRandomDate();
//            String data[] = datatmp.split("-");
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(data[0]));
//            c.add(Calendar.MONTH, Integer.parseInt(data[1]));
//            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
//            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//            dayOfWeek--;
//            Random r = new Random();
////            dayOfWeek = dayOfWeek * 2 + r.nextInt(2);
//            if(dayOfWeek == 6){
////                DataBaseHelper.insertArhiviraniGrupi(datatmp, user, restoraniHelp[6], stavkiHelp[6][r.nextInt(stavkiHelp[dayOfWeek].length)]);
//            }            
//        }
    }

    public static String getResStavka(int i, String[] res) {
        Random r = new Random();
        int predict[] = {0, 4, 3, 8, 6, 10, 9, 13};
        int num = RandomDate.randBetween(predict[i * 2], predict[i * 2 + 1]);
        return res[num];
    }

    private static void InsertUserInDB(int i, String[][] users) {
//        DataBaseHelper.insertUser("Филип" + i, "Хрисафов" + i, "fhrisafov" + i, "filip.hrisfov@gmail.com", "fhrisafov" + i);
//        DataBaseHelper.insertUser("Игор" + i, "Судијовски" + i, "isudijovski" + i, "igor.sudijovski@gmail.com", "isudijovski" + i);
//        DataBaseHelper.insertUser("Ѓорѓи" + i, "Маџаров" + i, "gmadzarov" + i, "gjorgji.madzarov@gmail.com", "gmadzarov" + i);
//        DataBaseHelper.insertUser("Дејан" + i, "Ѓорѓевиќ" + i, "dgjorgjevik" + i, "dejan.gjorgjevikj@gmail.com", "dgjorgjevikj" + i);
        users[0][i - 1] = "fhrisafov" + i;
        users[1][i - 1] = "isudijovski" + i;
        users[2][i - 1] = "gmadzarov" + i;
        users[3][i - 1] = "dgjorgjevik" + i;
    }

    private static int IndexOf(String[] list, String value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
