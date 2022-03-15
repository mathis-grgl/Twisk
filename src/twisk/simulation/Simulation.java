package twisk.simulation;

import twisk.monde.*;
import twisk.outils.KitC;

public class Simulation {
    private KitC c;
    private int nbClients;

    public Simulation(){
        c = new KitC();
        c.creerEnvironnemment();
        nbClients = 2;
    }

    public void setNbClients(int nbClients){
        this.nbClients = nbClients;
    }

    public void simuler(Monde monde){
        System.out.println(monde+"\n");

        for(Etape e : monde) System.out.println(e);
        System.out.println();

        String code = monde.toC();
        c.creerFichier(code);
        c.compiler();
        c.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");

        //Initialisation et déclarations des variables
        int nbEtapes = monde.nbEtapes();
        int nbGuichets = monde.nbGuichets();
        int[] Guichet = new int[nbGuichets];
        
        int cmpGuichet = 0;
        for (Etape e : monde) {
            if(e.estUnGuichet()){
                Guichet g = (Guichet) e;
                Guichet[cmpGuichet] = g.getNbjetons();
                cmpGuichet++;
            }
        }

        int[] tabProc = start_simulation(nbEtapes,nbGuichets,nbClients,Guichet);


        //Affichage des clients
        System.out.print("les clients : ");
        for(int i =0;i!=tabProc.length;i++) System.out.print(tabProc[i]+" ");
        System.out.println();

        //Création et affectation du tableau de la position des clients
        int[] cliPos = ou_sont_les_clients(nbEtapes,nbClients);

        //Affichage des étapes avec le nombre de clients et les numéros des clients
        int cmp = 0;
        while(cmp != 2){

            //Délai d'affichage
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Permet de faire un affichage correct des étapes (en fonction du cours)
            int affichageCmp = 0;

            //Affichage de l'étape 0
            System.out.print("\nEtape "+affichageCmp+" ("+monde.iterator().next().getNom()+") "+ cliPos[0]+" clients : ");
            if(cliPos[0]!=0) for(int j =1;j<cliPos[0]+1;j++) System.out.print(cliPos[j]+" ");

            //Affichage de toutes les étapes (de toutes les activités)
            for(Etape e : monde){
                if(affichageCmp>1) {
                    System.out.print("\nEtape "+(affichageCmp - 1)+" ("+e.getNom()+") "+cliPos[affichageCmp*(nbClients + 1)]+" clients : ");
                    for (int j = affichageCmp*(nbClients+1)+1 ;j < affichageCmp*(nbClients + 1)+cliPos[affichageCmp*(nbClients + 1)]+1 ;j++)
                        System.out.print(cliPos[j]+" ");
                }
                affichageCmp++;
            }


            //Affichage de la sortie
            System.out.print("\nEtape "+(affichageCmp-1)+" ("+new SasSortie().getNom()+") "+ cliPos[(nbClients+1)]+" clients : ");
            for(int j =nbClients+2;j<(nbClients+1)+cliPos[nbClients+1]+1;j++) System.out.print(cliPos[j]+" ");


            //Actualisation
            cliPos = ou_sont_les_clients(nbEtapes,nbClients);

            System.out.println();

            //Teste si tous les clients sont arrivés à la sortie (et permet d'afficher correctement la dernière sortie)
            if(cliPos[nbClients+1]==nbClients) cmp += 1;
        }

        //Retour à la ligne
        System.out.println();

        //Nettoyage
        nettoyage();
    }

    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();
}
