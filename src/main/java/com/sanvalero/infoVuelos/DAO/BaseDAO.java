package com.sanvalero.infoVuelos.DAO;

import com.sanvalero.infoVuelos.util.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creado por @author: Javier
 * el 02/11/2020
 */
public class BaseDAO {

    protected Connection conexion;

    public Connection conectar() throws ClassNotFoundException, SQLException, IOException {
            Properties configuration = new Properties();
            configuration.load(R.getProperties("database.properties"));
            String host = configuration.getProperty("host");
            String port = configuration.getProperty("port");
            String name = configuration.getProperty("name");
            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");
            String driver = configuration.getProperty("driverMySql");
            Class.forName(driver);
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimeZone=UTC",
                    username, password);
        return conexion;
    }

    public void desconectar() throws SQLException{

        conexion.close();
        //conexion = null;
    }

}
