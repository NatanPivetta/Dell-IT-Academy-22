package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import natanpivetta.itacademy.util.TipoEvento;



@Entity
@Table (name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;

    private int impacto;

    @ManyToOne
    @JoinColumn(name = "startup")
    private Startup startup;

    @ManyToOne
    @JoinColumn(name = "batalha")
    private Batalha batalha;

    public Evento() {}



    public Evento(TipoEvento tipo, Startup startup, Batalha batalha) {
        this.tipo = tipo;
        this.impacto = tipo.getImpacto();
        this.startup = startup;
        this.batalha = batalha;
    }

    public Long getId() {return id;}
    public TipoEvento getTipo() { return tipo; }
    public int getImpacto() { return impacto; }
    public Startup getStartup() { return startup; }


}
