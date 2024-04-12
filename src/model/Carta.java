package model;

public class Carta {
    String tipo;
    String color;
    String valor;

    public Carta(String tipo, String color, String valor) {
        this.tipo = tipo;
        this.color = color;
        this.valor = valor;
    }

    public String toString() {
        if (tipo.equals("Número")) {
            return color + " " + valor;
        } else {
            return color + " " + tipo;
        }
    }
}
