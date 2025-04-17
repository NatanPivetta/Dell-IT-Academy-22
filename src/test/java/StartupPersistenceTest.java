import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.StartupRepository;
import natanpivetta.itacademy.service.BatalhaService;
import natanpivetta.itacademy.service.StartupService;

import java.util.List;

public class StartupPersistenceTest {
    public static void main(String[] args) {
        StartupRepository stRepository = new StartupRepository();
        StartupService stService = new StartupService(stRepository);
        Startup startup1 = new Startup("Tech Innovators", "Inovação para o futuro", 2010, 70);
        Startup startup2 = new Startup("GreenFuture", "Soluções sustentáveis", 2015, 65);
        Startup startup3 = new Startup("HealthPlus", "Tecnologia para sua saúde", 2012, 80);
        Startup startup4 = new Startup("EduSmart", "Aprendizado inteligente", 2018, 75);
        Startup startup5 = new Startup("FinTechNow", "Seu dinheiro, sua liberdade", 2016, 68);
        Startup startup6 = new Startup("AgroNova", "Inovando o campo", 2013, 72);
        Startup startup7 = new Startup("AutoMind", "IA que dirige com você", 2019, 77);
        Startup startup8 = new Startup("ByteForce", "Soluções em nuvem seguras", 2011, 69);

        stService.cadastrarStartup(startup1);
        stService.cadastrarStartup(startup2);
        stService.cadastrarStartup(startup3);
        stService.cadastrarStartup(startup4);
        stService.cadastrarStartup(startup5);
        stService.cadastrarStartup(startup6);
        stService.cadastrarStartup(startup7);
        stService.cadastrarStartup(startup8);
        startup1  = stRepository.findById(startup1.getId());
        startup2  = stRepository.findById(startup2.getId());
        startup3  = stRepository.findById(startup3.getId());
        startup4  = stRepository.findById(startup4.getId());
        startup5  = stRepository.findById(startup5.getId());
        startup6  = stRepository.findById(startup6.getId());
        startup7  = stRepository.findById(startup7.getId());
        startup8  = stRepository.findById(startup8.getId());

        // Iniciar Rodada

        List<Startup> lista = stService.obterStartups();
        for (Startup s : lista) {
            System.out.println(s.getId());
        }



        BatalhaRepository btRepository = new BatalhaRepository();
        BatalhaService btService = new BatalhaService(btRepository);
        Batalha batalha = new Batalha(startup1, startup2, 1);
        btService.iniciarBatalha(batalha);
        btService.setVencedorBatalha(batalha);
        btService.registraBatalha(batalha);

    }


}
