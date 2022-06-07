package twisk.mondeIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptions.MondeException;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MondeIGTest {
    private MondeIG monde;


    @BeforeEach
    void setUp() {
        monde = new MondeIG();
    }

    @Test
    void ajouter() {
        assertEquals(monde.getSize(),1,"Bug dans ajouter");
        monde.ajouter("Activite");
        assertEquals(monde.getSize(),2,"Bug dans ajouter");
        monde.ajouter("Activite");
        assertEquals(monde.getSize(),3,"Bug dans ajouter");
        monde.ajouter("Activite");
        assertEquals(monde.getSize(),4,"Bug dans ajouter");
    }
    
    @Test
    void ajouter1() {
        try {
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            assertEquals(monde.getSizeArc(), 1, "Bug dans ajouter");
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            assertEquals(monde.getSizeArc(), 2, "Bug dans ajouter");
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            assertEquals(monde.getSizeArc(), 3, "Bug dans ajouter");
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            assertEquals(monde.getSizeArc(), 4, "Bug dans ajouter");
        } catch (MondeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void iterator() {
        int cpt=0;
        monde.ajouter("Activite");
        monde.ajouter("Activite");
        monde.ajouter("Activite");
        monde.ajouter("Activite");
        Iterator<EtapeIG> it = monde.iterator();
        while(it.hasNext()){
            cpt++;
            it.next();
        }
        assertEquals(cpt,5,"Bug dans iterator");
    }

    @Test
    void iteratorArc(){
        int cpt=0;
        try {
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
            monde.ajouter(new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)), new PointDeControleIG(100, 100, "0", new ActiviteIG("DD", "DD", 100, 100)));
        } catch (MondeException e) {
            e.printStackTrace();
        }
        Iterator<ArcIG> ite = monde.iteratorArc();
        while(ite.hasNext()){
            cpt++;
            ite.next();
        }
        assertEquals(cpt,4,"Bug dans iterator");
    }

    @Test
    void selectDeselect(){
        ArcIG arc = new ArcIG(new PointDeControleIG(100,100,"0",new ActiviteIG("DD","DD",100,100)),new PointDeControleIG(100,100,"0",new ActiviteIG("DD","DD",100,100)));
        monde.selectDeselect(arc);
        assertEquals(1,monde.getListeArcsSelec().size(),"Bug dans selectDeselect");
        monde.selectDeselect(arc);
        assertEquals(0,monde.getListeArcsSelec().size(),"Bug dans selectDeselect");
    }

    @Test
    void supprListeEtapesSelec() {
        monde.ajouter("Activite");
        monde.ajouter("Activite");
        monde.ajouter("Activite");
        for(EtapeIG e : monde)
            monde.getListeEtapesSelec().add(e);
        assertEquals(4,monde.getSize(),"Bug dans supprListeSelec()");
        monde.supprListeEtapesSelec();
        assertEquals(0,monde.getSize(),"Bug dans supprListeSelec()");
    }

    @Test
    void resetListeSelec(){
        monde.selectDeselect(new ActiviteIG("DD","DD",100,100));
        monde.selectDeselect(new ActiviteIG("DD","DD",100,100));
        monde.selectDeselect(new ActiviteIG("DD","DD",100,100));
        assertEquals(3,monde.getListeEtapesSelec().size(),"Bug dans resetListeSelec()");
        monde.resetListeSelec();
        assertEquals(0,monde.getListeEtapesSelec().size(),"Bug dans resetListeSelec()");
    }
}