package natanpivetta.itacademy.util;

import natanpivetta.itacademy.model.Batalha;
import natanpivetta.itacademy.model.Rodada;

public class RodadaOptions {
    private Rodada rodada;


    public RodadaOptions(Rodada rodada) {
        this.rodada = rodada;
    }

    public void gerarOpcoes(){
        int contador = 1;

        if(rodada.getBatalhasPendentes().isEmpty()){
            rodada.setStatusFinalizada();
        }else{
            System.out.println("Selecione a batalha pendente para administrar: ");
            for(Batalha bt: this.rodada.getBatalhasPendentes()){
                System.out.println(contador++ + " - " + bt.confronto());
            }
        }

    }

    public Batalha lerOpcao(int i){
        return this.rodada.getBatalhasPendentes().get(i-1);
    }


}
