/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prediction;

import DataBase.DataBaseHelper;
import TestingWithj48.GettingClass;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;

/**
 *
 * @author igor
 */
public class BuildC45forAllEmployee {

    HashMap<String, J48> restoranPredict = new HashMap<String, J48>();
    HashMap<String, J48> stavkaPredict = new HashMap<String, J48>();
    String optionsForRestoran = new String();
    String optionsForStavki = new String();
    ArrayList<String> restorants = GettingClass.getRestorani();
    ArrayList<String> stavki = GettingClass.getStavki();
    ArrayList<Attribute> attrRestorans = new ArrayList<Attribute>();
    ArrayList<Attribute> attrAll = new ArrayList<Attribute>();

    public void BuildAllClassifiers() throws Exception {
        if (restoranPredict.size() == 0) {
            return;
        }
        ArrayList<String> keys = new ArrayList<String>(restoranPredict.keySet());
        for (int i = 0; i < keys.size(); i++) {
            BuildClassifier(keys.get(i));;
        }

    }

    public void add(String user) {
        restoranPredict.put(user, new J48());
        stavkaPredict.put(user, new J48());
    }

    public void addAndBuild(String user) throws Exception {
        add(user);
        BuildClassifier(user);
    }

    public void BuildClassifier(String user) throws Exception {
        J48 threeRestoran = restoranPredict.get(user);
        J48 threeStavka = stavkaPredict.get(user);
        threeRestoran.setOptions(Utils.splitOptions(optionsForRestoran));
        threeStavka.setOptions(Utils.splitOptions(optionsForStavki));
        Attribute attrRes = new Attribute("Restorants", restorants);
        Attribute attrStavki = new Attribute("Stavki", stavki);
        ArrayList<String> days = new ArrayList<String>();
        for (int j = 1; j <= 7; j++) {
            days.add(GettingClass.getDayByCalendar(j));
        }
        Attribute attrDays = new Attribute("denovi", days);
        attrRestorans.add(attrDays);
        attrRestorans.add(attrRes);
        attrAll.add(attrDays);
        attrAll.add(attrRes);
        attrAll.add(attrStavki);
        Instances dataAll = new Instances("Stavka", attrAll, 0);
        Instances dataRes = new Instances("Restoran", attrRestorans, 0);
        dataAll.setClass(attrStavki);
        dataRes.setClass(attrRes);
        List<List<String>> lst = DataBaseHelper.getVremeRestoranStavkaOdArhivirani(user);
        Calendar c = Calendar.getInstance();
        for (int j = 0; j < lst.get(0).size(); j++) {
            double value[] = new double[3];
            String vreme = lst.get(0).get(j);
            String restoran = lst.get(1).get(j);
            String stavka = lst.get(2).get(j);
            String vremesplit[] = vreme.split("-");
            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(vremesplit[0]));
            c.add(Calendar.MONTH, Integer.parseInt(vremesplit[1]));
            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(vremesplit[2]));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            String datum = GettingClass.getDayByCalendar(dayOfWeek);
            value[0] = days.indexOf(datum);
            value[1] = restorants.indexOf(restoran);
            value[2] = stavki.indexOf(stavka);
            dataAll.add(new DenseInstance(j, value));
            value = new double[2];
            value[0] = days.indexOf(datum);
            value[1] = restorants.indexOf(restoran);
            dataRes.add(new DenseInstance(j, value));
        }
        threeRestoran.buildClassifier(dataRes);
        threeStavka.buildClassifier(dataAll);
        restoranPredict.put(user, threeRestoran);
        stavkaPredict.put(user, threeStavka);
    }

    public void setOptionsforRestorants(String options) {
        optionsForRestoran = options;
    }

    public void setOptionsforStavki(String options) {
        optionsForStavki = options;
    }

    public String getPredictionByUserDay(String user, String day) throws Exception {
        Instances testRes = new Instances("testRess", attrRestorans, 0);
        double value[] = {GettingClass.getIndexByDay(day),0};
        testRes.setClassIndex(1);
        testRes.add(new DenseInstance(0,value));
        J48 three = restoranPredict.get(user);
        double restoran = three.classifyInstance(testRes.get(0));
        testRes = new Instances("testAll", attrAll, 0);
        double value1[] = {GettingClass.getIndexByDay(day),restoran,0};
        testRes.add(new DenseInstance(0,value1));
        testRes.setClassIndex(2);
        three = stavkaPredict.get(user);
        double stavka = three.classifyInstance(testRes.get(0));
        return restorants.get((int)restoran) + ";" + stavki.get((int)stavka);
    }
}
