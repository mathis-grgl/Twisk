package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    private Etape ac,acR,sE,sS;
    private Guichet gui,gui2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ac = new Activite("ac");
        gui = new Guichet("gui");
        acR = new ActiviteRestreinte("acR");
        sE = new SasEntree();
        sS = new SasSortie();
        gui2 = new Guichet("gui2");
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
    void estUnGuichet() {
        assertFalse(ac.estUnGuichet(),"bug dans estUnGuichet()");
        assertTrue(gui.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(acR.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(sE.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(sS.estUnGuichet(),"bug dans estUnGuichet()");
    }

    @org.junit.jupiter.api.Test
    void toString1() {
        ac.ajouterSuccesseur(acR);
        assertEquals(ac.toString(),"ac : 1 successeur - acR");
        assertEquals(gui.toString(),"gui : 0 successeur");
        assertEquals(acR.toString(),"acR : 0 successeur");
        assertEquals(sE.toString(),"entree : 0 successeur");
        assertEquals(sS.toString(),"sortie : 0 successeur");
    }

    @org.junit.jupiter.api.Test
    void getNum1(){
        assertEquals(ac.getNum(),12);
        assertEquals(gui.getNum(),13);
        assertEquals(acR.getNum(),14);
        assertEquals(sE.getNum(),15);
        assertEquals(sS.getNum(),16);
    }

    @org.junit.jupiter.api.Test
    void getSem1(){
        assertEquals(gui.getSema(),7);
        assertEquals(gui2.getSema(),8);
    }
}