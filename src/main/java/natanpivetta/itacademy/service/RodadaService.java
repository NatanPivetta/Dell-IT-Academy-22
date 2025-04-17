package natanpivetta.itacademy.service;


import natanpivetta.itacademy.model.Rodada;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.RodadaRepository;

public class RodadaService {

    private RodadaRepository rodadaRepository;

    public RodadaService(RodadaRepository rodadaRepository) {
        this.rodadaRepository = rodadaRepository;
    }

    public void iniciaRodada(Rodada rodada){
        rodada.setStatusIniciada();
    }

    public void finalizaRodada(Rodada rodada){
        rodada.setStatusFinalizada();
    }

}
