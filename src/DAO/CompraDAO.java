/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Producto;
import java.sql.Connection;

/**
 *
 * @author Alumne
 */
public interface CompraDAO {  
    
    public void realizarCompra(Connection con);    
    public void cambiarStock(Connection con);
    
}
