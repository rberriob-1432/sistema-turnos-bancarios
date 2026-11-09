package main.java.com.banco.sistema;


import main.java.com.banco.estructuras.Cola;
import main.java.com.banco.estructuras.Pila;
import main.java.com.banco.modelo.EstadoTurno;
import main.java.com.banco.modelo.TurnoBancario;

public class GestorProcesamiento {

    private final Cola<TurnoBancario> pendientes;
    private final Pila<TurnoBancario> historial;

    public GestorProcesamiento(
            Cola<TurnoBancario> pendientes,
            Pila<TurnoBancario> historial) {

        this.pendientes = pendientes;
        this.historial = historial;
    }

    public TurnoBancario procesarSiguiente() {
        TurnoBancario turno = pendientes.desencolar();

        turno.setEstado(EstadoTurno.ATENDIDO);
        historial.apilar(turno);

        return turno;
    }

    public TurnoBancario deshacerUltimo() {
        TurnoBancario turno = historial.desapilar();

        turno.setEstado(EstadoTurno.PENDIENTE);
        pendientes.encolar(turno);

        return turno;
    }

    public void mostrarPendientes() {
        if (pendientes.esVacia()) {
            System.out.println("No hay turnos pendientes.");
            return;
        }

        pendientes.mostrar();
    }

    public void mostrarHistorial() {
        if (historial.esVacia()) {
            System.out.println("El historial está vacío.");
            return;
        }

        historial.mostrar();
    }

    public int cantidadPendientes() {
        return pendientes.tamanio();
    }

    public int cantidadAtendidos() {
        return historial.tamanio();
    }
}