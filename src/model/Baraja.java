package model;

import java.util.*;

public class Baraja {
    private Stack<Carta> mazo;
    private Stack<Carta> mazoDescarte;

    public Baraja() {

        this.mazo = new Stack<>();
        this.mazoDescarte = new Stack<>();
        String[] colors = {"Rojo", "Verde", "Azul", "Amarillo"};
        String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] especiales = {"Roba 2", "Revertir", "Salto"};
        int cartas_duplicadas = 2;
        int cambios_de_color = 8;
        int roba_4 = 4;

        for (String color : colors) {
            for (String num : numeros) {
                if (num.equals("0")) {
                    this.mazo.push(new Carta("numero", color, num));
                } else {
                    for (int i = 0; i < cartas_duplicadas; i++) {
                        this.mazo.push(new Carta("numero", color, num));
                    }
                }
            }
            for (String esp : especiales) {
                for (int i = 0; i < cartas_duplicadas; i++) {
                    this.mazo.push(new Carta(esp, color, null));
                }
            }
        }

        for (int i = 0; i < cambios_de_color; i++) {
            this.mazo.push(new Carta("Cambio de color", null, null));
        }

        for (int i = 0; i < roba_4; i++) {
            this.mazo.push(new Carta("Roba 4", null, null));
        }

        Collections.shuffle(this.mazo);    
    }

    public Carta sacar_carta(boolean carta_inicial) {

        if (this.mazo.isEmpty() && this.mazoDescarte.isEmpty()) {
            System.out.println("La baraja está vacía.");
            return null;
        } else if (this.mazo.isEmpty()) {
            while (!this.mazoDescarte.isEmpty()) {
                this.mazo.push(this.mazoDescarte.pop());
            }
            Collections.shuffle(this.mazo);
        }
        Carta carta = this.mazo.pop();
        if (carta_inicial ) {
            while (true) {
                System.out.println("en el ciclo");
                if (carta.getTipo().equals("numero")) {
                    this.mazoDescarte.push(carta);
                    return carta;
                } else {
                    Collections.shuffle(this.mazo);
                }
            }
            
        }

        return carta;    
    }

    public void descartarCarta(Carta carta) {
        this.mazoDescarte.push(carta);
    }

    public Carta cartaSuperiorDescarte() {

        if (this.mazoDescarte.isEmpty()) {
            return null;
        }
        return this.mazoDescarte.peek();    
    }
}
