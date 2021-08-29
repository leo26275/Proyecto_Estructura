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
public class Proveedor {

    private String idproveedor;
    private String nombreEmpresa;
    private String apellido;
    private String empresa;
    private String nitempresa;
    private String direccion;
    private String telefono;

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombreEmpresa;
    }

    public void setNombre(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNitempresa() {
        return nitempresa;
    }

    public void setNitempresa(String nitempresa) {
        this.nitempresa = nitempresa;
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

    public Proveedor() {
    }

    public Proveedor(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Proveedor(String nombreEmpresa, String apellido, String empresa, String nitempresa, String direccion, String telefono) {
        this.nombreEmpresa = nombreEmpresa;
        this.apellido = apellido;
        this.empresa = empresa;
        this.nitempresa = nitempresa;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Proveedor(String idproveedor, String nombreEmpresa, String apellido, String empresa, String nitempresa, String direccion, String telefono) {
        this.idproveedor = idproveedor;
        this.nombreEmpresa = nombreEmpresa;
        this.apellido = apellido;
        this.empresa = empresa;
        this.nitempresa = nitempresa;
        this.direccion = direccion;
        this.telefono = telefono;
    }

}
