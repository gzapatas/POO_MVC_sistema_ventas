/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gzapata
 */
public class UtilDate {
    public static DateTime Now(){
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        DateTime dt = new DateTime();
        
        dt.setFechaHora(dtf.format(date));
        dt.setFecha(df.format(date));
        dt.setTimestamp(date.getTime());

        return dt;
    }
}
