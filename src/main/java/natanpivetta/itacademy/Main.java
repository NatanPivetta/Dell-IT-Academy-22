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
        inicializarTorneio();
        executarRodadaInicial();
        executarRodadasFinais();
        finalizarTorneio();
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

    private static void finalizarTorneio() {
        torneio.setStatusFinalizado();
        trRepository.save(torneio);
        System.out.println("Startup Vencedora: " + torneio.getVencedora().getNome());
    }


}