package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireSuccesseursTest {
    private GestionnaireSuccesseurs gE;
    private Etape e1,e2,e3;

    @BeforeEach
    void setUp() {
        gE = new GestionnaireSuccesseurs();
        Etape e1 = new Activite("e1");
        Etape e2 = new Activite("e2");
        Etape e3 = new Activite("e3");
    }

    @Test
    void ajouter() {
        gE.ajouter(e1);
        assertEquals(gE.nbEtapes(),1,"bug dans ajouter()");
        gE.ajouter(e2);
        assertEquals(gE.nbEtapes(),2,"bug dans ajouter()");
        gE.ajouter(e3);
        assertEquals(gE.nbEtapes(),3,"bug dans ajouter()");
        gE.ajouter(e1,e2,e3);
        assertEquals(gE.nbEtapes(),6,"bug dans ajouter()");
    }


}