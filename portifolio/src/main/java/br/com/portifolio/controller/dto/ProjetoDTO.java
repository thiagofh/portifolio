package br.com.portifolio.controller.dto;

import br.com.portifolio.model.enums.ClassificacaoRiscoEnum;
import br.com.portifolio.model.enums.StatusProjetoEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjetoDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataPrevisaoFim;

    private LocalDate dataFim;

    private String descricao;

    private StatusProjetoEnum status;

    private Float orcamento;

    private ClassificacaoRiscoEnum risco;

    private Long idGerente;

    public ProjetoDTO() {
    }

    public ProjetoDTO(Long id, String nome, LocalDate dataInicio, LocalDate dataPrevisaoFim, LocalDate dataFim,
                      String descricao, StatusProjetoEnum status, Float orcamento, ClassificacaoRiscoEnum risco, Long idGerente) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataPrevisaoFim = dataPrevisaoFim;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
        this.orcamento = orcamento;
        this.risco = risco;
        this.idGerente = idGerente;
    }

}
