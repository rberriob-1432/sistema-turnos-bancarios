package main.java.com.banco.sistema;


import main.java.com.banco.estructuras.Lista;
import main.java.com.banco.modelo.TurnoBancario;

import static main.java.com.banco.modelo.EstadoTurno.*;

public class GeneradorReportes {

    private final Lista<TurnoBancario> turnos;

    public GeneradorReportes(Lista<TurnoBancario> turnos) {
        this.turnos = turnos;
    }

    public void mostrarReporte() {
        int pendientes = 0;
        int atendidos = 0;
        int cancelados = 0;

        for (int i = 0; i < turnos.cuentaElementos(); i++) {
            TurnoBancario turno = turnos.buscarDato(i);

            switch (turno.getEstado()) {
                case PENDIENTE -> pendientes++;
                case ATENDIDO -> atendidos++;
                case CANCELADO -> cancelados++;
            }
        }

        System.out.println("\n===== REPORTE GENERAL =====");
        System.out.println("Total registrados: " + turnos.cuentaElementos());
        System.out.println("Pendientes: " + pendientes);
        System.out.println("Atendidos: " + atendidos);
        System.out.println("Cancelados: " + cancelados);
    }
}