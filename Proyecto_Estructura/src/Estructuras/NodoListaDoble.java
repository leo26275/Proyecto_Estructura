
package Estructuras;


public class NodoListaDoble<E> {
    E dato;
    NodoListaDoble sig;
    NodoListaDoble ant;

    public NodoListaDoble(E dato) {
        this.dato = dato;
        this.sig = null;
        this.ant = null;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoListaDoble getSig() {
        return sig;
    }

    public void setSig(NodoListaDoble sig) {
        this.sig = sig;
    }

    public NodoListaDoble getAnt() {
        return ant;
    }

    public void setAnt(NodoListaDoble ant) {
        this.ant = ant;
    }
    
    
}
