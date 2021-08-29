package Estructuras;

public class ColaNodo<E> {

    NodoCola fr;
    NodoCola fn;

    public ColaNodo() {
        fr = null;
        fn = null;
    }

    public boolean isEmpty() {
        return fr == null;
    }

    public void offer(E dato) {
        NodoCola n = new NodoCola(dato);
        if (isEmpty()) {
            fr = n;
        } else {
            fn.setSig(n);
        }
        fn = n;
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E aux = (E) fr.getDato();
        if (fr.getSig() == null) {
            fr = fn = null;
        } else {
            fr = fr.getSig();
        }
        return aux;
    }

    public void offerFirst(E dato) {
        NodoCola n = new NodoCola(dato, fr);
        fr = n;
    }

    public void offerLast(E dato) {
        offer(dato);
    }

    public E pollFirst() {
        return poll();
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        if (fr == fn) {
            return poll();
        } else {
            E dato = (E) fn.getDato();
            NodoCola aux = fr;
            while (aux.getSig() != fn) {
                aux = aux.getSig();
            }
            fn = aux;
            fn.setSig(null);
            return dato;
        }
    }
}
