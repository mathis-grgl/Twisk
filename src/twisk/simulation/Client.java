package twisk.simulation;

import twisk.monde.Etape;

/**
 * Représente la classe qui gère les informations d'un client tel que son numéro, là où il se situe et son rang.
 */
public class Client {
    protected int numeroClient;
    private int rang;
    private Etape etape;

    /**
     * Instancie un nouveau client.
     * @param numero son id
     */
    public Client(int numero){
        numeroClient = numero;
        rang = 0;
    }

    /**
     * Modifie l'étape et le rang d'un client
     * @param etape la nouvelle étape
     * @param rang son rang dans l'étape
     */
    public void allerA(Etape etape, int rang){
        this.etape = etape;
        this.rang = rang;
    }

    /**
     * Retourne le rang dans l'étape.
     * @return le rang
     */
    public int getRang() {
        return rang;
    }

    /**
     * Retourne l'étape où il se trouve.
     * @return l'étape
     */
    public Etape getEtape() {
        return etape;
    }

    /**
     * Retourne son id.
     * @return l'id du client
     */
    public int getNumeroClient() {
        return numeroClient;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Client n"+numeroClient+", rg : "+rang+" de l'étape "+ etape.getNom());
        return str.toString();
    }
}
