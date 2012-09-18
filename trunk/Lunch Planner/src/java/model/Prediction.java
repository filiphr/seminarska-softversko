/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Prediction {
    public ArrayList<String> partiRes = new ArrayList<String>();
    public ArrayList<String> partiStavka = new ArrayList<String>();
    public ArrayList<String> RestKreator = new ArrayList<String>();
    
    public ArrayList<String> ParticipantForRestoran(String Restoran){
        ArrayList<String> returnvalue = new ArrayList<String>();
        for(int i = 0;i < partiRes.size(); i++){
            String[] splitstr = partiRes.get(i).split(";");
            if(splitstr[1].equals(Restoran)){
                returnvalue.add(splitstr[0]);
            }
        }
        return returnvalue;
    }
    
    public ArrayList<String> KreatosForRestoran(String Restoran){
        ArrayList<String> returnvalue = new ArrayList<String>();
        for(int i = 0;i < RestKreator.size(); i++){
            String[] splitstr = RestKreator.get(i).split(";");
            if(splitstr[0].equals(Restoran)){
                returnvalue.add(splitstr[1]);
            }
        }
        return returnvalue;
    }
    
}
