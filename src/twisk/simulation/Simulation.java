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
        Etape gui = new Guichet("gui");
        Etape sE = new SasEntree();
        Etape sS = new SasSortie();
        Etape ac = new Activite("Acti");
        monde.ajouter(gui,ac);
        monde.aCommeEntree(sE);
        monde.aCommeSortie(sS);
        sim.simuler(monde);
    }
}
