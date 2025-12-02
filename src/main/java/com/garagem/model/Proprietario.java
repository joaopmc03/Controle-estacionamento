package com.garagem.model;

import jakarta.persistence.*;

/**
 * Representa um proprietário de veículo.
 */
@Entity
@Table(name = "proprietarios")
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do proprietário
    @Column(nullable = false)
    private String nome;

    // Telefone de contato
    private String telefone;

    public Proprietario() {}

    public Proprietario(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
