package main.java.com.banco.sistema;

import main.java.com.banco.estructuras.Cola;
import main.java.com.banco.modelo.EstadoTurno;
import main.java.com.banco.modelo.TurnoBancario;

public class GestorCancelaciones {

    private final Cola<TurnoBancario> pendientes;

    public GestorCancelaciones(Cola<TurnoBancario> pendientes) {
        this.pendientes = pendientes;
    }

    public TurnoBancario cancelar(int numero) {
        Cola<TurnoBancario> auxiliar = new Cola<>();
        TurnoBancario cancelado = null;

        while (!pendientes.esVacia()) {
            TurnoBancario turno = pendientes.desencolar();

            if (turno.getNumeroTurno() == numero && cancelado == null) {
                cancelado = turno;
            } else {
                auxiliar.encolar(turno);
            }
        }

        while (!auxiliar.esVacia()) {
            pendientes.encolar(auxiliar.desencolar());
        }

        if (cancelado == null) {
            throw new IllegalArgumentException(
                    "No existe ese turno pendiente."
            );
        }

        cancelado.setEstado(EstadoTurno.CANCELADO);

        return cancelado;
    }
}