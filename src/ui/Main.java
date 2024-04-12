package ui;

import model.Juego;
import model.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese la cantidad de jugadores: ");
            int numJugadores = scanner.nextInt();
            scanner.nextLine();
            List<Jugador> jugadores = new ArrayList<>();
            for (int i = 1; i <= numJugadores; i++) {
                System.out.print("Ingrese el nombre del jugador " + i + ": ");
                String nombre = scanner.nextLine();
                jugadores.add(new Jugador(nombre));
            }
            Juego juego = new Juego(jugadores);
            juego.iniciarJuego();
        } finally {
            // Cerrar el Scanner
            scanner.close();
        }
    }
}