/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

/**
 *
 * @author Home
 */
public class DataBaseSetup {
    
    static String dbURL = "jdbc:mysql://localhost:3306/dbsoftversko";
    static String dbDriver = "com.mysql.jdbc.Driver";
    static String dbUser = "root";
    static String dbPassword = "admin";
    
    public static String getDbUrl()
    {
        return dbURL;
    }
    public static String getDbDriver()
    {
        return dbDriver;
    }
    public static String getDbUser()
    {
        return dbUser;
    }
    public static String getDbPassword()
    {
        return dbPassword;
    }
}
