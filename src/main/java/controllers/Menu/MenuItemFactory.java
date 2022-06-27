package controllers.Menu;

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
    public static String BOLETA = "Boleta";
    public static String FACTURA = "Factura";
    
    
    public static MenuItem getMenuItem(String key){       
        switch(key){
            case "Productos":
                return new MenuItemProductos(key);
            case "Inventario":
                return new MenuItemInventario(key);
            case "Categorias":
                return new MenuItemCategorias(key);
            case "Boleta":
                return new MenuItemProductos(key);
            case "Factura":
                return new MenuItemProductos(key);
            default:
                return null;
        }
    }
}
