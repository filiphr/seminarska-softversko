/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DataBaseHelper {

    public static List<String> GetQuery(String query, int number) {
        String dbUrl = "jdbc:mysql://localhost:3306/dbsoftversko";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "";
        Connection conect = null;
        ResultSet rs = null;
        List<String> lst = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            Statement s = conect.createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                lst.add(rs.getString(number));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        String dbUrl = "jdbc:mysql://localhost:3306/dbsoftversko";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "";
        Connection conect = null;
        int number = 0;
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            Statement s = conect.createStatement();
            number = s.executeUpdate(Query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        } finally {
            try {
                conect.close();
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

    public static List<String> GetAllGroups() {
        List<String> lst = GetQuery("select * from tekovnagrupa", 1);
        return lst;
    }

    public static List<List<String>> getNameSNameAndLunch(int ID_Group) {
        List<List<String>> lst = new ArrayList<List<String>>();
        List<String> users = GetQuery("select Korisnik_User from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = '" + ID_Group + "'", 0);
        List<String> LucnhID = GetQuery("select StavkaMeni_Ime from tekovnagrupa,naracka where idTekovnaGrupa = TekovnaGrupa_idTekovnaGrupa And idTekovnaGrupa = '" + ID_Group + "'", 0);
        List<String> Ime = new ArrayList<String>();
        List<String> Prezime = new ArrayList<String>();
        List<String> Lucnh = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++) {
            Ime.add(getUserIme(users.get(i)));
            Prezime.add(getUserPrezime(users.get(i)));
        }
        for (int i = 0; i < LucnhID.size(); i++) {
            Lucnh.add(getLunch(LucnhID.get(i)));
        }
        lst.add(Ime);
        lst.add(Prezime);
        lst.add(Lucnh);
        return lst;
    }

    public static String getLunch(String ID_Stavka) {
        List<String> lst = (GetQuery("select * from stavkameni WHERE Ime = '" + ID_Stavka + "'", 2));
        if (!lst.isEmpty()) {
            return lst.get(0);
        }
        return new String();
    }


    public static String getPreferencesHour(String user) {
        List<String> lst = (GetQuery("select * from preferences where Korisnik_User = '" + user + "'", 1));
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
        List<String> lst = (GetQuery("select Ime from stavkameni,preferences WHERE Ime=StavkaMeni_Ime And Korisnik_User = '" + user + "'", 1));
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

    public static List<String> getAllRestaurantNames() {
        List<String> lst = GetQuery("select * from restoran", 1);
        return lst;
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
        StringBuilder sqlStr = new StringBuilder("INSERT INTO meni (Cena,Restoran_Ime, StavkaMeni_Ime) VALUES('");
        sqlStr.append(Cena);
        sqlStr.append("', '");
        sqlStr.append(Restoran);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertRestoran(String Ime, String Adresa) {
        ExecuteQuery("INSERT INTO restoran VALUES('" + Ime + "', '" + Adresa + "');");
    }

    public static void insertStavkaMeni(String Ime) //throws SQLException
    {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO stavkameni (Ime) VALUES('");
        sqlStr.append(Ime);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertTekovnaGrupa(String Vreme, String User, String Restoran) {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO tekovnagrupa (Vreme,Korisnik_USer,Restoran_Ime) VALUES('");
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
        int ID_Naracka;
        List<String> lst = GetQuery("select idNaracka from naracka order by idNaracka", 1);
        if (!lst.isEmpty()) {
            ID_Naracka = Integer.parseInt(lst.get(lst.size() - 1));
            ID_Naracka++;
        } else {
            ID_Naracka = 0;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka VALUES('");
        sqlStr.append(ID_Naracka);
        sqlStr.append("', '");
        sqlStr.append(" ");
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(ID_Grupa);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertNaracka(String User, int ID_Grupa, String Stavka, String Komentar) {
        int ID_Naracka;
        List<String> lst = GetQuery("select idNaracka from naracka order by idNaracka", 1);
        if (!lst.isEmpty()) {
            ID_Naracka = Integer.parseInt(lst.get(lst.size() - 1));
            ID_Naracka++;
        } else {
            ID_Naracka = 0;
        }
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka VALUES('");
        sqlStr.append(ID_Naracka);
        sqlStr.append("', '");
        sqlStr.append(Komentar);
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(ID_Grupa);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
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

    public static void updatePreferences(String User, String Vreme, String Restoran, String Stavka) {
        updatePreferencesRestoran(Restoran, User);
        updatePreferencesStavka(Stavka, User);
        updatePreferencesVreme(Vreme, User);
    }
}
