package br.com.fiap.space.domain;

public abstract class Sonda {
    private String idSonda;
    private NivelEnergia bateria;
    private Coordenada posicaoAtual;

    public Sonda(String idSonda, double capacidadeBateria) {
        if (idSonda == null || idSonda.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da sonda nao pode ser nulo ou vazio.");
        }
        this.idSonda = idSonda;
        this.bateria = new NivelEnergia(capacidadeBateria, capacidadeBateria);
        this.posicaoAtual = new Coordenada(0, 0);
    }

    public final void executarRotinaAutonoma(Coordenada destino, Terreno terreno) {
        validarStatus();
        mover(destino, terreno);
        realizarAcaoLocal();
        enviarRelatorio();
    }

    private void validarStatus() {
        if (bateria.getCapacidadeAtual() <= 10) {
            throw new RuntimeException("Alerta: Bateria Critica! Status de diagnostico reprovado.");
        }
    }

    public void mover(Coordenada destino, Terreno terreno) {
        // Distancia calculada em metros na malha
        int distanciaMetros = Math.abs(destino.getEixoX() - posicaoAtual.getEixoX()) +
                Math.abs(destino.getEixoY() - posicaoAtual.getEixoY());

        // Reduzimos o consumo base para 0.1 por metro (andara 50 vezes mais longe!)
        double consumoBase = distanciaMetros * 0.1;
        double consumoFinal = consumoBase * terreno.getMultiplicadorConsumo();

        this.bateria = this.bateria.consumir(consumoFinal);
        this.posicaoAtual = destino;
        System.out.println("Sonda " + idSonda + " moveu-se " + distanciaMetros + " metros ate " + destino + " em terreno " + terreno.getTipoSolo());
    }

    public abstract void realizarAcaoLocal();

    private void enviarRelatorio() {
        System.out.println("Relatorio enviado ao Centro de Comando para " + idSonda + ". Bateria restante: " + bateria.getCapacidadeAtual());
    }

    public String getIdSonda() { return idSonda; }
    public NivelEnergia getBateria() { return bateria; }
    public Coordenada getPosicaoAtual() { return posicaoAtual; }

    protected void setBateria(NivelEnergia bateria) { this.bateria = bateria; }
}