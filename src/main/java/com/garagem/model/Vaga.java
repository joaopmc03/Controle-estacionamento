package com.garagem.model;

import jakarta.persistence.*;

/**
 * Representa uma vaga de estacionamento.
 */
@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número identificador da vaga (ex: 1, 2A)
    @Column(nullable = false, unique = true)
    private String numero;

    // Tipo de vaga (ex: COMUM, PCD, VIP)
    private String tipo;

    public Vaga() {}

    public Vaga(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
