package natanpivetta.itacademy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Batalha {
    @Id
    private Long id;
    @OneToOne
    private Startup startupA;
    @OneToOne
    private Startup startupB;
    @OneToOne
    private Startup vencedora;
    private int rodada;
    private boolean finalizada;

    public Batalha() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
