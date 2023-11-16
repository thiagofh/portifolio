package br.com.portifolio.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MembroIdDTO implements Serializable {

    private Long idProjeto;

    private Long idPessoa;

    public MembroIdDTO(Long idProjeto, Long idPessoa) {
        this.idProjeto = idProjeto;
        this.idPessoa = idPessoa;
    }

}