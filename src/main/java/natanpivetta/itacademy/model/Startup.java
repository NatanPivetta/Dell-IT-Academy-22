package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import org.hibernate.mapping.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "startup")
public class Startup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String slogan;
    private int anoFundacao;
    private int pontuacao;

    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> estatisticas = new ArrayList<>();

    public Startup(){}

    public Startup(String nome, String slogan, int anoFundacao, int pontuacao) {
        this.nome = nome;
        this.slogan = slogan;
        this.anoFundacao = anoFundacao;
        this.pontuacao = pontuacao;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setEstatisticas(int pitches, int bugs, int tracoes, int investidoresIrritados, int fakeNews) {

    }

    public int getPontuacao() {
        return pontuacao;
    }
}
