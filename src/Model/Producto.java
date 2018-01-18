/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alumne
 */
public class Producto {
    
    private int id;
    private String nombre,categoria,descripcion;
    private int stock;
    private double precio;

    public Producto(int id, String nombre, String categoria, String descripcion, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }
    
     public Producto(String nombre, String categoria, String descripcion, int stock, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public String toStringConCategoria() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
    
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
     
     
    
    
}
