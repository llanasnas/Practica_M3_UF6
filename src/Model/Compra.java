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
public class Compra {
    
    private int id;
    private int id_cliente;
    private int id_producto;
    private int cantidad;

    public Compra(int id, int id_cliente, int id_producto, int cantidad) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public Compra(int id_cliente, int id_producto, int cantidad) {
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
