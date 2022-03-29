package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>{
    private ArrayList<Client> gC;

    public GestionnaireClients(){
        gC = new ArrayList<>();
    }

    public GestionnaireClients(int nbClients){
        gC = new ArrayList<>();
        for(int i = 0;i<nbClients;i++){
            gC.add(new Client(i));
        }
    }

    public void setClients(int... tabClients){
        for (int num :tabClients) {
            gC.add(new Client(num));
        }
    }

    public void setNbClients(int nbClients) {
        gC = new ArrayList<>(nbClients);
    }

    public void allerA(int numeroClient, Etape etape, int rang){
       for(Client c : gC){
           if(c.numeroClient == numeroClient){
               c.numeroClient = numeroClient;
           }
       }
    }

    public void nettoyer(){
        gC.clear();
    }

    public int size(){
        return gC.size();
    }

    public Client getClient(int index){
        return gC.get(index);
    }

    @Override
    public Iterator<Client> iterator() {
        return gC.iterator();
    }
}
