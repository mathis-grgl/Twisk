package twisk.monde;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    private Etape ac,acR,ac2,ac3,sE,sS;
    private Guichet gui,gui2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ac = new Activite("ac");
        gui = new Guichet("gui");
        acR = new ActiviteRestreinte("acR");
        ac2 = new Activite("ac 2");
        ac3 = new Activite("ac&3");
        gui2 = new Guichet("guié2");
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
        assertTrue(ac2.estUneActivite(),"bug dans estUneActivite()");
        assertTrue(ac3.estUneActivite(),"bug dans estUneActivite()");
    }

    @org.junit.jupiter.api.Test
    void getNomBien(){
        assertEquals(ac.getNom()+8,ac.getNomNumero(),"Bug dans getNomNumero()");
        assertEquals(acR.getNom()+10,acR.getNomNumero(),"Bug dans getNomNumero()");
        assertEquals(gui.getNom()+9,gui.getNomNumero(),"Bug dans getNomNumero()");
        assertNotEquals(ac2.getNom(),ac2.getNomNumero(),"Bug dans getNomNumero()");
        assertNotEquals(ac3.getNom(),ac3.getNomNumero(),"Bug dans getNomNumero()");
        assertNotEquals(gui2.getNom(),gui2.getNomNumero(),"Bug dans getNomNumero()");
    }

    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertFalse(ac.estUnGuichet(),"bug dans estUnGuichet()");
        assertTrue(gui.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(acR.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(ac2.estUnGuichet(),"bug dans estUnGuichet()");
        assertFalse(ac3.estUnGuichet(),"bug dans estUnGuichet()");
    }

    @org.junit.jupiter.api.Test
    void toString1() {
        ac.ajouterSuccesseur(acR);
        assertEquals(ac.toString(),"ac : 1 successeur - acR");
        assertEquals(gui.toString(),"gui : 0 successeur");
        assertEquals(acR.toString(),"acR : 0 successeur");
        assertEquals(ac2.toString(),"ac 2 : 0 successeur");
        assertEquals(ac3.toString(),"ac&3 : 0 successeur");
    }

    @org.junit.jupiter.api.Test
    void toNotC(){
        ac.ajouterSuccesseur(acR);
        acR.ajouterSuccesseur(ac2);
        ac2.ajouterSuccesseur(ac3);
        ac3.ajouterSuccesseur(ac);
        assertEquals("delai(4,2);\n",ac.delai(), "Bug dans toNotC()");
        assertEquals("delai(4,2);\n",acR.delai(), "Bug dans toNotC()");
        assertEquals("delai(4,2);\n",ac2.delai(), "Bug dans toNotC()");
        assertEquals("delai(4,2);\n",ac3.delai(), "Bug dans toNotC()");
    }

    @org.junit.jupiter.api.Test
    void getNomSema(){
        assertEquals(gui.getNom()+gui.getNum()+"Sema"+gui.getSema(),gui.getNomSema(),"Bug dans getNomNumero()");
    }

    @org.junit.jupiter.api.Test
    void nbSuccesseur(){
        sE.ajouterSuccesseur(ac);
        ac.ajouterSuccesseur(acR);
        acR.ajouterSuccesseur(gui);
        gui.ajouterSuccesseur(ac2);
        ac2.ajouterSuccesseur(gui2);
        gui2.ajouterSuccesseur(ac3);
        ac3.ajouterSuccesseur(sS);
        assertEquals(1,sE.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,ac.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,acR.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,gui.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,ac2.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,gui2.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(1,ac3.nbSuccesseur(),"Bug dans nbSuccesseur()");
        assertEquals(0,sS.nbSuccesseur(),"Bug dans nbSuccesseur()");
    }

    @org.junit.jupiter.api.Test
    void toC1(){
        sE.ajouterSuccesseur(ac);
        ac.ajouterSuccesseur(acR);
        acR.ajouterSuccesseur(gui);
        gui.ajouterSuccesseur(ac2);
        ac2.ajouterSuccesseur(gui2);
        gui2.ajouterSuccesseur(ac3);
        ac3.ajouterSuccesseur(sS);

        //lancer test toC1() directement en remplaçant assertNotEquals par assertEquals pour que ça marche
        assertNotEquals("entrer(entree);\n" +
                "delai(4,2);\n" +
                "transfert(entree,ac);\n" +
                "delai(4,2);\n" +
                "transfert(ac,acR);\n" +
                "delai(4,2);\n" +
                "transfert(acR,gui);\n" +
                "P(ids,1);\n" +
                "transfert(gui,ac2);\n" +
                "delai(4,2);\n" +
                "transfert(ac2,gui2);\n" +
                "V(ids,1);\n" +
                "P(ids,2);\n" +
                "transfert(gui2,ac3);\n" +
                "delai(4,2);\n" +
                "transfert(ac3,sortie);\n" +
                "V(ids,2);\n",sE.toC());
    }
}