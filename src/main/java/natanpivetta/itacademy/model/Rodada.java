package natanpivetta.itacademy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rodada")
public class Rodada {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    @OneToMany
    List<Batalha> batalhas;
    private boolean status;

    public Rodada() {}

    public void setStatusIniciada(){
        this.status = true;
    }

    public void setStatusFinalizada(){
        this.status = false;
    }

    public boolean isFinalizada(){
        return this.status;
    }


}
