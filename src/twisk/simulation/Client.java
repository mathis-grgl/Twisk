package twisk.simulation;

import twisk.monde.Etape;

public class Client {
    protected int numeroClient;
    private int rang;
    private Etape etape;

    public Client(int numero){
        numeroClient = numero;
        rang = 0;
    }

    public void allerA(Etape etape, int rang){
        this.etape = etape;
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }

    public Etape getEtape() {
        return etape;
    }
}
