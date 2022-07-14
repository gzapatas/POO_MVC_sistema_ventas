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
import modelo.dao.InventarioDAO;
import modelo.dao.ProductosDAO;
import modelo.db.Categorias;
import modelo.db.Inventario;
import modelo.db.Productos;
import utilitarios.UtilDate;
import utilitarios.UtilDialog;
import utilitarios.UtilTable;
import vista.InventarioView;
import vista.MenuPrincipalView;

/**
 *
 * @author gzapata
 */
public class CtrlInventario extends AbstractAction {
    private final InventarioView view;
    private final CategoriasDAO categoriasDAO;
    private final ProductosDAO produtosDAO;
    private final InventarioDAO inventarioDAO;
    
    public CtrlInventario(InventarioView view) {
        this.view = view;
        this.produtosDAO = new ProductosDAO();
        this.inventarioDAO = new InventarioDAO();
        this.categoriasDAO = new CategoriasDAO();
    }
    
    public void init() {
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnList.addActionListener(this);
        this.view.categoriaCombo.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnAgregar.setActionCommand("CREATE");
        this.view.btnBuscar.setActionCommand("SEARCH");
        this.view.btnList.setActionCommand("LIST");
        this.view.btnGuardar.setActionCommand("SAVE");
        this.view.categoriaCombo.setActionCommand("CATEGORIACOMBO");
        
        MenuPrincipalView.getInstance().AddWindow(this.view);
        this.view.setVisible(true);
        this.limpiar();
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
            case "CATEGORIACOMBO": {
                actualizarComboProducto();
                break;
            }
        }
    }
    
    public void crear() {
        Inventario item = new Inventario();
        
        int value = (Integer) view.cantidadSpinner.getValue();
        Productos prod = (Productos)view.productoCombo.getSelectedItem();
        
        item.setCantidad(value);
        item.setPrecioUnitario(Double.parseDouble(view.precioText.getText()));
        item.setIdProducto(prod.getIdProducto());
        var dt = UtilDate.Now();
        item.setFecha(dt.getFecha());
        item.setFechaHora(dt.getFechaHora());
        item.setTimestamp(dt.getTimestamp());
        
        
        if(!item.valido()){
            UtilDialog.Error(view, "Registro no valido, intente nuevamente");
            return;
        }
        
        if(!inventarioDAO.sp_insertarinventario(item)){
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
        
        var item = inventarioDAO.buscar(Long.parseLong(id));
        
        UtilTable.ClearTable(view.table);
        
        if(item.getIdInventario() == 0) {
            return;
        }
        
        UtilTable.AddRow(view.table, new ArrayList<>(
            Arrays.asList(
                item.getIdInventario(),
                item.getIdProducto(),
                item.getProducto(),
                item.getCantidad(),
                item.getPrecioUnitario(),
                item.getFecha(),
                item.getFechaHora(),
                item.getTimestamp()
            )
        ));
        UtilTable.AddActions(view.table, this);
    }
    
    public void limpiar() {
        view.cantidadSpinner.setValue(0);
        view.precioText.setText("0.00");
        view.identificadorText.setText("");
        
        //Llenando el combo de categorias
        view.categoriaCombo.removeAllItems();
        var categorias = categoriasDAO.listar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        categorias.forEach(item -> {
            model.addElement(item);
        });
        view.categoriaCombo.setModel(model);
        
        //Llenando los producto
        if(categorias.size() > 0){
            this.actualizarComboProducto();
        }
        
        this.listar();
    }
    
    public void listar() {
        var items = inventarioDAO.listar();
        
        UtilTable.ClearTable(view.table);
        items.forEach((item) -> {
            UtilTable.AddRow(view.table, new ArrayList<>(
                Arrays.asList(
                    item.getIdInventario(),
                    item.getIdProducto(),
                    item.getProducto(),
                    item.getCantidad(),
                    item.getPrecioUnitario(),
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
            
            if(!inventarioDAO.sp_eliminarinventario(id)){
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
        
        this.view.categoriaCombo.removeActionListener(this);
        long idProducto = Long.valueOf(String.valueOf(items.get(1)));
        Productos prod = produtosDAO.buscar(idProducto);
        
        var count = view.categoriaCombo.getItemCount();
        for (int i = 0; i < count; i++) {
            Categorias cat = (Categorias)view.categoriaCombo.getItemAt(i);
            if(cat.getIdCategoria() == prod.getIdCategoria()){
                view.categoriaCombo.setSelectedIndex(i);
                break;
            }
        }
        
        actualizarComboProducto();
        
        count = view.productoCombo.getItemCount();
        for (int i = 0; i < count; i++) {
            prod = (Productos)view.productoCombo.getItemAt(i);
          
            if(prod.getIdProducto() == idProducto){
                view.productoCombo.setSelectedIndex(i);
                break;
            }
        }
        
        view.cantidadSpinner.setValue(Integer.valueOf(String.valueOf(items.get(3))));
        view.precioText.setText(String.valueOf(items.get(4)));
        
        this.view.categoriaCombo.addActionListener(this);
        this.setEstado(false);
    }
    
    public void guardar() {
        this.ejecutarGuardar();
        this.setEstado(true);
    }
    
    public void ejecutarGuardar() {
        if(UtilDialog.Question(view, "¿Esta seguro que desea actualizar?")){
            Inventario item = new Inventario();
            var id = Long.valueOf(view.identificadorText.getText());
            var dt = UtilDate.Now();
            Productos prod = (Productos)view.productoCombo.getSelectedItem();
            int cantidad = (Integer) view.cantidadSpinner.getValue();
            
            item.setIdInventario(id);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(Double.parseDouble(view.precioText.getText()));
            item.setIdProducto(prod.getIdProducto());
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            
            if(!inventarioDAO.sp_editarinventario(item)){
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
    
    public void actualizarComboProducto(){
        Categorias cat = (Categorias)view.categoriaCombo.getSelectedItem();
        
        if (cat == null){
            return;
        }
        
        var productos = produtosDAO.listarPorCategoria(cat.getIdCategoria());
        DefaultComboBoxModel modelProd = new DefaultComboBoxModel();
        productos.forEach(item -> {
            modelProd.addElement(item);
        });
        view.productoCombo.setModel(modelProd);
    }
}
