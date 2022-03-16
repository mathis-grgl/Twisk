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
        Etape to = new ActiviteRestreinte("Toboggan", 3, 2);
        Etape bas = new ActiviteRestreinte("BacAsable", 4, 2);
        Etape test = new ActiviteRestreinte("TestBac",4,2);
        fpi.ajouterSuccesseur(pi);
        pi.ajouterSuccesseur(fto);
        fto.ajouterSuccesseur(to);
        to.ajouterSuccesseur(bas);
        bas.ajouterSuccesseur(test);


        monde.aCommeEntree(fpi);
        monde.ajouter(fpi, pi, fto, to, bas,test);
        monde.aCommeSortie(test);

        sim.simuler(monde);
    }
}
