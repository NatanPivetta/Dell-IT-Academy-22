package natanpivetta.itacademy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Torneio {

    private List<Rodada> rodadas = new ArrayList<>();
    private List<Rodada> rodadasPentendes = new ArrayList<>();
    private Startup vencedora;
    private List<Startup> startups = new ArrayList<>();
    private boolean status;

    public Torneio(){}

    int PONTUACAO_INICIAL = 70;





    public int numeroDeRodadas(){
        if(startups.size()==8){
            return 3;
        }
        if(startups.size()==4){
            return 2;
        }
        return 0;
    }


    public void iniciarTorneio(){
        System.out.println("Iniciando torneio\n");
        System.out.println("Quantas Startups você deseja inserir no torneio? \n ");
        System.out.println("1 - 4 Startups");
        System.out.println("2 - 8 Startups");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch(op){
            case 1:
                while(startups.size()<4){
                    cadastrarStartups();
                }
                break;
            case 2:
                while(startups.size()<8){
                    cadastrarStartups();
                }
                break;

            default:
                System.out.println("Opção Inválida!");
        }

    }
    private void cadastrarStartups(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: \n");
        String stNome = sc.next();

        System.out.println("Slogan: \n");
        String stSlogan = sc.next();

        System.out.println("Ano de fundação: \n");
        int stAnoFundacao = sc.nextInt();

        Startup startup = new Startup(stNome, stSlogan, stAnoFundacao, PONTUACAO_INICIAL);
        startups.add(startup);

    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public List<Startup> getStartups() {
        return this.startups;
    }

    public void setRodadas(Rodada rodadas) {
        this.rodadas.add(rodadas);
    }



    public List<Rodada> getRodadasPendentes(){
        if(rodadasPentendes.size()==0){
            this.setStatusFinalizado();
        }
        for(Rodada rd : getRodadas()){
            if(!rd.isFinalizada()){
                this.rodadasPentendes.add(rd);
            }
        }

        return this.rodadasPentendes;
    }

    private void setStatusFinalizado() {
        this.status = true;
    }
}
