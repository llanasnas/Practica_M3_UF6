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
        double precioCompra = precioProducto(con,id)*cantidad;
        if (c.getSaldo()<precioCompra){
            System.out.println("No tienes suficientes fondos");
        }else{
           
        ResultSet rs = null;
        String insert = "INSERT INTO `compra`(`id`, `id_cliente`, `id_producto`, `cantidad`) VALUES (null,?,?,?)";
        String query = "SELECT id FROM cliente WHERE correo = ?";
        String update = "UPDATE cliente SET saldo = saldo - ? WHERE id = ?";
        try (
                PreparedStatement stmt = con.prepareStatement(query);
                PreparedStatement stmt2 = con.prepareStatement(insert);
                PreparedStatement stmt3 = con.prepareStatement(update);) {
           
            stmt.setString(1, c.getCorreo());
            rs = stmt.executeQuery();
            if(rs.next()){
            stmt2.setInt(1, rs.getInt("id"));
            stmt2.setInt(1, id);
            stmt2.setInt(3, cantidad);
            stmt2.executeUpdate();
            cambiarStock(con,rs.getInt("id"),cantidad);
            stmt3.setDouble(1,precioCompra);
            stmt3.setInt(2, rs.getInt("id"));
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
    public void cambiarStock(Connection con,int id,int cantitat) {
        
         String query = "UPDATE producto SET stock = stock - ? WHERE id = ?";
        
        try (
                PreparedStatement stmt = con.prepareStatement(query);) {
            
            stmt.setInt(1, cantitat);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
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
