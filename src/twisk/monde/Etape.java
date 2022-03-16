package twisk.monde;

import twisk.outils.FabriqueNumero;
import java.util.Iterator;

/**
 * Représente la classe abstraite Etape, qui est itérable (avec les fonctions, constructeurs et variables utiles pour les classes héritées).
 * @author Mathis GEORGEL
 * @version 1.0
 */
public abstract class Etape implements Iterable<Etape> {
    /**
     * Le nom de l'étape.
     */
    protected String nom;
    /**
     * Le numéro de l'étape.
     */
    protected int num;
    /**
     * Le gestionnaire de successeurs de l'étape.
     */
    protected GestionnaireSuccesseurs gestSucc;

    /**
     * Initialise une nouvelle étape.
     * @param nom Le nom de l'étape
     * @see FabriqueNumero
     */
    public Etape(String nom){
        this.nom = nom;
        this.gestSucc = new GestionnaireSuccesseurs();
        this.num = FabriqueNumero.getInstance().getNumeroEtape();
    }

    /**
     * Ajoute un successeur à l'étape.
     * @param e la ou les étapes à ajouter en successeur(s) de l'étape
     */
    public void ajouterSuccesseur(Etape... e){
        gestSucc.ajouter(e);
    }

    /**
     * Indique si l'étape est une activité.
     * @return Le booléen
     */
    public abstract boolean estUneActivite();

    /**
     * Indique si l'étape est un guichet.
     * @return Le booléen
     */
    public abstract boolean estUnGuichet();

    /** Retourne l'itérateur du gestionnaire de successeur.
     * @return L'itérateur de successeurs
     */
    public Iterator<Etape> iterator(){
        return gestSucc.iterator();
    }

    /**
     * Permet d'avoir le nom de l'étape sans espace ou caractère spéciaux afin de créer des noms de variables dans client.c (#define ...).
     * @return Le nom modifié
     */
    public String getNomBien() {
        String copieNom = nom;
        copieNom = copieNom.replaceAll("(\\s)|(\\W)","");
        return copieNom;
    }

    /**
     * Retourne le nom de l'étape.
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le nombre de successeurs de l'étape.
     * @return Le nombre de successeurs
     */
    public int nbSuccesseur(){
        return gestSucc.nbEtapes();
    }

    /**
     * Retourne une chaîne de caractères contenant le nom de l'étape & la chaîne de caractères générée par le gestionnaire de successeurs.
     * @return Le nom + toString de Gestionnaire de successeurs
     */
    public String toString(){return nom +" : "+ gestSucc.toString();}

    /**
     * Génère un morceau de code pour client.c qui n'appelle aucune autre fonction toC().
     * @return Une chaîne de caractère (String)
     */
    public abstract String toNonC();

    /**
     * Génère un morceau de code pour client.c.
     * @return Une chaîne de caractère (String)
     */
    public abstract String toC();
}
