package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

public class ObsttorteImp extends KuchenImp implements Obsttorte, Serializable {

    String kremsorte;
    String obstsorte;

    /**
     * constructor
     *
     * @param preis
     * @param hersteller
     * @param allergen  is an Java Collection
     * @param naehrwert
     * @param haltbarkeit is type Duration
     */

    public ObsttorteImp(BigDecimal preis, Hersteller hersteller, ArrayList<Allergen> allergen,
                        int naehrwert, Duration haltbarkeit,String kremsorte,String obstsorte) {
        super(preis, hersteller, allergen, naehrwert, haltbarkeit,"Obsttorte");
        this.kremsorte = kremsorte;
        this.obstsorte = obstsorte;
    }

    @Override
    public String getKremsorte() {
        return this.kremsorte;
    }

    @Override
    public String getObstsorte() {
        return this.obstsorte;
    }
}
