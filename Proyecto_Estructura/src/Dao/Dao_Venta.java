package Dao;

import Clases.Cliente;
import Clases.Empleado;
import Clases.Venta;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Venta {

    Conexion conexion;

    public Dao_Venta() {
        this.conexion = new Conexion();
    }

    public boolean insetarVenta(Venta c) { //LIST0
        try {
            int num;
            if (listaVenta().size() >= 1) {
                num = Integer.parseInt(listaVenta().get(new Dao_Venta().listaVenta().size() - 1).getNumerofactura());
            } else {
                num = 0;
            }
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO venta(numerofactura, idempleado, idcliente, fecha_compra, tipo_pago) VALUES ('" + (num + 1) + "', '" + c.getIdempleado().getNombre() + "', '" + c.getIdcliente().getNombre() + "', '" + c.getFecha() + "', '" + c.getTipo() + "');");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No sirbe" + ex);
            return false;
        }
    }

    public boolean eliminarVenta(String cd) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM venta WHERE numerofactura='" + cd + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarVenta(Venta c) { //LIST0
        try {
            this.conexion.getConexion();

            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE venta SET idempleado='" + c.getIdempleado().getNombre() + "', idcliente='" + c.getIdcliente().getNombre() + "', fecha_compra='" + c.getFecha() + "', tipo_pago='" + c.getTipo() + "' WHERE numerofactura = '" + c.getNumerofactura() + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Venta> listaVenta() { //LIST0
        ArrayList<Venta> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();

            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT venta.numerofactura, empleado.nombre, cliente.nombre as nom, venta.fecha_compra, venta.tipo_pago FROM venta INNER JOIN empleado ON venta.idempleado = empleado.idempleado INNER JOIN cliente ON venta.idcliente = cliente.idcliente ORDER BY venta.numerofactura");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Venta e = new Venta(res.getString("numerofactura"), new Empleado(res.getString("nombre")), new Cliente(res.getString("nom")), res.getString("fecha_compra"), res.getString("tipo_pago"));
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
