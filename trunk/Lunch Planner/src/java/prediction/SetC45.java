/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prediction;

import java.util.List;

/**
 *
 * @author Filip
 */
public class SetC45 {
    
    public static BuildC45forAllEmployee set() throws Exception
    {
        BuildC45forAllEmployee b = new BuildC45forAllEmployee();
        b.setOptionsforRestorants("-C 0.25 -M 2");
        b.setOptionsforStavki("-C 0.25 -M 2");
        b.setOptionsForOdrzuvanje("-C 0.25 -M 2");
        List<String> users = DataBase.DataBaseHelper.getAllUsernames();
        for(int i = 0; i < users.size(); i++){
            b.add(users.get(i));
        }
        b.BuildRestoran();
        b.BuildAllClassifiers(); 
        return b;
    }
}
