/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author leo_g
 */
public class Cargo implements Serializable {

    private int idcargo;
    private String Codigo;
    private String nombre;

    public Cargo() {
    }

    public Cargo(String nombre) {
        this.nombre = nombre;
    }

    public Cargo(int idcargo, String nombre) {
        this.idcargo = idcargo;
        this.nombre = nombre;
    }

    public Cargo(String Codigo, String nombre) {
        this.Codigo = Codigo;
        this.nombre = nombre;
    }

    public Cargo(int idcargo, String Codigo, String nombre) {
        this.idcargo = idcargo;
        this.Codigo = Codigo;
        this.nombre = nombre;
    }

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
