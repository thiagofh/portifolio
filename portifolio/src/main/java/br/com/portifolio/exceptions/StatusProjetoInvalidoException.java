package br.com.portifolio.exceptions;

import br.com.portifolio.model.enums.StatusProjetoEnum;

public class StatusProjetoInvalidoException extends RuntimeException {

    public StatusProjetoInvalidoException(StatusProjetoEnum status) {
        super("Não é possível excluir um projeto com o status: " + status);
    }
}
