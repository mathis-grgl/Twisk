package twisk.simulation;

import twisk.monde.*;
import twisk.outils.KitC;

public class Simulation {
    public Simulation(){}

    public void simuler(Monde monde){
        System.out.println(monde);
        KitC c = new KitC();
        c.creerEnvironnemment();
        String code = monde.toC();
        c.creerFichier(code);
        c.compiler();
        c.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        Etape fpi = new Guichet("FilePiscine",3);
        Etape pi = new Activite("Piscine",8,2);
        Etape fto = new Guichet("FileToboggan",1);
        Etape to = new Activite("Toboggan",3,2);
        Etape bas = new Activite("BacAsable",4,2);
        fpi.ajouterSuccesseur(pi);
        pi.ajouterSuccesseur(fto);
        fto.ajouterSuccesseur(to);
        to.ajouterSuccesseur(bas);


        monde.aCommeEntree(fpi);
        monde.ajouter(pi,fto,to);
        monde.aCommeSortie(bas);

        sim.simuler(monde);
    }

    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();
}
