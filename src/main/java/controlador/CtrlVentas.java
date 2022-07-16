/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import modelo.dao.CategoriasDAO;
import modelo.dao.ClientesDAO;
import modelo.dao.ComprobantesDAO;
import modelo.dao.InventarioDAO;
import modelo.dao.OrdenDetallesDAO;
import modelo.dao.OrdenesDAO;
import modelo.dao.ProductosDAO;
import modelo.db.Categorias;
import modelo.db.Clientes;
import modelo.db.Comprobantes;
import modelo.db.Inventario;
import modelo.db.OrdenDetalles;
import modelo.db.Ordenes;
import modelo.db.Productos;
import utilitarios.UtilDate;
import utilitarios.UtilDialog;
import utilitarios.UtilTable;
import vista.MenuPrincipalView;
import vista.VentasView;

/**
 *
 * @author gzapata
 */
public class CtrlVentas extends AbstractAction {
    private final VentasView view;
    private final CategoriasDAO categoriasDAO;
    private final ProductosDAO produtosDAO;
    private final InventarioDAO inventarioDAO;
    private final OrdenDetallesDAO ordenDetalleDAO;
    private final OrdenesDAO ordenesDAO;
    private final ComprobantesDAO comprobantesDAO;
    private final ClientesDAO clientesDAO;
    private Inventario inventarioSeleccionado;
    private Clientes clienteSeleccionado;
    private double totalVenta;
    
    public CtrlVentas(VentasView view) {
        this.view = view;
        this.produtosDAO = new ProductosDAO();
        this.inventarioDAO = new InventarioDAO();
        this.categoriasDAO = new CategoriasDAO();
        this.ordenDetalleDAO = new OrdenDetallesDAO();
        this.comprobantesDAO = new ComprobantesDAO();
        this.ordenesDAO = new OrdenesDAO();
        this.clientesDAO = new ClientesDAO();
    }
    
    public void init() {
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.categoriaCombo.addActionListener(this);
        this.view.productoCombo.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.cantidadSpinner.addChangeListener((ChangeEvent e) -> {
            double monto = Double.parseDouble(view.labelAmount.getText());
            int cantidad = (Integer)view.cantidadSpinner.getValue();
            view.labelTotalAmount.setText(String.format("%.2f", cantidad * monto));
        });
        
        this.view.btnAgregar.setActionCommand("CREATE");
        this.view.btnBuscar.setActionCommand("SEARCH");
        this.view.btnGuardar.setActionCommand("SAVE");
        this.view.categoriaCombo.setActionCommand("CATEGORIACOMBO");
        this.view.productoCombo.setActionCommand("PRODUCTOCOMBO");
        
        MenuPrincipalView.getInstance().AddWindow(this.view);
        this.view.setVisible(true);
        this.limpiar();
        this.limpiarTabla();
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
            case "DELETE": {
                this.eliminar(Integer.parseInt(listcmd[1]));
                break;
            }
            case "CATEGORIACOMBO": {
                actualizarComboCategoria();
                break;
            }
            case "PRODUCTOCOMBO": {
                actualizarComboProducto();
                break;
            }
        }
    }
    
    public void crear() {
        Productos producto = (Productos)view.productoCombo.getSelectedItem();
        var totalAmount = view.labelTotalAmount.getText();
        var amount = view.labelAmount.getText();
        var cantidad = (Integer)view.cantidadSpinner.getValue();
        
        if(cantidad == 0){
            return;
        }
        
        totalVenta += Double.valueOf(totalAmount);
        
        UtilTable.AddRow(view.table, new ArrayList<>(
            Arrays.asList(
                inventarioSeleccionado.getIdInventario(),
                producto.getIdProducto(),
                producto.getNombre(),
                cantidad,
                amount,
                totalAmount
            )
        ));
        
        UtilTable.AddActions(view.table,this);
        
        this.limpiar();
    }
    
    public void buscar() {
        String documento = view.textCliente.getText();

        if(documento.equals("")) {
            return;
        }
        
        clienteSeleccionado = clientesDAO.buscarPorDocumento(documento);
        
        if(clienteSeleccionado.getIdCliente() == 0) {
            UtilDialog.Error(view, "No existe el cliente, porfavor registrarlo");
        }
        
        view.labelNombre.setText(clienteSeleccionado.getNombres() + " "
                + clienteSeleccionado.getApellidos());
        view.labelTipoDocumento.setText(clienteSeleccionado.getTipoDocumento());
        view.labelDocumento.setText(clienteSeleccionado.getDocumento());
    }
    
    public void limpiar() {
        view.cantidadSpinner.setValue(0);
        view.labelAmount.setText("0.00");
        view.labelTotalAmount.setText("0.00");
        
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
            this.actualizarComboCategoria();
        }
    }
    
    public void limpiarTabla() {
        UtilTable.ClearTable(view.table);
        totalVenta = 0;
        clienteSeleccionado = new Clientes();
        view.textCliente.setText("");
        view.labelDocumento.setText("");
        view.labelTipoDocumento.setText("");
        view.labelNombre.setText("");
    }
    
    public void eliminar(int row) {
        var item = UtilTable.GetRow(view.table,row);
        var montoTotal = Double.valueOf(String.valueOf(item.get(4)));
        totalVenta -= montoTotal;
        UtilTable.RemoveRow(view.table,row);
    }

    public void guardar() {
        this.ejecutarGuardar();
        this.setEstado(true);
    }
    
    public void ejecutarGuardar() {
        if(clienteSeleccionado.getIdCliente() == 0){
            UtilDialog.Error(view, "Debe seleccionar al cliente por su documento");
            return;
        }
        
        
        var count = view.table.getModel().getRowCount();
        var impuesto = 0.18 * totalVenta;
        String message = String.format("¿Esta seguro que desea procesar la venta con un total de %.2f?", totalVenta);

        if(UtilDialog.Question(view, message)){
            message = String.format("\nCliente %s %s\n",clienteSeleccionado.getNombres(), clienteSeleccionado.getApellidos());
            
            message += "\nDetalle: \n";
            Ordenes item = new Ordenes();
        
            item.setDescuento(0);
            var dt = UtilDate.Now();
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            item.setMontoTotal(totalVenta);
            item.setIdEmpleado(1);
            item.setIdCliente(clienteSeleccionado.getIdCliente());

            long ordenId = ordenesDAO.insertar(item);
            
            if(ordenId == 0){
                UtilDialog.Error(view, "No se pudo generar la orden");
                return;
            }

            for(int i = 0; i < count; i++){
                OrdenDetalles detalle = new OrdenDetalles();
                var row = UtilTable.GetRow(view.table, i);

                detalle.setIdOrden(ordenId);
                detalle.setIdProducto(Long.valueOf(String.valueOf(row.get(1))));
                detalle.setCantidad(Integer.valueOf(String.valueOf(row.get(3))));
                detalle.setPrecioUnitario(Double.valueOf(String.valueOf(row.get(4))));
                detalle.setPrecioTotal(Double.valueOf(String.valueOf(row.get(5))));
                detalle.setFecha(dt.getFecha());
                detalle.setFechaHora(dt.getFechaHora());
                detalle.setTimestamp(dt.getTimestamp());

                ordenDetalleDAO.insertar(detalle);

                Inventario inventario = new Inventario();
                inventario.setIdInventario(Long.valueOf(String.valueOf(row.get(0))));
                inventario.setCantidad(detalle.getCantidad());

                inventarioDAO.reducirInventario(inventario);
                
                message += String.format("%s %d x %.2f = %.2f\n",String.valueOf(row.get(2)), 
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getPrecioTotal());
            }

            
            Comprobantes comprobante = new Comprobantes();
            
            UUID uuid = UUID.randomUUID();
            comprobante.setCodigo(uuid.toString());
            comprobante.setFecha(dt.getFecha());
            comprobante.setFechaHora(dt.getFechaHora());
            comprobante.setTimestamp(dt.getTimestamp());
            comprobante.setIdOrden(ordenId);
            comprobante.setImpuesto(impuesto);
            comprobante.setSubTotal(totalVenta - impuesto);
            if("Ruc".equals(clienteSeleccionado.getTipoDocumento())){
                comprobante.setTipo("Factura");
            }
            else {
                comprobante.setTipo("Boleta");
            }
            
            message += String.format("\nSubTotal %.2f\n",comprobante.getSubTotal());
            message += String.format("Impuesto %.2f\n",comprobante.getImpuesto());
            message += String.format("Total %.2f\n",totalVenta);

            comprobantesDAO.insertar(comprobante);

            UtilDialog.Information(view, "Se realizo la venta correctamente \n" + message);
            this.limpiar();
            this.limpiarTabla();
        }
    }
    
    public void setEstado(boolean status){
        view.btnAgregar.setEnabled(status);
        view.btnGuardar.setEnabled(status);
        view.btnBuscar.setEnabled(status);
        view.table.setEnabled(status);
    }
    
    public void actualizarComboCategoria(){
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
        
        view.labelAmount.setText("0.00");
        view.labelTotalAmount.setText("0.00");
        if(productos.size() > 0){
            var producto = productos.get(0);
            inventarioSeleccionado = inventarioDAO.buscarPorProducto(producto.getIdProducto());
            var monto = inventarioSeleccionado.getPrecioUnitario();
            
            view.labelAmount.setText(String.format("%.2f",monto));
            view.labelTotalAmount.setText(String.format("%.2f",0 * monto));
            view.cantidadSpinner.setModel(new SpinnerNumberModel(0,0,inventarioSeleccionado.getCantidad(),1));
        }
    }
    
    public void actualizarComboProducto(){
        Productos producto = (Productos)view.productoCombo.getSelectedItem();
        
        if (producto == null){
            return;
        }

        inventarioSeleccionado = inventarioDAO.buscarPorProducto(producto.getIdProducto());
        var monto = inventarioSeleccionado.getPrecioUnitario();

        view.labelAmount.setText(String.format("%.2f",monto));
        view.labelTotalAmount.setText(String.format("%.2f",0 * monto));
        view.cantidadSpinner.setModel(new SpinnerNumberModel(0,0,inventarioSeleccionado.getCantidad(),1));
    }
}
