package Clases;

public class CompraProducto {

    private String idcompra;
    private int cantidad;
    private double precio;
    private String fechaadquisiscion;
    private double porcentajeganancia;
    private Proveedor proveedor;
    private Producto producto;

    public CompraProducto() {
    }

    public CompraProducto(double precio, double porcentajeganancia) {
        this.precio = precio;
        this.porcentajeganancia = porcentajeganancia;
    }

    public CompraProducto(int cantidad, double precio, double porcentajeganancia) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.porcentajeganancia = porcentajeganancia;
    }

    public CompraProducto(String idcompra, int cantidad, double precio, String fechaadquisiscion, double porcentajeganancia, Proveedor proveedor, Producto producto) {
        this.idcompra = idcompra;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaadquisiscion = fechaadquisiscion;
        this.porcentajeganancia = porcentajeganancia;
        this.proveedor = proveedor;
        this.producto = producto;
    }

    public CompraProducto(int cantidad, double precio, String fechaadquisiscion, double porcentajeganancia, Proveedor proveedor, Producto producto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaadquisiscion = fechaadquisiscion;
        this.porcentajeganancia = porcentajeganancia;
        this.proveedor = proveedor;
        this.producto = producto;
    }

    public CompraProducto(String idcompra, int cantidad, String fechaadquisiscion, double precio, double porcentajeganancia) {
        this.idcompra = idcompra;
        this.cantidad = cantidad;
        this.fechaadquisiscion = fechaadquisiscion;
        this.precio = precio;
        this.porcentajeganancia = porcentajeganancia;
    }

    public CompraProducto(String idcompra) {
        this.idcompra = idcompra;
    }

    public String getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(String idcompra) {
        this.idcompra = idcompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFechaadquisiscion() {
        return fechaadquisiscion;
    }

    public void setFechaadquisiscion(String fechaadquisiscion) {
        this.fechaadquisiscion = fechaadquisiscion;
    }

    public double getPorcentajeganancia() {
        return porcentajeganancia;
    }

    public void setPorcentajeganancia(double porcentajeganancia) {
        this.porcentajeganancia = porcentajeganancia;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
