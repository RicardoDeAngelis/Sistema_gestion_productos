package Negocio;

import Conectar.Conexion;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String opcion ;
        
        Conexion con =  Conexion.getSingletonInstancie();
         try{
        con.ConectarDB();
         }catch(Exception  e){
             System.out.println("Conexion cancelada"+e);
             System.exit(0);
         }
        Articulos art = new Articulos();
        Categoria cate = new Categoria();
        Cierre ven = new Cierre();
        Scanner waitForKeypress = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        do {

            
            System.out.println("\nOpciones de categorias:");
            System.out.println("=========================\n");
            System.out.println("1- Crear categorias de productos");
            System.out.println("2- Modificar categorias");
            System.out.println("3- Mostrar todos los Productos con su Categorias ");
            System.out.println("4- Buscar y mostrar todos los productos por categoria ");
            System.out.println("5- Eliminar una Categoria ");
            System.out.println("\nOpciones  de Productos:");
            System.out.println("===========================\n");
            System.out.println("6- Ingresar Producto ");
            System.out.println("7- Modificar Producto");
            System.out.println("8- Eliminar Producto");
            System.out.println("9- Mostrar todos los Productos");
            System.out.println("10-Buscar productos por Codigo");
            System.out.println("\nOpciones de Ventas:");
            System.out.println("===========================\n");
            
            
            System.out.println("11- Nueva venta y egreso");
            System.out.println("12- Ver ventas y egresos");
            System.out.println("13- Eliminar una venta y egreso");
            System.out.println("14- Ventas del dia");
            System.out.println("===========================\n");
            System.out.println("s- Salir\n");

            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    cate.crearCategoriasProducto(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                case "2":
                    cate.modificarCategoria(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "3":
                    cate.mostrarTodosLosProductosPorCategoria(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "4":
                    cate.MostrarProductosPorCategoria(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                case "5":
                    cate.eliminarCategoria(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "6":
                    art.anadirProducto(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "7":
                    art.modificarProducto(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                case "8":
                    art.eliminarProducto(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "9":
                    art.mostrarProductos(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;

                case "10":
                    art.buscarProductoXcodigo(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                 case "11":
                    ven.nuevaVentaYegreso(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                 case "12":
                    ven.verVentas(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;
                    
                 case "13":
                    ven.eliminarVenta(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;   
                 case "14":
                    ven.ventasDelDia(con.getUrl(), con.getUsername(), con.getPass(), sc);
                    waitForKeypress.nextLine();
                    break;   
                case "s":
                case "S":
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta, seleccione una nueva opcion");
                    break;
            
            }

        } while (!opcion.equalsIgnoreCase("s"));
        
        
        borrarPantalla();
       System.exit(0);
       
       
        
    }

    public static void borrarPantalla() {

        for (int clear = 0; clear < 100; clear++) {
            System.out.println("\b");

        }

    }

   
}
