package model;

import java.util.*;

public class Juego {
    private Jugador[] jugadores;
    private Baraja baraja;
    private Queue<Jugador> ordenJugadores;
    private String color_actual;
    private int sentido;

    public Juego(Jugador[] jugadores) {
        this.jugadores = jugadores;
        this.baraja = new Baraja();
        this.ordenJugadores = new LinkedList<>(Arrays.asList(jugadores));
        this.color_actual = "";
        this.sentido = 1;    }

    public void repartirCartas() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                jugador.recibir_carta(baraja.sacar_carta(false));
            }
        }    
    }

    public void iniciarJuego() {
        repartirCartas();
        Carta carta_inicial = baraja.sacar_carta(true);
        color_actual = carta_inicial.getColor();
        jugar();    }

    public void cambiarColor(String nuevo_color) {
        color_actual = nuevo_color.substring(0, 1).toUpperCase() + nuevo_color.substring(1).toLowerCase();
    }

    public ArrayList<Carta> robarCarta(int range_number) {
        ArrayList<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < range_number; i++) {
            cartas.add(baraja.sacar_carta(false));
        }
        return cartas;    
    }

    public void jugar() {

        
        while (true) {
            Jugador jugador_actual = ordenJugadores.peek();
            boolean skip_turn = false;
            boolean skip_color = false;
            System.out.println("\nTurno de " + jugador_actual.getNombre());
            if (baraja.cartaSuperiorDescarte().getTipo().equals("numero") && baraja.cartaSuperiorDescarte().getNumero() != null && baraja.cartaSuperiorDescarte().getColor() != null) {
                System.out.println("Carta en la mesa: " + baraja.cartaSuperiorDescarte().getNumero() + " " + baraja.cartaSuperiorDescarte().getColor());
            } else if (!baraja.cartaSuperiorDescarte().getTipo().equals("numero") && baraja.cartaSuperiorDescarte().getColor() != null) {
                System.out.println("Carta en la mesa: " + baraja.cartaSuperiorDescarte().getTipo() + " " + baraja.cartaSuperiorDescarte().getColor());
            } else if (!baraja.cartaSuperiorDescarte().getTipo().equals("numero") && baraja.cartaSuperiorDescarte().getColor() == null) {
                System.out.println("Carta en la mesa: " + baraja.cartaSuperiorDescarte().getTipo());
            }

            System.out.println("Color actual: " + color_actual);
            System.out.print("Tus cartas: [");
            for (int i = 0; i < jugador_actual.getMano().size(); i++) {
                Carta carta = jugador_actual.getMano().toArray(new Carta[0])[i];
                if (carta.getTipo().equals("numero")) {
                    System.out.print("'" + carta.getNumero() + " " + carta.getColor() + "'");
                } else {
                    if (carta.getColor() != null) {
                        System.out.print("'" + carta.getTipo() + " " + carta.getColor() + "'");
                    } else {
                        System.out.print("'" + carta.getTipo() + "'");
                    }
                }
                if (i != jugador_actual.getMano().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Selecciona una carta para jugar (número de carta): ");
            String carta_jugada = scanner.nextLine();
            if (carta_jugada.matches("\\d+") && Integer.parseInt(carta_jugada) <= jugador_actual.getMano().size()) {
                Carta carta = jugador_actual.getMano().toArray(new Carta[0])[Integer.parseInt(carta_jugada) - 1];
                if (jugador_actual.jugar_carta(carta, baraja, color_actual) != null) {
                    if (carta.getTipo().equals("Cambio de color") || carta.getTipo().equals("Roba 4")) {
                        skip_color = true;
                        System.out.print("Selecciona un nuevo color: ");
                        String nuevoColor = scanner.nextLine().toUpperCase();
                        cambiarColor(nuevoColor);
                    }

                    if (carta.getTipo().equals("Roba 4")) {
                        skip_turn = true;
                        ordenJugadores.add(ordenJugadores.poll());
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                    }

                    if (carta.getTipo().equals("Roba 2")) {
                        skip_turn = true;
                        ordenJugadores.add(ordenJugadores.poll());
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                        ordenJugadores.peek().recibir_carta(baraja.sacar_carta(false));
                    }

                    if (carta.getTipo().equals("Salto")) {
                        ordenJugadores.add(ordenJugadores.poll());
                    }

                    if (carta.getTipo().equals("Revertir")) {
                        sentido *= -1;
                        Queue<Jugador> colaAuxiliar = new LinkedList<>();
                        while (!ordenJugadores.isEmpty()) {
                            colaAuxiliar.add(ordenJugadores.poll());
                        }
                        while (!colaAuxiliar.isEmpty()) {
                            ordenJugadores.add(colaAuxiliar.poll());
                        }
                        // Si el sentido es negativo, rotamos la cola una vez más
                        if (sentido == -1) {
                            ordenJugadores.add(ordenJugadores.poll());
                        }
                    }
                    
                    
                    
                    if (!skip_turn) {
                        ordenJugadores.add(ordenJugadores.poll());
                    }
                    if (!skip_color) {
                        cambiarColor(carta.getColor());
                    }

                    if (jugador_actual.getMano().size() == 0) {
                        System.out.println(jugador_actual.getNombre() + " ha ganado!");
                        break;
                    }
                }
                
            } else if (carta_jugada.toLowerCase().equals("robar")) {
                jugador_actual.recibir_carta(baraja.sacar_carta(false));
                ordenJugadores.add(ordenJugadores.poll());
            } else {
                System.out.println("Entrada inválida. Inténtalo de nuevo.");
            }
        }    
    }
}
