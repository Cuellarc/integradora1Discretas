package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego {
    List<Jugador> jugadores;
    Baraja baraja;
    List<Carta> mesa;
    String color_actual;
    int sentido;

    public Juego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.baraja = new Baraja();
        this.mesa = new ArrayList<>();
        this.color_actual = "";
        this.sentido = 1;
    }

    public void repartirCartas() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                jugador.recibirCarta(baraja.sacarCarta(false));
            }
        }
    }

    public void iniciarJuego() {
        repartirCartas();
        Carta cartaInicial = baraja.sacarCarta(true);
        mesa.add(cartaInicial);
        color_actual = cartaInicial.color;
        jugar();
    }

    public void cambiarColor(String nuevo_color) {
        color_actual = nuevo_color.substring(0, 1).toUpperCase() + nuevo_color.substring(1).toLowerCase();
    }

    public List<Carta> robar(int rangeNumber) {
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < rangeNumber; i++) {
            cartas.add(baraja.sacarCarta(false));
        }
        return cartas;
    }

    public void jugar() {
        int jugadorActualIdx = 0;

        while (true) {
            Jugador jugadorActual = jugadores.get(jugadorActualIdx);
            boolean skipTurn = false;
            boolean skipColor = false;
            System.out.println("\nTurno de " + jugadorActual.nombre);
            Carta cartaEnMesa = mesa.isEmpty() ? null : mesa.get(mesa.size() - 1);


            if (cartaEnMesa.tipo.equals("numero") && cartaEnMesa.valor != null && cartaEnMesa.color != null) {
                System.out.println("Carta en la mesa: " + cartaEnMesa.valor + " " + cartaEnMesa.color);
            } else if (!cartaEnMesa.tipo.equals("numero") && cartaEnMesa.color != null) {
                System.out.println("Carta en la mesa: " + cartaEnMesa.tipo + " " + cartaEnMesa.color);
            } else if (!cartaEnMesa.tipo.equals("numero") && cartaEnMesa.color == null) {
                System.out.println("Carta en la mesa: " + cartaEnMesa.tipo);
            }

            System.out.println("Color actual: " + color_actual);
            System.out.print("Tus cartas: [");
            for (int i = 0; i < jugadorActual.cartas.size(); i++) {
                Carta carta = jugadorActual.cartas.get(i);
                if (carta.tipo.equals("numero")) {
                    System.out.print("'" + carta.valor + " " + carta.color + "'");
                } else {
                    if (carta.color != null) {
                        System.out.print("'" + carta.tipo + " " + carta.color + "'");
                    } else {
                        System.out.print("'" + carta.tipo + "'");
                    }
                }
                if (i != jugadorActual.cartas.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Selecciona una carta para jugar (número de carta): ");
            String cartaJugadaInput = scanner.nextLine();
            if (cartaJugadaInput.matches("\\d+") && Integer.parseInt(cartaJugadaInput) <= jugadorActual.cartas.size()) {
                int cartaIndex = Integer.parseInt(cartaJugadaInput) - 1;
                Carta cartaJugada = jugadorActual.cartas.get(cartaIndex);
                if (jugadorActual.jugarCarta(cartaJugada, mesa, color_actual)) {
                    if (cartaJugada.tipo.equals("Cambio de color") || cartaJugada.tipo.equals("Roba 4")) {
                        skipColor = true;
                        System.out.print("Selecciona un nuevo color: ");
                        String nuevoColor = scanner.nextLine().toUpperCase();
                        cambiarColor(nuevoColor);
                    }

                    if (cartaJugada.tipo.equals("Roba 4")) {
                        jugadores.get((jugadorActualIdx + sentido) % jugadores.size()).cartas.addAll(robar(4));
                    }

                    if (cartaJugada.tipo.equals("Roba 2")) {
                        jugadores.get((jugadorActualIdx + sentido) % jugadores.size()).cartas.addAll(robar(2));
                    }

                    if (cartaJugada.tipo.equals("Salto")) {
                        skipTurn = true;
                        jugadorActualIdx = (jugadorActualIdx + (sentido * 2)) % jugadores.size();
                    }

                    if (cartaJugada.tipo.equals("Revertir")) {
                        sentido *= -1; // Cambia el sentido de avance de los jugadores
                    }

                    if (!skipTurn) {
                        jugadorActualIdx = (jugadorActualIdx + sentido) % jugadores.size();
                    }
                    if (!skipColor) {
                        cambiarColor(cartaJugada.color);
                    }

                    if (jugadorActual.cartas.isEmpty()) {
                        System.out.println(jugadorActual.nombre + " ha ganado!");
                        break;
                    }
                }
            } else if (cartaJugadaInput.equalsIgnoreCase("robar")) {
                if (jugadorActual.robarCarta(baraja)) {
                    System.out.println("Has robado una carta.");
                    jugadorActualIdx = (jugadorActualIdx + sentido) % jugadores.size();
                } else {
                    System.out.println("No quedan cartas en la baraja.");
                }
            } else {
                System.out.println("Entrada inválida. Inténtalo de nuevo.");
            }
        }
    }
}
