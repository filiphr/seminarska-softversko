/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestingWithj48;

/**
 *
 * @author igor
 */

import java.util.ArrayList;
import java.util.Calendar;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.Utils;
import DataBase.DataBaseHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.crypto.Data;
import weka.core.DenseInstance;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        J48 c45three = new J48();
        c45three.setOptions(Utils.splitOptions("-C 0.25 -M 2"));
        ArrayList<String> restorants = GettingClassForNotRandom.getRestorants();
        Attribute attrRes = new Attribute("Restorants", restorants);
        ArrayList<String> stavki = GettingClassForNotRandom.getStavki();
        Attribute attrStav = new Attribute("Stavki", stavki);
        ArrayList<String> days = new ArrayList<String>();
        for(int i = 1; i <= 7; i++)
            days.add(GettingClass.getDayByCalendar(i));
        Attribute attrdays = new Attribute("denovi", days);
        ArrayList<Attribute> attr = new ArrayList<Attribute>();
        attr.add(attrdays);
        attr.add(attrRes);
        attr.add(attrStav);
        Instances data = new Instances("user", attr, 0);
        data.setClass(attrStav);
        List<List<String>> lst = DataBaseHelper.getVremeRestoranStavkaOdArhivirani("pfilev");
        Calendar c = Calendar.getInstance();
        for(int i = 0; i < lst.get(0).size(); i++){
            double value[] = new double[3];
            String vreme = lst.get(0).get(i);
            String restoran = lst.get(1).get(i);
            String stavka = lst.get(2).get(i);
            String vremesplit[] = vreme.split("-");
            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(vremesplit[0]));
            c.add(Calendar.MONTH, Integer.parseInt(vremesplit[1]));
            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(vremesplit[2]));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            String datum = GettingClass.getDayByCalendar(dayOfWeek);
            value[0] = days.indexOf(datum);
            value[1] = restorants.indexOf(restoran);
            value[2] = stavki.indexOf(stavka);
            data.add(new DenseInstance(i, value));
        }
        c45three.buildClassifier(data);
        Instances test = new Instances("test", attr, 0);
        double val[] = {2,5,stavki.indexOf(GettingClassForNotRandom.getStavki(5).get(1))};
        test.add(new DenseInstance(0,val));
        test.setClass(attrStav);
        System.out.println(c45three.classifyInstance(test.get(0)));
        System.out.println(test.get(0).classValue());
        
        // TODO code application logic here
    }
}
