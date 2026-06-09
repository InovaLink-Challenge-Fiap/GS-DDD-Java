package br.com.fiap.space.domain;

public class CargaExcedidaException extends RuntimeException {
    public CargaExcedidaException(String mensagem) {
        super(mensagem);
    }
}