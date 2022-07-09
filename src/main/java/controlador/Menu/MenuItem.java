/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author gzapata
 */
public interface MenuItem extends ActionListener {
    public abstract JMenuItem getItem();
}
