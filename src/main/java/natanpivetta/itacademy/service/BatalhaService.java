package natanpivetta.itacademy.service;

import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.repository.BatalhaRepository;

import java.util.List;

public class BatalhaService {
    private BatalhaRepository batalhaRepository;

    public BatalhaService() {}

    public BatalhaService(Batalha batalha){
        batalha.setStatusIniciada();
    }

    public BatalhaService(BatalhaRepository batalhaRepository) {
        this.batalhaRepository = batalhaRepository;
    }

    public void registraBatalha(Batalha batalha){
        batalhaRepository.save(batalha);
    }

    public List<Batalha> listarBatalhas(){
        return batalhaRepository.findAll();
    }
    public void iniciarBatalha(Batalha batalha){
        batalha.setStatusIniciada();
    }

    public void setVencedorBatalha(Batalha batalha) {
        batalha.setVencedora();
    }
}
