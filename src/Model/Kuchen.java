package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

public interface Kuchen extends Serializable {

    /**
     * @return the price of the Kuchen
     */
    BigDecimal getPreis();

    /**
     * @return the manufacturer of the Kuchen
     */
    Hersteller getHersteller();

    /**
     * returns the allergene that can be caused by the Kauchen
     * @return
     */
    Collection<Allergen> getAllergene();
    /**
     * get the Naehrwet of the Kuchen
     * return an int
     */
    int getNaehrwert();
    /**
     * calculate if the Kuchen still eatable
     */
    Duration getHaltbarkeit();


    String getType();

    int getFachNummer();

    void setFachNummer(int a);

    void setInsertDate(LocalDate d);

}
