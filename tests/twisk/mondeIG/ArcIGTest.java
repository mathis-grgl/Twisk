package twisk.mondeIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcIGTest {
    private PointDeControleIG p1,p2;
    private ArcIG a;
    private EtapeIG e;

    @BeforeEach
    void setUp() {
        e = new EtapeIG("Activite","0",100,200);
        p1 = new PointDeControleIG(200,300,"1",e);
        p2 = new PointDeControleIG(100,150,"1",e);
        a = new ArcIG(p1,p2);
    }

    @Test
    void getPosXp() {
        assertEquals(a.getP1().getPosX(),p1.getPosX());
        assertEquals(a.getP2().getPosX(),p2.getPosX());
    }

    @Test
    void getP1PosY() {
        assertEquals(a.getP1().getPosY(),p1.getPosY());
        assertEquals(a.getP2().getPosY(),p2.getPosY());
    }
}