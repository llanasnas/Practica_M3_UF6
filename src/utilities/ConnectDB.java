/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Albert
 */
public class ConnectDB {
    
    private static Connection instance;
    
    private ConnectDB(){};
    
    public static Connection getInstance() throws SQLException {
    
        if (instance==null) {
            instance = DriverManager.getConnection(MYSQLConnection.URL,MYSQLConnection.USERNAME,MYSQLConnection.PASSWD);
        }
        return instance;
    }
    
    public static void closeConnection() throws SQLException{
        if (instance!=null) {
            instance.close();
            instance = null;
        }
    }
    
}
