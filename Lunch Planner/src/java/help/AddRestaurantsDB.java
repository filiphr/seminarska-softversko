/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import DataBase.DataBaseHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import prediction.GetClass;

/**
 *
 * @author Filip
 */
public class AddRestaurantsDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<String, ArrayList<String>> resSoStavki = GetClass.getResSoStavki();
        
        ArrayList<String> stavki = new ArrayList<String>();
        for(ArrayList<String> stavka : resSoStavki.values())
        {
            stavki.addAll(stavka);
        }
        System.out.println(stavki.size());
//        insertRestaurants(resSoStavki);
//        insertStavki(resSoStavki);
//        insertMeni(resSoStavki);
    }

    private static void insertMeni(HashMap<String, ArrayList<String>> resSoStavki) {
        for (Map.Entry<String, ArrayList<String>> pair: resSoStavki.entrySet())
        {
            String restoran = pair.getKey();
            Random r = new Random();
            ArrayList<String> stavki = pair.getValue();
            for (String stavka : stavki)
            {
                int cena = 40 + r.nextInt(260);
                DataBaseHelper.insertMeni(String.valueOf(cena), restoran, stavka);
            }
        }
    }

    private static void insertStavki(HashMap<String, ArrayList<String>> resSoStavki) {
        ArrayList<String> stavki = new ArrayList<String>();
        for(ArrayList<String> stavka : resSoStavki.values())
        {
            stavki.addAll(stavka);
        }
        for(String stavka : stavki)
        {
            DataBaseHelper.insertStavkaMeni(stavka);
        }
    }

    private static void insertRestaurants(HashMap<String, ArrayList<String>> resSoStavki) {
        ArrayList<String> restorani = new ArrayList<String>(resSoStavki.keySet());
        for (String res : restorani)
        {
            DataBaseHelper.insertRestoran(res, res+"1", "070123456");
        }
    }
}
