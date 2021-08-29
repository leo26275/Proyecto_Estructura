package Estructuras;

import Clases.Pago;
import java.util.ArrayList;

public class ListaDoblePago {

    private NodoListaDoble lista;

    public ListaDoblePago() {
        this.lista = null;
    }

    public boolean isEmpty() {
        return lista == null;
    }

    public void insertar(Pago dato) {
        NodoListaDoble nuevo = new NodoListaDoble(dato);
        if (isEmpty()) {
            lista = nuevo;
        } else if (dato.getFechapago().compareToIgnoreCase(((Pago) lista.getDato()).getFechapago()) < 0) {
            nuevo.setSig(lista);
            lista.setAnt(nuevo);
            lista = nuevo;
        } else {
            NodoListaDoble aux = ubicar(dato);
            nuevo.setSig(aux.getSig());
            nuevo.setAnt(aux);
            if (aux.getSig() != null) {
                nuevo.getSig().setAnt(nuevo);//primera forma
                //aux.getSig().setAnt(nuevo);//segunda forma
            }
            aux.setSig(nuevo);
        }
    }

    public NodoListaDoble ubicar(Pago dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getFechapago().compareToIgnoreCase(((Pago) lista.getDato()).getFechapago()) > 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getFechapago().compareToIgnoreCase(((Pago) lista.getDato()).getFechapago()) > 0) {
            ant = aux;
        }
        return ant;
    }

    public NodoListaDoble ubicarotro(Pago dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getFechapago().compareToIgnoreCase(((Pago) lista.getDato()).getFechapago()) != 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getFechapago().compareToIgnoreCase(((Pago) aux.getDato()).getFechapago()) != 0) {
            ant = aux;
        }
        return ant;
    }

    public ArrayList toArrayAsc() {
        ArrayList list = new ArrayList<>();
        NodoListaDoble aux = lista;
        while (aux != null) {
            list.add(aux.getDato());
            aux = aux.getSig();
        }
        return list;
    }

    public ArrayList toArrayDsc() {
        ArrayList list = new ArrayList<>();
        NodoListaDoble aux = lista;
        while (aux.getSig() != null) {
            aux = aux.getSig();
        }
        while (aux != null) {
            list.add(aux.getDato());
            aux = aux.getAnt();
        }
        return list;
    }

}
