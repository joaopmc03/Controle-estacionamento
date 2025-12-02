package com.garagem.service;

import com.garagem.dto.ProprietarioDTO;
import com.garagem.dto.VeiculoDTO;
import com.garagem.dto.VagaDTO;
import com.garagem.dto.MovimentacaoDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Serviço responsável pelo menu interativo do terminal.
 * Implementa a interface CLI do sistema de gerenciamento de garagem.
 *
 * Implementado por João Costa — apresentação e demonstração.
 * Use este serviço para acionar as operações de CRUD no console durante a
 * demonstração para a professora. As chamadas do menu encaminham para os
 * serviços de negócio (ProprietarioService, VeiculoService, VagaService,
 * MovimentacaoService).
 */
public class MenuService {

    private final ProprietarioService proprietarioService;
    private final VeiculoService veiculoService;
    private final VagaService vagaService;
    private final MovimentacaoService movimentacaoService;
    private final Scanner scanner;

    public MenuService(ProprietarioService proprietarioService,
                       VeiculoService veiculoService,
                       VagaService vagaService,
                       MovimentacaoService movimentacaoService) {
        this.proprietarioService = proprietarioService;
        this.veiculoService = veiculoService;
        this.vagaService = vagaService;
        this.movimentacaoService = movimentacaoService;
        this.scanner = new Scanner(System.in);
    }

    // Exibe o menu principal e processa a escolha do usuário
    public void exibirMenu() {
        boolean continuar = true;
        while (continuar) {
            limparTela();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE GERENCIAMENTO DE GARAGEM  ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1. Gerenciar Proprietários");
            System.out.println("2. Gerenciar Veículos");
            System.out.println("3. Gerenciar Vagas");
            System.out.println("4. Gerenciar Movimentações");
            System.out.println("0. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    menuProprietarios();
                    break;
                case "2":
                    menuVeiculos();
                    break;
                case "3":
                    menuVagas();
                    break;
                case "4":
                    menuMovimentacoes();
                    break;
                case "0":
                    continuar = false;
                    System.out.println("\nAté logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    pausa();
            }
        }
    }

    // ==================== MENU PROPRIETÁRIOS ====================
    private void menuProprietarios() {
        boolean voltar = false;
        while (!voltar) {
            limparTela();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║       GERENCIAR PROPRIETÁRIOS          ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1. Listar Proprietários");
            System.out.println("2. Criar Proprietário");
            System.out.println("3. Atualizar Proprietário");
            System.out.println("4. Deletar Proprietário");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    listarProprietarios();
                    break;
                case "2":
                    criarProprietario();
                    break;
                case "3":
                    atualizarProprietario();
                    break;
                case "4":
                    deletarProprietario();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pausa();
            }
        }
    }

    private void listarProprietarios() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        LISTA DE PROPRIETÁRIOS          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        List<ProprietarioDTO> proprietarios = proprietarioService.findAll();
        if (proprietarios.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado.");
        } else {
            for (ProprietarioDTO p : proprietarios) {
                System.out.printf("ID: %d | Nome: %s | Telefone: %s%n", p.getId(), p.getNome(), p.getTelefone());
            }
        }
        pausa();
    }

    private void criarProprietario() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      CRIAR NOVO PROPRIETÁRIO           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        ProprietarioDTO dto = new ProprietarioDTO();
        dto.setNome(nome);
        dto.setTelefone(telefone);

        ProprietarioDTO criado = proprietarioService.create(dto);
        System.out.println();
        System.out.println("✓ Proprietário criado com sucesso! ID: " + criado.getId());
        pausa();
    }

    private void atualizarProprietario() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    ATUALIZAR PROPRIETÁRIO              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID do proprietário a atualizar: ");
        Long id = obterLong();
        Optional<ProprietarioDTO> existente = proprietarioService.findById(id);
        if (existente.isEmpty()) {
            System.out.println("✗ Proprietário não encontrado.");
            pausa();
            return;
        }
        ProprietarioDTO p = existente.get();
        System.out.print("Novo nome (atual: " + p.getNome() + "): ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone (atual: " + p.getTelefone() + "): ");
        String telefone = scanner.nextLine();

        p.setNome(nome);
        p.setTelefone(telefone);
        proprietarioService.update(id, p);
        System.out.println();
        System.out.println("✓ Proprietário atualizado com sucesso!");
        pausa();
    }

    private void deletarProprietario() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    DELETAR PROPRIETÁRIO                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID do proprietário a deletar: ");
        Long id = obterLong();
        proprietarioService.delete(id);
        System.out.println();
        System.out.println("✓ Proprietário deletado com sucesso!");
        pausa();
    }

    // ==================== MENU VEÍCULOS ====================
    private void menuVeiculos() {
        boolean voltar = false;
        while (!voltar) {
            limparTela();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║         GERENCIAR VEÍCULOS             ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1. Listar Veículos");
            System.out.println("2. Criar Veículo");
            System.out.println("3. Atualizar Veículo");
            System.out.println("4. Deletar Veículo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    listarVeiculos();
                    break;
                case "2":
                    criarVeiculo();
                    break;
                case "3":
                    atualizarVeiculo();
                    break;
                case "4":
                    deletarVeiculo();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pausa();
            }
        }
    }

    private void listarVeiculos() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║          LISTA DE VEÍCULOS             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        List<VeiculoDTO> veiculos = veiculoService.findAll();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (VeiculoDTO v : veiculos) {
                System.out.printf("ID: %d | Placa: %s | Modelo: %s | Cor: %s | Proprietário ID: %s%n",
                        v.getId(), v.getPlaca(), v.getModelo(), v.getCor(), v.getProprietarioId());
            }
        }
        pausa();
    }

    private void criarVeiculo() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       CRIAR NOVO VEÍCULO               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("ID do Proprietário: ");
        Long proprietarioId = obterLong();

        VeiculoDTO dto = new VeiculoDTO();
        dto.setPlaca(placa);
        dto.setModelo(modelo);
        dto.setCor(cor);
        dto.setProprietarioId(proprietarioId);

        VeiculoDTO criado = veiculoService.create(dto);
        System.out.println();
        System.out.println("✓ Veículo criado com sucesso! ID: " + criado.getId());
        pausa();
    }

    private void atualizarVeiculo() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       ATUALIZAR VEÍCULO                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID do veículo a atualizar: ");
        Long id = obterLong();
        Optional<VeiculoDTO> existente = veiculoService.findById(id);
        if (existente.isEmpty()) {
            System.out.println("✗ Veículo não encontrado.");
            pausa();
            return;
        }
        VeiculoDTO v = existente.get();
        System.out.print("Nova placa (atual: " + v.getPlaca() + "): ");
        String placa = scanner.nextLine();
        System.out.print("Novo modelo (atual: " + v.getModelo() + "): ");
        String modelo = scanner.nextLine();
        System.out.print("Nova cor (atual: " + v.getCor() + "): ");
        String cor = scanner.nextLine();
        System.out.print("Novo ID do Proprietário (atual: " + v.getProprietarioId() + "): ");
        Long proprietarioId = obterLong();

        v.setPlaca(placa);
        v.setModelo(modelo);
        v.setCor(cor);
        v.setProprietarioId(proprietarioId);
        veiculoService.update(id, v);
        System.out.println();
        System.out.println("✓ Veículo atualizado com sucesso!");
        pausa();
    }

    private void deletarVeiculo() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       DELETAR VEÍCULO                  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID do veículo a deletar: ");
        Long id = obterLong();
        veiculoService.delete(id);
        System.out.println();
        System.out.println("✓ Veículo deletado com sucesso!");
        pausa();
    }

    // ==================== MENU VAGAS ====================
    private void menuVagas() {
        boolean voltar = false;
        while (!voltar) {
            limparTela();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║          GERENCIAR VAGAS               ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1. Listar Vagas");
            System.out.println("2. Criar Vaga");
            System.out.println("3. Atualizar Vaga");
            System.out.println("4. Deletar Vaga");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    listarVagas();
                    break;
                case "2":
                    criarVaga();
                    break;
                case "3":
                    atualizarVaga();
                    break;
                case "4":
                    deletarVaga();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pausa();
            }
        }
    }

    private void listarVagas() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           LISTA DE VAGAS               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        List<VagaDTO> vagas = vagaService.findAll();
        if (vagas.isEmpty()) {
            System.out.println("Nenhuma vaga cadastrada.");
        } else {
            for (VagaDTO vg : vagas) {
                System.out.printf("ID: %d | Número: %s | Tipo: %s%n", vg.getId(), vg.getNumero(), vg.getTipo());
            }
        }
        pausa();
    }

    private void criarVaga() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        CRIAR NOVA VAGA                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Número da vaga: ");
        String numero = scanner.nextLine();
        System.out.print("Tipo (COMUM, PCD, VIP): ");
        String tipo = scanner.nextLine();

        VagaDTO dto = new VagaDTO();
        dto.setNumero(numero);
        dto.setTipo(tipo);

        VagaDTO criada = vagaService.create(dto);
        System.out.println();
        System.out.println("✓ Vaga criada com sucesso! ID: " + criada.getId());
        pausa();
    }

    private void atualizarVaga() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       ATUALIZAR VAGA                   ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID da vaga a atualizar: ");
        Long id = obterLong();
        Optional<VagaDTO> existente = vagaService.findById(id);
        if (existente.isEmpty()) {
            System.out.println("✗ Vaga não encontrada.");
            pausa();
            return;
        }
        VagaDTO vg = existente.get();
        System.out.print("Novo número (atual: " + vg.getNumero() + "): ");
        String numero = scanner.nextLine();
        System.out.print("Novo tipo (atual: " + vg.getTipo() + "): ");
        String tipo = scanner.nextLine();

        vg.setNumero(numero);
        vg.setTipo(tipo);
        vagaService.update(id, vg);
        System.out.println();
        System.out.println("✓ Vaga atualizada com sucesso!");
        pausa();
    }

    private void deletarVaga() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       DELETAR VAGA                     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID da vaga a deletar: ");
        Long id = obterLong();
        vagaService.delete(id);
        System.out.println();
        System.out.println("✓ Vaga deletada com sucesso!");
        pausa();
    }

    // ==================== MENU MOVIMENTAÇÕES ====================
    private void menuMovimentacoes() {
        boolean voltar = false;
        while (!voltar) {
            limparTela();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║      GERENCIAR MOVIMENTAÇÕES           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1. Listar Movimentações");
            System.out.println("2. Criar Movimentação");
            System.out.println("3. Atualizar Movimentação");
            System.out.println("4. Deletar Movimentação");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    listarMovimentacoes();
                    break;
                case "2":
                    criarMovimentacao();
                    break;
                case "3":
                    atualizarMovimentacao();
                    break;
                case "4":
                    deletarMovimentacao();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    pausa();
            }
        }
    }

    private void listarMovimentacoes() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    LISTA DE MOVIMENTAÇÕES              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.findAll();
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (MovimentacaoDTO m : movimentacoes) {
                System.out.printf("ID: %d | Veiculo: %s | Vaga: %s | Entrada: %s | Saída: %s | Valor: R$ %.2f%n",
                        m.getId(), m.getVeiculoId(), m.getVagaId(), m.getEntrada(), m.getSaida(),
                        m.getValor().doubleValue());
            }
        }
        pausa();
    }

    private void criarMovimentacao() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    CRIAR NOVA MOVIMENTAÇÃO             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID do Veículo: ");
        Long veiculoId = obterLong();
        System.out.print("ID da Vaga: ");
        Long vagaId = obterLong();
        System.out.print("Data/Hora de Entrada (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime entrada = obterDataHora();
        System.out.print("Data/Hora de Saída (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime saida = obterDataHora();

        MovimentacaoDTO dto = new MovimentacaoDTO();
        dto.setVeiculoId(veiculoId);
        dto.setVagaId(vagaId);
        dto.setEntrada(entrada);
        dto.setSaida(saida);

        MovimentacaoDTO criada = movimentacaoService.create(dto);
        System.out.println();
        System.out.println("✓ Movimentação criada com sucesso! ID: " + criada.getId());
        System.out.printf("Valor calculado: R$ %.2f%n", criada.getValor().doubleValue());
        pausa();
    }

    private void atualizarMovimentacao() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    ATUALIZAR MOVIMENTAÇÃO              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID da movimentação a atualizar: ");
        Long id = obterLong();
        Optional<MovimentacaoDTO> existente = movimentacaoService.findById(id);
        if (existente.isEmpty()) {
            System.out.println("✗ Movimentação não encontrada.");
            pausa();
            return;
        }
        MovimentacaoDTO m = existente.get();
        System.out.print("Novo ID do Veículo (atual: " + m.getVeiculoId() + "): ");
        Long veiculoId = obterLong();
        System.out.print("Novo ID da Vaga (atual: " + m.getVagaId() + "): ");
        Long vagaId = obterLong();
        System.out.print("Nova Data/Hora de Entrada (yyyy-MM-dd HH:mm:ss, atual: " + m.getEntrada() + "): ");
        LocalDateTime entrada = obterDataHora();
        System.out.print("Nova Data/Hora de Saída (yyyy-MM-dd HH:mm:ss, atual: " + m.getSaida() + "): ");
        LocalDateTime saida = obterDataHora();

        m.setVeiculoId(veiculoId);
        m.setVagaId(vagaId);
        m.setEntrada(entrada);
        m.setSaida(saida);
        movimentacaoService.update(id, m);
        System.out.println();
        System.out.println("✓ Movimentação atualizada com sucesso!");
        pausa();
    }

    private void deletarMovimentacao() {
        limparTela();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    DELETAR MOVIMENTAÇÃO                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.print("ID da movimentação a deletar: ");
        Long id = obterLong();
        movimentacaoService.delete(id);
        System.out.println();
        System.out.println("✓ Movimentação deletada com sucesso!");
        pausa();
    }

    // ==================== UTILITÁRIOS ====================

    /**
     * Limpa a tela do console (funciona em Linux/Mac e Windows com ANSI).
     */
    private void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: apenas imprimir linhas em branco
            for (int i = 0; i < 30; i++) System.out.println();
        }
    }

    /**
     * Pausa até o usuário pressionar Enter.
     */
    private void pausa() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Obtém um Long seguro do scanner.
     */
    private Long obterLong() {
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Usando 0.");
            return 0L;
        }
    }

    /**
     * Obtém uma data/hora no formato "yyyy-MM-dd HH:mm:ss".
     */
    private LocalDateTime obterDataHora() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(scanner.nextLine(), formatter);
        } catch (Exception e) {
            System.out.println("Formato inválido. Usando LocalDateTime.now().");
            return LocalDateTime.now();
        }
    }
}
