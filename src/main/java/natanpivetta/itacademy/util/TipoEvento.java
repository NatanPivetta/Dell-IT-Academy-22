package natanpivetta.itacademy.util;

public enum TipoEvento {
    PITCH_CONVINCENTE(6),
    PRODUTO_COM_BUGS(-4),
    BOA_TRACAO(3),
    INVESTIDOR_IRRITADO(-6),
    FAKE_NEWS(-8),
    SHARK(2);

    private final int impacto;

    TipoEvento(int impacto) {
        this.impacto = impacto;
    }

    public int getImpacto() {
        return impacto;
    }
}
