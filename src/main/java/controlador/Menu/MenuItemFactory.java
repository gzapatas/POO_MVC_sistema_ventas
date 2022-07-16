package controlador.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class MenuItemFactory {
    public static String PRODUCTOS = "Productos";
    public static String INVENTARIO = "Inventario";
    public static String CATEGORIAS = "Categorias";
    public static String EMPLEADOS = "Empleados";
    public static String VENTAS = "Ventas";
    public static String ACERCA = "Acerca";
    public static String CLIENTES = "Clientes";
    
    
    public static MenuItem getMenuItem(String key){       
        switch(key){
            case "Productos":
                return new MenuItemProductos(key);
            case "Inventario":
                return new MenuItemInventario(key);
            case "Categorias":
                return new MenuItemCategorias(key);
            case "Empleados":
                return new MenuItemEmpleados(key);
            case "Clientes":
                return new MenuItemClientes(key);
            case "Ventas":
                return new MenuItemVentas(key);
            case "Acerca":
                return new MenuItemAcerca(key);
            default:
                return null;
        }
    }
}
