package Dao;

import Clases.Cargo;
import Clases.Cliente;
import Clases.Datos_credito;
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

public class Dao_Datos_credito {

    Conexion conexion;

    public Dao_Datos_credito() {
        this.conexion = new Conexion();
    }

    public boolean insetarDatos_credito(Datos_credito c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO datos_credito(fecha_cance, tiempo, cuotas_totales, cuotas_pagadas, id_datelleventa) VALUES ('" + c.getFecha_cance() + "', '" + c.getTiempo() + "', '" + c.getCuotas_totales() + "', '" + c.getCuotas_pagadas() + "', '" + c.getId_venta().getNumerofactura() + "');");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarDatos_credito(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM datos_credito WHERE id_datos='" + id + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarDatos_credito(Datos_credito c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE datos_credito SET fecha_cance='" + c.getFecha_cance() + "', tiempo='" + c.getTiempo() + "', cuotas_totales='" + c.getCuotas_totales() + "' WHERE id_datos = '" + c.getId_datos() + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Datos_credito> listaDatos_credito() { //LIST0
        ArrayList<Datos_credito> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT\n"
                    + "datos_credito.id_datos,\n"
                    + "datos_credito.fecha_cance,\n"
                    + "datos_credito.tiempo,\n"
                    + "datos_credito.cuotas_totales,\n"
                    + "datos_credito.cuotas_pagadas,\n"
                    + "producto.codigo,\n"
                    + "producto.nombre,\n"
                    + "detalle_venta.cantida,\n"
                    + "detalle_venta.tipo,\n"
                    + "detalle_venta.id_detalle\n"
                    + "FROM detalle_venta\n"
                    + "INNER JOIN datos_credito ON datos_credito.id_datelleventa = detalle_venta.id_detalle\n"
                    + "INNER JOIN producto ON detalle_venta.id_producto = producto.idproducto");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Datos_credito e = new Datos_credito(res.getString("id_datos"), res.getString("Fecha_cance"), res.getInt("tiempo"), res.getInt("cuotas_totales"), res.getInt("cuotas_pagadas"), new Producto(res.getString("codigo"), res.getString("nombre")), new Detalle_venta(res.getInt("cantida"), res.getString("tipo"), res.getString("id_detalle")));
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
