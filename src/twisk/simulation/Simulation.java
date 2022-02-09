package twisk.simulation;

import twisk.monde.*;

public class Simulation {
    public Simulation(){}

    public void simuler(Monde monde){
        System.out.println(monde);
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        Etape sE = new SasEntree();
        Etape fpi = new Guichet("File Piscine",3);
        Etape pi = new Activite("Piscine",8,2);
        Etape fto = new Guichet("File Toboggan",1);
        Etape to = new Activite("Toboggan",3,2);
        Etape bas = new Activite("Bac à sable",4,2);
        Etape sS = new SasSortie();

        monde.aCommeEntree(sE);
        monde.ajouter(fpi,pi,fto,to,bas);
        monde.aCommeSortie(sS);
        sim.simuler(monde);
    }
}
