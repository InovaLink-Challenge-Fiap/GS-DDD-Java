package br.com.fiap.space.infrastructure;

import br.com.fiap.space.domain.Sonda;
import br.com.fiap.space.domain.SondaRepository;

public class CentroDeComando {
    private static CentroDeComando instance;
    private final SondaRepository repository;

    private CentroDeComando() {
        this.repository = new InMemorySondaRepository();
    }

    public static synchronized CentroDeComando getInstance() {
        if (instance == null) {
            instance = new CentroDeComando();
        }
        return instance;
    }

    public SondaRepository getRepository() {
        return repository;
    }
}