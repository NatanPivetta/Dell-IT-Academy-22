package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.RodadaRepository;
import natanpivetta.itacademy.util.BatalhaOptions;
import natanpivetta.itacademy.util.RodadaOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "rodada")
public class Rodada {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    @OneToMany
    List<Batalha> batalhaList = new ArrayList<Batalha>();
    private boolean status;
    @ManyToMany
    private List<Startup> startupsList = new ArrayList<>();

    public Rodada() {}

    public void setStatusIniciada(){
        status = false;
    }

    public void setStatusFinalizada(){
        status = true;
    }

    public boolean isFinalizada(){
        return status;
    }

    public void setStartups(List<Startup> startups){
        this.startupsList = startups;
    }

    public List<Startup> getStartups(){
        return startupsList;
    }

    public int getNumero() {
        return numero;
    }


    public List<Batalha> getBatalhas() {
        return this.batalhaList;
    }

    public Long getID() {
        return this.id;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public List<Batalha> getBatalhasPendentes(){
        List<Batalha> btPendentes = new ArrayList<>();
        for(Batalha bt : getBatalhas()){
            if(!bt.isFinalizada()){
                btPendentes.add(bt);
            }
        }

        return btPendentes;
    }

    public Rodada criarNovaRodada(List<Startup> vencedoras, int numero) {
        Rodada rodada = new Rodada();
        rodada.setNumero(numero);
        rodada.setStartups(vencedoras);
        return rodada;
    }

    public void iniciarRodada(Rodada rodada, RodadaOptions rdOptions, Scanner sc, BatalhaRepository btRepository, RodadaRepository rdRepository) {
        while (!rodada.isFinalizada()) {
            rdOptions.gerarOpcoes();
            int op = sc.nextInt();
            Batalha bt = rdOptions.lerOpcao(op);
            BatalhaOptions btOptions = new BatalhaOptions(bt);
            bt.setStatusIniciada();
            bt.confronto();
            op = 0;
            while (!bt.isFinalizada() && op != 11) {
                bt.confronto();
                btOptions.imprimirOpcoes();
                op = sc.nextInt();
                sc.nextLine();
                btOptions.lerOpcao(op);
            }
            bt.setVencedora();
            System.out.println("Vencedora: " + (bt.getVencedora() != null ? bt.getVencedora().getNome() : "NENHUMA"));
            bt.setStatusFinalizada();
            btRepository.update(bt);
            if(this.getBatalhasPendentes().isEmpty()){
                setStatusFinalizada();
                rdRepository.update(this);
            }
        }
    }

    public List<Batalha> sortearBatalhas(List<Startup> startupsList, Rodada rodada){
        Collections.shuffle(startupsList);
        List<Batalha> batalhas = new ArrayList<>();
        for(int i = 0; i < startupsList.size(); i+=2){
            Batalha batalha = new Batalha(startupsList.get(i), startupsList.get(i+1), rodada);
            // System.out.println(batalha.confronto());
            batalhas.add(batalha);
        }
        return batalhas;
    }


    public void setBatalhas(Batalha bt) {
        this.batalhaList.add(bt);
    }

    public List<Startup> getVencedorasRodada(Rodada rodada) {
        List<Startup> vencedoras = new ArrayList<>();
        for(Batalha bt: batalhaList){
            if(bt.isFinalizada()){
                vencedoras.add(bt.getVencedora());
            }
        }
        return vencedoras;
    }
}
