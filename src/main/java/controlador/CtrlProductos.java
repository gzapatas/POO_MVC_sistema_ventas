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
import javax.swing.DefaultComboBoxModel;
import modelo.dao.CategoriasDAO;
import modelo.dao.ProductosDAO;
import modelo.db.Categorias;
import modelo.db.Productos;
import utilitarios.UtilDate;
import utilitarios.UtilDialog;
import utilitarios.UtilTable;
import vista.MenuPrincipalView;
import vista.ProductosView;

/**
 *
 * @author gzapata
 */
public class CtrlProductos extends AbstractAction {
    private final ProductosView view;
    private final ProductosDAO produtosDAO;
    private final CategoriasDAO categoriasDAO;

    public CtrlProductos(ProductosView view) {
        this.view = view;
        this.produtosDAO = new ProductosDAO();
        this.categoriasDAO = new CategoriasDAO();
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
        this.limpiar();
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
        Productos item = new Productos();
        
        item.setNombre(view.nombreText.getText());
        item.setSku(view.skuText.getText());
        item.setDescripcion(view.descripcionText.getText());
        Categorias cat = (Categorias)view.categoriaCombo.getSelectedItem();
        item.setIdCategoria(cat.getIdCategoria());
        var dt = UtilDate.Now();
        item.setFecha(dt.getFecha());
        item.setFechaHora(dt.getFechaHora());
        item.setTimestamp(dt.getTimestamp());
        
        if(!item.valido()){
            UtilDialog.Error(view, "Registro no valido, intente nuevamente");
            return;
        }
        
        if(!produtosDAO.insertar(item)){
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
        
        var item = produtosDAO.buscar(Long.parseLong(id));
        
        UtilTable.ClearTable(view.table);
        UtilTable.AddRow(view.table, new ArrayList<>(
            Arrays.asList(
                item.getIdProducto(),
                item.getSku(),
                item.getNombre(),
                item.getIdCategoria(),
                item.getDescripcion()
            )
        ));
        UtilTable.AddActions(view.table, this);
    }
    
    public void limpiar() {
        view.identificadorText.setText("");
        view.nombreText.setText("");
        view.skuText.setText("");
        view.descripcionText.setText("");
        
        //Llenando el combo de categorias
        var items = categoriasDAO.listar();
        view.categoriaCombo.removeAllItems();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        items.forEach(item -> {
            model.addElement(item);
        });
        view.categoriaCombo.setModel(model);
        
        this.listar();
    }
    
    public void listar() {
        var items = produtosDAO.listar();
        
        UtilTable.ClearTable(view.table);
        items.forEach((item) -> {
            UtilTable.AddRow(view.table, new ArrayList<>(
                Arrays.asList(
                    item.getIdProducto(),
                    item.getSku(),
                    item.getNombre(),
                    item.getIdCategoria(),
                    item.getDescripcion()
                )
            ));
        });
        UtilTable.AddActions(view.table,this);
    }
    
    
    public void eliminar(int row) {
        var items = UtilTable.GetRow(view.table, row);
        
        if(UtilDialog.Question(view, "¿Esta seguro que desea eliminar?")){
            var id = Long.valueOf(String.valueOf(items.get(0)));
            
            if(!produtosDAO.eliminar(id)){
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
            Productos item = new Productos();
            var id = Long.valueOf(String.valueOf(items.get(0)));
            var dt = UtilDate.Now();
            
            item.setIdProducto(id);
            item.setSku(String.valueOf(items.get(1)));
            item.setNombre(String.valueOf(items.get(2)));
            item.setIdCategoria(Long.valueOf(String.valueOf(items.get(3))));
            item.setDescripcion(String.valueOf(items.get(4)));
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            
            if(!produtosDAO.actualizar(item)){
                UtilDialog.Error(view, "No se pudo actualizar el registro");
                return;
            }
            
            this.limpiar();
            UtilDialog.Information(view, "Se actualizo el registro correctamente");
        }
    }
}
