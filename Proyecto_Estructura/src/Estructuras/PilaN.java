package Estructuras;

import java.util.ArrayList;

public class PilaN<E> {

    NodoPila pila;

    public PilaN() {
        pila = null;
    }

    public void push(E dato) {
        NodoPila nuevo = new NodoPila(dato);
        nuevo.setSig(pila);
        pila = nuevo;
    }

    public E pop() {
        E aux = null;
        if (!isEmpty()) {
            aux = (E) pila.getDato();
            pila = pila.getSig();
        }
        return aux;
    }

    public boolean isEmpty() {
        return pila == null;
    }

    public ArrayList<E> toArray() {
        ArrayList a = new ArrayList<E>();
        NodoPila aux = pila;
        while (aux != null) {
            a.add(aux.getDato());
            aux = aux.getSig();
        }
        return a;
    }
}
