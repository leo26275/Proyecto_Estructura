package Dao;

import Clases.Cargo;
import Clases.Empleado;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Empleados {

    Conexion conexion;

    public Dao_Empleados() {
        this.conexion = new Conexion();
    }

    public boolean insetarEmpleado(Empleado e) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO empleado(codigo, nombre, apellido, dui, nit, telefono, correo, direccion, salario, idcargo) VALUES ('" + e.getCodigo() + "', '" + e.getNombre() + "', '" + e.getApellido() + "', '" + e.getDui() + "', '" + e.getNit() + "', '" + e.getTelefono() + "', '" + e.getCorreo() + "', '" + e.getDireccion() + "', '" + e.getSalario() + "', '" + e.getId_cargo().getNombre() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarEmpleado(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM empleado WHERE codigo = '" + id + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarEmpleado(Empleado p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE empleado SET nombre='" + p.getNombre() + "', apellido='" + p.getApellido() + "', dui='" + p.getDui() + "', nit='" + p.getNit() + "', telefono='" + p.getTelefono() + "', correo='" + p.getCorreo() + "', direccion='" + p.getDireccion() + "', salario='" + p.getSalario() + "', idcargo='" + p.getId_cargo().getNombre() + "' WHERE  codigo = '" + p.getCodigo() + "'");
//            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Empleado> listaEmpleado() { //LIST0
        ArrayList<Empleado> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT empleado.idempleado, empleado.codigo, empleado.nombre, empleado.apellido, empleado.dui, empleado.nit, empleado.telefono, empleado.correo, empleado.direccion, empleado.salario, cargo.nombre as nombrecargo FROM empleado INNER JOIN cargo ON empleado.idcargo = cargo.idcargo");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Empleado e;
                e = new Empleado(res.getString("idempleado"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("dui"), res.getString("nit"), res.getString("telefono"), res.getString("correo"), res.getString("direccion"), res.getDouble("salario"), new Cargo(res.getString("nombrecargo")));
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
        ArrayList<Empleado> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT empleado.idempleado, empleado.codigo, empleado.nombre, empleado.apellido, empleado.dui, empleado.nit, empleado.telefono, empleado.correo, empleado.direccion, empleado.salario, cargo.nombre as nombrecargo FROM empleado INNER JOIN cargo ON empleado.idcargo = cargo.idcargo WHERE dui='"+id+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Empleado e;
                e = new Empleado(res.getString("idempleado"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("dui"), res.getString("nit"), res.getString("telefono"), res.getString("correo"), res.getString("direccion"), res.getDouble("salario"), new Cargo(res.getString("nombrecargo")));
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
      public boolean validarnit(String id) { //LIST0
        ArrayList<Empleado> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT empleado.idempleado, empleado.codigo, empleado.nombre, empleado.apellido, empleado.dui, empleado.nit, empleado.telefono, empleado.correo, empleado.direccion, empleado.salario, cargo.nombre as nombrecargo FROM empleado INNER JOIN cargo ON empleado.idcargo = cargo.idcargo WHERE nit='"+id+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Empleado e;
                e = new Empleado(res.getString("idempleado"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("dui"), res.getString("nit"), res.getString("telefono"), res.getString("correo"), res.getString("direccion"), res.getDouble("salario"), new Cargo(res.getString("nombrecargo")));
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
       public boolean validarcorreo(String id) { //LIST0
        ArrayList<Empleado> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT empleado.idempleado, empleado.codigo, empleado.nombre, empleado.apellido, empleado.dui, empleado.nit, empleado.telefono, empleado.correo, empleado.direccion, empleado.salario, cargo.nombre as nombrecargo FROM empleado INNER JOIN cargo ON empleado.idcargo = cargo.idcargo WHERE correo='"+id+"'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Empleado e;
                e = new Empleado(res.getString("idempleado"), res.getString("codigo"), res.getString("nombre"), res.getString("apellido"), res.getString("dui"), res.getString("nit"), res.getString("telefono"), res.getString("correo"), res.getString("direccion"), res.getDouble("salario"), new Cargo(res.getString("nombrecargo")));
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
