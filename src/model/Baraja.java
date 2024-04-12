package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        String[] colors = {"Rojo", "Verde", "Azul", "Amarillo"};
        String[] valores = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] especiales = {"Roba 2", "Revertir", "Salto"};
        int cartas_duplicadas = 2;
        int cambios_de_color = 8;
        int roba_4 = 4;

        for (String color : colors) {
            for (String val : valores) {
                if (val.equals("0")) {
                    cartas.add(new Carta("numero", color, val));
                } else {
                    for (int i = 0; i < cartas_duplicadas; i++) {
                        cartas.add(new Carta("numero", color, val));
                    }
                }
            }
            for (String esp : especiales) {
                for (int i = 0; i < cartas_duplicadas; i++) {
                    cartas.add(new Carta(esp, color, null));
                }
            }
        }

        for (int i = 0; i < cambios_de_color; i++) {
            cartas.add(new Carta("Cambio de color", null, null));
        }

        for (int i = 0; i < roba_4; i++) {
            cartas.add(new Carta("Roba 4", null, null));
        }

        Collections.shuffle(cartas);
    }

    public Carta sacarCarta(boolean carta_inicial) {
        if (cartas.size() > 0 && carta_inicial) {
            while (true) {
                Carta cartaPop = cartas.remove(cartas.size() - 1);
                if (cartaPop.tipo.equals("numero")) {
                    return cartaPop;
                } else {
                    Collections.shuffle(cartas);
                }
            }
        } else if (cartas.size() > 0) {
            return cartas.remove(cartas.size() - 1);
        } else {
            System.out.println("La baraja está vacía.");
            return null;
        }
    }
}
