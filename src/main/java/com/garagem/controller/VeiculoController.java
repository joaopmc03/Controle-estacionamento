package com.garagem.controller;

import com.garagem.dto.VeiculoDTO;
import com.garagem.service.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para Veiculo.
 */
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

/*
 * REST controller removed to keep the project minimal (CLI only).
 */
package com.garagem.controller;

public class VeiculoController {
    // Stub - empty for CLI-only project
}
