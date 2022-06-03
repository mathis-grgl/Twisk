package twisk.mondeIG;

import twisk.exceptions.MondeException;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.FabriqueNumero;
import twisk.outils.TailleComposant;
import twisk.vues.SujetObserve;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Représente la classe MondeIG (qui extends SujetObserve).
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG>   {
    private HashMap<String,EtapeIG> hmEtape ;
    private ArrayList<ArcIG> listeArc, listeArcsSelec;
    private PointDeControleIG pSelectionne;
    private ArrayList<EtapeIG> listeEtapesSelec ;
    private ArrayList<EtapeIG> etapesEntree;
    private ArrayList<EtapeIG> etapesSortie;
    private ArrayList<ClassLoaderPerso> classLoaderPersoList;
    private CorrespondanceEtapes correspondance;
    private Boolean estLancee;

    /**
     * Instancie un nouvel MondeIG.
     * Creation du monde
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
        estLancee = false;
    }

    /**
     * Creation du monde
     * @return monde
     */
    private Monde creerMonde(){
        Monde monde = new Monde();
        correspondance = new CorrespondanceEtapes();
        for (EtapeIG etapeIG : hmEtape.values()){
            Etape aAjouter = null;
            if(etapeIG.estUneActivite()){
                if (etapeIG.estUneActiviteRestreinte()){
                    aAjouter = new ActiviteRestreinte(etapeIG.getNom(),((ActiviteIG) etapeIG).getTemps(),((ActiviteIG) etapeIG).getEcartTemps());
                }else {
                    aAjouter = new Activite(etapeIG.getNom(), ((ActiviteIG) etapeIG).getTemps(), ((ActiviteIG) etapeIG).getEcartTemps());
                }
            }else {
                aAjouter = new Guichet(etapeIG.getNom());
            }
            monde.ajouter(aAjouter);
            correspondance.ajouter(etapeIG,aAjouter);
            if (etapeIG.estUneSortie()){
                monde.aCommeSortie(aAjouter);
            }
            if (etapeIG.estUneEntree()){
                monde.aCommeEntree(aAjouter);
            }
            correspondance.correspondanceSucc(etapeIG);
        }
        return monde;
    }

    /**
     * Ajouter une nouvelle étape au monde.
     * @param type Le type d'étape à ajouter
     */
    public void ajouter(String type){
        String id = FabriqueIdentifiant.getInstance().getIdentifiantEtape();

        switch(type){
            case "Activite":
                String idActivite = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
                String nomActivite = "Activite"+idActivite;
                hmEtape.put(id,new ActiviteIG(nomActivite,id, TailleComposant.getInstance().getLargeurAC(),TailleComposant.getInstance().getHauteurAC()));
                System.out.println("Activité ajoutée");
                break;
            case "Guichet":
                String idSema = FabriqueIdentifiant.getInstance().getSemaphore();
                String nomGuichet = "Guichet"+idSema;
                hmEtape.put(id,new GuichetIG(nomGuichet,id,TailleComposant.getInstance().getLargeurGUI(),TailleComposant.getInstance().getHauteurGUI()));
                System.out.println("Guichet ajouté");
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
        System.out.println("Nouvelle arc crée entre l'étape "+listeArc.get(listeArc.size() - 1).getP1().getEtapeIG().getNom()+" et l'étape "+listeArc.get(listeArc.size() - 1).getP2().getEtapeIG().getNom());
        pt1.getEtapeIG().ajouterSuccesseur(pt2.getEtapeIG());
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
     * Modifie la valeur de la variable mémoire pSelectionne du monde.
     * @param pSelectionne La nouvelle valeur de pSelectionne
     */
    public void setpSelectionne(PointDeControleIG pSelectionne) {
        this.pSelectionne = pSelectionne;
    }

    /**
     * Retourne le point sélectionné du monde.
     * @return Le point sélectionné
     */
    public PointDeControleIG getpSelectionne() {
        return pSelectionne;
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
            listeArc.removeIf(a -> e.getIdentifiant().equals(a.getP1().getEtapeIG().getIdentifiant()) || e.getIdentifiant().equals(a.getP2().getEtapeIG().getIdentifiant()));
            hmEtape.remove(e.getIdentifiant(),e);
        }
        for(ArcIG a : listeArcsSelec){
            listeArc.remove(a);
        }
        resetListeSelec();
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
     * Reset les listes sélectionnées des arcs et des étapes.
     */
    public void resetListeSelec(){
        listeArcsSelec.clear();
        listeEtapesSelec.clear();
        pSelectionne = null;
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
     * Permet la simulation du monde apres l'affichage graphique
     */
    public void simuler() throws MondeException {
        verifierMondeIG();
        Monde m = creerMonde();
        try{
            ClassLoaderPerso classloader = new ClassLoaderPerso(this.getClass().getClassLoader());
            classLoaderPersoList.add(classloader);
            Class<?> classSimu = classLoaderPersoList.get(classLoaderPersoList.size()-1).loadClass("twisk.simulation.Simulation");
            Object sim = classSimu.getConstructor().newInstance();
            Method simuler = classSimu.getMethod("simuler",Monde.class);
            //Method mAjouterObs = classSimu.getDeclaredMethod("ajouterObservateur", twisk.vues.Observateur.class);
            //mAjouterObs.invoke(sim,this);
            simuler.invoke(sim,m);
            System.out.println("La simulation du monde a été terminé");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifie si MondeIG est totalement correct
     */
    private void verifierMondeIG() throws MondeException{
        //le monde doit contenir au moin une entre, une sortie et une etape
        if (etapesSortie.size() < 1 || etapesEntree.size() <1 || hmEtape.size() < 1){
            throw new MondeException();
        }
        for (EtapeIG etape : hmEtape.values()){
            for (EtapeIG succ : etape.getSuccesseur() ){
                if(succ.estUneActivite() && etape.estUnGuichet()){
                    ((ActiviteIG) succ).setEstUnActiviteRestreinte();
                }
                //une activiter ne peut pas etre suivie par une activite restrainte
                if((etape.estUneActivite() || etape.estUneActiviteRestreinte()) && succ.estUneActiviteRestreinte()) {
                    throw new MondeException();
                }
                // Vérification qu'un guichet n'est pas suivis d'un guichet@
                if(succ.estUnGuichet() && etape.estUnGuichet()){
                    throw new MondeException();
                }
                // Tout les activités ont au moins un successeurs !
                if(etape.getSuccesseur().size() < 1){
                    throw new MondeException();
                }

                if (etape.estUnGuichet() && etape.getSuccesseur().size() > 1) {
                    throw new MondeException();
                }
            }
        }
    }

    public Boolean getEstLancee() {
        boolean copie = estLancee;
        estLancee = !estLancee;
        System.out.println(copie);
        return copie;
    }
}
