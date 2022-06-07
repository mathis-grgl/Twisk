package twisk.simulation;

import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;
import twisk.vues.SujetObserve;

/**
 * Représente la classe Simulation, qui simule le monde.
 * @author Mathis GEORGEL
 * @version 1.0
 */
public class Simulation extends SujetObserve {
    /**
     * Le KitC nécessaire au bon fonctionnement de la compilation.
     */
    private KitC c;
    /**
     * Le nombre de clients qui sont dans la simulation.
     */
    private int nbClients;

    /**
     * Le gestionnaire de clients
     */
    private GestionnaireClients gC;
    /**
     *
     */
    private boolean simuEstLancee;

    /**
     * Initialise une nouvelle simulation.
     */
    public Simulation(){
        c = new KitC();
        c.creerEnvironnemment();
        nbClients = 5;
        gC = new GestionnaireClients();
        simuEstLancee = false;
    }

    /**
     * Simule le monde en affichant les étapes, les clients ; en utilisant les fonctions de KitC et en simulant main.c.
     * @see KitC
     * @param monde Le monde à simuler
     */
    public void simuler(Monde monde){
        //Définit la simulation comme lancée
        simuEstLancee = true;

        //Affichage du monde
        System.out.println(monde+"\n");
        for(Etape e : monde) System.out.println(e);
        System.out.println();

        //Partie concernant la génération des fichiers .c
        String num = FabriqueNumero.getInstance().getNouveauNoSim();
        String code = monde.toC();
        c.creerFichier(code);
        c.compiler();
        c.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk"+ num+".so");




        //Initialisation et déclarations des variables
        int nbEtapes = monde.nbEtapes();
        int nbGuichets = monde.nbGuichets();
        int[] Guichet = new int[nbGuichets];




        //Assignation des jetons au(x) différent(s) guichet(s)
        int cmpGuichet = 0;
        for (Etape e : monde) {
            if(e.estUnGuichet()){
                Guichet g = (Guichet) e;
                Guichet[cmpGuichet] = g.getNbjetons();
                cmpGuichet++;
            }
        }

        //Commencement de la simulation et initialisation du tableau contenant les ID des clients
        gC.setClients(start_simulation(nbEtapes,nbGuichets,nbClients,Guichet));



        //Affichage des clients
        System.out.print("les clients : ");
        for(int i =0;i!=gC.size();i++) System.out.print(gC.getClient(i).getNumeroClient()+" ");
        System.out.println();




        //Création et affectation du tableau de la position des clients
        int[] cliPos = ou_sont_les_clients(nbEtapes,nbClients);





        //Affichage des étapes avec le nombre de clients et les numéros des clients
        int cmp = 0;
        int posClient = 0;
        while(cmp != 2){

            //Délai d'affichage
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Affichage de toutes les étapes (de toutes les activités)
            for(Etape e : monde){
                posClient = e.getNum()*(nbClients+1);
                if(e.getNum()!=1) {
                    System.out.print("\nEtape "+e.getNum()+" ("+e.getNom()+") "+cliPos[posClient]+" clients : ");
                    int rang=1;
                    for (int j = posClient+1 ;j < posClient+cliPos[posClient]+1 ;j++) {
                        System.out.print(cliPos[j] + " ");
                        gC.allerA(cliPos[j], e, rang);
                        this.notifierObservateurs();
                        rang++;
                    }
                } else {
                    int rang=1;
                    for (int j = posClient+1 ;j < posClient+cliPos[posClient]+1 ;j++) {
                        gC.allerA(cliPos[j], e, rang);
                        this.notifierObservateurs();
                        rang++;
                    }
                }
            }

            //Affichage de la sortie
            System.out.print("\nEtape 1 (Sortie) " + cliPos[(nbClients + 1)] + " clients : ");
            for (int j = nbClients + 2; j < (nbClients + 1) + cliPos[nbClients + 1] + 1; j++)
                System.out.print(cliPos[j] + " ");



            //Actualisation
            cliPos = ou_sont_les_clients(nbEtapes,nbClients);

            System.out.println("\n"+gC.toString());

            //Teste si tous les clients sont arrivés à la sortie (et permet d'afficher correctement la dernière sortie)
            if(cliPos[nbClients+1]==nbClients) cmp += 1;

            //Actualise l'affichage
            this.notifierObservateurs();
        }

        //Retour à la ligne
        System.out.println();

        //Nettoyage
        nettoyage();
        gC.nettoyer();

        //Définit que la simulation n'est plus lancée
        simuEstLancee = false;
    }

    /**
     * Permet d'indiquer le nombre de clients dans la simulation.
     * @param nbClients Le nombre de clients
     */
    public void setNbClients(int nbClients){
        this.nbClients = nbClients;
    }

    public GestionnaireClients getGestionnaireClients() {
        return gC;
    }

    public Boolean isSimuEstLancee() {
        return simuEstLancee;
    }

    public void setSimuEstLancee(boolean simuEstLancee) {
        this.simuEstLancee = simuEstLancee;
    }

    /**
     * Commence la simulation du monde & retourne le tableau contenant les ID des clients.
     * @param nbEtapes          Le nombre d'étapes
     * @param nbServices        Le nombre de guichets
     * @param nbClients         Le nombre de clients
     * @param tabJetonsServices Le nombre de jetons dans chaque guichet
     * @return Le tableau contenant les ID des clients
     */
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    /**
     * Actualise la simulation et retourne le tableau contenant la position des clients & et le nombre de clients dans chaque étape.
     * @param nbEtapes  Le nombre d'étapes
     * @param nbClients Le nombre de clients
     * @return Le tableau contenant la position des clients et le nombre de clients dans chaque étape
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Nettoie tout correctement.
     */
    public native void nettoyage();
}
