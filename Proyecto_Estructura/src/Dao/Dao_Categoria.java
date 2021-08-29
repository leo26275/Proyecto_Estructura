package Dao;

import Clases.Categoria;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Categoria {

    Conexion conexion;
    Categoria categoria;

    public Dao_Categoria() {
        this.conexion = new Conexion();
    }
  public ArrayList<Categoria> listaCargo() { //LIST0
        ArrayList<Categoria> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT * FROM categoria");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Categoria e = new Categoria(res.getInt("idcategoria"), res.getString("codigo"), res.getString("nombre"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
   public boolean insetarCategoria(Categoria c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO categoria(codigo, nombre) VALUES ('" + c.getCodigo() + "', '" + c.getNombre() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe");
            return false;
        }
    }
    public boolean eliminarCategoria(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM categoria WHERE codigo='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarCategoria(Categoria c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE categoria SET nombre='" + c.getNombre() + "' WHERE codigo = '" + c.getCodigo()+"'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
