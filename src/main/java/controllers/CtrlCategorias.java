/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import model.dao.CategoriasDAO;
import view.CategoriasView;
import model.db.Categorias;
import utils.UtilDate;
import utils.UtilDialog;
import utils.UtilTable;
import view.MenuPrincipalView;


/**
 *
 * @author gzapata
 */
public class CtrlCategorias extends AbstractAction{
    private final CategoriasView view;
    private final CategoriasDAO categoriasDTO;

    public CtrlCategorias(CategoriasView view) {
        this.view = view;
        this.categoriasDTO = new CategoriasDAO();
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
        this.view.btnAgregar.setActionCommand("CREATE");
        this.view.btnBuscar.setActionCommand("SEARCH");
        this.view.btnLimpiar.setActionCommand("CLEAN");
    }
    
    public void init() {
        MenuPrincipalView.getInstance().AddWindow(this.view);
        this.view.setVisible(true);
        this.listar();
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand();
        var listcmd = command.split("_");

        switch(listcmd[0]){
            case "CREATE": {
                this.crear();
                break;
            }
            case "SEARCH": {
                this.buscar();
                break;
            }
            case "CLEAN": {
                this.limpiar();
                break;
            }
            case "UPDATE": {
                this.actualizar(Integer.parseInt(listcmd[1]));
                break;
            }
            case "DELETE": {
                this.eliminar(Integer.parseInt(listcmd[1]));
                break;
            }
        }
    }
    
    public void crear() {
        Categorias item = new Categorias();
        
        item.setNombre(view.nombreText.getText());
        var dt = UtilDate.Now();
        item.setFecha(dt.getFecha());
        item.setFechaHora(dt.getFechaHora());
        item.setTimestamp(dt.getTimestamp());
        
        if(!item.valido()){
            UtilDialog.Error(view, "Registro no valido, intente nuevamente");
            return;
        }
        
        if(!categoriasDTO.insertar(item)){
            UtilDialog.Error(view, "No se pudo agregar el registro");
            return;
        }
        
        this.limpiar();
        UtilDialog.Information(view, "Se agrego el registro correctamente");
    }
    
    public void buscar() {
        String id = view.identificadorText.getText();

        if(id.equals("")) {
            return;
        }
        
        var item = categoriasDTO.buscar(Long.parseLong(id));
        
        UtilTable.ClearTable(view.table);
        UtilTable.AddRow(view.table, new ArrayList<>(
            Arrays.asList(
                item.getIdCategoria(),
                item.getNombre(),
                item.getFecha(),
                item.getFechaHora(),
                item.getTimestamp()
            )
        ));
        UtilTable.AddActions(view.table, this);
    }
    
    public void limpiar() {
        view.identificadorText.setText("");
        view.nombreText.setText("");
        this.listar();
    }
    
    public void listar() {
        var items = categoriasDTO.listar();
        
        UtilTable.ClearTable(view.table);
        items.forEach((item) -> {
            UtilTable.AddRow(view.table, new ArrayList<>(
                Arrays.asList(
                    item.getIdCategoria(),
                    item.getNombre(),
                    item.getFecha(),
                    item.getFechaHora(),
                    item.getTimestamp()
                )
            ));
        });
        UtilTable.AddActions(view.table,this);
    }
    
    
    public void eliminar(int row) {
        var items = UtilTable.GetRow(view.table, row);
        
        if(UtilDialog.Question(view, "¿Esta seguro que desea eliminar?")){
            var id = Long.valueOf(String.valueOf(items.get(0)));
            
            if(!categoriasDTO.eliminar(id)){
                UtilDialog.Error(view, "No se pudo eliminar el registro");
                return;
            }
            
            this.limpiar();
            UtilDialog.Information(view, "Se elimino el registro correctamente");
        }
    }
    
    public void actualizar(int row) {
        var items = UtilTable.GetRow(view.table, row);
        
        if(UtilDialog.Question(view, "¿Esta seguro que desea actualizar?")){
            Categorias item = new Categorias();
            var id = Long.valueOf(String.valueOf(items.get(0)));
            var dt = UtilDate.Now();
            
            item.setIdCategoria(id);
            item.setNombre(String.valueOf(items.get(1)));
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            
            if(!categoriasDTO.actualizar(item)){
                UtilDialog.Error(view, "No se pudo actualizar el registro");
                return;
            }
            
            this.limpiar();
            UtilDialog.Information(view, "Se actualizo el registro correctamente");
        }
    }
}
