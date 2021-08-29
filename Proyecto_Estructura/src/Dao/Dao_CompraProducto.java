package Dao;

import Clases.CompraProducto;
import Clases.Producto;
import Clases.Proveedor;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_CompraProducto {

    Conexion conexion;

    public Dao_CompraProducto() {
        this.conexion = new Conexion();
    }

    public boolean insetarCompraProducto(CompraProducto c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO compra(cantidad, precio, fechaadquisicion, porcentajeganancia, idproveedor, id_registro) VALUES ('" + c.getCantidad() + "', '" + c.getPrecio() + "', '" + c.getFechaadquisiscion() + "', '" + c.getPorcentajeganancia() + "', '" + c.getProveedor().getNombre() + "', '" + c.getProducto().getNombre() + "');");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe" + ex);
            return false;
        }
    }

    public boolean eliminarCompraProducto(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM compra WHERE idproducto='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarCompraProducto(CompraProducto c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE compra SET cantidad='" + c.getCantidad() + "', precio='" + c.getPrecio() + "', fechaadquisicion='" + c.getFechaadquisiscion() + "', porcentajeganancia='" + c.getPorcentajeganancia() + "', idproveedor='" + c.getProveedor().getNombre() + "', id_registro='" + c.getProducto().getNombre() + "' WHERE idproducto = '" + c.getIdcompra() + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<CompraProducto> listaCompraProducto() { //LIST0
        ArrayList<CompraProducto> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.idproducto, compra.cantidad, compra.precio, compra.fechaadquisicion, compra.porcentajeganancia, proveedor.nombre, producto.nombre as nom FROM compra INNER JOIN proveedor ON compra.idproveedor = proveedor.idproveedor INNER JOIN producto ON compra.id_registro = producto.idproducto");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                CompraProducto e = new CompraProducto(res.getString("idproducto"), res.getInt("cantidad"), res.getDouble("precio"), res.getString("fechaadquisicion"), res.getDouble("porcentajeganancia"), new Proveedor(res.getString("nombre")), new Producto(res.getString("nom")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public ArrayList<CompraProducto> listarID() { //LIST0
        ArrayList<CompraProducto> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.id_registro FROM compra");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                CompraProducto e = new CompraProducto(res.getString("id_registro"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public ArrayList<CompraProducto> listarprecio(String id) { //LIST0
        ArrayList<CompraProducto> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.precio, compra.porcentajeganancia FROM compra INNER JOIN producto ON compra.id_registro = producto.idproducto WHERE producto.nombre='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                CompraProducto e = new CompraProducto(res.getDouble("precio"), res.getDouble("porcentajeganancia"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
 public double listarPrecio(String id) { //LIST0
//        ArrayList<CompraProducto> temp = new ArrayList<>();
        double temp = 0;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.precio FROM compra INNER JOIN producto ON compra.id_registro = producto.idproducto WHERE producto.idproducto='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
               temp = res.getDouble("precio");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }
     public String  actenerFisico(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.cantidad FROM compra WHERE compra.idproducto='"+num+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
               hola = res.getString("cantidad");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
}
