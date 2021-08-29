package Clases;

public class Detalle_venta {

    private String id_detalle;
    private Producto id_producto;
    private int cantidad;
    private Venta id_datos_venta;
    private Cliente cliente;
    private String tipo;

    private CompraProducto compraproducto;
    private Categoria categoria;

    public Detalle_venta() {
    }

    public Detalle_venta(int cantidad, String tipo, String id_detalle) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.id_detalle = id_detalle;
    }

    public Detalle_venta(String tipo, String id_detalle) {
        this.tipo = tipo;
        this.id_detalle = id_detalle;
    }

    public Detalle_venta(Producto id_producto, Categoria categoria, CompraProducto compraproducto, Venta id_datos_venta, String tipo, Cliente cliente) {
        this.id_producto = id_producto;
        this.categoria = categoria;
        this.compraproducto = compraproducto;
        this.id_datos_venta = id_datos_venta;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public Detalle_venta(Producto id_producto, int cantidad, Venta id_datos_venta) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
    }

    public Detalle_venta(String id_detalle, Producto id_producto, int cantidad, Venta id_datos_venta) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
    }

    public Detalle_venta(String id_detalle, Producto id_producto, int cantidad, Venta id_datos_venta, Cliente cliente) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
        this.cliente = cliente;
    }

    public Detalle_venta(String id_detalle, Producto id_producto, int cantidad, Venta id_datos_venta, Cliente cliente, String tipo) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
        this.cliente = cliente;
        this.tipo = tipo;
    }

    public Detalle_venta(String id_detalle, Producto id_producto, int cantidad, Venta id_datos_venta, String tipo) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
        this.tipo = tipo;
    }

    public Detalle_venta(Producto id_producto, int cantidad, Venta id_datos_venta, String tipo) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.id_datos_venta = id_datos_venta;
        this.tipo = tipo;
    }

    public CompraProducto getCompraproducto() {
        return compraproducto;
    }

    public void setCompraproducto(CompraProducto compraproducto) {
        this.compraproducto = compraproducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Detalle_venta(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getId_datos_venta() {
        return id_datos_venta;
    }

    public void setId_datos_venta(Venta id_datos_venta) {
        this.id_datos_venta = id_datos_venta;
    }

}
