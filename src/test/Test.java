/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
        mostrarMenuInici();

    }

    public static void mostrarMenuInici() {

        int opc = 0;
        System.out.println("Bienvenido a Avalon (La competencia de Amazon)");
        do {

            System.out.println("_________MENÚ_________");
            System.out.println("1.Iniciar sesión");
            System.out.println("2.Registrarse");
            System.out.println("3.Salir");
            try {
                opc = read.nextInt();

                switch (opc) {

                    case 1:
                        login();
                        break;

                    case 2:
                        registro();
                        break;

                    case 3:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("opcio incorrecte");
                        break;

                }
            } catch (InputMismatchException e) {
                System.err.println("Introduce una opción válida");
            }

        } while (opc != 3);
    }

    public static void registro() {
        boolean correcto = false;
        String[] datos = {"Nombre: ", "Apellido: ", "Direccion: ", "Telefono: ", "Correo: ", "Contraseña: "};
        String aux;
        Cliente c = new Cliente();
        for (String data : datos) {
            switch (data) {

                case "Nombre: ":

                    System.out.println(datos[0]);
                    c.setNombre(read.nextLine());

                    break;
                case "Apellido: ":

                    System.out.println(datos[1]);
                    c.setApellido(read.nextLine());

                    break;
                case "Direccion: ":

                    System.out.println(datos[2]);
                    c.setDireccion(read.nextLine());

                    break;

                case "Telefono: ":

                    while (!correcto) {

                        System.out.println(datos[3]);
                        aux = read.nextLine();
                        if (aux.length() != 9) {
                            System.err.println("Tiene que tener una longitud de 9 carácteres");
                        } else {
                            try {
                                int numerico = Integer.parseInt(aux);
                                c.setTelefono(aux);
                                correcto = true;
                            } catch (NumberFormatException e) {
                                System.err.println("Tiene que tener formato numérico");
                            }

                        }

                    }
                    correcto = false;
                    break;

                case "Correo: ":

                    while (!correcto) {
                        System.out.println(datos[4]);
                        aux = read.nextLine();
                        if (!aux.contains("@") || !aux.contains(".")) {
                            System.err.println("Introduce un formato de email correcto");
                        } else {
                            if(userExists(aux)){
                            correcto = true;
                            c.setCorreo(aux);
                            }else{
                                System.out.println("Este correo ya tiene una cuenta registrada");
                            }
                        }
                    }
                    correcto = false;
                    break;

                case "Contraseña: ":

                    while (!correcto) {

                        System.out.println("La contraseña tiene que tener una longitud mínima de 6 carácteres.");
                        System.out.println(datos[5]);
                        aux = read.nextLine();
                        if (aux.length() < 6) {
                            System.err.println("La contraseña tiene que tener una longitud mínima de 6 carácteres.");
                        } else {
                            System.out.println("Usuario creado con éxito.");                            
                            c.setPassword(aux);
                            nuevoUsuario(c);
                            correcto=true;
                        }

                    }                    

                    break;
            }
        }

    }

    public static void login() {
 
        String user, passwd = "";
        boolean correcto = false;
 
        System.out.println("Introduce el correo:");
        while (!correcto) {
            user = read.next();
            if (!user.contains("@") || !user.contains(".")) {
                System.err.println("Introduce un formato de email correcto");
            } else {
                correcto = true;
                if (userExists(user)) {
                    //Login
                    System.out.println("Introduce la contraseña:");
                    passwd = read.next();
                    if (checkUserPasswd(user,passwd) != null) {
                        Cliente c = checkUserPasswd(user,passwd);
                        System.out.println("has entrao atontao");
                    }else{
                        System.err.println("Password incorrecta, volviendo al menu...");
                    }
 
                } else {
                    System.err.println("Este usuario no se encuentra registrado");
                }
 
            }
        }
 
    }

  public static boolean userExists(String username) {
        String mailQuery = "SELECT id FROM Cliente WHERE correo = ?";
        ResultSet rs = null;
        Cliente c = null;
        try (
            Connection con = ConnectDB.getInstance();
            PreparedStatement preparedStatement = con.prepareStatement(mailQuery);) {
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }finally{
            try {
                ConnectDB.closeConnection();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
  public static Cliente checkUserPasswd(String username, String passwd){
        String mailQuery = "SELECT * FROM Cliente WHERE correo = ? AND password = ?";
        ResultSet rs = null;
        Cliente c = null;
        try (
            Connection con = ConnectDB.getInstance();
            PreparedStatement preparedStatement = con.prepareStatement(mailQuery);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwd);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreo(rs.getString("correo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }finally{
            try {
                ConnectDB.closeConnection();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    public void entradaSistema() {

    }

    public static void nuevoUsuario(Cliente c) {

         String insert = "INSERT INTO `cliente`(`id`, `nombre`, `apellido`, `direccion`, `telefono`, "
                 + "`correo`, `password`) VALUES (null,?,?,?,?,?,?)";

        try (Connection con = ConnectDB.getInstance();
                PreparedStatement prepared = con.prepareStatement(insert);) {
            
            
            prepared.setString(1, c.getNombre());
            prepared.setString(2, c.getApellido());
            prepared.setString(3, c.getDireccion());
            prepared.setString(4, c.getTelefono());
            prepared.setString(5, c.getCorreo());
            prepared.setString(6, c.getPassword());
            
            prepared.executeUpdate();
             
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                ConnectDB.closeConnection();
            } catch (SQLException e) {
                System.err.println("Exception: " + e);
            }
        }

    }

}
