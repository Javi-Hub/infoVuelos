package com.sanvalero.infoVuelos.DAO;

import com.sanvalero.infoVuelos.domain.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creado por @author: Javier
 * el 02/12/2020
 */
public class UsuarioDAO extends BaseDAO{

    public PreparedStatement sentencia = null;
    public ResultSet resultado;

    //Sentencias SQL para realizar insertar y validar usuarios
    public final String REGISTRAR_USUARIO = "INSERT INTO usuarios (nombre, apellidos, nombre_usuario, email, password) VALUES (?, ?, ?, ?, ?)";
    public final String EXISTE_USUARIO = "SELECT * FROM usuarios WHERE nombre_usuario = ?";

    public void registrarUsuario(Usuario usuario) throws SQLException {
        sentencia = conexion.prepareStatement(REGISTRAR_USUARIO);
        sentencia.setString(1, usuario.getNombre());
        sentencia.setString(2, usuario.getApellidos());
        sentencia.setString(3, usuario.getNombreUsuario());
        sentencia.setString(4, usuario.getEmail());
        sentencia.setString(5, usuario.getPassword());
        sentencia.executeUpdate();
    }

    public boolean existeUsuario(String nombre) throws SQLException {
        sentencia = conexion.prepareStatement(EXISTE_USUARIO);
        sentencia.setString(1, nombre);
        resultado = sentencia.executeQuery();
        return resultado.next();
    }

}
