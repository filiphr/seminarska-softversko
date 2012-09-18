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
public class OdrzuvanjePredict {
    
    private ArrayList<String> participanti;
    private String Kreator;
    private String Vreme;
    private boolean odrzuvanje;    

    public OdrzuvanjePredict(String Kreator, String Vreme, boolean odrzuvanje) {
        this.Kreator = Kreator;
        this.Vreme = Vreme;
        this.odrzuvanje = odrzuvanje;
        participanti = new ArrayList<String>();
    }
    
    public void addElement(String user){
        participanti.add(user);
    }

    public void setParticipanti(ArrayList<String> participanti) {
        this.participanti = participanti;
    }

    public void setKreator(String Kreator) {
        this.Kreator = Kreator;
    }

    public void setVreme(String Vreme) {
        this.Vreme = Vreme;
    }

    public void setOdrzuvanje(boolean odrzuvanje) {
        this.odrzuvanje = odrzuvanje;
    }

    public ArrayList<String> getParticipanti() {
        return participanti;
    }

    public String getKreator() {
        return Kreator;
    }

    public String getVreme() {
        return Vreme;
    }

    public boolean isOdrzuvanje() {
        return odrzuvanje;
    }
}
