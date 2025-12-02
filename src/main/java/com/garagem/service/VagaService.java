package com.garagem.service;

import com.garagem.dto.VagaDTO;
import com.garagem.model.Vaga;
import com.garagem.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para gerenciar vagas.
 */
@Service
public class VagaService {

    private final VagaRepository repo;

    public VagaService(VagaRepository repo) {
        this.repo = repo;
    }

    public VagaDTO create(VagaDTO dto) {
        Vaga v = new Vaga(dto.getNumero(), dto.getTipo());
        v = repo.save(v);
        dto.setId(v.getId());
        return dto;
    }

    public List<VagaDTO> findAll() {
        return repo.findAll().stream().map(v -> {
            VagaDTO dto = new VagaDTO();
            dto.setId(v.getId());
            dto.setNumero(v.getNumero());
            dto.setTipo(v.getTipo());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<VagaDTO> findById(Long id) {
        return repo.findById(id).map(v -> {
            VagaDTO dto = new VagaDTO();
            dto.setId(v.getId());
            dto.setNumero(v.getNumero());
            dto.setTipo(v.getTipo());
            return dto;
        });
    }

    public Optional<VagaDTO> update(Long id, VagaDTO dto) {
        return repo.findById(id).map(existing -> {
            existing.setNumero(dto.getNumero());
            existing.setTipo(dto.getTipo());
            repo.save(existing);
            dto.setId(existing.getId());
            return dto;
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
