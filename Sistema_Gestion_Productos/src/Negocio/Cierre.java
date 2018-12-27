package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cierre extends Articulos {

    private Integer numero_venta;
    private Double ingresos;
    private Integer cantidad_productos;
    private Double egresos;
    private Double total;
    private String fecha_cierre;

    public Cierre() {
    }

    public void nuevaVentaYegreso(String url, String username, String pass, Scanner sc) {
       
        do {
            System.out.println("\n\n\n\n Ingresar Venta");
            

            Calendar cal = Calendar.getInstance();
            vencimiento = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
            

            System.out.print("Escanea el Codigo del producto: ");
            cod_producto = sc.nextLine();

            do {
                System.out.print("Ingresa la cantidad de productos : ");
                cantidad_productos = sc.nextInt();
               } while (cantidad_productos <= 0);
            consultarStock(url, username, pass, sc, cod_producto, cantidad_productos);
           
            ingresos = calcularMonto(url, username, pass, sc, cod_producto, cantidad_productos);
            System.out.println("la suma de la venta es: " + ingresos);

            System.out.print("Ingresa el monto de la extraccion: ");
            egresos = sc.nextDouble();

            total = (ingresos - egresos);

            sc.nextLine();
            System.out.print("Presiona enter para confirmar venta  -s- para cancelar: ");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");
            if (!opcion.equalsIgnoreCase("s")) {
                actualizarStock(url, username, pass, sc, cod_producto, cantidad_productos);
                try (Connection con = DriverManager.getConnection(url, username, pass)) {
                    Statement stmt = con.createStatement();

                    String query = "insert Into cierre (fecha_cierre, ingresos, egresos, total) values ('" + vencimiento + "'," + ingresos + ", " + egresos + "," + total + ")";
                    if (stmt.executeUpdate(query) == 1) {
                        JOptionPane.showMessageDialog(null, "Registro de venta añadido al sistema con exito. Presione Enter para continuar.");

                    } else {

                        JOptionPane.showMessageDialog(null, "Fallo al insertar el producto!");
                    }

                } catch (SQLException e) {
                    System.out.println("Exception creating connection: principal" + e);
                    System.out.println("Presione enter para continuar");
                    
                }
            }

            sc.nextLine();
            System.out.print("Presiona enter para agregar otra venta  -s- para Salir: ");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");

        } while (!opcion.equalsIgnoreCase("s"));

    }

    public void verVentas(String url, String username, String pass, Scanner sc) {

        String query = "SELECT * FROM cierre";
        int size = 0;

        System.out.println("\n\n\n\n\n\n\n\n\nLista de ventas");
        System.out.println("----------------\n");

        System.out.println("Numero ventas \t\t Fecha de venta  \t  \t \tIngresos \t \t Egresos  \t Total  \t \t  ");

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                numero_venta = rs.getInt("numero_venta");
                fecha_cierre = rs.getString("fecha_cierre");
                ingresos = rs.getDouble("ingresos");
                egresos = rs.getDouble("egresos");
                total = rs.getDouble("total");

                System.out.println(numero_venta + " \t\t\t " + fecha_cierre + " \t\t\t\t " + ingresos + "\t \t \t " + egresos + " \t \t " + total + " \t  ");
                size++;
            }

            if (size == 0) {
                System.out.println("No se encontraron registros...");
            }

            System.out.println("Presione Enter para continuar.");

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.out.println("Presione enter para continuar");

        }

    }

    public void eliminarVenta(String url, String username, String pass, Scanner sc) {

        do {

            System.out.println("\n\n\n\n\n\n\n\n\nEliminación de Registro de venta");
            System.out.println("----------------------\n");

            System.out.print("Ingresa el numero de venta que quiere eliminar: ");
            numero_venta = sc.nextInt();

            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                String query = "DELETE FROM cierre where numero_venta = " + numero_venta + "";

                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El registro fue eliminado con exito.\n Presione Enter para continuar.");
                } else {
                    System.out.println("Fallo al eliminar registro!");
                }
            } catch (SQLException e) {
                System.out.println("Exception creating connection: " + e);
                System.out.println("Presione enter para continuar");

            }
            sc.nextLine();
            System.out.print("Presiona enter para eliminar otra venta  -s- para Salir: ");
            opcion = sc.nextLine();
            System.out.println("Presione enter para continuar");
        } while (!opcion.equalsIgnoreCase("s"));

    }

    public void ventasDelDia(String url, String username, String pass, Scanner sc) {

        int size = 0;
        String query = "SELECT sum(total)total_ingresos FROM cierre ";

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                ingresos = rs.getDouble("total_ingresos");
                size++;

            }

            if (size == 0) {
                System.out.println("No se encontraron registros...");
            }

            JOptionPane.showMessageDialog(null, "El monto total de las ventas diarias es: " + ingresos);
            System.out.println("Presione enter para continuar");
        } catch (SQLException e) {
            System.out.println("Exception creating connection:funcion " + e);
            System.out.println("Presione enter para continuar");
            //System.exit(0);
        }

    }

    public Double calcularMonto(String url, String username, String pass, Scanner sc,
            String cod_producto, Integer cantidad_productos) {
        int size = 0;
        String query = "SELECT precio_final FROM bebidas where codigo_bebida = '" + cod_producto + "'";

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                precio_final = rs.getDouble("precio_final");
                size++;

            }

            if (size == 0) {
                System.out.println("No se encontraron registros...");
            }

            // System.out.println("\nPresione Enter para continuar.");
        } catch (SQLException e) {
            System.out.println("Exception creating connection:funcion " + e);
            System.out.println("Presione enter para continuar");
            //System.exit(0);
        }

        return (precio_final * cantidad_productos);
    }

    public void actualizarStock(String url, String username, String pass, Scanner sc, String cod_producto, Integer cantidad_productos) {

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();

            String query = " Update bebidas Set stock = stock " + (-cantidad_productos) + " Where codigo_bebida= '" + cod_producto + "'";

            if (stmt.executeUpdate(query) == 1) {
                JOptionPane.showMessageDialog(null, "Stock actualizado");

            } else {

                JOptionPane.showMessageDialog(null, "Fallo al modificar el stock");
            }

        } catch (SQLException e) {
            System.out.println("Exception creating connection: principal" + e);
            System.out.println("Presione enter para continuar");

        }

    }

    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    public void consultarStock(String url, String username, String pass, Scanner sc,
            String cod_producto, Integer cantidad_productos) {
      Integer resto=0;
        String query = "SELECT stock FROM bebidas where codigo_bebida = '" + cod_producto + "'";

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                stock = rs.getInt("stock");

            }
            if (stock >= cantidad_productos) {

                resto = stock - cantidad_productos;
            JOptionPane.showMessageDialog(null, resto + " en Stock");
            }
            else{
                resto=stock-cantidad_productos;
                if(resto<0)
                    System.out.println("sin stock cancelar la venta");
                    System.out.println("La cantidad maxima en stock es: "+stock);
                
               } 

            //    JOptionPane.showMessageDialog(null, stock + " en Stock");
               
                //System.exit(0);
            

        } catch (SQLException e) {
            System.out.println("Exception creating connection:funcion " + e);
            System.out.println("Presione enter para continuar");

        }
 
    
    }
}