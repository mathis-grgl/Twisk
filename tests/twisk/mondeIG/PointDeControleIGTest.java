package twisk.mondeIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PointDeControleIGTest {
    private EtapeIG e;
    private ArrayList<PointDeControleIG> liste;

    @BeforeEach
    void setUp() {
        e = new EtapeIG("Activite","0",500,250);
        liste = new ArrayList<>();
        for (PointDeControleIG p : e){
            liste.add(p);
        }
    }

    @Test
    void getPosY() {
        assertEquals(liste.get(0).getPosY(),e.getPosY(),"Bug dans getPosY");
        assertEquals(liste.get(1).getPosY(),e.getPosY()+e.getHauteur(),"Bug dans getPosY");
        assertEquals(liste.get(2).getPosY(),e.getPosY()+e.getHauteur()/2,"Bug dans getPosY");
        assertEquals(liste.get(3).getPosY(),e.getPosY()+e.getHauteur()/2,"Bug dans getPosY");
    }

    @Test
    void getPosX() {
        assertEquals(liste.get(0).getPosX(),e.getPosX()+e.getLargeur()/2,"Bug dans getPosX");
        assertEquals(liste.get(1).getPosX(),e.getPosX()+e.getLargeur()/2,"Bug dans getPosX");
        assertEquals(liste.get(2).getPosX(),e.getPosX(),"Bug dans getPosX");
        assertEquals(liste.get(3).getPosX(),e.getPosX()+e.getLargeur(),"Bug dans getPosX");
    }

    @Test
    void getEtape() {
        assertEquals(liste.get(0).getEtape(),e,"bug dans getEtape()");
        assertEquals(liste.get(1).getEtape(),e,"bug dans getEtape()");
        assertEquals(liste.get(2).getEtape(),e,"bug dans getEtape()");
        assertEquals(liste.get(3).getEtape(),e,"bug dans getEtape()");
    }

    @Test
    void getId() {
        assertEquals(liste.get(0).getId(),"1","bug dans getEtape()");
        assertEquals(liste.get(1).getId(),"2","bug dans getEtape()");
        assertEquals(liste.get(2).getId(),"3","bug dans getEtape()");
        assertEquals(liste.get(3).getId(),"4","bug dans getEtape()");
    }
}