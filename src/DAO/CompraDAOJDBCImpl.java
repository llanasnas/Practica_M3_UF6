/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;
import static test.Test.read;

/**
 *
 * @author Gerard
 */
public class CompraDAOJDBCImpl implements CompraDAO{

    @Override
    public void realizarCompra(Connection con,int id,Cliente c) {
        
        System.out.println("Introduce la cantidad deseada:");
        int cantidad = read.nextInt();      
        if (c.getSaldo()<precioProducto(con,id)*cantidad){
            System.out.println("No tienes suficientes fondos");
        }else{
           
        ResultSet rs = null;
        String query = "";
        try (
                
                PreparedStatement stmt = con.prepareStatement(query);) {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        }
    }

    @Override
    public void cambiarStock(Connection con) {
        
    }

    @Override
    public double precioProducto(Connection con, int id) {
         ResultSet rs = null;
        String query = "SELECT precio FROM producto WHERE id=?";
        
        try (
                PreparedStatement stmt = con.prepareStatement(query);) {
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery(query);
            return rs.getDouble("precio");
            

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return 0;
    }
    
    
    
    
    
    
}
