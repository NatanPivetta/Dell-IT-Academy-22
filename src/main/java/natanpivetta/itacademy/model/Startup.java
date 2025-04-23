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

    @Transient
    private Long torneioId;

    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> estatisticas = new ArrayList<>();

    public Startup(){}

    public Startup(String nome, String slogan, int anoFundacao, int pontuacao) {
        this.nome = nome;
        this.slogan = slogan;
        this.anoFundacao = anoFundacao;
        this.pontuacao = pontuacao;
    }



    public Long getId() {
        return id;
    }

    public int getPontuacao() {
        return pontuacao;
    }


    public void registraEvento(Evento evento) {
        this.pontuacao += evento.getImpacto();
        estatisticas.add(evento);

    }

    public String getNome() {
        return nome;
    }

    public void setPontosExtras() {
        this.pontuacao += 30;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public String getAnoFundacao() {
        return String.valueOf(anoFundacao);
    }

    public void setTorneioId(Long id) {
        this.torneioId = id;
    }

    public Long getTorneioId() {
        return this.torneioId;
    }

    public List<Evento> getEstatisticas() {
        return estatisticas;
    }
}
