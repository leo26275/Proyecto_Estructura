
package Estructuras;


public class NodoListaSimple<E> {
    
   E dato;
   NodoListaSimple siguiente;

   
    public NodoListaSimple(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public NodoListaSimple(E dato, NodoListaSimple siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoListaSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaSimple siguiente) {
        this.siguiente = siguiente;
    }
}
