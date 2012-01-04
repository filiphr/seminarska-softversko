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
            while(rs.next())
                lst.add(rs.getString(number));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(conect!=null)
                    conect.close();
            }catch(SQLException ex)
            {
                Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }
    
    public int ExecuteQuery(String Query)
    {
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
            throw new RuntimeException(e);
        } finally {
            try{
                conect.close();
            }catch(SQLException ex)
            {
                Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return number;
    }
    
    public static List<List<String>> getAllNamesSNamesUsersEmailsPass() throws SQLException
    {
        List<List<String>> lst = new ArrayList<List<String>>();;
            for(int i = 1; i<=5; i++)
                lst.add(GetQuery("SELECT * FROM korisnik", i));
        return lst;
    }
    
    public static boolean CheckUser(String user, String password)
    {
        return true;
    }
    public static List<List<String>> getUserAndLunch(String ID_Group)
    {
        List<List<String>> lst = null;
        return lst;
    }
    public static String getPreferencesHour(String user)
    {
        String str = null;
        return str;
    }
    public static String getPreferencesParticipant(String user)
    {
        String str = null;
        return str;
    }
    public static String getPreferencesMeal(String user)
    {
        List<String> lst = (GetQuery("select Ime from stavkameni,preferences WHERE idStavkaMeni=StavkaMeni_idStavkaMeni And Korisnik_User = '" + user + "'", 1));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
    public static String getPreferencesRestoran(String user)
    {
        List<String> lst = (GetQuery("select * from preferences where Korisnik_User = '" + user + "'", 3));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
    public static List<String> getAllUsernames()
    {
        List<String> lst = new ArrayList<String>();
        lst = GetQuery("select * from korisnik", 3);
        return lst;
    }
    public static List<String> getAllRestaurantNames()
    {
        List<String> lst = new ArrayList<String>();
        lst = GetQuery("select * from restoran", 1);
        return lst;
    }
    public static List<String> getAllMenuItems(String RestaurantNames)
    {
        List<String> lst = null;
        if("".equals(RestaurantNames))
            lst = new ArrayList<String>();
        else
        {
            /* IGOR TVOJOT KOD TUKA SAMO DA NE VRAKJA NULL NIKOGAS*/
        }
            
        return lst;
    }
    public static String getUserIme (String username)
    {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 1));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
    public static String getUserPrezime (String username)
    {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 2));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
    public static String getUserEmail (String username)
    {
        List<String> lst = (GetQuery("select * from korisnik where User = '" + username + "'", 4));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
    public static String getUserLozinka (String username)
    {
        List<String> lst  = (GetQuery("select * from korisnik where User = '" + username + "'", 5));
        if(!lst.isEmpty())
            return lst.get(0);
        return new String();
    }
}
    
