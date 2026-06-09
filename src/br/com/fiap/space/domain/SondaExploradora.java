package br.com.fiap.space.domain;

public class SondaExploradora extends Sonda {
    private double alcanceSensor;

    public SondaExploradora(String idSonda, double capacidadeBateria, double alcanceSensor) {
        super(idSonda, capacidadeBateria);
        if (alcanceSensor <= 0) {
            throw new IllegalArgumentException("O valor de alcance do sensor deve ser superior a zero.");
        }
        this.alcanceSensor = alcanceSensor;
    }

    @Override
    public void realizarAcaoLocal() {
        System.out.println("Sonda Exploradora " + getIdSonda() + " escaneando o ambiente em um raio de " + alcanceSensor + " metros...");
        System.out.println("Imagens e dados geologicos locais salvos no buffer.");
    }
}