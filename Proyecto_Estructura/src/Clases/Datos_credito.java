
package Clases;

import java.util.Date;


public class Datos_credito {
    private String id_datos;
    private String fecha_cance;
    private int tiempo;
    private Venta id_venta;
    private int cuotas_totales;
    private int cuotas_pagadas;
    private Cliente cliente;
    private Producto producto;
    private Detalle_venta detalleventa;

    public Datos_credito() {
    }

    public Datos_credito(String id_datos, String fecha_cance, int tiempo, Venta id_venta, int cuotas_totales, int cuotas_pagadas, Cliente cliente, Producto producto) {
        this.id_datos = id_datos;
        this.fecha_cance = fecha_cance;
        this.tiempo = tiempo;
        this.id_venta = id_venta;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
        this.cliente = cliente;
        this.producto = producto;
    }

    public Datos_credito(String fecha_cance, int tiempo, Venta id_venta, int cuotas_totales, int cuotas_pagadas) {
        this.fecha_cance = fecha_cance;
        this.tiempo = tiempo;
        this.id_venta = id_venta;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
    }

    
    public Datos_credito(String id_datos, String fecha_cance, int tiempo, Venta id_venta, int cuotas_totales, int cuotas_pagadas) {
        this.id_datos = id_datos;
        this.fecha_cance = fecha_cance;
        this.tiempo = tiempo;
        this.id_venta = id_venta;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
    }

    public Datos_credito(String id_datos, String fecha_cance, int tiempo, Venta id_venta, int cuotas_totales, int cuotas_pagadas, Cliente cliente) {
        this.id_datos = id_datos;
        this.fecha_cance = fecha_cance;
        this.tiempo = tiempo;
        this.id_venta = id_venta;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
        this.cliente = cliente;
    }

    public Datos_credito(String id_datos, int cuotas_totales, int cuotas_pagadas) {
        this.id_datos = id_datos;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
    }

    public Datos_credito(String id_datos) {
        this.id_datos = id_datos;
    }

    public Datos_credito(String id_datos, String fecha_cance, int tiempo, int cuotas_totales, int cuotas_pagadas, Producto producto, Detalle_venta detalleventa) {
        this.id_datos = id_datos;
        this.fecha_cance = fecha_cance;
        this.tiempo = tiempo;
        this.id_venta = id_venta;
        this.cuotas_totales = cuotas_totales;
        this.cuotas_pagadas = cuotas_pagadas;
        this.cliente = cliente;
        this.producto = producto;
        this.detalleventa = detalleventa;
    }

    public Detalle_venta getDetalleventa() {
        return detalleventa;
    }

    public void setDetalleventa(Detalle_venta detalleventa) {
        this.detalleventa = detalleventa;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getId_datos() {
        return id_datos;
    }

    public void setId_datos(String id_datos) {
        this.id_datos = id_datos;
    }

    public String getFecha_cance() {
        return fecha_cance;
    }

    public void setFecha_cance(String fecha_cance) {
        this.fecha_cance = fecha_cance;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }

    public int getCuotas_totales() {
        return cuotas_totales;
    }

    public void setCuotas_totales(int cuotas_totales) {
        this.cuotas_totales = cuotas_totales;
    }

    public int getCuotas_pagadas() {
        return cuotas_pagadas;
    }

    public void setCuotas_pagadas(int cuotas_pagadas) {
        this.cuotas_pagadas = cuotas_pagadas;
    }
    
    
    
}
