import natanpivetta.itacademy.model.*;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.EventoRepository;
import natanpivetta.itacademy.repository.RodadaRepository;
import natanpivetta.itacademy.repository.StartupRepository;
import natanpivetta.itacademy.util.BatalhaOptions;
import natanpivetta.itacademy.util.TipoEvento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StartupPersistenceTest {
    public static void main(String[] args) {

        final int PONTUACAO_INICIAL = 70;
        StartupRepository stRepository = new StartupRepository();
        BatalhaRepository btRepository = new BatalhaRepository();
        EventoRepository evRepository = new EventoRepository();
        RodadaRepository rdRepository = new RodadaRepository();



        List<Startup> startupsList = new ArrayList<>();
        List<Batalha> batalhaList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);




        Startup startup1 = new Startup("Tech Innovators", "Inovação para o futuro", 2010, PONTUACAO_INICIAL);
        Startup startup2 = new Startup("GreenFuture", "Soluções sustentáveis", 2015, PONTUACAO_INICIAL);
        Startup startup3 = new Startup("HealthPlus", "Tecnologia para sua saúde", 2012, PONTUACAO_INICIAL);
        Startup startup4 = new Startup("EduSmart", "Aprendizado inteligente", 2018, PONTUACAO_INICIAL);
        Startup startup5 = new Startup("FinTechNow", "Seu dinheiro, sua liberdade", 2016, PONTUACAO_INICIAL);
        Startup startup6 = new Startup("AgroNova", "Inovando o campo", 2013, PONTUACAO_INICIAL);
        Startup startup7 = new Startup("AutoMind", "IA que dirige com você", 2019, PONTUACAO_INICIAL);
        Startup startup8 = new Startup("ByteForce", "Soluções em nuvem seguras", 2011, PONTUACAO_INICIAL);

        stRepository.save(startup1);
        stRepository.save(startup2);
        stRepository.save(startup3);
        stRepository.save(startup4);
        stRepository.save(startup5);
        stRepository.save(startup6);
        stRepository.save(startup7);
        stRepository.save(startup8);


        startup1 = stRepository.findById(startup1.getId());
        startup2 = stRepository.findById(startup2.getId());
        startup3 = stRepository.findById(startup3.getId());
        startup4 = stRepository.findById(startup4.getId());
        startup5 = stRepository.findById(startup5.getId());
        startup6 = stRepository.findById(startup6.getId());
        startup7 = stRepository.findById(startup7.getId());
        startup8 = stRepository.findById(startup8.getId());
        startupsList.add(startup1);
        startupsList.add(startup2);
        startupsList.add(startup3);
        startupsList.add(startup4);
        startupsList.add(startup5);
        startupsList.add(startup6);
        startupsList.add(startup7);
        startupsList.add(startup8);

        Rodada rodada = new Rodada();
        rodada.setNumero(1);

        Collections.shuffle(startupsList);
        for(int i = 0; i < startupsList.size(); i+=2){
            Batalha batalha = new Batalha(startupsList.get(i), startupsList.get(i+1), rodada);
            btRepository.save(batalha);
            batalhaList.add(batalha);
            System.out.println(batalha.confronto());
        }

        Batalha batalha1 = batalhaList.getFirst();
        Evento evento = new Evento();
        BatalhaOptions btOptions = new BatalhaOptions(batalha1);
        int op = 0;
        do{
            btOptions.imprimirOpcoes();
            op = sc.nextInt();
            btOptions.lerOpcao(op);
        }while(op != 11);
        batalha1.setVencedora();
        batalha1.setStatusFinalizada();
        btRepository.update(batalha1);
        System.out.println(batalha1.getVencedora().getNome());





    }
}
