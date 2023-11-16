package br.com.portifolio.model;

import br.com.portifolio.model.enums.ClassificacaoRiscoEnum;
import br.com.portifolio.model.enums.StatusProjetoEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45)
    private StatusProjetoEnum status;

    @Column(name = "orcamento")
    private Float orcamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco", length = 45)
    private ClassificacaoRiscoEnum risco;

    @Column(name = "idgerente", nullable = false)
    private Long idGerente;

}


