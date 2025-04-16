package natanpivetta.itacademy.model;

import java.io.Serializable;

public enum Evento {
    PITCH_CONVINCENTE(6),
    PRODUTO_COM_BUGS(-4),
    BOA_TRACAO(3),
    INVESTIDOR_IRRITADO(-6),
    FAKE_NEWS(-8);

    private Long startupID;
    private Long batalhaID;
    private final int impacto;

    Evento(int impacto) {
        this.impacto = impacto;
    }

    public int getImpacto() {
        return impacto;
    }
}
