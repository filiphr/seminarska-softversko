/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class Pair {
   public ResultSet rSet;
   public Connection conect;

    public Pair(ResultSet rSet, Connection conect) {
        this.rSet = rSet;
        this.conect = conect;
    }
   
   
}
