package br.com.portifolio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "funcionario")
    private Boolean funcionario;
}
