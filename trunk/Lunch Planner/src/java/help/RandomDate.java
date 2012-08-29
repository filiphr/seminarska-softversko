/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author igor
 */
public class RandomDate {
    
    public static String getRandomDate(){
        int year = RandomDate.randBetween(2011, 2012);

        int month = RandomDate.randBetween(0, 11);

        GregorianCalendar gc = new GregorianCalendar(year, month, 1);

        int day = RandomDate.randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

        gc.set(year, month, day);
        
        DecimalFormat nft = new DecimalFormat("#00.###");
        nft.setDecimalSeparatorAlwaysShown(false);

        return nft.format(gc.get(GregorianCalendar.YEAR)) + "-" + nft.format(gc.get(GregorianCalendar.MONTH)) + "-" + nft.format(gc.get(GregorianCalendar.DAY_OF_MONTH));
    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
}
