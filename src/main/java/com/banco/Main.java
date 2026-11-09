package main.java.com.banco;
import main.java.com.banco.consola.EntradaConsola;
import main.java.com.banco.consola.Menu;
import main.java.com.banco.modelo.TipoServicio;
import main.java.com.banco.modelo.TurnoBancario;
import main.java.com.banco.sistema.SistemaDelCaso;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final EntradaConsola entrada =
            new EntradaConsola(SCANNER);

    private static final Menu menu = new Menu();

    private static final SistemaDelCaso sistema =
            new SistemaDelCaso();

    public static void main(String[] args) {
        int opcion;

        do {
            menu.mostrar();
            opcion = entrada.leerEntero("Seleccione una opción: ");

            ejecutarOpcion(opcion);

        } while (opcion != 10);

        SCANNER.close();
        System.out.println("Sistema finalizado.");
    }

    private static void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarTurno();
                case 2 -> sistema.mostrarTodos();
                case 3 -> sistema.mostrarPendientes();
                case 4 -> procesarTurno();
                case 5 -> sistema.mostrarHistorial();
                case 6 -> buscarTurno();
                case 7 -> cancelarTurno();
                case 8 -> deshacerProcesamiento();
                case 9 -> sistema.mostrarReporte();
                case 10 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Operación no realizada: " + e.getMessage());
        }
    }

    private static void registrarTurno() {
        System.out.println("\n===== REGISTRAR TURNO =====");

        String nombre = entrada.leerTexto("Nombre del cliente: ");
        String documento = entrada.leerTexto("Documento: ");

        TipoServicio servicio = seleccionarServicio();

        TurnoBancario turno = sistema.registrar(
                nombre,
                documento,
                servicio
        );

        System.out.println("Turno registrado correctamente:");
        System.out.println(turno);
    }

    private static TipoServicio seleccionarServicio() {
        TipoServicio[] servicios = TipoServicio.values();

        System.out.println("\nTipos de servicio:");

        for (int i = 0; i < servicios.length; i++) {
            System.out.println((i + 1) + ". " + servicios[i]);
        }

        while (true) {
            int opcion = entrada.leerEntero("Seleccione el servicio: ");

            if (opcion >= 1 && opcion <= servicios.length) {
                return servicios[opcion - 1];
            }

            System.out.println("Servicio inválido.");
        }
    }

    private static void procesarTurno() {
        TurnoBancario turno = sistema.procesar();

        System.out.println("Turno procesado:");
        System.out.println(turno);
    }

    private static void buscarTurno() {
        int numero = entrada.leerEntero("Número del turno: ");

        TurnoBancario turno = sistema.buscar(numero);

        if (turno == null) {
            System.out.println("Turno no encontrado.");
        } else {
            System.out.println(turno);
        }
    }

    private static void cancelarTurno() {
        int numero = entrada.leerEntero(
                "Número del turno que desea cancelar: "
        );

        TurnoBancario turno = sistema.cancelar(numero);

        System.out.println("Turno cancelado:");
        System.out.println(turno);
    }

    private static void deshacerProcesamiento() {
        TurnoBancario turno = sistema.deshacer();

        System.out.println("Se deshizo el último procesamiento:");
        System.out.println(turno);
    }
}