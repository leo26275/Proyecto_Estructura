package Estructuras;

import Clases.Cliente;
import Clases.CompraProducto;
import Clases.Datos_credito;
import Clases.Detalle_venta;
import Clases.Empleado;
import Clases.Pago;
import Clases.Producto;
import Clases.Proveedor;
import Clases.Referencia;
import Clases.Usuario;
import Clases.Venta;
import java.util.ArrayList;

public class ArbolBinario<T> {

    NodoArbol raiz;
    ArrayList arbolito;

    public ArbolBinario() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    protected ArrayList preOrdenNID(NodoArbol r) {
        if (r != null) {/*siempre que sea diferente de vacio*/

            arbolito.add(r.getDato());/*add el dato el arreglo*/

            preOrdenNID(r.getIzdo());/*llamando asi mismo el metodo ya que es recursivo para que se valla a la rama izquierda*/

            preOrdenNID(r.getDcho());/*llamando asi mismo el metodo ya que es recursivo para que se valla a la rama derecha*/

        }
        return arbolito;
    }

    protected ArrayList inOrdenIND(NodoArbol r) {
        if (r != null) {
            inOrdenIND(r.getIzdo());
            arbolito.add(r.getDato());
            inOrdenIND(r.getDcho());
        }
        return arbolito;
    }

    protected ArrayList postOrdenIDN(NodoArbol r) {

        if (r != null) {
            postOrdenIDN(r.getIzdo());
            postOrdenIDN(r.getDcho());
            arbolito.add(r.getDato());
        }
        return arbolito;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public ArrayList getArbolito() {
        return arbolito;
    }

    public void setArbolito(ArrayList arbolito) {
        this.arbolito = arbolito;
    }

    /*Metodos de comparacion*/
    public int compare(T dato, NodoArbol n) {
        return ((Cliente) dato).getNombre().compareToIgnoreCase(((Cliente) n.getDato()).getNombre());
    }

    public int compareEmpleado(T dato, NodoArbol n) {
        return ((Empleado) dato).getNombre().compareToIgnoreCase(((Empleado) n.getDato()).getNombre());
    }

    public int compareProducto(T dato, NodoArbol n) {
        return ((Producto) dato).getNombre().compareToIgnoreCase(((Producto) n.getDato()).getNombre());
    }

    public int compareProveedor(T dato, NodoArbol n) {
        return ((Proveedor) dato).getNombre().compareToIgnoreCase(((Proveedor) n.getDato()).getNombre());
    }

    public int compareReferencia(T dato, NodoArbol n) {
        return ((Referencia) dato).getNombre().compareToIgnoreCase(((Referencia) n.getDato()).getNombre());
    }

    public int compareUsuario(T dato, NodoArbol n) {
        return ((Usuario) dato).getNombre().compareToIgnoreCase(((Usuario) n.getDato()).getNombre());
    }

    public int compareCompraProducto(T dato, NodoArbol n) {
        return ((CompraProducto) dato).getProducto().getNombre().compareToIgnoreCase(((CompraProducto) n.getDato()).getProducto().getNombre());
    }

    public int compareVenta(T dato, NodoArbol n) {
        return ((Venta) dato).getIdcliente().getNombre().compareToIgnoreCase(((Venta) n.getDato()).getIdcliente().getNombre());
    }

    public int compareDetalleVenta(T dato, NodoArbol n) {
        return ((Detalle_venta) dato).getId_producto().getNombre().compareToIgnoreCase(((Detalle_venta) n.getDato()).getId_producto().getNombre());
    }

    public int compareDatosCredito(T dato, NodoArbol n) {
        return ((Datos_credito) dato).getProducto().getNombre().compareToIgnoreCase(((Datos_credito) n.getDato()).getProducto().getNombre());
    }

    public int comparePago(T dato, NodoArbol n) {
        return ((Pago) dato).getFechapago().compareToIgnoreCase(((Pago) n.getDato()).getFechapago());
    }

    public int compare(int dato, NodoArbol n) {
        if (dato < ((int) n.getDato())) {
            return -1;
        } else if (dato > ((int) n.getDato())) {
            return 1;
        } else {
            return 0;
        }
    }
    /*regla para insertar izquierda menores y la derecha los mayores
      para eliminar se ve si es una hoja solo se elimina y si tiene solo un hijo el hijo sustitiye el padre
    y si tiene dos hijos el que esta mas a la izquierda de la rama derecha y la otra que esta mas a la izqierda se el que
    eysta mas a la derecha
    
     */

}
