package Estructuras;

import Clases.Detalle_venta;
import java.util.ArrayList;

public class ListaDobleDetalleVenta {

    private NodoListaDoble lista;

    public ListaDobleDetalleVenta() {
        this.lista = null;
    }

    public boolean isEmpty() {
        return lista == null;
    }

    public void insertar(Detalle_venta dato) {
        NodoListaDoble nuevo = new NodoListaDoble(dato);
        if (isEmpty()) {
            lista = nuevo;
        } else if (dato.getId_producto().getNombre().compareToIgnoreCase(((Detalle_venta) lista.getDato()).getId_producto().getNombre()) < 0) {
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

    public NodoListaDoble ubicar(Detalle_venta dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getId_producto().getNombre().compareToIgnoreCase(((Detalle_venta) lista.getDato()).getId_producto().getNombre()) > 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getId_producto().getNombre().compareToIgnoreCase(((Detalle_venta) lista.getDato()).getId_producto().getNombre()) > 0) {
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
