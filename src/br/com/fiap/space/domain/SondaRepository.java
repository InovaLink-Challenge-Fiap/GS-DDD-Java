package br.com.fiap.space.domain;

import java.util.List;

public interface SondaRepository {
    void salvar(Sonda sonda);
    Sonda buscarPorId(String idSonda);
    List<Sonda> listarTodas();
}