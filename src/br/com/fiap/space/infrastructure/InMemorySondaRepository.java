package br.com.fiap.space.infrastructure;

import br.com.fiap.space.domain.Sonda;
import br.com.fiap.space.domain.SondaRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySondaRepository implements SondaRepository {
    private final Map<String, Sonda> db = new HashMap<>();

    @Override
    public void salvar(Sonda sonda) {
        if (db.containsKey(sonda.getIdSonda())) {
            throw new RuntimeException("Erro de Invariante: O ID " + sonda.getIdSonda() + " ja esta cadastrado na frota.");
        }
        db.put(sonda.getIdSonda(), sonda);
    }

    @Override
    public Sonda buscarPorId(String idSonda) {
        return db.get(idSonda);
    }

    @Override
    public List<Sonda> listarTodas() {
        return new ArrayList<>(db.values());
    }
}