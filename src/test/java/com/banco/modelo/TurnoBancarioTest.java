package test.java.com.banco.modelo;


import main.java.com.banco.modelo.EstadoTurno;
import main.java.com.banco.modelo.TipoServicio;
import main.java.com.banco.modelo.TurnoBancario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnoBancarioTest {

    @Test
    void debeCrearTurnoConEstadoPendiente() {
        TurnoBancario turno = new TurnoBancario(
                1, "Carlos", "12345", TipoServicio.CAJA
        );

        assertEquals(1, turno.getNumeroTurno());
        assertEquals("Carlos", turno.getNombreCliente());
        assertEquals("12345", turno.getDocumento());
        assertEquals(TipoServicio.CAJA, turno.getTipoServicio());
        assertEquals(EstadoTurno.PENDIENTE, turno.getEstado());
        assertNotNull(turno.getFechaRegistro());
    }

    @Test
    void noDebeAceptarNumeroDeTurnoInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TurnoBancario(
                        0, "Carlos", "12345", TipoServicio.CAJA
                )
        );
    }

    @Test
    void noDebeAceptarNombreVacio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TurnoBancario(
                        1, " ", "12345", TipoServicio.CAJA
                )
        );
    }

    @Test
    void noDebeAceptarDocumentoVacio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TurnoBancario(
                        1, "Carlos", "", TipoServicio.CAJA
                )
        );
    }

    @Test
    void debePermitirModificarNombre() {
        TurnoBancario turno = crearTurno();

        turno.setNombreCliente("Ana");

        assertEquals("Ana", turno.getNombreCliente());
    }

    @Test
    void debePermitirModificarDocumento() {
        TurnoBancario turno = crearTurno();

        turno.setDocumento("99999");

        assertEquals("99999", turno.getDocumento());
    }

    @Test
    void debePermitirModificarEstado() {
        TurnoBancario turno = crearTurno();

        turno.setEstado(EstadoTurno.ATENDIDO);

        assertEquals(EstadoTurno.ATENDIDO, turno.getEstado());
    }

    @Test
    void dosTurnosConElMismoNumeroDebenSerIguales() {
        TurnoBancario primero = crearTurno();

        TurnoBancario segundo = new TurnoBancario(
                1, "Ana", "99999", TipoServicio.CREDITO
        );

        assertEquals(primero, segundo);
        assertEquals(primero.hashCode(), segundo.hashCode());
    }

    @Test
    void dosTurnosConNumeroDiferenteNoDebenSerIguales() {
        TurnoBancario primero = crearTurno();

        TurnoBancario segundo = new TurnoBancario(
                2, "Ana", "99999", TipoServicio.CREDITO
        );

        assertNotEquals(primero, segundo);
    }

    @Test
    void debeGenerarRepresentacionEnTexto() {
        TurnoBancario turno = crearTurno();

        assertTrue(turno.toString().contains("Carlos"));
        assertTrue(turno.toString().contains("CAJA"));
    }

    private TurnoBancario crearTurno() {
        return new TurnoBancario(
                1, "Carlos", "12345", TipoServicio.CAJA
        );
    }
}