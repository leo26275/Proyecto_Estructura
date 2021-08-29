package Dao;

import Clases.Cliente;
import Clases.Proveedor;
import Clases.Referencia;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Referencia {

    Conexion conexion;

    public Dao_Referencia() {
        this.conexion = new Conexion();
    }

    public boolean insetarReferencia(Referencia p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO referencia(nombre, apellido, telefono, idcliente) VALUES ('" + p.getNombre() + "', '" + p.getApellido() + "', '" + p.getTelefono() + "', '" + p.getIdcliente().getNombre() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarReferencia(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM referencia WHERE idreferencia = '" + id + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarReferencia(Referencia p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE referencia SET nombre='" + p.getNombre() + "', apellido='" + p.getApellido() + "', telefono='" + p.getTelefono() + "', idcliente='" + p.getIdcliente().getNombre() + "' WHERE  idreferencia = '" + p.getIdreferencia()+"'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Referencia> listaReferencia() { //LIST0
        ArrayList<Referencia> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT referencia.idreferencia, referencia.nombre, referencia.apellido, referencia.telefono, cliente.nombre as nombrecliente FROM referencia INNER JOIN cliente ON referencia.idcliente = cliente.idcliente");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Referencia e;
                e = new Referencia(res.getInt("idreferencia"), res.getString("nombre"), res.getString("apellido"), res.getString("telefono"), new Cliente(res.getString("nombrecliente")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
}
