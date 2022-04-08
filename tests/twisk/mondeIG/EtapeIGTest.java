package twisk.mondeIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtapeIGTest {
    private EtapeIG e,e1,e2;
    private ActiviteIG ac;

    @BeforeEach
    void setUp() {
        e = new ActiviteIG("Etap","0",100,50);
        e1 = new ActiviteIG("Etap","2",100,50);
        e2 = new ActiviteIG("Etap","3",100,50);
        ac = new ActiviteIG("Ac","1",100,50);
    }

    @Test
    void estUneEntree() {
        e.changementEtatEntree();
        e1.changementEtatEntree();
        e2.changementEtatEntree();
        ac.changementEtatEntree();
        assertTrue(e.estUneEntree(),"Bug dans estUneEntree");
        assertTrue(e1.estUneEntree(),"Bug dans estUneEntree");
        assertTrue(e2.estUneEntree(),"Bug dans estUneEntree");
        assertTrue(ac.estUneEntree(),"Bug dans estUneEntree");
        e.changementEtatEntree();
        e1.changementEtatEntree();
        e2.changementEtatEntree();
        ac.changementEtatEntree();
        assertFalse(e.estUneEntree(),"Bug dans estUneEntree");
        assertFalse(e1.estUneEntree(),"Bug dans estUneEntree");
        assertFalse(e2.estUneEntree(),"Bug dans estUneEntree");
        assertFalse(ac.estUneEntree(),"Bug dans estUneEntree");
    }

    @Test
    void estUneSortie() {
        e.changementEtatSortie();
        e1.changementEtatSortie();
        e2.changementEtatSortie();
        ac.changementEtatSortie();
        assertTrue(e.estUneSortie(),"Bug dans estUneSortie");
        assertTrue(e1.estUneSortie(),"Bug dans estUneSortie");
        assertTrue(e2.estUneSortie(),"Bug dans estUneSortie");
        assertTrue(ac.estUneSortie(),"Bug dans estUneSortie");
        e.changementEtatSortie();
        e1.changementEtatSortie();
        e2.changementEtatSortie();
        ac.changementEtatSortie();
        assertFalse(e.estUneSortie(),"Bug dans estUneSortie");
        assertFalse(e1.estUneSortie(),"Bug dans estUneSortie");
        assertFalse(e2.estUneSortie(),"Bug dans estUneSortie");
        assertFalse(ac.estUneSortie(),"Bug dans estUneSortie");
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