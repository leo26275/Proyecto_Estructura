package Estructuras;

import Clases.Datos_credito;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArbolBBDatosCreditos<T> extends ArbolBinario {

    public ArbolBBDatosCreditos() {
        super();
    }

    public void insertar(T dato) {
        super.setRaiz(insertar(dato, super.getRaiz()));
    }

    private NodoArbol insertar(T dato, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(dato);
        } else if (super.compareDatosCredito(dato, r) < 0) {
            NodoArbol izq;
            izq = insertar(dato, r.getIzdo());
            r.setIzdo(izq);
        } else if (super.compareDatosCredito(dato, r) > 0) {
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
        } else if (dato.compareToIgnoreCase(((Datos_credito) r.getDato()).getProducto().getNombre()) == 0) {
            return r;
        } else {
            if (dato.compareToIgnoreCase(((Datos_credito) r.getDato()).getProducto().getNombre()) < 0) {
                NodoArbol izq;
                izq = buscar(dato, r.getIzdo());
                return izq;
            } else if (dato.compareToIgnoreCase(((Datos_credito) r.getDato()).getProducto().getNombre()) > 0) {
                NodoArbol der;
                der = buscar(dato, r.getDcho());
                return der;
            }
        }
        return r;
    }

}
