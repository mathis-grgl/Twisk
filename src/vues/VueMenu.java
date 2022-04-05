package vues;

import Ecouteur.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import mondeIG.MondeIG;

/**
 * Représente le Menu en haut (sous classe de MenuBar), qui est aussi un observateur.
 */
public class VueMenu extends MenuBar implements Observateur {
    private MondeIG monde;
    private Menu Fichier, Edition, Parametres;
    private MenuItem quitter,supprimer, renommer,effacer,entree,sortie,delai;

    /**
     * Instancie une nouvelle VueMenu.
     * @param monde Le monde où le menu peut interagir
     */
    public VueMenu(MondeIG monde){
        this.monde = monde;
        this.monde.ajouterObservateur(this);

        Fichier = new Menu("Fichier");
        Edition = new Menu("Edition");
        Parametres = new Menu("Paramètres");

        quitter = new MenuItem("Quitter");
        supprimer = new MenuItem("Supprimer");
        renommer =  new MenuItem("Renommer");
        effacer = new MenuItem("Effacer la sélection");
        entree = new MenuItem("Définir comme entrée");
        sortie = new MenuItem("Définir comme sortie");
        delai = new MenuItem("Modifier les délais");

        quitter.setOnAction(new EcouteurQuitter());
        supprimer.setOnAction(new EcouteurSupprimer(this.monde));
        renommer.setOnAction(new EcouteurRenommer(this.monde));
        effacer.setOnAction(new EcouteurEffacer(this.monde));
        entree.setOnAction(new EcouteurEntree(this.monde));
        sortie.setOnAction(new EcouteurSortie(this.monde));
        delai.setOnAction(new EcouteurDelai(this.monde));


        Fichier.getItems().add(quitter);
        Edition.getItems().addAll(supprimer,effacer,entree,sortie);

        this.getMenus().addAll(Fichier,Edition,Parametres);
    }

    @Override
    public void reagir() {
        //Affiche la possibilité de renommer ou de modifier les délais d'une activité quand il y a qu'une seule étape de sélectionnée
        if(this.monde.getListeEtapesSelec().size()==1){
            if(!Edition.getItems().contains(renommer))
                Edition.getItems().add(renommer);
            if(!Parametres.getItems().contains(delai))
                Parametres.getItems().add(delai);
        }
        else {
            Edition.getItems().remove(renommer);
            Parametres.getItems().remove(delai);
        }
    }
}
