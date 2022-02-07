package twisk.monde;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    Etape ac,gui,acR,sE,sS;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ac = new Activite("ac");
        gui = new Guichet("gui");
        acR = new ActiviteRestreinte("acR");
        sE = new SasEntree();
        sS = new SasSortie();
    }

    @org.junit.jupiter.api.Test
    void ajouterSuccesseur() {
        assertEquals(ac.nbSuccesseur(),0,"bug dans nbSuccesseur()");
        ac.ajouterSuccesseur(acR);
        assertEquals(ac.nbSuccesseur(),1,"bug dans nbSuccesseur()");

    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertTrue(ac.estUneActivite(),"bug dans estUneActivite()");
        assertFalse(gui.estUneActivite(),"bug dans estUneActivite()");
        assertTrue(acR.estUneActivite(),"bug dans estUneActivite()");
        assertTrue(sE.estUneActivite(),"bug dans estUneActivite()");
        assertTrue(sS.estUneActivite(),"bug dans estUneActivite()");
    }

    @org.junit.jupiter.api.Test
    void estUnGuicher() {
        assertTrue(ac.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(gui.estUnGuichet(),"bug dans estUnGuichet()");
        assertTrue(acR.estUnGuichet(),"bug dans estUnGuichet()");
        assertTrue(sE.estUnGuichet(),"bug dans estUnGuichet()");
        assertTrue(sS.estUnGuichet(),"bug dans estUnGuichet()");
    }
}