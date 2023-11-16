package br.com.portifolio.model.enums;

public enum ClassificacaoRiscoEnum {
    BAIXO("Baixo"),
    MEDIO("Medio"),
    ALTO("Alto");

    private final String descricao;

    ClassificacaoRiscoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

