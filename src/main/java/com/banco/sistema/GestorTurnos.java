package main.java.com.banco.sistema;

import main.java.com.banco.estructuras.Cola;
import main.java.com.banco.estructuras.Lista;
import main.java.com.banco.modelo.TipoServicio;
import main.java.com.banco.modelo.TurnoBancario;

public class GestorTurnos {

    private final Lista<TurnoBancario> listaGeneral;
    private final Cola<TurnoBancario> colaPendientes;
    private int siguienteNumero;

    public GestorTurnos(
            Lista<TurnoBancario> listaGeneral,
            Cola<TurnoBancario> colaPendientes) {

        this.listaGeneral = listaGeneral;
        this.colaPendientes = colaPendientes;
        this.siguienteNumero = 1;
    }

    public TurnoBancario registrar(
            String nombre,
            String documento,
            TipoServicio servicio) {

        TurnoBancario turno = new TurnoBancario(
                siguienteNumero++,
                nombre,
                documento,
                servicio
        );

        listaGeneral.agregar(turno);
        colaPendientes.encolar(turno);

        return turno;
    }

    public TurnoBancario buscar(int numero) {
        for (int i = 0; i < listaGeneral.cuentaElementos(); i++) {
            TurnoBancario turno = listaGeneral.buscarDato(i);

            if (turno.getNumeroTurno() == numero) {
                return turno;
            }
        }

        return null;
    }

    public void mostrarTodos() {
        if (listaGeneral.esVacia()) {
            System.out.println("No hay turnos registrados.");
            return;
        }

        listaGeneral.mostrarAdelante();
    }

    public int cantidadRegistrados() {
        return listaGeneral.cuentaElementos();
    }
}