package test.java.com.banco.estructuras;

import main.java.com.banco.estructuras.Lista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaTest {

    private Lista<Integer> lista;

    @BeforeEach
    void configurar() {
        lista = new Lista<>();
    }

    @Test
    void debeIniciarVacia() {
        assertTrue(lista.esVacia());
        assertEquals(0, lista.cuentaElementos());
    }

    @Test
    void debeAgregarElementosAlFinal() {
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(3, lista.cuentaElementos());
        assertEquals(10, lista.buscarDato(0));
        assertEquals(30, lista.buscarDato(2));
    }

    @Test
    void debeAgregarElementoAlInicio() {
        lista.agregar(20);
        lista.agregarAlInicio(10);

        assertEquals(10, lista.buscarDato(0));
        assertEquals(20, lista.buscarDato(1));
    }

    @Test
    void debeAgregarElementoEnPosicion() {
        lista.agregar(10);
        lista.agregar(30);

        lista.agregarEnPosicion(1, 20);

        assertEquals(3, lista.cuentaElementos());
        assertEquals(20, lista.buscarDato(1));
    }

    @Test
    void debeAgregarEnPosicionCero() {
        lista.agregar(20);
        lista.agregarEnPosicion(0, 10);

        assertEquals(10, lista.buscarDato(0));
    }

    @Test
    void debeAgregarEnLaUltimaPosicion() {
        lista.agregar(10);
        lista.agregarEnPosicion(1, 20);

        assertEquals(20, lista.buscarDato(1));
    }

    @Test
    void debeEliminarElPrimerElemento() {
        lista.agregar(10);
        lista.agregar(20);

        int eliminado = lista.eliminarPrimero();

        assertEquals(10, eliminado);
        assertEquals(20, lista.buscarDato(0));
        assertEquals(1, lista.cuentaElementos());
    }

    @Test
    void debeEliminarElUltimoElemento() {
        lista.agregar(10);
        lista.agregar(20);

        int eliminado = lista.eliminarUltimo();

        assertEquals(20, eliminado);
        assertEquals(10, lista.buscarDato(0));
        assertEquals(1, lista.cuentaElementos());
    }

    @Test
    void debeEliminarElementoEnPosicion() {
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        int eliminado = lista.eliminarEnPosicion(1);

        assertEquals(20, eliminado);
        assertEquals(2, lista.cuentaElementos());
        assertEquals(30, lista.buscarDato(1));
    }

    @Test
    void debeBuscarElementoPorValor() {
        lista.agregar(10);
        lista.agregar(20);

        Object buscado = Integer.valueOf(20);

        assertEquals(20, lista.buscarDato(buscado));
    }

    @Test
    void debeRetornarNullCuandoNoEncuentraElemento() {
        lista.agregar(10);

        Object buscado = Integer.valueOf(50);

        assertNull(lista.buscarDato(buscado));
    }

    @Test
    void debeComprobarSiContieneUnElemento() {
        lista.agregar(10);
        lista.agregar(20);

        assertTrue(lista.contiene(20));
        assertFalse(lista.contiene(50));
    }

    @Test
    void debeLimpiarLaLista() {
        lista.agregar(10);
        lista.agregar(20);

        lista.limpiar();

        assertTrue(lista.esVacia());
        assertEquals(0, lista.cuentaElementos());
    }

    @Test
    void debeLanzarExcepcionAlBuscarIndiceInvalido() {
        lista.agregar(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> lista.buscarDato(5)
        );
    }

    @Test
    void debeLanzarExcepcionAlInsertarEnPosicionInvalida() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> lista.agregarEnPosicion(2, 10)
        );
    }

    @Test
    void debeLanzarExcepcionAlEliminarEnListaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> lista.eliminarPrimero()
        );
    }

    @Test
    void debeLanzarExcepcionAlEliminarUltimoDeListaVacia() {
        assertThrows(
                IllegalStateException.class,
                () -> lista.eliminarUltimo()
        );
    }

    @Test
    void debeLanzarExcepcionAlAgregarNull() {
        assertThrows(
                NullPointerException.class,
                () -> lista.agregar(null)
        );
    }

    @Test
    void debeEliminarElUnicoElementoSinDejarReferencias() {
        lista.agregar(10);

        assertEquals(10, lista.eliminarPrimero());

        assertTrue(lista.esVacia());

        lista.agregar(20);

        assertEquals(20, lista.buscarDato(0));
    }
}