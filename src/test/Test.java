/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.ClienteDAO;
import DAO.ClienteDAOFactory;
import Model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.ConnectDB;

/**
 *
 * @author Alumne
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static Scanner read = new Scanner(System.in);
    

    public static void main(String[] args) {
        // TODO code application logic here
        read.useDelimiter("\n");
        mostrarMenuInici();

    }

    public static void mostrarMenuInici() {

        ClienteDAOFactory factory = new ClienteDAOFactory();
        ClienteDAO dao = factory.createClienteDAO();
        Cliente c ;
        int opc = 0;
        System.out.println("Bienvenido a Avalon (La competencia de Amazon)");
        try {
            Connection con = ConnectDB.getInstance();
            do {

                System.out.println("_________MENÚ_________");
                System.out.println("1.Iniciar sesión");
                System.out.println("2.Registrarse");
                System.out.println("3.Salir");

                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        c = dao.login(con);
                        mostrarMenuUsuari(c);
                        
                        break;

                    case 2:
                        dao.registro(con);
                        break;

                    case 3:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("opcio incorrecte");
                        break;

                }
            } while (opc != 3);
        } catch (InputMismatchException e) {
            System.err.println("Introduce una opción válida");
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void mostrarMenuUsuari(Cliente c){
    
        int opc = 0;
        System.out.println("Encantados de volver a verte " + c.getNombre());
        while(opc!=5){
        
             System.out.println("1.Ver lista de productos: ");
             
             System.out.println("2.Historial de compras");
             
             System.out.println("3.Configuración de usuario");
             
             System.out.println("4.Salir");
        
        }
    
    }

}
