package main.java.com.banco.estructuras;

import java.util.Objects;

public class Pila<T> {

    private Nodo<T> cima;
    private int tamanio;

    public void apilar(T dato) {
        Objects.requireNonNull(dato);

        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);

        cima = nuevo;
        tamanio++;
    }

    public T desapilar() {
        verificarNoVacia();

        T dato = cima.getDato();
        cima = cima.getSiguiente();

        tamanio--;
        return dato;
    }

    public T peek() {
        verificarNoVacia();
        return cima.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        return buscar(dato);
    }

    public boolean buscar(Object dato) {
        Nodo<T> actual = cima;

        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public void limpiar() {
        cima = null;
        tamanio = 0;
    }

    public void mostrar() {
        Nodo<T> actual = cima;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    private void verificarNoVacia() {
        if (esVacia()) {
            throw new IllegalStateException("La pila está vacía.");
        }
    }
}