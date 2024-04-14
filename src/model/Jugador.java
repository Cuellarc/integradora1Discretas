package model;

import java.util.*;

public class Jugador {
    private String nombre;
    private Queue<Carta> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new LinkedList<>();
    }

    public void recibir_carta(Carta carta) {
        this.mano.add(carta);
    }

    public Carta jugar_carta(Carta carta, Baraja baraja, String colorActual) {
        if (carta.getTipo().equals("numero")) {
            
            if (carta.getColor().equals(colorActual) || carta.getNumero().equals(baraja.cartaSuperiorDescarte().getNumero())) {
                this.mano.remove(carta);
                baraja.descartarCarta(carta);
                return carta;
            }
        } else if (carta.getTipo().equals("Cambio de color") || carta.getTipo().equals("Roba 4")) {
            this.mano.remove(carta);
            baraja.descartarCarta(carta);
            return carta;
        } else if (Arrays.asList("Roba 2", "Revertir", "Salto").contains(carta.getTipo())) {
            if (carta.getColor().equals(colorActual) || carta.getTipo().equals(baraja.cartaSuperiorDescarte().getTipo())) {
                this.mano.remove(carta);
                baraja.descartarCarta(carta);
                return carta;
            }
        }

        System.out.println("No puedes jugar esa carta.");
        return null;    }

    public Carta robar_carta(Baraja baraja) {
        Carta carta = baraja.sacar_carta(false);
        if (carta != null) {
            this.mano.add(carta);
            return carta;
        } else {
            System.out.println("No quedan cartas en la baraja.");
            return null;
        }    
    }

    public String getNombre() {
        return nombre;
    }

    public Queue<Carta> getMano() {
        return mano;
    }
}
