package Ecouteur;

import exceptions.TwiskException;
import exceptions.arcException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import mondeIG.MondeIG;
import mondeIG.PointDeControleIG;

/**
 * Représente l'écouteur permettant de sélectionner des points de contrôle et créer des arcs.
 */
public class EcouteurPointDeControle implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private PointDeControleIG point;

    /**
     * Instancie un nouveau EcouteurPointDeControle.
     * @param monde Le monde
     * @param point Le point de contrôle en question
     */
    public EcouteurPointDeControle(MondeIG monde, PointDeControleIG point) {
        this.monde = monde;
        this.point = point;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        //Garde en mémoire les points sélectionnés et permet d'utiliser la fonction ajouter() de deux points de controle.
            System.out.println("Le point sélectionné est le " + point.getId()+ " de l'étape "+ point.getEtape().getNom());

        //S'il n'y a aucun point en mémoire
        if (monde.getpSelectionne() == null) {

            //On ajoute le point en mémoire dans la première variable mémoire.
            monde.setpSelectionne(point);
        }
        else {
            if (monde.getpSelectionne().getEtape().getIdentifiant().equals(point.getEtape().getIdentifiant())) {
                monde.setpSelectionne(null);
                try {
                    throw new arcException();
                } catch (TwiskException e) {
                    e.printStackTrace();
                }
            } else {
                
                //On utilise la fonction ajouter pour crée l'arc en fonction des deux points en mémoire.
                monde.ajouter(monde.getpSelectionne(), point);

                // On le rend null afin de pouvoir le réutiliser par la suite
                monde.setpSelectionne(null);
            }
        }
        this.monde.notifierObservateurs();
    }
}
