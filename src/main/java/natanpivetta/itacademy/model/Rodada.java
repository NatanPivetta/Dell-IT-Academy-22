package natanpivetta.itacademy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Rodada {
    @Id
    private Long id;
    private int numero;
    @OneToMany
    List<Batalha> batalhas;

    public Rodada() {}

}
