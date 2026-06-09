package br.com.fiap.space.domain;

public final class Coordenada {
    private final int eixoX;
    private final int eixoY;

    public Coordenada(int eixoX, int eixoY) {
        if (eixoX < 0 || eixoY < 0) {
            throw new IllegalArgumentException("Coordenadas nao podem ser negativas.");
        }
        this.eixoX = eixoX;
        this.eixoY = eixoY;
    }

    public int getEixoX() {
        return eixoX;
    }

    public int getEixoY() {
        return eixoY;
    }

    @Override
    public String toString() {
        return "(" + eixoX + ", " + eixoY + ")";
    }
}