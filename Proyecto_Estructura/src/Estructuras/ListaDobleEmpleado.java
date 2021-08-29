package Estructuras;

import Clases.Empleado;
import java.util.ArrayList;

public class ListaDobleEmpleado {

    private NodoListaDoble lista;

    public ListaDobleEmpleado() {
        this.lista = null;
    }

    public boolean isEmpty() {
        return lista == null;
    }

    public void insertar(Empleado dato) {
        NodoListaDoble nuevo = new NodoListaDoble(dato);
        if (isEmpty()) {
            lista = nuevo;
        } else if (dato.getNombre().compareToIgnoreCase(((Empleado) lista.getDato()).getNombre()) < 0) {
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

    public NodoListaDoble ubicar(Empleado dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getNombre().compareToIgnoreCase(((Empleado) lista.getDato()).getNombre()) > 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getNombre().compareToIgnoreCase(((Empleado) lista.getDato()).getNombre()) > 0) {
            ant = aux;
        }
        return ant;
    }

    public NodoListaDoble ubicarotro(Empleado dato) {
        NodoListaDoble aux = lista;
        NodoListaDoble ant = lista;
        while (aux.getSig() != null && (dato.getNombre().compareToIgnoreCase(((Empleado) lista.getDato()).getNombre()) != 0)) {
            ant = aux;
            aux = aux.getSig();
        }
        if (dato.getNombre().compareToIgnoreCase(((Empleado) aux.getDato()).getNombre()) != 0) {
            ant = aux;
        }
        return ant;
    }

    public NodoListaDoble buscar(Empleado dato) {
        NodoListaDoble aux = lista;
        while (aux != null) {
            if (dato.getNombre().compareToIgnoreCase(((Empleado) aux.getDato()).getNombre()) == 0) {
                return aux;
            }
            aux = aux.getSig();
        }
        return null;
    }

    public void Eliminar(Empleado dato) {
        NodoListaDoble eliminar = buscar(dato);
        if (eliminar != null) {
            if (eliminar == lista) {//que se el primer numero. la lista pasara al siguiente 
                lista = eliminar.getSig();
                //lista.setAnt(null); // no es necesario ya que eliminar el igual a null
            } else {
                NodoListaDoble aux = ubicar(dato);
                aux.setSig(eliminar.getSig());
                if (eliminar.getSig() != null) {
                    eliminar.getSig().setAnt(aux);
                }

            }
        }
        eliminar = null;
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
