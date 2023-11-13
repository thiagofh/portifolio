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
    @JoinColumn(name = "idpessoa", referencedColumnName = "id", insertable = false, updatable = false)
    private Pessoa pessoa;

    public Membro(){
        this.id = new MembroId();
    }

}