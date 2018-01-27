/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.ClienteDAO;
import DAO.ClienteDAOFactory;
import DAO.FacturaDAO;
import DAO.FacturaDAOFactory;
import DAO.ProductoDAO;
import DAO.ProductoDAOFactory;
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
    public static ClienteDAOFactory factoryCliente = new ClienteDAOFactory();
    public static ClienteDAO daoCliente = factoryCliente.createClienteDAO();
    
    public static ProductoDAOFactory factoryProducto = new ProductoDAOFactory();
    public static ProductoDAO daoProducto = factoryProducto.createProductoDAO();
    
    public static FacturaDAOFactory factoryFactura = new FacturaDAOFactory();
    public static FacturaDAO facturaDAO = factoryFactura.createFacturaDAO();

    public static void main(String[] args) {
        // TODO code application logic here
        read.useDelimiter("\n");
        mostrarMenuInici();

    }

    public static void mostrarMenuInici() {

        Cliente c;
        int opc = 0;
        Connection con = null;
        System.out.println("Bienvenido a Avalon (La competencia de Amazon)");
        try {
            con = ConnectDB.getInstance();
            do {

                System.out.println("_________MENÚ_________");
                System.out.println("1.Iniciar sesión");
                System.out.println("2.Registrarse");
                System.out.println("3.Salir");

                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        if ((c = daoCliente.login(con)) != null) {
                            mostrarMenuUsuari(c);
                        }

                        break;

                    case 2:
                        daoCliente.registro(con);
                        break;

                    case 3:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opcion incorrecta");
                        break;

                }
            } while (opc != 3);
        } catch (InputMismatchException e) {
            System.err.println("Introduce una opción válida");
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }

    }

    public static void mostrarMenuUsuari(Cliente c) {

        int opc = 0;
        System.out.println("Encantados de volver a verte " + c.getNombre());
        Connection con = null;
        while (opc != 5) {

            try {
                con = ConnectDB.getInstance();

                System.out.println("1.Ver lista de productos");

                System.out.println("2.Historial de compras");

                System.out.println("3.Cargar credito en la cuenta");
                
                System.out.println("4.Configuración de usuario");

                System.out.println("5.Cerrar sesión");

                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        menuProductos(c);
                        break;

                    case 2:
                        facturaDAO.consultarFacturas(con);
                        break;

                    case 3:
                        daoCliente.cargarCredito(con, c.getCorreo());
                        break;

                    case 4:
                        configUser(c);
                        break;

                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public static void configUser(Cliente c) {
            
        int opc = 0;
        Connection con = null;
        while (opc != 3) {

            try {
                con = ConnectDB.getInstance();

                System.out.println("1.Cambiar contraseña ");

                System.out.println("2.Eliminar cuenta");

                System.out.println("3. Atras");

                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        daoCliente.cambiarContrasenya(con, c.getCorreo());
                        break;

                    case 2:
                        daoCliente.borrarCuenta(con, c);
                        break;
                    case 3:
                        System.out.println("");
                        break;
                    default: 
                        System.out.println("Entra un valor válido");
                        break;
                }
            }catch(NumberFormatException e){
                System.err.println("Se esperaba un numero");
            }
            catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public static void menuProductos(Cliente c){
        
        int opc = 0;
        Connection con = null;
        while (opc != 4) {

            try {
                con = ConnectDB.getInstance();

                System.out.println("1. Mostrar todos los productos");

                System.out.println("2. Mostrar productos por categorias");
                
                System.out.println("3. Comprar un producto");

                System.out.println("4. Atras");

                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        daoProducto.listarProductos(con);
                        break;

                    case 2:
                        daoProducto.listarProductosPorCategoria(con);
                        break;
                        
                    case 3:
                        daoProducto.listarProductos(con);
                        System.out.println("Introduce el id del producto:");
                        int aux = read.nextInt();
                        if(daoProducto.existeProducto(con,aux)){
                            
                        }else{
                            System.out.println("No existe el producto con ese id");
                        }
                        break;


                }
            }catch(NumberFormatException e){
                System.err.println("Se esperaba un numero");
            }
            catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    
    

}
