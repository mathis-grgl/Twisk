package twisk.outils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueIdentifiantTest {


    @Test
    void getInstance() {
        assertNotEquals(FabriqueIdentifiant.getInstance(),null,"Bug dans getInstance");
    }

    @Test
    void getIdentifiantEtape() {
        assertEquals(FabriqueIdentifiant.getInstance().getIdentifiantEtape(),"0","Bug dans getIdentifiantEtape()");
        assertEquals(FabriqueIdentifiant.getInstance().getIdentifiantEtape(),"1","Bug dans getIdentifiantEtape()");
        assertEquals(FabriqueIdentifiant.getInstance().getIdentifiantEtape(),"2","Bug dans getIdentifiantEtape()");
        assertEquals(FabriqueIdentifiant.getInstance().getIdentifiantEtape(),"3","Bug dans getIdentifiantEtape()");
        assertEquals(FabriqueIdentifiant.getInstance().getIdentifiantEtape(),"4","Bug dans getIdentifiantEtape()");
    }
}