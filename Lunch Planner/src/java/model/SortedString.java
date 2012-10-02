/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import weka.filters.unsupervised.attribute.Obfuscate;

/**
 *
 * @author igor
 */
public class SortedString {
    
    public static ArrayList<String> getSorted(ArrayList<String> lst){
                ArrayList<String> returnvalue = new ArrayList<String>();
        ArrayList<Object> lstobj = new ArrayList<Object>();
        for(int i = 0; i < lst.size(); i++){
            Object1 tmp = new Object1();
            String strsplit[] = lst.get(i).split(";");
            for(int j = 0; j < strsplit.length - 1; j++){
                tmp.str.append(strsplit[j]+";");
            }
            tmp.p = Double.parseDouble((strsplit[strsplit.length - 1]).replace(',', '.'));
            lstobj.add(tmp);
        }
        Object1 lista[] = new Object1[lstobj.size()];
        for (int i = 0; i<lstobj.size(); i++)
        {
            lista[i] = (Object1) lstobj.get(i);
        }
        Arrays.sort(lista);

        for(int i = 0; i < lista.length; i++){
            returnvalue.add(lista[i].str + "" + Double.toString(lista[i].p));
        }
            return returnvalue;
    }

    
}


    class Object1 implements Comparable<Object1> {
        StringBuilder str = new StringBuilder();
        Double p;

    public int compareTo(Object1 o) {
        return Double.compare(o.p, this.p);
    }
    }
