/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prediction;

import TestingWithj48.GettingClass;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author igor
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BuildC45forAllEmployee b = new BuildC45forAllEmployee();
        b.setOptionsforRestorants("-C 0.25 -M 2");
        b.setOptionsforStavki("-C 0.25 -M 2");
        b.setOptionsForOdrzuvanje("-C 0.25 -M 2");
        List<String> users = DataBase.DataBaseHelper.getAllUsernames();
        for(int i = 0; i < users.size(); i++){
            b.add(users.get(i));
        }
        b.BuildRestoran();
        b.BuildAllClassifiers();
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("fhrisafov5", "Маракана");
        map.put("fhrisafov6", "Маракана");
        map.put("fhrisafov7", "Маракана");
        map.put("fhrisafov3", "Маракана");
        map.put("isudijovski2", "Мартини");
        map.put("isudijovski5", "Мартини");
        map.put("gmadzarov10", "Лира");
        map.put("gmadzarov11", "Лира");
        map.put("gmadzarov9", "Лира");
        map.put("gmadzarov5", "Лира");
        map.put("gmadzarov2", "Лира");
        map.put("gmadzarov4", "Лира");
        map.put("fhrisafov1", "Буре");
        map.put("fhrisafov2", "Буре");
        map.put("fhrisafov10", "Буре");
        map.put("fhrisafov9", "Буре");
        map.put("fhrisafov11", "Буре");
        map.put("fhrisafov13", "Буре");
//        fhrisafov1, 2013-11-12 11:15:00.000
        b.getPrediction("2013-11-12 11:15:00.000" , "fhrisafov1");
        
//        System.out.println((int)(11 * 0.9));
        
//        b.addAndBuild("isudijov1");
//        System.out.println(b.getPredictionByUserDay("isudijov1", GettingClass.getDayByCalendar(7)));
//        List<List<String>> lst = DataBase.DataBaseHelper.getVremeRestoranStavkaOdArhivirani("isudijov1");
//        Calendar c = Calendar.getInstance();
//        ArrayList<ArrayList<String>> lst1 = new ArrayList<ArrayList<String>>();
//        for(int i = 0; i < 7; i++){
//            lst1.add(new ArrayList<String>());
//        }
//        for(int i = 0; i < lst.get(0).size(); i++){
//            String vreme = lst.get(0).get(i);
//            String restoran = lst.get(1).get(i);
//            String stavka = lst.get(2).get(i);
//            String vremesplit[] = vreme.split("-");
//            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(vremesplit[0]));
//            c.add(Calendar.MONTH, Integer.parseInt(vremesplit[1]));
//            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(vremesplit[2]));
//            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//            lst1.get(dayOfWeek-1).add(restoran+";"+stavka);
//        }
//        for(int i = 0; i < 7; i++){
//            System.out.println(GettingClass.getDayByCalendar(i+1));
//            for(int j = 0; j < lst1.get(i).size(); j++){
//                System.out.println(lst1.get(i).get(j));
//            }
//        }
            
        
        // TODO code application logic here
//        System.out.println(GettingClass.getIndexByDay(GettingClass.getDayByCalendar(2)));
    }
}
