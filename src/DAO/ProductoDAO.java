/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Albert
 */
public interface ProductoDAO {
    
    public void listarProductos(Connection con);
    public void listarProductosPorCategoria(Connection con);
    public boolean existeCategoria(Connection con, String categoria);
    public boolean existeProducto(Connection con,int aux);
    
}
