package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

public class KremkuchenImp extends KuchenImp implements Kremkuchen, Serializable {

    String kremsorte;

    /**
     * constructor
     *
     * @param preis
     * @param hersteller
     * @param allergen
     * @param naehrwert
     * @param haltbarkeit is type Duration
     */
    public KremkuchenImp(BigDecimal preis, Hersteller hersteller, ArrayList<Allergen> allergen,
                         int naehrwert, Duration haltbarkeit, String kremsorte) {
        super(preis, hersteller, allergen, naehrwert, haltbarkeit,"Kremkuchen");
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte() {
        return this.kremsorte;
    }
}
