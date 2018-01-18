/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;
import static test.Test.read;
import utilities.ConnectDB;

/**
 *
 * @author Alumne
 */
public class ClienteDAOJDBCImpl implements ClienteDAO {

    @Override
    public Cliente checkUserPassword(Connection con, String username, String passwd) {
        String mailQuery = "SELECT * FROM Cliente WHERE correo = ? AND password = ?";

        Cliente c = null;
        try (
                PreparedStatement preparedStatement = con.prepareStatement(mailQuery);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwd);
            ResultSet rs;
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

            e.printStackTrace();
        } finally {

        }
        return c;

    }

    @Override
    public void registro(Connection con) {
        boolean correcto = false;
        String[] datos = {"Nombre: ", "Apellido: ", "Direccion: ", "Telefono: ", "Correo: ", "Contraseña: "};
        String aux;
        Cliente c = new Cliente();
        for (String data : datos) {
            switch (data) {

                case "Nombre: ":

                    System.out.println(datos[0]);
                    c.setNombre(read.next());

                    break;
                case "Apellido: ":

                    System.out.println(datos[1]);
                    c.setApellido(read.next());

                    break;
                case "Direccion: ":

                    System.out.println(datos[2]);
                    c.setDireccion(read.next());

                    break;

                case "Telefono: ":

                    while (!correcto) {

                        System.out.println(datos[3]);
                        aux = read.next();
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
                        aux = read.next();
                        if (!aux.contains("@") || !aux.contains(".")) {
                            System.err.println("Introduce un formato de email correcto");
                        } else {
                            if (!userExists(con, aux)) {
                                correcto = true;
                                c.setCorreo(aux);
                            } else {
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
                        aux = read.next();
                        if (aux.length() < 6) {
                            System.err.println("La contraseña tiene que tener una longitud mínima de 6 carácteres.");
                        } else {
                            System.out.println("Usuario creado con éxito.");
                            c.setPassword(aux);
                            nuevoUsuario(con, c);
                            correcto = true;
                        }

                    }

                    break;
            }
        }

    }

    @Override
    public Cliente login(Connection con) {
        String user, passwd = "";
        boolean correcto = false;

        System.out.println("Introduce el correo:");
        while (!correcto) {
            user = read.next();
            if (!user.contains("@") || !user.contains(".")) {
                System.err.println("Introduce un formato de email correcto");
            } else {
                correcto = true;
                if (userExists(con, user)) {
                    //Login
                    System.out.println("Introduce la contraseña:");
                    passwd = read.next();
                    if (checkUserPassword(con, user, passwd) != null) {
                        Cliente c = checkUserPassword(con, user, passwd);
                        System.out.println("has entrao atontao");
                        return c;
                    } else {
                        System.err.println("Password incorrecta, volviendo al menu...");
                    }

                } else {
                    System.err.println("Este usuario no se encuentra registrado");
                }

            }
        }
        return null;
    }

    @Override
    public boolean userExists(Connection con, String username) {
        String mailQuery = "SELECT id FROM Cliente WHERE correo = ?";
        ResultSet rs = null;
        Cliente c = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(mailQuery);) {
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            try {
                if(rs!=null){
                rs.close();
                }                
            } catch (SQLException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public void nuevoUsuario(Connection con, Cliente c) {
        String insert = "INSERT INTO `cliente`(`id`, `nombre`, `apellido`, `direccion`, `telefono`, "
                + "`correo`, `password`,`saldo`) VALUES (null,?,?,?,?,?,?,0)";

        try (
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

    public void cambiarContrasenya(Connection con, String email) {

        boolean correcto = false;
        String pass = "";

        while (!correcto) {
            System.out.println("Introduce tu contraseña actual");
            pass = read.next();
            if (correctPasswd(con, email, pass)) {
                correcto = true;
            } else {
                System.err.println("Password incorrecto..");
            }
        }
        correcto = false;
        System.out.println("La contraseña tiene que tener una longitud mínima de 6 carácteres.");
        while (!correcto) {
            System.out.println(" ");

            System.out.println("Nueva Contraseña: ");
            pass = read.next();
            if (pass.length() < 6) {
                System.err.println("La contraseña tiene que tener una longitud mínima de 6 carácteres");
            } else {
                correcto = true;
            }

        }
        String update = "UPDATE cliente SET password = ? WHERE correo = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(update);) {

            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean correctPasswd(Connection con, String usuario, String passwd) {
        String mailQuery = "SELECT * FROM Cliente WHERE correo = ? AND password = ?";

        Cliente c = null;
        try (
                PreparedStatement preparedStatement = con.prepareStatement(mailQuery);) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, passwd);
            ResultSet rs;
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return false;

    }

    public void borrarCuenta(Connection con, Cliente c) {

        System.out.println("Introduce tu contraseña para poder eliminar la cuenta: ");

        String pass = read.next();

        if (correctPasswd(con, c.getCorreo(), pass)) {

            String delete = "DELETE FROM cliente WHERE correo = '" + c.getCorreo() + "'";

            try (Statement stm = con.createStatement()) {
                
                stm.execute(delete);

            } catch (SQLException e) {

                System.out.println(e);
            }

        }else{
        
            System.err.println("Contraseña incorecta, volviendo al menú...");
            
        }

    }

    

}
