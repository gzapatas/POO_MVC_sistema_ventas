/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gzapata
 */
public class ComprobanteBoleta extends Comprobante implements ComprobanteInterface {
    @Override
    public String getTipo() {
        return "BOLETA";
    }
}