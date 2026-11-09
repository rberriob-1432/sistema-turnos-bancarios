package test.java.com.banco.estructuras;
import main.java.com.banco.estructuras.Cola;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaTest {

    private Cola<Integer> cola;

    @BeforeEach
    void configurar() {
        cola = new Cola<>();
    }

    @Test
    void debeIniciarVacia() {
        assertTrue(cola.esVacia());
        assertEquals(0, cola.tamanio());
    }

    @Test
    void debeEncolarElementos() {
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);

        assertEquals(3, cola.tamanio());
    }

    @Test
    void debeDesencolarEnOrdenFifo() {
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);

        assertEquals(10, cola.desencolar());
        assertEquals(20, cola.desencolar());
        assertEquals(30, cola.desencolar());
    }

    @Test
    void peekDebeMostrarElPrimerElementoSinEliminarlo() {
        cola.encolar(10);
        cola.encolar(20);

        assertEquals(10, cola.peek());
        assertEquals(2, cola.tamanio());
    }

    @Test
    void debeContenerUnElemento() {
        cola.encolar(10);
        cola.encolar(20);

        assertTrue(cola.contiene(20));
        assertFalse(cola.contiene(50));
    }

    @Test
    void debeLimpiarLaCola() {
        cola.encolar(10);
        cola.encolar(20);

        cola.limpiar();

        assertTrue(cola.esVacia());
        assertEquals(0, cola.tamanio());
    }

    @Test
    void debeLanzarExcepcionAlDesencolarColaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> cola.desencolar()
        );
    }

    @Test
    void debeLanzarExcepcionAlConsultarColaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> cola.peek()
        );
    }

    @Test
    void debeLanzarExcepcionAlEncolarNull() {
        assertThrows(
                NullPointerException.class,
                () -> cola.encolar(null)
        );
    }

    @Test
    void debePermitirEncolarDespuesDeVaciarse() {
        cola.encolar(10);
        cola.desencolar();

        cola.encolar(20);

        assertEquals(20, cola.peek());
        assertEquals(1, cola.tamanio());
    }

    @Test
    void debeMantenerElOrdenDespuesDeDesencolar() {
        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);

        cola.desencolar();

        assertEquals(20, cola.peek());
        assertEquals(2, cola.tamanio());
    }
}