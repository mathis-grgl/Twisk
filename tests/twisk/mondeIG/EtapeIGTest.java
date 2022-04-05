package twisk.mondeIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EtapeIGTest {
    private EtapeIG e,e1,e2;
    private ActiviteIG ac;

    @BeforeEach
    void setUp() {
        e = new EtapeIG("Etap","0",100,50);
        e1 = new EtapeIG("Etap","2",100,50);
        e2 = new EtapeIG("Etap","3",100,50);
        ac = new ActiviteIG("Ac","1",100,50);
    }

    @Test
    void estUneEntree() {
        e.estUneEntree();
        e1.estUneEntree();
        e2.estUneEntree();
        ac.estUneEntree();
        assertTrue(e.isEntree(),"Bug dans estUneEntree");
        assertTrue(e1.isEntree(),"Bug dans estUneEntree");
        assertTrue(e2.isEntree(),"Bug dans estUneEntree");
        assertTrue(ac.isEntree(),"Bug dans estUneEntree");
        e.estUneEntree();
        e1.estUneEntree();
        e2.estUneEntree();
        ac.estUneEntree();
        assertFalse(e.isEntree(),"Bug dans estUneEntree");
        assertFalse(e1.isEntree(),"Bug dans estUneEntree");
        assertFalse(e2.isEntree(),"Bug dans estUneEntree");
        assertFalse(ac.isEntree(),"Bug dans estUneEntree");
    }

    @Test
    void estUneSortie() {
        e.estUneSortie();
        e1.estUneSortie();
        e2.estUneSortie();
        ac.estUneSortie();
        assertTrue(e.isSortie(),"Bug dans estUneSortie");
        assertTrue(e1.isSortie(),"Bug dans estUneSortie");
        assertTrue(e2.isSortie(),"Bug dans estUneSortie");
        assertTrue(ac.isSortie(),"Bug dans estUneSortie");
        e.estUneSortie();
        e1.estUneSortie();
        e2.estUneSortie();
        ac.estUneSortie();
        assertFalse(e.isSortie(),"Bug dans estUneSortie");
        assertFalse(e1.isSortie(),"Bug dans estUneSortie");
        assertFalse(e2.isSortie(),"Bug dans estUneSortie");
        assertFalse(ac.isSortie(),"Bug dans estUneSortie");
    }

    @Test
    void iterator() {
        int cpt=0;
        for(PointDeControleIG p : e)
            cpt++;
        assertEquals(cpt,4,"Bug dans iterator");
        for(PointDeControleIG p : e1)
            cpt++;
        assertEquals(cpt,8,"Bug dans iterator");
        for(PointDeControleIG p : e2)
            cpt++;
        assertEquals(cpt,12,"Bug dans iterator");
        for(PointDeControleIG p : ac)
            cpt++;
        assertEquals(cpt,16,"Bug dans iterator");
    }

    @Test
    void modifPosition() {
        assertNotEquals(e.getPosX(),200,"Bug dans modifPosition");
        assertNotEquals(e.getPosY(),150,"Bug dans modifPosition");
        e.modifPosition(200,150);
        assertEquals(e.getPosX(),200,"Bug dans modifPosition");
        assertEquals(e.getPosY(),150-e.getHauteur()/2-5,"Bug dans modifPosition");
        assertNotEquals(e1.getPosX(),200,"Bug dans modifPosition");
        assertNotEquals(e1.getPosY(),150-e.getHauteur()/2-5,"Bug dans modifPosition");
        e1.modifPosition(200,150);
        assertEquals(e1.getPosX(),200,"Bug dans modifPosition");
        assertEquals(e1.getPosY(),150-e.getHauteur()/2-5,"Bug dans modifPosition");
    }
}