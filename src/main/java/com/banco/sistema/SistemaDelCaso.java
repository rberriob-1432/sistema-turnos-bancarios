package main.java.com.banco.sistema;

import main.java.com.banco.estructuras.Cola;
import main.java.com.banco.estructuras.Lista;
import main.java.com.banco.estructuras.Pila;
import main.java.com.banco.modelo.TipoServicio;
import main.java.com.banco.modelo.TurnoBancario;

public class SistemaDelCaso {

    private final Lista<TurnoBancario> listaGeneral = new Lista<>();
    private final Cola<TurnoBancario> pendientes = new Cola<>();
    private final Pila<TurnoBancario> historial = new Pila<>();

    private final GestorTurnos gestorTurnos;
    private final GestorProcesamiento gestorProcesamiento;
    private final GestorCancelaciones gestorCancelaciones;
    private final GeneradorReportes generadorReportes;

    public SistemaDelCaso() {
        gestorTurnos = new GestorTurnos(listaGeneral, pendientes);

        gestorProcesamiento = new GestorProcesamiento(
                pendientes,
                historial
        );

        gestorCancelaciones = new GestorCancelaciones(pendientes);

        generadorReportes = new GeneradorReportes(listaGeneral);
    }

    public TurnoBancario registrar(
            String nombre,
            String documento,
            TipoServicio servicio) {

        return gestorTurnos.registrar(nombre, documento, servicio);
    }

    public TurnoBancario buscar(int numero) {
        return gestorTurnos.buscar(numero);
    }

    public TurnoBancario procesar() {
        return gestorProcesamiento.procesarSiguiente();
    }

    public TurnoBancario deshacer() {
        return gestorProcesamiento.deshacerUltimo();
    }

    public TurnoBancario cancelar(int numero) {
        return gestorCancelaciones.cancelar(numero);
    }

    public void mostrarTodos() {
        gestorTurnos.mostrarTodos();
    }

    public void mostrarPendientes() {
        gestorProcesamiento.mostrarPendientes();
    }

    public void mostrarHistorial() {
        gestorProcesamiento.mostrarHistorial();
    }

    public void mostrarReporte() {
        generadorReportes.mostrarReporte();
    }

    public int cantidadRegistrados() {
        return gestorTurnos.cantidadRegistrados();
    }

    public int cantidadPendientes() {
        return gestorProcesamiento.cantidadPendientes();
    }

    public int cantidadAtendidos() {
        return gestorProcesamiento.cantidadAtendidos();
    }
}