
package Clases;

public class Pago {
    private String idpago;
    private String fechapago;
    private Datos_credito datoscredito;

    public Pago() {
    }

    public Pago(String idpago, String fechapago, Datos_credito datoscredito) {
        this.idpago = idpago;
        this.fechapago = fechapago;
        this.datoscredito = datoscredito;
    }

    public Pago(String fechapago, Datos_credito datoscredito) {
        this.fechapago = fechapago;
        this.datoscredito = datoscredito;
    }

    public String getIdpago() {
        return idpago;
    }

    public void setIdpago(String idpago) {
        this.idpago = idpago;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public Datos_credito getDatoscredito() {
        return datoscredito;
    }

    public void setDatoscredito(Datos_credito datoscredito) {
        this.datoscredito = datoscredito;
    }
    
    
}
