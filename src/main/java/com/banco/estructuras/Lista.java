package main.java.com.banco.estructuras;

import java.util.Objects;

public class Lista<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public void agregar(T dato) {
        Objects.requireNonNull(dato);

        Nodo<T> nuevo = new Nodo<>(dato);

        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }

        tamanio++;
    }

    public void agregarAlInicio(T dato) {
        Objects.requireNonNull(dato);

        Nodo<T> nuevo = new Nodo<>(dato);

        if (esVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }

        tamanio++;
    }

    public void agregarEnPosicion(int indice, T dato) {
        validarPosicionInsercion(indice);
        Objects.requireNonNull(dato);

        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }

        if (indice == tamanio) {
            agregar(dato);
            return;
        }

        Nodo<T> actual = obtenerNodo(indice);
        Nodo<T> nuevo = new Nodo<>(dato);
        Nodo<T> anterior = actual.getAnterior();

        nuevo.setAnterior(anterior);
        nuevo.setSiguiente(actual);
        anterior.setSiguiente(nuevo);
        actual.setAnterior(nuevo);

        tamanio++;
    }

    public T eliminarPrimero() {
        verificarNoVacia();

        T dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();

        if (cabeza == null) {
            cola = null;
        } else {
            cabeza.setAnterior(null);
        }

        tamanio--;
        return dato;
    }

    public T eliminarUltimo() {
        verificarNoVacia();

        T dato = cola.getDato();
        cola = cola.getAnterior();

        if (cola == null) {
            cabeza = null;
        } else {
            cola.setSiguiente(null);
        }

        tamanio--;
        return dato;
    }

    public T eliminarEnPosicion(int indice) {
        validarIndice(indice);

        if (indice == 0) {
            return eliminarPrimero();
        }

        if (indice == tamanio - 1) {
            return eliminarUltimo();
        }

        Nodo<T> actual = obtenerNodo(indice);

        actual.getAnterior().setSiguiente(actual.getSiguiente());
        actual.getSiguiente().setAnterior(actual.getAnterior());

        tamanio--;
        return actual.getDato();
    }

    public T buscarDato(int indice) {
        validarIndice(indice);
        return obtenerNodo(indice).getDato();
    }

    public T buscarDato(Object dato) {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return actual.getDato();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    public int cuentaElementos() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void mostrarAdelante() {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void mostrarAtras() {
        Nodo<T> actual = cola;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }

    private Nodo<T> obtenerNodo(int indice) {
        Nodo<T> actual;

        if (indice < tamanio / 2) {
            actual = cabeza;

            for (int i = 0; i < indice; i++) {
                actual = actual.getSiguiente();
            }
        } else {
            actual = cola;

            for (int i = tamanio - 1; i > indice; i--) {
                actual = actual.getAnterior();
            }
        }

        return actual;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    private void validarPosicionInsercion(int indice) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida.");
        }
    }

    private void verificarNoVacia() {
        if (esVacia()) {
            throw new IllegalStateException("La lista está vacía.");
        }
    }
}