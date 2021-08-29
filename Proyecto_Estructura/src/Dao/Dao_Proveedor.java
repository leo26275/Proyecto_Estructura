package Dao;

import Clases.Cargo;
import Clases.Proveedor;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Proveedor {

    Conexion conexion;

    public Dao_Proveedor() {
        this.conexion = new Conexion();
    }

    public boolean insetarProveedor(Proveedor p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO proveedor(nombre, apellido, empresa, nitempresa, direccion, telefono) VALUES ('" + p.getNombre() + "', '" + p.getApellido() + "', '" + p.getEmpresa() + "', '" + p.getNitempresa() + "', '" + p.getDireccion() + "', '" + p.getTelefono() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarProveedor(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM proveedor WHERE idproveedor = '" + id + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarProveedor(Proveedor p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE proveedor SET nombre='" + p.getNombre() + "', apellido='" + p.getApellido() + "', empresa='" + p.getEmpresa() + "', nitempresa='" + p.getNitempresa() + "', direccion='" + p.getDireccion() + "', telefono='" + p.getTelefono() + "' WHERE  idproveedor = " + p.getIdproveedor());
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Proveedor> listaProveedor() { //LIST0
        ArrayList<Proveedor> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT * FROM proveedor");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Proveedor e;
                e = new Proveedor(res.getString("idproveedor"), res.getString("nombre"), res.getString("apellido"), res.getString("empresa"), res.getString("nitempresa"), res.getString("direccion"), res.getString("telefono"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
    public boolean validarnit(String id) { //LIST0
        ArrayList<Proveedor> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT * FROM proveedor WHERE nitempresa='"+id+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Proveedor e;
                e = new Proveedor(res.getString("idproveedor"), res.getString("nombre"), res.getString("apellido"), res.getString("empresa"), res.getString("nitempresa"), res.getString("direccion"), res.getString("telefono"));
                temp.add(e);
                cc = true;
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return cc;
    }
}
