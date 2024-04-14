package ui;

import java.util.Scanner;
import model.Juego;
import model.Jugador;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de jugadores: ");
        int num_jugadores = scanner.nextInt();
        scanner.nextLine();
        Jugador[] jugadores = new Jugador[num_jugadores];
        for (int i = 0; i < num_jugadores; i++) {
            System.out.print("Ingrese el nombre del jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            jugadores[i] = new Jugador(nombre);
        }
        Juego juego = new Juego(jugadores);
        juego.iniciarJuego();
    }
}
