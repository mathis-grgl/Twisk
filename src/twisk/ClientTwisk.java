package twisk;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        Etape fpi = new Guichet("FilePiscine", 3);
        Etape pi = new Activite("Piscine", 8, 2);
        Etape fto = new Guichet("FileToboggan", 1);
        Etape to = new Activite("Toboggan", 3, 2);
        Etape bas = new Activite("BacAsable", 4, 2);
        fpi.ajouterSuccesseur(pi);
        pi.ajouterSuccesseur(fto);
        fto.ajouterSuccesseur(to);
        to.ajouterSuccesseur(bas);


        monde.aCommeEntree(fpi);
        monde.ajouter(fpi, pi, fto, to, bas);
        monde.aCommeSortie(bas);

        sim.simuler(monde);
    }
}
