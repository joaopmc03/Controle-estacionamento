package com.garagem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para Movimentacao. Contém ids para vincular veiculo e vaga.
 */
public class MovimentacaoDTO {

    private Long id;
    private Long veiculoId;
    private Long vagaId;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private BigDecimal valor;

    public MovimentacaoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getVagaId() {
        return vagaId;
    }

    public void setVagaId(Long vagaId) {
        this.vagaId = vagaId;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
