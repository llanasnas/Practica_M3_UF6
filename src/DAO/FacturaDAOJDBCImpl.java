/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Model.Compra;
import Model.Factura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;

/**
 *
 * @author Gerard
 */
public class FacturaDAOJDBCImpl implements FacturaDAO{

    @Override
    public void consultarFacturas(Connection con) {        
        
        String query = "SELECT * FROM factura AS f, cliente AS c, compra AS co WHERE c.id=co.id_cliente AND co.id=f.id_compra";
        
        //valors a la bd : id	id_compra	fecha	precio_total
        
        try(Statement stm = con.createStatement()){
        
            
            ResultSet rs = stm.executeQuery(query);
            Factura factura = new Factura();
            
            while(rs.next()){
                factura.setFecha(rs.getDate("fecha"));
                factura.setId_compra(rs.getInt("id_compra"));
                factura.setPrecio_total(rs.getDouble("precio_total"));
                System.out.println(factura.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void facturar(Connection con, Compra c) {
     
        String insert = "INSERT INTO `factura`(`id`, `id_compra`, `fecha`, `precio_total`) VALUES (null,?,?,?)";
        String query = "SELECT MAX(id) AS id FROM compra WHERE id_cliente=?";
        String queryPrice = "SELECT precio FROM producto WHERE id = ?";
        ResultSet rs = null;        
        int id;
        try (               PreparedStatement stmt = con.prepareStatement(query);
                PreparedStatement stmt2 = con.prepareStatement(insert);
                PreparedStatement stmt3 = con.prepareStatement(queryPrice);) {
            stmt.setInt(1, c.getId_cliente());
            rs = stmt.executeQuery();
           if(rs.next()){
               stmt2.setInt(1, rs.getInt("id"));  
           }
           stmt3.setInt(1, c.getId_producto());
           java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
           stmt2.setDate(2, date);           
           rs= stmt3.executeQuery();
           if(rs.next()){
               stmt2.setDouble(3, (rs.getDouble("precio"))*c.getCantidad());               
           }
           stmt2.executeUpdate();
           
            

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
