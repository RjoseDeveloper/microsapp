/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.methods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Raimundo Jose
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //System.out.println(""+new Functions().getSomaCredito(1, 3));
        SimpleDateFormat  sf = new SimpleDateFormat("dd-MM-yyyy");
      
        Calendar cal = Calendar.getInstance();
        int days = 30;
        int mes = 3;
        cal.add(Calendar.DATE, days);
        System.out.println("/DateINI: "+sf.format(cal.getTime()));
        
        cal.add(Calendar.MONTH, mes-1);
        System.out.println("/DateF: "+sf.format(cal.getTime()));
    }
    
}
