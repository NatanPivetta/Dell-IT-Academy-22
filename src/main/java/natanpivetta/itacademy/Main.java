package natanpivetta.itacademy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Rodada;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.model.Torneio;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.RodadaRepository;
import natanpivetta.itacademy.repository.StartupRepository;
import natanpivetta.itacademy.util.BatalhaOptions;
import natanpivetta.itacademy.util.RodadaOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int op = 0;
        Torneio torneio = new Torneio();
        Scanner sc = new Scanner(System.in);

        StartupRepository stRepository = new StartupRepository();
        BatalhaRepository btRepository = new BatalhaRepository();
        RodadaRepository rdRepository = new RodadaRepository();


        torneio.iniciarTorneio();
        for (Startup st : torneio.getStartups()) {
            stRepository.save(st);
        }


        Rodada rodada = new Rodada();
        rodada.setStartups(torneio.getStartups());
        List<Startup> startupsRodada = new ArrayList<>(rodada.getStartups());

        List<Batalha> batalhasList = rodada.sortearBatalhas(startupsRodada, rodada);
        for (Batalha bt : batalhasList) {
            btRepository.save(bt);
            rodada.setBatalhas(bt);
        }
        torneio.setRodadas(rodada);
        RodadaOptions rdOptions = new RodadaOptions(rodada);
        rodada.iniciarRodada(rodada, rdOptions, sc, btRepository, rdRepository); // iniciando rodada 1 fora do loop
        rdRepository.save(rodada);

        List<Startup> vencedoras = rodada.getVencedorasRodada(rodada);
        int numeroRodada = rodada.getNumero();


        while (vencedoras.size() > 1) {
            numeroRodada++;

            rodada = rodada.criarNovaRodada(vencedoras, numeroRodada);
            rdRepository.save(rodada);
            batalhasList = rodada.sortearBatalhas(vencedoras, rodada);
            for (Batalha bt : batalhasList) {
                btRepository.save(bt);
                rodada.setBatalhas(bt);
            }
            torneio.setRodadas(rodada);
            rdOptions = new RodadaOptions(rodada);
            rodada.iniciarRodada(rodada, rdOptions, sc, btRepository, rdRepository);


            vencedoras = rodada.getVencedorasRodada(rodada);
        }


        System.out.println("Startup Vencedora: " + vencedoras.get(0).getNome());


    }


}