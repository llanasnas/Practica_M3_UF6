/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;



import Model.Cliente;
import Model.Compra;
import java.sql.Connection;
/**
 *
 * @author Gerard
 */
public interface FacturaDAO {
    
    public void consultarFacturas(Connection con, Cliente c);
    public void facturar(Connection con, Compra c);
    
    
}
