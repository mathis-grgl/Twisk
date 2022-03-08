package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {
    private Monde monde;
    private GestionnaireEtapes gE;
    private SasEntree sE;
    private SasSortie sS;
    private Etape e1,e2,e3;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        gE = new GestionnaireEtapes();
        sE = new SasEntree();
        sS = new SasSortie();
        e1 = new Guichet("Guichet");
        e2 = new Activite("Activite1");
        e3 = new Activite("Activite2");
    }

    @Test
    void aCommeEntree() {
        assertEquals(monde.nbEtapes(),2);
        monde.aCommeEntree(e1);
        assertEquals(monde.nbEtapes(),3);
    }

    @Test
    void aCommeSortie() {
        assertEquals(monde.nbEtapes(),2);
        monde.aCommeSortie(e1);
        assertEquals(monde.nbEtapes(),3);
    }

    @Test
    void ajouter() {
        monde.ajouter(e1);
        assertEquals(monde.nbEtapes(),3,"bug dans ajouter()");
        monde.ajouter(sS);
        assertEquals(monde.nbEtapes(),4,"bug dans ajouter()");
        monde.ajouter(sE);
        assertEquals(monde.nbEtapes(),5,"bug dans ajouter()");
        monde.ajouter(e1,sS,sE);
        assertEquals(monde.nbEtapes(),8,"bug dans ajouter()");
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

    @org.junit.jupiter.api.Test
    void toC1(){
        monde.aCommeEntree(sE);
        sE.ajouterSuccesseur(e1);
        monde.ajouter(e1);
        e1.ajouterSuccesseur(sS);
        monde.aCommeSortie(sS);
        assertEquals(monde.toC(),"#include \"def.h\"\n" +
                "#define entree 0\n" +
                "#define Guichet 2\n" +
                "#define sortie 1\n" +
                "void simulation(int ids){\n" +
                "entrer(entree);\n" +
                "delai(2,1);\n" +
                "transfert(entree,Guichet);\n" +
                "delai(4,2);\n" +
                "P(ids,Guichet);\n" +
                "transfert(Guichet,sortie);\n" +
                "V(ids,Guichet);\n" +
                "}\n");
    }
}