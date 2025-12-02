package com.garagem.model;

import jakarta.persistence.*;

/**
 * Representa um veículo cadastrado na garagem.
 */
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    private String modelo;

    private String cor;

    // Relacionamento ManyToOne com Proprietario
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    public Veiculo() {}

    public Veiculo(String placa, String modelo, String cor, Proprietario proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.proprietario = proprietario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
