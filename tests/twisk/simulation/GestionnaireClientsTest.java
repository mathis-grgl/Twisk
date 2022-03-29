package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.SasSortie;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    @Test
    void setClients() {
    }

    @Test
    void setNbClients() {
    }

    @Test
    void allerA() {
        GestionnaireClients gC = new GestionnaireClients();
        gC.setClients(10,20,30,40,50,60);
        gC.allerA(10,new SasSortie(),1);
        gC.allerA(20,new SasSortie(),2);
        gC.allerA(30,new SasSortie(),3);
        assertEquals(1,gC.getClient(0).getRang());
        assertEquals(2,gC.getClient(1).getRang());
        assertEquals(3,gC.getClient(2).getRang());
    }

    @Test
    void nettoyer() {
    }

    @Test
    void size() {
    }

    @Test
    void iterator() {
    }
}