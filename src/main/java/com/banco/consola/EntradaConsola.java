package main.java.com.banco.consola;
import java.util.Scanner;

public class EntradaConsola {

    private final Scanner scanner;

    public EntradaConsola(Scanner scanner) {
        this.scanner = scanner;
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    public String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isBlank()) {
                return texto;
            }

            System.out.println("El campo no puede estar vacío.");
        }
    }
}