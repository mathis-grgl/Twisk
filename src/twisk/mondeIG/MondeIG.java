package twisk.mondeIG;

import javafx.concurrent.Task;
import twisk.exceptions.MondeException;
import twisk.monde.*;
import twisk.outils.*;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;
import twisk.vues.Observateur;
import twisk.vues.SujetObserve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Représente la classe MondeIG (qui extends SujetObserve).
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur   {
    private HashMap<String,EtapeIG> hmEtape ;
    private ArrayList<ArcIG> listeArc, listeArcsSelec;
    private PointDeControleIG pSelectionne;
    private ArrayList<EtapeIG> listeEtapesSelec ;
    private ArrayList<EtapeIG> etapesEntree;
    private ArrayList<EtapeIG> etapesSortie;
    private ArrayList<ClassLoaderPerso> classLoaderPersoList;
    private Boolean simuEstLancee;
    private CorrespondanceEtapes correspondanceEtapes;
    private Object simulation;
    private String loi ="Uniforme";

    /**
     * Instancie un nouvel MondeIG.
     */
    public MondeIG() {
        hmEtape = new HashMap<>();
        ajouter("Activite");
        listeArc = new ArrayList<>();
        pSelectionne = null;
        listeEtapesSelec = new ArrayList<>();
        listeArcsSelec = new ArrayList<>();
        etapesEntree = new ArrayList<>();
        etapesSortie = new ArrayList<>();
        classLoaderPersoList = new ArrayList<>();
        simuEstLancee = false;
    }

    /**
     * Creation du monde correspondant au mondeIG.
     * @return monde correspondant
     */
    private Monde creerMonde(){

        FabriqueNumero.getInstance().reset();

        Monde monde = new Monde(loi);

        correspondanceEtapes = new CorrespondanceEtapes();

        for (EtapeIG etapeIG : hmEtape.values()){
            Etape etapeAAjouter;
            if(etapeIG.estUneActivite()){
                if (etapeIG.estUneActiviteRestreinte())
                    etapeAAjouter = new ActiviteRestreinte(etapeIG.getNomSansModification(),((ActiviteIG) etapeIG).getTemps(),((ActiviteIG) etapeIG).getEcartTemps());
                else
                    etapeAAjouter = new Activite(etapeIG.getNomSansModification(), ((ActiviteIG) etapeIG).getTemps(), ((ActiviteIG) etapeIG).getEcartTemps());
            } else
                etapeAAjouter = new Guichet(etapeIG.getNomSansModification());
            monde.ajouter(etapeAAjouter);
            correspondanceEtapes.ajouter(etapeIG,etapeAAjouter);
            if (etapeIG.estUneSortie()) monde.aCommeSortie(etapeAAjouter);
            if (etapeIG.estUneEntree()) monde.aCommeEntree(etapeAAjouter);
        }
        for(EtapeIG etapeIG : hmEtape.values()) correspondanceEtapes.correspondanceSucc(etapeIG);
        return monde;
    }

    /**
     * Ajoute une nouvelle étape au monde.
     * @param type Le type d'étape à ajouter
     */
    public void ajouter(String type){
        String id = FabriqueIdentifiant.getInstance().getIdentifiantEtape();

        switch(type){
            case "Activite":
                String idActivite = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
                String nomActivite = "Activite"+idActivite;
                hmEtape.put(id,new ActiviteIG(nomActivite,id, TailleComposant.getInstance().getLargeurAC(),TailleComposant.getInstance().getHauteurAC()));
                break;
            case "Guichet":
                String idSema = FabriqueIdentifiant.getInstance().getSemaphore();
                String nomGuichet = "Guichet"+idSema;
                hmEtape.put(id,new GuichetIG(nomGuichet,id,TailleComposant.getInstance().getLargeurGUI(),TailleComposant.getInstance().getHauteurGUI()));
                break;
            default:
                break;
        }
        notifierObservateurs();
    }

    /**
     * Crée un arc et l'ajoute à la liste des arcs du monde en fonction des deux points de contrôle en paramètre.
     * @param pt1 Premier point de contrôle
     * @param pt2 Deuxième point de contrôle
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2){
        listeArc.add(new ArcIG(pt1, pt2));
        pt1.getEtapeIG().ajouterSuccesseur(pt2.getEtapeIG());
    }

    /**
     * Permet de simuler le monde à partir de la partie graphique du monde.
     */
    public void simuler() {
        simuEstLancee = true;
        try {
            FabriqueNumero.getInstance().reset();
            verifierMondeIG();
            ClassLoaderPerso classloader = new ClassLoaderPerso(this.getClass().getClassLoader());
            classLoaderPersoList.add(classloader);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Monde m = creerMonde();
                    Class<?> classSimu = classLoaderPersoList.get(classLoaderPersoList.size() - 1).loadClass("twisk.simulation.Simulation");
                    simulation = classSimu.getConstructor().newInstance();
                    Method simuler = classSimu.getMethod("simuler", Monde.class);
                    Method mAjouterObs = classSimu.getMethod("ajouterObservateur", Observateur.class);
                    simuler.invoke(simulation, m);
                    mAjouterObs.invoke(simulation, this);
                    System.out.println("La simulation du monde a été terminé");
                    return null;
                }
            };
            ThreadsManager.getInstance().lancer(task);
        } catch (MondeException ignored) {}

        simuEstLancee = getSimuEstLancee();
    }

    /**
     * Vérifie si le monde crée dans la partie graphique du monde est correcte.
     */
    private void verifierMondeIG() throws MondeException{
        setAllActiviteRestreinteFalse();

        //le monde doit contenir au moin une entre, une sortie et une etape
        if (etapesSortie.size() < 1 || etapesEntree.size() <1 || hmEtape.size() < 1){
            throw new MondeException("Pas assez d'entrées, de sorties ou trop petit.");
        }
        int compteur = 0;
        for (EtapeIG etape : hmEtape.values()){
            for (EtapeIG succ : etape.getListSuccesseurs() ){
                if(succ.estUneActivite() && etape.estUnGuichet()){
                    ((ActiviteIG) succ).setEstUneActiviteRestreinte(true);
                }
                //Une activité ne peut pas être suivit d'une activité restreinte
                if((etape.estUneActivite() || etape.estUneActiviteRestreinte()) && succ.estUneActiviteRestreinte()) {
                    throw new MondeException("Activité suivi d'une activité restreinte.");
                }
                // Vérification qu'un guichet n'est pas suivit d'un guichet
                if(succ.estUnGuichet() && etape.estUnGuichet()){
                    throw new MondeException("Un guichet est suivi d'un guichet.");
                }
                // Vérification que chaque étape ait un successeur
                if(etape.getListSuccesseurs().size() < 1){
                    throw new MondeException("Chaque étape n'a pas un successeur.");
                }

                // Vérification qu'un guichet n'a pas plusieurs étapes qui la suivent
                if (etape.estUnGuichet() && etape.getListSuccesseurs().size() > 1) {
                    throw new MondeException("Trop d'étapes qui suivent un seul guichet.");
                }
            }

            if(etape.getListSuccesseurs().size()>=1) compteur++;
        }
        if(compteur != hmEtape.size()-1){
            throw new MondeException("Le lien n'est pas fait entre toutes les étapes.");
        }
    }

    /**
     * Modifie la position d'une étape dans la liste des étapes du monde en fonction de son identifiant et des nouvelles positions x et y.
     * @param idEtape L'identifiant de l'étape recherché.
     * @param posX    La nouvelle position en x
     * @param posY    La nouvelle position en y
     */
    public void dragnDrop(String idEtape,int posX, int posY){
        for(EtapeIG e : this){
            if(idEtape.equals(e.getIdentifiant())){
                e.modifPosition(posX,posY);
            }
        }
    }

    /**
     * Permet d'ajouter à la liste d'étapes sélectionnées une étape ou l'enlever.
     * @param etape L'étape à ajouter ou enlever
     */
    public void selectDeselect(EtapeIG etape) {
        if (listeEtapesSelec.contains(etape)) {
            listeEtapesSelec.remove(etape);
        } else {
            listeEtapesSelec.add(etape);
        }
    }

    /**
     * Permet d'ajouter à la liste d'arcs sélectionnés un arc ou l'enlever.
     * @param arc L'arc à ajouter ou enlever
     */
    public void selectDeselect(ArcIG arc) {
        if (listeArcsSelec.contains(arc)) {
            listeArcsSelec.remove(arc);
        } else {
            listeArcsSelec.add(arc);
        }
    }

    /**
     * Supprime les étapes sélectionnées et les arcs liés aux étapes dans la liste d'étapes sélectionnées et dans la liste d'étapes du monde (et remet à 0 les listes sélectionnées d'étapes et d'arcs).
     * @see #resetListeSelec()
     */
    public void supprListeEtapesSelec(){
        for(EtapeIG e : listeEtapesSelec){
            for(ArcIG arc : listeArc){
                if(e == arc.getP2().getEtapeIG()){
                    EtapeIG predecesseur = arc.getP1().getEtapeIG();
                    predecesseur.supprimerSuccesseur(e);
                }
            }

            listeArc.removeIf(arc -> e == arc.getP2().getEtapeIG() || e == arc.getP1().getEtapeIG());
            hmEtape.remove(e.getIdentifiant(),e);
        }
        for(ArcIG a : listeArcsSelec){
            listeArc.remove(a);
            EtapeIG etape = a.getP1().getEtapeIG();
            EtapeIG succ = a.getP2().getEtapeIG();
            etape.supprimerSuccesseur(succ);
        }
        resetListeSelec();
    }

    /**
     * Reset les listes sélectionnées des arcs et des étapes.
     */
    public void resetListeSelec(){
        listeArcsSelec.clear();
        listeEtapesSelec.clear();
        pSelectionne = null;
    }

    /**
     * Pour toutes les étapes sélectionnées du monde on applique la fonction estUneEntree() pour inverser la valeur entree.
     */
    public void aCommeEntree() {
        for(EtapeIG e : listeEtapesSelec){
            e.changementEtatEntree();
            etapesEntree.add(e);
        }
    }

    /**
     * Pour toutes les étapes sélectionnées du monde on applique la fonction estUneSortie() pour inverser la valeur sortie.
     */
    public void aCommeSortie() {
        for(EtapeIG e : listeEtapesSelec){
            e.changementEtatSortie();
            etapesSortie.add(e);
        }
    }

    /**
     * Permet de modifier le temps de l'activité sélectionnée en fonction du temps en paramètre.
     * @param temps Le nouveau temps
     */
    public void modifTemps(int temps) {
        ActiviteIG ac = (ActiviteIG) listeEtapesSelec.get(0);
        ac.setTemps(temps);
    }

    /**
     * Permet de modifier l'écart-temps de l'activité sélectionnée en fonction de l'écart-temps en paramètre.
     * @param ecartTemps Le nouvel écart-temps
     */
    public void modifEcartTemps(int ecartTemps) {
        ActiviteIG ac = (ActiviteIG) listeEtapesSelec.get(0);
        ac.setEcartTemps(ecartTemps);
    }

    /**
     * Permet de modifier le nombre de jetons d'un guichet sélectionné en fonction du nombre en paramètre.
     * @param nbjetons Le nouveau nombre de jetons
     */
    public void modifNbJetons(int nbjetons){
        GuichetIG gui = (GuichetIG) listeEtapesSelec.get(0);
        gui.setNbJetons(nbjetons);
    }

    /**
     * Retourne le nombre d'étapes dans le monde.
     * @return Nb étapes
     */
    public int getSize(){
        return hmEtape.size();
    }

    /**
     * Retourne le nombre d'arcs dans le monde.
     * @return Nb arcs.
     */
    public int getSizeArc(){
        return listeArc.size();
    }

    /**
     * Retourne la liste des étapes sélectionnées.
     * @return La liste d'étapes
     */
    public ArrayList<EtapeIG> getListeEtapesSelec() {
        return listeEtapesSelec;
    }

    /**
     * Retourne la liste des arcs sélectionnés.
     * @return La liste d'arcs
     */
    public ArrayList<ArcIG> getListeArcsSelec() {
        return listeArcsSelec;
    }


    /**
     * Modifie la valeur de la variable mémoire pSelectionne du monde.
     * @param pSelectionne La nouvelle valeur de pSelectionne
     */
    public void setpSelectionne(PointDeControleIG pSelectionne) {
        this.pSelectionne = pSelectionne;
    }

    /**
     * Remet toutes les activités du Monde en tant qu'activités classiques.
     */
    public void setAllActiviteRestreinteFalse(){
        for(EtapeIG e : hmEtape.values()){
            if(e.estUneActivite()) ((ActiviteIG) e).setEstUneActiviteRestreinte(false);
        }
    }

    /**
     * Retourne le point sélectionné du monde.
     * @return Le point sélectionné
     */
    public PointDeControleIG getpSelectionne() {
        return pSelectionne;
    }

    public CorrespondanceEtapes getCorrespondanceEtapes() {
        return correspondanceEtapes;
    }

    /**
     * Retourne le nombre de clients du monde.
     * @return la liste de clients
     */
    public ArrayList<Client> getClients(){
        GestionnaireClients gestionnaireClients = null;
        if(simulation != null) {
            try {
                Method getGesClients = simulation.getClass().getMethod("getGestionnaireClients");
                gestionnaireClients = (GestionnaireClients) getGesClients.invoke(simulation);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return Objects.requireNonNull(gestionnaireClients).getListClients();
    }

    public void setNbClients(int nbClients){
        GestionnaireClients gestionnaireClients = null;
        if(simulation != null) {
            try {
                Method getGesClients = simulation.getClass().getMethod("getGestionnaireClients");
                gestionnaireClients = (GestionnaireClients) getGesClients.invoke(simulation);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(gestionnaireClients.size());
            Objects.requireNonNull(gestionnaireClients).setNbClients(nbClients);
        }
    }

    /**
     * Retourne si la simulation est lancée depuis la classe Simulation.java.
     * @return Vrai si la simulation est lancée
     */
    public Boolean getSimuEstLancee() {
        boolean isStarted = false;
        if(simulation != null) {
            try {
                Method isSimuStarted = simulation.getClass().getDeclaredMethod("isSimuEstLancee");
                isStarted = (boolean) isSimuStarted.invoke(simulation);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isStarted;
    }

    /**
     * Définit dans la classe Simulation si la simulation est lancée
     */
    public void setSimStarted(boolean nouveauBooleen) {
        try {
            Method setSimuStarted = simulation.getClass().getDeclaredMethod("setSimuEstLancee", boolean.class);
            setSimuStarted.invoke(simulation, nouveauBooleen);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getSimulation() {
        return simulation;
    }

    public void stopSimulation() {
        simuEstLancee = getSimuEstLancee();
        if(simulation != null && simuEstLancee) {
            setSimStarted(false);
            simuEstLancee = false;
            ThreadsManager.getInstance().detruireTout();
        }
    }

    /**
     * setter loi
     * @param loi nouvelle loi
     */
    public void setLoi(String loi) {
        this.loi = loi;
    }

    /**
     * Rend itérable les étapes du monde.
     * @return L'itérateur des étapes
     */
    public Iterator<EtapeIG> iterator() {
        return hmEtape.values().iterator();
    }


    /**
     * Rend itérable les arcs du monde.
     * @return L'itérateur des arcs
     */
    public Iterator<ArcIG> iteratorArc(){
        return listeArc.iterator();
    }

    @Override
    public void reagir() {
    }
}
