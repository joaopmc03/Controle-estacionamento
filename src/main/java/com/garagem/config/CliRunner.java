package com.garagem.config;

import com.garagem.service.MenuService;
import com.garagem.service.MovimentacaoService;
import com.garagem.service.ProprietarioService;
import com.garagem.service.VagaService;
import com.garagem.service.VeiculoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Configura e executa o menu CLI ao iniciar a aplicação.
 * Implementa CommandLineRunner do Spring para inicializar a interface do terminal.
 */
@Component
public class CliRunner implements CommandLineRunner {

    private final ProprietarioService proprietarioService;
    private final VeiculoService veiculoService;
    private final VagaService vagaService;
    private final MovimentacaoService movimentacaoService;

    public CliRunner(ProprietarioService proprietarioService,
                     VeiculoService veiculoService,
                     VagaService vagaService,
                     MovimentacaoService movimentacaoService) {
        this.proprietarioService = proprietarioService;
        this.veiculoService = veiculoService;
        this.vagaService = vagaService;
        this.movimentacaoService = movimentacaoService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cria a instância do menu e exibe-o
        MenuService menu = new MenuService(proprietarioService, veiculoService, vagaService, movimentacaoService);
        menu.exibirMenu();
    }
}
