/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
//import java.sql.Date;
//import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import model.OdrzuvanjePredict;
import model.Prediction;
import model.RestoranPredict;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author user
 */
public class DataBaseHelper {

    public static List<String> GetQuery(String query, int number) {
        String dbUrl = DataBaseSetup.getDbUrl();
        String driver = DataBaseSetup.getDbDriver();
        String user = DataBaseSetup.getDbUser();
        String pass = DataBaseSetup.getDbPassword();
        Connection conect = null;
        ResultSet rs = null;
        List<String> lst = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            Statement s = conect.createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(number) != null) {
                    lst.add(rs.getString(number));
                } else {
                    lst.add("");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        } finally {
            try {
                if (conect != null) {
                    conect.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

    public static int ExecuteQuery(String Query) {
        String dbUrl = DataBaseSetup.getDbUrl();
        String driver = DataBaseSetup.getDbDriver();
        String user = DataBaseSetup.getDbUser();
        String pass = DataBaseSetup.getDbPassword();
        Connection conect = null;
        int number = 0;
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            conect.setAutoCommit(false);
            Statement s = conect.createStatement();
            number = s.executeUpdate(Query);
            conect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            try {
                conect.rollback();
            } catch (SQLException sql) {
                System.out.println(e.getMessage());
            }
            //throw new RuntimeException(e);
        } finally {
            try {
                if (conect != null) {
                    conect.setAutoCommit(true);
                    conect.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return number;
    }
    
    public static int ExecuteQueryAndGetID(String Query) {
        String dbUrl = DataBaseSetup.getDbUrl();
        String driver = DataBaseSetup.getDbDriver();
        String user = DataBaseSetup.getDbUser();
        String pass = DataBaseSetup.getDbPassword();
        Connection conect = null;
        int number = 0;
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            conect.setAutoCommit(false);
            Statement s = conect.createStatement();
            number = s.executeUpdate(Query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()){
                number=rs.getInt(1);
            }
            rs.close();
            conect.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            try {
                conect.rollback();
            } catch (SQLException sql) {
                System.out.println(e.getMessage());
            }
            //throw new RuntimeException(e);
        } finally {
            try {
                if (conect != null) {
                    conect.setAutoCommit(true);
                    conect.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return number;
    }

    public static List<List<String>> getAllNamesSNamesUsersEmailsPass() throws SQLException {
        List<List<String>> lst = new ArrayList<List<String>>();
        for (int i = 1; i <= 5; i++) {
            lst.add(GetQuery("SELECT * FROM korisnik", i));
        }
        return lst;
    }
    
    public static String getUserName(String User)
    {
        List<String> str = GetQuery("select User from korisnik where User = '" + User + "'", 1);
        return str.get(0);
    }

    public static boolean CheckUser(String user, String password) {
        List<String> lst = GetQuery("select * from korisnik where User = '" + user + "'", 5);
        if (lst.isEmpty()) {
            return false;
        }
        if (password.equals(lst.get(0))) {
            return true;
        }
        return false;
    }

    public static List<String> getAllGroups() {
        List<String> lst = GetQuery("select * from tekovnagrupa", 1);
        return lst;
    }

    public static List<String> getRestoranAndVreme(int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("select * from tekovnagrupa where idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        List<String> Vreme = GetQuery(sqlStr.toString(), 2);
        List<String> Restoran = GetQuery(sqlStr.toString(), 4);
        List<String> lst = new ArrayList<String>();
        if (Restoran.isEmpty()) {
            lst.add("");
        } else {
            lst.add(Restoran.get(0));
        }
        if (Vreme.isEmpty()) {
            lst.add("");
        } else {
            lst.add(Vreme.get(0).substring(0, 5));
        }
        return lst;
    }

    public static List<String> getUserByGroup(int Group) {
        List<String> users = GetQuery("select naracka.Korisnik_User from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + Group, 1);
        if (users == null || users.isEmpty()) {
            return new ArrayList<String>();
        }
        return users;
    }

    public static List<List<String>> getNameSNameLunchAndKoments(int ID_Group) {
        List<List<String>> lst = new ArrayList<List<String>>();
        List<String> users = GetQuery("select * from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + ID_Group, 7);
        List<String> LucnhID = GetQuery("select * from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + ID_Group, 9);
        List<String> Komentar = GetQuery("select * from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + ID_Group, 6);
        List<String> Ime = new ArrayList<String>();
        List<String> Prezime = new ArrayList<String>();
        List<String> Lucnh = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++) {
            Ime.add(getUserIme(users.get(i)));
            Prezime.add(getUserPrezime(users.get(i)));
        }
        for (int i = 0; i < LucnhID.size(); i++) {
            Lucnh.add(IsInMeni((LucnhID.get(i))));
        }
        lst.add(Ime);
        lst.add(Prezime);
        lst.add(Lucnh);
        lst.add(Komentar);
        lst.add(users);
        return lst;
    }

    public static List<List<String>> getNameSNameAndLunch(int ID_Group) {
        List<List<String>> lst = new ArrayList<List<String>>();
        List<String> users = GetQuery("select naracka.Korisnik_User from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + ID_Group, 1);
        List<String> LucnhID = GetQuery("select StavkaMeni_Ime from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = " + ID_Group, 1);
        List<String> Ime = new ArrayList<String>();
        List<String> Prezime = new ArrayList<String>();
        List<String> Lucnh = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++) {
            Ime.add(getUserIme(users.get(i)));
            Prezime.add(getUserPrezime(users.get(i)));
        }
        for (int i = 0; i < LucnhID.size(); i++) {
            Lucnh.add(IsInMeni((LucnhID.get(i))));
        }
        lst.add(Ime);
        lst.add(Prezime);
        lst.add(Lucnh);
        lst.add(users);
        return lst;
    }

    public static String getRestaurantName(int ID_Group) {
        List<String> restaurant = GetQuery("select Restoran_Ime from tekovnagrupa where idTekovnaGrupa='" + ID_Group + "'", 1);
        if (!restaurant.isEmpty()) {
            return restaurant.get(0);
        }

        return new String();
    }

    //Filip go ima smeneto bidejki mi treba imeto na stavkata ne id-to
    public static List<String> getLunch(String User, int Grupa) {
        StringBuilder sqlStr = new StringBuilder("select stavkameni_Ime from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(Grupa);
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        if (lst == null) {
            return new ArrayList<String>();
        }
        if (lst.isEmpty()) {
            return new ArrayList<String>();
        }
        return lst;
    }

    public static String getPreferencesHour(String user) {
        List<String> lst = (GetQuery("select Vreme from preferences where Korisnik_User = '" + user + "'", 1));
        if (!lst.isEmpty() && !(lst.get(0).isEmpty())) {
            return lst.get(0).substring(0, 5);
        }
        return new String();
    }

    public static String getPreferencesKomentar(String user) {
        List<String> lst = (GetQuery("select Komentar from preferences where Korisnik_User = '" + user + "'", 1));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static List<String> getPreferencesParticipant(String user) {
        List<String> lst = GetQuery("select korisnik_User from korisnik_has_preferences where preferences_Korisnik_User = '" + user + "'", 1);
        return lst;
    }

    public static String getPreferencesMeal(String user) {
        List<String> lst = (GetQuery("select stavkameni_Ime from preferences WHERE Korisnik_User = '" + user + "'", 1));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static String getPreferencesRestoran(String user) {
        List<String> lst = (GetQuery("select * from preferences where Korisnik_User = '" + user + "'", 3));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static List<String> getAllUsernames() {
        List<String> lst = GetQuery("select * from korisnik", 3);
        return lst;
    }
    
    public static HashMap<String, ArrayList<String>> getAllRestorantsAndStavki(){
        HashMap<String, ArrayList<String>> resStavki = new HashMap<String, ArrayList<String>>();
        List<List<String>> values = new ArrayList<List<String>>();
        for(int i = 3; i < 5; i++){            
            values.add(GetQuery("select * from meni", i));
        }
        for(int i = 0; i < values.get(0).size(); i++){
            resStavki.put(values.get(0).get(i), new ArrayList<String>());
        }
        for(int i = 0; i < values.get(0).size(); i++){
            (resStavki.get(values.get(0).get(i))).add(values.get(1).get(i));
        }
        return resStavki;
    }

    public static List<String> getAllRestaurantNames() {
        List<String> lst = GetQuery("select * from restoran", 1);
        return lst;
    }

    public static List<String> getAllItems() {
        List<String> lst = GetQuery("SELECT * FROM stavkameni", 1);
        if (lst != null) {
            return lst;
        } else {
            return new ArrayList<String>();
        }
    }

    public static List<List<String>> getAllMenuItemsAndPrice(String RestaurantName) {
        List<List<String>> lst = new ArrayList<List<String>>();
        lst.add(GetQuery("select Ime from meni,stavkameni where Ime = StavkaMeni_Ime And Restoran_Ime = '" + RestaurantName + "'", 1));
        List<String> Ceni = GetQuery("select * from meni where Restoran_Ime = '" + RestaurantName + "'", 2);
        lst.add(Ceni);
        return lst;
    }

    public static String getUserIme(String username) {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 1));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static String getUserPrezime(String username) {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 2));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static String getUserEmail(String username) {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 4));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static String getUserLozinka(String username) {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 5));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }

    public static void insertUser(String ime, String prezime, String user, String email, String password) {
        if ((ime == null) || (prezime == null) || (user == null) || (email == null) || (password == null)) {
            return;
        }
        if (ime.isEmpty() || prezime.isEmpty() || user.isEmpty() || password.isEmpty()) {
            return;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO korisnik VALUES('");
        sqlStr.append(ime);
        sqlStr.append("', '");
        sqlStr.append(prezime);
        sqlStr.append("', '");
        sqlStr.append(user);
        sqlStr.append("', '");
        sqlStr.append(email);
        sqlStr.append("', '");
        sqlStr.append(password);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
        StringBuilder sqlStr1 = new StringBuilder("insert into preferences (Korisnik_User) VALUES('");
        sqlStr1.append(user);
        sqlStr1.append("' );");
        ExecuteQuery(sqlStr1.toString());
    }

    public static void insertMeni(String Cena, String Restoran, String Stavka) {
        if (Restoran == null || Cena == null || Stavka == null) {
            return;
        }
        if (Restoran.isEmpty() || Stavka.isEmpty()) {
            return;
        }
        String str = IsInMeni(Stavka);
        if (str == null || "".equals(str)) {
            insertStavkaMeni(Stavka);
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO meni (Cena,Restoran_Ime, StavkaMeni_Ime) VALUES('");
        sqlStr.append(Cena);
        sqlStr.append("', '");
        sqlStr.append(Restoran);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertRestoran(String Ime, String Adresa, String Telefon) {
        if (Ime == null || Adresa == null || Telefon == null) {
            return;
        }
        if (Ime.isEmpty()) {
            return;
        }
        ExecuteQuery("INSERT INTO restoran VALUES('" + Ime + "', '" + Adresa + "', '" + Telefon + "' );");
    }

    public static void insertStavkaMeni(String Ime) //throws SQLException
    {
        if (Ime == null) {
            return;
        }
        if (Ime.isEmpty()) {
            return;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO stavkameni (Ime) VALUES('");
        sqlStr.append(Ime);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertTekovnaGrupa(String Vreme, String User, String Restoran) {
        if (Vreme.isEmpty() || User.isEmpty() || Restoran.isEmpty()) {
            return;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO tekovnagrupa (Vreme, Korisnik_User, Restoran_Ime) VALUES('");
        sqlStr.append(Vreme);
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(Restoran);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }
    //insert into stavkameni (Ime) VALUES ('Burek');

    public static void insertNaracka(String User, int ID_Grupa, String Stavka) {
        if (User == null) {
            return;
        }
        if (User.isEmpty()) {
            return;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka (Korisnik_User, TekovnaGrupa_idTekovnaGrupa, stavkameni_Ime) VALUES('");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(ID_Grupa);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertNaracka(String User, int ID_Grupa, String Stavka, String Komentar, String Naracal) {
        if (User == null) {
            return;
        }
        if (User.isEmpty()) {
            return;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka (Komentar, Korisnik_User, TekovnaGrupa_idTekovnaGrupa, stavkameni_Ime,Naracal_User) VALUES('");
        sqlStr.append(Komentar);
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(ID_Grupa);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("', '");
        sqlStr.append(Naracal);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }
    //UPDATE dbsoftversko.preferences SET `Restoran_Ime` = 'Enriko' WHERE `Korisnik_User` = 'isudijovski';

    public static void updatePreferencesRestoran(String Restoran, String User) {
        if (!Restoran.isEmpty() && !(User.isEmpty())) {
            ExecuteQuery("UPDATE preferences SET Restoran_Ime = '" + Restoran + "' WHERE Korisnik_User = '" + User + "';");
        }
    }

    public static void updatePreferencesVreme(String Vreme, String User) {
        if (!Vreme.isEmpty() && !(User.isEmpty())) {
            ExecuteQuery("UPDATE preferences SET Vreme = '" + Vreme + "' WHERE Korisnik_User = '" + User + "';");
        }
    }

    public static void updatePreferencesStavka(String Stavka, String User) {
        if (!Stavka.isEmpty() && !(User.isEmpty())) {
            ExecuteQuery("UPDATE preferences SET StavkaMeni_Ime = '" + Stavka + "' WHERE Korisnik_User = '" + User + "';");
        }
    }

    public static void updatePreferencesKomentar(String Komentar, String User) {
        if (!Komentar.isEmpty() && !(User.isEmpty())) {
            ExecuteQuery("UPDATE preferences SET Komentar = '" + Komentar + "' WHERE Korisnik_User = '" + User + "';");
        }
    }

    public static void updatePreferences(String User, String Vreme, String Restoran, String Stavka, String Komentar) {
        if (Restoran != null && Vreme != null && Stavka != null && Komentar != null && User != null) {
            String str = "UPDATE preferences SET "
                    + "Restoran_Ime = '" + Restoran
                    + "' StavkaMeni_Ime = '" + Stavka
                    + "' Vreme = '" + Vreme
                    + "' WHERE Korisnik_User = '" + User + "';";
            ExecuteQuery(str);

        } else {
            getAllUsernames();
        }
    }

    public static void deleteUser(String User) {
        String sqlStr = "DELETE FROM korisnik WHERE User='" + User + "'";
        ExecuteQuery(sqlStr);
    }

    public static void updateUser(String ime, String prezime, String user, String lozinka, String mail) {
        StringBuilder sqlSqtr = new StringBuilder("UPDATE korisnik SET ");
        sqlSqtr.append("Ime='").append(ime).append("', ");
        sqlSqtr.append("Prezime='").append(prezime).append("', ");
        sqlSqtr.append("Email='").append(mail).append("', ");
        sqlSqtr.append("Password='").append(lozinka).append("' ");
        sqlSqtr.append("WHERE User='").append(user).append("'");
        ExecuteQuery(sqlSqtr.toString());
    }

    public static void deleteRestoran(String Ime) {
        String sqlStr = "DELETE FROM restoran WHERE Ime='" + Ime + "'";
        ExecuteQuery(sqlStr);
    }

    public static void deleteMeni(String Restoran, String Stavka) {
        String sqlStr = "DELETE FROM Meni WHERE Restoran_ime='" + Restoran + "' And StavkaMeni_Ime = '" + Stavka + "'";
        ExecuteQuery(sqlStr);
    }
    //UPDATE dbsoftversko.naracka SET `stavkameni_Ime` = 'Sendvic', `Komentar` = 'So Kecap' WHERE `idNaracka` = 1;

    public static void UpdateNaracka(int ID_Naracka, String Komentar, String Stavka) {
        ExecuteQuery("UPDATE naracka SET stavkameni_Ime = '" + Stavka + "' , Komentar = '" + Komentar + "' WHERE idNaracka = " + ID_Naracka);
    }

    public static int getIDNaracka(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("select idNaracka from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        int ID;
        if (!lst.isEmpty()) {
            ID = Integer.parseInt(lst.get(0));
        } else {
            ID = -1;
        }
        return ID;
    }

    //Filip ja ima napisano go zema komentarot koj user-ot go ostavil za svojata naracka vo soodvetnata grupa
    public static String getKomentar(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("select Komentar from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        String Komentar;
        if (!lst.isEmpty()) {
            Komentar = lst.get(0);
        } else {
            Komentar = new String();
        }
        return Komentar;
    }

    public static List<String> getKomentars(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("select * from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        List<String> lst = GetQuery(sqlStr.toString(), 2);
        if (lst.isEmpty()) {
            return new ArrayList<String>();
        } else {
            return lst;
        }
    }

    //Filip go ima napisano ova. gi vrakja site ID koi nekoj korisnik gi napravil vo opredelena grupa
    public static List<Integer> getIDNaracka2(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("select idNaracka from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        List<Integer> ID = new ArrayList<Integer>();
        if (!lst.isEmpty()) {
            for (int i = 0; i < lst.size(); i++) {
                String number = lst.get(i);
                ID.add(Integer.parseInt(number));
            }
        }

        return ID;
    }

    public static int getIDNaracka(String User, int ID_Grupa, String Stavka) {
        StringBuilder sqlStr = new StringBuilder("select idNaracka from naracka where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        sqlStr.append(" And stavkameni_Ime =  '");
        sqlStr.append(Stavka);
        sqlStr.append("'");
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        int ID;
        if (!lst.isEmpty()) {
            ID = Integer.parseInt(lst.get(0));
        } else {
            ID = -1;
        }
        return ID;
    }

    public static String getKomentarByStavka(String Stavka) {
        if (Stavka == null && Stavka.isEmpty()) {
            return new String();
        }
        StringBuilder sqlStr = new StringBuilder("select Komentar from naracka where stavkameni_Ime = '");
        sqlStr.append(Stavka);
        sqlStr.append("'");
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        String Komentar;
        if (!lst.isEmpty()) {
            Komentar = lst.get(0);
        } else {
            Komentar = new String();
        }
        return Komentar;
    }

    public static int getGroupIDFromCreator(String userID) {
        String str = "SELECT idTekovnaGrupa FROM tekovnagrupa WHERE Korisnik_User ='" + userID + "';";
        List<String> lst = GetQuery(str, 1);
        if (lst == null || lst.isEmpty()) {
            return -1;
        }
        return Integer.parseInt(lst.get(0));
    }

    public static String getVremeFromGroup(int groupID) {
        String str = "SELECT Vreme FROM tekovnagrupa WHERE idTekovnaGrupa='" + groupID + "';";
        List<String> lst = GetQuery(str, 1);
        return lst.get(0).substring(0, 5);
    }

    public static void deleteNaracka(int ID_Naracka) {
        ExecuteQuery("DELETE FROM naracka WHERE idNaracka = " + ID_Naracka);
    }

    public static void insertPokani(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO pokani VALUES('");
        sqlStr.append(User);
        sqlStr.append("', ");
        sqlStr.append(ID_Grupa);
        sqlStr.append(" );");
        ExecuteQuery(sqlStr.toString());
    }

    public static List<String> getAllPokani(String User) {
        if (User == null) {
            return new ArrayList<String>();
        }
        if (User.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> lst = GetQuery("select tekovnagrupa_idTekovnaGrupa from pokani where korisnik_User = '" + User + "'", 1);
        return lst;
    }

    public static void deletePokani(String User, int ID_Grupa) {
        StringBuilder sqlStr = new StringBuilder("DELETE FROM pokani WHERE korisnik_USer = '");
        sqlStr.append(User);
        sqlStr.append("' And tekovnagrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertPreferencesParticipant(String User, String participant) {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO korisnik_has_preferences VALUES('");
        sqlStr.append(participant);
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void deletePreferencesParticipant(String User, String participant) {
        StringBuilder sqlStr = new StringBuilder("DELETE FROM korisnik_has_preferences WHERE korisnik_User = '");
        sqlStr.append(participant);
        sqlStr.append("' And  preferences_Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("'");
        ExecuteQuery(sqlStr.toString());
    }

    public static void deleteTekovnaGrupa(int ID_Grupa) {
        ExecuteQuery("DELETE FROM tekovnagrupa where idTekovnaGrupa = " + ID_Grupa);
    }

    public static void deleteAllGroups() {
        ExecuteQuery("DELETE FROM tekovnagrupa");
    }

    public static void deleteAllNaracki() {
        ExecuteQuery("DELETE FROM naracka");
    }

    public static void insertArhiviraniGrupi(String User, String Stavka, int GroupID) {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO arhiviranagrupa (Korisnik_User, StavkaMeni, Grupa) VALUES('");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("', '");
        sqlStr.append(GroupID);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void clearPreferenceRestorant(String user) {
        if (user != null && !("".equals(user))) {
            String str = "UPDATE preferences SET  Restoran_Ime = null WHERE Korisnik_User = '" + user + "';";
            ExecuteQuery(str);
        }
    }

    public static void clearPreferenceMeal(String user) {
        if (user != null && !("".equals(user))) {
            String str = "UPDATE preferences SET  stavkameni_Ime = null WHERE Korisnik_User = '" + user + "';";
            ExecuteQuery(str);
        }
    }

    public static void clearPreferenceTime(String user) {
        if (user != null && !("".equals(user))) {
            String str = "UPDATE preferences SET  Vreme = null WHERE Korisnik_User = '" + user + "';";
            ExecuteQuery(str);
        }
    }

    public static void clearPreferenceKomentar(String user) {
        if (user != null && !("".equals(user))) {
            String str = "UPDATE preferences SET  Komentar = null WHERE Korisnik_User = '" + user + "';";
            ExecuteQuery(str);
        }
    }

    public static boolean IsNaracka(String user) {
        if (user == null) {
            return false;
        }
        if (user.isEmpty()) {
            return false;
        }
        String str = "select Korisnik_User from naracka where Korisnik_User = '" + user + "'";
        List<String> lst = GetQuery(str, 1);
        if (lst == null || lst.isEmpty()) {
            return false;
        }
        return true;


    }

    public static int getGroupOdNaracka(String User) {
        List<String> lst = GetQuery("select TekovnaGrupa_idTekovnaGrupa from naracka where Korisnik_User = '" + User + "'", 1);
        if (lst.isEmpty()) {
            return -1;
        } else {
            return Integer.parseInt(lst.get(0));
        }
    }

    public static String getRestoranAddress(String Restoran) {
        if (Restoran == null) {
            return new String();
        }
        if (Restoran.isEmpty()) {
            return new String();
        }
        List<String> lst = GetQuery("select Adresa from restoran where Ime = '" + Restoran + "'", 1);
        if (lst.isEmpty()) {
            return new String();
        }
        return lst.get(0);
    }

    public static String getRestoranTelefon(String Restoran) {
        if (Restoran == null) {
            return new String();
        }
        if (Restoran.isEmpty()) {
            return new String();
        }
        List<String> lst = GetQuery("select Telefon from restoran where Ime = '" + Restoran + "'", 1);
        if (lst.isEmpty()) {
            return new String();
        }
        return lst.get(0);
    }

    public static void deleteAllPokani() {
        ExecuteQuery("DELETE FROM pokani");
    }

    public static void deleteAllPokaniForUser(String User) {
        StringBuilder sqlStr = new StringBuilder("DELETE FROM pokani WHERE korisnik_USer = '");
        sqlStr.append(User);
        sqlStr.append("'");
        ExecuteQuery(sqlStr.toString());
    }

    public static boolean hasCreatedEvent(String userID) {
        List<String> lst = GetQuery("SELECT Korisnik_User FROM tekovnagrupa WHERE Korisnik_User = '" + userID + "';", 1);
        if (lst == null || lst.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static List<String> getParticipantBezJadenje() {
        List<String> Users = getAllUsernames();
        List<String> UsersSoJadenja = GetQuery("(select distinct(Korisnik_User) from naracka)", 1);
        if (UsersSoJadenja == null) {
            return Users;
        }
        if (UsersSoJadenja.isEmpty()) {
            return Users;
        }
        for (int i = 0; i < UsersSoJadenja.size(); i++) {
            for (int j = 0; j < Users.size(); j++) {
                if (Users.get(j).equals(UsersSoJadenja.get(i))) {
                    Users.remove(j);
                    break;
                }
            }
        }
        return Users;
    }

    public static String IsInMeni(String Stavka) {
        List<String> lst = (GetQuery("select Ime from stavkameni WHERE Ime = '" + Stavka + "'", 1));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }
    
    public static ArrayList<OdrzuvanjePredict> getOdrzuvanjePredict(String Restoran){
        List<List<String>> lstGrupa = new ArrayList<List<String>>();
        for(int i = 1; i <=5; i++){
            lstGrupa.add(GetQuery("select * from grupa where Restoran='" + Restoran + "'", i));
        }
        if(lstGrupa.get(0).isEmpty()) return new ArrayList<OdrzuvanjePredict>();
        StringBuilder mnozestvo = new StringBuilder();
        mnozestvo.append("( ");
        for(int i = 0; i < lstGrupa.get(1).size() - 1; i++){
            mnozestvo.append(lstGrupa.get(0).get(i)).append(", ");
        }
        mnozestvo.append(lstGrupa.get(0).get(lstGrupa.get(0).size() - 1)).append(" )");
        List<List<String>> participanti = new ArrayList<List<String>>();
        for(int i = 1; i <= 2; i++){
            participanti.add(GetQuery("select * from grupa_korisnik where grupa in " + mnozestvo.toString(), i));
        }
        HashMap<Integer, ArrayList<String>> participantiMap = new HashMap<Integer, ArrayList<String>>();
        for(int i = 0; i < participanti.get(0).size(); i++){
            participantiMap.put(Integer.parseInt(participanti.get(0).get(i)), new ArrayList<String>());
        }
        for(int i = 0; i < participanti.get(0).size(); i++){
            participantiMap.get(Integer.parseInt(participanti.get(0).get(i))).add(participanti.get(1).get(i));
        }
        ArrayList<OdrzuvanjePredict> returnvalue = new ArrayList<OdrzuvanjePredict>();
        for(int i = 0; i < lstGrupa.get(0).size(); i++){
            OdrzuvanjePredict od = new OdrzuvanjePredict(lstGrupa.get(2).get(i), lstGrupa.get(3).get(i), ("1".equals(lstGrupa.get(4).get(i))?true:false));
            od.setParticipanti(participantiMap.get(Integer.parseInt(lstGrupa.get(0).get(i))));
            returnvalue.add(od);
        }
        return returnvalue;
    }
    
    public static Prediction getPredictionObject(String date){
        Prediction predictObject = new Prediction();
        List<List<String>> grupaAll = new ArrayList<List<String>>();
        for(int i = 1; i <= 5; i++){
            grupaAll.add(GetQuery("select * from grupa", i));
        }
        ArrayList<String> grupaFiltered = new ArrayList<String>();
        for(int i = 0; i < grupaAll.get(0).size(); i++){
            if(grupaAll.get(3).get(i).contains(date.split(" ")[0])){
                grupaFiltered.add(grupaAll.get(0).get(i) + ";" + grupaAll.get(1).get(i));
                predictObject.RestKreator.add(grupaAll.get(1).get(i) + ";" + grupaAll.get(2).get(i));
            }
        }
        StringBuilder mnozestvo = new StringBuilder();
        mnozestvo.append("( ");
        for(int i = 0; i < grupaFiltered.size() - 1; i++){
            String[] split = grupaFiltered.get(i).split(";");
            mnozestvo.append(split[0]).append(", ");
        }
        String[] split = grupaFiltered.get(grupaFiltered.size() - 1).split(";");
        mnozestvo.append(split[0]).append(" )");
        grupaAll.clear();
        for(int i = 1; i < 3; i++){
            grupaAll.add(GetQuery("select * from grupa_korisnik where grupa in " + mnozestvo.toString(), i));
        }
        for(int i = 0; i < grupaAll.get(0).size(); i++){
            for(int j = 0; j < grupaFiltered.size(); j++){
                split = grupaFiltered.get(j).split(";");
                if(split[0].equals(grupaAll.get(0).get(i))){
                    predictObject.partiRes.add(grupaAll.get(1).get(i) + ";" + split[1]);
                    break;
                }
            }
        }
        grupaAll.clear();
        for(int i = 1; i <=4; i++){
            grupaAll.add(GetQuery("select * from arhiviranagrupa where Grupa in " + mnozestvo.toString(), i));
        }
        for(int i = 0; i < grupaAll.get(0).size(); i++){
            predictObject.partiStavka.add(grupaAll.get(3).get(i) + ";" + grupaAll.get(0).get(i));
        }
        return predictObject;
    }
    
    public static ArrayList<RestoranPredict> getRestoranPredict(String user){
        List<String> groupIDS = GetQuery("select Grupa from arhiviranagrupa where Korisnik_User='" + user + "'", 1);
        StringBuilder mnozestvo = new StringBuilder();
        mnozestvo.append("( ");
        for(int i = 0; i < groupIDS.size() - 1; i++){
            mnozestvo.append(groupIDS.get(i)).append(", ");
        }
        mnozestvo.append(groupIDS.get(groupIDS.size() - 1)).append(" )");
        int tmp[] = {1,2,3,4};
        int tmpStavki[] = {1,3,4};
        List<List<String>> lstgrupa = new ArrayList<List<String>>();
        for(int i = 0; i < tmp.length; i++){
            List<String> temp = GetQuery("select * from grupa where idGrupa in " + mnozestvo.toString(), tmp[i]);
            lstgrupa.add(temp);
        }
        List<List<String>> lstGrupa_Korisnik = new ArrayList<List<String>>();
        for(int i = 0; i < 2; i++){
            List<String> temp = GetQuery("select * from grupa_korisnik where grupa in " + mnozestvo.toString(), i+1);
            lstGrupa_Korisnik.add(temp);
        }
        HashMap<Integer,ArrayList<String>> idGrupaUser = new HashMap<Integer, ArrayList<String>>();
        HashMap<String,String> StavkaMeni = new HashMap<String, String>();
        List<List<String>> lstarhivirani = new ArrayList<List<String>>();
        for(int i = 0; i < tmpStavki.length; i++){
            List<String> temp = GetQuery("select * from arhiviranagrupa", tmpStavki[i]);
            lstarhivirani.add(temp);
        }
        for(int i = 0; i < lstarhivirani.get(0).size(); i++){
            String key = lstarhivirani.get(1).get(i)+";"+lstarhivirani.get(2).get(i);
            StavkaMeni.put(key, lstarhivirani.get(0).get(i));
        }
        for(int i = 0; i < lstGrupa_Korisnik.get(0).size(); i++){
            idGrupaUser.put(Integer.parseInt(lstGrupa_Korisnik.get(0).get(i)), new ArrayList<String>());
        }
        for(int i = 0; i < lstGrupa_Korisnik.get(0).size(); i++){
            idGrupaUser.get(Integer.parseInt(lstGrupa_Korisnik.get(0).get(i))).add(lstGrupa_Korisnik.get(1).get(i));
        }
        ArrayList<RestoranPredict> returnvalue = new ArrayList<RestoranPredict>();
        for(int i = 0; i < lstgrupa.get(0).size();i++){
            Integer idGroup = Integer.parseInt(lstgrupa.get(0).get(i));
            RestoranPredict respredict = new RestoranPredict(lstgrupa.get(3).get(i), lstgrupa.get(1).get(i),lstgrupa.get(2).get(i),StavkaMeni.get(idGroup + ";" + user));
            for(int j = 0; j < idGrupaUser.get(idGroup).size();j++){
                respredict.putHash(idGrupaUser.get(idGroup).get(j), lstgrupa.get(1).get(i));
                respredict.putHashStavka(idGrupaUser.get(idGroup).get(j), StavkaMeni.get(idGroup + ";" + idGrupaUser.get(idGroup).get(j)));
//                select StavkaMeni from arhiviranagrupa where Grupa=281 and Korisnik_User='fhrisafov12'
                /*List<String> stavkameni = GetQuery("select * from arhiviranagrupa where Grupa="+idGroup + " and Korisnik_User='" + idGrupaUser.get(idGroup).get(j) + "'", 1);
                if(!stavkameni.isEmpty()){
                    respredict.putHashStavka(idGrupaUser.get(idGroup).get(j), stavkameni.get(0));
                }  */              
            }
            returnvalue.add(respredict);
        }
        return returnvalue;
    }
    
    public static List<List<String>> getVremeRestoranStavkaOdArhivirani(String User) {
        if (User == null) {
            return new ArrayList<List<String>>();
        }
        if (User.isEmpty()) {
            return new ArrayList<List<String>>();
        }
        List<List<String>> lst = new ArrayList<List<String>>();
        for (int i = 1; i <= 3; i++) {
            List<String> tmp = GetQuery("SELECT Vreme, Restoran, StavkaMeni FROM arhiviranagrupa where Korisnik_user = '" + User + "' ORDER BY Vreme", i);
            if (tmp == null || tmp.isEmpty()) {
                return new ArrayList<List<String>>();
            }
            lst.add(tmp);
        }
        return lst;
    }

    public static void insertAdministrator(String User) {
        if (User == null) {
            return;
        }
        if (User.isEmpty()) {
            return;
        }
        ExecuteQuery("INSERT INTO administrator VALUES('" + User + "');");
    }

    public static boolean isAdministrator(String User) {
        if (User == null) {
            return false;
        }
        if (User.isEmpty()) {
            return false;
        }
        List<String> lst = GetQuery("select * from administrator where korisnik_User = '" + User + "'", 1);
        if (lst == null || lst.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void deleteAdministrator(String User) {
        if (User == null) {
            return;
        }
        if (User.isEmpty()) {
            return;
        }
        ExecuteQuery("DELETE FROM administrator where korisnik_User = '" + User + "'");
    }

    public static void insertNotofication(String Notification, String User) {
        if (Notification == null || User == null || Notification.isEmpty() || User.isEmpty()) {
            return;
        }
        ExecuteQuery("INSERT INTO notification (Notification, korisnik_User) VALUES('" + Notification + "', '" + User + "');");
    }

    public static void deleteNotification(String User) {
        if (User == null || User.isEmpty()) {
            return;
        }
        ExecuteQuery("DELETE FROM notification where korisnik_User = '" + User + "'");
    }

    public static List<String> getNotification(String User) {
        if (User == null || User.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> lst = GetQuery("select * from notification where korisnik_User = '" + User + "'", 2);
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<String>();
        }
        return lst;
    }

    public static void deleteGroup(int ID_Group) {
        ExecuteQuery("DELETE FROM tekovnagrupa where idTekovnaGrupa = " + ID_Group);
    }

    public static String getIDGrupa(String Restoran, String Vreme) {
        if (Restoran == null || Vreme == null || Restoran.isEmpty() || Vreme.isEmpty()) {
            return new String();
        }
        List<String> ID = GetQuery("select * from tekovnagrupa where Vreme = '" + Vreme + "' And Restoran_Ime = '" + Restoran + "'", 1);

        if (ID.isEmpty()) {
            return new String();
        }
        return ID.get(0);
    }

    public static List<String> getNarackiByNaracatel(String User) {
        if (User == null || User.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> lst = GetQuery("select distinct(Korisnik_User) from naracka where Naracal_User = '" + User + "'", 1);
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<String>();
        }
        return lst;
    }

    public static String getGroupCreator(int ID) {
        List<String> lst = GetQuery("select * from tekovnagrupa where idTekovnaGrupa = " + ID, 3);
        if (lst == null || lst.isEmpty()) {
            return new String();
        }
        return lst.get(0);
    }
    
    public static void Arhiviraj()
    {
        List<String> lst = getAllGroups();
        Calendar date = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String Today = format.format(date.getTime());
        for(int i = 0; i<lst.size(); i++)
        {
            int grupa = Integer.parseInt(lst.get(i));
            List<String> Ime = GetQuery("select naracka.Korisnik_User, Restoran_Ime, stavkameni_Ime from naracka,tekovnagrupa where TekovnaGrupa_idTekovnaGrupa = idTekovnaGrupa And idTekovnaGrupa = " + grupa, 1);
            List<String> Restoran = GetQuery("select naracka.Korisnik_User, Restoran_Ime, stavkameni_Ime from naracka,tekovnagrupa where TekovnaGrupa_idTekovnaGrupa = idTekovnaGrupa And idTekovnaGrupa = " + grupa, 2);
            List<String> Stavka = GetQuery("select naracka.Korisnik_User, Restoran_Ime, stavkameni_Ime from naracka,tekovnagrupa where TekovnaGrupa_idTekovnaGrupa = idTekovnaGrupa And idTekovnaGrupa = " + grupa, 3);
            for(int j = 0; j<Ime.size(); j++)
            {
              // DataBaseHelper.insertArhiviraniGrupi(Today, Ime.get(j), Restoran.get(j), Stavka.get(j));
            }
        }
        DataBaseHelper.deleteAllNaracki();
        DataBaseHelper.deleteAllGroups();
        DataBaseHelper.deleteAllPokani();
        DataBaseHelper.deleteAllNotification();
    }
    
    public static void deleteAllNotification()
    {
        ExecuteQuery("DELETE FROM notification");
    }

    public static int insertGrupa(String restoran, String kreator, String date, boolean odrzana) {
          StringBuilder sqlStr = new StringBuilder("INSERT INTO grupa (Restoran, Kreator, Vreme, Odrzana) VALUES('");
        sqlStr.append(restoran);
        sqlStr.append("', '");
        sqlStr.append(kreator);
        sqlStr.append("', '");
        sqlStr.append(date);
        sqlStr.append("', '");
        sqlStr.append((odrzana)?1:0);
        sqlStr.append("' );");
        
        //sqlStr.append("SELECT LAST_INSERT_ID();");
        
        
        int groupID = ExecuteQueryAndGetID(sqlStr.toString());
        
        return groupID;
    }

    public static void insertGrupaKorisnik(String kreator, int groupID) {
         StringBuilder sqlStr = new StringBuilder("INSERT INTO grupa_korisnik (korisnik, grupa) VALUES('");
        sqlStr.append(kreator);
        sqlStr.append("', '");
        sqlStr.append(groupID);
        sqlStr.append("' );");

       ExecuteQuery(sqlStr.toString());
        
    }
}
