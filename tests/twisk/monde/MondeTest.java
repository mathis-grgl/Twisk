package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {
    private Monde monde;
    private GestionnaireEtapes gE;
    private Etape e1,e2,e3;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        gE = new GestionnaireEtapes();
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
        monde.ajouter(e2);
        assertEquals(monde.nbEtapes(),4,"bug dans ajouter()");
        monde.ajouter(e3);
        assertEquals(monde.nbEtapes(),5,"bug dans ajouter()");
        monde.ajouter(e1,e2,e3);
        assertEquals(monde.nbEtapes(),8,"bug dans ajouter()");
    }

    @Test
    void nbGuichets() {
        assertEquals(monde.nbGuichets(),0);
        monde.ajouter(e1);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(e2);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(e3);
        assertEquals(monde.nbGuichets(),1);
        monde.ajouter(e1);
        assertEquals(monde.nbGuichets(),2);
    }

    @org.junit.jupiter.api.Test
    void toC1(){
        monde.aCommeEntree(e1);
        e1.ajouterSuccesseur(e2);
        monde.ajouter(e1,e2,e3);
        e2.ajouterSuccesseur(e3);
        monde.aCommeSortie(e3);
        assertEquals(monde.toC(),"#include \"def.h\"\n" +
                "#define entree 0\n" +
                "#define sortie 1\n" +
                "#define Guichet 2\n" +
                "#define Activite1 3\n" +
                "#define Activite2 4\n" +
                "void simulation(int ids){\n" +
                "entrer(entree);\n" +
                "delai(4,2);\n" +
                "transfert(entree,Guichet);\n" +
                "P(ids,1);\n" +
                "transfert(Guichet,Activite1);\n" +
                "V(ids,1);\n" +
                "delai(4,2);\n" +
                "transfert(Activite1,Activite2);\n" +
                "delai(4,2);\n" +
                "transfert(Activite2,sortie);\n" +
                "}\n");
    }
}