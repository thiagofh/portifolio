package br.com.portifolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "membros")
@Data
public class Membro {

    @EmbeddedId
    private MembroId id;

    @ManyToOne
    @MapsId("idProjeto")
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    public Membro(){
        this.id = new MembroId();
    }
}