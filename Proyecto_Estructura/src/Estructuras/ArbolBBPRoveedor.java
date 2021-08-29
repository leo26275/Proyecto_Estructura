
package Estructuras;

import Clases.Proveedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArbolBBPRoveedor<T> extends ArbolBinario {
    
    public ArbolBBPRoveedor() {
        super();
    }

    public void insertar(T dato) {
        super.setRaiz(insertar(dato, super.getRaiz()));
    }

    private NodoArbol insertar(T dato, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(dato);
        } else if (super.compareProveedor(dato, r) < 0) {
            NodoArbol izq;
            izq = insertar(dato, r.getIzdo());
            r.setIzdo(izq);
        } else if (super.compareProveedor(dato, r) > 0) {
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
        } else if (dato.compareToIgnoreCase(((Proveedor) r.getDato()).getNombre()) == 0) {
            return r;
        } else {
            if (dato.compareToIgnoreCase(((Proveedor) r.getDato()).getNombre()) < 0) {
                NodoArbol izq;
                izq = buscar(dato, r.getIzdo());
                return izq;
            } else if (dato.compareToIgnoreCase(((Proveedor) r.getDato()).getNombre()) > 0) {
                NodoArbol der;
                der = buscar(dato, r.getDcho());
                return der;
            }
        }
        return r;
    }

}
