package com.sanvalero.infoVuelos.DAO;

import com.sanvalero.infoVuelos.domain.Vuelo;

import java.security.cert.TrustAnchor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void guardarVuelo(Vuelo vuelo){

        try {
            sentencia = conexion.prepareStatement(GUARDAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.setString(2, vuelo.getOrigen());
            sentencia.setString(3, vuelo.getDestino());
            sentencia.setString(4, vuelo.getOperadora());
            sentencia.setString(5, vuelo.getFecha());
            sentencia.setString(6, vuelo.getClase());
            sentencia.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle){
                    sqle.printStackTrace();
                }
            }
        }
    }

    public void modificarVuelo(Vuelo vuelo){
        try {
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, vuelo.getOrigen());
            sentencia.setString(2, vuelo.getDestino());
            sentencia.setString(3, vuelo.getOperadora());
            sentencia.setString(4, vuelo.getFecha());
            sentencia.setString(5, vuelo.getClase());
            sentencia.setString(6, vuelo.getCodigo());
            sentencia.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                }catch (SQLException sqle){
                    sqle.printStackTrace();
                }
            }
        }

    }

    public void eliminarVuelo(Vuelo vuelo) {
        try {
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.executeUpdate();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle){
                    sqle.printStackTrace();
                }
            }
        }


    }

    public List<Vuelo> listarVuelos(){
        String sql = "SELECT * FROM vuelos";

        try {
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

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

}
