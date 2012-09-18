/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author igor
 */
public class RestoranPredict {
    
    private String Vreme;
    private HashMap<String,String> userRestoran;
    private String Restoran;
    private String Kreator;
    private HashMap<String,String> userStavka;
    private String Stavka;

    public void setRestoran(String Restoran) {
        this.Restoran = Restoran;
    }

    public RestoranPredict(String Vreme, String Restoran, String Kreator,String Stavka) {
        this.Vreme = Vreme;
        this.Restoran = Restoran;
        this.Kreator = Kreator;
        this.Stavka = Stavka;
        this.userRestoran = new HashMap<String, String>();
        this.userStavka = new HashMap<String, String>();
    }

    public void setStavka(String Stavka) {
        this.Stavka = Stavka;
    }

    public String getStavka() {
        return Stavka;
    }

    public void setKreator(String Kreator) {
        this.Kreator = Kreator;
    }

    public void setUserStavka(HashMap<String, String> userStavka) {
        this.userStavka = userStavka;
    }

    public String getKreator() {
        return Kreator;
    }

    public HashMap<String, String> getUserStavka() {
        return userStavka;
    }

    public String getRestoran() {
        return Restoran;
    }

    public RestoranPredict(String Vreme, HashMap<String, String> userRestoran, String Restoran) {
        this.Vreme = Vreme;
        this.userRestoran = userRestoran;
        this.Restoran = Restoran;
    }

    public RestoranPredict(String Vreme, HashMap<String, String> userRestoran) {
        this.Vreme = Vreme;
        this.userRestoran = userRestoran;
    }

    public String getVreme() {
        return Vreme;
    }

    public HashMap<String, String> getUserRestoran() {
        return userRestoran;
    }

    public void setVreme(String Vreme) {
        this.Vreme = Vreme;
    }

    public void setUserRestoran(HashMap<String, String> userRestoran) {
        this.userRestoran = userRestoran;
    }
    
    public void putHash(String user, String Restoran){
        userRestoran.put(user, Restoran);
    }
    public void putHashStavka(String user, String Stavka){
        userStavka.put(user,Stavka);
    }
    
}
