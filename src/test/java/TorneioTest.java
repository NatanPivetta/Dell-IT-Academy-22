import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.model.Torneio;

import java.util.ArrayList;
import java.util.List;

public class TorneioTest {
    public static void main(String[] args) {

        Torneio torneio = new Torneio();

        List<Startup> vencedoras = new ArrayList<>();
        for (Batalha bt : batalhasList) {
            if (bt.getVencedora() != null) {
                vencedoras.add(bt.getVencedora());
            }
        }

        while (torneio.getRodadasPendentes().size() > 0) {
            // Executa lógica da rodada atual
            // Ao final da rodada:
            List<Startup> vencedoras = pegarVencedorasDaRodada(rodada);
            if (vencedoras.size() > 1) {
                criarNovaRodadaCom(vencedoras);
            } else {
                System.out.println("Startup vencedora do torneio: " + vencedoras.get(0).getNome());
                break;
            }
        }
    }
}
