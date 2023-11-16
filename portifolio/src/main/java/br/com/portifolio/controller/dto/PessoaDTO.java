package br.com.portifolio.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTO {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private Boolean funcionario;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, LocalDate dataNascimento, String cpf, Boolean funcionario) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
    }

}
