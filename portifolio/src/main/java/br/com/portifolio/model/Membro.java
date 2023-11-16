package br.com.portifolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "membros")
@Data
public class Membro {

    @EmbeddedId
    private MembroId id;

    public Membro(){
        this.id = new MembroId();
    }
}