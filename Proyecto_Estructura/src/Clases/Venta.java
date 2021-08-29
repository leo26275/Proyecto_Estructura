/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author leo_g
 */
public class Venta {

    private String numerofactura;
    private Empleado idempleado;
    private Cliente idcliente;
    private String fecha;
    private String tipo;

    public Venta() {
    }

    public Venta(String numerofactura, Empleado idempleado, Cliente idcliente, String fecha, String tipo) {
        this.numerofactura = numerofactura;
        this.idempleado = idempleado;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Venta(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

}
