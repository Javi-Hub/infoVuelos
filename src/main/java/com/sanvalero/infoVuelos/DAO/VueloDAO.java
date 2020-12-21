package com.sanvalero.infoVuelos.DAO;

import com.sanvalero.infoVuelos.domain.Vuelo;

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

    public PreparedStatement sentencia = null;
    public ResultSet resultado;

    //Sentencias SQL para realizar las diferentes consultas a la BBDD
    public final String GUARDAR = "INSERT INTO vuelos (codigo, origen, destino, operadora, fecha, clase) VALUES (?, ?, ?, ?, ?, ?)";
    public final String MODIFICAR = "UPDATE vuelos SET origen = ?, destino = ?, operadora = ?, fecha = ?, clase = ? WHERE codigo = ?";
    public final String ELIMINAR = "DELETE FROM vuelos WHERE codigo = ?";
    public final String EXISTE = "SELECT * FROM vuelos WHERE codigo = ?";
    public final String LISTAR_VUELOS = "SELECT * FROM vuelos ORDER BY fecha";
    public final String FILTRO_ORIGEN = "SELECT * FROM vuelos WHERE UPPER(origen) = ?";
    public final String FILTRO_DESTINO = "SELECT * FROM vuelos WHERE UPPER(destino) = ?";
    public final String FILTRO_ORIGEN_DESTINO = "SELECT * FROM vuelos WHERE UPPER(origen) = ? AND UPPER(destino) = ?";
    public final String FILTRO_CODIGO = "SELECT * FROM vuelos WHERE UPPER(codigo) = ?";
    public final String BORRAR_DATOS = "TRUNCATE TABLE vuelos";

    public void guardarVuelo(Vuelo vuelo)throws SQLException{
            sentencia = conexion.prepareStatement(GUARDAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.setString(2, vuelo.getOrigen());
            sentencia.setString(3, vuelo.getDestino());
            sentencia.setString(4, vuelo.getOperadora());
            sentencia.setDate(5, vuelo.getFecha());
            sentencia.setString(6, vuelo.getClase());
            sentencia.executeUpdate();
    }

    public void modificarVuelo(Vuelo vuelo) throws SQLException{
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, vuelo.getOrigen());
            sentencia.setString(2, vuelo.getDestino());
            sentencia.setString(3, vuelo.getOperadora());
            sentencia.setDate(4, vuelo.getFecha());
            sentencia.setString(5, vuelo.getClase());
            sentencia.setString(6, vuelo.getCodigo());
            sentencia.executeUpdate();
    }

    public void eliminarVuelo(Vuelo vuelo) throws SQLException{
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setString(1, vuelo.getCodigo());
            sentencia.executeUpdate();
    }

    public void borrarDatos () throws SQLException {
        sentencia = conexion.prepareStatement(BORRAR_DATOS);
        sentencia.executeUpdate();
    }

    public boolean existeVuelo(String codigo) throws SQLException{
        sentencia = conexion.prepareStatement(EXISTE);
        sentencia.setString(1, codigo);
        resultado = sentencia.executeQuery();
        return resultado.next();
    }

    public List<Vuelo> filtrarOrigen (String origen) throws SQLException{
            sentencia = conexion.prepareStatement(FILTRO_ORIGEN);
            sentencia.setString(1, origen);
            resultado = sentencia.executeQuery();
            List<Vuelo> lista = new ArrayList<>();
            while (resultado.next()){
                Vuelo vuelo = new Vuelo(
                        resultado.getString(1),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getDate(5),
                        resultado.getString(6)
                );
                lista.add(vuelo);
            }
            return  lista;
    }

    public List<Vuelo> filtrarDestino (String destino) throws SQLException{
        sentencia = conexion.prepareStatement(FILTRO_DESTINO);
        sentencia.setString(1, destino);
        resultado = sentencia.executeQuery();
        List<Vuelo> lista = new ArrayList<>();
        while (resultado.next()){
            Vuelo vuelo = new Vuelo(
                    resultado.getString(1),
                    resultado.getString(2),
                    resultado.getString(3),
                    resultado.getString(4),
                    resultado.getDate(5),
                    resultado.getString(6)
            );
            lista.add(vuelo);
        }
        return  lista;
    }

    public List<Vuelo> filtrarOrigenDestino (String origen, String destino) throws SQLException{
        sentencia = conexion.prepareStatement(FILTRO_ORIGEN_DESTINO);
        sentencia.setString(1, origen);
        sentencia.setString(2, destino);
        resultado = sentencia.executeQuery();
        List<Vuelo> lista = new ArrayList<>();
        while (resultado.next()){
            Vuelo vuelo = new Vuelo(
                    resultado.getString(1),
                    resultado.getString(2),
                    resultado.getString(3),
                    resultado.getString(4),
                    resultado.getDate(5),
                    resultado.getString(6)
            );
            lista.add(vuelo);
        }
        return  lista;
    }

    public List<Vuelo> filtrarCodigo(String codigo) throws SQLException {
        sentencia = conexion.prepareStatement(FILTRO_CODIGO);
        sentencia.setString(1, codigo);
        resultado = sentencia.executeQuery();
        List<Vuelo> lista = new ArrayList<>();
        while (resultado.next()){
            Vuelo vuelo = new Vuelo(
                    resultado.getString(1),
                    resultado.getString(2),
                    resultado.getString(3),
                    resultado.getString(4),
                    resultado.getDate(5),
                    resultado.getString(6)
            );
            lista.add(vuelo);
        }

        return lista;
    }

    public List<Vuelo> listarVuelos() throws SQLException{
            sentencia = conexion.prepareStatement(LISTAR_VUELOS);
            resultado = sentencia.executeQuery();
            List<Vuelo> lista = new ArrayList<Vuelo>();
            while (resultado.next()){
                Vuelo vuelo = new Vuelo(
                        resultado.getString(1), resultado.getString(2),
                        resultado.getString(3), resultado.getString(4),
                        resultado.getDate(5), resultado.getString(6)
                );
                lista.add(vuelo);
            }
            return lista;

    }
}
