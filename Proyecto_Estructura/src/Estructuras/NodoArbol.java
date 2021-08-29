/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author leo_g
 */
public class NodoArbol<T> {

    private T dato;/*datos parametrizado*/

    private NodoArbol izdo;/*rama izquiera*/

    private NodoArbol dcho;/*rama derecha*/

    int alt = 0;/*altura maxxima de los hijos*/

    public NodoArbol(T dato) {
        this.dato = dato;
        izdo = null;
        dcho = null;
        alt=0;
    }

    public NodoArbol(T dato, NodoArbol izdo, NodoArbol dcho) {
        this.dato = dato;
        this.izdo = izdo;
        this.dcho = dcho;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoArbol getIzdo() {
        return izdo;
    }

    public void setIzdo(NodoArbol izdo) {
        this.izdo = izdo;
    }

    public NodoArbol getDcho() {
        return dcho;
    }

    public void setDcho(NodoArbol dcho) {
        this.dcho = dcho;
    }
}
