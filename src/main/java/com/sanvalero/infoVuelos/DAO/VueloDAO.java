package com.sanvalero.infoVuelos.DAO;

import com.sanvalero.infoVuelos.domain.Vuelo;
import com.sun.prism.ResourceFactoryListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: Javier
 * el 02/11/2020
 */
public class VueloDAO extends BaseDAO{

    PreparedStatement sentencia = null;
    ResultSet resultado;
    final String GUARDAR = "INSERT INTO vuelos (codigo, origen, destino, operadora, fecha, clase) VALUES (?, ?, ?, ?, ?, ?)";
    final String MODIFICAR = "UPDATE vuelos SET origen = ?, destino = ?, operadora = ?, fecha = ?, clase = ? WHERE codigo = ?";
    final String ELIMINAR = "DELETE FROM vuelos WHERE codigo = ?";
    final String EXISTE = "SELECT * FROM vuelos WHERE codigo = ?";

    public void guardarVuelo(Vuelo vuelo)throws SQLException{

            sentencia = conexion.prepareStatement(GUARDAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.setString(2, vuelo.getOrigen());
            sentencia.setString(3, vuelo.getDestino());
            sentencia.setString(4, vuelo.getOperadora());
            sentencia.setString(5, vuelo.getFecha());
            sentencia.setString(6, vuelo.getClase());
            sentencia.executeUpdate();
    }

    public void modificarVuelo(Vuelo vuelo) throws SQLException{

            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, vuelo.getOrigen());
            sentencia.setString(2, vuelo.getDestino());
            sentencia.setString(3, vuelo.getOperadora());
            sentencia.setString(4, vuelo.getFecha());
            sentencia.setString(5, vuelo.getClase());
            sentencia.setString(6, vuelo.getCodigo());
            sentencia.executeUpdate();

    }

    public void eliminarVuelo(Vuelo vuelo) throws SQLException{

            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.executeUpdate();

    }

    public List<Vuelo> listarVuelos() throws SQLException{
        String sql = "SELECT * FROM vuelos";

            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            List<Vuelo> lista = new ArrayList<Vuelo>();
            while (resultado.next()){
                Vuelo vuelo = new Vuelo(
                        resultado.getString(1), resultado.getString(2),
                        resultado.getString(3), resultado.getString(4),
                        resultado.getString(5), resultado.getString(6)
                );
                lista.add(vuelo);
            }
            return lista;

    }

    public boolean existeVuelo(String codigo) throws SQLException{
        sentencia = conexion.prepareStatement(EXISTE);
        sentencia.setString(1, codigo);
        resultado = sentencia.executeQuery();
        return resultado.next();

    }

}
