
package Estructuras;

import Clases.Detalle_venta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArbolBBDetalleVenta<T> extends ArbolBinario {
    
    public ArbolBBDetalleVenta() {
        super();
    }

    public void insertar(T dato) {
        super.setRaiz(insertar(dato, super.getRaiz()));
    }

    private NodoArbol insertar(T dato, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(dato);
        } else if (super.compareDetalleVenta(dato, r) < 0) {
            NodoArbol izq;
            izq = insertar(dato, r.getIzdo());
            r.setIzdo(izq);
        } else if (super.compareDetalleVenta(dato, r) > 0) {
            NodoArbol der;
            der = insertar(dato, r.getDcho());
            r.setDcho(der);
        } else {
            JOptionPane.showMessageDialog(null, "Duplicado");
        }
        return r;
    }

    public NodoArbol buscar(String dato) {
        return buscar(dato, super.getRaiz());
    }

    public NodoArbol buscar(String dato, NodoArbol r) {
        if (r == null) {
            return null;
        } else if (dato.compareToIgnoreCase(((Detalle_venta) r.getDato()).getId_producto().getNombre()) == 0) {
            return r;
        } else {
            if (dato.compareToIgnoreCase(((Detalle_venta) r.getDato()).getId_producto().getNombre()) < 0) {
                NodoArbol izq;
                izq = buscar(dato, r.getIzdo());
                return izq;
            } else if (dato.compareToIgnoreCase(((Detalle_venta) r.getDato()).getId_producto().getNombre()) > 0) {
                NodoArbol der;
                der = buscar(dato, r.getDcho());
                return der;
            }
        }
        return r;
    }


}
