package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HerstellerImpTest {

    @Test
    void getName() {
        Hersteller hersteller = new HerstellerImp("hersteller");
        assertEquals("hersteller",hersteller.getName());
    }
}