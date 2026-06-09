package br.com.fiap.space.domain;

public class SondaMineradora extends Sonda implements Recarregavel {
    private CompartimentoCarga carga;

    public SondaMineradora(String idSonda, double capacidadeBateria, double volumeMaximoCarga) {
        super(idSonda, capacidadeBateria);
        this.carga = new CompartimentoCarga(0, volumeMaximoCarga);
    }

    @Override
    public void realizarAcaoLocal() {
        System.out.println("Sonda Mineradora " + getIdSonda() + " iniciando perfuracao...");
        this.carga = this.carga.adicionarCarga(20.0);
        System.out.println("Minerais extraidos com sucesso! Carga atual: " + carga.getVolumeOcupado() + "/" + carga.getVolumeMaximo());
    }

    @Override
    public void conectarBase() {
        System.out.println("Sonda Mineradora conectada a base. Carregando via inducao de solo...");
        setBateria(new NivelEnergia(getBateria().getCapacidadeMaxima(), getBateria().getCapacidadeMaxima()));
    }

    public CompartimentoCarga getCarga() { return carga; }
}