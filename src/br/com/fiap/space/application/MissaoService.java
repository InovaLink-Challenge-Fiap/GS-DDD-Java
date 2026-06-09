package br.com.fiap.space.application;

import br.com.fiap.space.domain.Coordenada;
import br.com.fiap.space.domain.Sonda;
import br.com.fiap.space.domain.SondaRepository;
import br.com.fiap.space.domain.Terreno;
import java.util.List;

public class MissaoService {
    private final SondaRepository repository;

    public MissaoService(SondaRepository repository) {
        this.repository = repository;
    }

    public void registrarSonda(Sonda sonda) {
        repository.salvar(sonda);
    }

    public List<Sonda> obterFrota() {
        return repository.listarTodas();
    }

    public void dispararMissaoAutonoma(String idSonda, int x, int y, Terreno terreno) {
        if (idSonda == null || idSonda.trim().isEmpty()) {
            throw new IllegalArgumentException("ID informado e invalido.");
        }

        Sonda sonda = repository.buscarPorId(idSonda.trim().toUpperCase());

        if (sonda == null) {
            throw new RuntimeException("Comando abortado: A sonda com ID '" + idSonda.toUpperCase() + "' nao existe no sistema.");
        }

        Coordenada destino = new Coordenada(x, y);
        sonda.executarRotinaAutonoma(destino, terreno);
    }
}