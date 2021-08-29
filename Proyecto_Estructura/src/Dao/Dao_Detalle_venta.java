package Dao;

import Clases.Cargo;
import Clases.Categoria;
import Clases.Cliente;
import Clases.CompraProducto;
import Clases.Detalle_venta;
import Clases.Producto;
import Clases.Venta;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Detalle_venta {

    Conexion conexion;

    public Dao_Detalle_venta() {
        this.conexion = new Conexion();
    }

    public boolean insetarDetalle_venta(Detalle_venta c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO detalle_venta(id_producto, cantida, id_datos_venta, tipo) VALUES ('" + c.getId_producto().getNombre() + "', '" + c.getCantidad() + "', '" + c.getId_datos_venta().getNumerofactura() + "', '" + c.getTipo() + "')");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarDetalle_venta(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM detalle_venta WHERE id_detalle = '" + id + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarDetalle_venta(Detalle_venta c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE detalle_venta SET id_producto='" + c.getId_producto().getNombre() + "', cantida='" + c.getCantidad() + "', id_datos_venta='" + c.getId_datos_venta().getNumerofactura() + "', tipo='" + c.getTipo() + "' WHERE id_detalle='" + c.getId_detalle() + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + ex);
            return false;
        }
    }

    public ArrayList<Detalle_venta> listaDetalle_venta() { //LIST0
        ArrayList<Detalle_venta> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT detalle_venta.id_detalle, producto.nombre, detalle_venta.cantida, venta.numerofactura, cliente.nombre as nomdd, detalle_venta.tipo FROM detalle_venta INNER JOIN venta ON detalle_venta.id_datos_venta = venta.numerofactura INNER JOIN producto ON detalle_venta.id_producto = producto.idproducto INNER JOIN cliente ON venta.idcliente = cliente.idcliente");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Detalle_venta e = new Detalle_venta(res.getString("id_detalle"), new Producto(res.getString("nombre")), res.getInt("cantida"), new Venta(res.getString("numerofactura")), new Cliente(res.getString("nomdd")), res.getString("tipo"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public ArrayList<Detalle_venta> listarFactura() { //LIST0
        ArrayList<Detalle_venta> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT\n"
                    + "producto.codigo,\n"
                    + "producto.nombre,\n"
                    + "producto.descripcion,\n"
                    + "categoria.nombre AS nom,\n"
                    + "detalle_venta.cantida,\n"
                    + "compra.precio,\n"
                    + "compra.porcentajeganancia,\n"
                    + "venta.numerofactura,\n"
                    + "detalle_venta.tipo,\n"
                    + "cliente.nombre as nom1\n"
                    + "FROM producto\n"
                    + "INNER JOIN compra ON compra.id_registro = producto.idproducto\n"
                    + "INNER JOIN detalle_venta ON detalle_venta.id_producto = producto.idproducto\n"
                    + "INNER JOIN categoria ON producto.id_categoria = categoria.idcategoria\n"
                    + "INNER JOIN venta ON detalle_venta.id_datos_venta = venta.numerofactura\n"
                    + "INNER JOIN cliente ON venta.idcliente = cliente.idcliente");
            // ORDER BY detalle_venta.id_detalle DESC
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Detalle_venta e = new Detalle_venta(new Producto(res.getString("codigo"), res.getString("nombre"), res.getString("descripcion")), new Categoria(res.getString("nom")), new CompraProducto(res.getInt("cantida"), res.getDouble("precio"), res.getDouble("porcentajeganancia")), new Venta(res.getString("numerofactura")), res.getString("tipo"), new Cliente(res.getString("nom1")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public ArrayList<Detalle_venta> listaID(String id) { //LIST0
        ArrayList<Detalle_venta> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT detalle_venta.id_producto FROM detalle_venta WHERE detalle_venta.id_datos_venta='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Detalle_venta e = new Detalle_venta(res.getString("id_producto"));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public String actenerIDClienteVenta(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT venta.idcliente FROM venta WHERE venta.numerofactura='" + num + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                hola = res.getString("idcliente");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
    public String actenerIDClienteFerencia(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT cliente.idcliente FROM cliente INNER JOIN referencia ON referencia.idcliente = cliente.idcliente WHERE referencia.idcliente='" + num + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                hola = res.getString("idcliente");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
}
