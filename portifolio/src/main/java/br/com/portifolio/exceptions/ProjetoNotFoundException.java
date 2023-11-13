package br.com.portifolio.exceptions;

public class ProjetoNotFoundException extends RuntimeException {

    public ProjetoNotFoundException(Long id) {
        super("Projeto não encontrado com o ID: " + id);
    }
}
