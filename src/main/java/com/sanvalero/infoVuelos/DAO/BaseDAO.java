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
    private final String USUARIO = "javier";
    private final String PASSWORD = "javier2";


    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/infovuelos?serverTimeZone=UTC",
                    USUARIO, PASSWORD);
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return conexion;
    }

    public void desconectar(){
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
