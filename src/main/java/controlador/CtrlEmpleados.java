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
import modelo.dao.EmpleadosDAO;
import modelo.db.Empleados;
import utilitarios.UtilDate;
import utilitarios.UtilDialog;
import utilitarios.UtilTable;
import vista.EmpleadosView;
import vista.MenuPrincipalView;


/**
 *
 * @author gzapata
 */
public class CtrlEmpleados extends AbstractAction{
    private final EmpleadosView view;
    private final EmpleadosDAO empleadosDAO;

    public CtrlEmpleados(EmpleadosView view) {
        this.view = view;
        this.empleadosDAO = new EmpleadosDAO();
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
        Empleados item = new Empleados();
        
        item.setNombres(view.nombresText.getText());
        item.setApellidos(view.apellidosText.getText());
        item.setCelular(view.celularText.getText());
        item.setDocumento(view.documentoText.getText());
        item.setTelefono(view.telefonoText.getText());
        String tipo = (String)view.tipoCombo.getSelectedItem();
        item.setTipoEmpleado(tipo);
        var dt = UtilDate.Now();
        item.setFecha(dt.getFecha());
        item.setFechaHora(dt.getFechaHora());
        item.setTimestamp(dt.getTimestamp());
        
        if(!item.valido()){
            UtilDialog.Error(view, "Registro no valido, intente nuevamente");
            return;
        }
        
        if(!empleadosDAO.sp_insertarempleado(item)){
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
        
        var item = empleadosDAO.buscar(Long.parseLong(id));
        
        UtilTable.ClearTable(view.table);
        
        if(item.getIdEmpleado()== 0) {
            return;
        }
        
        UtilTable.AddRow(view.table, new ArrayList<>(
            Arrays.asList(
                item.getIdEmpleado(),
                item.getNombres(),
                item.getApellidos(),
                item.getTelefono(),
                item.getCelular(),
                item.getDocumento(),
                item.getTipoEmpleado()
            )
        ));
        UtilTable.AddActions(view.table, this);
    }
    
    public void limpiar() {
        view.identificadorText.setText("");
        view.nombresText.setText("");
        view.apellidosText.setText("");
        view.telefonoText.setText("");
        view.celularText.setText("");
        view.documentoText.setText("");
        view.tipoCombo.setSelectedIndex(0);
        this.listar();
    }
    
    public void listar() {
        var items = empleadosDAO.listar();
        
        UtilTable.ClearTable(view.table);
        items.forEach((item) -> {
            UtilTable.AddRow(view.table, new ArrayList<>(
                Arrays.asList(
                    item.getIdEmpleado(),
                    item.getNombres(),
                    item.getApellidos(),
                    item.getTelefono(),
                    item.getCelular(),
                    item.getDocumento(),
                    item.getTipoEmpleado()
                )
            ));
        });
        UtilTable.AddActions(view.table,this);
    }
    
    
    public void eliminar(int row) {
        var items = UtilTable.GetRow(view.table, row);
        
        if(UtilDialog.Question(view, "??Esta seguro que desea eliminar?")){
            var id = Long.valueOf(String.valueOf(items.get(0)));
            
            if(!empleadosDAO.sp_eliminarempleado(id)){
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
        view.nombresText.setText(String.valueOf(items.get(1)));
        view.apellidosText.setText(String.valueOf(items.get(2)));
        view.telefonoText.setText(String.valueOf(items.get(3)));
        view.celularText.setText(String.valueOf(items.get(4)));
        view.documentoText.setText(String.valueOf(items.get(5)));
        view.tipoCombo.setSelectedItem(String.valueOf(items.get(6)));
        
        this.setEstado(false);
    }
    
    public void guardar() {
        this.ejecutarGuardar();
        this.setEstado(true);
    }
    
    public void ejecutarGuardar() {
        if(UtilDialog.Question(view, "??Esta seguro que desea actualizar?")){
            Empleados item = new Empleados();
            var id = Long.valueOf(view.identificadorText.getText());
            var dt = UtilDate.Now();
            
            item.setIdEmpleado(id);
            item.setNombres(view.nombresText.getText());
            item.setApellidos(view.apellidosText.getText());
            item.setCelular(view.celularText.getText());
            item.setDocumento(view.documentoText.getText());
            item.setTelefono(view.telefonoText.getText());
            String tipo = (String)view.tipoCombo.getSelectedItem();
            item.setTipoEmpleado(tipo);
            item.setFecha(dt.getFecha());
            item.setFechaHora(dt.getFechaHora());
            item.setTimestamp(dt.getTimestamp());
            
            if(!empleadosDAO.sp_editarempleado(item)){
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
