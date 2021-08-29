package Dao;

import Clases.Cargo;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Cargo {

    Conexion conexion;
    Cargo cargo;

    public Dao_Cargo() {
        this.conexion = new Conexion();
    }

    public boolean insetarCargo(Cargo c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO cargo(codigo,nombre) VALUES ('" + c.getCodigo() + "','" + c.getNombre() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe");
            return false;
        }
    }

    public boolean eliminarCargo(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM cargo WHERE codigo='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarCargo(Cargo c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE cargo SET nombre='" + c.getNombre() + "' WHERE codigo = '" + c.getCodigo()+"'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Cargo> listaCargo() { //LIST0
        ArrayList<Cargo> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT * FROM cargo");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Cargo e = new Cargo(res.getInt("idcargo"), res.getString("codigo"), res.getString("nombre"));
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
