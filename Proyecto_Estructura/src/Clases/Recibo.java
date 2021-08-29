
package Clases;

public class Recibo {
    private String nombrec;
    private String numerofactura;
    private String numeroRecibo;
    private String nomproducto;
    private String fechapago;
    private String conto;
    private String pagoTotal;
    private int totales;

    public Recibo() {
    }

    public Recibo(String nombrec, String numerofactura, String numeroRecibo, String nomproducto, String fechapago, String conto, String pagoTotal) {
        this.nombrec = nombrec;
        this.numerofactura = numerofactura;
        this.numeroRecibo = numeroRecibo;
        this.nomproducto = nomproducto;
        this.fechapago = fechapago;
        this.conto = conto;
        this.pagoTotal = pagoTotal;
    }

    public Recibo(String nombrec, String numerofactura, String numeroRecibo, String nomproducto, String fechapago, String conto) {
        this.nombrec = nombrec;
        this.numerofactura = numerofactura;
        this.numeroRecibo = numeroRecibo;
        this.nomproducto = nomproducto;
        this.fechapago = fechapago;
        this.conto = conto;
    }

    public Recibo(String nombrec, String numerofactura, String numeroRecibo, String nomproducto, String fechapago) {
        this.nombrec = nombrec;
        this.numerofactura = numerofactura;
        this.numeroRecibo = numeroRecibo;
        this.nomproducto = nomproducto;
        this.fechapago = fechapago;
    }

    public Recibo(String nombrec, String numerofactura, String numeroRecibo, String nomproducto, String fechapago, String conto, String pagoTotal, int totales) {
        this.nombrec = nombrec;
        this.numerofactura = numerofactura;
        this.numeroRecibo = numeroRecibo;
        this.nomproducto = nomproducto;
        this.fechapago = fechapago;
        this.conto = conto;
        this.pagoTotal = pagoTotal;
        this.totales = totales;
    }

    public int getTotales() {
        return totales;
    }

    public void setTotales(int totales) {
        this.totales = totales;
    }

    public String getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(String pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(String numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getNomproducto() {
        return nomproducto;
    }

    public void setNomproducto(String nomproducto) {
        this.nomproducto = nomproducto;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public String getConto() {
        return conto;
    }

    public void setConto(String conto) {
        this.conto = conto;
    }
    
}
