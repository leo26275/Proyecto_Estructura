package Clases;

import java.util.Date;

public class inventario {

    private int id_inventario;
    private String fecha;
    private String movimiento;
    private int cantidad_E;
    private double costoU_E;
    private double costoI_E;
    private int cantidad_S;
    private double costoU_S;
    private double costoI_S;
    private int cantidad_F;
    private double costoU_F;
    private double costoI_F;
    private int id_producto;

    public inventario() {
    }

    public inventario( String fecha, String movimiento, int cantidad_S, double costoU_S, double costoI_S, int cantidad_F, double costoU_F, double costoI_F, int id_producto, int a) {
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad_S = cantidad_S;
        this.costoU_S = costoU_S;
        this.costoI_S = costoI_S;
        this.cantidad_F = cantidad_F;
        this.costoU_F = costoU_F;
        this.costoI_F = costoI_F;
        this.id_producto = id_producto;
    }

    public inventario(String fecha, String movimiento, int cantidad_E, double costoU_E, double costoI_E, int cantidad_F, double costoU_F, double costoI_F, int id_producto) {
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad_E = cantidad_E;
        this.costoU_E = costoU_E;
        this.costoI_E = costoI_E;
        this.cantidad_F = cantidad_F;
        this.costoU_F = costoU_F;
        this.costoI_F = costoI_F;
        this.id_producto = id_producto;
    }

    public inventario(String fecha, String movimiento, int cantidad_E, double costoU_E, double costoI_E, int cantidad_S, double costoU_S, double costoI_S, int cantidad_F, double costoU_F, double costoI_F, int id_producto) {
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad_E = cantidad_E;
        this.costoU_E = costoU_E;
        this.costoI_E = costoI_E;
        this.cantidad_S = cantidad_S;
        this.costoU_S = costoU_S;
        this.costoI_S = costoI_S;
        this.cantidad_F = cantidad_F;
        this.costoU_F = costoU_F;
        this.costoI_F = costoI_F;
        this.id_producto = id_producto;
    }

    public inventario(String fecha, String movimiento, int cantidad_F, double costoU_F, double costoI_F,int id_producto) {
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad_F = cantidad_F;
        this.costoU_F = costoU_F;
        this.costoI_F = costoI_F;
        this.id_producto = id_producto;
    }

    public inventario(int id_inventario, String fecha, String movimiento, int cantidad_E, double costoU_E, double costoI_E, int cantidad_S, double costoU_S, double costoI_S, int cantidad_F, double costoU_F, double costoI_F) {
        this.id_inventario = id_inventario;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cantidad_E = cantidad_E;
        this.costoU_E = costoU_E;
        this.costoI_E = costoI_E;
        this.cantidad_S = cantidad_S;
        this.costoU_S = costoU_S;
        this.costoI_S = costoI_S;
        this.cantidad_F = cantidad_F;
        this.costoU_F = costoU_F;
        this.costoI_F = costoI_F;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getCantidad_E() {
        return cantidad_E;
    }

    public void setCantidad_E(int cantidad_E) {
        this.cantidad_E = cantidad_E;
    }

    public double getCostoU_E() {
        return costoU_E;
    }

    public void setCostoU_E(double costoU_E) {
        this.costoU_E = costoU_E;
    }

    public double getCostoI_E() {
        return costoI_E;
    }

    public void setCostoI_E(double costoI_E) {
        this.costoI_E = costoI_E;
    }

    public int getCantidad_S() {
        return cantidad_S;
    }

    public void setCantidad_S(int cantidad_S) {
        this.cantidad_S = cantidad_S;
    }

    public double getCostoU_S() {
        return costoU_S;
    }

    public void setCostoU_S(double costoU_S) {
        this.costoU_S = costoU_S;
    }

    public double getCostoI_S() {
        return costoI_S;
    }

    public void setCostoI_S(double costoI_S) {
        this.costoI_S = costoI_S;
    }

    public int getCantidad_F() {
        return cantidad_F;
    }

    public void setCantidad_F(int cantidad_F) {
        this.cantidad_F = cantidad_F;
    }

    public double getCostoU_F() {
        return costoU_F;
    }

    public void setCostoU_F(double costoU_F) {
        this.costoU_F = costoU_F;
    }

    public double getCostoI_F() {
        return costoI_F;
    }

    public void setCostoI_F(double costoI_F) {
        this.costoI_F = costoI_F;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

}
