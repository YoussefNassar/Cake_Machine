package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.*;

class ObsttorteImpTest {

    ObsttorteImp kuchen;

    @BeforeEach
    void setUpKuchen(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        kuchen  = new ObsttorteImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"kremsorte","apfel");
    }

    @Test
    void getKremsorte() {
        assertEquals("kremsorte",this.kuchen.getKremsorte());
    }

    @Test
    void getObstsorte() {
        assertEquals("apfel",this.kuchen.getObstsorte());
    }
}