package main.java.com.banco.estructuras;
import java.util.Objects;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public void encolar(T dato) {
        Objects.requireNonNull(dato);

        Nodo<T> nuevo = new Nodo<>(dato);

        if (esVacia()) {
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }

        tamanio++;
    }

    public T desencolar() {
        verificarNoVacia();

        T dato = frente.getDato();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        tamanio--;
        return dato;
    }

    public T peek() {
        verificarNoVacia();
        return frente.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        Nodo<T> actual = frente;

        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void mostrar() {
        Nodo<T> actual = frente;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    private void verificarNoVacia() {
        if (esVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }
    }
}