/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Alumne
 */
public class Factura {
    
    private int id;
    private int id_compra;
    private Date fecha;
    private double precio_total;

    public Factura(int id, int id_compra, Date fecha, double precio_total) {
        this.id = id;
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.precio_total = precio_total;
    }
    public Factura(int id_compra, Date fecha, double precio_total) {        
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.precio_total = precio_total;
    }

    public int getId() {
        return id;
    }

    public int getId_compra() {
        return id_compra;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getPrecio_total() {
        return precio_total;
    }
    
    
}
