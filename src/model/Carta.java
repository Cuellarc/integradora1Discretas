package model;

import java.util.*;

public class Carta {
    private String tipo;
    private String color;
    private String numero;

    public Carta(String tipo, String color, String numero) {
        this.tipo = tipo;
        this.color = color;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        if (this.tipo.equals("numero")) {
            return this.color + " " + this.numero;
        } else {
            return this.color + " " + this.tipo;
        }
    }
}
