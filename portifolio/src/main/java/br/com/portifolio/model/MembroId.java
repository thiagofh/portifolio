package br.com.portifolio.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MembroId implements Serializable {

    private Long idProjeto;

    private Long idPessoa;

}