package Estructuras;

public class NodoPila<E> {

    E dato;
    NodoPila sig;

    public NodoPila(E dato) {
        this.dato = dato;
        sig = null;
    }

    public NodoPila(E dato, NodoPila n) {
        this.dato = dato;
        this.sig = n;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoPila getSig() {
        return sig;
    }

    public void setSig(NodoPila sig) {
        this.sig = sig;
    }

}
