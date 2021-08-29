
package Estructuras;

import Clases.Cliente;
import Clases.Empleado;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ArbolBB<T> extends ArbolBinario {

    public ArbolBB() {
        super();
    }

    public void insertar(T dato) {
        super.setRaiz(insertar(dato, super.getRaiz()));
    }

    private NodoArbol insertar(T dato, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(dato);
        } else if (super.compare(dato, r) < 0) {
            NodoArbol izq;
            izq = insertar(dato, r.getIzdo());
            r.setIzdo(izq);
        } else if (super.compare(dato, r) > 0) {
            NodoArbol der;
            der = insertar(dato, r.getDcho());
            r.setDcho(der);
        } else {
            JOptionPane.showMessageDialog(null, "Duplicado");
        }
        return r;
    }

    public NodoArbol buscar(String dato) {
        return buscar(dato, super.getRaiz());
    }

    public NodoArbol buscar(String dato, NodoArbol r) {
        if (r == null) {
            return null;
        } else if (dato.compareToIgnoreCase(((Cliente) r.getDato()).getNombre()) == 0) {
            return r;
        } else {
            if (dato.compareToIgnoreCase(((Cliente) r.getDato()).getNombre()) < 0) {
                NodoArbol izq;
                izq = buscar(dato, r.getIzdo());
                return izq;
            } else if (dato.compareToIgnoreCase(((Cliente) r.getDato()).getNombre()) > 0) {
                NodoArbol der;
                der = buscar(dato, r.getDcho());
                return der;
            }
        }
        return r;
    }

    public void eliminar(T dato) {
        super.setRaiz(eliminar(super.getRaiz(), dato));
    }

    public NodoArbol eliminar(NodoArbol r, T dato) {
        if (r == null) {
            JOptionPane.showMessageDialog(null, "No hay datos");
        } else if (((String) dato).compareToIgnoreCase(((Cliente) r.getDato()).getNombre()) < 0) {/*comparamos la cadena que le enviamos para eliminar*/
            NodoArbol izq;
            izq = eliminar(r.getIzdo(), dato);
            r.setIzdo(izq);
        } else if (((String) dato).compareToIgnoreCase(((Cliente) r.getDato()).getNombre()) > 0) {/*comparamos la cadena que le enviamos para eliminar*/
            NodoArbol drcha;
            drcha = eliminar(r.getDcho(), dato);
            r.setDcho(drcha);
        } else {//nodo a quitar
            NodoArbol q;
            q = r;
            if (q.getIzdo() == null) {/*si el dato a eliminar no tiene hijos**/
                r = q.getDcho();/*si a la izquiera es null se sustituye por la deerecha*/
            } else if (q.getDcho() == null) {/*si el dato a eliminar no tiene hijos**/
                r = q.getIzdo();/*si a la derecha es null se sustituye por la izquierda*/
            } else {/*si hay dos hijos es decir el dato tiene dos datos a sus lados*/
                q = reemplazar(q);
            }
            q = null;
        }
        return r;
    }

    /*metodo reemplazar en caso de que el dato tenga dos hijos*/
    private NodoArbol reemplazar(NodoArbol actual) {
        NodoArbol a, p;
        /*
        *entro a la izquierda y me boy mas a laderecha de las rama
        *
        *
         */
        p = actual;
        a = actual.getIzdo();/*contemdra la rama izquierda y se busca mas a la derecha*/
        while (a.getDcho() != null) {/*ahora de la rama izquierda me dirijo a la derecha*/
            p = a;/*trasladamos p al valor de la rama que llevamos*/
            a = a.getDcho();
        }
        actual.setDato(a.getDato());/*elimine el 100 y subo 81 en caso de que 81 no tuviera hijos a la izquierda*/

 /*if para validar si 81 tenia un hijo a la izquierda ya que se estaban buscando solo los de la derecha*/
        if (p == actual) {/*si es igual al que boy a eliminar*/
            p.setIzdo(a.getIzdo());/*agarro mi dato de la izquierda para que no quede perdido*/
        } else {
            p.setDcho(a.getIzdo());/*cuando subimos el 81 (como eliminamos 100) y tiene un hijo a la izquierda que es 80.5 este pasa a sustituir al dato que eliminamos**/
        }
        return a;
    }


    /*HACERLO*/
 /*SACAR PROFUNDIDAD,CUANTAS HOJAS TIENE, SACAR ALTURA,SI UN ARBOL ESTA COMPLETO,PESO DEL ARBOL(CANTIDAD DE NODOS QUE TIENE)*/
    public ArrayList NID() {
        ArrayList x = new ArrayList();
        super.setArbolito(x);
        return super.preOrdenNID(super.getRaiz());
    }

    public ArrayList IND() {
        ArrayList x = new ArrayList();
        super.setArbolito(x);
        return super.inOrdenIND(super.getRaiz());
    }

    public ArrayList IDN() {
        ArrayList x = new ArrayList();
        super.setArbolito(x);
        return super.postOrdenIDN(super.getRaiz());
    }
}
