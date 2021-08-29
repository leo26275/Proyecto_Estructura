package Dao;

import Clases.Datos_credito;
import Clases.Pago;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Pago {

    Conexion conexion;

    public Dao_Pago() {
        this.conexion = new Conexion();
    }

    public boolean insetarPago(Pago c) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO recibo(fecha, datos_credito) VALUES ('" + c.getFechapago() + "', '" + c.getDatoscredito().getId_datos() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "No sirbe");
            return false;
        }
    }

    public boolean eliminarPago(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM recibo WHERE id_recibo='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarPago(Pago c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE recibo SET fecha='" + c.getFechapago() + "' WHERE id_recibo = '" + c.getIdpago() + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Pago> listaPago() { //LIST0
        ArrayList<Pago> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT recibo.id_recibo, recibo.fecha, datos_credito.id_datos, datos_credito.cuotas_totales, datos_credito.cuotas_pagadas FROM recibo INNER JOIN datos_credito ON recibo.datos_credito = datos_credito.id_datos");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Pago e = new Pago(res.getString("id_recibo"), res.getString("fecha"), new Datos_credito(res.getString("id_datos"), res.getInt("cuotas_totales"), res.getInt("cuotas_pagadas")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public boolean modificarDatos_credito_Pago(String can, String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE datos_credito SET cuotas_pagadas='" + can + "' WHERE id_datos = '" + id + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public String  actenerpago(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT datos_credito.cuotas_pagadas FROM datos_credito WHERE datos_credito.id_datos='"+num+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
               hola = res.getString("cuotas_pagadas");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
     public String  actenerTiempo(String num) { //LIST0
        String hola = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT datos_credito.tiempo FROM datos_credito WHERE datos_credito.id_datos='"+num+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
               hola = res.getString("tiempo");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return hola;
    }
  
}
