package br.com.fiap.space.domain;

public final class NivelEnergia {
    private final double capacidadeAtual;
    private final double capacidadeMaxima;

    public NivelEnergia(double capacidadeAtual, double capacidadeMaxima) {
        if (capacidadeAtual < 0) {
            throw new RuntimeException("Alerta: Bateria Critica!");
        }
        if (capacidadeAtual > capacidadeMaxima) {
            this.capacidadeAtual = capacidadeMaxima;
        } else {
            this.capacidadeAtual = capacidadeAtual;
        }
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public NivelEnergia consumir(double quantidade) {
        if (this.capacidadeAtual - quantidade < 0) {
            throw new RuntimeException("Alerta: Bateria Critica!");
        }
        return new NivelEnergia(this.capacidadeAtual - quantidade, this.capacidadeMaxima);
    }

    public double getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}