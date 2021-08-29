package Dao;

import Clases.Empleado;
import Clases.Usuario;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dao_Usuario {

    Conexion conexion;

    public Dao_Usuario() {
        this.conexion = new Conexion();
    }

    public boolean insetarUsuario(Usuario e) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("INSERT INTO usuario(nombre, contrasena, id_empleado, tipo) VALUES ('" + e.getNombre() + "', '" + e.getContraseña() + "', '" + e.getId_empleado().getNombre() + "', '" + e.getTipo() + "')");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarUsuario(String id) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("DELETE FROM usuario WHERE  nombre='" + id + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarUsuario(Usuario p) { //LIST0
        try {
            this.conexion.getConexion();
            Statement estatuto = conexion.getConexion().createStatement();
            estatuto.executeUpdate("UPDATE usuario SET nombre='" + p.getNombre() + "', contrasena='" + p.getContraseña() + "', id_empleado='" + p.getId_empleado().getNombre() + "', tipo='" + p.getTipo() + "' WHERE  id_usuario = '" + p.getId_usuario() + "'");
            estatuto.close();
            conexion.cerrarConexiones();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Usuario> listaUsuario() { //LIST0
        ArrayList<Usuario> temp = new ArrayList<>();
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT usuario.id_usuario, usuario.nombre, usuario.contrasena, usuario.tipo, empleado.nombre as nom FROM usuario INNER JOIN empleado ON usuario.id_empleado = empleado.idempleado");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Usuario e;
                e = new Usuario(res.getInt("id_usuario"), res.getString("nombre"), res.getString("contrasena"), res.getString("tipo"), new Empleado(res.getString("nom")));
                temp.add(e);
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
        }
        return temp;
    }

    public boolean validarcorreo(String id) { //LIST0
        ArrayList<Usuario> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT usuario.id_usuario, usuario.nombre, usuario.contrasena, usuario.tipo, empleado.nombre as nom FROM usuario INNER JOIN empleado ON usuario.id_empleado = empleado.idempleado WHERE usuario.nombre='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Usuario e;
                e = new Usuario(res.getInt("id_usuario"), res.getString("nombre"), res.getString("contrasena"), res.getString("tipo"), new Empleado(res.getString("nom")));
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

    public boolean validarcontrasena(String id) { //LIST0
        ArrayList<Usuario> temp = new ArrayList<>();
        boolean cc = false;
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT usuario.id_usuario, usuario.nombre, usuario.contrasena, usuario.tipo, empleado.nombre as nom FROM usuario INNER JOIN empleado ON usuario.id_empleado = empleado.idempleado WHERE usuario.contrasena='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                Usuario e;
                e = new Usuario(res.getInt("id_usuario"), res.getString("nombre"), res.getString("contrasena"), res.getString("tipo"), new Empleado(res.getString("nom")));
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

    public String Recuparar(String nom, String ape, String dui, String nit, String correo, String telefono) { //LIST0
        String con = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT usuario.contrasena FROM usuario INNER JOIN empleado ON usuario.id_empleado = empleado.idempleado WHERE empleado.nombre='" + nom + "' AND empleado.apellido='" + ape + "' AND empleado.dui='" + dui + "' AND empleado.nit='" + nit + "' AND empleado.correo='" + correo + "' AND  empleado.telefono='" + telefono + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                con = res.getString("contrasena");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No hay Datos" + e);
            con = "";
        }
        return con;
    }
    public String tipocuenta(String id) { //LIST0
        String cc = "";
        try {
            this.conexion.getConexion();
            PreparedStatement consulta = conexion.getConexion().prepareStatement("SELECT usuario.tipo FROM usuario WHERE usuario.nombre='" + id + "'");
            ResultSet res = consulta.executeQuery();
            while (res.next()) {
                cc = res.getString("tipo");
            }
            res.close();
            consulta.close();
        } catch (SQLException e) {
           cc = "";
        }
        return cc;
    }
}
