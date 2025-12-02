package com.garagem.service;

import com.garagem.dto.ProprietarioDTO;
import com.garagem.model.Proprietario;
import com.garagem.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço para regras de negócio relacionadas a Proprietario.
 */
@Service
public class ProprietarioService {

    private final ProprietarioRepository repo;

    public ProprietarioService(ProprietarioRepository repo) {
        this.repo = repo;
    }

    // Cria um novo proprietário a partir do DTO
    public ProprietarioDTO create(ProprietarioDTO dto) {
        Proprietario p = new Proprietario(dto.getNome(), dto.getTelefone());
        p = repo.save(p);
        dto.setId(p.getId());
        return dto;
    }

    public List<ProprietarioDTO> findAll() {
        return repo.findAll().stream()
                .map(p -> new ProprietarioDTO(p.getId(), p.getNome(), p.getTelefone()))
                .collect(Collectors.toList());
    }

    public Optional<ProprietarioDTO> findById(Long id) {
        return repo.findById(id).map(p -> new ProprietarioDTO(p.getId(), p.getNome(), p.getTelefone()));
    }

    public Optional<ProprietarioDTO> update(Long id, ProprietarioDTO dto) {
        return repo.findById(id).map(existing -> {
            existing.setNome(dto.getNome());
            existing.setTelefone(dto.getTelefone());
            repo.save(existing);
            dto.setId(existing.getId());
            return dto;
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
