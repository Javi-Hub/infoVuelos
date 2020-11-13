package com.sanvalero.infoVuelos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creado por @author: Javier
 * el 02/11/2020
 */
public class BaseDAO {

    protected Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "";


    public Connection conectar() throws ClassNotFoundException, SQLException{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/infovuelos?serverTimeZone=UTC",
                    USUARIO, PASSWORD);
        return conexion;
    }

    public void desconectar() throws SQLException{

        conexion.close();
        //conexion = null;
    }

}
