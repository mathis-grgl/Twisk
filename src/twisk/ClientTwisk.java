package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        Etape fpi = new Guichet("FilePiscine", 3);
        Etape pi = new ActiviteRestreinte("Piscine", 8, 2);
        Etape fto = new Guichet("FileToboggan", 1);
        Etape to = new ActiviteRestreinte("Toboggan", 5, 2);
        Etape bas = new ActiviteRestreinte("BacAsable", 2, 1);
        Etape test = new ActiviteRestreinte("BacAsable",2,1);
        fpi.ajouterSuccesseur(pi);
        pi.ajouterSuccesseur(fto);
        fto.ajouterSuccesseur(to);
        to.ajouterSuccesseur(bas);
        bas.ajouterSuccesseur(test);

        sim.setNbClients(6);
        monde.aCommeEntree(fpi);
        monde.ajouter(fpi, pi, to, fto, bas,test);
        monde.aCommeSortie(test);

        sim.simuler(monde);
    }
}
