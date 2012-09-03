/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestingWithj48;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author igor
 */
public class GettingClass {

    public static ArrayList<String> getStavki() {

        ArrayList<String> stavkiHelp = GettingClassForNotRandom.getStavki();

        ArrayList<String> stavki = new ArrayList<String>();

        for (int i = 0; i < stavkiHelp.size(); i++) {
            String ime = stavkiHelp.get(i);
            for (int j = 0; j < 3; j++) {
                stavki.add(stavkiHelp.get(i) + Integer.toString(j + 1));
            }
        }
        for(int i = 0; i < stavkiHelp.size(); i++)
            stavki.add(stavkiHelp.get(i));
        return stavki;
    }

    public static ArrayList<String> getRestorani() {
        String[] restoraniHelp = {"Енрико", "Буре", "Маракана", "Костарика", "Занзибар", "Мартини", "Балканика", "Лира", "Дојрана", "Национал", "Дуомо", "Воденица", "Буре Битола", "Метро"};

        ArrayList<String> restorani = new ArrayList<String>();

        for (int i = 0; i < restoraniHelp.length; i++) {
            for (int j = 0; j < 5; j++) {
                restorani.add(restoraniHelp[i] + Integer.toString(j + 1));
            }
        }
        for(int i = 0; i < restoraniHelp.length; i++)
            restorani.add(restoraniHelp[i]);
        return restorani;
    }

    public static ArrayList<String> getUsers() {
        ArrayList<String> korisnici = new ArrayList<String>();
        for (int i = 1; i < 51; i++) {
            korisnici.add("fhrisafov" + i);
            korisnici.add("isudijovski" + i);
            korisnici.add("gmadzarov" + i);
            korisnici.add("dgjorgjevik" + i);
        }
        return korisnici;
    }
    
    public static String getDayByCalendar(int i){
        String days[] = {"","nedela","ponedelnik", "vtornik", "sreda", "cetvrtok", "petok", "sabota"};
        return days[i];
    }
    
    public static int getIndexByDay(String day){
        String days[] = {"nedela","ponedelnik", "vtornik", "sreda", "cetvrtok", "petok", "sabota"};
//        ArrayList<String> lst = new ArrayList<String>(days);
        List<String> lst = Arrays.asList(days);
        return (lst.indexOf(day));
    }
    
}
