/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author gzapata
 */
public class ComprobanteFactura extends Comprobante implements ComprobanteInterface {
    @Override
    public String getTipo() {
        return "FACTURA";
    }
}