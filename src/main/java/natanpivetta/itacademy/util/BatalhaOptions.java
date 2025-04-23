package natanpivetta.itacademy.util;

import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Evento;
import natanpivetta.itacademy.model.Rodada;
import natanpivetta.itacademy.model.Startup;
import natanpivetta.itacademy.repository.BatalhaRepository;
import natanpivetta.itacademy.repository.EventoRepository;
import natanpivetta.itacademy.repository.StartupRepository;

import java.util.ArrayList;
import java.util.List;

public class BatalhaOptions {


    List<String> opcoes = new ArrayList<>();
    private int contadorEv = 0;
    private Batalha batalha;
    private Startup stA;
    private Startup stB;
    private EventoRepository evRepository = new EventoRepository();
    private BatalhaRepository btRepository = new BatalhaRepository();
    private StartupRepository stRepository = new StartupRepository();

    public BatalhaOptions(Batalha batalha) {
        this.batalha = batalha;
        this.stA = batalha.getStartupA();
        this.stB = batalha.getStartupB();
        gerarOpcoes();
    }

    private void gerarOpcoes() {

        int contador = 1;

        for (TipoEvento tipo : TipoEvento.values()) {
            if(tipo != TipoEvento.SHARK){
                    this.opcoes.add(contador++ + " - Registrar " + formatarNomeEvento(tipo) + " para " + stA.getNome());

                    this.opcoes.add(contador++ + " - Registrar " + formatarNomeEvento(tipo) + " para " + stB.getNome());

            }
        }
        this.opcoes.add(contador++ + " - Finalizar Batalha");

    }


    private String formatarNomeEvento(TipoEvento evento) {
        return evento.name().replace("_", " ").toLowerCase()
                .replaceFirst(".", (evento.name().charAt(0) + "").toUpperCase());
    }

    public void imprimirOpcoes() {
        placarAtual();
        for (String opcao : opcoes) {
            System.out.println(opcao);
        }
    }

        public void lerOpcao(int i){
            Evento evento;
            switch (i) {
                case 1:
                    evento = new Evento(TipoEvento.PITCH_CONVINCENTE, stA, this.batalha);
                    verificaEvento(evento, stA);
                    break;
                case 2:
                    evento = new Evento(TipoEvento.PITCH_CONVINCENTE, stB, this.batalha);
                    verificaEvento(evento, stB);
                    break;
                case 3:
                    evento = new Evento(TipoEvento.PRODUTO_COM_BUGS, stA, this.batalha);
                    verificaEvento(evento, stA);
                    break;
                case 4:
                    evento = new Evento(TipoEvento.PRODUTO_COM_BUGS, stB, this.batalha);
                    verificaEvento(evento, stB);
                    break;
                case 5:
                    evento = new Evento(TipoEvento.BOA_TRACAO, stA, this.batalha);
                    verificaEvento(evento, stA);
                    break;
                case 6:
                    evento = new Evento(TipoEvento.BOA_TRACAO, stB, this.batalha);
                    verificaEvento(evento, stB);
                    break;
                case 7:
                    evento = new Evento(TipoEvento.INVESTIDOR_IRRITADO, stA, this.batalha);
                    verificaEvento(evento, stA);
                    break;
                case 8:
                    evento = new Evento(TipoEvento.INVESTIDOR_IRRITADO, stB, this.batalha);
                    verificaEvento(evento, stB);
                    break;
                case 9:
                    evento = new Evento(TipoEvento.FAKE_NEWS, stA, this.batalha);
                    verificaEvento(evento, stA);
                    break;
                case 10:
                    evento = new Evento(TipoEvento.FAKE_NEWS, stB, this.batalha);
                    verificaEvento(evento, stB);
                    break;
                case 11:
                    this.batalha.setStatusFinalizada();
                    break;
            }
        }

    private void verificaEvento(Evento evento, Startup st) {
        if (!evRepository.existsBy(this.batalha.getId(), st.getId(), evento.getTipo())) {
            this.contadorEv++;
            evRepository.registraEvento(evento);
            this.batalha.registraEvento(evento);
            st.registraEvento(evento);
            stRepository.update(st);
        }else{
            System.out.println(formatarNomeEvento(evento.getTipo()) + " já registrado para " + st.getNome() + "\n");
        }

        if(this.contadorEv == 10){
            this.batalha.setStatusFinalizada();
        }
    }

    public void placarAtual(){
        String s = "\n Placar: ";
        Startup stA = this.batalha.getStartupA();
        Startup stB = this.batalha.getStartupB();
        s += stA.getNome() + " - " + stA.getPontuacao() + " pontos VS " + stB.getNome() + " - " + stB.getPontuacao() + " pontos \n";
        System.out.println(s);
    }



}


