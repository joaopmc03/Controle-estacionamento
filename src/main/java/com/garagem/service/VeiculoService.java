package com.garagem.service;

import com.garagem.dto.VeiculoDTO;
import com.garagem.model.Proprietario;
import com.garagem.model.Veiculo;
import com.garagem.repository.ProprietarioRepository;
import com.garagem.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para operações de Veiculo.
 */
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepo;
    private final ProprietarioRepository proprietarioRepo;

    public VeiculoService(VeiculoRepository veiculoRepo, ProprietarioRepository proprietarioRepo) {
        this.veiculoRepo = veiculoRepo;
        this.proprietarioRepo = proprietarioRepo;
    }

    public VeiculoDTO create(VeiculoDTO dto) {
        Proprietario owner = null;
        if (dto.getProprietarioId() != null) {
            owner = proprietarioRepo.findById(dto.getProprietarioId()).orElse(null);
        }
        Veiculo v = new Veiculo(dto.getPlaca(), dto.getModelo(), dto.getCor(), owner);
        v = veiculoRepo.save(v);
        dto.setId(v.getId());
        return dto;
    }

    public List<VeiculoDTO> findAll() {
        return veiculoRepo.findAll().stream().map(v -> {
            VeiculoDTO dto = new VeiculoDTO();
            dto.setId(v.getId());
            dto.setPlaca(v.getPlaca());
            dto.setModelo(v.getModelo());
            dto.setCor(v.getCor());
            dto.setProprietarioId(v.getProprietario() != null ? v.getProprietario().getId() : null);
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<VeiculoDTO> findById(Long id) {
        return veiculoRepo.findById(id).map(v -> {
            VeiculoDTO dto = new VeiculoDTO();
            dto.setId(v.getId());
            dto.setPlaca(v.getPlaca());
            dto.setModelo(v.getModelo());
            dto.setCor(v.getCor());
            dto.setProprietarioId(v.getProprietario() != null ? v.getProprietario().getId() : null);
            return dto;
        });
    }

    public Optional<VeiculoDTO> update(Long id, VeiculoDTO dto) {
        return veiculoRepo.findById(id).map(existing -> {
            existing.setPlaca(dto.getPlaca());
            existing.setModelo(dto.getModelo());
            existing.setCor(dto.getCor());
            if (dto.getProprietarioId() != null) {
                Proprietario owner = proprietarioRepo.findById(dto.getProprietarioId()).orElse(null);
                existing.setProprietario(owner);
            }
            veiculoRepo.save(existing);
            dto.setId(existing.getId());
            return dto;
        });
    }

    public void delete(Long id) {
        veiculoRepo.deleteById(id);
    }
}
