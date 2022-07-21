/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import modelo.dao.CategoriasDAO;
import vista.CategoriasView;
import modelo.db.Categorias;
import utilitarios.UtilDate;
import utilitarios.UtilDialog;
import utilitarios.UtilTable;
import vista.MenuPrincipalView;


/**
 *
 * @author gzapata
 */
public class CtrlCategorias extends AbstractAction{
    private final CategoriasView view;
    private final CategoriasDAO categoriasDAO;

    public CtrlCategorias(CategoriasView view) {
        this.view = view;
        this.categoriasDAO = new CategoriasDAO();
        
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnList.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnAgregar.setActionCommand("CREATE");
        this.view.btnBuscar.setActionCommand("SEARCH");
        this.view.btnList.setActionCommand("LIST");
        this.view.btnGuardar.setActionCommand("SAVE");
    }
    
    public void init() {
        MenuPrincipalView.getInstance().AddWindow(this.view);
        this.view.setVisible(true);
        this.listar();
        this.setEstado(true);
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
            case "SAVE": {
                this.guardar();
                break;
            }
            case "LIST": {
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
        
        if(!categoriasDAO.sp_insertarcategoria(item)){
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
        
        var item = categoriasDAO.buscar(Long.parseLong(id));
        
        UtilTable.ClearTable(view.table);
        
        if(item.getIdCategoria()== 0) {
            return;
        }
        
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
        var items = categoriasDAO.listar();
        
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
            
            if(!categoriasDAO.sp_eliminarcategoria(id)){
                UtilDialog.Error(view, "No se pudo eliminar el registro");
                return;
            }
            
            this.limpiar();
            UtilDialog.Information(view, "Se elimino el registro correctamente");
        }
    }
    
    public void actualizar(int row) {
        var items = UtilTable.GetRow(view.table, row);
        view.identificadorText.setText(String.valueOf(items.get(0)));
        view.nombreText.setText(String.valueOf(items.get(1)));
        
        this.setEstado(false);
    }
    
    public void guardar() {
        this.ejecutarGuardar();
        this.setEstado(true);
    }
    
    public void ejecutarGuardar() {
        if(UtilDialog.Question(view, "¿Esta seguro que desea actualizar?")){
            Categorias item = new Categorias();
            var id = Long.valueOf(view.identificadorText.getText());
            var dt = UtilDate.Now();
            
            item.setIdCategoria(id);
            item.setNombre(view.nombreText.getText());
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            
            if(!categoriasDAO.sp_editarcategoria(item)){
                UtilDialog.Error(view, "No se pudo actualizar el registro");
                return;
            }
            
            this.limpiar();
            UtilDialog.Information(view, "Se actualizo el registro correctamente");
        }
    }
    
    public void setEstado(boolean status){
        view.btnAgregar.setEnabled(status);
        view.btnList.setEnabled(status);
        view.btnGuardar.setEnabled(!status);
        view.btnBuscar.setEnabled(status);
        view.table.setEnabled(status);
        view.identificadorText.setEnabled(status);
    }
}
