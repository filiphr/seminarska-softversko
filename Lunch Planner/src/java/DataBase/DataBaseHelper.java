/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DataBaseHelper {

    private ResultSet GetQuery(String query) {
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "";
        Connection conect = null;
        ResultSet rs = null;
        try {
            Class.forName(driver).newInstance();
            conect = DriverManager.getConnection(dbUrl, user, pass);
            Statement s = conect.createStatement();
            rs = s.executeQuery(query);

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
        return rs;
    }
    
    private int ExecuteQuery(String Query)
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
    
}
    
