package br.com.fiap.space.domain;

public final class CompartimentoCarga {
    private final double volumeOcupado;
    private final double volumeMaximo;

    public CompartimentoCarga(double volumeOcupado, double volumeMaximo) {
        if (volumeOcupado < 0 || volumeOcupado > volumeMaximo) {
            throw new RuntimeException("Alerta: Carga Excedida!");
        }
        this.volumeOcupado = volumeOcupado;
        this.volumeMaximo = volumeMaximo;
    }

    public CompartimentoCarga adicionarCarga(double volume) {
        return new CompartimentoCarga(this.volumeOcupado + volume, this.volumeMaximo);
    }

    public double getVolumeOcupado() {
        return volumeOcupado;
    }

    public double getVolumeMaximo() {
        return volumeMaximo;
    }
}