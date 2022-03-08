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
    void toC1(){
        ac.ajouterSuccesseur(acR);
        acR.ajouterSuccesseur(gui);
        sE.ajouterSuccesseur(ac);
        gui.ajouterSuccesseur(ac);
        gui2.ajouterSuccesseur(acR);
        assertEquals(sE.toC(),"entrer(entree);\n" +
                "delai(4,2);\n" +
                "transfert(ac,acR);\n" +
                "delai(4,2);\n","Bug dans toC1");
        assertEquals(ac.toC(),"transfert(ac,acR);\n" + "delai(4,2);\n","Bug dans toC1");
        assertEquals(gui.toC(),"P(ids,1);\n" +
                "transfert(gui,ac);\n" +
                "V(ids,1);\n","Bug dans toC1");
        assertEquals(gui2.toC(),"P(ids,2);\n" +
                "transfert(gui2,acR);\n" +
                "V(ids,2);\n","Bug dans toC1");
        assertEquals(acR.toC(),"transfert(acR,gui);\n" +"delai(4,2);\n","Bug dans toC1");
    }
}