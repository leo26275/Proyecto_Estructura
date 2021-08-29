/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author leo_g
 */
public class Referencia {
    private int idreferencia;
    private String nombre;
    private String apellido;
    private String telefono;
    private Cliente idcliente;

    public Referencia() {
    }

    public Referencia(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Referencia(String nombre, String apellido, String telefono, Cliente idcliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.idcliente = idcliente;
    }

    public Referencia(int idreferencia, String nombre, String apellido, String telefono, Cliente idcliente) {
        this.idreferencia = idreferencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.idcliente = idcliente;
    }

    public int getIdreferencia() {
        return idreferencia;
    }

    public void setIdreferencia(int idreferencia) {
        this.idreferencia = idreferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }
    
}
