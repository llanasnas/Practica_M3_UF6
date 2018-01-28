/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;
import static test.Test.read;

/**
 *
 * @author Gerard
 */
public class ProductoDAOJDBCImpl implements ProductoDAO {

    @Override
    public void listarProductos(Connection con) {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        String query = "SELECT id, nombre, descripcion, categoria, precio FROM producto WHERE stock > 0";
        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                productos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }

        for (Producto p : productos) {
            System.out.println(p.toStringConCategoria());
        }
    }

    @Override
    public void listarProductosPorCategoria(Connection con) {
        boolean existe = false;
        ArrayList<Producto> productos = new ArrayList<Producto>();
        String categoria = "";
        String query = "SELECT id,nombre, descripcion, precio FROM producto WHERE stock > 0 AND categoria = ?";
        String queryCat = "SELECT categoria FROM producto GROUP BY categoria";
        ResultSet rs = null;
        try (
                PreparedStatement stmt = con.prepareStatement(query);
                Statement stmtCat = con.createStatement();) {

            rs = stmtCat.executeQuery(queryCat);
            System.out.println("Categorias disponibles:");
            while (rs.next()) {
                System.out.println(rs.getString("categoria"));
            }
            while (!existe) {
                System.out.println("Introduce una categoria para filtrar:");
                categoria = read.next();
                if (existeCategoria(con, categoria)) {
                    stmt.setString(1, categoria);
                    existe = true;
                } else {
                    System.err.println("La categoria indicada no existe, porfavor introduce de nuevo");
                }
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                productos.add(p);
            }

            for (Producto p : productos) {
                System.out.println(p.toStringConCategoria());
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

    @Override
    public boolean existeCategoria(Connection con, String categoria) {
        String queryCat = "SELECT categoria FROM producto WHERE categoria = ? GROUP BY categoria";
        ResultSet rs = null;
        try (
                PreparedStatement stmt = con.prepareStatement(queryCat);) {

            stmt.setString(1, categoria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    public boolean existeProducto(Connection con,int aux) {

        String query = "SELECT id FROM producto WHERE id=?";
        ResultSet rs = null;        
        int id;
        try (               
                PreparedStatement stmt = con.prepareStatement(query);) {

            stmt.setInt(1,aux);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true; 
            }else{
                return false;
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
        return false;

    }

}
