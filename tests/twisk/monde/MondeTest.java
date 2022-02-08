package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {
    private Monde monde;
    private GestionnaireEtapes gE;
    private SasEntree sE;
    private SasSortie sS;
    private Etape e1;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        gE = new GestionnaireEtapes();
        sE = new SasEntree();
        sS = new SasSortie();
        e1 = new Guichet("Guichet");
    }

    @Test
    void aCommeEntree() {
        assertEquals(monde.nbEtapes(),0);
        monde.ajouter(sE);
        assertEquals(monde.nbEtapes(),1);
    }

    @Test
    void aCommeSortie() {
        assertEquals(monde.nbEtapes(),0);
        monde.ajouter(sS);
        assertEquals(monde.nbEtapes(),1);
    }

    @Test
    void ajouter() {
        monde.ajouter(e1);
        assertEquals(monde.nbEtapes(),1,"bug dans ajouter()");
        monde.ajouter(sS);
        assertEquals(monde.nbEtapes(),2,"bug dans ajouter()");
        monde.ajouter(sE);
        assertEquals(monde.nbEtapes(),3,"bug dans ajouter()");
        monde.ajouter(e1,sS,sE);
        assertEquals(monde.nbEtapes(),6,"bug dans ajouter()");
    }

    @Test
    void nbGuichets() {
        assertEquals(monde.nbGuichets(),0);
        monde.ajouter(e1);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(sS);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(sE);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(e1);
        assertEquals(monde.nbGuichets(),2);
    }
}