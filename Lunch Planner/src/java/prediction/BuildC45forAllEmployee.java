/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prediction;

import DataBase.DataBaseHelper;
import TestingWithj48.GettingClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import model.OdrzuvanjePredict;
import model.Prediction;
import model.RestoranPredict;
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
    HashMap<String, J48> OdrzuvanjePredict = new HashMap<String, J48>();
    String optionsForOdrzuvanje = new String();
    String optionsForRestoran = new String();
    String optionsForStavki = new String();
    HashMap<String, ArrayList<String>> rest = GetClass.getResSoStavki();
    ArrayList<String> restorants = GetClass.getRestoratns();
    ArrayList<String> stavki = GetClass.getStavki();
    ArrayList<Attribute> attrRestorans = new ArrayList<Attribute>();
    ArrayList<Attribute> attrAll = new ArrayList<Attribute>();
    ArrayList<Attribute> attrOdrzuvanje = new ArrayList<Attribute>();

    public void BuildAllClassifiers() throws Exception {
        if (restoranPredict.isEmpty()) {
            return;
        }
        ArrayList<String> keys = new ArrayList<String>(restoranPredict.keySet());
        for (int i = 0; i < keys.size(); i++) {
            BuildClassifierForUser(keys.get(i));
        }

    }

    public void BuildRestoran() throws Exception {
        for (int i = 1; i < restorants.size(); i++) {
            OdrzuvanjePredict.put(restorants.get(i), new J48());
        }
        for (int i = 1; i < restorants.size(); i++) {
            BuildCalssifierForRestoran(restorants.get(i));
        }
    }

    public void add(String user) {
        restoranPredict.put(user, new J48());
        stavkaPredict.put(user, new J48());
    }

    public void addAndBuild(String user) throws Exception {
        add(user);
        BuildClassifierForUser(user);
    }

    public void BuildCalssifierForRestoran(String Restoran) throws Exception {
        attrOdrzuvanje.clear();
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        users.add(0, "");
        J48 three = OdrzuvanjePredict.get(Restoran);
        three.setOptions(Utils.splitOptions(optionsForOdrzuvanje));
        Attribute attrKreator = new Attribute("Kreator" + Restoran, users);
        Attribute attrdate = new Attribute("Date" + Restoran, "yyyy-MM-dd HH:mm:ss.SSS");
        attrOdrzuvanje.add(attrKreator);
        attrOdrzuvanje.add(attrdate);
        for (int i = 1; i < users.size(); i++) {
            attrOdrzuvanje.add(new Attribute("Participant" + i + Restoran, users));
        }
        String truefalse[] = {"false", "true"};
        Attribute attrOdr = new Attribute("Odrzuvanje" + Restoran, new ArrayList<String>(Arrays.asList(truefalse)));
        attrOdrzuvanje.add(attrOdr);
        Instances dataOdr = new Instances("dataOdrzuvanje", attrOdrzuvanje, 0);
        dataOdr.setClass(attrOdr);
        ArrayList<OdrzuvanjePredict> OdrzuvanjeList = DataBaseHelper.getOdrzuvanjePredict(Restoran);
        int i = 0;
        for (i = 0; i < OdrzuvanjeList.size(); i++) {
            double values[] = new double[dataOdr.numAttributes()];
            values[0] = users.indexOf(OdrzuvanjeList.get(i).getKreator());
            values[1] = dataOdr.attribute(1).parseDate(OdrzuvanjeList.get(i).getVreme());
            for (int valueindex = 2; valueindex < values.length - 1; valueindex++) {
                if (OdrzuvanjeList.get(i).getParticipanti().contains(users.get(valueindex - 1))) {
                    values[valueindex] = users.indexOf(users.get(valueindex - 1));
                } else {
                    values[valueindex] = 0;
                }
            }
            values[values.length - 1] = (OdrzuvanjeList.get(i).isOdrzuvanje())?1:0;
            dataOdr.add(new DenseInstance(1.0, values));
        }
        three.buildClassifier(dataOdr);
        OdrzuvanjePredict.put(Restoran, three);
    }

    public void BuildClassifierForUser(String user) throws Exception {
        attrAll.clear();
        attrRestorans.clear();
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
//        ArrayList<String> users = new ArrayList<String>(DataBaseHelper.getAllUsernames());
        users.add(0, "");
        J48 threeRestoran = restoranPredict.get(user);
        J48 threeStavka = stavkaPredict.get(user);
        threeRestoran.setOptions(Utils.splitOptions(optionsForRestoran));
        threeStavka.setOptions(Utils.splitOptions(optionsForStavki));
        //nominal
        Attribute attrRes = new Attribute("Resotrants" + user, restorants);
        //nominal
        Attribute attrStavki = new Attribute("Stavki" + user, stavki);
        //date
        Attribute attrdate = new Attribute("Date" + user, "yyyy-MM-dd HH:mm:ss.SSS");
        //relational
        Attribute attrusers = new Attribute("userst" + user, users);
        attrRestorans.add(attrdate);
        for (int i = 1; i < users.size(); i++) {
            attrRestorans.add(new Attribute("participantst" + i + user, users));
        }
        for (int i = 1; i < users.size(); i++) {
            attrRestorans.add(new Attribute("Restorantst" + i + user, restorants));
        }
        attrRestorans.add(attrRes);
        attrAll.add(attrdate);
        attrAll.add(attrRes);
        attrAll.add(attrusers);
        for (int i = 1; i < users.size(); i++) {
            attrAll.add(new Attribute("Stavkit" + i + user, stavki));
        }
        attrAll.add(attrStavki);
        Instances dataAll = new Instances("dataStavkat", attrAll, 0);
        Instances dataRes = new Instances("dataRestorant", attrRestorans, 0);
        dataAll.setClass(attrStavki);
        dataRes.setClass(attrRes);
        Instances dataAllTest = new Instances("dataStavkatest", attrAll, 0);
        Instances dataResTest = new Instances("dataRestorantest", attrRestorans, 0);
        dataAllTest.setClass(attrStavki);
        dataResTest.setClass(attrRes);
        ArrayList<RestoranPredict> restoranList = DataBaseHelper.getRestoranPredict(user);
        int i = 0;
        for (i = 0; i < restoranList.size(); i++) {
            double values[] = new double[dataRes.numAttributes()];
            values[0] = dataRes.attribute(0).parseDate(restoranList.get(i).getVreme());
            int valueind = 1;
            for (valueind = 1; valueind < users.size(); valueind++) {
                String restorantemp = restoranList.get(i).getUserRestoran().get(users.get(valueind));
                if (restorantemp == null) {
                    values[valueind] = 0;
                    values[valueind + users.size() - 1] = 0;
                } else {
                    values[valueind] = valueind;
                    values[valueind + users.size() - 1] = restorants.indexOf(restorantemp);
                }
            }
            values[values.length - 1] = restorants.indexOf(restoranList.get(i).getRestoran());
            dataRes.add(new DenseInstance(1.0, values));
            double valuesAll[] = new double[dataAll.numAttributes()];
            valuesAll[0] = dataAll.attribute(0).parseDate(restoranList.get(i).getVreme());
            valuesAll[1] = restorants.indexOf(restoranList.get(i).getRestoran());
            valuesAll[2] = users.indexOf(restoranList.get(i).getKreator());
            for (valueind = 3; valueind < valuesAll.length - 1; valueind++) {
                String Stavka = restoranList.get(i).getUserStavka().get(users.get(valueind - 2));
                if (Stavka == null) {
                    valuesAll[valueind] = 0;
                } else {
                    valuesAll[valueind] = stavki.indexOf(Stavka);
                }
            }
            valuesAll[valuesAll.length - 1] = stavki.indexOf(restoranList.get(i).getStavka());
            dataAll.add(new DenseInstance(1.0, valuesAll));
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

    public void setOptionsForOdrzuvanje(String options) {
        optionsForOdrzuvanje = options;
    }

    public String getPredictionByUserDay(String user, String day) throws Exception {
        Instances testRes = new Instances("testRess", attrRestorans, 0);
        double value[] = {GettingClass.getIndexByDay(day), 0};
        testRes.setClassIndex(1);
        testRes.add(new DenseInstance(0, value));
        J48 three = restoranPredict.get(user);
        double restoran = three.classifyInstance(testRes.get(0));
        testRes = new Instances("testAll", attrAll, 0);
        double value1[] = {GettingClass.getIndexByDay(day), restoran, 0};
        testRes.add(new DenseInstance(0, value1));
        testRes.setClassIndex(2);
        three = stavkaPredict.get(user);
        double stavka = three.classifyInstance(testRes.get(0));
        return restorants.get((int) restoran) + ";" + stavki.get((int) stavka);
    }
    
    public ArrayList<String> getPrediction(String date, String user) throws Exception{
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        users.add(0, "");
        Instances testData = new Instances("testData", attrRestorans, 0);
        testData.setClassIndex(testData.numAttributes() - 1);
        double values[] = new double[testData.numAttributes()];
            values[0] = testData.attribute(0).parseDate(date);
            int valueind = 1;
            Prediction object = DataBaseHelper.getPredictionObject(date);
            HashMap<String,String> map = new HashMap<String, String>();
            for(int i = 0; i < object.partiRes.size(); i++){
                String split[] = object.partiRes.get(i).split(";");
                map.put(split[0], split[1]);
            }
        for (valueind = 1; valueind < users.size(); valueind++) {
                String restorantemp = map.get(users.get(valueind));
                if (restorantemp == null) {
                    values[valueind] = 0;
                    values[valueind + users.size() - 1] = 0;
                } else {
                    values[valueind] = valueind;
                    values[valueind + users.size() - 1] = restorants.indexOf(restorantemp);
                }
            }
            values[values.length - 1] = 0;
            testData.add(new DenseInstance(1.0, values));
            double predicts[] = restoranPredict.get(user).distributionForInstance(testData.get(0));
            ArrayList<Double> indexof = new ArrayList<Double>();
            double sorted[] = new double[predicts.length];
            for(int i = 0; i < predicts.length; i++){
                sorted[i] = predicts[i];
                indexof.add(predicts[i]);
            }
            Arrays.sort(sorted);
            ArrayList<String> restorantsForTest = new ArrayList<String>();
            for(int i = sorted.length - 1; i >= 0; i--){
                if(sorted[i] == 0) break;
                //System.out.println(restorants.get(indexof.indexOf(sorted[i])) + " " + (sorted[i] * 100) + "%");
                restorantsForTest.add(restorants.get(indexof.indexOf(sorted[i])) + ";" + (sorted[i] * 100) + "%");
            }
            /*for(int i = 0; i < restorantsForTest.size(); i++){
                getPredictionRestoran(restorantsForTest.get(i), date);
                getPredictionStavka(restorantsForTest.get(i), date, user);
            }*/
            return restorantsForTest;
            
    }
    
    public void getPredictionStavka(String Restoran, String date, String user) throws Exception {
        ArrayList<String> returnvalue = new ArrayList<String>();
        Prediction object = DataBaseHelper.getPredictionObject(date);
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        users.add(0, "");
        Instances testData = new Instances("testData2", attrAll, 0);
        testData.setClassIndex(testData.numAttributes() - 1);
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < object.partiStavka.size(); i++) {
            String split[] = object.partiStavka.get(i).split(";");
            map.put(split[0], split[1]);
        }
        ArrayList<String> kreators = object.KreatosForRestoran(Restoran);
        for (int k = 0; k < kreators.size(); k++) {
            double valuesAll[] = new double[testData.numAttributes()];
            valuesAll[0] = testData.attribute(0).parseDate(date);
            valuesAll[1] = restorants.indexOf(Restoran);
            valuesAll[2] = users.indexOf(kreators.get(k));
            for (int valueind = 3; valueind < valuesAll.length - 1; valueind++) {
                String Stavka = map.get(users.get(valueind - 2));
                if (Stavka == null) {
                    valuesAll[valueind] = 0;
                } else {
                    valuesAll[valueind] = stavki.indexOf(Stavka);
                }
            }
            valuesAll[valuesAll.length - 1] = 0;
            testData.add(new DenseInstance(1.0, valuesAll));
            double predicts[] = stavkaPredict.get(user).distributionForInstance(testData.get(0));
            ArrayList<Double> indexof = new ArrayList<Double>();
            double sorted[] = new double[predicts.length];
            for(int i = 0; i < predicts.length; i++){
                sorted[i] = predicts[i];
                indexof.add(predicts[i]);
            }
            Arrays.sort(sorted);
            ArrayList<String> stavkiForRestoran = GetClass.getStavki(Restoran);
            for(int i = sorted.length - 1; i >= 0; i--){
                if(sorted[i] == 0) break;
                if(stavkiForRestoran.contains(stavki.get(indexof.indexOf(sorted[i])))){
//                    System.out.println(Restoran + " " + kreators.get(k) + " " + stavki.get(indexof.indexOf(sorted[i])) + " " + (sorted[i] * 100) + "%");
                    returnvalue.add(Restoran + ";" + kreators.get(k) + ";" + stavki.get(indexof.indexOf(sorted[i])) + ";" + (sorted[i] * 100) + "%");
                }                
            }
        }

    }
    
    public ArrayList<String> getPredictionRestoran(String Restoran, String date) throws Exception{
        ArrayList<String> returnvalue = new ArrayList<String>();
        Prediction object = DataBaseHelper.getPredictionObject(date);
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        users.add(0, "");
        Instances testData = new Instances("testData1", attrOdrzuvanje, 0);
        testData.setClassIndex(testData.numAttributes() - 1);
        ArrayList<String> participants = object.ParticipantForRestoran(Restoran);
        ArrayList<String> kreators = object.KreatosForRestoran(Restoran);
        for(int i = 0; i < kreators.size(); i++){
            double values[] = new double[testData.numAttributes()];
            values[0] = users.indexOf(kreators.get(i));
            values[1] = testData.attribute(1).parseDate(date);
            for (int valueindex = 2; valueindex < values.length - 1; valueindex++) {
                if (participants.contains(users.get(valueindex - 1))) {
                    values[valueindex] = users.indexOf(users.get(valueindex - 1));
                } else {
                    values[valueindex] = 0;
                }
            }
            values[values.length - 1] = 0;
            testData.add(new DenseInstance(1.0, values));

            double prediction[] = OdrzuvanjePredict.get(Restoran).distributionForInstance(testData.get(i));
            returnvalue.add(Restoran + ";" + kreators.get(i) + ";" + Double.toString(prediction[1] * 100) + "%");
        }
        return returnvalue;
    }
    
    public void getPrediction(HashMap<String,String> map, String date, String user) throws Exception{
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        Instances testData = new Instances("testData", attrRestorans, 0);
        testData.setClassIndex(testData.numAttributes() - 1);
        double values[] = new double[testData.numAttributes()];
            values[0] = testData.attribute(0).parseDate(date);
            int valueind = 1;
            for (valueind = 1; valueind < users.size(); valueind++) {
                String restorantemp = map.get(users.get(valueind));
                if (restorantemp == null) {
                    values[valueind] = 0;
                    values[valueind + users.size() - 1] = 0;
                } else {
                    values[valueind] = valueind;
                    values[valueind + users.size() - 1] = restorants.indexOf(restorantemp);
                }
            }
            values[values.length - 1] = 0;
            testData.add(new DenseInstance(1.0, values));
            double predicts[] = restoranPredict.get(user).distributionForInstance(testData.get(0));
            ArrayList<Double> indexof = new ArrayList<Double>();
            double sorted[] = new double[predicts.length];
            for(int i = 0; i < predicts.length; i++){
                sorted[i] = predicts[i];
                indexof.add(predicts[i]);
            }
            Arrays.sort(sorted);
            for(int i = sorted.length - 1; i >= 0; i--){
                if(sorted[i] == 0) break;
                System.out.println(restorants.get(indexof.indexOf(sorted[i])) + " " + (sorted[i] * 100) + "%");
            }
    }
    
    

    public void TestPrediction() throws Exception {
        TestPredictUsers();
        TestOdrzuvanje();
    }
    
    private void TestOdrzuvanje() throws Exception {
        int good = 0;
        int bad = 0;
        for(int i = 1; i < restorants.size(); i++){
            int tmp[] = TestOdrzuvanjeRestoran(restorants.get(i));
            good += tmp[0];
            bad +=tmp[1];
        }
        System.out.println(good + " " + bad);
    }
    
    private int[] TestOdrzuvanjeRestoran(String Restoran) throws Exception{
        attrOdrzuvanje.clear();
        int good = 0;
        int bad = 0;
        int returnvalue[] = new int[2];
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        users.add(0, "");
        J48 three = OdrzuvanjePredict.get(Restoran);
        three.setOptions(Utils.splitOptions(optionsForOdrzuvanje));
        Attribute attrKreator = new Attribute("Kreator" + Restoran, users);
        Attribute attrdate = new Attribute("Date" + Restoran, "yyyy-MM-dd HH:mm:ss.SSS");
        attrOdrzuvanje.add(attrKreator);
        attrOdrzuvanje.add(attrdate);
        for (int i = 1; i < users.size(); i++) {
            attrOdrzuvanje.add(new Attribute("Participant" + i + Restoran, users));
        }
        String truefalse[] = {"false", "true"};
        Attribute attrOdr = new Attribute("Odrzuvanje" + Restoran, new ArrayList<String>(Arrays.asList(truefalse)));
        attrOdrzuvanje.add(attrOdr);
        Instances dataOdr = new Instances("dataOdrzuvanje", attrOdrzuvanje, 0);
        dataOdr.setClass(attrOdr);
        Instances dataOdrTest = new Instances("dataOdrTest", attrOdrzuvanje, 0);
        dataOdrTest.setClass(attrOdr);
        ArrayList<OdrzuvanjePredict> OdrzuvanjeList = DataBaseHelper.getOdrzuvanjePredict(Restoran);
        int i = 0;
        for (i = 0; i < (int)(OdrzuvanjeList.size() * 0.95); i++) {
            double values[] = new double[dataOdr.numAttributes()];
            values[0] = users.indexOf(OdrzuvanjeList.get(i).getKreator());
            values[1] = dataOdr.attribute(1).parseDate(OdrzuvanjeList.get(i).getVreme());
            for (int valueindex = 2; valueindex < values.length - 1; valueindex++) {
                if (OdrzuvanjeList.get(i).getParticipanti().contains(users.get(valueindex - 1))) {
                    values[valueindex] = users.indexOf(users.get(valueindex - 1));
                } else {
                    values[valueindex] = 0;
                }
            }
            values[values.length - 1] = (OdrzuvanjeList.get(i).isOdrzuvanje())?1:0;
            dataOdr.add(new DenseInstance(1.0, values));
        }
        three.buildClassifier(dataOdr);
        for (; i < OdrzuvanjeList.size(); i++) {
            double values[] = new double[dataOdrTest.numAttributes()];
            values[0] = users.indexOf(OdrzuvanjeList.get(i).getKreator());
            values[1] = dataOdrTest.attribute(1).parseDate(OdrzuvanjeList.get(i).getVreme());
            for (int valueindex = 2; valueindex < values.length - 1; valueindex++) {
                if (OdrzuvanjeList.get(i).getParticipanti().contains(users.get(valueindex - 1))) {
                    values[valueindex] = users.indexOf(users.get(valueindex - 1));
                } else {
                    values[valueindex] = 0;
                }
            }
            values[values.length - 1] = (OdrzuvanjeList.get(i).isOdrzuvanje())?1:0;
            dataOdrTest.add(new DenseInstance(1.0, values));
        }
        for(int j = 0; j < dataOdrTest.numInstances(); j++){
            double index = three.classifyInstance(dataOdrTest.get(j));
            if(index == dataOdrTest.get(j).classValue()){
                good++;
            }else{
                bad++;
            }
        }
        returnvalue[0] = good;
        returnvalue[1] = bad;
        return returnvalue;
    }
    
    

    private void TestPredictUsers() throws Exception {
        int goodRestoran = 0;
        int badRestoran = 0;
        int goodStavka = 0;
        int badStavka = 0;
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
        for (int i = 0; i < users.size(); i++) {
            int goodbad[] = TestPredictUser(users.get(i));
            goodRestoran += goodbad[0];
            badRestoran += goodbad[1];
            goodStavka += goodbad[2];
            badStavka += goodbad[3];
        }
        System.out.println(goodRestoran + " " + badRestoran + " " + goodStavka + " " + badStavka);
    }

    private int[] TestPredictUser(String user) throws Exception {
        attrAll.clear();
        attrOdrzuvanje.clear();
        attrRestorans.clear();
        int goodRestoran = 0;
        int badRestoran = 0;
        int goodStavka = 0;
        int badStavka = 0;
        int returngoodbad[] = new int[4];
        ArrayList<String> users = new ArrayList<String>(restoranPredict.keySet());
//        ArrayList<String> users = new ArrayList<String>(DataBaseHelper.getAllUsernames());
        users.add(0, "");
        J48 threeRestoran = restoranPredict.get(user);
        J48 threeStavka = stavkaPredict.get(user);
        threeRestoran.setOptions(Utils.splitOptions(optionsForRestoran));
        threeStavka.setOptions(Utils.splitOptions(optionsForStavki));
        //nominal
        Attribute attrRes = new Attribute("Resotrants" + user, restorants);
        //nominal
        Attribute attrStavki = new Attribute("Stavki" + user, stavki);
        //date
        Attribute attrdate = new Attribute("Date" + user, "yyyy-MM-dd HH:mm:ss.SSS");
        //relational
        Attribute attrusers = new Attribute("userst" + user, users);
        attrRestorans.add(attrdate);
        for (int i = 1; i < users.size(); i++) {
            attrRestorans.add(new Attribute("participantst" + i + user, users));
        }
        for (int i = 1; i < users.size(); i++) {
            attrRestorans.add(new Attribute("Restorantst" + i + user, restorants));
        }
        attrRestorans.add(attrRes);
        attrAll.add(attrdate);
        attrAll.add(attrRes);
        attrAll.add(attrusers);
        for (int i = 1; i < users.size(); i++) {
            attrAll.add(new Attribute("Stavkit" + i + user, stavki));
        }
        attrAll.add(attrStavki);
        Instances dataAll = new Instances("dataStavkat", attrAll, 0);
        Instances dataRes = new Instances("dataRestorant", attrRestorans, 0);
        dataAll.setClass(attrStavki);
        dataRes.setClass(attrRes);
        Instances dataAllTest = new Instances("dataStavkatest", attrAll, 0);
        Instances dataResTest = new Instances("dataRestorantest", attrRestorans, 0);
        dataAllTest.setClass(attrStavki);
        dataResTest.setClass(attrRes);
        ArrayList<RestoranPredict> restoranList = DataBaseHelper.getRestoranPredict(user);
        int i = 0;
        for (i = 0; i < (int) (restoranList.size() * 0.9); i++) {
            double values[] = new double[dataRes.numAttributes()];
            values[0] = dataRes.attribute(0).parseDate(restoranList.get(i).getVreme());
            int valueind = 1;
            for (valueind = 1; valueind < users.size(); valueind++) {
                String restorantemp = restoranList.get(i).getUserRestoran().get(users.get(valueind));
                if (restorantemp == null) {
                    values[valueind] = 0;
                    values[valueind + users.size() - 1] = 0;
                } else {
                    values[valueind] = valueind;
                    values[valueind + users.size() - 1] = restorants.indexOf(restorantemp);
                }
            }
            values[values.length - 1] = restorants.indexOf(restoranList.get(i).getRestoran());
            dataRes.add(new DenseInstance(1.0, values));
            double valuesAll[] = new double[dataAll.numAttributes()];
            valuesAll[0] = dataAll.attribute(0).parseDate(restoranList.get(i).getVreme());
            valuesAll[1] = restorants.indexOf(restoranList.get(i).getRestoran());
            valuesAll[2] = users.indexOf(restoranList.get(i).getKreator());
            for (valueind = 3; valueind < valuesAll.length - 1; valueind++) {
                String Stavka = restoranList.get(i).getUserStavka().get(users.get(valueind - 2));
                if (Stavka == null) {
                    valuesAll[valueind] = 0;
                } else {
                    valuesAll[valueind] = stavki.indexOf(Stavka);
                }
            }
            valuesAll[valuesAll.length - 1] = stavki.indexOf(restoranList.get(i).getStavka());
            dataAll.add(new DenseInstance(1.0, valuesAll));
        }
        threeRestoran.buildClassifier(dataRes);
        threeStavka.buildClassifier(dataAll);
        for (; i < restoranList.size(); i++) {
            double values[] = new double[dataResTest.numAttributes()];
            values[0] = dataResTest.attribute(0).parseDate(restoranList.get(i).getVreme());
            for (int valueind = 1; valueind < users.size(); valueind++) {
                String restorantemp = restoranList.get(i).getUserRestoran().get(users.get(valueind));
                if (restorantemp == null) {
                    values[valueind] = 0;
                    values[valueind + users.size() - 1] = 0;
                } else {
                    values[valueind] = valueind;
                    values[valueind + users.size() - 1] = restorants.indexOf(restorantemp);
                }
            }
            values[values.length - 1] = restorants.indexOf(restoranList.get(i).getRestoran());
            dataResTest.add(new DenseInstance(1.0, values));
            double valuesAll[] = new double[dataAllTest.numAttributes()];
            valuesAll[0] = dataAllTest.attribute(0).parseDate(restoranList.get(i).getVreme());
            valuesAll[1] = restorants.indexOf(restoranList.get(i).getRestoran());
            valuesAll[2] = users.indexOf(restoranList.get(i).getKreator());
            for (int valueind = 3; valueind < valuesAll.length - 1; valueind++) {
                String Stavka = restoranList.get(i).getUserStavka().get(users.get(valueind - 2));
                if (Stavka == null) {
                    valuesAll[valueind] = 0;
                } else {
                    valuesAll[valueind] = stavki.indexOf(Stavka);
                }
            }
            valuesAll[valuesAll.length - 1] = stavki.indexOf(restoranList.get(i).getStavka());
            dataAllTest.add(new DenseInstance(1.0, valuesAll));
        }
        for (int ind = 0; ind < dataResTest.numInstances(); ind++) {
            double index = threeRestoran.classifyInstance(dataResTest.get(ind));
            if (index == dataResTest.get(ind).classValue()) {
                goodRestoran++;
            } else {
                badRestoran++;
            }
        }
        for (int ind = 0; ind < dataAllTest.numInstances(); ind++) {
            double index = threeStavka.classifyInstance(dataAllTest.get(ind));
            if (index == dataAllTest.get(ind).classValue()) {
                goodStavka++;
            } else {
                badStavka++;
            }
        }
        returngoodbad[0] = goodRestoran;
        returngoodbad[1] = badRestoran;
        returngoodbad[2] = goodStavka;
        returngoodbad[3] = badStavka;
        return returngoodbad;
    }
}
