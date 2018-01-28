/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import java.sql.Connection;
/**
 *
 * @author Alumne
 */
public interface ClienteDAO {
    

    public Cliente checkUserPassword(Connection con, String passwd,String username);
    public void registro(Connection con);
    public Cliente login(Connection con);
    public boolean userExists(Connection con, String username);
    public double cargarCredito(Connection con, String email);
    public void nuevoUsuario(Connection con,Cliente c);
    public void cambiarContrasenya(Connection con,String email);
    public void borrarCuenta(Connection con,Cliente c);
    
    
}
