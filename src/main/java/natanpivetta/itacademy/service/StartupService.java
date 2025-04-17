package natanpivetta.itacademy.service;

import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.repository.StartupRepository;
import java.util.List;

public class StartupService {
    private final StartupRepository startupRepository;

    public StartupService(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

    public void cadastrarStartup(Startup startup) {
        startupRepository.save(startup);
    }


    public List<Startup> obterStartups() {
        return startupRepository.findAll();
    }


}
