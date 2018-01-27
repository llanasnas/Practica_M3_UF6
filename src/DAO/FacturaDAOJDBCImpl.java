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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                factura.toString();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void facturar(Connection con, Compra c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
