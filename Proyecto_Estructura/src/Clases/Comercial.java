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
public class Comercial {
    private int idcomercial;
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;

    public Comercial() {
    }

    public Comercial(int idcomercial, String nombre, String direccion, String telefono, String nit) {
        this.idcomercial = idcomercial;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
    }

    public int getIdcomercial() {
        return idcomercial;
    }

    public void setIdcomercial(int idcomercial) {
        this.idcomercial = idcomercial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
}
