package test.java.com.banco.estructuras;

import main.java.com.banco.estructuras.Pila;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PilaTest {

    private Pila<Integer> pila;

    @BeforeEach
    void configurar() {
        pila = new Pila<>();
    }

    @Test
    void debeIniciarVacia() {
        assertTrue(pila.esVacia());
        assertEquals(0, pila.tamanio());
    }

    @Test
    void debeApilarElementos() {
        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);

        assertEquals(3, pila.tamanio());
    }

    @Test
    void debeDesapilarEnOrdenLifo() {
        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);

        assertEquals(30, pila.desapilar());
        assertEquals(20, pila.desapilar());
        assertEquals(10, pila.desapilar());
    }

    @Test
    void peekDebeMostrarLaCimaSinEliminarla() {
        pila.apilar(10);
        pila.apilar(20);

        assertEquals(20, pila.peek());
        assertEquals(2, pila.tamanio());
    }

    @Test
    void debeBuscarUnElemento() {
        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);

        assertTrue(pila.buscar(20));
        assertFalse(pila.buscar(50));
    }

    @Test
    void contieneDebeComprobarLaExistenciaDeUnElemento() {
        pila.apilar(10);
        pila.apilar(20);

        assertTrue(pila.contiene(10));
        assertFalse(pila.contiene(50));
    }

    @Test
    void debeLimpiarLaPila() {
        pila.apilar(10);
        pila.apilar(20);

        pila.limpiar();

        assertTrue(pila.esVacia());
        assertEquals(0, pila.tamanio());
    }

    @Test
    void debeLanzarExcepcionAlDesapilarPilaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> pila.desapilar()
        );
    }

    @Test
    void debeLanzarExcepcionAlConsultarPilaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> pila.peek()
        );
    }

    @Test
    void debeLanzarExcepcionAlApilarNull() {
        assertThrows(
                NullPointerException.class,
                () -> pila.apilar(null)
        );
    }

    @Test
    void debePermitirApilarDespuesDeLimpiar() {
        pila.apilar(10);
        pila.limpiar();

        pila.apilar(20);

        assertEquals(20, pila.peek());
        assertEquals(1, pila.tamanio());
    }
}