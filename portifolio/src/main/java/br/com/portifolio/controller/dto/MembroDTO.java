package br.com.portifolio.controller.dto;

import br.com.portifolio.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MembroDTO {

    private ProjetoDTO projeto;
    private PessoaDTO pessoa;

    @JsonIgnore
    private MembroIdDTO membroId;

    public MembroDTO(ProjetoDTO projeto, PessoaDTO pessoa, MembroIdDTO membroId) {
        this.projeto = projeto;
        this.pessoa = pessoa;
        this.membroId = membroId;
    }
}
