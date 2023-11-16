package br.com.portifolio.model.enums;

public enum StatusProjetoEnum{
    EM_ANALISE("Em Analise"),
    ANALISE_REALIZADA("Analise Realizada"),
    ANALISE_APROVADA("Analise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusProjetoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


