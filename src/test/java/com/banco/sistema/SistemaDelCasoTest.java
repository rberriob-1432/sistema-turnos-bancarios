package test.java.com.banco.sistema;
import main.java.com.banco.modelo.EstadoTurno;
import main.java.com.banco.modelo.TipoServicio;
import main.java.com.banco.modelo.TurnoBancario;
import main.java.com.banco.sistema.SistemaDelCaso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaDelCasoTest {

    private SistemaDelCaso sistema;

    @BeforeEach
    void configurar() {
        sistema = new SistemaDelCaso();
    }

    @Test
    void debeRegistrarTurno() {
        TurnoBancario turno = sistema.registrar(
                "Carlos",
                "12345",
                TipoServicio.CAJA
        );

        assertEquals(1, turno.getNumeroTurno());
        assertEquals(EstadoTurno.PENDIENTE, turno.getEstado());
        assertEquals(1, sistema.cantidadRegistrados());
        assertEquals(1, sistema.cantidadPendientes());
    }

    @Test
    void debeGenerarNumerosConsecutivos() {
        TurnoBancario primero = sistema.registrar(
                "Carlos", "12345", TipoServicio.CAJA
        );

        TurnoBancario segundo = sistema.registrar(
                "Ana", "67890", TipoServicio.CREDITO
        );

        assertEquals(1, primero.getNumeroTurno());
        assertEquals(2, segundo.getNumeroTurno());
    }

    @Test
    void debeBuscarTurnoExistente() {
        sistema.registrar("Carlos", "12345", TipoServicio.CAJA);

        TurnoBancario encontrado = sistema.buscar(1);

        assertNotNull(encontrado);
        assertEquals("Carlos", encontrado.getNombreCliente());
    }

    @Test
    void debeRetornarNullAlBuscarTurnoInexistente() {
        assertNull(sistema.buscar(100));
    }

    @Test
    void debeProcesarElPrimerTurnoRegistrado() {
        sistema.registrar("Carlos", "12345", TipoServicio.CAJA);
        sistema.registrar("Ana", "67890", TipoServicio.CREDITO);

        TurnoBancario procesado = sistema.procesar();

        assertEquals(1, procesado.getNumeroTurno());
        assertEquals(EstadoTurno.ATENDIDO, procesado.getEstado());
        assertEquals(1, sistema.cantidadPendientes());
        assertEquals(1, sistema.cantidadAtendidos());
    }

    @Test
    void debeDeshacerElUltimoProcesamiento() {
        sistema.registrar("Carlos", "12345", TipoServicio.CAJA);
        sistema.procesar();

        TurnoBancario deshecho = sistema.deshacer();

        assertEquals(1, deshecho.getNumeroTurno());
        assertEquals(EstadoTurno.PENDIENTE, deshecho.getEstado());
        assertEquals(1, sistema.cantidadPendientes());
        assertEquals(0, sistema.cantidadAtendidos());
    }

    @Test
    void debeCancelarTurnoPendiente() {
        sistema.registrar("Carlos", "12345", TipoServicio.CAJA);

        TurnoBancario cancelado = sistema.cancelar(1);

        assertEquals(EstadoTurno.CANCELADO, cancelado.getEstado());
        assertEquals(0, sistema.cantidadPendientes());
        assertEquals(1, sistema.cantidadRegistrados());
    }

    @Test
    void noDebeCancelarTurnoYaProcesado() {
        sistema.registrar("Carlos", "12345", TipoServicio.CAJA);
        sistema.procesar();

        assertThrows(
                IllegalArgumentException.class,
                () -> sistema.cancelar(1)
        );
    }

    @Test
    void noDebeCancelarTurnoInexistente() {
        assertThrows(
                IllegalArgumentException.class,
                () -> sistema.cancelar(100)
        );
    }

    @Test
    void noDebeProcesarCuandoNoHayPendientes() {
        assertThrows(
                IllegalStateException.class,
                () -> sistema.procesar()
        );
    }

    @Test
    void noDebeDeshacerCuandoElHistorialEstaVacio() {
        assertThrows(
                IllegalStateException.class,
                () -> sistema.deshacer()
        );
    }

    @Test
    void debeConservarElOrdenDePendientesAlCancelar() {
        sistema.registrar("Carlos", "111", TipoServicio.CAJA);
        sistema.registrar("Ana", "222", TipoServicio.CREDITO);
        sistema.registrar("Luis", "333", TipoServicio.ASESORIA);

        sistema.cancelar(2);

        TurnoBancario primero = sistema.procesar();
        TurnoBancario segundo = sistema.procesar();

        assertEquals(1, primero.getNumeroTurno());
        assertEquals(3, segundo.getNumeroTurno());
    }

    @Test
    void debeMantenerElTotalRegistradoDespuesDeCancelar() {
        sistema.registrar("Carlos", "111", TipoServicio.CAJA);
        sistema.registrar("Ana", "222", TipoServicio.CREDITO);

        sistema.cancelar(1);

        assertEquals(2, sistema.cantidadRegistrados());
        assertEquals(1, sistema.cantidadPendientes());
    }
}