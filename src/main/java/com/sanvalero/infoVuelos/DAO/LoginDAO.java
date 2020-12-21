package com.sanvalero.infoVuelos.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creado por @author: Javier
 * el 03/12/2020
 */
public class LoginDAO extends BaseDAO{

    public PreparedStatement sentencia = null;
    public ResultSet resultado;

    //Sentencia SQL para validar el usuario
    public final String EXISTE_USUARIO = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";

    public boolean existeUsuario(String nombre, String password) throws SQLException {
        sentencia = conexion.prepareStatement(EXISTE_USUARIO);
        sentencia.setString(1, nombre);
        sentencia.setString(2, password);
        resultado = sentencia.executeQuery();
        return resultado.next();

    }

}
