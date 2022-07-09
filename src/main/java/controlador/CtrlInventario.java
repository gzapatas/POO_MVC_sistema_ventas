/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import modelo.dao.CategoriasDAO;
import modelo.dao.InventarioDAO;
import modelo.dao.ProductosDAO;
import modelo.db.Categorias;
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
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
        this.view.categoriaCombo.addActionListener(this);
        this.view.btnAgregar.setActionCommand("CREATE");
        this.view.btnBuscar.setActionCommand("SEARCH");
        this.view.btnLimpiar.setActionCommand("CLEAN");
        this.view.categoriaCombo.setActionCommand("CATEGORIACOMBO");
    }
    
    public void init() {
        MenuPrincipalView.getInstance().AddWindow(this.view);
        this.view.setVisible(true);
        this.limpiar();
    }
    
    public void limpiar() {
        view.cantidadSpinner.setValue(0);
        view.precioText.setText("0.00");
        
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
        
        
        //this.listar();
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
                //this.crear();
                break;
            }
            case "SEARCH": {
                //this.buscar();
                break;
            }
            case "CLEAN": {
                this.limpiar();
                break;
            }
            case "UPDATE": {
                //this.actualizar(Integer.parseInt(listcmd[1]));
                break;
            }
            case "DELETE": {
                //this.eliminar(Integer.parseInt(listcmd[1]));
                break;
            }
            case "CATEGORIACOMBO": {
                actualizarComboProducto();
                //this.eliminar(Integer.parseInt(listcmd[1]));
                break;
            }
        }
    }
    
    public void actualizarComboProducto(){
        Categorias cat = (Categorias)view.categoriaCombo.getSelectedItem();
        
        if (cat == null){
            return;
        }
        
        System.out.println(cat);
        
        var productos = produtosDAO.listarPorCategoria(cat.getIdCategoria());
        DefaultComboBoxModel modelProd = new DefaultComboBoxModel();
        productos.forEach(item -> {
            modelProd.addElement(item);
        });
        view.productoCombo.setModel(modelProd);
    }
}
