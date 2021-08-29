/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

public class NodoCola<E> {

    E dato;
    NodoCola sig;

    public NodoCola(E dato) {
        this.dato = dato;
        sig = null;
    }

    public NodoCola(E dato, NodoCola n) {
        this.dato = dato;
        this.sig = n;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoCola getSig() {
        return sig;
    }

    public void setSig(NodoCola sig) {
        this.sig = sig;
    }

}
