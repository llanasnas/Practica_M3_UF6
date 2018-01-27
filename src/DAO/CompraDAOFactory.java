/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Gerard
 */
public class CompraDAOFactory {

    public CompraDAO crearCompra() {
        return new CompraDAOJDBCImpl();
    }

}
