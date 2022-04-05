package mondeIG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Représente la classe EtapeIG.
 */
public class EtapeIG implements Iterable<PointDeControleIG>{
    private String nom,identifiant;
    private int posX, posY, largeur, hauteur;
    private ArrayList<PointDeControleIG> PdcIG;
    private boolean entree,sortie;

    /**
     * Instancie une nouvelle EtapeIG.
     * @param nom  Le nom
     * @param idf  L'identifiant
     * @param larg La largeur
     * @param haut La hauteur
     */
    public EtapeIG(String nom, String idf, int larg, int haut){
        Random r1,r2;
        r1 = new Random();
        r2 = new Random();
        this.nom = nom;
        this.identifiant = idf;
        this.largeur = larg;
        this.hauteur = haut;
        posX = r1.nextInt(1000-largeur)%(1000-largeur);
        posY = r2.nextInt(700-hauteur)%(700-hauteur);
        PdcIG = new ArrayList<>(4);
        entree = false;
        sortie = false;

        this.PdcIG.add(new PointDeControleIG(posX+this.largeur/2,posY,""+1,this));
        this.PdcIG.add(new PointDeControleIG(posX+this.largeur/2,posY+this.hauteur,""+2,this));
        this.PdcIG.add(new PointDeControleIG(posX,posY+this.hauteur/2,""+3,this));
        this.PdcIG.add(new PointDeControleIG(posX+this.largeur,posY+this.hauteur/2,""+4,this));
    }

    /**
     * Retourne l'identifiant.
     * @return L'identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Retourne le nom de l'étape.
     * @return Le nom modifié
     */
    public String getNom() {
        return nom;
    }

    /**
     * Si l'étape était une entrée alors elle ne l'est plus et inversement.
     */
    public void estUneEntree(){
        if(entree) System.out.println(nom+" n'est plus définit comme une entree");
        else System.out.println(nom+" est définit comme une entree");
        entree = !entree;
    }

    /**
     * Si l'étape était une sortie alors elle ne l'est plus et inversement.
     */
    public void estUneSortie(){
        if(sortie) System.out.println(nom+" n'est plus définit comme une sortie");
        else System.out.println(nom+" est définit comme une sortie");
        sortie = !sortie;
    }

    /**
     * Retourne la valeur d'entrée (si l'étape est une entrée ou non).
     * @return Le booléen
     */
    public boolean isEntree() {
        return entree;
    }

    /**
     * Retourne la valeur de sortie (si l'étape est une sortie ou non).
     * @return Le booléen
     */
    public boolean isSortie() {
        return sortie;
    }

    /**
     * Retourne la position en x de l'étape.
     * @return La position en x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Retourne la position en y de l'étape.
     * @return La position en y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Retourne la hauteur de l'étape.
     * @return La hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Retourne la largeur de l'étape.
     * @return La largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Modifie le nom de l'étape en fonction du nom en paramètre.
     * @param nom Le nom de l'étape
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "EtapeIG{" +
                "nom='" + nom + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                "}";
    }

    /**
     * Rend itérable les points de contrôle de l'étape.
     * @return L'itérateur des points de contrôle
     */
    public Iterator<PointDeControleIG> iterator(){
        return this.PdcIG.iterator();
    }

    /**
     * Modifie la position de l'étape et ses points de contrôle en fonction des positions rentrées en paramètre.
     * @param posX La nouvelle position en x
     * @param posY La nouvelle position en y
     */
    public void modifPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY-hauteur/2-5;
        PdcIG.get(0).setPos(this.posX+this.largeur/2,this.posY);
        PdcIG.get(1).setPos(this.posX+this.largeur/2,this.posY+this.hauteur);
        PdcIG.get(2).setPos(this.posX,this.posY+this.hauteur/2);
        PdcIG.get(3).setPos(this.posX+this.largeur,this.posY+this.hauteur/2);
    }
}