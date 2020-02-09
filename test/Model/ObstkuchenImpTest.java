package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.*;

class ObstkuchenImpTest {

    ObstkuchenImp kuchen;

    @BeforeEach
    void setUpKuchen(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        kuchen  = new ObstkuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"apfel");
    }

    @Test
    void getObstsorte() {
        assertEquals("apfel",this.kuchen.getObstsorte());
    }
}