package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import natanpivetta.itacademy.util.TipoEvento;

import java.io.Serializable;

@Entity
@Table (name = "evento")
public class Evento implements Serializable {

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

    // Construtor padrão
    public Evento() {}

    // Construtor com tipo e startup
    public Evento(TipoEvento tipo, Startup startup) {
        this.tipo = tipo;
        this.impacto = tipo.getImpacto(); // salva o impacto automaticamente
        this.startup = startup;
    }

    // Getters e setters
    public Long getId() { return id; }
    public TipoEvento getTipo() { return tipo; }
    public int getImpacto() { return impacto; }
    public Startup getStartup() { return startup; }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
        this.impacto = tipo.getImpacto();
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }
}
