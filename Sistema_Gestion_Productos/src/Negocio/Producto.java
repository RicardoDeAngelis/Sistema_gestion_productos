package Negocio;

import java.util.Scanner;

public abstract class Producto {

    protected String cod_producto;
    protected String descripcion;
   protected Integer stock;
    protected String vencimiento;
     protected Double precio_final;
    protected String cod_categoria;
 

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Double precio_final) {
        this.precio_final = precio_final;
    }

    public String getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(String cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public abstract void anadirProducto(String url, String username, String pass, Scanner sc);

    public abstract void mostrarProductos(String url, String username, String pass, Scanner sc);

    public abstract void buscarProductoXcodigo(String url, String username, String pass, Scanner sc);

    public abstract void modificarProducto(String url, String username, String pass, Scanner sc);

     public abstract void eliminarProducto(String url, String username, String pass, Scanner sc);

    
    
}
