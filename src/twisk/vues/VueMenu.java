package twisk.vues;

import twisk.ecouteur.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.mondeIG.MondeIG;

/**
 * Représente le Menu en haut (sous classe de MenuBar), qui est aussi un observateur.
 */
public class VueMenu extends MenuBar implements Observateur {
    private MondeIG monde;
    private Menu Fichier, Edition, Monde, Parametres;
    private MenuItem quitter,supprimer, renommer,effacer,entree,sortie,delai,nbjetons;

    /**
     * Instancie une nouvelle VueMenu.
     * @param monde Le monde où le menu peut interagir
     */
    public VueMenu(MondeIG monde){
        this.monde = monde;
        this.monde.ajouterObservateur(this);

        Fichier = new Menu("Fichier");
        Edition = new Menu("Edition");
        Monde = new Menu("Monde");
        Parametres = new Menu("Paramètres");

        quitter = new MenuItem("Quitter");
        supprimer = new MenuItem("Supprimer");
        renommer =  new MenuItem("Renommer");
        effacer = new MenuItem("Effacer la sélection");
        entree = new MenuItem("Définir comme entrée");
        sortie = new MenuItem("Définir comme sortie");
        delai = new MenuItem("Modifier les délais");
        nbjetons = new MenuItem("Modifier le nombre de jetons");

        quitter.setOnAction(new EcouteurQuitter());
        supprimer.setOnAction(new EcouteurSupprimer(this.monde));
        renommer.setOnAction(new EcouteurRenommer(this.monde));
        effacer.setOnAction(new EcouteurEffacer(this.monde));
        entree.setOnAction(new EcouteurEntree(this.monde));
        sortie.setOnAction(new EcouteurSortie(this.monde));
        delai.setOnAction(new EcouteurDelai(this.monde));
        nbjetons.setOnAction(new EcouteurJetons(this.monde));


        Fichier.getItems().add(quitter);
        Edition.getItems().addAll(supprimer,effacer);
        Monde.getItems().addAll(entree,sortie);

        this.getMenus().addAll(Fichier,Edition,Monde,Parametres);
    }

    @Override
    public void reagir() {
        //Affiche la possibilité de renommer ou de modifier les délais d'une activité quand il y a qu'une seule étape de sélectionnée
        if(this.monde.getListeEtapesSelec().size()==1){
            if(!Edition.getItems().contains(renommer))
                Edition.getItems().add(renommer);
            if(monde.getListeEtapesSelec().get(0).estUnGuichet()) {
                if (!Parametres.getItems().contains(nbjetons))
                    Parametres.getItems().add(nbjetons);
            } else {
                if (!Parametres.getItems().contains(delai))
                    Parametres.getItems().add(delai);
            }
        }
        else {
            Edition.getItems().remove(renommer);
            Parametres.getItems().remove(delai);
            Parametres.getItems().remove(nbjetons);
        }
    }
}
