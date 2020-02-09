package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

public class ObstkuchenImp extends KuchenImp implements Obstkuchen, Serializable {

    String obstsorte;

    /**
     * constructor
     *
     * @param preis
     * @param hersteller
     * @param allergen
     * @param naehrwert
     * @param haltbarkeit is type Duration
     */
    public ObstkuchenImp(BigDecimal preis, Hersteller hersteller, ArrayList<Allergen> allergen,
                         int naehrwert, Duration haltbarkeit, String obstsorte) {
        super(preis, hersteller, allergen, naehrwert, haltbarkeit,"ObstKuchen");
        this.obstsorte = obstsorte;
    }

    @Override
    public String getObstsorte() {
        return this.obstsorte;
    }
}
