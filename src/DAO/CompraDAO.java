/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Compra;
import Model.Producto;
import java.sql.Connection;

/**
 *
 * @author Alumne
 */
public interface CompraDAO {  
    
    public Compra realizarCompra(Connection con,int id,Cliente c);    
    public void cambiarStock(Connection con,int id,int cantitat);
    public double precioProducto(Connection con, int id);
    
}
