package com.garagem.service;

import com.garagem.dto.MovimentacaoDTO;
import com.garagem.model.Movimentacao;
import com.garagem.model.Vaga;
import com.garagem.model.Veiculo;
import com.garagem.repository.MovimentacaoRepository;
import com.garagem.repository.VagaRepository;
import com.garagem.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para movimentações (entrada/saída). Inclui cálculo simples de valor.
 *
 * Implementado por João Costa — contém a regra de negócio que calcula o
 * valor da permanência em R$5,00 por hora (arredondamento para cima).
 */
@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repo;
    private final VeiculoRepository veiculoRepo;
    private final VagaRepository vagaRepo;

    public MovimentacaoService(MovimentacaoRepository repo, VeiculoRepository veiculoRepo, VagaRepository vagaRepo) {
        this.repo = repo;
        this.veiculoRepo = veiculoRepo;
        this.vagaRepo = vagaRepo;
    }

    /**
     * Calcula o valor da movimentação.
     * Regra: R$5,00 por hora. O tempo é arredondado para cima (qualquer fração conta como hora completa).
     * Retorna R$0,00 se entrada ou saída forem nulos.
     *
     * Este método é intencionalmente simples para facilitar a demonstração.
     */
    private BigDecimal calcularValor(java.time.LocalDateTime entrada, java.time.LocalDateTime saida) {
        if (entrada == null || saida == null) return BigDecimal.ZERO;
        long segundos = Duration.between(entrada, saida).getSeconds();
        long horas = Math.max(1, (segundos + 3599) / 3600); // arredonda para cima
        return BigDecimal.valueOf(horas * 5L);
    }

    public MovimentacaoDTO create(MovimentacaoDTO dto) {
        Movimentacao m = new Movimentacao();
        Veiculo v = null;
        Vaga vg = null;
        if (dto.getVeiculoId() != null) v = veiculoRepo.findById(dto.getVeiculoId()).orElse(null);
        if (dto.getVagaId() != null) vg = vagaRepo.findById(dto.getVagaId()).orElse(null);
        m.setVeiculo(v);
        m.setVaga(vg);
        m.setEntrada(dto.getEntrada());
        m.setSaida(dto.getSaida());
        m.setValor(calcularValor(dto.getEntrada(), dto.getSaida()));
        m = repo.save(m);
        dto.setId(m.getId());
        dto.setValor(m.getValor());
        return dto;
    }

    public List<MovimentacaoDTO> findAll() {
        return repo.findAll().stream().map(m -> {
            MovimentacaoDTO dto = new MovimentacaoDTO();
            dto.setId(m.getId());
            dto.setVeiculoId(m.getVeiculo() != null ? m.getVeiculo().getId() : null);
            dto.setVagaId(m.getVaga() != null ? m.getVaga().getId() : null);
            dto.setEntrada(m.getEntrada());
            dto.setSaida(m.getSaida());
            dto.setValor(m.getValor());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<MovimentacaoDTO> findById(Long id) {
        return repo.findById(id).map(m -> {
            MovimentacaoDTO dto = new MovimentacaoDTO();
            dto.setId(m.getId());
            dto.setVeiculoId(m.getVeiculo() != null ? m.getVeiculo().getId() : null);
            dto.setVagaId(m.getVaga() != null ? m.getVaga().getId() : null);
            dto.setEntrada(m.getEntrada());
            dto.setSaida(m.getSaida());
            dto.setValor(m.getValor());
            return dto;
        });
    }

    public Optional<MovimentacaoDTO> update(Long id, MovimentacaoDTO dto) {
        return repo.findById(id).map(existing -> {
            if (dto.getVeiculoId() != null) existing.setVeiculo(veiculoRepo.findById(dto.getVeiculoId()).orElse(null));
            if (dto.getVagaId() != null) existing.setVaga(vagaRepo.findById(dto.getVagaId()).orElse(null));
            existing.setEntrada(dto.getEntrada());
            existing.setSaida(dto.getSaida());
            existing.setValor(calcularValor(existing.getEntrada(), existing.getSaida()));
            repo.save(existing);
            dto.setId(existing.getId());
            dto.setValor(existing.getValor());
            return dto;
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
