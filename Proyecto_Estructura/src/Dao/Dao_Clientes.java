
package Dao;

import Clases.Cliente;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Clientes {
      Conexion conexion;

    public Dao_Clientes() {
         this.conexion = new Conexion();
    }
         public boolean insetarCliente(Cliente c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO cliente(codigo, nombre, apellido, direccion, telefono, dui) VALUES ('"+c.getCodigo()+"', '"+c.getNombre()+"', '"+c.getApellido()+"', '"+c.getDireccion()+"', '"+c.getTelefono()+"', '"+c.getDui()+"');");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe");
            return false;
        }
    }

    public boolean eliminarCliente(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM cliente WHERE codigo='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarCliente(Cliente c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE cliente SET nombre='" + c.getNombre() + "', apellido='"+c.getApellido()+"', direccion='"+c.getDireccion()+"', telefono='"+c.getTelefono()+"', dui='"+c.getDui()+"' WHERE codigo = '" + c.getCodigo()+"'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Cliente> listaCliente() { //LIST0
        ArrayList<Cliente> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT idcliente, codigo, nombre, apellido, direccion, telefono, dui FROM cliente;");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Cliente e = new Cliente(res.getInt("idcliente"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("direccion"), res.getString("telefono"), res.getString("dui"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
      public boolean validardui(String id) { //LIST0
        ArrayList<Cliente> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT idcliente, codigo, nombre, apellido, direccion, telefono, dui FROM cliente WHERE dui='"+id+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Cliente e = new Cliente(res.getInt("idcliente"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("direccion"), res.getString("telefono"), res.getString("dui"));
                temp.add(e);
                cc= true;
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return cc;
    }
}
