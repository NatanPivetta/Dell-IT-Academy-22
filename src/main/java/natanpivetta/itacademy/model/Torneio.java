package natanpivetta.itacademy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "torneio")
public class Torneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rodada> rodadas = new ArrayList<>();
    @OneToOne
    private Startup vencedora;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Startup> startups = new ArrayList<>();
    private boolean status;
    @Transient
    int PONTUACAO_INICIAL = 70;
    public Torneio(){}



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
        System.out.println("Nome: ");
        String stNome = sc.nextLine();

        System.out.println("Slogan: ");
        String stSlogan = sc.nextLine();

        int stAnoFundacao = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("Ano de fundação:");
            String input = sc.nextLine();
            try {
                stAnoFundacao = Integer.parseInt(input);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número inteiro para o ano.");
            }
        }

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


    public void setStatusFinalizado() {
        this.status = true;
    }

    public Long getId(){
        return this.id;
    }

    public void setVencedora(Startup vencedora) {
        this.vencedora = vencedora;
    }

    public Startup getVencedora() {
        return this.vencedora;
    }
}
