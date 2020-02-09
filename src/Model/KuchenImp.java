package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class KuchenImp implements Kuchen , Serializable {

    private BigDecimal preis;
    private Hersteller hersteller;
    private Collection<Allergen> allergen;
    private int naehrwert;
    private Duration haltbarkeit;
    private int fachNummer;
    private LocalDate insertDate;
    private String type;


    /**
     * constructor
     *
     * @param preis
     * @param hersteller
     * @param allergen
     * @param naehrwert
     * @param haltbarkeit is type Duration
     */
    public KuchenImp(BigDecimal preis, Hersteller hersteller, ArrayList<Allergen> allergen,
                     int naehrwert, Duration haltbarkeit, String type) {
        this.preis = preis;
        this.hersteller = hersteller;
        this.allergen = allergen;
        this.naehrwert = naehrwert;
        this.haltbarkeit = haltbarkeit;
        this.fachNummer = 0;
        this.insertDate = LocalDate.now();
        this.type = type;
    }



    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }


    public void setAllergen(Collection<Allergen> allergen) {
        this.allergen = allergen;
    }

    public void setNaehrwert(int naehrwert) {
        this.naehrwert = naehrwert;
    }

    public void setHaltbarkeit(Duration haltbarkeit) {
        this.haltbarkeit = haltbarkeit;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getFachNummer() {
        return fachNummer;
    }

    @Override
    public void setFachNummer(int fachNummer) {
        this.fachNummer = fachNummer;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }


    @Override
    public BigDecimal getPreis() {
        return this.preis;
    }

    @Override
    public Hersteller getHersteller() {
        return this.hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene() {
        return this.allergen;
    }



    @Override
    public int getNaehrwert() {
        return this.naehrwert;
    }

    @Override
    public Duration getHaltbarkeit() {
        return this.haltbarkeit;
    }



    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }


    @Override
    public String toString() {
        return this.hersteller.getName();
    }
}