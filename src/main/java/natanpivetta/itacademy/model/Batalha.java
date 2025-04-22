package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import natanpivetta.itacademy.repository.EventoRepository;
import natanpivetta.itacademy.util.TipoEvento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table (name = "batalha")
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Startup startupA;
    @OneToOne
    private Startup startupB;
    @OneToOne
    private Startup vencedora;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Rodada rodada;
    private boolean finalizada;
    @OneToMany(mappedBy = "batalha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos = new ArrayList<>();


    public Batalha() {}

    public Batalha(Startup startupA, Startup startupB, Rodada rodada){
        this.startupA = startupA;
        this.startupB = startupB;
        this.rodada = rodada;
        this.finalizada = false;
    }


    public Long getId() {
        return id;
    }

    public void setStatusIniciada() {
        this.finalizada = false;
    }

    public void setStatusFinalizada() {
        this.finalizada = true;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void registraEvento(Evento evento){
        this.eventos.add(evento);
    }

    public void setVencedora() {
        if(this.startupA.getPontuacao() > this.startupB.getPontuacao())
        this.vencedora = startupA;
        else if(this.startupB.getPontuacao() > this.startupA.getPontuacao()){
            this.vencedora = startupB;
        }else{
            shark();
            setVencedora();
        }
    }

    private void shark() {
        Evento evento;
        EventoRepository evRepository = new EventoRepository();
        double rA = Math.random();
        double rB = Math.random();
        System.out.println(rA + "  -  " + rB);
        if(rA > rB){
            evento = new Evento(TipoEvento.SHARK, this.startupA, this);
            this.startupA.registraEvento(evento);
            evRepository.registraEvento(evento);
        }else if(rB > rA){
            evento = new Evento(TipoEvento.SHARK, this.startupB, this);
            this.startupB.registraEvento(evento);
            evRepository.registraEvento(evento);
        }else{
            shark();
        }
    }

    public String confronto(){
        return this.startupA.getNome() + " VS " + this.startupB.getNome();
    }

    public Startup getStartupA(){
        return startupA;
    }


    public Startup getStartupB(){
        return startupB;
    }

    public Startup getVencedora() {
        return vencedora;
    }

    public List<Evento> getEventos(){
        return eventos;
    }


}
