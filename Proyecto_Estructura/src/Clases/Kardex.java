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
public class Kardex {
    private int idkardex;
    private String movimiento;
    private int cantidad_e;
    private double cotou_e;
    private double costoi_e;
    private int cantidad_s;
    private double costou_s;
    private double costoi_s;
    private int cantidad_f;
    private double costou_f;
    private double costoi_f;

    public Kardex() {
    }

    public Kardex(int idkardex, String movimiento, int cantidad_e, double cotou_e, double costoi_e, int cantidad_s, double costou_s, double costoi_s, int cantidad_f, double costou_f, double costoi_f) {
        this.idkardex = idkardex;
        this.movimiento = movimiento;
        this.cantidad_e = cantidad_e;
        this.cotou_e = cotou_e;
        this.costoi_e = costoi_e;
        this.cantidad_s = cantidad_s;
        this.costou_s = costou_s;
        this.costoi_s = costoi_s;
        this.cantidad_f = cantidad_f;
        this.costou_f = costou_f;
        this.costoi_f = costoi_f;
    }

    public int getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(int idkardex) {
        this.idkardex = idkardex;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public int getCantidad_e() {
        return cantidad_e;
    }

    public void setCantidad_e(int cantidad_e) {
        this.cantidad_e = cantidad_e;
    }

    public double getCotou_e() {
        return cotou_e;
    }

    public void setCotou_e(double cotou_e) {
        this.cotou_e = cotou_e;
    }

    public double getCostoi_e() {
        return costoi_e;
    }

    public void setCostoi_e(double costoi_e) {
        this.costoi_e = costoi_e;
    }

    public int getCantidad_s() {
        return cantidad_s;
    }

    public void setCantidad_s(int cantidad_s) {
        this.cantidad_s = cantidad_s;
    }

    public double getCostou_s() {
        return costou_s;
    }

    public void setCostou_s(double costou_s) {
        this.costou_s = costou_s;
    }

    public double getCostoi_s() {
        return costoi_s;
    }

    public void setCostoi_s(double costoi_s) {
        this.costoi_s = costoi_s;
    }

    public int getCantidad_f() {
        return cantidad_f;
    }

    public void setCantidad_f(int cantidad_f) {
        this.cantidad_f = cantidad_f;
    }

    public double getCostou_f() {
        return costou_f;
    }

    public void setCostou_f(double costou_f) {
        this.costou_f = costou_f;
    }

    public double getCostoi_f() {
        return costoi_f;
    }

    public void setCostoi_f(double costoi_f) {
        this.costoi_f = costoi_f;
    }
    
    
}
