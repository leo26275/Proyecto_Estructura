
package Clases;


public class Usuario {
  private int id_usuario;
  private String nombre;
  private String contraseña;
  private String tipo;
  private Empleado id_empleado;

    public Usuario() {
    }

    public Usuario(String nombre, String contraseña, String tipo, Empleado id_empleado) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipo = tipo;
        this.id_empleado = id_empleado;
    }

    public Usuario(int id_usuario, String nombre, String contraseña, String tipo, Empleado id_empleado) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipo = tipo;
        this.id_empleado = id_empleado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empleado getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Empleado id_empleado) {
        this.id_empleado = id_empleado;
    }
  
  
}
