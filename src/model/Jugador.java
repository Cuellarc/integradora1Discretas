package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    String nombre;
    List<Carta> cartas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        cartas.add(carta);
    }

    public boolean jugarCarta(Carta carta, List<Carta> mesa, String color_actual) {
        if (carta.tipo.equals("numero")) {
            if (carta.color.equals(color_actual) || carta.valor.equals(mesa.get(mesa.size() - 1).valor)) {
                mesa.add(carta);
                cartas.remove(carta);
                return true;
            }
        } else if (carta.tipo.equals("Cambio de color") || carta.tipo.equals("Roba 4")) {
            mesa.add(carta);
            cartas.remove(carta);
            return true;
        } else if (carta.tipo.equals("Roba 2") || carta.tipo.equals("Revertir") || carta.tipo.equals("Salto")) {
            if (carta.color.equals(color_actual) || carta.tipo.equals(mesa.get(mesa.size() - 1).tipo)) {
                mesa.add(carta);
                cartas.remove(carta);
                return true;
            }
        }
        System.out.println("No puedes jugar esa carta.");
        return false;
    }

    public boolean robarCarta(Baraja baraja) {
        Carta carta = baraja.sacarCarta(false);
        if (carta != null) {
            cartas.add(carta);
            return true;
        } else {
            System.out.println("No quedan cartas en la baraja.");
            return false;
        }
    }
}
