/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gzapata
 */
public class UtilTable {
    public static void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.setRowHeight(32);
    }
    
    public static void AddRow(JTable table, ArrayList<Object> object){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        object.add("");
        object.add("");
        model.addRow(object.toArray());
        
        table.setModel(model);
    }
    
    
    public static void AddActions(JTable table, Action action){
        var count = table.getColumnCount();
        ButtonColumn editar = new ButtonColumn(table, action, count-2, "UPDATE");
        ButtonColumn eliminar = new ButtonColumn(table, action, count-1, "DELETE");
        
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(count-2).setMaxWidth(32);
        table.getColumnModel().getColumn(count-1).setMaxWidth(32);
        
        editar.setMnemonic(KeyEvent.VK_E);
        eliminar.setMnemonic(KeyEvent.VK_D);
    }
    
    public static ArrayList<Object> GetRow(JTable table, int row){
        var model = table.getModel();
        var count = table.getColumnCount();
        var rowItems = new ArrayList<>();
        
        for (int i = 0; i < count; i++){
            rowItems.add(model.getValueAt(row, i));
        }
        
        return rowItems;
    }
}
