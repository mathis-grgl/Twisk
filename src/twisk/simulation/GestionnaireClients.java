package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Représente la classe qui gère une liste des clients.
 */
public class GestionnaireClients implements Iterable<Client>{
    private ArrayList<Client> gC;

    /**
     * Instancie un nouveau GestionnaireClients.
     */
    public GestionnaireClients(){
        gC = new ArrayList<>();
    }

    /**
     * Instancie un nouveau GestionnaireClients en fonction du nombre de clients.
     */
    public GestionnaireClients(int nbClients){
        gC = new ArrayList<>();
        for(int i = 0;i<nbClients;i++){
            gC.add(new Client(i));
        }
    }

    /**
     * Ajoute les clients dans le gestionnaire des clients.
     * @param tabClients les clients
     */
    public void setClients(int... tabClients){
        for (int num :tabClients) {
            gC.add(new Client(num));
        }
    }

    /**
     * Définit des clients dans le gestionnaire des clients en fonction du nombre de clients voulu en paramètre.
     * @param nbClients le nombre de clients
     */
    public void setNbClients(int nbClients) {
        gC = new ArrayList<>(nbClients);
    }

    /**
     * Modifie l'étape et le rang des clients.
     * @param numeroClient le numéro du client
     * @param etape la nouvelle étape
     * @param rang son rang dans l'étape
     */
    public void allerA(int numeroClient, Etape etape, int rang){
       for(Client c : this){
           if(c.numeroClient == numeroClient){
               c.allerA(etape,rang);
           }
       }
    }

    /**
     * Vide le gestionnaire de clients.
     */
    public void nettoyer(){
        gC.clear();
    }

    /**
     * Retourne la taille du gestionnaire de clients.
     * @return la taille de la liste
     */
    public int size(){
        return gC.size();
    }

    /**
     * Retourne le client en fonction de l'index dans le gestionnaire des clients.
     * @param index l'index du client
     * @return le client
     */
    public Client getClient(int index){
        return gC.get(index);
    }

    /**
     * Retourne la liste des clients.
     * @return la liste des clients
     */
    public ArrayList<Client> getListClients() {
        return gC;
    }

    @Override
    public Iterator<Client> iterator() {
        return gC.iterator();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Client c : this) str.append(c.toString()+"\n");

        return str.toString();
    }


}
