package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Categoria extends Articulos {

    private String descripcion_categoria;

    public Categoria() {
    }

    public void crearCategoriasProducto(String url, String username, String pass, Scanner sc) {
do{
        System.out.println("\n\n\n\n Añadir Categorias");
        System.out.println("-------------\n");

        System.out.print("Ingresa el Codigo de la categoria : ");
        cod_categoria = sc.nextLine();

        System.out.print("Ingrese el nombre o descripcion de la categoria: ");
        
        descripcion = sc.nextLine();

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();

            String query = "INSERT INTO categoria VALUES ('" + cod_categoria + "', '" + descripcion + "')";

            if (stmt.executeUpdate(query) == 1) {
                System.out.println("La Categoria fue añadida al sistema con exito.\n Presione Enter para continuar.");

            } else {
                System.out.println("Fallo al insertar la categoria!");
            }

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
             System.out.println("Presione enter para continuar");
        }
 System.out.println ("Presiona enter para agregar otra categoria - s- Salir: ");
             opcion=sc.nextLine();
             
}while(!opcion.equalsIgnoreCase("s"));

    }

    public void modificarCategoria(String url, String username, String pass, Scanner sc) {
do{
        System.out.println("\n\n\n\n\n\n\n\n\nModificación de Categoria");
        System.out.println("----------------\n");
try{
        System.out.print("Ingresa CODIGO de la categoria  A MODIFICAR O ACTUALIZAR: ");
        cod_producto = sc.nextLine();
        System.out.print("Ingresa el nombre de la categoria: ");
        descripcion = sc.nextLine();
}catch(Exception e){
    System.out.println("error");
}
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            String query = "UPDATE categoria SET descripcion_categoria = '" + descripcion + "'  where codigo_categoria = '" + cod_producto + "'";

            if (stmt.executeUpdate(query) == 1) {
                System.out.println("Categoria modificada con exito. Presione Enter para continuar.");
            } else {
                System.out.println("Fallo al modificar categoria!");
            }
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            
               System.out.println("Presione enter para continuar");
        }
System.out.println ("Presiona enter para modificar otra categoria - s- Salir: ");
             opcion=sc.nextLine();
             
}while(!opcion.equalsIgnoreCase("s"));

    }

    public void mostrarTodosLosProductosPorCategoria(String url, String username, String pass, Scanner sc) {
    
        int size = 0;

        System.out.println("\n\n\n\n\n\n\n\n\nLista de Categorias y codigos");
        System.out.println("----------------\n");

        String query = "select*from vista_categoria_produc  ";

        System.out.println("Nombre de las bebidas\t \t\t\t  Nombre  de la categoria  ");
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                descripcion = rs.getString("descripcion_bebida");
                descripcion_categoria = rs.getString("descripcion_categoria");

                System.out.println(descripcion + " \t\t \t\t \t\t " + descripcion_categoria + " \t\t ");
                size++;

            }

            if (size == 0) {
                System.out.println("No se encontraron registros...");
            }

            System.out.println("\nPresione Enter para continuar.");

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
             System.out.println("Presione enter para continuar");
            
        }
    }

    public void MostrarProductosPorCategoria(String url, String username, String pass, Scanner sc) {
        int size = 0;
do{
        System.out.println("\n\n\n\n\n\n\n\n\nLista de Articulos");
        System.out.println("----------------\n");
        System.out.print("Ingresa el codigo de la categoria: ");
        cod_producto = sc.nextLine();

        String query = "SELECT b.descripcion_bebida,c.descripcion_categoria"
                + " FROM bebidas AS b inner join categoria as c"
                + " on c.codigo_categoria=b.codigo_categoria"
                + " and c.codigo_categoria = '" + cod_producto + "'";

        System.out.println("Nombre del articulo   \t \t\t \t  Nombre de la Categoria  ");
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                descripcion = rs.getString("descripcion_bebida");
                descripcion_categoria = rs.getString("descripcion_categoria");

                System.out.println(descripcion + " \t \t\t \t\t \t \t " + descripcion_categoria);
                size++;

            }

            if (size == 0) {
                System.out.println("No se encontraron registros...");
            }

            System.out.println("\nPresione Enter para continuar.");

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
           System.out.println("Presione enter para continuar");  
          
        }
System.out.print ("Presiona enter para mostrar otra categoria - s- Salir: ");
             opcion=sc.nextLine();
              System.out.println("Presione enter para continuar");
}while(!opcion.equalsIgnoreCase("s"));
    }

    public void eliminarCategoria(String url, String username, String pass, Scanner sc) {
do{
        System.out.println("\n\n\n\n\n\n\n\n\nEliminación de Categorias");
        System.out.println("----------------------\n");
try{
        System.out.print("Escribe codigo de la categoria que quiere eliminar: ");
        cod_producto = sc.nextLine();
}catch(Exception e){
    System.out.println("valor ingresado incorrecto");
}
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            String query = "DELETE FROM categoria where codigo_categoria = '" + cod_producto + "'";

            if (stmt.executeUpdate(query) == 1) {
                System.out.println("La Categoria fue eliminado con exito.\n Presione Enter para continuar.");
            } else {
                System.out.println("Fallo al eliminar la categoiria!");
            }
        } catch (SQLException e) {
            System.out.println("no puede eliminar categorias con productos relacionados " );
            System.out.println("Presione enter para continuar");
           
        }
System.out.print ("Presiona enter para eliminar otra categoria - s- Salir: ");
             opcion=sc.nextLine();
             
}while(!opcion.equalsIgnoreCase("s"));
System.out.println("Presione enter para continuar");
    }

}
