package br.com.fiap.space.presentation;

import br.com.fiap.space.application.MissaoService;
import br.com.fiap.space.domain.Sonda;
import br.com.fiap.space.domain.SondaMineradora;
import br.com.fiap.space.domain.Terreno;
import br.com.fiap.space.infrastructure.CentroDeComando;
import br.com.fiap.space.infrastructure.SondaFactory;

import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        CentroDeComando centro = CentroDeComando.getInstance();
        MissaoService service = new MissaoService(centro.getRepository());

        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        System.out.println("--- SISTEMA AUTONOMO DE FROTA DE SUPERFICIE (SAFS) ---");

        while (rodando) {
            System.out.println("\n[1] Lancar Nova Sonda ");
            System.out.println("[2] Listar Status da Frota");
            System.out.println("[3] Enviar Coordenada de Missao ");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma diretriz: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        System.out.print("Digite o tipo (MINERACAO ou EXPLORACAO): ");
                        String tipo = scanner.nextLine();
                        System.out.print("Digite o ID da Sonda (Ex: SND-001): ");
                        String id = scanner.nextLine();

                        Sonda novaSonda = SondaFactory.criarSonda(tipo, id);

                        service.registrarSonda(novaSonda);
                        System.out.println("Sonda instanciada e cadastrada com sucesso!");
                        break;

                    case "2":
                        System.out.println("\n--- STATUS DA FROTA ATIVA ---");
                        for (Sonda s : service.obterFrota()) {
                            String infoCarga = "";
                            if (s instanceof SondaMineradora) {
                                infoCarga = " | Carga Ocupada: " + ((SondaMineradora) s).getCarga().getVolumeOcupado();
                            }
                            System.out.println("ID: " + s.getIdSonda() +
                                    " | Bateria: " + s.getBateria().getCapacidadeAtual() +
                                    " | Posicao Atual: " + s.getPosicaoAtual() + infoCarga);
                        }
                        break;

                    case "3":
                        System.out.print("ID da Sonda para a missao: ");
                        String idBusca = scanner.nextLine();

                        if (idBusca == null || idBusca.trim().isEmpty() ||
                                centro.getRepository().buscarPorId(idBusca.trim().toUpperCase()) == null) {
                            throw new RuntimeException("Comando abortado: A sonda com ID '" + idBusca.toUpperCase() + "' nao existe no sistema.");
                        }

                        System.out.print("Coordenada Destino X: ");
                        int x = Integer.parseInt(scanner.nextLine());
                        System.out.print("Coordenada Destino Y: ");
                        int y = Integer.parseInt(scanner.nextLine());

                        System.out.println("Escolha o Terreno: [1] Planicie [2] Cratera [3] Solo Rochoso");
                        String tOpcao = scanner.nextLine();
                        Terreno terrenoEscolhido = Terreno.PLANICIE;
                        if (tOpcao.equals("2")) terrenoEscolhido = Terreno.CRATERA;
                        if (tOpcao.equals("3")) terrenoEscolhido = Terreno.SOLO_ROCHOSO;

                        service.dispararMissaoAutonoma(idBusca, x, y, terrenoEscolhido);
                        break;

                    case "4":
                        rodando = false;
                        System.out.println("Fechando conexao terminal. Boa sorte, Engenheiro!");
                        break;

                    default:
                        System.out.println("Opcao invalida na macro-diretriz.");
                }
            } catch (Exception e) {
                System.out.println("\n>>> [ALERTA DE SISTEMA CRITICO]: " + e.getMessage());
            }
        }
        scanner.close();
    }
}