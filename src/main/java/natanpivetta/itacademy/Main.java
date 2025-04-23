package natanpivetta.itacademy;


import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Rodada;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.model.Torneio;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.RodadaRepository;
import natanpivetta.itacademy.repository.StartupRepository;
import natanpivetta.itacademy.repository.TorneioRepository;

import natanpivetta.itacademy.util.RodadaOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Torneio torneio;
    private static StartupRepository stRepository;
    private static BatalhaRepository btRepository;
    private static RodadaRepository rdRepository;
    private static TorneioRepository trRepository;
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        inicializarRepositorios();
        exibirMenuPrincipal();
    }

    private static void inicializarRepositorios() {
        stRepository = new StartupRepository();
        btRepository = new BatalhaRepository();
        rdRepository = new RodadaRepository();
        trRepository = new TorneioRepository();
    }

    private static void inicializarTorneio() {
        torneio = new Torneio();
        torneio.iniciarTorneio();
        for (Startup st : torneio.getStartups()) {
            stRepository.save(st);
        }
    }

    private static void executarRodadaInicial() {
        Rodada rodada = new Rodada();
        rodada.setStartups(torneio.getStartups());
        rodada.setNumero(1);

        List<Startup> startupsRodada = new ArrayList<>(rodada.getStartups());
        carregarBatalhas(startupsRodada, rodada);
        rdRepository.save(rodada);
    }

    private static void executarRodadasFinais() {
        List<Rodada> rodadas = torneio.getRodadas();
        Rodada rodadaAnterior = rodadas.getLast();
        List<Startup> vencedoras = rodadaAnterior.getVencedorasRodada(rodadaAnterior);
        int numeroRodada = rodadaAnterior.getNumero();

        while (vencedoras.size() > 1) {
            numeroRodada++;
            Rodada novaRodada = rodadaAnterior.criarNovaRodada(vencedoras, numeroRodada);
            rdRepository.save(novaRodada);

            carregarBatalhas(vencedoras, novaRodada);

            vencedoras = novaRodada.getVencedorasRodada(novaRodada);
            rodadaAnterior = novaRodada;
        }

        torneio.setVencedora(vencedoras.getFirst());
    }

    private static void carregarBatalhas(List<Startup> vencedoras, Rodada novaRodada) {
        List<Batalha> batalhasList = novaRodada.sortearBatalhas(vencedoras, novaRodada);
        for (Batalha bt : batalhasList) {
            btRepository.save(bt);
            novaRodada.setBatalhas(bt);
        }

        torneio.setRodadas(novaRodada);
        RodadaOptions rdOptions = new RodadaOptions(novaRodada);
        novaRodada.iniciarRodada(novaRodada, rdOptions, sc, btRepository, rdRepository);
    }

    private static void executarTorneioCompleto() {
        inicializarTorneio();
        executarRodadaInicial();
        executarRodadasFinais();
        finalizarTorneio();
    }

    private static void finalizarTorneio() {
        torneio.setStatusFinalizado();
        trRepository.save(torneio);
        Startup vencedora = torneio.getVencedora();
        System.out.println("Startup Vencedora: " + vencedora.getNome() + " - " + vencedora.getSlogan());
        exibeEstatisticas();
    }

    private static void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 3) {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Iniciar Novo Torneio");
            System.out.println("2 - Visualizar Hall da Fama");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        executarTorneioCompleto();
                        break;
                    case 2:
                        exibirHallDaFama();
                        break;
                    case 3:
                        System.out.println("Saindo da aplicação. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }

    private static void exibirHallDaFama() {
        List<Torneio> torneios;
        List<Startup> hallDaFama = new ArrayList<>();
        torneios = trRepository.findAll();
        for(Torneio t : torneios) {
            Startup vencedora = t.getVencedora();
            vencedora.setTorneioId(t.getId());
            hallDaFama.add(vencedora);
        }
        System.out.println("\n==== HALL DA FAMA ====");
        for(Startup s : hallDaFama) {
            System.out.println(s.getNome() + " - " + s.getSlogan() + " - Fundada em " + s.getAnoFundacao() + " Vencedora do torneio " + s.getTorneioId() );
        }

    }

    private static void exibeEstatisticas(){
        System.out.println("Estatísticas do torneio: ");
        for(String linha: torneio.getEstatisticas()){
            System.out.println(linha);
        }
    }

}