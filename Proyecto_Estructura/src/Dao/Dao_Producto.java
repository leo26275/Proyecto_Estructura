package Dao;

import Clases.Categoria;
import Clases.CompraProducto;
import Clases.Producto;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Producto {

    Conexion conexion;

    public Dao_Producto() {
        this.conexion = new Conexion();
    }

    public boolean insetarProducto(Producto c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO producto(codigo, nombre, id_categoria, descripcion) VALUES ('" + c.getCodigo() + "', '" + c.getNombre() + "', '" + c.getIdcategoria().getNombre() + "', '" + c.getDescriccion() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe");
            return false;
        }
    }

    public boolean eliminarProducto(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM producto WHERE codigo='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarProducto(Producto c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE producto SET nombre='" + c.getNombre() + "', id_categoria='" + c.getIdcategoria().getNombre() + "', descripcion='" + c.getDescriccion() + "' WHERE codigo = '" + c.getCodigo() + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Producto> listaProducto() { //LIST0
        ArrayList<Producto> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT producto.idproducto, producto.codigo, producto.nombre, categoria.nombre as nom, producto.descripcion FROM producto INNER JOIN categoria ON producto.id_categoria = categoria.idcategoria");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Producto e = new Producto(res.getString("idproducto"), res.getString("codigo"), res.getString("nombre"), new Categoria(res.getString("nom")), res.getString("descripcion"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public boolean ActualizarInventario(String c, String i) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE compra SET cantidad='" + c + "' WHERE idproducto = '" + i + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Producto> listaInventario() { //LIST0
        ArrayList<Producto> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT\n"
                    + "producto.codigo,\n"
                    + "producto.nombre,\n"
                    + "categoria.nombre as nom,\n"
                    + "producto.descripcion,\n"
                    + "compra.idproducto,\n"
                    + "compra.cantidad,\n"
                    + "compra.fechaadquisicion,\n"
                    + "compra.precio,\n"
                    + "compra.porcentajeganancia\n"
                    + "FROM producto\n"
                    + "INNER JOIN compra ON compra.id_registro = producto.idproducto\n"
                    + "INNER JOIN categoria ON producto.id_categoria = categoria.idcategoria");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Producto e = new Producto(res.getString("codigo"), res.getString("nombre"), new Categoria(res.getString("nom")), res.getString("descripcion"), new CompraProducto(res.getString("idproducto"), res.getInt("cantidad"), res.getString("fechaadquisicion"), res.getDouble("precio"), res.getDouble("porcentajeganancia")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
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
     
      public String  actenerIDcompra(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT compra.idproducto FROM producto INNER JOIN compra ON compra.id_registro = producto.idproducto WHERE producto.idproducto ='"+num+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
               hola = res.getString("idproducto");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
}
