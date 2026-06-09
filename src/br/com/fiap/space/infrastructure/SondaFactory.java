package br.com.fiap.space.infrastructure;

import br.com.fiap.space.domain.Sonda;
import br.com.fiap.space.domain.SondaExploradora;
import br.com.fiap.space.domain.SondaMineradora;

public class SondaFactory {
    public static Sonda criarSonda(String tipo, String idSonda) {
        if (idSonda == null || idSonda.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da sonda nao pode ser nulo ou vazio.");
        }

        String idFormatado = idSonda.trim().toUpperCase();

        if (tipo.equalsIgnoreCase("MINERACAO")) {
            return new SondaMineradora(idFormatado, 100.0, 50.0);
        } else if (tipo.equalsIgnoreCase("EXPLORACAO")) {
            return new SondaExploradora(idFormatado, 80.0, 150.0);
        }
        throw new IllegalArgumentException("Tipo de missao/sonda desconhecido.");
    }
}