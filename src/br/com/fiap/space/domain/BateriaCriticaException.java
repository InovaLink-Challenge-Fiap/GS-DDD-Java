package br.com.fiap.space.domain;

public class BateriaCriticaException extends RuntimeException {
    public BateriaCriticaException(String mensagem) {
        super(mensagem);
    }
}