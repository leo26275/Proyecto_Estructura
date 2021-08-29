package Estructuras;

import Clases.Proveedor;
import java.util.ArrayList;

public class ListaDobleProveedor {

    private NodoListaDoble lista;

    public ListaDobleProveedor() {
        this.lista = null;
    }

    public boolean isEmpty() {
        return lista == null;
    }

    public void insertar(Proveedor dato) {
        NodoListaDoble nuevo = new NodoListaDoble(dato);
        if (isEmpty()) {
            lista = nuevo;
        } else if (dato.getNombre().compareToIgnoreCase(((Proveedor) lista.getDato()).getNombre()) < 0) {
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

    public NodoListaDoble ubicar(Proveedor dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getNombre().compareToIgnoreCase(((Proveedor) lista.getDato()).getNombre()) > 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getNombre().compareToIgnoreCase(((Proveedor) lista.getDato()).getNombre()) > 0) {
            ant = aux;
        }
        return ant;
    }

    public NodoListaDoble ubicarotro(Proveedor dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getNombre().compareToIgnoreCase(((Proveedor) lista.getDato()).getNombre()) != 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getNombre().compareToIgnoreCase(((Proveedor) aux.getDato()).getNombre()) != 0) {
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
