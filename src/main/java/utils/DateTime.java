/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author gzapata
 */
public class DateTime {
    private String Fecha;
    private String FechaHora;
    private long Timestamp;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Date) {
        this.Fecha = Date;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String DateTime) {
        this.FechaHora = DateTime;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long Timestamp) {
        this.Timestamp = Timestamp;
    }
}
