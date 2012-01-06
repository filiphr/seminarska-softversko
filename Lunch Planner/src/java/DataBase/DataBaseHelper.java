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
        String pass = "admin";
        Connection conect = null;
        ResultSet rs = null;
        List<String> lst = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            Statement s = conect.createStatement();
            rs = s.executeQuery(query);
            while (rs.next()) {
                if(rs.getString(number)!=null)
                lst.add(rs.getString(number));
                else lst.add("");
            }
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
        return lst;
    }

    public static int ExecuteQuery(String Query) {
        String dbUrl = "jdbc:mysql://localhost:3306/dbsoftversko";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "admin";
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
                if(conect!=null)
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

    public static String getLunch(String Stavka) {
        List<String> lst = (GetQuery("select Ime from stavkameni WHERE Ime = '" + Stavka + "'", 1));
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
        if((ime == null) || (prezime == null) || (user == null) || (email == null) || (password == null)) return;
        if(ime.isEmpty() || prezime.isEmpty() || user.isEmpty() || password.isEmpty()) return;
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
        if(Restoran == null || Cena == null || Stavka == null) return;
        if(Restoran.isEmpty() || Stavka.isEmpty()) return;
        String str = getLunch(Stavka);
        if(str==null || "".equals(str))
            insertStavkaMeni(Stavka);
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
        if(Ime == null || Adresa == null || Telefon == null) return;
        if(Ime.isEmpty()) return;
        ExecuteQuery("INSERT INTO restoran VALUES('" + Ime + "', '" + Adresa + "');");
    }

    public static void insertStavkaMeni(String Ime) //throws SQLException
    {
        if(Ime == null) return;
        if(Ime.isEmpty()) return;
        StringBuilder sqlStr = new StringBuilder("INSERT INTO stavkameni (Ime) VALUES('");
        sqlStr.append(Ime);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertTekovnaGrupa(String Vreme, String User, String Restoran) {
        if(Vreme.isEmpty() || User.isEmpty() || Restoran.isEmpty()) return;
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
        if(User == null) return;
        if(User.isEmpty()) return;
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka (Korisnik_User, TekovnaGrupa_idTekovnaGrupa, stavkameni_Ime) VALUES('");
        sqlStr.append(User);
        sqlStr.append("', '");
        sqlStr.append(ID_Grupa);
        sqlStr.append("', '");
        sqlStr.append(Stavka);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }

    public static void insertNaracka(String User, int ID_Grupa, String Stavka, String Komentar) {
        if(User == null) return;
        if(User.isEmpty()) return;
        StringBuilder sqlStr = new StringBuilder("INSERT INTO naracka (Komentar, Korisnik_User, TekovnaGrupa_idTekovnaGrupa, stavkameni_Ime) VALUES('");
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
    public static void updatePreferencesKomentar(String Komentar, String User)
    {
        if (!Komentar.isEmpty() && !(User.isEmpty())) {
            ExecuteQuery("UPDATE preferences SET Komentar = '" + Komentar + "' WHERE Korisnik_User = '" + User + "';");
        }
    }

    public static void updatePreferences(String User, String Vreme, String Restoran, String Stavka, String Komentar) {
        updatePreferencesRestoran(Restoran, User);
        updatePreferencesStavka(Stavka, User);
        updatePreferencesVreme(Vreme, User);
        updatePreferencesKomentar(Komentar, User);
    }
    
    public static void deleteUser (String User)
    {
        String sqlStr = "DELETE FROM korisnik WHERE User='" + User +"'";
        ExecuteQuery(User);
    }
    public static void updateUser (String ime, String prezime, String user, String lozinka, String mail)
    {
        StringBuilder sqlSqtr = new StringBuilder("UPDATE korisnik SET");
        sqlSqtr.append("Ime='").append(ime).append("', ");
        sqlSqtr.append("Prezime='").append(prezime).append("', ");
        sqlSqtr.append("Email='").append(mail).append("', ");
        sqlSqtr.append("Password='").append(lozinka).append("' ");
        sqlSqtr.append("WHERE User='").append(user).append("'");
        ExecuteQuery(sqlSqtr.toString());
    }
    public static void deleteRestoran(String Ime)
    {
        String sqlStr = "DELETE FROM restoran WHERE Ime='" +Ime+"'";
        ExecuteQuery(sqlStr);
    }
    public static void deleteMeni (String Restoran, String Stavka)
    {
        String sqlStr = "DELETE FROM Meni WHERE Restoran_ime='"+Restoran+"' And StavkaMeni_Ime = '" + Stavka +"'";
        ExecuteQuery(sqlStr);
    }
    //UPDATE dbsoftversko.naracka SET `stavkameni_Ime` = 'Sendvic', `Komentar` = 'So Kecap' WHERE `idNaracka` = 1;
    public static void UpdateNaracka(int ID_Naracka, String Komentar, String Stavka)
    {
        ExecuteQuery("UPDATE naracka SET stavkameni_Ime = '" + Stavka + "' , Komentar = '" + Komentar + "' WHERE idNaracka = " + ID_Naracka);
    }
    public static int getIDNaracka(String User, int ID_Grupa, String Stavka)
    {
        StringBuilder sqlStr = new StringBuilder("select idNaracka from naracke where Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' And  TekovnaGrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        sqlStr.append(" And stavkameni_Ime =  '");
        sqlStr.append(Stavka);
        sqlStr.append("'");
        List<String> lst = GetQuery(sqlStr.toString(), 1);
        int ID;
        if(!lst.isEmpty())
        {
            ID = Integer.parseInt(lst.get(0));
        }else ID = -1;
        return ID;
    }
    public static void deleteNaracka(int ID_Naracka)
    {
        ExecuteQuery("DELETE FROM naracka WHERE idNaracka = " + ID_Naracka);
    }
    
    public static void insertPokani(String User, int ID_Grupa)
    {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO pokani VALUES('");
        sqlStr.append(User);
        sqlStr.append("', ");
        sqlStr.append(ID_Grupa);
        sqlStr.append(" );");
        ExecuteQuery(sqlStr.toString());
    }
    
    public static void deletePokani(String User, int ID_Grupa)
    {
        StringBuilder sqlStr = new StringBuilder("DELETE FROM pokani WHERE korisnik_USer = '");
        sqlStr.append(User);
        sqlStr.append("' And tekovnagrupa_idTekovnaGrupa = ");
        sqlStr.append(ID_Grupa);
        sqlStr.append(" );");
        ExecuteQuery(sqlStr.toString());
    }
    
    public static void insertPreferencesParticipant(String User, String participant)
    {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO korisnik_has_preferences VALUES('");
        sqlStr.append(participant);
        sqlStr.append("', '");
        sqlStr.append(User);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }
    public static void deletePreferencesParticipant(String User, String participant)
    {
        StringBuilder sqlStr = new StringBuilder("DELETE FROM korisnik_has_preferences WHERE korisnik_USer = '");
        sqlStr.append(participant);
        sqlStr.append("' And  preferences_Korisnik_User = '");
        sqlStr.append(User);
        sqlStr.append("' );");
        ExecuteQuery(sqlStr.toString());
    }
    
    public static void deleteTekovnaGrupa(int ID_Grupa)
    {
        ExecuteQuery("DELETE FROM tekovnagrupa where idTekovnaGrupa = " + ID_Grupa);
    }
    public static void deleteAllGroups()
    {
        ExecuteQuery("DELETE FROM tekovnagrupa");
    }
    public static void deleteAllNaracki()
    {
        ExecuteQuery("DELETE FROM naracka");
    }
    public static void insertArhiviraniGrupi(String Vreme, String User, String Restoran, String Stavka)
    {
        StringBuilder sqlStr = new StringBuilder("INSERT INTO arhiviranagrupa VALUES('");
        sqlStr.append(Vreme);
        sqlStr.append("', ");
        sqlStr.append(User);
        sqlStr.append("', ");
        sqlStr.append(Restoran);
        sqlStr.append("', ");
        sqlStr.append(Stavka);
        sqlStr.append(" );");
        ExecuteQuery(sqlStr.toString());
    }
}
