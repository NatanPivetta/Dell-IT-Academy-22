package natanpivetta.itacademy.model;

import jakarta.persistence.*;
import org.hibernate.mapping.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Startup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slogan;
    private int anoFundacao;
    private int pontuacao;

    public Startup(){}


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
