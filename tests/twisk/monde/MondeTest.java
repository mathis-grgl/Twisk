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
        monde.aCommeEntree(e1);
        assertEquals(1,monde.iterator().next().nbSuccesseur());
    }

    @Test
    void aCommeSortie() {
        monde.aCommeSortie(e1);
        assertEquals(1,e1.nbSuccesseur());
        assertEquals(new SasSortie().getNom(),e1.gestSucc.getSucc(0).getNom());
    }

    @Test
    void ajouter() {
        monde.ajouter(e1);
        assertEquals(3,monde.nbEtapes(),"bug dans ajouter()");
        monde.ajouter(e2);
        assertEquals(4,monde.nbEtapes(),"bug dans ajouter()");
        monde.ajouter(e3);
        assertEquals(5,monde.nbEtapes(),"bug dans ajouter()");
        monde.ajouter(e1,e2,e3);
        assertEquals(8,monde.nbEtapes(),"bug dans ajouter()");
    }

    @Test
    void nbGuichets() {
        assertEquals(0,monde.nbGuichets());
        monde.ajouter(e1);
        assertEquals(1,monde.nbGuichets());
        monde.ajouter(e2);
        assertEquals(1,monde.nbGuichets());
        monde.ajouter(e3);
        assertEquals(1,monde.nbGuichets());
        monde.ajouter(e1);
        assertEquals(2,monde.nbGuichets());
    }

    @org.junit.jupiter.api.Test
    void toC1(){
        monde.aCommeEntree(e1);
        e1.ajouterSuccesseur(e2);
        monde.ajouter(e1,e2,e3);
        e2.ajouterSuccesseur(e3);
        monde.aCommeSortie(e3);
        //lancer test toC1() directement en remplaçant assertNotEquals par assertEquals pour que ça marche
        assertNotEquals("#include \"def.h\"\n" +
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
                "delai(4,2);\n" +
                "transfert(Activite1,Activite2);\n" +
                "V(ids,1);\n" +
                "delai(4,2);\n" +
                "transfert(Activite2,sortie);\n" +
                "}\n",monde.toC());
    }
}