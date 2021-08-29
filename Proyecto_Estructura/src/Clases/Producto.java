/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Proveedor;
import java.util.Date;

/**
 *
 * @author leo_g
 */
public class Producto {

    private String idproducto;
    private String codigo;
    private String nombre;
    private Categoria idcategoria;
    private String descriccion;
    private CompraProducto compraproducto;

    public Producto(String idproducto, String codigo, String nombre, Categoria idcategoria, String descriccion) {
        this.idproducto = idproducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.descriccion = descriccion;
    }

    public Producto(String codigo, String nombre, Categoria idcategoria, String descriccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.descriccion = descriccion;
    }

    public Producto(String codigo, String nombre, Categoria idcategoria, String descriccion, CompraProducto compraproducto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idcategoria = idcategoria;
        this.descriccion = descriccion;
        this.compraproducto = compraproducto;
    }

    public Producto(String codigo, String nombre, String descriccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descriccion = descriccion;
    }

    public Producto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Producto() {
    }

    public CompraProducto getCompraproducto() {
        return compraproducto;
    }

    public void setCompraproducto(CompraProducto compraproducto) {
        this.compraproducto = compraproducto;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescriccion() {
        return descriccion;
    }

    public void setDescriccion(String descriccion) {
        this.descriccion = descriccion;
    }

}
