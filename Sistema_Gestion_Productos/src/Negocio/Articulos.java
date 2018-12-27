package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Articulos extends Producto {

    String opcion = "";
    Scanner sc = new Scanner(System.in);
    Scanner waitForKeypress = new Scanner(System.in);

    public Articulos() {
    }

    @Override

    public void anadirProducto(String url, String username, String pass, Scanner sc) {

        do {
            System.out.println("\n\n\n\n Añadir articulo");
            System.out.println("-------------\n");
            try {

                System.out.print("Escanea el Codigo del producto: ");
                cod_producto = sc.nextLine();
                System.out.print("Ingrese el nombre del producto: ");
                descripcion = sc.nextLine();

                System.out.print("Ingrese el stock del producto: ");
                stock = sc.nextInt();
                System.out.print("Ingrese el vencimiento del producto MM/dd/aaaa ");
                sc.nextLine();
                vencimiento = sc.nextLine();

                System.out.print("Ingrese precio final : ");
                precio_final = sc.nextDouble();
                System.out.print("Ingrese el codigo a la  categoria correspondiente: ");
                sc.nextLine();
                cod_categoria = sc.nextLine();
            } catch (Exception e) {
                System.out.println("valor o valores erroneos" + e);
            }
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "INSERT INTO bebidas VALUES ('" + cod_producto + "', '" + descripcion + "'," + stock + ",'" + vencimiento + "'," + precio_final + ",'" + cod_categoria + "')";

                if (stmt.executeUpdate(query) == 1) {
                    JOptionPane.showMessageDialog(null, "El producto fue añadido al sistema con exito. Presione Enter para continuar.");

                } else {

                    JOptionPane.showMessageDialog(null, "Fallo al insertar el producto!");
                }

            } catch (SQLException e) {
                System.out.println("Exception creating connection: " + e);
                System.out.println("Presione enter para continuar");
                //System.exit(0);
            }
            System.out.print("Presiona enter para agregar otro producto -s- para Salir: ");
            
            opcion = sc.nextLine();
       
            System.out.println("Presione enter para continuar");

        } while (!opcion.equalsIgnoreCase("s"));

    }

    @Override
    public void modificarProducto(String url, String username, String pass, Scanner sc) {
        do {
            System.out.println("\n\n\n\n\n\n\n\n\nModificación de articulos");
            System.out.println("----------------\n");
            try {
                System.out.print("ESCANEA EL CODIGO DEL PRODUCTO A MODIFICAR O ACTUALIZAR: ");
                cod_producto = sc.nextLine();
                System.out.print("Ingresa el nombre del producto: ");
                descripcion = sc.nextLine();
                System.out.print("INGRESA el stock: ");
                stock = sc.nextInt();
                System.out.print("INGRESA la fecha de vencimiento MM/dd/aaaa : ");
                sc.nextLine();
                vencimiento = sc.nextLine();

                System.out.print("Actualice el precio: ");
                precio_final = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Error ingresando los valores"+e);
            }
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                String query = "UPDATE bebidas SET descripcion_bebida = '" + descripcion + "',stock=" + stock + " ,vencimiento='" + vencimiento + "',precio_final=" + precio_final + " "
                        + "      where codigo_bebida = '" + cod_producto + "'";

                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("PRODUCTO modificado con exito. Presione Enter para continuar.");
                } else {
                    System.out.println("Fallo al modificar PRODUCTO!");
                }
            } catch (SQLException e) {
                System.out.println("Exception creating connection: " + e);
                System.out.println("Presione enter para continuar");
                // System.exit(0);
            }
            sc.nextLine();
            System.out.print("Presiona enter para modificar otro producto - s- Salir");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");

        } while (!opcion.equalsIgnoreCase("s"));
        System.out.println("Presione enter para continuar");
    }

    @Override
    public void eliminarProducto(String url, String username, String pass, Scanner sc) {
        do {
            System.out.println("\n\n\n\n\n\n\n\n\nEliminación de Articulos");
            System.out.println("----------------------\n");
            try {
                System.out.print("Escanea el codigo del producto que quiere eliminar: ");
                cod_producto = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                String query = "DELETE FROM bebidas where codigo_bebida = '" + cod_producto + "'";

                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El producto fue eliminado con exito. Presione Enter para continuar.");
                } else {
                    System.out.println("Fallo al eliminar producto!");
                }
            } catch (SQLException e) {
                System.out.println("Exception creating connection: " + e);
                System.out.println("Presione enter para continuar");

            }
            System.out.print("Presiona enter para eliminar otro producto  s- Salir: ");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");

        } while (!opcion.equalsIgnoreCase("s"));
        System.out.println("Presione enter para continuar");
    }

    @Override
    public void buscarProductoXcodigo(String url, String username, String pass, Scanner sc) {
        do {
            int size = 0;

            System.out.println("\n\n\n\n\n\n\n\n\nLista de Articulos");
            System.out.println("----------------\n");
            System.out.print("Escanea el codigo: ");
            cod_producto = sc.nextLine();

            String query = "SELECT * FROM bebidas where codigo_bebida = '" + cod_producto + "'";

            System.out.println("NOMNRE \t \t\t\t\t CODIGO BEBIDA  \tSTOCK \t \t  VENCIMIENTO \t  \t PRECIO  \t \t   COD_CATEGORIA ");
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    cod_producto = rs.getString("codigo_bebida");
                    descripcion = rs.getString("descripcion_bebida");
                    stock = rs.getInt("stock");
                    vencimiento = rs.getString("vencimiento");
                    precio_final = rs.getDouble("precio_final");
                    cod_categoria = rs.getString("codigo_categoria");

                    System.out.println(descripcion + " \t\t \t\t\t" + cod_producto + " \t\t " + stock + "\t \t  " + vencimiento + " \t \t " + precio_final + " \t  \t  \t  \t  " + cod_categoria);
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
            System.out.print("Presiona enter para buscar otro producto  s- Salir: ");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");
        } while (!opcion.equalsIgnoreCase("s"));
        System.out.println("Presione enter para continuar");
    }

    @Override
    public void mostrarProductos(String url, String username, String pass, Scanner sc) {
        String query = "SELECT * FROM bebidas";
        int size = 0;

        System.out.println("\n\n\n\n\n\n\n\n\nLista de productos");
        System.out.println("----------------\n");

        System.out.println("Descripcion \t\t\t\t Codigo  \t  \t stock \t \t  vencimiento \t  \t precio  \t \t   codigo_categoria ");

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                descripcion = rs.getString("Descripcion_bebida");
                cod_producto = rs.getString("Codigo_bebida");
                stock = rs.getInt("Stock");
                vencimiento = rs.getString("Vencimiento");
                precio_final = rs.getDouble("Precio_final");
                cod_categoria = rs.getString("Codigo_categoria");

                System.out.println(descripcion + " \t\t\t\t\t " + cod_producto + " \t\t " + stock + "\t \t  " + vencimiento + " \t \t " + precio_final + " \t  \t  \t  \t  " + cod_categoria);
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

}
