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
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
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
            while(rs.next())
                lst.add(rs.getString(number));
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
        return lst;
    }
    
    public int ExecuteQuery(String Query)
    {
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
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
    
    public static List<List<String>> getAllNamesSNamesUsersEmails() throws SQLException
    {
        List<List<String>> lst = new ArrayList<List<String>>();;
            for(int i = 1; i<=4; i++)
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
        String str = null;
        return str;
    }
    public static String getPreferencesRestoran(String user)
    {
        String str = null;
        return str;
    }
    public static List<String> getAllUsernames()
    {
        List<String> lst = new ArrayList<String>();
        return lst;
    }
    public static List<String> getAllRestaurantNames()
    {
        List<String> lst = new ArrayList<String>();
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
}
    
