package natanpivetta.itacademy.model;

import jakarta.persistence.*;

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
    private int rodada;
    private boolean finalizada;

    @OneToMany(mappedBy = "batalha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos;


    public Batalha() {}

    public Batalha(Startup startupA, Startup startupB, int rodada){
        this.startupA = startupA;
        this.startupB = startupB;
        this.rodada = rodada;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setVencedora() {
        if(this.startupA.getPontuacao() > this.startupB.getPontuacao())
        this.vencedora = startupA;
        else{
            this.vencedora = startupB;
        }
    }


}
